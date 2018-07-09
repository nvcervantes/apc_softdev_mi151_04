package okhttp3.internal.http;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Address;
import okhttp3.Authenticator;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.connection.StreamAllocation;

public final class RetryAndFollowUpInterceptor
  implements Interceptor
{
  private static final int MAX_FOLLOW_UPS = 20;
  private Object callStackTrace;
  private volatile boolean canceled;
  private final OkHttpClient client;
  private final boolean forWebSocket;
  private StreamAllocation streamAllocation;
  
  public RetryAndFollowUpInterceptor(OkHttpClient paramOkHttpClient, boolean paramBoolean)
  {
    client = paramOkHttpClient;
    forWebSocket = paramBoolean;
  }
  
  private Address createAddress(HttpUrl paramHttpUrl)
  {
    Object localObject2;
    Object localObject1;
    CertificatePinner localCertificatePinner;
    Object localObject3;
    if (paramHttpUrl.isHttps())
    {
      localObject2 = client.sslSocketFactory();
      localObject1 = client.hostnameVerifier();
      localCertificatePinner = client.certificatePinner();
      localObject3 = localCertificatePinner;
    }
    else
    {
      localCertificatePinner = null;
      localObject1 = localCertificatePinner;
      localObject3 = localObject1;
      localObject2 = localCertificatePinner;
    }
    return new Address(paramHttpUrl.host(), paramHttpUrl.port(), client.dns(), client.socketFactory(), (SSLSocketFactory)localObject2, (HostnameVerifier)localObject1, (CertificatePinner)localObject3, client.proxyAuthenticator(), client.proxy(), client.protocols(), client.connectionSpecs(), client.proxySelector());
  }
  
  private Request followUpRequest(Response paramResponse)
    throws IOException
  {
    if (paramResponse == null) {
      throw new IllegalStateException();
    }
    Object localObject = streamAllocation.connection();
    Proxy localProxy = null;
    if (localObject != null) {
      localObject = ((Connection)localObject).route();
    } else {
      localObject = null;
    }
    int i = paramResponse.code();
    String str = paramResponse.request().method();
    switch (i)
    {
    default: 
      return null;
    case 408: 
      if (!client.retryOnConnectionFailure()) {
        return null;
      }
      if ((paramResponse.request().body() instanceof UnrepeatableRequestBody)) {
        return null;
      }
      if ((paramResponse.priorResponse() != null) && (paramResponse.priorResponse().code() == 408)) {
        return null;
      }
      return paramResponse.request();
    case 407: 
      if (localObject != null) {
        localProxy = ((Route)localObject).proxy();
      } else {
        localProxy = client.proxy();
      }
      if (localProxy.type() != Proxy.Type.HTTP) {
        throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
      }
      return client.proxyAuthenticator().authenticate((Route)localObject, paramResponse);
    case 401: 
      return client.authenticator().authenticate((Route)localObject, paramResponse);
    case 307: 
    case 308: 
      if ((!str.equals("GET")) && (!str.equals("HEAD"))) {
        return null;
      }
      break;
    }
    if (!client.followRedirects()) {
      return null;
    }
    localObject = paramResponse.header("Location");
    if (localObject == null) {
      return null;
    }
    HttpUrl localHttpUrl = paramResponse.request().url().resolve((String)localObject);
    if (localHttpUrl == null) {
      return null;
    }
    if ((!localHttpUrl.scheme().equals(paramResponse.request().url().scheme())) && (!client.followSslRedirects())) {
      return null;
    }
    Request.Builder localBuilder = paramResponse.request().newBuilder();
    if (HttpMethod.permitsRequestBody(str))
    {
      boolean bool = HttpMethod.redirectsWithBody(str);
      if (HttpMethod.redirectsToGet(str))
      {
        localBuilder.method("GET", null);
      }
      else
      {
        localObject = localProxy;
        if (bool) {
          localObject = paramResponse.request().body();
        }
        localBuilder.method(str, (RequestBody)localObject);
      }
      if (!bool)
      {
        localBuilder.removeHeader("Transfer-Encoding");
        localBuilder.removeHeader("Content-Length");
        localBuilder.removeHeader("Content-Type");
      }
    }
    if (!sameConnection(paramResponse, localHttpUrl)) {
      localBuilder.removeHeader("Authorization");
    }
    return localBuilder.url(localHttpUrl).build();
  }
  
  private boolean isRecoverable(IOException paramIOException, boolean paramBoolean)
  {
    boolean bool1 = paramIOException instanceof ProtocolException;
    boolean bool2 = false;
    if (bool1) {
      return false;
    }
    if ((paramIOException instanceof InterruptedIOException))
    {
      bool1 = bool2;
      if ((paramIOException instanceof SocketTimeoutException))
      {
        bool1 = bool2;
        if (!paramBoolean) {
          bool1 = true;
        }
      }
      return bool1;
    }
    if (((paramIOException instanceof SSLHandshakeException)) && ((paramIOException.getCause() instanceof CertificateException))) {
      return false;
    }
    return !(paramIOException instanceof SSLPeerUnverifiedException);
  }
  
  private boolean recover(IOException paramIOException, boolean paramBoolean, Request paramRequest)
  {
    streamAllocation.streamFailed(paramIOException);
    if (!client.retryOnConnectionFailure()) {
      return false;
    }
    if ((paramBoolean) && ((paramRequest.body() instanceof UnrepeatableRequestBody))) {
      return false;
    }
    if (!isRecoverable(paramIOException, paramBoolean)) {
      return false;
    }
    return streamAllocation.hasMoreRoutes();
  }
  
  private boolean sameConnection(Response paramResponse, HttpUrl paramHttpUrl)
  {
    paramResponse = paramResponse.request().url();
    return (paramResponse.host().equals(paramHttpUrl.host())) && (paramResponse.port() == paramHttpUrl.port()) && (paramResponse.scheme().equals(paramHttpUrl.scheme()));
  }
  
  public void cancel()
  {
    canceled = true;
    StreamAllocation localStreamAllocation = streamAllocation;
    if (localStreamAllocation != null) {
      localStreamAllocation.cancel();
    }
  }
  
  /* Error */
  public Response intercept(okhttp3.Interceptor.Chain paramChain)
    throws IOException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface 285 1 0
    //   6: astore 5
    //   8: aload_1
    //   9: checkcast 287	okhttp3/internal/http/RealInterceptorChain
    //   12: astore 6
    //   14: aload 6
    //   16: invokevirtual 291	okhttp3/internal/http/RealInterceptorChain:call	()Lokhttp3/Call;
    //   19: astore 7
    //   21: aload 6
    //   23: invokevirtual 295	okhttp3/internal/http/RealInterceptorChain:eventListener	()Lokhttp3/EventListener;
    //   26: astore 8
    //   28: aload_0
    //   29: new 101	okhttp3/internal/connection/StreamAllocation
    //   32: dup
    //   33: aload_0
    //   34: getfield 25	okhttp3/internal/http/RetryAndFollowUpInterceptor:client	Lokhttp3/OkHttpClient;
    //   37: invokevirtual 299	okhttp3/OkHttpClient:connectionPool	()Lokhttp3/ConnectionPool;
    //   40: aload_0
    //   41: aload 5
    //   43: invokevirtual 192	okhttp3/Request:url	()Lokhttp3/HttpUrl;
    //   46: invokespecial 301	okhttp3/internal/http/RetryAndFollowUpInterceptor:createAddress	(Lokhttp3/HttpUrl;)Lokhttp3/Address;
    //   49: aload 7
    //   51: aload 8
    //   53: aload_0
    //   54: getfield 303	okhttp3/internal/http/RetryAndFollowUpInterceptor:callStackTrace	Ljava/lang/Object;
    //   57: invokespecial 306	okhttp3/internal/connection/StreamAllocation:<init>	(Lokhttp3/ConnectionPool;Lokhttp3/Address;Lokhttp3/Call;Lokhttp3/EventListener;Ljava/lang/Object;)V
    //   60: putfield 99	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   63: iconst_0
    //   64: istore_2
    //   65: aconst_null
    //   66: astore 4
    //   68: aload 5
    //   70: astore_1
    //   71: aload_0
    //   72: getfield 276	okhttp3/internal/http/RetryAndFollowUpInterceptor:canceled	Z
    //   75: ifeq +21 -> 96
    //   78: aload_0
    //   79: getfield 99	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   82: invokevirtual 309	okhttp3/internal/connection/StreamAllocation:release	()V
    //   85: new 94	java/io/IOException
    //   88: dup
    //   89: ldc_w 311
    //   92: invokespecial 312	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   95: athrow
    //   96: aload 6
    //   98: aload_1
    //   99: aload_0
    //   100: getfield 99	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   103: aconst_null
    //   104: aconst_null
    //   105: invokevirtual 316	okhttp3/internal/http/RealInterceptorChain:proceed	(Lokhttp3/Request;Lokhttp3/internal/connection/StreamAllocation;Lokhttp3/internal/http/HttpCodec;Lokhttp3/internal/connection/RealConnection;)Lokhttp3/Response;
    //   108: astore 5
    //   110: aload 4
    //   112: ifnull +30 -> 142
    //   115: aload 5
    //   117: invokevirtual 319	okhttp3/Response:newBuilder	()Lokhttp3/Response$Builder;
    //   120: aload 4
    //   122: invokevirtual 319	okhttp3/Response:newBuilder	()Lokhttp3/Response$Builder;
    //   125: aconst_null
    //   126: invokevirtual 324	okhttp3/Response$Builder:body	(Lokhttp3/ResponseBody;)Lokhttp3/Response$Builder;
    //   129: invokevirtual 326	okhttp3/Response$Builder:build	()Lokhttp3/Response;
    //   132: invokevirtual 329	okhttp3/Response$Builder:priorResponse	(Lokhttp3/Response;)Lokhttp3/Response$Builder;
    //   135: invokevirtual 326	okhttp3/Response$Builder:build	()Lokhttp3/Response;
    //   138: astore_1
    //   139: goto +6 -> 145
    //   142: aload 5
    //   144: astore_1
    //   145: aload_0
    //   146: aload_1
    //   147: invokespecial 331	okhttp3/internal/http/RetryAndFollowUpInterceptor:followUpRequest	(Lokhttp3/Response;)Lokhttp3/Request;
    //   150: astore 5
    //   152: aload 5
    //   154: ifnonnull +19 -> 173
    //   157: aload_0
    //   158: getfield 27	okhttp3/internal/http/RetryAndFollowUpInterceptor:forWebSocket	Z
    //   161: ifne +10 -> 171
    //   164: aload_0
    //   165: getfield 99	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   168: invokevirtual 309	okhttp3/internal/connection/StreamAllocation:release	()V
    //   171: aload_1
    //   172: areturn
    //   173: aload_1
    //   174: invokevirtual 334	okhttp3/Response:body	()Lokhttp3/ResponseBody;
    //   177: invokestatic 340	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   180: iload_2
    //   181: iconst_1
    //   182: iadd
    //   183: istore_2
    //   184: iload_2
    //   185: bipush 20
    //   187: if_icmple +44 -> 231
    //   190: aload_0
    //   191: getfield 99	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   194: invokevirtual 309	okhttp3/internal/connection/StreamAllocation:release	()V
    //   197: new 342	java/lang/StringBuilder
    //   200: dup
    //   201: invokespecial 343	java/lang/StringBuilder:<init>	()V
    //   204: astore_1
    //   205: aload_1
    //   206: ldc_w 345
    //   209: invokevirtual 349	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: pop
    //   213: aload_1
    //   214: iload_2
    //   215: invokevirtual 352	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   218: pop
    //   219: new 155	java/net/ProtocolException
    //   222: dup
    //   223: aload_1
    //   224: invokevirtual 355	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   227: invokespecial 160	java/net/ProtocolException:<init>	(Ljava/lang/String;)V
    //   230: athrow
    //   231: aload 5
    //   233: invokevirtual 132	okhttp3/Request:body	()Lokhttp3/RequestBody;
    //   236: instanceof 134
    //   239: ifeq +25 -> 264
    //   242: aload_0
    //   243: getfield 99	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   246: invokevirtual 309	okhttp3/internal/connection/StreamAllocation:release	()V
    //   249: new 357	java/net/HttpRetryException
    //   252: dup
    //   253: ldc_w 359
    //   256: aload_1
    //   257: invokevirtual 116	okhttp3/Response:code	()I
    //   260: invokespecial 362	java/net/HttpRetryException:<init>	(Ljava/lang/String;I)V
    //   263: athrow
    //   264: aload_0
    //   265: aload_1
    //   266: aload 5
    //   268: invokevirtual 192	okhttp3/Request:url	()Lokhttp3/HttpUrl;
    //   271: invokespecial 237	okhttp3/internal/http/RetryAndFollowUpInterceptor:sameConnection	(Lokhttp3/Response;Lokhttp3/HttpUrl;)Z
    //   274: ifne +48 -> 322
    //   277: aload_0
    //   278: getfield 99	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   281: invokevirtual 309	okhttp3/internal/connection/StreamAllocation:release	()V
    //   284: aload_0
    //   285: new 101	okhttp3/internal/connection/StreamAllocation
    //   288: dup
    //   289: aload_0
    //   290: getfield 25	okhttp3/internal/http/RetryAndFollowUpInterceptor:client	Lokhttp3/OkHttpClient;
    //   293: invokevirtual 299	okhttp3/OkHttpClient:connectionPool	()Lokhttp3/ConnectionPool;
    //   296: aload_0
    //   297: aload 5
    //   299: invokevirtual 192	okhttp3/Request:url	()Lokhttp3/HttpUrl;
    //   302: invokespecial 301	okhttp3/internal/http/RetryAndFollowUpInterceptor:createAddress	(Lokhttp3/HttpUrl;)Lokhttp3/Address;
    //   305: aload 7
    //   307: aload 8
    //   309: aload_0
    //   310: getfield 303	okhttp3/internal/http/RetryAndFollowUpInterceptor:callStackTrace	Ljava/lang/Object;
    //   313: invokespecial 306	okhttp3/internal/connection/StreamAllocation:<init>	(Lokhttp3/ConnectionPool;Lokhttp3/Address;Lokhttp3/Call;Lokhttp3/EventListener;Ljava/lang/Object;)V
    //   316: putfield 99	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   319: goto +60 -> 379
    //   322: aload_0
    //   323: getfield 99	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   326: invokevirtual 366	okhttp3/internal/connection/StreamAllocation:codec	()Lokhttp3/internal/http/HttpCodec;
    //   329: ifnull +50 -> 379
    //   332: new 342	java/lang/StringBuilder
    //   335: dup
    //   336: invokespecial 343	java/lang/StringBuilder:<init>	()V
    //   339: astore 4
    //   341: aload 4
    //   343: ldc_w 368
    //   346: invokevirtual 349	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   349: pop
    //   350: aload 4
    //   352: aload_1
    //   353: invokevirtual 371	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   356: pop
    //   357: aload 4
    //   359: ldc_w 373
    //   362: invokevirtual 349	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   365: pop
    //   366: new 96	java/lang/IllegalStateException
    //   369: dup
    //   370: aload 4
    //   372: invokevirtual 355	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   375: invokespecial 374	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   378: athrow
    //   379: aload_1
    //   380: astore 4
    //   382: aload 5
    //   384: astore_1
    //   385: goto -314 -> 71
    //   388: astore_1
    //   389: goto +54 -> 443
    //   392: astore 5
    //   394: aload 5
    //   396: instanceof 376
    //   399: ifne +61 -> 460
    //   402: iconst_1
    //   403: istore_3
    //   404: goto +3 -> 407
    //   407: aload_0
    //   408: aload 5
    //   410: iload_3
    //   411: aload_1
    //   412: invokespecial 378	okhttp3/internal/http/RetryAndFollowUpInterceptor:recover	(Ljava/io/IOException;ZLokhttp3/Request;)Z
    //   415: ifne -344 -> 71
    //   418: aload 5
    //   420: athrow
    //   421: astore 5
    //   423: aload_0
    //   424: aload 5
    //   426: invokevirtual 382	okhttp3/internal/connection/RouteException:getLastConnectException	()Ljava/io/IOException;
    //   429: iconst_0
    //   430: aload_1
    //   431: invokespecial 378	okhttp3/internal/http/RetryAndFollowUpInterceptor:recover	(Ljava/io/IOException;ZLokhttp3/Request;)Z
    //   434: ifne -363 -> 71
    //   437: aload 5
    //   439: invokevirtual 382	okhttp3/internal/connection/RouteException:getLastConnectException	()Ljava/io/IOException;
    //   442: athrow
    //   443: aload_0
    //   444: getfield 99	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   447: aconst_null
    //   448: invokevirtual 268	okhttp3/internal/connection/StreamAllocation:streamFailed	(Ljava/io/IOException;)V
    //   451: aload_0
    //   452: getfield 99	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   455: invokevirtual 309	okhttp3/internal/connection/StreamAllocation:release	()V
    //   458: aload_1
    //   459: athrow
    //   460: iconst_0
    //   461: istore_3
    //   462: goto -55 -> 407
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	465	0	this	RetryAndFollowUpInterceptor
    //   0	465	1	paramChain	okhttp3.Interceptor.Chain
    //   64	151	2	i	int
    //   403	59	3	bool	boolean
    //   66	315	4	localObject1	Object
    //   6	377	5	localObject2	Object
    //   392	27	5	localIOException	IOException
    //   421	17	5	localRouteException	okhttp3.internal.connection.RouteException
    //   12	85	6	localRealInterceptorChain	RealInterceptorChain
    //   19	287	7	localCall	okhttp3.Call
    //   26	282	8	localEventListener	okhttp3.EventListener
    // Exception table:
    //   from	to	target	type
    //   96	110	388	finally
    //   394	402	388	finally
    //   407	421	388	finally
    //   423	443	388	finally
    //   96	110	392	java/io/IOException
    //   96	110	421	okhttp3/internal/connection/RouteException
  }
  
  public boolean isCanceled()
  {
    return canceled;
  }
  
  public void setCallStackTrace(Object paramObject)
  {
    callStackTrace = paramObject;
  }
  
  public StreamAllocation streamAllocation()
  {
    return streamAllocation;
  }
}

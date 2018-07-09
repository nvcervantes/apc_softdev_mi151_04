package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.Address;
import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http1.Http1Codec;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Codec;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2Connection.Builder;
import okhttp3.internal.http2.Http2Connection.Listener;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket.Streams;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Source;
import okio.Timeout;

public final class RealConnection
  extends Http2Connection.Listener
  implements Connection
{
  private static final int MAX_TUNNEL_ATTEMPTS = 21;
  private static final String NPE_THROW_WITH_NULL = "throw with null exception";
  public int allocationLimit = 1;
  public final List<Reference<StreamAllocation>> allocations = new ArrayList();
  private final ConnectionPool connectionPool;
  private Handshake handshake;
  private Http2Connection http2Connection;
  public long idleAtNanos = Long.MAX_VALUE;
  public boolean noNewStreams;
  private Protocol protocol;
  private Socket rawSocket;
  private final Route route;
  private BufferedSink sink;
  private Socket socket;
  private BufferedSource source;
  public int successCount;
  
  public RealConnection(ConnectionPool paramConnectionPool, Route paramRoute)
  {
    connectionPool = paramConnectionPool;
    route = paramRoute;
  }
  
  /* Error */
  private void connectSocket(int paramInt1, int paramInt2, Call paramCall, EventListener paramEventListener)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 61	okhttp3/internal/connection/RealConnection:route	Lokhttp3/Route;
    //   4: invokevirtual 76	okhttp3/Route:proxy	()Ljava/net/Proxy;
    //   7: astore 6
    //   9: aload_0
    //   10: getfield 61	okhttp3/internal/connection/RealConnection:route	Lokhttp3/Route;
    //   13: invokevirtual 80	okhttp3/Route:address	()Lokhttp3/Address;
    //   16: astore 5
    //   18: aload 6
    //   20: invokevirtual 86	java/net/Proxy:type	()Ljava/net/Proxy$Type;
    //   23: getstatic 92	java/net/Proxy$Type:DIRECT	Ljava/net/Proxy$Type;
    //   26: if_acmpeq +31 -> 57
    //   29: aload 6
    //   31: invokevirtual 86	java/net/Proxy:type	()Ljava/net/Proxy$Type;
    //   34: getstatic 95	java/net/Proxy$Type:HTTP	Ljava/net/Proxy$Type;
    //   37: if_acmpne +6 -> 43
    //   40: goto +17 -> 57
    //   43: new 97	java/net/Socket
    //   46: dup
    //   47: aload 6
    //   49: invokespecial 100	java/net/Socket:<init>	(Ljava/net/Proxy;)V
    //   52: astore 5
    //   54: goto +13 -> 67
    //   57: aload 5
    //   59: invokevirtual 106	okhttp3/Address:socketFactory	()Ljavax/net/SocketFactory;
    //   62: invokevirtual 112	javax/net/SocketFactory:createSocket	()Ljava/net/Socket;
    //   65: astore 5
    //   67: aload_0
    //   68: aload 5
    //   70: putfield 114	okhttp3/internal/connection/RealConnection:rawSocket	Ljava/net/Socket;
    //   73: aload 4
    //   75: aload_3
    //   76: aload_0
    //   77: getfield 61	okhttp3/internal/connection/RealConnection:route	Lokhttp3/Route;
    //   80: invokevirtual 118	okhttp3/Route:socketAddress	()Ljava/net/InetSocketAddress;
    //   83: aload 6
    //   85: invokevirtual 124	okhttp3/EventListener:connectStart	(Lokhttp3/Call;Ljava/net/InetSocketAddress;Ljava/net/Proxy;)V
    //   88: aload_0
    //   89: getfield 114	okhttp3/internal/connection/RealConnection:rawSocket	Ljava/net/Socket;
    //   92: iload_2
    //   93: invokevirtual 128	java/net/Socket:setSoTimeout	(I)V
    //   96: invokestatic 134	okhttp3/internal/platform/Platform:get	()Lokhttp3/internal/platform/Platform;
    //   99: aload_0
    //   100: getfield 114	okhttp3/internal/connection/RealConnection:rawSocket	Ljava/net/Socket;
    //   103: aload_0
    //   104: getfield 61	okhttp3/internal/connection/RealConnection:route	Lokhttp3/Route;
    //   107: invokevirtual 118	okhttp3/Route:socketAddress	()Ljava/net/InetSocketAddress;
    //   110: iload_1
    //   111: invokevirtual 137	okhttp3/internal/platform/Platform:connectSocket	(Ljava/net/Socket;Ljava/net/InetSocketAddress;I)V
    //   114: aload_0
    //   115: aload_0
    //   116: getfield 114	okhttp3/internal/connection/RealConnection:rawSocket	Ljava/net/Socket;
    //   119: invokestatic 142	okio/Okio:source	(Ljava/net/Socket;)Lokio/Source;
    //   122: invokestatic 146	okio/Okio:buffer	(Lokio/Source;)Lokio/BufferedSource;
    //   125: putfield 148	okhttp3/internal/connection/RealConnection:source	Lokio/BufferedSource;
    //   128: aload_0
    //   129: aload_0
    //   130: getfield 114	okhttp3/internal/connection/RealConnection:rawSocket	Ljava/net/Socket;
    //   133: invokestatic 151	okio/Okio:sink	(Ljava/net/Socket;)Lokio/Sink;
    //   136: invokestatic 154	okio/Okio:buffer	(Lokio/Sink;)Lokio/BufferedSink;
    //   139: putfield 156	okhttp3/internal/connection/RealConnection:sink	Lokio/BufferedSink;
    //   142: return
    //   143: astore_3
    //   144: ldc 15
    //   146: aload_3
    //   147: invokevirtual 160	java/lang/NullPointerException:getMessage	()Ljava/lang/String;
    //   150: invokevirtual 166	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   153: ifeq +12 -> 165
    //   156: new 66	java/io/IOException
    //   159: dup
    //   160: aload_3
    //   161: invokespecial 169	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   164: athrow
    //   165: return
    //   166: astore_3
    //   167: new 171	java/lang/StringBuilder
    //   170: dup
    //   171: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   174: astore 4
    //   176: aload 4
    //   178: ldc -82
    //   180: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: pop
    //   184: aload 4
    //   186: aload_0
    //   187: getfield 61	okhttp3/internal/connection/RealConnection:route	Lokhttp3/Route;
    //   190: invokevirtual 118	okhttp3/Route:socketAddress	()Ljava/net/InetSocketAddress;
    //   193: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   196: pop
    //   197: new 68	java/net/ConnectException
    //   200: dup
    //   201: aload 4
    //   203: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   206: invokespecial 187	java/net/ConnectException:<init>	(Ljava/lang/String;)V
    //   209: astore 4
    //   211: aload 4
    //   213: aload_3
    //   214: invokevirtual 191	java/net/ConnectException:initCause	(Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   217: pop
    //   218: aload 4
    //   220: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	221	0	this	RealConnection
    //   0	221	1	paramInt1	int
    //   0	221	2	paramInt2	int
    //   0	221	3	paramCall	Call
    //   0	221	4	paramEventListener	EventListener
    //   16	53	5	localObject	Object
    //   7	77	6	localProxy	Proxy
    // Exception table:
    //   from	to	target	type
    //   114	142	143	java/lang/NullPointerException
    //   96	114	166	java/net/ConnectException
  }
  
  /* Error */
  private void connectTls(ConnectionSpecSelector paramConnectionSpecSelector)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 61	okhttp3/internal/connection/RealConnection:route	Lokhttp3/Route;
    //   4: invokevirtual 80	okhttp3/Route:address	()Lokhttp3/Address;
    //   7: astore 6
    //   9: aload 6
    //   11: invokevirtual 200	okhttp3/Address:sslSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
    //   14: astore_3
    //   15: aconst_null
    //   16: astore_2
    //   17: aconst_null
    //   18: astore 5
    //   20: aconst_null
    //   21: astore 4
    //   23: aload_3
    //   24: aload_0
    //   25: getfield 114	okhttp3/internal/connection/RealConnection:rawSocket	Ljava/net/Socket;
    //   28: aload 6
    //   30: invokevirtual 204	okhttp3/Address:url	()Lokhttp3/HttpUrl;
    //   33: invokevirtual 209	okhttp3/HttpUrl:host	()Ljava/lang/String;
    //   36: aload 6
    //   38: invokevirtual 204	okhttp3/Address:url	()Lokhttp3/HttpUrl;
    //   41: invokevirtual 213	okhttp3/HttpUrl:port	()I
    //   44: iconst_1
    //   45: invokevirtual 218	javax/net/ssl/SSLSocketFactory:createSocket	(Ljava/net/Socket;Ljava/lang/String;IZ)Ljava/net/Socket;
    //   48: checkcast 220	javax/net/ssl/SSLSocket
    //   51: astore_3
    //   52: aload_1
    //   53: aload_3
    //   54: invokevirtual 226	okhttp3/internal/connection/ConnectionSpecSelector:configureSecureSocket	(Ljavax/net/ssl/SSLSocket;)Lokhttp3/ConnectionSpec;
    //   57: astore 5
    //   59: aload 5
    //   61: invokevirtual 232	okhttp3/ConnectionSpec:supportsTlsExtensions	()Z
    //   64: ifeq +23 -> 87
    //   67: invokestatic 134	okhttp3/internal/platform/Platform:get	()Lokhttp3/internal/platform/Platform;
    //   70: aload_3
    //   71: aload 6
    //   73: invokevirtual 204	okhttp3/Address:url	()Lokhttp3/HttpUrl;
    //   76: invokevirtual 209	okhttp3/HttpUrl:host	()Ljava/lang/String;
    //   79: aload 6
    //   81: invokevirtual 236	okhttp3/Address:protocols	()Ljava/util/List;
    //   84: invokevirtual 240	okhttp3/internal/platform/Platform:configureTlsExtensions	(Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V
    //   87: aload_3
    //   88: invokevirtual 243	javax/net/ssl/SSLSocket:startHandshake	()V
    //   91: aload_3
    //   92: invokevirtual 247	javax/net/ssl/SSLSocket:getSession	()Ljavax/net/ssl/SSLSession;
    //   95: invokestatic 252	okhttp3/Handshake:get	(Ljavax/net/ssl/SSLSession;)Lokhttp3/Handshake;
    //   98: astore_2
    //   99: aload 6
    //   101: invokevirtual 256	okhttp3/Address:hostnameVerifier	()Ljavax/net/ssl/HostnameVerifier;
    //   104: aload 6
    //   106: invokevirtual 204	okhttp3/Address:url	()Lokhttp3/HttpUrl;
    //   109: invokevirtual 209	okhttp3/HttpUrl:host	()Ljava/lang/String;
    //   112: aload_3
    //   113: invokevirtual 247	javax/net/ssl/SSLSocket:getSession	()Ljavax/net/ssl/SSLSession;
    //   116: invokeinterface 262 3 0
    //   121: ifne +114 -> 235
    //   124: aload_2
    //   125: invokevirtual 265	okhttp3/Handshake:peerCertificates	()Ljava/util/List;
    //   128: iconst_0
    //   129: invokeinterface 270 2 0
    //   134: checkcast 272	java/security/cert/X509Certificate
    //   137: astore_1
    //   138: new 171	java/lang/StringBuilder
    //   141: dup
    //   142: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   145: astore_2
    //   146: aload_2
    //   147: ldc_w 274
    //   150: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   153: pop
    //   154: aload_2
    //   155: aload 6
    //   157: invokevirtual 204	okhttp3/Address:url	()Lokhttp3/HttpUrl;
    //   160: invokevirtual 209	okhttp3/HttpUrl:host	()Ljava/lang/String;
    //   163: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: aload_2
    //   168: ldc_w 276
    //   171: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: pop
    //   175: aload_2
    //   176: aload_1
    //   177: invokestatic 282	okhttp3/CertificatePinner:pin	(Ljava/security/cert/Certificate;)Ljava/lang/String;
    //   180: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: pop
    //   184: aload_2
    //   185: ldc_w 284
    //   188: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: pop
    //   192: aload_2
    //   193: aload_1
    //   194: invokevirtual 288	java/security/cert/X509Certificate:getSubjectDN	()Ljava/security/Principal;
    //   197: invokeinterface 293 1 0
    //   202: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: pop
    //   206: aload_2
    //   207: ldc_w 295
    //   210: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   213: pop
    //   214: aload_2
    //   215: aload_1
    //   216: invokestatic 301	okhttp3/internal/tls/OkHostnameVerifier:allSubjectAltNames	(Ljava/security/cert/X509Certificate;)Ljava/util/List;
    //   219: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   222: pop
    //   223: new 303	javax/net/ssl/SSLPeerUnverifiedException
    //   226: dup
    //   227: aload_2
    //   228: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   231: invokespecial 304	javax/net/ssl/SSLPeerUnverifiedException:<init>	(Ljava/lang/String;)V
    //   234: athrow
    //   235: aload 6
    //   237: invokevirtual 308	okhttp3/Address:certificatePinner	()Lokhttp3/CertificatePinner;
    //   240: aload 6
    //   242: invokevirtual 204	okhttp3/Address:url	()Lokhttp3/HttpUrl;
    //   245: invokevirtual 209	okhttp3/HttpUrl:host	()Ljava/lang/String;
    //   248: aload_2
    //   249: invokevirtual 265	okhttp3/Handshake:peerCertificates	()Ljava/util/List;
    //   252: invokevirtual 312	okhttp3/CertificatePinner:check	(Ljava/lang/String;Ljava/util/List;)V
    //   255: aload 4
    //   257: astore_1
    //   258: aload 5
    //   260: invokevirtual 232	okhttp3/ConnectionSpec:supportsTlsExtensions	()Z
    //   263: ifeq +11 -> 274
    //   266: invokestatic 134	okhttp3/internal/platform/Platform:get	()Lokhttp3/internal/platform/Platform;
    //   269: aload_3
    //   270: invokevirtual 316	okhttp3/internal/platform/Platform:getSelectedProtocol	(Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;
    //   273: astore_1
    //   274: aload_0
    //   275: aload_3
    //   276: putfield 318	okhttp3/internal/connection/RealConnection:socket	Ljava/net/Socket;
    //   279: aload_0
    //   280: aload_0
    //   281: getfield 318	okhttp3/internal/connection/RealConnection:socket	Ljava/net/Socket;
    //   284: invokestatic 142	okio/Okio:source	(Ljava/net/Socket;)Lokio/Source;
    //   287: invokestatic 146	okio/Okio:buffer	(Lokio/Source;)Lokio/BufferedSource;
    //   290: putfield 148	okhttp3/internal/connection/RealConnection:source	Lokio/BufferedSource;
    //   293: aload_0
    //   294: aload_0
    //   295: getfield 318	okhttp3/internal/connection/RealConnection:socket	Ljava/net/Socket;
    //   298: invokestatic 151	okio/Okio:sink	(Ljava/net/Socket;)Lokio/Sink;
    //   301: invokestatic 154	okio/Okio:buffer	(Lokio/Sink;)Lokio/BufferedSink;
    //   304: putfield 156	okhttp3/internal/connection/RealConnection:sink	Lokio/BufferedSink;
    //   307: aload_0
    //   308: aload_2
    //   309: putfield 320	okhttp3/internal/connection/RealConnection:handshake	Lokhttp3/Handshake;
    //   312: aload_1
    //   313: ifnull +11 -> 324
    //   316: aload_1
    //   317: invokestatic 325	okhttp3/Protocol:get	(Ljava/lang/String;)Lokhttp3/Protocol;
    //   320: astore_1
    //   321: goto +7 -> 328
    //   324: getstatic 328	okhttp3/Protocol:HTTP_1_1	Lokhttp3/Protocol;
    //   327: astore_1
    //   328: aload_0
    //   329: aload_1
    //   330: putfield 330	okhttp3/internal/connection/RealConnection:protocol	Lokhttp3/Protocol;
    //   333: aload_3
    //   334: ifnull +10 -> 344
    //   337: invokestatic 134	okhttp3/internal/platform/Platform:get	()Lokhttp3/internal/platform/Platform;
    //   340: aload_3
    //   341: invokevirtual 334	okhttp3/internal/platform/Platform:afterHandshake	(Ljavax/net/ssl/SSLSocket;)V
    //   344: return
    //   345: astore_1
    //   346: goto +45 -> 391
    //   349: astore_2
    //   350: aload_3
    //   351: astore_1
    //   352: aload_2
    //   353: astore_3
    //   354: goto +13 -> 367
    //   357: astore_1
    //   358: aload_2
    //   359: astore_3
    //   360: goto +31 -> 391
    //   363: astore_3
    //   364: aload 5
    //   366: astore_1
    //   367: aload_1
    //   368: astore_2
    //   369: aload_3
    //   370: invokestatic 340	okhttp3/internal/Util:isAndroidGetsocknameError	(Ljava/lang/AssertionError;)Z
    //   373: ifeq +14 -> 387
    //   376: aload_1
    //   377: astore_2
    //   378: new 66	java/io/IOException
    //   381: dup
    //   382: aload_3
    //   383: invokespecial 169	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   386: athrow
    //   387: aload_1
    //   388: astore_2
    //   389: aload_3
    //   390: athrow
    //   391: aload_3
    //   392: ifnull +10 -> 402
    //   395: invokestatic 134	okhttp3/internal/platform/Platform:get	()Lokhttp3/internal/platform/Platform;
    //   398: aload_3
    //   399: invokevirtual 334	okhttp3/internal/platform/Platform:afterHandshake	(Ljavax/net/ssl/SSLSocket;)V
    //   402: aload_3
    //   403: invokestatic 344	okhttp3/internal/Util:closeQuietly	(Ljava/net/Socket;)V
    //   406: aload_1
    //   407: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	408	0	this	RealConnection
    //   0	408	1	paramConnectionSpecSelector	ConnectionSpecSelector
    //   16	293	2	localObject1	Object
    //   349	10	2	localAssertionError1	AssertionError
    //   368	21	2	localConnectionSpecSelector	ConnectionSpecSelector
    //   14	346	3	localObject2	Object
    //   363	40	3	localAssertionError2	AssertionError
    //   21	235	4	localObject3	Object
    //   18	347	5	localConnectionSpec	ConnectionSpec
    //   7	234	6	localAddress	Address
    // Exception table:
    //   from	to	target	type
    //   52	87	345	finally
    //   87	235	345	finally
    //   235	255	345	finally
    //   258	274	345	finally
    //   274	312	345	finally
    //   316	321	345	finally
    //   324	328	345	finally
    //   328	333	345	finally
    //   52	87	349	java/lang/AssertionError
    //   87	235	349	java/lang/AssertionError
    //   235	255	349	java/lang/AssertionError
    //   258	274	349	java/lang/AssertionError
    //   274	312	349	java/lang/AssertionError
    //   316	321	349	java/lang/AssertionError
    //   324	328	349	java/lang/AssertionError
    //   328	333	349	java/lang/AssertionError
    //   23	52	357	finally
    //   369	376	357	finally
    //   378	387	357	finally
    //   389	391	357	finally
    //   23	52	363	java/lang/AssertionError
  }
  
  private void connectTunnel(int paramInt1, int paramInt2, int paramInt3, Call paramCall, EventListener paramEventListener)
    throws IOException
  {
    Request localRequest = createTunnelRequest();
    HttpUrl localHttpUrl = localRequest.url();
    int i = 0;
    while (i < 21)
    {
      connectSocket(paramInt1, paramInt2, paramCall, paramEventListener);
      localRequest = createTunnel(paramInt2, paramInt3, localRequest, localHttpUrl);
      if (localRequest == null) {
        return;
      }
      Util.closeQuietly(rawSocket);
      rawSocket = null;
      sink = null;
      source = null;
      paramEventListener.connectEnd(paramCall, route.socketAddress(), route.proxy(), null);
      i += 1;
    }
  }
  
  private Request createTunnel(int paramInt1, int paramInt2, Request paramRequest, HttpUrl paramHttpUrl)
    throws IOException
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("CONNECT ");
    ((StringBuilder)localObject).append(Util.hostHeader(paramHttpUrl, true));
    ((StringBuilder)localObject).append(" HTTP/1.1");
    paramHttpUrl = ((StringBuilder)localObject).toString();
    for (;;)
    {
      Http1Codec localHttp1Codec = new Http1Codec(null, null, source, sink);
      source.timeout().timeout(paramInt1, TimeUnit.MILLISECONDS);
      sink.timeout().timeout(paramInt2, TimeUnit.MILLISECONDS);
      localHttp1Codec.writeRequest(paramRequest.headers(), paramHttpUrl);
      localHttp1Codec.finishRequest();
      localObject = localHttp1Codec.readResponseHeaders(false).request(paramRequest).build();
      long l2 = HttpHeaders.contentLength((Response)localObject);
      long l1 = l2;
      if (l2 == -1L) {
        l1 = 0L;
      }
      paramRequest = localHttp1Codec.newFixedLengthSource(l1);
      Util.skipAll(paramRequest, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
      paramRequest.close();
      int i = ((Response)localObject).code();
      if (i == 200) {
        break;
      }
      if (i != 407)
      {
        paramRequest = new StringBuilder();
        paramRequest.append("Unexpected response code for CONNECT: ");
        paramRequest.append(((Response)localObject).code());
        throw new IOException(paramRequest.toString());
      }
      paramRequest = route.address().proxyAuthenticator().authenticate(route, (Response)localObject);
      if (paramRequest == null) {
        throw new IOException("Failed to authenticate with proxy");
      }
      if ("close".equalsIgnoreCase(((Response)localObject).header("Connection"))) {
        return paramRequest;
      }
    }
    if ((source.buffer().exhausted()) && (sink.buffer().exhausted())) {
      return null;
    }
    throw new IOException("TLS tunnel buffered too many bytes!");
  }
  
  private Request createTunnelRequest()
  {
    return new Request.Builder().url(route.address().url()).header("Host", Util.hostHeader(route.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", Version.userAgent()).build();
  }
  
  private void establishProtocol(ConnectionSpecSelector paramConnectionSpecSelector, Call paramCall, EventListener paramEventListener)
    throws IOException
  {
    if (route.address().sslSocketFactory() == null)
    {
      protocol = Protocol.HTTP_1_1;
      socket = rawSocket;
      return;
    }
    paramEventListener.secureConnectStart(paramCall);
    connectTls(paramConnectionSpecSelector);
    paramEventListener.secureConnectEnd(paramCall, handshake);
    if (protocol == Protocol.HTTP_2)
    {
      socket.setSoTimeout(0);
      http2Connection = new Http2Connection.Builder(true).socket(socket, route.address().url().host(), source, sink).listener(this).build();
      http2Connection.start();
    }
  }
  
  public static RealConnection testConnection(ConnectionPool paramConnectionPool, Route paramRoute, Socket paramSocket, long paramLong)
  {
    paramConnectionPool = new RealConnection(paramConnectionPool, paramRoute);
    socket = paramSocket;
    idleAtNanos = paramLong;
    return paramConnectionPool;
  }
  
  public void cancel()
  {
    Util.closeQuietly(rawSocket);
  }
  
  public void connect(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, Call arg5, EventListener paramEventListener)
  {
    if (protocol != null) {
      throw new IllegalStateException("already connected");
    }
    Object localObject1 = route.address().connectionSpecs();
    ConnectionSpecSelector localConnectionSpecSelector = new ConnectionSpecSelector((List)localObject1);
    if (route.address().sslSocketFactory() == null)
    {
      if (!((List)localObject1).contains(ConnectionSpec.CLEARTEXT)) {
        throw new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
      }
      localObject1 = route.address().url().host();
      if (!Platform.get().isCleartextTrafficPermitted((String)localObject1))
      {
        ??? = new StringBuilder();
        ???.append("CLEARTEXT communication to ");
        ???.append((String)localObject1);
        ???.append(" not permitted by network security policy");
        throw new RouteException(new UnknownServiceException(???.toString()));
      }
    }
    localObject1 = null;
    label252:
    Object localObject2;
    do
    {
      try
      {
        if (route.requiresTunnel())
        {
          connectTunnel(paramInt1, paramInt2, paramInt3, ???, paramEventListener);
          Socket localSocket = rawSocket;
          if (localSocket == null) {
            break label252;
          }
        }
        try
        {
          connectSocket(paramInt1, paramInt2, ???, paramEventListener);
          establishProtocol(localConnectionSpecSelector, ???, paramEventListener);
          paramEventListener.connectEnd(???, route.socketAddress(), route.proxy(), protocol);
          if ((route.requiresTunnel()) && (rawSocket == null)) {
            throw new RouteException(new ProtocolException("Too many tunnel connections attempted: 21"));
          }
          if (http2Connection != null) {
            synchronized (connectionPool)
            {
              allocationLimit = http2Connection.maxConcurrentStreams();
              return;
            }
          }
          return;
        }
        catch (IOException localIOException1) {}
        Util.closeQuietly(socket);
      }
      catch (IOException localIOException2) {}
      Util.closeQuietly(rawSocket);
      socket = null;
      rawSocket = null;
      source = null;
      sink = null;
      handshake = null;
      protocol = null;
      http2Connection = null;
      paramEventListener.connectFailed(???, route.socketAddress(), route.proxy(), null, localIOException2);
      if (localObject1 == null)
      {
        localObject2 = new RouteException(localIOException2);
      }
      else
      {
        ((RouteException)localObject1).addConnectException(localIOException2);
        localObject2 = localObject1;
      }
      if (!paramBoolean) {
        break;
      }
      localObject1 = localObject2;
    } while (localConnectionSpecSelector.connectionFailed(localIOException2));
    throw ((Throwable)localObject2);
  }
  
  public Handshake handshake()
  {
    return handshake;
  }
  
  public boolean isEligible(Address paramAddress, @Nullable Route paramRoute)
  {
    if (allocations.size() < allocationLimit)
    {
      if (noNewStreams) {
        return false;
      }
      if (!Internal.instance.equalsNonHost(route.address(), paramAddress)) {
        return false;
      }
      if (paramAddress.url().host().equals(route().address().url().host())) {
        return true;
      }
      if (http2Connection == null) {
        return false;
      }
      if (paramRoute == null) {
        return false;
      }
      if (paramRoute.proxy().type() != Proxy.Type.DIRECT) {
        return false;
      }
      if (route.proxy().type() != Proxy.Type.DIRECT) {
        return false;
      }
      if (!route.socketAddress().equals(paramRoute.socketAddress())) {
        return false;
      }
      if (paramRoute.address().hostnameVerifier() != OkHostnameVerifier.INSTANCE) {
        return false;
      }
      if (!supportsUrl(paramAddress.url())) {
        return false;
      }
    }
    try
    {
      paramAddress.certificatePinner().check(paramAddress.url().host(), handshake().peerCertificates());
      return true;
    }
    catch (SSLPeerUnverifiedException paramAddress) {}
    return false;
    return false;
  }
  
  public boolean isHealthy(boolean paramBoolean)
  {
    if ((!socket.isClosed()) && (!socket.isInputShutdown()))
    {
      if (socket.isOutputShutdown()) {
        return false;
      }
      if (http2Connection != null) {
        return http2Connection.isShutdown() ^ true;
      }
      if (!paramBoolean) {}
    }
    try
    {
      int i = socket.getSoTimeout();
      try
      {
        socket.setSoTimeout(1);
        paramBoolean = source.exhausted();
        return !paramBoolean;
      }
      finally
      {
        socket.setSoTimeout(i);
      }
      return true;
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
      return true;
    }
    catch (IOException localIOException) {}
    return false;
    return false;
  }
  
  public boolean isMultiplexed()
  {
    return http2Connection != null;
  }
  
  public HttpCodec newCodec(OkHttpClient paramOkHttpClient, Interceptor.Chain paramChain, StreamAllocation paramStreamAllocation)
    throws SocketException
  {
    if (http2Connection != null) {
      return new Http2Codec(paramOkHttpClient, paramChain, paramStreamAllocation, http2Connection);
    }
    socket.setSoTimeout(paramChain.readTimeoutMillis());
    source.timeout().timeout(paramChain.readTimeoutMillis(), TimeUnit.MILLISECONDS);
    sink.timeout().timeout(paramChain.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
    return new Http1Codec(paramOkHttpClient, paramStreamAllocation, source, sink);
  }
  
  public RealWebSocket.Streams newWebSocketStreams(final StreamAllocation paramStreamAllocation)
  {
    new RealWebSocket.Streams(true, source, sink)
    {
      public void close()
        throws IOException
      {
        paramStreamAllocation.streamFinished(true, paramStreamAllocation.codec(), -1L, null);
      }
    };
  }
  
  public void onSettings(Http2Connection paramHttp2Connection)
  {
    synchronized (connectionPool)
    {
      allocationLimit = paramHttp2Connection.maxConcurrentStreams();
      return;
    }
  }
  
  public void onStream(Http2Stream paramHttp2Stream)
    throws IOException
  {
    paramHttp2Stream.close(ErrorCode.REFUSED_STREAM);
  }
  
  public Protocol protocol()
  {
    return protocol;
  }
  
  public Route route()
  {
    return route;
  }
  
  public Socket socket()
  {
    return socket;
  }
  
  public boolean supportsUrl(HttpUrl paramHttpUrl)
  {
    if (paramHttpUrl.port() != route.address().url().port()) {
      return false;
    }
    if (!paramHttpUrl.host().equals(route.address().url().host())) {
      return (handshake != null) && (OkHostnameVerifier.INSTANCE.verify(paramHttpUrl.host(), (X509Certificate)handshake.peerCertificates().get(0)));
    }
    return true;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Connection{");
    localStringBuilder.append(route.address().url().host());
    localStringBuilder.append(":");
    localStringBuilder.append(route.address().url().port());
    localStringBuilder.append(", proxy=");
    localStringBuilder.append(route.proxy());
    localStringBuilder.append(" hostAddress=");
    localStringBuilder.append(route.socketAddress());
    localStringBuilder.append(" cipherSuite=");
    Object localObject;
    if (handshake != null) {
      localObject = handshake.cipherSuite();
    } else {
      localObject = "none";
    }
    localStringBuilder.append(localObject);
    localStringBuilder.append(" protocol=");
    localStringBuilder.append(protocol);
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}

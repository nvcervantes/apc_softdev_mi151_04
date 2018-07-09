package okhttp3.internal.cache;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class CacheInterceptor
  implements Interceptor
{
  final InternalCache cache;
  
  public CacheInterceptor(InternalCache paramInternalCache)
  {
    cache = paramInternalCache;
  }
  
  private Response cacheWritingResponse(final CacheRequest paramCacheRequest, Response paramResponse)
    throws IOException
  {
    if (paramCacheRequest == null) {
      return paramResponse;
    }
    Object localObject = paramCacheRequest.body();
    if (localObject == null) {
      return paramResponse;
    }
    paramCacheRequest = new Source()
    {
      boolean cacheRequestClosed;
      
      public void close()
        throws IOException
      {
        if ((!cacheRequestClosed) && (!Util.discard(this, 100, TimeUnit.MILLISECONDS)))
        {
          cacheRequestClosed = true;
          paramCacheRequest.abort();
        }
        val$source.close();
      }
      
      public long read(Buffer paramAnonymousBuffer, long paramAnonymousLong)
        throws IOException
      {
        try
        {
          paramAnonymousLong = val$source.read(paramAnonymousBuffer, paramAnonymousLong);
          if (paramAnonymousLong == -1L)
          {
            if (!cacheRequestClosed)
            {
              cacheRequestClosed = true;
              val$cacheBody.close();
            }
            return -1L;
          }
          paramAnonymousBuffer.copyTo(val$cacheBody.buffer(), paramAnonymousBuffer.size() - paramAnonymousLong, paramAnonymousLong);
          val$cacheBody.emitCompleteSegments();
          return paramAnonymousLong;
        }
        catch (IOException paramAnonymousBuffer)
        {
          if (!cacheRequestClosed)
          {
            cacheRequestClosed = true;
            paramCacheRequest.abort();
          }
          throw paramAnonymousBuffer;
        }
      }
      
      public Timeout timeout()
      {
        return val$source.timeout();
      }
    };
    localObject = paramResponse.header("Content-Type");
    long l = paramResponse.body().contentLength();
    return paramResponse.newBuilder().body(new RealResponseBody((String)localObject, l, Okio.buffer(paramCacheRequest))).build();
  }
  
  private static Headers combine(Headers paramHeaders1, Headers paramHeaders2)
  {
    Headers.Builder localBuilder = new Headers.Builder();
    int k = paramHeaders1.size();
    int j = 0;
    int i = 0;
    while (i < k)
    {
      String str1 = paramHeaders1.name(i);
      String str2 = paramHeaders1.value(i);
      if (((!"Warning".equalsIgnoreCase(str1)) || (!str2.startsWith("1"))) && ((!isEndToEnd(str1)) || (paramHeaders2.get(str1) == null))) {
        Internal.instance.addLenient(localBuilder, str1, str2);
      }
      i += 1;
    }
    k = paramHeaders2.size();
    i = j;
    while (i < k)
    {
      paramHeaders1 = paramHeaders2.name(i);
      if ((!"Content-Length".equalsIgnoreCase(paramHeaders1)) && (isEndToEnd(paramHeaders1))) {
        Internal.instance.addLenient(localBuilder, paramHeaders1, paramHeaders2.value(i));
      }
      i += 1;
    }
    return localBuilder.build();
  }
  
  static boolean isEndToEnd(String paramString)
  {
    return (!"Connection".equalsIgnoreCase(paramString)) && (!"Keep-Alive".equalsIgnoreCase(paramString)) && (!"Proxy-Authenticate".equalsIgnoreCase(paramString)) && (!"Proxy-Authorization".equalsIgnoreCase(paramString)) && (!"TE".equalsIgnoreCase(paramString)) && (!"Trailers".equalsIgnoreCase(paramString)) && (!"Transfer-Encoding".equalsIgnoreCase(paramString)) && (!"Upgrade".equalsIgnoreCase(paramString));
  }
  
  private static Response stripBody(Response paramResponse)
  {
    Response localResponse = paramResponse;
    if (paramResponse != null)
    {
      localResponse = paramResponse;
      if (paramResponse.body() != null) {
        localResponse = paramResponse.newBuilder().body(null).build();
      }
    }
    return localResponse;
  }
  
  /* Error */
  public Response intercept(okhttp3.Interceptor.Chain paramChain)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 17	okhttp3/internal/cache/CacheInterceptor:cache	Lokhttp3/internal/cache/InternalCache;
    //   4: ifnull +22 -> 26
    //   7: aload_0
    //   8: getfield 17	okhttp3/internal/cache/CacheInterceptor:cache	Lokhttp3/internal/cache/InternalCache;
    //   11: aload_1
    //   12: invokeinterface 158 1 0
    //   17: invokeinterface 163 2 0
    //   22: astore_2
    //   23: goto +5 -> 28
    //   26: aconst_null
    //   27: astore_2
    //   28: new 165	okhttp3/internal/cache/CacheStrategy$Factory
    //   31: dup
    //   32: invokestatic 170	java/lang/System:currentTimeMillis	()J
    //   35: aload_1
    //   36: invokeinterface 158 1 0
    //   41: aload_2
    //   42: invokespecial 173	okhttp3/internal/cache/CacheStrategy$Factory:<init>	(JLokhttp3/Request;Lokhttp3/Response;)V
    //   45: invokevirtual 176	okhttp3/internal/cache/CacheStrategy$Factory:get	()Lokhttp3/internal/cache/CacheStrategy;
    //   48: astore 5
    //   50: aload 5
    //   52: getfield 182	okhttp3/internal/cache/CacheStrategy:networkRequest	Lokhttp3/Request;
    //   55: astore_3
    //   56: aload 5
    //   58: getfield 186	okhttp3/internal/cache/CacheStrategy:cacheResponse	Lokhttp3/Response;
    //   61: astore 4
    //   63: aload_0
    //   64: getfield 17	okhttp3/internal/cache/CacheInterceptor:cache	Lokhttp3/internal/cache/InternalCache;
    //   67: ifnull +14 -> 81
    //   70: aload_0
    //   71: getfield 17	okhttp3/internal/cache/CacheInterceptor:cache	Lokhttp3/internal/cache/InternalCache;
    //   74: aload 5
    //   76: invokeinterface 190 2 0
    //   81: aload_2
    //   82: ifnull +15 -> 97
    //   85: aload 4
    //   87: ifnonnull +10 -> 97
    //   90: aload_2
    //   91: invokevirtual 33	okhttp3/Response:body	()Lokhttp3/ResponseBody;
    //   94: invokestatic 196	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   97: aload_3
    //   98: ifnonnull +63 -> 161
    //   101: aload 4
    //   103: ifnonnull +58 -> 161
    //   106: new 72	okhttp3/Response$Builder
    //   109: dup
    //   110: invokespecial 197	okhttp3/Response$Builder:<init>	()V
    //   113: aload_1
    //   114: invokeinterface 158 1 0
    //   119: invokevirtual 200	okhttp3/Response$Builder:request	(Lokhttp3/Request;)Lokhttp3/Response$Builder;
    //   122: getstatic 206	okhttp3/Protocol:HTTP_1_1	Lokhttp3/Protocol;
    //   125: invokevirtual 210	okhttp3/Response$Builder:protocol	(Lokhttp3/Protocol;)Lokhttp3/Response$Builder;
    //   128: sipush 504
    //   131: invokevirtual 214	okhttp3/Response$Builder:code	(I)Lokhttp3/Response$Builder;
    //   134: ldc -40
    //   136: invokevirtual 220	okhttp3/Response$Builder:message	(Ljava/lang/String;)Lokhttp3/Response$Builder;
    //   139: getstatic 224	okhttp3/internal/Util:EMPTY_RESPONSE	Lokhttp3/ResponseBody;
    //   142: invokevirtual 75	okhttp3/Response$Builder:body	(Lokhttp3/ResponseBody;)Lokhttp3/Response$Builder;
    //   145: ldc2_w 225
    //   148: invokevirtual 230	okhttp3/Response$Builder:sentRequestAtMillis	(J)Lokhttp3/Response$Builder;
    //   151: invokestatic 170	java/lang/System:currentTimeMillis	()J
    //   154: invokevirtual 233	okhttp3/Response$Builder:receivedResponseAtMillis	(J)Lokhttp3/Response$Builder;
    //   157: invokevirtual 79	okhttp3/Response$Builder:build	()Lokhttp3/Response;
    //   160: areturn
    //   161: aload_3
    //   162: ifnonnull +20 -> 182
    //   165: aload 4
    //   167: invokevirtual 62	okhttp3/Response:newBuilder	()Lokhttp3/Response$Builder;
    //   170: aload 4
    //   172: invokestatic 235	okhttp3/internal/cache/CacheInterceptor:stripBody	(Lokhttp3/Response;)Lokhttp3/Response;
    //   175: invokevirtual 238	okhttp3/Response$Builder:cacheResponse	(Lokhttp3/Response;)Lokhttp3/Response$Builder;
    //   178: invokevirtual 79	okhttp3/Response$Builder:build	()Lokhttp3/Response;
    //   181: areturn
    //   182: aload_1
    //   183: aload_3
    //   184: invokeinterface 241 2 0
    //   189: astore_1
    //   190: aload_1
    //   191: ifnonnull +14 -> 205
    //   194: aload_2
    //   195: ifnull +10 -> 205
    //   198: aload_2
    //   199: invokevirtual 33	okhttp3/Response:body	()Lokhttp3/ResponseBody;
    //   202: invokestatic 196	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   205: aload 4
    //   207: ifnull +104 -> 311
    //   210: aload_1
    //   211: invokevirtual 243	okhttp3/Response:code	()I
    //   214: sipush 304
    //   217: if_icmpne +86 -> 303
    //   220: aload 4
    //   222: invokevirtual 62	okhttp3/Response:newBuilder	()Lokhttp3/Response$Builder;
    //   225: aload 4
    //   227: invokevirtual 246	okhttp3/Response:headers	()Lokhttp3/Headers;
    //   230: aload_1
    //   231: invokevirtual 246	okhttp3/Response:headers	()Lokhttp3/Headers;
    //   234: invokestatic 248	okhttp3/internal/cache/CacheInterceptor:combine	(Lokhttp3/Headers;Lokhttp3/Headers;)Lokhttp3/Headers;
    //   237: invokevirtual 251	okhttp3/Response$Builder:headers	(Lokhttp3/Headers;)Lokhttp3/Response$Builder;
    //   240: aload_1
    //   241: invokevirtual 253	okhttp3/Response:sentRequestAtMillis	()J
    //   244: invokevirtual 230	okhttp3/Response$Builder:sentRequestAtMillis	(J)Lokhttp3/Response$Builder;
    //   247: aload_1
    //   248: invokevirtual 255	okhttp3/Response:receivedResponseAtMillis	()J
    //   251: invokevirtual 233	okhttp3/Response$Builder:receivedResponseAtMillis	(J)Lokhttp3/Response$Builder;
    //   254: aload 4
    //   256: invokestatic 235	okhttp3/internal/cache/CacheInterceptor:stripBody	(Lokhttp3/Response;)Lokhttp3/Response;
    //   259: invokevirtual 238	okhttp3/Response$Builder:cacheResponse	(Lokhttp3/Response;)Lokhttp3/Response$Builder;
    //   262: aload_1
    //   263: invokestatic 235	okhttp3/internal/cache/CacheInterceptor:stripBody	(Lokhttp3/Response;)Lokhttp3/Response;
    //   266: invokevirtual 258	okhttp3/Response$Builder:networkResponse	(Lokhttp3/Response;)Lokhttp3/Response$Builder;
    //   269: invokevirtual 79	okhttp3/Response$Builder:build	()Lokhttp3/Response;
    //   272: astore_2
    //   273: aload_1
    //   274: invokevirtual 33	okhttp3/Response:body	()Lokhttp3/ResponseBody;
    //   277: invokevirtual 261	okhttp3/ResponseBody:close	()V
    //   280: aload_0
    //   281: getfield 17	okhttp3/internal/cache/CacheInterceptor:cache	Lokhttp3/internal/cache/InternalCache;
    //   284: invokeinterface 264 1 0
    //   289: aload_0
    //   290: getfield 17	okhttp3/internal/cache/CacheInterceptor:cache	Lokhttp3/internal/cache/InternalCache;
    //   293: aload 4
    //   295: aload_2
    //   296: invokeinterface 268 3 0
    //   301: aload_2
    //   302: areturn
    //   303: aload 4
    //   305: invokevirtual 33	okhttp3/Response:body	()Lokhttp3/ResponseBody;
    //   308: invokestatic 196	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   311: aload_1
    //   312: invokevirtual 62	okhttp3/Response:newBuilder	()Lokhttp3/Response$Builder;
    //   315: aload 4
    //   317: invokestatic 235	okhttp3/internal/cache/CacheInterceptor:stripBody	(Lokhttp3/Response;)Lokhttp3/Response;
    //   320: invokevirtual 238	okhttp3/Response$Builder:cacheResponse	(Lokhttp3/Response;)Lokhttp3/Response$Builder;
    //   323: aload_1
    //   324: invokestatic 235	okhttp3/internal/cache/CacheInterceptor:stripBody	(Lokhttp3/Response;)Lokhttp3/Response;
    //   327: invokevirtual 258	okhttp3/Response$Builder:networkResponse	(Lokhttp3/Response;)Lokhttp3/Response$Builder;
    //   330: invokevirtual 79	okhttp3/Response$Builder:build	()Lokhttp3/Response;
    //   333: astore_1
    //   334: aload_0
    //   335: getfield 17	okhttp3/internal/cache/CacheInterceptor:cache	Lokhttp3/internal/cache/InternalCache;
    //   338: ifnull +54 -> 392
    //   341: aload_1
    //   342: invokestatic 274	okhttp3/internal/http/HttpHeaders:hasBody	(Lokhttp3/Response;)Z
    //   345: ifeq +27 -> 372
    //   348: aload_1
    //   349: aload_3
    //   350: invokestatic 278	okhttp3/internal/cache/CacheStrategy:isCacheable	(Lokhttp3/Response;Lokhttp3/Request;)Z
    //   353: ifeq +19 -> 372
    //   356: aload_0
    //   357: aload_0
    //   358: getfield 17	okhttp3/internal/cache/CacheInterceptor:cache	Lokhttp3/internal/cache/InternalCache;
    //   361: aload_1
    //   362: invokeinterface 282 2 0
    //   367: aload_1
    //   368: invokespecial 284	okhttp3/internal/cache/CacheInterceptor:cacheWritingResponse	(Lokhttp3/internal/cache/CacheRequest;Lokhttp3/Response;)Lokhttp3/Response;
    //   371: areturn
    //   372: aload_3
    //   373: invokevirtual 290	okhttp3/Request:method	()Ljava/lang/String;
    //   376: invokestatic 295	okhttp3/internal/http/HttpMethod:invalidatesCache	(Ljava/lang/String;)Z
    //   379: ifeq +13 -> 392
    //   382: aload_0
    //   383: getfield 17	okhttp3/internal/cache/CacheInterceptor:cache	Lokhttp3/internal/cache/InternalCache;
    //   386: aload_3
    //   387: invokeinterface 299 2 0
    //   392: aload_1
    //   393: areturn
    //   394: astore_1
    //   395: aload_2
    //   396: ifnull +10 -> 406
    //   399: aload_2
    //   400: invokevirtual 33	okhttp3/Response:body	()Lokhttp3/ResponseBody;
    //   403: invokestatic 196	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   406: aload_1
    //   407: athrow
    //   408: astore_2
    //   409: aload_1
    //   410: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	411	0	this	CacheInterceptor
    //   0	411	1	paramChain	okhttp3.Interceptor.Chain
    //   22	378	2	localResponse1	Response
    //   408	1	2	localIOException	IOException
    //   55	332	3	localRequest	okhttp3.Request
    //   61	255	4	localResponse2	Response
    //   48	27	5	localCacheStrategy	CacheStrategy
    // Exception table:
    //   from	to	target	type
    //   182	190	394	finally
    //   382	392	408	java/io/IOException
  }
}

package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Cache.Entry;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;

public class BasicNetwork
  implements Network
{
  protected static final boolean DEBUG = VolleyLog.DEBUG;
  private static int DEFAULT_POOL_SIZE = 4096;
  private static int SLOW_REQUEST_THRESHOLD_MS = 3000;
  protected final HttpStack mHttpStack;
  protected final ByteArrayPool mPool;
  
  public BasicNetwork(HttpStack paramHttpStack)
  {
    this(paramHttpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
  }
  
  public BasicNetwork(HttpStack paramHttpStack, ByteArrayPool paramByteArrayPool)
  {
    mHttpStack = paramHttpStack;
    mPool = paramByteArrayPool;
  }
  
  private void addCacheHeaders(Map<String, String> paramMap, Cache.Entry paramEntry)
  {
    if (paramEntry == null) {
      return;
    }
    if (etag != null) {
      paramMap.put("If-None-Match", etag);
    }
    if (lastModified > 0L) {
      paramMap.put("If-Modified-Since", DateUtils.formatDate(new Date(lastModified)));
    }
  }
  
  private static void attemptRetryOnException(String paramString, Request<?> paramRequest, VolleyError paramVolleyError)
    throws VolleyError
  {
    RetryPolicy localRetryPolicy = paramRequest.getRetryPolicy();
    int i = paramRequest.getTimeoutMs();
    try
    {
      localRetryPolicy.retry(paramVolleyError);
      paramRequest.addMarker(String.format("%s-retry [timeout=%s]", new Object[] { paramString, Integer.valueOf(i) }));
      return;
    }
    catch (VolleyError paramVolleyError)
    {
      paramRequest.addMarker(String.format("%s-timeout-giveup [timeout=%s]", new Object[] { paramString, Integer.valueOf(i) }));
      throw paramVolleyError;
    }
  }
  
  protected static Map<String, String> convertHeaders(Header[] paramArrayOfHeader)
  {
    TreeMap localTreeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    int i = 0;
    while (i < paramArrayOfHeader.length)
    {
      localTreeMap.put(paramArrayOfHeader[i].getName(), paramArrayOfHeader[i].getValue());
      i += 1;
    }
    return localTreeMap;
  }
  
  private byte[] entityToBytes(HttpEntity paramHttpEntity)
    throws IOException, ServerError
  {
    PoolingByteArrayOutputStream localPoolingByteArrayOutputStream = new PoolingByteArrayOutputStream(mPool, (int)paramHttpEntity.getContentLength());
    Object localObject5 = null;
    for (;;)
    {
      Object localObject1;
      try
      {
        Object localObject2 = paramHttpEntity.getContent();
        if (localObject2 == null) {
          throw new ServerError();
        }
        localObject1 = mPool.getBuf(1024);
        try
        {
          int i = ((InputStream)localObject2).read((byte[])localObject1);
          if (i != -1)
          {
            localPoolingByteArrayOutputStream.write((byte[])localObject1, 0, i);
            continue;
          }
          localObject2 = localPoolingByteArrayOutputStream.toByteArray();
        }
        finally {}
      }
      finally
      {
        localObject1 = localObject5;
      }
      try
      {
        paramHttpEntity.consumeContent();
      }
      catch (IOException paramHttpEntity) {}
    }
    VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
    mPool.returnBuf((byte[])localObject1);
    localPoolingByteArrayOutputStream.close();
    return localObject2;
    try
    {
      paramHttpEntity.consumeContent();
    }
    catch (IOException paramHttpEntity)
    {
      for (;;) {}
    }
    VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
    mPool.returnBuf((byte[])localObject1);
    localPoolingByteArrayOutputStream.close();
    throw localObject4;
  }
  
  private void logSlowRequests(long paramLong, Request<?> paramRequest, byte[] paramArrayOfByte, StatusLine paramStatusLine)
  {
    if ((DEBUG) || (paramLong > SLOW_REQUEST_THRESHOLD_MS))
    {
      if (paramArrayOfByte != null) {
        paramArrayOfByte = Integer.valueOf(paramArrayOfByte.length);
      } else {
        paramArrayOfByte = "null";
      }
      VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", new Object[] { paramRequest, Long.valueOf(paramLong), paramArrayOfByte, Integer.valueOf(paramStatusLine.getStatusCode()), Integer.valueOf(paramRequest.getRetryPolicy().getCurrentRetryCount()) });
    }
  }
  
  protected void logError(String paramString1, String paramString2, long paramLong)
  {
    VolleyLog.v("HTTP ERROR(%s) %d ms to fetch %s", new Object[] { paramString1, Long.valueOf(SystemClock.elapsedRealtime() - paramLong), paramString2 });
  }
  
  /* Error */
  public com.android.volley.NetworkResponse performRequest(Request<?> paramRequest)
    throws VolleyError
  {
    // Byte code:
    //   0: invokestatic 231	android/os/SystemClock:elapsedRealtime	()J
    //   3: lstore_3
    //   4: invokestatic 245	java/util/Collections:emptyMap	()Ljava/util/Map;
    //   7: astore 9
    //   9: new 247	java/util/HashMap
    //   12: dup
    //   13: invokespecial 248	java/util/HashMap:<init>	()V
    //   16: astore 7
    //   18: aload_0
    //   19: aload 7
    //   21: aload_1
    //   22: invokevirtual 252	com/android/volley/Request:getCacheEntry	()Lcom/android/volley/Cache$Entry;
    //   25: invokespecial 254	com/android/volley/toolbox/BasicNetwork:addCacheHeaders	(Ljava/util/Map;Lcom/android/volley/Cache$Entry;)V
    //   28: aload_0
    //   29: getfield 41	com/android/volley/toolbox/BasicNetwork:mHttpStack	Lcom/android/volley/toolbox/HttpStack;
    //   32: aload_1
    //   33: aload 7
    //   35: invokeinterface 259 3 0
    //   40: astore 10
    //   42: aload 10
    //   44: invokeinterface 265 1 0
    //   49: astore 11
    //   51: aload 11
    //   53: invokeinterface 215 1 0
    //   58: istore_2
    //   59: aload 10
    //   61: invokeinterface 269 1 0
    //   66: invokestatic 271	com/android/volley/toolbox/BasicNetwork:convertHeaders	([Lorg/apache/http/Header;)Ljava/util/Map;
    //   69: astore 7
    //   71: iload_2
    //   72: sipush 304
    //   75: if_icmpne +97 -> 172
    //   78: aload_1
    //   79: invokevirtual 252	com/android/volley/Request:getCacheEntry	()Lcom/android/volley/Cache$Entry;
    //   82: astore 8
    //   84: aload 8
    //   86: ifnonnull +23 -> 109
    //   89: new 273	com/android/volley/NetworkResponse
    //   92: dup
    //   93: sipush 304
    //   96: aconst_null
    //   97: aload 7
    //   99: iconst_1
    //   100: invokestatic 231	android/os/SystemClock:elapsedRealtime	()J
    //   103: lload_3
    //   104: lsub
    //   105: invokespecial 276	com/android/volley/NetworkResponse:<init>	(I[BLjava/util/Map;ZJ)V
    //   108: areturn
    //   109: aload 8
    //   111: getfield 280	com/android/volley/Cache$Entry:responseHeaders	Ljava/util/Map;
    //   114: aload 7
    //   116: invokeinterface 284 2 0
    //   121: new 273	com/android/volley/NetworkResponse
    //   124: dup
    //   125: sipush 304
    //   128: aload 8
    //   130: getfield 288	com/android/volley/Cache$Entry:data	[B
    //   133: aload 8
    //   135: getfield 280	com/android/volley/Cache$Entry:responseHeaders	Ljava/util/Map;
    //   138: iconst_1
    //   139: invokestatic 231	android/os/SystemClock:elapsedRealtime	()J
    //   142: lload_3
    //   143: lsub
    //   144: invokespecial 276	com/android/volley/NetworkResponse:<init>	(I[BLjava/util/Map;ZJ)V
    //   147: astore 8
    //   149: aload 8
    //   151: areturn
    //   152: astore 8
    //   154: aconst_null
    //   155: astore 11
    //   157: aload 7
    //   159: astore 9
    //   161: aload 8
    //   163: astore 7
    //   165: aload 11
    //   167: astore 8
    //   169: goto +175 -> 344
    //   172: aload 10
    //   174: invokeinterface 292 1 0
    //   179: astore 8
    //   181: aload 8
    //   183: ifnull +19 -> 202
    //   186: aload_0
    //   187: aload 10
    //   189: invokeinterface 292 1 0
    //   194: invokespecial 294	com/android/volley/toolbox/BasicNetwork:entityToBytes	(Lorg/apache/http/HttpEntity;)[B
    //   197: astore 8
    //   199: goto +8 -> 207
    //   202: iconst_0
    //   203: newarray byte
    //   205: astore 8
    //   207: aload_0
    //   208: invokestatic 231	android/os/SystemClock:elapsedRealtime	()J
    //   211: lload_3
    //   212: lsub
    //   213: aload_1
    //   214: aload 8
    //   216: aload 11
    //   218: invokespecial 296	com/android/volley/toolbox/BasicNetwork:logSlowRequests	(JLcom/android/volley/Request;[BLorg/apache/http/StatusLine;)V
    //   221: iload_2
    //   222: sipush 200
    //   225: if_icmplt +36 -> 261
    //   228: iload_2
    //   229: sipush 299
    //   232: if_icmple +6 -> 238
    //   235: goto +26 -> 261
    //   238: invokestatic 231	android/os/SystemClock:elapsedRealtime	()J
    //   241: lstore 5
    //   243: new 273	com/android/volley/NetworkResponse
    //   246: dup
    //   247: iload_2
    //   248: aload 8
    //   250: aload 7
    //   252: iconst_0
    //   253: lload 5
    //   255: lload_3
    //   256: lsub
    //   257: invokespecial 276	com/android/volley/NetworkResponse:<init>	(I[BLjava/util/Map;ZJ)V
    //   260: areturn
    //   261: new 145	java/io/IOException
    //   264: dup
    //   265: invokespecial 297	java/io/IOException:<init>	()V
    //   268: athrow
    //   269: astore 9
    //   271: goto +5 -> 276
    //   274: astore 9
    //   276: aload 9
    //   278: astore 11
    //   280: aload 7
    //   282: astore 9
    //   284: aload 11
    //   286: astore 7
    //   288: goto +56 -> 344
    //   291: astore 8
    //   293: aload 7
    //   295: astore 9
    //   297: aconst_null
    //   298: astore 11
    //   300: aload 8
    //   302: astore 7
    //   304: aload 11
    //   306: astore 8
    //   308: goto +36 -> 344
    //   311: astore 8
    //   313: aload 10
    //   315: astore 7
    //   317: goto +8 -> 325
    //   320: astore 8
    //   322: aconst_null
    //   323: astore 7
    //   325: aconst_null
    //   326: astore 10
    //   328: aload 8
    //   330: astore 11
    //   332: aload 10
    //   334: astore 8
    //   336: aload 7
    //   338: astore 10
    //   340: aload 11
    //   342: astore 7
    //   344: aload 10
    //   346: ifnull +120 -> 466
    //   349: aload 10
    //   351: invokeinterface 265 1 0
    //   356: invokeinterface 215 1 0
    //   361: istore_2
    //   362: ldc_w 299
    //   365: iconst_2
    //   366: anewarray 4	java/lang/Object
    //   369: dup
    //   370: iconst_0
    //   371: iload_2
    //   372: invokestatic 106	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   375: aastore
    //   376: dup
    //   377: iconst_1
    //   378: aload_1
    //   379: invokevirtual 302	com/android/volley/Request:getUrl	()Ljava/lang/String;
    //   382: aastore
    //   383: invokestatic 305	com/android/volley/VolleyLog:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   386: aload 8
    //   388: ifnull +69 -> 457
    //   391: new 273	com/android/volley/NetworkResponse
    //   394: dup
    //   395: iload_2
    //   396: aload 8
    //   398: aload 9
    //   400: iconst_0
    //   401: invokestatic 231	android/os/SystemClock:elapsedRealtime	()J
    //   404: lload_3
    //   405: lsub
    //   406: invokespecial 276	com/android/volley/NetworkResponse:<init>	(I[BLjava/util/Map;ZJ)V
    //   409: astore 7
    //   411: iload_2
    //   412: sipush 401
    //   415: if_icmpeq +23 -> 438
    //   418: iload_2
    //   419: sipush 403
    //   422: if_icmpne +6 -> 428
    //   425: goto +13 -> 438
    //   428: new 147	com/android/volley/ServerError
    //   431: dup
    //   432: aload 7
    //   434: invokespecial 308	com/android/volley/ServerError:<init>	(Lcom/android/volley/NetworkResponse;)V
    //   437: athrow
    //   438: ldc_w 310
    //   441: aload_1
    //   442: new 312	com/android/volley/AuthFailureError
    //   445: dup
    //   446: aload 7
    //   448: invokespecial 313	com/android/volley/AuthFailureError:<init>	(Lcom/android/volley/NetworkResponse;)V
    //   451: invokestatic 315	com/android/volley/toolbox/BasicNetwork:attemptRetryOnException	(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
    //   454: goto -450 -> 4
    //   457: new 317	com/android/volley/NetworkError
    //   460: dup
    //   461: aconst_null
    //   462: invokespecial 318	com/android/volley/NetworkError:<init>	(Lcom/android/volley/NetworkResponse;)V
    //   465: athrow
    //   466: new 320	com/android/volley/NoConnectionError
    //   469: dup
    //   470: aload 7
    //   472: invokespecial 323	com/android/volley/NoConnectionError:<init>	(Ljava/lang/Throwable;)V
    //   475: athrow
    //   476: astore 7
    //   478: aload_1
    //   479: invokevirtual 302	com/android/volley/Request:getUrl	()Ljava/lang/String;
    //   482: invokestatic 326	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   485: astore_1
    //   486: aload_1
    //   487: invokevirtual 329	java/lang/String:length	()I
    //   490: ifeq +14 -> 504
    //   493: ldc_w 331
    //   496: aload_1
    //   497: invokevirtual 335	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   500: astore_1
    //   501: goto +14 -> 515
    //   504: new 108	java/lang/String
    //   507: dup
    //   508: ldc_w 331
    //   511: invokespecial 337	java/lang/String:<init>	(Ljava/lang/String;)V
    //   514: astore_1
    //   515: new 339	java/lang/RuntimeException
    //   518: dup
    //   519: aload_1
    //   520: aload 7
    //   522: invokespecial 342	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   525: athrow
    //   526: ldc_w 344
    //   529: aload_1
    //   530: new 346	com/android/volley/TimeoutError
    //   533: dup
    //   534: invokespecial 347	com/android/volley/TimeoutError:<init>	()V
    //   537: invokestatic 315	com/android/volley/toolbox/BasicNetwork:attemptRetryOnException	(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
    //   540: goto -536 -> 4
    //   543: ldc_w 349
    //   546: aload_1
    //   547: new 346	com/android/volley/TimeoutError
    //   550: dup
    //   551: invokespecial 347	com/android/volley/TimeoutError:<init>	()V
    //   554: invokestatic 315	com/android/volley/toolbox/BasicNetwork:attemptRetryOnException	(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
    //   557: goto -553 -> 4
    //   560: astore 7
    //   562: goto -19 -> 543
    //   565: astore 7
    //   567: goto -41 -> 526
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	570	0	this	BasicNetwork
    //   0	570	1	paramRequest	Request<?>
    //   58	365	2	i	int
    //   3	402	3	l1	long
    //   241	13	5	l2	long
    //   16	455	7	localObject1	Object
    //   476	45	7	localMalformedURLException	java.net.MalformedURLException
    //   560	1	7	localSocketTimeoutException	java.net.SocketTimeoutException
    //   565	1	7	localConnectTimeoutException	org.apache.http.conn.ConnectTimeoutException
    //   82	68	8	localObject2	Object
    //   152	10	8	localIOException1	IOException
    //   167	82	8	localObject3	Object
    //   291	10	8	localIOException2	IOException
    //   306	1	8	localObject4	Object
    //   311	1	8	localIOException3	IOException
    //   320	9	8	localIOException4	IOException
    //   334	63	8	localObject5	Object
    //   7	153	9	localObject6	Object
    //   269	1	9	localIOException5	IOException
    //   274	3	9	localIOException6	IOException
    //   282	117	9	localObject7	Object
    //   40	310	10	localObject8	Object
    //   49	292	11	localObject9	Object
    // Exception table:
    //   from	to	target	type
    //   78	84	152	java/io/IOException
    //   89	109	152	java/io/IOException
    //   109	149	152	java/io/IOException
    //   186	199	152	java/io/IOException
    //   243	261	269	java/io/IOException
    //   261	269	269	java/io/IOException
    //   207	221	274	java/io/IOException
    //   238	243	274	java/io/IOException
    //   172	181	291	java/io/IOException
    //   202	207	291	java/io/IOException
    //   42	71	311	java/io/IOException
    //   9	42	320	java/io/IOException
    //   9	42	476	java/net/MalformedURLException
    //   42	71	476	java/net/MalformedURLException
    //   78	84	476	java/net/MalformedURLException
    //   89	109	476	java/net/MalformedURLException
    //   109	149	476	java/net/MalformedURLException
    //   172	181	476	java/net/MalformedURLException
    //   186	199	476	java/net/MalformedURLException
    //   202	207	476	java/net/MalformedURLException
    //   207	221	476	java/net/MalformedURLException
    //   238	243	476	java/net/MalformedURLException
    //   243	261	476	java/net/MalformedURLException
    //   261	269	476	java/net/MalformedURLException
    //   9	42	560	java/net/SocketTimeoutException
    //   42	71	560	java/net/SocketTimeoutException
    //   78	84	560	java/net/SocketTimeoutException
    //   89	109	560	java/net/SocketTimeoutException
    //   109	149	560	java/net/SocketTimeoutException
    //   172	181	560	java/net/SocketTimeoutException
    //   186	199	560	java/net/SocketTimeoutException
    //   202	207	560	java/net/SocketTimeoutException
    //   207	221	560	java/net/SocketTimeoutException
    //   238	243	560	java/net/SocketTimeoutException
    //   243	261	560	java/net/SocketTimeoutException
    //   261	269	560	java/net/SocketTimeoutException
    //   9	42	565	org/apache/http/conn/ConnectTimeoutException
    //   42	71	565	org/apache/http/conn/ConnectTimeoutException
    //   78	84	565	org/apache/http/conn/ConnectTimeoutException
    //   89	109	565	org/apache/http/conn/ConnectTimeoutException
    //   109	149	565	org/apache/http/conn/ConnectTimeoutException
    //   172	181	565	org/apache/http/conn/ConnectTimeoutException
    //   186	199	565	org/apache/http/conn/ConnectTimeoutException
    //   202	207	565	org/apache/http/conn/ConnectTimeoutException
    //   207	221	565	org/apache/http/conn/ConnectTimeoutException
    //   238	243	565	org/apache/http/conn/ConnectTimeoutException
    //   243	261	565	org/apache/http/conn/ConnectTimeoutException
    //   261	269	565	org/apache/http/conn/ConnectTimeoutException
  }
}

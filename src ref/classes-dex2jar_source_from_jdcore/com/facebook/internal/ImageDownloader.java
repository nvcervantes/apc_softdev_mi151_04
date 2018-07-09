package com.facebook.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import java.io.Closeable;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ImageDownloader
{
  private static final int CACHE_READ_QUEUE_MAX_CONCURRENT = 2;
  private static final int DOWNLOAD_QUEUE_MAX_CONCURRENT = 8;
  private static WorkQueue cacheReadQueue = new WorkQueue(2);
  private static WorkQueue downloadQueue = new WorkQueue(8);
  private static Handler handler;
  private static final Map<RequestKey, DownloaderContext> pendingRequests = new HashMap();
  
  public ImageDownloader() {}
  
  public static boolean cancelRequest(ImageRequest arg0)
  {
    RequestKey localRequestKey = new RequestKey(???.getImageUri(), ???.getCallerTag());
    for (;;)
    {
      synchronized (pendingRequests)
      {
        DownloaderContext localDownloaderContext = (DownloaderContext)pendingRequests.get(localRequestKey);
        bool = true;
        if (localDownloaderContext != null)
        {
          if (workItem.cancel()) {
            pendingRequests.remove(localRequestKey);
          } else {
            isCancelled = true;
          }
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public static void clearCache(Context paramContext)
  {
    ImageResponseCache.clearCache(paramContext);
    UrlRedirectCache.clearCache();
  }
  
  /* Error */
  private static void download(RequestKey paramRequestKey, Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 8
    //   6: iconst_1
    //   7: istore_3
    //   8: iconst_1
    //   9: istore_2
    //   10: new 113	java/net/URL
    //   13: dup
    //   14: aload_0
    //   15: getfield 117	com/facebook/internal/ImageDownloader$RequestKey:uri	Landroid/net/Uri;
    //   18: invokevirtual 123	android/net/Uri:toString	()Ljava/lang/String;
    //   21: invokespecial 126	java/net/URL:<init>	(Ljava/lang/String;)V
    //   24: invokevirtual 130	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   27: checkcast 132	java/net/HttpURLConnection
    //   30: astore 7
    //   32: aload 7
    //   34: iconst_0
    //   35: invokevirtual 136	java/net/HttpURLConnection:setInstanceFollowRedirects	(Z)V
    //   38: aload 7
    //   40: invokevirtual 140	java/net/HttpURLConnection:getResponseCode	()I
    //   43: istore 4
    //   45: iload 4
    //   47: sipush 200
    //   50: if_icmpeq +294 -> 344
    //   53: iload 4
    //   55: tableswitch	default:+408->463, 301:+197->252, 302:+197->252
    //   76: aload 7
    //   78: invokevirtual 144	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   81: astore 6
    //   83: aload 6
    //   85: astore_1
    //   86: aload 6
    //   88: astore 5
    //   90: new 146	java/lang/StringBuilder
    //   93: dup
    //   94: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   97: astore 8
    //   99: aload 6
    //   101: ifnull +96 -> 197
    //   104: aload 6
    //   106: astore_1
    //   107: aload 6
    //   109: astore 5
    //   111: new 149	java/io/InputStreamReader
    //   114: dup
    //   115: aload 6
    //   117: invokespecial 152	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   120: astore 9
    //   122: aload 6
    //   124: astore_1
    //   125: aload 6
    //   127: astore 5
    //   129: sipush 128
    //   132: newarray char
    //   134: astore 10
    //   136: aload 6
    //   138: astore_1
    //   139: aload 6
    //   141: astore 5
    //   143: aload 9
    //   145: aload 10
    //   147: iconst_0
    //   148: aload 10
    //   150: arraylength
    //   151: invokevirtual 156	java/io/InputStreamReader:read	([CII)I
    //   154: istore 4
    //   156: iload 4
    //   158: ifle +24 -> 182
    //   161: aload 6
    //   163: astore_1
    //   164: aload 6
    //   166: astore 5
    //   168: aload 8
    //   170: aload 10
    //   172: iconst_0
    //   173: iload 4
    //   175: invokevirtual 160	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   178: pop
    //   179: goto -43 -> 136
    //   182: aload 6
    //   184: astore_1
    //   185: aload 6
    //   187: astore 5
    //   189: aload 9
    //   191: invokestatic 166	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   194: goto +18 -> 212
    //   197: aload 6
    //   199: astore_1
    //   200: aload 6
    //   202: astore 5
    //   204: aload 8
    //   206: ldc -88
    //   208: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   211: pop
    //   212: aload 6
    //   214: astore_1
    //   215: aload 6
    //   217: astore 5
    //   219: new 173	com/facebook/FacebookException
    //   222: dup
    //   223: aload 8
    //   225: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   228: invokespecial 175	com/facebook/FacebookException:<init>	(Ljava/lang/String;)V
    //   231: astore 8
    //   233: aconst_null
    //   234: astore_1
    //   235: aload 8
    //   237: astore 5
    //   239: goto +134 -> 373
    //   242: astore_0
    //   243: goto +169 -> 412
    //   246: astore_1
    //   247: iload_3
    //   248: istore_2
    //   249: goto +184 -> 433
    //   252: aload 7
    //   254: ldc -79
    //   256: invokevirtual 181	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   259: astore_1
    //   260: aload_1
    //   261: invokestatic 185	com/facebook/internal/Utility:isNullOrEmpty	(Ljava/lang/String;)Z
    //   264: ifne +56 -> 320
    //   267: aload_1
    //   268: invokestatic 189	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   271: astore_1
    //   272: aload_0
    //   273: getfield 117	com/facebook/internal/ImageDownloader$RequestKey:uri	Landroid/net/Uri;
    //   276: aload_1
    //   277: invokestatic 193	com/facebook/internal/UrlRedirectCache:cacheUriRedirect	(Landroid/net/Uri;Landroid/net/Uri;)V
    //   280: aload_0
    //   281: invokestatic 197	com/facebook/internal/ImageDownloader:removePendingRequest	(Lcom/facebook/internal/ImageDownloader$RequestKey;)Lcom/facebook/internal/ImageDownloader$DownloaderContext;
    //   284: astore 6
    //   286: aload 6
    //   288: ifnull +32 -> 320
    //   291: aload 6
    //   293: getfield 99	com/facebook/internal/ImageDownloader$DownloaderContext:isCancelled	Z
    //   296: ifne +24 -> 320
    //   299: aload 6
    //   301: getfield 201	com/facebook/internal/ImageDownloader$DownloaderContext:request	Lcom/facebook/internal/ImageRequest;
    //   304: new 17	com/facebook/internal/ImageDownloader$RequestKey
    //   307: dup
    //   308: aload_1
    //   309: aload_0
    //   310: getfield 205	com/facebook/internal/ImageDownloader$RequestKey:tag	Ljava/lang/Object;
    //   313: invokespecial 76	com/facebook/internal/ImageDownloader$RequestKey:<init>	(Landroid/net/Uri;Ljava/lang/Object;)V
    //   316: iconst_0
    //   317: invokestatic 209	com/facebook/internal/ImageDownloader:enqueueCacheRead	(Lcom/facebook/internal/ImageRequest;Lcom/facebook/internal/ImageDownloader$RequestKey;Z)V
    //   320: aconst_null
    //   321: astore 6
    //   323: aload 6
    //   325: astore_1
    //   326: iconst_0
    //   327: istore_2
    //   328: aload 8
    //   330: astore 5
    //   332: goto +41 -> 373
    //   335: astore_1
    //   336: aconst_null
    //   337: astore 5
    //   339: iconst_0
    //   340: istore_2
    //   341: goto +92 -> 433
    //   344: aload_1
    //   345: aload 7
    //   347: invokestatic 213	com/facebook/internal/ImageResponseCache:interceptAndCacheImageStream	(Landroid/content/Context;Ljava/net/HttpURLConnection;)Ljava/io/InputStream;
    //   350: astore 6
    //   352: aload 6
    //   354: astore_1
    //   355: aload 6
    //   357: astore 5
    //   359: aload 6
    //   361: invokestatic 219	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   364: astore 9
    //   366: aload 9
    //   368: astore_1
    //   369: aload 8
    //   371: astore 5
    //   373: aload 6
    //   375: invokestatic 166	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   378: aload 7
    //   380: invokestatic 223	com/facebook/internal/Utility:disconnectQuietly	(Ljava/net/URLConnection;)V
    //   383: aload_1
    //   384: astore 6
    //   386: goto +63 -> 449
    //   389: astore_0
    //   390: aload 5
    //   392: astore_1
    //   393: goto +19 -> 412
    //   396: astore_1
    //   397: aconst_null
    //   398: astore 5
    //   400: iload_3
    //   401: istore_2
    //   402: goto +31 -> 433
    //   405: astore_0
    //   406: aconst_null
    //   407: astore 7
    //   409: aload 5
    //   411: astore_1
    //   412: aload_1
    //   413: invokestatic 166	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   416: aload 7
    //   418: invokestatic 223	com/facebook/internal/Utility:disconnectQuietly	(Ljava/net/URLConnection;)V
    //   421: aload_0
    //   422: athrow
    //   423: astore_1
    //   424: aconst_null
    //   425: astore 5
    //   427: aload 5
    //   429: astore 7
    //   431: iload_3
    //   432: istore_2
    //   433: aload 5
    //   435: invokestatic 166	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   438: aload 7
    //   440: invokestatic 223	com/facebook/internal/Utility:disconnectQuietly	(Ljava/net/URLConnection;)V
    //   443: aconst_null
    //   444: astore 6
    //   446: aload_1
    //   447: astore 5
    //   449: iload_2
    //   450: ifeq +12 -> 462
    //   453: aload_0
    //   454: aload 5
    //   456: aload 6
    //   458: iconst_0
    //   459: invokestatic 227	com/facebook/internal/ImageDownloader:issueResponse	(Lcom/facebook/internal/ImageDownloader$RequestKey;Ljava/lang/Exception;Landroid/graphics/Bitmap;Z)V
    //   462: return
    //   463: goto -387 -> 76
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	466	0	paramRequestKey	RequestKey
    //   0	466	1	paramContext	Context
    //   9	441	2	i	int
    //   7	425	3	j	int
    //   43	131	4	k	int
    //   1	454	5	localObject1	Object
    //   81	376	6	localObject2	Object
    //   30	409	7	localObject3	Object
    //   4	366	8	localObject4	Object
    //   120	247	9	localObject5	Object
    //   134	37	10	arrayOfChar	char[]
    // Exception table:
    //   from	to	target	type
    //   90	99	242	finally
    //   111	122	242	finally
    //   129	136	242	finally
    //   143	156	242	finally
    //   168	179	242	finally
    //   189	194	242	finally
    //   204	212	242	finally
    //   219	233	242	finally
    //   359	366	242	finally
    //   90	99	246	java/io/IOException
    //   111	122	246	java/io/IOException
    //   129	136	246	java/io/IOException
    //   143	156	246	java/io/IOException
    //   168	179	246	java/io/IOException
    //   189	194	246	java/io/IOException
    //   204	212	246	java/io/IOException
    //   219	233	246	java/io/IOException
    //   359	366	246	java/io/IOException
    //   252	286	335	java/io/IOException
    //   291	320	335	java/io/IOException
    //   32	45	389	finally
    //   76	83	389	finally
    //   252	286	389	finally
    //   291	320	389	finally
    //   344	352	389	finally
    //   32	45	396	java/io/IOException
    //   76	83	396	java/io/IOException
    //   344	352	396	java/io/IOException
    //   10	32	405	finally
    //   10	32	423	java/io/IOException
  }
  
  public static void downloadAsync(ImageRequest paramImageRequest)
  {
    if (paramImageRequest == null) {
      return;
    }
    RequestKey localRequestKey = new RequestKey(paramImageRequest.getImageUri(), paramImageRequest.getCallerTag());
    synchronized (pendingRequests)
    {
      DownloaderContext localDownloaderContext = (DownloaderContext)pendingRequests.get(localRequestKey);
      if (localDownloaderContext != null)
      {
        request = paramImageRequest;
        isCancelled = false;
        workItem.moveToFront();
      }
      else
      {
        enqueueCacheRead(paramImageRequest, localRequestKey, paramImageRequest.isCachedRedirectAllowed());
      }
      return;
    }
  }
  
  private static void enqueueCacheRead(ImageRequest paramImageRequest, RequestKey paramRequestKey, boolean paramBoolean)
  {
    enqueueRequest(paramImageRequest, paramRequestKey, cacheReadQueue, new CacheReadWorkItem(paramImageRequest.getContext(), paramRequestKey, paramBoolean));
  }
  
  private static void enqueueDownload(ImageRequest paramImageRequest, RequestKey paramRequestKey)
  {
    enqueueRequest(paramImageRequest, paramRequestKey, downloadQueue, new DownloadImageWorkItem(paramImageRequest.getContext(), paramRequestKey));
  }
  
  private static void enqueueRequest(ImageRequest paramImageRequest, RequestKey paramRequestKey, WorkQueue paramWorkQueue, Runnable paramRunnable)
  {
    synchronized (pendingRequests)
    {
      DownloaderContext localDownloaderContext = new DownloaderContext(null);
      request = paramImageRequest;
      pendingRequests.put(paramRequestKey, localDownloaderContext);
      workItem = paramWorkQueue.addActiveWorkItem(paramRunnable);
      return;
    }
  }
  
  private static Handler getHandler()
  {
    try
    {
      if (handler == null) {
        handler = new Handler(Looper.getMainLooper());
      }
      Handler localHandler = handler;
      return localHandler;
    }
    finally {}
  }
  
  private static void issueResponse(RequestKey paramRequestKey, final Exception paramException, final Bitmap paramBitmap, final boolean paramBoolean)
  {
    paramRequestKey = removePendingRequest(paramRequestKey);
    if ((paramRequestKey != null) && (!isCancelled))
    {
      paramRequestKey = request;
      final ImageRequest.Callback localCallback = paramRequestKey.getCallback();
      if (localCallback != null) {
        getHandler().post(new Runnable()
        {
          public void run()
          {
            ImageResponse localImageResponse = new ImageResponse(val$request, paramException, paramBoolean, paramBitmap);
            localCallback.onCompleted(localImageResponse);
          }
        });
      }
    }
  }
  
  public static void prioritizeRequest(ImageRequest arg0)
  {
    Object localObject1 = new RequestKey(???.getImageUri(), ???.getCallerTag());
    synchronized (pendingRequests)
    {
      localObject1 = (DownloaderContext)pendingRequests.get(localObject1);
      if (localObject1 != null) {
        workItem.moveToFront();
      }
      return;
    }
  }
  
  private static void readFromCache(RequestKey paramRequestKey, Context paramContext, boolean paramBoolean)
  {
    boolean bool = false;
    if (paramBoolean)
    {
      localObject = UrlRedirectCache.getRedirectedUri(uri);
      if (localObject != null)
      {
        InputStream localInputStream = ImageResponseCache.getCachedImageStream((Uri)localObject, paramContext);
        paramBoolean = bool;
        localObject = localInputStream;
        if (localInputStream == null) {
          break label53;
        }
        paramBoolean = true;
        localObject = localInputStream;
        break label53;
      }
    }
    Object localObject = null;
    paramBoolean = bool;
    label53:
    if (!paramBoolean) {
      localObject = ImageResponseCache.getCachedImageStream(uri, paramContext);
    }
    if (localObject != null)
    {
      paramContext = BitmapFactory.decodeStream((InputStream)localObject);
      Utility.closeQuietly((Closeable)localObject);
      issueResponse(paramRequestKey, null, paramContext, paramBoolean);
      return;
    }
    paramContext = removePendingRequest(paramRequestKey);
    if ((paramContext != null) && (!isCancelled)) {
      enqueueDownload(request, paramRequestKey);
    }
  }
  
  private static DownloaderContext removePendingRequest(RequestKey paramRequestKey)
  {
    synchronized (pendingRequests)
    {
      paramRequestKey = (DownloaderContext)pendingRequests.remove(paramRequestKey);
      return paramRequestKey;
    }
  }
  
  private static class CacheReadWorkItem
    implements Runnable
  {
    private boolean allowCachedRedirects;
    private Context context;
    private ImageDownloader.RequestKey key;
    
    CacheReadWorkItem(Context paramContext, ImageDownloader.RequestKey paramRequestKey, boolean paramBoolean)
    {
      context = paramContext;
      key = paramRequestKey;
      allowCachedRedirects = paramBoolean;
    }
    
    public void run()
    {
      ImageDownloader.readFromCache(key, context, allowCachedRedirects);
    }
  }
  
  private static class DownloadImageWorkItem
    implements Runnable
  {
    private Context context;
    private ImageDownloader.RequestKey key;
    
    DownloadImageWorkItem(Context paramContext, ImageDownloader.RequestKey paramRequestKey)
    {
      context = paramContext;
      key = paramRequestKey;
    }
    
    public void run()
    {
      ImageDownloader.download(key, context);
    }
  }
  
  private static class DownloaderContext
  {
    boolean isCancelled;
    ImageRequest request;
    WorkQueue.WorkItem workItem;
    
    private DownloaderContext() {}
  }
  
  private static class RequestKey
  {
    private static final int HASH_MULTIPLIER = 37;
    private static final int HASH_SEED = 29;
    Object tag;
    Uri uri;
    
    RequestKey(Uri paramUri, Object paramObject)
    {
      uri = paramUri;
      tag = paramObject;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (paramObject != null)
      {
        bool1 = bool2;
        if ((paramObject instanceof RequestKey))
        {
          paramObject = (RequestKey)paramObject;
          bool1 = bool2;
          if (uri == uri)
          {
            bool1 = bool2;
            if (tag == tag) {
              bool1 = true;
            }
          }
        }
      }
      return bool1;
    }
    
    public int hashCode()
    {
      return (1073 + uri.hashCode()) * 37 + tag.hashCode();
    }
  }
}

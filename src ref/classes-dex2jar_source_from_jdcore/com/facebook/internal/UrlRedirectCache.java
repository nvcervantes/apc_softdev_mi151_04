package com.facebook.internal;

import com.facebook.LoggingBehavior;
import java.io.IOException;

class UrlRedirectCache
{
  private static final String REDIRECT_CONTENT_TAG;
  static final String TAG = "UrlRedirectCache";
  private static FileLruCache urlRedirectCache;
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(TAG);
    localStringBuilder.append("_Redirect");
    REDIRECT_CONTENT_TAG = localStringBuilder.toString();
  }
  
  UrlRedirectCache() {}
  
  /* Error */
  static void cacheUriRedirect(android.net.Uri paramUri1, android.net.Uri paramUri2)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +63 -> 64
    //   4: aload_1
    //   5: ifnonnull +4 -> 9
    //   8: return
    //   9: aconst_null
    //   10: astore_3
    //   11: aconst_null
    //   12: astore_2
    //   13: invokestatic 42	com/facebook/internal/UrlRedirectCache:getCache	()Lcom/facebook/internal/FileLruCache;
    //   16: aload_0
    //   17: invokevirtual 45	android/net/Uri:toString	()Ljava/lang/String;
    //   20: getstatic 32	com/facebook/internal/UrlRedirectCache:REDIRECT_CONTENT_TAG	Ljava/lang/String;
    //   23: invokevirtual 51	com/facebook/internal/FileLruCache:openPutStream	(Ljava/lang/String;Ljava/lang/String;)Ljava/io/OutputStream;
    //   26: astore_0
    //   27: aload_0
    //   28: aload_1
    //   29: invokevirtual 45	android/net/Uri:toString	()Ljava/lang/String;
    //   32: invokevirtual 57	java/lang/String:getBytes	()[B
    //   35: invokevirtual 63	java/io/OutputStream:write	([B)V
    //   38: aload_0
    //   39: invokestatic 69	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   42: return
    //   43: astore_1
    //   44: goto +9 -> 53
    //   47: goto +12 -> 59
    //   50: astore_1
    //   51: aload_2
    //   52: astore_0
    //   53: aload_0
    //   54: invokestatic 69	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   57: aload_1
    //   58: athrow
    //   59: aload_0
    //   60: invokestatic 69	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   63: return
    //   64: return
    //   65: astore_0
    //   66: aload_3
    //   67: astore_0
    //   68: goto -9 -> 59
    //   71: astore_1
    //   72: goto -25 -> 47
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	75	0	paramUri1	android.net.Uri
    //   0	75	1	paramUri2	android.net.Uri
    //   12	40	2	localObject1	Object
    //   10	57	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   27	38	43	finally
    //   13	27	50	finally
    //   13	27	65	java/io/IOException
    //   27	38	71	java/io/IOException
  }
  
  static void clearCache()
  {
    try
    {
      getCache().clearCache();
      return;
    }
    catch (IOException localIOException)
    {
      LoggingBehavior localLoggingBehavior = LoggingBehavior.CACHE;
      String str = TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("clearCache failed ");
      localStringBuilder.append(localIOException.getMessage());
      Logger.log(localLoggingBehavior, 5, str, localStringBuilder.toString());
    }
  }
  
  static FileLruCache getCache()
    throws IOException
  {
    try
    {
      if (urlRedirectCache == null) {
        urlRedirectCache = new FileLruCache(TAG, new FileLruCache.Limits());
      }
      FileLruCache localFileLruCache = urlRedirectCache;
      return localFileLruCache;
    }
    finally {}
  }
  
  /* Error */
  static android.net.Uri getRedirectedUri(android.net.Uri paramUri)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aload_0
    //   7: invokevirtual 45	android/net/Uri:toString	()Ljava/lang/String;
    //   10: astore_3
    //   11: invokestatic 42	com/facebook/internal/UrlRedirectCache:getCache	()Lcom/facebook/internal/FileLruCache;
    //   14: astore 5
    //   16: aconst_null
    //   17: astore_0
    //   18: iconst_0
    //   19: istore_1
    //   20: aload 5
    //   22: aload_3
    //   23: getstatic 32	com/facebook/internal/UrlRedirectCache:REDIRECT_CONTENT_TAG	Ljava/lang/String;
    //   26: invokevirtual 104	com/facebook/internal/FileLruCache:get	(Ljava/lang/String;Ljava/lang/String;)Ljava/io/InputStream;
    //   29: astore 4
    //   31: aload 4
    //   33: ifnull +78 -> 111
    //   36: iconst_1
    //   37: istore_1
    //   38: new 106	java/io/InputStreamReader
    //   41: dup
    //   42: aload 4
    //   44: invokespecial 109	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   47: astore_3
    //   48: sipush 128
    //   51: newarray char
    //   53: astore_0
    //   54: new 15	java/lang/StringBuilder
    //   57: dup
    //   58: invokespecial 18	java/lang/StringBuilder:<init>	()V
    //   61: astore 4
    //   63: aload_3
    //   64: aload_0
    //   65: iconst_0
    //   66: aload_0
    //   67: arraylength
    //   68: invokevirtual 113	java/io/InputStreamReader:read	([CII)I
    //   71: istore_2
    //   72: iload_2
    //   73: ifle +15 -> 88
    //   76: aload 4
    //   78: aload_0
    //   79: iconst_0
    //   80: iload_2
    //   81: invokevirtual 116	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   84: pop
    //   85: goto -22 -> 63
    //   88: aload_3
    //   89: invokestatic 69	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   92: aload 4
    //   94: invokevirtual 30	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   97: astore 4
    //   99: aload_3
    //   100: astore_0
    //   101: aload 4
    //   103: astore_3
    //   104: goto -84 -> 20
    //   107: astore_0
    //   108: goto +42 -> 150
    //   111: iload_1
    //   112: ifeq +14 -> 126
    //   115: aload_3
    //   116: invokestatic 120	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   119: astore_3
    //   120: aload_0
    //   121: invokestatic 69	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   124: aload_3
    //   125: areturn
    //   126: aload_0
    //   127: invokestatic 69	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   130: aconst_null
    //   131: areturn
    //   132: astore 4
    //   134: aload_0
    //   135: astore_3
    //   136: aload 4
    //   138: astore_0
    //   139: goto +11 -> 150
    //   142: aload_0
    //   143: astore_3
    //   144: goto +14 -> 158
    //   147: astore_0
    //   148: aconst_null
    //   149: astore_3
    //   150: aload_3
    //   151: invokestatic 69	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   154: aload_0
    //   155: athrow
    //   156: aconst_null
    //   157: astore_3
    //   158: aload_3
    //   159: invokestatic 69	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   162: aconst_null
    //   163: areturn
    //   164: astore_0
    //   165: goto -9 -> 156
    //   168: astore_3
    //   169: goto -27 -> 142
    //   172: astore_0
    //   173: goto -15 -> 158
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	176	0	paramUri	android.net.Uri
    //   19	93	1	i	int
    //   71	10	2	j	int
    //   10	149	3	localObject1	Object
    //   168	1	3	localIOException	IOException
    //   29	73	4	localObject2	Object
    //   132	5	4	localObject3	Object
    //   14	7	5	localFileLruCache	FileLruCache
    // Exception table:
    //   from	to	target	type
    //   48	63	107	finally
    //   63	72	107	finally
    //   76	85	107	finally
    //   88	99	107	finally
    //   20	31	132	finally
    //   38	48	132	finally
    //   115	120	132	finally
    //   11	16	147	finally
    //   11	16	164	java/io/IOException
    //   20	31	168	java/io/IOException
    //   38	48	168	java/io/IOException
    //   115	120	168	java/io/IOException
    //   48	63	172	java/io/IOException
    //   63	72	172	java/io/IOException
    //   76	85	172	java/io/IOException
    //   88	99	172	java/io/IOException
  }
}

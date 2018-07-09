package com.facebook.internal;

import android.content.Context;
import android.net.Uri;
import com.facebook.LoggingBehavior;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class ImageResponseCache
{
  static final String TAG = "ImageResponseCache";
  private static FileLruCache imageCache;
  
  ImageResponseCache() {}
  
  static void clearCache(Context paramContext)
  {
    try
    {
      getCache(paramContext).clearCache();
      return;
    }
    catch (IOException paramContext)
    {
      LoggingBehavior localLoggingBehavior = LoggingBehavior.CACHE;
      String str = TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("clearCache failed ");
      localStringBuilder.append(paramContext.getMessage());
      Logger.log(localLoggingBehavior, 5, str, localStringBuilder.toString());
    }
  }
  
  static FileLruCache getCache(Context paramContext)
    throws IOException
  {
    try
    {
      if (imageCache == null) {
        imageCache = new FileLruCache(TAG, new FileLruCache.Limits());
      }
      paramContext = imageCache;
      return paramContext;
    }
    finally {}
  }
  
  static InputStream getCachedImageStream(Uri paramUri, Context paramContext)
  {
    if ((paramUri != null) && (isCDNURL(paramUri))) {
      try
      {
        paramUri = getCache(paramContext).get(paramUri.toString());
        return paramUri;
      }
      catch (IOException paramUri)
      {
        Logger.log(LoggingBehavior.CACHE, 5, TAG, paramUri.toString());
      }
    }
    return null;
  }
  
  static InputStream interceptAndCacheImageStream(Context paramContext, HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    Uri localUri;
    InputStream localInputStream2;
    InputStream localInputStream1;
    if (paramHttpURLConnection.getResponseCode() == 200)
    {
      localUri = Uri.parse(paramHttpURLConnection.getURL().toString());
      localInputStream2 = paramHttpURLConnection.getInputStream();
      localInputStream1 = localInputStream2;
    }
    try
    {
      if (isCDNURL(localUri))
      {
        paramContext = getCache(paramContext).interceptAndPut(localUri.toString(), new BufferedHttpInputStream(localInputStream2, paramHttpURLConnection));
        return paramContext;
        localInputStream1 = null;
      }
      return localInputStream1;
    }
    catch (IOException paramContext) {}
    return localInputStream2;
  }
  
  private static boolean isCDNURL(Uri paramUri)
  {
    if (paramUri != null)
    {
      paramUri = paramUri.getHost();
      if (paramUri.endsWith("fbcdn.net")) {
        return true;
      }
      if ((paramUri.startsWith("fbcdn")) && (paramUri.endsWith("akamaihd.net"))) {
        return true;
      }
    }
    return false;
  }
  
  private static class BufferedHttpInputStream
    extends BufferedInputStream
  {
    HttpURLConnection connection;
    
    BufferedHttpInputStream(InputStream paramInputStream, HttpURLConnection paramHttpURLConnection)
    {
      super(8192);
      connection = paramHttpURLConnection;
    }
    
    public void close()
      throws IOException
    {
      super.close();
      Utility.disconnectQuietly(connection);
    }
  }
}

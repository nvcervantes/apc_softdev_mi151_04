package com.android.volley.toolbox;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import com.android.volley.RequestQueue;
import java.io.File;

public class Volley
{
  private static final String DEFAULT_CACHE_DIR = "volley";
  
  public Volley() {}
  
  public static RequestQueue newRequestQueue(Context paramContext)
  {
    return newRequestQueue(paramContext, null);
  }
  
  public static RequestQueue newRequestQueue(Context paramContext, HttpStack paramHttpStack)
  {
    File localFile = new File(paramContext.getCacheDir(), "volley");
    try
    {
      str = paramContext.getPackageName();
      paramContext = paramContext.getPackageManager().getPackageInfo(str, 0);
      str = String.valueOf(String.valueOf(str));
      int i = versionCode;
      paramContext = new StringBuilder(12 + str.length());
      paramContext.append(str);
      paramContext.append("/");
      paramContext.append(i);
      str = paramContext.toString();
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      String str;
      for (;;) {}
    }
    str = "volley/0";
    paramContext = paramHttpStack;
    if (paramHttpStack == null) {
      if (Build.VERSION.SDK_INT >= 9) {
        paramContext = new HurlStack();
      } else {
        paramContext = new HttpClientStack(AndroidHttpClient.newInstance(str));
      }
    }
    paramContext = new BasicNetwork(paramContext);
    paramContext = new RequestQueue(new DiskBasedCache(localFile), paramContext);
    paramContext.start();
    return paramContext;
  }
}

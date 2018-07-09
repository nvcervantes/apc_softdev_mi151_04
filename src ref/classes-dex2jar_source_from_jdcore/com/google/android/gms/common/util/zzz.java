package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.zzt;
import com.google.android.gms.internal.zzbig;
import com.google.android.gms.internal.zzbih;

public final class zzz
{
  public static boolean zza(Context paramContext, int paramInt)
  {
    if (!zza(paramContext, paramInt, "com.google.android.gms")) {
      return false;
    }
    Object localObject = paramContext.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo("com.google.android.gms", 64);
      return zzt.zza(paramContext).zza((PackageInfo)localObject);
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    if (Log.isLoggable("UidVerifier", 3)) {
      Log.d("UidVerifier", "Package manager can't find google play services package, defaulting to false");
    }
    return false;
  }
  
  @TargetApi(19)
  public static boolean zza(Context paramContext, int paramInt, String paramString)
  {
    return zzbih.zza(paramContext).zza(paramInt, paramString);
  }
}

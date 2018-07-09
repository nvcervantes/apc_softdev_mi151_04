package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Binder;
import android.os.Process;
import com.google.android.gms.common.util.zzs;

public final class zzbig
{
  private Context zza;
  
  public zzbig(Context paramContext)
  {
    zza = paramContext;
  }
  
  public final int zza(String paramString)
  {
    return zza.checkCallingOrSelfPermission(paramString);
  }
  
  public final int zza(String paramString1, String paramString2)
  {
    return zza.getPackageManager().checkPermission(paramString1, paramString2);
  }
  
  public final ApplicationInfo zza(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return zza.getPackageManager().getApplicationInfo(paramString, paramInt);
  }
  
  public final boolean zza()
  {
    if (Binder.getCallingUid() == Process.myUid()) {
      return zzbif.zza(zza);
    }
    if (zzs.zzi())
    {
      String str = zza.getPackageManager().getNameForUid(Binder.getCallingUid());
      if (str != null) {
        return zza.getPackageManager().isInstantApp(str);
      }
    }
    return false;
  }
  
  @TargetApi(19)
  public final boolean zza(int paramInt, String paramString)
  {
    if (zzs.zze()) {}
    try
    {
      ((AppOpsManager)zza.getSystemService("appops")).checkPackage(paramInt, paramString);
      return true;
    }
    catch (SecurityException paramString) {}
    String[] arrayOfString = zza.getPackageManager().getPackagesForUid(paramInt);
    if ((paramString != null) && (arrayOfString != null))
    {
      paramInt = 0;
      while (paramInt < arrayOfString.length)
      {
        if (paramString.equals(arrayOfString[paramInt])) {
          return true;
        }
        paramInt += 1;
      }
    }
    return false;
    return false;
  }
  
  public final String[] zza(int paramInt)
  {
    return zza.getPackageManager().getPackagesForUid(paramInt);
  }
  
  public final PackageInfo zzb(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return zza.getPackageManager().getPackageInfo(paramString, paramInt);
  }
  
  public final CharSequence zzb(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return zza.getPackageManager().getApplicationLabel(zza.getPackageManager().getApplicationInfo(paramString, 0));
  }
}

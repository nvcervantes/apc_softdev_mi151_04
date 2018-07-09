package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbig;
import com.google.android.gms.internal.zzbih;

@Hide
public final class zzd
{
  public static int zza(Context paramContext, String paramString)
  {
    paramContext = zzc(paramContext, paramString);
    if (paramContext != null)
    {
      if (applicationInfo == null) {
        return -1;
      }
      paramContext = applicationInfo.metaData;
      if (paramContext == null) {
        return -1;
      }
      return paramContext.getInt("com.google.android.gms.version", -1);
    }
    return -1;
  }
  
  public static boolean zzb(Context paramContext, String paramString)
  {
    "com.google.android.gms".equals(paramString);
    try
    {
      int i = zzazza0flags;
      return (i & 0x200000) != 0;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  @Nullable
  private static PackageInfo zzc(Context paramContext, String paramString)
  {
    try
    {
      paramContext = zzbih.zza(paramContext).zzb(paramString, 128);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return null;
  }
}

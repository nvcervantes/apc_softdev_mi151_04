package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;

public final class zzj
{
  private static Boolean zza;
  private static Boolean zzb;
  private static Boolean zzc;
  private static Boolean zzd;
  private static Boolean zze;
  
  @TargetApi(20)
  public static boolean zza(Context paramContext)
  {
    if (zzc == null)
    {
      boolean bool;
      if ((zzs.zzf()) && (paramContext.getPackageManager().hasSystemFeature("android.hardware.type.watch"))) {
        bool = true;
      } else {
        bool = false;
      }
      zzc = Boolean.valueOf(bool);
    }
    return zzc.booleanValue();
  }
  
  public static boolean zza(Resources paramResources)
  {
    boolean bool2 = false;
    if (paramResources == null) {
      return false;
    }
    if (zza == null)
    {
      int i;
      if ((getConfigurationscreenLayout & 0xF) > 3) {
        i = 1;
      } else {
        i = 0;
      }
      boolean bool1;
      if (i == 0)
      {
        if (zzb == null)
        {
          paramResources = paramResources.getConfiguration();
          if (((screenLayout & 0xF) <= 3) && (smallestScreenWidthDp >= 600)) {
            bool1 = true;
          } else {
            bool1 = false;
          }
          zzb = Boolean.valueOf(bool1);
        }
        bool1 = bool2;
        if (!zzb.booleanValue()) {}
      }
      else
      {
        bool1 = true;
      }
      zza = Boolean.valueOf(bool1);
    }
    return zza.booleanValue();
  }
  
  @TargetApi(24)
  public static boolean zzb(Context paramContext)
  {
    return ((!zzs.zzh()) || (zzc(paramContext))) && (zza(paramContext));
  }
  
  @TargetApi(21)
  public static boolean zzc(Context paramContext)
  {
    if (zzd == null)
    {
      boolean bool;
      if ((zzs.zzg()) && (paramContext.getPackageManager().hasSystemFeature("cn.google"))) {
        bool = true;
      } else {
        bool = false;
      }
      zzd = Boolean.valueOf(bool);
    }
    return zzd.booleanValue();
  }
  
  public static boolean zzd(Context paramContext)
  {
    if (zze == null)
    {
      boolean bool;
      if ((!paramContext.getPackageManager().hasSystemFeature("android.hardware.type.iot")) && (!paramContext.getPackageManager().hasSystemFeature("android.hardware.type.embedded"))) {
        bool = false;
      } else {
        bool = true;
      }
      zze = Boolean.valueOf(bool);
    }
    return zze.booleanValue();
  }
}

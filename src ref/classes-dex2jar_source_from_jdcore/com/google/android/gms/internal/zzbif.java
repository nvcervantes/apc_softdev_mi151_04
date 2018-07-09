package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.common.util.zzs;

public final class zzbif
{
  private static Context zza;
  private static Boolean zzb;
  
  public static boolean zza(Context paramContext)
  {
    Context localContext;
    for (;;)
    {
      try
      {
        localContext = paramContext.getApplicationContext();
        if ((zza != null) && (zzb != null) && (zza == localContext))
        {
          bool = zzb.booleanValue();
          return bool;
        }
        zzb = null;
        if (zzs.zzi())
        {
          paramContext = Boolean.valueOf(localContext.getPackageManager().isInstantApp());
          zzb = paramContext;
        }
      }
      finally {}
      try
      {
        paramContext.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
        zzb = Boolean.valueOf(true);
      }
      catch (ClassNotFoundException paramContext)
      {
        continue;
      }
      paramContext = Boolean.valueOf(false);
    }
    zza = localContext;
    boolean bool = zzb.booleanValue();
    return bool;
  }
}

package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;

public final class zzdob<T>
{
  private static final Object zza = new Object();
  @SuppressLint({"StaticFieldLeak"})
  private static Context zzb;
  private static boolean zzc = false;
  private static Boolean zzd;
  
  public static void zza(Context paramContext)
  {
    for (;;)
    {
      Context localContext;
      synchronized (zza)
      {
        if ((Build.VERSION.SDK_INT < 24) || (!paramContext.isDeviceProtectedStorage()))
        {
          localContext = paramContext.getApplicationContext();
          if (localContext != null) {
            break label63;
          }
        }
        if (zzb != paramContext) {
          zzd = null;
        }
        zzb = paramContext;
        zzc = false;
        return;
      }
      label63:
      paramContext = localContext;
    }
  }
  
  public static void zzb(Context paramContext)
  {
    if (zzb == null) {
      zza(paramContext);
    }
  }
}

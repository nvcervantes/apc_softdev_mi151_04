package com.google.android.gms.internal;

import android.content.Context;

public final class zzbih
{
  private static zzbih zzb = new zzbih();
  private zzbig zza = null;
  
  public zzbih() {}
  
  public static zzbig zza(Context paramContext)
  {
    return zzb.zzb(paramContext);
  }
  
  private final zzbig zzb(Context paramContext)
  {
    try
    {
      if (zza == null)
      {
        if (paramContext.getApplicationContext() != null) {
          paramContext = paramContext.getApplicationContext();
        }
        zza = new zzbig(paramContext);
      }
      paramContext = zza;
      return paramContext;
    }
    finally {}
  }
}

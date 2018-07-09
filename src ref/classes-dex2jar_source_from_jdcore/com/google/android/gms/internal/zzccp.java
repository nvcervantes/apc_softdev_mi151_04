package com.google.android.gms.internal;

import com.google.android.gms.common.internal.Hide;

@Hide
public final class zzccp
{
  private static zzccp zza;
  private final zzcck zzb = new zzcck();
  private final zzccl zzc = new zzccl();
  
  static
  {
    zzccp localZzccp = new zzccp();
    try
    {
      zza = localZzccp;
      return;
    }
    finally {}
  }
  
  private zzccp() {}
  
  public static zzcck zza()
  {
    return zzczzb;
  }
  
  public static zzccl zzb()
  {
    return zzczzc;
  }
  
  private static zzccp zzc()
  {
    try
    {
      zzccp localZzccp = zza;
      return localZzccp;
    }
    finally {}
  }
}

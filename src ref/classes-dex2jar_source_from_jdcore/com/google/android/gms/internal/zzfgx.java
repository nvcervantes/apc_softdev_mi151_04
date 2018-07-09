package com.google.android.gms.internal;

final class zzfgx
{
  private final zzfhg zza;
  private final byte[] zzb;
  
  private zzfgx(int paramInt)
  {
    zzb = new byte[paramInt];
    zza = zzfhg.zza(zzb);
  }
  
  public final zzfgs zza()
  {
    zza.zzc();
    return new zzfgz(zzb);
  }
  
  public final zzfhg zzb()
  {
    return zza;
  }
}

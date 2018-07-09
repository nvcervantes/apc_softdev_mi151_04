package com.google.android.gms.internal;

public final class zzx<T>
{
  public final T zza;
  public final zzc zzb;
  public final zzae zzc;
  public boolean zzd = false;
  
  private zzx(zzae paramZzae)
  {
    zza = null;
    zzb = null;
    zzc = paramZzae;
  }
  
  private zzx(T paramT, zzc paramZzc)
  {
    zza = paramT;
    zzb = paramZzc;
    zzc = null;
  }
  
  public static <T> zzx<T> zza(zzae paramZzae)
  {
    return new zzx(paramZzae);
  }
  
  public static <T> zzx<T> zza(T paramT, zzc paramZzc)
  {
    return new zzx(paramT, paramZzc);
  }
}

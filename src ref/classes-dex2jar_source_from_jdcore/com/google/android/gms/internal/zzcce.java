package com.google.android.gms.internal;

import com.google.android.gms.common.internal.Hide;

@Hide
public abstract class zzcce<T>
{
  private final int zza;
  private final String zzb;
  private final T zzc;
  
  private zzcce(int paramInt, String paramString, T paramT)
  {
    zza = paramInt;
    zzb = paramString;
    zzc = paramT;
    zzccp.zza().zza(this);
  }
  
  @Hide
  public static zzccg zza(int paramInt, String paramString, Boolean paramBoolean)
  {
    return new zzccg(0, paramString, paramBoolean);
  }
  
  @Hide
  public static zzcch zza(int paramInt1, String paramString, int paramInt2)
  {
    return new zzcch(0, paramString, Integer.valueOf(paramInt2));
  }
  
  @Hide
  public static zzcci zza(int paramInt, String paramString, long paramLong)
  {
    return new zzcci(0, paramString, Long.valueOf(paramLong));
  }
  
  @Hide
  protected abstract T zza(zzccm paramZzccm);
  
  public final String zza()
  {
    return zzb;
  }
  
  public final T zzb()
  {
    return zzc;
  }
  
  @Hide
  public final int zzc()
  {
    return zza;
  }
}

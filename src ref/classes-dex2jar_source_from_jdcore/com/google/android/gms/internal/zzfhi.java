package com.google.android.gms.internal;

import java.io.IOException;

final class zzfhi
  implements zzfli
{
  private final zzfhg zza;
  
  private zzfhi(zzfhg paramZzfhg)
  {
    zza = ((zzfhg)zzfhz.zza(paramZzfhg, "output"));
    zza.zza = this;
  }
  
  public static zzfhi zza(zzfhg paramZzfhg)
  {
    if (zza != null) {
      return zza;
    }
    return new zzfhi(paramZzfhg);
  }
  
  public final int zza()
  {
    return zzfhu.zzg.zzl;
  }
  
  public final void zza(int paramInt, Object paramObject)
  {
    try
    {
      if ((paramObject instanceof zzfgs))
      {
        zza.zzb(paramInt, (zzfgs)paramObject);
        return;
      }
      zza.zzb(paramInt, (zzfjc)paramObject);
      return;
    }
    catch (IOException paramObject)
    {
      throw new RuntimeException(paramObject);
    }
  }
}

package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzcci
  extends zzcce<Long>
{
  public zzcci(int paramInt, String paramString, Long paramLong)
  {
    super(0, paramString, paramLong, null);
  }
  
  private final Long zzb(zzccm paramZzccm)
  {
    try
    {
      long l = paramZzccm.getLongFlagValue(zza(), ((Long)zzb()).longValue(), zzc());
      return Long.valueOf(l);
    }
    catch (RemoteException paramZzccm)
    {
      for (;;) {}
    }
    return (Long)zzb();
  }
}

package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzcch
  extends zzcce<Integer>
{
  public zzcch(int paramInt, String paramString, Integer paramInteger)
  {
    super(0, paramString, paramInteger, null);
  }
  
  private final Integer zzb(zzccm paramZzccm)
  {
    try
    {
      int i = paramZzccm.getIntFlagValue(zza(), ((Integer)zzb()).intValue(), zzc());
      return Integer.valueOf(i);
    }
    catch (RemoteException paramZzccm)
    {
      for (;;) {}
    }
    return (Integer)zzb();
  }
}

package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzccg
  extends zzcce<Boolean>
{
  public zzccg(int paramInt, String paramString, Boolean paramBoolean)
  {
    super(0, paramString, paramBoolean, null);
  }
  
  private final Boolean zzb(zzccm paramZzccm)
  {
    try
    {
      boolean bool = paramZzccm.getBooleanFlagValue(zza(), ((Boolean)zzb()).booleanValue(), zzc());
      return Boolean.valueOf(bool);
    }
    catch (RemoteException paramZzccm)
    {
      for (;;) {}
    }
    return (Boolean)zzb();
  }
}

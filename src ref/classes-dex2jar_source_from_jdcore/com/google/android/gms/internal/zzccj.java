package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzccj
  extends zzcce<String>
{
  public zzccj(int paramInt, String paramString1, String paramString2)
  {
    super(0, paramString1, paramString2, null);
  }
  
  private final String zzb(zzccm paramZzccm)
  {
    try
    {
      paramZzccm = paramZzccm.getStringFlagValue(zza(), (String)zzb(), zzc());
      return paramZzccm;
    }
    catch (RemoteException paramZzccm)
    {
      for (;;) {}
    }
    return (String)zzb();
  }
}

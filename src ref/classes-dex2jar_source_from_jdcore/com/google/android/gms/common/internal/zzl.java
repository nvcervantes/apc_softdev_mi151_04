package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;

public final class zzl
  implements ServiceConnection
{
  private final int zza;
  
  public zzl(zzd paramZzd, int paramInt)
  {
    zza = paramInt;
  }
  
  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    if (paramIBinder == null)
    {
      zzd.zza(zzb, 16);
      return;
    }
    synchronized (zzd.zza(zzb))
    {
      zzd localZzd = zzb;
      if (paramIBinder == null)
      {
        paramComponentName = null;
      }
      else
      {
        paramComponentName = paramIBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        if ((paramComponentName != null) && ((paramComponentName instanceof zzay))) {
          paramComponentName = (zzay)paramComponentName;
        } else {
          paramComponentName = new zzaz(paramIBinder);
        }
      }
      zzd.zza(localZzd, paramComponentName);
      zzb.zza(0, null, zza);
      return;
    }
  }
  
  public final void onServiceDisconnected(ComponentName arg1)
  {
    synchronized (zzd.zza(zzb))
    {
      zzd.zza(zzb, null);
      zzb.zza.sendMessage(zzb.zza.obtainMessage(6, zza, 1));
      return;
    }
  }
}

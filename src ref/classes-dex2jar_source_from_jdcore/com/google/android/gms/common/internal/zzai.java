package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.stats.zza;
import java.util.HashMap;

final class zzai
  extends zzag
  implements Handler.Callback
{
  private final HashMap<zzah, zzaj> zza = new HashMap();
  private final Context zzb;
  private final Handler zzc;
  private final zza zzd;
  private final long zze;
  private final long zzf;
  
  zzai(Context paramContext)
  {
    zzb = paramContext.getApplicationContext();
    zzc = new Handler(paramContext.getMainLooper(), this);
    zzd = zza.zza();
    zze = 5000L;
    zzf = 300000L;
  }
  
  public final boolean handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      return false;
    case 1: 
      synchronized (zza)
      {
        zzah localZzah = (zzah)obj;
        zzaj localZzaj = (zzaj)zza.get(localZzah);
        if ((localZzaj != null) && (localZzaj.zzb() == 3))
        {
          paramMessage = String.valueOf(localZzah);
          ??? = new StringBuilder(47 + String.valueOf(paramMessage).length());
          ((StringBuilder)???).append("Timeout waiting for ServiceConnection callback ");
          ((StringBuilder)???).append(paramMessage);
          Log.wtf("GmsClientSupervisor", ((StringBuilder)???).toString(), new Exception());
          ??? = localZzaj.zze();
          paramMessage = (Message)???;
          if (??? == null) {
            paramMessage = localZzah.zzb();
          }
          ??? = paramMessage;
          if (paramMessage == null) {
            ??? = new ComponentName(localZzah.zza(), "unknown");
          }
          localZzaj.onServiceDisconnected((ComponentName)???);
        }
        return true;
      }
    }
    synchronized (zza)
    {
      paramMessage = (zzah)obj;
      ??? = (zzaj)zza.get(paramMessage);
      if ((??? != null) && (((zzaj)???).zzc()))
      {
        if (((zzaj)???).zza()) {
          ((zzaj)???).zzb("GmsClientSupervisor");
        }
        zza.remove(paramMessage);
      }
      return true;
    }
  }
  
  protected final boolean zza(zzah paramZzah, ServiceConnection paramServiceConnection, String paramString)
  {
    zzbq.zza(paramServiceConnection, "ServiceConnection must not be null");
    for (;;)
    {
      zzaj localZzaj;
      synchronized (zza)
      {
        localZzaj = (zzaj)zza.get(paramZzah);
        if (localZzaj == null)
        {
          localZzaj = new zzaj(this, paramZzah);
          localZzaj.zza(paramServiceConnection, paramString);
          localZzaj.zza(paramString);
          zza.put(paramZzah, localZzaj);
          paramZzah = localZzaj;
        }
        else
        {
          zzc.removeMessages(0, paramZzah);
          if (localZzaj.zza(paramServiceConnection))
          {
            paramZzah = String.valueOf(paramZzah);
            paramServiceConnection = new StringBuilder(81 + String.valueOf(paramZzah).length());
            paramServiceConnection.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");
            paramServiceConnection.append(paramZzah);
            throw new IllegalStateException(paramServiceConnection.toString());
          }
          localZzaj.zza(paramServiceConnection, paramString);
        }
        switch (localZzaj.zzb())
        {
        case 2: 
          localZzaj.zza(paramString);
          paramZzah = localZzaj;
          break;
        case 1: 
          paramServiceConnection.onServiceConnected(localZzaj.zze(), localZzaj.zzd());
          paramZzah = localZzaj;
          boolean bool = paramZzah.zza();
          return bool;
        }
      }
      paramZzah = localZzaj;
    }
  }
  
  protected final void zzb(zzah paramZzah, ServiceConnection paramServiceConnection, String paramString)
  {
    zzbq.zza(paramServiceConnection, "ServiceConnection must not be null");
    synchronized (zza)
    {
      zzaj localZzaj = (zzaj)zza.get(paramZzah);
      if (localZzaj == null)
      {
        paramZzah = String.valueOf(paramZzah);
        paramServiceConnection = new StringBuilder(50 + String.valueOf(paramZzah).length());
        paramServiceConnection.append("Nonexistent connection status for service config: ");
        paramServiceConnection.append(paramZzah);
        throw new IllegalStateException(paramServiceConnection.toString());
      }
      if (!localZzaj.zza(paramServiceConnection))
      {
        paramZzah = String.valueOf(paramZzah);
        paramServiceConnection = new StringBuilder(76 + String.valueOf(paramZzah).length());
        paramServiceConnection.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");
        paramServiceConnection.append(paramZzah);
        throw new IllegalStateException(paramServiceConnection.toString());
      }
      localZzaj.zzb(paramServiceConnection, paramString);
      if (localZzaj.zzc())
      {
        paramZzah = zzc.obtainMessage(0, paramZzah);
        zzc.sendMessageDelayed(paramZzah, zze);
      }
      return;
    }
  }
}

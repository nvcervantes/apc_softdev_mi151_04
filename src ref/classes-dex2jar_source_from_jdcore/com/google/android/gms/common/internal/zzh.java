package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.atomic.AtomicInteger;

@Hide
final class zzh
  extends Handler
{
  public zzh(zzd paramZzd, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  private static void zza(Message paramMessage)
  {
    paramMessage = (zzi)obj;
    paramMessage.zzb();
    paramMessage.zzd();
  }
  
  private static boolean zzb(Message paramMessage)
  {
    if ((what != 2) && (what != 1)) {
      return what == 7;
    }
    return true;
  }
  
  public final void handleMessage(Message paramMessage)
  {
    if (zza.zzc.get() != arg1)
    {
      if (zzb(paramMessage)) {
        zza(paramMessage);
      }
      return;
    }
    if (((what == 1) || (what == 7) || (what == 4) || (what == 5)) && (!zza.zzt()))
    {
      zza(paramMessage);
      return;
    }
    int i = what;
    PendingIntent localPendingIntent = null;
    if (i == 4)
    {
      zzd.zza(zza, new ConnectionResult(arg2));
      if ((zzd.zzb(zza)) && (!zzd.zzc(zza)))
      {
        zzd.zza(zza, 3, null);
        return;
      }
      if (zzd.zzd(zza) != null) {
        paramMessage = zzd.zzd(zza);
      } else {
        paramMessage = new ConnectionResult(8);
      }
      zza.zzb.zza(paramMessage);
      zza.zza(paramMessage);
      return;
    }
    if (what == 5)
    {
      if (zzd.zzd(zza) != null) {
        paramMessage = zzd.zzd(zza);
      } else {
        paramMessage = new ConnectionResult(8);
      }
      zza.zzb.zza(paramMessage);
      zza.zza(paramMessage);
      return;
    }
    if (what == 3)
    {
      if ((obj instanceof PendingIntent)) {
        localPendingIntent = (PendingIntent)obj;
      }
      paramMessage = new ConnectionResult(arg2, localPendingIntent);
      zza.zzb.zza(paramMessage);
      zza.zza(paramMessage);
      return;
    }
    if (what == 6)
    {
      zzd.zza(zza, 5, null);
      if (zzd.zze(zza) != null) {
        zzd.zze(zza).zza(arg2);
      }
      zza.zza(arg2);
      zzd.zza(zza, 5, 1, null);
      return;
    }
    if ((what == 2) && (!zza.zzs()))
    {
      zza(paramMessage);
      return;
    }
    if (zzb(paramMessage))
    {
      ((zzi)obj).zzc();
      return;
    }
    i = what;
    paramMessage = new StringBuilder(45);
    paramMessage.append("Don't know how to handle message: ");
    paramMessage.append(i);
    Log.wtf("GmsClient", paramMessage.toString(), new Exception());
  }
}

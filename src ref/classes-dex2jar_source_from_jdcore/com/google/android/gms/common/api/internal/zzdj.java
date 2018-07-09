package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

final class zzdj
  extends Handler
{
  public zzdj(zzdh paramZzdh, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public final void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      int i = what;
      paramMessage = new StringBuilder(70);
      paramMessage.append("TransformationResultHandler received unknown message type: ");
      paramMessage.append(i);
      Log.e("TransformedResultImpl", paramMessage.toString());
      return;
    case 1: 
      localObject1 = (RuntimeException)obj;
      paramMessage = String.valueOf(((RuntimeException)localObject1).getMessage());
      if (paramMessage.length() != 0) {
        paramMessage = "Runtime exception on the transformation worker thread: ".concat(paramMessage);
      } else {
        paramMessage = new String("Runtime exception on the transformation worker thread: ");
      }
      Log.e("TransformedResultImpl", paramMessage);
      throw ((Throwable)localObject1);
    }
    Object localObject1 = (PendingResult)obj;
    paramMessage = zzdh.zzd(zza);
    if (localObject1 == null) {}
    try
    {
      zzdh.zza(zzdh.zze(zza), new Status(13, "Transform returned null"));
      break label203;
      if ((localObject1 instanceof zzct)) {
        zzdh.zza(zzdh.zze(zza), ((zzct)localObject1).zza());
      } else {
        zzdh.zze(zza).zza((PendingResult)localObject1);
      }
      label203:
      return;
    }
    finally
    {
      for (;;) {}
    }
    throw ((Throwable)localObject1);
  }
}

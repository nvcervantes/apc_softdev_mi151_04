package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

final class zzbk
  extends Handler
{
  zzbk(zzbi paramZzbi, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public final void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      int i = what;
      paramMessage = new StringBuilder(31);
      paramMessage.append("Unknown message id: ");
      paramMessage.append(i);
      Log.w("GACStateManager", paramMessage.toString());
      return;
    case 2: 
      throw ((RuntimeException)obj);
    }
    ((zzbj)obj).zza(zza);
  }
}

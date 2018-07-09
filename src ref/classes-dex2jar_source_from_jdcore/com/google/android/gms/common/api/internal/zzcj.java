package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.internal.zzbq;

final class zzcj
  extends Handler
{
  public zzcj(zzci paramZzci, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public final void handleMessage(Message paramMessage)
  {
    int i = what;
    boolean bool = true;
    if (i != 1) {
      bool = false;
    }
    zzbq.zzb(bool);
    zza.zzb((zzcl)obj);
  }
}

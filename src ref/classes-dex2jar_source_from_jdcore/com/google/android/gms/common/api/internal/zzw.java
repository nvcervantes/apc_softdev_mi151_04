package com.google.android.gms.common.api.internal;

import java.util.concurrent.locks.Lock;

final class zzw
  implements Runnable
{
  zzw(zzv paramZzv) {}
  
  public final void run()
  {
    zzv.zza(zza).lock();
    try
    {
      zzv.zzb(zza);
      return;
    }
    finally
    {
      zzv.zza(zza).unlock();
    }
  }
}

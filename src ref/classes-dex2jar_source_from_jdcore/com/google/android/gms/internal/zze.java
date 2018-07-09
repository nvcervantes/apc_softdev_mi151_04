package com.google.android.gms.internal;

import java.util.concurrent.BlockingQueue;

final class zze
  implements Runnable
{
  zze(zzd paramZzd, zzr paramZzr) {}
  
  public final void run()
  {
    try
    {
      zzd.zza(zzb).put(zza);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
    Thread.currentThread().interrupt();
  }
}

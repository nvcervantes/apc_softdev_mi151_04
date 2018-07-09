package com.google.android.gms.internal;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

public final class zzd
  extends Thread
{
  private static final boolean zza = zzaf.zza;
  private final BlockingQueue<zzr<?>> zzb;
  private final BlockingQueue<zzr<?>> zzc;
  private final zzb zzd;
  private final zzaa zze;
  private volatile boolean zzf = false;
  private final zzf zzg;
  
  public zzd(BlockingQueue<zzr<?>> paramBlockingQueue1, BlockingQueue<zzr<?>> paramBlockingQueue2, zzb paramZzb, zzaa paramZzaa)
  {
    zzb = paramBlockingQueue1;
    zzc = paramBlockingQueue2;
    zzd = paramZzb;
    zze = paramZzaa;
    zzg = new zzf(this);
  }
  
  private final void zzb()
    throws InterruptedException
  {
    zzr localZzr = (zzr)zzb.take();
    localZzr.zza("cache-queue-take");
    localZzr.zze();
    zzc localZzc = zzd.zza(localZzr.zzc());
    if (localZzc == null)
    {
      localZzr.zza("cache-miss");
      if (!zzf.zza(zzg, localZzr)) {
        zzc.put(localZzr);
      }
      return;
    }
    if (localZzc.zza())
    {
      localZzr.zza("cache-hit-expired");
      localZzr.zza(localZzc);
      if (!zzf.zza(zzg, localZzr)) {
        zzc.put(localZzr);
      }
      return;
    }
    localZzr.zza("cache-hit");
    zzx localZzx = localZzr.zza(new zzp(zza, zzg));
    localZzr.zza("cache-hit-parsed");
    int i;
    if (zzf < System.currentTimeMillis()) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0)
    {
      localZzr.zza("cache-hit-refresh-needed");
      localZzr.zza(localZzc);
      zzd = true;
      if (!zzf.zza(zzg, localZzr))
      {
        zze.zza(localZzr, localZzx, new zze(this, localZzr));
        return;
      }
    }
    zze.zza(localZzr, localZzx);
  }
  
  public final void run()
  {
    if (zza) {
      zzaf.zza("start new dispatcher", new Object[0]);
    }
    Process.setThreadPriority(10);
    zzd.zza();
    do
    {
      try
      {
        for (;;)
        {
          zzb();
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
    } while (!zzf);
  }
  
  public final void zza()
  {
    zzf = true;
    interrupt();
  }
}

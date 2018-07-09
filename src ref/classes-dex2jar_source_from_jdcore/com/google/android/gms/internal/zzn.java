package com.google.android.gms.internal;

import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

public final class zzn
  extends Thread
{
  private final BlockingQueue<zzr<?>> zza;
  private final zzm zzb;
  private final zzb zzc;
  private final zzaa zzd;
  private volatile boolean zze = false;
  
  public zzn(BlockingQueue<zzr<?>> paramBlockingQueue, zzm paramZzm, zzb paramZzb, zzaa paramZzaa)
  {
    zza = paramBlockingQueue;
    zzb = paramZzm;
    zzc = paramZzb;
    zzd = paramZzaa;
  }
  
  private final void zzb()
    throws InterruptedException
  {
    long l = SystemClock.elapsedRealtime();
    zzr localZzr = (zzr)zza.take();
    try
    {
      localZzr.zza("network-queue-take");
      localZzr.zze();
      TrafficStats.setThreadStatsTag(localZzr.zzb());
      Object localObject = zzb.zza(localZzr);
      localZzr.zza("network-http-complete");
      if ((zze) && (localZzr.zzl()))
      {
        localZzr.zzb("not-modified");
        localZzr.zzm();
        return;
      }
      localObject = localZzr.zza((zzp)localObject);
      localZzr.zza("network-parse-complete");
      if ((localZzr.zzh()) && (zzb != null))
      {
        zzc.zza(localZzr.zzc(), zzb);
        localZzr.zza("network-cache-written");
      }
      localZzr.zzk();
      zzd.zza(localZzr, (zzx)localObject);
      localZzr.zza((zzx)localObject);
      return;
    }
    catch (Exception localException)
    {
      zzaf.zza(localException, "Unhandled exception %s", new Object[] { localException.toString() });
      zzae localZzae1 = new zzae(localException);
      localZzae1.zza(SystemClock.elapsedRealtime() - l);
      zzd.zza(localZzr, localZzae1);
      localZzr.zzm();
      return;
    }
    catch (zzae localZzae2)
    {
      for (;;)
      {
        localZzae2.zza(SystemClock.elapsedRealtime() - l);
        zzd.zza(localZzr, localZzae2);
      }
    }
  }
  
  public final void run()
  {
    Process.setThreadPriority(10);
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
    } while (!zze);
  }
  
  public final void zza()
  {
    zze = true;
    interrupt();
  }
}

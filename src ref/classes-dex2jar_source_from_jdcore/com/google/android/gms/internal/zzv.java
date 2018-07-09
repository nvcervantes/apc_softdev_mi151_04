package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzv
{
  private final AtomicInteger zza = new AtomicInteger();
  private final Set<zzr<?>> zzb = new HashSet();
  private final PriorityBlockingQueue<zzr<?>> zzc = new PriorityBlockingQueue();
  private final PriorityBlockingQueue<zzr<?>> zzd = new PriorityBlockingQueue();
  private final zzb zze;
  private final zzm zzf;
  private final zzaa zzg;
  private final zzn[] zzh;
  private zzd zzi;
  private final List<zzw> zzj = new ArrayList();
  
  public zzv(zzb paramZzb, zzm paramZzm)
  {
    this(paramZzb, paramZzm, 4);
  }
  
  private zzv(zzb paramZzb, zzm paramZzm, int paramInt)
  {
    this(paramZzb, paramZzm, 4, new zzi(new Handler(Looper.getMainLooper())));
  }
  
  private zzv(zzb paramZzb, zzm paramZzm, int paramInt, zzaa paramZzaa)
  {
    zze = paramZzb;
    zzf = paramZzm;
    zzh = new zzn[4];
    zzg = paramZzaa;
  }
  
  public final <T> zzr<T> zza(zzr<T> paramZzr)
  {
    paramZzr.zza(this);
    synchronized (zzb)
    {
      zzb.add(paramZzr);
      paramZzr.zza(zza.incrementAndGet());
      paramZzr.zza("add-to-queue");
      if (!paramZzr.zzh())
      {
        ??? = zzd;
        ((PriorityBlockingQueue)???).add(paramZzr);
        return paramZzr;
      }
      ??? = zzc;
    }
  }
  
  public final void zza()
  {
    if (zzi != null) {
      zzi.zza();
    }
    Object localObject1 = zzh;
    int j = 0;
    int k = localObject1.length;
    int i = 0;
    while (i < k)
    {
      Object localObject2 = localObject1[i];
      if (localObject2 != null) {
        localObject2.zza();
      }
      i += 1;
    }
    zzi = new zzd(zzc, zzd, zze, zzg);
    zzi.start();
    i = j;
    while (i < zzh.length)
    {
      localObject1 = new zzn(zzd, zzf, zze, zzg);
      zzh[i] = localObject1;
      ((zzn)localObject1).start();
      i += 1;
    }
  }
  
  final <T> void zzb(zzr<T> paramZzr)
  {
    synchronized (zzb)
    {
      zzb.remove(paramZzr);
      synchronized (zzj)
      {
        Iterator localIterator = zzj.iterator();
        while (localIterator.hasNext()) {
          ((zzw)localIterator.next()).zza(paramZzr);
        }
        return;
      }
    }
  }
}

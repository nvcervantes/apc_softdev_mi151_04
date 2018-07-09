package com.google.android.gms.internal;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

final class zzdys
{
  private final ConcurrentHashMap<zzdyt, List<Throwable>> zza = new ConcurrentHashMap(16, 0.75F, 10);
  private final ReferenceQueue<Throwable> zzb = new ReferenceQueue();
  
  zzdys() {}
  
  public final List<Throwable> zza(Throwable paramThrowable, boolean paramBoolean)
  {
    for (;;)
    {
      Reference localReference = zzb.poll();
      if (localReference == null) {
        break;
      }
      zza.remove(localReference);
    }
    paramThrowable = new zzdyt(paramThrowable, null);
    return (List)zza.get(paramThrowable);
  }
}

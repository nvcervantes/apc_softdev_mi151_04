package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

final class zzf
  implements zzt
{
  private final Map<String, List<zzr<?>>> zza = new HashMap();
  private final zzd zzb;
  
  zzf(zzd paramZzd)
  {
    zzb = paramZzd;
  }
  
  private final boolean zzb(zzr<?> paramZzr)
  {
    try
    {
      String str = paramZzr.zzc();
      if (zza.containsKey(str))
      {
        List localList = (List)zza.get(str);
        Object localObject = localList;
        if (localList == null) {
          localObject = new ArrayList();
        }
        paramZzr.zza("waiting-for-response");
        ((List)localObject).add(paramZzr);
        zza.put(str, localObject);
        if (zzaf.zza) {
          zzaf.zzb("Request for cacheKey=%s is in flight, putting on hold.", new Object[] { str });
        }
        return true;
      }
      zza.put(str, null);
      paramZzr.zza(this);
      if (zzaf.zza) {
        zzaf.zzb("new request, sending to network %s", new Object[] { str });
      }
      return false;
    }
    finally {}
  }
  
  public final void zza(zzr<?> paramZzr)
  {
    try
    {
      paramZzr = paramZzr.zzc();
      List localList = (List)zza.remove(paramZzr);
      if ((localList != null) && (!localList.isEmpty()))
      {
        if (zzaf.zza) {
          zzaf.zza("%d waiting requests for cacheKey=%s; resend to network", new Object[] { Integer.valueOf(localList.size()), paramZzr });
        }
        zzr localZzr = (zzr)localList.remove(0);
        zza.put(paramZzr, localList);
        localZzr.zza(this);
        try
        {
          zzd.zza(zzb).put(localZzr);
          return;
        }
        catch (InterruptedException paramZzr)
        {
          zzaf.zzc("Couldn't add request to queue. %s", new Object[] { paramZzr.toString() });
          Thread.currentThread().interrupt();
          zzb.zza();
        }
      }
      return;
    }
    finally {}
  }
  
  public final void zza(zzr<?> paramZzr, zzx<?> paramZzx)
  {
    if ((zzb != null) && (!zzb.zza()))
    {
      paramZzr = paramZzr.zzc();
      try
      {
        Object localObject = (List)zza.remove(paramZzr);
        if (localObject != null)
        {
          if (zzaf.zza) {
            zzaf.zza("Releasing %d waiting requests for cacheKey=%s.", new Object[] { Integer.valueOf(((List)localObject).size()), paramZzr });
          }
          paramZzr = ((List)localObject).iterator();
          while (paramZzr.hasNext())
          {
            localObject = (zzr)paramZzr.next();
            zzd.zzb(zzb).zza((zzr)localObject, paramZzx);
          }
        }
        return;
      }
      finally {}
    }
    zza(paramZzr);
  }
}

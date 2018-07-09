package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzcyj;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class zzar
  extends zzay
{
  private final Map<Api.zze, zzaq> zzb;
  
  public zzar(Map<Api.zze, zzaq> paramMap)
  {
    super(paramMap, null);
    Object localObject;
    zzb = localObject;
  }
  
  private final int zza(@NonNull Api.zze paramZze, @NonNull Map<Api.zze, Integer> paramMap)
  {
    zzbq.zza(paramZze);
    zzbq.zza(paramMap);
    if (!paramZze.zzu()) {
      return 0;
    }
    if (paramMap.containsKey(paramZze)) {
      return ((Integer)paramMap.get(paramZze)).intValue();
    }
    Object localObject = paramMap.keySet().iterator();
    int i;
    if (((Iterator)localObject).hasNext())
    {
      localObject = (Api.zze)((Iterator)localObject).next();
      ((Api.zze)localObject).zzx();
      paramZze.zzx();
      i = ((Integer)paramMap.get(localObject)).intValue();
    }
    else
    {
      i = -1;
    }
    int j = i;
    if (i == -1) {
      j = zzf.zza(zzao.zza(zza), paramZze.zzx());
    }
    paramMap.put(paramZze, Integer.valueOf(j));
    return j;
  }
  
  @WorkerThread
  public final void zza()
  {
    Object localObject2 = new ArrayList();
    Object localObject3 = new ArrayList();
    Object localObject1 = zzb.keySet().iterator();
    Object localObject4;
    while (((Iterator)localObject1).hasNext())
    {
      localObject4 = (Api.zze)((Iterator)localObject1).next();
      if ((((Api.zze)localObject4).zzu()) && (!zzaq.zza((zzaq)zzb.get(localObject4)))) {
        ((List)localObject2).add(localObject4);
      } else {
        ((List)localObject3).add(localObject4);
      }
    }
    localObject1 = new HashMap(zzb.size());
    int i = -1;
    boolean bool = ((List)localObject2).isEmpty();
    int k = 0;
    int j = 0;
    int m;
    if (bool)
    {
      localObject2 = (ArrayList)localObject3;
      m = ((ArrayList)localObject2).size();
      do
      {
        if (j >= m) {
          break;
        }
        localObject3 = ((ArrayList)localObject2).get(j);
        j += 1;
        k = zza((Api.zze)localObject3, (Map)localObject1);
        i = k;
      } while (k != 0);
      i = k;
    }
    else
    {
      localObject2 = (ArrayList)localObject2;
      m = ((ArrayList)localObject2).size();
      j = k;
      while (j < m)
      {
        localObject3 = ((ArrayList)localObject2).get(j);
        j += 1;
        k = zza((Api.zze)localObject3, (Map)localObject1);
        i = k;
        if (k != 0) {
          i = k;
        }
      }
    }
    if (i != 0)
    {
      localObject1 = new ConnectionResult(i, null);
      zzao.zzc(zza).zza(new zzas(this, zza, (ConnectionResult)localObject1));
      return;
    }
    if (zzao.zzd(zza)) {
      zzao.zze(zza).zzi();
    }
    localObject2 = zzb.keySet().iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (Api.zze)((Iterator)localObject2).next();
      localObject4 = (zzj)zzb.get(localObject3);
      if ((((Api.zze)localObject3).zzu()) && (zza((Api.zze)localObject3, (Map)localObject1) != 0)) {
        zzao.zzc(zza).zza(new zzat(this, zza, (zzj)localObject4));
      } else {
        ((Api.zze)localObject3).zza((zzj)localObject4);
      }
    }
  }
}

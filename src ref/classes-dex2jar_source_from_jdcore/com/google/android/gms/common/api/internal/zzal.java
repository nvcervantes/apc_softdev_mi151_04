package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzbz;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class zzal
  implements zzbh
{
  private final zzbi zza;
  private boolean zzb = false;
  
  public zzal(zzbi paramZzbi)
  {
    zza = paramZzbi;
  }
  
  public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zza(T paramT)
  {
    return zzb(paramT);
  }
  
  public final void zza() {}
  
  public final void zza(int paramInt)
  {
    zza.zza(null);
    zza.zze.zza(paramInt, zzb);
  }
  
  public final void zza(Bundle paramBundle) {}
  
  public final void zza(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean) {}
  
  public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zzb(T paramT)
  {
    try
    {
      zza.zzd.zze.zza(paramT);
      Object localObject1 = zza.zzd;
      Object localObject2 = paramT.zzc();
      localObject2 = (Api.zze)zzb.get(localObject2);
      zzbq.zza(localObject2, "Appropriate Api was not requested.");
      if ((!((Api.zze)localObject2).zzs()) && (zza.zzb.containsKey(paramT.zzc())))
      {
        paramT.zzc(new Status(17));
        return paramT;
      }
      localObject1 = localObject2;
      if ((localObject2 instanceof zzbz)) {
        localObject1 = zzbz.zzi();
      }
      paramT.zzb((Api.zzb)localObject1);
      return paramT;
    }
    catch (DeadObjectException localDeadObjectException)
    {
      for (;;) {}
    }
    zza.zza(new zzam(this, this));
    return paramT;
  }
  
  public final boolean zzb()
  {
    if (zzb) {
      return false;
    }
    if (zza.zzd.zzg())
    {
      zzb = true;
      Iterator localIterator = zza.zzd.zzd.iterator();
      while (localIterator.hasNext()) {
        ((zzdh)localIterator.next()).zza();
      }
      return false;
    }
    zza.zza(null);
    return true;
  }
  
  public final void zzc()
  {
    if (zzb)
    {
      zzb = false;
      zza.zza(new zzan(this, this));
    }
  }
  
  final void zzd()
  {
    if (zzb)
    {
      zzb = false;
      zza.zzd.zze.zza();
      zzb();
    }
  }
}

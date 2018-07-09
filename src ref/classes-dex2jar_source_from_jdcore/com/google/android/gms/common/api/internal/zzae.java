package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;

public final class zzae
{
  private final Map<BasePendingResult<?>, Boolean> zza = Collections.synchronizedMap(new WeakHashMap());
  private final Map<TaskCompletionSource<?>, Boolean> zzb = Collections.synchronizedMap(new WeakHashMap());
  
  public zzae() {}
  
  private final void zza(boolean paramBoolean, Status paramStatus)
  {
    synchronized (zza)
    {
      Object localObject2 = new HashMap(zza);
      synchronized (zzb)
      {
        ??? = new HashMap(zzb);
        localObject2 = ((Map)localObject2).entrySet().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          ??? = (Map.Entry)((Iterator)localObject2).next();
          if ((paramBoolean) || (((Boolean)((Map.Entry)???).getValue()).booleanValue())) {
            ((BasePendingResult)((Map.Entry)???).getKey()).zzd(paramStatus);
          }
        }
        ??? = ((Map)???).entrySet().iterator();
        while (((Iterator)???).hasNext())
        {
          localObject2 = (Map.Entry)((Iterator)???).next();
          if ((paramBoolean) || (((Boolean)((Map.Entry)localObject2).getValue()).booleanValue())) {
            ((TaskCompletionSource)((Map.Entry)localObject2).getKey()).trySetException(new ApiException(paramStatus));
          }
        }
        return;
      }
    }
  }
  
  final void zza(BasePendingResult<? extends Result> paramBasePendingResult, boolean paramBoolean)
  {
    zza.put(paramBasePendingResult, Boolean.valueOf(paramBoolean));
    paramBasePendingResult.zza(new zzaf(this, paramBasePendingResult));
  }
  
  final <TResult> void zza(TaskCompletionSource<TResult> paramTaskCompletionSource, boolean paramBoolean)
  {
    zzb.put(paramTaskCompletionSource, Boolean.valueOf(paramBoolean));
    paramTaskCompletionSource.getTask().addOnCompleteListener(new zzag(this, paramTaskCompletionSource));
  }
  
  final boolean zza()
  {
    return (!zza.isEmpty()) || (!zzb.isEmpty());
  }
  
  public final void zzb()
  {
    zza(false, zzbm.zza);
  }
  
  public final void zzc()
  {
    zza(true, zzdk.zza);
  }
}

package com.google.android.gms.common.api.internal;

import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Hide
public final class zzj
{
  private final ArrayMap<zzh<?>, ConnectionResult> zza = new ArrayMap();
  private final ArrayMap<zzh<?>, String> zzb = new ArrayMap();
  private final TaskCompletionSource<Map<zzh<?>, String>> zzc = new TaskCompletionSource();
  private int zzd;
  private boolean zze = false;
  
  public zzj(Iterable<? extends GoogleApi<?>> paramIterable)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      GoogleApi localGoogleApi = (GoogleApi)paramIterable.next();
      zza.put(localGoogleApi.zzc(), null);
    }
    zzd = zza.keySet().size();
  }
  
  public final Set<zzh<?>> zza()
  {
    return zza.keySet();
  }
  
  public final void zza(zzh<?> paramZzh, ConnectionResult paramConnectionResult, @Nullable String paramString)
  {
    zza.put(paramZzh, paramConnectionResult);
    zzb.put(paramZzh, paramString);
    zzd -= 1;
    if (!paramConnectionResult.isSuccess()) {
      zze = true;
    }
    if (zzd == 0)
    {
      if (zze)
      {
        paramZzh = new AvailabilityException(zza);
        zzc.setException(paramZzh);
        return;
      }
      zzc.setResult(zzb);
    }
  }
  
  public final Task<Map<zzh<?>, String>> zzb()
  {
    return zzc.getTask();
  }
}

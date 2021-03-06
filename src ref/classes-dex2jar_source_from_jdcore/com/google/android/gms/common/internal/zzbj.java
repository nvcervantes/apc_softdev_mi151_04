package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

@Hide
public final class zzbj
{
  private static final zzbp zza = new zzbk();
  
  public static <R extends Result> Task<Void> zza(PendingResult<R> paramPendingResult)
  {
    return zza(paramPendingResult, new zzbn());
  }
  
  public static <R extends Result, T extends Response<R>> Task<T> zza(PendingResult<R> paramPendingResult, T paramT)
  {
    return zza(paramPendingResult, new zzbm(paramT));
  }
  
  public static <R extends Result, T> Task<T> zza(PendingResult<R> paramPendingResult, zzbo<R, T> paramZzbo)
  {
    zzbp localZzbp = zza;
    TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    paramPendingResult.zza(new zzbl(paramPendingResult, localTaskCompletionSource, paramZzbo, localZzbp));
    return localTaskCompletionSource.getTask();
  }
}

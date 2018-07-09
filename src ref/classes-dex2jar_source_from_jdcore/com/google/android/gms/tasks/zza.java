package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zza<TResult, TContinuationResult>
  implements zzm<TResult>
{
  private final Executor zza;
  private final Continuation<TResult, TContinuationResult> zzb;
  private final zzp<TContinuationResult> zzc;
  
  public zza(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, TContinuationResult> paramContinuation, @NonNull zzp<TContinuationResult> paramZzp)
  {
    zza = paramExecutor;
    zzb = paramContinuation;
    zzc = paramZzp;
  }
  
  public final void zza()
  {
    throw new UnsupportedOperationException();
  }
  
  public final void zza(@NonNull Task<TResult> paramTask)
  {
    zza.execute(new zzb(this, paramTask));
  }
}

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzc<TResult, TContinuationResult>
  implements OnFailureListener, OnSuccessListener<TContinuationResult>, zzm<TResult>
{
  private final Executor zza;
  private final Continuation<TResult, Task<TContinuationResult>> zzb;
  private final zzp<TContinuationResult> zzc;
  
  public zzc(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation, @NonNull zzp<TContinuationResult> paramZzp)
  {
    zza = paramExecutor;
    zzb = paramContinuation;
    zzc = paramZzp;
  }
  
  public final void onFailure(@NonNull Exception paramException)
  {
    zzc.zza(paramException);
  }
  
  public final void onSuccess(TContinuationResult paramTContinuationResult)
  {
    zzc.zza(paramTContinuationResult);
  }
  
  public final void zza()
  {
    throw new UnsupportedOperationException();
  }
  
  public final void zza(@NonNull Task<TResult> paramTask)
  {
    zza.execute(new zzd(this, paramTask));
  }
}

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzk<TResult, TContinuationResult>
  implements OnFailureListener, OnSuccessListener<TContinuationResult>, zzm<TResult>
{
  private final Executor zza;
  private final SuccessContinuation<TResult, TContinuationResult> zzb;
  private final zzp<TContinuationResult> zzc;
  
  public zzk(@NonNull Executor paramExecutor, @NonNull SuccessContinuation<TResult, TContinuationResult> paramSuccessContinuation, @NonNull zzp<TContinuationResult> paramZzp)
  {
    zza = paramExecutor;
    zzb = paramSuccessContinuation;
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
    zza.execute(new zzl(this, paramTask));
  }
}

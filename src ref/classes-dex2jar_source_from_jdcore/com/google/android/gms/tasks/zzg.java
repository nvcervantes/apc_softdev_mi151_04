package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzg<TResult>
  implements zzm<TResult>
{
  private final Executor zza;
  private final Object zzb = new Object();
  private OnFailureListener zzc;
  
  public zzg(@NonNull Executor paramExecutor, @NonNull OnFailureListener paramOnFailureListener)
  {
    zza = paramExecutor;
    zzc = paramOnFailureListener;
  }
  
  public final void zza()
  {
    synchronized (zzb)
    {
      zzc = null;
      return;
    }
  }
  
  public final void zza(@NonNull Task<TResult> paramTask)
  {
    if (!paramTask.isSuccessful()) {
      synchronized (zzb)
      {
        if (zzc == null) {
          return;
        }
        zza.execute(new zzh(this, paramTask));
        return;
      }
    }
  }
}

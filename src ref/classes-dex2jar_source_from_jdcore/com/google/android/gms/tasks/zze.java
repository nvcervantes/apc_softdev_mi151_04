package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zze<TResult>
  implements zzm<TResult>
{
  private final Executor zza;
  private final Object zzb = new Object();
  private OnCompleteListener<TResult> zzc;
  
  public zze(@NonNull Executor paramExecutor, @NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    zza = paramExecutor;
    zzc = paramOnCompleteListener;
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
    synchronized (zzb)
    {
      if (zzc == null) {
        return;
      }
      zza.execute(new zzf(this, paramTask));
      return;
    }
  }
}

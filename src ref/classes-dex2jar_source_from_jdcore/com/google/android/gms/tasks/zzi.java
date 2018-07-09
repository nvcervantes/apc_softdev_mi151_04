package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzi<TResult>
  implements zzm<TResult>
{
  private final Executor zza;
  private final Object zzb = new Object();
  private OnSuccessListener<? super TResult> zzc;
  
  public zzi(@NonNull Executor paramExecutor, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    zza = paramExecutor;
    zzc = paramOnSuccessListener;
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
    if (paramTask.isSuccessful()) {
      synchronized (zzb)
      {
        if (zzc == null) {
          return;
        }
        zza.execute(new zzj(this, paramTask));
        return;
      }
    }
  }
}

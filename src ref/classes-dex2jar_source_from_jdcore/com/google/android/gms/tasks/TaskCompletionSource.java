package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public class TaskCompletionSource<TResult>
{
  private final zzp<TResult> zza = new zzp();
  
  public TaskCompletionSource() {}
  
  @NonNull
  public Task<TResult> getTask()
  {
    return zza;
  }
  
  public void setException(@NonNull Exception paramException)
  {
    zza.zza(paramException);
  }
  
  public void setResult(TResult paramTResult)
  {
    zza.zza(paramTResult);
  }
  
  public boolean trySetException(@NonNull Exception paramException)
  {
    return zza.zzb(paramException);
  }
  
  public boolean trySetResult(TResult paramTResult)
  {
    return zza.zzb(paramTResult);
  }
}

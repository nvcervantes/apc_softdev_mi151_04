package com.google.android.gms.tasks;

final class zzd
  implements Runnable
{
  zzd(zzc paramZzc, Task paramTask) {}
  
  public final void run()
  {
    try
    {
      Task localTask = (Task)zzc.zza(zzb).then(zza);
      if (localTask == null)
      {
        zzb.onFailure(new NullPointerException("Continuation returned null"));
        return;
      }
      localTask.addOnSuccessListener(TaskExecutors.zza, zzb);
      localTask.addOnFailureListener(TaskExecutors.zza, zzb);
      return;
    }
    catch (Exception localException)
    {
      zzc.zzb(zzb).zza(localException);
      return;
    }
    catch (RuntimeExecutionException localRuntimeExecutionException)
    {
      if ((localRuntimeExecutionException.getCause() instanceof Exception))
      {
        zzc.zzb(zzb).zza((Exception)localRuntimeExecutionException.getCause());
        return;
      }
      zzc.zzb(zzb).zza(localRuntimeExecutionException);
    }
  }
}

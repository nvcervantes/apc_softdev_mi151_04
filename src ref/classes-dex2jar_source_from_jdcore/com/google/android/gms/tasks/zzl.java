package com.google.android.gms.tasks;

final class zzl
  implements Runnable
{
  zzl(zzk paramZzk, Task paramTask) {}
  
  public final void run()
  {
    try
    {
      Task localTask = zzk.zza(zzb).then(zza.getResult());
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
      zzb.onFailure(localException);
      return;
    }
    catch (RuntimeExecutionException localRuntimeExecutionException)
    {
      if ((localRuntimeExecutionException.getCause() instanceof Exception))
      {
        zzb.onFailure((Exception)localRuntimeExecutionException.getCause());
        return;
      }
      zzb.onFailure(localRuntimeExecutionException);
    }
  }
}

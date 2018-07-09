package com.google.android.gms.tasks;

final class zzb
  implements Runnable
{
  zzb(zza paramZza, Task paramTask) {}
  
  public final void run()
  {
    try
    {
      Object localObject = zza.zza(zzb).then(zza);
      zza.zzb(zzb).zza(localObject);
      return;
    }
    catch (Exception localException)
    {
      zza.zzb(zzb).zza(localException);
      return;
    }
    catch (RuntimeExecutionException localRuntimeExecutionException)
    {
      if ((localRuntimeExecutionException.getCause() instanceof Exception))
      {
        zza.zzb(zzb).zza((Exception)localRuntimeExecutionException.getCause());
        return;
      }
      zza.zzb(zzb).zza(localRuntimeExecutionException);
    }
  }
}

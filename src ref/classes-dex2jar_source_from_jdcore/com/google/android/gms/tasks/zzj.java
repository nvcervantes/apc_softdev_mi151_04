package com.google.android.gms.tasks;

final class zzj
  implements Runnable
{
  zzj(zzi paramZzi, Task paramTask) {}
  
  public final void run()
  {
    synchronized (zzi.zza(zzb))
    {
      if (zzi.zzb(zzb) != null) {
        zzi.zzb(zzb).onSuccess(zza.getResult());
      }
      return;
    }
  }
}

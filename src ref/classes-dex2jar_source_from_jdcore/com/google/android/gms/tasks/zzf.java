package com.google.android.gms.tasks;

final class zzf
  implements Runnable
{
  zzf(zze paramZze, Task paramTask) {}
  
  public final void run()
  {
    synchronized (zze.zza(zzb))
    {
      if (zze.zzb(zzb) != null) {
        zze.zzb(zzb).onComplete(zza);
      }
      return;
    }
  }
}

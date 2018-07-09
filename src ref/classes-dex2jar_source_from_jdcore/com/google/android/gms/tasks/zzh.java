package com.google.android.gms.tasks;

final class zzh
  implements Runnable
{
  zzh(zzg paramZzg, Task paramTask) {}
  
  public final void run()
  {
    synchronized (zzg.zza(zzb))
    {
      if (zzg.zzb(zzb) != null) {
        zzg.zzb(zzb).onFailure(zza.getException());
      }
      return;
    }
  }
}

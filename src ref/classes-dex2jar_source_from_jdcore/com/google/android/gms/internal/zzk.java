package com.google.android.gms.internal;

final class zzk
  implements Runnable
{
  private final zzr zza;
  private final zzx zzb;
  private final Runnable zzc;
  
  public zzk(zzi paramZzi, zzr paramZzr, zzx paramZzx, Runnable paramRunnable)
  {
    zza = paramZzr;
    zzb = paramZzx;
    zzc = paramRunnable;
  }
  
  public final void run()
  {
    zza.zze();
    int i;
    if (zzb.zzc == null) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      zza.zza(zzb.zza);
    } else {
      zza.zza(zzb.zzc);
    }
    if (zzb.zzd) {
      zza.zza("intermediate-response");
    } else {
      zza.zzb("done");
    }
    if (zzc != null) {
      zzc.run();
    }
  }
}

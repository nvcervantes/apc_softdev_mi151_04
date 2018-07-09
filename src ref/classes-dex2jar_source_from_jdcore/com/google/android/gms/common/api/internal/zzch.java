package com.google.android.gms.common.api.internal;

import android.os.Bundle;

final class zzch
  implements Runnable
{
  zzch(zzcg paramZzcg, LifecycleCallback paramLifecycleCallback, String paramString) {}
  
  public final void run()
  {
    if (zzcg.zza(zzc) > 0)
    {
      LifecycleCallback localLifecycleCallback = zza;
      Bundle localBundle;
      if (zzcg.zzb(zzc) != null) {
        localBundle = zzcg.zzb(zzc).getBundle(zzb);
      } else {
        localBundle = null;
      }
      localLifecycleCallback.zza(localBundle);
    }
    if (zzcg.zza(zzc) >= 2) {
      zza.zza();
    }
    if (zzcg.zza(zzc) >= 3) {
      zza.zze();
    }
    if (zzcg.zza(zzc) >= 4) {
      zza.zzb();
    }
    if (zzcg.zza(zzc) >= 5) {
      zza.zzh();
    }
  }
}

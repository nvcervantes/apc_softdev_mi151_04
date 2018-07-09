package com.google.android.gms.common.api.internal;

import android.os.Bundle;

final class zzdd
  implements Runnable
{
  zzdd(zzdc paramZzdc, LifecycleCallback paramLifecycleCallback, String paramString) {}
  
  public final void run()
  {
    if (zzdc.zza(zzc) > 0)
    {
      LifecycleCallback localLifecycleCallback = zza;
      Bundle localBundle;
      if (zzdc.zzb(zzc) != null) {
        localBundle = zzdc.zzb(zzc).getBundle(zzb);
      } else {
        localBundle = null;
      }
      localLifecycleCallback.zza(localBundle);
    }
    if (zzdc.zza(zzc) >= 2) {
      zza.zza();
    }
    if (zzdc.zza(zzc) >= 3) {
      zza.zze();
    }
    if (zzdc.zza(zzc) >= 4) {
      zza.zzb();
    }
    if (zzdc.zza(zzc) >= 5) {
      zza.zzh();
    }
  }
}

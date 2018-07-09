package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;

public final class zzm
  implements zzj
{
  public zzm(zzd paramZzd) {}
  
  public final void zza(@NonNull ConnectionResult paramConnectionResult)
  {
    if (paramConnectionResult.isSuccess())
    {
      zza.zza(null, zza.zzah());
      return;
    }
    if (zzd.zzg(zza) != null) {
      zzd.zzg(zza).zza(paramConnectionResult);
    }
  }
}

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.zze;
import java.util.Collections;
import java.util.Map;

final class zzbv
  implements Runnable
{
  zzbv(zzbu paramZzbu, ConnectionResult paramConnectionResult) {}
  
  public final void run()
  {
    if (zza.isSuccess())
    {
      zzbu.zza(zzb, true);
      if (zzbu.zza(zzb).l_())
      {
        zzbu.zzb(zzb);
        return;
      }
      zzbu.zza(zzb).zza(null, Collections.emptySet());
      return;
    }
    ((zzbo)zzbm.zzj(zzb.zza).get(zzbu.zzc(zzb))).onConnectionFailed(zza);
  }
}

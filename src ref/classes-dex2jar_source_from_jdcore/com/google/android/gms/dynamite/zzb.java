package com.google.android.gms.dynamite;

import android.content.Context;

final class zzb
  implements DynamiteModule.zzd
{
  zzb() {}
  
  public final zzj zza(Context paramContext, String paramString, zzi paramZzi)
    throws DynamiteModule.zzc
  {
    zzj localZzj = new zzj();
    zzb = paramZzi.zza(paramContext, paramString, true);
    if (zzb != 0)
    {
      zzc = 1;
      return localZzj;
    }
    zza = paramZzi.zza(paramContext, paramString);
    if (zza != 0) {
      zzc = -1;
    }
    return localZzj;
  }
}

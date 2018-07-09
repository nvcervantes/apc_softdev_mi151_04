package com.google.android.gms.dynamite;

import android.content.Context;

final class zzc
  implements DynamiteModule.zzd
{
  zzc() {}
  
  public final zzj zza(Context paramContext, String paramString, zzi paramZzi)
    throws DynamiteModule.zzc
  {
    zzj localZzj = new zzj();
    zza = paramZzi.zza(paramContext, paramString);
    if (zza != 0)
    {
      zzc = -1;
      return localZzj;
    }
    zzb = paramZzi.zza(paramContext, paramString, true);
    if (zzb != 0) {
      zzc = 1;
    }
    return localZzj;
  }
}

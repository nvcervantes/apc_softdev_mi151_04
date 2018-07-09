package com.google.android.gms.dynamite;

import android.content.Context;

final class zzd
  implements DynamiteModule.zzd
{
  zzd() {}
  
  public final zzj zza(Context paramContext, String paramString, zzi paramZzi)
    throws DynamiteModule.zzc
  {
    zzj localZzj = new zzj();
    zza = paramZzi.zza(paramContext, paramString);
    zzb = paramZzi.zza(paramContext, paramString, true);
    if ((zza == 0) && (zzb == 0)) {}
    for (int i = 0;; i = -1)
    {
      zzc = i;
      return localZzj;
      if (zza < zzb) {
        break;
      }
    }
    zzc = 1;
    return localZzj;
  }
}

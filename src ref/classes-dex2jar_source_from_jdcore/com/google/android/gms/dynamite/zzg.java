package com.google.android.gms.dynamite;

import android.content.Context;

final class zzg
  implements DynamiteModule.zzd
{
  zzg() {}
  
  public final zzj zza(Context paramContext, String paramString, zzi paramZzi)
    throws DynamiteModule.zzc
  {
    zzj localZzj = new zzj();
    zza = paramZzi.zza(paramContext, paramString);
    if (zza != 0) {}
    for (int i = paramZzi.zza(paramContext, paramString, false);; i = paramZzi.zza(paramContext, paramString, true))
    {
      zzb = i;
      break;
    }
    if ((zza == 0) && (zzb == 0))
    {
      zzc = 0;
      return localZzj;
    }
    if (zzb >= zza)
    {
      zzc = 1;
      return localZzj;
    }
    zzc = -1;
    return localZzj;
  }
}

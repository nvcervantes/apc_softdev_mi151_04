package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbq;

public final class zzd
{
  private zzda zza;
  private Looper zzb;
  
  public zzd() {}
  
  public final GoogleApi.zza zza()
  {
    if (zza == null) {
      zza = new zzg();
    }
    if (zzb == null) {
      zzb = Looper.getMainLooper();
    }
    return new GoogleApi.zza(zza, null, zzb, null);
  }
  
  public final zzd zza(Looper paramLooper)
  {
    zzbq.zza(paramLooper, "Looper must not be null.");
    zzb = paramLooper;
    return this;
  }
  
  public final zzd zza(zzda paramZzda)
  {
    zzbq.zza(paramZzda, "StatusExceptionMapper must not be null.");
    zza = paramZzda;
    return this;
  }
}

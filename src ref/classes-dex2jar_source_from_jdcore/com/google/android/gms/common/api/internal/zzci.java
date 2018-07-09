package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;

public final class zzci<L>
{
  private final zzcj zza;
  private volatile L zzb;
  private final zzck<L> zzc;
  
  zzci(@NonNull Looper paramLooper, @NonNull L paramL, @NonNull String paramString)
  {
    zza = new zzcj(this, paramLooper);
    zzb = zzbq.zza(paramL, "Listener must not be null");
    zzc = new zzck(paramL, zzbq.zza(paramString));
  }
  
  public final void zza(zzcl<? super L> paramZzcl)
  {
    zzbq.zza(paramZzcl, "Notifier must not be null");
    paramZzcl = zza.obtainMessage(1, paramZzcl);
    zza.sendMessage(paramZzcl);
  }
  
  public final boolean zza()
  {
    return zzb != null;
  }
  
  public final void zzb()
  {
    zzb = null;
  }
  
  final void zzb(zzcl<? super L> paramZzcl)
  {
    Object localObject = zzb;
    if (localObject == null)
    {
      paramZzcl.zza();
      return;
    }
    try
    {
      paramZzcl.zza(localObject);
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      paramZzcl.zza();
      throw localRuntimeException;
    }
  }
  
  @NonNull
  public final zzck<L> zzc()
  {
    return zzc;
  }
}

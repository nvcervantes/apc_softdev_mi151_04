package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.internal.zzcyk;

@Hide
public final class zzz<O extends Api.ApiOptions>
  extends GoogleApi<O>
{
  private final Api.zze zzb;
  private final zzt zzc;
  private final zzr zzd;
  private final Api.zza<? extends zzcyj, zzcyk> zze;
  
  public zzz(@NonNull Context paramContext, Api<O> paramApi, Looper paramLooper, @NonNull Api.zze paramZze, @NonNull zzt paramZzt, zzr paramZzr, Api.zza<? extends zzcyj, zzcyk> paramZza)
  {
    super(paramContext, paramApi, paramLooper);
    zzb = paramZze;
    zzc = paramZzt;
    zzd = paramZzr;
    zze = paramZza;
    zza.zza(this);
  }
  
  public final Api.zze zza(Looper paramLooper, zzbo<O> paramZzbo)
  {
    zzc.zza(paramZzbo);
    return zzb;
  }
  
  public final zzcv zza(Context paramContext, Handler paramHandler)
  {
    return new zzcv(paramContext, paramHandler, zzd, zze);
  }
  
  public final Api.zze zzh()
  {
    return zzb;
  }
}

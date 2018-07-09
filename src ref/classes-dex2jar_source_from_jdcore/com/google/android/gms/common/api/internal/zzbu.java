package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzan;
import com.google.android.gms.common.internal.zzj;
import java.util.Map;
import java.util.Set;

final class zzbu
  implements zzcy, zzj
{
  private final Api.zze zzb;
  private final zzh<?> zzc;
  private zzan zzd = null;
  private Set<Scope> zze = null;
  private boolean zzf = false;
  
  public zzbu(Api.zze paramZze, zzh<?> paramZzh)
  {
    zzb = paramZzh;
    Object localObject;
    zzc = localObject;
  }
  
  @WorkerThread
  private final void zza()
  {
    if ((zzf) && (zzd != null)) {
      zzb.zza(zzd, zze);
    }
  }
  
  public final void zza(@NonNull ConnectionResult paramConnectionResult)
  {
    zzbm.zza(zza).post(new zzbv(this, paramConnectionResult));
  }
  
  @WorkerThread
  public final void zza(zzan paramZzan, Set<Scope> paramSet)
  {
    if ((paramZzan != null) && (paramSet != null))
    {
      zzd = paramZzan;
      zze = paramSet;
      zza();
      return;
    }
    Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
    zzb(new ConnectionResult(4));
  }
  
  @WorkerThread
  public final void zzb(ConnectionResult paramConnectionResult)
  {
    ((zzbo)zzbm.zzj(zza).get(zzc)).zza(paramConnectionResult);
  }
}

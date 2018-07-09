package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzbt;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzcyg;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.internal.zzcyk;
import com.google.android.gms.internal.zzcyo;
import com.google.android.gms.internal.zzcyw;
import java.util.Set;

public final class zzcv
  extends zzcyo
  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
  private static Api.zza<? extends zzcyj, zzcyk> zza = zzcyg.zza;
  private final Context zzb;
  private final Handler zzc;
  private final Api.zza<? extends zzcyj, zzcyk> zzd;
  private Set<Scope> zze;
  private zzr zzf;
  private zzcyj zzg;
  private zzcy zzh;
  
  @WorkerThread
  public zzcv(Context paramContext, Handler paramHandler, @NonNull zzr paramZzr)
  {
    this(paramContext, paramHandler, paramZzr, zza);
  }
  
  @WorkerThread
  public zzcv(Context paramContext, Handler paramHandler, @NonNull zzr paramZzr, Api.zza<? extends zzcyj, zzcyk> paramZza)
  {
    zzb = paramContext;
    zzc = paramHandler;
    zzf = ((zzr)zzbq.zza(paramZzr, "ClientSettings must not be null"));
    zze = paramZzr.zze();
    zzd = paramZza;
  }
  
  @WorkerThread
  private final void zzb(zzcyw paramZzcyw)
  {
    Object localObject2 = paramZzcyw.zza();
    Object localObject1 = localObject2;
    if (((ConnectionResult)localObject2).isSuccess())
    {
      paramZzcyw = paramZzcyw.zzb();
      localObject1 = paramZzcyw.zzb();
      if (!((ConnectionResult)localObject1).isSuccess())
      {
        paramZzcyw = String.valueOf(localObject1);
        localObject2 = new StringBuilder(48 + String.valueOf(paramZzcyw).length());
        ((StringBuilder)localObject2).append("Sign-in succeeded with resolve account failure: ");
        ((StringBuilder)localObject2).append(paramZzcyw);
        Log.wtf("SignInCoordinator", ((StringBuilder)localObject2).toString(), new Exception());
      }
    }
    else
    {
      zzh.zzb((ConnectionResult)localObject1);
    }
    for (;;)
    {
      zzg.zzg();
      return;
      zzh.zza(paramZzcyw.zza(), zze);
    }
  }
  
  @WorkerThread
  public final void onConnected(@Nullable Bundle paramBundle)
  {
    zzg.zza(this);
  }
  
  @WorkerThread
  public final void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult)
  {
    zzh.zzb(paramConnectionResult);
  }
  
  @WorkerThread
  public final void onConnectionSuspended(int paramInt)
  {
    zzg.zzg();
  }
  
  public final zzcyj zza()
  {
    return zzg;
  }
  
  @WorkerThread
  public final void zza(zzcy paramZzcy)
  {
    if (zzg != null) {
      zzg.zzg();
    }
    zzf.zza(Integer.valueOf(System.identityHashCode(this)));
    zzg = ((zzcyj)zzd.zza(zzb, zzc.getLooper(), zzf, zzf.zzk(), this, this));
    zzh = paramZzcy;
    if ((zze != null) && (!zze.isEmpty()))
    {
      zzg.zzi();
      return;
    }
    zzc.post(new zzcw(this));
  }
  
  @BinderThread
  public final void zza(zzcyw paramZzcyw)
  {
    zzc.post(new zzcx(this, paramZzcyw));
  }
  
  public final void zzb()
  {
    if (zzg != null) {
      zzg.zzg();
    }
  }
}

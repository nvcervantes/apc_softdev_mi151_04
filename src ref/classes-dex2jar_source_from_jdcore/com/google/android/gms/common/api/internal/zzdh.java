package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzbq;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;

public final class zzdh<R extends Result>
  extends TransformedResult<R>
  implements ResultCallback<R>
{
  private ResultTransform<? super R, ? extends Result> zza = null;
  private zzdh<? extends Result> zzb = null;
  private volatile ResultCallbacks<? super R> zzc = null;
  private PendingResult<R> zzd = null;
  private final Object zze = new Object();
  private Status zzf = null;
  private final WeakReference<GoogleApiClient> zzg;
  private final zzdj zzh;
  private boolean zzi = false;
  
  public zzdh(WeakReference<GoogleApiClient> paramWeakReference)
  {
    zzbq.zza(paramWeakReference, "GoogleApiClient reference must not be null");
    zzg = paramWeakReference;
    paramWeakReference = (GoogleApiClient)zzg.get();
    if (paramWeakReference != null) {
      paramWeakReference = paramWeakReference.zzc();
    } else {
      paramWeakReference = Looper.getMainLooper();
    }
    zzh = new zzdj(this, paramWeakReference);
  }
  
  private static void zza(Result paramResult)
  {
    if ((paramResult instanceof Releasable)) {
      try
      {
        ((Releasable)paramResult).release();
        return;
      }
      catch (RuntimeException localRuntimeException)
      {
        paramResult = String.valueOf(paramResult);
        StringBuilder localStringBuilder = new StringBuilder(18 + String.valueOf(paramResult).length());
        localStringBuilder.append("Unable to release ");
        localStringBuilder.append(paramResult);
        Log.w("TransformedResultImpl", localStringBuilder.toString(), localRuntimeException);
      }
    }
  }
  
  private final void zza(Status paramStatus)
  {
    synchronized (zze)
    {
      zzf = paramStatus;
      zzb(zzf);
      return;
    }
  }
  
  private final void zzb()
  {
    if ((zza == null) && (zzc == null)) {
      return;
    }
    GoogleApiClient localGoogleApiClient = (GoogleApiClient)zzg.get();
    if ((!zzi) && (zza != null) && (localGoogleApiClient != null))
    {
      localGoogleApiClient.zza(this);
      zzi = true;
    }
    if (zzf != null)
    {
      zzb(zzf);
      return;
    }
    if (zzd != null) {
      zzd.setResultCallback(this);
    }
  }
  
  private final void zzb(Status paramStatus)
  {
    synchronized (zze)
    {
      if (zza != null)
      {
        paramStatus = zza.onFailure(paramStatus);
        zzbq.zza(paramStatus, "onFailure must not return null");
        zzb.zza(paramStatus);
      }
      else if (zzc())
      {
        zzc.onFailure(paramStatus);
      }
      return;
    }
  }
  
  private final boolean zzc()
  {
    GoogleApiClient localGoogleApiClient = (GoogleApiClient)zzg.get();
    return (zzc != null) && (localGoogleApiClient != null);
  }
  
  public final void andFinally(@NonNull ResultCallbacks<? super R> paramResultCallbacks)
  {
    for (;;)
    {
      synchronized (zze)
      {
        ResultCallbacks localResultCallbacks = zzc;
        boolean bool2 = false;
        if (localResultCallbacks == null)
        {
          bool1 = true;
          zzbq.zza(bool1, "Cannot call andFinally() twice.");
          bool1 = bool2;
          if (zza == null) {
            bool1 = true;
          }
          zzbq.zza(bool1, "Cannot call then() and andFinally() on the same TransformedResult.");
          zzc = paramResultCallbacks;
          zzb();
          return;
        }
      }
      boolean bool1 = false;
    }
  }
  
  public final void onResult(R paramR)
  {
    synchronized (zze)
    {
      if (paramR.getStatus().isSuccess())
      {
        if (zza != null) {
          zzcs.zza().submit(new zzdi(this, paramR));
        } else if (zzc()) {
          zzc.onSuccess(paramR);
        }
      }
      else
      {
        zza(paramR.getStatus());
        zza(paramR);
      }
      return;
    }
  }
  
  @NonNull
  public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> paramResultTransform)
  {
    for (;;)
    {
      synchronized (zze)
      {
        ResultTransform localResultTransform = zza;
        boolean bool2 = false;
        if (localResultTransform == null)
        {
          bool1 = true;
          zzbq.zza(bool1, "Cannot call then() twice.");
          bool1 = bool2;
          if (zzc == null) {
            bool1 = true;
          }
          zzbq.zza(bool1, "Cannot call then() and andFinally() on the same TransformedResult.");
          zza = paramResultTransform;
          paramResultTransform = new zzdh(zzg);
          zzb = paramResultTransform;
          zzb();
          return paramResultTransform;
        }
      }
      boolean bool1 = false;
    }
  }
  
  final void zza()
  {
    zzc = null;
  }
  
  public final void zza(PendingResult<?> paramPendingResult)
  {
    synchronized (zze)
    {
      zzd = paramPendingResult;
      zzb();
      return;
    }
  }
}

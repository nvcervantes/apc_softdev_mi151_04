package com.google.android.gms.location.places;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzchx;
import com.google.android.gms.location.places.internal.zzy;

@Hide
public class zzm
  extends zzy
{
  private static final String zza = "zzm";
  private final zzd zzb;
  private final zza zzc;
  private final zze zzd;
  private final zzf zze;
  private final zzc zzf;
  
  public zzm(zza paramZza)
  {
    zzb = null;
    zzc = paramZza;
    zzd = null;
    zze = null;
    zzf = null;
  }
  
  public zzm(zzc paramZzc)
  {
    zzb = null;
    zzc = null;
    zzd = null;
    zze = null;
    zzf = paramZzc;
  }
  
  public zzm(zzd paramZzd)
  {
    zzb = paramZzd;
    zzc = null;
    zzd = null;
    zze = null;
    zzf = null;
  }
  
  public zzm(zzf paramZzf)
  {
    zzb = null;
    zzc = null;
    zzd = null;
    zze = paramZzf;
    zzf = null;
  }
  
  public final void zza(Status paramStatus)
    throws RemoteException
  {
    zze.zza(paramStatus);
  }
  
  public final void zza(DataHolder paramDataHolder)
    throws RemoteException
  {
    boolean bool;
    if (zzb != null) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zza(bool, "placeEstimator cannot be null");
    if (paramDataHolder == null)
    {
      if (Log.isLoggable(zza, 6)) {
        Log.e(zza, "onPlaceEstimated received null DataHolder", new Throwable());
      }
      zzb.zzc(Status.zzc);
      return;
    }
    Bundle localBundle = paramDataHolder.zzc();
    int i;
    if (localBundle == null) {
      i = 100;
    } else {
      i = PlaceLikelihoodBuffer.zza(localBundle);
    }
    paramDataHolder = new PlaceLikelihoodBuffer(paramDataHolder, i);
    zzb.zza(paramDataHolder);
  }
  
  public final void zzb(DataHolder paramDataHolder)
    throws RemoteException
  {
    if (paramDataHolder == null)
    {
      if (Log.isLoggable(zza, 6)) {
        Log.e(zza, "onAutocompletePrediction received null DataHolder", new Throwable());
      }
      zzc.zzc(Status.zzc);
      return;
    }
    zzc.zza(new AutocompletePredictionBuffer(paramDataHolder));
  }
  
  public final void zzc(DataHolder paramDataHolder)
    throws RemoteException
  {
    if (paramDataHolder == null)
    {
      if (Log.isLoggable(zza, 6)) {
        Log.e(zza, "onPlaceUserDataFetched received null DataHolder", new Throwable());
      }
      paramDataHolder = Status.zzc;
      throw new NullPointerException();
    }
    new zzchx(paramDataHolder);
    throw new NullPointerException();
  }
  
  public final void zzd(DataHolder paramDataHolder)
    throws RemoteException
  {
    paramDataHolder = new PlaceBuffer(paramDataHolder);
    zzf.zza(paramDataHolder);
  }
  
  public static abstract class zza<A extends Api.zze>
    extends zzm.zzb<AutocompletePredictionBuffer, A>
  {
    public zza(Api paramApi, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
  }
  
  public static abstract class zzb<R extends Result, A extends Api.zze>
    extends com.google.android.gms.common.api.internal.zzm<R, A>
  {
    public zzb(Api paramApi, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
  }
  
  public static abstract class zzc<A extends Api.zze>
    extends zzm.zzb<PlaceBuffer, A>
  {
    public zzc(Api paramApi, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
  }
  
  public static abstract class zzd<A extends Api.zze>
    extends zzm.zzb<PlaceLikelihoodBuffer, A>
  {
    public zzd(Api paramApi, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
  }
  
  @Deprecated
  public static abstract class zze<A extends Api.zze>
    extends zzm.zzb<zzchx, A>
  {}
  
  public static abstract class zzf<A extends Api.zze>
    extends zzm.zzb<Status, A>
  {
    public zzf(Api paramApi, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
  }
}

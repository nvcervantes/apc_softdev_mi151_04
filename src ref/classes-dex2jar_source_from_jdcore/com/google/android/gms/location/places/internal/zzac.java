package com.google.android.gms.location.places.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.zzm;
import java.util.Locale;

@Hide
public final class zzac
  extends zzab<zzr>
{
  private final zzau zzd;
  private final Locale zze = Locale.getDefault();
  
  private zzac(Context paramContext, Looper paramLooper, com.google.android.gms.common.internal.zzr paramZzr, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, PlacesOptions paramPlacesOptions)
  {
    super(paramContext, paramLooper, 67, paramZzr, paramConnectionCallbacks, paramOnConnectionFailedListener);
    if (paramZzr.zzb() != null) {
      paramContext = zzbname;
    } else {
      paramContext = null;
    }
    zzd = new zzau(paramString, zze, paramContext, null, 0);
  }
  
  protected final String zza()
  {
    return "com.google.android.gms.location.places.PlaceDetectionApi";
  }
  
  public final void zza(zzm paramZzm, PlaceFilter paramPlaceFilter)
    throws RemoteException
  {
    zzbq.zza(paramZzm, "callback == null");
    PlaceFilter localPlaceFilter = paramPlaceFilter;
    if (paramPlaceFilter == null) {
      localPlaceFilter = PlaceFilter.zza();
    }
    ((zzr)zzaf()).zza(localPlaceFilter, zzd, paramZzm);
  }
  
  public final void zza(zzm paramZzm, PlaceReport paramPlaceReport)
    throws RemoteException
  {
    zzbq.zza(paramZzm, "callback == null");
    ((zzr)zzaf()).zza(paramPlaceReport, zzd, paramZzm);
  }
  
  protected final String zzb()
  {
    return "com.google.android.gms.location.places.internal.IGooglePlaceDetectionService";
  }
}

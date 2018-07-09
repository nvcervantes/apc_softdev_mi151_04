package com.google.android.gms.location.places.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompleteFilter.Builder;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.zzm;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;
import java.util.Locale;

@Hide
public final class zzo
  extends zzab<zzt>
{
  private final zzau zzd;
  
  private zzo(Context paramContext, Looper paramLooper, zzr paramZzr, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, PlacesOptions paramPlacesOptions)
  {
    super(paramContext, paramLooper, 65, paramZzr, paramConnectionCallbacks, paramOnConnectionFailedListener);
    paramLooper = Locale.getDefault();
    if (paramZzr.zzb() != null) {
      paramContext = zzbname;
    } else {
      paramContext = null;
    }
    zzd = new zzau(paramString, paramLooper, paramContext, null, 0);
  }
  
  protected final String zza()
  {
    return "com.google.android.gms.location.places.GeoDataApi";
  }
  
  public final void zza(com.google.android.gms.location.places.zzd paramZzd, String paramString)
    throws RemoteException
  {
    zzbq.zza(paramZzd, "callback cannot be null");
    ((zzt)zzaf()).zza(paramString, zzd, paramZzd);
  }
  
  public final void zza(com.google.android.gms.location.places.zzd paramZzd, String paramString, int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException
  {
    zzbq.zza(paramZzd, "callback cannot be null");
    ((zzt)zzaf()).zza(paramString, paramInt1, paramInt2, paramInt3, zzd, paramZzd);
  }
  
  public final void zza(zzm paramZzm, AddPlaceRequest paramAddPlaceRequest)
    throws RemoteException
  {
    zzbq.zza(paramZzm, "callback == null");
    ((zzt)zzaf()).zza(paramAddPlaceRequest, zzd, paramZzm);
  }
  
  public final void zza(zzm paramZzm, String paramString, @Nullable LatLngBounds paramLatLngBounds, int paramInt, @Nullable AutocompleteFilter paramAutocompleteFilter)
    throws RemoteException
  {
    zzbq.zza(paramZzm, "callback == null");
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    paramString = paramAutocompleteFilter;
    if (paramAutocompleteFilter == null) {
      paramString = new AutocompleteFilter.Builder().build();
    }
    ((zzt)zzaf()).zza(str, paramLatLngBounds, paramInt, paramString, zzd, paramZzm);
  }
  
  public final void zza(zzm paramZzm, List<String> paramList)
    throws RemoteException
  {
    zzbq.zza(paramZzm, "callback == null");
    ((zzt)zzaf()).zza(paramList, zzd, paramZzm);
  }
  
  protected final String zzb()
  {
    return "com.google.android.gms.location.places.internal.IGooglePlacesService";
  }
}

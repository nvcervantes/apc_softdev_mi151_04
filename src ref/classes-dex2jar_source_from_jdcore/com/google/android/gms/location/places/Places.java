package com.google.android.gms.location.places;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.location.places.internal.zzac;
import com.google.android.gms.location.places.internal.zzae;
import com.google.android.gms.location.places.internal.zzh;
import com.google.android.gms.location.places.internal.zzo;
import com.google.android.gms.location.places.internal.zzq;
import com.google.android.gms.location.places.internal.zzz;

public class Places
{
  public static final Api<PlacesOptions> GEO_DATA_API;
  public static final GeoDataApi GeoDataApi = new zzh();
  public static final Api<PlacesOptions> PLACE_DETECTION_API;
  public static final PlaceDetectionApi PlaceDetectionApi = new zzz();
  @Hide
  private static Api.zzf<zzo> zza = new Api.zzf();
  @Hide
  private static Api.zzf<zzac> zzb = new Api.zzf();
  
  static
  {
    GEO_DATA_API = new Api("Places.GEO_DATA_API", new zzq(), zza);
    PLACE_DETECTION_API = new Api("Places.PLACE_DETECTION_API", new zzae(), zzb);
  }
  
  private Places() {}
  
  public static GeoDataClient getGeoDataClient(@NonNull Activity paramActivity)
  {
    return getGeoDataClient(paramActivity, null);
  }
  
  @Deprecated
  public static GeoDataClient getGeoDataClient(@NonNull Activity paramActivity, @Nullable PlacesOptions paramPlacesOptions)
  {
    if (paramPlacesOptions == null) {
      paramPlacesOptions = new PlacesOptions.Builder().build();
    }
    return new GeoDataClient(paramActivity, paramPlacesOptions);
  }
  
  public static GeoDataClient getGeoDataClient(@NonNull Context paramContext)
  {
    return getGeoDataClient(paramContext, null);
  }
  
  @Deprecated
  public static GeoDataClient getGeoDataClient(@NonNull Context paramContext, @Nullable PlacesOptions paramPlacesOptions)
  {
    if (paramPlacesOptions == null) {
      paramPlacesOptions = new PlacesOptions.Builder().build();
    }
    return new GeoDataClient(paramContext, paramPlacesOptions);
  }
  
  public static PlaceDetectionClient getPlaceDetectionClient(@NonNull Activity paramActivity)
  {
    return getPlaceDetectionClient(paramActivity, null);
  }
  
  @Deprecated
  public static PlaceDetectionClient getPlaceDetectionClient(@NonNull Activity paramActivity, @Nullable PlacesOptions paramPlacesOptions)
  {
    if (paramPlacesOptions == null) {
      paramPlacesOptions = new PlacesOptions.Builder().build();
    }
    return new PlaceDetectionClient(paramActivity, paramPlacesOptions);
  }
  
  public static PlaceDetectionClient getPlaceDetectionClient(@NonNull Context paramContext)
  {
    return getPlaceDetectionClient(paramContext, null);
  }
  
  @Deprecated
  public static PlaceDetectionClient getPlaceDetectionClient(@NonNull Context paramContext, @Nullable PlacesOptions paramPlacesOptions)
  {
    if (paramPlacesOptions == null) {
      paramPlacesOptions = new PlacesOptions.Builder().build();
    }
    return new PlaceDetectionClient(paramContext, paramPlacesOptions);
  }
}

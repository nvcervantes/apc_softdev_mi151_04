package com.google.android.gms.location.places;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApi.zza;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.tasks.Task;

public class PlaceDetectionClient
  extends GoogleApi<PlacesOptions>
{
  PlaceDetectionClient(@NonNull Activity paramActivity, @NonNull PlacesOptions paramPlacesOptions)
  {
    super(paramActivity, Places.PLACE_DETECTION_API, paramPlacesOptions, GoogleApi.zza.zza);
  }
  
  private PlaceDetectionClient(@NonNull Context paramContext, @NonNull Api<PlacesOptions> paramApi, @NonNull PlacesOptions paramPlacesOptions)
  {
    super(paramContext, paramApi, paramPlacesOptions, GoogleApi.zza.zza);
  }
  
  PlaceDetectionClient(@NonNull Context paramContext, @NonNull PlacesOptions paramPlacesOptions)
  {
    this(paramContext, Places.PLACE_DETECTION_API, paramPlacesOptions);
  }
  
  @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
  public Task<PlaceLikelihoodBufferResponse> getCurrentPlace(@Nullable PlaceFilter paramPlaceFilter)
  {
    return zzbj.zza(Places.PlaceDetectionApi.getCurrentPlace(zze(), paramPlaceFilter), new PlaceLikelihoodBufferResponse());
  }
  
  public Task<Void> reportDeviceAtPlace(@NonNull PlaceReport paramPlaceReport)
  {
    return zzbj.zza(Places.PlaceDetectionApi.reportDeviceAtPlace(zze(), paramPlaceReport));
  }
}

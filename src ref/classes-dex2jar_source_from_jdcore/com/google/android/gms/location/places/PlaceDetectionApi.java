package com.google.android.gms.location.places;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public abstract interface PlaceDetectionApi
{
  @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
  public abstract PendingResult<PlaceLikelihoodBuffer> getCurrentPlace(@NonNull GoogleApiClient paramGoogleApiClient, @Nullable PlaceFilter paramPlaceFilter);
  
  public abstract PendingResult<Status> reportDeviceAtPlace(@NonNull GoogleApiClient paramGoogleApiClient, @NonNull PlaceReport paramPlaceReport);
}

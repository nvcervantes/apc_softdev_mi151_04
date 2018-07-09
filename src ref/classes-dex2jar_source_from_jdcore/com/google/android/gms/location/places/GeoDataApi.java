package com.google.android.gms.location.places;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.maps.model.LatLngBounds;

public abstract interface GeoDataApi
{
  @Deprecated
  public abstract PendingResult<PlaceBuffer> addPlace(@NonNull GoogleApiClient paramGoogleApiClient, @NonNull AddPlaceRequest paramAddPlaceRequest);
  
  public abstract PendingResult<AutocompletePredictionBuffer> getAutocompletePredictions(@NonNull GoogleApiClient paramGoogleApiClient, String paramString, LatLngBounds paramLatLngBounds, AutocompleteFilter paramAutocompleteFilter);
  
  public abstract PendingResult<PlaceBuffer> getPlaceById(@NonNull GoogleApiClient paramGoogleApiClient, @NonNull String... paramVarArgs);
  
  public abstract PendingResult<PlacePhotoMetadataResult> getPlacePhotos(@NonNull GoogleApiClient paramGoogleApiClient, @NonNull String paramString);
}

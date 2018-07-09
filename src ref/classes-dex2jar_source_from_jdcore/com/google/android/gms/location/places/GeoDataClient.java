package com.google.android.gms.location.places;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.location.places.internal.zzh;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.Task;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class GeoDataClient
  extends GoogleApi<PlacesOptions>
{
  GeoDataClient(@NonNull Activity paramActivity, @NonNull PlacesOptions paramPlacesOptions)
  {
    super(paramActivity, Places.GEO_DATA_API, paramPlacesOptions, new zzg());
  }
  
  GeoDataClient(@NonNull Context paramContext, @NonNull PlacesOptions paramPlacesOptions)
  {
    super(paramContext, Places.GEO_DATA_API, paramPlacesOptions, new zzg());
  }
  
  @Deprecated
  public Task<PlaceBufferResponse> addPlace(@NonNull AddPlaceRequest paramAddPlaceRequest)
  {
    return zzbj.zza(Places.GeoDataApi.addPlace(zze(), paramAddPlaceRequest), new PlaceBufferResponse());
  }
  
  public Task<AutocompletePredictionBufferResponse> getAutocompletePredictions(@Nullable String paramString, @Nullable LatLngBounds paramLatLngBounds, int paramInt, @Nullable AutocompleteFilter paramAutocompleteFilter)
  {
    return zzbj.zza(((zzh)Places.GeoDataApi).zza(zze(), paramString, paramLatLngBounds, paramInt, paramAutocompleteFilter), new AutocompletePredictionBufferResponse());
  }
  
  public Task<AutocompletePredictionBufferResponse> getAutocompletePredictions(@Nullable String paramString, @Nullable LatLngBounds paramLatLngBounds, @Nullable AutocompleteFilter paramAutocompleteFilter)
  {
    return getAutocompletePredictions(paramString, paramLatLngBounds, 1, paramAutocompleteFilter);
  }
  
  public Task<PlacePhotoResponse> getPhoto(@NonNull PlacePhotoMetadata paramPlacePhotoMetadata)
  {
    return getScaledPhoto(paramPlacePhotoMetadata, paramPlacePhotoMetadata.getMaxWidth(), paramPlacePhotoMetadata.getMaxHeight());
  }
  
  public Task<PlaceBufferResponse> getPlaceById(@NonNull String... paramVarArgs)
  {
    return zzbj.zza(Places.GeoDataApi.getPlaceById(zze(), paramVarArgs), new PlaceBufferResponse());
  }
  
  public Task<PlacePhotoMetadataResponse> getPlacePhotos(@NonNull String paramString)
  {
    return zzbj.zza(Places.GeoDataApi.getPlacePhotos(zze(), paramString), new PlacePhotoMetadataResponse());
  }
  
  public Task<PlacePhotoResponse> getScaledPhoto(@NonNull PlacePhotoMetadata paramPlacePhotoMetadata, @IntRange(from=1L) int paramInt1, @IntRange(from=1L) int paramInt2)
  {
    return zzbj.zza(((zzh)Places.GeoDataApi).zza(zze(), paramPlacePhotoMetadata, paramInt1, paramInt2), new PlacePhotoResponse());
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface BoundsMode
  {
    public static final int BIAS = 1;
    public static final int STRICT = 2;
  }
}

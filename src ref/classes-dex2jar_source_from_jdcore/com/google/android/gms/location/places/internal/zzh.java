package com.google.android.gms.location.places.internal;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLngBounds;

@Hide
public final class zzh
  implements GeoDataApi
{
  public zzh() {}
  
  public final PendingResult<PlaceBuffer> addPlace(@NonNull GoogleApiClient paramGoogleApiClient, @NonNull AddPlaceRequest paramAddPlaceRequest)
  {
    zzbq.zza(paramAddPlaceRequest, "userAddedPlace == null");
    return paramGoogleApiClient.zzb(new zzi(this, Places.GEO_DATA_API, paramGoogleApiClient, paramAddPlaceRequest));
  }
  
  public final PendingResult<AutocompletePredictionBuffer> getAutocompletePredictions(@NonNull GoogleApiClient paramGoogleApiClient, @Nullable String paramString, @Nullable LatLngBounds paramLatLngBounds, @Nullable AutocompleteFilter paramAutocompleteFilter)
  {
    return paramGoogleApiClient.zza(new zzn(this, Places.GEO_DATA_API, paramGoogleApiClient, paramString, paramLatLngBounds, paramAutocompleteFilter));
  }
  
  public final PendingResult<PlaceBuffer> getPlaceById(@NonNull GoogleApiClient paramGoogleApiClient, @NonNull String... paramVarArgs)
  {
    boolean bool;
    if (paramVarArgs != null) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zzb(bool, "placeIds == null");
    if (paramVarArgs.length > 0) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zzb(bool, "placeIds is empty");
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      String str = paramVarArgs[i];
      if (str != null) {
        bool = true;
      } else {
        bool = false;
      }
      zzbq.zzb(bool, "placeId == null");
      zzbq.zzb(str.isEmpty() ^ true, "placeId is empty");
      i += 1;
    }
    return paramGoogleApiClient.zza(new zzl(this, Places.GEO_DATA_API, paramGoogleApiClient, paramVarArgs));
  }
  
  public final PendingResult<PlacePhotoMetadataResult> getPlacePhotos(@NonNull GoogleApiClient paramGoogleApiClient, @NonNull String paramString)
  {
    zzbq.zza(paramString, "placeId == null");
    zzbq.zzb(paramString.isEmpty() ^ true, "placeId is empty");
    return paramGoogleApiClient.zza(new zzj(this, Places.GEO_DATA_API, paramGoogleApiClient, paramString));
  }
  
  public final PendingResult<PlacePhotoResult> zza(@NonNull GoogleApiClient paramGoogleApiClient, @NonNull PlacePhotoMetadata paramPlacePhotoMetadata, @IntRange(from=1L) int paramInt1, @IntRange(from=1L) int paramInt2)
  {
    zzbq.zza(paramPlacePhotoMetadata, "photo == null");
    boolean bool2 = false;
    if (paramInt1 > 0) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzbq.zzb(bool1, "width <= 0");
    boolean bool1 = bool2;
    if (paramInt2 > 0) {
      bool1 = true;
    }
    zzbq.zzb(bool1, "height <= 0");
    paramPlacePhotoMetadata = (zzar)paramPlacePhotoMetadata.freeze();
    String str = paramPlacePhotoMetadata.zza();
    int i = paramPlacePhotoMetadata.zzb();
    zzbq.zza(str, "fifeUrl == null");
    return paramGoogleApiClient.zza(new zzk(this, Places.GEO_DATA_API, paramGoogleApiClient, str, paramInt1, paramInt2, i));
  }
  
  public final PendingResult<AutocompletePredictionBuffer> zza(GoogleApiClient paramGoogleApiClient, @Nullable String paramString, @Nullable LatLngBounds paramLatLngBounds, int paramInt, @Nullable AutocompleteFilter paramAutocompleteFilter)
  {
    return paramGoogleApiClient.zza(new zzm(this, Places.GEO_DATA_API, paramGoogleApiClient, paramString, paramLatLngBounds, paramInt, paramAutocompleteFilter));
  }
}

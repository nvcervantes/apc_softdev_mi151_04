package com.google.android.gms.location.places.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.location.places.PlaceDetectionApi;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.Places;

@Hide
public final class zzz
  implements PlaceDetectionApi
{
  public zzz() {}
  
  public final PendingResult<PlaceLikelihoodBuffer> getCurrentPlace(@NonNull GoogleApiClient paramGoogleApiClient, @Nullable PlaceFilter paramPlaceFilter)
  {
    return paramGoogleApiClient.zza(new zzaa(this, Places.PLACE_DETECTION_API, paramGoogleApiClient, paramPlaceFilter));
  }
  
  public final PendingResult<Status> reportDeviceAtPlace(@NonNull GoogleApiClient paramGoogleApiClient, @NonNull PlaceReport paramPlaceReport)
  {
    zzbq.zza(paramPlaceReport, "report == null");
    return paramGoogleApiClient.zzb(new zzab(this, Places.PLACE_DETECTION_API, paramGoogleApiClient, paramPlaceReport));
  }
}

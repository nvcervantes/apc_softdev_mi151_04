package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.SettingsApi;

@Hide
public final class zzchs
  implements SettingsApi
{
  public zzchs() {}
  
  public final PendingResult<LocationSettingsResult> checkLocationSettings(GoogleApiClient paramGoogleApiClient, LocationSettingsRequest paramLocationSettingsRequest)
  {
    return paramGoogleApiClient.zza(new zzcht(this, paramGoogleApiClient, paramLocationSettingsRequest, null));
  }
}

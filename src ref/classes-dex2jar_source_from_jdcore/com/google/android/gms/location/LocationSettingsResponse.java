package com.google.android.gms.location;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.internal.Hide;

public class LocationSettingsResponse
  extends Response<LocationSettingsResult>
{
  @Hide
  public LocationSettingsResponse() {}
  
  public LocationSettingsStates getLocationSettingsStates()
  {
    return ((LocationSettingsResult)getResult()).getLocationSettingsStates();
  }
}

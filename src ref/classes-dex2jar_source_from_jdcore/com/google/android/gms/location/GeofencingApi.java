package com.google.android.gms.location;

import android.app.PendingIntent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.util.List;

@Deprecated
public abstract interface GeofencingApi
{
  @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
  public abstract PendingResult<Status> addGeofences(GoogleApiClient paramGoogleApiClient, GeofencingRequest paramGeofencingRequest, PendingIntent paramPendingIntent);
  
  @Deprecated
  @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
  public abstract PendingResult<Status> addGeofences(GoogleApiClient paramGoogleApiClient, List<Geofence> paramList, PendingIntent paramPendingIntent);
  
  public abstract PendingResult<Status> removeGeofences(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent);
  
  public abstract PendingResult<Status> removeGeofences(GoogleApiClient paramGoogleApiClient, List<String> paramList);
}

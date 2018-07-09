package com.google.android.gms.location;

import android.app.PendingIntent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Hide;

@Deprecated
public abstract interface ActivityRecognitionApi
{
  @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
  public abstract PendingResult<Status> removeActivityUpdates(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent);
  
  @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
  public abstract PendingResult<Status> requestActivityUpdates(GoogleApiClient paramGoogleApiClient, long paramLong, PendingIntent paramPendingIntent);
  
  @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
  @Hide
  public abstract PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent);
  
  @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
  @Hide
  public abstract PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, ActivityTransitionRequest paramActivityTransitionRequest, PendingIntent paramPendingIntent);
}

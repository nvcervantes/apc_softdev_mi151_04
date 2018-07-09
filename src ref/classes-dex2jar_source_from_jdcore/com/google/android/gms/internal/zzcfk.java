package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.location.ActivityRecognitionApi;
import com.google.android.gms.location.ActivityTransitionRequest;

@Hide
public final class zzcfk
  implements ActivityRecognitionApi
{
  public zzcfk() {}
  
  public final PendingResult<Status> removeActivityUpdates(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent)
  {
    return paramGoogleApiClient.zzb(new zzcfm(this, paramGoogleApiClient, paramPendingIntent));
  }
  
  public final PendingResult<Status> requestActivityUpdates(GoogleApiClient paramGoogleApiClient, long paramLong, PendingIntent paramPendingIntent)
  {
    return paramGoogleApiClient.zzb(new zzcfl(this, paramGoogleApiClient, paramLong, paramPendingIntent));
  }
  
  public final PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent)
  {
    return paramGoogleApiClient.zzb(new zzcfo(this, paramGoogleApiClient, paramPendingIntent));
  }
  
  public final PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, ActivityTransitionRequest paramActivityTransitionRequest, PendingIntent paramPendingIntent)
  {
    return paramGoogleApiClient.zzb(new zzcfn(this, paramGoogleApiClient, paramActivityTransitionRequest, paramPendingIntent));
  }
}

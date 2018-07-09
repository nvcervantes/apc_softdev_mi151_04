package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingApi;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.GeofencingRequest.Builder;
import com.google.android.gms.location.zzal;
import java.util.List;

@Hide
public final class zzcgn
  implements GeofencingApi
{
  public zzcgn() {}
  
  private final PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, zzal paramZzal)
  {
    return paramGoogleApiClient.zzb(new zzcgp(this, paramGoogleApiClient, paramZzal));
  }
  
  public final PendingResult<Status> addGeofences(GoogleApiClient paramGoogleApiClient, GeofencingRequest paramGeofencingRequest, PendingIntent paramPendingIntent)
  {
    return paramGoogleApiClient.zzb(new zzcgo(this, paramGoogleApiClient, paramGeofencingRequest, paramPendingIntent));
  }
  
  @Deprecated
  public final PendingResult<Status> addGeofences(GoogleApiClient paramGoogleApiClient, List<Geofence> paramList, PendingIntent paramPendingIntent)
  {
    GeofencingRequest.Builder localBuilder = new GeofencingRequest.Builder();
    localBuilder.addGeofences(paramList);
    localBuilder.setInitialTrigger(5);
    return addGeofences(paramGoogleApiClient, localBuilder.build(), paramPendingIntent);
  }
  
  public final PendingResult<Status> removeGeofences(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent)
  {
    return zza(paramGoogleApiClient, zzal.zza(paramPendingIntent));
  }
  
  public final PendingResult<Status> removeGeofences(GoogleApiClient paramGoogleApiClient, List<String> paramList)
  {
    return zza(paramGoogleApiClient, zzal.zza(paramList));
  }
}

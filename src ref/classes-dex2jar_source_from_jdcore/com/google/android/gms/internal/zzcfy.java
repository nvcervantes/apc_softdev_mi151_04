package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

@Hide
public final class zzcfy
  implements FusedLocationProviderApi
{
  public zzcfy() {}
  
  public final PendingResult<Status> flushLocations(GoogleApiClient paramGoogleApiClient)
  {
    return paramGoogleApiClient.zzb(new zzcgd(this, paramGoogleApiClient));
  }
  
  public final Location getLastLocation(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient = LocationServices.zza(paramGoogleApiClient);
    try
    {
      paramGoogleApiClient = paramGoogleApiClient.zzi();
      return paramGoogleApiClient;
    }
    catch (Exception paramGoogleApiClient)
    {
      for (;;) {}
    }
    return null;
  }
  
  public final LocationAvailability getLocationAvailability(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient = LocationServices.zza(paramGoogleApiClient);
    try
    {
      paramGoogleApiClient = paramGoogleApiClient.zzj();
      return paramGoogleApiClient;
    }
    catch (Exception paramGoogleApiClient)
    {
      for (;;) {}
    }
    return null;
  }
  
  public final PendingResult<Status> removeLocationUpdates(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent)
  {
    return paramGoogleApiClient.zzb(new zzcgi(this, paramGoogleApiClient, paramPendingIntent));
  }
  
  public final PendingResult<Status> removeLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationCallback paramLocationCallback)
  {
    return paramGoogleApiClient.zzb(new zzcga(this, paramGoogleApiClient, paramLocationCallback));
  }
  
  public final PendingResult<Status> removeLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationListener paramLocationListener)
  {
    return paramGoogleApiClient.zzb(new zzcgh(this, paramGoogleApiClient, paramLocationListener));
  }
  
  public final PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationRequest paramLocationRequest, PendingIntent paramPendingIntent)
  {
    return paramGoogleApiClient.zzb(new zzcgg(this, paramGoogleApiClient, paramLocationRequest, paramPendingIntent));
  }
  
  public final PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationRequest paramLocationRequest, LocationCallback paramLocationCallback, Looper paramLooper)
  {
    return paramGoogleApiClient.zzb(new zzcgf(this, paramGoogleApiClient, paramLocationRequest, paramLocationCallback, paramLooper));
  }
  
  public final PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationRequest paramLocationRequest, LocationListener paramLocationListener)
  {
    zzbq.zza(Looper.myLooper(), "Calling thread must be a prepared Looper thread.");
    return paramGoogleApiClient.zzb(new zzcfz(this, paramGoogleApiClient, paramLocationRequest, paramLocationListener));
  }
  
  public final PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationRequest paramLocationRequest, LocationListener paramLocationListener, Looper paramLooper)
  {
    return paramGoogleApiClient.zzb(new zzcge(this, paramGoogleApiClient, paramLocationRequest, paramLocationListener, paramLooper));
  }
  
  public final PendingResult<Status> setMockLocation(GoogleApiClient paramGoogleApiClient, Location paramLocation)
  {
    return paramGoogleApiClient.zzb(new zzcgc(this, paramGoogleApiClient, paramLocation));
  }
  
  public final PendingResult<Status> setMockMode(GoogleApiClient paramGoogleApiClient, boolean paramBoolean)
  {
    return paramGoogleApiClient.zzb(new zzcgb(this, paramGoogleApiClient, paramBoolean));
  }
}

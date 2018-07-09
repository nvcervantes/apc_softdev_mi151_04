package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.tasks.Task;
import java.util.List;

public class GeofencingClient
  extends GoogleApi<Api.ApiOptions.NoOptions>
{
  @Hide
  public GeofencingClient(@NonNull Activity paramActivity)
  {
    super(paramActivity, LocationServices.API, null, new zzg());
  }
  
  @Hide
  public GeofencingClient(@NonNull Context paramContext)
  {
    super(paramContext, LocationServices.API, null, new zzg());
  }
  
  @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
  public Task<Void> addGeofences(GeofencingRequest paramGeofencingRequest, PendingIntent paramPendingIntent)
  {
    return zzbj.zza(LocationServices.GeofencingApi.addGeofences(zze(), paramGeofencingRequest, paramPendingIntent));
  }
  
  public Task<Void> removeGeofences(PendingIntent paramPendingIntent)
  {
    return zzbj.zza(LocationServices.GeofencingApi.removeGeofences(zze(), paramPendingIntent));
  }
  
  public Task<Void> removeGeofences(List<String> paramList)
  {
    return zzbj.zza(LocationServices.GeofencingApi.removeGeofences(zze(), paramList));
  }
}

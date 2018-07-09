package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.common.api.internal.zzdf;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.internal.zzcgl;
import com.google.android.gms.internal.zzcgr;
import com.google.android.gms.internal.zzcgs;
import com.google.android.gms.internal.zzchl;
import com.google.android.gms.internal.zzchz;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public class FusedLocationProviderClient
  extends GoogleApi<Api.ApiOptions.NoOptions>
{
  public static final String KEY_VERTICAL_ACCURACY = "verticalAccuracy";
  
  @Hide
  public FusedLocationProviderClient(@NonNull Activity paramActivity)
  {
    super(paramActivity, LocationServices.API, null, new zzg());
  }
  
  @Hide
  public FusedLocationProviderClient(@NonNull Context paramContext)
  {
    super(paramContext, LocationServices.API, null, new zzg());
  }
  
  private final zzcgr zza(TaskCompletionSource<Boolean> paramTaskCompletionSource)
  {
    return new zzp(this, paramTaskCompletionSource);
  }
  
  public Task<Void> flushLocations()
  {
    return zzbj.zza(LocationServices.FusedLocationApi.flushLocations(zze()));
  }
  
  @RequiresPermission(anyOf={"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public Task<Location> getLastLocation()
  {
    return zza(new zzl(this));
  }
  
  @RequiresPermission(anyOf={"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public Task<LocationAvailability> getLocationAvailability()
  {
    return zza(new zzm(this));
  }
  
  public Task<Void> removeLocationUpdates(PendingIntent paramPendingIntent)
  {
    return zzbj.zza(LocationServices.FusedLocationApi.removeLocationUpdates(zze(), paramPendingIntent));
  }
  
  public Task<Void> removeLocationUpdates(LocationCallback paramLocationCallback)
  {
    return zzdf.zza(zza(zzcm.zza(paramLocationCallback, LocationCallback.class.getSimpleName())));
  }
  
  @RequiresPermission(anyOf={"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public Task<Void> requestLocationUpdates(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent)
  {
    return zzbj.zza(LocationServices.FusedLocationApi.requestLocationUpdates(zze(), paramLocationRequest, paramPendingIntent));
  }
  
  @RequiresPermission(anyOf={"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public Task<Void> requestLocationUpdates(LocationRequest paramLocationRequest, LocationCallback paramLocationCallback, @Nullable Looper paramLooper)
  {
    paramLocationRequest = zzchl.zza(paramLocationRequest);
    paramLocationCallback = zzcm.zzb(paramLocationCallback, zzchz.zza(paramLooper), LocationCallback.class.getSimpleName());
    return zza(new zzn(this, paramLocationCallback, paramLocationRequest, paramLocationCallback), new zzo(this, paramLocationCallback.zzc()));
  }
  
  @RequiresPermission(anyOf={"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public Task<Void> setMockLocation(Location paramLocation)
  {
    return zzbj.zza(LocationServices.FusedLocationApi.setMockLocation(zze(), paramLocation));
  }
  
  @RequiresPermission(anyOf={"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public Task<Void> setMockMode(boolean paramBoolean)
  {
    return zzbj.zza(LocationServices.FusedLocationApi.setMockMode(zze(), paramBoolean));
  }
  
  static final class zza
    extends zzcgs
  {
    private final TaskCompletionSource<Void> zza;
    
    public zza(TaskCompletionSource<Void> paramTaskCompletionSource)
    {
      zza = paramTaskCompletionSource;
    }
    
    public final void zza(zzcgl paramZzcgl)
    {
      zzdf.zza(paramZzcgl.getStatus(), null, zza);
    }
  }
}

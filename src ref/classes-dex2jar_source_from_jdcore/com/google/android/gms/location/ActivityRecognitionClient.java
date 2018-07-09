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

public class ActivityRecognitionClient
  extends GoogleApi<Api.ApiOptions.NoOptions>
{
  @Hide
  public ActivityRecognitionClient(@NonNull Activity paramActivity)
  {
    super(paramActivity, LocationServices.API, null, new zzg());
  }
  
  @Hide
  public ActivityRecognitionClient(@NonNull Context paramContext)
  {
    super(paramContext, LocationServices.API, null, new zzg());
  }
  
  @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
  public Task<Void> removeActivityTransitionUpdates(PendingIntent paramPendingIntent)
  {
    return zzbj.zza(ActivityRecognition.ActivityRecognitionApi.zza(zze(), paramPendingIntent));
  }
  
  @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
  public Task<Void> removeActivityUpdates(PendingIntent paramPendingIntent)
  {
    return zzbj.zza(ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(zze(), paramPendingIntent));
  }
  
  @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
  public Task<Void> requestActivityTransitionUpdates(ActivityTransitionRequest paramActivityTransitionRequest, PendingIntent paramPendingIntent)
  {
    return zzbj.zza(ActivityRecognition.ActivityRecognitionApi.zza(zze(), paramActivityTransitionRequest, paramPendingIntent));
  }
  
  @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
  public Task<Void> requestActivityUpdates(long paramLong, PendingIntent paramPendingIntent)
  {
    return zzbj.zza(ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(zze(), paramLong, paramPendingIntent));
  }
}

package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.location.LocationStatusCodes;

final class zzchj
  extends zzcgv
{
  private zzn<Status> zza;
  
  public zzchj(zzn<Status> paramZzn)
  {
    zza = paramZzn;
  }
  
  private final void zza(int paramInt)
  {
    if (zza == null)
    {
      Log.wtf("LocationClientImpl", "onRemoveGeofencesResult called multiple times");
      return;
    }
    Status localStatus = LocationStatusCodes.zzb(LocationStatusCodes.zza(paramInt));
    zza.zza(localStatus);
    zza = null;
  }
  
  public final void zza(int paramInt, PendingIntent paramPendingIntent)
  {
    zza(paramInt);
  }
  
  public final void zza(int paramInt, String[] paramArrayOfString)
  {
    Log.wtf("LocationClientImpl", "Unexpected call to onAddGeofencesResult");
  }
  
  public final void zzb(int paramInt, String[] paramArrayOfString)
  {
    zza(paramInt);
  }
}

package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.location.LocationStatusCodes;

final class zzchi
  extends zzcgv
{
  private zzn<Status> zza;
  
  public zzchi(zzn<Status> paramZzn)
  {
    zza = paramZzn;
  }
  
  public final void zza(int paramInt, PendingIntent paramPendingIntent)
  {
    Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByPendingIntentResult");
  }
  
  public final void zza(int paramInt, String[] paramArrayOfString)
  {
    if (zza == null)
    {
      Log.wtf("LocationClientImpl", "onAddGeofenceResult called multiple times");
      return;
    }
    paramArrayOfString = LocationStatusCodes.zzb(LocationStatusCodes.zza(paramInt));
    zza.zza(paramArrayOfString);
    zza = null;
  }
  
  public final void zzb(int paramInt, String[] paramArrayOfString)
  {
    Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByRequestIdsResult");
  }
}

package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.zzv;

final class zzchb
  extends zzv
{
  private final zzci<LocationCallback> zza;
  
  zzchb(zzci<LocationCallback> paramZzci)
  {
    zza = paramZzci;
  }
  
  public final void zza()
  {
    try
    {
      zza.zzb();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public final void zza(LocationAvailability paramLocationAvailability)
  {
    zza.zza(new zzchd(this, paramLocationAvailability));
  }
  
  public final void zza(LocationResult paramLocationResult)
  {
    zza.zza(new zzchc(this, paramLocationResult));
  }
}

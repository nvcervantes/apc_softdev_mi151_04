package com.google.android.gms.internal;

import android.location.Location;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.zzy;

final class zzchf
  extends zzy
{
  private final zzci<LocationListener> zza;
  
  zzchf(zzci<LocationListener> paramZzci)
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
  
  public final void zza(Location paramLocation)
  {
    try
    {
      zza.zza(new zzchg(this, paramLocation));
      return;
    }
    finally
    {
      paramLocation = finally;
      throw paramLocation;
    }
  }
}

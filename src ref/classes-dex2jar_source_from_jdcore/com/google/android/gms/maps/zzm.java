package com.google.android.gms.maps;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.maps.internal.zzah;
import com.google.android.gms.maps.model.RuntimeRemoteException;

final class zzm
  implements LocationSource.OnLocationChangedListener
{
  zzm(zzl paramZzl, zzah paramZzah) {}
  
  public final void onLocationChanged(Location paramLocation)
  {
    try
    {
      zza.zza(paramLocation);
      return;
    }
    catch (RemoteException paramLocation)
    {
      throw new RuntimeRemoteException(paramLocation);
    }
  }
}

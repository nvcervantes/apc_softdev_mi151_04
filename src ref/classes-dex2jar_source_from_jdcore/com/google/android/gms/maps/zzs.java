package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.zzbc;
import com.google.android.gms.maps.model.PointOfInterest;

final class zzs
  extends zzbc
{
  zzs(GoogleMap paramGoogleMap, GoogleMap.OnPoiClickListener paramOnPoiClickListener) {}
  
  public final void zza(PointOfInterest paramPointOfInterest)
    throws RemoteException
  {
    zza.onPoiClick(paramPointOfInterest);
  }
}

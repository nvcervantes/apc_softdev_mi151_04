package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzaq;

final class zzab
  extends zzaq
{
  zzab(MapFragment.zza paramZza, OnMapReadyCallback paramOnMapReadyCallback) {}
  
  public final void zza(IGoogleMapDelegate paramIGoogleMapDelegate)
    throws RemoteException
  {
    zza.onMapReady(new GoogleMap(paramIGoogleMapDelegate));
  }
}

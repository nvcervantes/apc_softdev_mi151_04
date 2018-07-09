package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

@Hide
public abstract interface zzbj
  extends IInterface
{
  public abstract void zza(StreetViewPanoramaLocation paramStreetViewPanoramaLocation)
    throws RemoteException;
}

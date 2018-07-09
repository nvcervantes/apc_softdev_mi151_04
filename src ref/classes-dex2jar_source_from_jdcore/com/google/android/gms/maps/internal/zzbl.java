package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

@Hide
public abstract interface zzbl
  extends IInterface
{
  public abstract void zza(StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation)
    throws RemoteException;
}

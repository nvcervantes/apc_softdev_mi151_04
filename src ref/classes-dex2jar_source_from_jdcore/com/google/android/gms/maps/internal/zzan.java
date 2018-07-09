package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.maps.model.LatLng;

@Hide
public abstract interface zzan
  extends IInterface
{
  public abstract void zza(LatLng paramLatLng)
    throws RemoteException;
}

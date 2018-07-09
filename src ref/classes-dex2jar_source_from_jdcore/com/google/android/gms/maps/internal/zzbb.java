package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.maps.model.PointOfInterest;

@Hide
public abstract interface zzbb
  extends IInterface
{
  public abstract void zza(PointOfInterest paramPointOfInterest)
    throws RemoteException;
}

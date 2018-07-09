package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;

@Hide
public abstract interface zzbf
  extends IInterface
{
  public abstract void zza(IPolylineDelegate paramIPolylineDelegate)
    throws RemoteException;
}

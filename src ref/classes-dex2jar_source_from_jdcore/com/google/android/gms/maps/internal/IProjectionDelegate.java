package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

@Hide
public abstract interface IProjectionDelegate
  extends IInterface
{
  public abstract LatLng fromScreenLocation(IObjectWrapper paramIObjectWrapper)
    throws RemoteException;
  
  public abstract VisibleRegion getVisibleRegion()
    throws RemoteException;
  
  public abstract IObjectWrapper toScreenLocation(LatLng paramLatLng)
    throws RemoteException;
}

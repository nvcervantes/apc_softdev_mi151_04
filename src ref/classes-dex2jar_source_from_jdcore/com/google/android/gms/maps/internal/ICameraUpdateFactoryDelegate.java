package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

@Hide
public abstract interface ICameraUpdateFactoryDelegate
  extends IInterface
{
  public abstract IObjectWrapper newCameraPosition(CameraPosition paramCameraPosition)
    throws RemoteException;
  
  public abstract IObjectWrapper newLatLng(LatLng paramLatLng)
    throws RemoteException;
  
  public abstract IObjectWrapper newLatLngBounds(LatLngBounds paramLatLngBounds, int paramInt)
    throws RemoteException;
  
  public abstract IObjectWrapper newLatLngBoundsWithSize(LatLngBounds paramLatLngBounds, int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException;
  
  public abstract IObjectWrapper newLatLngZoom(LatLng paramLatLng, float paramFloat)
    throws RemoteException;
  
  public abstract IObjectWrapper scrollBy(float paramFloat1, float paramFloat2)
    throws RemoteException;
  
  public abstract IObjectWrapper zoomBy(float paramFloat)
    throws RemoteException;
  
  public abstract IObjectWrapper zoomByWithFocus(float paramFloat, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract IObjectWrapper zoomIn()
    throws RemoteException;
  
  public abstract IObjectWrapper zoomOut()
    throws RemoteException;
  
  public abstract IObjectWrapper zoomTo(float paramFloat)
    throws RemoteException;
}

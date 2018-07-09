package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

@Hide
public abstract interface IStreetViewPanoramaDelegate
  extends IInterface
{
  public abstract void animateTo(StreetViewPanoramaCamera paramStreetViewPanoramaCamera, long paramLong)
    throws RemoteException;
  
  public abstract void enablePanning(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void enableStreetNames(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void enableUserNavigation(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void enableZoom(boolean paramBoolean)
    throws RemoteException;
  
  public abstract StreetViewPanoramaCamera getPanoramaCamera()
    throws RemoteException;
  
  public abstract StreetViewPanoramaLocation getStreetViewPanoramaLocation()
    throws RemoteException;
  
  public abstract boolean isPanningGesturesEnabled()
    throws RemoteException;
  
  public abstract boolean isStreetNamesEnabled()
    throws RemoteException;
  
  public abstract boolean isUserNavigationEnabled()
    throws RemoteException;
  
  public abstract boolean isZoomGesturesEnabled()
    throws RemoteException;
  
  public abstract IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation)
    throws RemoteException;
  
  public abstract StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper paramIObjectWrapper)
    throws RemoteException;
  
  public abstract void setOnStreetViewPanoramaCameraChangeListener(zzbh paramZzbh)
    throws RemoteException;
  
  public abstract void setOnStreetViewPanoramaChangeListener(zzbj paramZzbj)
    throws RemoteException;
  
  public abstract void setOnStreetViewPanoramaClickListener(zzbl paramZzbl)
    throws RemoteException;
  
  public abstract void setOnStreetViewPanoramaLongClickListener(zzbn paramZzbn)
    throws RemoteException;
  
  public abstract void setPosition(LatLng paramLatLng)
    throws RemoteException;
  
  public abstract void setPositionWithID(String paramString)
    throws RemoteException;
  
  public abstract void setPositionWithRadius(LatLng paramLatLng, int paramInt)
    throws RemoteException;
  
  public abstract void setPositionWithRadiusAndSource(LatLng paramLatLng, int paramInt, StreetViewSource paramStreetViewSource)
    throws RemoteException;
  
  public abstract void setPositionWithSource(LatLng paramLatLng, StreetViewSource paramStreetViewSource)
    throws RemoteException;
}

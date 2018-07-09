package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.maps.internal.IProjectionDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.VisibleRegion;

public final class Projection
{
  private final IProjectionDelegate zza;
  
  Projection(IProjectionDelegate paramIProjectionDelegate)
  {
    zza = paramIProjectionDelegate;
  }
  
  public final LatLng fromScreenLocation(Point paramPoint)
  {
    try
    {
      paramPoint = zza.fromScreenLocation(zzn.zza(paramPoint));
      return paramPoint;
    }
    catch (RemoteException paramPoint)
    {
      throw new RuntimeRemoteException(paramPoint);
    }
  }
  
  public final VisibleRegion getVisibleRegion()
  {
    try
    {
      VisibleRegion localVisibleRegion = zza.getVisibleRegion();
      return localVisibleRegion;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final Point toScreenLocation(LatLng paramLatLng)
  {
    try
    {
      paramLatLng = (Point)zzn.zza(zza.toScreenLocation(paramLatLng));
      return paramLatLng;
    }
    catch (RemoteException paramLatLng)
    {
      throw new RuntimeRemoteException(paramLatLng);
    }
  }
}

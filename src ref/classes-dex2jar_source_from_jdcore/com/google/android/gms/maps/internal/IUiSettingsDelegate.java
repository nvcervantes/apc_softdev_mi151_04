package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;

@Hide
public abstract interface IUiSettingsDelegate
  extends IInterface
{
  public abstract boolean isCompassEnabled()
    throws RemoteException;
  
  public abstract boolean isIndoorLevelPickerEnabled()
    throws RemoteException;
  
  public abstract boolean isMapToolbarEnabled()
    throws RemoteException;
  
  public abstract boolean isMyLocationButtonEnabled()
    throws RemoteException;
  
  public abstract boolean isRotateGesturesEnabled()
    throws RemoteException;
  
  public abstract boolean isScrollGesturesEnabled()
    throws RemoteException;
  
  public abstract boolean isTiltGesturesEnabled()
    throws RemoteException;
  
  public abstract boolean isZoomControlsEnabled()
    throws RemoteException;
  
  public abstract boolean isZoomGesturesEnabled()
    throws RemoteException;
  
  public abstract void setAllGesturesEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setCompassEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setIndoorLevelPickerEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setMapToolbarEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setMyLocationButtonEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setRotateGesturesEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setScrollGesturesEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setTiltGesturesEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setZoomControlsEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setZoomGesturesEnabled(boolean paramBoolean)
    throws RemoteException;
}

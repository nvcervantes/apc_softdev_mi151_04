package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.List;

@Hide
public abstract interface IPolylineDelegate
  extends IInterface
{
  public abstract boolean equalsRemote(IPolylineDelegate paramIPolylineDelegate)
    throws RemoteException;
  
  public abstract int getColor()
    throws RemoteException;
  
  public abstract Cap getEndCap()
    throws RemoteException;
  
  public abstract String getId()
    throws RemoteException;
  
  public abstract int getJointType()
    throws RemoteException;
  
  public abstract List<PatternItem> getPattern()
    throws RemoteException;
  
  public abstract List<LatLng> getPoints()
    throws RemoteException;
  
  public abstract Cap getStartCap()
    throws RemoteException;
  
  public abstract IObjectWrapper getTag()
    throws RemoteException;
  
  public abstract float getWidth()
    throws RemoteException;
  
  public abstract float getZIndex()
    throws RemoteException;
  
  public abstract int hashCodeRemote()
    throws RemoteException;
  
  public abstract boolean isClickable()
    throws RemoteException;
  
  public abstract boolean isGeodesic()
    throws RemoteException;
  
  public abstract boolean isVisible()
    throws RemoteException;
  
  public abstract void remove()
    throws RemoteException;
  
  public abstract void setClickable(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setColor(int paramInt)
    throws RemoteException;
  
  public abstract void setEndCap(Cap paramCap)
    throws RemoteException;
  
  public abstract void setGeodesic(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setJointType(int paramInt)
    throws RemoteException;
  
  public abstract void setPattern(List<PatternItem> paramList)
    throws RemoteException;
  
  public abstract void setPoints(List<LatLng> paramList)
    throws RemoteException;
  
  public abstract void setStartCap(Cap paramCap)
    throws RemoteException;
  
  public abstract void setTag(IObjectWrapper paramIObjectWrapper)
    throws RemoteException;
  
  public abstract void setVisible(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setWidth(float paramFloat)
    throws RemoteException;
  
  public abstract void setZIndex(float paramFloat)
    throws RemoteException;
  
  public static abstract class zza
    extends zzew
    implements IPolylineDelegate
  {
    public static IPolylineDelegate zza(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
      if ((localIInterface instanceof IPolylineDelegate)) {
        return (IPolylineDelegate)localIInterface;
      }
      return new zzv(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      throw new NoSuchMethodError();
    }
  }
}

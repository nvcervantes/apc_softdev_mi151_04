package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzb
  extends zzev
  implements ICameraUpdateFactoryDelegate
{
  zzb(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
  }
  
  public final IObjectWrapper newCameraPosition(CameraPosition paramCameraPosition)
    throws RemoteException
  {
    Object localObject = a_();
    zzex.zza((Parcel)localObject, paramCameraPosition);
    paramCameraPosition = zza(7, (Parcel)localObject);
    localObject = IObjectWrapper.zza.zza(paramCameraPosition.readStrongBinder());
    paramCameraPosition.recycle();
    return localObject;
  }
  
  public final IObjectWrapper newLatLng(LatLng paramLatLng)
    throws RemoteException
  {
    Object localObject = a_();
    zzex.zza((Parcel)localObject, paramLatLng);
    paramLatLng = zza(8, (Parcel)localObject);
    localObject = IObjectWrapper.zza.zza(paramLatLng.readStrongBinder());
    paramLatLng.recycle();
    return localObject;
  }
  
  public final IObjectWrapper newLatLngBounds(LatLngBounds paramLatLngBounds, int paramInt)
    throws RemoteException
  {
    Object localObject = a_();
    zzex.zza((Parcel)localObject, paramLatLngBounds);
    ((Parcel)localObject).writeInt(paramInt);
    paramLatLngBounds = zza(10, (Parcel)localObject);
    localObject = IObjectWrapper.zza.zza(paramLatLngBounds.readStrongBinder());
    paramLatLngBounds.recycle();
    return localObject;
  }
  
  public final IObjectWrapper newLatLngBoundsWithSize(LatLngBounds paramLatLngBounds, int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException
  {
    Object localObject = a_();
    zzex.zza((Parcel)localObject, paramLatLngBounds);
    ((Parcel)localObject).writeInt(paramInt1);
    ((Parcel)localObject).writeInt(paramInt2);
    ((Parcel)localObject).writeInt(paramInt3);
    paramLatLngBounds = zza(11, (Parcel)localObject);
    localObject = IObjectWrapper.zza.zza(paramLatLngBounds.readStrongBinder());
    paramLatLngBounds.recycle();
    return localObject;
  }
  
  public final IObjectWrapper newLatLngZoom(LatLng paramLatLng, float paramFloat)
    throws RemoteException
  {
    Object localObject = a_();
    zzex.zza((Parcel)localObject, paramLatLng);
    ((Parcel)localObject).writeFloat(paramFloat);
    paramLatLng = zza(9, (Parcel)localObject);
    localObject = IObjectWrapper.zza.zza(paramLatLng.readStrongBinder());
    paramLatLng.recycle();
    return localObject;
  }
  
  public final IObjectWrapper scrollBy(float paramFloat1, float paramFloat2)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat1);
    localParcel.writeFloat(paramFloat2);
    localParcel = zza(3, localParcel);
    IObjectWrapper localIObjectWrapper = IObjectWrapper.zza.zza(localParcel.readStrongBinder());
    localParcel.recycle();
    return localIObjectWrapper;
  }
  
  public final IObjectWrapper zoomBy(float paramFloat)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat);
    localParcel = zza(5, localParcel);
    IObjectWrapper localIObjectWrapper = IObjectWrapper.zza.zza(localParcel.readStrongBinder());
    localParcel.recycle();
    return localIObjectWrapper;
  }
  
  public final IObjectWrapper zoomByWithFocus(float paramFloat, int paramInt1, int paramInt2)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat);
    localParcel.writeInt(paramInt1);
    localParcel.writeInt(paramInt2);
    localParcel = zza(6, localParcel);
    IObjectWrapper localIObjectWrapper = IObjectWrapper.zza.zza(localParcel.readStrongBinder());
    localParcel.recycle();
    return localIObjectWrapper;
  }
  
  public final IObjectWrapper zoomIn()
    throws RemoteException
  {
    Parcel localParcel = zza(1, a_());
    IObjectWrapper localIObjectWrapper = IObjectWrapper.zza.zza(localParcel.readStrongBinder());
    localParcel.recycle();
    return localIObjectWrapper;
  }
  
  public final IObjectWrapper zoomOut()
    throws RemoteException
  {
    Parcel localParcel = zza(2, a_());
    IObjectWrapper localIObjectWrapper = IObjectWrapper.zza.zza(localParcel.readStrongBinder());
    localParcel.recycle();
    return localIObjectWrapper;
  }
  
  public final IObjectWrapper zoomTo(float paramFloat)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat);
    localParcel = zza(4, localParcel);
    IObjectWrapper localIObjectWrapper = IObjectWrapper.zza.zza(localParcel.readStrongBinder());
    localParcel.recycle();
    return localIObjectWrapper;
  }
}

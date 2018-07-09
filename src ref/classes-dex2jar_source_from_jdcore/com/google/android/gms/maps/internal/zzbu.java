package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

public final class zzbu
  extends zzev
  implements IStreetViewPanoramaDelegate
{
  zzbu(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
  }
  
  public final void animateTo(StreetViewPanoramaCamera paramStreetViewPanoramaCamera, long paramLong)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramStreetViewPanoramaCamera);
    localParcel.writeLong(paramLong);
    zzb(9, localParcel);
  }
  
  public final void enablePanning(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(2, localParcel);
  }
  
  public final void enableStreetNames(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(4, localParcel);
  }
  
  public final void enableUserNavigation(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(3, localParcel);
  }
  
  public final void enableZoom(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(1, localParcel);
  }
  
  public final StreetViewPanoramaCamera getPanoramaCamera()
    throws RemoteException
  {
    Parcel localParcel = zza(10, a_());
    StreetViewPanoramaCamera localStreetViewPanoramaCamera = (StreetViewPanoramaCamera)zzex.zza(localParcel, StreetViewPanoramaCamera.CREATOR);
    localParcel.recycle();
    return localStreetViewPanoramaCamera;
  }
  
  public final StreetViewPanoramaLocation getStreetViewPanoramaLocation()
    throws RemoteException
  {
    Parcel localParcel = zza(14, a_());
    StreetViewPanoramaLocation localStreetViewPanoramaLocation = (StreetViewPanoramaLocation)zzex.zza(localParcel, StreetViewPanoramaLocation.CREATOR);
    localParcel.recycle();
    return localStreetViewPanoramaLocation;
  }
  
  public final boolean isPanningGesturesEnabled()
    throws RemoteException
  {
    Parcel localParcel = zza(6, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final boolean isStreetNamesEnabled()
    throws RemoteException
  {
    Parcel localParcel = zza(8, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final boolean isUserNavigationEnabled()
    throws RemoteException
  {
    Parcel localParcel = zza(7, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final boolean isZoomGesturesEnabled()
    throws RemoteException
  {
    Parcel localParcel = zza(5, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation)
    throws RemoteException
  {
    Object localObject = a_();
    zzex.zza((Parcel)localObject, paramStreetViewPanoramaOrientation);
    paramStreetViewPanoramaOrientation = zza(19, (Parcel)localObject);
    localObject = IObjectWrapper.zza.zza(paramStreetViewPanoramaOrientation.readStrongBinder());
    paramStreetViewPanoramaOrientation.recycle();
    return localObject;
  }
  
  public final StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper paramIObjectWrapper)
    throws RemoteException
  {
    Object localObject = a_();
    zzex.zza((Parcel)localObject, paramIObjectWrapper);
    paramIObjectWrapper = zza(18, (Parcel)localObject);
    localObject = (StreetViewPanoramaOrientation)zzex.zza(paramIObjectWrapper, StreetViewPanoramaOrientation.CREATOR);
    paramIObjectWrapper.recycle();
    return localObject;
  }
  
  public final void setOnStreetViewPanoramaCameraChangeListener(zzbh paramZzbh)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzbh);
    zzb(16, localParcel);
  }
  
  public final void setOnStreetViewPanoramaChangeListener(zzbj paramZzbj)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzbj);
    zzb(15, localParcel);
  }
  
  public final void setOnStreetViewPanoramaClickListener(zzbl paramZzbl)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzbl);
    zzb(17, localParcel);
  }
  
  public final void setOnStreetViewPanoramaLongClickListener(zzbn paramZzbn)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzbn);
    zzb(20, localParcel);
  }
  
  public final void setPosition(LatLng paramLatLng)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramLatLng);
    zzb(12, localParcel);
  }
  
  public final void setPositionWithID(String paramString)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeString(paramString);
    zzb(11, localParcel);
  }
  
  public final void setPositionWithRadius(LatLng paramLatLng, int paramInt)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramLatLng);
    localParcel.writeInt(paramInt);
    zzb(13, localParcel);
  }
  
  public final void setPositionWithRadiusAndSource(LatLng paramLatLng, int paramInt, StreetViewSource paramStreetViewSource)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramLatLng);
    localParcel.writeInt(paramInt);
    zzex.zza(localParcel, paramStreetViewSource);
    zzb(22, localParcel);
  }
  
  public final void setPositionWithSource(LatLng paramLatLng, StreetViewSource paramStreetViewSource)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramLatLng);
    zzex.zza(localParcel, paramStreetViewSource);
    zzb(21, localParcel);
  }
}

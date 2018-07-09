package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

public final class zzbr
  extends zzev
  implements IProjectionDelegate
{
  zzbr(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.internal.IProjectionDelegate");
  }
  
  public final LatLng fromScreenLocation(IObjectWrapper paramIObjectWrapper)
    throws RemoteException
  {
    Object localObject = a_();
    zzex.zza((Parcel)localObject, paramIObjectWrapper);
    paramIObjectWrapper = zza(1, (Parcel)localObject);
    localObject = (LatLng)zzex.zza(paramIObjectWrapper, LatLng.CREATOR);
    paramIObjectWrapper.recycle();
    return localObject;
  }
  
  public final VisibleRegion getVisibleRegion()
    throws RemoteException
  {
    Parcel localParcel = zza(3, a_());
    VisibleRegion localVisibleRegion = (VisibleRegion)zzex.zza(localParcel, VisibleRegion.CREATOR);
    localParcel.recycle();
    return localVisibleRegion;
  }
  
  public final IObjectWrapper toScreenLocation(LatLng paramLatLng)
    throws RemoteException
  {
    Object localObject = a_();
    zzex.zza((Parcel)localObject, paramLatLng);
    paramLatLng = zza(2, (Parcel)localObject);
    localObject = IObjectWrapper.zza.zza(paramLatLng.readStrongBinder());
    paramLatLng.recycle();
    return localObject;
  }
}

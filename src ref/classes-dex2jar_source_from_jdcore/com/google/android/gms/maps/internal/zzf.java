package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.model.internal.zza;

public final class zzf
  extends zzev
  implements zze
{
  zzf(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.internal.ICreator");
  }
  
  public final ICameraUpdateFactoryDelegate zza()
    throws RemoteException
  {
    Parcel localParcel = zza(4, a_());
    Object localObject = localParcel.readStrongBinder();
    if (localObject == null)
    {
      localObject = null;
    }
    else
    {
      IInterface localIInterface = ((IBinder)localObject).queryLocalInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
      if ((localIInterface instanceof ICameraUpdateFactoryDelegate)) {
        localObject = (ICameraUpdateFactoryDelegate)localIInterface;
      } else {
        localObject = new zzb((IBinder)localObject);
      }
    }
    localParcel.recycle();
    return localObject;
  }
  
  public final IMapFragmentDelegate zza(IObjectWrapper paramIObjectWrapper)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramIObjectWrapper);
    localParcel = zza(2, localParcel);
    paramIObjectWrapper = localParcel.readStrongBinder();
    if (paramIObjectWrapper == null)
    {
      paramIObjectWrapper = null;
    }
    else
    {
      IInterface localIInterface = paramIObjectWrapper.queryLocalInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
      if ((localIInterface instanceof IMapFragmentDelegate)) {
        paramIObjectWrapper = (IMapFragmentDelegate)localIInterface;
      } else {
        paramIObjectWrapper = new zzj(paramIObjectWrapper);
      }
    }
    localParcel.recycle();
    return paramIObjectWrapper;
  }
  
  public final IMapViewDelegate zza(IObjectWrapper paramIObjectWrapper, GoogleMapOptions paramGoogleMapOptions)
    throws RemoteException
  {
    Object localObject = a_();
    zzex.zza((Parcel)localObject, paramIObjectWrapper);
    zzex.zza((Parcel)localObject, paramGoogleMapOptions);
    paramGoogleMapOptions = zza(3, (Parcel)localObject);
    paramIObjectWrapper = paramGoogleMapOptions.readStrongBinder();
    if (paramIObjectWrapper == null)
    {
      paramIObjectWrapper = null;
    }
    else
    {
      localObject = paramIObjectWrapper.queryLocalInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
      if ((localObject instanceof IMapViewDelegate)) {
        paramIObjectWrapper = (IMapViewDelegate)localObject;
      } else {
        paramIObjectWrapper = new zzk(paramIObjectWrapper);
      }
    }
    paramGoogleMapOptions.recycle();
    return paramIObjectWrapper;
  }
  
  public final IStreetViewPanoramaViewDelegate zza(IObjectWrapper paramIObjectWrapper, StreetViewPanoramaOptions paramStreetViewPanoramaOptions)
    throws RemoteException
  {
    Object localObject = a_();
    zzex.zza((Parcel)localObject, paramIObjectWrapper);
    zzex.zza((Parcel)localObject, paramStreetViewPanoramaOptions);
    paramStreetViewPanoramaOptions = zza(7, (Parcel)localObject);
    paramIObjectWrapper = paramStreetViewPanoramaOptions.readStrongBinder();
    if (paramIObjectWrapper == null)
    {
      paramIObjectWrapper = null;
    }
    else
    {
      localObject = paramIObjectWrapper.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
      if ((localObject instanceof IStreetViewPanoramaViewDelegate)) {
        paramIObjectWrapper = (IStreetViewPanoramaViewDelegate)localObject;
      } else {
        paramIObjectWrapper = new zzbw(paramIObjectWrapper);
      }
    }
    paramStreetViewPanoramaOptions.recycle();
    return paramIObjectWrapper;
  }
  
  public final void zza(IObjectWrapper paramIObjectWrapper, int paramInt)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramIObjectWrapper);
    localParcel.writeInt(paramInt);
    zzb(6, localParcel);
  }
  
  public final IStreetViewPanoramaFragmentDelegate zzb(IObjectWrapper paramIObjectWrapper)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramIObjectWrapper);
    localParcel = zza(8, localParcel);
    paramIObjectWrapper = localParcel.readStrongBinder();
    if (paramIObjectWrapper == null)
    {
      paramIObjectWrapper = null;
    }
    else
    {
      IInterface localIInterface = paramIObjectWrapper.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
      if ((localIInterface instanceof IStreetViewPanoramaFragmentDelegate)) {
        paramIObjectWrapper = (IStreetViewPanoramaFragmentDelegate)localIInterface;
      } else {
        paramIObjectWrapper = new zzbv(paramIObjectWrapper);
      }
    }
    localParcel.recycle();
    return paramIObjectWrapper;
  }
  
  public final zza zzb()
    throws RemoteException
  {
    Parcel localParcel = zza(5, a_());
    zza localZza = com.google.android.gms.maps.model.internal.zzb.zza(localParcel.readStrongBinder());
    localParcel.recycle();
    return localZza;
  }
}

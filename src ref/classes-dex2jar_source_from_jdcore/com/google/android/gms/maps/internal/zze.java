package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.model.internal.zza;

@Hide
public abstract interface zze
  extends IInterface
{
  public abstract ICameraUpdateFactoryDelegate zza()
    throws RemoteException;
  
  public abstract IMapFragmentDelegate zza(IObjectWrapper paramIObjectWrapper)
    throws RemoteException;
  
  public abstract IMapViewDelegate zza(IObjectWrapper paramIObjectWrapper, GoogleMapOptions paramGoogleMapOptions)
    throws RemoteException;
  
  public abstract IStreetViewPanoramaViewDelegate zza(IObjectWrapper paramIObjectWrapper, StreetViewPanoramaOptions paramStreetViewPanoramaOptions)
    throws RemoteException;
  
  public abstract void zza(IObjectWrapper paramIObjectWrapper, int paramInt)
    throws RemoteException;
  
  public abstract IStreetViewPanoramaFragmentDelegate zzb(IObjectWrapper paramIObjectWrapper)
    throws RemoteException;
  
  public abstract zza zzb()
    throws RemoteException;
}

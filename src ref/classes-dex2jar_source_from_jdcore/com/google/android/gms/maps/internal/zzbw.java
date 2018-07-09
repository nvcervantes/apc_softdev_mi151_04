package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzbw
  extends zzev
  implements IStreetViewPanoramaViewDelegate
{
  zzbw(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
  }
  
  public final IStreetViewPanoramaDelegate getStreetViewPanorama()
    throws RemoteException
  {
    Parcel localParcel = zza(1, a_());
    Object localObject = localParcel.readStrongBinder();
    if (localObject == null)
    {
      localObject = null;
    }
    else
    {
      IInterface localIInterface = ((IBinder)localObject).queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
      if ((localIInterface instanceof IStreetViewPanoramaDelegate)) {
        localObject = (IStreetViewPanoramaDelegate)localIInterface;
      } else {
        localObject = new zzbu((IBinder)localObject);
      }
    }
    localParcel.recycle();
    return localObject;
  }
  
  public final void getStreetViewPanoramaAsync(zzbp paramZzbp)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzbp);
    zzb(9, localParcel);
  }
  
  public final IObjectWrapper getView()
    throws RemoteException
  {
    Parcel localParcel = zza(8, a_());
    IObjectWrapper localIObjectWrapper = IObjectWrapper.zza.zza(localParcel.readStrongBinder());
    localParcel.recycle();
    return localIObjectWrapper;
  }
  
  public final void onCreate(Bundle paramBundle)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBundle);
    zzb(2, localParcel);
  }
  
  public final void onDestroy()
    throws RemoteException
  {
    zzb(5, a_());
  }
  
  public final void onLowMemory()
    throws RemoteException
  {
    zzb(6, a_());
  }
  
  public final void onPause()
    throws RemoteException
  {
    zzb(4, a_());
  }
  
  public final void onResume()
    throws RemoteException
  {
    zzb(3, a_());
  }
  
  public final void onSaveInstanceState(Bundle paramBundle)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBundle);
    localParcel = zza(7, localParcel);
    if (localParcel.readInt() != 0) {
      paramBundle.readFromParcel(localParcel);
    }
    localParcel.recycle();
  }
}

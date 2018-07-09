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

public final class zzk
  extends zzev
  implements IMapViewDelegate
{
  zzk(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.internal.IMapViewDelegate");
  }
  
  public final IGoogleMapDelegate getMap()
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
      IInterface localIInterface = ((IBinder)localObject).queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
      if ((localIInterface instanceof IGoogleMapDelegate)) {
        localObject = (IGoogleMapDelegate)localIInterface;
      } else {
        localObject = new zzg((IBinder)localObject);
      }
    }
    localParcel.recycle();
    return localObject;
  }
  
  public final void getMapAsync(zzap paramZzap)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzap);
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
  
  public final void onEnterAmbient(Bundle paramBundle)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBundle);
    zzb(10, localParcel);
  }
  
  public final void onExitAmbient()
    throws RemoteException
  {
    zzb(11, a_());
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
  
  public final void onStart()
    throws RemoteException
  {
    zzb(12, a_());
  }
  
  public final void onStop()
    throws RemoteException
  {
    zzb(13, a_());
  }
}

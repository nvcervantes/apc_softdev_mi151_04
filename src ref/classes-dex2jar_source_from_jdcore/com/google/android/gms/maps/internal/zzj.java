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
import com.google.android.gms.maps.GoogleMapOptions;

public final class zzj
  extends zzev
  implements IMapFragmentDelegate
{
  zzj(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.internal.IMapFragmentDelegate");
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
    zzb(12, localParcel);
  }
  
  public final boolean isReady()
    throws RemoteException
  {
    Parcel localParcel = zza(11, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final void onCreate(Bundle paramBundle)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBundle);
    zzb(3, localParcel);
  }
  
  public final IObjectWrapper onCreateView(IObjectWrapper paramIObjectWrapper1, IObjectWrapper paramIObjectWrapper2, Bundle paramBundle)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramIObjectWrapper1);
    zzex.zza(localParcel, paramIObjectWrapper2);
    zzex.zza(localParcel, paramBundle);
    paramIObjectWrapper1 = zza(4, localParcel);
    paramIObjectWrapper2 = IObjectWrapper.zza.zza(paramIObjectWrapper1.readStrongBinder());
    paramIObjectWrapper1.recycle();
    return paramIObjectWrapper2;
  }
  
  public final void onDestroy()
    throws RemoteException
  {
    zzb(8, a_());
  }
  
  public final void onDestroyView()
    throws RemoteException
  {
    zzb(7, a_());
  }
  
  public final void onEnterAmbient(Bundle paramBundle)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBundle);
    zzb(13, localParcel);
  }
  
  public final void onExitAmbient()
    throws RemoteException
  {
    zzb(14, a_());
  }
  
  public final void onInflate(IObjectWrapper paramIObjectWrapper, GoogleMapOptions paramGoogleMapOptions, Bundle paramBundle)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramIObjectWrapper);
    zzex.zza(localParcel, paramGoogleMapOptions);
    zzex.zza(localParcel, paramBundle);
    zzb(2, localParcel);
  }
  
  public final void onLowMemory()
    throws RemoteException
  {
    zzb(9, a_());
  }
  
  public final void onPause()
    throws RemoteException
  {
    zzb(6, a_());
  }
  
  public final void onResume()
    throws RemoteException
  {
    zzb(5, a_());
  }
  
  public final void onSaveInstanceState(Bundle paramBundle)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBundle);
    localParcel = zza(10, localParcel);
    if (localParcel.readInt() != 0) {
      paramBundle.readFromParcel(localParcel);
    }
    localParcel.recycle();
  }
  
  public final void onStart()
    throws RemoteException
  {
    zzb(15, a_());
  }
  
  public final void onStop()
    throws RemoteException
  {
    zzb(16, a_());
  }
}

package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzev;

public final class zzav
  extends zzev
  implements zzat
{
  zzav(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.common.internal.ICertData");
  }
  
  public final IObjectWrapper zzb()
    throws RemoteException
  {
    Parcel localParcel = zza(1, a_());
    IObjectWrapper localIObjectWrapper = IObjectWrapper.zza.zza(localParcel.readStrongBinder());
    localParcel.recycle();
    return localIObjectWrapper;
  }
  
  public final int zzc()
    throws RemoteException
  {
    Parcel localParcel = zza(2, a_());
    int i = localParcel.readInt();
    localParcel.recycle();
    return i;
  }
}

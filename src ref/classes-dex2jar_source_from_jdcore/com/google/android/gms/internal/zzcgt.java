package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzcgt
  extends zzev
  implements zzcgr
{
  zzcgt(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.location.internal.IFusedLocationProviderCallback");
  }
  
  public final void zza(zzcgl paramZzcgl)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzcgl);
    zzc(1, localParcel);
  }
}

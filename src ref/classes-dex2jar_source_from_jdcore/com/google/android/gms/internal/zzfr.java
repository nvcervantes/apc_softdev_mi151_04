package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzfr
  extends zzev
  implements zzfp
{
  zzfr(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
  }
  
  public final String zza()
    throws RemoteException
  {
    Parcel localParcel = zza(1, a_());
    String str = localParcel.readString();
    localParcel.recycle();
    return str;
  }
  
  public final boolean zza(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, true);
    localParcel = zza(2, localParcel);
    paramBoolean = zzex.zza(localParcel);
    localParcel.recycle();
    return paramBoolean;
  }
  
  public final boolean zzb()
    throws RemoteException
  {
    Parcel localParcel = zza(6, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
}

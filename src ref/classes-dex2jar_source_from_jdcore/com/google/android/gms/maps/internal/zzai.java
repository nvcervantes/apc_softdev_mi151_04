package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzai
  extends zzev
  implements zzah
{
  zzai(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.internal.IOnLocationChangeListener");
  }
  
  public final void zza(Location paramLocation)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramLocation);
    zzb(2, localParcel);
  }
}

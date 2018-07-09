package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzz
  extends zzev
  implements zzx
{
  zzz(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.location.ILocationListener");
  }
  
  public final void zza(Location paramLocation)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramLocation);
    zzc(1, localParcel);
  }
}

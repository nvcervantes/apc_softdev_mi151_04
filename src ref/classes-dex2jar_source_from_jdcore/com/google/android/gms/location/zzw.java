package com.google.android.gms.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzw
  extends zzev
  implements zzu
{
  zzw(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.location.ILocationCallback");
  }
  
  public final void zza(LocationAvailability paramLocationAvailability)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramLocationAvailability);
    zzc(2, localParcel);
  }
  
  public final void zza(LocationResult paramLocationResult)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramLocationResult);
    zzc(1, localParcel);
  }
}

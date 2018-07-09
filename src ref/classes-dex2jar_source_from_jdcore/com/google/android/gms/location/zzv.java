package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzv
  extends zzew
  implements zzu
{
  public zzv()
  {
    attachInterface(this, "com.google.android.gms.location.ILocationCallback");
  }
  
  public static zzu zza(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
    if ((localIInterface instanceof zzu)) {
      return (zzu)localIInterface;
    }
    return new zzw(paramIBinder);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2)) {
      return true;
    }
    switch (paramInt1)
    {
    default: 
      return false;
    case 2: 
      zza((LocationAvailability)zzex.zza(paramParcel1, LocationAvailability.CREATOR));
      return true;
    }
    zza((LocationResult)zzex.zza(paramParcel1, LocationResult.CREATOR));
    return true;
  }
}

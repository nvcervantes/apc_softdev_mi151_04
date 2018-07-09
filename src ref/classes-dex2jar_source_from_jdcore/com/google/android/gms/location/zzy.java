package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzy
  extends zzew
  implements zzx
{
  public zzy()
  {
    attachInterface(this, "com.google.android.gms.location.ILocationListener");
  }
  
  public static zzx zza(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.ILocationListener");
    if ((localIInterface instanceof zzx)) {
      return (zzx)localIInterface;
    }
    return new zzz(paramIBinder);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2)) {
      return true;
    }
    if (paramInt1 == 1)
    {
      zza((Location)zzex.zza(paramParcel1, Location.CREATOR));
      return true;
    }
    return false;
  }
}

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;

public abstract class zzu
  extends zzew
  implements zzt
{
  public zzu()
  {
    attachInterface(this, "com.google.android.gms.maps.internal.IOnCameraMoveStartedListener");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2)) {
      return true;
    }
    if (paramInt1 == 1)
    {
      zza(paramParcel1.readInt());
      paramParcel2.writeNoException();
      return true;
    }
    return false;
  }
}

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzaw
  extends zzew
  implements zzav
{
  public zzaw()
  {
    attachInterface(this, "com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2)) {
      return true;
    }
    if (paramInt1 == 1)
    {
      boolean bool = zza();
      paramParcel2.writeNoException();
      zzex.zza(paramParcel2, bool);
      return true;
    }
    return false;
  }
}

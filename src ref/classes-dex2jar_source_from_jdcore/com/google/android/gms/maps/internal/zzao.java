package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.maps.model.LatLng;

public abstract class zzao
  extends zzew
  implements zzan
{
  public zzao()
  {
    attachInterface(this, "com.google.android.gms.maps.internal.IOnMapLongClickListener");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2)) {
      return true;
    }
    if (paramInt1 == 1)
    {
      zza((LatLng)zzex.zza(paramParcel1, LatLng.CREATOR));
      paramParcel2.writeNoException();
      return true;
    }
    return false;
  }
}

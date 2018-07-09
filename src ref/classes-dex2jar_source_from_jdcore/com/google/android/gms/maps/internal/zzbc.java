package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.maps.model.PointOfInterest;

public abstract class zzbc
  extends zzew
  implements zzbb
{
  public zzbc()
  {
    attachInterface(this, "com.google.android.gms.maps.internal.IOnPoiClickListener");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2)) {
      return true;
    }
    if (paramInt1 == 1)
    {
      zza((PointOfInterest)zzex.zza(paramParcel1, PointOfInterest.CREATOR));
      paramParcel2.writeNoException();
      return true;
    }
    return false;
  }
}

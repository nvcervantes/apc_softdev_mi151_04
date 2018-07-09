package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;

public abstract class zzd
  extends zzew
  implements zzc
{
  public zzd()
  {
    attachInterface(this, "com.google.android.gms.maps.internal.ICancelableCallback");
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
      zzb();
      break;
    case 1: 
      zza();
    }
    paramParcel2.writeNoException();
    return true;
  }
}

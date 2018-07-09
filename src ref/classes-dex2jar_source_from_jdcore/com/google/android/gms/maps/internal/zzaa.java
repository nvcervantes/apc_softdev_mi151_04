package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.maps.model.internal.zzk;

public abstract class zzaa
  extends zzew
  implements zzz
{
  public zzaa()
  {
    attachInterface(this, "com.google.android.gms.maps.internal.IOnIndoorStateChangeListener");
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
      zza(zzk.zza(paramParcel1.readStrongBinder()));
      break;
    case 1: 
      zza();
    }
    paramParcel2.writeNoException();
    return true;
  }
}

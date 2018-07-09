package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzax
  extends zzew
  implements zzaw
{
  public zzax()
  {
    attachInterface(this, "com.google.android.gms.common.internal.IGmsCallbacks");
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
      zza(paramParcel1.readInt(), (Bundle)zzex.zza(paramParcel1, Bundle.CREATOR));
      break;
    case 1: 
      zza(paramParcel1.readInt(), paramParcel1.readStrongBinder(), (Bundle)zzex.zza(paramParcel1, Bundle.CREATOR));
    }
    paramParcel2.writeNoException();
    return true;
  }
}

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.maps.model.internal.zzq;

public abstract class zzi
  extends zzew
  implements zzh
{
  public zzi()
  {
    attachInterface(this, "com.google.android.gms.maps.internal.IInfoWindowAdapter");
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
      paramParcel1 = zzb(zzq.zza(paramParcel1.readStrongBinder()));
      break;
    case 1: 
      paramParcel1 = zza(zzq.zza(paramParcel1.readStrongBinder()));
    }
    paramParcel2.writeNoException();
    zzex.zza(paramParcel2, paramParcel1);
    return true;
  }
}

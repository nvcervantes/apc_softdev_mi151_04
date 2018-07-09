package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.maps.model.internal.zzq;

public abstract class zzas
  extends zzew
  implements zzar
{
  public zzas()
  {
    attachInterface(this, "com.google.android.gms.maps.internal.IOnMarkerClickListener");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2)) {
      return true;
    }
    if (paramInt1 == 1)
    {
      boolean bool = zza(zzq.zza(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      zzex.zza(paramParcel2, bool);
      return true;
    }
    return false;
  }
}

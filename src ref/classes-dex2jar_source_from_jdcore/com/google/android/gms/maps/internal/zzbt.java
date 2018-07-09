package com.google.android.gms.maps.internal;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzbt
  extends zzew
  implements zzbs
{
  public zzbt()
  {
    attachInterface(this, "com.google.android.gms.maps.internal.ISnapshotReadyCallback");
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
      zza(IObjectWrapper.zza.zza(paramParcel1.readStrongBinder()));
      break;
    case 1: 
      zza((Bitmap)zzex.zza(paramParcel1, Bitmap.CREATOR));
    }
    paramParcel2.writeNoException();
    return true;
  }
}

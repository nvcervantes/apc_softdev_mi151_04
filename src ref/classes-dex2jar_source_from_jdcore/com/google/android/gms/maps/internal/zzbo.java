package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

public abstract class zzbo
  extends zzew
  implements zzbn
{
  public zzbo()
  {
    attachInterface(this, "com.google.android.gms.maps.internal.IOnStreetViewPanoramaLongClickListener");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2)) {
      return true;
    }
    if (paramInt1 == 1)
    {
      zza((StreetViewPanoramaOrientation)zzex.zza(paramParcel1, StreetViewPanoramaOrientation.CREATOR));
      paramParcel2.writeNoException();
      return true;
    }
    return false;
  }
}

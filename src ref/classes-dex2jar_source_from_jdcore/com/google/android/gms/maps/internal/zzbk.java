package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

public abstract class zzbk
  extends zzew
  implements zzbj
{
  public zzbk()
  {
    attachInterface(this, "com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2)) {
      return true;
    }
    if (paramInt1 == 1)
    {
      zza((StreetViewPanoramaLocation)zzex.zza(paramParcel1, StreetViewPanoramaLocation.CREATOR));
      paramParcel2.writeNoException();
      return true;
    }
    return false;
  }
}

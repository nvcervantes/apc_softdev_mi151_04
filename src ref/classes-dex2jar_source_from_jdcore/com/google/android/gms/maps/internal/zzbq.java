package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;

public abstract class zzbq
  extends zzew
  implements zzbp
{
  public zzbq()
  {
    attachInterface(this, "com.google.android.gms.maps.internal.IOnStreetViewPanoramaReadyCallback");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2)) {
      return true;
    }
    if (paramInt1 == 1)
    {
      paramParcel1 = paramParcel1.readStrongBinder();
      if (paramParcel1 == null)
      {
        paramParcel1 = null;
      }
      else
      {
        IInterface localIInterface = paramParcel1.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        if ((localIInterface instanceof IStreetViewPanoramaDelegate)) {
          paramParcel1 = (IStreetViewPanoramaDelegate)localIInterface;
        } else {
          paramParcel1 = new zzbu(paramParcel1);
        }
      }
      zza(paramParcel1);
      paramParcel2.writeNoException();
      return true;
    }
    return false;
  }
}

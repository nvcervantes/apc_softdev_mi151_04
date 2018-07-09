package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;

public abstract class zzw
  extends zzew
  implements zzv
{
  public zzw()
  {
    attachInterface(this, "com.google.android.gms.location.places.internal.IPhotosCallbacks");
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
    case 3: 
      zza((PlacePhotoMetadataResult)zzex.zza(paramParcel1, PlacePhotoMetadataResult.CREATOR));
      return true;
    }
    zza((PlacePhotoResult)zzex.zza(paramParcel1, PlacePhotoResult.CREATOR));
    return true;
  }
}

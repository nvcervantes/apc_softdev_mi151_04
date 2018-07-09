package com.google.android.gms.location.places.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;

@Hide
public abstract interface zzv
  extends IInterface
{
  public abstract void zza(PlacePhotoMetadataResult paramPlacePhotoMetadataResult)
    throws RemoteException;
  
  public abstract void zza(PlacePhotoResult paramPlacePhotoResult)
    throws RemoteException;
}

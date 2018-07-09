package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.maps.model.CameraPosition;

@Hide
public abstract interface zzl
  extends IInterface
{
  public abstract void zza(CameraPosition paramCameraPosition)
    throws RemoteException;
}

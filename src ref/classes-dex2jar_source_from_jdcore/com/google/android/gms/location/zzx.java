package com.google.android.gms.location;

import android.location.Location;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;

@Hide
public abstract interface zzx
  extends IInterface
{
  public abstract void zza(Location paramLocation)
    throws RemoteException;
}

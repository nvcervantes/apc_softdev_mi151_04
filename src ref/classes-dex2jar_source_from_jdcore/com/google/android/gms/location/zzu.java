package com.google.android.gms.location;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;

@Hide
public abstract interface zzu
  extends IInterface
{
  public abstract void zza(LocationAvailability paramLocationAvailability)
    throws RemoteException;
  
  public abstract void zza(LocationResult paramLocationResult)
    throws RemoteException;
}

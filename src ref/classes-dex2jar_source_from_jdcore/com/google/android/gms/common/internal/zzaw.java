package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public abstract interface zzaw
  extends IInterface
{
  public abstract void zza(int paramInt, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void zza(int paramInt, IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;
}

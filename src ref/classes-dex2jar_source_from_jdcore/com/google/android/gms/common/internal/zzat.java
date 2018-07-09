package com.google.android.gms.common.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

@Hide
public abstract interface zzat
  extends IInterface
{
  public abstract IObjectWrapper zzb()
    throws RemoteException;
  
  public abstract int zzc()
    throws RemoteException;
}

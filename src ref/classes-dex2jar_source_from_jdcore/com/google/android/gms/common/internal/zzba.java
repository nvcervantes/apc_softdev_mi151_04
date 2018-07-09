package com.google.android.gms.common.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.zzn;
import com.google.android.gms.dynamic.IObjectWrapper;

@Hide
public abstract interface zzba
  extends IInterface
{
  public abstract boolean zza(zzn paramZzn, IObjectWrapper paramIObjectWrapper)
    throws RemoteException;
}

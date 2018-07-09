package com.google.android.gms.common.api.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Hide;

@Hide
public abstract interface zzca
  extends IInterface
{
  public abstract void zza(Status paramStatus)
    throws RemoteException;
}

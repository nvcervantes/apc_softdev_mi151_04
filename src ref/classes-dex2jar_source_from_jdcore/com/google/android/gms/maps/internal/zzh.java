package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.internal.zzp;

@Hide
public abstract interface zzh
  extends IInterface
{
  public abstract IObjectWrapper zza(zzp paramZzp)
    throws RemoteException;
  
  public abstract IObjectWrapper zzb(zzp paramZzp)
    throws RemoteException;
}

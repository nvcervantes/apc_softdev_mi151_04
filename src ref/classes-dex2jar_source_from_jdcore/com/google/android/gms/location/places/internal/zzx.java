package com.google.android.gms.location.places.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Hide;

@Hide
public abstract interface zzx
  extends IInterface
{
  public abstract void zza(Status paramStatus)
    throws RemoteException;
  
  public abstract void zza(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void zzb(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void zzc(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void zzd(DataHolder paramDataHolder)
    throws RemoteException;
}

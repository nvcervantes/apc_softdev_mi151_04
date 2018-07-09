package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.clearcut.zzc;
import com.google.android.gms.clearcut.zze;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public abstract interface zzbfp
  extends IInterface
{
  public abstract void zza(Status paramStatus)
    throws RemoteException;
  
  public abstract void zza(Status paramStatus, long paramLong)
    throws RemoteException;
  
  public abstract void zza(Status paramStatus, zzc paramZzc)
    throws RemoteException;
  
  public abstract void zza(Status paramStatus, zze[] paramArrayOfZze)
    throws RemoteException;
  
  public abstract void zza(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void zzb(Status paramStatus)
    throws RemoteException;
  
  public abstract void zzb(Status paramStatus, long paramLong)
    throws RemoteException;
  
  public abstract void zzb(Status paramStatus, zzc paramZzc)
    throws RemoteException;
  
  public abstract void zzc(Status paramStatus)
    throws RemoteException;
}

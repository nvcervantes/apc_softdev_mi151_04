package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import java.util.List;

@Hide
public abstract interface zzj
  extends IInterface
{
  public abstract int zza()
    throws RemoteException;
  
  public abstract boolean zza(zzj paramZzj)
    throws RemoteException;
  
  public abstract int zzb()
    throws RemoteException;
  
  public abstract List<IBinder> zzc()
    throws RemoteException;
  
  public abstract boolean zzd()
    throws RemoteException;
  
  public abstract int zze()
    throws RemoteException;
}

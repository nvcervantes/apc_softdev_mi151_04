package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;

@Hide
public abstract interface zzcgu
  extends IInterface
{
  public abstract void zza(int paramInt, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zza(int paramInt, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void zzb(int paramInt, String[] paramArrayOfString)
    throws RemoteException;
}

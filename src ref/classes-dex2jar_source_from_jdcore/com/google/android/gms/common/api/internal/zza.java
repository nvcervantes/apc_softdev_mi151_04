package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zzs;

public abstract class zza
{
  private int zza;
  
  public zza(int paramInt)
  {
    zza = paramInt;
  }
  
  private static Status zzb(RemoteException paramRemoteException)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((zzs.zza()) && ((paramRemoteException instanceof TransactionTooLargeException))) {
      localStringBuilder.append("TransactionTooLargeException: ");
    }
    localStringBuilder.append(paramRemoteException.getLocalizedMessage());
    return new Status(8, localStringBuilder.toString());
  }
  
  public abstract void zza(@NonNull Status paramStatus);
  
  public abstract void zza(@NonNull zzae paramZzae, boolean paramBoolean);
  
  public abstract void zza(zzbo<?> paramZzbo)
    throws DeadObjectException;
}

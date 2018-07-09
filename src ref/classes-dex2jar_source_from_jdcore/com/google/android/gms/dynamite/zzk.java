package com.google.android.gms.dynamite;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.dynamic.IObjectWrapper;

@Hide
public abstract interface zzk
  extends IInterface
{
  public abstract int zza(IObjectWrapper paramIObjectWrapper, String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract IObjectWrapper zza(IObjectWrapper paramIObjectWrapper, String paramString, int paramInt)
    throws RemoteException;
}

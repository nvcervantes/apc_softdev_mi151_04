package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.dynamic.IObjectWrapper;

@Hide
public abstract interface zzccm
  extends IInterface
{
  public abstract boolean getBooleanFlagValue(String paramString, boolean paramBoolean, int paramInt)
    throws RemoteException;
  
  public abstract int getIntFlagValue(String paramString, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract long getLongFlagValue(String paramString, long paramLong, int paramInt)
    throws RemoteException;
  
  public abstract String getStringFlagValue(String paramString1, String paramString2, int paramInt)
    throws RemoteException;
  
  public abstract void init(IObjectWrapper paramIObjectWrapper)
    throws RemoteException;
}

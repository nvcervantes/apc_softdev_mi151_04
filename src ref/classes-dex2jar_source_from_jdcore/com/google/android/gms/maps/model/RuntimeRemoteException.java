package com.google.android.gms.maps.model;

import android.os.RemoteException;

public final class RuntimeRemoteException
  extends RuntimeException
{
  public RuntimeRemoteException(RemoteException paramRemoteException)
  {
    super(paramRemoteException);
  }
}

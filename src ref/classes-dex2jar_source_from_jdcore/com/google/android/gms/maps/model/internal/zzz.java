package com.google.android.gms.maps.model.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.maps.model.Tile;

@Hide
public abstract interface zzz
  extends IInterface
{
  public abstract Tile zza(int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException;
}

package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.maps.model.Tile;

public final class zzab
  extends zzev
  implements zzz
{
  zzab(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
  }
  
  public final Tile zza(int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeInt(paramInt1);
    localParcel.writeInt(paramInt2);
    localParcel.writeInt(paramInt3);
    localParcel = zza(1, localParcel);
    Tile localTile = (Tile)zzex.zza(localParcel, Tile.CREATOR);
    localParcel.recycle();
    return localTile;
  }
}

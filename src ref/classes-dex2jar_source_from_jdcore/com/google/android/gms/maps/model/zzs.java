package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.maps.model.internal.zzz;

final class zzs
  implements TileProvider
{
  private final zzz zza = TileOverlayOptions.zza(zzb);
  
  zzs(TileOverlayOptions paramTileOverlayOptions) {}
  
  public final Tile getTile(int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      Tile localTile = zza.zza(paramInt1, paramInt2, paramInt3);
      return localTile;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;) {}
    }
    return null;
  }
}

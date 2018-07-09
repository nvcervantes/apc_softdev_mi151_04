package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.maps.internal.zzbt;

final class zzr
  extends zzbt
{
  zzr(GoogleMap paramGoogleMap, GoogleMap.SnapshotReadyCallback paramSnapshotReadyCallback) {}
  
  public final void zza(Bitmap paramBitmap)
    throws RemoteException
  {
    zza.onSnapshotReady(paramBitmap);
  }
  
  public final void zza(IObjectWrapper paramIObjectWrapper)
    throws RemoteException
  {
    zza.onSnapshotReady((Bitmap)zzn.zza(paramIObjectWrapper));
  }
}

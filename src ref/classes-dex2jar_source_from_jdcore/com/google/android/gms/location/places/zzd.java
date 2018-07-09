package com.google.android.gms.location.places;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.location.places.internal.zzw;

@Hide
public final class zzd
  extends zzw
{
  private final zzf zza;
  private final zze zzb;
  
  public zzd(zze paramZze)
  {
    zza = null;
    zzb = paramZze;
  }
  
  public zzd(zzf paramZzf)
  {
    zza = paramZzf;
    zzb = null;
  }
  
  public final void zza(PlacePhotoMetadataResult paramPlacePhotoMetadataResult)
    throws RemoteException
  {
    zza.zza(paramPlacePhotoMetadataResult);
  }
  
  public final void zza(PlacePhotoResult paramPlacePhotoResult)
    throws RemoteException
  {
    zzb.zza(paramPlacePhotoResult);
  }
}

package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzm;
import com.google.android.gms.maps.model.CameraPosition;

final class zzt
  extends zzm
{
  zzt(GoogleMap paramGoogleMap, GoogleMap.OnCameraChangeListener paramOnCameraChangeListener) {}
  
  public final void zza(CameraPosition paramCameraPosition)
  {
    zza.onCameraChange(paramCameraPosition);
  }
}

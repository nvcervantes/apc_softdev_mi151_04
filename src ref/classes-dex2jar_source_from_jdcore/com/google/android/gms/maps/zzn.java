package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzy;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.internal.zzg;

final class zzn
  extends zzy
{
  zzn(GoogleMap paramGoogleMap, GoogleMap.OnGroundOverlayClickListener paramOnGroundOverlayClickListener) {}
  
  public final void zza(zzg paramZzg)
  {
    zza.onGroundOverlayClick(new GroundOverlay(paramZzg));
  }
}

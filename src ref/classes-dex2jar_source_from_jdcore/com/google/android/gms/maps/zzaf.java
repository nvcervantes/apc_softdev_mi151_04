package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzbm;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

final class zzaf
  extends zzbm
{
  zzaf(StreetViewPanorama paramStreetViewPanorama, StreetViewPanorama.OnStreetViewPanoramaClickListener paramOnStreetViewPanoramaClickListener) {}
  
  public final void zza(StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation)
  {
    zza.onStreetViewPanoramaClick(paramStreetViewPanoramaOrientation);
  }
}

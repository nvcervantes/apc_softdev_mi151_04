package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzas;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.zzp;

final class zzb
  extends zzas
{
  zzb(GoogleMap paramGoogleMap, GoogleMap.OnMarkerClickListener paramOnMarkerClickListener) {}
  
  public final boolean zza(zzp paramZzp)
  {
    return zza.onMarkerClick(new Marker(paramZzp));
  }
}

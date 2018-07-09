package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzac;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.zzp;

final class zzd
  extends zzac
{
  zzd(GoogleMap paramGoogleMap, GoogleMap.OnInfoWindowClickListener paramOnInfoWindowClickListener) {}
  
  public final void zza(zzp paramZzp)
  {
    zza.onInfoWindowClick(new Marker(paramZzp));
  }
}

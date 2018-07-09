package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzae;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.zzp;

final class zzf
  extends zzae
{
  zzf(GoogleMap paramGoogleMap, GoogleMap.OnInfoWindowCloseListener paramOnInfoWindowCloseListener) {}
  
  public final void zza(zzp paramZzp)
  {
    zza.onInfoWindowClose(new Marker(paramZzp));
  }
}

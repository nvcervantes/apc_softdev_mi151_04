package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzag;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.zzp;

final class zze
  extends zzag
{
  zze(GoogleMap paramGoogleMap, GoogleMap.OnInfoWindowLongClickListener paramOnInfoWindowLongClickListener) {}
  
  public final void zza(zzp paramZzp)
  {
    zza.onInfoWindowLongClick(new Marker(paramZzp));
  }
}

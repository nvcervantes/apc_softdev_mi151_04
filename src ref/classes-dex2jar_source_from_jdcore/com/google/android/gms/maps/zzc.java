package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzau;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.zzp;

final class zzc
  extends zzau
{
  zzc(GoogleMap paramGoogleMap, GoogleMap.OnMarkerDragListener paramOnMarkerDragListener) {}
  
  public final void zza(zzp paramZzp)
  {
    zza.onMarkerDragStart(new Marker(paramZzp));
  }
  
  public final void zzb(zzp paramZzp)
  {
    zza.onMarkerDragEnd(new Marker(paramZzp));
  }
  
  public final void zzc(zzp paramZzp)
  {
    zza.onMarkerDrag(new Marker(paramZzp));
  }
}

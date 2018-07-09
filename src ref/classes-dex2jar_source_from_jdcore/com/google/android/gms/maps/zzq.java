package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzbg;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;

final class zzq
  extends zzbg
{
  zzq(GoogleMap paramGoogleMap, GoogleMap.OnPolylineClickListener paramOnPolylineClickListener) {}
  
  public final void zza(IPolylineDelegate paramIPolylineDelegate)
  {
    zza.onPolylineClick(new Polyline(paramIPolylineDelegate));
  }
}

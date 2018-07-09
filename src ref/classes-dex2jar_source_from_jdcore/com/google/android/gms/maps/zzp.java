package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzbe;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.internal.zzs;

final class zzp
  extends zzbe
{
  zzp(GoogleMap paramGoogleMap, GoogleMap.OnPolygonClickListener paramOnPolygonClickListener) {}
  
  public final void zza(zzs paramZzs)
  {
    zza.onPolygonClick(new Polygon(paramZzs));
  }
}

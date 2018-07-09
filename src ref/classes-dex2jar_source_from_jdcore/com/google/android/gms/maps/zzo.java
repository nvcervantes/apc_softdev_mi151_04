package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzw;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.internal.zzd;

final class zzo
  extends zzw
{
  zzo(GoogleMap paramGoogleMap, GoogleMap.OnCircleClickListener paramOnCircleClickListener) {}
  
  public final void zza(zzd paramZzd)
  {
    zza.onCircleClick(new Circle(paramZzd));
  }
}

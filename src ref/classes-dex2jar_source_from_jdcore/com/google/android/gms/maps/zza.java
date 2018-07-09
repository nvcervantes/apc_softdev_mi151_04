package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzaa;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.internal.zzj;

final class zza
  extends zzaa
{
  zza(GoogleMap paramGoogleMap, GoogleMap.OnIndoorStateChangeListener paramOnIndoorStateChangeListener) {}
  
  public final void zza()
  {
    zza.onIndoorBuildingFocused();
  }
  
  public final void zza(zzj paramZzj)
  {
    zza.onIndoorLevelActivated(new IndoorBuilding(paramZzj));
  }
}

package com.google.android.gms.maps;

import android.location.Location;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.maps.internal.zzay;

final class zzh
  extends zzay
{
  zzh(GoogleMap paramGoogleMap, GoogleMap.OnMyLocationChangeListener paramOnMyLocationChangeListener) {}
  
  public final void zza(IObjectWrapper paramIObjectWrapper)
  {
    zza.onMyLocationChange((Location)zzn.zza(paramIObjectWrapper));
  }
}

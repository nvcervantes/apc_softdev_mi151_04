package com.google.android.gms.maps;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.maps.internal.zzi;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.zzp;

final class zzg
  extends zzi
{
  zzg(GoogleMap paramGoogleMap, GoogleMap.InfoWindowAdapter paramInfoWindowAdapter) {}
  
  public final IObjectWrapper zza(zzp paramZzp)
  {
    return zzn.zza(zza.getInfoWindow(new Marker(paramZzp)));
  }
  
  public final IObjectWrapper zzb(zzp paramZzp)
  {
    return zzn.zza(zza.getInfoContents(new Marker(paramZzp)));
  }
}

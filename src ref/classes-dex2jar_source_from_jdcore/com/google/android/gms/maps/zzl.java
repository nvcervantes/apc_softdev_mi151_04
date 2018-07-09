package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.ILocationSourceDelegate.zza;
import com.google.android.gms.maps.internal.zzah;

final class zzl
  extends ILocationSourceDelegate.zza
{
  zzl(GoogleMap paramGoogleMap, LocationSource paramLocationSource) {}
  
  public final void activate(zzah paramZzah)
  {
    zza.activate(new zzm(this, paramZzah));
  }
  
  public final void deactivate()
  {
    zza.deactivate();
  }
}

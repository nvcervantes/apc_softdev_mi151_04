package com.google.android.gms.location.places;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.zzb;

public class PlaceBufferResponse
  extends zzb<Place, PlaceBuffer>
{
  PlaceBufferResponse() {}
  
  @Nullable
  public CharSequence getAttributions()
  {
    return ((PlaceBuffer)getResult()).getAttributions();
  }
}

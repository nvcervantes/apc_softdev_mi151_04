package com.google.android.gms.location.places;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.zzb;
import com.google.android.gms.common.internal.Hide;

public class PlaceLikelihoodBufferResponse
  extends zzb<PlaceLikelihood, PlaceLikelihoodBuffer>
{
  PlaceLikelihoodBufferResponse() {}
  
  @Nullable
  public CharSequence getAttributions()
  {
    return ((PlaceLikelihoodBuffer)getResult()).getAttributions();
  }
  
  @Hide
  public String toString()
  {
    return ((PlaceLikelihoodBuffer)getResult()).toString();
  }
}

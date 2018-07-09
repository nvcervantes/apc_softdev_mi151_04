package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;

@Hide
public final class zzak
  extends zzaw
  implements PlaceLikelihood
{
  public zzak(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  public final float getLikelihood()
  {
    return zza("place_likelihood", -1.0F);
  }
  
  public final Place getPlace()
  {
    return new zzat(zza, zzb);
  }
}

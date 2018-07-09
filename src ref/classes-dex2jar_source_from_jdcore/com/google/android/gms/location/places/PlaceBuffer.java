package com.google.android.gms.location.places;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.location.places.internal.zzat;

public class PlaceBuffer
  extends AbstractDataBuffer<Place>
  implements Result
{
  private final Status zzb;
  private final String zzc;
  
  @Hide
  public PlaceBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
    zzb = PlacesStatusCodes.zza(paramDataHolder.zzb());
    if ((paramDataHolder != null) && (paramDataHolder.zzc() != null)) {}
    for (paramDataHolder = paramDataHolder.zzc().getString("com.google.android.gms.location.places.PlaceBuffer.ATTRIBUTIONS_EXTRA_KEY");; paramDataHolder = null)
    {
      zzc = paramDataHolder;
      return;
    }
  }
  
  public Place get(int paramInt)
  {
    return new zzat(zza, paramInt);
  }
  
  @Nullable
  public CharSequence getAttributions()
  {
    return zzc;
  }
  
  public Status getStatus()
  {
    return zzb;
  }
}

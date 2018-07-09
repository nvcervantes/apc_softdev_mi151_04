package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.location.places.internal.zzd;

public class AutocompletePredictionBuffer
  extends AbstractDataBuffer<AutocompletePrediction>
  implements Result
{
  @Hide
  public AutocompletePredictionBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  public AutocompletePrediction get(int paramInt)
  {
    return new zzd(zza, paramInt);
  }
  
  public Status getStatus()
  {
    return PlacesStatusCodes.zza(zza.zzb());
  }
  
  @Hide
  public String toString()
  {
    return zzbg.zza(this).zza("status", getStatus()).toString();
  }
}

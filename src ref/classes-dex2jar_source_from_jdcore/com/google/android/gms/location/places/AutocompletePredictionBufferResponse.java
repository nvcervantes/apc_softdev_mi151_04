package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.zzb;
import com.google.android.gms.common.internal.Hide;

public class AutocompletePredictionBufferResponse
  extends zzb<AutocompletePrediction, AutocompletePredictionBuffer>
{
  AutocompletePredictionBufferResponse() {}
  
  @Hide
  public String toString()
  {
    return ((AutocompletePredictionBuffer)getResult()).toString();
  }
}

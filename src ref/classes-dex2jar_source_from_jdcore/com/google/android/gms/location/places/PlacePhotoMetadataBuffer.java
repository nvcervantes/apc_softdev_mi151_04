package com.google.android.gms.location.places;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.location.places.internal.zzas;

public class PlacePhotoMetadataBuffer
  extends AbstractDataBuffer<PlacePhotoMetadata>
{
  @Hide
  public PlacePhotoMetadataBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  public PlacePhotoMetadata get(int paramInt)
  {
    return new zzas(zza, paramInt);
  }
}

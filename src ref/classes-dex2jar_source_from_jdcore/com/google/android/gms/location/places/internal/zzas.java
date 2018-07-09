package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResult;

@Hide
public final class zzas
  extends zzaw
  implements PlacePhotoMetadata
{
  private final String zzc = zze("photo_fife_url");
  
  public zzas(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  public final CharSequence getAttributions()
  {
    return zza("photo_attributions", null);
  }
  
  public final int getMaxHeight()
  {
    return zza("photo_max_height", 0);
  }
  
  public final int getMaxWidth()
  {
    return zza("photo_max_width", 0);
  }
  
  public final PendingResult<PlacePhotoResult> getPhoto(GoogleApiClient paramGoogleApiClient)
  {
    return getScaledPhoto(paramGoogleApiClient, getMaxWidth(), getMaxHeight());
  }
  
  public final PendingResult<PlacePhotoResult> getScaledPhoto(GoogleApiClient paramGoogleApiClient, int paramInt1, int paramInt2)
  {
    return ((PlacePhotoMetadata)freeze()).getScaledPhoto(paramGoogleApiClient, paramInt1, paramInt2);
  }
}

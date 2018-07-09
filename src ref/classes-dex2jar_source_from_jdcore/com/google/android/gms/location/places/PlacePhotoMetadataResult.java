package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public class PlacePhotoMetadataResult
  extends zzbgl
  implements Result
{
  public static final Parcelable.Creator<PlacePhotoMetadataResult> CREATOR = new zzj();
  private final Status zza;
  private DataHolder zzb;
  private final PlacePhotoMetadataBuffer zzc;
  
  @Hide
  public PlacePhotoMetadataResult(Status paramStatus, DataHolder paramDataHolder)
  {
    zza = paramStatus;
    zzb = paramDataHolder;
    if (paramDataHolder == null) {}
    for (paramStatus = null;; paramStatus = new PlacePhotoMetadataBuffer(zzb))
    {
      zzc = paramStatus;
      return;
    }
  }
  
  public PlacePhotoMetadataBuffer getPhotoMetadata()
  {
    return zzc;
  }
  
  public Status getStatus()
  {
    return zza;
  }
  
  @Hide
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, getStatus(), paramInt, false);
    zzbgo.zza(paramParcel, 2, zzb, paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
}

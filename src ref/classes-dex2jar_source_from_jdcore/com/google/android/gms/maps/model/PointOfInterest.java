package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class PointOfInterest
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<PointOfInterest> CREATOR = new zzj();
  public final LatLng latLng;
  public final String name;
  public final String placeId;
  
  public PointOfInterest(LatLng paramLatLng, String paramString1, String paramString2)
  {
    latLng = paramLatLng;
    placeId = paramString1;
    name = paramString2;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, latLng, paramInt, false);
    zzbgo.zza(paramParcel, 3, placeId, false);
    zzbgo.zza(paramParcel, 4, name, false);
    zzbgo.zza(paramParcel, i);
  }
}

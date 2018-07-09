package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class VisibleRegion
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<VisibleRegion> CREATOR = new zzv();
  public final LatLng farLeft;
  public final LatLng farRight;
  public final LatLngBounds latLngBounds;
  public final LatLng nearLeft;
  public final LatLng nearRight;
  
  public VisibleRegion(LatLng paramLatLng1, LatLng paramLatLng2, LatLng paramLatLng3, LatLng paramLatLng4, LatLngBounds paramLatLngBounds)
  {
    nearLeft = paramLatLng1;
    nearRight = paramLatLng2;
    farLeft = paramLatLng3;
    farRight = paramLatLng4;
    latLngBounds = paramLatLngBounds;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof VisibleRegion)) {
      return false;
    }
    paramObject = (VisibleRegion)paramObject;
    return (nearLeft.equals(nearLeft)) && (nearRight.equals(nearRight)) && (farLeft.equals(farLeft)) && (farRight.equals(farRight)) && (latLngBounds.equals(latLngBounds));
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { nearLeft, nearRight, farLeft, farRight, latLngBounds });
  }
  
  public final String toString()
  {
    return zzbg.zza(this).zza("nearLeft", nearLeft).zza("nearRight", nearRight).zza("farLeft", farLeft).zza("farRight", farRight).zza("latLngBounds", latLngBounds).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, nearLeft, paramInt, false);
    zzbgo.zza(paramParcel, 3, nearRight, paramInt, false);
    zzbgo.zza(paramParcel, 4, farLeft, paramInt, false);
    zzbgo.zza(paramParcel, 5, farRight, paramInt, false);
    zzbgo.zza(paramParcel, 6, latLngBounds, paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
}

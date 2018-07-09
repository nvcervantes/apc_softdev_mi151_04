package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class LatLng
  extends zzbgl
  implements ReflectedParcelable
{
  @Hide
  public static final Parcelable.Creator<LatLng> CREATOR = new zzf();
  public final double latitude;
  public final double longitude;
  
  public LatLng(double paramDouble1, double paramDouble2)
  {
    if ((-180.0D > paramDouble2) || (paramDouble2 >= 180.0D)) {
      paramDouble2 = ((paramDouble2 - 180.0D) % 360.0D + 360.0D) % 360.0D - 180.0D;
    }
    longitude = paramDouble2;
    latitude = Math.max(-90.0D, Math.min(90.0D, paramDouble1));
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof LatLng)) {
      return false;
    }
    paramObject = (LatLng)paramObject;
    return (Double.doubleToLongBits(latitude) == Double.doubleToLongBits(latitude)) && (Double.doubleToLongBits(longitude) == Double.doubleToLongBits(longitude));
  }
  
  public final int hashCode()
  {
    long l = Double.doubleToLongBits(latitude);
    int i = (int)(l ^ l >>> 32);
    l = Double.doubleToLongBits(longitude);
    return (i + 31) * 31 + (int)(l ^ l >>> 32);
  }
  
  public final String toString()
  {
    double d1 = latitude;
    double d2 = longitude;
    StringBuilder localStringBuilder = new StringBuilder(60);
    localStringBuilder.append("lat/lng: (");
    localStringBuilder.append(d1);
    localStringBuilder.append(",");
    localStringBuilder.append(d2);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, latitude);
    zzbgo.zza(paramParcel, 3, longitude);
    zzbgo.zza(paramParcel, paramInt);
  }
}

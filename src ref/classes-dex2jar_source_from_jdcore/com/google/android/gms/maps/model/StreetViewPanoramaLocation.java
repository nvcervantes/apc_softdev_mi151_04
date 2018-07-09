package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public class StreetViewPanoramaLocation
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<StreetViewPanoramaLocation> CREATOR = new zzo();
  public final StreetViewPanoramaLink[] links;
  public final String panoId;
  public final LatLng position;
  
  public StreetViewPanoramaLocation(StreetViewPanoramaLink[] paramArrayOfStreetViewPanoramaLink, LatLng paramLatLng, String paramString)
  {
    links = paramArrayOfStreetViewPanoramaLink;
    position = paramLatLng;
    panoId = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof StreetViewPanoramaLocation)) {
      return false;
    }
    paramObject = (StreetViewPanoramaLocation)paramObject;
    return (panoId.equals(panoId)) && (position.equals(position));
  }
  
  public int hashCode()
  {
    return Arrays.hashCode(new Object[] { position, panoId });
  }
  
  public String toString()
  {
    return zzbg.zza(this).zza("panoId", panoId).zza("position", position.toString()).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, links, paramInt, false);
    zzbgo.zza(paramParcel, 3, position, paramInt, false);
    zzbgo.zza(paramParcel, 4, panoId, false);
    zzbgo.zza(paramParcel, i);
  }
}

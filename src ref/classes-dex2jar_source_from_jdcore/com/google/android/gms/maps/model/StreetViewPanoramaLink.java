package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public class StreetViewPanoramaLink
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<StreetViewPanoramaLink> CREATOR = new zzn();
  public final float bearing;
  public final String panoId;
  
  @Hide
  public StreetViewPanoramaLink(String paramString, float paramFloat)
  {
    panoId = paramString;
    float f = paramFloat;
    if (paramFloat <= 0.0D) {
      f = paramFloat % 360.0F + 360.0F;
    }
    bearing = (f % 360.0F);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof StreetViewPanoramaLink)) {
      return false;
    }
    paramObject = (StreetViewPanoramaLink)paramObject;
    return (panoId.equals(panoId)) && (Float.floatToIntBits(bearing) == Float.floatToIntBits(bearing));
  }
  
  public int hashCode()
  {
    return Arrays.hashCode(new Object[] { panoId, Float.valueOf(bearing) });
  }
  
  public String toString()
  {
    return zzbg.zza(this).zza("panoId", panoId).zza("bearing", Float.valueOf(bearing)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, panoId, false);
    zzbgo.zza(paramParcel, 3, bearing);
    zzbgo.zza(paramParcel, paramInt);
  }
}

package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public class StreetViewPanoramaCamera
  extends zzbgl
  implements ReflectedParcelable
{
  @Hide
  public static final Parcelable.Creator<StreetViewPanoramaCamera> CREATOR = new zzm();
  public final float bearing;
  public final float tilt;
  public final float zoom;
  @NonNull
  private final StreetViewPanoramaOrientation zza;
  
  public StreetViewPanoramaCamera(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    boolean bool;
    if ((-90.0F <= paramFloat2) && (paramFloat2 <= 90.0F)) {
      bool = true;
    } else {
      bool = false;
    }
    StringBuilder localStringBuilder = new StringBuilder(62);
    localStringBuilder.append("Tilt needs to be between -90 and 90 inclusive: ");
    localStringBuilder.append(paramFloat2);
    zzbq.zzb(bool, localStringBuilder.toString());
    float f = paramFloat1;
    if (paramFloat1 <= 0.0D) {
      f = 0.0F;
    }
    zoom = f;
    tilt = (0.0F + paramFloat2);
    if (paramFloat3 <= 0.0D) {
      paramFloat1 = paramFloat3 % 360.0F + 360.0F;
    } else {
      paramFloat1 = paramFloat3;
    }
    bearing = (paramFloat1 % 360.0F);
    zza = new StreetViewPanoramaOrientation.Builder().tilt(paramFloat2).bearing(paramFloat3).build();
  }
  
  public static Builder builder()
  {
    return new Builder();
  }
  
  public static Builder builder(@NonNull StreetViewPanoramaCamera paramStreetViewPanoramaCamera)
  {
    return new Builder(paramStreetViewPanoramaCamera);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof StreetViewPanoramaCamera)) {
      return false;
    }
    paramObject = (StreetViewPanoramaCamera)paramObject;
    return (Float.floatToIntBits(zoom) == Float.floatToIntBits(zoom)) && (Float.floatToIntBits(tilt) == Float.floatToIntBits(tilt)) && (Float.floatToIntBits(bearing) == Float.floatToIntBits(bearing));
  }
  
  @NonNull
  public StreetViewPanoramaOrientation getOrientation()
  {
    return zza;
  }
  
  public int hashCode()
  {
    return Arrays.hashCode(new Object[] { Float.valueOf(zoom), Float.valueOf(tilt), Float.valueOf(bearing) });
  }
  
  public String toString()
  {
    return zzbg.zza(this).zza("zoom", Float.valueOf(zoom)).zza("tilt", Float.valueOf(tilt)).zza("bearing", Float.valueOf(bearing)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, zoom);
    zzbgo.zza(paramParcel, 3, tilt);
    zzbgo.zza(paramParcel, 4, bearing);
    zzbgo.zza(paramParcel, paramInt);
  }
  
  public static final class Builder
  {
    public float bearing;
    public float tilt;
    public float zoom;
    
    public Builder() {}
    
    public Builder(@NonNull StreetViewPanoramaCamera paramStreetViewPanoramaCamera)
    {
      zzbq.zza(paramStreetViewPanoramaCamera, "StreetViewPanoramaCamera");
      zoom = zoom;
      bearing = bearing;
      tilt = tilt;
    }
    
    public final Builder bearing(float paramFloat)
    {
      bearing = paramFloat;
      return this;
    }
    
    public final StreetViewPanoramaCamera build()
    {
      return new StreetViewPanoramaCamera(zoom, tilt, bearing);
    }
    
    public final Builder orientation(StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation)
    {
      zzbq.zza(paramStreetViewPanoramaOrientation, "StreetViewPanoramaOrientation");
      tilt = tilt;
      bearing = bearing;
      return this;
    }
    
    public final Builder tilt(float paramFloat)
    {
      tilt = paramFloat;
      return this;
    }
    
    public final Builder zoom(float paramFloat)
    {
      zoom = paramFloat;
      return this;
    }
  }
}

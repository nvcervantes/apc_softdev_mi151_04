package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import com.google.android.gms.R.styleable;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class CameraPosition
  extends zzbgl
  implements ReflectedParcelable
{
  @Hide
  public static final Parcelable.Creator<CameraPosition> CREATOR = new zza();
  public final float bearing;
  public final LatLng target;
  public final float tilt;
  public final float zoom;
  
  public CameraPosition(LatLng paramLatLng, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    zzbq.zza(paramLatLng, "null camera target");
    boolean bool;
    if ((0.0F <= paramFloat2) && (paramFloat2 <= 90.0F)) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zzb(bool, "Tilt needs to be between 0 and 90 inclusive: %s", new Object[] { Float.valueOf(paramFloat2) });
    target = paramLatLng;
    zoom = paramFloat1;
    tilt = (paramFloat2 + 0.0F);
    paramFloat1 = paramFloat3;
    if (paramFloat3 <= 0.0D) {
      paramFloat1 = paramFloat3 % 360.0F + 360.0F;
    }
    bearing = (paramFloat1 % 360.0F);
  }
  
  public static Builder builder()
  {
    return new Builder();
  }
  
  public static Builder builder(CameraPosition paramCameraPosition)
  {
    return new Builder(paramCameraPosition);
  }
  
  public static CameraPosition createFromAttributes(Context paramContext, AttributeSet paramAttributeSet)
  {
    if (paramAttributeSet == null) {
      return null;
    }
    paramContext = paramContext.getResources().obtainAttributes(paramAttributeSet, R.styleable.MapAttrs);
    float f1;
    if (paramContext.hasValue(R.styleable.MapAttrs_cameraTargetLat)) {
      f1 = paramContext.getFloat(R.styleable.MapAttrs_cameraTargetLat, 0.0F);
    } else {
      f1 = 0.0F;
    }
    float f2;
    if (paramContext.hasValue(R.styleable.MapAttrs_cameraTargetLng)) {
      f2 = paramContext.getFloat(R.styleable.MapAttrs_cameraTargetLng, 0.0F);
    } else {
      f2 = 0.0F;
    }
    paramAttributeSet = new LatLng(f1, f2);
    Builder localBuilder = builder();
    localBuilder.target(paramAttributeSet);
    if (paramContext.hasValue(R.styleable.MapAttrs_cameraZoom)) {
      localBuilder.zoom(paramContext.getFloat(R.styleable.MapAttrs_cameraZoom, 0.0F));
    }
    if (paramContext.hasValue(R.styleable.MapAttrs_cameraBearing)) {
      localBuilder.bearing(paramContext.getFloat(R.styleable.MapAttrs_cameraBearing, 0.0F));
    }
    if (paramContext.hasValue(R.styleable.MapAttrs_cameraTilt)) {
      localBuilder.tilt(paramContext.getFloat(R.styleable.MapAttrs_cameraTilt, 0.0F));
    }
    return localBuilder.build();
  }
  
  public static final CameraPosition fromLatLngZoom(LatLng paramLatLng, float paramFloat)
  {
    return new CameraPosition(paramLatLng, paramFloat, 0.0F, 0.0F);
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof CameraPosition)) {
      return false;
    }
    paramObject = (CameraPosition)paramObject;
    return (target.equals(target)) && (Float.floatToIntBits(zoom) == Float.floatToIntBits(zoom)) && (Float.floatToIntBits(tilt) == Float.floatToIntBits(tilt)) && (Float.floatToIntBits(bearing) == Float.floatToIntBits(bearing));
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { target, Float.valueOf(zoom), Float.valueOf(tilt), Float.valueOf(bearing) });
  }
  
  public final String toString()
  {
    return zzbg.zza(this).zza("target", target).zza("zoom", Float.valueOf(zoom)).zza("tilt", Float.valueOf(tilt)).zza("bearing", Float.valueOf(bearing)).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, target, paramInt, false);
    zzbgo.zza(paramParcel, 3, zoom);
    zzbgo.zza(paramParcel, 4, tilt);
    zzbgo.zza(paramParcel, 5, bearing);
    zzbgo.zza(paramParcel, i);
  }
  
  public static final class Builder
  {
    private LatLng zza;
    private float zzb;
    private float zzc;
    private float zzd;
    
    public Builder() {}
    
    public Builder(CameraPosition paramCameraPosition)
    {
      zza = target;
      zzb = zoom;
      zzc = tilt;
      zzd = bearing;
    }
    
    public final Builder bearing(float paramFloat)
    {
      zzd = paramFloat;
      return this;
    }
    
    public final CameraPosition build()
    {
      return new CameraPosition(zza, zzb, zzc, zzd);
    }
    
    public final Builder target(LatLng paramLatLng)
    {
      zza = paramLatLng;
      return this;
    }
    
    public final Builder tilt(float paramFloat)
    {
      zzc = paramFloat;
      return this;
    }
    
    public final Builder zoom(float paramFloat)
    {
      zzb = paramFloat;
      return this;
    }
  }
}

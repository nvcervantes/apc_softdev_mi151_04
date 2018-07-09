package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.List;

public final class CircleOptions
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<CircleOptions> CREATOR = new zzc();
  private LatLng zza = null;
  private double zzb = 0.0D;
  private float zzc = 10.0F;
  private int zzd = -16777216;
  private int zze = 0;
  private float zzf = 0.0F;
  private boolean zzg = true;
  private boolean zzh = false;
  @Nullable
  private List<PatternItem> zzi = null;
  
  public CircleOptions() {}
  
  @Hide
  CircleOptions(LatLng paramLatLng, double paramDouble, float paramFloat1, int paramInt1, int paramInt2, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, @Nullable List<PatternItem> paramList)
  {
    zza = paramLatLng;
    zzb = paramDouble;
    zzc = paramFloat1;
    zzd = paramInt1;
    zze = paramInt2;
    zzf = paramFloat2;
    zzg = paramBoolean1;
    zzh = paramBoolean2;
    zzi = paramList;
  }
  
  public final CircleOptions center(LatLng paramLatLng)
  {
    zza = paramLatLng;
    return this;
  }
  
  public final CircleOptions clickable(boolean paramBoolean)
  {
    zzh = paramBoolean;
    return this;
  }
  
  public final CircleOptions fillColor(int paramInt)
  {
    zze = paramInt;
    return this;
  }
  
  public final LatLng getCenter()
  {
    return zza;
  }
  
  public final int getFillColor()
  {
    return zze;
  }
  
  public final double getRadius()
  {
    return zzb;
  }
  
  public final int getStrokeColor()
  {
    return zzd;
  }
  
  @Nullable
  public final List<PatternItem> getStrokePattern()
  {
    return zzi;
  }
  
  public final float getStrokeWidth()
  {
    return zzc;
  }
  
  public final float getZIndex()
  {
    return zzf;
  }
  
  public final boolean isClickable()
  {
    return zzh;
  }
  
  public final boolean isVisible()
  {
    return zzg;
  }
  
  public final CircleOptions radius(double paramDouble)
  {
    zzb = paramDouble;
    return this;
  }
  
  public final CircleOptions strokeColor(int paramInt)
  {
    zzd = paramInt;
    return this;
  }
  
  public final CircleOptions strokePattern(@Nullable List<PatternItem> paramList)
  {
    zzi = paramList;
    return this;
  }
  
  public final CircleOptions strokeWidth(float paramFloat)
  {
    zzc = paramFloat;
    return this;
  }
  
  public final CircleOptions visible(boolean paramBoolean)
  {
    zzg = paramBoolean;
    return this;
  }
  
  @Hide
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, getCenter(), paramInt, false);
    zzbgo.zza(paramParcel, 3, getRadius());
    zzbgo.zza(paramParcel, 4, getStrokeWidth());
    zzbgo.zza(paramParcel, 5, getStrokeColor());
    zzbgo.zza(paramParcel, 6, getFillColor());
    zzbgo.zza(paramParcel, 7, getZIndex());
    zzbgo.zza(paramParcel, 8, isVisible());
    zzbgo.zza(paramParcel, 9, isClickable());
    zzbgo.zzc(paramParcel, 10, getStrokePattern(), false);
    zzbgo.zza(paramParcel, i);
  }
  
  public final CircleOptions zIndex(float paramFloat)
  {
    zzf = paramFloat;
    return this;
  }
}

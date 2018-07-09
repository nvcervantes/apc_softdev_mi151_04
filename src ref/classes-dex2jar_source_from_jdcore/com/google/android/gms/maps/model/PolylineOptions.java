package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class PolylineOptions
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<PolylineOptions> CREATOR = new zzl();
  private final List<LatLng> zza;
  private float zzb = 10.0F;
  private int zzc = -16777216;
  private float zzd = 0.0F;
  private boolean zze = true;
  private boolean zzf = false;
  private boolean zzg = false;
  @NonNull
  private Cap zzh = new ButtCap();
  @NonNull
  private Cap zzi = new ButtCap();
  private int zzj = 0;
  @Nullable
  private List<PatternItem> zzk = null;
  
  public PolylineOptions()
  {
    zza = new ArrayList();
  }
  
  @Hide
  PolylineOptions(List paramList, float paramFloat1, int paramInt1, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, @Nullable Cap paramCap1, @Nullable Cap paramCap2, int paramInt2, @Nullable List<PatternItem> paramList1)
  {
    zza = paramList;
    zzb = paramFloat1;
    zzc = paramInt1;
    zzd = paramFloat2;
    zze = paramBoolean1;
    zzf = paramBoolean2;
    zzg = paramBoolean3;
    if (paramCap1 != null) {
      zzh = paramCap1;
    }
    if (paramCap2 != null) {
      zzi = paramCap2;
    }
    zzj = paramInt2;
    zzk = paramList1;
  }
  
  public final PolylineOptions add(LatLng paramLatLng)
  {
    zza.add(paramLatLng);
    return this;
  }
  
  public final PolylineOptions add(LatLng... paramVarArgs)
  {
    zza.addAll(Arrays.asList(paramVarArgs));
    return this;
  }
  
  public final PolylineOptions addAll(Iterable<LatLng> paramIterable)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      LatLng localLatLng = (LatLng)paramIterable.next();
      zza.add(localLatLng);
    }
    return this;
  }
  
  public final PolylineOptions clickable(boolean paramBoolean)
  {
    zzg = paramBoolean;
    return this;
  }
  
  public final PolylineOptions color(int paramInt)
  {
    zzc = paramInt;
    return this;
  }
  
  public final PolylineOptions endCap(@NonNull Cap paramCap)
  {
    zzi = ((Cap)zzbq.zza(paramCap, "endCap must not be null"));
    return this;
  }
  
  public final PolylineOptions geodesic(boolean paramBoolean)
  {
    zzf = paramBoolean;
    return this;
  }
  
  public final int getColor()
  {
    return zzc;
  }
  
  @NonNull
  public final Cap getEndCap()
  {
    return zzi;
  }
  
  public final int getJointType()
  {
    return zzj;
  }
  
  @Nullable
  public final List<PatternItem> getPattern()
  {
    return zzk;
  }
  
  public final List<LatLng> getPoints()
  {
    return zza;
  }
  
  @NonNull
  public final Cap getStartCap()
  {
    return zzh;
  }
  
  public final float getWidth()
  {
    return zzb;
  }
  
  public final float getZIndex()
  {
    return zzd;
  }
  
  public final boolean isClickable()
  {
    return zzg;
  }
  
  public final boolean isGeodesic()
  {
    return zzf;
  }
  
  public final boolean isVisible()
  {
    return zze;
  }
  
  public final PolylineOptions jointType(int paramInt)
  {
    zzj = paramInt;
    return this;
  }
  
  public final PolylineOptions pattern(@Nullable List<PatternItem> paramList)
  {
    zzk = paramList;
    return this;
  }
  
  public final PolylineOptions startCap(@NonNull Cap paramCap)
  {
    zzh = ((Cap)zzbq.zza(paramCap, "startCap must not be null"));
    return this;
  }
  
  public final PolylineOptions visible(boolean paramBoolean)
  {
    zze = paramBoolean;
    return this;
  }
  
  public final PolylineOptions width(float paramFloat)
  {
    zzb = paramFloat;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zzc(paramParcel, 2, getPoints(), false);
    zzbgo.zza(paramParcel, 3, getWidth());
    zzbgo.zza(paramParcel, 4, getColor());
    zzbgo.zza(paramParcel, 5, getZIndex());
    zzbgo.zza(paramParcel, 6, isVisible());
    zzbgo.zza(paramParcel, 7, isGeodesic());
    zzbgo.zza(paramParcel, 8, isClickable());
    zzbgo.zza(paramParcel, 9, getStartCap(), paramInt, false);
    zzbgo.zza(paramParcel, 10, getEndCap(), paramInt, false);
    zzbgo.zza(paramParcel, 11, getJointType());
    zzbgo.zzc(paramParcel, 12, getPattern(), false);
    zzbgo.zza(paramParcel, i);
  }
  
  public final PolylineOptions zIndex(float paramFloat)
  {
    zzd = paramFloat;
    return this;
  }
}

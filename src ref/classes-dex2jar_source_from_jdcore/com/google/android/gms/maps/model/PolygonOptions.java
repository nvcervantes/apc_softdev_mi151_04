package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class PolygonOptions
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<PolygonOptions> CREATOR = new zzk();
  private final List<LatLng> zza;
  private final List<List<LatLng>> zzb;
  private float zzc = 10.0F;
  private int zzd = -16777216;
  private int zze = 0;
  private float zzf = 0.0F;
  private boolean zzg = true;
  private boolean zzh = false;
  private boolean zzi = false;
  private int zzj = 0;
  @Nullable
  private List<PatternItem> zzk = null;
  
  public PolygonOptions()
  {
    zza = new ArrayList();
    zzb = new ArrayList();
  }
  
  @Hide
  PolygonOptions(List<LatLng> paramList, List paramList1, float paramFloat1, int paramInt1, int paramInt2, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt3, @Nullable List<PatternItem> paramList2)
  {
    zza = paramList;
    zzb = paramList1;
    zzc = paramFloat1;
    zzd = paramInt1;
    zze = paramInt2;
    zzf = paramFloat2;
    zzg = paramBoolean1;
    zzh = paramBoolean2;
    zzi = paramBoolean3;
    zzj = paramInt3;
    zzk = paramList2;
  }
  
  public final PolygonOptions add(LatLng paramLatLng)
  {
    zza.add(paramLatLng);
    return this;
  }
  
  public final PolygonOptions add(LatLng... paramVarArgs)
  {
    zza.addAll(Arrays.asList(paramVarArgs));
    return this;
  }
  
  public final PolygonOptions addAll(Iterable<LatLng> paramIterable)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      LatLng localLatLng = (LatLng)paramIterable.next();
      zza.add(localLatLng);
    }
    return this;
  }
  
  public final PolygonOptions addHole(Iterable<LatLng> paramIterable)
  {
    ArrayList localArrayList = new ArrayList();
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext()) {
      localArrayList.add((LatLng)paramIterable.next());
    }
    zzb.add(localArrayList);
    return this;
  }
  
  public final PolygonOptions clickable(boolean paramBoolean)
  {
    zzi = paramBoolean;
    return this;
  }
  
  public final PolygonOptions fillColor(int paramInt)
  {
    zze = paramInt;
    return this;
  }
  
  public final PolygonOptions geodesic(boolean paramBoolean)
  {
    zzh = paramBoolean;
    return this;
  }
  
  public final int getFillColor()
  {
    return zze;
  }
  
  public final List<List<LatLng>> getHoles()
  {
    return zzb;
  }
  
  public final List<LatLng> getPoints()
  {
    return zza;
  }
  
  public final int getStrokeColor()
  {
    return zzd;
  }
  
  public final int getStrokeJointType()
  {
    return zzj;
  }
  
  @Nullable
  public final List<PatternItem> getStrokePattern()
  {
    return zzk;
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
    return zzi;
  }
  
  public final boolean isGeodesic()
  {
    return zzh;
  }
  
  public final boolean isVisible()
  {
    return zzg;
  }
  
  public final PolygonOptions strokeColor(int paramInt)
  {
    zzd = paramInt;
    return this;
  }
  
  public final PolygonOptions strokeJointType(int paramInt)
  {
    zzj = paramInt;
    return this;
  }
  
  public final PolygonOptions strokePattern(@Nullable List<PatternItem> paramList)
  {
    zzk = paramList;
    return this;
  }
  
  public final PolygonOptions strokeWidth(float paramFloat)
  {
    zzc = paramFloat;
    return this;
  }
  
  public final PolygonOptions visible(boolean paramBoolean)
  {
    zzg = paramBoolean;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zzc(paramParcel, 2, getPoints(), false);
    zzbgo.zzd(paramParcel, 3, zzb, false);
    zzbgo.zza(paramParcel, 4, getStrokeWidth());
    zzbgo.zza(paramParcel, 5, getStrokeColor());
    zzbgo.zza(paramParcel, 6, getFillColor());
    zzbgo.zza(paramParcel, 7, getZIndex());
    zzbgo.zza(paramParcel, 8, isVisible());
    zzbgo.zza(paramParcel, 9, isGeodesic());
    zzbgo.zza(paramParcel, 10, isClickable());
    zzbgo.zza(paramParcel, 11, getStrokeJointType());
    zzbgo.zzc(paramParcel, 12, getStrokePattern(), false);
    zzbgo.zza(paramParcel, paramInt);
  }
  
  public final PolygonOptions zIndex(float paramFloat)
  {
    zzf = paramFloat;
    return this;
  }
}

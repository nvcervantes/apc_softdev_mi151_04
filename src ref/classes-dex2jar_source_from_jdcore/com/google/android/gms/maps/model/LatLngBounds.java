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

public final class LatLngBounds
  extends zzbgl
  implements ReflectedParcelable
{
  @Hide
  public static final Parcelable.Creator<LatLngBounds> CREATOR = new zze();
  public final LatLng northeast;
  public final LatLng southwest;
  
  public LatLngBounds(LatLng paramLatLng1, LatLng paramLatLng2)
  {
    zzbq.zza(paramLatLng1, "null southwest");
    zzbq.zza(paramLatLng2, "null northeast");
    boolean bool;
    if (latitude >= latitude) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zzb(bool, "southern latitude exceeds northern latitude (%s > %s)", new Object[] { Double.valueOf(latitude), Double.valueOf(latitude) });
    southwest = paramLatLng1;
    northeast = paramLatLng2;
  }
  
  public static Builder builder()
  {
    return new Builder();
  }
  
  public static LatLngBounds createFromAttributes(Context paramContext, AttributeSet paramAttributeSet)
  {
    if (paramContext != null)
    {
      if (paramAttributeSet == null) {
        return null;
      }
      Object localObject = paramContext.getResources().obtainAttributes(paramAttributeSet, R.styleable.MapAttrs);
      if (((TypedArray)localObject).hasValue(R.styleable.MapAttrs_latLngBoundsSouthWestLatitude)) {
        paramContext = Float.valueOf(((TypedArray)localObject).getFloat(R.styleable.MapAttrs_latLngBoundsSouthWestLatitude, 0.0F));
      } else {
        paramContext = null;
      }
      if (((TypedArray)localObject).hasValue(R.styleable.MapAttrs_latLngBoundsSouthWestLongitude)) {
        paramAttributeSet = Float.valueOf(((TypedArray)localObject).getFloat(R.styleable.MapAttrs_latLngBoundsSouthWestLongitude, 0.0F));
      } else {
        paramAttributeSet = null;
      }
      Float localFloat;
      if (((TypedArray)localObject).hasValue(R.styleable.MapAttrs_latLngBoundsNorthEastLatitude)) {
        localFloat = Float.valueOf(((TypedArray)localObject).getFloat(R.styleable.MapAttrs_latLngBoundsNorthEastLatitude, 0.0F));
      } else {
        localFloat = null;
      }
      if (((TypedArray)localObject).hasValue(R.styleable.MapAttrs_latLngBoundsNorthEastLongitude)) {
        localObject = Float.valueOf(((TypedArray)localObject).getFloat(R.styleable.MapAttrs_latLngBoundsNorthEastLongitude, 0.0F));
      } else {
        localObject = null;
      }
      if ((paramContext != null) && (paramAttributeSet != null) && (localFloat != null))
      {
        if (localObject == null) {
          return null;
        }
        return new LatLngBounds(new LatLng(paramContext.floatValue(), paramAttributeSet.floatValue()), new LatLng(localFloat.floatValue(), ((Float)localObject).floatValue()));
      }
    }
    return null;
  }
  
  private final boolean zza(double paramDouble)
  {
    if (southwest.longitude <= northeast.longitude) {
      return (southwest.longitude <= paramDouble) && (paramDouble <= northeast.longitude);
    }
    if (southwest.longitude > paramDouble) {
      return paramDouble <= northeast.longitude;
    }
    return true;
  }
  
  private static double zzc(double paramDouble1, double paramDouble2)
  {
    return (paramDouble1 - paramDouble2 + 360.0D) % 360.0D;
  }
  
  private static double zzd(double paramDouble1, double paramDouble2)
  {
    return (paramDouble2 - paramDouble1 + 360.0D) % 360.0D;
  }
  
  public final boolean contains(LatLng paramLatLng)
  {
    double d = latitude;
    int i;
    if ((southwest.latitude <= d) && (d <= northeast.latitude)) {
      i = 1;
    } else {
      i = 0;
    }
    return (i != 0) && (zza(longitude));
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof LatLngBounds)) {
      return false;
    }
    paramObject = (LatLngBounds)paramObject;
    return (southwest.equals(southwest)) && (northeast.equals(northeast));
  }
  
  public final LatLng getCenter()
  {
    double d2 = (southwest.latitude + northeast.latitude) / 2.0D;
    double d1 = northeast.longitude;
    double d3 = southwest.longitude;
    if (d3 <= d1) {}
    for (;;)
    {
      d1 = (d1 + d3) / 2.0D;
      break;
      d1 += 360.0D;
    }
    return new LatLng(d2, d1);
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { southwest, northeast });
  }
  
  public final LatLngBounds including(LatLng paramLatLng)
  {
    double d6 = Math.min(southwest.latitude, latitude);
    double d7 = Math.max(northeast.latitude, latitude);
    double d4 = northeast.longitude;
    double d5 = southwest.longitude;
    double d3 = longitude;
    double d1 = d4;
    double d2 = d5;
    if (!zza(d3)) {
      if (zzc(d5, d3) < zzd(d4, d3))
      {
        d2 = d3;
        d1 = d4;
      }
      else
      {
        d1 = d3;
        d2 = d5;
      }
    }
    return new LatLngBounds(new LatLng(d6, d2), new LatLng(d7, d1));
  }
  
  public final String toString()
  {
    return zzbg.zza(this).zza("southwest", southwest).zza("northeast", northeast).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, southwest, paramInt, false);
    zzbgo.zza(paramParcel, 3, northeast, paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
  
  public static final class Builder
  {
    private double zza = Double.POSITIVE_INFINITY;
    private double zzb = Double.NEGATIVE_INFINITY;
    private double zzc = NaN.0D;
    private double zzd = NaN.0D;
    
    public Builder() {}
    
    public final LatLngBounds build()
    {
      zzbq.zza(Double.isNaN(zzc) ^ true, "no included points");
      return new LatLngBounds(new LatLng(zza, zzc), new LatLng(zzb, zzd));
    }
    
    public final Builder include(LatLng paramLatLng)
    {
      zza = Math.min(zza, latitude);
      zzb = Math.max(zzb, latitude);
      double d1 = longitude;
      if (Double.isNaN(zzc))
      {
        zzc = d1;
      }
      else
      {
        double d2 = zzc;
        double d3 = zzd;
        int j = 0;
        if (d2 <= d3)
        {
          i = j;
          if (zzc > d1) {
            break label130;
          }
          i = j;
          if (d1 > zzd) {
            break label130;
          }
        }
        else if (zzc > d1)
        {
          i = j;
          if (d1 > zzd) {
            break label130;
          }
        }
        int i = 1;
        label130:
        if (i != 0) {
          return this;
        }
        if (LatLngBounds.zza(zzc, d1) < LatLngBounds.zzb(zzd, d1))
        {
          zzc = d1;
          return this;
        }
      }
      zzd = d1;
      return this;
    }
  }
}

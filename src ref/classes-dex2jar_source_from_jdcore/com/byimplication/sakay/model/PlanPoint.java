package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000<\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\000\n\002\020\006\n\002\b\017\n\002\020\b\n\000\n\002\020\013\n\000\n\002\020\000\n\002\b\003\n\002\020\002\n\002\b\004\b\b\030\000 #2\0020\001:\001#B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B%\022\006\020\005\032\0020\006\022\006\020\007\032\0020\b\022\006\020\t\032\0020\b\022\006\020\n\032\0020\006¢\006\002\020\013J\t\020\022\032\0020\006HÆ\003J\t\020\023\032\0020\bHÆ\003J\t\020\024\032\0020\bHÆ\003J\t\020\025\032\0020\006HÆ\003J1\020\026\032\0020\0002\b\b\002\020\005\032\0020\0062\b\b\002\020\007\032\0020\b2\b\b\002\020\t\032\0020\b2\b\b\002\020\n\032\0020\006HÆ\001J\b\020\027\032\0020\030H\026J\023\020\031\032\0020\0322\b\020\033\032\004\030\0010\034HÖ\003J\t\020\035\032\0020\030HÖ\001J\t\020\036\032\0020\006HÖ\001J\030\020\037\032\0020 2\006\020!\032\0020\0032\006\020\"\032\0020\030H\026R\021\020\t\032\0020\b¢\006\b\n\000\032\004\b\f\020\rR\021\020\007\032\0020\b¢\006\b\n\000\032\004\b\016\020\rR\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\017\020\020R\021\020\n\032\0020\006¢\006\b\n\000\032\004\b\021\020\020¨\006$"}, d2={"Lcom/byimplication/sakay/model/PlanPoint;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "name", "", "lon", "", "lat", "orig", "(Ljava/lang/String;DDLjava/lang/String;)V", "getLat", "()D", "getLon", "getName", "()Ljava/lang/String;", "getOrig", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class PlanPoint
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<PlanPoint> CREATOR = (Parcelable.Creator)new PlanPoint.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  private final double lat;
  private final double lon;
  @NotNull
  private final String name;
  @NotNull
  private final String orig;
  
  public PlanPoint(@NotNull Parcel paramParcel)
  {
    this(str, d1, d2, paramParcel);
  }
  
  public PlanPoint(@NotNull String paramString1, double paramDouble1, double paramDouble2, @NotNull String paramString2)
  {
    name = paramString1;
    lon = paramDouble1;
    lat = paramDouble2;
    orig = paramString2;
  }
  
  @NotNull
  public final String component1()
  {
    return name;
  }
  
  public final double component2()
  {
    return lon;
  }
  
  public final double component3()
  {
    return lat;
  }
  
  @NotNull
  public final String component4()
  {
    return orig;
  }
  
  @NotNull
  public final PlanPoint copy(@NotNull String paramString1, double paramDouble1, double paramDouble2, @NotNull String paramString2)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "name");
    Intrinsics.checkParameterIsNotNull(paramString2, "orig");
    return new PlanPoint(paramString1, paramDouble1, paramDouble2, paramString2);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof PlanPoint))
      {
        paramObject = (PlanPoint)paramObject;
        if ((Intrinsics.areEqual(name, name)) && (Double.compare(lon, lon) == 0) && (Double.compare(lat, lat) == 0) && (Intrinsics.areEqual(orig, orig))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  public final double getLat()
  {
    return lat;
  }
  
  public final double getLon()
  {
    return lon;
  }
  
  @NotNull
  public final String getName()
  {
    return name;
  }
  
  @NotNull
  public final String getOrig()
  {
    return orig;
  }
  
  public int hashCode()
  {
    String str = name;
    int j = 0;
    int i;
    if (str != null) {
      i = str.hashCode();
    } else {
      i = 0;
    }
    long l = Double.doubleToLongBits(lon);
    int k = (int)(l ^ l >>> 32);
    l = Double.doubleToLongBits(lat);
    int m = (int)(l ^ l >>> 32);
    str = orig;
    if (str != null) {
      j = str.hashCode();
    }
    return ((i * 31 + k) * 31 + m) * 31 + j;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PlanPoint(name=");
    localStringBuilder.append(name);
    localStringBuilder.append(", lon=");
    localStringBuilder.append(lon);
    localStringBuilder.append(", lat=");
    localStringBuilder.append(lat);
    localStringBuilder.append(", orig=");
    localStringBuilder.append(orig);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramParcel, "dest");
    paramParcel.writeString(name);
    paramParcel.writeDouble(lon);
    paramParcel.writeDouble(lat);
    paramParcel.writeString(orig);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\006"}, d2={"Lcom/byimplication/sakay/model/PlanPoint$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/PlanPoint;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
  }
}

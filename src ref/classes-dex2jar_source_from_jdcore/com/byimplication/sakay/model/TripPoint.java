package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000N\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\000\n\002\020\006\n\002\b\002\n\002\020\t\n\002\b\005\n\002\020\b\n\002\b\002\n\002\030\002\n\002\b#\n\002\020\013\n\000\n\002\020\000\n\002\b\003\n\002\020\002\n\002\b\004\b\b\030\000 A2\0020\001:\001AB\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004Bs\022\b\020\005\032\004\030\0010\006\022\b\020\007\032\004\030\0010\b\022\b\020\t\032\004\030\0010\b\022\b\020\n\032\004\030\0010\013\022\b\020\f\032\004\030\0010\013\022\b\020\r\032\004\030\0010\006\022\b\020\016\032\004\030\0010\006\022\b\020\017\032\004\030\0010\006\022\b\020\020\032\004\030\0010\021\022\b\020\022\032\004\030\0010\021\022\b\020\023\032\004\030\0010\024¢\006\002\020\025J\013\020)\032\004\030\0010\006HÆ\003J\020\020*\032\004\030\0010\021HÆ\003¢\006\002\020%J\013\020+\032\004\030\0010\024HÆ\003J\020\020,\032\004\030\0010\bHÆ\003¢\006\002\020\035J\020\020-\032\004\030\0010\bHÆ\003¢\006\002\020\035J\020\020.\032\004\030\0010\013HÆ\003¢\006\002\020\027J\020\020/\032\004\030\0010\013HÆ\003¢\006\002\020\027J\013\0200\032\004\030\0010\006HÆ\003J\013\0201\032\004\030\0010\006HÆ\003J\013\0202\032\004\030\0010\006HÆ\003J\020\0203\032\004\030\0010\021HÆ\003¢\006\002\020%J\001\0204\032\0020\0002\n\b\002\020\005\032\004\030\0010\0062\n\b\002\020\007\032\004\030\0010\b2\n\b\002\020\t\032\004\030\0010\b2\n\b\002\020\n\032\004\030\0010\0132\n\b\002\020\f\032\004\030\0010\0132\n\b\002\020\r\032\004\030\0010\0062\n\b\002\020\016\032\004\030\0010\0062\n\b\002\020\017\032\004\030\0010\0062\n\b\002\020\020\032\004\030\0010\0212\n\b\002\020\022\032\004\030\0010\0212\n\b\002\020\023\032\004\030\0010\024HÆ\001¢\006\002\0205J\b\0206\032\0020\021H\026J\023\0207\032\002082\b\0209\032\004\030\0010:HÖ\003J\t\020;\032\0020\021HÖ\001J\t\020<\032\0020\006HÖ\001J\030\020=\032\0020>2\006\020?\032\0020\0032\006\020@\032\0020\021H\026R\025\020\f\032\004\030\0010\013¢\006\n\n\002\020\030\032\004\b\026\020\027R\023\020\023\032\004\030\0010\024¢\006\b\n\000\032\004\b\031\020\032R\025\020\n\032\004\030\0010\013¢\006\n\n\002\020\030\032\004\b\033\020\027R\025\020\t\032\004\030\0010\b¢\006\n\n\002\020\036\032\004\b\034\020\035R\025\020\007\032\004\030\0010\b¢\006\n\n\002\020\036\032\004\b\037\020\035R\023\020\005\032\004\030\0010\006¢\006\b\n\000\032\004\b \020!R\023\020\r\032\004\030\0010\006¢\006\b\n\000\032\004\b\"\020!R\023\020\016\032\004\030\0010\006¢\006\b\n\000\032\004\b#\020!R\025\020\020\032\004\030\0010\021¢\006\n\n\002\020&\032\004\b$\020%R\025\020\022\032\004\030\0010\021¢\006\n\n\002\020&\032\004\b'\020%R\023\020\017\032\004\030\0010\006¢\006\b\n\000\032\004\b(\020!¨\006B"}, d2={"Lcom/byimplication/sakay/model/TripPoint;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "name", "", "lon", "", "lat", "departure", "", "arrival", "orig", "stopId", "zoneId", "stopIndex", "", "stopSequence", "congestion", "Lcom/byimplication/sakay/model/CongestionModel;", "(Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/byimplication/sakay/model/CongestionModel;)V", "getArrival", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getCongestion", "()Lcom/byimplication/sakay/model/CongestionModel;", "getDeparture", "getLat", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getLon", "getName", "()Ljava/lang/String;", "getOrig", "getStopId", "getStopIndex", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getStopSequence", "getZoneId", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/byimplication/sakay/model/CongestionModel;)Lcom/byimplication/sakay/model/TripPoint;", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class TripPoint
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<TripPoint> CREATOR = (Parcelable.Creator)new TripPoint.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  @Nullable
  private final Long arrival;
  @Nullable
  private final CongestionModel congestion;
  @Nullable
  private final Long departure;
  @Nullable
  private final Double lat;
  @Nullable
  private final Double lon;
  @Nullable
  private final String name;
  @Nullable
  private final String orig;
  @Nullable
  private final String stopId;
  @Nullable
  private final Integer stopIndex;
  @Nullable
  private final Integer stopSequence;
  @Nullable
  private final String zoneId;
  
  public TripPoint(@NotNull Parcel paramParcel)
  {
    this(paramParcel.readString(), (Double)paramParcel.readValue(Double.TYPE.getClassLoader()), (Double)paramParcel.readValue(Double.TYPE.getClassLoader()), (Long)paramParcel.readValue(Long.TYPE.getClassLoader()), (Long)paramParcel.readValue(Long.TYPE.getClassLoader()), paramParcel.readString(), paramParcel.readString(), paramParcel.readString(), (Integer)paramParcel.readValue(Integer.TYPE.getClassLoader()), (Integer)paramParcel.readValue(Integer.TYPE.getClassLoader()), (CongestionModel)paramParcel.readParcelable(CongestionModel.class.getClassLoader()));
  }
  
  public TripPoint(@Nullable String paramString1, @Nullable Double paramDouble1, @Nullable Double paramDouble2, @Nullable Long paramLong1, @Nullable Long paramLong2, @Nullable String paramString2, @Nullable String paramString3, @Nullable String paramString4, @Nullable Integer paramInteger1, @Nullable Integer paramInteger2, @Nullable CongestionModel paramCongestionModel)
  {
    name = paramString1;
    lon = paramDouble1;
    lat = paramDouble2;
    departure = paramLong1;
    arrival = paramLong2;
    orig = paramString2;
    stopId = paramString3;
    zoneId = paramString4;
    stopIndex = paramInteger1;
    stopSequence = paramInteger2;
    congestion = paramCongestionModel;
  }
  
  @Nullable
  public final String component1()
  {
    return name;
  }
  
  @Nullable
  public final Integer component10()
  {
    return stopSequence;
  }
  
  @Nullable
  public final CongestionModel component11()
  {
    return congestion;
  }
  
  @Nullable
  public final Double component2()
  {
    return lon;
  }
  
  @Nullable
  public final Double component3()
  {
    return lat;
  }
  
  @Nullable
  public final Long component4()
  {
    return departure;
  }
  
  @Nullable
  public final Long component5()
  {
    return arrival;
  }
  
  @Nullable
  public final String component6()
  {
    return orig;
  }
  
  @Nullable
  public final String component7()
  {
    return stopId;
  }
  
  @Nullable
  public final String component8()
  {
    return zoneId;
  }
  
  @Nullable
  public final Integer component9()
  {
    return stopIndex;
  }
  
  @NotNull
  public final TripPoint copy(@Nullable String paramString1, @Nullable Double paramDouble1, @Nullable Double paramDouble2, @Nullable Long paramLong1, @Nullable Long paramLong2, @Nullable String paramString2, @Nullable String paramString3, @Nullable String paramString4, @Nullable Integer paramInteger1, @Nullable Integer paramInteger2, @Nullable CongestionModel paramCongestionModel)
  {
    return new TripPoint(paramString1, paramDouble1, paramDouble2, paramLong1, paramLong2, paramString2, paramString3, paramString4, paramInteger1, paramInteger2, paramCongestionModel);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof TripPoint))
      {
        paramObject = (TripPoint)paramObject;
        if ((Intrinsics.areEqual(name, name)) && (Intrinsics.areEqual(lon, lon)) && (Intrinsics.areEqual(lat, lat)) && (Intrinsics.areEqual(departure, departure)) && (Intrinsics.areEqual(arrival, arrival)) && (Intrinsics.areEqual(orig, orig)) && (Intrinsics.areEqual(stopId, stopId)) && (Intrinsics.areEqual(zoneId, zoneId)) && (Intrinsics.areEqual(stopIndex, stopIndex)) && (Intrinsics.areEqual(stopSequence, stopSequence)) && (Intrinsics.areEqual(congestion, congestion))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @Nullable
  public final Long getArrival()
  {
    return arrival;
  }
  
  @Nullable
  public final CongestionModel getCongestion()
  {
    return congestion;
  }
  
  @Nullable
  public final Long getDeparture()
  {
    return departure;
  }
  
  @Nullable
  public final Double getLat()
  {
    return lat;
  }
  
  @Nullable
  public final Double getLon()
  {
    return lon;
  }
  
  @Nullable
  public final String getName()
  {
    return name;
  }
  
  @Nullable
  public final String getOrig()
  {
    return orig;
  }
  
  @Nullable
  public final String getStopId()
  {
    return stopId;
  }
  
  @Nullable
  public final Integer getStopIndex()
  {
    return stopIndex;
  }
  
  @Nullable
  public final Integer getStopSequence()
  {
    return stopSequence;
  }
  
  @Nullable
  public final String getZoneId()
  {
    return zoneId;
  }
  
  public int hashCode()
  {
    Object localObject = name;
    int i6 = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = lon;
    int j;
    if (localObject != null) {
      j = localObject.hashCode();
    } else {
      j = 0;
    }
    localObject = lat;
    int k;
    if (localObject != null) {
      k = localObject.hashCode();
    } else {
      k = 0;
    }
    localObject = departure;
    int m;
    if (localObject != null) {
      m = localObject.hashCode();
    } else {
      m = 0;
    }
    localObject = arrival;
    int n;
    if (localObject != null) {
      n = localObject.hashCode();
    } else {
      n = 0;
    }
    localObject = orig;
    int i1;
    if (localObject != null) {
      i1 = localObject.hashCode();
    } else {
      i1 = 0;
    }
    localObject = stopId;
    int i2;
    if (localObject != null) {
      i2 = localObject.hashCode();
    } else {
      i2 = 0;
    }
    localObject = zoneId;
    int i3;
    if (localObject != null) {
      i3 = localObject.hashCode();
    } else {
      i3 = 0;
    }
    localObject = stopIndex;
    int i4;
    if (localObject != null) {
      i4 = localObject.hashCode();
    } else {
      i4 = 0;
    }
    localObject = stopSequence;
    int i5;
    if (localObject != null) {
      i5 = localObject.hashCode();
    } else {
      i5 = 0;
    }
    localObject = congestion;
    if (localObject != null) {
      i6 = localObject.hashCode();
    }
    return (((((((((i * 31 + j) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2) * 31 + i3) * 31 + i4) * 31 + i5) * 31 + i6;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("TripPoint(name=");
    localStringBuilder.append(name);
    localStringBuilder.append(", lon=");
    localStringBuilder.append(lon);
    localStringBuilder.append(", lat=");
    localStringBuilder.append(lat);
    localStringBuilder.append(", departure=");
    localStringBuilder.append(departure);
    localStringBuilder.append(", arrival=");
    localStringBuilder.append(arrival);
    localStringBuilder.append(", orig=");
    localStringBuilder.append(orig);
    localStringBuilder.append(", stopId=");
    localStringBuilder.append(stopId);
    localStringBuilder.append(", zoneId=");
    localStringBuilder.append(zoneId);
    localStringBuilder.append(", stopIndex=");
    localStringBuilder.append(stopIndex);
    localStringBuilder.append(", stopSequence=");
    localStringBuilder.append(stopSequence);
    localStringBuilder.append(", congestion=");
    localStringBuilder.append(congestion);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramParcel, "dest");
    paramParcel.writeString(name);
    paramParcel.writeValue(lon);
    paramParcel.writeValue(lat);
    paramParcel.writeValue(departure);
    paramParcel.writeValue(arrival);
    paramParcel.writeString(orig);
    paramParcel.writeString(stopId);
    paramParcel.writeString(zoneId);
    paramParcel.writeValue(stopIndex);
    paramParcel.writeValue(stopSequence);
    paramParcel.writeParcelable((Parcelable)congestion, 0);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\006"}, d2={"Lcom/byimplication/sakay/model/TripPoint$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/TripPoint;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
  }
}

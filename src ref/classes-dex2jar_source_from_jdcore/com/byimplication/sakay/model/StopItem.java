package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000F\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\002\n\002\020\006\n\002\b\002\n\002\030\002\n\002\b\022\n\002\020\b\n\000\n\002\020\013\n\000\n\002\020\000\n\002\b\003\n\002\020\002\n\002\b\004\b\b\030\000 *2\0020\001:\001*B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B7\022\b\020\005\032\004\030\0010\006\022\b\020\007\032\004\030\0010\006\022\b\020\b\032\004\030\0010\t\022\b\020\n\032\004\030\0010\t\022\b\020\013\032\004\030\0010\f¢\006\002\020\rJ\013\020\027\032\004\030\0010\006HÆ\003J\013\020\030\032\004\030\0010\006HÆ\003J\020\020\031\032\004\030\0010\tHÆ\003¢\006\002\020\023J\020\020\032\032\004\030\0010\tHÆ\003¢\006\002\020\023J\013\020\033\032\004\030\0010\fHÆ\003JJ\020\034\032\0020\0002\n\b\002\020\005\032\004\030\0010\0062\n\b\002\020\007\032\004\030\0010\0062\n\b\002\020\b\032\004\030\0010\t2\n\b\002\020\n\032\004\030\0010\t2\n\b\002\020\013\032\004\030\0010\fHÆ\001¢\006\002\020\035J\b\020\036\032\0020\037H\026J\023\020 \032\0020!2\b\020\"\032\004\030\0010#HÖ\003J\t\020$\032\0020\037HÖ\001J\t\020%\032\0020\006HÖ\001J\030\020&\032\0020'2\006\020(\032\0020\0032\006\020)\032\0020\037H\026R\023\020\013\032\004\030\0010\f¢\006\b\n\000\032\004\b\016\020\017R\023\020\005\032\004\030\0010\006¢\006\b\n\000\032\004\b\020\020\021R\025\020\b\032\004\030\0010\t¢\006\n\n\002\020\024\032\004\b\022\020\023R\025\020\n\032\004\030\0010\t¢\006\n\n\002\020\024\032\004\b\025\020\023R\023\020\007\032\004\030\0010\006¢\006\b\n\000\032\004\b\026\020\021¨\006+"}, d2={"Lcom/byimplication/sakay/model/StopItem;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "id", "", "name", "lat", "", "lon", "congestion", "Lcom/byimplication/sakay/model/CongestionModel;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Lcom/byimplication/sakay/model/CongestionModel;)V", "getCongestion", "()Lcom/byimplication/sakay/model/CongestionModel;", "getId", "()Ljava/lang/String;", "getLat", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getLon", "getName", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Lcom/byimplication/sakay/model/CongestionModel;)Lcom/byimplication/sakay/model/StopItem;", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class StopItem
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<StopItem> CREATOR = (Parcelable.Creator)new StopItem.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  @Nullable
  private final CongestionModel congestion;
  @Nullable
  private final String id;
  @Nullable
  private final Double lat;
  @Nullable
  private final Double lon;
  @Nullable
  private final String name;
  
  public StopItem(@NotNull Parcel paramParcel)
  {
    this(paramParcel.readString(), paramParcel.readString(), (Double)paramParcel.readValue(Double.TYPE.getClassLoader()), (Double)paramParcel.readValue(Double.TYPE.getClassLoader()), (CongestionModel)paramParcel.readParcelable(CongestionModel.class.getClassLoader()));
  }
  
  public StopItem(@Nullable String paramString1, @Nullable String paramString2, @Nullable Double paramDouble1, @Nullable Double paramDouble2, @Nullable CongestionModel paramCongestionModel)
  {
    id = paramString1;
    name = paramString2;
    lat = paramDouble1;
    lon = paramDouble2;
    congestion = paramCongestionModel;
  }
  
  @Nullable
  public final String component1()
  {
    return id;
  }
  
  @Nullable
  public final String component2()
  {
    return name;
  }
  
  @Nullable
  public final Double component3()
  {
    return lat;
  }
  
  @Nullable
  public final Double component4()
  {
    return lon;
  }
  
  @Nullable
  public final CongestionModel component5()
  {
    return congestion;
  }
  
  @NotNull
  public final StopItem copy(@Nullable String paramString1, @Nullable String paramString2, @Nullable Double paramDouble1, @Nullable Double paramDouble2, @Nullable CongestionModel paramCongestionModel)
  {
    return new StopItem(paramString1, paramString2, paramDouble1, paramDouble2, paramCongestionModel);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof StopItem))
      {
        paramObject = (StopItem)paramObject;
        if ((Intrinsics.areEqual(id, id)) && (Intrinsics.areEqual(name, name)) && (Intrinsics.areEqual(lat, lat)) && (Intrinsics.areEqual(lon, lon)) && (Intrinsics.areEqual(congestion, congestion))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @Nullable
  public final CongestionModel getCongestion()
  {
    return congestion;
  }
  
  @Nullable
  public final String getId()
  {
    return id;
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
  
  public int hashCode()
  {
    Object localObject = id;
    int n = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = name;
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
    localObject = lon;
    int m;
    if (localObject != null) {
      m = localObject.hashCode();
    } else {
      m = 0;
    }
    localObject = congestion;
    if (localObject != null) {
      n = localObject.hashCode();
    }
    return (((i * 31 + j) * 31 + k) * 31 + m) * 31 + n;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("StopItem(id=");
    localStringBuilder.append(id);
    localStringBuilder.append(", name=");
    localStringBuilder.append(name);
    localStringBuilder.append(", lat=");
    localStringBuilder.append(lat);
    localStringBuilder.append(", lon=");
    localStringBuilder.append(lon);
    localStringBuilder.append(", congestion=");
    localStringBuilder.append(congestion);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramParcel, "dest");
    paramParcel.writeString(id);
    paramParcel.writeString(name);
    paramParcel.writeValue(lat);
    paramParcel.writeValue(lon);
    paramParcel.writeParcelable((Parcelable)congestion, 0);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\006"}, d2={"Lcom/byimplication/sakay/model/StopItem$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/StopItem;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
  }
}

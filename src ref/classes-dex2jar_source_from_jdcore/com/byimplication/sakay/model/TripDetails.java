package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000H\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020 \n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\016\n\002\020\b\n\000\n\002\020\013\n\000\n\002\020\000\n\002\b\003\n\002\020\002\n\002\b\004\b\b\030\000 &2\0020\001:\001&B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B1\022\f\020\005\032\b\022\004\022\0020\0070\006\022\b\020\b\032\004\030\0010\t\022\b\020\n\032\004\030\0010\t\022\b\020\013\032\004\030\0010\f¢\006\002\020\rJ\017\020\025\032\b\022\004\022\0020\0070\006HÆ\003J\013\020\026\032\004\030\0010\tHÆ\003J\013\020\027\032\004\030\0010\tHÆ\003J\013\020\030\032\004\030\0010\fHÆ\003J=\020\031\032\0020\0002\016\b\002\020\005\032\b\022\004\022\0020\0070\0062\n\b\002\020\b\032\004\030\0010\t2\n\b\002\020\n\032\004\030\0010\t2\n\b\002\020\013\032\004\030\0010\fHÆ\001J\b\020\032\032\0020\033H\026J\023\020\034\032\0020\0352\b\020\036\032\004\030\0010\037HÖ\003J\t\020 \032\0020\033HÖ\001J\t\020!\032\0020\tHÖ\001J\030\020\"\032\0020#2\006\020$\032\0020\0032\006\020%\032\0020\033H\026R\023\020\b\032\004\030\0010\t¢\006\b\n\000\032\004\b\016\020\017R\023\020\n\032\004\030\0010\t¢\006\b\n\000\032\004\b\020\020\017R\023\020\013\032\004\030\0010\f¢\006\b\n\000\032\004\b\021\020\022R\027\020\005\032\b\022\004\022\0020\0070\006¢\006\b\n\000\032\004\b\023\020\024¨\006'"}, d2={"Lcom/byimplication/sakay/model/TripDetails;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "stops", "", "Lcom/byimplication/sakay/model/StopItem;", "direction", "", "geometry", "meta", "Lcom/byimplication/sakay/model/TripDetailMetaInfo;", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lcom/byimplication/sakay/model/TripDetailMetaInfo;)V", "getDirection", "()Ljava/lang/String;", "getGeometry", "getMeta", "()Lcom/byimplication/sakay/model/TripDetailMetaInfo;", "getStops", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class TripDetails
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<TripDetails> CREATOR = (Parcelable.Creator)new TripDetails.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  @Nullable
  private final String direction;
  @Nullable
  private final String geometry;
  @Nullable
  private final TripDetailMetaInfo meta;
  @NotNull
  private final List<StopItem> stops;
  
  public TripDetails(@NotNull Parcel paramParcel)
  {
    this((List)localArrayList, paramParcel.readString(), paramParcel.readString(), (TripDetailMetaInfo)paramParcel.readParcelable(TripDetailMetaInfo.class.getClassLoader()));
  }
  
  public TripDetails(@NotNull List<StopItem> paramList, @Nullable String paramString1, @Nullable String paramString2, @Nullable TripDetailMetaInfo paramTripDetailMetaInfo)
  {
    stops = paramList;
    direction = paramString1;
    geometry = paramString2;
    meta = paramTripDetailMetaInfo;
  }
  
  @NotNull
  public final List<StopItem> component1()
  {
    return stops;
  }
  
  @Nullable
  public final String component2()
  {
    return direction;
  }
  
  @Nullable
  public final String component3()
  {
    return geometry;
  }
  
  @Nullable
  public final TripDetailMetaInfo component4()
  {
    return meta;
  }
  
  @NotNull
  public final TripDetails copy(@NotNull List<StopItem> paramList, @Nullable String paramString1, @Nullable String paramString2, @Nullable TripDetailMetaInfo paramTripDetailMetaInfo)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "stops");
    return new TripDetails(paramList, paramString1, paramString2, paramTripDetailMetaInfo);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof TripDetails))
      {
        paramObject = (TripDetails)paramObject;
        if ((Intrinsics.areEqual(stops, stops)) && (Intrinsics.areEqual(direction, direction)) && (Intrinsics.areEqual(geometry, geometry)) && (Intrinsics.areEqual(meta, meta))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @Nullable
  public final String getDirection()
  {
    return direction;
  }
  
  @Nullable
  public final String getGeometry()
  {
    return geometry;
  }
  
  @Nullable
  public final TripDetailMetaInfo getMeta()
  {
    return meta;
  }
  
  @NotNull
  public final List<StopItem> getStops()
  {
    return stops;
  }
  
  public int hashCode()
  {
    Object localObject = stops;
    int m = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = direction;
    int j;
    if (localObject != null) {
      j = localObject.hashCode();
    } else {
      j = 0;
    }
    localObject = geometry;
    int k;
    if (localObject != null) {
      k = localObject.hashCode();
    } else {
      k = 0;
    }
    localObject = meta;
    if (localObject != null) {
      m = localObject.hashCode();
    }
    return ((i * 31 + j) * 31 + k) * 31 + m;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("TripDetails(stops=");
    localStringBuilder.append(stops);
    localStringBuilder.append(", direction=");
    localStringBuilder.append(direction);
    localStringBuilder.append(", geometry=");
    localStringBuilder.append(geometry);
    localStringBuilder.append(", meta=");
    localStringBuilder.append(meta);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramParcel, "dest");
    paramParcel.writeTypedList(stops);
    paramParcel.writeString(direction);
    paramParcel.writeString(geometry);
    paramParcel.writeParcelable((Parcelable)meta, 0);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\006"}, d2={"Lcom/byimplication/sakay/model/TripDetails$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/TripDetails;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
  }
}

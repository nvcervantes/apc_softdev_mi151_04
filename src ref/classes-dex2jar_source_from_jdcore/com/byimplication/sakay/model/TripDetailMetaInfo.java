package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000<\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\006\n\002\020\b\n\000\n\002\020\013\n\000\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\002\n\002\b\004\b\b\030\000 \0312\0020\001:\001\031B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B\017\022\b\020\005\032\004\030\0010\006¢\006\002\020\007J\013\020\n\032\004\030\0010\006HÆ\003J\025\020\013\032\0020\0002\n\b\002\020\005\032\004\030\0010\006HÆ\001J\b\020\f\032\0020\rH\026J\023\020\016\032\0020\0172\b\020\020\032\004\030\0010\021HÖ\003J\t\020\022\032\0020\rHÖ\001J\t\020\023\032\0020\024HÖ\001J\030\020\025\032\0020\0262\006\020\027\032\0020\0032\006\020\030\032\0020\rH\026R\023\020\005\032\004\030\0010\006¢\006\b\n\000\032\004\b\b\020\t¨\006\032"}, d2={"Lcom/byimplication/sakay/model/TripDetailMetaInfo;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "congestionInfo", "Lcom/byimplication/sakay/model/CongestionInfo;", "(Lcom/byimplication/sakay/model/CongestionInfo;)V", "getCongestionInfo", "()Lcom/byimplication/sakay/model/CongestionInfo;", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class TripDetailMetaInfo
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<TripDetailMetaInfo> CREATOR = (Parcelable.Creator)new TripDetailMetaInfo.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  @Nullable
  private final CongestionInfo congestionInfo;
  
  public TripDetailMetaInfo(@NotNull Parcel paramParcel)
  {
    this((CongestionInfo)paramParcel.readParcelable(CongestionInfo.class.getClassLoader()));
  }
  
  public TripDetailMetaInfo(@Nullable CongestionInfo paramCongestionInfo)
  {
    congestionInfo = paramCongestionInfo;
  }
  
  @Nullable
  public final CongestionInfo component1()
  {
    return congestionInfo;
  }
  
  @NotNull
  public final TripDetailMetaInfo copy(@Nullable CongestionInfo paramCongestionInfo)
  {
    return new TripDetailMetaInfo(paramCongestionInfo);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof TripDetailMetaInfo))
      {
        paramObject = (TripDetailMetaInfo)paramObject;
        if (Intrinsics.areEqual(congestionInfo, congestionInfo)) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @Nullable
  public final CongestionInfo getCongestionInfo()
  {
    return congestionInfo;
  }
  
  public int hashCode()
  {
    CongestionInfo localCongestionInfo = congestionInfo;
    if (localCongestionInfo != null) {
      return localCongestionInfo.hashCode();
    }
    return 0;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("TripDetailMetaInfo(congestionInfo=");
    localStringBuilder.append(congestionInfo);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramParcel, "dest");
    paramParcel.writeParcelable((Parcelable)congestionInfo, 0);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\006"}, d2={"Lcom/byimplication/sakay/model/TripDetailMetaInfo$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/TripDetailMetaInfo;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
  }
}

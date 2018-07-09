package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000<\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\t\n\000\n\002\020\b\n\002\b\r\n\002\020\013\n\000\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\002\n\002\b\004\b\b\030\000  2\0020\001:\001 B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B\031\022\b\020\005\032\004\030\0010\006\022\b\020\007\032\004\030\0010\b¢\006\002\020\tJ\020\020\020\032\004\030\0010\006HÆ\003¢\006\002\020\013J\020\020\021\032\004\030\0010\bHÆ\003¢\006\002\020\016J&\020\022\032\0020\0002\n\b\002\020\005\032\004\030\0010\0062\n\b\002\020\007\032\004\030\0010\bHÆ\001¢\006\002\020\023J\b\020\024\032\0020\bH\026J\023\020\025\032\0020\0262\b\020\027\032\004\030\0010\030HÖ\003J\t\020\031\032\0020\bHÖ\001J\t\020\032\032\0020\033HÖ\001J\030\020\034\032\0020\0352\006\020\036\032\0020\0032\006\020\037\032\0020\bH\026R\025\020\005\032\004\030\0010\006¢\006\n\n\002\020\f\032\004\b\n\020\013R\025\020\007\032\004\030\0010\b¢\006\n\n\002\020\017\032\004\b\r\020\016¨\006!"}, d2={"Lcom/byimplication/sakay/model/CongestionInfo;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "lastUpdated", "", "status", "", "(Ljava/lang/Long;Ljava/lang/Integer;)V", "getLastUpdated", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getStatus", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "copy", "(Ljava/lang/Long;Ljava/lang/Integer;)Lcom/byimplication/sakay/model/CongestionInfo;", "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class CongestionInfo
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<CongestionInfo> CREATOR = (Parcelable.Creator)new CongestionInfo.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  @Nullable
  private final Long lastUpdated;
  @Nullable
  private final Integer status;
  
  public CongestionInfo(@NotNull Parcel paramParcel)
  {
    this((Long)paramParcel.readValue(Long.TYPE.getClassLoader()), (Integer)paramParcel.readValue(Integer.TYPE.getClassLoader()));
  }
  
  public CongestionInfo(@Nullable Long paramLong, @Nullable Integer paramInteger)
  {
    lastUpdated = paramLong;
    status = paramInteger;
  }
  
  @Nullable
  public final Long component1()
  {
    return lastUpdated;
  }
  
  @Nullable
  public final Integer component2()
  {
    return status;
  }
  
  @NotNull
  public final CongestionInfo copy(@Nullable Long paramLong, @Nullable Integer paramInteger)
  {
    return new CongestionInfo(paramLong, paramInteger);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof CongestionInfo))
      {
        paramObject = (CongestionInfo)paramObject;
        if ((Intrinsics.areEqual(lastUpdated, lastUpdated)) && (Intrinsics.areEqual(status, status))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @Nullable
  public final Long getLastUpdated()
  {
    return lastUpdated;
  }
  
  @Nullable
  public final Integer getStatus()
  {
    return status;
  }
  
  public int hashCode()
  {
    Object localObject = lastUpdated;
    int j = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = status;
    if (localObject != null) {
      j = localObject.hashCode();
    }
    return i * 31 + j;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("CongestionInfo(lastUpdated=");
    localStringBuilder.append(lastUpdated);
    localStringBuilder.append(", status=");
    localStringBuilder.append(status);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramParcel, "dest");
    paramParcel.writeValue(lastUpdated);
    paramParcel.writeValue(status);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\006"}, d2={"Lcom/byimplication/sakay/model/CongestionInfo$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/CongestionInfo;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
  }
}

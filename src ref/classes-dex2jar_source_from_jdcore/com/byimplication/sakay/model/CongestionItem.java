package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000<\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\020\006\n\002\b\022\n\002\020\013\n\000\n\002\020\000\n\002\b\003\n\002\020\016\n\000\n\002\020\002\n\002\b\004\b\b\030\000 &2\0020\001:\001&B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B#\022\b\020\005\032\004\030\0010\006\022\b\020\007\032\004\030\0010\b\022\b\020\t\032\004\030\0010\b¢\006\002\020\nJ\020\020\024\032\004\030\0010\006HÆ\003¢\006\002\020\021J\020\020\025\032\004\030\0010\bHÆ\003¢\006\002\020\fJ\020\020\026\032\004\030\0010\bHÆ\003¢\006\002\020\fJ2\020\027\032\0020\0002\n\b\002\020\005\032\004\030\0010\0062\n\b\002\020\007\032\004\030\0010\b2\n\b\002\020\t\032\004\030\0010\bHÆ\001¢\006\002\020\030J\b\020\031\032\0020\006H\026J\023\020\032\032\0020\0332\b\020\034\032\004\030\0010\035HÖ\003J\006\020\036\032\0020\006J\t\020\037\032\0020\006HÖ\001J\t\020 \032\0020!HÖ\001J\030\020\"\032\0020#2\006\020$\032\0020\0032\006\020%\032\0020\006H\026R\036\020\007\032\004\030\0010\bX\016¢\006\020\n\002\020\017\032\004\b\013\020\f\"\004\b\r\020\016R\025\020\005\032\004\030\0010\006¢\006\n\n\002\020\022\032\004\b\020\020\021R\025\020\t\032\004\030\0010\b¢\006\n\n\002\020\017\032\004\b\023\020\f¨\006'"}, d2={"Lcom/byimplication/sakay/model/CongestionItem;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "mod", "", "mean", "", "stDev", "(Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Double;)V", "getMean", "()Ljava/lang/Double;", "setMean", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "getMod", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getStDev", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Double;)Lcom/byimplication/sakay/model/CongestionItem;", "describeContents", "equals", "", "other", "", "getSimplifiedMean", "hashCode", "toString", "", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class CongestionItem
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<CongestionItem> CREATOR = (Parcelable.Creator)new CongestionItem.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  @Nullable
  private Double mean;
  @Nullable
  private final Integer mod;
  @Nullable
  private final Double stDev;
  
  public CongestionItem(@NotNull Parcel paramParcel)
  {
    this((Integer)paramParcel.readValue(Integer.TYPE.getClassLoader()), (Double)paramParcel.readValue(Double.TYPE.getClassLoader()), (Double)paramParcel.readValue(Double.TYPE.getClassLoader()));
  }
  
  public CongestionItem(@Nullable Integer paramInteger, @Nullable Double paramDouble1, @Nullable Double paramDouble2)
  {
    mod = paramInteger;
    mean = paramDouble1;
    stDev = paramDouble2;
  }
  
  @Nullable
  public final Integer component1()
  {
    return mod;
  }
  
  @Nullable
  public final Double component2()
  {
    return mean;
  }
  
  @Nullable
  public final Double component3()
  {
    return stDev;
  }
  
  @NotNull
  public final CongestionItem copy(@Nullable Integer paramInteger, @Nullable Double paramDouble1, @Nullable Double paramDouble2)
  {
    return new CongestionItem(paramInteger, paramDouble1, paramDouble2);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof CongestionItem))
      {
        paramObject = (CongestionItem)paramObject;
        if ((Intrinsics.areEqual(mod, mod)) && (Intrinsics.areEqual(mean, mean)) && (Intrinsics.areEqual(stDev, stDev))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @Nullable
  public final Double getMean()
  {
    return mean;
  }
  
  @Nullable
  public final Integer getMod()
  {
    return mod;
  }
  
  public final int getSimplifiedMean()
  {
    Double localDouble = mean;
    if (localDouble != null)
    {
      double d1 = localDouble.doubleValue();
      double d2 = 4;
      if (d1 < d2) {
        return 0;
      }
      if ((localDouble.doubleValue() >= d2) && (localDouble.doubleValue() < 8)) {
        return 1;
      }
      return 2;
    }
    return -1;
  }
  
  @Nullable
  public final Double getStDev()
  {
    return stDev;
  }
  
  public int hashCode()
  {
    Object localObject = mod;
    int k = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = mean;
    int j;
    if (localObject != null) {
      j = localObject.hashCode();
    } else {
      j = 0;
    }
    localObject = stDev;
    if (localObject != null) {
      k = localObject.hashCode();
    }
    return (i * 31 + j) * 31 + k;
  }
  
  public final void setMean(@Nullable Double paramDouble)
  {
    mean = paramDouble;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("CongestionItem(mod=");
    localStringBuilder.append(mod);
    localStringBuilder.append(", mean=");
    localStringBuilder.append(mean);
    localStringBuilder.append(", stDev=");
    localStringBuilder.append(stDev);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramParcel, "dest");
    paramParcel.writeValue(mod);
    paramParcel.writeValue(mean);
    paramParcel.writeValue(stDev);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\006"}, d2={"Lcom/byimplication/sakay/model/CongestionItem$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/CongestionItem;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
  }
}

package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000<\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\006\n\002\020\b\n\000\n\002\020\013\n\000\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\002\n\002\b\004\b\b\030\000 \0312\0020\001:\001\031B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B\r\022\006\020\005\032\0020\006¢\006\002\020\007J\t\020\n\032\0020\006HÆ\003J\023\020\013\032\0020\0002\b\b\002\020\005\032\0020\006HÆ\001J\b\020\f\032\0020\rH\026J\023\020\016\032\0020\0172\b\020\020\032\004\030\0010\021HÖ\003J\t\020\022\032\0020\rHÖ\001J\t\020\023\032\0020\024HÖ\001J\030\020\025\032\0020\0262\006\020\027\032\0020\0032\006\020\030\032\0020\rH\026R\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\b\020\t¨\006\032"}, d2={"Lcom/byimplication/sakay/model/StopDetails;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "congestion", "Lcom/byimplication/sakay/model/CongestionModel;", "(Lcom/byimplication/sakay/model/CongestionModel;)V", "getCongestion", "()Lcom/byimplication/sakay/model/CongestionModel;", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class StopDetails
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<StopDetails> CREATOR = (Parcelable.Creator)new StopDetails.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  @NotNull
  private final CongestionModel congestion;
  
  public StopDetails(@NotNull Parcel paramParcel)
  {
    this((CongestionModel)paramParcel);
  }
  
  public StopDetails(@NotNull CongestionModel paramCongestionModel)
  {
    congestion = paramCongestionModel;
  }
  
  @NotNull
  public final CongestionModel component1()
  {
    return congestion;
  }
  
  @NotNull
  public final StopDetails copy(@NotNull CongestionModel paramCongestionModel)
  {
    Intrinsics.checkParameterIsNotNull(paramCongestionModel, "congestion");
    return new StopDetails(paramCongestionModel);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof StopDetails))
      {
        paramObject = (StopDetails)paramObject;
        if (Intrinsics.areEqual(congestion, congestion)) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final CongestionModel getCongestion()
  {
    return congestion;
  }
  
  public int hashCode()
  {
    CongestionModel localCongestionModel = congestion;
    if (localCongestionModel != null) {
      return localCongestionModel.hashCode();
    }
    return 0;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("StopDetails(congestion=");
    localStringBuilder.append(congestion);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramParcel, "dest");
    paramParcel.writeParcelable((Parcelable)congestion, 0);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\006"}, d2={"Lcom/byimplication/sakay/model/StopDetails$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/StopDetails;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
  }
}

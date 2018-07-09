package com.byimplication.sakay.store;

import com.byimplication.sakay.model.StopDetails;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000.\n\002\030\002\n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\f\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B\037\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003\022\b\020\005\032\004\030\0010\006¢\006\002\020\007J\n\020\r\032\004\030\0010\001H\026J\t\020\016\032\0020\003HÆ\003J\t\020\017\032\0020\003HÆ\003J\013\020\020\032\004\030\0010\006HÆ\003J)\020\021\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0032\n\b\002\020\005\032\004\030\0010\006HÆ\001J\023\020\022\032\0020\0232\b\020\024\032\004\030\0010\025HÖ\003J\t\020\026\032\0020\027HÖ\001J\t\020\030\032\0020\003HÖ\001R\023\020\005\032\004\030\0010\006¢\006\b\n\000\032\004\b\b\020\tR\021\020\004\032\0020\003¢\006\b\n\000\032\004\b\n\020\013R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\f\020\013¨\006\031"}, d2={"Lcom/byimplication/sakay/store/StopDetailsReceived;", "Lcom/byimplication/sakay/store/StoreData;", "tripId", "", "stopId", "stopDetails", "Lcom/byimplication/sakay/model/StopDetails;", "(Ljava/lang/String;Ljava/lang/String;Lcom/byimplication/sakay/model/StopDetails;)V", "getStopDetails", "()Lcom/byimplication/sakay/model/StopDetails;", "getStopId", "()Ljava/lang/String;", "getTripId", "clone", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class StopDetailsReceived
  implements StoreData
{
  @Nullable
  private final StopDetails stopDetails;
  @NotNull
  private final String stopId;
  @NotNull
  private final String tripId;
  
  public StopDetailsReceived(@NotNull String paramString1, @NotNull String paramString2, @Nullable StopDetails paramStopDetails)
  {
    tripId = paramString1;
    stopId = paramString2;
    stopDetails = paramStopDetails;
  }
  
  @Nullable
  public StoreData clone()
  {
    return (StoreData)copy$default(this, null, null, null, 7, null);
  }
  
  @NotNull
  public final String component1()
  {
    return tripId;
  }
  
  @NotNull
  public final String component2()
  {
    return stopId;
  }
  
  @Nullable
  public final StopDetails component3()
  {
    return stopDetails;
  }
  
  @NotNull
  public final StopDetailsReceived copy(@NotNull String paramString1, @NotNull String paramString2, @Nullable StopDetails paramStopDetails)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "tripId");
    Intrinsics.checkParameterIsNotNull(paramString2, "stopId");
    return new StopDetailsReceived(paramString1, paramString2, paramStopDetails);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof StopDetailsReceived))
      {
        paramObject = (StopDetailsReceived)paramObject;
        if ((Intrinsics.areEqual(tripId, tripId)) && (Intrinsics.areEqual(stopId, stopId)) && (Intrinsics.areEqual(stopDetails, stopDetails))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @Nullable
  public final StopDetails getStopDetails()
  {
    return stopDetails;
  }
  
  @NotNull
  public final String getStopId()
  {
    return stopId;
  }
  
  @NotNull
  public final String getTripId()
  {
    return tripId;
  }
  
  public int hashCode()
  {
    Object localObject = tripId;
    int k = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = stopId;
    int j;
    if (localObject != null) {
      j = localObject.hashCode();
    } else {
      j = 0;
    }
    localObject = stopDetails;
    if (localObject != null) {
      k = localObject.hashCode();
    }
    return (i * 31 + j) * 31 + k;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("StopDetailsReceived(tripId=");
    localStringBuilder.append(tripId);
    localStringBuilder.append(", stopId=");
    localStringBuilder.append(stopId);
    localStringBuilder.append(", stopDetails=");
    localStringBuilder.append(stopDetails);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

package com.byimplication.sakay.action;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000&\n\002\030\002\n\002\030\002\n\000\n\002\020\016\n\002\b\t\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003¢\006\002\020\005J\t\020\t\032\0020\003HÆ\003J\t\020\n\032\0020\003HÆ\003J\035\020\013\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\003HÆ\001J\023\020\f\032\0020\r2\b\020\016\032\004\030\0010\017HÖ\003J\t\020\020\032\0020\021HÖ\001J\t\020\022\032\0020\003HÖ\001R\021\020\004\032\0020\003¢\006\b\n\000\032\004\b\006\020\007R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\b\020\007¨\006\023"}, d2={"Lcom/byimplication/sakay/action/GetStopInfo;", "Lcom/byimplication/sakay/action/Action;", "tripId", "", "stopId", "(Ljava/lang/String;Ljava/lang/String;)V", "getStopId", "()Ljava/lang/String;", "getTripId", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class GetStopInfo
  implements Action
{
  @NotNull
  private final String stopId;
  @NotNull
  private final String tripId;
  
  public GetStopInfo(@NotNull String paramString1, @NotNull String paramString2)
  {
    tripId = paramString1;
    stopId = paramString2;
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
  
  @NotNull
  public final GetStopInfo copy(@NotNull String paramString1, @NotNull String paramString2)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "tripId");
    Intrinsics.checkParameterIsNotNull(paramString2, "stopId");
    return new GetStopInfo(paramString1, paramString2);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof GetStopInfo))
      {
        paramObject = (GetStopInfo)paramObject;
        if ((Intrinsics.areEqual(tripId, tripId)) && (Intrinsics.areEqual(stopId, stopId))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
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
    String str = tripId;
    int j = 0;
    int i;
    if (str != null) {
      i = str.hashCode();
    } else {
      i = 0;
    }
    str = stopId;
    if (str != null) {
      j = str.hashCode();
    }
    return i * 31 + j;
  }
  
  public void post()
  {
    Action.DefaultImpls.post(this);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("GetStopInfo(tripId=");
    localStringBuilder.append(tripId);
    localStringBuilder.append(", stopId=");
    localStringBuilder.append(stopId);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

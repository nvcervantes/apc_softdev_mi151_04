package com.byimplication.sakay.store;

import com.byimplication.sakay.model.ScheduleType;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\n\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B\027\022\b\020\002\032\004\030\0010\003\022\006\020\004\032\0020\005¢\006\002\020\006J\n\020\013\032\004\030\0010\001H\026J\013\020\f\032\004\030\0010\003HÆ\003J\t\020\r\032\0020\005HÆ\003J\037\020\016\032\0020\0002\n\b\002\020\002\032\004\030\0010\0032\b\b\002\020\004\032\0020\005HÆ\001J\023\020\017\032\0020\0202\b\020\021\032\004\030\0010\022HÖ\003J\t\020\023\032\0020\024HÖ\001J\t\020\025\032\0020\026HÖ\001R\023\020\002\032\004\030\0010\003¢\006\b\n\000\032\004\b\007\020\bR\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\t\020\n¨\006\027"}, d2={"Lcom/byimplication/sakay/store/PlanTime;", "Lcom/byimplication/sakay/store/StoreData;", "planTime", "Ljava/util/Date;", "scheduleType", "Lcom/byimplication/sakay/model/ScheduleType;", "(Ljava/util/Date;Lcom/byimplication/sakay/model/ScheduleType;)V", "getPlanTime", "()Ljava/util/Date;", "getScheduleType", "()Lcom/byimplication/sakay/model/ScheduleType;", "clone", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class PlanTime
  implements StoreData
{
  @Nullable
  private final Date planTime;
  @NotNull
  private final ScheduleType scheduleType;
  
  public PlanTime(@Nullable Date paramDate, @NotNull ScheduleType paramScheduleType)
  {
    planTime = paramDate;
    scheduleType = paramScheduleType;
  }
  
  @Nullable
  public StoreData clone()
  {
    return (StoreData)copy$default(this, null, null, 3, null);
  }
  
  @Nullable
  public final Date component1()
  {
    return planTime;
  }
  
  @NotNull
  public final ScheduleType component2()
  {
    return scheduleType;
  }
  
  @NotNull
  public final PlanTime copy(@Nullable Date paramDate, @NotNull ScheduleType paramScheduleType)
  {
    Intrinsics.checkParameterIsNotNull(paramScheduleType, "scheduleType");
    return new PlanTime(paramDate, paramScheduleType);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof PlanTime))
      {
        paramObject = (PlanTime)paramObject;
        if ((Intrinsics.areEqual(planTime, planTime)) && (Intrinsics.areEqual(scheduleType, scheduleType))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @Nullable
  public final Date getPlanTime()
  {
    return planTime;
  }
  
  @NotNull
  public final ScheduleType getScheduleType()
  {
    return scheduleType;
  }
  
  public int hashCode()
  {
    Object localObject = planTime;
    int j = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = scheduleType;
    if (localObject != null) {
      j = localObject.hashCode();
    }
    return i * 31 + j;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PlanTime(planTime=");
    localStringBuilder.append(planTime);
    localStringBuilder.append(", scheduleType=");
    localStringBuilder.append(scheduleType);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

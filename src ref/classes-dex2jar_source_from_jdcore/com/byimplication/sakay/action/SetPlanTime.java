package com.byimplication.sakay.action;

import com.byimplication.sakay.model.ScheduleType;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\017\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B+\022\b\020\002\032\004\030\0010\003\022\006\020\004\032\0020\005\022\b\b\002\020\006\032\0020\007\022\b\b\002\020\b\032\0020\007¢\006\002\020\tJ\013\020\020\032\004\030\0010\003HÆ\003J\t\020\021\032\0020\005HÆ\003J\t\020\022\032\0020\007HÆ\003J\t\020\023\032\0020\007HÆ\003J3\020\024\032\0020\0002\n\b\002\020\002\032\004\030\0010\0032\b\b\002\020\004\032\0020\0052\b\b\002\020\006\032\0020\0072\b\b\002\020\b\032\0020\007HÆ\001J\023\020\025\032\0020\0072\b\020\026\032\004\030\0010\027HÖ\003J\t\020\030\032\0020\031HÖ\001J\t\020\032\032\0020\033HÖ\001R\021\020\006\032\0020\007¢\006\b\n\000\032\004\b\006\020\nR\023\020\002\032\004\030\0010\003¢\006\b\n\000\032\004\b\013\020\fR\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\r\020\016R\021\020\b\032\0020\007¢\006\b\n\000\032\004\b\017\020\n¨\006\034"}, d2={"Lcom/byimplication/sakay/action/SetPlanTime;", "Lcom/byimplication/sakay/action/Action;", "planTime", "Ljava/util/Date;", "scheduleType", "Lcom/byimplication/sakay/model/ScheduleType;", "isSilent", "", "shouldSearch", "(Ljava/util/Date;Lcom/byimplication/sakay/model/ScheduleType;ZZ)V", "()Z", "getPlanTime", "()Ljava/util/Date;", "getScheduleType", "()Lcom/byimplication/sakay/model/ScheduleType;", "getShouldSearch", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class SetPlanTime
  implements Action
{
  private final boolean isSilent;
  @Nullable
  private final Date planTime;
  @NotNull
  private final ScheduleType scheduleType;
  private final boolean shouldSearch;
  
  public SetPlanTime(@Nullable Date paramDate, @NotNull ScheduleType paramScheduleType, boolean paramBoolean1, boolean paramBoolean2)
  {
    planTime = paramDate;
    scheduleType = paramScheduleType;
    isSilent = paramBoolean1;
    shouldSearch = paramBoolean2;
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
  
  public final boolean component3()
  {
    return isSilent;
  }
  
  public final boolean component4()
  {
    return shouldSearch;
  }
  
  @NotNull
  public final SetPlanTime copy(@Nullable Date paramDate, @NotNull ScheduleType paramScheduleType, boolean paramBoolean1, boolean paramBoolean2)
  {
    Intrinsics.checkParameterIsNotNull(paramScheduleType, "scheduleType");
    return new SetPlanTime(paramDate, paramScheduleType, paramBoolean1, paramBoolean2);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof SetPlanTime))
      {
        paramObject = (SetPlanTime)paramObject;
        if ((Intrinsics.areEqual(planTime, planTime)) && (Intrinsics.areEqual(scheduleType, scheduleType)))
        {
          int i;
          if (isSilent == isSilent) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0)
          {
            if (shouldSearch == shouldSearch) {
              i = 1;
            } else {
              i = 0;
            }
            if (i != 0) {
              return true;
            }
          }
        }
      }
      return false;
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
  
  public final boolean getShouldSearch()
  {
    return shouldSearch;
  }
  
  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public final boolean isSilent()
  {
    return isSilent;
  }
  
  public void post()
  {
    Action.DefaultImpls.post(this);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SetPlanTime(planTime=");
    localStringBuilder.append(planTime);
    localStringBuilder.append(", scheduleType=");
    localStringBuilder.append(scheduleType);
    localStringBuilder.append(", isSilent=");
    localStringBuilder.append(isSilent);
    localStringBuilder.append(", shouldSearch=");
    localStringBuilder.append(shouldSearch);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

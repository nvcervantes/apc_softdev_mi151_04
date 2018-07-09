package com.byimplication.sakay.store;

import com.byimplication.sakay.model.Plan;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0006\n\002\030\002\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\000\n\002\020\013\n\000\n\002\030\002\n\002\b\027\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B=\022\b\020\002\032\004\030\0010\003\022\b\020\004\032\004\030\0010\005\022\006\020\006\032\0020\007\022\006\020\b\032\0020\t\022\b\b\002\020\n\032\0020\007\022\b\b\002\020\013\032\0020\007¢\006\002\020\fJ\n\020\026\032\004\030\0010\001H\026J\020\020\027\032\004\030\0010\003HÆ\003¢\006\002\020\024J\013\020\030\032\004\030\0010\005HÆ\003J\t\020\031\032\0020\007HÆ\003J\t\020\032\032\0020\tHÆ\003J\t\020\033\032\0020\007HÆ\003J\t\020\034\032\0020\007HÆ\003JN\020\035\032\0020\0002\n\b\002\020\002\032\004\030\0010\0032\n\b\002\020\004\032\004\030\0010\0052\b\b\002\020\006\032\0020\0072\b\b\002\020\b\032\0020\t2\b\b\002\020\n\032\0020\0072\b\b\002\020\013\032\0020\007HÆ\001¢\006\002\020\036J\023\020\037\032\0020\0072\b\020 \032\004\030\0010!HÖ\003J\t\020\"\032\0020#HÖ\001J\t\020$\032\0020%HÖ\001R\021\020\b\032\0020\t¢\006\b\n\000\032\004\b\r\020\016R\021\020\006\032\0020\007¢\006\b\n\000\032\004\b\017\020\020R\021\020\013\032\0020\007¢\006\b\n\000\032\004\b\013\020\020R\021\020\n\032\0020\007¢\006\b\n\000\032\004\b\n\020\020R\023\020\004\032\004\030\0010\005¢\006\b\n\000\032\004\b\021\020\022R\025\020\002\032\004\030\0010\003¢\006\n\n\002\020\025\032\004\b\023\020\024¨\006&"}, d2={"Lcom/byimplication/sakay/store/PlanUpdated;", "Lcom/byimplication/sakay/store/StoreData;", "searchId", "", "plan", "Lcom/byimplication/sakay/model/Plan;", "failure", "", "dateUsed", "Ljava/util/Date;", "isStored", "isRecall", "(Ljava/lang/Long;Lcom/byimplication/sakay/model/Plan;ZLjava/util/Date;ZZ)V", "getDateUsed", "()Ljava/util/Date;", "getFailure", "()Z", "getPlan", "()Lcom/byimplication/sakay/model/Plan;", "getSearchId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "clone", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/Long;Lcom/byimplication/sakay/model/Plan;ZLjava/util/Date;ZZ)Lcom/byimplication/sakay/store/PlanUpdated;", "equals", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class PlanUpdated
  implements StoreData
{
  @NotNull
  private final Date dateUsed;
  private final boolean failure;
  private final boolean isRecall;
  private final boolean isStored;
  @Nullable
  private final Plan plan;
  @Nullable
  private final Long searchId;
  
  public PlanUpdated(@Nullable Long paramLong, @Nullable Plan paramPlan, boolean paramBoolean1, @NotNull Date paramDate, boolean paramBoolean2, boolean paramBoolean3)
  {
    searchId = paramLong;
    plan = paramPlan;
    failure = paramBoolean1;
    dateUsed = paramDate;
    isStored = paramBoolean2;
    isRecall = paramBoolean3;
  }
  
  @Nullable
  public StoreData clone()
  {
    return (StoreData)copy$default(this, null, null, false, null, false, false, 63, null);
  }
  
  @Nullable
  public final Long component1()
  {
    return searchId;
  }
  
  @Nullable
  public final Plan component2()
  {
    return plan;
  }
  
  public final boolean component3()
  {
    return failure;
  }
  
  @NotNull
  public final Date component4()
  {
    return dateUsed;
  }
  
  public final boolean component5()
  {
    return isStored;
  }
  
  public final boolean component6()
  {
    return isRecall;
  }
  
  @NotNull
  public final PlanUpdated copy(@Nullable Long paramLong, @Nullable Plan paramPlan, boolean paramBoolean1, @NotNull Date paramDate, boolean paramBoolean2, boolean paramBoolean3)
  {
    Intrinsics.checkParameterIsNotNull(paramDate, "dateUsed");
    return new PlanUpdated(paramLong, paramPlan, paramBoolean1, paramDate, paramBoolean2, paramBoolean3);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof PlanUpdated))
      {
        paramObject = (PlanUpdated)paramObject;
        if ((Intrinsics.areEqual(searchId, searchId)) && (Intrinsics.areEqual(plan, plan)))
        {
          int i;
          if (failure == failure) {
            i = 1;
          } else {
            i = 0;
          }
          if ((i != 0) && (Intrinsics.areEqual(dateUsed, dateUsed)))
          {
            if (isStored == isStored) {
              i = 1;
            } else {
              i = 0;
            }
            if (i != 0)
            {
              if (isRecall == isRecall) {
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
      }
      return false;
    }
    return true;
  }
  
  @NotNull
  public final Date getDateUsed()
  {
    return dateUsed;
  }
  
  public final boolean getFailure()
  {
    return failure;
  }
  
  @Nullable
  public final Plan getPlan()
  {
    return plan;
  }
  
  @Nullable
  public final Long getSearchId()
  {
    return searchId;
  }
  
  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public final boolean isRecall()
  {
    return isRecall;
  }
  
  public final boolean isStored()
  {
    return isStored;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PlanUpdated(searchId=");
    localStringBuilder.append(searchId);
    localStringBuilder.append(", plan=");
    localStringBuilder.append(plan);
    localStringBuilder.append(", failure=");
    localStringBuilder.append(failure);
    localStringBuilder.append(", dateUsed=");
    localStringBuilder.append(dateUsed);
    localStringBuilder.append(", isStored=");
    localStringBuilder.append(isStored);
    localStringBuilder.append(", isRecall=");
    localStringBuilder.append(isRecall);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

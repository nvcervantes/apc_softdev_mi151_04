package com.byimplication.sakay.store;

import com.byimplication.sakay.model.PlanError;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\016\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B'\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\005\022\b\b\002\020\007\032\0020\005¢\006\002\020\bJ\n\020\f\032\004\030\0010\001H\026J\t\020\r\032\0020\003HÆ\003J\t\020\016\032\0020\005HÆ\003J\t\020\017\032\0020\005HÆ\003J\t\020\020\032\0020\005HÆ\003J1\020\021\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0052\b\b\002\020\006\032\0020\0052\b\b\002\020\007\032\0020\005HÆ\001J\023\020\022\032\0020\0052\b\020\023\032\004\030\0010\024HÖ\003J\t\020\025\032\0020\026HÖ\001J\t\020\027\032\0020\030HÖ\001R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\t\020\nR\021\020\006\032\0020\005¢\006\b\n\000\032\004\b\006\020\013R\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\004\020\013R\021\020\007\032\0020\005¢\006\b\n\000\032\004\b\007\020\013¨\006\031"}, d2={"Lcom/byimplication/sakay/store/PlanRequestError;", "Lcom/byimplication/sakay/store/StoreData;", "error", "Lcom/byimplication/sakay/model/PlanError;", "isOriginOutOfBounds", "", "isDestinationOutOfBounds", "isRecall", "(Lcom/byimplication/sakay/model/PlanError;ZZZ)V", "getError", "()Lcom/byimplication/sakay/model/PlanError;", "()Z", "clone", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class PlanRequestError
  implements StoreData
{
  @NotNull
  private final PlanError error;
  private final boolean isDestinationOutOfBounds;
  private final boolean isOriginOutOfBounds;
  private final boolean isRecall;
  
  public PlanRequestError(@NotNull PlanError paramPlanError, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    error = paramPlanError;
    isOriginOutOfBounds = paramBoolean1;
    isDestinationOutOfBounds = paramBoolean2;
    isRecall = paramBoolean3;
  }
  
  @Nullable
  public StoreData clone()
  {
    return (StoreData)copy$default(this, null, false, false, false, 15, null);
  }
  
  @NotNull
  public final PlanError component1()
  {
    return error;
  }
  
  public final boolean component2()
  {
    return isOriginOutOfBounds;
  }
  
  public final boolean component3()
  {
    return isDestinationOutOfBounds;
  }
  
  public final boolean component4()
  {
    return isRecall;
  }
  
  @NotNull
  public final PlanRequestError copy(@NotNull PlanError paramPlanError, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    Intrinsics.checkParameterIsNotNull(paramPlanError, "error");
    return new PlanRequestError(paramPlanError, paramBoolean1, paramBoolean2, paramBoolean3);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof PlanRequestError))
      {
        paramObject = (PlanRequestError)paramObject;
        if (Intrinsics.areEqual(error, error))
        {
          int i;
          if (isOriginOutOfBounds == isOriginOutOfBounds) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0)
          {
            if (isDestinationOutOfBounds == isDestinationOutOfBounds) {
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
  public final PlanError getError()
  {
    return error;
  }
  
  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public final boolean isDestinationOutOfBounds()
  {
    return isDestinationOutOfBounds;
  }
  
  public final boolean isOriginOutOfBounds()
  {
    return isOriginOutOfBounds;
  }
  
  public final boolean isRecall()
  {
    return isRecall;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PlanRequestError(error=");
    localStringBuilder.append(error);
    localStringBuilder.append(", isOriginOutOfBounds=");
    localStringBuilder.append(isOriginOutOfBounds);
    localStringBuilder.append(", isDestinationOutOfBounds=");
    localStringBuilder.append(isDestinationOutOfBounds);
    localStringBuilder.append(", isRecall=");
    localStringBuilder.append(isRecall);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

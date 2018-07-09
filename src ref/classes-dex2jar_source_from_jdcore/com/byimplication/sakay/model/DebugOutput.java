package com.byimplication.sakay.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000(\n\002\030\002\n\002\020\000\n\000\n\002\020\b\n\002\b\002\n\002\020 \n\002\b\003\n\002\020\013\n\002\b\025\n\002\020\016\n\000\b\b\030\0002\0020\001B;\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003\022\f\020\005\032\b\022\004\022\0020\0030\006\022\006\020\007\032\0020\003\022\006\020\b\032\0020\003\022\006\020\t\032\0020\n¢\006\002\020\013J\t\020\025\032\0020\003HÆ\003J\t\020\026\032\0020\003HÆ\003J\017\020\027\032\b\022\004\022\0020\0030\006HÆ\003J\t\020\030\032\0020\003HÆ\003J\t\020\031\032\0020\003HÆ\003J\t\020\032\032\0020\nHÆ\003JK\020\033\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0032\016\b\002\020\005\032\b\022\004\022\0020\0030\0062\b\b\002\020\007\032\0020\0032\b\b\002\020\b\032\0020\0032\b\b\002\020\t\032\0020\nHÆ\001J\023\020\034\032\0020\n2\b\020\035\032\004\030\0010\001HÖ\003J\t\020\036\032\0020\003HÖ\001J\t\020\037\032\0020 HÖ\001R\021\020\004\032\0020\003¢\006\b\n\000\032\004\b\f\020\rR\027\020\005\032\b\022\004\022\0020\0030\006¢\006\b\n\000\032\004\b\016\020\017R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\020\020\rR\021\020\007\032\0020\003¢\006\b\n\000\032\004\b\021\020\rR\021\020\t\032\0020\n¢\006\b\n\000\032\004\b\022\020\023R\021\020\b\032\0020\003¢\006\b\n\000\032\004\b\024\020\r¨\006!"}, d2={"Lcom/byimplication/sakay/model/DebugOutput;", "", "precalculationTime", "", "pathCalculationTime", "pathTimes", "", "renderingTime", "totalTime", "timedOut", "", "(IILjava/util/List;IIZ)V", "getPathCalculationTime", "()I", "getPathTimes", "()Ljava/util/List;", "getPrecalculationTime", "getRenderingTime", "getTimedOut", "()Z", "getTotalTime", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class DebugOutput
{
  private final int pathCalculationTime;
  @NotNull
  private final List<Integer> pathTimes;
  private final int precalculationTime;
  private final int renderingTime;
  private final boolean timedOut;
  private final int totalTime;
  
  public DebugOutput(int paramInt1, int paramInt2, @NotNull List<Integer> paramList, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    precalculationTime = paramInt1;
    pathCalculationTime = paramInt2;
    pathTimes = paramList;
    renderingTime = paramInt3;
    totalTime = paramInt4;
    timedOut = paramBoolean;
  }
  
  public final int component1()
  {
    return precalculationTime;
  }
  
  public final int component2()
  {
    return pathCalculationTime;
  }
  
  @NotNull
  public final List<Integer> component3()
  {
    return pathTimes;
  }
  
  public final int component4()
  {
    return renderingTime;
  }
  
  public final int component5()
  {
    return totalTime;
  }
  
  public final boolean component6()
  {
    return timedOut;
  }
  
  @NotNull
  public final DebugOutput copy(int paramInt1, int paramInt2, @NotNull List<Integer> paramList, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "pathTimes");
    return new DebugOutput(paramInt1, paramInt2, paramList, paramInt3, paramInt4, paramBoolean);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof DebugOutput))
      {
        paramObject = (DebugOutput)paramObject;
        int i;
        if (precalculationTime == precalculationTime) {
          i = 1;
        } else {
          i = 0;
        }
        if (i != 0)
        {
          if (pathCalculationTime == pathCalculationTime) {
            i = 1;
          } else {
            i = 0;
          }
          if ((i != 0) && (Intrinsics.areEqual(pathTimes, pathTimes)))
          {
            if (renderingTime == renderingTime) {
              i = 1;
            } else {
              i = 0;
            }
            if (i != 0)
            {
              if (totalTime == totalTime) {
                i = 1;
              } else {
                i = 0;
              }
              if (i != 0)
              {
                if (timedOut == timedOut) {
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
      }
      return false;
    }
    return true;
  }
  
  public final int getPathCalculationTime()
  {
    return pathCalculationTime;
  }
  
  @NotNull
  public final List<Integer> getPathTimes()
  {
    return pathTimes;
  }
  
  public final int getPrecalculationTime()
  {
    return precalculationTime;
  }
  
  public final int getRenderingTime()
  {
    return renderingTime;
  }
  
  public final boolean getTimedOut()
  {
    return timedOut;
  }
  
  public final int getTotalTime()
  {
    return totalTime;
  }
  
  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("DebugOutput(precalculationTime=");
    localStringBuilder.append(precalculationTime);
    localStringBuilder.append(", pathCalculationTime=");
    localStringBuilder.append(pathCalculationTime);
    localStringBuilder.append(", pathTimes=");
    localStringBuilder.append(pathTimes);
    localStringBuilder.append(", renderingTime=");
    localStringBuilder.append(renderingTime);
    localStringBuilder.append(", totalTime=");
    localStringBuilder.append(totalTime);
    localStringBuilder.append(", timedOut=");
    localStringBuilder.append(timedOut);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

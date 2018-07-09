package com.byimplication.sakay;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000,\n\002\030\002\n\002\020\000\n\000\n\002\020\b\n\002\b\003\n\002\020 \n\002\030\002\n\002\b\r\n\002\020\013\n\002\b\003\n\002\020\016\n\000\b\b\030\0002\0020\001B+\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003\022\006\020\005\032\0020\003\022\f\020\006\032\b\022\004\022\0020\b0\007¢\006\002\020\tJ\t\020\020\032\0020\003HÆ\003J\t\020\021\032\0020\003HÆ\003J\t\020\022\032\0020\003HÆ\003J\017\020\023\032\b\022\004\022\0020\b0\007HÆ\003J7\020\024\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0032\b\b\002\020\005\032\0020\0032\016\b\002\020\006\032\b\022\004\022\0020\b0\007HÆ\001J\023\020\025\032\0020\0262\b\020\027\032\004\030\0010\001HÖ\003J\t\020\030\032\0020\003HÖ\001J\t\020\031\032\0020\032HÖ\001R\021\020\005\032\0020\003¢\006\b\n\000\032\004\b\n\020\013R\027\020\006\032\b\022\004\022\0020\b0\007¢\006\b\n\000\032\004\b\f\020\rR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\016\020\013R\021\020\004\032\0020\003¢\006\b\n\000\032\004\b\017\020\013¨\006\033"}, d2={"Lcom/byimplication/sakay/ModeGuide;", "", "modeNameRes", "", "picRes", "descRes", "instructions", "", "Lcom/byimplication/sakay/GuideInstruction;", "(IIILjava/util/List;)V", "getDescRes", "()I", "getInstructions", "()Ljava/util/List;", "getModeNameRes", "getPicRes", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class ModeGuide
{
  private final int descRes;
  @NotNull
  private final List<GuideInstruction> instructions;
  private final int modeNameRes;
  private final int picRes;
  
  public ModeGuide(int paramInt1, int paramInt2, int paramInt3, @NotNull List<GuideInstruction> paramList)
  {
    modeNameRes = paramInt1;
    picRes = paramInt2;
    descRes = paramInt3;
    instructions = paramList;
  }
  
  public final int component1()
  {
    return modeNameRes;
  }
  
  public final int component2()
  {
    return picRes;
  }
  
  public final int component3()
  {
    return descRes;
  }
  
  @NotNull
  public final List<GuideInstruction> component4()
  {
    return instructions;
  }
  
  @NotNull
  public final ModeGuide copy(int paramInt1, int paramInt2, int paramInt3, @NotNull List<GuideInstruction> paramList)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "instructions");
    return new ModeGuide(paramInt1, paramInt2, paramInt3, paramList);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof ModeGuide))
      {
        paramObject = (ModeGuide)paramObject;
        int i;
        if (modeNameRes == modeNameRes) {
          i = 1;
        } else {
          i = 0;
        }
        if (i != 0)
        {
          if (picRes == picRes) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0)
          {
            if (descRes == descRes) {
              i = 1;
            } else {
              i = 0;
            }
            if ((i != 0) && (Intrinsics.areEqual(instructions, instructions))) {
              return true;
            }
          }
        }
      }
      return false;
    }
    return true;
  }
  
  public final int getDescRes()
  {
    return descRes;
  }
  
  @NotNull
  public final List<GuideInstruction> getInstructions()
  {
    return instructions;
  }
  
  public final int getModeNameRes()
  {
    return modeNameRes;
  }
  
  public final int getPicRes()
  {
    return picRes;
  }
  
  public int hashCode()
  {
    int j = modeNameRes;
    int k = picRes;
    int m = descRes;
    List localList = instructions;
    int i;
    if (localList != null) {
      i = localList.hashCode();
    } else {
      i = 0;
    }
    return ((j * 31 + k) * 31 + m) * 31 + i;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("ModeGuide(modeNameRes=");
    localStringBuilder.append(modeNameRes);
    localStringBuilder.append(", picRes=");
    localStringBuilder.append(picRes);
    localStringBuilder.append(", descRes=");
    localStringBuilder.append(descRes);
    localStringBuilder.append(", instructions=");
    localStringBuilder.append(instructions);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

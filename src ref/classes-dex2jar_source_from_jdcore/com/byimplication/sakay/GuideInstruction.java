package com.byimplication.sakay;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000 \n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\000\n\002\020\b\n\002\b\f\n\002\020\013\n\002\b\004\b\b\030\0002\0020\001B\035\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\005¢\006\002\020\007J\t\020\r\032\0020\003HÆ\003J\t\020\016\032\0020\005HÆ\003J\t\020\017\032\0020\005HÆ\003J'\020\020\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0052\b\b\002\020\006\032\0020\005HÆ\001J\023\020\021\032\0020\0222\b\020\023\032\004\030\0010\001HÖ\003J\t\020\024\032\0020\005HÖ\001J\t\020\025\032\0020\003HÖ\001R\021\020\006\032\0020\005¢\006\b\n\000\032\004\b\b\020\tR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\n\020\013R\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\f\020\t¨\006\026"}, d2={"Lcom/byimplication/sakay/GuideInstruction;", "", "label", "", "picRes", "", "desc", "(Ljava/lang/String;II)V", "getDesc", "()I", "getLabel", "()Ljava/lang/String;", "getPicRes", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class GuideInstruction
{
  private final int desc;
  @NotNull
  private final String label;
  private final int picRes;
  
  public GuideInstruction(@NotNull String paramString, int paramInt1, int paramInt2)
  {
    label = paramString;
    picRes = paramInt1;
    desc = paramInt2;
  }
  
  @NotNull
  public final String component1()
  {
    return label;
  }
  
  public final int component2()
  {
    return picRes;
  }
  
  public final int component3()
  {
    return desc;
  }
  
  @NotNull
  public final GuideInstruction copy(@NotNull String paramString, int paramInt1, int paramInt2)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "label");
    return new GuideInstruction(paramString, paramInt1, paramInt2);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof GuideInstruction))
      {
        paramObject = (GuideInstruction)paramObject;
        if (Intrinsics.areEqual(label, label))
        {
          int i;
          if (picRes == picRes) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0)
          {
            if (desc == desc) {
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
  
  public final int getDesc()
  {
    return desc;
  }
  
  @NotNull
  public final String getLabel()
  {
    return label;
  }
  
  public final int getPicRes()
  {
    return picRes;
  }
  
  public int hashCode()
  {
    String str = label;
    int i;
    if (str != null) {
      i = str.hashCode();
    } else {
      i = 0;
    }
    return (i * 31 + picRes) * 31 + desc;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("GuideInstruction(label=");
    localStringBuilder.append(label);
    localStringBuilder.append(", picRes=");
    localStringBuilder.append(picRes);
    localStringBuilder.append(", desc=");
    localStringBuilder.append(desc);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

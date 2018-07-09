package com.byimplication.sakay.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000(\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\000\n\002\020\t\n\002\b\017\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B%\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\003\022\006\020\007\032\0020\003¢\006\002\020\bJ\t\020\017\032\0020\003HÆ\003J\t\020\020\032\0020\005HÆ\003J\t\020\021\032\0020\003HÆ\003J\t\020\022\032\0020\003HÆ\003J1\020\023\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0052\b\b\002\020\006\032\0020\0032\b\b\002\020\007\032\0020\003HÆ\001J\023\020\024\032\0020\0252\b\020\026\032\004\030\0010\001HÖ\003J\t\020\027\032\0020\030HÖ\001J\t\020\031\032\0020\003HÖ\001R\021\020\006\032\0020\003¢\006\b\n\000\032\004\b\t\020\nR\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\013\020\fR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\r\020\nR\021\020\007\032\0020\003¢\006\b\n\000\032\004\b\016\020\n¨\006\032"}, d2={"Lcom/byimplication/sakay/model/UberTime;", "", "localized_display_name", "", "estimate", "", "display_name", "product_id", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;)V", "getDisplay_name", "()Ljava/lang/String;", "getEstimate", "()J", "getLocalized_display_name", "getProduct_id", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class UberTime
{
  @NotNull
  private final String display_name;
  private final long estimate;
  @NotNull
  private final String localized_display_name;
  @NotNull
  private final String product_id;
  
  public UberTime(@NotNull String paramString1, long paramLong, @NotNull String paramString2, @NotNull String paramString3)
  {
    localized_display_name = paramString1;
    estimate = paramLong;
    display_name = paramString2;
    product_id = paramString3;
  }
  
  @NotNull
  public final String component1()
  {
    return localized_display_name;
  }
  
  public final long component2()
  {
    return estimate;
  }
  
  @NotNull
  public final String component3()
  {
    return display_name;
  }
  
  @NotNull
  public final String component4()
  {
    return product_id;
  }
  
  @NotNull
  public final UberTime copy(@NotNull String paramString1, long paramLong, @NotNull String paramString2, @NotNull String paramString3)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "localized_display_name");
    Intrinsics.checkParameterIsNotNull(paramString2, "display_name");
    Intrinsics.checkParameterIsNotNull(paramString3, "product_id");
    return new UberTime(paramString1, paramLong, paramString2, paramString3);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof UberTime))
      {
        paramObject = (UberTime)paramObject;
        if (Intrinsics.areEqual(localized_display_name, localized_display_name))
        {
          int i;
          if (estimate == estimate) {
            i = 1;
          } else {
            i = 0;
          }
          if ((i != 0) && (Intrinsics.areEqual(display_name, display_name)) && (Intrinsics.areEqual(product_id, product_id))) {
            return true;
          }
        }
      }
      return false;
    }
    return true;
  }
  
  @NotNull
  public final String getDisplay_name()
  {
    return display_name;
  }
  
  public final long getEstimate()
  {
    return estimate;
  }
  
  @NotNull
  public final String getLocalized_display_name()
  {
    return localized_display_name;
  }
  
  @NotNull
  public final String getProduct_id()
  {
    return product_id;
  }
  
  public int hashCode()
  {
    String str = localized_display_name;
    int k = 0;
    int i;
    if (str != null) {
      i = str.hashCode();
    } else {
      i = 0;
    }
    long l = estimate;
    int m = (int)(l ^ l >>> 32);
    str = display_name;
    int j;
    if (str != null) {
      j = str.hashCode();
    } else {
      j = 0;
    }
    str = product_id;
    if (str != null) {
      k = str.hashCode();
    }
    return ((i * 31 + m) * 31 + j) * 31 + k;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("UberTime(localized_display_name=");
    localStringBuilder.append(localized_display_name);
    localStringBuilder.append(", estimate=");
    localStringBuilder.append(estimate);
    localStringBuilder.append(", display_name=");
    localStringBuilder.append(display_name);
    localStringBuilder.append(", product_id=");
    localStringBuilder.append(product_id);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

package com.byimplication.sakay.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000(\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\000\n\002\020\006\n\002\b\003\n\002\020\b\n\002\b\034\n\002\020\013\n\002\b\004\b\b\030\0002\0020\001BM\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\003\022\006\020\007\032\0020\003\022\006\020\b\032\0020\t\022\006\020\n\032\0020\t\022\006\020\013\032\0020\t\022\006\020\f\032\0020\003\022\006\020\r\032\0020\003¢\006\002\020\016J\t\020\033\032\0020\003HÆ\003J\t\020\034\032\0020\005HÆ\003J\t\020\035\032\0020\003HÆ\003J\t\020\036\032\0020\003HÆ\003J\t\020\037\032\0020\tHÆ\003J\t\020 \032\0020\tHÆ\003J\t\020!\032\0020\tHÆ\003J\t\020\"\032\0020\003HÆ\003J\t\020#\032\0020\003HÆ\003Jc\020$\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0052\b\b\002\020\006\032\0020\0032\b\b\002\020\007\032\0020\0032\b\b\002\020\b\032\0020\t2\b\b\002\020\n\032\0020\t2\b\b\002\020\013\032\0020\t2\b\b\002\020\f\032\0020\0032\b\b\002\020\r\032\0020\003HÆ\001J\023\020%\032\0020&2\b\020'\032\004\030\0010\001HÖ\003J\t\020(\032\0020\tHÖ\001J\t\020)\032\0020\003HÖ\001R\021\020\r\032\0020\003¢\006\b\n\000\032\004\b\017\020\020R\021\020\006\032\0020\003¢\006\b\n\000\032\004\b\021\020\020R\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\022\020\023R\021\020\013\032\0020\t¢\006\b\n\000\032\004\b\024\020\025R\021\020\f\032\0020\003¢\006\b\n\000\032\004\b\026\020\020R\021\020\b\032\0020\t¢\006\b\n\000\032\004\b\027\020\025R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\030\020\020R\021\020\n\032\0020\t¢\006\b\n\000\032\004\b\031\020\025R\021\020\007\032\0020\003¢\006\b\n\000\032\004\b\032\020\020¨\006*"}, d2={"Lcom/byimplication/sakay/model/UberPrice;", "", "localized_display_name", "", "distance", "", "display_name", "product_id", "high_estimate", "", "low_estimate", "duration", "estimate", "currency_code", "(Ljava/lang/String;DLjava/lang/String;Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;)V", "getCurrency_code", "()Ljava/lang/String;", "getDisplay_name", "getDistance", "()D", "getDuration", "()I", "getEstimate", "getHigh_estimate", "getLocalized_display_name", "getLow_estimate", "getProduct_id", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class UberPrice
{
  @NotNull
  private final String currency_code;
  @NotNull
  private final String display_name;
  private final double distance;
  private final int duration;
  @NotNull
  private final String estimate;
  private final int high_estimate;
  @NotNull
  private final String localized_display_name;
  private final int low_estimate;
  @NotNull
  private final String product_id;
  
  public UberPrice(@NotNull String paramString1, double paramDouble, @NotNull String paramString2, @NotNull String paramString3, int paramInt1, int paramInt2, int paramInt3, @NotNull String paramString4, @NotNull String paramString5)
  {
    localized_display_name = paramString1;
    distance = paramDouble;
    display_name = paramString2;
    product_id = paramString3;
    high_estimate = paramInt1;
    low_estimate = paramInt2;
    duration = paramInt3;
    estimate = paramString4;
    currency_code = paramString5;
  }
  
  @NotNull
  public final String component1()
  {
    return localized_display_name;
  }
  
  public final double component2()
  {
    return distance;
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
  
  public final int component5()
  {
    return high_estimate;
  }
  
  public final int component6()
  {
    return low_estimate;
  }
  
  public final int component7()
  {
    return duration;
  }
  
  @NotNull
  public final String component8()
  {
    return estimate;
  }
  
  @NotNull
  public final String component9()
  {
    return currency_code;
  }
  
  @NotNull
  public final UberPrice copy(@NotNull String paramString1, double paramDouble, @NotNull String paramString2, @NotNull String paramString3, int paramInt1, int paramInt2, int paramInt3, @NotNull String paramString4, @NotNull String paramString5)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "localized_display_name");
    Intrinsics.checkParameterIsNotNull(paramString2, "display_name");
    Intrinsics.checkParameterIsNotNull(paramString3, "product_id");
    Intrinsics.checkParameterIsNotNull(paramString4, "estimate");
    Intrinsics.checkParameterIsNotNull(paramString5, "currency_code");
    return new UberPrice(paramString1, paramDouble, paramString2, paramString3, paramInt1, paramInt2, paramInt3, paramString4, paramString5);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof UberPrice))
      {
        paramObject = (UberPrice)paramObject;
        if ((Intrinsics.areEqual(localized_display_name, localized_display_name)) && (Double.compare(distance, distance) == 0) && (Intrinsics.areEqual(display_name, display_name)) && (Intrinsics.areEqual(product_id, product_id)))
        {
          int i;
          if (high_estimate == high_estimate) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0)
          {
            if (low_estimate == low_estimate) {
              i = 1;
            } else {
              i = 0;
            }
            if (i != 0)
            {
              if (duration == duration) {
                i = 1;
              } else {
                i = 0;
              }
              if ((i != 0) && (Intrinsics.areEqual(estimate, estimate)) && (Intrinsics.areEqual(currency_code, currency_code))) {
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
  public final String getCurrency_code()
  {
    return currency_code;
  }
  
  @NotNull
  public final String getDisplay_name()
  {
    return display_name;
  }
  
  public final double getDistance()
  {
    return distance;
  }
  
  public final int getDuration()
  {
    return duration;
  }
  
  @NotNull
  public final String getEstimate()
  {
    return estimate;
  }
  
  public final int getHigh_estimate()
  {
    return high_estimate;
  }
  
  @NotNull
  public final String getLocalized_display_name()
  {
    return localized_display_name;
  }
  
  public final int getLow_estimate()
  {
    return low_estimate;
  }
  
  @NotNull
  public final String getProduct_id()
  {
    return product_id;
  }
  
  public int hashCode()
  {
    String str = localized_display_name;
    int n = 0;
    int i;
    if (str != null) {
      i = str.hashCode();
    } else {
      i = 0;
    }
    long l = Double.doubleToLongBits(distance);
    int i1 = (int)(l ^ l >>> 32);
    str = display_name;
    int j;
    if (str != null) {
      j = str.hashCode();
    } else {
      j = 0;
    }
    str = product_id;
    int k;
    if (str != null) {
      k = str.hashCode();
    } else {
      k = 0;
    }
    int i2 = high_estimate;
    int i3 = low_estimate;
    int i4 = duration;
    str = estimate;
    int m;
    if (str != null) {
      m = str.hashCode();
    } else {
      m = 0;
    }
    str = currency_code;
    if (str != null) {
      n = str.hashCode();
    }
    return (((((((i * 31 + i1) * 31 + j) * 31 + k) * 31 + i2) * 31 + i3) * 31 + i4) * 31 + m) * 31 + n;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("UberPrice(localized_display_name=");
    localStringBuilder.append(localized_display_name);
    localStringBuilder.append(", distance=");
    localStringBuilder.append(distance);
    localStringBuilder.append(", display_name=");
    localStringBuilder.append(display_name);
    localStringBuilder.append(", product_id=");
    localStringBuilder.append(product_id);
    localStringBuilder.append(", high_estimate=");
    localStringBuilder.append(high_estimate);
    localStringBuilder.append(", low_estimate=");
    localStringBuilder.append(low_estimate);
    localStringBuilder.append(", duration=");
    localStringBuilder.append(duration);
    localStringBuilder.append(", estimate=");
    localStringBuilder.append(estimate);
    localStringBuilder.append(", currency_code=");
    localStringBuilder.append(currency_code);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

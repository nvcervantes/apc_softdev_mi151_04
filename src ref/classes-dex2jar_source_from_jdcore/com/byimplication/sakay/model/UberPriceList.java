package com.byimplication.sakay.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\002\020\000\n\000\n\002\020 \n\002\030\002\n\002\b\006\n\002\020\013\n\002\b\002\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B\023\022\f\020\002\032\b\022\004\022\0020\0040\003¢\006\002\020\005J\017\020\b\032\b\022\004\022\0020\0040\003HÆ\003J\031\020\t\032\0020\0002\016\b\002\020\002\032\b\022\004\022\0020\0040\003HÆ\001J\023\020\n\032\0020\0132\b\020\f\032\004\030\0010\001HÖ\003J\t\020\r\032\0020\016HÖ\001J\t\020\017\032\0020\020HÖ\001R\027\020\002\032\b\022\004\022\0020\0040\003¢\006\b\n\000\032\004\b\006\020\007¨\006\021"}, d2={"Lcom/byimplication/sakay/model/UberPriceList;", "", "prices", "", "Lcom/byimplication/sakay/model/UberPrice;", "(Ljava/util/List;)V", "getPrices", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class UberPriceList
{
  @NotNull
  private final List<UberPrice> prices;
  
  public UberPriceList(@NotNull List<UberPrice> paramList)
  {
    prices = paramList;
  }
  
  @NotNull
  public final List<UberPrice> component1()
  {
    return prices;
  }
  
  @NotNull
  public final UberPriceList copy(@NotNull List<UberPrice> paramList)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "prices");
    return new UberPriceList(paramList);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof UberPriceList))
      {
        paramObject = (UberPriceList)paramObject;
        if (Intrinsics.areEqual(prices, prices)) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final List<UberPrice> getPrices()
  {
    return prices;
  }
  
  public int hashCode()
  {
    List localList = prices;
    if (localList != null) {
      return localList.hashCode();
    }
    return 0;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("UberPriceList(prices=");
    localStringBuilder.append(prices);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

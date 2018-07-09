package com.byimplication.sakay.store;

import com.byimplication.sakay.model.Incident;
import com.byimplication.sakay.model.Itinerary;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000@\n\002\030\002\n\002\030\002\n\000\n\002\020\t\n\000\n\002\020 \n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\000\n\002\030\002\n\002\b\027\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001BI\022\b\020\002\032\004\030\0010\003\022\016\020\004\032\n\022\004\022\0020\006\030\0010\005\022\016\020\007\032\n\022\004\022\0020\b\030\0010\005\022\006\020\t\032\0020\n\022\006\020\013\032\0020\f\022\b\b\002\020\r\032\0020\n¢\006\002\020\016J\n\020\031\032\004\030\0010\001H\026J\020\020\032\032\004\030\0010\003HÆ\003¢\006\002\020\027J\021\020\033\032\n\022\004\022\0020\006\030\0010\005HÆ\003J\021\020\034\032\n\022\004\022\0020\b\030\0010\005HÆ\003J\t\020\035\032\0020\nHÆ\003J\t\020\036\032\0020\fHÆ\003J\t\020\037\032\0020\nHÆ\003J\\\020 \032\0020\0002\n\b\002\020\002\032\004\030\0010\0032\020\b\002\020\004\032\n\022\004\022\0020\006\030\0010\0052\020\b\002\020\007\032\n\022\004\022\0020\b\030\0010\0052\b\b\002\020\t\032\0020\n2\b\b\002\020\013\032\0020\f2\b\b\002\020\r\032\0020\nHÆ\001¢\006\002\020!J\023\020\"\032\0020\n2\b\020#\032\004\030\0010$HÖ\003J\t\020%\032\0020&HÖ\001J\t\020'\032\0020(HÖ\001R\021\020\013\032\0020\f¢\006\b\n\000\032\004\b\017\020\020R\021\020\t\032\0020\n¢\006\b\n\000\032\004\b\021\020\022R\031\020\007\032\n\022\004\022\0020\b\030\0010\005¢\006\b\n\000\032\004\b\023\020\024R\021\020\r\032\0020\n¢\006\b\n\000\032\004\b\r\020\022R\031\020\004\032\n\022\004\022\0020\006\030\0010\005¢\006\b\n\000\032\004\b\025\020\024R\025\020\002\032\004\030\0010\003¢\006\n\n\002\020\030\032\004\b\026\020\027¨\006)"}, d2={"Lcom/byimplication/sakay/store/RoutesUpdated;", "Lcom/byimplication/sakay/store/StoreData;", "searchId", "", "itineraries", "", "Lcom/byimplication/sakay/model/Itinerary;", "incidents", "Lcom/byimplication/sakay/model/Incident;", "failure", "", "dateUsed", "Ljava/util/Date;", "isStored", "(Ljava/lang/Long;Ljava/util/List;Ljava/util/List;ZLjava/util/Date;Z)V", "getDateUsed", "()Ljava/util/Date;", "getFailure", "()Z", "getIncidents", "()Ljava/util/List;", "getItineraries", "getSearchId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "clone", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/Long;Ljava/util/List;Ljava/util/List;ZLjava/util/Date;Z)Lcom/byimplication/sakay/store/RoutesUpdated;", "equals", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class RoutesUpdated
  implements StoreData
{
  @NotNull
  private final Date dateUsed;
  private final boolean failure;
  @Nullable
  private final List<Incident> incidents;
  private final boolean isStored;
  @Nullable
  private final List<Itinerary> itineraries;
  @Nullable
  private final Long searchId;
  
  public RoutesUpdated(@Nullable Long paramLong, @Nullable List<Itinerary> paramList, @Nullable List<Incident> paramList1, boolean paramBoolean1, @NotNull Date paramDate, boolean paramBoolean2)
  {
    searchId = paramLong;
    itineraries = paramList;
    incidents = paramList1;
    failure = paramBoolean1;
    dateUsed = paramDate;
    isStored = paramBoolean2;
  }
  
  @Nullable
  public StoreData clone()
  {
    return (StoreData)copy$default(this, null, null, null, false, null, false, 63, null);
  }
  
  @Nullable
  public final Long component1()
  {
    return searchId;
  }
  
  @Nullable
  public final List<Itinerary> component2()
  {
    return itineraries;
  }
  
  @Nullable
  public final List<Incident> component3()
  {
    return incidents;
  }
  
  public final boolean component4()
  {
    return failure;
  }
  
  @NotNull
  public final Date component5()
  {
    return dateUsed;
  }
  
  public final boolean component6()
  {
    return isStored;
  }
  
  @NotNull
  public final RoutesUpdated copy(@Nullable Long paramLong, @Nullable List<Itinerary> paramList, @Nullable List<Incident> paramList1, boolean paramBoolean1, @NotNull Date paramDate, boolean paramBoolean2)
  {
    Intrinsics.checkParameterIsNotNull(paramDate, "dateUsed");
    return new RoutesUpdated(paramLong, paramList, paramList1, paramBoolean1, paramDate, paramBoolean2);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof RoutesUpdated))
      {
        paramObject = (RoutesUpdated)paramObject;
        if ((Intrinsics.areEqual(searchId, searchId)) && (Intrinsics.areEqual(itineraries, itineraries)) && (Intrinsics.areEqual(incidents, incidents)))
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
  public final List<Incident> getIncidents()
  {
    return incidents;
  }
  
  @Nullable
  public final List<Itinerary> getItineraries()
  {
    return itineraries;
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
  
  public final boolean isStored()
  {
    return isStored;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("RoutesUpdated(searchId=");
    localStringBuilder.append(searchId);
    localStringBuilder.append(", itineraries=");
    localStringBuilder.append(itineraries);
    localStringBuilder.append(", incidents=");
    localStringBuilder.append(incidents);
    localStringBuilder.append(", failure=");
    localStringBuilder.append(failure);
    localStringBuilder.append(", dateUsed=");
    localStringBuilder.append(dateUsed);
    localStringBuilder.append(", isStored=");
    localStringBuilder.append(isStored);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

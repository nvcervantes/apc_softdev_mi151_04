package com.byimplication.sakay.action;

import com.byimplication.sakay.model.Incident;
import com.byimplication.sakay.model.Itinerary;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000@\n\002\030\002\n\002\030\002\n\000\n\002\020\t\n\000\n\002\020 \n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\000\n\002\030\002\n\002\b\024\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001BE\022\b\020\002\032\004\030\0010\003\022\016\020\004\032\n\022\004\022\0020\006\030\0010\005\022\016\020\007\032\n\022\004\022\0020\b\030\0010\005\022\b\b\002\020\t\032\0020\n\022\n\b\002\020\013\032\004\030\0010\f¢\006\002\020\rJ\020\020\030\032\004\030\0010\003HÆ\003¢\006\002\020\024J\021\020\031\032\n\022\004\022\0020\006\030\0010\005HÆ\003J\021\020\032\032\n\022\004\022\0020\b\030\0010\005HÆ\003J\t\020\033\032\0020\nHÆ\003J\013\020\034\032\004\030\0010\fHÆ\003JT\020\035\032\0020\0002\n\b\002\020\002\032\004\030\0010\0032\020\b\002\020\004\032\n\022\004\022\0020\006\030\0010\0052\020\b\002\020\007\032\n\022\004\022\0020\b\030\0010\0052\b\b\002\020\t\032\0020\n2\n\b\002\020\013\032\004\030\0010\fHÆ\001¢\006\002\020\036J\023\020\037\032\0020\n2\b\020 \032\004\030\0010!HÖ\003J\t\020\"\032\0020#HÖ\001J\t\020$\032\0020%HÖ\001R\023\020\013\032\004\030\0010\f¢\006\b\n\000\032\004\b\016\020\017R\031\020\007\032\n\022\004\022\0020\b\030\0010\005¢\006\b\n\000\032\004\b\020\020\021R\031\020\004\032\n\022\004\022\0020\006\030\0010\005¢\006\b\n\000\032\004\b\022\020\021R\025\020\002\032\004\030\0010\003¢\006\n\n\002\020\025\032\004\b\023\020\024R\021\020\t\032\0020\n¢\006\b\n\000\032\004\b\026\020\027¨\006&"}, d2={"Lcom/byimplication/sakay/action/UpdateRoutes;", "Lcom/byimplication/sakay/action/Action;", "searchId", "", "itineraries", "", "Lcom/byimplication/sakay/model/Itinerary;", "incidents", "Lcom/byimplication/sakay/model/Incident;", "stored", "", "dateUsed", "Ljava/util/Date;", "(Ljava/lang/Long;Ljava/util/List;Ljava/util/List;ZLjava/util/Date;)V", "getDateUsed", "()Ljava/util/Date;", "getIncidents", "()Ljava/util/List;", "getItineraries", "getSearchId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getStored", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Long;Ljava/util/List;Ljava/util/List;ZLjava/util/Date;)Lcom/byimplication/sakay/action/UpdateRoutes;", "equals", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class UpdateRoutes
  implements Action
{
  @Nullable
  private final Date dateUsed;
  @Nullable
  private final List<Incident> incidents;
  @Nullable
  private final List<Itinerary> itineraries;
  @Nullable
  private final Long searchId;
  private final boolean stored;
  
  public UpdateRoutes(@Nullable Long paramLong, @Nullable List<Itinerary> paramList, @Nullable List<Incident> paramList1, boolean paramBoolean, @Nullable Date paramDate)
  {
    searchId = paramLong;
    itineraries = paramList;
    incidents = paramList1;
    stored = paramBoolean;
    dateUsed = paramDate;
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
    return stored;
  }
  
  @Nullable
  public final Date component5()
  {
    return dateUsed;
  }
  
  @NotNull
  public final UpdateRoutes copy(@Nullable Long paramLong, @Nullable List<Itinerary> paramList, @Nullable List<Incident> paramList1, boolean paramBoolean, @Nullable Date paramDate)
  {
    return new UpdateRoutes(paramLong, paramList, paramList1, paramBoolean, paramDate);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof UpdateRoutes))
      {
        paramObject = (UpdateRoutes)paramObject;
        if ((Intrinsics.areEqual(searchId, searchId)) && (Intrinsics.areEqual(itineraries, itineraries)) && (Intrinsics.areEqual(incidents, incidents)))
        {
          int i;
          if (stored == stored) {
            i = 1;
          } else {
            i = 0;
          }
          if ((i != 0) && (Intrinsics.areEqual(dateUsed, dateUsed))) {
            return true;
          }
        }
      }
      return false;
    }
    return true;
  }
  
  @Nullable
  public final Date getDateUsed()
  {
    return dateUsed;
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
  
  public final boolean getStored()
  {
    return stored;
  }
  
  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public void post()
  {
    Action.DefaultImpls.post(this);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("UpdateRoutes(searchId=");
    localStringBuilder.append(searchId);
    localStringBuilder.append(", itineraries=");
    localStringBuilder.append(itineraries);
    localStringBuilder.append(", incidents=");
    localStringBuilder.append(incidents);
    localStringBuilder.append(", stored=");
    localStringBuilder.append(stored);
    localStringBuilder.append(", dateUsed=");
    localStringBuilder.append(dateUsed);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

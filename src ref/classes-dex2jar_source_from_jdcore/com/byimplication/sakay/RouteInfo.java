package com.byimplication.sakay;

import com.byimplication.sakay.model.Incident;
import com.byimplication.sakay.model.Itinerary;
import com.byimplication.sakay.model.ScheduleType;
import com.byimplication.sakay.model.Terminal;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000J\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\002\n\002\020\t\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\000\n\002\030\002\n\002\b\030\n\002\020\013\n\002\b\002\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001BQ\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003\022\b\020\005\032\004\030\0010\006\022\b\020\007\032\004\030\0010\b\022\006\020\t\032\0020\n\022\016\020\013\032\n\022\004\022\0020\r\030\0010\f\022\016\020\016\032\n\022\004\022\0020\017\030\0010\f¢\006\002\020\020J\t\020\036\032\0020\003HÆ\003J\t\020\037\032\0020\003HÆ\003J\020\020 \032\004\030\0010\006HÆ\003¢\006\002\020\034J\013\020!\032\004\030\0010\bHÆ\003J\t\020\"\032\0020\nHÆ\003J\021\020#\032\n\022\004\022\0020\r\030\0010\fHÆ\003J\021\020$\032\n\022\004\022\0020\017\030\0010\fHÆ\003Jh\020%\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0032\n\b\002\020\005\032\004\030\0010\0062\n\b\002\020\007\032\004\030\0010\b2\b\b\002\020\t\032\0020\n2\020\b\002\020\013\032\n\022\004\022\0020\r\030\0010\f2\020\b\002\020\016\032\n\022\004\022\0020\017\030\0010\fHÆ\001¢\006\002\020&J\023\020'\032\0020(2\b\020)\032\004\030\0010\001HÖ\003J\t\020*\032\0020+HÖ\001J\t\020,\032\0020-HÖ\001R\021\020\004\032\0020\003¢\006\b\n\000\032\004\b\021\020\022R\031\020\016\032\n\022\004\022\0020\017\030\0010\f¢\006\b\n\000\032\004\b\023\020\024R\031\020\013\032\n\022\004\022\0020\r\030\0010\f¢\006\b\n\000\032\004\b\025\020\024R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\026\020\022R\023\020\007\032\004\030\0010\b¢\006\b\n\000\032\004\b\027\020\030R\021\020\t\032\0020\n¢\006\b\n\000\032\004\b\031\020\032R\025\020\005\032\004\030\0010\006¢\006\n\n\002\020\035\032\004\b\033\020\034¨\006."}, d2={"Lcom/byimplication/sakay/RouteInfo;", "", "origin", "Lcom/byimplication/sakay/model/Terminal;", "destination", "searchId", "", "schedule", "Ljava/util/Date;", "scheduleType", "Lcom/byimplication/sakay/model/ScheduleType;", "itineraries", "", "Lcom/byimplication/sakay/model/Itinerary;", "incidents", "Lcom/byimplication/sakay/model/Incident;", "(Lcom/byimplication/sakay/model/Terminal;Lcom/byimplication/sakay/model/Terminal;Ljava/lang/Long;Ljava/util/Date;Lcom/byimplication/sakay/model/ScheduleType;Ljava/util/List;Ljava/util/List;)V", "getDestination", "()Lcom/byimplication/sakay/model/Terminal;", "getIncidents", "()Ljava/util/List;", "getItineraries", "getOrigin", "getSchedule", "()Ljava/util/Date;", "getScheduleType", "()Lcom/byimplication/sakay/model/ScheduleType;", "getSearchId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Lcom/byimplication/sakay/model/Terminal;Lcom/byimplication/sakay/model/Terminal;Ljava/lang/Long;Ljava/util/Date;Lcom/byimplication/sakay/model/ScheduleType;Ljava/util/List;Ljava/util/List;)Lcom/byimplication/sakay/RouteInfo;", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class RouteInfo
{
  @NotNull
  private final Terminal destination;
  @Nullable
  private final List<Incident> incidents;
  @Nullable
  private final List<Itinerary> itineraries;
  @NotNull
  private final Terminal origin;
  @Nullable
  private final Date schedule;
  @NotNull
  private final ScheduleType scheduleType;
  @Nullable
  private final Long searchId;
  
  public RouteInfo(@NotNull Terminal paramTerminal1, @NotNull Terminal paramTerminal2, @Nullable Long paramLong, @Nullable Date paramDate, @NotNull ScheduleType paramScheduleType, @Nullable List<Itinerary> paramList, @Nullable List<Incident> paramList1)
  {
    origin = paramTerminal1;
    destination = paramTerminal2;
    searchId = paramLong;
    schedule = paramDate;
    scheduleType = paramScheduleType;
    itineraries = paramList;
    incidents = paramList1;
  }
  
  @NotNull
  public final Terminal component1()
  {
    return origin;
  }
  
  @NotNull
  public final Terminal component2()
  {
    return destination;
  }
  
  @Nullable
  public final Long component3()
  {
    return searchId;
  }
  
  @Nullable
  public final Date component4()
  {
    return schedule;
  }
  
  @NotNull
  public final ScheduleType component5()
  {
    return scheduleType;
  }
  
  @Nullable
  public final List<Itinerary> component6()
  {
    return itineraries;
  }
  
  @Nullable
  public final List<Incident> component7()
  {
    return incidents;
  }
  
  @NotNull
  public final RouteInfo copy(@NotNull Terminal paramTerminal1, @NotNull Terminal paramTerminal2, @Nullable Long paramLong, @Nullable Date paramDate, @NotNull ScheduleType paramScheduleType, @Nullable List<Itinerary> paramList, @Nullable List<Incident> paramList1)
  {
    Intrinsics.checkParameterIsNotNull(paramTerminal1, "origin");
    Intrinsics.checkParameterIsNotNull(paramTerminal2, "destination");
    Intrinsics.checkParameterIsNotNull(paramScheduleType, "scheduleType");
    return new RouteInfo(paramTerminal1, paramTerminal2, paramLong, paramDate, paramScheduleType, paramList, paramList1);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof RouteInfo))
      {
        paramObject = (RouteInfo)paramObject;
        if ((Intrinsics.areEqual(origin, origin)) && (Intrinsics.areEqual(destination, destination)) && (Intrinsics.areEqual(searchId, searchId)) && (Intrinsics.areEqual(schedule, schedule)) && (Intrinsics.areEqual(scheduleType, scheduleType)) && (Intrinsics.areEqual(itineraries, itineraries)) && (Intrinsics.areEqual(incidents, incidents))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final Terminal getDestination()
  {
    return destination;
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
  
  @NotNull
  public final Terminal getOrigin()
  {
    return origin;
  }
  
  @Nullable
  public final Date getSchedule()
  {
    return schedule;
  }
  
  @NotNull
  public final ScheduleType getScheduleType()
  {
    return scheduleType;
  }
  
  @Nullable
  public final Long getSearchId()
  {
    return searchId;
  }
  
  public int hashCode()
  {
    Object localObject = origin;
    int i2 = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = destination;
    int j;
    if (localObject != null) {
      j = localObject.hashCode();
    } else {
      j = 0;
    }
    localObject = searchId;
    int k;
    if (localObject != null) {
      k = localObject.hashCode();
    } else {
      k = 0;
    }
    localObject = schedule;
    int m;
    if (localObject != null) {
      m = localObject.hashCode();
    } else {
      m = 0;
    }
    localObject = scheduleType;
    int n;
    if (localObject != null) {
      n = localObject.hashCode();
    } else {
      n = 0;
    }
    localObject = itineraries;
    int i1;
    if (localObject != null) {
      i1 = localObject.hashCode();
    } else {
      i1 = 0;
    }
    localObject = incidents;
    if (localObject != null) {
      i2 = localObject.hashCode();
    }
    return (((((i * 31 + j) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("RouteInfo(origin=");
    localStringBuilder.append(origin);
    localStringBuilder.append(", destination=");
    localStringBuilder.append(destination);
    localStringBuilder.append(", searchId=");
    localStringBuilder.append(searchId);
    localStringBuilder.append(", schedule=");
    localStringBuilder.append(schedule);
    localStringBuilder.append(", scheduleType=");
    localStringBuilder.append(scheduleType);
    localStringBuilder.append(", itineraries=");
    localStringBuilder.append(itineraries);
    localStringBuilder.append(", incidents=");
    localStringBuilder.append(incidents);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

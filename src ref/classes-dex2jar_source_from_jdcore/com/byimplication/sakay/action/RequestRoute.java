package com.byimplication.sakay.action;

import com.byimplication.sakay.model.ScheduleType;
import com.byimplication.sakay.model.Terminal;
import com.google.android.gms.maps.model.LatLng;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000>\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\021\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B/\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003\022\006\020\005\032\0020\006\022\006\020\007\032\0020\b\022\b\020\t\032\004\030\0010\n¢\006\002\020\013J\t\020\025\032\0020\003HÆ\003J\t\020\026\032\0020\003HÆ\003J\t\020\027\032\0020\006HÆ\003J\t\020\030\032\0020\bHÆ\003J\013\020\031\032\004\030\0010\nHÆ\003J=\020\032\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0032\b\b\002\020\005\032\0020\0062\b\b\002\020\007\032\0020\b2\n\b\002\020\t\032\004\030\0010\nHÆ\001J\023\020\033\032\0020\0342\b\020\035\032\004\030\0010\036HÖ\003J\t\020\037\032\0020 HÖ\001J\t\020!\032\0020\"HÖ\001R\023\020\t\032\004\030\0010\n¢\006\b\n\000\032\004\b\f\020\rR\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\016\020\017R\021\020\004\032\0020\003¢\006\b\n\000\032\004\b\020\020\021R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\022\020\021R\021\020\007\032\0020\b¢\006\b\n\000\032\004\b\023\020\024¨\006#"}, d2={"Lcom/byimplication/sakay/action/RequestRoute;", "Lcom/byimplication/sakay/action/Action;", "origin", "Lcom/byimplication/sakay/model/Terminal;", "destination", "date", "Ljava/util/Date;", "scheduleType", "Lcom/byimplication/sakay/model/ScheduleType;", "currLoc", "Lcom/google/android/gms/maps/model/LatLng;", "(Lcom/byimplication/sakay/model/Terminal;Lcom/byimplication/sakay/model/Terminal;Ljava/util/Date;Lcom/byimplication/sakay/model/ScheduleType;Lcom/google/android/gms/maps/model/LatLng;)V", "getCurrLoc", "()Lcom/google/android/gms/maps/model/LatLng;", "getDate", "()Ljava/util/Date;", "getDestination", "()Lcom/byimplication/sakay/model/Terminal;", "getOrigin", "getScheduleType", "()Lcom/byimplication/sakay/model/ScheduleType;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class RequestRoute
  implements Action
{
  @Nullable
  private final LatLng currLoc;
  @NotNull
  private final Date date;
  @NotNull
  private final Terminal destination;
  @NotNull
  private final Terminal origin;
  @NotNull
  private final ScheduleType scheduleType;
  
  public RequestRoute(@NotNull Terminal paramTerminal1, @NotNull Terminal paramTerminal2, @NotNull Date paramDate, @NotNull ScheduleType paramScheduleType, @Nullable LatLng paramLatLng)
  {
    origin = paramTerminal1;
    destination = paramTerminal2;
    date = paramDate;
    scheduleType = paramScheduleType;
    currLoc = paramLatLng;
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
  
  @NotNull
  public final Date component3()
  {
    return date;
  }
  
  @NotNull
  public final ScheduleType component4()
  {
    return scheduleType;
  }
  
  @Nullable
  public final LatLng component5()
  {
    return currLoc;
  }
  
  @NotNull
  public final RequestRoute copy(@NotNull Terminal paramTerminal1, @NotNull Terminal paramTerminal2, @NotNull Date paramDate, @NotNull ScheduleType paramScheduleType, @Nullable LatLng paramLatLng)
  {
    Intrinsics.checkParameterIsNotNull(paramTerminal1, "origin");
    Intrinsics.checkParameterIsNotNull(paramTerminal2, "destination");
    Intrinsics.checkParameterIsNotNull(paramDate, "date");
    Intrinsics.checkParameterIsNotNull(paramScheduleType, "scheduleType");
    return new RequestRoute(paramTerminal1, paramTerminal2, paramDate, paramScheduleType, paramLatLng);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof RequestRoute))
      {
        paramObject = (RequestRoute)paramObject;
        if ((Intrinsics.areEqual(origin, origin)) && (Intrinsics.areEqual(destination, destination)) && (Intrinsics.areEqual(date, date)) && (Intrinsics.areEqual(scheduleType, scheduleType)) && (Intrinsics.areEqual(currLoc, currLoc))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @Nullable
  public final LatLng getCurrLoc()
  {
    return currLoc;
  }
  
  @NotNull
  public final Date getDate()
  {
    return date;
  }
  
  @NotNull
  public final Terminal getDestination()
  {
    return destination;
  }
  
  @NotNull
  public final Terminal getOrigin()
  {
    return origin;
  }
  
  @NotNull
  public final ScheduleType getScheduleType()
  {
    return scheduleType;
  }
  
  public int hashCode()
  {
    Object localObject = origin;
    int n = 0;
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
    localObject = date;
    int k;
    if (localObject != null) {
      k = localObject.hashCode();
    } else {
      k = 0;
    }
    localObject = scheduleType;
    int m;
    if (localObject != null) {
      m = localObject.hashCode();
    } else {
      m = 0;
    }
    localObject = currLoc;
    if (localObject != null) {
      n = localObject.hashCode();
    }
    return (((i * 31 + j) * 31 + k) * 31 + m) * 31 + n;
  }
  
  public void post()
  {
    Action.DefaultImpls.post(this);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("RequestRoute(origin=");
    localStringBuilder.append(origin);
    localStringBuilder.append(", destination=");
    localStringBuilder.append(destination);
    localStringBuilder.append(", date=");
    localStringBuilder.append(date);
    localStringBuilder.append(", scheduleType=");
    localStringBuilder.append(scheduleType);
    localStringBuilder.append(", currLoc=");
    localStringBuilder.append(currLoc);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

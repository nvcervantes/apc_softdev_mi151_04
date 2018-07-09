package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000T\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\t\n\000\n\002\030\002\n\002\b\002\n\002\020 \n\002\030\002\n\000\n\002\030\002\n\002\b\020\n\002\020\b\n\000\n\002\020\013\n\000\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\002\n\002\b\004\b\b\030\000 +2\0020\001:\001+B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B9\022\006\020\005\032\0020\006\022\006\020\007\032\0020\b\022\006\020\t\032\0020\b\022\f\020\n\032\b\022\004\022\0020\f0\013\022\f\020\r\032\b\022\004\022\0020\0160\013¢\006\002\020\017J\t\020\030\032\0020\006HÆ\003J\t\020\031\032\0020\bHÆ\003J\t\020\032\032\0020\bHÆ\003J\017\020\033\032\b\022\004\022\0020\f0\013HÆ\003J\017\020\034\032\b\022\004\022\0020\0160\013HÆ\003JG\020\035\032\0020\0002\b\b\002\020\005\032\0020\0062\b\b\002\020\007\032\0020\b2\b\b\002\020\t\032\0020\b2\016\b\002\020\n\032\b\022\004\022\0020\f0\0132\016\b\002\020\r\032\b\022\004\022\0020\0160\013HÆ\001J\b\020\036\032\0020\037H\026J\023\020 \032\0020!2\b\020\"\032\004\030\0010#HÖ\003J\t\020$\032\0020\037HÖ\001J\t\020%\032\0020&HÖ\001J\030\020'\032\0020(2\006\020)\032\0020\0032\006\020*\032\0020\037H\026R\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\020\020\021R\021\020\007\032\0020\b¢\006\b\n\000\032\004\b\022\020\023R\027\020\r\032\b\022\004\022\0020\0160\013¢\006\b\n\000\032\004\b\024\020\025R\027\020\n\032\b\022\004\022\0020\f0\013¢\006\b\n\000\032\004\b\026\020\025R\021\020\t\032\0020\b¢\006\b\n\000\032\004\b\027\020\023¨\006,"}, d2={"Lcom/byimplication/sakay/model/Plan;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "date", "", "from", "Lcom/byimplication/sakay/model/PlanPoint;", "to", "itineraries", "", "Lcom/byimplication/sakay/model/Itinerary;", "incidents", "Lcom/byimplication/sakay/model/Incident;", "(JLcom/byimplication/sakay/model/PlanPoint;Lcom/byimplication/sakay/model/PlanPoint;Ljava/util/List;Ljava/util/List;)V", "getDate", "()J", "getFrom", "()Lcom/byimplication/sakay/model/PlanPoint;", "getIncidents", "()Ljava/util/List;", "getItineraries", "getTo", "component1", "component2", "component3", "component4", "component5", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class Plan
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<Plan> CREATOR = (Parcelable.Creator)new Plan.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  private final long date;
  @NotNull
  private final PlanPoint from;
  @NotNull
  private final List<Incident> incidents;
  @NotNull
  private final List<Itinerary> itineraries;
  @NotNull
  private final PlanPoint to;
  
  public Plan(long paramLong, @NotNull PlanPoint paramPlanPoint1, @NotNull PlanPoint paramPlanPoint2, @NotNull List<Itinerary> paramList, @NotNull List<Incident> paramList1)
  {
    date = paramLong;
    from = paramPlanPoint1;
    to = paramPlanPoint2;
    itineraries = paramList;
    incidents = paramList1;
  }
  
  public Plan(@NotNull Parcel paramParcel)
  {
    this(l, (PlanPoint)localObject1, (PlanPoint)localObject2, (List)localObject3, (List)paramParcel);
  }
  
  public final long component1()
  {
    return date;
  }
  
  @NotNull
  public final PlanPoint component2()
  {
    return from;
  }
  
  @NotNull
  public final PlanPoint component3()
  {
    return to;
  }
  
  @NotNull
  public final List<Itinerary> component4()
  {
    return itineraries;
  }
  
  @NotNull
  public final List<Incident> component5()
  {
    return incidents;
  }
  
  @NotNull
  public final Plan copy(long paramLong, @NotNull PlanPoint paramPlanPoint1, @NotNull PlanPoint paramPlanPoint2, @NotNull List<Itinerary> paramList, @NotNull List<Incident> paramList1)
  {
    Intrinsics.checkParameterIsNotNull(paramPlanPoint1, "from");
    Intrinsics.checkParameterIsNotNull(paramPlanPoint2, "to");
    Intrinsics.checkParameterIsNotNull(paramList, "itineraries");
    Intrinsics.checkParameterIsNotNull(paramList1, "incidents");
    return new Plan(paramLong, paramPlanPoint1, paramPlanPoint2, paramList, paramList1);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof Plan))
      {
        paramObject = (Plan)paramObject;
        int i;
        if (date == date) {
          i = 1;
        } else {
          i = 0;
        }
        if ((i != 0) && (Intrinsics.areEqual(from, from)) && (Intrinsics.areEqual(to, to)) && (Intrinsics.areEqual(itineraries, itineraries)) && (Intrinsics.areEqual(incidents, incidents))) {
          return true;
        }
      }
      return false;
    }
    return true;
  }
  
  public final long getDate()
  {
    return date;
  }
  
  @NotNull
  public final PlanPoint getFrom()
  {
    return from;
  }
  
  @NotNull
  public final List<Incident> getIncidents()
  {
    return incidents;
  }
  
  @NotNull
  public final List<Itinerary> getItineraries()
  {
    return itineraries;
  }
  
  @NotNull
  public final PlanPoint getTo()
  {
    return to;
  }
  
  public int hashCode()
  {
    long l = date;
    int n = (int)(l ^ l >>> 32);
    Object localObject = from;
    int m = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = to;
    int j;
    if (localObject != null) {
      j = localObject.hashCode();
    } else {
      j = 0;
    }
    localObject = itineraries;
    int k;
    if (localObject != null) {
      k = localObject.hashCode();
    } else {
      k = 0;
    }
    localObject = incidents;
    if (localObject != null) {
      m = localObject.hashCode();
    }
    return (((n * 31 + i) * 31 + j) * 31 + k) * 31 + m;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Plan(date=");
    localStringBuilder.append(date);
    localStringBuilder.append(", from=");
    localStringBuilder.append(from);
    localStringBuilder.append(", to=");
    localStringBuilder.append(to);
    localStringBuilder.append(", itineraries=");
    localStringBuilder.append(itineraries);
    localStringBuilder.append(", incidents=");
    localStringBuilder.append(incidents);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramParcel, "dest");
    paramParcel.writeLong(date);
    paramParcel.writeParcelable((Parcelable)from, 0);
    paramParcel.writeParcelable((Parcelable)to, 0);
    paramParcel.writeTypedList(itineraries);
    paramParcel.writeTypedList(incidents);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\006"}, d2={"Lcom/byimplication/sakay/model/Plan$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/Plan;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
  }
}

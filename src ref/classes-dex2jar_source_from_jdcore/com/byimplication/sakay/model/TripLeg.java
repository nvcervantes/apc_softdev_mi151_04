package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000r\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\000\n\002\020\b\n\000\n\002\020\t\n\002\b\002\n\002\020 \n\002\b\002\n\002\030\002\n\000\n\002\020\006\n\000\n\002\020\016\n\002\b\004\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\004\n\002\030\002\n\002\bG\n\002\020\000\n\002\b\003\n\002\020\002\n\002\b\004\b\b\030\000 v2\0020\001:\001vB\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B÷\001\022\006\020\005\032\0020\006\022\006\020\007\032\0020\b\022\006\020\t\032\0020\n\022\006\020\013\032\0020\n\022\016\020\f\032\n\022\004\022\0020\n\030\0010\r\022\006\020\016\032\0020\n\022\f\020\017\032\b\022\004\022\0020\0200\r\022\b\020\021\032\004\030\0010\022\022\b\020\023\032\004\030\0010\024\022\006\020\025\032\0020\b\022\006\020\026\032\0020\024\022\006\020\027\032\0020\006\022\006\020\030\032\0020\031\022\006\020\032\032\0020\006\022\006\020\033\032\0020\006\022\006\020\034\032\0020\035\022\b\020\036\032\004\030\0010\024\022\b\020\037\032\004\030\0010\024\022\006\020 \032\0020\022\022\006\020!\032\0020\"\022\006\020#\032\0020\006\022\006\020$\032\0020\b\022\006\020%\032\0020\035\022\016\020&\032\n\022\004\022\0020'\030\0010\r\022\b\020(\032\004\030\0010\024\022\b\020)\032\004\030\0010\024¢\006\002\020*J\t\020P\032\0020\006HÆ\003J\t\020Q\032\0020\bHÆ\003J\t\020R\032\0020\024HÆ\003J\t\020S\032\0020\006HÆ\003J\t\020T\032\0020\031HÆ\003J\t\020U\032\0020\006HÆ\003J\t\020V\032\0020\006HÆ\003J\t\020W\032\0020\035HÆ\003J\013\020X\032\004\030\0010\024HÆ\003J\013\020Y\032\004\030\0010\024HÆ\003J\t\020Z\032\0020\022HÆ\003J\t\020[\032\0020\bHÆ\003J\t\020\\\032\0020\"HÆ\003J\t\020]\032\0020\006HÆ\003J\t\020^\032\0020\bHÆ\003J\t\020_\032\0020\035HÆ\003J\021\020`\032\n\022\004\022\0020'\030\0010\rHÆ\003J\013\020a\032\004\030\0010\024HÆ\003J\013\020b\032\004\030\0010\024HÆ\003J\t\020c\032\0020\nHÆ\003J\t\020d\032\0020\nHÆ\003J\021\020e\032\n\022\004\022\0020\n\030\0010\rHÆ\003J\t\020f\032\0020\nHÆ\003J\017\020g\032\b\022\004\022\0020\0200\rHÆ\003J\020\020h\032\004\030\0010\022HÆ\003¢\006\002\0209J\013\020i\032\004\030\0010\024HÆ\003J´\002\020j\032\0020\0002\b\b\002\020\005\032\0020\0062\b\b\002\020\007\032\0020\b2\b\b\002\020\t\032\0020\n2\b\b\002\020\013\032\0020\n2\020\b\002\020\f\032\n\022\004\022\0020\n\030\0010\r2\b\b\002\020\016\032\0020\n2\016\b\002\020\017\032\b\022\004\022\0020\0200\r2\n\b\002\020\021\032\004\030\0010\0222\n\b\002\020\023\032\004\030\0010\0242\b\b\002\020\025\032\0020\b2\b\b\002\020\026\032\0020\0242\b\b\002\020\027\032\0020\0062\b\b\002\020\030\032\0020\0312\b\b\002\020\032\032\0020\0062\b\b\002\020\033\032\0020\0062\b\b\002\020\034\032\0020\0352\n\b\002\020\036\032\004\030\0010\0242\n\b\002\020\037\032\004\030\0010\0242\b\b\002\020 \032\0020\0222\b\b\002\020!\032\0020\"2\b\b\002\020#\032\0020\0062\b\b\002\020$\032\0020\b2\b\b\002\020%\032\0020\0352\020\b\002\020&\032\n\022\004\022\0020'\030\0010\r2\n\b\002\020(\032\004\030\0010\0242\n\b\002\020)\032\004\030\0010\024HÆ\001¢\006\002\020kJ\b\020l\032\0020\bH\026J\023\020m\032\0020\0062\b\020n\032\004\030\0010oHÖ\003J\t\020p\032\0020\bHÖ\001J\t\020q\032\0020\024HÖ\001J\030\020r\032\0020s2\006\020t\032\0020\0032\006\020u\032\0020\bH\026R\021\020\t\032\0020\n¢\006\b\n\000\032\004\b+\020,R\031\020&\032\n\022\004\022\0020'\030\0010\r¢\006\b\n\000\032\004\b-\020.R\021\020$\032\0020\b¢\006\b\n\000\032\004\b/\0200R\021\020!\032\0020\"¢\006\b\n\000\032\004\b1\0202R\021\020\007\032\0020\b¢\006\b\n\000\032\004\b3\0200R\021\020 \032\0020\022¢\006\b\n\000\032\004\b4\0205R\021\020\025\032\0020\b¢\006\b\n\000\032\004\b6\0200R\021\020\016\032\0020\n¢\006\b\n\000\032\004\b7\020,R\025\020\021\032\004\030\0010\022¢\006\n\n\002\020:\032\004\b8\0209R\023\020\023\032\004\030\0010\024¢\006\b\n\000\032\004\b;\020<R\021\020\034\032\0020\035¢\006\b\n\000\032\004\b=\020>R\023\020(\032\004\030\0010\024¢\006\b\n\000\032\004\b?\020<R\031\020\f\032\n\022\004\022\0020\n\030\0010\r¢\006\b\n\000\032\004\b@\020.R\021\020\027\032\0020\006¢\006\b\n\000\032\004\bA\020BR\021\020\030\032\0020\031¢\006\b\n\000\032\004\bC\020DR\021\020\033\032\0020\006¢\006\b\n\000\032\004\bE\020BR\021\020\032\032\0020\006¢\006\b\n\000\032\004\bF\020BR\021\020\005\032\0020\006¢\006\b\n\000\032\004\bG\020BR\021\020\026\032\0020\024¢\006\b\n\000\032\004\bH\020<R\023\020\036\032\004\030\0010\024¢\006\b\n\000\032\004\bI\020<R\023\020)\032\004\030\0010\024¢\006\b\n\000\032\004\bJ\020<R\021\020\013\032\0020\n¢\006\b\n\000\032\004\bK\020,R\027\020\017\032\b\022\004\022\0020\0200\r¢\006\b\n\000\032\004\bL\020.R\021\020%\032\0020\035¢\006\b\n\000\032\004\bM\020>R\021\020#\032\0020\006¢\006\b\n\000\032\004\bN\020BR\023\020\037\032\004\030\0010\024¢\006\b\n\000\032\004\bO\020<¨\006w"}, d2={"Lcom/byimplication/sakay/model/TripLeg;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "rentedBike", "", "departureDelay", "", "agencyTimeZoneOffset", "", "startTime", "incidentIDs", "", "endTime", "steps", "Lcom/byimplication/sakay/model/TripStep;", "fare", "", "fareId", "", "duration", "route", "interlineWithPreviousLeg", "legGeometry", "Lcom/byimplication/sakay/model/LegGeometry;", "realTime", "pathway", "from", "Lcom/byimplication/sakay/model/TripPoint;", "routeId", "tripId", "distance", "conveyance", "Lcom/byimplication/sakay/model/Conveyance;", "transitLeg", "arrivalDelay", "to", "alternatives", "Lcom/byimplication/sakay/model/Alternative;", "headsign", "routeLongName", "(ZIJJLjava/util/List;JLjava/util/List;Ljava/lang/Double;Ljava/lang/String;ILjava/lang/String;ZLcom/byimplication/sakay/model/LegGeometry;ZZLcom/byimplication/sakay/model/TripPoint;Ljava/lang/String;Ljava/lang/String;DLcom/byimplication/sakay/model/Conveyance;ZILcom/byimplication/sakay/model/TripPoint;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getAgencyTimeZoneOffset", "()J", "getAlternatives", "()Ljava/util/List;", "getArrivalDelay", "()I", "getConveyance", "()Lcom/byimplication/sakay/model/Conveyance;", "getDepartureDelay", "getDistance", "()D", "getDuration", "getEndTime", "getFare", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getFareId", "()Ljava/lang/String;", "getFrom", "()Lcom/byimplication/sakay/model/TripPoint;", "getHeadsign", "getIncidentIDs", "getInterlineWithPreviousLeg", "()Z", "getLegGeometry", "()Lcom/byimplication/sakay/model/LegGeometry;", "getPathway", "getRealTime", "getRentedBike", "getRoute", "getRouteId", "getRouteLongName", "getStartTime", "getSteps", "getTo", "getTransitLeg", "getTripId", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(ZIJJLjava/util/List;JLjava/util/List;Ljava/lang/Double;Ljava/lang/String;ILjava/lang/String;ZLcom/byimplication/sakay/model/LegGeometry;ZZLcom/byimplication/sakay/model/TripPoint;Ljava/lang/String;Ljava/lang/String;DLcom/byimplication/sakay/model/Conveyance;ZILcom/byimplication/sakay/model/TripPoint;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)Lcom/byimplication/sakay/model/TripLeg;", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class TripLeg
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<TripLeg> CREATOR = (Parcelable.Creator)new TripLeg.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  private final long agencyTimeZoneOffset;
  @Nullable
  private final List<Alternative> alternatives;
  private final int arrivalDelay;
  @NotNull
  private final Conveyance conveyance;
  private final int departureDelay;
  private final double distance;
  private final int duration;
  private final long endTime;
  @Nullable
  private final Double fare;
  @Nullable
  private final String fareId;
  @NotNull
  private final TripPoint from;
  @Nullable
  private final String headsign;
  @Nullable
  private final List<Long> incidentIDs;
  private final boolean interlineWithPreviousLeg;
  @NotNull
  private final LegGeometry legGeometry;
  private final boolean pathway;
  private final boolean realTime;
  private final boolean rentedBike;
  @NotNull
  private final String route;
  @Nullable
  private final String routeId;
  @Nullable
  private final String routeLongName;
  private final long startTime;
  @NotNull
  private final List<TripStep> steps;
  @NotNull
  private final TripPoint to;
  private final boolean transitLeg;
  @Nullable
  private final String tripId;
  
  public TripLeg(@NotNull Parcel paramParcel)
  {
    this(bool1, i, l1, l2, localList, l3, (List)localObject1, localDouble, str1, j, str2, bool2, (LegGeometry)localObject2, bool3, bool4, (TripPoint)localObject3, str3, str4, d, (Conveyance)localObject4, bool5, k, (TripPoint)localParcelable, (List)paramParcel.createTypedArrayList(Alternative.CREATOR), paramParcel.readString(), paramParcel.readString());
  }
  
  public TripLeg(boolean paramBoolean1, int paramInt1, long paramLong1, long paramLong2, @Nullable List<Long> paramList, long paramLong3, @NotNull List<TripStep> paramList1, @Nullable Double paramDouble, @Nullable String paramString1, int paramInt2, @NotNull String paramString2, boolean paramBoolean2, @NotNull LegGeometry paramLegGeometry, boolean paramBoolean3, boolean paramBoolean4, @NotNull TripPoint paramTripPoint1, @Nullable String paramString3, @Nullable String paramString4, double paramDouble1, @NotNull Conveyance paramConveyance, boolean paramBoolean5, int paramInt3, @NotNull TripPoint paramTripPoint2, @Nullable List<Alternative> paramList2, @Nullable String paramString5, @Nullable String paramString6)
  {
    rentedBike = paramBoolean1;
    departureDelay = paramInt1;
    agencyTimeZoneOffset = paramLong1;
    startTime = paramLong2;
    incidentIDs = paramList;
    endTime = paramLong3;
    steps = paramList1;
    fare = paramDouble;
    fareId = paramString1;
    duration = paramInt2;
    route = paramString2;
    interlineWithPreviousLeg = paramBoolean2;
    legGeometry = paramLegGeometry;
    realTime = paramBoolean3;
    pathway = paramBoolean4;
    from = paramTripPoint1;
    routeId = paramString3;
    tripId = paramString4;
    distance = paramDouble1;
    conveyance = paramConveyance;
    transitLeg = paramBoolean5;
    arrivalDelay = paramInt3;
    to = paramTripPoint2;
    alternatives = paramList2;
    headsign = paramString5;
    routeLongName = paramString6;
  }
  
  public final boolean component1()
  {
    return rentedBike;
  }
  
  public final int component10()
  {
    return duration;
  }
  
  @NotNull
  public final String component11()
  {
    return route;
  }
  
  public final boolean component12()
  {
    return interlineWithPreviousLeg;
  }
  
  @NotNull
  public final LegGeometry component13()
  {
    return legGeometry;
  }
  
  public final boolean component14()
  {
    return realTime;
  }
  
  public final boolean component15()
  {
    return pathway;
  }
  
  @NotNull
  public final TripPoint component16()
  {
    return from;
  }
  
  @Nullable
  public final String component17()
  {
    return routeId;
  }
  
  @Nullable
  public final String component18()
  {
    return tripId;
  }
  
  public final double component19()
  {
    return distance;
  }
  
  public final int component2()
  {
    return departureDelay;
  }
  
  @NotNull
  public final Conveyance component20()
  {
    return conveyance;
  }
  
  public final boolean component21()
  {
    return transitLeg;
  }
  
  public final int component22()
  {
    return arrivalDelay;
  }
  
  @NotNull
  public final TripPoint component23()
  {
    return to;
  }
  
  @Nullable
  public final List<Alternative> component24()
  {
    return alternatives;
  }
  
  @Nullable
  public final String component25()
  {
    return headsign;
  }
  
  @Nullable
  public final String component26()
  {
    return routeLongName;
  }
  
  public final long component3()
  {
    return agencyTimeZoneOffset;
  }
  
  public final long component4()
  {
    return startTime;
  }
  
  @Nullable
  public final List<Long> component5()
  {
    return incidentIDs;
  }
  
  public final long component6()
  {
    return endTime;
  }
  
  @NotNull
  public final List<TripStep> component7()
  {
    return steps;
  }
  
  @Nullable
  public final Double component8()
  {
    return fare;
  }
  
  @Nullable
  public final String component9()
  {
    return fareId;
  }
  
  @NotNull
  public final TripLeg copy(boolean paramBoolean1, int paramInt1, long paramLong1, long paramLong2, @Nullable List<Long> paramList, long paramLong3, @NotNull List<TripStep> paramList1, @Nullable Double paramDouble, @Nullable String paramString1, int paramInt2, @NotNull String paramString2, boolean paramBoolean2, @NotNull LegGeometry paramLegGeometry, boolean paramBoolean3, boolean paramBoolean4, @NotNull TripPoint paramTripPoint1, @Nullable String paramString3, @Nullable String paramString4, double paramDouble1, @NotNull Conveyance paramConveyance, boolean paramBoolean5, int paramInt3, @NotNull TripPoint paramTripPoint2, @Nullable List<Alternative> paramList2, @Nullable String paramString5, @Nullable String paramString6)
  {
    Intrinsics.checkParameterIsNotNull(paramList1, "steps");
    Intrinsics.checkParameterIsNotNull(paramString2, "route");
    Intrinsics.checkParameterIsNotNull(paramLegGeometry, "legGeometry");
    Intrinsics.checkParameterIsNotNull(paramTripPoint1, "from");
    Intrinsics.checkParameterIsNotNull(paramConveyance, "conveyance");
    Intrinsics.checkParameterIsNotNull(paramTripPoint2, "to");
    return new TripLeg(paramBoolean1, paramInt1, paramLong1, paramLong2, paramList, paramLong3, paramList1, paramDouble, paramString1, paramInt2, paramString2, paramBoolean2, paramLegGeometry, paramBoolean3, paramBoolean4, paramTripPoint1, paramString3, paramString4, paramDouble1, paramConveyance, paramBoolean5, paramInt3, paramTripPoint2, paramList2, paramString5, paramString6);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof TripLeg))
      {
        paramObject = (TripLeg)paramObject;
        int i;
        if (rentedBike == rentedBike) {
          i = 1;
        } else {
          i = 0;
        }
        if (i != 0)
        {
          if (departureDelay == departureDelay) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0)
          {
            if (agencyTimeZoneOffset == agencyTimeZoneOffset) {
              i = 1;
            } else {
              i = 0;
            }
            if (i != 0)
            {
              if (startTime == startTime) {
                i = 1;
              } else {
                i = 0;
              }
              if ((i != 0) && (Intrinsics.areEqual(incidentIDs, incidentIDs)))
              {
                if (endTime == endTime) {
                  i = 1;
                } else {
                  i = 0;
                }
                if ((i != 0) && (Intrinsics.areEqual(steps, steps)) && (Intrinsics.areEqual(fare, fare)) && (Intrinsics.areEqual(fareId, fareId)))
                {
                  if (duration == duration) {
                    i = 1;
                  } else {
                    i = 0;
                  }
                  if ((i != 0) && (Intrinsics.areEqual(route, route)))
                  {
                    if (interlineWithPreviousLeg == interlineWithPreviousLeg) {
                      i = 1;
                    } else {
                      i = 0;
                    }
                    if ((i != 0) && (Intrinsics.areEqual(legGeometry, legGeometry)))
                    {
                      if (realTime == realTime) {
                        i = 1;
                      } else {
                        i = 0;
                      }
                      if (i != 0)
                      {
                        if (pathway == pathway) {
                          i = 1;
                        } else {
                          i = 0;
                        }
                        if ((i != 0) && (Intrinsics.areEqual(from, from)) && (Intrinsics.areEqual(routeId, routeId)) && (Intrinsics.areEqual(tripId, tripId)) && (Double.compare(distance, distance) == 0) && (Intrinsics.areEqual(conveyance, conveyance)))
                        {
                          if (transitLeg == transitLeg) {
                            i = 1;
                          } else {
                            i = 0;
                          }
                          if (i != 0)
                          {
                            if (arrivalDelay == arrivalDelay) {
                              i = 1;
                            } else {
                              i = 0;
                            }
                            if ((i != 0) && (Intrinsics.areEqual(to, to)) && (Intrinsics.areEqual(alternatives, alternatives)) && (Intrinsics.areEqual(headsign, headsign)) && (Intrinsics.areEqual(routeLongName, routeLongName))) {
                              return true;
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      return false;
    }
    return true;
  }
  
  public final long getAgencyTimeZoneOffset()
  {
    return agencyTimeZoneOffset;
  }
  
  @Nullable
  public final List<Alternative> getAlternatives()
  {
    return alternatives;
  }
  
  public final int getArrivalDelay()
  {
    return arrivalDelay;
  }
  
  @NotNull
  public final Conveyance getConveyance()
  {
    return conveyance;
  }
  
  public final int getDepartureDelay()
  {
    return departureDelay;
  }
  
  public final double getDistance()
  {
    return distance;
  }
  
  public final int getDuration()
  {
    return duration;
  }
  
  public final long getEndTime()
  {
    return endTime;
  }
  
  @Nullable
  public final Double getFare()
  {
    return fare;
  }
  
  @Nullable
  public final String getFareId()
  {
    return fareId;
  }
  
  @NotNull
  public final TripPoint getFrom()
  {
    return from;
  }
  
  @Nullable
  public final String getHeadsign()
  {
    return headsign;
  }
  
  @Nullable
  public final List<Long> getIncidentIDs()
  {
    return incidentIDs;
  }
  
  public final boolean getInterlineWithPreviousLeg()
  {
    return interlineWithPreviousLeg;
  }
  
  @NotNull
  public final LegGeometry getLegGeometry()
  {
    return legGeometry;
  }
  
  public final boolean getPathway()
  {
    return pathway;
  }
  
  public final boolean getRealTime()
  {
    return realTime;
  }
  
  public final boolean getRentedBike()
  {
    return rentedBike;
  }
  
  @NotNull
  public final String getRoute()
  {
    return route;
  }
  
  @Nullable
  public final String getRouteId()
  {
    return routeId;
  }
  
  @Nullable
  public final String getRouteLongName()
  {
    return routeLongName;
  }
  
  public final long getStartTime()
  {
    return startTime;
  }
  
  @NotNull
  public final List<TripStep> getSteps()
  {
    return steps;
  }
  
  @NotNull
  public final TripPoint getTo()
  {
    return to;
  }
  
  public final boolean getTransitLeg()
  {
    return transitLeg;
  }
  
  @Nullable
  public final String getTripId()
  {
    return tripId;
  }
  
  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("TripLeg(rentedBike=");
    localStringBuilder.append(rentedBike);
    localStringBuilder.append(", departureDelay=");
    localStringBuilder.append(departureDelay);
    localStringBuilder.append(", agencyTimeZoneOffset=");
    localStringBuilder.append(agencyTimeZoneOffset);
    localStringBuilder.append(", startTime=");
    localStringBuilder.append(startTime);
    localStringBuilder.append(", incidentIDs=");
    localStringBuilder.append(incidentIDs);
    localStringBuilder.append(", endTime=");
    localStringBuilder.append(endTime);
    localStringBuilder.append(", steps=");
    localStringBuilder.append(steps);
    localStringBuilder.append(", fare=");
    localStringBuilder.append(fare);
    localStringBuilder.append(", fareId=");
    localStringBuilder.append(fareId);
    localStringBuilder.append(", duration=");
    localStringBuilder.append(duration);
    localStringBuilder.append(", route=");
    localStringBuilder.append(route);
    localStringBuilder.append(", interlineWithPreviousLeg=");
    localStringBuilder.append(interlineWithPreviousLeg);
    localStringBuilder.append(", legGeometry=");
    localStringBuilder.append(legGeometry);
    localStringBuilder.append(", realTime=");
    localStringBuilder.append(realTime);
    localStringBuilder.append(", pathway=");
    localStringBuilder.append(pathway);
    localStringBuilder.append(", from=");
    localStringBuilder.append(from);
    localStringBuilder.append(", routeId=");
    localStringBuilder.append(routeId);
    localStringBuilder.append(", tripId=");
    localStringBuilder.append(tripId);
    localStringBuilder.append(", distance=");
    localStringBuilder.append(distance);
    localStringBuilder.append(", conveyance=");
    localStringBuilder.append(conveyance);
    localStringBuilder.append(", transitLeg=");
    localStringBuilder.append(transitLeg);
    localStringBuilder.append(", arrivalDelay=");
    localStringBuilder.append(arrivalDelay);
    localStringBuilder.append(", to=");
    localStringBuilder.append(to);
    localStringBuilder.append(", alternatives=");
    localStringBuilder.append(alternatives);
    localStringBuilder.append(", headsign=");
    localStringBuilder.append(headsign);
    localStringBuilder.append(", routeLongName=");
    localStringBuilder.append(routeLongName);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\"\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\002\b\002\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\024\020\006\032\b\022\004\022\0020\b0\0072\006\020\t\032\0020\005R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\n"}, d2={"Lcom/byimplication/sakay/model/TripLeg$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/TripLeg;", "getPoints", "", "Lcom/google/android/gms/maps/model/LatLng;", "leg", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
    
    @NotNull
    public final List<LatLng> getPoints(@NotNull TripLeg paramTripLeg)
    {
      Intrinsics.checkParameterIsNotNull(paramTripLeg, "leg");
      paramTripLeg = PolyUtil.decode(paramTripLeg.getLegGeometry().getPoints());
      Intrinsics.checkExpressionValueIsNotNull(paramTripLeg, "PolyUtil.decode(leg.legGeometry.points)");
      return paramTripLeg;
    }
  }
}

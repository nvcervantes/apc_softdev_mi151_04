package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000T\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\020\t\n\000\n\002\020 \n\002\b\002\n\002\020\006\n\002\b\002\n\002\020\013\n\002\b\004\n\002\030\002\n\002\b)\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\002\n\002\b\004\b\b\030\000 G2\0020\001:\001GB\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B\001\022\006\020\005\032\0020\006\022\006\020\007\032\0020\b\022\016\020\t\032\n\022\004\022\0020\b\030\0010\n\022\006\020\013\032\0020\b\022\006\020\f\032\0020\r\022\006\020\016\032\0020\b\022\006\020\017\032\0020\020\022\006\020\021\032\0020\020\022\006\020\022\032\0020\006\022\006\020\023\032\0020\r\022\f\020\024\032\b\022\004\022\0020\0250\n\022\006\020\026\032\0020\006\022\006\020\027\032\0020\b\022\006\020\030\032\0020\006¢\006\002\020\031J\t\020-\032\0020\006HÆ\003J\t\020.\032\0020\rHÆ\003J\017\020/\032\b\022\004\022\0020\0250\nHÆ\003J\t\0200\032\0020\006HÆ\003J\t\0201\032\0020\bHÆ\003J\t\0202\032\0020\006HÆ\003J\t\0203\032\0020\bHÆ\003J\021\0204\032\n\022\004\022\0020\b\030\0010\nHÆ\003J\t\0205\032\0020\bHÆ\003J\t\0206\032\0020\rHÆ\003J\t\0207\032\0020\bHÆ\003J\t\0208\032\0020\020HÆ\003J\t\0209\032\0020\020HÆ\003J\t\020:\032\0020\006HÆ\003J£\001\020;\032\0020\0002\b\b\002\020\005\032\0020\0062\b\b\002\020\007\032\0020\b2\020\b\002\020\t\032\n\022\004\022\0020\b\030\0010\n2\b\b\002\020\013\032\0020\b2\b\b\002\020\f\032\0020\r2\b\b\002\020\016\032\0020\b2\b\b\002\020\017\032\0020\0202\b\b\002\020\021\032\0020\0202\b\b\002\020\022\032\0020\0062\b\b\002\020\023\032\0020\r2\016\b\002\020\024\032\b\022\004\022\0020\0250\n2\b\b\002\020\026\032\0020\0062\b\b\002\020\027\032\0020\b2\b\b\002\020\030\032\0020\006HÆ\001J\b\020<\032\0020\006H\026J\023\020=\032\0020\0202\b\020>\032\004\030\0010?HÖ\003J\t\020@\032\0020\006HÖ\001J\t\020A\032\0020BHÖ\001J\030\020C\032\0020D2\006\020E\032\0020\0032\006\020F\032\0020\006H\026R\021\020\016\032\0020\b¢\006\b\n\000\032\004\b\032\020\033R\021\020\f\032\0020\r¢\006\b\n\000\032\004\b\034\020\035R\021\020\030\032\0020\006¢\006\b\n\000\032\004\b\036\020\037R\021\020\013\032\0020\b¢\006\b\n\000\032\004\b \020\033R\031\020\t\032\n\022\004\022\0020\b\030\0010\n¢\006\b\n\000\032\004\b!\020\"R\027\020\024\032\b\022\004\022\0020\0250\n¢\006\b\n\000\032\004\b#\020\"R\021\020\007\032\0020\b¢\006\b\n\000\032\004\b$\020\033R\021\020\021\032\0020\020¢\006\b\n\000\032\004\b%\020&R\021\020\026\032\0020\006¢\006\b\n\000\032\004\b'\020\037R\021\020\027\032\0020\b¢\006\b\n\000\032\004\b(\020\033R\021\020\005\032\0020\006¢\006\b\n\000\032\004\b)\020\037R\021\020\023\032\0020\r¢\006\b\n\000\032\004\b*\020\035R\021\020\017\032\0020\020¢\006\b\n\000\032\004\b+\020&R\021\020\022\032\0020\006¢\006\b\n\000\032\004\b,\020\037¨\006H"}, d2={"Lcom/byimplication/sakay/model/Itinerary;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "waitingTime", "", "startTime", "", "incidentIDs", "", "endTime", "elevationGained", "", "duration", "walkLimitExceeded", "", "tooSloped", "walkTime", "walkDistance", "legs", "Lcom/byimplication/sakay/model/TripLeg;", "transfers", "transitTime", "elevationLost", "(IJLjava/util/List;JDJZZIDLjava/util/List;IJI)V", "getDuration", "()J", "getElevationGained", "()D", "getElevationLost", "()I", "getEndTime", "getIncidentIDs", "()Ljava/util/List;", "getLegs", "getStartTime", "getTooSloped", "()Z", "getTransfers", "getTransitTime", "getWaitingTime", "getWalkDistance", "getWalkLimitExceeded", "getWalkTime", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "other", "", "hashCode", "toString", "", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class Itinerary
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<Itinerary> CREATOR = (Parcelable.Creator)new Itinerary.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  private final long duration;
  private final double elevationGained;
  private final int elevationLost;
  private final long endTime;
  @Nullable
  private final List<Long> incidentIDs;
  @NotNull
  private final List<TripLeg> legs;
  private final long startTime;
  private final boolean tooSloped;
  private final int transfers;
  private final long transitTime;
  private final int waitingTime;
  private final double walkDistance;
  private final boolean walkLimitExceeded;
  private final int walkTime;
  
  public Itinerary(int paramInt1, long paramLong1, @Nullable List<Long> paramList, long paramLong2, double paramDouble1, long paramLong3, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, double paramDouble2, @NotNull List<TripLeg> paramList1, int paramInt3, long paramLong4, int paramInt4)
  {
    waitingTime = paramInt1;
    startTime = paramLong1;
    incidentIDs = paramList;
    endTime = paramLong2;
    elevationGained = paramDouble1;
    duration = paramLong3;
    walkLimitExceeded = paramBoolean1;
    tooSloped = paramBoolean2;
    walkTime = paramInt2;
    walkDistance = paramDouble2;
    legs = paramList1;
    transfers = paramInt3;
    transitTime = paramLong4;
    elevationLost = paramInt4;
  }
  
  public Itinerary(@NotNull Parcel paramParcel)
  {
    this(i, l1, localList, l2, d1, l3, bool1, bool2, j, d2, (List)localArrayList, paramParcel.readInt(), paramParcel.readLong(), paramParcel.readInt());
  }
  
  public final int component1()
  {
    return waitingTime;
  }
  
  public final double component10()
  {
    return walkDistance;
  }
  
  @NotNull
  public final List<TripLeg> component11()
  {
    return legs;
  }
  
  public final int component12()
  {
    return transfers;
  }
  
  public final long component13()
  {
    return transitTime;
  }
  
  public final int component14()
  {
    return elevationLost;
  }
  
  public final long component2()
  {
    return startTime;
  }
  
  @Nullable
  public final List<Long> component3()
  {
    return incidentIDs;
  }
  
  public final long component4()
  {
    return endTime;
  }
  
  public final double component5()
  {
    return elevationGained;
  }
  
  public final long component6()
  {
    return duration;
  }
  
  public final boolean component7()
  {
    return walkLimitExceeded;
  }
  
  public final boolean component8()
  {
    return tooSloped;
  }
  
  public final int component9()
  {
    return walkTime;
  }
  
  @NotNull
  public final Itinerary copy(int paramInt1, long paramLong1, @Nullable List<Long> paramList, long paramLong2, double paramDouble1, long paramLong3, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, double paramDouble2, @NotNull List<TripLeg> paramList1, int paramInt3, long paramLong4, int paramInt4)
  {
    Intrinsics.checkParameterIsNotNull(paramList1, "legs");
    return new Itinerary(paramInt1, paramLong1, paramList, paramLong2, paramDouble1, paramLong3, paramBoolean1, paramBoolean2, paramInt2, paramDouble2, paramList1, paramInt3, paramLong4, paramInt4);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof Itinerary))
      {
        paramObject = (Itinerary)paramObject;
        int i;
        if (waitingTime == waitingTime) {
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
            if ((i != 0) && (Double.compare(elevationGained, elevationGained) == 0))
            {
              if (duration == duration) {
                i = 1;
              } else {
                i = 0;
              }
              if (i != 0)
              {
                if (walkLimitExceeded == walkLimitExceeded) {
                  i = 1;
                } else {
                  i = 0;
                }
                if (i != 0)
                {
                  if (tooSloped == tooSloped) {
                    i = 1;
                  } else {
                    i = 0;
                  }
                  if (i != 0)
                  {
                    if (walkTime == walkTime) {
                      i = 1;
                    } else {
                      i = 0;
                    }
                    if ((i != 0) && (Double.compare(walkDistance, walkDistance) == 0) && (Intrinsics.areEqual(legs, legs)))
                    {
                      if (transfers == transfers) {
                        i = 1;
                      } else {
                        i = 0;
                      }
                      if (i != 0)
                      {
                        if (transitTime == transitTime) {
                          i = 1;
                        } else {
                          i = 0;
                        }
                        if (i != 0)
                        {
                          if (elevationLost == elevationLost) {
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
  
  public final long getDuration()
  {
    return duration;
  }
  
  public final double getElevationGained()
  {
    return elevationGained;
  }
  
  public final int getElevationLost()
  {
    return elevationLost;
  }
  
  public final long getEndTime()
  {
    return endTime;
  }
  
  @Nullable
  public final List<Long> getIncidentIDs()
  {
    return incidentIDs;
  }
  
  @NotNull
  public final List<TripLeg> getLegs()
  {
    return legs;
  }
  
  public final long getStartTime()
  {
    return startTime;
  }
  
  public final boolean getTooSloped()
  {
    return tooSloped;
  }
  
  public final int getTransfers()
  {
    return transfers;
  }
  
  public final long getTransitTime()
  {
    return transitTime;
  }
  
  public final int getWaitingTime()
  {
    return waitingTime;
  }
  
  public final double getWalkDistance()
  {
    return walkDistance;
  }
  
  public final boolean getWalkLimitExceeded()
  {
    return walkLimitExceeded;
  }
  
  public final int getWalkTime()
  {
    return walkTime;
  }
  
  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Itinerary(waitingTime=");
    localStringBuilder.append(waitingTime);
    localStringBuilder.append(", startTime=");
    localStringBuilder.append(startTime);
    localStringBuilder.append(", incidentIDs=");
    localStringBuilder.append(incidentIDs);
    localStringBuilder.append(", endTime=");
    localStringBuilder.append(endTime);
    localStringBuilder.append(", elevationGained=");
    localStringBuilder.append(elevationGained);
    localStringBuilder.append(", duration=");
    localStringBuilder.append(duration);
    localStringBuilder.append(", walkLimitExceeded=");
    localStringBuilder.append(walkLimitExceeded);
    localStringBuilder.append(", tooSloped=");
    localStringBuilder.append(tooSloped);
    localStringBuilder.append(", walkTime=");
    localStringBuilder.append(walkTime);
    localStringBuilder.append(", walkDistance=");
    localStringBuilder.append(walkDistance);
    localStringBuilder.append(", legs=");
    localStringBuilder.append(legs);
    localStringBuilder.append(", transfers=");
    localStringBuilder.append(transfers);
    localStringBuilder.append(", transitTime=");
    localStringBuilder.append(transitTime);
    localStringBuilder.append(", elevationLost=");
    localStringBuilder.append(elevationLost);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\0002\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\n\002\020\006\n\002\b\002\n\002\020 \n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\016\020\006\032\0020\0072\006\020\b\032\0020\005J \020\t\032\024\022\020\022\016\022\004\022\0020\f\022\004\022\0020\r0\0130\n2\006\020\b\032\0020\005J\024\020\016\032\b\022\004\022\0020\r0\n2\006\020\b\032\0020\005R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\017"}, d2={"Lcom/byimplication/sakay/model/Itinerary$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/Itinerary;", "getFare", "", "itinerary", "getModePoints", "", "Lkotlin/Pair;", "Lcom/byimplication/sakay/model/Conveyance;", "Lcom/google/android/gms/maps/model/LatLng;", "getPath", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
    
    public final double getFare(@NotNull Itinerary paramItinerary)
    {
      Intrinsics.checkParameterIsNotNull(paramItinerary, "itinerary");
      paramItinerary = paramItinerary.getLegs().iterator();
      double d = 0.0D;
      while (paramItinerary.hasNext())
      {
        TripLeg localTripLeg = (TripLeg)paramItinerary.next();
        if (localTripLeg.getFare() != null) {
          d += localTripLeg.getFare().doubleValue();
        }
      }
      return d;
    }
    
    @NotNull
    public final List<Pair<Conveyance, LatLng>> getModePoints(@NotNull Itinerary paramItinerary)
    {
      Intrinsics.checkParameterIsNotNull(paramItinerary, "itinerary");
      ArrayList localArrayList = new ArrayList();
      paramItinerary = paramItinerary.getLegs().iterator();
      while (paramItinerary.hasNext())
      {
        TripLeg localTripLeg = (TripLeg)paramItinerary.next();
        LatLng localLatLng = (LatLng)TripLeg.Companion.getPoints(localTripLeg).get(0);
        ((Collection)localArrayList).add(new Pair(localTripLeg.getConveyance(), new LatLng(latitude, longitude)));
      }
      return CollectionsKt.toList((Iterable)localArrayList);
    }
    
    @NotNull
    public final List<LatLng> getPath(@NotNull Itinerary paramItinerary)
    {
      Intrinsics.checkParameterIsNotNull(paramItinerary, "itinerary");
      ArrayList localArrayList = new ArrayList();
      paramItinerary = paramItinerary.getLegs().iterator();
      while (paramItinerary.hasNext())
      {
        TripLeg localTripLeg = (TripLeg)paramItinerary.next();
        localArrayList.addAll((Collection)TripLeg.Companion.getPoints(localTripLeg));
      }
      return CollectionsKt.toList((Iterable)localArrayList);
    }
  }
}

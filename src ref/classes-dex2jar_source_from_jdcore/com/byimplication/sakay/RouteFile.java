package com.byimplication.sakay;

import com.byimplication.sakay.model.ScheduleType;
import com.google.android.gms.maps.model.LatLng;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0008\n\002\030\002\n\002\020\000\n\000\n\002\020\t\n\000\n\002\020\016\n\000\n\002\030\002\n\002\b\006\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\b\n\000\n\002\020\013\n\002\b:\b\b\030\0002\0020\001B\001\022\b\020\002\032\004\030\0010\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007\022\006\020\b\032\0020\005\022\006\020\t\032\0020\005\022\006\020\n\032\0020\007\022\006\020\013\032\0020\005\022\006\020\f\032\0020\005\022\n\b\002\020\r\032\004\030\0010\016\022\b\b\002\020\017\032\0020\020\022\b\b\002\020\021\032\0020\022\022\b\b\002\020\023\032\0020\024\022\006\020\025\032\0020\005\022\b\b\002\020\026\032\0020\003¢\006\002\020\027J\020\020:\032\004\030\0010\003HÆ\003¢\006\002\0208J\t\020;\032\0020\020HÆ\003J\t\020<\032\0020\022HÆ\003J\t\020=\032\0020\024HÆ\003J\t\020>\032\0020\005HÆ\003J\t\020?\032\0020\003HÆ\003J\t\020@\032\0020\005HÆ\003J\t\020A\032\0020\007HÆ\003J\t\020B\032\0020\005HÆ\003J\t\020C\032\0020\005HÆ\003J\t\020D\032\0020\007HÆ\003J\t\020E\032\0020\005HÆ\003J\t\020F\032\0020\005HÆ\003J\013\020G\032\004\030\0010\016HÆ\003J\001\020H\032\0020\0002\n\b\002\020\002\032\004\030\0010\0032\b\b\002\020\004\032\0020\0052\b\b\002\020\006\032\0020\0072\b\b\002\020\b\032\0020\0052\b\b\002\020\t\032\0020\0052\b\b\002\020\n\032\0020\0072\b\b\002\020\013\032\0020\0052\b\b\002\020\f\032\0020\0052\n\b\002\020\r\032\004\030\0010\0162\b\b\002\020\017\032\0020\0202\b\b\002\020\021\032\0020\0222\b\b\002\020\023\032\0020\0242\b\b\002\020\025\032\0020\0052\b\b\002\020\026\032\0020\003HÆ\001¢\006\002\020IJ\023\020J\032\0020\0242\b\020K\032\004\030\0010\001HÖ\003J\t\020L\032\0020\022HÖ\001J\t\020M\032\0020\005HÖ\001R\034\020\r\032\004\030\0010\016X\016¢\006\016\n\000\032\004\b\030\020\031\"\004\b\032\020\033R\021\020\n\032\0020\007¢\006\b\n\000\032\004\b\034\020\035R\032\020\f\032\0020\005X\016¢\006\016\n\000\032\004\b\036\020\037\"\004\b \020!R\021\020\013\032\0020\005¢\006\b\n\000\032\004\b\"\020\037R\021\020\004\032\0020\005¢\006\b\n\000\032\004\b#\020\037R\032\020\021\032\0020\022X\016¢\006\016\n\000\032\004\b$\020%\"\004\b&\020'R\032\020\023\032\0020\024X\016¢\006\016\n\000\032\004\b\023\020(\"\004\b)\020*R\021\020\006\032\0020\007¢\006\b\n\000\032\004\b+\020\035R\032\020\t\032\0020\005X\016¢\006\016\n\000\032\004\b,\020\037\"\004\b-\020!R\021\020\b\032\0020\005¢\006\b\n\000\032\004\b.\020\037R\032\020\025\032\0020\005X\016¢\006\016\n\000\032\004\b/\020\037\"\004\b0\020!R\021\020\026\032\0020\003¢\006\b\n\000\032\004\b1\0202R\032\020\017\032\0020\020X\016¢\006\016\n\000\032\004\b3\0204\"\004\b5\0206R\025\020\002\032\004\030\0010\003¢\006\n\n\002\0209\032\004\b7\0208¨\006N"}, d2={"Lcom/byimplication/sakay/RouteFile;", "", "searchId", "", "filename", "", "origin", "Lcom/google/android/gms/maps/model/LatLng;", "originName", "originAlias", "destination", "destinationName", "destinationAlias", "departureTime", "Ljava/util/Date;", "scheduleType", "Lcom/byimplication/sakay/model/ScheduleType;", "id", "", "isGeocoded", "", "routeName", "savedOn", "(Ljava/lang/Long;Ljava/lang/String;Lcom/google/android/gms/maps/model/LatLng;Ljava/lang/String;Ljava/lang/String;Lcom/google/android/gms/maps/model/LatLng;Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;Lcom/byimplication/sakay/model/ScheduleType;IZLjava/lang/String;J)V", "getDepartureTime", "()Ljava/util/Date;", "setDepartureTime", "(Ljava/util/Date;)V", "getDestination", "()Lcom/google/android/gms/maps/model/LatLng;", "getDestinationAlias", "()Ljava/lang/String;", "setDestinationAlias", "(Ljava/lang/String;)V", "getDestinationName", "getFilename", "getId", "()I", "setId", "(I)V", "()Z", "setGeocoded", "(Z)V", "getOrigin", "getOriginAlias", "setOriginAlias", "getOriginName", "getRouteName", "setRouteName", "getSavedOn", "()J", "getScheduleType", "()Lcom/byimplication/sakay/model/ScheduleType;", "setScheduleType", "(Lcom/byimplication/sakay/model/ScheduleType;)V", "getSearchId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Long;Ljava/lang/String;Lcom/google/android/gms/maps/model/LatLng;Ljava/lang/String;Ljava/lang/String;Lcom/google/android/gms/maps/model/LatLng;Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;Lcom/byimplication/sakay/model/ScheduleType;IZLjava/lang/String;J)Lcom/byimplication/sakay/RouteFile;", "equals", "other", "hashCode", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class RouteFile
{
  @Nullable
  private Date departureTime;
  @NotNull
  private final LatLng destination;
  @NotNull
  private String destinationAlias;
  @NotNull
  private final String destinationName;
  @NotNull
  private final String filename;
  private int id;
  private boolean isGeocoded;
  @NotNull
  private final LatLng origin;
  @NotNull
  private String originAlias;
  @NotNull
  private final String originName;
  @NotNull
  private String routeName;
  private final long savedOn;
  @NotNull
  private ScheduleType scheduleType;
  @Nullable
  private final Long searchId;
  
  public RouteFile(@Nullable Long paramLong, @NotNull String paramString1, @NotNull LatLng paramLatLng1, @NotNull String paramString2, @NotNull String paramString3, @NotNull LatLng paramLatLng2, @NotNull String paramString4, @NotNull String paramString5, @Nullable Date paramDate, @NotNull ScheduleType paramScheduleType, int paramInt, boolean paramBoolean, @NotNull String paramString6, long paramLong1)
  {
    searchId = paramLong;
    filename = paramString1;
    origin = paramLatLng1;
    originName = paramString2;
    originAlias = paramString3;
    destination = paramLatLng2;
    destinationName = paramString4;
    destinationAlias = paramString5;
    departureTime = paramDate;
    scheduleType = paramScheduleType;
    id = paramInt;
    isGeocoded = paramBoolean;
    routeName = paramString6;
    savedOn = paramLong1;
  }
  
  @Nullable
  public final Long component1()
  {
    return searchId;
  }
  
  @NotNull
  public final ScheduleType component10()
  {
    return scheduleType;
  }
  
  public final int component11()
  {
    return id;
  }
  
  public final boolean component12()
  {
    return isGeocoded;
  }
  
  @NotNull
  public final String component13()
  {
    return routeName;
  }
  
  public final long component14()
  {
    return savedOn;
  }
  
  @NotNull
  public final String component2()
  {
    return filename;
  }
  
  @NotNull
  public final LatLng component3()
  {
    return origin;
  }
  
  @NotNull
  public final String component4()
  {
    return originName;
  }
  
  @NotNull
  public final String component5()
  {
    return originAlias;
  }
  
  @NotNull
  public final LatLng component6()
  {
    return destination;
  }
  
  @NotNull
  public final String component7()
  {
    return destinationName;
  }
  
  @NotNull
  public final String component8()
  {
    return destinationAlias;
  }
  
  @Nullable
  public final Date component9()
  {
    return departureTime;
  }
  
  @NotNull
  public final RouteFile copy(@Nullable Long paramLong, @NotNull String paramString1, @NotNull LatLng paramLatLng1, @NotNull String paramString2, @NotNull String paramString3, @NotNull LatLng paramLatLng2, @NotNull String paramString4, @NotNull String paramString5, @Nullable Date paramDate, @NotNull ScheduleType paramScheduleType, int paramInt, boolean paramBoolean, @NotNull String paramString6, long paramLong1)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "filename");
    Intrinsics.checkParameterIsNotNull(paramLatLng1, "origin");
    Intrinsics.checkParameterIsNotNull(paramString2, "originName");
    Intrinsics.checkParameterIsNotNull(paramString3, "originAlias");
    Intrinsics.checkParameterIsNotNull(paramLatLng2, "destination");
    Intrinsics.checkParameterIsNotNull(paramString4, "destinationName");
    Intrinsics.checkParameterIsNotNull(paramString5, "destinationAlias");
    Intrinsics.checkParameterIsNotNull(paramScheduleType, "scheduleType");
    Intrinsics.checkParameterIsNotNull(paramString6, "routeName");
    return new RouteFile(paramLong, paramString1, paramLatLng1, paramString2, paramString3, paramLatLng2, paramString4, paramString5, paramDate, paramScheduleType, paramInt, paramBoolean, paramString6, paramLong1);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof RouteFile))
      {
        paramObject = (RouteFile)paramObject;
        if ((Intrinsics.areEqual(searchId, searchId)) && (Intrinsics.areEqual(filename, filename)) && (Intrinsics.areEqual(origin, origin)) && (Intrinsics.areEqual(originName, originName)) && (Intrinsics.areEqual(originAlias, originAlias)) && (Intrinsics.areEqual(destination, destination)) && (Intrinsics.areEqual(destinationName, destinationName)) && (Intrinsics.areEqual(destinationAlias, destinationAlias)) && (Intrinsics.areEqual(departureTime, departureTime)) && (Intrinsics.areEqual(scheduleType, scheduleType)))
        {
          int i;
          if (id == id) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0)
          {
            if (isGeocoded == isGeocoded) {
              i = 1;
            } else {
              i = 0;
            }
            if ((i != 0) && (Intrinsics.areEqual(routeName, routeName)))
            {
              if (savedOn == savedOn) {
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
      return false;
    }
    return true;
  }
  
  @Nullable
  public final Date getDepartureTime()
  {
    return departureTime;
  }
  
  @NotNull
  public final LatLng getDestination()
  {
    return destination;
  }
  
  @NotNull
  public final String getDestinationAlias()
  {
    return destinationAlias;
  }
  
  @NotNull
  public final String getDestinationName()
  {
    return destinationName;
  }
  
  @NotNull
  public final String getFilename()
  {
    return filename;
  }
  
  public final int getId()
  {
    return id;
  }
  
  @NotNull
  public final LatLng getOrigin()
  {
    return origin;
  }
  
  @NotNull
  public final String getOriginAlias()
  {
    return originAlias;
  }
  
  @NotNull
  public final String getOriginName()
  {
    return originName;
  }
  
  @NotNull
  public final String getRouteName()
  {
    return routeName;
  }
  
  public final long getSavedOn()
  {
    return savedOn;
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
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public final boolean isGeocoded()
  {
    return isGeocoded;
  }
  
  public final void setDepartureTime(@Nullable Date paramDate)
  {
    departureTime = paramDate;
  }
  
  public final void setDestinationAlias(@NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "<set-?>");
    destinationAlias = paramString;
  }
  
  public final void setGeocoded(boolean paramBoolean)
  {
    isGeocoded = paramBoolean;
  }
  
  public final void setId(int paramInt)
  {
    id = paramInt;
  }
  
  public final void setOriginAlias(@NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "<set-?>");
    originAlias = paramString;
  }
  
  public final void setRouteName(@NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "<set-?>");
    routeName = paramString;
  }
  
  public final void setScheduleType(@NotNull ScheduleType paramScheduleType)
  {
    Intrinsics.checkParameterIsNotNull(paramScheduleType, "<set-?>");
    scheduleType = paramScheduleType;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("RouteFile(searchId=");
    localStringBuilder.append(searchId);
    localStringBuilder.append(", filename=");
    localStringBuilder.append(filename);
    localStringBuilder.append(", origin=");
    localStringBuilder.append(origin);
    localStringBuilder.append(", originName=");
    localStringBuilder.append(originName);
    localStringBuilder.append(", originAlias=");
    localStringBuilder.append(originAlias);
    localStringBuilder.append(", destination=");
    localStringBuilder.append(destination);
    localStringBuilder.append(", destinationName=");
    localStringBuilder.append(destinationName);
    localStringBuilder.append(", destinationAlias=");
    localStringBuilder.append(destinationAlias);
    localStringBuilder.append(", departureTime=");
    localStringBuilder.append(departureTime);
    localStringBuilder.append(", scheduleType=");
    localStringBuilder.append(scheduleType);
    localStringBuilder.append(", id=");
    localStringBuilder.append(id);
    localStringBuilder.append(", isGeocoded=");
    localStringBuilder.append(isGeocoded);
    localStringBuilder.append(", routeName=");
    localStringBuilder.append(routeName);
    localStringBuilder.append(", savedOn=");
    localStringBuilder.append(savedOn);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

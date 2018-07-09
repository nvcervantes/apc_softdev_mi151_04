package com.byimplication.sakay.model;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\002\b\024\030\0002\0020\001BY\022\b\020\002\032\004\030\0010\003\022\b\020\004\032\004\030\0010\003\022\b\020\005\032\004\030\0010\003\022\006\020\006\032\0020\003\022\006\020\007\032\0020\003\022\006\020\b\032\0020\003\022\b\020\t\032\004\030\0010\003\022\b\020\n\032\004\030\0010\003\022\b\020\013\032\004\030\0010\003¢\006\002\020\fR\021\020\006\032\0020\003¢\006\b\n\000\032\004\b\r\020\016R\023\020\004\032\004\030\0010\003¢\006\b\n\000\032\004\b\017\020\016R\023\020\n\032\004\030\0010\003¢\006\b\n\000\032\004\b\020\020\016R\021\020\007\032\0020\003¢\006\b\n\000\032\004\b\021\020\016R\021\020\b\032\0020\003¢\006\b\n\000\032\004\b\022\020\016R\023\020\t\032\004\030\0010\003¢\006\b\n\000\032\004\b\023\020\016R\023\020\002\032\004\030\0010\003¢\006\b\n\000\032\004\b\024\020\016R\023\020\005\032\004\030\0010\003¢\006\b\n\000\032\004\b\025\020\016R\023\020\013\032\004\030\0010\003¢\006\b\n\000\032\004\b\026\020\016¨\006\027"}, d2={"Lcom/byimplication/sakay/model/RequestParameters;", "", "time", "", "fromPlace", "toPlace", "date", "mode", "numItineraries", "preferredRoutes", "fromPlacePoint", "toPlacePoint", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDate", "()Ljava/lang/String;", "getFromPlace", "getFromPlacePoint", "getMode", "getNumItineraries", "getPreferredRoutes", "getTime", "getToPlace", "getToPlacePoint", "app_release"}, k=1, mv={1, 1, 9})
public final class RequestParameters
{
  @NotNull
  private final String date;
  @Nullable
  private final String fromPlace;
  @Nullable
  private final String fromPlacePoint;
  @NotNull
  private final String mode;
  @NotNull
  private final String numItineraries;
  @Nullable
  private final String preferredRoutes;
  @Nullable
  private final String time;
  @Nullable
  private final String toPlace;
  @Nullable
  private final String toPlacePoint;
  
  public RequestParameters(@Nullable String paramString1, @Nullable String paramString2, @Nullable String paramString3, @NotNull String paramString4, @NotNull String paramString5, @NotNull String paramString6, @Nullable String paramString7, @Nullable String paramString8, @Nullable String paramString9)
  {
    time = paramString1;
    fromPlace = paramString2;
    toPlace = paramString3;
    date = paramString4;
    mode = paramString5;
    numItineraries = paramString6;
    preferredRoutes = paramString7;
    fromPlacePoint = paramString8;
    toPlacePoint = paramString9;
  }
  
  @NotNull
  public final String getDate()
  {
    return date;
  }
  
  @Nullable
  public final String getFromPlace()
  {
    return fromPlace;
  }
  
  @Nullable
  public final String getFromPlacePoint()
  {
    return fromPlacePoint;
  }
  
  @NotNull
  public final String getMode()
  {
    return mode;
  }
  
  @NotNull
  public final String getNumItineraries()
  {
    return numItineraries;
  }
  
  @Nullable
  public final String getPreferredRoutes()
  {
    return preferredRoutes;
  }
  
  @Nullable
  public final String getTime()
  {
    return time;
  }
  
  @Nullable
  public final String getToPlace()
  {
    return toPlace;
  }
  
  @Nullable
  public final String getToPlacePoint()
  {
    return toPlacePoint;
  }
}

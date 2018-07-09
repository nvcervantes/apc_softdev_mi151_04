package com.byimplication.sakay;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000V\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\006\n\002\b\005\n\002\020 \n\002\020\016\n\002\b\003\n\002\030\002\n\002\b\007\n\002\020\b\n\002\b\004\n\002\020\025\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\002\n\000\n\002\020\013\n\002\b\005\bÆ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J\026\020!\032\0020\0332\006\020\"\032\0020#2\006\020$\032\0020\033J\016\020%\032\0020 2\006\020\"\032\0020#J\020\020&\032\0020'2\006\020\"\032\0020#H\002J\016\020(\032\0020)2\006\020*\032\0020\023J\026\020+\032\0020)2\006\020,\032\0020\0232\006\020-\032\0020\023R\021\020\003\032\0020\004¢\006\b\n\000\032\004\b\005\020\006R\033\020\007\032\0020\b8FX\002¢\006\f\n\004\b\013\020\f\032\004\b\t\020\nR\027\020\r\032\b\022\004\022\0020\0170\016¢\006\b\n\000\032\004\b\020\020\021R\021\020\022\032\0020\023¢\006\b\n\000\032\004\b\024\020\025R\021\020\026\032\0020\023¢\006\b\n\000\032\004\b\027\020\025R\027\020\030\032\b\022\004\022\0020\0170\016¢\006\b\n\000\032\004\b\031\020\021R\016\020\032\032\0020\033XT¢\006\002\n\000R\021\020\034\032\0020\033¢\006\b\n\000\032\004\b\035\020\036R\020\020\037\032\004\030\0010 X\016¢\006\002\n\000¨\006."}, d2={"Lcom/byimplication/sakay/SakayConstants;", "", "()V", "BOUNDS_MANILA", "Lcom/google/android/gms/maps/model/LatLngBounds;", "getBOUNDS_MANILA", "()Lcom/google/android/gms/maps/model/LatLngBounds;", "INCIDENT_FROM_TIME", "", "getINCIDENT_FROM_TIME", "()D", "INCIDENT_FROM_TIME$delegate", "Lkotlin/Lazy;", "INTERNET_PERMISSIONS", "", "", "getINTERNET_PERMISSIONS", "()Ljava/util/List;", "LATLNG_CEBU", "Lcom/google/android/gms/maps/model/LatLng;", "getLATLNG_CEBU", "()Lcom/google/android/gms/maps/model/LatLng;", "LATLNG_MANILA", "getLATLNG_MANILA", "LOCATION_PERMISSIONS", "getLOCATION_PERMISSIONS", "MIN_DISTANCE", "", "SAKAYGREEN", "getSAKAYGREEN", "()I", "itineraryColors", "", "getItineraryColor", "context", "Landroid/content/Context;", "position", "getItineraryColors", "initializeItineraryColors", "", "isLatLngOutOfManilaBounds", "", "latLng", "tooNear", "a", "b", "app_release"}, k=1, mv={1, 1, 9})
public final class SakayConstants
{
  @NotNull
  private static final LatLngBounds BOUNDS_MANILA;
  @NotNull
  private static final Lazy INCIDENT_FROM_TIME$delegate = LazyKt.lazy((Function0)INCIDENT_FROM_TIME.2.INSTANCE);
  public static final SakayConstants INSTANCE = new SakayConstants();
  @NotNull
  private static final List<String> INTERNET_PERMISSIONS;
  @NotNull
  private static final LatLng LATLNG_CEBU;
  @NotNull
  private static final LatLng LATLNG_MANILA;
  @NotNull
  private static final List<String> LOCATION_PERMISSIONS = CollectionsKt.listOf(new String[] { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" });
  public static final int MIN_DISTANCE = 2;
  private static final int SAKAYGREEN;
  private static int[] itineraryColors;
  
  static
  {
    INTERNET_PERMISSIONS = CollectionsKt.listOf(new String[] { "android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE" });
    LATLNG_MANILA = new LatLng(14.5833D, 121.0D);
    BOUNDS_MANILA = new LatLngBounds(new LatLng(14.270995D, 120.663168D), new LatLng(14.896841D, 121.27521D));
    LATLNG_CEBU = new LatLng(10.31417D, 123.92675D);
    SAKAYGREEN = Color.parseColor("#2A6D45");
  }
  
  private SakayConstants() {}
  
  private final void initializeItineraryColors(Context paramContext)
  {
    itineraryColors = new int[] { ContextCompat.getColor(paramContext, 2131099768), ContextCompat.getColor(paramContext, 2131099771), ContextCompat.getColor(paramContext, 2131099772) };
  }
  
  @NotNull
  public final LatLngBounds getBOUNDS_MANILA()
  {
    return BOUNDS_MANILA;
  }
  
  public final double getINCIDENT_FROM_TIME()
  {
    Lazy localLazy = INCIDENT_FROM_TIME$delegate;
    KProperty localKProperty = $$delegatedProperties[0];
    return ((Number)localLazy.getValue()).doubleValue();
  }
  
  @NotNull
  public final List<String> getINTERNET_PERMISSIONS()
  {
    return INTERNET_PERMISSIONS;
  }
  
  public final int getItineraryColor(@NotNull Context paramContext, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    if (itineraryColors == null) {
      initializeItineraryColors(paramContext);
    }
    paramContext = itineraryColors;
    if (paramContext == null) {
      Intrinsics.throwNpe();
    }
    int[] arrayOfInt = itineraryColors;
    if (arrayOfInt == null) {
      Intrinsics.throwNpe();
    }
    return paramContext[(paramInt % arrayOfInt.length)];
  }
  
  @NotNull
  public final int[] getItineraryColors(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    if (itineraryColors == null) {
      initializeItineraryColors(paramContext);
    }
    paramContext = itineraryColors;
    if (paramContext == null) {
      Intrinsics.throwNpe();
    }
    return paramContext;
  }
  
  @NotNull
  public final LatLng getLATLNG_CEBU()
  {
    return LATLNG_CEBU;
  }
  
  @NotNull
  public final LatLng getLATLNG_MANILA()
  {
    return LATLNG_MANILA;
  }
  
  @NotNull
  public final List<String> getLOCATION_PERMISSIONS()
  {
    return LOCATION_PERMISSIONS;
  }
  
  public final int getSAKAYGREEN()
  {
    return SAKAYGREEN;
  }
  
  public final boolean isLatLngOutOfManilaBounds(@NotNull LatLng paramLatLng)
  {
    Intrinsics.checkParameterIsNotNull(paramLatLng, "latLng");
    return (latitude < BOUNDS_MANILAsouthwest.latitude) || (latitude > BOUNDS_MANILAnortheast.latitude) || (longitude < BOUNDS_MANILAsouthwest.longitude) || (longitude > BOUNDS_MANILAnortheast.longitude);
  }
  
  public final boolean tooNear(@NotNull LatLng paramLatLng1, @NotNull LatLng paramLatLng2)
  {
    Intrinsics.checkParameterIsNotNull(paramLatLng1, "a");
    Intrinsics.checkParameterIsNotNull(paramLatLng2, "b");
    double d2 = Math.abs(latitude - latitude);
    double d1 = 'Ϩ';
    d2 *= d1;
    d1 = Math.abs(longitude - longitude) * d1;
    return d2 * d2 + d1 * d1 < 4;
  }
}

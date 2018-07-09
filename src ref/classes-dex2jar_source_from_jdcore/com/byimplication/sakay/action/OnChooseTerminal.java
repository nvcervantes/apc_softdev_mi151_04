package com.byimplication.sakay.action;

import com.byimplication.sakay.model.TerminalType;
import com.google.android.gms.maps.model.LatLng;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0004\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\021\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B3\022\b\020\002\032\004\030\0010\003\022\b\020\004\032\004\030\0010\005\022\b\020\006\032\004\030\0010\005\022\006\020\007\032\0020\b\022\006\020\t\032\0020\005¢\006\002\020\nJ\013\020\023\032\004\030\0010\003HÆ\003J\013\020\024\032\004\030\0010\005HÆ\003J\013\020\025\032\004\030\0010\005HÆ\003J\t\020\026\032\0020\bHÆ\003J\t\020\027\032\0020\005HÆ\003JA\020\030\032\0020\0002\n\b\002\020\002\032\004\030\0010\0032\n\b\002\020\004\032\004\030\0010\0052\n\b\002\020\006\032\004\030\0010\0052\b\b\002\020\007\032\0020\b2\b\b\002\020\t\032\0020\005HÆ\001J\023\020\031\032\0020\0322\b\020\033\032\004\030\0010\034HÖ\003J\t\020\035\032\0020\036HÖ\001J\t\020\037\032\0020\005HÖ\001R\021\020\t\032\0020\005¢\006\b\n\000\032\004\b\013\020\fR\023\020\004\032\004\030\0010\005¢\006\b\n\000\032\004\b\r\020\fR\023\020\002\032\004\030\0010\003¢\006\b\n\000\032\004\b\016\020\017R\023\020\006\032\004\030\0010\005¢\006\b\n\000\032\004\b\020\020\fR\021\020\007\032\0020\b¢\006\b\n\000\032\004\b\021\020\022¨\006 "}, d2={"Lcom/byimplication/sakay/action/OnChooseTerminal;", "Lcom/byimplication/sakay/action/Action;", "location", "Lcom/google/android/gms/maps/model/LatLng;", "description", "", "placeId", "whichTerminal", "Lcom/byimplication/sakay/model/TerminalType;", "byMethod", "(Lcom/google/android/gms/maps/model/LatLng;Ljava/lang/String;Ljava/lang/String;Lcom/byimplication/sakay/model/TerminalType;Ljava/lang/String;)V", "getByMethod", "()Ljava/lang/String;", "getDescription", "getLocation", "()Lcom/google/android/gms/maps/model/LatLng;", "getPlaceId", "getWhichTerminal", "()Lcom/byimplication/sakay/model/TerminalType;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class OnChooseTerminal
  implements Action
{
  @NotNull
  private final String byMethod;
  @Nullable
  private final String description;
  @Nullable
  private final LatLng location;
  @Nullable
  private final String placeId;
  @NotNull
  private final TerminalType whichTerminal;
  
  public OnChooseTerminal(@Nullable LatLng paramLatLng, @Nullable String paramString1, @Nullable String paramString2, @NotNull TerminalType paramTerminalType, @NotNull String paramString3)
  {
    location = paramLatLng;
    description = paramString1;
    placeId = paramString2;
    whichTerminal = paramTerminalType;
    byMethod = paramString3;
  }
  
  @Nullable
  public final LatLng component1()
  {
    return location;
  }
  
  @Nullable
  public final String component2()
  {
    return description;
  }
  
  @Nullable
  public final String component3()
  {
    return placeId;
  }
  
  @NotNull
  public final TerminalType component4()
  {
    return whichTerminal;
  }
  
  @NotNull
  public final String component5()
  {
    return byMethod;
  }
  
  @NotNull
  public final OnChooseTerminal copy(@Nullable LatLng paramLatLng, @Nullable String paramString1, @Nullable String paramString2, @NotNull TerminalType paramTerminalType, @NotNull String paramString3)
  {
    Intrinsics.checkParameterIsNotNull(paramTerminalType, "whichTerminal");
    Intrinsics.checkParameterIsNotNull(paramString3, "byMethod");
    return new OnChooseTerminal(paramLatLng, paramString1, paramString2, paramTerminalType, paramString3);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof OnChooseTerminal))
      {
        paramObject = (OnChooseTerminal)paramObject;
        if ((Intrinsics.areEqual(location, location)) && (Intrinsics.areEqual(description, description)) && (Intrinsics.areEqual(placeId, placeId)) && (Intrinsics.areEqual(whichTerminal, whichTerminal)) && (Intrinsics.areEqual(byMethod, byMethod))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final String getByMethod()
  {
    return byMethod;
  }
  
  @Nullable
  public final String getDescription()
  {
    return description;
  }
  
  @Nullable
  public final LatLng getLocation()
  {
    return location;
  }
  
  @Nullable
  public final String getPlaceId()
  {
    return placeId;
  }
  
  @NotNull
  public final TerminalType getWhichTerminal()
  {
    return whichTerminal;
  }
  
  public int hashCode()
  {
    Object localObject = location;
    int n = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = description;
    int j;
    if (localObject != null) {
      j = localObject.hashCode();
    } else {
      j = 0;
    }
    localObject = placeId;
    int k;
    if (localObject != null) {
      k = localObject.hashCode();
    } else {
      k = 0;
    }
    localObject = whichTerminal;
    int m;
    if (localObject != null) {
      m = localObject.hashCode();
    } else {
      m = 0;
    }
    localObject = byMethod;
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
    localStringBuilder.append("OnChooseTerminal(location=");
    localStringBuilder.append(location);
    localStringBuilder.append(", description=");
    localStringBuilder.append(description);
    localStringBuilder.append(", placeId=");
    localStringBuilder.append(placeId);
    localStringBuilder.append(", whichTerminal=");
    localStringBuilder.append(whichTerminal);
    localStringBuilder.append(", byMethod=");
    localStringBuilder.append(byMethod);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

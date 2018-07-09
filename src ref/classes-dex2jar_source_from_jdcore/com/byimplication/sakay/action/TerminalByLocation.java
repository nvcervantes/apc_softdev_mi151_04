package com.byimplication.sakay.action;

import com.byimplication.sakay.model.TerminalType;
import com.google.android.gms.maps.model.LatLng;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\016\n\002\b\f\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B!\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\n\b\002\020\006\032\004\030\0010\007¢\006\002\020\bJ\t\020\017\032\0020\003HÆ\003J\t\020\020\032\0020\005HÆ\003J\013\020\021\032\004\030\0010\007HÆ\003J)\020\022\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0052\n\b\002\020\006\032\004\030\0010\007HÆ\001J\023\020\023\032\0020\0242\b\020\025\032\004\030\0010\026HÖ\003J\t\020\027\032\0020\030HÖ\001J\t\020\031\032\0020\007HÖ\001R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\t\020\nR\023\020\006\032\004\030\0010\007¢\006\b\n\000\032\004\b\013\020\fR\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\r\020\016¨\006\032"}, d2={"Lcom/byimplication/sakay/action/TerminalByLocation;", "Lcom/byimplication/sakay/action/Action;", "location", "Lcom/google/android/gms/maps/model/LatLng;", "which", "Lcom/byimplication/sakay/model/TerminalType;", "temporaryName", "", "(Lcom/google/android/gms/maps/model/LatLng;Lcom/byimplication/sakay/model/TerminalType;Ljava/lang/String;)V", "getLocation", "()Lcom/google/android/gms/maps/model/LatLng;", "getTemporaryName", "()Ljava/lang/String;", "getWhich", "()Lcom/byimplication/sakay/model/TerminalType;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class TerminalByLocation
  implements Action
{
  @NotNull
  private final LatLng location;
  @Nullable
  private final String temporaryName;
  @NotNull
  private final TerminalType which;
  
  public TerminalByLocation(@NotNull LatLng paramLatLng, @NotNull TerminalType paramTerminalType, @Nullable String paramString)
  {
    location = paramLatLng;
    which = paramTerminalType;
    temporaryName = paramString;
  }
  
  @NotNull
  public final LatLng component1()
  {
    return location;
  }
  
  @NotNull
  public final TerminalType component2()
  {
    return which;
  }
  
  @Nullable
  public final String component3()
  {
    return temporaryName;
  }
  
  @NotNull
  public final TerminalByLocation copy(@NotNull LatLng paramLatLng, @NotNull TerminalType paramTerminalType, @Nullable String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramLatLng, "location");
    Intrinsics.checkParameterIsNotNull(paramTerminalType, "which");
    return new TerminalByLocation(paramLatLng, paramTerminalType, paramString);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof TerminalByLocation))
      {
        paramObject = (TerminalByLocation)paramObject;
        if ((Intrinsics.areEqual(location, location)) && (Intrinsics.areEqual(which, which)) && (Intrinsics.areEqual(temporaryName, temporaryName))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final LatLng getLocation()
  {
    return location;
  }
  
  @Nullable
  public final String getTemporaryName()
  {
    return temporaryName;
  }
  
  @NotNull
  public final TerminalType getWhich()
  {
    return which;
  }
  
  public int hashCode()
  {
    Object localObject = location;
    int k = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = which;
    int j;
    if (localObject != null) {
      j = localObject.hashCode();
    } else {
      j = 0;
    }
    localObject = temporaryName;
    if (localObject != null) {
      k = localObject.hashCode();
    }
    return (i * 31 + j) * 31 + k;
  }
  
  public void post()
  {
    Action.DefaultImpls.post(this);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("TerminalByLocation(location=");
    localStringBuilder.append(location);
    localStringBuilder.append(", which=");
    localStringBuilder.append(which);
    localStringBuilder.append(", temporaryName=");
    localStringBuilder.append(temporaryName);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

package com.byimplication.sakay.action;

import com.byimplication.sakay.model.TerminalType;
import com.google.android.gms.maps.model.LatLng;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\t\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B\027\022\006\020\002\032\0020\003\022\b\b\002\020\004\032\0020\005¢\006\002\020\006J\t\020\013\032\0020\003HÆ\003J\t\020\f\032\0020\005HÆ\003J\035\020\r\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\005HÆ\001J\023\020\016\032\0020\0172\b\020\020\032\004\030\0010\021HÖ\003J\t\020\022\032\0020\023HÖ\001J\t\020\024\032\0020\025HÖ\001R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\007\020\bR\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\t\020\n¨\006\026"}, d2={"Lcom/byimplication/sakay/action/TemporaryTerminalByLocation;", "Lcom/byimplication/sakay/action/Action;", "location", "Lcom/google/android/gms/maps/model/LatLng;", "which", "Lcom/byimplication/sakay/model/TerminalType;", "(Lcom/google/android/gms/maps/model/LatLng;Lcom/byimplication/sakay/model/TerminalType;)V", "getLocation", "()Lcom/google/android/gms/maps/model/LatLng;", "getWhich", "()Lcom/byimplication/sakay/model/TerminalType;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class TemporaryTerminalByLocation
  implements Action
{
  @NotNull
  private final LatLng location;
  @NotNull
  private final TerminalType which;
  
  public TemporaryTerminalByLocation(@NotNull LatLng paramLatLng, @NotNull TerminalType paramTerminalType)
  {
    location = paramLatLng;
    which = paramTerminalType;
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
  
  @NotNull
  public final TemporaryTerminalByLocation copy(@NotNull LatLng paramLatLng, @NotNull TerminalType paramTerminalType)
  {
    Intrinsics.checkParameterIsNotNull(paramLatLng, "location");
    Intrinsics.checkParameterIsNotNull(paramTerminalType, "which");
    return new TemporaryTerminalByLocation(paramLatLng, paramTerminalType);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof TemporaryTerminalByLocation))
      {
        paramObject = (TemporaryTerminalByLocation)paramObject;
        if ((Intrinsics.areEqual(location, location)) && (Intrinsics.areEqual(which, which))) {}
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
  
  @NotNull
  public final TerminalType getWhich()
  {
    return which;
  }
  
  public int hashCode()
  {
    Object localObject = location;
    int j = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = which;
    if (localObject != null) {
      j = localObject.hashCode();
    }
    return i * 31 + j;
  }
  
  public void post()
  {
    Action.DefaultImpls.post(this);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("TemporaryTerminalByLocation(location=");
    localStringBuilder.append(location);
    localStringBuilder.append(", which=");
    localStringBuilder.append(which);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

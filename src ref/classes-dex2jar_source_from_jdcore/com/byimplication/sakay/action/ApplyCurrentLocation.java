package com.byimplication.sakay.action;

import com.google.android.gms.maps.model.LatLng;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\f\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B\035\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\005¢\006\002\020\007J\t\020\f\032\0020\003HÆ\003J\t\020\r\032\0020\005HÆ\003J\t\020\016\032\0020\005HÆ\003J'\020\017\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0052\b\b\002\020\006\032\0020\005HÆ\001J\023\020\020\032\0020\0052\b\020\021\032\004\030\0010\022HÖ\003J\t\020\023\032\0020\024HÖ\001J\t\020\025\032\0020\026HÖ\001R\021\020\006\032\0020\005¢\006\b\n\000\032\004\b\006\020\bR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\t\020\nR\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\013\020\b¨\006\027"}, d2={"Lcom/byimplication/sakay/action/ApplyCurrentLocation;", "Lcom/byimplication/sakay/action/Action;", "latLng", "Lcom/google/android/gms/maps/model/LatLng;", "shouldMoveMap", "", "isAnimated", "(Lcom/google/android/gms/maps/model/LatLng;ZZ)V", "()Z", "getLatLng", "()Lcom/google/android/gms/maps/model/LatLng;", "getShouldMoveMap", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class ApplyCurrentLocation
  implements Action
{
  private final boolean isAnimated;
  @NotNull
  private final LatLng latLng;
  private final boolean shouldMoveMap;
  
  public ApplyCurrentLocation(@NotNull LatLng paramLatLng, boolean paramBoolean1, boolean paramBoolean2)
  {
    latLng = paramLatLng;
    shouldMoveMap = paramBoolean1;
    isAnimated = paramBoolean2;
  }
  
  @NotNull
  public final LatLng component1()
  {
    return latLng;
  }
  
  public final boolean component2()
  {
    return shouldMoveMap;
  }
  
  public final boolean component3()
  {
    return isAnimated;
  }
  
  @NotNull
  public final ApplyCurrentLocation copy(@NotNull LatLng paramLatLng, boolean paramBoolean1, boolean paramBoolean2)
  {
    Intrinsics.checkParameterIsNotNull(paramLatLng, "latLng");
    return new ApplyCurrentLocation(paramLatLng, paramBoolean1, paramBoolean2);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof ApplyCurrentLocation))
      {
        paramObject = (ApplyCurrentLocation)paramObject;
        if (Intrinsics.areEqual(latLng, latLng))
        {
          int i;
          if (shouldMoveMap == shouldMoveMap) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0)
          {
            if (isAnimated == isAnimated) {
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
      return false;
    }
    return true;
  }
  
  @NotNull
  public final LatLng getLatLng()
  {
    return latLng;
  }
  
  public final boolean getShouldMoveMap()
  {
    return shouldMoveMap;
  }
  
  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public final boolean isAnimated()
  {
    return isAnimated;
  }
  
  public void post()
  {
    Action.DefaultImpls.post(this);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("ApplyCurrentLocation(latLng=");
    localStringBuilder.append(latLng);
    localStringBuilder.append(", shouldMoveMap=");
    localStringBuilder.append(shouldMoveMap);
    localStringBuilder.append(", isAnimated=");
    localStringBuilder.append(isAnimated);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

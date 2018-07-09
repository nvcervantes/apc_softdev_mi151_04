package com.byimplication.sakay.store;

import com.google.android.gms.maps.model.LatLng;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\013\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005¢\006\002\020\006J\n\020\013\032\004\030\0010\001H\026J\t\020\f\032\0020\003HÆ\003J\t\020\r\032\0020\005HÆ\003J\035\020\016\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\005HÆ\001J\023\020\017\032\0020\0052\b\020\020\032\004\030\0010\021HÖ\003J\t\020\022\032\0020\023HÖ\001J\t\020\024\032\0020\025HÖ\001R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\007\020\bR\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\t\020\n¨\006\026"}, d2={"Lcom/byimplication/sakay/store/MapCenterUpdate;", "Lcom/byimplication/sakay/store/StoreData;", "latLng", "Lcom/google/android/gms/maps/model/LatLng;", "setTrueSize", "", "(Lcom/google/android/gms/maps/model/LatLng;Z)V", "getLatLng", "()Lcom/google/android/gms/maps/model/LatLng;", "getSetTrueSize", "()Z", "clone", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class MapCenterUpdate
  implements StoreData
{
  @NotNull
  private final LatLng latLng;
  private final boolean setTrueSize;
  
  public MapCenterUpdate(@NotNull LatLng paramLatLng, boolean paramBoolean)
  {
    latLng = paramLatLng;
    setTrueSize = paramBoolean;
  }
  
  @Nullable
  public StoreData clone()
  {
    return (StoreData)copy$default(this, null, false, 3, null);
  }
  
  @NotNull
  public final LatLng component1()
  {
    return latLng;
  }
  
  public final boolean component2()
  {
    return setTrueSize;
  }
  
  @NotNull
  public final MapCenterUpdate copy(@NotNull LatLng paramLatLng, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramLatLng, "latLng");
    return new MapCenterUpdate(paramLatLng, paramBoolean);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof MapCenterUpdate))
      {
        paramObject = (MapCenterUpdate)paramObject;
        if (Intrinsics.areEqual(latLng, latLng))
        {
          int i;
          if (setTrueSize == setTrueSize) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0) {
            return true;
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
  
  public final boolean getSetTrueSize()
  {
    return setTrueSize;
  }
  
  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("MapCenterUpdate(latLng=");
    localStringBuilder.append(latLng);
    localStringBuilder.append(", setTrueSize=");
    localStringBuilder.append(setTrueSize);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

package com.byimplication.sakay.action;

import com.google.android.gms.maps.model.LatLng;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000.\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\020\013\n\002\b\016\n\002\020\000\n\000\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B+\022\006\020\002\032\0020\003\022\b\020\004\032\004\030\0010\005\022\b\020\006\032\004\030\0010\005\022\b\b\002\020\007\032\0020\b¢\006\002\020\tJ\t\020\020\032\0020\003HÆ\003J\013\020\021\032\004\030\0010\005HÆ\003J\013\020\022\032\004\030\0010\005HÆ\003J\t\020\023\032\0020\bHÆ\003J5\020\024\032\0020\0002\b\b\002\020\002\032\0020\0032\n\b\002\020\004\032\004\030\0010\0052\n\b\002\020\006\032\004\030\0010\0052\b\b\002\020\007\032\0020\bHÆ\001J\023\020\025\032\0020\b2\b\020\026\032\004\030\0010\027HÖ\003J\t\020\030\032\0020\031HÖ\001J\t\020\032\032\0020\005HÖ\001R\023\020\004\032\004\030\0010\005¢\006\b\n\000\032\004\b\n\020\013R\021\020\007\032\0020\b¢\006\b\n\000\032\004\b\007\020\fR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\r\020\016R\023\020\006\032\004\030\0010\005¢\006\b\n\000\032\004\b\017\020\013¨\006\033"}, d2={"Lcom/byimplication/sakay/action/SetHome;", "Lcom/byimplication/sakay/action/Action;", "location", "Lcom/google/android/gms/maps/model/LatLng;", "description", "", "provider", "isGeocoded", "", "(Lcom/google/android/gms/maps/model/LatLng;Ljava/lang/String;Ljava/lang/String;Z)V", "getDescription", "()Ljava/lang/String;", "()Z", "getLocation", "()Lcom/google/android/gms/maps/model/LatLng;", "getProvider", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class SetHome
  implements Action
{
  @Nullable
  private final String description;
  private final boolean isGeocoded;
  @NotNull
  private final LatLng location;
  @Nullable
  private final String provider;
  
  public SetHome(@NotNull LatLng paramLatLng, @Nullable String paramString1, @Nullable String paramString2, boolean paramBoolean)
  {
    location = paramLatLng;
    description = paramString1;
    provider = paramString2;
    isGeocoded = paramBoolean;
  }
  
  @NotNull
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
    return provider;
  }
  
  public final boolean component4()
  {
    return isGeocoded;
  }
  
  @NotNull
  public final SetHome copy(@NotNull LatLng paramLatLng, @Nullable String paramString1, @Nullable String paramString2, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramLatLng, "location");
    return new SetHome(paramLatLng, paramString1, paramString2, paramBoolean);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof SetHome))
      {
        paramObject = (SetHome)paramObject;
        if ((Intrinsics.areEqual(location, location)) && (Intrinsics.areEqual(description, description)) && (Intrinsics.areEqual(provider, provider)))
        {
          int i;
          if (isGeocoded == isGeocoded) {
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
  
  @Nullable
  public final String getDescription()
  {
    return description;
  }
  
  @NotNull
  public final LatLng getLocation()
  {
    return location;
  }
  
  @Nullable
  public final String getProvider()
  {
    return provider;
  }
  
  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public final boolean isGeocoded()
  {
    return isGeocoded;
  }
  
  public void post()
  {
    Action.DefaultImpls.post(this);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SetHome(location=");
    localStringBuilder.append(location);
    localStringBuilder.append(", description=");
    localStringBuilder.append(description);
    localStringBuilder.append(", provider=");
    localStringBuilder.append(provider);
    localStringBuilder.append(", isGeocoded=");
    localStringBuilder.append(isGeocoded);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

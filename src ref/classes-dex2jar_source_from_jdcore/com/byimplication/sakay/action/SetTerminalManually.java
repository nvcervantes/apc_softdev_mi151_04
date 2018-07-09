package com.byimplication.sakay.action;

import com.byimplication.sakay.model.TerminalType;
import com.google.android.gms.maps.model.LatLng;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0004\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\000\n\002\020\013\n\002\b\025\n\002\020\000\n\000\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001BG\022\006\020\002\032\0020\003\022\b\020\004\032\004\030\0010\005\022\b\020\006\032\004\030\0010\005\022\006\020\007\032\0020\b\022\b\b\002\020\t\032\0020\n\022\b\b\002\020\013\032\0020\n\022\b\b\002\020\f\032\0020\n¢\006\002\020\rJ\t\020\026\032\0020\003HÆ\003J\013\020\027\032\004\030\0010\005HÆ\003J\013\020\030\032\004\030\0010\005HÆ\003J\t\020\031\032\0020\bHÆ\003J\t\020\032\032\0020\nHÆ\003J\t\020\033\032\0020\nHÆ\003J\t\020\034\032\0020\nHÆ\003JS\020\035\032\0020\0002\b\b\002\020\002\032\0020\0032\n\b\002\020\004\032\004\030\0010\0052\n\b\002\020\006\032\004\030\0010\0052\b\b\002\020\007\032\0020\b2\b\b\002\020\t\032\0020\n2\b\b\002\020\013\032\0020\n2\b\b\002\020\f\032\0020\nHÆ\001J\023\020\036\032\0020\n2\b\020\037\032\004\030\0010 HÖ\003J\t\020!\032\0020\"HÖ\001J\t\020#\032\0020\005HÖ\001R\023\020\004\032\004\030\0010\005¢\006\b\n\000\032\004\b\016\020\017R\021\020\t\032\0020\n¢\006\b\n\000\032\004\b\t\020\020R\021\020\f\032\0020\n¢\006\b\n\000\032\004\b\f\020\020R\021\020\013\032\0020\n¢\006\b\n\000\032\004\b\013\020\020R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\021\020\022R\023\020\006\032\004\030\0010\005¢\006\b\n\000\032\004\b\023\020\017R\021\020\007\032\0020\b¢\006\b\n\000\032\004\b\024\020\025¨\006$"}, d2={"Lcom/byimplication/sakay/action/SetTerminalManually;", "Lcom/byimplication/sakay/action/Action;", "location", "Lcom/google/android/gms/maps/model/LatLng;", "description", "", "provider", "which", "Lcom/byimplication/sakay/model/TerminalType;", "isGeocoded", "", "isSilent", "isRecall", "(Lcom/google/android/gms/maps/model/LatLng;Ljava/lang/String;Ljava/lang/String;Lcom/byimplication/sakay/model/TerminalType;ZZZ)V", "getDescription", "()Ljava/lang/String;", "()Z", "getLocation", "()Lcom/google/android/gms/maps/model/LatLng;", "getProvider", "getWhich", "()Lcom/byimplication/sakay/model/TerminalType;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class SetTerminalManually
  implements Action
{
  @Nullable
  private final String description;
  private final boolean isGeocoded;
  private final boolean isRecall;
  private final boolean isSilent;
  @NotNull
  private final LatLng location;
  @Nullable
  private final String provider;
  @NotNull
  private final TerminalType which;
  
  public SetTerminalManually(@NotNull LatLng paramLatLng, @Nullable String paramString1, @Nullable String paramString2, @NotNull TerminalType paramTerminalType, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    location = paramLatLng;
    description = paramString1;
    provider = paramString2;
    which = paramTerminalType;
    isGeocoded = paramBoolean1;
    isSilent = paramBoolean2;
    isRecall = paramBoolean3;
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
  
  @NotNull
  public final TerminalType component4()
  {
    return which;
  }
  
  public final boolean component5()
  {
    return isGeocoded;
  }
  
  public final boolean component6()
  {
    return isSilent;
  }
  
  public final boolean component7()
  {
    return isRecall;
  }
  
  @NotNull
  public final SetTerminalManually copy(@NotNull LatLng paramLatLng, @Nullable String paramString1, @Nullable String paramString2, @NotNull TerminalType paramTerminalType, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    Intrinsics.checkParameterIsNotNull(paramLatLng, "location");
    Intrinsics.checkParameterIsNotNull(paramTerminalType, "which");
    return new SetTerminalManually(paramLatLng, paramString1, paramString2, paramTerminalType, paramBoolean1, paramBoolean2, paramBoolean3);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof SetTerminalManually))
      {
        paramObject = (SetTerminalManually)paramObject;
        if ((Intrinsics.areEqual(location, location)) && (Intrinsics.areEqual(description, description)) && (Intrinsics.areEqual(provider, provider)) && (Intrinsics.areEqual(which, which)))
        {
          int i;
          if (isGeocoded == isGeocoded) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0)
          {
            if (isSilent == isSilent) {
              i = 1;
            } else {
              i = 0;
            }
            if (i != 0)
            {
              if (isRecall == isRecall) {
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
  
  @NotNull
  public final TerminalType getWhich()
  {
    return which;
  }
  
  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public final boolean isGeocoded()
  {
    return isGeocoded;
  }
  
  public final boolean isRecall()
  {
    return isRecall;
  }
  
  public final boolean isSilent()
  {
    return isSilent;
  }
  
  public void post()
  {
    Action.DefaultImpls.post(this);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SetTerminalManually(location=");
    localStringBuilder.append(location);
    localStringBuilder.append(", description=");
    localStringBuilder.append(description);
    localStringBuilder.append(", provider=");
    localStringBuilder.append(provider);
    localStringBuilder.append(", which=");
    localStringBuilder.append(which);
    localStringBuilder.append(", isGeocoded=");
    localStringBuilder.append(isGeocoded);
    localStringBuilder.append(", isSilent=");
    localStringBuilder.append(isSilent);
    localStringBuilder.append(", isRecall=");
    localStringBuilder.append(isRecall);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

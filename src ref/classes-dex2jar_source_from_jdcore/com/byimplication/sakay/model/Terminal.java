package com.byimplication.sakay.model;

import com.google.android.gms.maps.model.LatLng;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000(\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\020\016\n\002\b\021\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\003\b\b\030\000 \0342\0020\001:\001\034B#\022\b\020\002\032\004\030\0010\003\022\b\020\004\032\004\030\0010\005\022\b\020\006\032\004\030\0010\005¢\006\002\020\007J\013\020\022\032\004\030\0010\003HÆ\003J\013\020\023\032\004\030\0010\005HÆ\003J\013\020\024\032\004\030\0010\005HÆ\003J-\020\025\032\0020\0002\n\b\002\020\002\032\004\030\0010\0032\n\b\002\020\004\032\004\030\0010\0052\n\b\002\020\006\032\004\030\0010\005HÆ\001J\023\020\026\032\0020\0272\b\020\030\032\004\030\0010\001HÖ\003J\t\020\031\032\0020\032HÖ\001J\t\020\033\032\0020\005HÖ\001R\034\020\004\032\004\030\0010\005X\016¢\006\016\n\000\032\004\b\b\020\t\"\004\b\n\020\013R\034\020\002\032\004\030\0010\003X\016¢\006\016\n\000\032\004\b\f\020\r\"\004\b\016\020\017R\034\020\006\032\004\030\0010\005X\016¢\006\016\n\000\032\004\b\020\020\t\"\004\b\021\020\013¨\006\035"}, d2={"Lcom/byimplication/sakay/model/Terminal;", "", "location", "Lcom/google/android/gms/maps/model/LatLng;", "description", "", "provider", "(Lcom/google/android/gms/maps/model/LatLng;Ljava/lang/String;Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "setDescription", "(Ljava/lang/String;)V", "getLocation", "()Lcom/google/android/gms/maps/model/LatLng;", "setLocation", "(Lcom/google/android/gms/maps/model/LatLng;)V", "getProvider", "setProvider", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class Terminal
{
  public static final Companion Companion = new Companion(null);
  @NotNull
  public static final String facebookProvided = "f:";
  @NotNull
  public static final String googleProvided = "g:";
  @NotNull
  public static final String peliasProvided = "_e";
  @NotNull
  public static final String photonProvided = "_p:";
  @NotNull
  public static final String s2Provided = "s2:";
  @Nullable
  private String description;
  @Nullable
  private LatLng location;
  @Nullable
  private String provider;
  
  public Terminal(@Nullable LatLng paramLatLng, @Nullable String paramString1, @Nullable String paramString2)
  {
    location = paramLatLng;
    description = paramString1;
    provider = paramString2;
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
    return provider;
  }
  
  @NotNull
  public final Terminal copy(@Nullable LatLng paramLatLng, @Nullable String paramString1, @Nullable String paramString2)
  {
    return new Terminal(paramLatLng, paramString1, paramString2);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof Terminal))
      {
        paramObject = (Terminal)paramObject;
        if ((Intrinsics.areEqual(location, location)) && (Intrinsics.areEqual(description, description)) && (Intrinsics.areEqual(provider, provider))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
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
  public final String getProvider()
  {
    return provider;
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
    localObject = description;
    int j;
    if (localObject != null) {
      j = localObject.hashCode();
    } else {
      j = 0;
    }
    localObject = provider;
    if (localObject != null) {
      k = localObject.hashCode();
    }
    return (i * 31 + j) * 31 + k;
  }
  
  public final void setDescription(@Nullable String paramString)
  {
    description = paramString;
  }
  
  public final void setLocation(@Nullable LatLng paramLatLng)
  {
    location = paramLatLng;
  }
  
  public final void setProvider(@Nullable String paramString)
  {
    provider = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Terminal(location=");
    localStringBuilder.append(location);
    localStringBuilder.append(", description=");
    localStringBuilder.append(description);
    localStringBuilder.append(", provider=");
    localStringBuilder.append(provider);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\005\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\016\020\t\032\0020\n2\006\020\013\032\0020\nJ\016\020\f\032\0020\0042\006\020\013\032\0020\nR\016\020\003\032\0020\004XT¢\006\002\n\000R\016\020\005\032\0020\004XT¢\006\002\n\000R\016\020\006\032\0020\004XT¢\006\002\n\000R\016\020\007\032\0020\004XT¢\006\002\n\000R\016\020\b\032\0020\004XT¢\006\002\n\000¨\006\r"}, d2={"Lcom/byimplication/sakay/model/Terminal$Companion;", "", "()V", "facebookProvided", "", "googleProvided", "peliasProvided", "photonProvided", "s2Provided", "getOpposite", "Lcom/byimplication/sakay/model/TerminalType;", "terminalType", "turnToReadableString", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
    
    @NotNull
    public final TerminalType getOpposite(@NotNull TerminalType paramTerminalType)
    {
      Intrinsics.checkParameterIsNotNull(paramTerminalType, "terminalType");
      switch (Terminal.Companion.WhenMappings.$EnumSwitchMapping$0[paramTerminalType.ordinal()])
      {
      default: 
        throw new NoWhenBranchMatchedException();
      case 4: 
        return TerminalType.HOME;
      case 3: 
        return TerminalType.WORK;
      case 2: 
        return TerminalType.ORIGIN;
      }
      return TerminalType.DESTINATION;
    }
    
    @NotNull
    public final String turnToReadableString(@NotNull TerminalType paramTerminalType)
    {
      Intrinsics.checkParameterIsNotNull(paramTerminalType, "terminalType");
      switch (Terminal.Companion.WhenMappings.$EnumSwitchMapping$1[paramTerminalType.ordinal()])
      {
      default: 
        throw new NoWhenBranchMatchedException();
      case 4: 
        return "Work";
      case 3: 
        return "Home";
      case 2: 
        return "Destination";
      }
      return "Origin";
    }
  }
}

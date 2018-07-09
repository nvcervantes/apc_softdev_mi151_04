package com.byimplication.sakay.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\"\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\002\b\017\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B%\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003\022\006\020\005\032\0020\003\022\006\020\006\032\0020\003¢\006\002\020\007J\t\020\r\032\0020\003HÆ\003J\t\020\016\032\0020\003HÆ\003J\t\020\017\032\0020\003HÆ\003J\t\020\020\032\0020\003HÆ\003J1\020\021\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0032\b\b\002\020\005\032\0020\0032\b\b\002\020\006\032\0020\003HÆ\001J\023\020\022\032\0020\0232\b\020\024\032\004\030\0010\001HÖ\003J\t\020\025\032\0020\026HÖ\001J\t\020\027\032\0020\003HÖ\001R\021\020\006\032\0020\003¢\006\b\n\000\032\004\b\b\020\tR\021\020\004\032\0020\003¢\006\b\n\000\032\004\b\n\020\tR\021\020\005\032\0020\003¢\006\b\n\000\032\004\b\013\020\tR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\f\020\t¨\006\030"}, d2={"Lcom/byimplication/sakay/model/PeliasOsmLang;", "", "name", "", "iso6391", "iso6393", "defaulted", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDefaulted", "()Ljava/lang/String;", "getIso6391", "getIso6393", "getName", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class PeliasOsmLang
{
  @NotNull
  private final String defaulted;
  @NotNull
  private final String iso6391;
  @NotNull
  private final String iso6393;
  @NotNull
  private final String name;
  
  public PeliasOsmLang(@NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3, @NotNull String paramString4)
  {
    name = paramString1;
    iso6391 = paramString2;
    iso6393 = paramString3;
    defaulted = paramString4;
  }
  
  @NotNull
  public final String component1()
  {
    return name;
  }
  
  @NotNull
  public final String component2()
  {
    return iso6391;
  }
  
  @NotNull
  public final String component3()
  {
    return iso6393;
  }
  
  @NotNull
  public final String component4()
  {
    return defaulted;
  }
  
  @NotNull
  public final PeliasOsmLang copy(@NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3, @NotNull String paramString4)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "name");
    Intrinsics.checkParameterIsNotNull(paramString2, "iso6391");
    Intrinsics.checkParameterIsNotNull(paramString3, "iso6393");
    Intrinsics.checkParameterIsNotNull(paramString4, "defaulted");
    return new PeliasOsmLang(paramString1, paramString2, paramString3, paramString4);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof PeliasOsmLang))
      {
        paramObject = (PeliasOsmLang)paramObject;
        if ((Intrinsics.areEqual(name, name)) && (Intrinsics.areEqual(iso6391, iso6391)) && (Intrinsics.areEqual(iso6393, iso6393)) && (Intrinsics.areEqual(defaulted, defaulted))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final String getDefaulted()
  {
    return defaulted;
  }
  
  @NotNull
  public final String getIso6391()
  {
    return iso6391;
  }
  
  @NotNull
  public final String getIso6393()
  {
    return iso6393;
  }
  
  @NotNull
  public final String getName()
  {
    return name;
  }
  
  public int hashCode()
  {
    String str = name;
    int m = 0;
    int i;
    if (str != null) {
      i = str.hashCode();
    } else {
      i = 0;
    }
    str = iso6391;
    int j;
    if (str != null) {
      j = str.hashCode();
    } else {
      j = 0;
    }
    str = iso6393;
    int k;
    if (str != null) {
      k = str.hashCode();
    } else {
      k = 0;
    }
    str = defaulted;
    if (str != null) {
      m = str.hashCode();
    }
    return ((i * 31 + j) * 31 + k) * 31 + m;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PeliasOsmLang(name=");
    localStringBuilder.append(name);
    localStringBuilder.append(", iso6391=");
    localStringBuilder.append(iso6391);
    localStringBuilder.append(", iso6393=");
    localStringBuilder.append(iso6393);
    localStringBuilder.append(", defaulted=");
    localStringBuilder.append(defaulted);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

package com.byimplication.sakay.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\"\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\002\b\f\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B\035\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003\022\006\020\005\032\0020\003¢\006\002\020\006J\t\020\013\032\0020\003HÆ\003J\t\020\f\032\0020\003HÆ\003J\t\020\r\032\0020\003HÆ\003J'\020\016\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0032\b\b\002\020\005\032\0020\003HÆ\001J\023\020\017\032\0020\0202\b\020\021\032\004\030\0010\001HÖ\003J\t\020\022\032\0020\023HÖ\001J\t\020\024\032\0020\003HÖ\001R\021\020\004\032\0020\003¢\006\b\n\000\032\004\b\007\020\bR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\t\020\bR\021\020\005\032\0020\003¢\006\b\n\000\032\004\b\n\020\b¨\006\025"}, d2={"Lcom/byimplication/sakay/model/OsmEngine;", "", "name", "", "author", "version", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAuthor", "()Ljava/lang/String;", "getName", "getVersion", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class OsmEngine
{
  @NotNull
  private final String author;
  @NotNull
  private final String name;
  @NotNull
  private final String version;
  
  public OsmEngine(@NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3)
  {
    name = paramString1;
    author = paramString2;
    version = paramString3;
  }
  
  @NotNull
  public final String component1()
  {
    return name;
  }
  
  @NotNull
  public final String component2()
  {
    return author;
  }
  
  @NotNull
  public final String component3()
  {
    return version;
  }
  
  @NotNull
  public final OsmEngine copy(@NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "name");
    Intrinsics.checkParameterIsNotNull(paramString2, "author");
    Intrinsics.checkParameterIsNotNull(paramString3, "version");
    return new OsmEngine(paramString1, paramString2, paramString3);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof OsmEngine))
      {
        paramObject = (OsmEngine)paramObject;
        if ((Intrinsics.areEqual(name, name)) && (Intrinsics.areEqual(author, author)) && (Intrinsics.areEqual(version, version))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final String getAuthor()
  {
    return author;
  }
  
  @NotNull
  public final String getName()
  {
    return name;
  }
  
  @NotNull
  public final String getVersion()
  {
    return version;
  }
  
  public int hashCode()
  {
    String str = name;
    int k = 0;
    int i;
    if (str != null) {
      i = str.hashCode();
    } else {
      i = 0;
    }
    str = author;
    int j;
    if (str != null) {
      j = str.hashCode();
    } else {
      j = 0;
    }
    str = version;
    if (str != null) {
      k = str.hashCode();
    }
    return (i * 31 + j) * 31 + k;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OsmEngine(name=");
    localStringBuilder.append(name);
    localStringBuilder.append(", author=");
    localStringBuilder.append(author);
    localStringBuilder.append(", version=");
    localStringBuilder.append(version);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

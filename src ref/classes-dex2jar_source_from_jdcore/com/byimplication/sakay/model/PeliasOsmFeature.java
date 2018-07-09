package com.byimplication.sakay.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000.\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\f\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B\037\022\b\020\002\032\004\030\0010\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007¢\006\002\020\bJ\013\020\017\032\004\030\0010\003HÆ\003J\t\020\020\032\0020\005HÆ\003J\t\020\021\032\0020\007HÆ\003J)\020\022\032\0020\0002\n\b\002\020\002\032\004\030\0010\0032\b\b\002\020\004\032\0020\0052\b\b\002\020\006\032\0020\007HÆ\001J\023\020\023\032\0020\0242\b\020\025\032\004\030\0010\001HÖ\003J\t\020\026\032\0020\027HÖ\001J\t\020\030\032\0020\003HÖ\001R\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\t\020\nR\021\020\006\032\0020\007¢\006\b\n\000\032\004\b\013\020\fR\023\020\002\032\004\030\0010\003¢\006\b\n\000\032\004\b\r\020\016¨\006\031"}, d2={"Lcom/byimplication/sakay/model/PeliasOsmFeature;", "", "type", "", "geometry", "Lcom/byimplication/sakay/model/OsmGeometry;", "properties", "Lcom/byimplication/sakay/model/PeliasOsmProperties;", "(Ljava/lang/String;Lcom/byimplication/sakay/model/OsmGeometry;Lcom/byimplication/sakay/model/PeliasOsmProperties;)V", "getGeometry", "()Lcom/byimplication/sakay/model/OsmGeometry;", "getProperties", "()Lcom/byimplication/sakay/model/PeliasOsmProperties;", "getType", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class PeliasOsmFeature
{
  @NotNull
  private final OsmGeometry geometry;
  @NotNull
  private final PeliasOsmProperties properties;
  @Nullable
  private final String type;
  
  public PeliasOsmFeature(@Nullable String paramString, @NotNull OsmGeometry paramOsmGeometry, @NotNull PeliasOsmProperties paramPeliasOsmProperties)
  {
    type = paramString;
    geometry = paramOsmGeometry;
    properties = paramPeliasOsmProperties;
  }
  
  @Nullable
  public final String component1()
  {
    return type;
  }
  
  @NotNull
  public final OsmGeometry component2()
  {
    return geometry;
  }
  
  @NotNull
  public final PeliasOsmProperties component3()
  {
    return properties;
  }
  
  @NotNull
  public final PeliasOsmFeature copy(@Nullable String paramString, @NotNull OsmGeometry paramOsmGeometry, @NotNull PeliasOsmProperties paramPeliasOsmProperties)
  {
    Intrinsics.checkParameterIsNotNull(paramOsmGeometry, "geometry");
    Intrinsics.checkParameterIsNotNull(paramPeliasOsmProperties, "properties");
    return new PeliasOsmFeature(paramString, paramOsmGeometry, paramPeliasOsmProperties);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof PeliasOsmFeature))
      {
        paramObject = (PeliasOsmFeature)paramObject;
        if ((Intrinsics.areEqual(type, type)) && (Intrinsics.areEqual(geometry, geometry)) && (Intrinsics.areEqual(properties, properties))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final OsmGeometry getGeometry()
  {
    return geometry;
  }
  
  @NotNull
  public final PeliasOsmProperties getProperties()
  {
    return properties;
  }
  
  @Nullable
  public final String getType()
  {
    return type;
  }
  
  public int hashCode()
  {
    Object localObject = type;
    int k = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = geometry;
    int j;
    if (localObject != null) {
      j = localObject.hashCode();
    } else {
      j = 0;
    }
    localObject = properties;
    if (localObject != null) {
      k = localObject.hashCode();
    }
    return (i * 31 + j) * 31 + k;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PeliasOsmFeature(type=");
    localStringBuilder.append(type);
    localStringBuilder.append(", geometry=");
    localStringBuilder.append(geometry);
    localStringBuilder.append(", properties=");
    localStringBuilder.append(properties);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

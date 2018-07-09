package com.byimplication.sakay.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000,\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\000\n\002\020 \n\002\020\006\n\002\b\t\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B\033\022\006\020\002\032\0020\003\022\f\020\004\032\b\022\004\022\0020\0060\005¢\006\002\020\007J\t\020\f\032\0020\003HÆ\003J\017\020\r\032\b\022\004\022\0020\0060\005HÆ\003J#\020\016\032\0020\0002\b\b\002\020\002\032\0020\0032\016\b\002\020\004\032\b\022\004\022\0020\0060\005HÆ\001J\023\020\017\032\0020\0202\b\020\021\032\004\030\0010\001HÖ\003J\t\020\022\032\0020\023HÖ\001J\t\020\024\032\0020\003HÖ\001R\027\020\004\032\b\022\004\022\0020\0060\005¢\006\b\n\000\032\004\b\b\020\tR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\n\020\013¨\006\025"}, d2={"Lcom/byimplication/sakay/model/OsmGeometry;", "", "type", "", "coordinates", "", "", "(Ljava/lang/String;Ljava/util/List;)V", "getCoordinates", "()Ljava/util/List;", "getType", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class OsmGeometry
{
  @NotNull
  private final List<Double> coordinates;
  @NotNull
  private final String type;
  
  public OsmGeometry(@NotNull String paramString, @NotNull List<Double> paramList)
  {
    type = paramString;
    coordinates = paramList;
  }
  
  @NotNull
  public final String component1()
  {
    return type;
  }
  
  @NotNull
  public final List<Double> component2()
  {
    return coordinates;
  }
  
  @NotNull
  public final OsmGeometry copy(@NotNull String paramString, @NotNull List<Double> paramList)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "type");
    Intrinsics.checkParameterIsNotNull(paramList, "coordinates");
    return new OsmGeometry(paramString, paramList);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof OsmGeometry))
      {
        paramObject = (OsmGeometry)paramObject;
        if ((Intrinsics.areEqual(type, type)) && (Intrinsics.areEqual(coordinates, coordinates))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final List<Double> getCoordinates()
  {
    return coordinates;
  }
  
  @NotNull
  public final String getType()
  {
    return type;
  }
  
  public int hashCode()
  {
    Object localObject = type;
    int j = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = coordinates;
    if (localObject != null) {
      j = localObject.hashCode();
    }
    return i * 31 + j;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OsmGeometry(type=");
    localStringBuilder.append(type);
    localStringBuilder.append(", coordinates=");
    localStringBuilder.append(coordinates);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

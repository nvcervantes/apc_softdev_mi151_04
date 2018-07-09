package com.byimplication.sakay.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0006\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\t\n\002\b\021\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B3\022\b\020\002\032\004\030\0010\003\022\b\020\004\032\004\030\0010\003\022\b\020\005\032\004\030\0010\006\022\006\020\007\032\0020\b\022\006\020\t\032\0020\n¢\006\002\020\013J\013\020\025\032\004\030\0010\003HÆ\003J\013\020\026\032\004\030\0010\003HÆ\003J\013\020\027\032\004\030\0010\006HÆ\003J\t\020\030\032\0020\bHÆ\003J\t\020\031\032\0020\nHÆ\003JA\020\032\032\0020\0002\n\b\002\020\002\032\004\030\0010\0032\n\b\002\020\004\032\004\030\0010\0032\n\b\002\020\005\032\004\030\0010\0062\b\b\002\020\007\032\0020\b2\b\b\002\020\t\032\0020\nHÆ\001J\023\020\033\032\0020\0342\b\020\035\032\004\030\0010\001HÖ\003J\t\020\036\032\0020\037HÖ\001J\t\020 \032\0020\003HÖ\001R\023\020\004\032\004\030\0010\003¢\006\b\n\000\032\004\b\f\020\rR\021\020\007\032\0020\b¢\006\b\n\000\032\004\b\016\020\017R\023\020\005\032\004\030\0010\006¢\006\b\n\000\032\004\b\020\020\021R\021\020\t\032\0020\n¢\006\b\n\000\032\004\b\022\020\023R\023\020\002\032\004\030\0010\003¢\006\b\n\000\032\004\b\024\020\r¨\006!"}, d2={"Lcom/byimplication/sakay/model/PeliasOsmGeocoding;", "", "version", "", "attribution", "query", "Lcom/byimplication/sakay/model/PeliasOsmQuery;", "engine", "Lcom/byimplication/sakay/model/OsmEngine;", "timestamp", "", "(Ljava/lang/String;Ljava/lang/String;Lcom/byimplication/sakay/model/PeliasOsmQuery;Lcom/byimplication/sakay/model/OsmEngine;J)V", "getAttribution", "()Ljava/lang/String;", "getEngine", "()Lcom/byimplication/sakay/model/OsmEngine;", "getQuery", "()Lcom/byimplication/sakay/model/PeliasOsmQuery;", "getTimestamp", "()J", "getVersion", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class PeliasOsmGeocoding
{
  @Nullable
  private final String attribution;
  @NotNull
  private final OsmEngine engine;
  @Nullable
  private final PeliasOsmQuery query;
  private final long timestamp;
  @Nullable
  private final String version;
  
  public PeliasOsmGeocoding(@Nullable String paramString1, @Nullable String paramString2, @Nullable PeliasOsmQuery paramPeliasOsmQuery, @NotNull OsmEngine paramOsmEngine, long paramLong)
  {
    version = paramString1;
    attribution = paramString2;
    query = paramPeliasOsmQuery;
    engine = paramOsmEngine;
    timestamp = paramLong;
  }
  
  @Nullable
  public final String component1()
  {
    return version;
  }
  
  @Nullable
  public final String component2()
  {
    return attribution;
  }
  
  @Nullable
  public final PeliasOsmQuery component3()
  {
    return query;
  }
  
  @NotNull
  public final OsmEngine component4()
  {
    return engine;
  }
  
  public final long component5()
  {
    return timestamp;
  }
  
  @NotNull
  public final PeliasOsmGeocoding copy(@Nullable String paramString1, @Nullable String paramString2, @Nullable PeliasOsmQuery paramPeliasOsmQuery, @NotNull OsmEngine paramOsmEngine, long paramLong)
  {
    Intrinsics.checkParameterIsNotNull(paramOsmEngine, "engine");
    return new PeliasOsmGeocoding(paramString1, paramString2, paramPeliasOsmQuery, paramOsmEngine, paramLong);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof PeliasOsmGeocoding))
      {
        paramObject = (PeliasOsmGeocoding)paramObject;
        if ((Intrinsics.areEqual(version, version)) && (Intrinsics.areEqual(attribution, attribution)) && (Intrinsics.areEqual(query, query)) && (Intrinsics.areEqual(engine, engine)))
        {
          int i;
          if (timestamp == timestamp) {
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
  public final String getAttribution()
  {
    return attribution;
  }
  
  @NotNull
  public final OsmEngine getEngine()
  {
    return engine;
  }
  
  @Nullable
  public final PeliasOsmQuery getQuery()
  {
    return query;
  }
  
  public final long getTimestamp()
  {
    return timestamp;
  }
  
  @Nullable
  public final String getVersion()
  {
    return version;
  }
  
  public int hashCode()
  {
    Object localObject = version;
    int m = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = attribution;
    int j;
    if (localObject != null) {
      j = localObject.hashCode();
    } else {
      j = 0;
    }
    localObject = query;
    int k;
    if (localObject != null) {
      k = localObject.hashCode();
    } else {
      k = 0;
    }
    localObject = engine;
    if (localObject != null) {
      m = localObject.hashCode();
    }
    long l = timestamp;
    return (((i * 31 + j) * 31 + k) * 31 + m) * 31 + (int)(l ^ l >>> 32);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PeliasOsmGeocoding(version=");
    localStringBuilder.append(version);
    localStringBuilder.append(", attribution=");
    localStringBuilder.append(attribution);
    localStringBuilder.append(", query=");
    localStringBuilder.append(query);
    localStringBuilder.append(", engine=");
    localStringBuilder.append(engine);
    localStringBuilder.append(", timestamp=");
    localStringBuilder.append(timestamp);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

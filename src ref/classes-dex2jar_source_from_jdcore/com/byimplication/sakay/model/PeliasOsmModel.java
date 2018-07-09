package com.byimplication.sakay.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0002\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\002\b\f\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B%\022\006\020\002\032\0020\003\022\b\020\004\032\004\030\0010\005\022\f\020\006\032\b\022\004\022\0020\b0\007¢\006\002\020\tJ\t\020\020\032\0020\003HÆ\003J\013\020\021\032\004\030\0010\005HÆ\003J\017\020\022\032\b\022\004\022\0020\b0\007HÆ\003J/\020\023\032\0020\0002\b\b\002\020\002\032\0020\0032\n\b\002\020\004\032\004\030\0010\0052\016\b\002\020\006\032\b\022\004\022\0020\b0\007HÆ\001J\023\020\024\032\0020\0252\b\020\026\032\004\030\0010\001HÖ\003J\t\020\027\032\0020\030HÖ\001J\t\020\031\032\0020\005HÖ\001R\027\020\006\032\b\022\004\022\0020\b0\007¢\006\b\n\000\032\004\b\n\020\013R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\f\020\rR\023\020\004\032\004\030\0010\005¢\006\b\n\000\032\004\b\016\020\017¨\006\032"}, d2={"Lcom/byimplication/sakay/model/PeliasOsmModel;", "", "geocoding", "Lcom/byimplication/sakay/model/PeliasOsmGeocoding;", "type", "", "features", "", "Lcom/byimplication/sakay/model/PeliasOsmFeature;", "(Lcom/byimplication/sakay/model/PeliasOsmGeocoding;Ljava/lang/String;Ljava/util/List;)V", "getFeatures", "()Ljava/util/List;", "getGeocoding", "()Lcom/byimplication/sakay/model/PeliasOsmGeocoding;", "getType", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class PeliasOsmModel
{
  @NotNull
  private final List<PeliasOsmFeature> features;
  @NotNull
  private final PeliasOsmGeocoding geocoding;
  @Nullable
  private final String type;
  
  public PeliasOsmModel(@NotNull PeliasOsmGeocoding paramPeliasOsmGeocoding, @Nullable String paramString, @NotNull List<PeliasOsmFeature> paramList)
  {
    geocoding = paramPeliasOsmGeocoding;
    type = paramString;
    features = paramList;
  }
  
  @NotNull
  public final PeliasOsmGeocoding component1()
  {
    return geocoding;
  }
  
  @Nullable
  public final String component2()
  {
    return type;
  }
  
  @NotNull
  public final List<PeliasOsmFeature> component3()
  {
    return features;
  }
  
  @NotNull
  public final PeliasOsmModel copy(@NotNull PeliasOsmGeocoding paramPeliasOsmGeocoding, @Nullable String paramString, @NotNull List<PeliasOsmFeature> paramList)
  {
    Intrinsics.checkParameterIsNotNull(paramPeliasOsmGeocoding, "geocoding");
    Intrinsics.checkParameterIsNotNull(paramList, "features");
    return new PeliasOsmModel(paramPeliasOsmGeocoding, paramString, paramList);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof PeliasOsmModel))
      {
        paramObject = (PeliasOsmModel)paramObject;
        if ((Intrinsics.areEqual(geocoding, geocoding)) && (Intrinsics.areEqual(type, type)) && (Intrinsics.areEqual(features, features))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final List<PeliasOsmFeature> getFeatures()
  {
    return features;
  }
  
  @NotNull
  public final PeliasOsmGeocoding getGeocoding()
  {
    return geocoding;
  }
  
  @Nullable
  public final String getType()
  {
    return type;
  }
  
  public int hashCode()
  {
    Object localObject = geocoding;
    int k = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = type;
    int j;
    if (localObject != null) {
      j = localObject.hashCode();
    } else {
      j = 0;
    }
    localObject = features;
    if (localObject != null) {
      k = localObject.hashCode();
    }
    return (i * 31 + j) * 31 + k;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PeliasOsmModel(geocoding=");
    localStringBuilder.append(geocoding);
    localStringBuilder.append(", type=");
    localStringBuilder.append(type);
    localStringBuilder.append(", features=");
    localStringBuilder.append(features);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

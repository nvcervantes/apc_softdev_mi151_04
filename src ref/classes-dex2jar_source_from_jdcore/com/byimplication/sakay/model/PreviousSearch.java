package com.byimplication.sakay.model;

import com.google.android.gms.maps.model.LatLng;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000,\n\002\030\002\n\002\020\000\n\000\n\002\020\b\n\000\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020\013\n\002\b\002\n\002\020\t\n\002\b%\b\b\030\0002\0020\001BI\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\b\020\006\032\004\030\0010\007\022\006\020\b\032\0020\t\022\006\020\n\032\0020\t\022\006\020\013\032\0020\f\022\006\020\r\032\0020\003\022\b\020\016\032\004\030\0010\007¢\006\002\020\017J\t\020$\032\0020\003HÆ\003J\t\020%\032\0020\005HÆ\003J\013\020&\032\004\030\0010\007HÆ\003J\t\020'\032\0020\tHÆ\003J\t\020(\032\0020\tHÆ\003J\t\020)\032\0020\fHÆ\003J\t\020*\032\0020\003HÆ\003J\013\020+\032\004\030\0010\007HÆ\003J]\020,\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0052\n\b\002\020\006\032\004\030\0010\0072\b\b\002\020\b\032\0020\t2\b\b\002\020\n\032\0020\t2\b\b\002\020\013\032\0020\f2\b\b\002\020\r\032\0020\0032\n\b\002\020\016\032\004\030\0010\007HÆ\001J\023\020-\032\0020\t2\b\020.\032\004\030\0010\001HÖ\003J\t\020/\032\0020\003HÖ\001J\t\0200\032\0020\007HÖ\001R\034\020\006\032\004\030\0010\007X\016¢\006\016\n\000\032\004\b\020\020\021\"\004\b\022\020\023R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\024\020\025R\032\020\n\032\0020\tX\016¢\006\016\n\000\032\004\b\n\020\026\"\004\b\027\020\030R\032\020\b\032\0020\tX\016¢\006\016\n\000\032\004\b\b\020\026\"\004\b\031\020\030R\032\020\013\032\0020\fX\016¢\006\016\n\000\032\004\b\032\020\033\"\004\b\034\020\035R\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\036\020\037R\023\020\016\032\004\030\0010\007¢\006\b\n\000\032\004\b \020\021R\032\020\r\032\0020\003X\016¢\006\016\n\000\032\004\b!\020\025\"\004\b\"\020#¨\0061"}, d2={"Lcom/byimplication/sakay/model/PreviousSearch;", "", "id", "", "latLng", "Lcom/google/android/gms/maps/model/LatLng;", "description", "", "isGeocoded", "", "isFavorite", "lastChosen", "", "timesChosen", "provider", "(ILcom/google/android/gms/maps/model/LatLng;Ljava/lang/String;ZZJILjava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "setDescription", "(Ljava/lang/String;)V", "getId", "()I", "()Z", "setFavorite", "(Z)V", "setGeocoded", "getLastChosen", "()J", "setLastChosen", "(J)V", "getLatLng", "()Lcom/google/android/gms/maps/model/LatLng;", "getProvider", "getTimesChosen", "setTimesChosen", "(I)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class PreviousSearch
{
  @Nullable
  private String description;
  private final int id;
  private boolean isFavorite;
  private boolean isGeocoded;
  private long lastChosen;
  @NotNull
  private final LatLng latLng;
  @Nullable
  private final String provider;
  private int timesChosen;
  
  public PreviousSearch(int paramInt1, @NotNull LatLng paramLatLng, @Nullable String paramString1, boolean paramBoolean1, boolean paramBoolean2, long paramLong, int paramInt2, @Nullable String paramString2)
  {
    id = paramInt1;
    latLng = paramLatLng;
    description = paramString1;
    isGeocoded = paramBoolean1;
    isFavorite = paramBoolean2;
    lastChosen = paramLong;
    timesChosen = paramInt2;
    provider = paramString2;
  }
  
  public final int component1()
  {
    return id;
  }
  
  @NotNull
  public final LatLng component2()
  {
    return latLng;
  }
  
  @Nullable
  public final String component3()
  {
    return description;
  }
  
  public final boolean component4()
  {
    return isGeocoded;
  }
  
  public final boolean component5()
  {
    return isFavorite;
  }
  
  public final long component6()
  {
    return lastChosen;
  }
  
  public final int component7()
  {
    return timesChosen;
  }
  
  @Nullable
  public final String component8()
  {
    return provider;
  }
  
  @NotNull
  public final PreviousSearch copy(int paramInt1, @NotNull LatLng paramLatLng, @Nullable String paramString1, boolean paramBoolean1, boolean paramBoolean2, long paramLong, int paramInt2, @Nullable String paramString2)
  {
    Intrinsics.checkParameterIsNotNull(paramLatLng, "latLng");
    return new PreviousSearch(paramInt1, paramLatLng, paramString1, paramBoolean1, paramBoolean2, paramLong, paramInt2, paramString2);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof PreviousSearch))
      {
        paramObject = (PreviousSearch)paramObject;
        int i;
        if (id == id) {
          i = 1;
        } else {
          i = 0;
        }
        if ((i != 0) && (Intrinsics.areEqual(latLng, latLng)) && (Intrinsics.areEqual(description, description)))
        {
          if (isGeocoded == isGeocoded) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0)
          {
            if (isFavorite == isFavorite) {
              i = 1;
            } else {
              i = 0;
            }
            if (i != 0)
            {
              if (lastChosen == lastChosen) {
                i = 1;
              } else {
                i = 0;
              }
              if (i != 0)
              {
                if (timesChosen == timesChosen) {
                  i = 1;
                } else {
                  i = 0;
                }
                if ((i != 0) && (Intrinsics.areEqual(provider, provider))) {
                  return true;
                }
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
  
  public final int getId()
  {
    return id;
  }
  
  public final long getLastChosen()
  {
    return lastChosen;
  }
  
  @NotNull
  public final LatLng getLatLng()
  {
    return latLng;
  }
  
  @Nullable
  public final String getProvider()
  {
    return provider;
  }
  
  public final int getTimesChosen()
  {
    return timesChosen;
  }
  
  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public final boolean isFavorite()
  {
    return isFavorite;
  }
  
  public final boolean isGeocoded()
  {
    return isGeocoded;
  }
  
  public final void setDescription(@Nullable String paramString)
  {
    description = paramString;
  }
  
  public final void setFavorite(boolean paramBoolean)
  {
    isFavorite = paramBoolean;
  }
  
  public final void setGeocoded(boolean paramBoolean)
  {
    isGeocoded = paramBoolean;
  }
  
  public final void setLastChosen(long paramLong)
  {
    lastChosen = paramLong;
  }
  
  public final void setTimesChosen(int paramInt)
  {
    timesChosen = paramInt;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PreviousSearch(id=");
    localStringBuilder.append(id);
    localStringBuilder.append(", latLng=");
    localStringBuilder.append(latLng);
    localStringBuilder.append(", description=");
    localStringBuilder.append(description);
    localStringBuilder.append(", isGeocoded=");
    localStringBuilder.append(isGeocoded);
    localStringBuilder.append(", isFavorite=");
    localStringBuilder.append(isFavorite);
    localStringBuilder.append(", lastChosen=");
    localStringBuilder.append(lastChosen);
    localStringBuilder.append(", timesChosen=");
    localStringBuilder.append(timesChosen);
    localStringBuilder.append(", provider=");
    localStringBuilder.append(provider);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

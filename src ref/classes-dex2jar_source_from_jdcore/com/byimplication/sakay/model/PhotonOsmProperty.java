package com.byimplication.sakay.model;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0004\n\002\030\002\n\002\020\000\n\000\n\002\020\t\n\000\n\002\020\016\n\002\b\016\n\002\020 \n\002\020\006\n\002\b'\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B£\001\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\005\022\006\020\007\032\0020\005\022\b\020\b\032\004\030\0010\005\022\b\020\t\032\004\030\0010\005\022\b\020\n\032\004\030\0010\005\022\b\020\013\032\004\030\0010\005\022\b\020\f\032\004\030\0010\005\022\b\020\r\032\004\030\0010\005\022\b\020\016\032\004\030\0010\005\022\b\020\017\032\004\030\0010\005\022\b\020\020\032\004\030\0010\005\022\b\020\021\032\004\030\0010\005\022\b\020\022\032\004\030\0010\005\022\016\020\023\032\n\022\004\022\0020\025\030\0010\024¢\006\002\020\026J\006\020*\032\0020\005J\t\020+\032\0020\003HÆ\003J\013\020,\032\004\030\0010\005HÆ\003J\013\020-\032\004\030\0010\005HÆ\003J\013\020.\032\004\030\0010\005HÆ\003J\013\020/\032\004\030\0010\005HÆ\003J\013\0200\032\004\030\0010\005HÆ\003J\013\0201\032\004\030\0010\005HÆ\003J\021\0202\032\n\022\004\022\0020\025\030\0010\024HÆ\003J\t\0203\032\0020\005HÆ\003J\t\0204\032\0020\005HÆ\003J\t\0205\032\0020\005HÆ\003J\013\0206\032\004\030\0010\005HÆ\003J\013\0207\032\004\030\0010\005HÆ\003J\013\0208\032\004\030\0010\005HÆ\003J\013\0209\032\004\030\0010\005HÆ\003J\013\020:\032\004\030\0010\005HÆ\003JÇ\001\020;\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0052\b\b\002\020\006\032\0020\0052\b\b\002\020\007\032\0020\0052\n\b\002\020\b\032\004\030\0010\0052\n\b\002\020\t\032\004\030\0010\0052\n\b\002\020\n\032\004\030\0010\0052\n\b\002\020\013\032\004\030\0010\0052\n\b\002\020\f\032\004\030\0010\0052\n\b\002\020\r\032\004\030\0010\0052\n\b\002\020\016\032\004\030\0010\0052\n\b\002\020\017\032\004\030\0010\0052\n\b\002\020\020\032\004\030\0010\0052\n\b\002\020\021\032\004\030\0010\0052\n\b\002\020\022\032\004\030\0010\0052\020\b\002\020\023\032\n\022\004\022\0020\025\030\0010\024HÆ\001J\023\020<\032\0020=2\b\020>\032\004\030\0010\001HÖ\003J\t\020?\032\0020@HÖ\001J\t\020A\032\0020\005HÖ\001R\023\020\016\032\004\030\0010\005¢\006\b\n\000\032\004\b\027\020\030R\023\020\021\032\004\030\0010\005¢\006\b\n\000\032\004\b\031\020\030R\023\020\017\032\004\030\0010\005¢\006\b\n\000\032\004\b\032\020\030R\031\020\023\032\n\022\004\022\0020\025\030\0010\024¢\006\b\n\000\032\004\b\033\020\034R\023\020\t\032\004\030\0010\005¢\006\b\n\000\032\004\b\035\020\030R\023\020\b\032\004\030\0010\005¢\006\b\n\000\032\004\b\036\020\030R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\037\020 R\021\020\006\032\0020\005¢\006\b\n\000\032\004\b!\020\030R\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\"\020\030R\021\020\007\032\0020\005¢\006\b\n\000\032\004\b#\020\030R\023\020\022\032\004\030\0010\005¢\006\b\n\000\032\004\b$\020\030R\023\020\013\032\004\030\0010\005¢\006\b\n\000\032\004\b%\020\030R\023\020\020\032\004\030\0010\005¢\006\b\n\000\032\004\b&\020\030R\023\020\n\032\004\030\0010\005¢\006\b\n\000\032\004\b'\020\030R\023\020\r\032\004\030\0010\005¢\006\b\n\000\032\004\b(\020\030R\023\020\f\032\004\030\0010\005¢\006\b\n\000\032\004\b)\020\030¨\006B"}, d2={"Lcom/byimplication/sakay/model/PhotonOsmProperty;", "", "osm_id", "", "osm_type", "", "osm_key", "osm_value", "name", "housenumber", "street", "road", "village", "town", "city", "county", "state", "country", "postcode", "extent", "", "", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getCity", "()Ljava/lang/String;", "getCountry", "getCounty", "getExtent", "()Ljava/util/List;", "getHousenumber", "getName", "getOsm_id", "()J", "getOsm_key", "getOsm_type", "getOsm_value", "getPostcode", "getRoad", "getState", "getStreet", "getTown", "getVillage", "assembleLabel", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class PhotonOsmProperty
{
  @Nullable
  private final String city;
  @Nullable
  private final String country;
  @Nullable
  private final String county;
  @Nullable
  private final List<Double> extent;
  @Nullable
  private final String housenumber;
  @Nullable
  private final String name;
  private final long osm_id;
  @NotNull
  private final String osm_key;
  @NotNull
  private final String osm_type;
  @NotNull
  private final String osm_value;
  @Nullable
  private final String postcode;
  @Nullable
  private final String road;
  @Nullable
  private final String state;
  @Nullable
  private final String street;
  @Nullable
  private final String town;
  @Nullable
  private final String village;
  
  public PhotonOsmProperty(long paramLong, @NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3, @Nullable String paramString4, @Nullable String paramString5, @Nullable String paramString6, @Nullable String paramString7, @Nullable String paramString8, @Nullable String paramString9, @Nullable String paramString10, @Nullable String paramString11, @Nullable String paramString12, @Nullable String paramString13, @Nullable String paramString14, @Nullable List<Double> paramList)
  {
    osm_id = paramLong;
    osm_type = paramString1;
    osm_key = paramString2;
    osm_value = paramString3;
    name = paramString4;
    housenumber = paramString5;
    street = paramString6;
    road = paramString7;
    village = paramString8;
    town = paramString9;
    city = paramString10;
    county = paramString11;
    state = paramString12;
    country = paramString13;
    postcode = paramString14;
    extent = paramList;
  }
  
  @NotNull
  public final String assembleLabel()
  {
    ArrayList localArrayList = new ArrayList();
    if (name != null) {
      localArrayList.add(name);
    }
    if (housenumber != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(housenumber);
      ((StringBuilder)localObject).append(" ");
      localObject = ((StringBuilder)localObject).toString();
    }
    else
    {
      localObject = "";
    }
    StringBuilder localStringBuilder;
    if (street != null)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject);
      localStringBuilder.append(street);
      localArrayList.add(localStringBuilder.toString());
    }
    if (road != null)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject);
      localStringBuilder.append(road);
      localArrayList.add(localStringBuilder.toString());
    }
    if (village != null) {
      localArrayList.add(village);
    }
    if (town != null) {
      localArrayList.add(town);
    }
    if (city != null) {
      localArrayList.add(city);
    }
    if (county != null) {
      localArrayList.add(county);
    }
    if (country != null) {
      localArrayList.add(country);
    }
    Object localObject = localArrayList.toString();
    Intrinsics.checkExpressionValueIsNotNull(localObject, "description");
    int i = ((String)localObject).length();
    if (localObject == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    localObject = ((String)localObject).substring(1, i - 1);
    Intrinsics.checkExpressionValueIsNotNull(localObject, "(this as java.lang.Strin…ing(startIndex, endIndex)");
    return localObject;
  }
  
  public final long component1()
  {
    return osm_id;
  }
  
  @Nullable
  public final String component10()
  {
    return town;
  }
  
  @Nullable
  public final String component11()
  {
    return city;
  }
  
  @Nullable
  public final String component12()
  {
    return county;
  }
  
  @Nullable
  public final String component13()
  {
    return state;
  }
  
  @Nullable
  public final String component14()
  {
    return country;
  }
  
  @Nullable
  public final String component15()
  {
    return postcode;
  }
  
  @Nullable
  public final List<Double> component16()
  {
    return extent;
  }
  
  @NotNull
  public final String component2()
  {
    return osm_type;
  }
  
  @NotNull
  public final String component3()
  {
    return osm_key;
  }
  
  @NotNull
  public final String component4()
  {
    return osm_value;
  }
  
  @Nullable
  public final String component5()
  {
    return name;
  }
  
  @Nullable
  public final String component6()
  {
    return housenumber;
  }
  
  @Nullable
  public final String component7()
  {
    return street;
  }
  
  @Nullable
  public final String component8()
  {
    return road;
  }
  
  @Nullable
  public final String component9()
  {
    return village;
  }
  
  @NotNull
  public final PhotonOsmProperty copy(long paramLong, @NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3, @Nullable String paramString4, @Nullable String paramString5, @Nullable String paramString6, @Nullable String paramString7, @Nullable String paramString8, @Nullable String paramString9, @Nullable String paramString10, @Nullable String paramString11, @Nullable String paramString12, @Nullable String paramString13, @Nullable String paramString14, @Nullable List<Double> paramList)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "osm_type");
    Intrinsics.checkParameterIsNotNull(paramString2, "osm_key");
    Intrinsics.checkParameterIsNotNull(paramString3, "osm_value");
    return new PhotonOsmProperty(paramLong, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramString9, paramString10, paramString11, paramString12, paramString13, paramString14, paramList);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof PhotonOsmProperty))
      {
        paramObject = (PhotonOsmProperty)paramObject;
        int i;
        if (osm_id == osm_id) {
          i = 1;
        } else {
          i = 0;
        }
        if ((i != 0) && (Intrinsics.areEqual(osm_type, osm_type)) && (Intrinsics.areEqual(osm_key, osm_key)) && (Intrinsics.areEqual(osm_value, osm_value)) && (Intrinsics.areEqual(name, name)) && (Intrinsics.areEqual(housenumber, housenumber)) && (Intrinsics.areEqual(street, street)) && (Intrinsics.areEqual(road, road)) && (Intrinsics.areEqual(village, village)) && (Intrinsics.areEqual(town, town)) && (Intrinsics.areEqual(city, city)) && (Intrinsics.areEqual(county, county)) && (Intrinsics.areEqual(state, state)) && (Intrinsics.areEqual(country, country)) && (Intrinsics.areEqual(postcode, postcode)) && (Intrinsics.areEqual(extent, extent))) {
          return true;
        }
      }
      return false;
    }
    return true;
  }
  
  @Nullable
  public final String getCity()
  {
    return city;
  }
  
  @Nullable
  public final String getCountry()
  {
    return country;
  }
  
  @Nullable
  public final String getCounty()
  {
    return county;
  }
  
  @Nullable
  public final List<Double> getExtent()
  {
    return extent;
  }
  
  @Nullable
  public final String getHousenumber()
  {
    return housenumber;
  }
  
  @Nullable
  public final String getName()
  {
    return name;
  }
  
  public final long getOsm_id()
  {
    return osm_id;
  }
  
  @NotNull
  public final String getOsm_key()
  {
    return osm_key;
  }
  
  @NotNull
  public final String getOsm_type()
  {
    return osm_type;
  }
  
  @NotNull
  public final String getOsm_value()
  {
    return osm_value;
  }
  
  @Nullable
  public final String getPostcode()
  {
    return postcode;
  }
  
  @Nullable
  public final String getRoad()
  {
    return road;
  }
  
  @Nullable
  public final String getState()
  {
    return state;
  }
  
  @Nullable
  public final String getStreet()
  {
    return street;
  }
  
  @Nullable
  public final String getTown()
  {
    return town;
  }
  
  @Nullable
  public final String getVillage()
  {
    return village;
  }
  
  public int hashCode()
  {
    long l = osm_id;
    int i11 = (int)(l ^ l >>> 32);
    Object localObject = osm_type;
    int i10 = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = osm_key;
    int j;
    if (localObject != null) {
      j = localObject.hashCode();
    } else {
      j = 0;
    }
    localObject = osm_value;
    int k;
    if (localObject != null) {
      k = localObject.hashCode();
    } else {
      k = 0;
    }
    localObject = name;
    int m;
    if (localObject != null) {
      m = localObject.hashCode();
    } else {
      m = 0;
    }
    localObject = housenumber;
    int n;
    if (localObject != null) {
      n = localObject.hashCode();
    } else {
      n = 0;
    }
    localObject = street;
    int i1;
    if (localObject != null) {
      i1 = localObject.hashCode();
    } else {
      i1 = 0;
    }
    localObject = road;
    int i2;
    if (localObject != null) {
      i2 = localObject.hashCode();
    } else {
      i2 = 0;
    }
    localObject = village;
    int i3;
    if (localObject != null) {
      i3 = localObject.hashCode();
    } else {
      i3 = 0;
    }
    localObject = town;
    int i4;
    if (localObject != null) {
      i4 = localObject.hashCode();
    } else {
      i4 = 0;
    }
    localObject = city;
    int i5;
    if (localObject != null) {
      i5 = localObject.hashCode();
    } else {
      i5 = 0;
    }
    localObject = county;
    int i6;
    if (localObject != null) {
      i6 = localObject.hashCode();
    } else {
      i6 = 0;
    }
    localObject = state;
    int i7;
    if (localObject != null) {
      i7 = localObject.hashCode();
    } else {
      i7 = 0;
    }
    localObject = country;
    int i8;
    if (localObject != null) {
      i8 = localObject.hashCode();
    } else {
      i8 = 0;
    }
    localObject = postcode;
    int i9;
    if (localObject != null) {
      i9 = localObject.hashCode();
    } else {
      i9 = 0;
    }
    localObject = extent;
    if (localObject != null) {
      i10 = localObject.hashCode();
    }
    return ((((((((((((((i11 * 31 + i) * 31 + j) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2) * 31 + i3) * 31 + i4) * 31 + i5) * 31 + i6) * 31 + i7) * 31 + i8) * 31 + i9) * 31 + i10;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PhotonOsmProperty(osm_id=");
    localStringBuilder.append(osm_id);
    localStringBuilder.append(", osm_type=");
    localStringBuilder.append(osm_type);
    localStringBuilder.append(", osm_key=");
    localStringBuilder.append(osm_key);
    localStringBuilder.append(", osm_value=");
    localStringBuilder.append(osm_value);
    localStringBuilder.append(", name=");
    localStringBuilder.append(name);
    localStringBuilder.append(", housenumber=");
    localStringBuilder.append(housenumber);
    localStringBuilder.append(", street=");
    localStringBuilder.append(street);
    localStringBuilder.append(", road=");
    localStringBuilder.append(road);
    localStringBuilder.append(", village=");
    localStringBuilder.append(village);
    localStringBuilder.append(", town=");
    localStringBuilder.append(town);
    localStringBuilder.append(", city=");
    localStringBuilder.append(city);
    localStringBuilder.append(", county=");
    localStringBuilder.append(county);
    localStringBuilder.append(", state=");
    localStringBuilder.append(state);
    localStringBuilder.append(", country=");
    localStringBuilder.append(country);
    localStringBuilder.append(", postcode=");
    localStringBuilder.append(postcode);
    localStringBuilder.append(", extent=");
    localStringBuilder.append(extent);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

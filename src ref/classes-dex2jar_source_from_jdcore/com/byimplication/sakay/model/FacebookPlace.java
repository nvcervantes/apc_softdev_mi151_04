package com.byimplication.sakay.model;

import com.google.android.gms.maps.model.LatLng;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Metadata(bv={1, 0, 2}, d1={"\000\"\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\005\n\002\030\002\n\002\b\n\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004R\035\020\005\032\004\030\0010\0068FX\002¢\006\f\n\004\b\t\020\n\032\004\b\007\020\bR\035\020\013\032\004\030\0010\f8FX\002¢\006\f\n\004\b\017\020\n\032\004\b\r\020\016R\035\020\020\032\004\030\0010\0068FX\002¢\006\f\n\004\b\022\020\n\032\004\b\021\020\bR\035\020\023\032\004\030\0010\0068FX\002¢\006\f\n\004\b\025\020\n\032\004\b\024\020\b¨\006\026"}, d2={"Lcom/byimplication/sakay/model/FacebookPlace;", "", "placeJson", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "id", "", "getId", "()Ljava/lang/String;", "id$delegate", "Lkotlin/Lazy;", "location", "Lcom/google/android/gms/maps/model/LatLng;", "getLocation", "()Lcom/google/android/gms/maps/model/LatLng;", "location$delegate", "name", "getName", "name$delegate", "singleLineAddress", "getSingleLineAddress", "singleLineAddress$delegate", "app_release"}, k=1, mv={1, 1, 9})
public final class FacebookPlace
{
  @Nullable
  private final Lazy id$delegate;
  @Nullable
  private final Lazy location$delegate;
  @Nullable
  private final Lazy name$delegate;
  @Nullable
  private final Lazy singleLineAddress$delegate;
  
  public FacebookPlace(@NotNull JSONObject paramJSONObject)
  {
    name$delegate = LazyKt.lazy((Function0)new Lambda(paramJSONObject)
    {
      public final String invoke()
      {
        return $placeJson.optString("name", null);
      }
    });
    id$delegate = LazyKt.lazy((Function0)new Lambda(paramJSONObject)
    {
      public final String invoke()
      {
        return $placeJson.optString("id", null);
      }
    });
    location$delegate = LazyKt.lazy((Function0)new Lambda(paramJSONObject)
    {
      @Nullable
      public final LatLng invoke()
      {
        JSONObject localJSONObject = $placeJson.optJSONObject("location");
        if ((localJSONObject != null) && (localJSONObject.has("latitude")) && (localJSONObject.has("longitude"))) {
          return new LatLng(localJSONObject.getDouble("latitude"), localJSONObject.getDouble("longitude"));
        }
        return null;
      }
    });
    singleLineAddress$delegate = LazyKt.lazy((Function0)new Lambda(paramJSONObject)
    {
      public final String invoke()
      {
        return $placeJson.optString("single_line_address", null);
      }
    });
  }
  
  @Nullable
  public final String getId()
  {
    Lazy localLazy = id$delegate;
    KProperty localKProperty = $$delegatedProperties[1];
    return (String)localLazy.getValue();
  }
  
  @Nullable
  public final LatLng getLocation()
  {
    Lazy localLazy = location$delegate;
    KProperty localKProperty = $$delegatedProperties[2];
    return (LatLng)localLazy.getValue();
  }
  
  @Nullable
  public final String getName()
  {
    Lazy localLazy = name$delegate;
    KProperty localKProperty = $$delegatedProperties[0];
    return (String)localLazy.getValue();
  }
  
  @Nullable
  public final String getSingleLineAddress()
  {
    Lazy localLazy = singleLineAddress$delegate;
    KProperty localKProperty = $$delegatedProperties[3];
    return (String)localLazy.getValue();
  }
}

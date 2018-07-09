package com.byimplication.sakay.components;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000<\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\002\b\005\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\000\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\016\020\f\032\0020\r2\006\020\016\032\0020\017J\016\020\020\032\0020\0212\006\020\022\032\0020\023J\006\020\024\032\0020\025R!\020\005\032\022\022\004\022\0020\0010\006j\b\022\004\022\0020\001`\007¢\006\b\n\000\032\004\b\b\020\tR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\n\020\013¨\006\026"}, d2={"Lcom/byimplication/sakay/components/Layer;", "", "map", "Lcom/google/android/gms/maps/GoogleMap;", "(Lcom/google/android/gms/maps/GoogleMap;)V", "layers", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getLayers", "()Ljava/util/ArrayList;", "getMap", "()Lcom/google/android/gms/maps/GoogleMap;", "addMarker", "Lcom/google/android/gms/maps/model/Marker;", "markerOpts", "Lcom/google/android/gms/maps/model/MarkerOptions;", "addPolyline", "Lcom/google/android/gms/maps/model/Polyline;", "polyOpts", "Lcom/google/android/gms/maps/model/PolylineOptions;", "clear", "", "app_release"}, k=1, mv={1, 1, 9})
public final class Layer
{
  @NotNull
  private final ArrayList<Object> layers;
  @NotNull
  private final GoogleMap map;
  
  public Layer(@NotNull GoogleMap paramGoogleMap)
  {
    map = paramGoogleMap;
    layers = new ArrayList();
  }
  
  @NotNull
  public final Marker addMarker(@NotNull MarkerOptions paramMarkerOptions)
  {
    Intrinsics.checkParameterIsNotNull(paramMarkerOptions, "markerOpts");
    paramMarkerOptions = map.addMarker(paramMarkerOptions);
    ((Collection)layers).add(paramMarkerOptions);
    Intrinsics.checkExpressionValueIsNotNull(paramMarkerOptions, "marker");
    return paramMarkerOptions;
  }
  
  @NotNull
  public final Polyline addPolyline(@NotNull PolylineOptions paramPolylineOptions)
  {
    Intrinsics.checkParameterIsNotNull(paramPolylineOptions, "polyOpts");
    paramPolylineOptions = map.addPolyline(paramPolylineOptions);
    ((Collection)layers).add(paramPolylineOptions);
    Intrinsics.checkExpressionValueIsNotNull(paramPolylineOptions, "polyline");
    return paramPolylineOptions;
  }
  
  public final void clear()
  {
    Iterator localIterator = layers.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if ((localObject instanceof Marker)) {
        ((Marker)localObject).remove();
      } else if ((localObject instanceof Polyline)) {
        ((Polyline)localObject).remove();
      }
    }
    layers.clear();
  }
  
  @NotNull
  public final ArrayList<Object> getLayers()
  {
    return layers;
  }
  
  @NotNull
  public final GoogleMap getMap()
  {
    return map;
  }
}

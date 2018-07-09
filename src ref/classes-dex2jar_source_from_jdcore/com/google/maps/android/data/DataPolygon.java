package com.google.maps.android.data;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public abstract interface DataPolygon<T>
  extends Geometry
{
  public abstract List<List<LatLng>> getInnerBoundaryCoordinates();
  
  public abstract List<LatLng> getOuterBoundaryCoordinates();
}

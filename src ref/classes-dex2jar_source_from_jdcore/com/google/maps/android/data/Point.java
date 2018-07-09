package com.google.maps.android.data;

import com.google.android.gms.maps.model.LatLng;

public class Point
  implements Geometry
{
  private static final String GEOMETRY_TYPE = "Point";
  private final LatLng mCoordinates;
  
  public Point(LatLng paramLatLng)
  {
    if (paramLatLng == null) {
      throw new IllegalArgumentException("Coordinates cannot be null");
    }
    mCoordinates = paramLatLng;
  }
  
  public LatLng getGeometryObject()
  {
    return mCoordinates;
  }
  
  public String getGeometryType()
  {
    return "Point";
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Point");
    localStringBuilder.append("{");
    localStringBuilder.append("\n coordinates=");
    localStringBuilder.append(mCoordinates);
    localStringBuilder.append("\n}\n");
    return localStringBuilder.toString();
  }
}

package com.google.maps.android.data;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public class LineString
  implements Geometry<List<LatLng>>
{
  private static final String GEOMETRY_TYPE = "LineString";
  private final List<LatLng> mCoordinates;
  
  public LineString(List<LatLng> paramList)
  {
    if (paramList == null) {
      throw new IllegalArgumentException("Coordinates cannot be null");
    }
    mCoordinates = paramList;
  }
  
  public List<LatLng> getGeometryObject()
  {
    return mCoordinates;
  }
  
  public String getGeometryType()
  {
    return "LineString";
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("LineString");
    localStringBuilder.append("{");
    localStringBuilder.append("\n coordinates=");
    localStringBuilder.append(mCoordinates);
    localStringBuilder.append("\n}\n");
    return localStringBuilder.toString();
  }
}

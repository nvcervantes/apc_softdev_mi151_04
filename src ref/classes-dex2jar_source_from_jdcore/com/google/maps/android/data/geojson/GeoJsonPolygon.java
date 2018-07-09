package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.DataPolygon;
import java.util.ArrayList;
import java.util.List;

public class GeoJsonPolygon
  implements DataPolygon
{
  private static final String GEOMETRY_TYPE = "Polygon";
  private static final int POLYGON_INNER_COORDINATE_INDEX = 1;
  private static final int POLYGON_OUTER_COORDINATE_INDEX = 0;
  private final List<? extends List<LatLng>> mCoordinates;
  
  public GeoJsonPolygon(List<? extends List<LatLng>> paramList)
  {
    if (paramList == null) {
      throw new IllegalArgumentException("Coordinates cannot be null");
    }
    mCoordinates = paramList;
  }
  
  public List<? extends List<LatLng>> getCoordinates()
  {
    return mCoordinates;
  }
  
  public List<? extends List<LatLng>> getGeometryObject()
  {
    return getCoordinates();
  }
  
  public String getGeometryType()
  {
    return getType();
  }
  
  public ArrayList<ArrayList<LatLng>> getInnerBoundaryCoordinates()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 1;
    while (i < getCoordinates().size())
    {
      localArrayList.add((ArrayList)getCoordinates().get(i));
      i += 1;
    }
    return localArrayList;
  }
  
  public ArrayList<LatLng> getOuterBoundaryCoordinates()
  {
    return (ArrayList)getCoordinates().get(0);
  }
  
  public String getType()
  {
    return "Polygon";
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Polygon");
    localStringBuilder.append("{");
    localStringBuilder.append("\n coordinates=");
    localStringBuilder.append(mCoordinates);
    localStringBuilder.append("\n}\n");
    return localStringBuilder.toString();
  }
}

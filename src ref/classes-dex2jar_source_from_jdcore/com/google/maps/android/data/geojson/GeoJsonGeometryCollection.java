package com.google.maps.android.data.geojson;

import com.google.maps.android.data.Geometry;
import com.google.maps.android.data.MultiGeometry;
import java.util.List;

public class GeoJsonGeometryCollection
  extends MultiGeometry
{
  public GeoJsonGeometryCollection(List<Geometry> paramList)
  {
    super(paramList);
    setGeometryType("GeometryCollection");
  }
  
  public List<Geometry> getGeometries()
  {
    return getGeometryObject();
  }
  
  public String getType()
  {
    return getGeometryType();
  }
}

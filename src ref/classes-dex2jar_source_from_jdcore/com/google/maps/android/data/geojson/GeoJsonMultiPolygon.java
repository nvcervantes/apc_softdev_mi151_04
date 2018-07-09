package com.google.maps.android.data.geojson;

import com.google.maps.android.data.MultiGeometry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeoJsonMultiPolygon
  extends MultiGeometry
{
  public GeoJsonMultiPolygon(List<GeoJsonPolygon> paramList)
  {
    super(paramList);
    setGeometryType("MultiPolygon");
  }
  
  public List<GeoJsonPolygon> getPolygons()
  {
    Object localObject = getGeometryObject();
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add((GeoJsonPolygon)((Iterator)localObject).next());
    }
    return localArrayList;
  }
  
  public String getType()
  {
    return getGeometryType();
  }
}

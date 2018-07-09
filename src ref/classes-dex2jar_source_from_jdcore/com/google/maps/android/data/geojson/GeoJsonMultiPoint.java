package com.google.maps.android.data.geojson;

import com.google.maps.android.data.MultiGeometry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeoJsonMultiPoint
  extends MultiGeometry
{
  public GeoJsonMultiPoint(List<GeoJsonPoint> paramList)
  {
    super(paramList);
    setGeometryType("MultiPoint");
  }
  
  public List<GeoJsonPoint> getPoints()
  {
    Object localObject = getGeometryObject();
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add((GeoJsonPoint)((Iterator)localObject).next());
    }
    return localArrayList;
  }
  
  public String getType()
  {
    return getGeometryType();
  }
}

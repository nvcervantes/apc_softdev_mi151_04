package com.google.maps.android.data.geojson;

import com.google.maps.android.data.MultiGeometry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeoJsonMultiLineString
  extends MultiGeometry
{
  public GeoJsonMultiLineString(List<GeoJsonLineString> paramList)
  {
    super(paramList);
    setGeometryType("MultiLineString");
  }
  
  public List<GeoJsonLineString> getLineStrings()
  {
    Object localObject = getGeometryObject();
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add((GeoJsonLineString)((Iterator)localObject).next());
    }
    return localArrayList;
  }
  
  public String getType()
  {
    return getGeometryType();
  }
}

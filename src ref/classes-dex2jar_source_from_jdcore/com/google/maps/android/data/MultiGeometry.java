package com.google.maps.android.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiGeometry
  implements Geometry
{
  private String geometryType = "MultiGeometry";
  private List<Geometry> mGeometries;
  
  public MultiGeometry(List<? extends Geometry> paramList)
  {
    if (paramList == null) {
      throw new IllegalArgumentException("Geometries cannot be null");
    }
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      localArrayList.add((Geometry)paramList.next());
    }
    mGeometries = localArrayList;
  }
  
  public List<Geometry> getGeometryObject()
  {
    return mGeometries;
  }
  
  public String getGeometryType()
  {
    return geometryType;
  }
  
  public void setGeometryType(String paramString)
  {
    geometryType = paramString;
  }
  
  public String toString()
  {
    String str = "Geometries=";
    if (geometryType.equals("MultiPoint")) {
      str = "LineStrings=";
    }
    if (geometryType.equals("MultiLineString")) {
      str = "points=";
    }
    if (geometryType.equals("MultiPolygon")) {
      str = "Polygons=";
    }
    StringBuilder localStringBuilder1 = new StringBuilder(getGeometryType());
    localStringBuilder1.append("{");
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("\n ");
    localStringBuilder2.append(str);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder1.append(getGeometryObject());
    localStringBuilder1.append("\n}\n");
    return localStringBuilder1.toString();
  }
}

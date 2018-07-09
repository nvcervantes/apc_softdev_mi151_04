package com.google.maps.android.data.kml;

import com.google.maps.android.data.Geometry;
import com.google.maps.android.data.MultiGeometry;
import java.util.ArrayList;

public class KmlMultiGeometry
  extends MultiGeometry
{
  public KmlMultiGeometry(ArrayList<Geometry> paramArrayList)
  {
    super(paramArrayList);
  }
  
  public ArrayList<Geometry> getGeometryObject()
  {
    return new ArrayList(super.getGeometryObject());
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(getGeometryType());
    localStringBuilder.append("{");
    localStringBuilder.append("\n geometries=");
    localStringBuilder.append(getGeometryObject());
    localStringBuilder.append("\n}\n");
    return localStringBuilder.toString();
  }
}

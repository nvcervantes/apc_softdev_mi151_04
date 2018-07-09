package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.DataPolygon;
import java.util.ArrayList;
import java.util.List;

public class KmlPolygon
  implements DataPolygon<ArrayList<ArrayList<LatLng>>>
{
  public static final String GEOMETRY_TYPE = "Polygon";
  private final List<List<LatLng>> mInnerBoundaryCoordinates;
  private final List<LatLng> mOuterBoundaryCoordinates;
  
  public KmlPolygon(List<LatLng> paramList, List<List<LatLng>> paramList1)
  {
    if (paramList == null) {
      throw new IllegalArgumentException("Outer boundary coordinates cannot be null");
    }
    mOuterBoundaryCoordinates = paramList;
    mInnerBoundaryCoordinates = paramList1;
  }
  
  public List<List<LatLng>> getGeometryObject()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(mOuterBoundaryCoordinates);
    if (mInnerBoundaryCoordinates != null) {
      localArrayList.addAll(mInnerBoundaryCoordinates);
    }
    return localArrayList;
  }
  
  public String getGeometryType()
  {
    return "Polygon";
  }
  
  public List<List<LatLng>> getInnerBoundaryCoordinates()
  {
    return mInnerBoundaryCoordinates;
  }
  
  public List<LatLng> getOuterBoundaryCoordinates()
  {
    return mOuterBoundaryCoordinates;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Polygon");
    localStringBuilder.append("{");
    localStringBuilder.append("\n outer coordinates=");
    localStringBuilder.append(mOuterBoundaryCoordinates);
    localStringBuilder.append(",\n inner coordinates=");
    localStringBuilder.append(mInnerBoundaryCoordinates);
    localStringBuilder.append("\n}\n");
    return localStringBuilder.toString();
  }
}

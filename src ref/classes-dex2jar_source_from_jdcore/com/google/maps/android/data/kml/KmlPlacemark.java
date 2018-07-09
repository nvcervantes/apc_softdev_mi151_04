package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Geometry;
import java.util.HashMap;

public class KmlPlacemark
  extends Feature
{
  private final KmlStyle mInlineStyle;
  private final String mStyle;
  
  public KmlPlacemark(Geometry paramGeometry, String paramString, KmlStyle paramKmlStyle, HashMap<String, String> paramHashMap)
  {
    super(paramGeometry, paramString, paramHashMap);
    mStyle = paramString;
    mInlineStyle = paramKmlStyle;
  }
  
  public KmlStyle getInlineStyle()
  {
    return mInlineStyle;
  }
  
  public MarkerOptions getMarkerOptions()
  {
    return mInlineStyle.getMarkerOptions();
  }
  
  public PolygonOptions getPolygonOptions()
  {
    return mInlineStyle.getPolygonOptions();
  }
  
  public PolylineOptions getPolylineOptions()
  {
    return mInlineStyle.getPolylineOptions();
  }
  
  public String getStyleId()
  {
    return super.getId();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Placemark");
    localStringBuilder.append("{");
    localStringBuilder.append("\n style id=");
    localStringBuilder.append(mStyle);
    localStringBuilder.append(",\n inline style=");
    localStringBuilder.append(mInlineStyle);
    localStringBuilder.append("\n}\n");
    return localStringBuilder.toString();
  }
}

package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Geometry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GeoJsonFeature
  extends Feature
  implements Observer
{
  private final LatLngBounds mBoundingBox;
  private final String mId;
  private GeoJsonLineStringStyle mLineStringStyle;
  private GeoJsonPointStyle mPointStyle;
  private GeoJsonPolygonStyle mPolygonStyle;
  
  public GeoJsonFeature(Geometry paramGeometry, String paramString, HashMap<String, String> paramHashMap, LatLngBounds paramLatLngBounds)
  {
    super(paramGeometry, paramString, paramHashMap);
    mId = paramString;
    mBoundingBox = paramLatLngBounds;
  }
  
  private void checkRedrawFeature(GeoJsonStyle paramGeoJsonStyle)
  {
    if ((hasGeometry()) && (Arrays.asList(paramGeoJsonStyle.getGeometryType()).contains(getGeometry().getGeometryType())))
    {
      setChanged();
      notifyObservers();
    }
  }
  
  public LatLngBounds getBoundingBox()
  {
    return mBoundingBox;
  }
  
  public GeoJsonLineStringStyle getLineStringStyle()
  {
    return mLineStringStyle;
  }
  
  public MarkerOptions getMarkerOptions()
  {
    return mPointStyle.toMarkerOptions();
  }
  
  public GeoJsonPointStyle getPointStyle()
  {
    return mPointStyle;
  }
  
  public PolygonOptions getPolygonOptions()
  {
    return mPolygonStyle.toPolygonOptions();
  }
  
  public GeoJsonPolygonStyle getPolygonStyle()
  {
    return mPolygonStyle;
  }
  
  public PolylineOptions getPolylineOptions()
  {
    return mLineStringStyle.toPolylineOptions();
  }
  
  public String removeProperty(String paramString)
  {
    return super.removeProperty(paramString);
  }
  
  public void setGeometry(Geometry paramGeometry)
  {
    super.setGeometry(paramGeometry);
    setChanged();
    notifyObservers();
  }
  
  public void setLineStringStyle(GeoJsonLineStringStyle paramGeoJsonLineStringStyle)
  {
    if (paramGeoJsonLineStringStyle == null) {
      throw new IllegalArgumentException("Line string style cannot be null");
    }
    if (mLineStringStyle != null) {
      mLineStringStyle.deleteObserver(this);
    }
    mLineStringStyle = paramGeoJsonLineStringStyle;
    mLineStringStyle.addObserver(this);
    checkRedrawFeature(mLineStringStyle);
  }
  
  public void setPointStyle(GeoJsonPointStyle paramGeoJsonPointStyle)
  {
    if (paramGeoJsonPointStyle == null) {
      throw new IllegalArgumentException("Point style cannot be null");
    }
    if (mPointStyle != null) {
      mPointStyle.deleteObserver(this);
    }
    mPointStyle = paramGeoJsonPointStyle;
    mPointStyle.addObserver(this);
    checkRedrawFeature(mPointStyle);
  }
  
  public void setPolygonStyle(GeoJsonPolygonStyle paramGeoJsonPolygonStyle)
  {
    if (paramGeoJsonPolygonStyle == null) {
      throw new IllegalArgumentException("Polygon style cannot be null");
    }
    if (mPolygonStyle != null) {
      mPolygonStyle.deleteObserver(this);
    }
    mPolygonStyle = paramGeoJsonPolygonStyle;
    mPolygonStyle.addObserver(this);
    checkRedrawFeature(mPolygonStyle);
  }
  
  public String setProperty(String paramString1, String paramString2)
  {
    return super.setProperty(paramString1, paramString2);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Feature{");
    localStringBuilder.append("\n bounding box=");
    localStringBuilder.append(mBoundingBox);
    localStringBuilder.append(",\n geometry=");
    localStringBuilder.append(getGeometry());
    localStringBuilder.append(",\n point style=");
    localStringBuilder.append(mPointStyle);
    localStringBuilder.append(",\n line string style=");
    localStringBuilder.append(mLineStringStyle);
    localStringBuilder.append(",\n polygon style=");
    localStringBuilder.append(mPolygonStyle);
    localStringBuilder.append(",\n id=");
    localStringBuilder.append(mId);
    localStringBuilder.append(",\n properties=");
    localStringBuilder.append(getProperties());
    localStringBuilder.append("\n}\n");
    return localStringBuilder.toString();
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    if ((paramObservable instanceof GeoJsonStyle)) {
      checkRedrawFeature((GeoJsonStyle)paramObservable);
    }
  }
}

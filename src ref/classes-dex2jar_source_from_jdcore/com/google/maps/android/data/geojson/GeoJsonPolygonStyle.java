package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.data.Style;
import java.util.Arrays;

public class GeoJsonPolygonStyle
  extends Style
  implements GeoJsonStyle
{
  private static final String[] GEOMETRY_TYPE = { "Polygon", "MultiPolygon", "GeometryCollection" };
  
  public GeoJsonPolygonStyle()
  {
    mPolygonOptions = new PolygonOptions();
  }
  
  private void styleChanged()
  {
    setChanged();
    notifyObservers();
  }
  
  public int getFillColor()
  {
    return mPolygonOptions.getFillColor();
  }
  
  public String[] getGeometryType()
  {
    return GEOMETRY_TYPE;
  }
  
  public int getStrokeColor()
  {
    return mPolygonOptions.getStrokeColor();
  }
  
  public float getStrokeWidth()
  {
    return mPolygonOptions.getStrokeWidth();
  }
  
  public float getZIndex()
  {
    return mPolygonOptions.getZIndex();
  }
  
  public boolean isGeodesic()
  {
    return mPolygonOptions.isGeodesic();
  }
  
  public boolean isVisible()
  {
    return mPolygonOptions.isVisible();
  }
  
  public void setFillColor(int paramInt)
  {
    setPolygonFillColor(paramInt);
    styleChanged();
  }
  
  public void setGeodesic(boolean paramBoolean)
  {
    mPolygonOptions.geodesic(paramBoolean);
    styleChanged();
  }
  
  public void setStrokeColor(int paramInt)
  {
    mPolygonOptions.strokeColor(paramInt);
    styleChanged();
  }
  
  public void setStrokeWidth(float paramFloat)
  {
    setPolygonStrokeWidth(paramFloat);
    styleChanged();
  }
  
  public void setVisible(boolean paramBoolean)
  {
    mPolygonOptions.visible(paramBoolean);
    styleChanged();
  }
  
  public void setZIndex(float paramFloat)
  {
    mPolygonOptions.zIndex(paramFloat);
    styleChanged();
  }
  
  public PolygonOptions toPolygonOptions()
  {
    PolygonOptions localPolygonOptions = new PolygonOptions();
    localPolygonOptions.fillColor(mPolygonOptions.getFillColor());
    localPolygonOptions.geodesic(mPolygonOptions.isGeodesic());
    localPolygonOptions.strokeColor(mPolygonOptions.getStrokeColor());
    localPolygonOptions.strokeWidth(mPolygonOptions.getStrokeWidth());
    localPolygonOptions.visible(mPolygonOptions.isVisible());
    localPolygonOptions.zIndex(mPolygonOptions.getZIndex());
    return localPolygonOptions;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("PolygonStyle{");
    localStringBuilder.append("\n geometry type=");
    localStringBuilder.append(Arrays.toString(GEOMETRY_TYPE));
    localStringBuilder.append(",\n fill color=");
    localStringBuilder.append(getFillColor());
    localStringBuilder.append(",\n geodesic=");
    localStringBuilder.append(isGeodesic());
    localStringBuilder.append(",\n stroke color=");
    localStringBuilder.append(getStrokeColor());
    localStringBuilder.append(",\n stroke width=");
    localStringBuilder.append(getStrokeWidth());
    localStringBuilder.append(",\n visible=");
    localStringBuilder.append(isVisible());
    localStringBuilder.append(",\n z index=");
    localStringBuilder.append(getZIndex());
    localStringBuilder.append("\n}\n");
    return localStringBuilder.toString();
  }
}

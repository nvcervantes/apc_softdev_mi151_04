package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.data.Style;
import java.util.Arrays;

public class GeoJsonPointStyle
  extends Style
  implements GeoJsonStyle
{
  private static final String[] GEOMETRY_TYPE = { "Point", "MultiPoint", "GeometryCollection" };
  
  public GeoJsonPointStyle()
  {
    mMarkerOptions = new MarkerOptions();
  }
  
  private void styleChanged()
  {
    setChanged();
    notifyObservers();
  }
  
  public float getAlpha()
  {
    return mMarkerOptions.getAlpha();
  }
  
  public float getAnchorU()
  {
    return mMarkerOptions.getAnchorU();
  }
  
  public float getAnchorV()
  {
    return mMarkerOptions.getAnchorV();
  }
  
  public String[] getGeometryType()
  {
    return GEOMETRY_TYPE;
  }
  
  public BitmapDescriptor getIcon()
  {
    return mMarkerOptions.getIcon();
  }
  
  public float getInfoWindowAnchorU()
  {
    return mMarkerOptions.getInfoWindowAnchorU();
  }
  
  public float getInfoWindowAnchorV()
  {
    return mMarkerOptions.getInfoWindowAnchorV();
  }
  
  public float getRotation()
  {
    return mMarkerOptions.getRotation();
  }
  
  public String getSnippet()
  {
    return mMarkerOptions.getSnippet();
  }
  
  public String getTitle()
  {
    return mMarkerOptions.getTitle();
  }
  
  public boolean isDraggable()
  {
    return mMarkerOptions.isDraggable();
  }
  
  public boolean isFlat()
  {
    return mMarkerOptions.isFlat();
  }
  
  public boolean isVisible()
  {
    return mMarkerOptions.isVisible();
  }
  
  public void setAlpha(float paramFloat)
  {
    mMarkerOptions.alpha(paramFloat);
    styleChanged();
  }
  
  public void setAnchor(float paramFloat1, float paramFloat2)
  {
    setMarkerHotSpot(paramFloat1, paramFloat2, "fraction", "fraction");
    styleChanged();
  }
  
  public void setDraggable(boolean paramBoolean)
  {
    mMarkerOptions.draggable(paramBoolean);
    styleChanged();
  }
  
  public void setFlat(boolean paramBoolean)
  {
    mMarkerOptions.flat(paramBoolean);
    styleChanged();
  }
  
  public void setIcon(BitmapDescriptor paramBitmapDescriptor)
  {
    mMarkerOptions.icon(paramBitmapDescriptor);
    styleChanged();
  }
  
  public void setInfoWindowAnchor(float paramFloat1, float paramFloat2)
  {
    mMarkerOptions.infoWindowAnchor(paramFloat1, paramFloat2);
    styleChanged();
  }
  
  public void setRotation(float paramFloat)
  {
    setMarkerRotation(paramFloat);
    styleChanged();
  }
  
  public void setSnippet(String paramString)
  {
    mMarkerOptions.snippet(paramString);
    styleChanged();
  }
  
  public void setTitle(String paramString)
  {
    mMarkerOptions.title(paramString);
    styleChanged();
  }
  
  public void setVisible(boolean paramBoolean)
  {
    mMarkerOptions.visible(paramBoolean);
    styleChanged();
  }
  
  public MarkerOptions toMarkerOptions()
  {
    MarkerOptions localMarkerOptions = new MarkerOptions();
    localMarkerOptions.alpha(mMarkerOptions.getAlpha());
    localMarkerOptions.anchor(mMarkerOptions.getAnchorU(), mMarkerOptions.getAnchorV());
    localMarkerOptions.draggable(mMarkerOptions.isDraggable());
    localMarkerOptions.flat(mMarkerOptions.isFlat());
    localMarkerOptions.icon(mMarkerOptions.getIcon());
    localMarkerOptions.infoWindowAnchor(mMarkerOptions.getInfoWindowAnchorU(), mMarkerOptions.getInfoWindowAnchorV());
    localMarkerOptions.rotation(mMarkerOptions.getRotation());
    localMarkerOptions.snippet(mMarkerOptions.getSnippet());
    localMarkerOptions.title(mMarkerOptions.getTitle());
    localMarkerOptions.visible(mMarkerOptions.isVisible());
    return localMarkerOptions;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("PointStyle{");
    localStringBuilder.append("\n geometry type=");
    localStringBuilder.append(Arrays.toString(GEOMETRY_TYPE));
    localStringBuilder.append(",\n alpha=");
    localStringBuilder.append(getAlpha());
    localStringBuilder.append(",\n anchor U=");
    localStringBuilder.append(getAnchorU());
    localStringBuilder.append(",\n anchor V=");
    localStringBuilder.append(getAnchorV());
    localStringBuilder.append(",\n draggable=");
    localStringBuilder.append(isDraggable());
    localStringBuilder.append(",\n flat=");
    localStringBuilder.append(isFlat());
    localStringBuilder.append(",\n info window anchor U=");
    localStringBuilder.append(getInfoWindowAnchorU());
    localStringBuilder.append(",\n info window anchor V=");
    localStringBuilder.append(getInfoWindowAnchorV());
    localStringBuilder.append(",\n rotation=");
    localStringBuilder.append(getRotation());
    localStringBuilder.append(",\n snippet=");
    localStringBuilder.append(getSnippet());
    localStringBuilder.append(",\n title=");
    localStringBuilder.append(getTitle());
    localStringBuilder.append(",\n visible=");
    localStringBuilder.append(isVisible());
    localStringBuilder.append("\n}\n");
    return localStringBuilder.toString();
  }
}

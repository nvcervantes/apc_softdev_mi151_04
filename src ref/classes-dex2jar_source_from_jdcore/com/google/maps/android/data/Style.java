package com.google.maps.android.data;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.Observable;

public abstract class Style
  extends Observable
{
  protected MarkerOptions mMarkerOptions = new MarkerOptions();
  protected PolygonOptions mPolygonOptions = new PolygonOptions();
  protected PolylineOptions mPolylineOptions = new PolylineOptions();
  
  public Style() {}
  
  public float getRotation()
  {
    return mMarkerOptions.getRotation();
  }
  
  public void setLineStringWidth(float paramFloat)
  {
    mPolylineOptions.width(paramFloat);
  }
  
  public void setMarkerHotSpot(float paramFloat1, float paramFloat2, String paramString1, String paramString2)
  {
    if (!paramString1.equals("fraction")) {
      paramFloat1 = 0.5F;
    }
    if (!paramString2.equals("fraction")) {
      paramFloat2 = 1.0F;
    }
    mMarkerOptions.anchor(paramFloat1, paramFloat2);
  }
  
  public void setMarkerRotation(float paramFloat)
  {
    mMarkerOptions.rotation(paramFloat);
  }
  
  public void setPolygonFillColor(int paramInt)
  {
    mPolygonOptions.fillColor(paramInt);
  }
  
  public void setPolygonStrokeWidth(float paramFloat)
  {
    mPolygonOptions.strokeWidth(paramFloat);
  }
}

package com.github.mikephil.charting.data;

import android.annotation.SuppressLint;

@SuppressLint({"ParcelCreator"})
public class RadarEntry
  extends Entry
{
  public RadarEntry(float paramFloat)
  {
    super(0.0F, paramFloat);
  }
  
  public RadarEntry(float paramFloat, Object paramObject)
  {
    super(0.0F, paramFloat, paramObject);
  }
  
  public RadarEntry copy()
  {
    return new RadarEntry(getY(), getData());
  }
  
  public float getValue()
  {
    return getY();
  }
  
  @Deprecated
  public float getX()
  {
    return super.getX();
  }
  
  @Deprecated
  public void setX(float paramFloat)
  {
    super.setX(paramFloat);
  }
}

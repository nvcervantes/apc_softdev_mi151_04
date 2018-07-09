package com.github.mikephil.charting.interfaces.datasets;

import com.github.mikephil.charting.data.RadarEntry;

public abstract interface IRadarDataSet
  extends ILineRadarDataSet<RadarEntry>
{
  public abstract int getHighlightCircleFillColor();
  
  public abstract float getHighlightCircleInnerRadius();
  
  public abstract float getHighlightCircleOuterRadius();
  
  public abstract int getHighlightCircleStrokeAlpha();
  
  public abstract int getHighlightCircleStrokeColor();
  
  public abstract float getHighlightCircleStrokeWidth();
  
  public abstract boolean isDrawHighlightCircleEnabled();
  
  public abstract void setDrawHighlightCircleEnabled(boolean paramBoolean);
}

package com.github.mikephil.charting.interfaces.datasets;

import android.graphics.DashPathEffect;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet.Mode;
import com.github.mikephil.charting.formatter.IFillFormatter;

public abstract interface ILineDataSet
  extends ILineRadarDataSet<Entry>
{
  public abstract int getCircleColor(int paramInt);
  
  public abstract int getCircleColorCount();
  
  public abstract int getCircleHoleColor();
  
  public abstract float getCircleHoleRadius();
  
  public abstract float getCircleRadius();
  
  public abstract float getCubicIntensity();
  
  public abstract DashPathEffect getDashPathEffect();
  
  public abstract IFillFormatter getFillFormatter();
  
  public abstract LineDataSet.Mode getMode();
  
  public abstract boolean isDashedLineEnabled();
  
  public abstract boolean isDrawCircleHoleEnabled();
  
  public abstract boolean isDrawCirclesEnabled();
  
  @Deprecated
  public abstract boolean isDrawCubicEnabled();
  
  @Deprecated
  public abstract boolean isDrawSteppedEnabled();
}

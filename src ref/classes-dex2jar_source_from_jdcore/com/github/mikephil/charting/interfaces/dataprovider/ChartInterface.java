package com.github.mikephil.charting.interfaces.dataprovider;

import android.graphics.RectF;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.MPPointF;

public abstract interface ChartInterface
{
  public abstract MPPointF getCenterOfView();
  
  public abstract MPPointF getCenterOffsets();
  
  public abstract RectF getContentRect();
  
  public abstract ChartData getData();
  
  public abstract IValueFormatter getDefaultValueFormatter();
  
  public abstract int getHeight();
  
  public abstract float getMaxHighlightDistance();
  
  public abstract int getMaxVisibleCount();
  
  public abstract int getWidth();
  
  public abstract float getXChartMax();
  
  public abstract float getXChartMin();
  
  public abstract float getXRange();
  
  public abstract float getYChartMax();
  
  public abstract float getYChartMin();
}

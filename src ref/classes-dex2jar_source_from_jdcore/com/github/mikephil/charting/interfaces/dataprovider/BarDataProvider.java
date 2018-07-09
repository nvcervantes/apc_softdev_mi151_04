package com.github.mikephil.charting.interfaces.dataprovider;

import com.github.mikephil.charting.data.BarData;

public abstract interface BarDataProvider
  extends BarLineScatterCandleBubbleDataProvider
{
  public abstract BarData getBarData();
  
  public abstract boolean isDrawBarShadowEnabled();
  
  public abstract boolean isDrawValueAboveBarEnabled();
  
  public abstract boolean isHighlightFullBarEnabled();
}

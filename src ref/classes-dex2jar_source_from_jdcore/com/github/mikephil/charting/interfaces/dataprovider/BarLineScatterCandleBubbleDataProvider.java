package com.github.mikephil.charting.interfaces.dataprovider;

import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.utils.Transformer;

public abstract interface BarLineScatterCandleBubbleDataProvider
  extends ChartInterface
{
  public abstract BarLineScatterCandleBubbleData getData();
  
  public abstract float getHighestVisibleX();
  
  public abstract float getLowestVisibleX();
  
  public abstract Transformer getTransformer(YAxis.AxisDependency paramAxisDependency);
  
  public abstract boolean isInverted(YAxis.AxisDependency paramAxisDependency);
}

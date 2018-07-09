package com.github.mikephil.charting.interfaces.dataprovider;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.LineData;

public abstract interface LineDataProvider
  extends BarLineScatterCandleBubbleDataProvider
{
  public abstract YAxis getAxis(YAxis.AxisDependency paramAxisDependency);
  
  public abstract LineData getLineData();
}

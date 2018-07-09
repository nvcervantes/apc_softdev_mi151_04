package com.github.mikephil.charting.interfaces.dataprovider;

import com.github.mikephil.charting.data.ScatterData;

public abstract interface ScatterDataProvider
  extends BarLineScatterCandleBubbleDataProvider
{
  public abstract ScatterData getScatterData();
}

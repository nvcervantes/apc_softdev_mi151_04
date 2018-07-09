package com.github.mikephil.charting.interfaces.datasets;

import com.github.mikephil.charting.data.Entry;

public abstract interface IBarLineScatterCandleBubbleDataSet<T extends Entry>
  extends IDataSet<T>
{
  public abstract int getHighLightColor();
}

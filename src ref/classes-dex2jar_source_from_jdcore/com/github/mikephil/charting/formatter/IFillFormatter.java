package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

public abstract interface IFillFormatter
{
  public abstract float getFillLinePosition(ILineDataSet paramILineDataSet, LineDataProvider paramLineDataProvider);
}

package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;

public abstract interface ColorFormatter
{
  public abstract int getColor(int paramInt, Entry paramEntry, IDataSet paramIDataSet);
}

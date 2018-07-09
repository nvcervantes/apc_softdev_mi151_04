package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract interface IValueFormatter
{
  public abstract String getFormattedValue(float paramFloat, Entry paramEntry, int paramInt, ViewPortHandler paramViewPortHandler);
}

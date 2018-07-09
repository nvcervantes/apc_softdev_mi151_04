package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;

public abstract interface IAxisValueFormatter
{
  public abstract String getFormattedValue(float paramFloat, AxisBase paramAxisBase);
}

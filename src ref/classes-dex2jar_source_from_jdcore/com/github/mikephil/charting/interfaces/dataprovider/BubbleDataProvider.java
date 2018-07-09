package com.github.mikephil.charting.interfaces.dataprovider;

import com.github.mikephil.charting.data.BubbleData;

public abstract interface BubbleDataProvider
  extends BarLineScatterCandleBubbleDataProvider
{
  public abstract BubbleData getBubbleData();
}

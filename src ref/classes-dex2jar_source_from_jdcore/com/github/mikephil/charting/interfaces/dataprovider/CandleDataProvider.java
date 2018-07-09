package com.github.mikephil.charting.interfaces.dataprovider;

import com.github.mikephil.charting.data.CandleData;

public abstract interface CandleDataProvider
  extends BarLineScatterCandleBubbleDataProvider
{
  public abstract CandleData getCandleData();
}

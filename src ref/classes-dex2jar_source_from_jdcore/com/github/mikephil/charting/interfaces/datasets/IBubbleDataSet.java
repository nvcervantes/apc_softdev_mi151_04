package com.github.mikephil.charting.interfaces.datasets;

import com.github.mikephil.charting.data.BubbleEntry;

public abstract interface IBubbleDataSet
  extends IBarLineScatterCandleBubbleDataSet<BubbleEntry>
{
  public abstract float getHighlightCircleWidth();
  
  public abstract float getMaxSize();
  
  public abstract boolean isNormalizeSizeEnabled();
  
  public abstract void setHighlightCircleWidth(float paramFloat);
}

package com.github.mikephil.charting.interfaces.datasets;

import android.graphics.DashPathEffect;
import com.github.mikephil.charting.data.Entry;

public abstract interface ILineScatterCandleRadarDataSet<T extends Entry>
  extends IBarLineScatterCandleBubbleDataSet<T>
{
  public abstract DashPathEffect getDashPathEffectHighlight();
  
  public abstract float getHighlightLineWidth();
  
  public abstract boolean isHorizontalHighlightIndicatorEnabled();
  
  public abstract boolean isVerticalHighlightIndicatorEnabled();
}

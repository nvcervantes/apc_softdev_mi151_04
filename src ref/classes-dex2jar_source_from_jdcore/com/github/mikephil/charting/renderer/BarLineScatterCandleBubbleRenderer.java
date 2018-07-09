package com.github.mikephil.charting.renderer;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.DataSet.Rounding;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class BarLineScatterCandleBubbleRenderer
  extends DataRenderer
{
  protected XBounds mXBounds = new XBounds();
  
  public BarLineScatterCandleBubbleRenderer(ChartAnimator paramChartAnimator, ViewPortHandler paramViewPortHandler)
  {
    super(paramChartAnimator, paramViewPortHandler);
  }
  
  protected boolean isInBoundsX(Entry paramEntry, IBarLineScatterCandleBubbleDataSet paramIBarLineScatterCandleBubbleDataSet)
  {
    if (paramEntry == null) {
      return false;
    }
    float f = paramIBarLineScatterCandleBubbleDataSet.getEntryIndex(paramEntry);
    if (paramEntry != null) {
      return f < paramIBarLineScatterCandleBubbleDataSet.getEntryCount() * mAnimator.getPhaseX();
    }
    return false;
  }
  
  protected boolean shouldDrawValues(IDataSet paramIDataSet)
  {
    return (paramIDataSet.isVisible()) && ((paramIDataSet.isDrawValuesEnabled()) || (paramIDataSet.isDrawIconsEnabled()));
  }
  
  protected class XBounds
  {
    public int max;
    public int min;
    public int range;
    
    protected XBounds() {}
    
    public void set(BarLineScatterCandleBubbleDataProvider paramBarLineScatterCandleBubbleDataProvider, IBarLineScatterCandleBubbleDataSet paramIBarLineScatterCandleBubbleDataSet)
    {
      float f1 = Math.max(0.0F, Math.min(1.0F, mAnimator.getPhaseX()));
      float f2 = paramBarLineScatterCandleBubbleDataProvider.getLowestVisibleX();
      float f3 = paramBarLineScatterCandleBubbleDataProvider.getHighestVisibleX();
      paramBarLineScatterCandleBubbleDataProvider = paramIBarLineScatterCandleBubbleDataSet.getEntryForXValue(f2, NaN.0F, DataSet.Rounding.DOWN);
      Entry localEntry = paramIBarLineScatterCandleBubbleDataSet.getEntryForXValue(f3, NaN.0F, DataSet.Rounding.UP);
      int j = 0;
      int i;
      if (paramBarLineScatterCandleBubbleDataProvider == null) {
        i = 0;
      } else {
        i = paramIBarLineScatterCandleBubbleDataSet.getEntryIndex(paramBarLineScatterCandleBubbleDataProvider);
      }
      min = i;
      if (localEntry == null) {
        i = j;
      } else {
        i = paramIBarLineScatterCandleBubbleDataSet.getEntryIndex(localEntry);
      }
      max = i;
      range = ((int)((max - min) * f1));
    }
  }
}

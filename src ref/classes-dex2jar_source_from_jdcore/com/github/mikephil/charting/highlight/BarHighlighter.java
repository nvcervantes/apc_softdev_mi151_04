package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;

public class BarHighlighter
  extends ChartHighlighter<BarDataProvider>
{
  public BarHighlighter(BarDataProvider paramBarDataProvider)
  {
    super(paramBarDataProvider);
  }
  
  protected int getClosestStackIndex(Range[] paramArrayOfRange, float paramFloat)
  {
    int k = 0;
    if (paramArrayOfRange != null)
    {
      if (paramArrayOfRange.length == 0) {
        return 0;
      }
      int m = paramArrayOfRange.length;
      int i = 0;
      int j = i;
      while (i < m)
      {
        if (paramArrayOfRange[i].contains(paramFloat)) {
          return j;
        }
        j += 1;
        i += 1;
      }
      j = Math.max(paramArrayOfRange.length - 1, 0);
      i = k;
      if (paramFloat > to) {
        i = j;
      }
      return i;
    }
    return 0;
  }
  
  protected BarLineScatterCandleBubbleData getData()
  {
    return ((BarDataProvider)mChart).getBarData();
  }
  
  protected float getDistance(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    return Math.abs(paramFloat1 - paramFloat3);
  }
  
  public Highlight getHighlight(float paramFloat1, float paramFloat2)
  {
    Highlight localHighlight = super.getHighlight(paramFloat1, paramFloat2);
    if (localHighlight == null) {
      return null;
    }
    MPPointD localMPPointD = getValsForTouch(paramFloat1, paramFloat2);
    IBarDataSet localIBarDataSet = (IBarDataSet)((BarDataProvider)mChart).getBarData().getDataSetByIndex(localHighlight.getDataSetIndex());
    if (localIBarDataSet.isStacked()) {
      return getStackedHighlight(localHighlight, localIBarDataSet, (float)x, (float)y);
    }
    MPPointD.recycleInstance(localMPPointD);
    return localHighlight;
  }
  
  public Highlight getStackedHighlight(Highlight paramHighlight, IBarDataSet paramIBarDataSet, float paramFloat1, float paramFloat2)
  {
    BarEntry localBarEntry = (BarEntry)paramIBarDataSet.getEntryForXValue(paramFloat1, paramFloat2);
    if (localBarEntry == null) {
      return null;
    }
    if (localBarEntry.getYVals() == null) {
      return paramHighlight;
    }
    Range[] arrayOfRange = localBarEntry.getRanges();
    if (arrayOfRange.length > 0)
    {
      int i = getClosestStackIndex(arrayOfRange, paramFloat2);
      paramIBarDataSet = ((BarDataProvider)mChart).getTransformer(paramIBarDataSet.getAxisDependency()).getPixelForValues(paramHighlight.getX(), to);
      paramHighlight = new Highlight(localBarEntry.getX(), localBarEntry.getY(), (float)x, (float)y, paramHighlight.getDataSetIndex(), i, paramHighlight.getAxis());
      MPPointD.recycleInstance(paramIBarDataSet);
      return paramHighlight;
    }
    return null;
  }
}

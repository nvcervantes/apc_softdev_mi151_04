package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.PieRadarChartBase;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import java.util.ArrayList;
import java.util.List;

public abstract class PieRadarHighlighter<T extends PieRadarChartBase>
  implements IHighlighter
{
  protected T mChart;
  protected List<Highlight> mHighlightBuffer = new ArrayList();
  
  public PieRadarHighlighter(T paramT)
  {
    mChart = paramT;
  }
  
  protected abstract Highlight getClosestHighlight(int paramInt, float paramFloat1, float paramFloat2);
  
  public Highlight getHighlight(float paramFloat1, float paramFloat2)
  {
    if (mChart.distanceToCenter(paramFloat1, paramFloat2) > mChart.getRadius()) {
      return null;
    }
    float f2 = mChart.getAngleForPoint(paramFloat1, paramFloat2);
    float f1 = f2;
    if ((mChart instanceof PieChart)) {
      f1 = f2 / mChart.getAnimator().getPhaseY();
    }
    int i = mChart.getIndexForAngle(f1);
    if (i >= 0)
    {
      if (i >= mChart.getData().getMaxEntryCountSet().getEntryCount()) {
        return null;
      }
      return getClosestHighlight(i, paramFloat1, paramFloat2);
    }
    return null;
  }
}

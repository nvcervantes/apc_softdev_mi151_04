package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;

public class RadarHighlighter
  extends PieRadarHighlighter<RadarChart>
{
  public RadarHighlighter(RadarChart paramRadarChart)
  {
    super(paramRadarChart);
  }
  
  protected Highlight getClosestHighlight(int paramInt, float paramFloat1, float paramFloat2)
  {
    List localList = getHighlightsAtIndex(paramInt);
    float f2 = ((RadarChart)mChart).distanceToCenter(paramFloat1, paramFloat2) / ((RadarChart)mChart).getFactor();
    Object localObject = null;
    paramFloat1 = Float.MAX_VALUE;
    paramInt = 0;
    while (paramInt < localList.size())
    {
      Highlight localHighlight = (Highlight)localList.get(paramInt);
      float f1 = Math.abs(localHighlight.getY() - f2);
      paramFloat2 = paramFloat1;
      if (f1 < paramFloat1)
      {
        localObject = localHighlight;
        paramFloat2 = f1;
      }
      paramInt += 1;
      paramFloat1 = paramFloat2;
    }
    return localObject;
  }
  
  protected List<Highlight> getHighlightsAtIndex(int paramInt)
  {
    mHighlightBuffer.clear();
    float f1 = ((RadarChart)mChart).getAnimator().getPhaseX();
    float f2 = ((RadarChart)mChart).getAnimator().getPhaseY();
    float f3 = ((RadarChart)mChart).getSliceAngle();
    float f4 = ((RadarChart)mChart).getFactor();
    MPPointF localMPPointF1 = MPPointF.getInstance(0.0F, 0.0F);
    int i = 0;
    for (;;)
    {
      int j = paramInt;
      if (i >= ((RadarData)((RadarChart)mChart).getData()).getDataSetCount()) {
        break;
      }
      IDataSet localIDataSet = ((RadarData)((RadarChart)mChart).getData()).getDataSetByIndex(i);
      Entry localEntry = localIDataSet.getEntryForIndex(j);
      float f5 = localEntry.getY();
      float f6 = ((RadarChart)mChart).getYChartMin();
      MPPointF localMPPointF2 = ((RadarChart)mChart).getCenterOffsets();
      float f7 = j;
      Utils.getPosition(localMPPointF2, (f5 - f6) * f4 * f2, f3 * f7 * f1 + ((RadarChart)mChart).getRotationAngle(), localMPPointF1);
      mHighlightBuffer.add(new Highlight(f7, localEntry.getY(), x, y, i, localIDataSet.getAxisDependency()));
      i += 1;
    }
    return mHighlightBuffer;
  }
}

package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.DataSet.Rounding;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChartHighlighter<T extends BarLineScatterCandleBubbleDataProvider>
  implements IHighlighter
{
  protected T mChart;
  protected List<Highlight> mHighlightBuffer = new ArrayList();
  
  public ChartHighlighter(T paramT)
  {
    mChart = paramT;
  }
  
  protected List<Highlight> buildHighlights(IDataSet paramIDataSet, int paramInt, float paramFloat, DataSet.Rounding paramRounding)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = paramIDataSet.getEntriesForXValue(paramFloat);
    Object localObject1 = localObject2;
    if (((List)localObject2).size() == 0)
    {
      paramRounding = paramIDataSet.getEntryForXValue(paramFloat, NaN.0F, paramRounding);
      localObject1 = localObject2;
      if (paramRounding != null) {
        localObject1 = paramIDataSet.getEntriesForXValue(paramRounding.getX());
      }
    }
    if (((List)localObject1).size() == 0) {
      return localArrayList;
    }
    paramRounding = ((List)localObject1).iterator();
    while (paramRounding.hasNext())
    {
      localObject1 = (Entry)paramRounding.next();
      localObject2 = mChart.getTransformer(paramIDataSet.getAxisDependency()).getPixelForValues(((Entry)localObject1).getX(), ((Entry)localObject1).getY());
      localArrayList.add(new Highlight(((Entry)localObject1).getX(), ((Entry)localObject1).getY(), (float)x, (float)y, paramInt, paramIDataSet.getAxisDependency()));
    }
    return localArrayList;
  }
  
  public Highlight getClosestHighlightByPixel(List<Highlight> paramList, float paramFloat1, float paramFloat2, YAxis.AxisDependency paramAxisDependency, float paramFloat3)
  {
    Object localObject1 = null;
    int i = 0;
    while (i < paramList.size())
    {
      Highlight localHighlight = (Highlight)paramList.get(i);
      Object localObject2;
      float f1;
      if (paramAxisDependency != null)
      {
        localObject2 = localObject1;
        f1 = paramFloat3;
        if (localHighlight.getAxis() != paramAxisDependency) {}
      }
      else
      {
        float f2 = getDistance(paramFloat1, paramFloat2, localHighlight.getXPx(), localHighlight.getYPx());
        localObject2 = localObject1;
        f1 = paramFloat3;
        if (f2 < paramFloat3)
        {
          localObject2 = localHighlight;
          f1 = f2;
        }
      }
      i += 1;
      localObject1 = localObject2;
      paramFloat3 = f1;
    }
    return localObject1;
  }
  
  protected BarLineScatterCandleBubbleData getData()
  {
    return mChart.getData();
  }
  
  protected float getDistance(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    return (float)Math.hypot(paramFloat1 - paramFloat3, paramFloat2 - paramFloat4);
  }
  
  public Highlight getHighlight(float paramFloat1, float paramFloat2)
  {
    MPPointD localMPPointD = getValsForTouch(paramFloat1, paramFloat2);
    float f = (float)x;
    MPPointD.recycleInstance(localMPPointD);
    return getHighlightForX(f, paramFloat1, paramFloat2);
  }
  
  protected Highlight getHighlightForX(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    List localList = getHighlightsAtXValue(paramFloat1, paramFloat2, paramFloat3);
    if (localList.isEmpty()) {
      return null;
    }
    if (getMinimumDistance(localList, paramFloat3, YAxis.AxisDependency.LEFT) < getMinimumDistance(localList, paramFloat3, YAxis.AxisDependency.RIGHT)) {}
    for (YAxis.AxisDependency localAxisDependency = YAxis.AxisDependency.LEFT;; localAxisDependency = YAxis.AxisDependency.RIGHT) {
      break;
    }
    return getClosestHighlightByPixel(localList, paramFloat2, paramFloat3, localAxisDependency, mChart.getMaxHighlightDistance());
  }
  
  protected float getHighlightPos(Highlight paramHighlight)
  {
    return paramHighlight.getYPx();
  }
  
  protected List<Highlight> getHighlightsAtXValue(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    mHighlightBuffer.clear();
    BarLineScatterCandleBubbleData localBarLineScatterCandleBubbleData = getData();
    if (localBarLineScatterCandleBubbleData == null) {
      return mHighlightBuffer;
    }
    int i = 0;
    int j = localBarLineScatterCandleBubbleData.getDataSetCount();
    while (i < j)
    {
      IDataSet localIDataSet = localBarLineScatterCandleBubbleData.getDataSetByIndex(i);
      if (localIDataSet.isHighlightEnabled()) {
        mHighlightBuffer.addAll(buildHighlights(localIDataSet, i, paramFloat1, DataSet.Rounding.CLOSEST));
      }
      i += 1;
    }
    return mHighlightBuffer;
  }
  
  protected float getMinimumDistance(List<Highlight> paramList, float paramFloat, YAxis.AxisDependency paramAxisDependency)
  {
    float f1 = Float.MAX_VALUE;
    int i = 0;
    while (i < paramList.size())
    {
      Highlight localHighlight = (Highlight)paramList.get(i);
      float f2 = f1;
      if (localHighlight.getAxis() == paramAxisDependency)
      {
        float f3 = Math.abs(getHighlightPos(localHighlight) - paramFloat);
        f2 = f1;
        if (f3 < f1) {
          f2 = f3;
        }
      }
      i += 1;
      f1 = f2;
    }
    return f1;
  }
  
  protected MPPointD getValsForTouch(float paramFloat1, float paramFloat2)
  {
    return mChart.getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(paramFloat1, paramFloat2);
  }
}

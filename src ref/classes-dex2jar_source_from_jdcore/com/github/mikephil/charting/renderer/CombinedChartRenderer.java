package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.CombinedChart.DrawOrder;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.BubbleDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.CandleDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.ScatterDataProvider;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CombinedChartRenderer
  extends DataRenderer
{
  protected WeakReference<Chart> mChart;
  protected List<Highlight> mHighlightBuffer = new ArrayList();
  protected List<DataRenderer> mRenderers = new ArrayList(5);
  
  public CombinedChartRenderer(CombinedChart paramCombinedChart, ChartAnimator paramChartAnimator, ViewPortHandler paramViewPortHandler)
  {
    super(paramChartAnimator, paramViewPortHandler);
    mChart = new WeakReference(paramCombinedChart);
    createRenderers();
  }
  
  public void createRenderers()
  {
    mRenderers.clear();
    CombinedChart localCombinedChart = (CombinedChart)mChart.get();
    if (localCombinedChart == null) {
      return;
    }
    CombinedChart.DrawOrder[] arrayOfDrawOrder = localCombinedChart.getDrawOrder();
    int j = arrayOfDrawOrder.length;
    int i = 0;
    while (i < j)
    {
      CombinedChart.DrawOrder localDrawOrder = arrayOfDrawOrder[i];
      switch (1.$SwitchMap$com$github$mikephil$charting$charts$CombinedChart$DrawOrder[localDrawOrder.ordinal()])
      {
      default: 
        break;
      case 5: 
        if (localCombinedChart.getScatterData() != null) {
          mRenderers.add(new ScatterChartRenderer(localCombinedChart, mAnimator, mViewPortHandler));
        }
        break;
      case 4: 
        if (localCombinedChart.getCandleData() != null) {
          mRenderers.add(new CandleStickChartRenderer(localCombinedChart, mAnimator, mViewPortHandler));
        }
        break;
      case 3: 
        if (localCombinedChart.getLineData() != null) {
          mRenderers.add(new LineChartRenderer(localCombinedChart, mAnimator, mViewPortHandler));
        }
        break;
      case 2: 
        if (localCombinedChart.getBubbleData() != null) {
          mRenderers.add(new BubbleChartRenderer(localCombinedChart, mAnimator, mViewPortHandler));
        }
        break;
      case 1: 
        if (localCombinedChart.getBarData() != null) {
          mRenderers.add(new BarChartRenderer(localCombinedChart, mAnimator, mViewPortHandler));
        }
        break;
      }
      i += 1;
    }
  }
  
  public void drawData(Canvas paramCanvas)
  {
    Iterator localIterator = mRenderers.iterator();
    while (localIterator.hasNext()) {
      ((DataRenderer)localIterator.next()).drawData(paramCanvas);
    }
  }
  
  public void drawExtras(Canvas paramCanvas)
  {
    Iterator localIterator = mRenderers.iterator();
    while (localIterator.hasNext()) {
      ((DataRenderer)localIterator.next()).drawExtras(paramCanvas);
    }
  }
  
  public void drawHighlighted(Canvas paramCanvas, Highlight[] paramArrayOfHighlight)
  {
    Chart localChart = (Chart)mChart.get();
    if (localChart == null) {
      return;
    }
    Iterator localIterator = mRenderers.iterator();
    while (localIterator.hasNext())
    {
      DataRenderer localDataRenderer = (DataRenderer)localIterator.next();
      Object localObject = null;
      if ((localDataRenderer instanceof BarChartRenderer)) {
        localObject = mChart.getBarData();
      } else if ((localDataRenderer instanceof LineChartRenderer)) {
        localObject = mChart.getLineData();
      } else if ((localDataRenderer instanceof CandleStickChartRenderer)) {
        localObject = mChart.getCandleData();
      } else if ((localDataRenderer instanceof ScatterChartRenderer)) {
        localObject = mChart.getScatterData();
      } else if ((localDataRenderer instanceof BubbleChartRenderer)) {
        localObject = mChart.getBubbleData();
      }
      int i;
      if (localObject == null) {
        i = -1;
      } else {
        i = ((CombinedData)localChart.getData()).getAllData().indexOf(localObject);
      }
      mHighlightBuffer.clear();
      int k = paramArrayOfHighlight.length;
      int j = 0;
      while (j < k)
      {
        localObject = paramArrayOfHighlight[j];
        if ((((Highlight)localObject).getDataIndex() == i) || (((Highlight)localObject).getDataIndex() == -1)) {
          mHighlightBuffer.add(localObject);
        }
        j += 1;
      }
      localDataRenderer.drawHighlighted(paramCanvas, (Highlight[])mHighlightBuffer.toArray(new Highlight[mHighlightBuffer.size()]));
    }
  }
  
  public void drawValues(Canvas paramCanvas)
  {
    Iterator localIterator = mRenderers.iterator();
    while (localIterator.hasNext()) {
      ((DataRenderer)localIterator.next()).drawValues(paramCanvas);
    }
  }
  
  public DataRenderer getSubRenderer(int paramInt)
  {
    if ((paramInt < mRenderers.size()) && (paramInt >= 0)) {
      return (DataRenderer)mRenderers.get(paramInt);
    }
    return null;
  }
  
  public List<DataRenderer> getSubRenderers()
  {
    return mRenderers;
  }
  
  public void initBuffers()
  {
    Iterator localIterator = mRenderers.iterator();
    while (localIterator.hasNext()) {
      ((DataRenderer)localIterator.next()).initBuffers();
    }
  }
  
  public void setSubRenderers(List<DataRenderer> paramList)
  {
    mRenderers = paramList;
  }
}

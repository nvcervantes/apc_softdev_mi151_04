package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.highlight.BarHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.IHighlighter;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.Transformer;

public class BarChart
  extends BarLineChartBase<BarData>
  implements BarDataProvider
{
  private boolean mDrawBarShadow = false;
  private boolean mDrawValueAboveBar = true;
  private boolean mFitBars = false;
  protected boolean mHighlightFullBarEnabled = false;
  
  public BarChart(Context paramContext)
  {
    super(paramContext);
  }
  
  public BarChart(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public BarChart(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void calcMinMax()
  {
    if (mFitBars) {
      mXAxis.calculate(((BarData)mData).getXMin() - ((BarData)mData).getBarWidth() / 2.0F, ((BarData)mData).getXMax() + ((BarData)mData).getBarWidth() / 2.0F);
    } else {
      mXAxis.calculate(((BarData)mData).getXMin(), ((BarData)mData).getXMax());
    }
    mAxisLeft.calculate(((BarData)mData).getYMin(YAxis.AxisDependency.LEFT), ((BarData)mData).getYMax(YAxis.AxisDependency.LEFT));
    mAxisRight.calculate(((BarData)mData).getYMin(YAxis.AxisDependency.RIGHT), ((BarData)mData).getYMax(YAxis.AxisDependency.RIGHT));
  }
  
  public RectF getBarBounds(BarEntry paramBarEntry)
  {
    RectF localRectF = new RectF();
    getBarBounds(paramBarEntry, localRectF);
    return localRectF;
  }
  
  public void getBarBounds(BarEntry paramBarEntry, RectF paramRectF)
  {
    IBarDataSet localIBarDataSet = (IBarDataSet)((BarData)mData).getDataSetForEntry(paramBarEntry);
    if (localIBarDataSet == null)
    {
      paramRectF.set(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE);
      return;
    }
    float f1 = paramBarEntry.getY();
    float f3 = paramBarEntry.getX();
    float f4 = ((BarData)mData).getBarWidth() / 2.0F;
    float f2;
    if (f1 >= 0.0F) {
      f2 = f1;
    } else {
      f2 = 0.0F;
    }
    if (f1 > 0.0F) {
      f1 = 0.0F;
    }
    paramRectF.set(f3 - f4, f2, f3 + f4, f1);
    getTransformer(localIBarDataSet.getAxisDependency()).rectValueToPixel(paramRectF);
  }
  
  public BarData getBarData()
  {
    return (BarData)mData;
  }
  
  public Highlight getHighlightByTouchPoint(float paramFloat1, float paramFloat2)
  {
    if (mData == null)
    {
      Log.e("MPAndroidChart", "Can't select by touch. No data set.");
      return null;
    }
    Highlight localHighlight = getHighlighter().getHighlight(paramFloat1, paramFloat2);
    if (localHighlight != null)
    {
      if (!isHighlightFullBarEnabled()) {
        return localHighlight;
      }
      return new Highlight(localHighlight.getX(), localHighlight.getY(), localHighlight.getXPx(), localHighlight.getYPx(), localHighlight.getDataSetIndex(), -1, localHighlight.getAxis());
    }
    return localHighlight;
  }
  
  public void groupBars(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (getBarData() == null) {
      throw new RuntimeException("You need to set data for the chart before grouping bars.");
    }
    getBarData().groupBars(paramFloat1, paramFloat2, paramFloat3);
    notifyDataSetChanged();
  }
  
  public void highlightValue(float paramFloat, int paramInt1, int paramInt2)
  {
    highlightValue(new Highlight(paramFloat, paramInt1, paramInt2), false);
  }
  
  protected void init()
  {
    super.init();
    mRenderer = new BarChartRenderer(this, mAnimator, mViewPortHandler);
    setHighlighter(new BarHighlighter(this));
    getXAxis().setSpaceMin(0.5F);
    getXAxis().setSpaceMax(0.5F);
  }
  
  public boolean isDrawBarShadowEnabled()
  {
    return mDrawBarShadow;
  }
  
  public boolean isDrawValueAboveBarEnabled()
  {
    return mDrawValueAboveBar;
  }
  
  public boolean isHighlightFullBarEnabled()
  {
    return mHighlightFullBarEnabled;
  }
  
  public void setDrawBarShadow(boolean paramBoolean)
  {
    mDrawBarShadow = paramBoolean;
  }
  
  public void setDrawValueAboveBar(boolean paramBoolean)
  {
    mDrawValueAboveBar = paramBoolean;
  }
  
  public void setFitBars(boolean paramBoolean)
  {
    mFitBars = paramBoolean;
  }
  
  public void setHighlightFullBarEnabled(boolean paramBoolean)
  {
    mHighlightFullBarEnabled = paramBoolean;
  }
}

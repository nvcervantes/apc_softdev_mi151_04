package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.highlight.RadarHighlighter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.renderer.LegendRenderer;
import com.github.mikephil.charting.renderer.RadarChartRenderer;
import com.github.mikephil.charting.renderer.XAxisRendererRadarChart;
import com.github.mikephil.charting.renderer.YAxisRendererRadarChart;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class RadarChart
  extends PieRadarChartBase<RadarData>
{
  private boolean mDrawWeb = true;
  private float mInnerWebLineWidth = 1.5F;
  private int mSkipWebLineCount = 0;
  private int mWebAlpha = 150;
  private int mWebColor = Color.rgb(122, 122, 122);
  private int mWebColorInner = Color.rgb(122, 122, 122);
  private float mWebLineWidth = 2.5F;
  protected XAxisRendererRadarChart mXAxisRenderer;
  private YAxis mYAxis;
  protected YAxisRendererRadarChart mYAxisRenderer;
  
  public RadarChart(Context paramContext)
  {
    super(paramContext);
  }
  
  public RadarChart(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public RadarChart(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void calcMinMax()
  {
    super.calcMinMax();
    mYAxis.calculate(((RadarData)mData).getYMin(YAxis.AxisDependency.LEFT), ((RadarData)mData).getYMax(YAxis.AxisDependency.LEFT));
    mXAxis.calculate(0.0F, ((IRadarDataSet)((RadarData)mData).getMaxEntryCountSet()).getEntryCount());
  }
  
  public float getFactor()
  {
    RectF localRectF = mViewPortHandler.getContentRect();
    return Math.min(localRectF.width() / 2.0F, localRectF.height() / 2.0F) / mYAxis.mAxisRange;
  }
  
  public int getIndexForAngle(float paramFloat)
  {
    paramFloat = Utils.getNormalizedAngle(paramFloat - getRotationAngle());
    float f = getSliceAngle();
    int k = ((IRadarDataSet)((RadarData)mData).getMaxEntryCountSet()).getEntryCount();
    int j;
    for (int i = 0; i < k; i = j)
    {
      j = i + 1;
      if (j * f - f / 2.0F > paramFloat) {
        return i;
      }
    }
    return 0;
  }
  
  public float getRadius()
  {
    RectF localRectF = mViewPortHandler.getContentRect();
    return Math.min(localRectF.width() / 2.0F, localRectF.height() / 2.0F);
  }
  
  protected float getRequiredBaseOffset()
  {
    if ((mXAxis.isEnabled()) && (mXAxis.isDrawLabelsEnabled())) {
      return mXAxis.mLabelRotatedWidth;
    }
    return Utils.convertDpToPixel(10.0F);
  }
  
  protected float getRequiredLegendOffset()
  {
    return mLegendRenderer.getLabelPaint().getTextSize() * 4.0F;
  }
  
  public int getSkipWebLineCount()
  {
    return mSkipWebLineCount;
  }
  
  public float getSliceAngle()
  {
    return 360.0F / ((IRadarDataSet)((RadarData)mData).getMaxEntryCountSet()).getEntryCount();
  }
  
  public int getWebAlpha()
  {
    return mWebAlpha;
  }
  
  public int getWebColor()
  {
    return mWebColor;
  }
  
  public int getWebColorInner()
  {
    return mWebColorInner;
  }
  
  public float getWebLineWidth()
  {
    return mWebLineWidth;
  }
  
  public float getWebLineWidthInner()
  {
    return mInnerWebLineWidth;
  }
  
  public YAxis getYAxis()
  {
    return mYAxis;
  }
  
  public float getYChartMax()
  {
    return mYAxis.mAxisMaximum;
  }
  
  public float getYChartMin()
  {
    return mYAxis.mAxisMinimum;
  }
  
  public float getYRange()
  {
    return mYAxis.mAxisRange;
  }
  
  protected void init()
  {
    super.init();
    mYAxis = new YAxis(YAxis.AxisDependency.LEFT);
    mWebLineWidth = Utils.convertDpToPixel(1.5F);
    mInnerWebLineWidth = Utils.convertDpToPixel(0.75F);
    mRenderer = new RadarChartRenderer(this, mAnimator, mViewPortHandler);
    mYAxisRenderer = new YAxisRendererRadarChart(mViewPortHandler, mYAxis, this);
    mXAxisRenderer = new XAxisRendererRadarChart(mViewPortHandler, mXAxis, this);
    mHighlighter = new RadarHighlighter(this);
  }
  
  public void notifyDataSetChanged()
  {
    if (mData == null) {
      return;
    }
    calcMinMax();
    mYAxisRenderer.computeAxis(mYAxis.mAxisMinimum, mYAxis.mAxisMaximum, mYAxis.isInverted());
    mXAxisRenderer.computeAxis(mXAxis.mAxisMinimum, mXAxis.mAxisMaximum, false);
    if ((mLegend != null) && (!mLegend.isLegendCustom())) {
      mLegendRenderer.computeLegend(mData);
    }
    calculateOffsets();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (mData == null) {
      return;
    }
    if (mXAxis.isEnabled()) {
      mXAxisRenderer.computeAxis(mXAxis.mAxisMinimum, mXAxis.mAxisMaximum, false);
    }
    mXAxisRenderer.renderAxisLabels(paramCanvas);
    if (mDrawWeb) {
      mRenderer.drawExtras(paramCanvas);
    }
    if ((mYAxis.isEnabled()) && (mYAxis.isDrawLimitLinesBehindDataEnabled())) {
      mYAxisRenderer.renderLimitLines(paramCanvas);
    }
    mRenderer.drawData(paramCanvas);
    if (valuesToHighlight()) {
      mRenderer.drawHighlighted(paramCanvas, mIndicesToHighlight);
    }
    if ((mYAxis.isEnabled()) && (!mYAxis.isDrawLimitLinesBehindDataEnabled())) {
      mYAxisRenderer.renderLimitLines(paramCanvas);
    }
    mYAxisRenderer.renderAxisLabels(paramCanvas);
    mRenderer.drawValues(paramCanvas);
    mLegendRenderer.renderLegend(paramCanvas);
    drawDescription(paramCanvas);
    drawMarkers(paramCanvas);
  }
  
  public void setDrawWeb(boolean paramBoolean)
  {
    mDrawWeb = paramBoolean;
  }
  
  public void setSkipWebLineCount(int paramInt)
  {
    mSkipWebLineCount = Math.max(0, paramInt);
  }
  
  public void setWebAlpha(int paramInt)
  {
    mWebAlpha = paramInt;
  }
  
  public void setWebColor(int paramInt)
  {
    mWebColor = paramInt;
  }
  
  public void setWebColorInner(int paramInt)
  {
    mWebColorInner = paramInt;
  }
  
  public void setWebLineWidth(float paramFloat)
  {
    mWebLineWidth = Utils.convertDpToPixel(paramFloat);
  }
  
  public void setWebLineWidthInner(float paramFloat)
  {
    mInnerWebLineWidth = Utils.convertDpToPixel(paramFloat);
  }
}

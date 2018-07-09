package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.HorizontalBarHighlighter;
import com.github.mikephil.charting.highlight.IHighlighter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.renderer.HorizontalBarChartRenderer;
import com.github.mikephil.charting.renderer.XAxisRendererHorizontalBarChart;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.renderer.YAxisRendererHorizontalBarChart;
import com.github.mikephil.charting.utils.HorizontalViewPortHandler;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.TransformerHorizontalBarChart;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class HorizontalBarChart
  extends BarChart
{
  protected float[] mGetPositionBuffer = new float[2];
  private RectF mOffsetsBuffer = new RectF();
  
  public HorizontalBarChart(Context paramContext)
  {
    super(paramContext);
  }
  
  public HorizontalBarChart(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public HorizontalBarChart(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public void calculateOffsets()
  {
    calculateLegendOffsets(mOffsetsBuffer);
    float f6 = mOffsetsBuffer.left + 0.0F;
    float f2 = mOffsetsBuffer.top + 0.0F;
    float f5 = mOffsetsBuffer.right + 0.0F;
    float f1 = 0.0F + mOffsetsBuffer.bottom;
    float f3 = f2;
    if (mAxisLeft.needsOffset()) {
      f3 = f2 + mAxisLeft.getRequiredHeightSpace(mAxisRendererLeft.getPaintAxisLabels());
    }
    float f4 = f1;
    if (mAxisRight.needsOffset()) {
      f4 = f1 + mAxisRight.getRequiredHeightSpace(mAxisRendererRight.getPaintAxisLabels());
    }
    float f7 = mXAxis.mLabelRotatedWidth;
    f1 = f6;
    f2 = f5;
    if (mXAxis.isEnabled()) {
      if (mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM)
      {
        f1 = f6 + f7;
        f2 = f5;
      }
      else if (mXAxis.getPosition() == XAxis.XAxisPosition.TOP)
      {
        f2 = f5 + f7;
        f1 = f6;
      }
      else
      {
        f1 = f6;
        f2 = f5;
        if (mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED)
        {
          f1 = f6 + f7;
          f2 = f5 + f7;
        }
      }
    }
    f3 += getExtraTopOffset();
    f2 += getExtraRightOffset();
    f4 += getExtraBottomOffset();
    f1 += getExtraLeftOffset();
    f5 = Utils.convertDpToPixel(mMinOffset);
    mViewPortHandler.restrainViewPort(Math.max(f5, f1), Math.max(f5, f3), Math.max(f5, f2), Math.max(f5, f4));
    if (mLogEnabled)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("offsetLeft: ");
      localStringBuilder.append(f1);
      localStringBuilder.append(", offsetTop: ");
      localStringBuilder.append(f3);
      localStringBuilder.append(", offsetRight: ");
      localStringBuilder.append(f2);
      localStringBuilder.append(", offsetBottom: ");
      localStringBuilder.append(f4);
      Log.i("MPAndroidChart", localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Content: ");
      localStringBuilder.append(mViewPortHandler.getContentRect().toString());
      Log.i("MPAndroidChart", localStringBuilder.toString());
    }
    prepareOffsetMatrix();
    prepareValuePxMatrix();
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
    paramRectF.set(f2, f3 - f4, f1, f3 + f4);
    getTransformer(localIBarDataSet.getAxisDependency()).rectValueToPixel(paramRectF);
  }
  
  public float getHighestVisibleX()
  {
    getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(mViewPortHandler.contentLeft(), mViewPortHandler.contentTop(), posForGetHighestVisibleX);
    return (float)Math.min(mXAxis.mAxisMaximum, posForGetHighestVisibleX.y);
  }
  
  public Highlight getHighlightByTouchPoint(float paramFloat1, float paramFloat2)
  {
    if (mData == null)
    {
      if (mLogEnabled) {
        Log.e("MPAndroidChart", "Can't select by touch. No data set.");
      }
      return null;
    }
    return getHighlighter().getHighlight(paramFloat2, paramFloat1);
  }
  
  public float getLowestVisibleX()
  {
    getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(mViewPortHandler.contentLeft(), mViewPortHandler.contentBottom(), posForGetLowestVisibleX);
    return (float)Math.max(mXAxis.mAxisMinimum, posForGetLowestVisibleX.y);
  }
  
  protected float[] getMarkerPosition(Highlight paramHighlight)
  {
    return new float[] { paramHighlight.getDrawY(), paramHighlight.getDrawX() };
  }
  
  public MPPointF getPosition(Entry paramEntry, YAxis.AxisDependency paramAxisDependency)
  {
    if (paramEntry == null) {
      return null;
    }
    float[] arrayOfFloat = mGetPositionBuffer;
    arrayOfFloat[0] = paramEntry.getY();
    arrayOfFloat[1] = paramEntry.getX();
    getTransformer(paramAxisDependency).pointValuesToPixel(arrayOfFloat);
    return MPPointF.getInstance(arrayOfFloat[0], arrayOfFloat[1]);
  }
  
  protected void init()
  {
    mViewPortHandler = new HorizontalViewPortHandler();
    super.init();
    mLeftAxisTransformer = new TransformerHorizontalBarChart(mViewPortHandler);
    mRightAxisTransformer = new TransformerHorizontalBarChart(mViewPortHandler);
    mRenderer = new HorizontalBarChartRenderer(this, mAnimator, mViewPortHandler);
    setHighlighter(new HorizontalBarHighlighter(this));
    mAxisRendererLeft = new YAxisRendererHorizontalBarChart(mViewPortHandler, mAxisLeft, mLeftAxisTransformer);
    mAxisRendererRight = new YAxisRendererHorizontalBarChart(mViewPortHandler, mAxisRight, mRightAxisTransformer);
    mXAxisRenderer = new XAxisRendererHorizontalBarChart(mViewPortHandler, mXAxis, mLeftAxisTransformer, this);
  }
  
  protected void prepareValuePxMatrix()
  {
    mRightAxisTransformer.prepareMatrixValuePx(mAxisRight.mAxisMinimum, mAxisRight.mAxisRange, mXAxis.mAxisRange, mXAxis.mAxisMinimum);
    mLeftAxisTransformer.prepareMatrixValuePx(mAxisLeft.mAxisMinimum, mAxisLeft.mAxisRange, mXAxis.mAxisRange, mXAxis.mAxisMinimum);
  }
  
  public void setVisibleXRange(float paramFloat1, float paramFloat2)
  {
    paramFloat1 = mXAxis.mAxisRange / paramFloat1;
    paramFloat2 = mXAxis.mAxisRange / paramFloat2;
    mViewPortHandler.setMinMaxScaleY(paramFloat1, paramFloat2);
  }
  
  public void setVisibleXRangeMaximum(float paramFloat)
  {
    paramFloat = mXAxis.mAxisRange / paramFloat;
    mViewPortHandler.setMinimumScaleY(paramFloat);
  }
  
  public void setVisibleXRangeMinimum(float paramFloat)
  {
    paramFloat = mXAxis.mAxisRange / paramFloat;
    mViewPortHandler.setMaximumScaleY(paramFloat);
  }
  
  public void setVisibleYRange(float paramFloat1, float paramFloat2, YAxis.AxisDependency paramAxisDependency)
  {
    paramFloat1 = getAxisRange(paramAxisDependency) / paramFloat1;
    paramFloat2 = getAxisRange(paramAxisDependency) / paramFloat2;
    mViewPortHandler.setMinMaxScaleX(paramFloat1, paramFloat2);
  }
  
  public void setVisibleYRangeMaximum(float paramFloat, YAxis.AxisDependency paramAxisDependency)
  {
    paramFloat = getAxisRange(paramAxisDependency) / paramFloat;
    mViewPortHandler.setMinimumScaleX(paramFloat);
  }
  
  public void setVisibleYRangeMinimum(float paramFloat, YAxis.AxisDependency paramAxisDependency)
  {
    paramFloat = getAxisRange(paramAxisDependency) / paramFloat;
    mViewPortHandler.setMaximumScaleX(paramFloat);
  }
}

package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.PieHighlighter;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.renderer.LegendRenderer;
import com.github.mikephil.charting.renderer.PieChartRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;

public class PieChart
  extends PieRadarChartBase<PieData>
{
  private float[] mAbsoluteAngles = new float[1];
  private CharSequence mCenterText = "";
  private MPPointF mCenterTextOffset = MPPointF.getInstance(0.0F, 0.0F);
  private float mCenterTextRadiusPercent = 100.0F;
  private RectF mCircleBox = new RectF();
  private float[] mDrawAngles = new float[1];
  private boolean mDrawCenterText = true;
  private boolean mDrawEntryLabels = true;
  private boolean mDrawHole = true;
  private boolean mDrawRoundedSlices = false;
  private boolean mDrawSlicesUnderHole = false;
  private float mHoleRadiusPercent = 50.0F;
  protected float mMaxAngle = 360.0F;
  protected float mTransparentCircleRadiusPercent = 55.0F;
  private boolean mUsePercentValues = false;
  
  public PieChart(Context paramContext)
  {
    super(paramContext);
  }
  
  public PieChart(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public PieChart(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private float calcAngle(float paramFloat)
  {
    return calcAngle(paramFloat, ((PieData)mData).getYValueSum());
  }
  
  private float calcAngle(float paramFloat1, float paramFloat2)
  {
    return paramFloat1 / paramFloat2 * mMaxAngle;
  }
  
  private void calcAngles()
  {
    int j = ((PieData)mData).getEntryCount();
    if (mDrawAngles.length != j)
    {
      mDrawAngles = new float[j];
    }
    else
    {
      i = 0;
      while (i < j)
      {
        mDrawAngles[i] = 0.0F;
        i += 1;
      }
    }
    if (mAbsoluteAngles.length != j)
    {
      mAbsoluteAngles = new float[j];
    }
    else
    {
      i = 0;
      while (i < j)
      {
        mAbsoluteAngles[i] = 0.0F;
        i += 1;
      }
    }
    float f = ((PieData)mData).getYValueSum();
    List localList = ((PieData)mData).getDataSets();
    j = 0;
    int i = j;
    while (j < ((PieData)mData).getDataSetCount())
    {
      IPieDataSet localIPieDataSet = (IPieDataSet)localList.get(j);
      int k = 0;
      while (k < localIPieDataSet.getEntryCount())
      {
        mDrawAngles[i] = calcAngle(Math.abs(((PieEntry)localIPieDataSet.getEntryForIndex(k)).getY()), f);
        if (i == 0) {
          mAbsoluteAngles[i] = mDrawAngles[i];
        } else {
          mAbsoluteAngles[i] = (mAbsoluteAngles[(i - 1)] + mDrawAngles[i]);
        }
        i += 1;
        k += 1;
      }
      j += 1;
    }
  }
  
  protected void calcMinMax()
  {
    calcAngles();
  }
  
  public void calculateOffsets()
  {
    super.calculateOffsets();
    if (mData == null) {
      return;
    }
    float f1 = getDiameter() / 2.0F;
    MPPointF localMPPointF = getCenterOffsets();
    float f2 = ((PieData)mData).getDataSet().getSelectionShift();
    mCircleBox.set(x - f1 + f2, y - f1 + f2, x + f1 - f2, y + f1 - f2);
    MPPointF.recycleInstance(localMPPointF);
  }
  
  public float[] getAbsoluteAngles()
  {
    return mAbsoluteAngles;
  }
  
  public MPPointF getCenterCircleBox()
  {
    return MPPointF.getInstance(mCircleBox.centerX(), mCircleBox.centerY());
  }
  
  public CharSequence getCenterText()
  {
    return mCenterText;
  }
  
  public MPPointF getCenterTextOffset()
  {
    return MPPointF.getInstance(mCenterTextOffset.x, mCenterTextOffset.y);
  }
  
  public float getCenterTextRadiusPercent()
  {
    return mCenterTextRadiusPercent;
  }
  
  public RectF getCircleBox()
  {
    return mCircleBox;
  }
  
  public int getDataSetIndexForIndex(int paramInt)
  {
    List localList = ((PieData)mData).getDataSets();
    int i = 0;
    while (i < localList.size())
    {
      if (((IPieDataSet)localList.get(i)).getEntryForXValue(paramInt, NaN.0F) != null) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public float[] getDrawAngles()
  {
    return mDrawAngles;
  }
  
  public float getHoleRadius()
  {
    return mHoleRadiusPercent;
  }
  
  public int getIndexForAngle(float paramFloat)
  {
    paramFloat = Utils.getNormalizedAngle(paramFloat - getRotationAngle());
    int i = 0;
    while (i < mAbsoluteAngles.length)
    {
      if (mAbsoluteAngles[i] > paramFloat) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  protected float[] getMarkerPosition(Highlight paramHighlight)
  {
    MPPointF localMPPointF = getCenterCircleBox();
    float f2 = getRadius();
    float f1 = f2 / 10.0F * 3.6F;
    if (isDrawHoleEnabled()) {
      f1 = (f2 - f2 / 100.0F * getHoleRadius()) / 2.0F;
    }
    float f3 = getRotationAngle();
    int i = (int)paramHighlight.getX();
    float f4 = mDrawAngles[i] / 2.0F;
    double d = f2 - f1;
    f1 = (float)(Math.cos(Math.toRadians((mAbsoluteAngles[i] + f3 - f4) * mAnimator.getPhaseY())) * d + x);
    f2 = (float)(d * Math.sin(Math.toRadians((f3 + mAbsoluteAngles[i] - f4) * mAnimator.getPhaseY())) + y);
    MPPointF.recycleInstance(localMPPointF);
    return new float[] { f1, f2 };
  }
  
  public float getMaxAngle()
  {
    return mMaxAngle;
  }
  
  public float getRadius()
  {
    if (mCircleBox == null) {
      return 0.0F;
    }
    return Math.min(mCircleBox.width() / 2.0F, mCircleBox.height() / 2.0F);
  }
  
  protected float getRequiredBaseOffset()
  {
    return 0.0F;
  }
  
  protected float getRequiredLegendOffset()
  {
    return mLegendRenderer.getLabelPaint().getTextSize() * 2.0F;
  }
  
  public float getTransparentCircleRadius()
  {
    return mTransparentCircleRadiusPercent;
  }
  
  @Deprecated
  public XAxis getXAxis()
  {
    throw new RuntimeException("PieChart has no XAxis");
  }
  
  protected void init()
  {
    super.init();
    mRenderer = new PieChartRenderer(this, mAnimator, mViewPortHandler);
    mXAxis = null;
    mHighlighter = new PieHighlighter(this);
  }
  
  public boolean isDrawCenterTextEnabled()
  {
    return mDrawCenterText;
  }
  
  public boolean isDrawEntryLabelsEnabled()
  {
    return mDrawEntryLabels;
  }
  
  public boolean isDrawHoleEnabled()
  {
    return mDrawHole;
  }
  
  public boolean isDrawRoundedSlicesEnabled()
  {
    return mDrawRoundedSlices;
  }
  
  public boolean isDrawSlicesUnderHoleEnabled()
  {
    return mDrawSlicesUnderHole;
  }
  
  public boolean isUsePercentValuesEnabled()
  {
    return mUsePercentValues;
  }
  
  public boolean needsHighlight(int paramInt)
  {
    if (!valuesToHighlight()) {
      return false;
    }
    int i = 0;
    while (i < mIndicesToHighlight.length)
    {
      if ((int)mIndicesToHighlight[i].getX() == paramInt) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  protected void onDetachedFromWindow()
  {
    if ((mRenderer != null) && ((mRenderer instanceof PieChartRenderer))) {
      ((PieChartRenderer)mRenderer).releaseBitmap();
    }
    super.onDetachedFromWindow();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (mData == null) {
      return;
    }
    mRenderer.drawData(paramCanvas);
    if (valuesToHighlight()) {
      mRenderer.drawHighlighted(paramCanvas, mIndicesToHighlight);
    }
    mRenderer.drawExtras(paramCanvas);
    mRenderer.drawValues(paramCanvas);
    mLegendRenderer.renderLegend(paramCanvas);
    drawDescription(paramCanvas);
    drawMarkers(paramCanvas);
  }
  
  public void setCenterText(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null)
    {
      mCenterText = "";
      return;
    }
    mCenterText = paramCharSequence;
  }
  
  public void setCenterTextColor(int paramInt)
  {
    ((PieChartRenderer)mRenderer).getPaintCenterText().setColor(paramInt);
  }
  
  public void setCenterTextOffset(float paramFloat1, float paramFloat2)
  {
    mCenterTextOffset.x = Utils.convertDpToPixel(paramFloat1);
    mCenterTextOffset.y = Utils.convertDpToPixel(paramFloat2);
  }
  
  public void setCenterTextRadiusPercent(float paramFloat)
  {
    mCenterTextRadiusPercent = paramFloat;
  }
  
  public void setCenterTextSize(float paramFloat)
  {
    ((PieChartRenderer)mRenderer).getPaintCenterText().setTextSize(Utils.convertDpToPixel(paramFloat));
  }
  
  public void setCenterTextSizePixels(float paramFloat)
  {
    ((PieChartRenderer)mRenderer).getPaintCenterText().setTextSize(paramFloat);
  }
  
  public void setCenterTextTypeface(Typeface paramTypeface)
  {
    ((PieChartRenderer)mRenderer).getPaintCenterText().setTypeface(paramTypeface);
  }
  
  public void setDrawCenterText(boolean paramBoolean)
  {
    mDrawCenterText = paramBoolean;
  }
  
  public void setDrawEntryLabels(boolean paramBoolean)
  {
    mDrawEntryLabels = paramBoolean;
  }
  
  public void setDrawHoleEnabled(boolean paramBoolean)
  {
    mDrawHole = paramBoolean;
  }
  
  @Deprecated
  public void setDrawSliceText(boolean paramBoolean)
  {
    mDrawEntryLabels = paramBoolean;
  }
  
  public void setDrawSlicesUnderHole(boolean paramBoolean)
  {
    mDrawSlicesUnderHole = paramBoolean;
  }
  
  public void setEntryLabelColor(int paramInt)
  {
    ((PieChartRenderer)mRenderer).getPaintEntryLabels().setColor(paramInt);
  }
  
  public void setEntryLabelTextSize(float paramFloat)
  {
    ((PieChartRenderer)mRenderer).getPaintEntryLabels().setTextSize(Utils.convertDpToPixel(paramFloat));
  }
  
  public void setEntryLabelTypeface(Typeface paramTypeface)
  {
    ((PieChartRenderer)mRenderer).getPaintEntryLabels().setTypeface(paramTypeface);
  }
  
  public void setHoleColor(int paramInt)
  {
    ((PieChartRenderer)mRenderer).getPaintHole().setColor(paramInt);
  }
  
  public void setHoleRadius(float paramFloat)
  {
    mHoleRadiusPercent = paramFloat;
  }
  
  public void setMaxAngle(float paramFloat)
  {
    float f = paramFloat;
    if (paramFloat > 360.0F) {
      f = 360.0F;
    }
    paramFloat = f;
    if (f < 90.0F) {
      paramFloat = 90.0F;
    }
    mMaxAngle = paramFloat;
  }
  
  public void setTransparentCircleAlpha(int paramInt)
  {
    ((PieChartRenderer)mRenderer).getPaintTransparentCircle().setAlpha(paramInt);
  }
  
  public void setTransparentCircleColor(int paramInt)
  {
    Paint localPaint = ((PieChartRenderer)mRenderer).getPaintTransparentCircle();
    int i = localPaint.getAlpha();
    localPaint.setColor(paramInt);
    localPaint.setAlpha(i);
  }
  
  public void setTransparentCircleRadius(float paramFloat)
  {
    mTransparentCircleRadiusPercent = paramFloat;
  }
  
  public void setUsePercentValues(boolean paramBoolean)
  {
    mUsePercentValues = paramBoolean;
  }
}

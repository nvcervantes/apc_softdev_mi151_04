package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class XAxisRenderer
  extends AxisRenderer
{
  protected RectF mGridClippingRect = new RectF();
  protected RectF mLimitLineClippingRect = new RectF();
  private Path mLimitLinePath = new Path();
  float[] mLimitLineSegmentsBuffer = new float[4];
  protected float[] mRenderGridLinesBuffer = new float[2];
  protected Path mRenderGridLinesPath = new Path();
  protected float[] mRenderLimitLinesBuffer = new float[2];
  protected XAxis mXAxis;
  
  public XAxisRenderer(ViewPortHandler paramViewPortHandler, XAxis paramXAxis, Transformer paramTransformer)
  {
    super(paramViewPortHandler, paramTransformer, paramXAxis);
    mXAxis = paramXAxis;
    mAxisLabelPaint.setColor(-16777216);
    mAxisLabelPaint.setTextAlign(Paint.Align.CENTER);
    mAxisLabelPaint.setTextSize(Utils.convertDpToPixel(10.0F));
  }
  
  public void computeAxis(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    float f2 = paramFloat1;
    float f1 = paramFloat2;
    if (mViewPortHandler.contentWidth() > 10.0F)
    {
      f2 = paramFloat1;
      f1 = paramFloat2;
      if (!mViewPortHandler.isFullyZoomedOutX())
      {
        MPPointD localMPPointD1 = mTrans.getValuesByTouchPoint(mViewPortHandler.contentLeft(), mViewPortHandler.contentTop());
        MPPointD localMPPointD2 = mTrans.getValuesByTouchPoint(mViewPortHandler.contentRight(), mViewPortHandler.contentTop());
        if (paramBoolean)
        {
          paramFloat2 = (float)x;
          paramFloat1 = (float)x;
        }
        else
        {
          paramFloat2 = (float)x;
          paramFloat1 = (float)x;
        }
        MPPointD.recycleInstance(localMPPointD1);
        MPPointD.recycleInstance(localMPPointD2);
        f1 = paramFloat1;
        f2 = paramFloat2;
      }
    }
    computeAxisValues(f2, f1);
  }
  
  protected void computeAxisValues(float paramFloat1, float paramFloat2)
  {
    super.computeAxisValues(paramFloat1, paramFloat2);
    computeSize();
  }
  
  protected void computeSize()
  {
    Object localObject = mXAxis.getLongestLabel();
    mAxisLabelPaint.setTypeface(mXAxis.getTypeface());
    mAxisLabelPaint.setTextSize(mXAxis.getTextSize());
    localObject = Utils.calcTextSize(mAxisLabelPaint, (String)localObject);
    float f1 = width;
    float f2 = Utils.calcTextHeight(mAxisLabelPaint, "Q");
    FSize localFSize = Utils.getSizeOfRotatedRectangleByDegrees(f1, f2, mXAxis.getLabelRotationAngle());
    mXAxis.mLabelWidth = Math.round(f1);
    mXAxis.mLabelHeight = Math.round(f2);
    mXAxis.mLabelRotatedWidth = Math.round(width);
    mXAxis.mLabelRotatedHeight = Math.round(height);
    FSize.recycleInstance(localFSize);
    FSize.recycleInstance((FSize)localObject);
  }
  
  protected void drawGridLine(Canvas paramCanvas, float paramFloat1, float paramFloat2, Path paramPath)
  {
    paramPath.moveTo(paramFloat1, mViewPortHandler.contentBottom());
    paramPath.lineTo(paramFloat1, mViewPortHandler.contentTop());
    paramCanvas.drawPath(paramPath, mGridPaint);
    paramPath.reset();
  }
  
  protected void drawLabel(Canvas paramCanvas, String paramString, float paramFloat1, float paramFloat2, MPPointF paramMPPointF, float paramFloat3)
  {
    Utils.drawXAxisValue(paramCanvas, paramString, paramFloat1, paramFloat2, mAxisLabelPaint, paramMPPointF, paramFloat3);
  }
  
  protected void drawLabels(Canvas paramCanvas, float paramFloat, MPPointF paramMPPointF)
  {
    float f3 = mXAxis.getLabelRotationAngle();
    boolean bool = mXAxis.isCenterAxisLabelsEnabled();
    float[] arrayOfFloat = new float[mXAxis.mEntryCount * 2];
    int i = 0;
    while (i < arrayOfFloat.length)
    {
      if (bool) {
        arrayOfFloat[i] = mXAxis.mCenteredEntries[(i / 2)];
      } else {
        arrayOfFloat[i] = mXAxis.mEntries[(i / 2)];
      }
      i += 2;
    }
    mTrans.pointValuesToPixel(arrayOfFloat);
    i = 0;
    while (i < arrayOfFloat.length)
    {
      float f2 = arrayOfFloat[i];
      if (mViewPortHandler.isInBoundsX(f2))
      {
        String str = mXAxis.getValueFormatter().getFormattedValue(mXAxis.mEntries[(i / 2)], mXAxis);
        float f1 = f2;
        if (mXAxis.isAvoidFirstLastClippingEnabled()) {
          if ((i == mXAxis.mEntryCount - 1) && (mXAxis.mEntryCount > 1))
          {
            float f4 = Utils.calcTextWidth(mAxisLabelPaint, str);
            f1 = f2;
            if (f4 > mViewPortHandler.offsetRight() * 2.0F)
            {
              f1 = f2;
              if (f2 + f4 > mViewPortHandler.getChartWidth()) {
                f1 = f2 - f4 / 2.0F;
              }
            }
          }
          else
          {
            f1 = f2;
            if (i == 0) {
              f1 = f2 + Utils.calcTextWidth(mAxisLabelPaint, str) / 2.0F;
            }
          }
        }
        drawLabel(paramCanvas, str, f1, paramFloat, paramMPPointF, f3);
      }
      i += 2;
    }
  }
  
  public RectF getGridClippingRect()
  {
    mGridClippingRect.set(mViewPortHandler.getContentRect());
    mGridClippingRect.inset(-mAxis.getGridLineWidth(), 0.0F);
    return mGridClippingRect;
  }
  
  public void renderAxisLabels(Canvas paramCanvas)
  {
    if (mXAxis.isEnabled())
    {
      if (!mXAxis.isDrawLabelsEnabled()) {
        return;
      }
      float f = mXAxis.getYOffset();
      mAxisLabelPaint.setTypeface(mXAxis.getTypeface());
      mAxisLabelPaint.setTextSize(mXAxis.getTextSize());
      mAxisLabelPaint.setColor(mXAxis.getTextColor());
      MPPointF localMPPointF = MPPointF.getInstance(0.0F, 0.0F);
      if (mXAxis.getPosition() == XAxis.XAxisPosition.TOP)
      {
        x = 0.5F;
        y = 1.0F;
        drawLabels(paramCanvas, mViewPortHandler.contentTop() - f, localMPPointF);
      }
      else if (mXAxis.getPosition() == XAxis.XAxisPosition.TOP_INSIDE)
      {
        x = 0.5F;
        y = 1.0F;
        drawLabels(paramCanvas, mViewPortHandler.contentTop() + f + mXAxis.mLabelRotatedHeight, localMPPointF);
      }
      else if (mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM)
      {
        x = 0.5F;
        y = 0.0F;
        drawLabels(paramCanvas, mViewPortHandler.contentBottom() + f, localMPPointF);
      }
      else if (mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM_INSIDE)
      {
        x = 0.5F;
        y = 0.0F;
        drawLabels(paramCanvas, mViewPortHandler.contentBottom() - f - mXAxis.mLabelRotatedHeight, localMPPointF);
      }
      else
      {
        x = 0.5F;
        y = 1.0F;
        drawLabels(paramCanvas, mViewPortHandler.contentTop() - f, localMPPointF);
        x = 0.5F;
        y = 0.0F;
        drawLabels(paramCanvas, mViewPortHandler.contentBottom() + f, localMPPointF);
      }
      MPPointF.recycleInstance(localMPPointF);
      return;
    }
  }
  
  public void renderAxisLine(Canvas paramCanvas)
  {
    if (mXAxis.isDrawAxisLineEnabled())
    {
      if (!mXAxis.isEnabled()) {
        return;
      }
      mAxisLinePaint.setColor(mXAxis.getAxisLineColor());
      mAxisLinePaint.setStrokeWidth(mXAxis.getAxisLineWidth());
      mAxisLinePaint.setPathEffect(mXAxis.getAxisLineDashPathEffect());
      if ((mXAxis.getPosition() == XAxis.XAxisPosition.TOP) || (mXAxis.getPosition() == XAxis.XAxisPosition.TOP_INSIDE) || (mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED)) {
        paramCanvas.drawLine(mViewPortHandler.contentLeft(), mViewPortHandler.contentTop(), mViewPortHandler.contentRight(), mViewPortHandler.contentTop(), mAxisLinePaint);
      }
      if ((mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM) || (mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM_INSIDE) || (mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED)) {
        paramCanvas.drawLine(mViewPortHandler.contentLeft(), mViewPortHandler.contentBottom(), mViewPortHandler.contentRight(), mViewPortHandler.contentBottom(), mAxisLinePaint);
      }
      return;
    }
  }
  
  public void renderGridLines(Canvas paramCanvas)
  {
    if (mXAxis.isDrawGridLinesEnabled())
    {
      if (!mXAxis.isEnabled()) {
        return;
      }
      int k = paramCanvas.save();
      paramCanvas.clipRect(getGridClippingRect());
      if (mRenderGridLinesBuffer.length != mAxis.mEntryCount * 2) {
        mRenderGridLinesBuffer = new float[mXAxis.mEntryCount * 2];
      }
      float[] arrayOfFloat = mRenderGridLinesBuffer;
      int j = 0;
      int i = 0;
      while (i < arrayOfFloat.length)
      {
        localObject = mXAxis.mEntries;
        int m = i / 2;
        arrayOfFloat[i] = localObject[m];
        arrayOfFloat[(i + 1)] = mXAxis.mEntries[m];
        i += 2;
      }
      mTrans.pointValuesToPixel(arrayOfFloat);
      setupGridPaint();
      Object localObject = mRenderGridLinesPath;
      ((Path)localObject).reset();
      i = j;
      while (i < arrayOfFloat.length)
      {
        drawGridLine(paramCanvas, arrayOfFloat[i], arrayOfFloat[(i + 1)], (Path)localObject);
        i += 2;
      }
      paramCanvas.restoreToCount(k);
      return;
    }
  }
  
  public void renderLimitLineLabel(Canvas paramCanvas, LimitLine paramLimitLine, float[] paramArrayOfFloat, float paramFloat)
  {
    String str = paramLimitLine.getLabel();
    if ((str != null) && (!str.equals("")))
    {
      mLimitLinePaint.setStyle(paramLimitLine.getTextStyle());
      mLimitLinePaint.setPathEffect(null);
      mLimitLinePaint.setColor(paramLimitLine.getTextColor());
      mLimitLinePaint.setStrokeWidth(0.5F);
      mLimitLinePaint.setTextSize(paramLimitLine.getTextSize());
      float f1 = paramLimitLine.getLineWidth() + paramLimitLine.getXOffset();
      paramLimitLine = paramLimitLine.getLabelPosition();
      float f2;
      if (paramLimitLine == LimitLine.LimitLabelPosition.RIGHT_TOP)
      {
        f2 = Utils.calcTextHeight(mLimitLinePaint, str);
        mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
        paramCanvas.drawText(str, paramArrayOfFloat[0] + f1, mViewPortHandler.contentTop() + paramFloat + f2, mLimitLinePaint);
        return;
      }
      if (paramLimitLine == LimitLine.LimitLabelPosition.RIGHT_BOTTOM)
      {
        mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
        paramCanvas.drawText(str, paramArrayOfFloat[0] + f1, mViewPortHandler.contentBottom() - paramFloat, mLimitLinePaint);
        return;
      }
      if (paramLimitLine == LimitLine.LimitLabelPosition.LEFT_TOP)
      {
        mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
        f2 = Utils.calcTextHeight(mLimitLinePaint, str);
        paramCanvas.drawText(str, paramArrayOfFloat[0] - f1, mViewPortHandler.contentTop() + paramFloat + f2, mLimitLinePaint);
        return;
      }
      mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
      paramCanvas.drawText(str, paramArrayOfFloat[0] - f1, mViewPortHandler.contentBottom() - paramFloat, mLimitLinePaint);
    }
  }
  
  public void renderLimitLineLine(Canvas paramCanvas, LimitLine paramLimitLine, float[] paramArrayOfFloat)
  {
    mLimitLineSegmentsBuffer[0] = paramArrayOfFloat[0];
    mLimitLineSegmentsBuffer[1] = mViewPortHandler.contentTop();
    mLimitLineSegmentsBuffer[2] = paramArrayOfFloat[0];
    mLimitLineSegmentsBuffer[3] = mViewPortHandler.contentBottom();
    mLimitLinePath.reset();
    mLimitLinePath.moveTo(mLimitLineSegmentsBuffer[0], mLimitLineSegmentsBuffer[1]);
    mLimitLinePath.lineTo(mLimitLineSegmentsBuffer[2], mLimitLineSegmentsBuffer[3]);
    mLimitLinePaint.setStyle(Paint.Style.STROKE);
    mLimitLinePaint.setColor(paramLimitLine.getLineColor());
    mLimitLinePaint.setStrokeWidth(paramLimitLine.getLineWidth());
    mLimitLinePaint.setPathEffect(paramLimitLine.getDashPathEffect());
    paramCanvas.drawPath(mLimitLinePath, mLimitLinePaint);
  }
  
  public void renderLimitLines(Canvas paramCanvas)
  {
    List localList = mXAxis.getLimitLines();
    if (localList != null)
    {
      if (localList.size() <= 0) {
        return;
      }
      float[] arrayOfFloat = mRenderLimitLinesBuffer;
      arrayOfFloat[0] = 0.0F;
      arrayOfFloat[1] = 0.0F;
      int i = 0;
      while (i < localList.size())
      {
        LimitLine localLimitLine = (LimitLine)localList.get(i);
        if (localLimitLine.isEnabled())
        {
          int j = paramCanvas.save();
          mLimitLineClippingRect.set(mViewPortHandler.getContentRect());
          mLimitLineClippingRect.inset(-localLimitLine.getLineWidth(), 0.0F);
          paramCanvas.clipRect(mLimitLineClippingRect);
          arrayOfFloat[0] = localLimitLine.getLimit();
          arrayOfFloat[1] = 0.0F;
          mTrans.pointValuesToPixel(arrayOfFloat);
          renderLimitLineLine(paramCanvas, localLimitLine, arrayOfFloat);
          renderLimitLineLabel(paramCanvas, localLimitLine, arrayOfFloat, 2.0F + localLimitLine.getYOffset());
          paramCanvas.restoreToCount(j);
        }
        i += 1;
      }
      return;
    }
  }
  
  protected void setupGridPaint()
  {
    mGridPaint.setColor(mXAxis.getGridColor());
    mGridPaint.setStrokeWidth(mXAxis.getGridLineWidth());
    mGridPaint.setPathEffect(mXAxis.getGridDashPathEffect());
  }
}

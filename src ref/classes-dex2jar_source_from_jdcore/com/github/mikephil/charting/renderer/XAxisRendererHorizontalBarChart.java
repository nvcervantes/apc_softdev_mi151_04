package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.charts.BarChart;
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

public class XAxisRendererHorizontalBarChart
  extends XAxisRenderer
{
  protected BarChart mChart;
  protected Path mRenderLimitLinesPathBuffer = new Path();
  
  public XAxisRendererHorizontalBarChart(ViewPortHandler paramViewPortHandler, XAxis paramXAxis, Transformer paramTransformer, BarChart paramBarChart)
  {
    super(paramViewPortHandler, paramXAxis, paramTransformer);
    mChart = paramBarChart;
  }
  
  public void computeAxis(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    float f2 = paramFloat1;
    float f1 = paramFloat2;
    if (mViewPortHandler.contentWidth() > 10.0F)
    {
      f2 = paramFloat1;
      f1 = paramFloat2;
      if (!mViewPortHandler.isFullyZoomedOutY())
      {
        MPPointD localMPPointD1 = mTrans.getValuesByTouchPoint(mViewPortHandler.contentLeft(), mViewPortHandler.contentBottom());
        MPPointD localMPPointD2 = mTrans.getValuesByTouchPoint(mViewPortHandler.contentLeft(), mViewPortHandler.contentTop());
        if (paramBoolean)
        {
          paramFloat2 = (float)y;
          paramFloat1 = (float)y;
        }
        else
        {
          paramFloat2 = (float)y;
          paramFloat1 = (float)y;
        }
        MPPointD.recycleInstance(localMPPointD1);
        MPPointD.recycleInstance(localMPPointD2);
        f1 = paramFloat1;
        f2 = paramFloat2;
      }
    }
    computeAxisValues(f2, f1);
  }
  
  protected void computeSize()
  {
    mAxisLabelPaint.setTypeface(mXAxis.getTypeface());
    mAxisLabelPaint.setTextSize(mXAxis.getTextSize());
    Object localObject = mXAxis.getLongestLabel();
    localObject = Utils.calcTextSize(mAxisLabelPaint, (String)localObject);
    float f1 = (int)(width + mXAxis.getXOffset() * 3.5F);
    float f2 = height;
    localObject = Utils.getSizeOfRotatedRectangleByDegrees(width, f2, mXAxis.getLabelRotationAngle());
    mXAxis.mLabelWidth = Math.round(f1);
    mXAxis.mLabelHeight = Math.round(f2);
    mXAxis.mLabelRotatedWidth = ((int)(width + mXAxis.getXOffset() * 3.5F));
    mXAxis.mLabelRotatedHeight = Math.round(height);
    FSize.recycleInstance((FSize)localObject);
  }
  
  protected void drawGridLine(Canvas paramCanvas, float paramFloat1, float paramFloat2, Path paramPath)
  {
    paramPath.moveTo(mViewPortHandler.contentRight(), paramFloat2);
    paramPath.lineTo(mViewPortHandler.contentLeft(), paramFloat2);
    paramCanvas.drawPath(paramPath, mGridPaint);
    paramPath.reset();
  }
  
  protected void drawLabels(Canvas paramCanvas, float paramFloat, MPPointF paramMPPointF)
  {
    float f1 = mXAxis.getLabelRotationAngle();
    boolean bool = mXAxis.isCenterAxisLabelsEnabled();
    float[] arrayOfFloat = new float[mXAxis.mEntryCount * 2];
    int i = 0;
    while (i < arrayOfFloat.length)
    {
      if (bool) {
        arrayOfFloat[(i + 1)] = mXAxis.mCenteredEntries[(i / 2)];
      } else {
        arrayOfFloat[(i + 1)] = mXAxis.mEntries[(i / 2)];
      }
      i += 2;
    }
    mTrans.pointValuesToPixel(arrayOfFloat);
    i = 0;
    while (i < arrayOfFloat.length)
    {
      float f2 = arrayOfFloat[(i + 1)];
      if (mViewPortHandler.isInBoundsY(f2)) {
        drawLabel(paramCanvas, mXAxis.getValueFormatter().getFormattedValue(mXAxis.mEntries[(i / 2)], mXAxis), paramFloat, f2, paramMPPointF, f1);
      }
      i += 2;
    }
  }
  
  public RectF getGridClippingRect()
  {
    mGridClippingRect.set(mViewPortHandler.getContentRect());
    mGridClippingRect.inset(0.0F, -mAxis.getGridLineWidth());
    return mGridClippingRect;
  }
  
  public void renderAxisLabels(Canvas paramCanvas)
  {
    if (mXAxis.isEnabled())
    {
      if (!mXAxis.isDrawLabelsEnabled()) {
        return;
      }
      float f = mXAxis.getXOffset();
      mAxisLabelPaint.setTypeface(mXAxis.getTypeface());
      mAxisLabelPaint.setTextSize(mXAxis.getTextSize());
      mAxisLabelPaint.setColor(mXAxis.getTextColor());
      MPPointF localMPPointF = MPPointF.getInstance(0.0F, 0.0F);
      if (mXAxis.getPosition() == XAxis.XAxisPosition.TOP)
      {
        x = 0.0F;
        y = 0.5F;
        drawLabels(paramCanvas, mViewPortHandler.contentRight() + f, localMPPointF);
      }
      else if (mXAxis.getPosition() == XAxis.XAxisPosition.TOP_INSIDE)
      {
        x = 1.0F;
        y = 0.5F;
        drawLabels(paramCanvas, mViewPortHandler.contentRight() - f, localMPPointF);
      }
      else if (mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM)
      {
        x = 1.0F;
        y = 0.5F;
        drawLabels(paramCanvas, mViewPortHandler.contentLeft() - f, localMPPointF);
      }
      else if (mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM_INSIDE)
      {
        x = 1.0F;
        y = 0.5F;
        drawLabels(paramCanvas, mViewPortHandler.contentLeft() + f, localMPPointF);
      }
      else
      {
        x = 0.0F;
        y = 0.5F;
        drawLabels(paramCanvas, mViewPortHandler.contentRight() + f, localMPPointF);
        x = 1.0F;
        y = 0.5F;
        drawLabels(paramCanvas, mViewPortHandler.contentLeft() - f, localMPPointF);
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
      if ((mXAxis.getPosition() == XAxis.XAxisPosition.TOP) || (mXAxis.getPosition() == XAxis.XAxisPosition.TOP_INSIDE) || (mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED)) {
        paramCanvas.drawLine(mViewPortHandler.contentRight(), mViewPortHandler.contentTop(), mViewPortHandler.contentRight(), mViewPortHandler.contentBottom(), mAxisLinePaint);
      }
      if ((mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM) || (mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM_INSIDE) || (mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED)) {
        paramCanvas.drawLine(mViewPortHandler.contentLeft(), mViewPortHandler.contentTop(), mViewPortHandler.contentLeft(), mViewPortHandler.contentBottom(), mAxisLinePaint);
      }
      return;
    }
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
      int i = 0;
      arrayOfFloat[0] = 0.0F;
      arrayOfFloat[1] = 0.0F;
      Path localPath = mRenderLimitLinesPathBuffer;
      localPath.reset();
      while (i < localList.size())
      {
        Object localObject = (LimitLine)localList.get(i);
        if (((LimitLine)localObject).isEnabled())
        {
          int j = paramCanvas.save();
          mLimitLineClippingRect.set(mViewPortHandler.getContentRect());
          mLimitLineClippingRect.inset(0.0F, -((LimitLine)localObject).getLineWidth());
          paramCanvas.clipRect(mLimitLineClippingRect);
          mLimitLinePaint.setStyle(Paint.Style.STROKE);
          mLimitLinePaint.setColor(((LimitLine)localObject).getLineColor());
          mLimitLinePaint.setStrokeWidth(((LimitLine)localObject).getLineWidth());
          mLimitLinePaint.setPathEffect(((LimitLine)localObject).getDashPathEffect());
          arrayOfFloat[1] = ((LimitLine)localObject).getLimit();
          mTrans.pointValuesToPixel(arrayOfFloat);
          localPath.moveTo(mViewPortHandler.contentLeft(), arrayOfFloat[1]);
          localPath.lineTo(mViewPortHandler.contentRight(), arrayOfFloat[1]);
          paramCanvas.drawPath(localPath, mLimitLinePaint);
          localPath.reset();
          String str = ((LimitLine)localObject).getLabel();
          if ((str != null) && (!str.equals("")))
          {
            mLimitLinePaint.setStyle(((LimitLine)localObject).getTextStyle());
            mLimitLinePaint.setPathEffect(null);
            mLimitLinePaint.setColor(((LimitLine)localObject).getTextColor());
            mLimitLinePaint.setStrokeWidth(0.5F);
            mLimitLinePaint.setTextSize(((LimitLine)localObject).getTextSize());
            float f1 = Utils.calcTextHeight(mLimitLinePaint, str);
            float f2 = Utils.convertDpToPixel(4.0F) + ((LimitLine)localObject).getXOffset();
            float f3 = ((LimitLine)localObject).getLineWidth() + f1 + ((LimitLine)localObject).getYOffset();
            localObject = ((LimitLine)localObject).getLabelPosition();
            if (localObject == LimitLine.LimitLabelPosition.RIGHT_TOP)
            {
              mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
              paramCanvas.drawText(str, mViewPortHandler.contentRight() - f2, arrayOfFloat[1] - f3 + f1, mLimitLinePaint);
            }
            else if (localObject == LimitLine.LimitLabelPosition.RIGHT_BOTTOM)
            {
              mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
              paramCanvas.drawText(str, mViewPortHandler.contentRight() - f2, arrayOfFloat[1] + f3, mLimitLinePaint);
            }
            else if (localObject == LimitLine.LimitLabelPosition.LEFT_TOP)
            {
              mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
              paramCanvas.drawText(str, mViewPortHandler.contentLeft() + f2, arrayOfFloat[1] - f3 + f1, mLimitLinePaint);
            }
            else
            {
              mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
              paramCanvas.drawText(str, mViewPortHandler.offsetLeft() + f2, arrayOfFloat[1] + f3, mLimitLinePaint);
            }
          }
          paramCanvas.restoreToCount(j);
        }
        i += 1;
      }
      return;
    }
  }
}

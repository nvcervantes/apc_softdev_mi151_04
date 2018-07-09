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
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class YAxisRendererHorizontalBarChart
  extends YAxisRenderer
{
  protected Path mDrawZeroLinePathBuffer = new Path();
  protected float[] mRenderLimitLinesBuffer = new float[4];
  protected Path mRenderLimitLinesPathBuffer = new Path();
  
  public YAxisRendererHorizontalBarChart(ViewPortHandler paramViewPortHandler, YAxis paramYAxis, Transformer paramTransformer)
  {
    super(paramViewPortHandler, paramYAxis, paramTransformer);
    mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
  }
  
  public void computeAxis(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    float f2 = paramFloat1;
    float f1 = paramFloat2;
    if (mViewPortHandler.contentHeight() > 10.0F)
    {
      f2 = paramFloat1;
      f1 = paramFloat2;
      if (!mViewPortHandler.isFullyZoomedOutX())
      {
        MPPointD localMPPointD1 = mTrans.getValuesByTouchPoint(mViewPortHandler.contentLeft(), mViewPortHandler.contentTop());
        MPPointD localMPPointD2 = mTrans.getValuesByTouchPoint(mViewPortHandler.contentRight(), mViewPortHandler.contentTop());
        if (!paramBoolean)
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
  
  protected void drawYLabels(Canvas paramCanvas, float paramFloat1, float[] paramArrayOfFloat, float paramFloat2)
  {
    mAxisLabelPaint.setTypeface(mYAxis.getTypeface());
    mAxisLabelPaint.setTextSize(mYAxis.getTextSize());
    mAxisLabelPaint.setColor(mYAxis.getTextColor());
    int j = mYAxis.isDrawBottomYLabelEntryEnabled() ^ true;
    int i;
    if (mYAxis.isDrawTopYLabelEntryEnabled()) {
      i = mYAxis.mEntryCount;
    } else {
      i = mYAxis.mEntryCount - 1;
    }
    while (j < i)
    {
      paramCanvas.drawText(mYAxis.getFormattedLabel(j), paramArrayOfFloat[(j * 2)], paramFloat1 - paramFloat2, mAxisLabelPaint);
      int k;
      j += 1;
    }
  }
  
  protected void drawZeroLine(Canvas paramCanvas)
  {
    int i = paramCanvas.save();
    mZeroLineClippingRect.set(mViewPortHandler.getContentRect());
    mZeroLineClippingRect.inset(-mYAxis.getZeroLineWidth(), 0.0F);
    paramCanvas.clipRect(mLimitLineClippingRect);
    MPPointD localMPPointD = mTrans.getPixelForValues(0.0F, 0.0F);
    mZeroLinePaint.setColor(mYAxis.getZeroLineColor());
    mZeroLinePaint.setStrokeWidth(mYAxis.getZeroLineWidth());
    Path localPath = mDrawZeroLinePathBuffer;
    localPath.reset();
    localPath.moveTo((float)x - 1.0F, mViewPortHandler.contentTop());
    localPath.lineTo((float)x - 1.0F, mViewPortHandler.contentBottom());
    paramCanvas.drawPath(localPath, mZeroLinePaint);
    paramCanvas.restoreToCount(i);
  }
  
  public RectF getGridClippingRect()
  {
    mGridClippingRect.set(mViewPortHandler.getContentRect());
    mGridClippingRect.inset(-mAxis.getGridLineWidth(), 0.0F);
    return mGridClippingRect;
  }
  
  protected float[] getTransformedPositions()
  {
    if (mGetTransformedPositionsBuffer.length != mYAxis.mEntryCount * 2) {
      mGetTransformedPositionsBuffer = new float[mYAxis.mEntryCount * 2];
    }
    float[] arrayOfFloat = mGetTransformedPositionsBuffer;
    int i = 0;
    while (i < arrayOfFloat.length)
    {
      arrayOfFloat[i] = mYAxis.mEntries[(i / 2)];
      i += 2;
    }
    mTrans.pointValuesToPixel(arrayOfFloat);
    return arrayOfFloat;
  }
  
  protected Path linePath(Path paramPath, int paramInt, float[] paramArrayOfFloat)
  {
    paramPath.moveTo(paramArrayOfFloat[paramInt], mViewPortHandler.contentTop());
    paramPath.lineTo(paramArrayOfFloat[paramInt], mViewPortHandler.contentBottom());
    return paramPath;
  }
  
  public void renderAxisLabels(Canvas paramCanvas)
  {
    if (mYAxis.isEnabled())
    {
      if (!mYAxis.isDrawLabelsEnabled()) {
        return;
      }
      float[] arrayOfFloat = getTransformedPositions();
      mAxisLabelPaint.setTypeface(mYAxis.getTypeface());
      mAxisLabelPaint.setTextSize(mYAxis.getTextSize());
      mAxisLabelPaint.setColor(mYAxis.getTextColor());
      mAxisLabelPaint.setTextAlign(Paint.Align.CENTER);
      float f1 = Utils.convertDpToPixel(2.5F);
      float f2 = Utils.calcTextHeight(mAxisLabelPaint, "Q");
      YAxis.AxisDependency localAxisDependency = mYAxis.getAxisDependency();
      YAxis.YAxisLabelPosition localYAxisLabelPosition = mYAxis.getLabelPosition();
      if (localAxisDependency == YAxis.AxisDependency.LEFT)
      {
        if (localYAxisLabelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART) {
          f1 = mViewPortHandler.contentTop() - f1;
        } else {
          f1 = mViewPortHandler.contentTop() - f1;
        }
      }
      else if (localYAxisLabelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART) {
        f1 = mViewPortHandler.contentBottom() + f2 + f1;
      } else {
        f1 = mViewPortHandler.contentBottom() + f2 + f1;
      }
      drawYLabels(paramCanvas, f1, arrayOfFloat, mYAxis.getYOffset());
      return;
    }
  }
  
  public void renderAxisLine(Canvas paramCanvas)
  {
    if (mYAxis.isEnabled())
    {
      if (!mYAxis.isDrawAxisLineEnabled()) {
        return;
      }
      mAxisLinePaint.setColor(mYAxis.getAxisLineColor());
      mAxisLinePaint.setStrokeWidth(mYAxis.getAxisLineWidth());
      if (mYAxis.getAxisDependency() == YAxis.AxisDependency.LEFT)
      {
        paramCanvas.drawLine(mViewPortHandler.contentLeft(), mViewPortHandler.contentTop(), mViewPortHandler.contentRight(), mViewPortHandler.contentTop(), mAxisLinePaint);
        return;
      }
      paramCanvas.drawLine(mViewPortHandler.contentLeft(), mViewPortHandler.contentBottom(), mViewPortHandler.contentRight(), mViewPortHandler.contentBottom(), mAxisLinePaint);
      return;
    }
  }
  
  public void renderLimitLines(Canvas paramCanvas)
  {
    List localList = mYAxis.getLimitLines();
    if (localList != null)
    {
      if (localList.size() <= 0) {
        return;
      }
      float[] arrayOfFloat = mRenderLimitLinesBuffer;
      arrayOfFloat[0] = 0.0F;
      arrayOfFloat[1] = 0.0F;
      arrayOfFloat[2] = 0.0F;
      arrayOfFloat[3] = 0.0F;
      Path localPath = mRenderLimitLinesPathBuffer;
      localPath.reset();
      int i = 0;
      while (i < localList.size())
      {
        Object localObject = (LimitLine)localList.get(i);
        if (((LimitLine)localObject).isEnabled())
        {
          int j = paramCanvas.save();
          mLimitLineClippingRect.set(mViewPortHandler.getContentRect());
          mLimitLineClippingRect.inset(-((LimitLine)localObject).getLineWidth(), 0.0F);
          paramCanvas.clipRect(mLimitLineClippingRect);
          arrayOfFloat[0] = ((LimitLine)localObject).getLimit();
          arrayOfFloat[2] = ((LimitLine)localObject).getLimit();
          mTrans.pointValuesToPixel(arrayOfFloat);
          arrayOfFloat[1] = mViewPortHandler.contentTop();
          arrayOfFloat[3] = mViewPortHandler.contentBottom();
          localPath.moveTo(arrayOfFloat[0], arrayOfFloat[1]);
          localPath.lineTo(arrayOfFloat[2], arrayOfFloat[3]);
          mLimitLinePaint.setStyle(Paint.Style.STROKE);
          mLimitLinePaint.setColor(((LimitLine)localObject).getLineColor());
          mLimitLinePaint.setPathEffect(((LimitLine)localObject).getDashPathEffect());
          mLimitLinePaint.setStrokeWidth(((LimitLine)localObject).getLineWidth());
          paramCanvas.drawPath(localPath, mLimitLinePaint);
          localPath.reset();
          String str = ((LimitLine)localObject).getLabel();
          if ((str != null) && (!str.equals("")))
          {
            mLimitLinePaint.setStyle(((LimitLine)localObject).getTextStyle());
            mLimitLinePaint.setPathEffect(null);
            mLimitLinePaint.setColor(((LimitLine)localObject).getTextColor());
            mLimitLinePaint.setTypeface(((LimitLine)localObject).getTypeface());
            mLimitLinePaint.setStrokeWidth(0.5F);
            mLimitLinePaint.setTextSize(((LimitLine)localObject).getTextSize());
            float f1 = ((LimitLine)localObject).getLineWidth() + ((LimitLine)localObject).getXOffset();
            float f2 = Utils.convertDpToPixel(2.0F) + ((LimitLine)localObject).getYOffset();
            localObject = ((LimitLine)localObject).getLabelPosition();
            float f3;
            if (localObject == LimitLine.LimitLabelPosition.RIGHT_TOP)
            {
              f3 = Utils.calcTextHeight(mLimitLinePaint, str);
              mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
              paramCanvas.drawText(str, arrayOfFloat[0] + f1, mViewPortHandler.contentTop() + f2 + f3, mLimitLinePaint);
            }
            else if (localObject == LimitLine.LimitLabelPosition.RIGHT_BOTTOM)
            {
              mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
              paramCanvas.drawText(str, arrayOfFloat[0] + f1, mViewPortHandler.contentBottom() - f2, mLimitLinePaint);
            }
            else if (localObject == LimitLine.LimitLabelPosition.LEFT_TOP)
            {
              mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
              f3 = Utils.calcTextHeight(mLimitLinePaint, str);
              paramCanvas.drawText(str, arrayOfFloat[0] - f1, mViewPortHandler.contentTop() + f2 + f3, mLimitLinePaint);
            }
            else
            {
              mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
              paramCanvas.drawText(str, arrayOfFloat[0] - f1, mViewPortHandler.contentBottom() - f2, mLimitLinePaint);
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

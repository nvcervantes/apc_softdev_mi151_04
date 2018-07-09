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

public class YAxisRenderer
  extends AxisRenderer
{
  protected Path mDrawZeroLinePath = new Path();
  protected float[] mGetTransformedPositionsBuffer = new float[2];
  protected RectF mGridClippingRect = new RectF();
  protected RectF mLimitLineClippingRect = new RectF();
  protected Path mRenderGridLinesPath = new Path();
  protected Path mRenderLimitLines = new Path();
  protected float[] mRenderLimitLinesBuffer = new float[2];
  protected YAxis mYAxis;
  protected RectF mZeroLineClippingRect = new RectF();
  protected Paint mZeroLinePaint;
  
  public YAxisRenderer(ViewPortHandler paramViewPortHandler, YAxis paramYAxis, Transformer paramTransformer)
  {
    super(paramViewPortHandler, paramTransformer, paramYAxis);
    mYAxis = paramYAxis;
    if (mViewPortHandler != null)
    {
      mAxisLabelPaint.setColor(-16777216);
      mAxisLabelPaint.setTextSize(Utils.convertDpToPixel(10.0F));
      mZeroLinePaint = new Paint(1);
      mZeroLinePaint.setColor(-7829368);
      mZeroLinePaint.setStrokeWidth(1.0F);
      mZeroLinePaint.setStyle(Paint.Style.STROKE);
    }
  }
  
  protected void drawYLabels(Canvas paramCanvas, float paramFloat1, float[] paramArrayOfFloat, float paramFloat2)
  {
    int j = mYAxis.isDrawBottomYLabelEntryEnabled() ^ true;
    int i;
    if (mYAxis.isDrawTopYLabelEntryEnabled()) {
      i = mYAxis.mEntryCount;
    } else {
      i = mYAxis.mEntryCount - 1;
    }
    while (j < i)
    {
      paramCanvas.drawText(mYAxis.getFormattedLabel(j), paramFloat1, paramArrayOfFloat[(j * 2 + 1)] + paramFloat2, mAxisLabelPaint);
      int k;
      j += 1;
    }
  }
  
  protected void drawZeroLine(Canvas paramCanvas)
  {
    int i = paramCanvas.save();
    mZeroLineClippingRect.set(mViewPortHandler.getContentRect());
    mZeroLineClippingRect.inset(0.0F, -mYAxis.getZeroLineWidth());
    paramCanvas.clipRect(mZeroLineClippingRect);
    MPPointD localMPPointD = mTrans.getPixelForValues(0.0F, 0.0F);
    mZeroLinePaint.setColor(mYAxis.getZeroLineColor());
    mZeroLinePaint.setStrokeWidth(mYAxis.getZeroLineWidth());
    Path localPath = mDrawZeroLinePath;
    localPath.reset();
    localPath.moveTo(mViewPortHandler.contentLeft(), (float)y);
    localPath.lineTo(mViewPortHandler.contentRight(), (float)y);
    paramCanvas.drawPath(localPath, mZeroLinePaint);
    paramCanvas.restoreToCount(i);
  }
  
  public RectF getGridClippingRect()
  {
    mGridClippingRect.set(mViewPortHandler.getContentRect());
    mGridClippingRect.inset(0.0F, -mAxis.getGridLineWidth());
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
      arrayOfFloat[(i + 1)] = mYAxis.mEntries[(i / 2)];
      i += 2;
    }
    mTrans.pointValuesToPixel(arrayOfFloat);
    return arrayOfFloat;
  }
  
  protected Path linePath(Path paramPath, int paramInt, float[] paramArrayOfFloat)
  {
    float f = mViewPortHandler.offsetLeft();
    paramInt += 1;
    paramPath.moveTo(f, paramArrayOfFloat[paramInt]);
    paramPath.lineTo(mViewPortHandler.contentRight(), paramArrayOfFloat[paramInt]);
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
      float f1 = mYAxis.getXOffset();
      float f2 = Utils.calcTextHeight(mAxisLabelPaint, "A") / 2.5F;
      float f3 = mYAxis.getYOffset();
      YAxis.AxisDependency localAxisDependency = mYAxis.getAxisDependency();
      YAxis.YAxisLabelPosition localYAxisLabelPosition = mYAxis.getLabelPosition();
      if (localAxisDependency == YAxis.AxisDependency.LEFT)
      {
        if (localYAxisLabelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        {
          mAxisLabelPaint.setTextAlign(Paint.Align.RIGHT);
          f1 = mViewPortHandler.offsetLeft() - f1;
        }
        else
        {
          mAxisLabelPaint.setTextAlign(Paint.Align.LEFT);
          f1 = mViewPortHandler.offsetLeft() + f1;
        }
      }
      else if (localYAxisLabelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART)
      {
        mAxisLabelPaint.setTextAlign(Paint.Align.LEFT);
        f1 = mViewPortHandler.contentRight() + f1;
      }
      else
      {
        mAxisLabelPaint.setTextAlign(Paint.Align.RIGHT);
        f1 = mViewPortHandler.contentRight() - f1;
      }
      drawYLabels(paramCanvas, f1, arrayOfFloat, f2 + f3);
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
        paramCanvas.drawLine(mViewPortHandler.contentLeft(), mViewPortHandler.contentTop(), mViewPortHandler.contentLeft(), mViewPortHandler.contentBottom(), mAxisLinePaint);
        return;
      }
      paramCanvas.drawLine(mViewPortHandler.contentRight(), mViewPortHandler.contentTop(), mViewPortHandler.contentRight(), mViewPortHandler.contentBottom(), mAxisLinePaint);
      return;
    }
  }
  
  public void renderGridLines(Canvas paramCanvas)
  {
    if (!mYAxis.isEnabled()) {
      return;
    }
    if (mYAxis.isDrawGridLinesEnabled())
    {
      int j = paramCanvas.save();
      paramCanvas.clipRect(getGridClippingRect());
      float[] arrayOfFloat = getTransformedPositions();
      mGridPaint.setColor(mYAxis.getGridColor());
      mGridPaint.setStrokeWidth(mYAxis.getGridLineWidth());
      mGridPaint.setPathEffect(mYAxis.getGridDashPathEffect());
      Path localPath = mRenderGridLinesPath;
      localPath.reset();
      int i = 0;
      while (i < arrayOfFloat.length)
      {
        paramCanvas.drawPath(linePath(localPath, i, arrayOfFloat), mGridPaint);
        localPath.reset();
        i += 2;
      }
      paramCanvas.restoreToCount(j);
    }
    if (mYAxis.isDrawZeroLineEnabled()) {
      drawZeroLine(paramCanvas);
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
      int i = 0;
      arrayOfFloat[0] = 0.0F;
      arrayOfFloat[1] = 0.0F;
      Path localPath = mRenderLimitLines;
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
            mLimitLinePaint.setTypeface(((LimitLine)localObject).getTypeface());
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

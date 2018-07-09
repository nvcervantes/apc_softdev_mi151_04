package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.buffer.HorizontalBarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class HorizontalBarChartRenderer
  extends BarChartRenderer
{
  private RectF mBarShadowRectBuffer = new RectF();
  
  public HorizontalBarChartRenderer(BarDataProvider paramBarDataProvider, ChartAnimator paramChartAnimator, ViewPortHandler paramViewPortHandler)
  {
    super(paramBarDataProvider, paramChartAnimator, paramViewPortHandler);
    mValuePaint.setTextAlign(Paint.Align.LEFT);
  }
  
  protected void drawDataSet(Canvas paramCanvas, IBarDataSet paramIBarDataSet, int paramInt)
  {
    Object localObject = mChart.getTransformer(paramIBarDataSet.getAxisDependency());
    mBarBorderPaint.setColor(paramIBarDataSet.getBarBorderColor());
    mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(paramIBarDataSet.getBarBorderWidth()));
    float f1 = paramIBarDataSet.getBarBorderWidth();
    int k = 0;
    int m = 1;
    int i;
    if (f1 > 0.0F) {
      i = 1;
    } else {
      i = 0;
    }
    f1 = mAnimator.getPhaseX();
    float f2 = mAnimator.getPhaseY();
    int n;
    if (mChart.isDrawBarShadowEnabled())
    {
      mShadowPaint.setColor(paramIBarDataSet.getBarShadowColor());
      float f3 = mChart.getBarData().getBarWidth() / 2.0F;
      n = Math.min((int)Math.ceil(paramIBarDataSet.getEntryCount() * f1), paramIBarDataSet.getEntryCount());
      j = 0;
      while (j < n)
      {
        float f4 = ((BarEntry)paramIBarDataSet.getEntryForIndex(j)).getX();
        mBarShadowRectBuffer.top = (f4 - f3);
        mBarShadowRectBuffer.bottom = (f4 + f3);
        ((Transformer)localObject).rectValueToPixel(mBarShadowRectBuffer);
        if (mViewPortHandler.isInBoundsTop(mBarShadowRectBuffer.bottom))
        {
          if (!mViewPortHandler.isInBoundsBottom(mBarShadowRectBuffer.top)) {
            break;
          }
          mBarShadowRectBuffer.left = mViewPortHandler.contentLeft();
          mBarShadowRectBuffer.right = mViewPortHandler.contentRight();
          paramCanvas.drawRect(mBarShadowRectBuffer, mShadowPaint);
        }
        j += 1;
      }
    }
    BarBuffer localBarBuffer = mBarBuffers[paramInt];
    localBarBuffer.setPhases(f1, f2);
    localBarBuffer.setDataSet(paramInt);
    localBarBuffer.setInverted(mChart.isInverted(paramIBarDataSet.getAxisDependency()));
    localBarBuffer.setBarWidth(mChart.getBarData().getBarWidth());
    localBarBuffer.feed(paramIBarDataSet);
    ((Transformer)localObject).pointValuesToPixel(buffer);
    if (paramIBarDataSet.getColors().size() == 1) {
      paramInt = m;
    } else {
      paramInt = 0;
    }
    int j = k;
    if (paramInt != 0)
    {
      mRenderPaint.setColor(paramIBarDataSet.getColor());
      j = k;
    }
    while (j < localBarBuffer.size())
    {
      localObject = mViewPortHandler;
      float[] arrayOfFloat = buffer;
      k = j + 3;
      if (!((ViewPortHandler)localObject).isInBoundsTop(arrayOfFloat[k])) {
        return;
      }
      localObject = mViewPortHandler;
      arrayOfFloat = buffer;
      m = j + 1;
      if (((ViewPortHandler)localObject).isInBoundsBottom(arrayOfFloat[m]))
      {
        if (paramInt == 0) {
          mRenderPaint.setColor(paramIBarDataSet.getColor(j / 4));
        }
        f1 = buffer[j];
        f2 = buffer[m];
        localObject = buffer;
        n = j + 2;
        paramCanvas.drawRect(f1, f2, localObject[n], buffer[k], mRenderPaint);
        if (i != 0) {
          paramCanvas.drawRect(buffer[j], buffer[m], buffer[n], buffer[k], mBarBorderPaint);
        }
      }
      j += 4;
    }
  }
  
  protected void drawValue(Canvas paramCanvas, String paramString, float paramFloat1, float paramFloat2, int paramInt)
  {
    mValuePaint.setColor(paramInt);
    paramCanvas.drawText(paramString, paramFloat1, paramFloat2, mValuePaint);
  }
  
  public void drawValues(Canvas paramCanvas)
  {
    if (isDrawingValuesAllowed(mChart))
    {
      Object localObject1 = mChart.getBarData().getDataSets();
      float f1 = Utils.convertDpToPixel(5.0F);
      boolean bool1 = mChart.isDrawValueAboveBarEnabled();
      int j = 0;
      while (j < mChart.getBarData().getDataSetCount())
      {
        IBarDataSet localIBarDataSet = (IBarDataSet)((List)localObject1).get(j);
        if (shouldDrawValues(localIBarDataSet))
        {
          boolean bool3 = mChart.isInverted(localIBarDataSet.getAxisDependency());
          applyValueTextStyle(localIBarDataSet);
          float f9 = Utils.calcTextHeight(mValuePaint, "10") / 2.0F;
          Object localObject7 = localIBarDataSet.getValueFormatter();
          Object localObject6 = mBarBuffers[j];
          float f10 = mAnimator.getPhaseY();
          Object localObject5 = MPPointF.getInstance(localIBarDataSet.getIconsOffset());
          x = Utils.convertDpToPixel(x);
          y = Utils.convertDpToPixel(y);
          int i;
          float f6;
          boolean bool2;
          Object localObject3;
          Object localObject2;
          Object localObject4;
          int k;
          float f7;
          float f8;
          float f3;
          float f4;
          float f5;
          float f2;
          if (!localIBarDataSet.isStacked())
          {
            i = 0;
            f6 = f9;
            bool2 = bool3;
            localObject3 = localObject1;
            localObject2 = localObject7;
            localObject4 = localObject6;
            localObject1 = localObject5;
            while (i < buffer.length * mAnimator.getPhaseX())
            {
              localObject5 = buffer;
              k = i + 1;
              f7 = (localObject5[k] + buffer[(i + 3)]) / 2.0F;
              if (!mViewPortHandler.isInBoundsTop(buffer[k])) {
                break;
              }
              if (mViewPortHandler.isInBoundsX(buffer[i]))
              {
                while (!mViewPortHandler.isInBoundsBottom(buffer[k])) {}
                localObject7 = (BarEntry)localIBarDataSet.getEntryForIndex(i / 4);
                f8 = ((BarEntry)localObject7).getY();
                localObject5 = ((IValueFormatter)localObject2).getFormattedValue(f8, (Entry)localObject7, j, mViewPortHandler);
                f9 = Utils.calcTextWidth(mValuePaint, (String)localObject5);
                if (bool1) {
                  f3 = f1;
                } else {
                  f3 = -(f9 + f1);
                }
                if (bool1) {
                  f4 = -(f9 + f1);
                } else {
                  f4 = f1;
                }
                f5 = f3;
                f2 = f4;
                if (bool2)
                {
                  f5 = -f3 - f9;
                  f2 = -f4 - f9;
                }
                f3 = f5;
                if (localIBarDataSet.isDrawValuesEnabled())
                {
                  f5 = buffer[(i + 2)];
                  if (f8 >= 0.0F) {
                    f4 = f3;
                  } else {
                    f4 = f2;
                  }
                  drawValue(paramCanvas, (String)localObject5, f4 + f5, f7 + f6, localIBarDataSet.getValueTextColor(i / 2));
                }
                localObject5 = localObject2;
                localObject6 = localObject1;
                localObject2 = localObject5;
                if (((BarEntry)localObject7).getIcon() != null)
                {
                  localObject2 = localObject5;
                  if (localIBarDataSet.isDrawIconsEnabled())
                  {
                    localObject2 = ((BarEntry)localObject7).getIcon();
                    f4 = buffer[(i + 2)];
                    if (f8 < 0.0F) {
                      f3 = f2;
                    }
                    f2 = x;
                    f5 = y;
                    Utils.drawImage(paramCanvas, (Drawable)localObject2, (int)(f4 + f3 + f2), (int)(f7 + f5), ((Drawable)localObject2).getIntrinsicWidth(), ((Drawable)localObject2).getIntrinsicHeight());
                    localObject2 = localObject5;
                  }
                }
              }
              i += 4;
            }
            localObject4 = localObject1;
            f2 = f1;
            bool2 = bool1;
            localObject1 = localObject3;
          }
          else
          {
            localObject2 = localObject1;
            localObject3 = localObject5;
            Transformer localTransformer = mChart.getTransformer(localIBarDataSet.getAxisDependency());
            k = 0;
            i = 0;
            for (;;)
            {
              localObject4 = localObject3;
              f2 = f1;
              bool2 = bool1;
              localObject1 = localObject2;
              if (k >= localIBarDataSet.getEntryCount() * mAnimator.getPhaseX()) {
                break;
              }
              localObject5 = (BarEntry)localIBarDataSet.getEntryForIndex(k);
              int i1 = localIBarDataSet.getValueTextColor(k);
              localObject1 = ((BarEntry)localObject5).getYVals();
              Object localObject8;
              int m;
              if (localObject1 == null)
              {
                localObject4 = mViewPortHandler;
                localObject8 = buffer;
                m = i + 1;
                if (!((ViewPortHandler)localObject4).isInBoundsTop(localObject8[m]))
                {
                  localObject4 = localObject3;
                  f2 = f1;
                  bool2 = bool1;
                  localObject1 = localObject2;
                  break;
                }
                if ((!mViewPortHandler.isInBoundsX(buffer[i])) || (!mViewPortHandler.isInBoundsBottom(buffer[m]))) {
                  continue;
                }
                localObject4 = ((IValueFormatter)localObject7).getFormattedValue(((BarEntry)localObject5).getY(), (Entry)localObject5, j, mViewPortHandler);
                f6 = Utils.calcTextWidth(mValuePaint, (String)localObject4);
                if (bool1) {
                  f3 = f1;
                } else {
                  f3 = -(f6 + f1);
                }
                if (bool1) {
                  f4 = -(f6 + f1);
                } else {
                  f4 = f1;
                }
                f5 = f3;
                f2 = f4;
                if (bool3)
                {
                  f5 = -f3 - f6;
                  f2 = -f4 - f6;
                }
                f3 = f5;
                if (localIBarDataSet.isDrawValuesEnabled())
                {
                  f5 = buffer[(i + 2)];
                  if (((BarEntry)localObject5).getY() >= 0.0F) {
                    f4 = f3;
                  } else {
                    f4 = f2;
                  }
                  drawValue(paramCanvas, (String)localObject4, f5 + f4, buffer[m] + f9, i1);
                }
                bool2 = bool1;
                if (((BarEntry)localObject5).getIcon() != null)
                {
                  bool2 = bool1;
                  if (localIBarDataSet.isDrawIconsEnabled())
                  {
                    localObject4 = ((BarEntry)localObject5).getIcon();
                    f4 = buffer[(i + 2)];
                    if (((BarEntry)localObject5).getY() < 0.0F) {
                      f3 = f2;
                    }
                    f2 = buffer[m];
                    f5 = x;
                    f6 = y;
                    Utils.drawImage(paramCanvas, (Drawable)localObject4, (int)(f4 + f3 + f5), (int)(f2 + f6), ((Drawable)localObject4).getIntrinsicWidth(), ((Drawable)localObject4).getIntrinsicHeight());
                    bool2 = bool1;
                  }
                }
              }
              else
              {
                f2 = f1;
                localObject8 = localObject1;
                localObject4 = new float[localObject8.length * 2];
                f3 = -((BarEntry)localObject5).getNegativeSum();
                f8 = 0.0F;
                int n = 0;
                m = 0;
                while (n < localObject4.length)
                {
                  f7 = localObject8[m];
                  if (f7 == 0.0F)
                  {
                    f4 = f7;
                    f5 = f8;
                    f6 = f3;
                    if (f8 == 0.0F) {
                      break label1301;
                    }
                    if (f3 == 0.0F)
                    {
                      f4 = f7;
                      f5 = f8;
                      f6 = f3;
                      break label1301;
                    }
                  }
                  if (f7 >= 0.0F)
                  {
                    f4 = f8 + f7;
                    f5 = f4;
                    f6 = f3;
                  }
                  else
                  {
                    f6 = f3 - f7;
                    f5 = f8;
                    f4 = f3;
                  }
                  label1301:
                  localObject4[n] = (f4 * f10);
                  n += 2;
                  m += 1;
                  f8 = f5;
                  f3 = f6;
                }
                localTransformer.pointValuesToPixel((float[])localObject4);
                m = 0;
                for (;;)
                {
                  bool2 = bool1;
                  if (m >= localObject4.length) {
                    break;
                  }
                  float f11 = localObject8[(m / 2)];
                  Object localObject9 = ((IValueFormatter)localObject7).getFormattedValue(f11, (Entry)localObject5, j, mViewPortHandler);
                  float f12 = Utils.calcTextWidth(mValuePaint, (String)localObject9);
                  if (bool1) {
                    f5 = f2;
                  } else {
                    f5 = -(f12 + f2);
                  }
                  if (bool1) {
                    f6 = -(f12 + f2);
                  } else {
                    f6 = f2;
                  }
                  f7 = f5;
                  f4 = f6;
                  if (bool3)
                  {
                    f7 = -f5 - f12;
                    f4 = -f6 - f12;
                  }
                  if (((f11 == 0.0F) && (f3 == 0.0F) && (f8 > 0.0F)) || (f11 < 0.0F)) {
                    n = 1;
                  } else {
                    n = 0;
                  }
                  f5 = localObject4[m];
                  if (n != 0) {
                    f7 = f4;
                  }
                  f4 = f5 + f7;
                  f5 = (buffer[(i + 1)] + buffer[(i + 3)]) / 2.0F;
                  if (!mViewPortHandler.isInBoundsTop(f5)) {
                    break label1692;
                  }
                  if (mViewPortHandler.isInBoundsX(f4))
                  {
                    while (!mViewPortHandler.isInBoundsBottom(f5)) {}
                    if (localIBarDataSet.isDrawValuesEnabled()) {
                      drawValue(paramCanvas, (String)localObject9, f4, f5 + f9, i1);
                    }
                    if ((((BarEntry)localObject5).getIcon() != null) && (localIBarDataSet.isDrawIconsEnabled()))
                    {
                      localObject9 = ((BarEntry)localObject5).getIcon();
                      Utils.drawImage(paramCanvas, (Drawable)localObject9, (int)(f4 + x), (int)(f5 + y), ((Drawable)localObject9).getIntrinsicWidth(), ((Drawable)localObject9).getIntrinsicHeight());
                    }
                  }
                  m += 2;
                }
              }
              bool1 = bool2;
              label1692:
              if (localObject1 == null) {
                i += 4;
              } else {
                i += 4 * localObject1.length;
              }
              k += 1;
            }
          }
          f1 = f2;
          bool1 = bool2;
          MPPointF.recycleInstance((MPPointF)localObject4);
        }
        j += 1;
      }
    }
  }
  
  public void initBuffers()
  {
    BarData localBarData = mChart.getBarData();
    mBarBuffers = new HorizontalBarBuffer[localBarData.getDataSetCount()];
    int i = 0;
    while (i < mBarBuffers.length)
    {
      IBarDataSet localIBarDataSet = (IBarDataSet)localBarData.getDataSetByIndex(i);
      BarBuffer[] arrayOfBarBuffer = mBarBuffers;
      int k = localIBarDataSet.getEntryCount();
      int j;
      if (localIBarDataSet.isStacked()) {
        j = localIBarDataSet.getStackSize();
      } else {
        j = 1;
      }
      arrayOfBarBuffer[i] = new HorizontalBarBuffer(k * 4 * j, localBarData.getDataSetCount(), localIBarDataSet.isStacked());
      i += 1;
    }
  }
  
  protected boolean isDrawingValuesAllowed(ChartInterface paramChartInterface)
  {
    return paramChartInterface.getData().getEntryCount() < paramChartInterface.getMaxVisibleCount() * mViewPortHandler.getScaleY();
  }
  
  protected void prepareBarHighlight(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Transformer paramTransformer)
  {
    mBarRect.set(paramFloat2, paramFloat1 - paramFloat4, paramFloat3, paramFloat1 + paramFloat4);
    paramTransformer.rectToPixelPhaseHorizontal(mBarRect, mAnimator.getPhaseY());
  }
  
  protected void setHighlightDrawPos(Highlight paramHighlight, RectF paramRectF)
  {
    paramHighlight.setDraw(paramRectF.centerY(), right);
  }
}

package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.Range;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class BarChartRenderer
  extends BarLineScatterCandleBubbleRenderer
{
  protected Paint mBarBorderPaint;
  protected BarBuffer[] mBarBuffers;
  protected RectF mBarRect = new RectF();
  private RectF mBarShadowRectBuffer = new RectF();
  protected BarDataProvider mChart;
  protected Paint mShadowPaint;
  
  public BarChartRenderer(BarDataProvider paramBarDataProvider, ChartAnimator paramChartAnimator, ViewPortHandler paramViewPortHandler)
  {
    super(paramChartAnimator, paramViewPortHandler);
    mChart = paramBarDataProvider;
    mHighlightPaint = new Paint(1);
    mHighlightPaint.setStyle(Paint.Style.FILL);
    mHighlightPaint.setColor(Color.rgb(0, 0, 0));
    mHighlightPaint.setAlpha(120);
    mShadowPaint = new Paint(1);
    mShadowPaint.setStyle(Paint.Style.FILL);
    mBarBorderPaint = new Paint(1);
    mBarBorderPaint.setStyle(Paint.Style.STROKE);
  }
  
  public void drawData(Canvas paramCanvas)
  {
    BarData localBarData = mChart.getBarData();
    int i = 0;
    while (i < localBarData.getDataSetCount())
    {
      IBarDataSet localIBarDataSet = (IBarDataSet)localBarData.getDataSetByIndex(i);
      if (localIBarDataSet.isVisible()) {
        drawDataSet(paramCanvas, localIBarDataSet, i);
      }
      i += 1;
    }
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
    float f3;
    int n;
    if (mChart.isDrawBarShadowEnabled())
    {
      mShadowPaint.setColor(paramIBarDataSet.getBarShadowColor());
      f3 = mChart.getBarData().getBarWidth() / 2.0F;
      n = Math.min((int)Math.ceil(paramIBarDataSet.getEntryCount() * f1), paramIBarDataSet.getEntryCount());
      j = 0;
      while (j < n)
      {
        float f4 = ((BarEntry)paramIBarDataSet.getEntryForIndex(j)).getX();
        mBarShadowRectBuffer.left = (f4 - f3);
        mBarShadowRectBuffer.right = (f4 + f3);
        ((Transformer)localObject).rectValueToPixel(mBarShadowRectBuffer);
        if (mViewPortHandler.isInBoundsLeft(mBarShadowRectBuffer.right))
        {
          if (!mViewPortHandler.isInBoundsRight(mBarShadowRectBuffer.left)) {
            break;
          }
          mBarShadowRectBuffer.top = mViewPortHandler.contentTop();
          mBarShadowRectBuffer.bottom = mViewPortHandler.contentBottom();
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
      k = j + 2;
      if (((ViewPortHandler)localObject).isInBoundsLeft(arrayOfFloat[k]))
      {
        if (!mViewPortHandler.isInBoundsRight(buffer[j])) {
          return;
        }
        if (paramInt == 0) {
          mRenderPaint.setColor(paramIBarDataSet.getColor(j / 4));
        }
        f1 = buffer[j];
        localObject = buffer;
        m = j + 1;
        f2 = localObject[m];
        f3 = buffer[k];
        localObject = buffer;
        n = j + 3;
        paramCanvas.drawRect(f1, f2, f3, localObject[n], mRenderPaint);
        if (i != 0) {
          paramCanvas.drawRect(buffer[j], buffer[m], buffer[k], buffer[n], mBarBorderPaint);
        }
      }
      j += 4;
    }
  }
  
  public void drawExtras(Canvas paramCanvas) {}
  
  public void drawHighlighted(Canvas paramCanvas, Highlight[] paramArrayOfHighlight)
  {
    BarData localBarData = mChart.getBarData();
    int k = paramArrayOfHighlight.length;
    int i = 0;
    while (i < k)
    {
      Highlight localHighlight = paramArrayOfHighlight[i];
      Object localObject = (IBarDataSet)localBarData.getDataSetByIndex(localHighlight.getDataSetIndex());
      if ((localObject != null) && (((IBarDataSet)localObject).isHighlightEnabled()))
      {
        BarEntry localBarEntry = (BarEntry)((IBarDataSet)localObject).getEntryForXValue(localHighlight.getX(), localHighlight.getY());
        if (isInBoundsX(localBarEntry, (IBarLineScatterCandleBubbleDataSet)localObject))
        {
          Transformer localTransformer = mChart.getTransformer(((IBarDataSet)localObject).getAxisDependency());
          mHighlightPaint.setColor(((IBarDataSet)localObject).getHighLightColor());
          mHighlightPaint.setAlpha(((IBarDataSet)localObject).getHighLightAlpha());
          int j;
          if ((localHighlight.getStackIndex() >= 0) && (localBarEntry.isStacked())) {
            j = 1;
          } else {
            j = 0;
          }
          float f1;
          float f2;
          if (j != 0)
          {
            if (mChart.isHighlightFullBarEnabled())
            {
              f1 = localBarEntry.getPositiveSum();
              f2 = -localBarEntry.getNegativeSum();
            }
            else
            {
              localObject = localBarEntry.getRanges()[localHighlight.getStackIndex()];
              f1 = from;
              f2 = to;
            }
          }
          else
          {
            f1 = localBarEntry.getY();
            f2 = 0.0F;
          }
          prepareBarHighlight(localBarEntry.getX(), f1, f2, localBarData.getBarWidth() / 2.0F, localTransformer);
          setHighlightDrawPos(localHighlight, mBarRect);
          paramCanvas.drawRect(mBarRect, mHighlightPaint);
        }
      }
      i += 1;
    }
  }
  
  public void drawValues(Canvas paramCanvas)
  {
    if (isDrawingValuesAllowed(mChart))
    {
      Object localObject1 = mChart.getBarData().getDataSets();
      float f3 = Utils.convertDpToPixel(4.5F);
      boolean bool1 = mChart.isDrawValueAboveBarEnabled();
      int i = 0;
      while (i < mChart.getBarData().getDataSetCount())
      {
        IBarDataSet localIBarDataSet = (IBarDataSet)((List)localObject1).get(i);
        float f1;
        Object localObject2;
        if (!shouldDrawValues(localIBarDataSet))
        {
          f1 = f3;
          localObject2 = localObject1;
        }
        else
        {
          applyValueTextStyle(localIBarDataSet);
          boolean bool2 = mChart.isInverted(localIBarDataSet.getAxisDependency());
          float f6 = Utils.calcTextHeight(mValuePaint, "8");
          float f4;
          if (bool1) {
            f4 = -f3;
          } else {
            f4 = f6 + f3;
          }
          float f5;
          if (bool1) {
            f5 = f6 + f3;
          } else {
            f5 = -f3;
          }
          float f2 = f4;
          f1 = f5;
          if (bool2)
          {
            f2 = -f4 - f6;
            f1 = -f5 - f6;
          }
          Object localObject6 = mBarBuffers[i];
          float f11 = mAnimator.getPhaseY();
          Object localObject4 = MPPointF.getInstance(localIBarDataSet.getIconsOffset());
          x = Utils.convertDpToPixel(x);
          y = Utils.convertDpToPixel(y);
          int j;
          Object localObject3;
          Object localObject5;
          int k;
          int m;
          float f7;
          if (!localIBarDataSet.isStacked())
          {
            j = 0;
            localObject2 = localObject1;
            localObject3 = localObject6;
            localObject1 = localObject4;
            while (j < buffer.length * mAnimator.getPhaseX())
            {
              f5 = (buffer[j] + buffer[(j + 2)]) / 2.0F;
              if (!mViewPortHandler.isInBoundsRight(f5)) {
                break;
              }
              localObject4 = mViewPortHandler;
              localObject5 = buffer;
              k = j + 1;
              if ((((ViewPortHandler)localObject4).isInBoundsY(localObject5[k])) && (mViewPortHandler.isInBoundsLeft(f5)))
              {
                m = j / 4;
                localObject6 = (BarEntry)localIBarDataSet.getEntryForIndex(m);
                f6 = ((BarEntry)localObject6).getY();
                if (localIBarDataSet.isDrawValuesEnabled())
                {
                  localObject4 = localIBarDataSet.getValueFormatter();
                  if (f6 >= 0.0F) {}
                  for (f4 = buffer[k] + f2;; f4 = buffer[(j + 3)] + f1) {
                    break;
                  }
                  drawValue(paramCanvas, (IValueFormatter)localObject4, f6, (Entry)localObject6, i, f5, f4, localIBarDataSet.getValueTextColor(m));
                }
                localObject5 = localObject1;
                localObject4 = localObject3;
                if ((((BarEntry)localObject6).getIcon() != null) && (localIBarDataSet.isDrawIconsEnabled()))
                {
                  localObject6 = ((BarEntry)localObject6).getIcon();
                  if (f6 >= 0.0F) {
                    f4 = buffer[k] + f2;
                  } else {
                    f4 = buffer[(j + 3)] + f1;
                  }
                  f6 = x;
                  f7 = y;
                  Utils.drawImage(paramCanvas, (Drawable)localObject6, (int)(f5 + f6), (int)(f4 + f7), ((Drawable)localObject6).getIntrinsicWidth(), ((Drawable)localObject6).getIntrinsicHeight());
                }
              }
              j += 4;
            }
            f4 = f3;
            bool2 = bool1;
            localObject5 = localObject1;
            k = i;
          }
          else
          {
            localObject3 = localObject4;
            localObject4 = localObject1;
            localObject1 = mChart.getTransformer(localIBarDataSet.getAxisDependency());
            j = 0;
            m = 0;
            f4 = f3;
            bool2 = bool1;
            localObject5 = localObject3;
            k = i;
            localObject2 = localObject4;
            if (j < localIBarDataSet.getEntryCount() * mAnimator.getPhaseX())
            {
              BarEntry localBarEntry = (BarEntry)localIBarDataSet.getEntryForIndex(j);
              localObject2 = localBarEntry.getYVals();
              f7 = (buffer[m] + buffer[(m + 2)]) / 2.0F;
              int i1 = localIBarDataSet.getValueTextColor(j);
              Object localObject7;
              float f8;
              if (localObject2 == null)
              {
                if (!mViewPortHandler.isInBoundsRight(f7))
                {
                  f4 = f3;
                  bool2 = bool1;
                  localObject5 = localObject3;
                  k = i;
                  localObject2 = localObject4;
                  break label1505;
                }
                localObject5 = mViewPortHandler;
                localObject7 = buffer;
                k = m + 1;
                if ((((ViewPortHandler)localObject5).isInBoundsY(localObject7[k])) && (mViewPortHandler.isInBoundsLeft(f7)))
                {
                  if (localIBarDataSet.isDrawValuesEnabled())
                  {
                    localObject5 = localIBarDataSet.getValueFormatter();
                    f5 = localBarEntry.getY();
                    f6 = buffer[k];
                    if (localBarEntry.getY() >= 0.0F) {
                      f4 = f2;
                    } else {
                      f4 = f1;
                    }
                    drawValue(paramCanvas, (IValueFormatter)localObject5, f5, localBarEntry, i, f7, f6 + f4, i1);
                  }
                  if ((localBarEntry.getIcon() == null) || (!localIBarDataSet.isDrawIconsEnabled())) {
                    break label1472;
                  }
                  localObject5 = localBarEntry.getIcon();
                  f5 = buffer[k];
                  if (localBarEntry.getY() >= 0.0F) {
                    f4 = f2;
                  } else {
                    f4 = f1;
                  }
                  f6 = x;
                  f8 = y;
                  Utils.drawImage(paramCanvas, (Drawable)localObject5, (int)(f7 + f6), (int)(f5 + f4 + f8), ((Drawable)localObject5).getIntrinsicWidth(), ((Drawable)localObject5).getIntrinsicHeight());
                  break label1472;
                }
                k = m;
              }
              for (;;)
              {
                m = k;
                break;
                localObject7 = localObject2;
                localObject5 = new float[localObject7.length * 2];
                f4 = -localBarEntry.getNegativeSum();
                f6 = 0.0F;
                int n = 0;
                k = 0;
                while (n < localObject5.length)
                {
                  float f10 = localObject7[k];
                  float f9;
                  if (f10 == 0.0F)
                  {
                    f5 = f10;
                    f8 = f6;
                    f9 = f4;
                    if (f6 == 0.0F) {
                      break label1186;
                    }
                    if (f4 == 0.0F)
                    {
                      f5 = f10;
                      f8 = f6;
                      f9 = f4;
                      break label1186;
                    }
                  }
                  if (f10 >= 0.0F)
                  {
                    f5 = f6 + f10;
                    f8 = f5;
                    f9 = f4;
                  }
                  else
                  {
                    f9 = f4 - f10;
                    f8 = f6;
                    f5 = f4;
                  }
                  label1186:
                  localObject5[(n + 1)] = (f5 * f11);
                  n += 2;
                  k += 1;
                  f6 = f8;
                  f4 = f9;
                }
                ((Transformer)localObject1).pointValuesToPixel((float[])localObject5);
                k = 0;
                while (k < localObject5.length)
                {
                  int i2 = k / 2;
                  f5 = localObject7[i2];
                  if (((f5 == 0.0F) && (f4 == 0.0F) && (f6 > 0.0F)) || (f5 < 0.0F)) {
                    n = 1;
                  } else {
                    n = 0;
                  }
                  f8 = localObject5[(k + 1)];
                  if (n != 0) {
                    f5 = f1;
                  } else {
                    f5 = f2;
                  }
                  f5 = f8 + f5;
                  if (!mViewPortHandler.isInBoundsRight(f7)) {
                    break;
                  }
                  if ((mViewPortHandler.isInBoundsY(f5)) && (mViewPortHandler.isInBoundsLeft(f7)))
                  {
                    if (localIBarDataSet.isDrawValuesEnabled()) {
                      drawValue(paramCanvas, localIBarDataSet.getValueFormatter(), localObject7[i2], localBarEntry, i, f7, f5, i1);
                    }
                    if ((localBarEntry.getIcon() != null) && (localIBarDataSet.isDrawIconsEnabled()))
                    {
                      Drawable localDrawable = localBarEntry.getIcon();
                      Utils.drawImage(paramCanvas, localDrawable, (int)(f7 + x), (int)(f5 + y), localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight());
                    }
                  }
                  k += 2;
                }
                label1472:
                if (localObject2 == null) {
                  k = m + 4;
                } else {
                  k = m + 4 * localObject2.length;
                }
                j += 1;
              }
            }
          }
          label1505:
          f1 = f4;
          bool1 = bool2;
          MPPointF.recycleInstance((MPPointF)localObject5);
          i = k;
        }
        i += 1;
        localObject1 = localObject2;
        f3 = f1;
      }
    }
  }
  
  public void initBuffers()
  {
    BarData localBarData = mChart.getBarData();
    mBarBuffers = new BarBuffer[localBarData.getDataSetCount()];
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
      arrayOfBarBuffer[i] = new BarBuffer(k * 4 * j, localBarData.getDataSetCount(), localIBarDataSet.isStacked());
      i += 1;
    }
  }
  
  protected void prepareBarHighlight(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Transformer paramTransformer)
  {
    mBarRect.set(paramFloat1 - paramFloat4, paramFloat2, paramFloat1 + paramFloat4, paramFloat3);
    paramTransformer.rectToPixelPhase(mBarRect, mAnimator.getPhaseY());
  }
  
  protected void setHighlightDrawPos(Highlight paramHighlight, RectF paramRectF)
  {
    paramHighlight.setDraw(paramRectF.centerX(), top);
  }
}

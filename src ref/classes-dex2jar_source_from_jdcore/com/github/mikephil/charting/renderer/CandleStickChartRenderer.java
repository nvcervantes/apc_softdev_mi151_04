package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.CandleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.Iterator;
import java.util.List;

public class CandleStickChartRenderer
  extends LineScatterCandleRadarRenderer
{
  private float[] mBodyBuffers = new float[4];
  protected CandleDataProvider mChart;
  private float[] mCloseBuffers = new float[4];
  private float[] mOpenBuffers = new float[4];
  private float[] mRangeBuffers = new float[4];
  private float[] mShadowBuffers = new float[8];
  
  public CandleStickChartRenderer(CandleDataProvider paramCandleDataProvider, ChartAnimator paramChartAnimator, ViewPortHandler paramViewPortHandler)
  {
    super(paramChartAnimator, paramViewPortHandler);
    mChart = paramCandleDataProvider;
  }
  
  public void drawData(Canvas paramCanvas)
  {
    Iterator localIterator = mChart.getCandleData().getDataSets().iterator();
    while (localIterator.hasNext())
    {
      ICandleDataSet localICandleDataSet = (ICandleDataSet)localIterator.next();
      if (localICandleDataSet.isVisible()) {
        drawDataSet(paramCanvas, localICandleDataSet);
      }
    }
  }
  
  protected void drawDataSet(Canvas paramCanvas, ICandleDataSet paramICandleDataSet)
  {
    Transformer localTransformer = mChart.getTransformer(paramICandleDataSet.getAxisDependency());
    float f1 = mAnimator.getPhaseY();
    float f2 = paramICandleDataSet.getBarSpace();
    boolean bool = paramICandleDataSet.getShowCandleBar();
    mXBounds.set(mChart, paramICandleDataSet);
    mRenderPaint.setStrokeWidth(paramICandleDataSet.getShadowWidth());
    int j = mXBounds.min;
    while (j <= mXBounds.range + mXBounds.min)
    {
      Object localObject = (CandleEntry)paramICandleDataSet.getEntryForIndex(j);
      if (localObject != null)
      {
        float f3 = ((CandleEntry)localObject).getX();
        float f4 = ((CandleEntry)localObject).getOpen();
        float f5 = ((CandleEntry)localObject).getClose();
        float f6 = ((CandleEntry)localObject).getHigh();
        float f7 = ((CandleEntry)localObject).getLow();
        int i;
        if (bool)
        {
          mShadowBuffers[0] = f3;
          mShadowBuffers[2] = f3;
          mShadowBuffers[4] = f3;
          mShadowBuffers[6] = f3;
          if (f4 > f5)
          {
            mShadowBuffers[1] = (f6 * f1);
            mShadowBuffers[3] = (f4 * f1);
            mShadowBuffers[5] = (f7 * f1);
            mShadowBuffers[7] = (f5 * f1);
          }
          else if (f4 < f5)
          {
            mShadowBuffers[1] = (f6 * f1);
            mShadowBuffers[3] = (f5 * f1);
            mShadowBuffers[5] = (f7 * f1);
            mShadowBuffers[7] = (f4 * f1);
          }
          else
          {
            mShadowBuffers[1] = (f6 * f1);
            mShadowBuffers[3] = (f4 * f1);
            mShadowBuffers[5] = (f7 * f1);
            mShadowBuffers[7] = mShadowBuffers[3];
          }
          localTransformer.pointValuesToPixel(mShadowBuffers);
          if (paramICandleDataSet.getShadowColorSameAsCandle())
          {
            if (f4 > f5)
            {
              localObject = mRenderPaint;
              if (paramICandleDataSet.getDecreasingColor() == 1122867) {
                i = paramICandleDataSet.getColor(j);
              } else {
                i = paramICandleDataSet.getDecreasingColor();
              }
              ((Paint)localObject).setColor(i);
            }
            else if (f4 < f5)
            {
              localObject = mRenderPaint;
              if (paramICandleDataSet.getIncreasingColor() == 1122867) {
                i = paramICandleDataSet.getColor(j);
              } else {
                i = paramICandleDataSet.getIncreasingColor();
              }
              ((Paint)localObject).setColor(i);
            }
            else
            {
              localObject = mRenderPaint;
              if (paramICandleDataSet.getNeutralColor() == 1122867) {
                i = paramICandleDataSet.getColor(j);
              } else {
                i = paramICandleDataSet.getNeutralColor();
              }
              ((Paint)localObject).setColor(i);
            }
          }
          else
          {
            localObject = mRenderPaint;
            if (paramICandleDataSet.getShadowColor() == 1122867) {
              i = paramICandleDataSet.getColor(j);
            } else {
              i = paramICandleDataSet.getShadowColor();
            }
            ((Paint)localObject).setColor(i);
          }
          mRenderPaint.setStyle(Paint.Style.STROKE);
          paramCanvas.drawLines(mShadowBuffers, mRenderPaint);
          mBodyBuffers[0] = (f3 - 0.5F + f2);
          mBodyBuffers[1] = (f5 * f1);
          mBodyBuffers[2] = (f3 + 0.5F - f2);
          mBodyBuffers[3] = (f4 * f1);
          localTransformer.pointValuesToPixel(mBodyBuffers);
          if (f4 > f5)
          {
            if (paramICandleDataSet.getDecreasingColor() == 1122867) {
              mRenderPaint.setColor(paramICandleDataSet.getColor(j));
            } else {
              mRenderPaint.setColor(paramICandleDataSet.getDecreasingColor());
            }
            mRenderPaint.setStyle(paramICandleDataSet.getDecreasingPaintStyle());
            paramCanvas.drawRect(mBodyBuffers[0], mBodyBuffers[3], mBodyBuffers[2], mBodyBuffers[1], mRenderPaint);
          }
          else if (f4 < f5)
          {
            if (paramICandleDataSet.getIncreasingColor() == 1122867) {
              mRenderPaint.setColor(paramICandleDataSet.getColor(j));
            } else {
              mRenderPaint.setColor(paramICandleDataSet.getIncreasingColor());
            }
            mRenderPaint.setStyle(paramICandleDataSet.getIncreasingPaintStyle());
            paramCanvas.drawRect(mBodyBuffers[0], mBodyBuffers[1], mBodyBuffers[2], mBodyBuffers[3], mRenderPaint);
          }
          else
          {
            if (paramICandleDataSet.getNeutralColor() == 1122867) {
              mRenderPaint.setColor(paramICandleDataSet.getColor(j));
            } else {
              mRenderPaint.setColor(paramICandleDataSet.getNeutralColor());
            }
            paramCanvas.drawLine(mBodyBuffers[0], mBodyBuffers[1], mBodyBuffers[2], mBodyBuffers[3], mRenderPaint);
          }
        }
        else
        {
          mRangeBuffers[0] = f3;
          mRangeBuffers[1] = (f6 * f1);
          mRangeBuffers[2] = f3;
          mRangeBuffers[3] = (f7 * f1);
          mOpenBuffers[0] = (f3 - 0.5F + f2);
          localObject = mOpenBuffers;
          f6 = f4 * f1;
          localObject[1] = f6;
          mOpenBuffers[2] = f3;
          mOpenBuffers[3] = f6;
          mCloseBuffers[0] = (0.5F + f3 - f2);
          localObject = mCloseBuffers;
          f6 = f5 * f1;
          localObject[1] = f6;
          mCloseBuffers[2] = f3;
          mCloseBuffers[3] = f6;
          localTransformer.pointValuesToPixel(mRangeBuffers);
          localTransformer.pointValuesToPixel(mOpenBuffers);
          localTransformer.pointValuesToPixel(mCloseBuffers);
          if (f4 > f5)
          {
            if (paramICandleDataSet.getDecreasingColor() == 1122867) {
              i = paramICandleDataSet.getColor(j);
            } else {
              i = paramICandleDataSet.getDecreasingColor();
            }
          }
          else if (f4 < f5)
          {
            if (paramICandleDataSet.getIncreasingColor() == 1122867) {
              i = paramICandleDataSet.getColor(j);
            } else {
              i = paramICandleDataSet.getIncreasingColor();
            }
          }
          else if (paramICandleDataSet.getNeutralColor() == 1122867) {
            i = paramICandleDataSet.getColor(j);
          } else {
            i = paramICandleDataSet.getNeutralColor();
          }
          mRenderPaint.setColor(i);
          paramCanvas.drawLine(mRangeBuffers[0], mRangeBuffers[1], mRangeBuffers[2], mRangeBuffers[3], mRenderPaint);
          paramCanvas.drawLine(mOpenBuffers[0], mOpenBuffers[1], mOpenBuffers[2], mOpenBuffers[3], mRenderPaint);
          paramCanvas.drawLine(mCloseBuffers[0], mCloseBuffers[1], mCloseBuffers[2], mCloseBuffers[3], mRenderPaint);
        }
      }
      j += 1;
    }
  }
  
  public void drawExtras(Canvas paramCanvas) {}
  
  public void drawHighlighted(Canvas paramCanvas, Highlight[] paramArrayOfHighlight)
  {
    CandleData localCandleData = mChart.getCandleData();
    int i = 0;
    int j = paramArrayOfHighlight.length;
    while (i < j)
    {
      Highlight localHighlight = paramArrayOfHighlight[i];
      ICandleDataSet localICandleDataSet = (ICandleDataSet)localCandleData.getDataSetByIndex(localHighlight.getDataSetIndex());
      if ((localICandleDataSet != null) && (localICandleDataSet.isHighlightEnabled()))
      {
        Object localObject = (CandleEntry)localICandleDataSet.getEntryForXValue(localHighlight.getX(), localHighlight.getY());
        if (isInBoundsX((Entry)localObject, localICandleDataSet))
        {
          float f = (((CandleEntry)localObject).getLow() * mAnimator.getPhaseY() + ((CandleEntry)localObject).getHigh() * mAnimator.getPhaseY()) / 2.0F;
          localObject = mChart.getTransformer(localICandleDataSet.getAxisDependency()).getPixelForValues(((CandleEntry)localObject).getX(), f);
          localHighlight.setDraw((float)x, (float)y);
          drawHighlightLines(paramCanvas, (float)x, (float)y, localICandleDataSet);
        }
      }
      i += 1;
    }
  }
  
  public void drawValues(Canvas paramCanvas)
  {
    if (isDrawingValuesAllowed(mChart))
    {
      List localList = mChart.getCandleData().getDataSets();
      int i = 0;
      while (i < localList.size())
      {
        ICandleDataSet localICandleDataSet = (ICandleDataSet)localList.get(i);
        if (shouldDrawValues(localICandleDataSet))
        {
          applyValueTextStyle(localICandleDataSet);
          Object localObject1 = mChart.getTransformer(localICandleDataSet.getAxisDependency());
          mXBounds.set(mChart, localICandleDataSet);
          float[] arrayOfFloat = ((Transformer)localObject1).generateTransformedValuesCandle(localICandleDataSet, mAnimator.getPhaseX(), mAnimator.getPhaseY(), mXBounds.min, mXBounds.max);
          float f1 = Utils.convertDpToPixel(5.0F);
          localObject1 = MPPointF.getInstance(localICandleDataSet.getIconsOffset());
          x = Utils.convertDpToPixel(x);
          y = Utils.convertDpToPixel(y);
          int j = 0;
          while (j < arrayOfFloat.length)
          {
            float f2 = arrayOfFloat[j];
            float f3 = arrayOfFloat[(j + 1)];
            if (!mViewPortHandler.isInBoundsRight(f2)) {
              break;
            }
            if ((mViewPortHandler.isInBoundsLeft(f2)) && (mViewPortHandler.isInBoundsY(f3)))
            {
              int k = j / 2;
              Object localObject3 = (CandleEntry)localICandleDataSet.getEntryForIndex(mXBounds.min + k);
              if (localICandleDataSet.isDrawValuesEnabled()) {
                drawValue(paramCanvas, localICandleDataSet.getValueFormatter(), ((CandleEntry)localObject3).getHigh(), (Entry)localObject3, i, f2, f3 - f1, localICandleDataSet.getValueTextColor(k));
              }
              Object localObject2 = localObject1;
              if ((((CandleEntry)localObject3).getIcon() != null) && (localICandleDataSet.isDrawIconsEnabled()))
              {
                localObject3 = ((CandleEntry)localObject3).getIcon();
                Utils.drawImage(paramCanvas, (Drawable)localObject3, (int)(f2 + x), (int)(f3 + y), ((Drawable)localObject3).getIntrinsicWidth(), ((Drawable)localObject3).getIntrinsicHeight());
              }
            }
            j += 2;
          }
          MPPointF.recycleInstance((MPPointF)localObject1);
        }
        i += 1;
      }
    }
  }
  
  public void initBuffers() {}
}

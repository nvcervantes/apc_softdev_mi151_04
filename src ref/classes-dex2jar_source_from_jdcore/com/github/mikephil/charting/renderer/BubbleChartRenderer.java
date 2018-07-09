package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.Iterator;
import java.util.List;

public class BubbleChartRenderer
  extends BarLineScatterCandleBubbleRenderer
{
  private float[] _hsvBuffer = new float[3];
  protected BubbleDataProvider mChart;
  private float[] pointBuffer = new float[2];
  private float[] sizeBuffer = new float[4];
  
  public BubbleChartRenderer(BubbleDataProvider paramBubbleDataProvider, ChartAnimator paramChartAnimator, ViewPortHandler paramViewPortHandler)
  {
    super(paramChartAnimator, paramViewPortHandler);
    mChart = paramBubbleDataProvider;
    mRenderPaint.setStyle(Paint.Style.FILL);
    mHighlightPaint.setStyle(Paint.Style.STROKE);
    mHighlightPaint.setStrokeWidth(Utils.convertDpToPixel(1.5F));
  }
  
  public void drawData(Canvas paramCanvas)
  {
    Iterator localIterator = mChart.getBubbleData().getDataSets().iterator();
    while (localIterator.hasNext())
    {
      IBubbleDataSet localIBubbleDataSet = (IBubbleDataSet)localIterator.next();
      if (localIBubbleDataSet.isVisible()) {
        drawDataSet(paramCanvas, localIBubbleDataSet);
      }
    }
  }
  
  protected void drawDataSet(Canvas paramCanvas, IBubbleDataSet paramIBubbleDataSet)
  {
    Transformer localTransformer = mChart.getTransformer(paramIBubbleDataSet.getAxisDependency());
    float f1 = mAnimator.getPhaseY();
    mXBounds.set(mChart, paramIBubbleDataSet);
    sizeBuffer[0] = 0.0F;
    sizeBuffer[2] = 1.0F;
    localTransformer.pointValuesToPixel(sizeBuffer);
    boolean bool = paramIBubbleDataSet.isNormalizeSizeEnabled();
    float f2 = Math.abs(sizeBuffer[2] - sizeBuffer[0]);
    f2 = Math.min(Math.abs(mViewPortHandler.contentBottom() - mViewPortHandler.contentTop()), f2);
    int i = mXBounds.min;
    while (i <= mXBounds.range + mXBounds.min)
    {
      BubbleEntry localBubbleEntry = (BubbleEntry)paramIBubbleDataSet.getEntryForIndex(i);
      pointBuffer[0] = localBubbleEntry.getX();
      pointBuffer[1] = (localBubbleEntry.getY() * f1);
      localTransformer.pointValuesToPixel(pointBuffer);
      float f3 = getShapeSize(localBubbleEntry.getSize(), paramIBubbleDataSet.getMaxSize(), f2, bool) / 2.0F;
      if ((mViewPortHandler.isInBoundsTop(pointBuffer[1] + f3)) && (mViewPortHandler.isInBoundsBottom(pointBuffer[1] - f3)) && (mViewPortHandler.isInBoundsLeft(pointBuffer[0] + f3)))
      {
        if (!mViewPortHandler.isInBoundsRight(pointBuffer[0] - f3)) {
          return;
        }
        int j = paramIBubbleDataSet.getColor((int)localBubbleEntry.getX());
        mRenderPaint.setColor(j);
        paramCanvas.drawCircle(pointBuffer[0], pointBuffer[1], f3, mRenderPaint);
      }
      i += 1;
    }
  }
  
  public void drawExtras(Canvas paramCanvas) {}
  
  public void drawHighlighted(Canvas paramCanvas, Highlight[] paramArrayOfHighlight)
  {
    BubbleData localBubbleData = mChart.getBubbleData();
    float f1 = mAnimator.getPhaseY();
    int j = paramArrayOfHighlight.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramArrayOfHighlight[i];
      IBubbleDataSet localIBubbleDataSet = (IBubbleDataSet)localBubbleData.getDataSetByIndex(((Highlight)localObject).getDataSetIndex());
      if ((localIBubbleDataSet != null) && (localIBubbleDataSet.isHighlightEnabled()))
      {
        BubbleEntry localBubbleEntry = (BubbleEntry)localIBubbleDataSet.getEntryForXValue(((Highlight)localObject).getX(), ((Highlight)localObject).getY());
        if ((localBubbleEntry.getY() == ((Highlight)localObject).getY()) && (isInBoundsX(localBubbleEntry, localIBubbleDataSet)))
        {
          Transformer localTransformer = mChart.getTransformer(localIBubbleDataSet.getAxisDependency());
          sizeBuffer[0] = 0.0F;
          sizeBuffer[2] = 1.0F;
          localTransformer.pointValuesToPixel(sizeBuffer);
          boolean bool = localIBubbleDataSet.isNormalizeSizeEnabled();
          float f2 = Math.abs(sizeBuffer[2] - sizeBuffer[0]);
          f2 = Math.min(Math.abs(mViewPortHandler.contentBottom() - mViewPortHandler.contentTop()), f2);
          pointBuffer[0] = localBubbleEntry.getX();
          pointBuffer[1] = (localBubbleEntry.getY() * f1);
          localTransformer.pointValuesToPixel(pointBuffer);
          ((Highlight)localObject).setDraw(pointBuffer[0], pointBuffer[1]);
          f2 = getShapeSize(localBubbleEntry.getSize(), localIBubbleDataSet.getMaxSize(), f2, bool) / 2.0F;
          if ((mViewPortHandler.isInBoundsTop(pointBuffer[1] + f2)) && (mViewPortHandler.isInBoundsBottom(pointBuffer[1] - f2)) && (mViewPortHandler.isInBoundsLeft(pointBuffer[0] + f2)))
          {
            if (!mViewPortHandler.isInBoundsRight(pointBuffer[0] - f2)) {
              return;
            }
            int k = localIBubbleDataSet.getColor((int)localBubbleEntry.getX());
            Color.RGBToHSV(Color.red(k), Color.green(k), Color.blue(k), _hsvBuffer);
            localObject = _hsvBuffer;
            localObject[2] *= 0.5F;
            k = Color.HSVToColor(Color.alpha(k), _hsvBuffer);
            mHighlightPaint.setColor(k);
            mHighlightPaint.setStrokeWidth(localIBubbleDataSet.getHighlightCircleWidth());
            paramCanvas.drawCircle(pointBuffer[0], pointBuffer[1], f2, mHighlightPaint);
          }
        }
      }
      i += 1;
    }
  }
  
  public void drawValues(Canvas paramCanvas)
  {
    Object localObject1 = mChart.getBubbleData();
    if (localObject1 == null) {
      return;
    }
    if (isDrawingValuesAllowed(mChart))
    {
      List localList = ((BubbleData)localObject1).getDataSets();
      float f3 = Utils.calcTextHeight(mValuePaint, "1");
      int i = 0;
      while (i < localList.size())
      {
        IBubbleDataSet localIBubbleDataSet = (IBubbleDataSet)localList.get(i);
        if (shouldDrawValues(localIBubbleDataSet))
        {
          applyValueTextStyle(localIBubbleDataSet);
          float f1 = Math.max(0.0F, Math.min(1.0F, mAnimator.getPhaseX()));
          float f2 = mAnimator.getPhaseY();
          mXBounds.set(mChart, localIBubbleDataSet);
          float[] arrayOfFloat = mChart.getTransformer(localIBubbleDataSet.getAxisDependency()).generateTransformedValuesBubble(localIBubbleDataSet, f2, mXBounds.min, mXBounds.max);
          if (f1 == 1.0F) {
            f1 = f2;
          }
          localObject1 = MPPointF.getInstance(localIBubbleDataSet.getIconsOffset());
          x = Utils.convertDpToPixel(x);
          y = Utils.convertDpToPixel(y);
          int j = 0;
          while (j < arrayOfFloat.length)
          {
            int k = j / 2;
            int m = localIBubbleDataSet.getValueTextColor(mXBounds.min + k);
            m = Color.argb(Math.round(255.0F * f1), Color.red(m), Color.green(m), Color.blue(m));
            f2 = arrayOfFloat[j];
            float f4 = arrayOfFloat[(j + 1)];
            if (!mViewPortHandler.isInBoundsRight(f2)) {
              break;
            }
            if ((mViewPortHandler.isInBoundsLeft(f2)) && (mViewPortHandler.isInBoundsY(f4)))
            {
              Object localObject3 = (BubbleEntry)localIBubbleDataSet.getEntryForIndex(k + mXBounds.min);
              if (localIBubbleDataSet.isDrawValuesEnabled()) {
                drawValue(paramCanvas, localIBubbleDataSet.getValueFormatter(), ((BubbleEntry)localObject3).getSize(), (Entry)localObject3, i, f2, f4 + 0.5F * f3, m);
              }
              Object localObject2 = localObject1;
              if ((((BubbleEntry)localObject3).getIcon() != null) && (localIBubbleDataSet.isDrawIconsEnabled()))
              {
                localObject3 = ((BubbleEntry)localObject3).getIcon();
                Utils.drawImage(paramCanvas, (Drawable)localObject3, (int)(f2 + x), (int)(f4 + y), ((Drawable)localObject3).getIntrinsicWidth(), ((Drawable)localObject3).getIntrinsicHeight());
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
  
  protected float getShapeSize(float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean)
  {
    float f = paramFloat1;
    if (paramBoolean) {
      if (paramFloat2 == 0.0F) {
        f = 1.0F;
      } else {
        f = (float)Math.sqrt(paramFloat1 / paramFloat2);
      }
    }
    return paramFloat3 * f;
  }
  
  public void initBuffers() {}
}

package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.ScatterDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.renderer.scatter.IShapeRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.Iterator;
import java.util.List;

public class ScatterChartRenderer
  extends LineScatterCandleRadarRenderer
{
  protected ScatterDataProvider mChart;
  float[] mPixelBuffer = new float[2];
  
  public ScatterChartRenderer(ScatterDataProvider paramScatterDataProvider, ChartAnimator paramChartAnimator, ViewPortHandler paramViewPortHandler)
  {
    super(paramChartAnimator, paramViewPortHandler);
    mChart = paramScatterDataProvider;
  }
  
  public void drawData(Canvas paramCanvas)
  {
    Iterator localIterator = mChart.getScatterData().getDataSets().iterator();
    while (localIterator.hasNext())
    {
      IScatterDataSet localIScatterDataSet = (IScatterDataSet)localIterator.next();
      if (localIScatterDataSet.isVisible()) {
        drawDataSet(paramCanvas, localIScatterDataSet);
      }
    }
  }
  
  protected void drawDataSet(Canvas paramCanvas, IScatterDataSet paramIScatterDataSet)
  {
    ViewPortHandler localViewPortHandler = mViewPortHandler;
    Transformer localTransformer = mChart.getTransformer(paramIScatterDataSet.getAxisDependency());
    float f = mAnimator.getPhaseY();
    IShapeRenderer localIShapeRenderer = paramIScatterDataSet.getShapeRenderer();
    if (localIShapeRenderer == null)
    {
      Log.i("MISSING", "There's no IShapeRenderer specified for ScatterDataSet");
      return;
    }
    int j = (int)Math.min(Math.ceil(paramIScatterDataSet.getEntryCount() * mAnimator.getPhaseX()), paramIScatterDataSet.getEntryCount());
    int i = 0;
    while (i < j)
    {
      Entry localEntry = paramIScatterDataSet.getEntryForIndex(i);
      mPixelBuffer[0] = localEntry.getX();
      mPixelBuffer[1] = (localEntry.getY() * f);
      localTransformer.pointValuesToPixel(mPixelBuffer);
      if (!localViewPortHandler.isInBoundsRight(mPixelBuffer[0])) {
        return;
      }
      if ((localViewPortHandler.isInBoundsLeft(mPixelBuffer[0])) && (localViewPortHandler.isInBoundsY(mPixelBuffer[1])))
      {
        mRenderPaint.setColor(paramIScatterDataSet.getColor(i / 2));
        localIShapeRenderer.renderShape(paramCanvas, paramIScatterDataSet, mViewPortHandler, mPixelBuffer[0], mPixelBuffer[1], mRenderPaint);
      }
      i += 1;
    }
  }
  
  public void drawExtras(Canvas paramCanvas) {}
  
  public void drawHighlighted(Canvas paramCanvas, Highlight[] paramArrayOfHighlight)
  {
    ScatterData localScatterData = mChart.getScatterData();
    int i = 0;
    int j = paramArrayOfHighlight.length;
    while (i < j)
    {
      Highlight localHighlight = paramArrayOfHighlight[i];
      IScatterDataSet localIScatterDataSet = (IScatterDataSet)localScatterData.getDataSetByIndex(localHighlight.getDataSetIndex());
      if ((localIScatterDataSet != null) && (localIScatterDataSet.isHighlightEnabled()))
      {
        Object localObject = localIScatterDataSet.getEntryForXValue(localHighlight.getX(), localHighlight.getY());
        if (isInBoundsX((Entry)localObject, localIScatterDataSet))
        {
          localObject = mChart.getTransformer(localIScatterDataSet.getAxisDependency()).getPixelForValues(((Entry)localObject).getX(), ((Entry)localObject).getY() * mAnimator.getPhaseY());
          localHighlight.setDraw((float)x, (float)y);
          drawHighlightLines(paramCanvas, (float)x, (float)y, localIScatterDataSet);
        }
      }
      i += 1;
    }
  }
  
  public void drawValues(Canvas paramCanvas)
  {
    Object localObject1 = this;
    if (((ScatterChartRenderer)localObject1).isDrawingValuesAllowed(mChart))
    {
      List localList = mChart.getScatterData().getDataSets();
      int i = 0;
      for (;;)
      {
        localObject1 = this;
        if (i >= mChart.getScatterData().getDataSetCount()) {
          break;
        }
        IScatterDataSet localIScatterDataSet = (IScatterDataSet)localList.get(i);
        if (((ScatterChartRenderer)localObject1).shouldDrawValues(localIScatterDataSet))
        {
          ((ScatterChartRenderer)localObject1).applyValueTextStyle(localIScatterDataSet);
          mXBounds.set(mChart, localIScatterDataSet);
          float[] arrayOfFloat = mChart.getTransformer(localIScatterDataSet.getAxisDependency()).generateTransformedValuesScatter(localIScatterDataSet, mAnimator.getPhaseX(), mAnimator.getPhaseY(), mXBounds.min, mXBounds.max);
          float f = Utils.convertDpToPixel(localIScatterDataSet.getScatterShapeSize());
          localObject1 = MPPointF.getInstance(localIScatterDataSet.getIconsOffset());
          x = Utils.convertDpToPixel(x);
          y = Utils.convertDpToPixel(y);
          int j = 0;
          for (;;)
          {
            Object localObject2 = this;
            if ((j >= arrayOfFloat.length) || (!mViewPortHandler.isInBoundsRight(arrayOfFloat[j]))) {
              break;
            }
            if (mViewPortHandler.isInBoundsLeft(arrayOfFloat[j]))
            {
              Object localObject3 = mViewPortHandler;
              int k = j + 1;
              if (((ViewPortHandler)localObject3).isInBoundsY(arrayOfFloat[k]))
              {
                int m = j / 2;
                localObject3 = localIScatterDataSet.getEntryForIndex(mXBounds.min + m);
                if (localIScatterDataSet.isDrawValuesEnabled()) {
                  ((ScatterChartRenderer)localObject2).drawValue(paramCanvas, localIScatterDataSet.getValueFormatter(), ((Entry)localObject3).getY(), (Entry)localObject3, i, arrayOfFloat[j], arrayOfFloat[k] - f, localIScatterDataSet.getValueTextColor(m + mXBounds.min));
                }
                localObject2 = localObject1;
                if ((((Entry)localObject3).getIcon() != null) && (localIScatterDataSet.isDrawIconsEnabled()))
                {
                  localObject3 = ((Entry)localObject3).getIcon();
                  Utils.drawImage(paramCanvas, (Drawable)localObject3, (int)(arrayOfFloat[j] + x), (int)(arrayOfFloat[k] + y), ((Drawable)localObject3).getIntrinsicWidth(), ((Drawable)localObject3).getIntrinsicHeight());
                }
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

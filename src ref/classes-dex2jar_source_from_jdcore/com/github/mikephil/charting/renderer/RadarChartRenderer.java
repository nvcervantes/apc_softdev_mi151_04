package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.Iterator;
import java.util.List;

public class RadarChartRenderer
  extends LineRadarRenderer
{
  protected RadarChart mChart;
  protected Path mDrawDataSetSurfacePathBuffer = new Path();
  protected Path mDrawHighlightCirclePathBuffer = new Path();
  protected Paint mHighlightCirclePaint;
  protected Paint mWebPaint;
  
  public RadarChartRenderer(RadarChart paramRadarChart, ChartAnimator paramChartAnimator, ViewPortHandler paramViewPortHandler)
  {
    super(paramChartAnimator, paramViewPortHandler);
    mChart = paramRadarChart;
    mHighlightPaint = new Paint(1);
    mHighlightPaint.setStyle(Paint.Style.STROKE);
    mHighlightPaint.setStrokeWidth(2.0F);
    mHighlightPaint.setColor(Color.rgb(255, 187, 115));
    mWebPaint = new Paint(1);
    mWebPaint.setStyle(Paint.Style.STROKE);
    mHighlightCirclePaint = new Paint(1);
  }
  
  public void drawData(Canvas paramCanvas)
  {
    Object localObject = (RadarData)mChart.getData();
    int i = ((IRadarDataSet)((RadarData)localObject).getMaxEntryCountSet()).getEntryCount();
    localObject = ((RadarData)localObject).getDataSets().iterator();
    while (((Iterator)localObject).hasNext())
    {
      IRadarDataSet localIRadarDataSet = (IRadarDataSet)((Iterator)localObject).next();
      if (localIRadarDataSet.isVisible()) {
        drawDataSet(paramCanvas, localIRadarDataSet, i);
      }
    }
  }
  
  protected void drawDataSet(Canvas paramCanvas, IRadarDataSet paramIRadarDataSet, int paramInt)
  {
    float f1 = mAnimator.getPhaseX();
    float f2 = mAnimator.getPhaseY();
    float f3 = mChart.getSliceAngle();
    float f4 = mChart.getFactor();
    MPPointF localMPPointF1 = mChart.getCenterOffsets();
    MPPointF localMPPointF2 = MPPointF.getInstance(0.0F, 0.0F);
    Path localPath = mDrawDataSetSurfacePathBuffer;
    localPath.reset();
    int i = 0;
    int j = 0;
    while (i < paramIRadarDataSet.getEntryCount())
    {
      mRenderPaint.setColor(paramIRadarDataSet.getColor(i));
      Utils.getPosition(localMPPointF1, (((RadarEntry)paramIRadarDataSet.getEntryForIndex(i)).getY() - mChart.getYChartMin()) * f4 * f2, i * f3 * f1 + mChart.getRotationAngle(), localMPPointF2);
      if (!Float.isNaN(x)) {
        if (j == 0)
        {
          localPath.moveTo(x, y);
          j = 1;
        }
        else
        {
          localPath.lineTo(x, y);
        }
      }
      i += 1;
    }
    if (paramIRadarDataSet.getEntryCount() > paramInt) {
      localPath.lineTo(x, y);
    }
    localPath.close();
    if (paramIRadarDataSet.isDrawFilledEnabled())
    {
      Drawable localDrawable = paramIRadarDataSet.getFillDrawable();
      if (localDrawable != null) {
        drawFilledPath(paramCanvas, localPath, localDrawable);
      } else {
        drawFilledPath(paramCanvas, localPath, paramIRadarDataSet.getFillColor(), paramIRadarDataSet.getFillAlpha());
      }
    }
    mRenderPaint.setStrokeWidth(paramIRadarDataSet.getLineWidth());
    mRenderPaint.setStyle(Paint.Style.STROKE);
    if ((!paramIRadarDataSet.isDrawFilledEnabled()) || (paramIRadarDataSet.getFillAlpha() < 255)) {
      paramCanvas.drawPath(localPath, mRenderPaint);
    }
    MPPointF.recycleInstance(localMPPointF1);
    MPPointF.recycleInstance(localMPPointF2);
  }
  
  public void drawExtras(Canvas paramCanvas)
  {
    drawWeb(paramCanvas);
  }
  
  public void drawHighlightCircle(Canvas paramCanvas, MPPointF paramMPPointF, float paramFloat1, float paramFloat2, int paramInt1, int paramInt2, float paramFloat3)
  {
    paramCanvas.save();
    paramFloat2 = Utils.convertDpToPixel(paramFloat2);
    paramFloat1 = Utils.convertDpToPixel(paramFloat1);
    if (paramInt1 != 1122867)
    {
      Path localPath = mDrawHighlightCirclePathBuffer;
      localPath.reset();
      localPath.addCircle(x, y, paramFloat2, Path.Direction.CW);
      if (paramFloat1 > 0.0F) {
        localPath.addCircle(x, y, paramFloat1, Path.Direction.CCW);
      }
      mHighlightCirclePaint.setColor(paramInt1);
      mHighlightCirclePaint.setStyle(Paint.Style.FILL);
      paramCanvas.drawPath(localPath, mHighlightCirclePaint);
    }
    if (paramInt2 != 1122867)
    {
      mHighlightCirclePaint.setColor(paramInt2);
      mHighlightCirclePaint.setStyle(Paint.Style.STROKE);
      mHighlightCirclePaint.setStrokeWidth(Utils.convertDpToPixel(paramFloat3));
      paramCanvas.drawCircle(x, y, paramFloat2, mHighlightCirclePaint);
    }
    paramCanvas.restore();
  }
  
  public void drawHighlighted(Canvas paramCanvas, Highlight[] paramArrayOfHighlight)
  {
    float f1 = mChart.getSliceAngle();
    float f2 = mChart.getFactor();
    MPPointF localMPPointF1 = mChart.getCenterOffsets();
    MPPointF localMPPointF2 = MPPointF.getInstance(0.0F, 0.0F);
    RadarData localRadarData = (RadarData)mChart.getData();
    int j = paramArrayOfHighlight.length;
    int k = 0;
    while (k < j)
    {
      Highlight localHighlight = paramArrayOfHighlight[k];
      IRadarDataSet localIRadarDataSet = (IRadarDataSet)localRadarData.getDataSetByIndex(localHighlight.getDataSetIndex());
      if ((localIRadarDataSet != null) && (localIRadarDataSet.isHighlightEnabled()))
      {
        RadarEntry localRadarEntry = (RadarEntry)localIRadarDataSet.getEntryForIndex((int)localHighlight.getX());
        if (isInBoundsX(localRadarEntry, localIRadarDataSet))
        {
          Utils.getPosition(localMPPointF1, (localRadarEntry.getY() - mChart.getYChartMin()) * f2 * mAnimator.getPhaseY(), localHighlight.getX() * f1 * mAnimator.getPhaseX() + mChart.getRotationAngle(), localMPPointF2);
          localHighlight.setDraw(x, y);
          drawHighlightLines(paramCanvas, x, y, localIRadarDataSet);
          if ((localIRadarDataSet.isDrawHighlightCircleEnabled()) && (!Float.isNaN(x)) && (!Float.isNaN(y)))
          {
            int m = localIRadarDataSet.getHighlightCircleStrokeColor();
            int i = m;
            if (m == 1122867) {
              i = localIRadarDataSet.getColor(0);
            }
            m = i;
            if (localIRadarDataSet.getHighlightCircleStrokeAlpha() < 255) {
              m = ColorTemplate.colorWithAlpha(i, localIRadarDataSet.getHighlightCircleStrokeAlpha());
            }
            drawHighlightCircle(paramCanvas, localMPPointF2, localIRadarDataSet.getHighlightCircleInnerRadius(), localIRadarDataSet.getHighlightCircleOuterRadius(), localIRadarDataSet.getHighlightCircleFillColor(), m, localIRadarDataSet.getHighlightCircleStrokeWidth());
          }
        }
      }
      k += 1;
    }
    MPPointF.recycleInstance(localMPPointF1);
    MPPointF.recycleInstance(localMPPointF2);
  }
  
  public void drawValues(Canvas paramCanvas)
  {
    float f1 = mAnimator.getPhaseX();
    float f4 = mAnimator.getPhaseY();
    float f2 = mChart.getSliceAngle();
    float f5 = mChart.getFactor();
    MPPointF localMPPointF = mChart.getCenterOffsets();
    Object localObject2 = MPPointF.getInstance(0.0F, 0.0F);
    Object localObject1 = MPPointF.getInstance(0.0F, 0.0F);
    float f6 = Utils.convertDpToPixel(5.0F);
    int i = 0;
    while (i < ((RadarData)mChart.getData()).getDataSetCount())
    {
      Object localObject4 = (IRadarDataSet)((RadarData)mChart.getData()).getDataSetByIndex(i);
      if (!shouldDrawValues((IDataSet)localObject4))
      {
        f3 = f2;
        localObject3 = localObject2;
        localObject2 = localObject1;
        f2 = f1;
        f1 = f3;
        localObject1 = localObject3;
      }
      else
      {
        applyValueTextStyle((IDataSet)localObject4);
        localObject3 = MPPointF.getInstance(((IRadarDataSet)localObject4).getIconsOffset());
        x = Utils.convertDpToPixel(x);
        y = Utils.convertDpToPixel(y);
        int j = 0;
        while (j < ((IRadarDataSet)localObject4).getEntryCount())
        {
          RadarEntry localRadarEntry = (RadarEntry)((IRadarDataSet)localObject4).getEntryForIndex(j);
          f3 = localRadarEntry.getY();
          float f7 = mChart.getYChartMin();
          float f8 = j * f2 * f1;
          Utils.getPosition(localMPPointF, (f3 - f7) * f5 * f4, f8 + mChart.getRotationAngle(), (MPPointF)localObject2);
          if (((IRadarDataSet)localObject4).isDrawValuesEnabled()) {
            drawValue(paramCanvas, ((IRadarDataSet)localObject4).getValueFormatter(), localRadarEntry.getY(), localRadarEntry, i, x, y - f6, ((IRadarDataSet)localObject4).getValueTextColor(j));
          }
          if ((localRadarEntry.getIcon() != null) && (((IRadarDataSet)localObject4).isDrawIconsEnabled()))
          {
            Drawable localDrawable = localRadarEntry.getIcon();
            Utils.getPosition(localMPPointF, localRadarEntry.getY() * f5 * f4 + y, f8 + mChart.getRotationAngle(), (MPPointF)localObject1);
            y += x;
            Utils.drawImage(paramCanvas, localDrawable, (int)x, (int)y, localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight());
          }
          j += 1;
        }
        f3 = f1;
        f1 = f2;
        localObject4 = localObject1;
        MPPointF.recycleInstance((MPPointF)localObject3);
        localObject1 = localObject2;
        f2 = f3;
        localObject2 = localObject4;
      }
      i += 1;
      float f3 = f1;
      Object localObject3 = localObject1;
      localObject1 = localObject2;
      f1 = f2;
      f2 = f3;
      localObject2 = localObject3;
    }
    MPPointF.recycleInstance(localMPPointF);
    MPPointF.recycleInstance((MPPointF)localObject2);
    MPPointF.recycleInstance((MPPointF)localObject1);
  }
  
  protected void drawWeb(Canvas paramCanvas)
  {
    float f1 = mChart.getSliceAngle();
    float f2 = mChart.getFactor();
    float f3 = mChart.getRotationAngle();
    MPPointF localMPPointF1 = mChart.getCenterOffsets();
    mWebPaint.setStrokeWidth(mChart.getWebLineWidth());
    mWebPaint.setColor(mChart.getWebColor());
    mWebPaint.setAlpha(mChart.getWebAlpha());
    int j = mChart.getSkipWebLineCount();
    int k = ((IRadarDataSet)((RadarData)mChart.getData()).getMaxEntryCountSet()).getEntryCount();
    MPPointF localMPPointF2 = MPPointF.getInstance(0.0F, 0.0F);
    int i = 0;
    while (i < k)
    {
      Utils.getPosition(localMPPointF1, mChart.getYRange() * f2, i * f1 + f3, localMPPointF2);
      paramCanvas.drawLine(x, y, x, y, mWebPaint);
      i += 1 + j;
    }
    MPPointF.recycleInstance(localMPPointF2);
    mWebPaint.setStrokeWidth(mChart.getWebLineWidthInner());
    mWebPaint.setColor(mChart.getWebColorInner());
    mWebPaint.setAlpha(mChart.getWebAlpha());
    k = mChart.getYAxis().mEntryCount;
    localMPPointF2 = MPPointF.getInstance(0.0F, 0.0F);
    MPPointF localMPPointF3 = MPPointF.getInstance(0.0F, 0.0F);
    i = 0;
    while (i < k)
    {
      j = 0;
      while (j < ((RadarData)mChart.getData()).getEntryCount())
      {
        float f4 = (mChart.getYAxis().mEntries[i] - mChart.getYChartMin()) * f2;
        Utils.getPosition(localMPPointF1, f4, j * f1 + f3, localMPPointF2);
        j += 1;
        Utils.getPosition(localMPPointF1, f4, j * f1 + f3, localMPPointF3);
        paramCanvas.drawLine(x, y, x, y, mWebPaint);
      }
      i += 1;
    }
    MPPointF.recycleInstance(localMPPointF2);
    MPPointF.recycleInstance(localMPPointF3);
  }
  
  public Paint getWebPaint()
  {
    return mWebPaint;
  }
  
  public void initBuffers() {}
}

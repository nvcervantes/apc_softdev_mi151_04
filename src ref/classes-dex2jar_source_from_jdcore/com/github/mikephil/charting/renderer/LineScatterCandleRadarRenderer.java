package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class LineScatterCandleRadarRenderer
  extends BarLineScatterCandleBubbleRenderer
{
  private Path mHighlightLinePath = new Path();
  
  public LineScatterCandleRadarRenderer(ChartAnimator paramChartAnimator, ViewPortHandler paramViewPortHandler)
  {
    super(paramChartAnimator, paramViewPortHandler);
  }
  
  protected void drawHighlightLines(Canvas paramCanvas, float paramFloat1, float paramFloat2, ILineScatterCandleRadarDataSet paramILineScatterCandleRadarDataSet)
  {
    mHighlightPaint.setColor(paramILineScatterCandleRadarDataSet.getHighLightColor());
    mHighlightPaint.setStrokeWidth(paramILineScatterCandleRadarDataSet.getHighlightLineWidth());
    mHighlightPaint.setPathEffect(paramILineScatterCandleRadarDataSet.getDashPathEffectHighlight());
    if (paramILineScatterCandleRadarDataSet.isVerticalHighlightIndicatorEnabled())
    {
      mHighlightLinePath.reset();
      mHighlightLinePath.moveTo(paramFloat1, mViewPortHandler.contentTop());
      mHighlightLinePath.lineTo(paramFloat1, mViewPortHandler.contentBottom());
      paramCanvas.drawPath(mHighlightLinePath, mHighlightPaint);
    }
    if (paramILineScatterCandleRadarDataSet.isHorizontalHighlightIndicatorEnabled())
    {
      mHighlightLinePath.reset();
      mHighlightLinePath.moveTo(mViewPortHandler.contentLeft(), paramFloat2);
      mHighlightLinePath.lineTo(mViewPortHandler.contentRight(), paramFloat2);
      paramCanvas.drawPath(mHighlightLinePath, mHighlightPaint);
    }
  }
}

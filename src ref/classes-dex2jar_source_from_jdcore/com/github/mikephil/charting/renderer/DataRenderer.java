package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class DataRenderer
  extends Renderer
{
  protected ChartAnimator mAnimator;
  protected Paint mDrawPaint;
  protected Paint mHighlightPaint;
  protected Paint mRenderPaint;
  protected Paint mValuePaint;
  
  public DataRenderer(ChartAnimator paramChartAnimator, ViewPortHandler paramViewPortHandler)
  {
    super(paramViewPortHandler);
    mAnimator = paramChartAnimator;
    mRenderPaint = new Paint(1);
    mRenderPaint.setStyle(Paint.Style.FILL);
    mDrawPaint = new Paint(4);
    mValuePaint = new Paint(1);
    mValuePaint.setColor(Color.rgb(63, 63, 63));
    mValuePaint.setTextAlign(Paint.Align.CENTER);
    mValuePaint.setTextSize(Utils.convertDpToPixel(9.0F));
    mHighlightPaint = new Paint(1);
    mHighlightPaint.setStyle(Paint.Style.STROKE);
    mHighlightPaint.setStrokeWidth(2.0F);
    mHighlightPaint.setColor(Color.rgb(255, 187, 115));
  }
  
  protected void applyValueTextStyle(IDataSet paramIDataSet)
  {
    mValuePaint.setTypeface(paramIDataSet.getValueTypeface());
    mValuePaint.setTextSize(paramIDataSet.getValueTextSize());
  }
  
  public abstract void drawData(Canvas paramCanvas);
  
  public abstract void drawExtras(Canvas paramCanvas);
  
  public abstract void drawHighlighted(Canvas paramCanvas, Highlight[] paramArrayOfHighlight);
  
  public void drawValue(Canvas paramCanvas, IValueFormatter paramIValueFormatter, float paramFloat1, Entry paramEntry, int paramInt1, float paramFloat2, float paramFloat3, int paramInt2)
  {
    mValuePaint.setColor(paramInt2);
    paramCanvas.drawText(paramIValueFormatter.getFormattedValue(paramFloat1, paramEntry, paramInt1, mViewPortHandler), paramFloat2, paramFloat3, mValuePaint);
  }
  
  public abstract void drawValues(Canvas paramCanvas);
  
  public Paint getPaintHighlight()
  {
    return mHighlightPaint;
  }
  
  public Paint getPaintRender()
  {
    return mRenderPaint;
  }
  
  public Paint getPaintValues()
  {
    return mValuePaint;
  }
  
  public abstract void initBuffers();
  
  protected boolean isDrawingValuesAllowed(ChartInterface paramChartInterface)
  {
    return paramChartInterface.getData().getEntryCount() < paramChartInterface.getMaxVisibleCount() * mViewPortHandler.getScaleX();
  }
}

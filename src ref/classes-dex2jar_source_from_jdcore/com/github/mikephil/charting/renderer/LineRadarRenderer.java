package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class LineRadarRenderer
  extends LineScatterCandleRadarRenderer
{
  public LineRadarRenderer(ChartAnimator paramChartAnimator, ViewPortHandler paramViewPortHandler)
  {
    super(paramChartAnimator, paramViewPortHandler);
  }
  
  private boolean clipPathSupported()
  {
    return Utils.getSDKInt() >= 18;
  }
  
  protected void drawFilledPath(Canvas paramCanvas, Path paramPath, int paramInt1, int paramInt2)
  {
    paramInt1 = paramInt1 & 0xFFFFFF | paramInt2 << 24;
    if (clipPathSupported())
    {
      paramInt2 = paramCanvas.save();
      paramCanvas.clipPath(paramPath);
      paramCanvas.drawColor(paramInt1);
      paramCanvas.restoreToCount(paramInt2);
      return;
    }
    Paint.Style localStyle = mRenderPaint.getStyle();
    paramInt2 = mRenderPaint.getColor();
    mRenderPaint.setStyle(Paint.Style.FILL);
    mRenderPaint.setColor(paramInt1);
    paramCanvas.drawPath(paramPath, mRenderPaint);
    mRenderPaint.setColor(paramInt2);
    mRenderPaint.setStyle(localStyle);
  }
  
  protected void drawFilledPath(Canvas paramCanvas, Path paramPath, Drawable paramDrawable)
  {
    if (clipPathSupported())
    {
      int i = paramCanvas.save();
      paramCanvas.clipPath(paramPath);
      paramDrawable.setBounds((int)mViewPortHandler.contentLeft(), (int)mViewPortHandler.contentTop(), (int)mViewPortHandler.contentRight(), (int)mViewPortHandler.contentBottom());
      paramDrawable.draw(paramCanvas);
      paramCanvas.restoreToCount(i);
      return;
    }
    paramCanvas = new StringBuilder();
    paramCanvas.append("Fill-drawables not (yet) supported below API level 18, this code was run on API level ");
    paramCanvas.append(Utils.getSDKInt());
    paramCanvas.append(".");
    throw new RuntimeException(paramCanvas.toString());
  }
}

package com.github.mikephil.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class CrossShapeRenderer
  implements IShapeRenderer
{
  public CrossShapeRenderer() {}
  
  public void renderShape(Canvas paramCanvas, IScatterDataSet paramIScatterDataSet, ViewPortHandler paramViewPortHandler, float paramFloat1, float paramFloat2, Paint paramPaint)
  {
    float f = paramIScatterDataSet.getScatterShapeSize() / 2.0F;
    paramPaint.setStyle(Paint.Style.STROKE);
    paramPaint.setStrokeWidth(Utils.convertDpToPixel(1.0F));
    paramCanvas.drawLine(paramFloat1 - f, paramFloat2, paramFloat1 + f, paramFloat2, paramPaint);
    paramCanvas.drawLine(paramFloat1, paramFloat2 - f, paramFloat1, paramFloat2 + f, paramPaint);
  }
}

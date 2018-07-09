package com.github.mikephil.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class SquareShapeRenderer
  implements IShapeRenderer
{
  public SquareShapeRenderer() {}
  
  public void renderShape(Canvas paramCanvas, IScatterDataSet paramIScatterDataSet, ViewPortHandler paramViewPortHandler, float paramFloat1, float paramFloat2, Paint paramPaint)
  {
    float f3 = paramIScatterDataSet.getScatterShapeSize();
    float f4 = f3 / 2.0F;
    float f2 = Utils.convertDpToPixel(paramIScatterDataSet.getScatterShapeHoleRadius());
    float f5 = (f3 - f2 * 2.0F) / 2.0F;
    float f1 = f5 / 2.0F;
    int i = paramIScatterDataSet.getScatterShapeHoleColor();
    if (f3 > 0.0D)
    {
      paramPaint.setStyle(Paint.Style.STROKE);
      paramPaint.setStrokeWidth(f5);
      f3 = paramFloat1 - f2;
      f4 = paramFloat2 - f2;
      paramFloat1 += f2;
      paramFloat2 += f2;
      paramCanvas.drawRect(f3 - f1, f4 - f1, paramFloat1 + f1, paramFloat2 + f1, paramPaint);
      if (i != 1122867)
      {
        paramPaint.setStyle(Paint.Style.FILL);
        paramPaint.setColor(i);
        paramCanvas.drawRect(f3, f4, paramFloat1, paramFloat2, paramPaint);
      }
    }
    else
    {
      paramPaint.setStyle(Paint.Style.FILL);
      paramCanvas.drawRect(paramFloat1 - f4, paramFloat2 - f4, paramFloat1 + f4, paramFloat2 + f4, paramPaint);
    }
  }
}

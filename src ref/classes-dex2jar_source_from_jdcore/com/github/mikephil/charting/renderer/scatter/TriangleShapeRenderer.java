package com.github.mikephil.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class TriangleShapeRenderer
  implements IShapeRenderer
{
  protected Path mTrianglePathBuffer = new Path();
  
  public TriangleShapeRenderer() {}
  
  public void renderShape(Canvas paramCanvas, IScatterDataSet paramIScatterDataSet, ViewPortHandler paramViewPortHandler, float paramFloat1, float paramFloat2, Paint paramPaint)
  {
    float f5 = paramIScatterDataSet.getScatterShapeSize();
    float f4 = f5 / 2.0F;
    float f1 = (f5 - Utils.convertDpToPixel(paramIScatterDataSet.getScatterShapeHoleRadius()) * 2.0F) / 2.0F;
    int i = paramIScatterDataSet.getScatterShapeHoleColor();
    paramPaint.setStyle(Paint.Style.FILL);
    paramIScatterDataSet = mTrianglePathBuffer;
    paramIScatterDataSet.reset();
    float f3 = paramFloat2 - f4;
    paramIScatterDataSet.moveTo(paramFloat1, f3);
    float f2 = paramFloat1 + f4;
    paramFloat2 += f4;
    paramIScatterDataSet.lineTo(f2, paramFloat2);
    f4 = paramFloat1 - f4;
    paramIScatterDataSet.lineTo(f4, paramFloat2);
    double d = f5;
    if (d > 0.0D)
    {
      paramIScatterDataSet.lineTo(paramFloat1, f3);
      f5 = f4 + f1;
      float f6 = paramFloat2 - f1;
      paramIScatterDataSet.moveTo(f5, f6);
      paramIScatterDataSet.lineTo(f2 - f1, f6);
      paramIScatterDataSet.lineTo(paramFloat1, f3 + f1);
      paramIScatterDataSet.lineTo(f5, f6);
    }
    paramIScatterDataSet.close();
    paramCanvas.drawPath(paramIScatterDataSet, paramPaint);
    paramIScatterDataSet.reset();
    if ((d > 0.0D) && (i != 1122867))
    {
      paramPaint.setColor(i);
      paramIScatterDataSet.moveTo(paramFloat1, f3 + f1);
      paramFloat1 = paramFloat2 - f1;
      paramIScatterDataSet.lineTo(f2 - f1, paramFloat1);
      paramIScatterDataSet.lineTo(f4 + f1, paramFloat1);
      paramIScatterDataSet.close();
      paramCanvas.drawPath(paramIScatterDataSet, paramPaint);
      paramIScatterDataSet.reset();
    }
  }
}

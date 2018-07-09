package com.github.mikephil.charting.data.filter;

import android.annotation.TargetApi;
import java.util.Arrays;

public class Approximator
{
  public Approximator() {}
  
  float[] concat(float[]... paramVarArgs)
  {
    int k = paramVarArgs.length;
    int i = 0;
    int j = i;
    while (i < k)
    {
      j += paramVarArgs[i].length;
      i += 1;
    }
    float[] arrayOfFloat1 = new float[j];
    int m = paramVarArgs.length;
    j = 0;
    i = j;
    while (j < m)
    {
      float[] arrayOfFloat2 = paramVarArgs[j];
      int n = arrayOfFloat2.length;
      k = 0;
      while (k < n)
      {
        arrayOfFloat1[i] = arrayOfFloat2[k];
        i += 1;
        k += 1;
      }
      j += 1;
    }
    return arrayOfFloat1;
  }
  
  @TargetApi(9)
  public float[] reduceWithDouglasPeucker(float[] paramArrayOfFloat, float paramFloat)
  {
    float f2 = paramArrayOfFloat[0];
    float f3 = paramArrayOfFloat[1];
    float f1 = 0.0F;
    Object localObject = new Line(f2, f3, paramArrayOfFloat[(paramArrayOfFloat.length - 2)], paramArrayOfFloat[(paramArrayOfFloat.length - 1)]);
    int j = 0;
    int i = 2;
    while (i < paramArrayOfFloat.length - 2)
    {
      f3 = ((Line)localObject).distance(paramArrayOfFloat[i], paramArrayOfFloat[(i + 1)]);
      f2 = f1;
      if (f3 > f1)
      {
        j = i;
        f2 = f3;
      }
      i += 2;
      f1 = f2;
    }
    if (f1 > paramFloat)
    {
      localObject = reduceWithDouglasPeucker(Arrays.copyOfRange(paramArrayOfFloat, 0, j + 2), paramFloat);
      paramArrayOfFloat = reduceWithDouglasPeucker(Arrays.copyOfRange(paramArrayOfFloat, j, paramArrayOfFloat.length), paramFloat);
      return concat(new float[][] { localObject, Arrays.copyOfRange(paramArrayOfFloat, 2, paramArrayOfFloat.length) });
    }
    return ((Line)localObject).getPoints();
  }
  
  private class Line
  {
    private float dx;
    private float dy;
    private float exsy;
    private float length;
    private float[] points;
    private float sxey;
    
    public Line(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
    {
      dx = (paramFloat1 - paramFloat3);
      dy = (paramFloat2 - paramFloat4);
      sxey = (paramFloat1 * paramFloat4);
      exsy = (paramFloat3 * paramFloat2);
      length = ((float)Math.sqrt(dx * dx + dy * dy));
      points = new float[] { paramFloat1, paramFloat2, paramFloat3, paramFloat4 };
    }
    
    public float distance(float paramFloat1, float paramFloat2)
    {
      return Math.abs(dy * paramFloat1 - dx * paramFloat2 + sxey - exsy) / length;
    }
    
    public float[] getPoints()
    {
      return points;
    }
  }
}

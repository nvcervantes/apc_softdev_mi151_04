package com.google.maps.android.heatmaps;

import android.graphics.Color;
import java.util.HashMap;

public class Gradient
{
  private static final int DEFAULT_COLOR_MAP_SIZE = 1000;
  public final int mColorMapSize;
  public int[] mColors;
  public float[] mStartPoints;
  
  public Gradient(int[] paramArrayOfInt, float[] paramArrayOfFloat)
  {
    this(paramArrayOfInt, paramArrayOfFloat, 1000);
  }
  
  public Gradient(int[] paramArrayOfInt, float[] paramArrayOfFloat, int paramInt)
  {
    if (paramArrayOfInt.length != paramArrayOfFloat.length) {
      throw new IllegalArgumentException("colors and startPoints should be same length");
    }
    if (paramArrayOfInt.length == 0) {
      throw new IllegalArgumentException("No colors have been defined");
    }
    int i = 1;
    while (i < paramArrayOfFloat.length)
    {
      if (paramArrayOfFloat[i] <= paramArrayOfFloat[(i - 1)]) {
        throw new IllegalArgumentException("startPoints should be in increasing order");
      }
      i += 1;
    }
    mColorMapSize = paramInt;
    mColors = new int[paramArrayOfInt.length];
    mStartPoints = new float[paramArrayOfFloat.length];
    System.arraycopy(paramArrayOfInt, 0, mColors, 0, paramArrayOfInt.length);
    System.arraycopy(paramArrayOfFloat, 0, mStartPoints, 0, paramArrayOfFloat.length);
  }
  
  private HashMap<Integer, ColorInterval> generateColorIntervals()
  {
    HashMap localHashMap = new HashMap();
    if (mStartPoints[0] != 0.0F) {
      localHashMap.put(Integer.valueOf(0), new ColorInterval(Color.argb(0, Color.red(mColors[0]), Color.green(mColors[0]), Color.blue(mColors[0])), mColors[0], mColorMapSize * mStartPoints[0], null));
    }
    int i = 1;
    while (i < mColors.length)
    {
      float f = mColorMapSize;
      float[] arrayOfFloat = mStartPoints;
      int j = i - 1;
      localHashMap.put(Integer.valueOf((int)(f * arrayOfFloat[j])), new ColorInterval(mColors[j], mColors[i], mColorMapSize * (mStartPoints[i] - mStartPoints[j]), null));
      i += 1;
    }
    if (mStartPoints[(mStartPoints.length - 1)] != 1.0F)
    {
      i = mStartPoints.length - 1;
      localHashMap.put(Integer.valueOf((int)(mColorMapSize * mStartPoints[i])), new ColorInterval(mColors[i], mColors[i], mColorMapSize * (1.0F - mStartPoints[i]), null));
    }
    return localHashMap;
  }
  
  static int interpolateColor(int paramInt1, int paramInt2, float paramFloat)
  {
    int i = (int)((Color.alpha(paramInt2) - Color.alpha(paramInt1)) * paramFloat + Color.alpha(paramInt1));
    float[] arrayOfFloat1 = new float[3];
    Color.RGBToHSV(Color.red(paramInt1), Color.green(paramInt1), Color.blue(paramInt1), arrayOfFloat1);
    float[] arrayOfFloat2 = new float[3];
    Color.RGBToHSV(Color.red(paramInt2), Color.green(paramInt2), Color.blue(paramInt2), arrayOfFloat2);
    paramInt1 = 0;
    if (arrayOfFloat1[0] - arrayOfFloat2[0] > 180.0F) {
      arrayOfFloat2[0] += 360.0F;
    } else if (arrayOfFloat2[0] - arrayOfFloat1[0] > 180.0F) {
      arrayOfFloat1[0] += 360.0F;
    }
    float[] arrayOfFloat3 = new float[3];
    while (paramInt1 < 3)
    {
      arrayOfFloat3[paramInt1] = ((arrayOfFloat2[paramInt1] - arrayOfFloat1[paramInt1]) * paramFloat + arrayOfFloat1[paramInt1]);
      paramInt1 += 1;
    }
    return Color.HSVToColor(i, arrayOfFloat3);
  }
  
  int[] generateColorMap(double paramDouble)
  {
    HashMap localHashMap = generateColorIntervals();
    int[] arrayOfInt = new int[mColorMapSize];
    int k = 0;
    ColorInterval localColorInterval = (ColorInterval)localHashMap.get(Integer.valueOf(0));
    int j = 0;
    int i = j;
    while (i < mColorMapSize)
    {
      if (localHashMap.containsKey(Integer.valueOf(i)))
      {
        localColorInterval = (ColorInterval)localHashMap.get(Integer.valueOf(i));
        j = i;
      }
      float f = (i - j) / duration;
      arrayOfInt[i] = interpolateColor(color1, color2, f);
      i += 1;
    }
    if (paramDouble != 1.0D)
    {
      i = k;
      while (i < mColorMapSize)
      {
        j = arrayOfInt[i];
        arrayOfInt[i] = Color.argb((int)(Color.alpha(j) * paramDouble), Color.red(j), Color.green(j), Color.blue(j));
        i += 1;
      }
    }
    return arrayOfInt;
  }
  
  private class ColorInterval
  {
    private final int color1;
    private final int color2;
    private final float duration;
    
    private ColorInterval(int paramInt1, int paramInt2, float paramFloat)
    {
      color1 = paramInt1;
      color2 = paramInt2;
      duration = paramFloat;
    }
  }
}

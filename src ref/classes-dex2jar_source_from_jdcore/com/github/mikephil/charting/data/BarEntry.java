package com.github.mikephil.charting.data;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.highlight.Range;

@SuppressLint({"ParcelCreator"})
public class BarEntry
  extends Entry
{
  private float mNegativeSum;
  private float mPositiveSum;
  private Range[] mRanges;
  private float[] mYVals;
  
  public BarEntry(float paramFloat1, float paramFloat2)
  {
    super(paramFloat1, paramFloat2);
  }
  
  public BarEntry(float paramFloat1, float paramFloat2, Drawable paramDrawable)
  {
    super(paramFloat1, paramFloat2, paramDrawable);
  }
  
  public BarEntry(float paramFloat1, float paramFloat2, Drawable paramDrawable, Object paramObject)
  {
    super(paramFloat1, paramFloat2, paramDrawable, paramObject);
  }
  
  public BarEntry(float paramFloat1, float paramFloat2, Object paramObject)
  {
    super(paramFloat1, paramFloat2, paramObject);
  }
  
  public BarEntry(float paramFloat, float[] paramArrayOfFloat)
  {
    super(paramFloat, calcSum(paramArrayOfFloat));
    mYVals = paramArrayOfFloat;
    calcPosNegSum();
    calcRanges();
  }
  
  public BarEntry(float paramFloat, float[] paramArrayOfFloat, Drawable paramDrawable)
  {
    super(paramFloat, calcSum(paramArrayOfFloat), paramDrawable);
    mYVals = paramArrayOfFloat;
    calcPosNegSum();
    calcRanges();
  }
  
  public BarEntry(float paramFloat, float[] paramArrayOfFloat, Drawable paramDrawable, Object paramObject)
  {
    super(paramFloat, calcSum(paramArrayOfFloat), paramDrawable, paramObject);
    mYVals = paramArrayOfFloat;
    calcPosNegSum();
    calcRanges();
  }
  
  public BarEntry(float paramFloat, float[] paramArrayOfFloat, Object paramObject)
  {
    super(paramFloat, calcSum(paramArrayOfFloat), paramObject);
    mYVals = paramArrayOfFloat;
    calcPosNegSum();
    calcRanges();
  }
  
  private void calcPosNegSum()
  {
    if (mYVals == null)
    {
      mNegativeSum = 0.0F;
      mPositiveSum = 0.0F;
      return;
    }
    float[] arrayOfFloat = mYVals;
    int j = arrayOfFloat.length;
    int i = 0;
    float f2 = 0.0F;
    float f1 = f2;
    while (i < j)
    {
      float f3 = arrayOfFloat[i];
      if (f3 <= 0.0F) {
        f2 += Math.abs(f3);
      } else {
        f1 += f3;
      }
      i += 1;
    }
    mNegativeSum = f2;
    mPositiveSum = f1;
  }
  
  private static float calcSum(float[] paramArrayOfFloat)
  {
    float f = 0.0F;
    if (paramArrayOfFloat == null) {
      return 0.0F;
    }
    int j = paramArrayOfFloat.length;
    int i = 0;
    while (i < j)
    {
      f += paramArrayOfFloat[i];
      i += 1;
    }
    return f;
  }
  
  protected void calcRanges()
  {
    float[] arrayOfFloat = getYVals();
    if (arrayOfFloat != null)
    {
      if (arrayOfFloat.length == 0) {
        return;
      }
      mRanges = new Range[arrayOfFloat.length];
      float f2 = -getNegativeSum();
      int i = 0;
      float f1 = 0.0F;
      while (i < mRanges.length)
      {
        float f3 = arrayOfFloat[i];
        Range[] arrayOfRange;
        if (f3 < 0.0F)
        {
          arrayOfRange = mRanges;
          f3 = f2 - f3;
          arrayOfRange[i] = new Range(f2, f3);
          f2 = f3;
        }
        else
        {
          arrayOfRange = mRanges;
          f3 += f1;
          arrayOfRange[i] = new Range(f1, f3);
          f1 = f3;
        }
        i += 1;
      }
      return;
    }
  }
  
  public BarEntry copy()
  {
    BarEntry localBarEntry = new BarEntry(getX(), getY(), getData());
    localBarEntry.setVals(mYVals);
    return localBarEntry;
  }
  
  @Deprecated
  public float getBelowSum(int paramInt)
  {
    return getSumBelow(paramInt);
  }
  
  public float getNegativeSum()
  {
    return mNegativeSum;
  }
  
  public float getPositiveSum()
  {
    return mPositiveSum;
  }
  
  public Range[] getRanges()
  {
    return mRanges;
  }
  
  public float getSumBelow(int paramInt)
  {
    float[] arrayOfFloat = mYVals;
    float f = 0.0F;
    if (arrayOfFloat == null) {
      return 0.0F;
    }
    int i = mYVals.length - 1;
    while ((i > paramInt) && (i >= 0))
    {
      f += mYVals[i];
      i -= 1;
    }
    return f;
  }
  
  public float getY()
  {
    return super.getY();
  }
  
  public float[] getYVals()
  {
    return mYVals;
  }
  
  public boolean isStacked()
  {
    return mYVals != null;
  }
  
  public void setVals(float[] paramArrayOfFloat)
  {
    setY(calcSum(paramArrayOfFloat));
    mYVals = paramArrayOfFloat;
    calcPosNegSum();
    calcRanges();
  }
}

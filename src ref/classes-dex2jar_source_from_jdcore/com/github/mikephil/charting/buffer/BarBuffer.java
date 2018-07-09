package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

public class BarBuffer
  extends AbstractBuffer<IBarDataSet>
{
  protected float mBarWidth = 1.0F;
  protected boolean mContainsStacks = false;
  protected int mDataSetCount = 1;
  protected int mDataSetIndex = 0;
  protected boolean mInverted = false;
  
  public BarBuffer(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    super(paramInt1);
    mDataSetCount = paramInt2;
    mContainsStacks = paramBoolean;
  }
  
  protected void addBar(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    float[] arrayOfFloat = buffer;
    int i = index;
    index = (i + 1);
    arrayOfFloat[i] = paramFloat1;
    arrayOfFloat = buffer;
    i = index;
    index = (i + 1);
    arrayOfFloat[i] = paramFloat2;
    arrayOfFloat = buffer;
    i = index;
    index = (i + 1);
    arrayOfFloat[i] = paramFloat3;
    arrayOfFloat = buffer;
    i = index;
    index = (i + 1);
    arrayOfFloat[i] = paramFloat4;
  }
  
  public void feed(IBarDataSet paramIBarDataSet)
  {
    float f7 = paramIBarDataSet.getEntryCount();
    float f8 = phaseX;
    float f9 = mBarWidth / 2.0F;
    int i = 0;
    while (i < f7 * f8)
    {
      BarEntry localBarEntry = (BarEntry)paramIBarDataSet.getEntryForIndex(i);
      if (localBarEntry != null)
      {
        float f10 = localBarEntry.getX();
        float f1 = localBarEntry.getY();
        float[] arrayOfFloat = localBarEntry.getYVals();
        float f3;
        int j;
        if ((mContainsStacks) && (arrayOfFloat != null))
        {
          f1 = -localBarEntry.getNegativeSum();
          f3 = 0.0F;
          j = 0;
        }
        while (j < arrayOfFloat.length)
        {
          float f2 = arrayOfFloat[j];
          float f6;
          float f4;
          float f5;
          if ((f2 == 0.0F) && ((f3 == 0.0F) || (f1 == 0.0F)))
          {
            f6 = f2;
            f4 = f3;
            f5 = f1;
            f1 = f2;
            f2 = f6;
          }
          else if (f2 >= 0.0F)
          {
            f2 += f3;
            f4 = f2;
            f5 = f1;
            f1 = f3;
          }
          else
          {
            f5 = Math.abs(f2) + f1;
            f2 = Math.abs(f2);
            f4 = f1;
            f6 = f2 + f1;
            f2 = f5;
            f1 = f4;
            f5 = f6;
            f4 = f3;
          }
          if (mInverted)
          {
            if (f1 >= f2) {
              f3 = f1;
            } else {
              f3 = f2;
            }
            if (f1 > f2) {
              f1 = f2;
            }
          }
          else
          {
            if (f1 >= f2) {
              f3 = f1;
            } else {
              f3 = f2;
            }
            if (f1 > f2) {
              f1 = f2;
            }
            f2 = f1;
            f1 = f3;
            f3 = f2;
          }
          addBar(f10 - f9, f1 * phaseY, f10 + f9, f3 * phaseY);
          j += 1;
          f3 = f4;
          f1 = f5;
          continue;
          if (mInverted)
          {
            if (f1 >= 0.0F) {
              f2 = f1;
            } else {
              f2 = 0.0F;
            }
            if (f1 > 0.0F) {
              f1 = 0.0F;
            }
            f3 = f2;
            f2 = f1;
            f1 = f3;
          }
          else
          {
            if (f1 >= 0.0F) {
              f2 = f1;
            } else {
              f2 = 0.0F;
            }
            if (f1 > 0.0F) {
              f1 = 0.0F;
            }
          }
          if (f2 > 0.0F) {
            f2 *= phaseY;
          } else {
            f1 *= phaseY;
          }
          addBar(f10 - f9, f2, f10 + f9, f1);
        }
      }
      i += 1;
    }
    reset();
  }
  
  public void setBarWidth(float paramFloat)
  {
    mBarWidth = paramFloat;
  }
  
  public void setDataSet(int paramInt)
  {
    mDataSetIndex = paramInt;
  }
  
  public void setInverted(boolean paramBoolean)
  {
    mInverted = paramBoolean;
  }
}

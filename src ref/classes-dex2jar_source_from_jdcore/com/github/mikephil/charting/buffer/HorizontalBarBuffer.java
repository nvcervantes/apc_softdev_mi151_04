package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

public class HorizontalBarBuffer
  extends BarBuffer
{
  public HorizontalBarBuffer(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    super(paramInt1, paramInt2, paramBoolean);
  }
  
  public void feed(IBarDataSet paramIBarDataSet)
  {
    float f8 = paramIBarDataSet.getEntryCount();
    float f9 = phaseX;
    float f10 = mBarWidth / 2.0F;
    int i = 0;
    while (i < f8 * f9)
    {
      BarEntry localBarEntry = (BarEntry)paramIBarDataSet.getEntryForIndex(i);
      if (localBarEntry != null)
      {
        float f11 = localBarEntry.getX();
        float f1 = localBarEntry.getY();
        float[] arrayOfFloat = localBarEntry.getYVals();
        float f2;
        int j;
        if ((mContainsStacks) && (arrayOfFloat != null))
        {
          f1 = -localBarEntry.getNegativeSum();
          f2 = 0.0F;
          j = 0;
        }
        while (j < arrayOfFloat.length)
        {
          float f3 = arrayOfFloat[j];
          float f5;
          float f4;
          float f6;
          if (f3 >= 0.0F)
          {
            f3 += f2;
            f5 = f1;
            f4 = f3;
            f1 = f2;
            f2 = f3;
            f3 = f5;
          }
          else
          {
            f6 = Math.abs(f3);
            f3 = Math.abs(f3);
            f4 = f2;
            f5 = f1;
            f3 += f1;
            f2 = f6 + f1;
            f1 = f5;
          }
          float f7;
          if (mInverted)
          {
            if (f1 >= f2) {
              f6 = f1;
            } else {
              f6 = f2;
            }
            f7 = f6;
            f5 = f2;
            if (f1 <= f2)
            {
              f7 = f6;
              f5 = f1;
            }
          }
          else
          {
            if (f1 >= f2) {
              f5 = f1;
            } else {
              f5 = f2;
            }
            f6 = f2;
            if (f1 <= f2) {
              f6 = f1;
            }
            f7 = f6;
          }
          f1 = phaseY;
          addBar(f7 * phaseY, f11 + f10, f5 * f1, f11 - f10);
          j += 1;
          f2 = f4;
          f1 = f3;
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
          addBar(f1, f11 + f10, f2, f11 - f10);
        }
      }
      i += 1;
    }
    reset();
  }
}

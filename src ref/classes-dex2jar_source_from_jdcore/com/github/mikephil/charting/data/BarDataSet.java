package com.github.mikephil.charting.data;

import android.graphics.Color;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import java.util.ArrayList;
import java.util.List;

public class BarDataSet
  extends BarLineScatterCandleBubbleDataSet<BarEntry>
  implements IBarDataSet
{
  private int mBarBorderColor = -16777216;
  private float mBarBorderWidth = 0.0F;
  private int mBarShadowColor = Color.rgb(215, 215, 215);
  private int mEntryCountStacks = 0;
  private int mHighLightAlpha = 120;
  private String[] mStackLabels = { "Stack" };
  private int mStackSize = 1;
  
  public BarDataSet(List<BarEntry> paramList, String paramString)
  {
    super(paramList, paramString);
    mHighLightColor = Color.rgb(0, 0, 0);
    calcStackSize(paramList);
    calcEntryCountIncludingStacks(paramList);
  }
  
  private void calcEntryCountIncludingStacks(List<BarEntry> paramList)
  {
    int i = 0;
    mEntryCountStacks = 0;
    while (i < paramList.size())
    {
      float[] arrayOfFloat = ((BarEntry)paramList.get(i)).getYVals();
      if (arrayOfFloat == null) {
        mEntryCountStacks += 1;
      } else {
        mEntryCountStacks += arrayOfFloat.length;
      }
      i += 1;
    }
  }
  
  private void calcStackSize(List<BarEntry> paramList)
  {
    int i = 0;
    while (i < paramList.size())
    {
      float[] arrayOfFloat = ((BarEntry)paramList.get(i)).getYVals();
      if ((arrayOfFloat != null) && (arrayOfFloat.length > mStackSize)) {
        mStackSize = arrayOfFloat.length;
      }
      i += 1;
    }
  }
  
  protected void calcMinMax(BarEntry paramBarEntry)
  {
    if ((paramBarEntry != null) && (!Float.isNaN(paramBarEntry.getY())))
    {
      if (paramBarEntry.getYVals() == null)
      {
        if (paramBarEntry.getY() < mYMin) {
          mYMin = paramBarEntry.getY();
        }
        if (paramBarEntry.getY() > mYMax) {
          mYMax = paramBarEntry.getY();
        }
      }
      else
      {
        if (-paramBarEntry.getNegativeSum() < mYMin) {
          mYMin = (-paramBarEntry.getNegativeSum());
        }
        if (paramBarEntry.getPositiveSum() > mYMax) {
          mYMax = paramBarEntry.getPositiveSum();
        }
      }
      calcMinMaxX(paramBarEntry);
    }
  }
  
  public DataSet<BarEntry> copy()
  {
    Object localObject = new ArrayList();
    ((List)localObject).clear();
    int i = 0;
    while (i < mValues.size())
    {
      ((List)localObject).add(((BarEntry)mValues.get(i)).copy());
      i += 1;
    }
    localObject = new BarDataSet((List)localObject, getLabel());
    mColors = mColors;
    mStackSize = mStackSize;
    mBarShadowColor = mBarShadowColor;
    mStackLabels = mStackLabels;
    mHighLightColor = mHighLightColor;
    mHighLightAlpha = mHighLightAlpha;
    return localObject;
  }
  
  public int getBarBorderColor()
  {
    return mBarBorderColor;
  }
  
  public float getBarBorderWidth()
  {
    return mBarBorderWidth;
  }
  
  public int getBarShadowColor()
  {
    return mBarShadowColor;
  }
  
  public int getEntryCountStacks()
  {
    return mEntryCountStacks;
  }
  
  public int getHighLightAlpha()
  {
    return mHighLightAlpha;
  }
  
  public String[] getStackLabels()
  {
    return mStackLabels;
  }
  
  public int getStackSize()
  {
    return mStackSize;
  }
  
  public boolean isStacked()
  {
    return mStackSize > 1;
  }
  
  public void setBarBorderColor(int paramInt)
  {
    mBarBorderColor = paramInt;
  }
  
  public void setBarBorderWidth(float paramFloat)
  {
    mBarBorderWidth = paramFloat;
  }
  
  public void setBarShadowColor(int paramInt)
  {
    mBarShadowColor = paramInt;
  }
  
  public void setHighLightAlpha(int paramInt)
  {
    mHighLightAlpha = paramInt;
  }
  
  public void setStackLabels(String[] paramArrayOfString)
  {
    mStackLabels = paramArrayOfString;
  }
}

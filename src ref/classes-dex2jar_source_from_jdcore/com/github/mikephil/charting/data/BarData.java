package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import java.util.Iterator;
import java.util.List;

public class BarData
  extends BarLineScatterCandleBubbleData<IBarDataSet>
{
  private float mBarWidth = 0.85F;
  
  public BarData() {}
  
  public BarData(List<IBarDataSet> paramList)
  {
    super(paramList);
  }
  
  public BarData(IBarDataSet... paramVarArgs)
  {
    super(paramVarArgs);
  }
  
  public float getBarWidth()
  {
    return mBarWidth;
  }
  
  public float getGroupWidth(float paramFloat1, float paramFloat2)
  {
    return mDataSets.size() * (mBarWidth + paramFloat2) + paramFloat1;
  }
  
  public void groupBars(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (mDataSets.size() <= 1) {
      throw new RuntimeException("BarData needs to hold at least 2 BarDataSets to allow grouping.");
    }
    int j = ((IBarDataSet)getMaxEntryCountSet()).getEntryCount();
    float f1 = paramFloat2 / 2.0F;
    float f2 = paramFloat3 / 2.0F;
    float f3 = mBarWidth / 2.0F;
    paramFloat3 = getGroupWidth(paramFloat2, paramFloat3);
    int i = 0;
    while (i < j)
    {
      paramFloat2 = paramFloat1 + f1;
      Iterator localIterator = mDataSets.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (IBarDataSet)localIterator.next();
        paramFloat2 = paramFloat2 + f2 + f3;
        if (i < ((IBarDataSet)localObject).getEntryCount())
        {
          localObject = (BarEntry)((IBarDataSet)localObject).getEntryForIndex(i);
          if (localObject != null) {
            ((BarEntry)localObject).setX(paramFloat2);
          }
        }
        paramFloat2 = paramFloat2 + f3 + f2;
      }
      paramFloat2 += f1;
      float f4 = paramFloat3 - (paramFloat2 - paramFloat1);
      if (f4 <= 0.0F)
      {
        paramFloat1 = paramFloat2;
        if (f4 < 0.0F) {}
      }
      for (;;)
      {
        break;
        paramFloat1 = paramFloat2 + f4;
      }
      i += 1;
    }
    notifyDataChanged();
  }
  
  public void setBarWidth(float paramFloat)
  {
    mBarWidth = paramFloat;
  }
}

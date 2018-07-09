package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import java.util.Iterator;
import java.util.List;

public class BubbleData
  extends BarLineScatterCandleBubbleData<IBubbleDataSet>
{
  public BubbleData() {}
  
  public BubbleData(List<IBubbleDataSet> paramList)
  {
    super(paramList);
  }
  
  public BubbleData(IBubbleDataSet... paramVarArgs)
  {
    super(paramVarArgs);
  }
  
  public void setHighlightCircleWidth(float paramFloat)
  {
    Iterator localIterator = mDataSets.iterator();
    while (localIterator.hasNext()) {
      ((IBubbleDataSet)localIterator.next()).setHighlightCircleWidth(paramFloat);
    }
  }
}

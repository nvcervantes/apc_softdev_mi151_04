package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import java.util.List;

public abstract class BarLineScatterCandleBubbleData<T extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>
  extends ChartData<T>
{
  public BarLineScatterCandleBubbleData() {}
  
  public BarLineScatterCandleBubbleData(List<T> paramList)
  {
    super(paramList);
  }
  
  public BarLineScatterCandleBubbleData(T... paramVarArgs)
  {
    super(paramVarArgs);
  }
}

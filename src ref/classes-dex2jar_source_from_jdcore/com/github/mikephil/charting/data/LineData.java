package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.util.List;

public class LineData
  extends BarLineScatterCandleBubbleData<ILineDataSet>
{
  public LineData() {}
  
  public LineData(List<ILineDataSet> paramList)
  {
    super(paramList);
  }
  
  public LineData(ILineDataSet... paramVarArgs)
  {
    super(paramVarArgs);
  }
}

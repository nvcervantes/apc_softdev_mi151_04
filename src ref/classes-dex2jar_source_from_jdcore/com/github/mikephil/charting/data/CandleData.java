package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import java.util.List;

public class CandleData
  extends BarLineScatterCandleBubbleData<ICandleDataSet>
{
  public CandleData() {}
  
  public CandleData(List<ICandleDataSet> paramList)
  {
    super(paramList);
  }
  
  public CandleData(ICandleDataSet... paramVarArgs)
  {
    super(paramVarArgs);
  }
}

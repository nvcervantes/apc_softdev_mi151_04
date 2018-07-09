package com.github.mikephil.charting.data;

import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import java.util.Arrays;
import java.util.List;

public class RadarData
  extends ChartData<IRadarDataSet>
{
  private List<String> mLabels;
  
  public RadarData() {}
  
  public RadarData(List<IRadarDataSet> paramList)
  {
    super(paramList);
  }
  
  public RadarData(IRadarDataSet... paramVarArgs)
  {
    super(paramVarArgs);
  }
  
  public Entry getEntryForHighlight(Highlight paramHighlight)
  {
    return ((IRadarDataSet)getDataSetByIndex(paramHighlight.getDataSetIndex())).getEntryForIndex((int)paramHighlight.getX());
  }
  
  public List<String> getLabels()
  {
    return mLabels;
  }
  
  public void setLabels(List<String> paramList)
  {
    mLabels = paramList;
  }
  
  public void setLabels(String... paramVarArgs)
  {
    mLabels = Arrays.asList(paramVarArgs);
  }
}

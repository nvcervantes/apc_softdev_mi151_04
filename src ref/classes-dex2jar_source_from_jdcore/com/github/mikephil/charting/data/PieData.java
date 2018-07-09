package com.github.mikephil.charting.data;

import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import java.util.List;

public class PieData
  extends ChartData<IPieDataSet>
{
  public PieData() {}
  
  public PieData(IPieDataSet paramIPieDataSet)
  {
    super(new IPieDataSet[] { paramIPieDataSet });
  }
  
  public IPieDataSet getDataSet()
  {
    return (IPieDataSet)mDataSets.get(0);
  }
  
  public IPieDataSet getDataSetByIndex(int paramInt)
  {
    if (paramInt == 0) {
      return getDataSet();
    }
    return null;
  }
  
  public IPieDataSet getDataSetByLabel(String paramString, boolean paramBoolean)
  {
    IPieDataSet localIPieDataSet = null;
    if (paramBoolean)
    {
      if (paramString.equalsIgnoreCase(((IPieDataSet)mDataSets.get(0)).getLabel())) {
        return (IPieDataSet)mDataSets.get(0);
      }
    }
    else if (paramString.equals(((IPieDataSet)mDataSets.get(0)).getLabel())) {
      localIPieDataSet = (IPieDataSet)mDataSets.get(0);
    }
    return localIPieDataSet;
  }
  
  public Entry getEntryForHighlight(Highlight paramHighlight)
  {
    return getDataSet().getEntryForIndex((int)paramHighlight.getX());
  }
  
  public float getYValueSum()
  {
    float f = 0.0F;
    int i = 0;
    while (i < getDataSet().getEntryCount())
    {
      f += ((PieEntry)getDataSet().getEntryForIndex(i)).getY();
      i += 1;
    }
    return f;
  }
  
  public void setDataSet(IPieDataSet paramIPieDataSet)
  {
    mDataSets.clear();
    mDataSets.add(paramIPieDataSet);
    notifyDataChanged();
  }
}

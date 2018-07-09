package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;

public class PieHighlighter
  extends PieRadarHighlighter<PieChart>
{
  public PieHighlighter(PieChart paramPieChart)
  {
    super(paramPieChart);
  }
  
  protected Highlight getClosestHighlight(int paramInt, float paramFloat1, float paramFloat2)
  {
    IPieDataSet localIPieDataSet = ((PieData)((PieChart)mChart).getData()).getDataSet();
    Entry localEntry = localIPieDataSet.getEntryForIndex(paramInt);
    return new Highlight(paramInt, localEntry.getY(), paramFloat1, paramFloat2, 0, localIPieDataSet.getAxisDependency());
  }
}

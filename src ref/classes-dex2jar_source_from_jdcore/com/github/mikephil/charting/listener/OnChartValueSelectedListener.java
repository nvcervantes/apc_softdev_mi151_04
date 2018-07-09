package com.github.mikephil.charting.listener;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

public abstract interface OnChartValueSelectedListener
{
  public abstract void onNothingSelected();
  
  public abstract void onValueSelected(Entry paramEntry, Highlight paramHighlight);
}

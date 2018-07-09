package com.github.mikephil.charting.listener;

import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;

public abstract interface OnDrawListener
{
  public abstract void onDrawFinished(DataSet<?> paramDataSet);
  
  public abstract void onEntryAdded(Entry paramEntry);
  
  public abstract void onEntryMoved(Entry paramEntry);
}

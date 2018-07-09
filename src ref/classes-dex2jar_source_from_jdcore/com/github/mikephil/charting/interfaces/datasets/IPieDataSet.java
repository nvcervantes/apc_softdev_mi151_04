package com.github.mikephil.charting.interfaces.datasets;

import com.github.mikephil.charting.data.PieDataSet.ValuePosition;
import com.github.mikephil.charting.data.PieEntry;

public abstract interface IPieDataSet
  extends IDataSet<PieEntry>
{
  public abstract float getSelectionShift();
  
  public abstract float getSliceSpace();
  
  public abstract int getValueLineColor();
  
  public abstract float getValueLinePart1Length();
  
  public abstract float getValueLinePart1OffsetPercentage();
  
  public abstract float getValueLinePart2Length();
  
  public abstract float getValueLineWidth();
  
  public abstract PieDataSet.ValuePosition getXValuePosition();
  
  public abstract PieDataSet.ValuePosition getYValuePosition();
  
  public abstract boolean isAutomaticallyDisableSliceSpacingEnabled();
  
  public abstract boolean isValueLineVariableLength();
}

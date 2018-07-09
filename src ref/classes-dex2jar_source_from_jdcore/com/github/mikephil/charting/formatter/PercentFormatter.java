package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;

public class PercentFormatter
  implements IValueFormatter, IAxisValueFormatter
{
  protected DecimalFormat mFormat;
  
  public PercentFormatter()
  {
    mFormat = new DecimalFormat("###,###,##0.0");
  }
  
  public PercentFormatter(DecimalFormat paramDecimalFormat)
  {
    mFormat = paramDecimalFormat;
  }
  
  public int getDecimalDigits()
  {
    return 1;
  }
  
  public String getFormattedValue(float paramFloat, AxisBase paramAxisBase)
  {
    paramAxisBase = new StringBuilder();
    paramAxisBase.append(mFormat.format(paramFloat));
    paramAxisBase.append(" %");
    return paramAxisBase.toString();
  }
  
  public String getFormattedValue(float paramFloat, Entry paramEntry, int paramInt, ViewPortHandler paramViewPortHandler)
  {
    paramEntry = new StringBuilder();
    paramEntry.append(mFormat.format(paramFloat));
    paramEntry.append(" %");
    return paramEntry.toString();
  }
}

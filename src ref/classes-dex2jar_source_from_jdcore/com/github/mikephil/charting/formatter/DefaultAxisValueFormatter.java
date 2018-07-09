package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import java.text.DecimalFormat;

public class DefaultAxisValueFormatter
  implements IAxisValueFormatter
{
  protected int digits;
  protected DecimalFormat mFormat;
  
  public DefaultAxisValueFormatter(int paramInt)
  {
    int i = 0;
    digits = 0;
    digits = paramInt;
    StringBuffer localStringBuffer = new StringBuffer();
    while (i < paramInt)
    {
      if (i == 0) {
        localStringBuffer.append(".");
      }
      localStringBuffer.append("0");
      i += 1;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("###,###,###,##0");
    localStringBuilder.append(localStringBuffer.toString());
    mFormat = new DecimalFormat(localStringBuilder.toString());
  }
  
  public int getDecimalDigits()
  {
    return digits;
  }
  
  public String getFormattedValue(float paramFloat, AxisBase paramAxisBase)
  {
    return mFormat.format(paramFloat);
  }
}

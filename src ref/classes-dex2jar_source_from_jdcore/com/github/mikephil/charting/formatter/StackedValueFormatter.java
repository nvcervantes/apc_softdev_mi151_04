package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;

public class StackedValueFormatter
  implements IValueFormatter
{
  private String mAppendix;
  private boolean mDrawWholeStack;
  private DecimalFormat mFormat;
  
  public StackedValueFormatter(boolean paramBoolean, String paramString, int paramInt)
  {
    mDrawWholeStack = paramBoolean;
    mAppendix = paramString;
    paramString = new StringBuffer();
    int i = 0;
    while (i < paramInt)
    {
      if (i == 0) {
        paramString.append(".");
      }
      paramString.append("0");
      i += 1;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("###,###,###,##0");
    localStringBuilder.append(paramString.toString());
    mFormat = new DecimalFormat(localStringBuilder.toString());
  }
  
  public String getFormattedValue(float paramFloat, Entry paramEntry, int paramInt, ViewPortHandler paramViewPortHandler)
  {
    if ((!mDrawWholeStack) && ((paramEntry instanceof BarEntry)))
    {
      paramEntry = (BarEntry)paramEntry;
      paramViewPortHandler = paramEntry.getYVals();
      if (paramViewPortHandler != null)
      {
        if (paramViewPortHandler[(paramViewPortHandler.length - 1)] == paramFloat)
        {
          paramViewPortHandler = new StringBuilder();
          paramViewPortHandler.append(mFormat.format(paramEntry.getY()));
          paramViewPortHandler.append(mAppendix);
          return paramViewPortHandler.toString();
        }
        return "";
      }
    }
    paramEntry = new StringBuilder();
    paramEntry.append(mFormat.format(paramFloat));
    paramEntry.append(mAppendix);
    return paramEntry.toString();
  }
}

package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import java.util.Collection;

public class IndexAxisValueFormatter
  implements IAxisValueFormatter
{
  private int mValueCount = 0;
  private String[] mValues = new String[0];
  
  public IndexAxisValueFormatter() {}
  
  public IndexAxisValueFormatter(Collection<String> paramCollection)
  {
    if (paramCollection != null) {
      setValues((String[])paramCollection.toArray(new String[paramCollection.size()]));
    }
  }
  
  public IndexAxisValueFormatter(String[] paramArrayOfString)
  {
    if (paramArrayOfString != null) {
      setValues(paramArrayOfString);
    }
  }
  
  public String getFormattedValue(float paramFloat, AxisBase paramAxisBase)
  {
    int i = Math.round(paramFloat);
    if ((i >= 0) && (i < mValueCount) && (i == (int)paramFloat)) {
      return mValues[i];
    }
    return "";
  }
  
  public String[] getValues()
  {
    return mValues;
  }
  
  public void setValues(String[] paramArrayOfString)
  {
    String[] arrayOfString = paramArrayOfString;
    if (paramArrayOfString == null) {
      arrayOfString = new String[0];
    }
    mValues = arrayOfString;
    mValueCount = arrayOfString.length;
  }
}

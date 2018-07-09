package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

public class DefaultFillFormatter
  implements IFillFormatter
{
  public DefaultFillFormatter() {}
  
  public float getFillLinePosition(ILineDataSet paramILineDataSet, LineDataProvider paramLineDataProvider)
  {
    float f1 = paramLineDataProvider.getYChartMax();
    float f2 = paramLineDataProvider.getYChartMin();
    paramLineDataProvider = paramLineDataProvider.getLineData();
    if ((paramILineDataSet.getYMax() > 0.0F) && (paramILineDataSet.getYMin() < 0.0F)) {
      return 0.0F;
    }
    if (paramLineDataProvider.getYMax() > 0.0F) {
      f1 = 0.0F;
    }
    if (paramLineDataProvider.getYMin() < 0.0F) {
      f2 = 0.0F;
    }
    if (paramILineDataSet.getYMin() >= 0.0F) {
      return f2;
    }
    return f1;
  }
}

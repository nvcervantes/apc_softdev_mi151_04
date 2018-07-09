package com.github.mikephil.charting.data;

import android.graphics.Color;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import java.util.List;

public abstract class BarLineScatterCandleBubbleDataSet<T extends Entry>
  extends DataSet<T>
  implements IBarLineScatterCandleBubbleDataSet<T>
{
  protected int mHighLightColor = Color.rgb(255, 187, 115);
  
  public BarLineScatterCandleBubbleDataSet(List<T> paramList, String paramString)
  {
    super(paramList, paramString);
  }
  
  public int getHighLightColor()
  {
    return mHighLightColor;
  }
  
  public void setHighLightColor(int paramInt)
  {
    mHighLightColor = paramInt;
  }
}

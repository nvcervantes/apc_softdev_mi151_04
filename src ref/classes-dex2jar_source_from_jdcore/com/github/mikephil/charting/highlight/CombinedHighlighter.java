package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet.Rounding;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.CombinedDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import java.util.Iterator;
import java.util.List;

public class CombinedHighlighter
  extends ChartHighlighter<CombinedDataProvider>
  implements IHighlighter
{
  protected BarHighlighter barHighlighter;
  
  public CombinedHighlighter(CombinedDataProvider paramCombinedDataProvider, BarDataProvider paramBarDataProvider)
  {
    super(paramCombinedDataProvider);
    if (paramBarDataProvider.getBarData() == null) {
      paramCombinedDataProvider = null;
    } else {
      paramCombinedDataProvider = new BarHighlighter(paramBarDataProvider);
    }
    barHighlighter = paramCombinedDataProvider;
  }
  
  protected List<Highlight> getHighlightsAtXValue(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    mHighlightBuffer.clear();
    List localList = ((CombinedDataProvider)mChart).getCombinedData().getAllData();
    int i = 0;
    while (i < localList.size())
    {
      Object localObject = (ChartData)localList.get(i);
      if ((barHighlighter != null) && ((localObject instanceof BarData)))
      {
        localObject = barHighlighter.getHighlight(paramFloat2, paramFloat3);
        if (localObject != null)
        {
          ((Highlight)localObject).setDataIndex(i);
          mHighlightBuffer.add(localObject);
        }
      }
      else
      {
        int k = ((ChartData)localObject).getDataSetCount();
        int j = 0;
        while (j < k)
        {
          localObject = ((BarLineScatterCandleBubbleData)localList.get(i)).getDataSetByIndex(j);
          if (((IDataSet)localObject).isHighlightEnabled())
          {
            localObject = buildHighlights((IDataSet)localObject, j, paramFloat1, DataSet.Rounding.CLOSEST).iterator();
            while (((Iterator)localObject).hasNext())
            {
              Highlight localHighlight = (Highlight)((Iterator)localObject).next();
              localHighlight.setDataIndex(i);
              mHighlightBuffer.add(localHighlight);
            }
          }
          j += 1;
        }
      }
      i += 1;
    }
    return mHighlightBuffer;
  }
}

package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.DataSet.Rounding;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HorizontalBarHighlighter
  extends BarHighlighter
{
  public HorizontalBarHighlighter(BarDataProvider paramBarDataProvider)
  {
    super(paramBarDataProvider);
  }
  
  protected List<Highlight> buildHighlights(IDataSet paramIDataSet, int paramInt, float paramFloat, DataSet.Rounding paramRounding)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = paramIDataSet.getEntriesForXValue(paramFloat);
    Object localObject1 = localObject2;
    if (((List)localObject2).size() == 0)
    {
      paramRounding = paramIDataSet.getEntryForXValue(paramFloat, NaN.0F, paramRounding);
      localObject1 = localObject2;
      if (paramRounding != null) {
        localObject1 = paramIDataSet.getEntriesForXValue(paramRounding.getX());
      }
    }
    if (((List)localObject1).size() == 0) {
      return localArrayList;
    }
    paramRounding = ((List)localObject1).iterator();
    while (paramRounding.hasNext())
    {
      localObject1 = (Entry)paramRounding.next();
      localObject2 = ((BarDataProvider)mChart).getTransformer(paramIDataSet.getAxisDependency()).getPixelForValues(((Entry)localObject1).getY(), ((Entry)localObject1).getX());
      localArrayList.add(new Highlight(((Entry)localObject1).getX(), ((Entry)localObject1).getY(), (float)x, (float)y, paramInt, paramIDataSet.getAxisDependency()));
    }
    return localArrayList;
  }
  
  protected float getDistance(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    return Math.abs(paramFloat2 - paramFloat4);
  }
  
  public Highlight getHighlight(float paramFloat1, float paramFloat2)
  {
    Object localObject = ((BarDataProvider)mChart).getBarData();
    MPPointD localMPPointD = getValsForTouch(paramFloat2, paramFloat1);
    Highlight localHighlight = getHighlightForX((float)y, paramFloat2, paramFloat1);
    if (localHighlight == null) {
      return null;
    }
    localObject = (IBarDataSet)((BarData)localObject).getDataSetByIndex(localHighlight.getDataSetIndex());
    if (((IBarDataSet)localObject).isStacked()) {
      return getStackedHighlight(localHighlight, (IBarDataSet)localObject, (float)y, (float)x);
    }
    MPPointD.recycleInstance(localMPPointD);
    return localHighlight;
  }
}

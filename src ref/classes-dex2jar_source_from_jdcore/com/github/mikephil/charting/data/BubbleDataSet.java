package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class BubbleDataSet
  extends BarLineScatterCandleBubbleDataSet<BubbleEntry>
  implements IBubbleDataSet
{
  private float mHighlightCircleWidth = 2.5F;
  protected float mMaxSize;
  protected boolean mNormalizeSize = true;
  
  public BubbleDataSet(List<BubbleEntry> paramList, String paramString)
  {
    super(paramList, paramString);
  }
  
  protected void calcMinMax(BubbleEntry paramBubbleEntry)
  {
    super.calcMinMax(paramBubbleEntry);
    float f = paramBubbleEntry.getSize();
    if (f > mMaxSize) {
      mMaxSize = f;
    }
  }
  
  public DataSet<BubbleEntry> copy()
  {
    Object localObject = new ArrayList();
    int i = 0;
    while (i < mValues.size())
    {
      ((List)localObject).add(((BubbleEntry)mValues.get(i)).copy());
      i += 1;
    }
    localObject = new BubbleDataSet((List)localObject, getLabel());
    mColors = mColors;
    mHighLightColor = mHighLightColor;
    return localObject;
  }
  
  public float getHighlightCircleWidth()
  {
    return mHighlightCircleWidth;
  }
  
  public float getMaxSize()
  {
    return mMaxSize;
  }
  
  public boolean isNormalizeSizeEnabled()
  {
    return mNormalizeSize;
  }
  
  public void setHighlightCircleWidth(float paramFloat)
  {
    mHighlightCircleWidth = Utils.convertDpToPixel(paramFloat);
  }
  
  public void setNormalizeSizeEnabled(boolean paramBoolean)
  {
    mNormalizeSize = paramBoolean;
  }
}

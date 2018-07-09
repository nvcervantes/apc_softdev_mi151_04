package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import java.util.ArrayList;
import java.util.List;

public class RadarDataSet
  extends LineRadarDataSet<RadarEntry>
  implements IRadarDataSet
{
  protected boolean mDrawHighlightCircleEnabled = false;
  protected int mHighlightCircleFillColor = -1;
  protected float mHighlightCircleInnerRadius = 3.0F;
  protected float mHighlightCircleOuterRadius = 4.0F;
  protected int mHighlightCircleStrokeAlpha = 76;
  protected int mHighlightCircleStrokeColor = 1122867;
  protected float mHighlightCircleStrokeWidth = 2.0F;
  
  public RadarDataSet(List<RadarEntry> paramList, String paramString)
  {
    super(paramList, paramString);
  }
  
  public DataSet<RadarEntry> copy()
  {
    Object localObject = new ArrayList();
    int i = 0;
    while (i < mValues.size())
    {
      ((List)localObject).add(((RadarEntry)mValues.get(i)).copy());
      i += 1;
    }
    localObject = new RadarDataSet((List)localObject, getLabel());
    mColors = mColors;
    mHighLightColor = mHighLightColor;
    return localObject;
  }
  
  public int getHighlightCircleFillColor()
  {
    return mHighlightCircleFillColor;
  }
  
  public float getHighlightCircleInnerRadius()
  {
    return mHighlightCircleInnerRadius;
  }
  
  public float getHighlightCircleOuterRadius()
  {
    return mHighlightCircleOuterRadius;
  }
  
  public int getHighlightCircleStrokeAlpha()
  {
    return mHighlightCircleStrokeAlpha;
  }
  
  public int getHighlightCircleStrokeColor()
  {
    return mHighlightCircleStrokeColor;
  }
  
  public float getHighlightCircleStrokeWidth()
  {
    return mHighlightCircleStrokeWidth;
  }
  
  public boolean isDrawHighlightCircleEnabled()
  {
    return mDrawHighlightCircleEnabled;
  }
  
  public void setDrawHighlightCircleEnabled(boolean paramBoolean)
  {
    mDrawHighlightCircleEnabled = paramBoolean;
  }
  
  public void setHighlightCircleFillColor(int paramInt)
  {
    mHighlightCircleFillColor = paramInt;
  }
  
  public void setHighlightCircleInnerRadius(float paramFloat)
  {
    mHighlightCircleInnerRadius = paramFloat;
  }
  
  public void setHighlightCircleOuterRadius(float paramFloat)
  {
    mHighlightCircleOuterRadius = paramFloat;
  }
  
  public void setHighlightCircleStrokeAlpha(int paramInt)
  {
    mHighlightCircleStrokeAlpha = paramInt;
  }
  
  public void setHighlightCircleStrokeColor(int paramInt)
  {
    mHighlightCircleStrokeColor = paramInt;
  }
  
  public void setHighlightCircleStrokeWidth(float paramFloat)
  {
    mHighlightCircleStrokeWidth = paramFloat;
  }
}

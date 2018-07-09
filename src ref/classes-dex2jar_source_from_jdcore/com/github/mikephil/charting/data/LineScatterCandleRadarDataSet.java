package com.github.mikephil.charting.data;

import android.graphics.DashPathEffect;
import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;

public abstract class LineScatterCandleRadarDataSet<T extends Entry>
  extends BarLineScatterCandleBubbleDataSet<T>
  implements ILineScatterCandleRadarDataSet<T>
{
  protected boolean mDrawHorizontalHighlightIndicator = true;
  protected boolean mDrawVerticalHighlightIndicator = true;
  protected DashPathEffect mHighlightDashPathEffect = null;
  protected float mHighlightLineWidth = 0.5F;
  
  public LineScatterCandleRadarDataSet(List<T> paramList, String paramString)
  {
    super(paramList, paramString);
  }
  
  public void disableDashedHighlightLine()
  {
    mHighlightDashPathEffect = null;
  }
  
  public void enableDashedHighlightLine(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    mHighlightDashPathEffect = new DashPathEffect(new float[] { paramFloat1, paramFloat2 }, paramFloat3);
  }
  
  public DashPathEffect getDashPathEffectHighlight()
  {
    return mHighlightDashPathEffect;
  }
  
  public float getHighlightLineWidth()
  {
    return mHighlightLineWidth;
  }
  
  public boolean isDashedHighlightLineEnabled()
  {
    return mHighlightDashPathEffect != null;
  }
  
  public boolean isHorizontalHighlightIndicatorEnabled()
  {
    return mDrawHorizontalHighlightIndicator;
  }
  
  public boolean isVerticalHighlightIndicatorEnabled()
  {
    return mDrawVerticalHighlightIndicator;
  }
  
  public void setDrawHighlightIndicators(boolean paramBoolean)
  {
    setDrawVerticalHighlightIndicator(paramBoolean);
    setDrawHorizontalHighlightIndicator(paramBoolean);
  }
  
  public void setDrawHorizontalHighlightIndicator(boolean paramBoolean)
  {
    mDrawHorizontalHighlightIndicator = paramBoolean;
  }
  
  public void setDrawVerticalHighlightIndicator(boolean paramBoolean)
  {
    mDrawVerticalHighlightIndicator = paramBoolean;
  }
  
  public void setHighlightLineWidth(float paramFloat)
  {
    mHighlightLineWidth = Utils.convertDpToPixel(paramFloat);
  }
}

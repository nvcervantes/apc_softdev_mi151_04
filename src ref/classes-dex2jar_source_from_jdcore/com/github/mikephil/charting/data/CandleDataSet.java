package com.github.mikephil.charting.data;

import android.graphics.Paint.Style;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class CandleDataSet
  extends LineScatterCandleRadarDataSet<CandleEntry>
  implements ICandleDataSet
{
  private float mBarSpace = 0.1F;
  protected int mDecreasingColor = 1122868;
  protected Paint.Style mDecreasingPaintStyle = Paint.Style.FILL;
  protected int mIncreasingColor = 1122868;
  protected Paint.Style mIncreasingPaintStyle = Paint.Style.STROKE;
  protected int mNeutralColor = 1122868;
  protected int mShadowColor = 1122868;
  private boolean mShadowColorSameAsCandle = false;
  private float mShadowWidth = 3.0F;
  private boolean mShowCandleBar = true;
  
  public CandleDataSet(List<CandleEntry> paramList, String paramString)
  {
    super(paramList, paramString);
  }
  
  protected void calcMinMax(CandleEntry paramCandleEntry)
  {
    if (paramCandleEntry.getLow() < mYMin) {
      mYMin = paramCandleEntry.getLow();
    }
    if (paramCandleEntry.getHigh() > mYMax) {
      mYMax = paramCandleEntry.getHigh();
    }
    calcMinMaxX(paramCandleEntry);
  }
  
  protected void calcMinMaxY(CandleEntry paramCandleEntry)
  {
    if (paramCandleEntry.getHigh() < mYMin) {
      mYMin = paramCandleEntry.getHigh();
    }
    if (paramCandleEntry.getHigh() > mYMax) {
      mYMax = paramCandleEntry.getHigh();
    }
    if (paramCandleEntry.getLow() < mYMin) {
      mYMin = paramCandleEntry.getLow();
    }
    if (paramCandleEntry.getLow() > mYMax) {
      mYMax = paramCandleEntry.getLow();
    }
  }
  
  public DataSet<CandleEntry> copy()
  {
    Object localObject = new ArrayList();
    ((List)localObject).clear();
    int i = 0;
    while (i < mValues.size())
    {
      ((List)localObject).add(((CandleEntry)mValues.get(i)).copy());
      i += 1;
    }
    localObject = new CandleDataSet((List)localObject, getLabel());
    mColors = mColors;
    mShadowWidth = mShadowWidth;
    mShowCandleBar = mShowCandleBar;
    mBarSpace = mBarSpace;
    mHighLightColor = mHighLightColor;
    mIncreasingPaintStyle = mIncreasingPaintStyle;
    mDecreasingPaintStyle = mDecreasingPaintStyle;
    mShadowColor = mShadowColor;
    return localObject;
  }
  
  public float getBarSpace()
  {
    return mBarSpace;
  }
  
  public int getDecreasingColor()
  {
    return mDecreasingColor;
  }
  
  public Paint.Style getDecreasingPaintStyle()
  {
    return mDecreasingPaintStyle;
  }
  
  public int getIncreasingColor()
  {
    return mIncreasingColor;
  }
  
  public Paint.Style getIncreasingPaintStyle()
  {
    return mIncreasingPaintStyle;
  }
  
  public int getNeutralColor()
  {
    return mNeutralColor;
  }
  
  public int getShadowColor()
  {
    return mShadowColor;
  }
  
  public boolean getShadowColorSameAsCandle()
  {
    return mShadowColorSameAsCandle;
  }
  
  public float getShadowWidth()
  {
    return mShadowWidth;
  }
  
  public boolean getShowCandleBar()
  {
    return mShowCandleBar;
  }
  
  public void setBarSpace(float paramFloat)
  {
    float f = paramFloat;
    if (paramFloat < 0.0F) {
      f = 0.0F;
    }
    paramFloat = f;
    if (f > 0.45F) {
      paramFloat = 0.45F;
    }
    mBarSpace = paramFloat;
  }
  
  public void setDecreasingColor(int paramInt)
  {
    mDecreasingColor = paramInt;
  }
  
  public void setDecreasingPaintStyle(Paint.Style paramStyle)
  {
    mDecreasingPaintStyle = paramStyle;
  }
  
  public void setIncreasingColor(int paramInt)
  {
    mIncreasingColor = paramInt;
  }
  
  public void setIncreasingPaintStyle(Paint.Style paramStyle)
  {
    mIncreasingPaintStyle = paramStyle;
  }
  
  public void setNeutralColor(int paramInt)
  {
    mNeutralColor = paramInt;
  }
  
  public void setShadowColor(int paramInt)
  {
    mShadowColor = paramInt;
  }
  
  public void setShadowColorSameAsCandle(boolean paramBoolean)
  {
    mShadowColorSameAsCandle = paramBoolean;
  }
  
  public void setShadowWidth(float paramFloat)
  {
    mShadowWidth = Utils.convertDpToPixel(paramFloat);
  }
  
  public void setShowCandleBar(boolean paramBoolean)
  {
    mShowCandleBar = paramBoolean;
  }
}

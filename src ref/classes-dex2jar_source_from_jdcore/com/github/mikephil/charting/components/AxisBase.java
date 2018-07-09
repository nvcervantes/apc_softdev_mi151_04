package com.github.mikephil.charting.components;

import android.graphics.DashPathEffect;
import android.util.Log;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public abstract class AxisBase
  extends ComponentBase
{
  private int mAxisLineColor = -7829368;
  private DashPathEffect mAxisLineDashPathEffect = null;
  private float mAxisLineWidth = 1.0F;
  public float mAxisMaximum = 0.0F;
  public float mAxisMinimum = 0.0F;
  public float mAxisRange = 0.0F;
  protected IAxisValueFormatter mAxisValueFormatter;
  protected boolean mCenterAxisLabels = false;
  public float[] mCenteredEntries = new float[0];
  protected boolean mCustomAxisMax = false;
  protected boolean mCustomAxisMin = false;
  public int mDecimals;
  protected boolean mDrawAxisLine = true;
  protected boolean mDrawGridLines = true;
  protected boolean mDrawLabels = true;
  protected boolean mDrawLimitLineBehindData = false;
  public float[] mEntries = new float[0];
  public int mEntryCount;
  protected boolean mForceLabels = false;
  protected float mGranularity = 1.0F;
  protected boolean mGranularityEnabled = false;
  private int mGridColor = -7829368;
  private DashPathEffect mGridDashPathEffect = null;
  private float mGridLineWidth = 1.0F;
  private int mLabelCount = 6;
  protected List<LimitLine> mLimitLines = new ArrayList();
  protected float mSpaceMax = 0.0F;
  protected float mSpaceMin = 0.0F;
  
  public AxisBase()
  {
    mTextSize = Utils.convertDpToPixel(10.0F);
    mXOffset = Utils.convertDpToPixel(5.0F);
    mYOffset = Utils.convertDpToPixel(5.0F);
  }
  
  public void addLimitLine(LimitLine paramLimitLine)
  {
    mLimitLines.add(paramLimitLine);
    if (mLimitLines.size() > 6) {
      Log.e("MPAndroiChart", "Warning! You have more than 6 LimitLines on your axis, do you really want that?");
    }
  }
  
  public void calculate(float paramFloat1, float paramFloat2)
  {
    if (mCustomAxisMin) {
      paramFloat1 = mAxisMinimum;
    } else {
      paramFloat1 -= mSpaceMin;
    }
    if (mCustomAxisMax) {
      paramFloat2 = mAxisMaximum;
    } else {
      paramFloat2 += mSpaceMax;
    }
    float f2 = paramFloat1;
    float f1 = paramFloat2;
    if (Math.abs(paramFloat2 - paramFloat1) == 0.0F)
    {
      f1 = paramFloat2 + 1.0F;
      f2 = paramFloat1 - 1.0F;
    }
    mAxisMinimum = f2;
    mAxisMaximum = f1;
    mAxisRange = Math.abs(f1 - f2);
  }
  
  public void disableAxisLineDashedLine()
  {
    mAxisLineDashPathEffect = null;
  }
  
  public void disableGridDashedLine()
  {
    mGridDashPathEffect = null;
  }
  
  public void enableAxisLineDashedLine(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    mAxisLineDashPathEffect = new DashPathEffect(new float[] { paramFloat1, paramFloat2 }, paramFloat3);
  }
  
  public void enableGridDashedLine(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    mGridDashPathEffect = new DashPathEffect(new float[] { paramFloat1, paramFloat2 }, paramFloat3);
  }
  
  public int getAxisLineColor()
  {
    return mAxisLineColor;
  }
  
  public DashPathEffect getAxisLineDashPathEffect()
  {
    return mAxisLineDashPathEffect;
  }
  
  public float getAxisLineWidth()
  {
    return mAxisLineWidth;
  }
  
  public float getAxisMaximum()
  {
    return mAxisMaximum;
  }
  
  public float getAxisMinimum()
  {
    return mAxisMinimum;
  }
  
  public String getFormattedLabel(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < mEntries.length)) {
      return getValueFormatter().getFormattedValue(mEntries[paramInt], this);
    }
    return "";
  }
  
  public float getGranularity()
  {
    return mGranularity;
  }
  
  public int getGridColor()
  {
    return mGridColor;
  }
  
  public DashPathEffect getGridDashPathEffect()
  {
    return mGridDashPathEffect;
  }
  
  public float getGridLineWidth()
  {
    return mGridLineWidth;
  }
  
  public int getLabelCount()
  {
    return mLabelCount;
  }
  
  public List<LimitLine> getLimitLines()
  {
    return mLimitLines;
  }
  
  public String getLongestLabel()
  {
    Object localObject1 = "";
    int i = 0;
    while (i < mEntries.length)
    {
      String str = getFormattedLabel(i);
      Object localObject2 = localObject1;
      if (str != null)
      {
        localObject2 = localObject1;
        if (((String)localObject1).length() < str.length()) {
          localObject2 = str;
        }
      }
      i += 1;
      localObject1 = localObject2;
    }
    return localObject1;
  }
  
  public float getSpaceMax()
  {
    return mSpaceMax;
  }
  
  public float getSpaceMin()
  {
    return mSpaceMin;
  }
  
  public IAxisValueFormatter getValueFormatter()
  {
    if ((mAxisValueFormatter == null) || (((mAxisValueFormatter instanceof DefaultAxisValueFormatter)) && (((DefaultAxisValueFormatter)mAxisValueFormatter).getDecimalDigits() != mDecimals))) {
      mAxisValueFormatter = new DefaultAxisValueFormatter(mDecimals);
    }
    return mAxisValueFormatter;
  }
  
  public boolean isAxisLineDashedLineEnabled()
  {
    return mAxisLineDashPathEffect != null;
  }
  
  public boolean isAxisMaxCustom()
  {
    return mCustomAxisMax;
  }
  
  public boolean isAxisMinCustom()
  {
    return mCustomAxisMin;
  }
  
  public boolean isCenterAxisLabelsEnabled()
  {
    return (mCenterAxisLabels) && (mEntryCount > 0);
  }
  
  public boolean isDrawAxisLineEnabled()
  {
    return mDrawAxisLine;
  }
  
  public boolean isDrawGridLinesEnabled()
  {
    return mDrawGridLines;
  }
  
  public boolean isDrawLabelsEnabled()
  {
    return mDrawLabels;
  }
  
  public boolean isDrawLimitLinesBehindDataEnabled()
  {
    return mDrawLimitLineBehindData;
  }
  
  public boolean isForceLabelsEnabled()
  {
    return mForceLabels;
  }
  
  public boolean isGranularityEnabled()
  {
    return mGranularityEnabled;
  }
  
  public boolean isGridDashedLineEnabled()
  {
    return mGridDashPathEffect != null;
  }
  
  public void removeAllLimitLines()
  {
    mLimitLines.clear();
  }
  
  public void removeLimitLine(LimitLine paramLimitLine)
  {
    mLimitLines.remove(paramLimitLine);
  }
  
  public void resetAxisMaximum()
  {
    mCustomAxisMax = false;
  }
  
  public void resetAxisMinimum()
  {
    mCustomAxisMin = false;
  }
  
  public void setAxisLineColor(int paramInt)
  {
    mAxisLineColor = paramInt;
  }
  
  public void setAxisLineDashedLine(DashPathEffect paramDashPathEffect)
  {
    mAxisLineDashPathEffect = paramDashPathEffect;
  }
  
  public void setAxisLineWidth(float paramFloat)
  {
    mAxisLineWidth = Utils.convertDpToPixel(paramFloat);
  }
  
  @Deprecated
  public void setAxisMaxValue(float paramFloat)
  {
    setAxisMaximum(paramFloat);
  }
  
  public void setAxisMaximum(float paramFloat)
  {
    mCustomAxisMax = true;
    mAxisMaximum = paramFloat;
    mAxisRange = Math.abs(paramFloat - mAxisMinimum);
  }
  
  @Deprecated
  public void setAxisMinValue(float paramFloat)
  {
    setAxisMinimum(paramFloat);
  }
  
  public void setAxisMinimum(float paramFloat)
  {
    mCustomAxisMin = true;
    mAxisMinimum = paramFloat;
    mAxisRange = Math.abs(mAxisMaximum - paramFloat);
  }
  
  public void setCenterAxisLabels(boolean paramBoolean)
  {
    mCenterAxisLabels = paramBoolean;
  }
  
  public void setDrawAxisLine(boolean paramBoolean)
  {
    mDrawAxisLine = paramBoolean;
  }
  
  public void setDrawGridLines(boolean paramBoolean)
  {
    mDrawGridLines = paramBoolean;
  }
  
  public void setDrawLabels(boolean paramBoolean)
  {
    mDrawLabels = paramBoolean;
  }
  
  public void setDrawLimitLinesBehindData(boolean paramBoolean)
  {
    mDrawLimitLineBehindData = paramBoolean;
  }
  
  public void setGranularity(float paramFloat)
  {
    mGranularity = paramFloat;
    mGranularityEnabled = true;
  }
  
  public void setGranularityEnabled(boolean paramBoolean)
  {
    mGranularityEnabled = paramBoolean;
  }
  
  public void setGridColor(int paramInt)
  {
    mGridColor = paramInt;
  }
  
  public void setGridDashedLine(DashPathEffect paramDashPathEffect)
  {
    mGridDashPathEffect = paramDashPathEffect;
  }
  
  public void setGridLineWidth(float paramFloat)
  {
    mGridLineWidth = Utils.convertDpToPixel(paramFloat);
  }
  
  public void setLabelCount(int paramInt)
  {
    int i = paramInt;
    if (paramInt > 25) {
      i = 25;
    }
    paramInt = i;
    if (i < 2) {
      paramInt = 2;
    }
    mLabelCount = paramInt;
    mForceLabels = false;
  }
  
  public void setLabelCount(int paramInt, boolean paramBoolean)
  {
    setLabelCount(paramInt);
    mForceLabels = paramBoolean;
  }
  
  public void setSpaceMax(float paramFloat)
  {
    mSpaceMax = paramFloat;
  }
  
  public void setSpaceMin(float paramFloat)
  {
    mSpaceMin = paramFloat;
  }
  
  public void setValueFormatter(IAxisValueFormatter paramIAxisValueFormatter)
  {
    if (paramIAxisValueFormatter == null)
    {
      mAxisValueFormatter = new DefaultAxisValueFormatter(mDecimals);
      return;
    }
    mAxisValueFormatter = paramIAxisValueFormatter;
  }
}

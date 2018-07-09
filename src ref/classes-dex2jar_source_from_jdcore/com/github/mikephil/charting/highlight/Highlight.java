package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.components.YAxis.AxisDependency;

public class Highlight
{
  private YAxis.AxisDependency axis;
  private int mDataIndex = -1;
  private int mDataSetIndex;
  private float mDrawX;
  private float mDrawY;
  private int mStackIndex = -1;
  private float mX = NaN.0F;
  private float mXPx;
  private float mY = NaN.0F;
  private float mYPx;
  
  public Highlight(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt1, int paramInt2, YAxis.AxisDependency paramAxisDependency)
  {
    this(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramInt1, paramAxisDependency);
    mStackIndex = paramInt2;
  }
  
  public Highlight(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt, YAxis.AxisDependency paramAxisDependency)
  {
    mX = paramFloat1;
    mY = paramFloat2;
    mXPx = paramFloat3;
    mYPx = paramFloat4;
    mDataSetIndex = paramInt;
    axis = paramAxisDependency;
  }
  
  public Highlight(float paramFloat1, float paramFloat2, int paramInt)
  {
    mX = paramFloat1;
    mY = paramFloat2;
    mDataSetIndex = paramInt;
  }
  
  public Highlight(float paramFloat, int paramInt1, int paramInt2)
  {
    this(paramFloat, NaN.0F, paramInt1);
    mStackIndex = paramInt2;
  }
  
  public boolean equalTo(Highlight paramHighlight)
  {
    if (paramHighlight == null) {
      return false;
    }
    return (mDataSetIndex == mDataSetIndex) && (mX == mX) && (mStackIndex == mStackIndex) && (mDataIndex == mDataIndex);
  }
  
  public YAxis.AxisDependency getAxis()
  {
    return axis;
  }
  
  public int getDataIndex()
  {
    return mDataIndex;
  }
  
  public int getDataSetIndex()
  {
    return mDataSetIndex;
  }
  
  public float getDrawX()
  {
    return mDrawX;
  }
  
  public float getDrawY()
  {
    return mDrawY;
  }
  
  public int getStackIndex()
  {
    return mStackIndex;
  }
  
  public float getX()
  {
    return mX;
  }
  
  public float getXPx()
  {
    return mXPx;
  }
  
  public float getY()
  {
    return mY;
  }
  
  public float getYPx()
  {
    return mYPx;
  }
  
  public boolean isStacked()
  {
    return mStackIndex >= 0;
  }
  
  public void setDataIndex(int paramInt)
  {
    mDataIndex = paramInt;
  }
  
  public void setDraw(float paramFloat1, float paramFloat2)
  {
    mDrawX = paramFloat1;
    mDrawY = paramFloat2;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Highlight, x: ");
    localStringBuilder.append(mX);
    localStringBuilder.append(", y: ");
    localStringBuilder.append(mY);
    localStringBuilder.append(", dataSetIndex: ");
    localStringBuilder.append(mDataSetIndex);
    localStringBuilder.append(", stackIndex (only stacked barentry): ");
    localStringBuilder.append(mStackIndex);
    return localStringBuilder.toString();
  }
}

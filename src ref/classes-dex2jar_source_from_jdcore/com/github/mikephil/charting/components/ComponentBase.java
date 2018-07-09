package com.github.mikephil.charting.components;

import android.graphics.Typeface;
import com.github.mikephil.charting.utils.Utils;

public abstract class ComponentBase
{
  protected boolean mEnabled = true;
  protected int mTextColor = -16777216;
  protected float mTextSize = Utils.convertDpToPixel(10.0F);
  protected Typeface mTypeface = null;
  protected float mXOffset = 5.0F;
  protected float mYOffset = 5.0F;
  
  public ComponentBase() {}
  
  public int getTextColor()
  {
    return mTextColor;
  }
  
  public float getTextSize()
  {
    return mTextSize;
  }
  
  public Typeface getTypeface()
  {
    return mTypeface;
  }
  
  public float getXOffset()
  {
    return mXOffset;
  }
  
  public float getYOffset()
  {
    return mYOffset;
  }
  
  public boolean isEnabled()
  {
    return mEnabled;
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    mEnabled = paramBoolean;
  }
  
  public void setTextColor(int paramInt)
  {
    mTextColor = paramInt;
  }
  
  public void setTextSize(float paramFloat)
  {
    float f = paramFloat;
    if (paramFloat > 24.0F) {
      f = 24.0F;
    }
    paramFloat = f;
    if (f < 6.0F) {
      paramFloat = 6.0F;
    }
    mTextSize = Utils.convertDpToPixel(paramFloat);
  }
  
  public void setTypeface(Typeface paramTypeface)
  {
    mTypeface = paramTypeface;
  }
  
  public void setXOffset(float paramFloat)
  {
    mXOffset = Utils.convertDpToPixel(paramFloat);
  }
  
  public void setYOffset(float paramFloat)
  {
    mYOffset = Utils.convertDpToPixel(paramFloat);
  }
}

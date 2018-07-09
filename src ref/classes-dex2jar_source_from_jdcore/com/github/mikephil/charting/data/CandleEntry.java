package com.github.mikephil.charting.data;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;

@SuppressLint({"ParcelCreator"})
public class CandleEntry
  extends Entry
{
  private float mClose = 0.0F;
  private float mOpen = 0.0F;
  private float mShadowHigh = 0.0F;
  private float mShadowLow = 0.0F;
  
  public CandleEntry(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5)
  {
    super(paramFloat1, (paramFloat2 + paramFloat3) / 2.0F);
    mShadowHigh = paramFloat2;
    mShadowLow = paramFloat3;
    mOpen = paramFloat4;
    mClose = paramFloat5;
  }
  
  public CandleEntry(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, Drawable paramDrawable)
  {
    super(paramFloat1, (paramFloat2 + paramFloat3) / 2.0F, paramDrawable);
    mShadowHigh = paramFloat2;
    mShadowLow = paramFloat3;
    mOpen = paramFloat4;
    mClose = paramFloat5;
  }
  
  public CandleEntry(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, Drawable paramDrawable, Object paramObject)
  {
    super(paramFloat1, (paramFloat2 + paramFloat3) / 2.0F, paramDrawable, paramObject);
    mShadowHigh = paramFloat2;
    mShadowLow = paramFloat3;
    mOpen = paramFloat4;
    mClose = paramFloat5;
  }
  
  public CandleEntry(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, Object paramObject)
  {
    super(paramFloat1, (paramFloat2 + paramFloat3) / 2.0F, paramObject);
    mShadowHigh = paramFloat2;
    mShadowLow = paramFloat3;
    mOpen = paramFloat4;
    mClose = paramFloat5;
  }
  
  public CandleEntry copy()
  {
    return new CandleEntry(getX(), mShadowHigh, mShadowLow, mOpen, mClose, getData());
  }
  
  public float getBodyRange()
  {
    return Math.abs(mOpen - mClose);
  }
  
  public float getClose()
  {
    return mClose;
  }
  
  public float getHigh()
  {
    return mShadowHigh;
  }
  
  public float getLow()
  {
    return mShadowLow;
  }
  
  public float getOpen()
  {
    return mOpen;
  }
  
  public float getShadowRange()
  {
    return Math.abs(mShadowHigh - mShadowLow);
  }
  
  public float getY()
  {
    return super.getY();
  }
  
  public void setClose(float paramFloat)
  {
    mClose = paramFloat;
  }
  
  public void setHigh(float paramFloat)
  {
    mShadowHigh = paramFloat;
  }
  
  public void setLow(float paramFloat)
  {
    mShadowLow = paramFloat;
  }
  
  public void setOpen(float paramFloat)
  {
    mOpen = paramFloat;
  }
}

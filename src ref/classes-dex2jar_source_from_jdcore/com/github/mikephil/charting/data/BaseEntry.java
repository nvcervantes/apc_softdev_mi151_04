package com.github.mikephil.charting.data;

import android.graphics.drawable.Drawable;

public abstract class BaseEntry
{
  private Object mData = null;
  private Drawable mIcon = null;
  private float y = 0.0F;
  
  public BaseEntry() {}
  
  public BaseEntry(float paramFloat)
  {
    y = paramFloat;
  }
  
  public BaseEntry(float paramFloat, Drawable paramDrawable)
  {
    this(paramFloat);
    mIcon = paramDrawable;
  }
  
  public BaseEntry(float paramFloat, Drawable paramDrawable, Object paramObject)
  {
    this(paramFloat);
    mIcon = paramDrawable;
    mData = paramObject;
  }
  
  public BaseEntry(float paramFloat, Object paramObject)
  {
    this(paramFloat);
    mData = paramObject;
  }
  
  public Object getData()
  {
    return mData;
  }
  
  public Drawable getIcon()
  {
    return mIcon;
  }
  
  public float getY()
  {
    return y;
  }
  
  public void setData(Object paramObject)
  {
    mData = paramObject;
  }
  
  public void setIcon(Drawable paramDrawable)
  {
    mIcon = paramDrawable;
  }
  
  public void setY(float paramFloat)
  {
    y = paramFloat;
  }
}

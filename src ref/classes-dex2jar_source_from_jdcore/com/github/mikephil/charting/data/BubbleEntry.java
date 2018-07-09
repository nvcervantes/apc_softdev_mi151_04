package com.github.mikephil.charting.data;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;

@SuppressLint({"ParcelCreator"})
public class BubbleEntry
  extends Entry
{
  private float mSize = 0.0F;
  
  public BubbleEntry(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    super(paramFloat1, paramFloat2);
    mSize = paramFloat3;
  }
  
  public BubbleEntry(float paramFloat1, float paramFloat2, float paramFloat3, Drawable paramDrawable)
  {
    super(paramFloat1, paramFloat2, paramDrawable);
    mSize = paramFloat3;
  }
  
  public BubbleEntry(float paramFloat1, float paramFloat2, float paramFloat3, Drawable paramDrawable, Object paramObject)
  {
    super(paramFloat1, paramFloat2, paramDrawable, paramObject);
    mSize = paramFloat3;
  }
  
  public BubbleEntry(float paramFloat1, float paramFloat2, float paramFloat3, Object paramObject)
  {
    super(paramFloat1, paramFloat2, paramObject);
    mSize = paramFloat3;
  }
  
  public BubbleEntry copy()
  {
    return new BubbleEntry(getX(), getY(), mSize, getData());
  }
  
  public float getSize()
  {
    return mSize;
  }
  
  public void setSize(float paramFloat)
  {
    mSize = paramFloat;
  }
}

package com.google.maps.android.ui;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.google.maps.android.R.drawable;

class BubbleDrawable
  extends Drawable
{
  private int mColor = -1;
  private final Drawable mMask;
  private final Drawable mShadow;
  
  public BubbleDrawable(Resources paramResources)
  {
    mMask = paramResources.getDrawable(R.drawable.amu_bubble_mask);
    mShadow = paramResources.getDrawable(R.drawable.amu_bubble_shadow);
  }
  
  public void draw(Canvas paramCanvas)
  {
    mMask.draw(paramCanvas);
    paramCanvas.drawColor(mColor, PorterDuff.Mode.SRC_IN);
    mShadow.draw(paramCanvas);
  }
  
  public int getOpacity()
  {
    return -3;
  }
  
  public boolean getPadding(Rect paramRect)
  {
    return mMask.getPadding(paramRect);
  }
  
  public void setAlpha(int paramInt)
  {
    throw new UnsupportedOperationException();
  }
  
  public void setBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mMask.setBounds(paramInt1, paramInt2, paramInt3, paramInt4);
    mShadow.setBounds(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void setBounds(Rect paramRect)
  {
    mMask.setBounds(paramRect);
    mShadow.setBounds(paramRect);
  }
  
  public void setColor(int paramInt)
  {
    mColor = paramInt;
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    throw new UnsupportedOperationException();
  }
}

package com.google.maps.android.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class RotationLayout
  extends FrameLayout
{
  private int mRotation;
  
  public RotationLayout(Context paramContext)
  {
    super(paramContext);
  }
  
  public RotationLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public RotationLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public void dispatchDraw(Canvas paramCanvas)
  {
    if (mRotation == 0)
    {
      super.dispatchDraw(paramCanvas);
      return;
    }
    if (mRotation == 1)
    {
      paramCanvas.translate(getWidth(), 0.0F);
      paramCanvas.rotate(90.0F, getWidth() / 2, 0.0F);
      paramCanvas.translate(getHeight() / 2, getWidth() / 2);
    }
    else if (mRotation == 2)
    {
      paramCanvas.rotate(180.0F, getWidth() / 2, getHeight() / 2);
    }
    else
    {
      paramCanvas.translate(0.0F, getHeight());
      paramCanvas.rotate(270.0F, getWidth() / 2, 0.0F);
      paramCanvas.translate(getHeight() / 2, -getWidth() / 2);
    }
    super.dispatchDraw(paramCanvas);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if ((mRotation != 1) && (mRotation != 3))
    {
      super.onMeasure(paramInt1, paramInt2);
      return;
    }
    super.onMeasure(paramInt1, paramInt2);
    setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
  }
  
  public void setViewRotation(int paramInt)
  {
    mRotation = ((paramInt + 360) % 360 / 90);
  }
}

package com.google.maps.android.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class SquareTextView
  extends TextView
{
  private int mOffsetLeft = 0;
  private int mOffsetTop = 0;
  
  public SquareTextView(Context paramContext)
  {
    super(paramContext);
  }
  
  public SquareTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public SquareTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public void draw(Canvas paramCanvas)
  {
    paramCanvas.translate(mOffsetLeft / 2, mOffsetTop / 2);
    super.draw(paramCanvas);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    paramInt1 = getMeasuredWidth();
    paramInt2 = getMeasuredHeight();
    int i = Math.max(paramInt1, paramInt2);
    if (paramInt1 > paramInt2)
    {
      mOffsetTop = (paramInt1 - paramInt2);
      mOffsetLeft = 0;
    }
    else
    {
      mOffsetTop = 0;
      mOffsetLeft = (paramInt2 - paramInt1);
    }
    setMeasuredDimension(i, i);
  }
}

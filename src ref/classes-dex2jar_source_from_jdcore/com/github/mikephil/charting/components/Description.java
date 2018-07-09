package com.github.mikephil.charting.components;

import android.graphics.Paint.Align;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

public class Description
  extends ComponentBase
{
  private MPPointF mPosition;
  private Paint.Align mTextAlign = Paint.Align.RIGHT;
  private String text = "Description Label";
  
  public Description()
  {
    mTextSize = Utils.convertDpToPixel(8.0F);
  }
  
  public MPPointF getPosition()
  {
    return mPosition;
  }
  
  public String getText()
  {
    return text;
  }
  
  public Paint.Align getTextAlign()
  {
    return mTextAlign;
  }
  
  public void setPosition(float paramFloat1, float paramFloat2)
  {
    if (mPosition == null)
    {
      mPosition = MPPointF.getInstance(paramFloat1, paramFloat2);
      return;
    }
    mPosition.x = paramFloat1;
    mPosition.y = paramFloat2;
  }
  
  public void setText(String paramString)
  {
    text = paramString;
  }
  
  public void setTextAlign(Paint.Align paramAlign)
  {
    mTextAlign = paramAlign;
  }
}

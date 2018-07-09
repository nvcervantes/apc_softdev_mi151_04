package com.github.mikephil.charting.data;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;

public abstract class LineRadarDataSet<T extends Entry>
  extends LineScatterCandleRadarDataSet<T>
  implements ILineRadarDataSet<T>
{
  private boolean mDrawFilled = false;
  private int mFillAlpha = 85;
  private int mFillColor = Color.rgb(140, 234, 255);
  protected Drawable mFillDrawable;
  private float mLineWidth = 2.5F;
  
  public LineRadarDataSet(List<T> paramList, String paramString)
  {
    super(paramList, paramString);
  }
  
  public int getFillAlpha()
  {
    return mFillAlpha;
  }
  
  public int getFillColor()
  {
    return mFillColor;
  }
  
  public Drawable getFillDrawable()
  {
    return mFillDrawable;
  }
  
  public float getLineWidth()
  {
    return mLineWidth;
  }
  
  public boolean isDrawFilledEnabled()
  {
    return mDrawFilled;
  }
  
  public void setDrawFilled(boolean paramBoolean)
  {
    mDrawFilled = paramBoolean;
  }
  
  public void setFillAlpha(int paramInt)
  {
    mFillAlpha = paramInt;
  }
  
  public void setFillColor(int paramInt)
  {
    mFillColor = paramInt;
    mFillDrawable = null;
  }
  
  @TargetApi(18)
  public void setFillDrawable(Drawable paramDrawable)
  {
    mFillDrawable = paramDrawable;
  }
  
  public void setLineWidth(float paramFloat)
  {
    float f = paramFloat;
    if (paramFloat < 0.0F) {
      f = 0.0F;
    }
    paramFloat = f;
    if (f > 10.0F) {
      paramFloat = 10.0F;
    }
    mLineWidth = Utils.convertDpToPixel(paramFloat);
  }
}

package com.github.mikephil.charting.interfaces.datasets;

import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.data.Entry;

public abstract interface ILineRadarDataSet<T extends Entry>
  extends ILineScatterCandleRadarDataSet<T>
{
  public abstract int getFillAlpha();
  
  public abstract int getFillColor();
  
  public abstract Drawable getFillDrawable();
  
  public abstract float getLineWidth();
  
  public abstract boolean isDrawFilledEnabled();
  
  public abstract void setDrawFilled(boolean paramBoolean);
}

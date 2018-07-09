package com.github.mikephil.charting.interfaces.datasets;

import android.graphics.Paint.Style;
import com.github.mikephil.charting.data.CandleEntry;

public abstract interface ICandleDataSet
  extends ILineScatterCandleRadarDataSet<CandleEntry>
{
  public abstract float getBarSpace();
  
  public abstract int getDecreasingColor();
  
  public abstract Paint.Style getDecreasingPaintStyle();
  
  public abstract int getIncreasingColor();
  
  public abstract Paint.Style getIncreasingPaintStyle();
  
  public abstract int getNeutralColor();
  
  public abstract int getShadowColor();
  
  public abstract boolean getShadowColorSameAsCandle();
  
  public abstract float getShadowWidth();
  
  public abstract boolean getShowCandleBar();
}

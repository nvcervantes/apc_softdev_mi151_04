package com.github.mikephil.charting.interfaces.datasets;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.renderer.scatter.IShapeRenderer;

public abstract interface IScatterDataSet
  extends ILineScatterCandleRadarDataSet<Entry>
{
  public abstract int getScatterShapeHoleColor();
  
  public abstract float getScatterShapeHoleRadius();
  
  public abstract float getScatterShapeSize();
  
  public abstract IShapeRenderer getShapeRenderer();
}

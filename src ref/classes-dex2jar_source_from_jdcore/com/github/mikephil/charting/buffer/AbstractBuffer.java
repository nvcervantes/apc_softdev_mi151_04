package com.github.mikephil.charting.buffer;

public abstract class AbstractBuffer<T>
{
  public final float[] buffer;
  protected int index = 0;
  protected int mFrom = 0;
  protected int mTo = 0;
  protected float phaseX = 1.0F;
  protected float phaseY = 1.0F;
  
  public AbstractBuffer(int paramInt)
  {
    buffer = new float[paramInt];
  }
  
  public abstract void feed(T paramT);
  
  public void limitFrom(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = 0;
    }
    mFrom = i;
  }
  
  public void limitTo(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = 0;
    }
    mTo = i;
  }
  
  public void reset()
  {
    index = 0;
  }
  
  public void setPhases(float paramFloat1, float paramFloat2)
  {
    phaseX = paramFloat1;
    phaseY = paramFloat2;
  }
  
  public int size()
  {
    return buffer.length;
  }
}

package com.github.mikephil.charting.highlight;

public final class Range
{
  public float from;
  public float to;
  
  public Range(float paramFloat1, float paramFloat2)
  {
    from = paramFloat1;
    to = paramFloat2;
  }
  
  public boolean contains(float paramFloat)
  {
    return (paramFloat > from) && (paramFloat <= to);
  }
  
  public boolean isLarger(float paramFloat)
  {
    return paramFloat > to;
  }
  
  public boolean isSmaller(float paramFloat)
  {
    return paramFloat < from;
  }
}

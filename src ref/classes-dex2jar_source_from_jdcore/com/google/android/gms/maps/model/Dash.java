package com.google.android.gms.maps.model;

public final class Dash
  extends PatternItem
{
  public final float length;
  
  public Dash(float paramFloat)
  {
    super(0, Float.valueOf(Math.max(paramFloat, 0.0F)));
    length = Math.max(paramFloat, 0.0F);
  }
  
  public final String toString()
  {
    float f = length;
    StringBuilder localStringBuilder = new StringBuilder(30);
    localStringBuilder.append("[Dash: length=");
    localStringBuilder.append(f);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

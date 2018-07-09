package com.google.android.gms.maps.model;

public final class Gap
  extends PatternItem
{
  public final float length;
  
  public Gap(float paramFloat)
  {
    super(2, Float.valueOf(Math.max(paramFloat, 0.0F)));
    length = Math.max(paramFloat, 0.0F);
  }
  
  public final String toString()
  {
    float f = length;
    StringBuilder localStringBuilder = new StringBuilder(29);
    localStringBuilder.append("[Gap: length=");
    localStringBuilder.append(f);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

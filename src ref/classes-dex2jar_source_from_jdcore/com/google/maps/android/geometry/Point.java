package com.google.maps.android.geometry;

public class Point
{
  public final double x;
  public final double y;
  
  public Point(double paramDouble1, double paramDouble2)
  {
    x = paramDouble1;
    y = paramDouble2;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Point{x=");
    localStringBuilder.append(x);
    localStringBuilder.append(", y=");
    localStringBuilder.append(y);
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}

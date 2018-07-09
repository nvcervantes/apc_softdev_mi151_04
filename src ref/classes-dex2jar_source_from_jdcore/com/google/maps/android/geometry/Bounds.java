package com.google.maps.android.geometry;

public class Bounds
{
  public final double maxX;
  public final double maxY;
  public final double midX;
  public final double midY;
  public final double minX;
  public final double minY;
  
  public Bounds(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    minX = paramDouble1;
    minY = paramDouble3;
    maxX = paramDouble2;
    maxY = paramDouble4;
    midX = ((paramDouble1 + paramDouble2) / 2.0D);
    midY = ((paramDouble3 + paramDouble4) / 2.0D);
  }
  
  public boolean contains(double paramDouble1, double paramDouble2)
  {
    return (minX <= paramDouble1) && (paramDouble1 <= maxX) && (minY <= paramDouble2) && (paramDouble2 <= maxY);
  }
  
  public boolean contains(Bounds paramBounds)
  {
    return (minX >= minX) && (maxX <= maxX) && (minY >= minY) && (maxY <= maxY);
  }
  
  public boolean contains(Point paramPoint)
  {
    return contains(x, y);
  }
  
  public boolean intersects(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    return (paramDouble1 < maxX) && (minX < paramDouble2) && (paramDouble3 < maxY) && (minY < paramDouble4);
  }
  
  public boolean intersects(Bounds paramBounds)
  {
    return intersects(minX, maxX, minY, maxY);
  }
}

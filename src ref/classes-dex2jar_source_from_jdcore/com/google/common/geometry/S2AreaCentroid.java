package com.google.common.geometry;

public final class S2AreaCentroid
{
  private final double area;
  private final S2Point centroid;
  
  public S2AreaCentroid(double paramDouble, S2Point paramS2Point)
  {
    area = paramDouble;
    centroid = paramS2Point;
  }
  
  public double getArea()
  {
    return area;
  }
  
  public S2Point getCentroid()
  {
    return centroid;
  }
}

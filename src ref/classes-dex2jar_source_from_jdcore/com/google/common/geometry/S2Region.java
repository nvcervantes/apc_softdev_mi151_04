package com.google.common.geometry;

public abstract interface S2Region
{
  public abstract boolean contains(S2Cell paramS2Cell);
  
  public abstract S2Cap getCapBound();
  
  public abstract S2LatLngRect getRectBound();
  
  public abstract boolean mayIntersect(S2Cell paramS2Cell);
}

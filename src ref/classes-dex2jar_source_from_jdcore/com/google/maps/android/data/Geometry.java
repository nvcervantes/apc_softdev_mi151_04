package com.google.maps.android.data;

public abstract interface Geometry<T>
{
  public abstract T getGeometryObject();
  
  public abstract String getGeometryType();
}

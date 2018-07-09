package com.google.maps.android.data.geojson;

abstract interface GeoJsonStyle
{
  public abstract String[] getGeometryType();
  
  public abstract boolean isVisible();
  
  public abstract void setVisible(boolean paramBoolean);
}

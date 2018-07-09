package com.google.maps.android.clustering;

import com.google.android.gms.maps.model.LatLng;

public abstract interface ClusterItem
{
  public abstract LatLng getPosition();
  
  public abstract String getSnippet();
  
  public abstract String getTitle();
}

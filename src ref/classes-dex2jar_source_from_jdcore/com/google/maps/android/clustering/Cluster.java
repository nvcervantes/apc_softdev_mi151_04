package com.google.maps.android.clustering;

import com.google.android.gms.maps.model.LatLng;
import java.util.Collection;

public abstract interface Cluster<T extends ClusterItem>
{
  public abstract Collection<T> getItems();
  
  public abstract LatLng getPosition();
  
  public abstract int getSize();
}

package com.google.maps.android.clustering.algo;

import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import java.util.Collection;
import java.util.Set;

public abstract interface Algorithm<T extends ClusterItem>
{
  public abstract void addItem(T paramT);
  
  public abstract void addItems(Collection<T> paramCollection);
  
  public abstract void clearItems();
  
  public abstract Set<? extends Cluster<T>> getClusters(double paramDouble);
  
  public abstract Collection<T> getItems();
  
  public abstract void removeItem(T paramT);
}

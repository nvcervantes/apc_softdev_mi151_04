package com.google.maps.android.clustering.view;

import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager.OnClusterClickListener;
import com.google.maps.android.clustering.ClusterManager.OnClusterInfoWindowClickListener;
import com.google.maps.android.clustering.ClusterManager.OnClusterItemClickListener;
import com.google.maps.android.clustering.ClusterManager.OnClusterItemInfoWindowClickListener;
import java.util.Set;

public abstract interface ClusterRenderer<T extends ClusterItem>
{
  public abstract void onAdd();
  
  public abstract void onClustersChanged(Set<? extends Cluster<T>> paramSet);
  
  public abstract void onRemove();
  
  public abstract void setAnimation(boolean paramBoolean);
  
  public abstract void setOnClusterClickListener(ClusterManager.OnClusterClickListener<T> paramOnClusterClickListener);
  
  public abstract void setOnClusterInfoWindowClickListener(ClusterManager.OnClusterInfoWindowClickListener<T> paramOnClusterInfoWindowClickListener);
  
  public abstract void setOnClusterItemClickListener(ClusterManager.OnClusterItemClickListener<T> paramOnClusterItemClickListener);
  
  public abstract void setOnClusterItemInfoWindowClickListener(ClusterManager.OnClusterItemInfoWindowClickListener<T> paramOnClusterItemInfoWindowClickListener);
}

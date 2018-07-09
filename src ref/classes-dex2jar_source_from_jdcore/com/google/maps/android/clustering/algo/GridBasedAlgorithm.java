package com.google.maps.android.clustering.algo;

import android.support.v4.util.LongSparseArray;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.projection.SphericalMercatorProjection;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GridBasedAlgorithm<T extends ClusterItem>
  implements Algorithm<T>
{
  private static final int GRID_SIZE = 100;
  private final Set<T> mItems = Collections.synchronizedSet(new HashSet());
  
  public GridBasedAlgorithm() {}
  
  private static long getCoord(long paramLong, double paramDouble1, double paramDouble2)
  {
    return (paramLong * Math.floor(paramDouble1) + Math.floor(paramDouble2));
  }
  
  public void addItem(T paramT)
  {
    mItems.add(paramT);
  }
  
  public void addItems(Collection<T> paramCollection)
  {
    mItems.addAll(paramCollection);
  }
  
  public void clearItems()
  {
    mItems.clear();
  }
  
  public Set<? extends Cluster<T>> getClusters(double paramDouble)
  {
    long l1 = Math.ceil(256.0D * Math.pow(2.0D, paramDouble) / 100.0D);
    SphericalMercatorProjection localSphericalMercatorProjection = new SphericalMercatorProjection(l1);
    HashSet localHashSet = new HashSet();
    LongSparseArray localLongSparseArray = new LongSparseArray();
    for (;;)
    {
      synchronized (mItems)
      {
        Iterator localIterator = mItems.iterator();
        if (localIterator.hasNext())
        {
          ClusterItem localClusterItem = (ClusterItem)localIterator.next();
          com.google.maps.android.projection.Point localPoint = localSphericalMercatorProjection.toPoint(localClusterItem.getPosition());
          long l2 = getCoord(l1, x, y);
          StaticCluster localStaticCluster = (StaticCluster)localLongSparseArray.get(l2);
          if (localStaticCluster == null)
          {
            localStaticCluster = new StaticCluster(localSphericalMercatorProjection.toLatLng(new com.google.maps.android.geometry.Point(Math.floor(x) + 0.5D, Math.floor(y) + 0.5D)));
            localLongSparseArray.put(l2, localStaticCluster);
            localHashSet.add(localStaticCluster);
            localStaticCluster.add(localClusterItem);
          }
        }
        else
        {
          return localHashSet;
        }
      }
    }
  }
  
  public Collection<T> getItems()
  {
    return mItems;
  }
  
  public void removeItem(T paramT)
  {
    mItems.remove(paramT);
  }
}

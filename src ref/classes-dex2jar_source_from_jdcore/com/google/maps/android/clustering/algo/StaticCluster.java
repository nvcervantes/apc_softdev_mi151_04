package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StaticCluster<T extends ClusterItem>
  implements Cluster<T>
{
  private final LatLng mCenter;
  private final List<T> mItems = new ArrayList();
  
  public StaticCluster(LatLng paramLatLng)
  {
    mCenter = paramLatLng;
  }
  
  public boolean add(T paramT)
  {
    return mItems.add(paramT);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool1 = paramObject instanceof StaticCluster;
    boolean bool2 = false;
    if (!bool1) {
      return false;
    }
    paramObject = (StaticCluster)paramObject;
    bool1 = bool2;
    if (mCenter.equals(mCenter))
    {
      bool1 = bool2;
      if (mItems.equals(mItems)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public Collection<T> getItems()
  {
    return mItems;
  }
  
  public LatLng getPosition()
  {
    return mCenter;
  }
  
  public int getSize()
  {
    return mItems.size();
  }
  
  public int hashCode()
  {
    return mCenter.hashCode() + mItems.hashCode();
  }
  
  public boolean remove(T paramT)
  {
    return mItems.remove(paramT);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("StaticCluster{mCenter=");
    localStringBuilder.append(mCenter);
    localStringBuilder.append(", mItems.size=");
    localStringBuilder.append(mItems.size());
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}

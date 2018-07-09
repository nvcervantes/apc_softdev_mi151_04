package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.quadtree.PointQuadTree;
import com.google.maps.android.quadtree.PointQuadTree.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NonHierarchicalDistanceBasedAlgorithm<T extends ClusterItem>
  implements Algorithm<T>
{
  public static final int MAX_DISTANCE_AT_ZOOM = 100;
  private static final SphericalMercatorProjection PROJECTION = new SphericalMercatorProjection(1.0D);
  private final Collection<QuadItem<T>> mItems = new ArrayList();
  private final PointQuadTree<QuadItem<T>> mQuadTree = new PointQuadTree(0.0D, 1.0D, 0.0D, 1.0D);
  
  public NonHierarchicalDistanceBasedAlgorithm() {}
  
  private Bounds createBoundsFromSpan(Point paramPoint, double paramDouble)
  {
    paramDouble /= 2.0D;
    return new Bounds(x - paramDouble, x + paramDouble, y - paramDouble, y + paramDouble);
  }
  
  private double distanceSquared(Point paramPoint1, Point paramPoint2)
  {
    return (x - x) * (x - x) + (y - y) * (y - y);
  }
  
  public void addItem(T arg1)
  {
    QuadItem localQuadItem = new QuadItem(???, null);
    synchronized (mQuadTree)
    {
      mItems.add(localQuadItem);
      mQuadTree.add(localQuadItem);
      return;
    }
  }
  
  public void addItems(Collection<T> paramCollection)
  {
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      addItem((ClusterItem)paramCollection.next());
    }
  }
  
  public void clearItems()
  {
    synchronized (mQuadTree)
    {
      mItems.clear();
      mQuadTree.clear();
      return;
    }
  }
  
  public Set<? extends Cluster<T>> getClusters(double paramDouble)
  {
    Object localObject1 = this;
    paramDouble = 100.0D / Math.pow(2.0D, (int)paramDouble) / 256.0D;
    HashSet localHashSet1 = new HashSet();
    HashSet localHashSet2 = new HashSet();
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    synchronized (mQuadTree)
    {
      Iterator localIterator1 = mItems.iterator();
      for (;;)
      {
        localObject1 = this;
        if (!localIterator1.hasNext()) {
          break;
        }
        QuadItem localQuadItem1 = (QuadItem)localIterator1.next();
        if (!localHashSet1.contains(localQuadItem1))
        {
          Object localObject3 = ((NonHierarchicalDistanceBasedAlgorithm)localObject1).createBoundsFromSpan(localQuadItem1.getPoint(), paramDouble);
          localObject1 = mQuadTree.search((Bounds)localObject3);
          if (((Collection)localObject1).size() == 1)
          {
            localHashSet2.add(localQuadItem1);
            localHashSet1.add(localQuadItem1);
            localHashMap1.put(localQuadItem1, Double.valueOf(0.0D));
          }
          else
          {
            localObject3 = new StaticCluster(mClusterItem.getPosition());
            localHashSet2.add(localObject3);
            Iterator localIterator2 = ((Collection)localObject1).iterator();
            while (localIterator2.hasNext())
            {
              QuadItem localQuadItem2 = (QuadItem)localIterator2.next();
              Double localDouble = (Double)localHashMap1.get(localQuadItem2);
              double d = distanceSquared(localQuadItem2.getPoint(), localQuadItem1.getPoint());
              if (localDouble != null)
              {
                if (localDouble.doubleValue() >= d) {
                  ((StaticCluster)localHashMap2.get(localQuadItem2)).remove(mClusterItem);
                }
              }
              else
              {
                localHashMap1.put(localQuadItem2, Double.valueOf(d));
                ((StaticCluster)localObject3).add(mClusterItem);
                localHashMap2.put(localQuadItem2, localObject3);
              }
            }
            localHashSet1.addAll((Collection)localObject1);
          }
        }
      }
      return localHashSet2;
    }
  }
  
  public Collection<T> getItems()
  {
    ArrayList localArrayList = new ArrayList();
    synchronized (mQuadTree)
    {
      Iterator localIterator = mItems.iterator();
      while (localIterator.hasNext()) {
        localArrayList.add(nextmClusterItem);
      }
      return localArrayList;
    }
  }
  
  public void removeItem(T arg1)
  {
    QuadItem localQuadItem = new QuadItem(???, null);
    synchronized (mQuadTree)
    {
      mItems.remove(localQuadItem);
      mQuadTree.remove(localQuadItem);
      return;
    }
  }
  
  private static class QuadItem<T extends ClusterItem>
    implements PointQuadTree.Item, Cluster<T>
  {
    private final T mClusterItem;
    private final Point mPoint;
    private final LatLng mPosition;
    private Set<T> singletonSet;
    
    private QuadItem(T paramT)
    {
      mClusterItem = paramT;
      mPosition = paramT.getPosition();
      mPoint = NonHierarchicalDistanceBasedAlgorithm.PROJECTION.toPoint(mPosition);
      singletonSet = Collections.singleton(mClusterItem);
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof QuadItem)) {
        return false;
      }
      return mClusterItem.equals(mClusterItem);
    }
    
    public Set<T> getItems()
    {
      return singletonSet;
    }
    
    public Point getPoint()
    {
      return mPoint;
    }
    
    public LatLng getPosition()
    {
      return mPosition;
    }
    
    public int getSize()
    {
      return 1;
    }
    
    public int hashCode()
    {
      return mClusterItem.hashCode();
    }
  }
}

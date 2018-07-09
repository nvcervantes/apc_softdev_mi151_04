package com.google.maps.android.quadtree;

import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class PointQuadTree<T extends Item>
{
  private static final int MAX_DEPTH = 40;
  private static final int MAX_ELEMENTS = 50;
  private final Bounds mBounds;
  private List<PointQuadTree<T>> mChildren = null;
  private final int mDepth;
  private List<T> mItems;
  
  public PointQuadTree(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    this(new Bounds(paramDouble1, paramDouble2, paramDouble3, paramDouble4));
  }
  
  private PointQuadTree(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, int paramInt)
  {
    this(new Bounds(paramDouble1, paramDouble2, paramDouble3, paramDouble4), paramInt);
  }
  
  public PointQuadTree(Bounds paramBounds)
  {
    this(paramBounds, 0);
  }
  
  private PointQuadTree(Bounds paramBounds, int paramInt)
  {
    mBounds = paramBounds;
    mDepth = paramInt;
  }
  
  private void insert(double paramDouble1, double paramDouble2, T paramT)
  {
    if (mChildren != null)
    {
      if (paramDouble2 < mBounds.midY)
      {
        if (paramDouble1 < mBounds.midX)
        {
          ((PointQuadTree)mChildren.get(0)).insert(paramDouble1, paramDouble2, paramT);
          return;
        }
        ((PointQuadTree)mChildren.get(1)).insert(paramDouble1, paramDouble2, paramT);
        return;
      }
      if (paramDouble1 < mBounds.midX)
      {
        ((PointQuadTree)mChildren.get(2)).insert(paramDouble1, paramDouble2, paramT);
        return;
      }
      ((PointQuadTree)mChildren.get(3)).insert(paramDouble1, paramDouble2, paramT);
      return;
    }
    if (mItems == null) {
      mItems = new ArrayList();
    }
    mItems.add(paramT);
    if ((mItems.size() > 50) && (mDepth < 40)) {
      split();
    }
  }
  
  private boolean remove(double paramDouble1, double paramDouble2, T paramT)
  {
    if (mChildren != null)
    {
      if (paramDouble2 < mBounds.midY)
      {
        if (paramDouble1 < mBounds.midX) {
          return ((PointQuadTree)mChildren.get(0)).remove(paramDouble1, paramDouble2, paramT);
        }
        return ((PointQuadTree)mChildren.get(1)).remove(paramDouble1, paramDouble2, paramT);
      }
      if (paramDouble1 < mBounds.midX) {
        return ((PointQuadTree)mChildren.get(2)).remove(paramDouble1, paramDouble2, paramT);
      }
      return ((PointQuadTree)mChildren.get(3)).remove(paramDouble1, paramDouble2, paramT);
    }
    if (mItems == null) {
      return false;
    }
    return mItems.remove(paramT);
  }
  
  private void search(Bounds paramBounds, Collection<T> paramCollection)
  {
    if (!mBounds.intersects(paramBounds)) {
      return;
    }
    Iterator localIterator;
    if (mChildren != null)
    {
      localIterator = mChildren.iterator();
      while (localIterator.hasNext()) {
        ((PointQuadTree)localIterator.next()).search(paramBounds, paramCollection);
      }
    }
    if (mItems != null)
    {
      if (paramBounds.contains(mBounds))
      {
        paramCollection.addAll(mItems);
        return;
      }
      localIterator = mItems.iterator();
      while (localIterator.hasNext())
      {
        Item localItem = (Item)localIterator.next();
        if (paramBounds.contains(localItem.getPoint())) {
          paramCollection.add(localItem);
        }
      }
    }
  }
  
  private void split()
  {
    mChildren = new ArrayList(4);
    mChildren.add(new PointQuadTree(mBounds.minX, mBounds.midX, mBounds.minY, mBounds.midY, mDepth + 1));
    mChildren.add(new PointQuadTree(mBounds.midX, mBounds.maxX, mBounds.minY, mBounds.midY, mDepth + 1));
    mChildren.add(new PointQuadTree(mBounds.minX, mBounds.midX, mBounds.midY, mBounds.maxY, mDepth + 1));
    mChildren.add(new PointQuadTree(mBounds.midX, mBounds.maxX, mBounds.midY, mBounds.maxY, mDepth + 1));
    Object localObject = mItems;
    mItems = null;
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Item localItem = (Item)((Iterator)localObject).next();
      insert(getPointx, getPointy, localItem);
    }
  }
  
  public void add(T paramT)
  {
    Point localPoint = paramT.getPoint();
    if (mBounds.contains(x, y)) {
      insert(x, y, paramT);
    }
  }
  
  public void clear()
  {
    mChildren = null;
    if (mItems != null) {
      mItems.clear();
    }
  }
  
  public boolean remove(T paramT)
  {
    Point localPoint = paramT.getPoint();
    if (mBounds.contains(x, y)) {
      return remove(x, y, paramT);
    }
    return false;
  }
  
  public Collection<T> search(Bounds paramBounds)
  {
    ArrayList localArrayList = new ArrayList();
    search(paramBounds, localArrayList);
    return localArrayList;
  }
  
  public static abstract interface Item
  {
    public abstract Point getPoint();
  }
}

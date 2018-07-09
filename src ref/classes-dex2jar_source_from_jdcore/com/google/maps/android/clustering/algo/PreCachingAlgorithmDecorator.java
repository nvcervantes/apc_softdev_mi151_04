package com.google.maps.android.clustering.algo;

import android.support.v4.util.LruCache;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PreCachingAlgorithmDecorator<T extends ClusterItem>
  implements Algorithm<T>
{
  private final Algorithm<T> mAlgorithm;
  private final LruCache<Integer, Set<? extends Cluster<T>>> mCache = new LruCache(5);
  private final ReadWriteLock mCacheLock = new ReentrantReadWriteLock();
  
  public PreCachingAlgorithmDecorator(Algorithm<T> paramAlgorithm)
  {
    mAlgorithm = paramAlgorithm;
  }
  
  private void clearCache()
  {
    mCache.evictAll();
  }
  
  private Set<? extends Cluster<T>> getClustersInternal(int paramInt)
  {
    mCacheLock.readLock().lock();
    Set localSet2 = (Set)mCache.get(Integer.valueOf(paramInt));
    mCacheLock.readLock().unlock();
    Set localSet1 = localSet2;
    if (localSet2 == null)
    {
      mCacheLock.writeLock().lock();
      localSet2 = (Set)mCache.get(Integer.valueOf(paramInt));
      localSet1 = localSet2;
      if (localSet2 == null)
      {
        localSet1 = mAlgorithm.getClusters(paramInt);
        mCache.put(Integer.valueOf(paramInt), localSet1);
      }
      mCacheLock.writeLock().unlock();
    }
    return localSet1;
  }
  
  public void addItem(T paramT)
  {
    mAlgorithm.addItem(paramT);
    clearCache();
  }
  
  public void addItems(Collection<T> paramCollection)
  {
    mAlgorithm.addItems(paramCollection);
    clearCache();
  }
  
  public void clearItems()
  {
    mAlgorithm.clearItems();
    clearCache();
  }
  
  public Set<? extends Cluster<T>> getClusters(double paramDouble)
  {
    int i = (int)paramDouble;
    Set localSet = getClustersInternal(i);
    LruCache localLruCache = mCache;
    int j = i + 1;
    if (localLruCache.get(Integer.valueOf(j)) == null) {
      new Thread(new PrecacheRunnable(j)).start();
    }
    localLruCache = mCache;
    i -= 1;
    if (localLruCache.get(Integer.valueOf(i)) == null) {
      new Thread(new PrecacheRunnable(i)).start();
    }
    return localSet;
  }
  
  public Collection<T> getItems()
  {
    return mAlgorithm.getItems();
  }
  
  public void removeItem(T paramT)
  {
    mAlgorithm.removeItem(paramT);
    clearCache();
  }
  
  private class PrecacheRunnable
    implements Runnable
  {
    private final int mZoom;
    
    public PrecacheRunnable(int paramInt)
    {
      mZoom = paramInt;
    }
    
    public void run()
    {
      try
      {
        Thread.sleep((Math.random() * 500.0D + 500.0D));
        PreCachingAlgorithmDecorator.this.getClustersInternal(mZoom);
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
    }
  }
}

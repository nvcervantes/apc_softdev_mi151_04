package com.google.maps.android.clustering;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.MarkerManager;
import com.google.maps.android.MarkerManager.Collection;
import com.google.maps.android.clustering.algo.Algorithm;
import com.google.maps.android.clustering.algo.NonHierarchicalDistanceBasedAlgorithm;
import com.google.maps.android.clustering.algo.PreCachingAlgorithmDecorator;
import com.google.maps.android.clustering.view.ClusterRenderer;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ClusterManager<T extends ClusterItem>
  implements GoogleMap.OnCameraIdleListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener
{
  private Algorithm<T> mAlgorithm;
  private final ReadWriteLock mAlgorithmLock = new ReentrantReadWriteLock();
  private final MarkerManager.Collection mClusterMarkers;
  private ClusterManager<T>.ClusterTask mClusterTask;
  private final ReadWriteLock mClusterTaskLock = new ReentrantReadWriteLock();
  private GoogleMap mMap;
  private final MarkerManager mMarkerManager;
  private final MarkerManager.Collection mMarkers;
  private OnClusterClickListener<T> mOnClusterClickListener;
  private OnClusterInfoWindowClickListener<T> mOnClusterInfoWindowClickListener;
  private OnClusterItemClickListener<T> mOnClusterItemClickListener;
  private OnClusterItemInfoWindowClickListener<T> mOnClusterItemInfoWindowClickListener;
  private CameraPosition mPreviousCameraPosition;
  private ClusterRenderer<T> mRenderer;
  
  public ClusterManager(Context paramContext, GoogleMap paramGoogleMap)
  {
    this(paramContext, paramGoogleMap, new MarkerManager(paramGoogleMap));
  }
  
  public ClusterManager(Context paramContext, GoogleMap paramGoogleMap, MarkerManager paramMarkerManager)
  {
    mMap = paramGoogleMap;
    mMarkerManager = paramMarkerManager;
    mClusterMarkers = paramMarkerManager.newCollection();
    mMarkers = paramMarkerManager.newCollection();
    mRenderer = new DefaultClusterRenderer(paramContext, paramGoogleMap, this);
    mAlgorithm = new PreCachingAlgorithmDecorator(new NonHierarchicalDistanceBasedAlgorithm());
    mClusterTask = new ClusterTask(null);
    mRenderer.onAdd();
  }
  
  public void addItem(T paramT)
  {
    mAlgorithmLock.writeLock().lock();
    try
    {
      mAlgorithm.addItem(paramT);
      return;
    }
    finally
    {
      mAlgorithmLock.writeLock().unlock();
    }
  }
  
  public void addItems(Collection<T> paramCollection)
  {
    mAlgorithmLock.writeLock().lock();
    try
    {
      mAlgorithm.addItems(paramCollection);
      return;
    }
    finally
    {
      mAlgorithmLock.writeLock().unlock();
    }
  }
  
  public void clearItems()
  {
    mAlgorithmLock.writeLock().lock();
    try
    {
      mAlgorithm.clearItems();
      return;
    }
    finally
    {
      mAlgorithmLock.writeLock().unlock();
    }
  }
  
  public void cluster()
  {
    mClusterTaskLock.writeLock().lock();
    try
    {
      mClusterTask.cancel(true);
      mClusterTask = new ClusterTask(null);
      if (Build.VERSION.SDK_INT < 11) {
        mClusterTask.execute(new Float[] { Float.valueOf(mMap.getCameraPosition().zoom) });
      } else {
        mClusterTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Float[] { Float.valueOf(mMap.getCameraPosition().zoom) });
      }
      return;
    }
    finally
    {
      mClusterTaskLock.writeLock().unlock();
    }
  }
  
  public Algorithm<T> getAlgorithm()
  {
    return mAlgorithm;
  }
  
  public MarkerManager.Collection getClusterMarkerCollection()
  {
    return mClusterMarkers;
  }
  
  public MarkerManager.Collection getMarkerCollection()
  {
    return mMarkers;
  }
  
  public MarkerManager getMarkerManager()
  {
    return mMarkerManager;
  }
  
  public ClusterRenderer<T> getRenderer()
  {
    return mRenderer;
  }
  
  public void onCameraIdle()
  {
    if ((mRenderer instanceof GoogleMap.OnCameraIdleListener)) {
      ((GoogleMap.OnCameraIdleListener)mRenderer).onCameraIdle();
    }
    CameraPosition localCameraPosition = mMap.getCameraPosition();
    if ((mPreviousCameraPosition != null) && (mPreviousCameraPosition.zoom == zoom)) {
      return;
    }
    mPreviousCameraPosition = mMap.getCameraPosition();
    cluster();
  }
  
  public void onInfoWindowClick(Marker paramMarker)
  {
    getMarkerManager().onInfoWindowClick(paramMarker);
  }
  
  public boolean onMarkerClick(Marker paramMarker)
  {
    return getMarkerManager().onMarkerClick(paramMarker);
  }
  
  public void removeItem(T paramT)
  {
    mAlgorithmLock.writeLock().lock();
    try
    {
      mAlgorithm.removeItem(paramT);
      return;
    }
    finally
    {
      mAlgorithmLock.writeLock().unlock();
    }
  }
  
  public void setAlgorithm(Algorithm<T> paramAlgorithm)
  {
    mAlgorithmLock.writeLock().lock();
    try
    {
      if (mAlgorithm != null) {
        paramAlgorithm.addItems(mAlgorithm.getItems());
      }
      mAlgorithm = new PreCachingAlgorithmDecorator(paramAlgorithm);
      mAlgorithmLock.writeLock().unlock();
      cluster();
      return;
    }
    finally
    {
      mAlgorithmLock.writeLock().unlock();
    }
  }
  
  public void setAnimation(boolean paramBoolean)
  {
    mRenderer.setAnimation(paramBoolean);
  }
  
  public void setOnClusterClickListener(OnClusterClickListener<T> paramOnClusterClickListener)
  {
    mOnClusterClickListener = paramOnClusterClickListener;
    mRenderer.setOnClusterClickListener(paramOnClusterClickListener);
  }
  
  public void setOnClusterInfoWindowClickListener(OnClusterInfoWindowClickListener<T> paramOnClusterInfoWindowClickListener)
  {
    mOnClusterInfoWindowClickListener = paramOnClusterInfoWindowClickListener;
    mRenderer.setOnClusterInfoWindowClickListener(paramOnClusterInfoWindowClickListener);
  }
  
  public void setOnClusterItemClickListener(OnClusterItemClickListener<T> paramOnClusterItemClickListener)
  {
    mOnClusterItemClickListener = paramOnClusterItemClickListener;
    mRenderer.setOnClusterItemClickListener(paramOnClusterItemClickListener);
  }
  
  public void setOnClusterItemInfoWindowClickListener(OnClusterItemInfoWindowClickListener<T> paramOnClusterItemInfoWindowClickListener)
  {
    mOnClusterItemInfoWindowClickListener = paramOnClusterItemInfoWindowClickListener;
    mRenderer.setOnClusterItemInfoWindowClickListener(paramOnClusterItemInfoWindowClickListener);
  }
  
  public void setRenderer(ClusterRenderer<T> paramClusterRenderer)
  {
    mRenderer.setOnClusterClickListener(null);
    mRenderer.setOnClusterItemClickListener(null);
    mClusterMarkers.clear();
    mMarkers.clear();
    mRenderer.onRemove();
    mRenderer = paramClusterRenderer;
    mRenderer.onAdd();
    mRenderer.setOnClusterClickListener(mOnClusterClickListener);
    mRenderer.setOnClusterInfoWindowClickListener(mOnClusterInfoWindowClickListener);
    mRenderer.setOnClusterItemClickListener(mOnClusterItemClickListener);
    mRenderer.setOnClusterItemInfoWindowClickListener(mOnClusterItemInfoWindowClickListener);
    cluster();
  }
  
  private class ClusterTask
    extends AsyncTask<Float, Void, Set<? extends Cluster<T>>>
  {
    private ClusterTask() {}
    
    protected Set<? extends Cluster<T>> doInBackground(Float... paramVarArgs)
    {
      mAlgorithmLock.readLock().lock();
      try
      {
        paramVarArgs = mAlgorithm.getClusters(paramVarArgs[0].floatValue());
        return paramVarArgs;
      }
      finally
      {
        mAlgorithmLock.readLock().unlock();
      }
    }
    
    protected void onPostExecute(Set<? extends Cluster<T>> paramSet)
    {
      mRenderer.onClustersChanged(paramSet);
    }
  }
  
  public static abstract interface OnClusterClickListener<T extends ClusterItem>
  {
    public abstract boolean onClusterClick(Cluster<T> paramCluster);
  }
  
  public static abstract interface OnClusterInfoWindowClickListener<T extends ClusterItem>
  {
    public abstract void onClusterInfoWindowClick(Cluster<T> paramCluster);
  }
  
  public static abstract interface OnClusterItemClickListener<T extends ClusterItem>
  {
    public abstract boolean onClusterItemClick(T paramT);
  }
  
  public static abstract interface OnClusterItemInfoWindowClickListener<T extends ClusterItem>
  {
    public abstract void onClusterItemInfoWindowClick(T paramT);
  }
}

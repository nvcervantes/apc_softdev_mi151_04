package com.google.maps.android.clustering.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.MessageQueue.IdleHandler;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.DecelerateInterpolator;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;
import com.google.maps.android.MarkerManager;
import com.google.maps.android.MarkerManager.Collection;
import com.google.maps.android.R.id;
import com.google.maps.android.R.style;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.ClusterManager.OnClusterClickListener;
import com.google.maps.android.clustering.ClusterManager.OnClusterInfoWindowClickListener;
import com.google.maps.android.clustering.ClusterManager.OnClusterItemClickListener;
import com.google.maps.android.clustering.ClusterManager.OnClusterItemInfoWindowClickListener;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.ui.IconGenerator;
import com.google.maps.android.ui.SquareTextView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DefaultClusterRenderer<T extends ClusterItem>
  implements ClusterRenderer<T>
{
  private static final TimeInterpolator ANIMATION_INTERP = new DecelerateInterpolator();
  private static final int[] BUCKETS;
  private static final boolean SHOULD_ANIMATE;
  private boolean mAnimate;
  private ClusterManager.OnClusterClickListener<T> mClickListener;
  private final ClusterManager<T> mClusterManager;
  private Map<Cluster<T>, Marker> mClusterToMarker = new HashMap();
  private Set<? extends Cluster<T>> mClusters;
  private ShapeDrawable mColoredCircleBackground;
  private final float mDensity;
  private final IconGenerator mIconGenerator;
  private SparseArray<BitmapDescriptor> mIcons = new SparseArray();
  private ClusterManager.OnClusterInfoWindowClickListener<T> mInfoWindowClickListener;
  private ClusterManager.OnClusterItemClickListener<T> mItemClickListener;
  private ClusterManager.OnClusterItemInfoWindowClickListener<T> mItemInfoWindowClickListener;
  private final GoogleMap mMap;
  private MarkerCache<T> mMarkerCache = new MarkerCache(null);
  private Map<Marker, Cluster<T>> mMarkerToCluster = new HashMap();
  private Set<MarkerWithPosition> mMarkers = Collections.newSetFromMap(new ConcurrentHashMap());
  private int mMinClusterSize = 4;
  private final DefaultClusterRenderer<T>.ViewModifier mViewModifier = new ViewModifier(null);
  private float mZoom;
  
  static
  {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 11) {
      bool = true;
    } else {
      bool = false;
    }
    SHOULD_ANIMATE = bool;
    BUCKETS = new int[] { 10, 20, 50, 100, 200, 500, 1000 };
  }
  
  public DefaultClusterRenderer(Context paramContext, GoogleMap paramGoogleMap, ClusterManager<T> paramClusterManager)
  {
    mMap = paramGoogleMap;
    mAnimate = true;
    mDensity = getResourcesgetDisplayMetricsdensity;
    mIconGenerator = new IconGenerator(paramContext);
    mIconGenerator.setContentView(makeSquareTextView(paramContext));
    mIconGenerator.setTextAppearance(R.style.amu_ClusterIcon_TextAppearance);
    mIconGenerator.setBackground(makeClusterBackground());
    mClusterManager = paramClusterManager;
  }
  
  private static double distanceSquared(Point paramPoint1, Point paramPoint2)
  {
    return (x - x) * (x - x) + (y - y) * (y - y);
  }
  
  private static Point findClosestCluster(List<Point> paramList, Point paramPoint)
  {
    Point localPoint = null;
    if (paramList != null)
    {
      if (paramList.isEmpty()) {
        return null;
      }
      double d1 = 10000.0D;
      Iterator localIterator = paramList.iterator();
      paramList = localPoint;
      while (localIterator.hasNext())
      {
        localPoint = (Point)localIterator.next();
        double d2 = distanceSquared(localPoint, paramPoint);
        if (d2 < d1)
        {
          paramList = localPoint;
          d1 = d2;
        }
      }
      return paramList;
    }
    return null;
  }
  
  private LayerDrawable makeClusterBackground()
  {
    mColoredCircleBackground = new ShapeDrawable(new OvalShape());
    Object localObject = new ShapeDrawable(new OvalShape());
    ((ShapeDrawable)localObject).getPaint().setColor(-2130706433);
    localObject = new LayerDrawable(new Drawable[] { localObject, mColoredCircleBackground });
    int i = (int)(mDensity * 3.0F);
    ((LayerDrawable)localObject).setLayerInset(1, i, i, i, i);
    return localObject;
  }
  
  private SquareTextView makeSquareTextView(Context paramContext)
  {
    paramContext = new SquareTextView(paramContext);
    paramContext.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    paramContext.setId(R.id.amu_text);
    int i = (int)(12.0F * mDensity);
    paramContext.setPadding(i, i, i, i);
    return paramContext;
  }
  
  protected int getBucket(Cluster<T> paramCluster)
  {
    int k = paramCluster.getSize();
    paramCluster = BUCKETS;
    int i = 0;
    if (k <= paramCluster[0]) {
      return k;
    }
    while (i < BUCKETS.length - 1)
    {
      paramCluster = BUCKETS;
      int j = i + 1;
      if (k < paramCluster[j]) {
        return BUCKETS[i];
      }
      i = j;
    }
    return BUCKETS[(BUCKETS.length - 1)];
  }
  
  public Cluster<T> getCluster(Marker paramMarker)
  {
    return (Cluster)mMarkerToCluster.get(paramMarker);
  }
  
  public T getClusterItem(Marker paramMarker)
  {
    return (ClusterItem)mMarkerCache.get(paramMarker);
  }
  
  protected String getClusterText(int paramInt)
  {
    if (paramInt < BUCKETS[0]) {
      return String.valueOf(paramInt);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(String.valueOf(paramInt));
    localStringBuilder.append("+");
    return localStringBuilder.toString();
  }
  
  protected int getColor(int paramInt)
  {
    float f = 300.0F - Math.min(paramInt, 300.0F);
    return Color.HSVToColor(new float[] { f * f / 90000.0F * 220.0F, 1.0F, 0.6F });
  }
  
  public Marker getMarker(Cluster<T> paramCluster)
  {
    return (Marker)mClusterToMarker.get(paramCluster);
  }
  
  public Marker getMarker(T paramT)
  {
    return mMarkerCache.get(paramT);
  }
  
  public int getMinClusterSize()
  {
    return mMinClusterSize;
  }
  
  public void onAdd()
  {
    mClusterManager.getMarkerCollection().setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
    {
      public boolean onMarkerClick(Marker paramAnonymousMarker)
      {
        return (mItemClickListener != null) && (mItemClickListener.onClusterItemClick((ClusterItem)mMarkerCache.get(paramAnonymousMarker)));
      }
    });
    mClusterManager.getMarkerCollection().setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
    {
      public void onInfoWindowClick(Marker paramAnonymousMarker)
      {
        if (mItemInfoWindowClickListener != null) {
          mItemInfoWindowClickListener.onClusterItemInfoWindowClick((ClusterItem)mMarkerCache.get(paramAnonymousMarker));
        }
      }
    });
    mClusterManager.getClusterMarkerCollection().setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
    {
      public boolean onMarkerClick(Marker paramAnonymousMarker)
      {
        return (mClickListener != null) && (mClickListener.onClusterClick((Cluster)mMarkerToCluster.get(paramAnonymousMarker)));
      }
    });
    mClusterManager.getClusterMarkerCollection().setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
    {
      public void onInfoWindowClick(Marker paramAnonymousMarker)
      {
        if (mInfoWindowClickListener != null) {
          mInfoWindowClickListener.onClusterInfoWindowClick((Cluster)mMarkerToCluster.get(paramAnonymousMarker));
        }
      }
    });
  }
  
  protected void onBeforeClusterItemRendered(T paramT, MarkerOptions paramMarkerOptions) {}
  
  protected void onBeforeClusterRendered(Cluster<T> paramCluster, MarkerOptions paramMarkerOptions)
  {
    int i = getBucket(paramCluster);
    BitmapDescriptor localBitmapDescriptor = (BitmapDescriptor)mIcons.get(i);
    paramCluster = localBitmapDescriptor;
    if (localBitmapDescriptor == null)
    {
      mColoredCircleBackground.getPaint().setColor(getColor(i));
      paramCluster = BitmapDescriptorFactory.fromBitmap(mIconGenerator.makeIcon(getClusterText(i)));
      mIcons.put(i, paramCluster);
    }
    paramMarkerOptions.icon(paramCluster);
  }
  
  protected void onClusterItemRendered(T paramT, Marker paramMarker) {}
  
  protected void onClusterRendered(Cluster<T> paramCluster, Marker paramMarker) {}
  
  public void onClustersChanged(Set<? extends Cluster<T>> paramSet)
  {
    mViewModifier.queue(paramSet);
  }
  
  public void onRemove()
  {
    mClusterManager.getMarkerCollection().setOnMarkerClickListener(null);
    mClusterManager.getMarkerCollection().setOnInfoWindowClickListener(null);
    mClusterManager.getClusterMarkerCollection().setOnMarkerClickListener(null);
    mClusterManager.getClusterMarkerCollection().setOnInfoWindowClickListener(null);
  }
  
  public void setAnimation(boolean paramBoolean)
  {
    mAnimate = paramBoolean;
  }
  
  public void setMinClusterSize(int paramInt)
  {
    mMinClusterSize = paramInt;
  }
  
  public void setOnClusterClickListener(ClusterManager.OnClusterClickListener<T> paramOnClusterClickListener)
  {
    mClickListener = paramOnClusterClickListener;
  }
  
  public void setOnClusterInfoWindowClickListener(ClusterManager.OnClusterInfoWindowClickListener<T> paramOnClusterInfoWindowClickListener)
  {
    mInfoWindowClickListener = paramOnClusterInfoWindowClickListener;
  }
  
  public void setOnClusterItemClickListener(ClusterManager.OnClusterItemClickListener<T> paramOnClusterItemClickListener)
  {
    mItemClickListener = paramOnClusterItemClickListener;
  }
  
  public void setOnClusterItemInfoWindowClickListener(ClusterManager.OnClusterItemInfoWindowClickListener<T> paramOnClusterItemInfoWindowClickListener)
  {
    mItemInfoWindowClickListener = paramOnClusterItemInfoWindowClickListener;
  }
  
  protected boolean shouldRenderAsCluster(Cluster<T> paramCluster)
  {
    return paramCluster.getSize() > mMinClusterSize;
  }
  
  @TargetApi(12)
  private class AnimationTask
    extends AnimatorListenerAdapter
    implements ValueAnimator.AnimatorUpdateListener
  {
    private final LatLng from;
    private MarkerManager mMarkerManager;
    private boolean mRemoveOnComplete;
    private final Marker marker;
    private final DefaultClusterRenderer.MarkerWithPosition markerWithPosition;
    private final LatLng to;
    
    private AnimationTask(DefaultClusterRenderer.MarkerWithPosition paramMarkerWithPosition, LatLng paramLatLng1, LatLng paramLatLng2)
    {
      markerWithPosition = paramMarkerWithPosition;
      marker = DefaultClusterRenderer.MarkerWithPosition.access$1800(paramMarkerWithPosition);
      from = paramLatLng1;
      to = paramLatLng2;
    }
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      if (mRemoveOnComplete)
      {
        paramAnimator = (Cluster)mMarkerToCluster.get(marker);
        mClusterToMarker.remove(paramAnimator);
        mMarkerCache.remove(marker);
        mMarkerToCluster.remove(marker);
        mMarkerManager.remove(marker);
      }
      DefaultClusterRenderer.MarkerWithPosition.access$1702(markerWithPosition, to);
    }
    
    public void onAnimationUpdate(ValueAnimator paramValueAnimator)
    {
      float f = paramValueAnimator.getAnimatedFraction();
      double d3 = to.latitude;
      double d4 = from.latitude;
      double d5 = f;
      double d6 = from.latitude;
      double d2 = to.longitude - from.longitude;
      double d1 = d2;
      if (Math.abs(d2) > 180.0D) {
        d1 = d2 - Math.signum(d2) * 360.0D;
      }
      paramValueAnimator = new LatLng((d3 - d4) * d5 + d6, d1 * d5 + from.longitude);
      marker.setPosition(paramValueAnimator);
    }
    
    public void perform()
    {
      ValueAnimator localValueAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
      localValueAnimator.setInterpolator(DefaultClusterRenderer.ANIMATION_INTERP);
      localValueAnimator.addUpdateListener(this);
      localValueAnimator.addListener(this);
      localValueAnimator.start();
    }
    
    public void removeOnAnimationComplete(MarkerManager paramMarkerManager)
    {
      mMarkerManager = paramMarkerManager;
      mRemoveOnComplete = true;
    }
  }
  
  private class CreateMarkerTask
  {
    private final LatLng animateFrom;
    private final Cluster<T> cluster;
    private final Set<DefaultClusterRenderer.MarkerWithPosition> newMarkers;
    
    public CreateMarkerTask(Set<DefaultClusterRenderer.MarkerWithPosition> paramSet, LatLng paramLatLng)
    {
      cluster = paramSet;
      newMarkers = paramLatLng;
      Object localObject;
      animateFrom = localObject;
    }
    
    private void perform(DefaultClusterRenderer<T>.MarkerModifier paramDefaultClusterRenderer)
    {
      Object localObject1;
      Marker localMarker;
      DefaultClusterRenderer.MarkerWithPosition localMarkerWithPosition;
      if (!shouldRenderAsCluster(cluster))
      {
        Iterator localIterator = cluster.getItems().iterator();
        while (localIterator.hasNext())
        {
          ClusterItem localClusterItem = (ClusterItem)localIterator.next();
          localObject2 = mMarkerCache.get(localClusterItem);
          if (localObject2 == null)
          {
            localObject1 = new MarkerOptions();
            if (animateFrom != null) {
              ((MarkerOptions)localObject1).position(animateFrom);
            } else {
              ((MarkerOptions)localObject1).position(localClusterItem.getPosition());
            }
            if ((localClusterItem.getTitle() != null) && (localClusterItem.getSnippet() != null))
            {
              ((MarkerOptions)localObject1).title(localClusterItem.getTitle());
              ((MarkerOptions)localObject1).snippet(localClusterItem.getSnippet());
            }
            else if (localClusterItem.getSnippet() != null)
            {
              ((MarkerOptions)localObject1).title(localClusterItem.getSnippet());
            }
            else if (localClusterItem.getTitle() != null)
            {
              ((MarkerOptions)localObject1).title(localClusterItem.getTitle());
            }
            onBeforeClusterItemRendered(localClusterItem, (MarkerOptions)localObject1);
            localMarker = mClusterManager.getMarkerCollection().addMarker((MarkerOptions)localObject1);
            localMarkerWithPosition = new DefaultClusterRenderer.MarkerWithPosition(localMarker, null);
            mMarkerCache.put(localClusterItem, localMarker);
            localObject2 = localMarker;
            localObject1 = localMarkerWithPosition;
            if (animateFrom != null)
            {
              paramDefaultClusterRenderer.animate(localMarkerWithPosition, animateFrom, localClusterItem.getPosition());
              localObject2 = localMarker;
              localObject1 = localMarkerWithPosition;
            }
          }
          else
          {
            localObject1 = new DefaultClusterRenderer.MarkerWithPosition((Marker)localObject2, null);
          }
          onClusterItemRendered(localClusterItem, (Marker)localObject2);
          newMarkers.add(localObject1);
        }
        return;
      }
      Object localObject2 = (Marker)mClusterToMarker.get(cluster);
      if (localObject2 == null)
      {
        localObject2 = new MarkerOptions();
        if (animateFrom == null) {
          localObject1 = cluster.getPosition();
        } else {
          localObject1 = animateFrom;
        }
        localObject1 = ((MarkerOptions)localObject2).position((LatLng)localObject1);
        onBeforeClusterRendered(cluster, (MarkerOptions)localObject1);
        localMarker = mClusterManager.getClusterMarkerCollection().addMarker((MarkerOptions)localObject1);
        mMarkerToCluster.put(localMarker, cluster);
        mClusterToMarker.put(cluster, localMarker);
        localMarkerWithPosition = new DefaultClusterRenderer.MarkerWithPosition(localMarker, null);
        localObject2 = localMarker;
        localObject1 = localMarkerWithPosition;
        if (animateFrom != null)
        {
          paramDefaultClusterRenderer.animate(localMarkerWithPosition, animateFrom, cluster.getPosition());
          localObject2 = localMarker;
          localObject1 = localMarkerWithPosition;
        }
      }
      else
      {
        localObject1 = new DefaultClusterRenderer.MarkerWithPosition((Marker)localObject2, null);
      }
      onClusterRendered(cluster, (Marker)localObject2);
      newMarkers.add(localObject1);
    }
  }
  
  private static class MarkerCache<T>
  {
    private Map<T, Marker> mCache = new HashMap();
    private Map<Marker, T> mCacheReverse = new HashMap();
    
    private MarkerCache() {}
    
    public Marker get(T paramT)
    {
      return (Marker)mCache.get(paramT);
    }
    
    public T get(Marker paramMarker)
    {
      return mCacheReverse.get(paramMarker);
    }
    
    public void put(T paramT, Marker paramMarker)
    {
      mCache.put(paramT, paramMarker);
      mCacheReverse.put(paramMarker, paramT);
    }
    
    public void remove(Marker paramMarker)
    {
      Object localObject = mCacheReverse.get(paramMarker);
      mCacheReverse.remove(paramMarker);
      mCache.remove(localObject);
    }
  }
  
  @SuppressLint({"HandlerLeak"})
  private class MarkerModifier
    extends Handler
    implements MessageQueue.IdleHandler
  {
    private static final int BLANK = 0;
    private final Condition busyCondition = lock.newCondition();
    private final Lock lock = new ReentrantLock();
    private Queue<DefaultClusterRenderer<T>.AnimationTask> mAnimationTasks = new LinkedList();
    private Queue<DefaultClusterRenderer<T>.CreateMarkerTask> mCreateMarkerTasks = new LinkedList();
    private boolean mListenerAdded;
    private Queue<DefaultClusterRenderer<T>.CreateMarkerTask> mOnScreenCreateMarkerTasks = new LinkedList();
    private Queue<Marker> mOnScreenRemoveMarkerTasks = new LinkedList();
    private Queue<Marker> mRemoveMarkerTasks = new LinkedList();
    
    private MarkerModifier()
    {
      super();
    }
    
    @TargetApi(11)
    private void performNextTask()
    {
      if (!mOnScreenRemoveMarkerTasks.isEmpty())
      {
        removeMarker((Marker)mOnScreenRemoveMarkerTasks.poll());
        return;
      }
      if (!mAnimationTasks.isEmpty())
      {
        ((DefaultClusterRenderer.AnimationTask)mAnimationTasks.poll()).perform();
        return;
      }
      if (!mOnScreenCreateMarkerTasks.isEmpty())
      {
        ((DefaultClusterRenderer.CreateMarkerTask)mOnScreenCreateMarkerTasks.poll()).perform(this);
        return;
      }
      if (!mCreateMarkerTasks.isEmpty())
      {
        ((DefaultClusterRenderer.CreateMarkerTask)mCreateMarkerTasks.poll()).perform(this);
        return;
      }
      if (!mRemoveMarkerTasks.isEmpty()) {
        removeMarker((Marker)mRemoveMarkerTasks.poll());
      }
    }
    
    private void removeMarker(Marker paramMarker)
    {
      Cluster localCluster = (Cluster)mMarkerToCluster.get(paramMarker);
      mClusterToMarker.remove(localCluster);
      mMarkerCache.remove(paramMarker);
      mMarkerToCluster.remove(paramMarker);
      mClusterManager.getMarkerManager().remove(paramMarker);
    }
    
    public void add(boolean paramBoolean, DefaultClusterRenderer<T>.CreateMarkerTask paramDefaultClusterRenderer)
    {
      lock.lock();
      sendEmptyMessage(0);
      if (paramBoolean) {
        mOnScreenCreateMarkerTasks.add(paramDefaultClusterRenderer);
      } else {
        mCreateMarkerTasks.add(paramDefaultClusterRenderer);
      }
      lock.unlock();
    }
    
    public void animate(DefaultClusterRenderer.MarkerWithPosition paramMarkerWithPosition, LatLng paramLatLng1, LatLng paramLatLng2)
    {
      lock.lock();
      mAnimationTasks.add(new DefaultClusterRenderer.AnimationTask(DefaultClusterRenderer.this, paramMarkerWithPosition, paramLatLng1, paramLatLng2, null));
      lock.unlock();
    }
    
    @TargetApi(11)
    public void animateThenRemove(DefaultClusterRenderer.MarkerWithPosition paramMarkerWithPosition, LatLng paramLatLng1, LatLng paramLatLng2)
    {
      lock.lock();
      paramMarkerWithPosition = new DefaultClusterRenderer.AnimationTask(DefaultClusterRenderer.this, paramMarkerWithPosition, paramLatLng1, paramLatLng2, null);
      paramMarkerWithPosition.removeOnAnimationComplete(mClusterManager.getMarkerManager());
      mAnimationTasks.add(paramMarkerWithPosition);
      lock.unlock();
    }
    
    public void handleMessage(Message paramMessage)
    {
      if (!mListenerAdded)
      {
        Looper.myQueue().addIdleHandler(this);
        mListenerAdded = true;
      }
      removeMessages(0);
      lock.lock();
      int i = 0;
      for (;;)
      {
        if (i < 10) {}
        try
        {
          performNextTask();
          i += 1;
        }
        finally
        {
          for (;;) {}
        }
      }
      if (!isBusy())
      {
        mListenerAdded = false;
        Looper.myQueue().removeIdleHandler(this);
        busyCondition.signalAll();
      }
      else
      {
        sendEmptyMessageDelayed(0, 10L);
      }
      lock.unlock();
      return;
      lock.unlock();
      throw paramMessage;
    }
    
    public boolean isBusy()
    {
      try
      {
        lock.lock();
        if ((mCreateMarkerTasks.isEmpty()) && (mOnScreenCreateMarkerTasks.isEmpty()) && (mOnScreenRemoveMarkerTasks.isEmpty()) && (mRemoveMarkerTasks.isEmpty()))
        {
          bool = mAnimationTasks.isEmpty();
          if (bool)
          {
            bool = false;
            break label81;
          }
        }
        boolean bool = true;
        label81:
        return bool;
      }
      finally
      {
        lock.unlock();
      }
    }
    
    public boolean queueIdle()
    {
      sendEmptyMessage(0);
      return true;
    }
    
    public void remove(boolean paramBoolean, Marker paramMarker)
    {
      lock.lock();
      sendEmptyMessage(0);
      if (paramBoolean) {
        mOnScreenRemoveMarkerTasks.add(paramMarker);
      } else {
        mRemoveMarkerTasks.add(paramMarker);
      }
      lock.unlock();
    }
    
    /* Error */
    public void waitUntilFree()
    {
      // Byte code:
      //   0: aload_0
      //   1: invokevirtual 198	com/google/maps/android/clustering/view/DefaultClusterRenderer$MarkerModifier:isBusy	()Z
      //   4: ifeq +71 -> 75
      //   7: aload_0
      //   8: iconst_0
      //   9: invokevirtual 157	com/google/maps/android/clustering/view/DefaultClusterRenderer$MarkerModifier:sendEmptyMessage	(I)Z
      //   12: pop
      //   13: aload_0
      //   14: getfield 52	com/google/maps/android/clustering/view/DefaultClusterRenderer$MarkerModifier:lock	Ljava/util/concurrent/locks/Lock;
      //   17: invokeinterface 153 1 0
      //   22: aload_0
      //   23: invokevirtual 198	com/google/maps/android/clustering/view/DefaultClusterRenderer$MarkerModifier:isBusy	()Z
      //   26: ifeq +12 -> 38
      //   29: aload_0
      //   30: getfield 60	com/google/maps/android/clustering/view/DefaultClusterRenderer$MarkerModifier:busyCondition	Ljava/util/concurrent/locks/Condition;
      //   33: invokeinterface 220 1 0
      //   38: aload_0
      //   39: getfield 52	com/google/maps/android/clustering/view/DefaultClusterRenderer$MarkerModifier:lock	Ljava/util/concurrent/locks/Lock;
      //   42: invokeinterface 163 1 0
      //   47: goto -47 -> 0
      //   50: astore_1
      //   51: goto +13 -> 64
      //   54: astore_1
      //   55: new 222	java/lang/RuntimeException
      //   58: dup
      //   59: aload_1
      //   60: invokespecial 225	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
      //   63: athrow
      //   64: aload_0
      //   65: getfield 52	com/google/maps/android/clustering/view/DefaultClusterRenderer$MarkerModifier:lock	Ljava/util/concurrent/locks/Lock;
      //   68: invokeinterface 163 1 0
      //   73: aload_1
      //   74: athrow
      //   75: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	76	0	this	MarkerModifier
      //   50	1	1	localObject	Object
      //   54	20	1	localInterruptedException	InterruptedException
      // Exception table:
      //   from	to	target	type
      //   22	38	50	finally
      //   55	64	50	finally
      //   22	38	54	java/lang/InterruptedException
    }
  }
  
  private static class MarkerWithPosition
  {
    private final Marker marker;
    private LatLng position;
    
    private MarkerWithPosition(Marker paramMarker)
    {
      marker = paramMarker;
      position = paramMarker.getPosition();
    }
    
    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof MarkerWithPosition)) {
        return marker.equals(marker);
      }
      return false;
    }
    
    public int hashCode()
    {
      return marker.hashCode();
    }
  }
  
  private class RenderTask
    implements Runnable
  {
    final Set<? extends Cluster<T>> clusters;
    private Runnable mCallback;
    private float mMapZoom;
    private Projection mProjection;
    private SphericalMercatorProjection mSphericalMercatorProjection;
    
    private RenderTask()
    {
      Object localObject;
      clusters = localObject;
    }
    
    @SuppressLint({"NewApi"})
    public void run()
    {
      if (clusters.equals(mClusters))
      {
        mCallback.run();
        return;
      }
      Object localObject1 = DefaultClusterRenderer.this;
      Object localObject2 = null;
      DefaultClusterRenderer.MarkerModifier localMarkerModifier = new DefaultClusterRenderer.MarkerModifier((DefaultClusterRenderer)localObject1, null);
      float f1 = mMapZoom;
      int i;
      if (f1 > mZoom) {
        i = 1;
      } else {
        i = 0;
      }
      float f2 = mZoom;
      Object localObject4 = mMarkers;
      LatLngBounds localLatLngBounds = mProjection.getVisibleRegion().latLngBounds;
      if ((mClusters != null) && (DefaultClusterRenderer.SHOULD_ANIMATE))
      {
        localObject3 = new ArrayList();
        localObject5 = mClusters.iterator();
        for (;;)
        {
          localObject1 = localObject3;
          if (!((Iterator)localObject5).hasNext()) {
            break;
          }
          localObject1 = (Cluster)((Iterator)localObject5).next();
          if ((shouldRenderAsCluster((Cluster)localObject1)) && (localLatLngBounds.contains(((Cluster)localObject1).getPosition()))) {
            ((List)localObject3).add(mSphericalMercatorProjection.toPoint(((Cluster)localObject1).getPosition()));
          }
        }
      }
      localObject1 = null;
      Object localObject3 = Collections.newSetFromMap(new ConcurrentHashMap());
      Object localObject5 = clusters.iterator();
      boolean bool;
      while (((Iterator)localObject5).hasNext())
      {
        Cluster localCluster = (Cluster)((Iterator)localObject5).next();
        bool = localLatLngBounds.contains(localCluster.getPosition());
        if ((i != 0) && (bool) && (DefaultClusterRenderer.SHOULD_ANIMATE))
        {
          Object localObject6 = DefaultClusterRenderer.findClosestCluster((List)localObject1, mSphericalMercatorProjection.toPoint(localCluster.getPosition()));
          if ((localObject6 != null) && (mAnimate))
          {
            localObject6 = mSphericalMercatorProjection.toLatLng((Point)localObject6);
            localMarkerModifier.add(true, new DefaultClusterRenderer.CreateMarkerTask(DefaultClusterRenderer.this, localCluster, (Set)localObject3, (LatLng)localObject6));
          }
          else
          {
            localMarkerModifier.add(true, new DefaultClusterRenderer.CreateMarkerTask(DefaultClusterRenderer.this, localCluster, (Set)localObject3, null));
          }
        }
        else
        {
          localMarkerModifier.add(bool, new DefaultClusterRenderer.CreateMarkerTask(DefaultClusterRenderer.this, localCluster, (Set)localObject3, null));
        }
      }
      localMarkerModifier.waitUntilFree();
      ((Set)localObject4).removeAll((Collection)localObject3);
      localObject1 = localObject2;
      if (DefaultClusterRenderer.SHOULD_ANIMATE)
      {
        localObject2 = new ArrayList();
        localObject5 = clusters.iterator();
        for (;;)
        {
          localObject1 = localObject2;
          if (!((Iterator)localObject5).hasNext()) {
            break;
          }
          localObject1 = (Cluster)((Iterator)localObject5).next();
          if ((shouldRenderAsCluster((Cluster)localObject1)) && (localLatLngBounds.contains(((Cluster)localObject1).getPosition()))) {
            ((List)localObject2).add(mSphericalMercatorProjection.toPoint(((Cluster)localObject1).getPosition()));
          }
        }
      }
      localObject2 = ((Set)localObject4).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject4 = (DefaultClusterRenderer.MarkerWithPosition)((Iterator)localObject2).next();
        bool = localLatLngBounds.contains(position);
        if ((i == 0) && (f1 - f2 > -3.0F) && (bool) && (DefaultClusterRenderer.SHOULD_ANIMATE))
        {
          localObject5 = DefaultClusterRenderer.findClosestCluster((List)localObject1, mSphericalMercatorProjection.toPoint(position));
          if ((localObject5 != null) && (mAnimate))
          {
            localObject5 = mSphericalMercatorProjection.toLatLng((Point)localObject5);
            localMarkerModifier.animateThenRemove((DefaultClusterRenderer.MarkerWithPosition)localObject4, position, (LatLng)localObject5);
          }
          else
          {
            localMarkerModifier.remove(true, marker);
          }
        }
        else
        {
          localMarkerModifier.remove(bool, marker);
        }
      }
      localMarkerModifier.waitUntilFree();
      DefaultClusterRenderer.access$1302(DefaultClusterRenderer.this, (Set)localObject3);
      DefaultClusterRenderer.access$1102(DefaultClusterRenderer.this, clusters);
      DefaultClusterRenderer.access$1002(DefaultClusterRenderer.this, f1);
      mCallback.run();
    }
    
    public void setCallback(Runnable paramRunnable)
    {
      mCallback = paramRunnable;
    }
    
    public void setMapZoom(float paramFloat)
    {
      mMapZoom = paramFloat;
      mSphericalMercatorProjection = new SphericalMercatorProjection(256.0D * Math.pow(2.0D, Math.min(paramFloat, mZoom)));
    }
    
    public void setProjection(Projection paramProjection)
    {
      mProjection = paramProjection;
    }
  }
  
  @SuppressLint({"HandlerLeak"})
  private class ViewModifier
    extends Handler
  {
    private static final int RUN_TASK = 0;
    private static final int TASK_FINISHED = 1;
    private DefaultClusterRenderer<T>.RenderTask mNextClusters = null;
    private boolean mViewModificationInProgress = false;
    
    private ViewModifier() {}
    
    public void handleMessage(Message paramMessage)
    {
      if (what == 1)
      {
        mViewModificationInProgress = false;
        if (mNextClusters != null) {
          sendEmptyMessage(0);
        }
        return;
      }
      removeMessages(0);
      if (mViewModificationInProgress) {
        return;
      }
      if (mNextClusters == null) {
        return;
      }
      paramMessage = mMap.getProjection();
      try
      {
        DefaultClusterRenderer.RenderTask localRenderTask = mNextClusters;
        mNextClusters = null;
        mViewModificationInProgress = true;
        localRenderTask.setCallback(new Runnable()
        {
          public void run()
          {
            sendEmptyMessage(1);
          }
        });
        localRenderTask.setProjection(paramMessage);
        localRenderTask.setMapZoom(mMap.getCameraPosition().zoom);
        new Thread(localRenderTask).start();
        return;
      }
      finally {}
    }
    
    public void queue(Set<? extends Cluster<T>> paramSet)
    {
      try
      {
        mNextClusters = new DefaultClusterRenderer.RenderTask(DefaultClusterRenderer.this, paramSet, null);
        sendEmptyMessage(0);
        return;
      }
      finally {}
    }
  }
}

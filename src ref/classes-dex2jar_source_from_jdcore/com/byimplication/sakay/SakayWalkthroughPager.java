package com.byimplication.sakay;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\013\n\000\n\002\020\002\n\002\b\003\n\002\030\002\n\002\b\003\030\0002\0020\001B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B\027\b\026\022\006\020\002\032\0020\003\022\006\020\005\032\0020\006¢\006\002\020\007J\006\020\n\032\0020\013J\006\020\f\032\0020\013J\020\020\r\032\0020\t2\006\020\016\032\0020\017H\026J\020\020\020\032\0020\t2\006\020\021\032\0020\017H\026R\016\020\b\032\0020\tX\016¢\006\002\n\000¨\006\022"}, d2={"Lcom/byimplication/sakay/SakayWalkthroughPager;", "Landroid/support/v4/view/ViewPager;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "swipeOverride", "", "disableSwiping", "", "enableSwiping", "onInterceptTouchEvent", "event", "Landroid/view/MotionEvent;", "onTouchEvent", "ev", "app_release"}, k=1, mv={1, 1, 9})
public final class SakayWalkthroughPager
  extends ViewPager
{
  private HashMap _$_findViewCache;
  private boolean swipeOverride;
  
  public SakayWalkthroughPager(@NotNull Context paramContext)
  {
    super(paramContext);
  }
  
  public SakayWalkthroughPager(@NotNull Context paramContext, @NotNull AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public void _$_clearFindViewByIdCache()
  {
    if (_$_findViewCache != null) {
      _$_findViewCache.clear();
    }
  }
  
  public View _$_findCachedViewById(int paramInt)
  {
    if (_$_findViewCache == null) {
      _$_findViewCache = new HashMap();
    }
    View localView2 = (View)_$_findViewCache.get(Integer.valueOf(paramInt));
    View localView1 = localView2;
    if (localView2 == null)
    {
      localView1 = findViewById(paramInt);
      _$_findViewCache.put(Integer.valueOf(paramInt), localView1);
    }
    return localView1;
  }
  
  public final void disableSwiping()
  {
    swipeOverride = true;
  }
  
  public final void enableSwiping()
  {
    swipeOverride = false;
  }
  
  public boolean onInterceptTouchEvent(@NotNull MotionEvent paramMotionEvent)
  {
    Intrinsics.checkParameterIsNotNull(paramMotionEvent, "event");
    if (swipeOverride) {
      return false;
    }
    try
    {
      boolean bool = super.onInterceptTouchEvent(paramMotionEvent);
      return bool;
    }
    catch (Exception paramMotionEvent) {}
    return false;
  }
  
  public boolean onTouchEvent(@NotNull MotionEvent paramMotionEvent)
  {
    Intrinsics.checkParameterIsNotNull(paramMotionEvent, "ev");
    if (swipeOverride) {
      return false;
    }
    try
    {
      boolean bool = super.onTouchEvent(paramMotionEvent);
      return bool;
    }
    catch (Exception paramMotionEvent) {}
    return false;
  }
}

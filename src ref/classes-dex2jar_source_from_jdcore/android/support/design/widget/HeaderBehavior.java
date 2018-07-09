package android.support.design.widget;

import android.content.Context;
import android.support.v4.math.MathUtils;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;

abstract class HeaderBehavior<V extends View>
  extends ViewOffsetBehavior<V>
{
  private static final int INVALID_POINTER = -1;
  private int mActivePointerId = -1;
  private Runnable mFlingRunnable;
  private boolean mIsBeingDragged;
  private int mLastMotionY;
  OverScroller mScroller;
  private int mTouchSlop = -1;
  private VelocityTracker mVelocityTracker;
  
  public HeaderBehavior() {}
  
  public HeaderBehavior(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private void ensureVelocityTracker()
  {
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
  }
  
  boolean canDragView(V paramV)
  {
    return false;
  }
  
  final boolean fling(CoordinatorLayout paramCoordinatorLayout, V paramV, int paramInt1, int paramInt2, float paramFloat)
  {
    if (mFlingRunnable != null)
    {
      paramV.removeCallbacks(mFlingRunnable);
      mFlingRunnable = null;
    }
    if (mScroller == null) {
      mScroller = new OverScroller(paramV.getContext());
    }
    mScroller.fling(0, getTopAndBottomOffset(), 0, Math.round(paramFloat), 0, 0, paramInt1, paramInt2);
    if (mScroller.computeScrollOffset())
    {
      mFlingRunnable = new FlingRunnable(paramCoordinatorLayout, paramV);
      ViewCompat.postOnAnimation(paramV, mFlingRunnable);
      return true;
    }
    onFlingFinished(paramCoordinatorLayout, paramV);
    return false;
  }
  
  int getMaxDragOffset(V paramV)
  {
    return -paramV.getHeight();
  }
  
  int getScrollRangeForDragFling(V paramV)
  {
    return paramV.getHeight();
  }
  
  int getTopBottomOffsetForScrollingSibling()
  {
    return getTopAndBottomOffset();
  }
  
  void onFlingFinished(CoordinatorLayout paramCoordinatorLayout, V paramV) {}
  
  public boolean onInterceptTouchEvent(CoordinatorLayout paramCoordinatorLayout, V paramV, MotionEvent paramMotionEvent)
  {
    if (mTouchSlop < 0) {
      mTouchSlop = ViewConfiguration.get(paramCoordinatorLayout.getContext()).getScaledTouchSlop();
    }
    if ((paramMotionEvent.getAction() == 2) && (mIsBeingDragged)) {
      return true;
    }
    int i;
    switch (paramMotionEvent.getActionMasked())
    {
    default: 
      break;
    case 2: 
      i = mActivePointerId;
      if (i != -1)
      {
        i = paramMotionEvent.findPointerIndex(i);
        if (i != -1)
        {
          i = (int)paramMotionEvent.getY(i);
          if (Math.abs(i - mLastMotionY) > mTouchSlop)
          {
            mIsBeingDragged = true;
            mLastMotionY = i;
          }
        }
      }
      break;
    case 1: 
    case 3: 
      mIsBeingDragged = false;
      mActivePointerId = -1;
      if (mVelocityTracker != null)
      {
        mVelocityTracker.recycle();
        mVelocityTracker = null;
      }
      break;
    case 0: 
      mIsBeingDragged = false;
      i = (int)paramMotionEvent.getX();
      int j = (int)paramMotionEvent.getY();
      if ((canDragView(paramV)) && (paramCoordinatorLayout.isPointInChildBounds(paramV, i, j)))
      {
        mLastMotionY = j;
        mActivePointerId = paramMotionEvent.getPointerId(0);
        ensureVelocityTracker();
      }
      break;
    }
    if (mVelocityTracker != null) {
      mVelocityTracker.addMovement(paramMotionEvent);
    }
    return mIsBeingDragged;
  }
  
  public boolean onTouchEvent(CoordinatorLayout paramCoordinatorLayout, V paramV, MotionEvent paramMotionEvent)
  {
    if (mTouchSlop < 0) {
      mTouchSlop = ViewConfiguration.get(paramCoordinatorLayout.getContext()).getScaledTouchSlop();
    }
    switch (paramMotionEvent.getActionMasked())
    {
    default: 
      break;
    case 2: 
      i = paramMotionEvent.findPointerIndex(mActivePointerId);
      if (i == -1) {
        return false;
      }
      int k = (int)paramMotionEvent.getY(i);
      j = mLastMotionY - k;
      i = j;
      if (!mIsBeingDragged)
      {
        i = j;
        if (Math.abs(j) > mTouchSlop)
        {
          mIsBeingDragged = true;
          if (j > 0) {
            i = j - mTouchSlop;
          } else {
            i = j + mTouchSlop;
          }
        }
      }
      if (!mIsBeingDragged) {
        break label328;
      }
      mLastMotionY = k;
      scroll(paramCoordinatorLayout, paramV, i, getMaxDragOffset(paramV), 0);
      break;
    case 1: 
      if (mVelocityTracker != null)
      {
        mVelocityTracker.addMovement(paramMotionEvent);
        mVelocityTracker.computeCurrentVelocity(1000);
        float f = mVelocityTracker.getYVelocity(mActivePointerId);
        fling(paramCoordinatorLayout, paramV, -getScrollRangeForDragFling(paramV), 0, f);
      }
    case 3: 
      mIsBeingDragged = false;
      mActivePointerId = -1;
      if (mVelocityTracker == null) {
        break label328;
      }
      mVelocityTracker.recycle();
      mVelocityTracker = null;
      break;
    }
    int i = (int)paramMotionEvent.getX();
    int j = (int)paramMotionEvent.getY();
    if ((paramCoordinatorLayout.isPointInChildBounds(paramV, i, j)) && (canDragView(paramV)))
    {
      mLastMotionY = j;
      mActivePointerId = paramMotionEvent.getPointerId(0);
      ensureVelocityTracker();
    }
    else
    {
      return false;
    }
    label328:
    if (mVelocityTracker != null) {
      mVelocityTracker.addMovement(paramMotionEvent);
    }
    return true;
  }
  
  final int scroll(CoordinatorLayout paramCoordinatorLayout, V paramV, int paramInt1, int paramInt2, int paramInt3)
  {
    return setHeaderTopBottomOffset(paramCoordinatorLayout, paramV, getTopBottomOffsetForScrollingSibling() - paramInt1, paramInt2, paramInt3);
  }
  
  int setHeaderTopBottomOffset(CoordinatorLayout paramCoordinatorLayout, V paramV, int paramInt)
  {
    return setHeaderTopBottomOffset(paramCoordinatorLayout, paramV, paramInt, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }
  
  int setHeaderTopBottomOffset(CoordinatorLayout paramCoordinatorLayout, V paramV, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = getTopAndBottomOffset();
    if ((paramInt2 != 0) && (i >= paramInt2) && (i <= paramInt3))
    {
      paramInt1 = MathUtils.clamp(paramInt1, paramInt2, paramInt3);
      if (i != paramInt1)
      {
        setTopAndBottomOffset(paramInt1);
        return i - paramInt1;
      }
    }
    return 0;
  }
  
  private class FlingRunnable
    implements Runnable
  {
    private final V mLayout;
    private final CoordinatorLayout mParent;
    
    FlingRunnable(V paramV)
    {
      mParent = paramV;
      Object localObject;
      mLayout = localObject;
    }
    
    public void run()
    {
      if ((mLayout != null) && (mScroller != null))
      {
        if (mScroller.computeScrollOffset())
        {
          setHeaderTopBottomOffset(mParent, mLayout, mScroller.getCurrY());
          ViewCompat.postOnAnimation(mLayout, this);
          return;
        }
        onFlingFinished(mParent, mLayout);
      }
    }
  }
}

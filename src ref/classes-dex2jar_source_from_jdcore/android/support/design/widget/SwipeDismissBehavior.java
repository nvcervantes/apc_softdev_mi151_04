package android.support.design.widget;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SwipeDismissBehavior<V extends View>
  extends CoordinatorLayout.Behavior<V>
{
  private static final float DEFAULT_ALPHA_END_DISTANCE = 0.5F;
  private static final float DEFAULT_ALPHA_START_DISTANCE = 0.0F;
  private static final float DEFAULT_DRAG_DISMISS_THRESHOLD = 0.5F;
  public static final int STATE_DRAGGING = 1;
  public static final int STATE_IDLE = 0;
  public static final int STATE_SETTLING = 2;
  public static final int SWIPE_DIRECTION_ANY = 2;
  public static final int SWIPE_DIRECTION_END_TO_START = 1;
  public static final int SWIPE_DIRECTION_START_TO_END = 0;
  float mAlphaEndSwipeDistance = 0.5F;
  float mAlphaStartSwipeDistance = 0.0F;
  private final ViewDragHelper.Callback mDragCallback = new ViewDragHelper.Callback()
  {
    private static final int INVALID_POINTER_ID = -1;
    private int mActivePointerId = -1;
    private int mOriginalCapturedViewLeft;
    
    private boolean shouldDismiss(View paramAnonymousView, float paramAnonymousFloat)
    {
      boolean bool = false;
      if (paramAnonymousFloat != 0.0F)
      {
        if (ViewCompat.getLayoutDirection(paramAnonymousView) == 1) {
          i = 1;
        } else {
          i = 0;
        }
        if (mSwipeDirection == 2) {
          return true;
        }
        if (mSwipeDirection == 0)
        {
          if (i != 0)
          {
            if (paramAnonymousFloat >= 0.0F) {}
          }
          else {
            while (paramAnonymousFloat > 0.0F) {
              return true;
            }
          }
          return false;
        }
        if (mSwipeDirection == 1)
        {
          if (i != 0)
          {
            if (paramAnonymousFloat <= 0.0F) {}
          }
          else {
            while (paramAnonymousFloat < 0.0F) {
              return true;
            }
          }
          return false;
        }
        return false;
      }
      int i = paramAnonymousView.getLeft();
      int j = mOriginalCapturedViewLeft;
      int k = Math.round(paramAnonymousView.getWidth() * mDragDismissThreshold);
      if (Math.abs(i - j) >= k) {
        bool = true;
      }
      return bool;
    }
    
    public int clampViewPositionHorizontal(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      if (ViewCompat.getLayoutDirection(paramAnonymousView) == 1) {
        paramAnonymousInt2 = 1;
      } else {
        paramAnonymousInt2 = 0;
      }
      int i;
      if (mSwipeDirection == 0)
      {
        if (paramAnonymousInt2 != 0)
        {
          i = mOriginalCapturedViewLeft - paramAnonymousView.getWidth();
          paramAnonymousInt2 = mOriginalCapturedViewLeft;
        }
        else
        {
          i = mOriginalCapturedViewLeft;
          paramAnonymousInt2 = mOriginalCapturedViewLeft;
          paramAnonymousInt2 = paramAnonymousView.getWidth() + paramAnonymousInt2;
        }
      }
      else if (mSwipeDirection == 1)
      {
        if (paramAnonymousInt2 != 0)
        {
          i = mOriginalCapturedViewLeft;
          paramAnonymousInt2 = mOriginalCapturedViewLeft;
          paramAnonymousInt2 = paramAnonymousView.getWidth() + paramAnonymousInt2;
        }
        else
        {
          i = mOriginalCapturedViewLeft - paramAnonymousView.getWidth();
          paramAnonymousInt2 = mOriginalCapturedViewLeft;
        }
      }
      else
      {
        i = mOriginalCapturedViewLeft - paramAnonymousView.getWidth();
        paramAnonymousInt2 = mOriginalCapturedViewLeft;
        paramAnonymousInt2 = paramAnonymousView.getWidth() + paramAnonymousInt2;
      }
      return SwipeDismissBehavior.clamp(i, paramAnonymousInt1, paramAnonymousInt2);
    }
    
    public int clampViewPositionVertical(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      return paramAnonymousView.getTop();
    }
    
    public int getViewHorizontalDragRange(View paramAnonymousView)
    {
      return paramAnonymousView.getWidth();
    }
    
    public void onViewCaptured(View paramAnonymousView, int paramAnonymousInt)
    {
      mActivePointerId = paramAnonymousInt;
      mOriginalCapturedViewLeft = paramAnonymousView.getLeft();
      paramAnonymousView = paramAnonymousView.getParent();
      if (paramAnonymousView != null) {
        paramAnonymousView.requestDisallowInterceptTouchEvent(true);
      }
    }
    
    public void onViewDragStateChanged(int paramAnonymousInt)
    {
      if (mListener != null) {
        mListener.onDragStateChanged(paramAnonymousInt);
      }
    }
    
    public void onViewPositionChanged(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
    {
      float f1 = mOriginalCapturedViewLeft + paramAnonymousView.getWidth() * mAlphaStartSwipeDistance;
      float f2 = mOriginalCapturedViewLeft + paramAnonymousView.getWidth() * mAlphaEndSwipeDistance;
      float f3 = paramAnonymousInt1;
      if (f3 <= f1)
      {
        paramAnonymousView.setAlpha(1.0F);
        return;
      }
      if (f3 >= f2)
      {
        paramAnonymousView.setAlpha(0.0F);
        return;
      }
      paramAnonymousView.setAlpha(SwipeDismissBehavior.clamp(0.0F, 1.0F - SwipeDismissBehavior.fraction(f1, f2, f3), 1.0F));
    }
    
    public void onViewReleased(View paramAnonymousView, float paramAnonymousFloat1, float paramAnonymousFloat2)
    {
      mActivePointerId = -1;
      int i = paramAnonymousView.getWidth();
      boolean bool;
      if (shouldDismiss(paramAnonymousView, paramAnonymousFloat1))
      {
        if (paramAnonymousView.getLeft() < mOriginalCapturedViewLeft) {
          i = mOriginalCapturedViewLeft - i;
        } else {
          i = mOriginalCapturedViewLeft + i;
        }
        bool = true;
      }
      else
      {
        i = mOriginalCapturedViewLeft;
        bool = false;
      }
      if (mViewDragHelper.settleCapturedViewAt(i, paramAnonymousView.getTop()))
      {
        ViewCompat.postOnAnimation(paramAnonymousView, new SwipeDismissBehavior.SettleRunnable(SwipeDismissBehavior.this, paramAnonymousView, bool));
        return;
      }
      if ((bool) && (mListener != null)) {
        mListener.onDismiss(paramAnonymousView);
      }
    }
    
    public boolean tryCaptureView(View paramAnonymousView, int paramAnonymousInt)
    {
      return (mActivePointerId == -1) && (canSwipeDismissView(paramAnonymousView));
    }
  };
  float mDragDismissThreshold = 0.5F;
  private boolean mInterceptingEvents;
  OnDismissListener mListener;
  private float mSensitivity = 0.0F;
  private boolean mSensitivitySet;
  int mSwipeDirection = 2;
  ViewDragHelper mViewDragHelper;
  
  public SwipeDismissBehavior() {}
  
  static float clamp(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return Math.min(Math.max(paramFloat1, paramFloat2), paramFloat3);
  }
  
  static int clamp(int paramInt1, int paramInt2, int paramInt3)
  {
    return Math.min(Math.max(paramInt1, paramInt2), paramInt3);
  }
  
  private void ensureViewDragHelper(ViewGroup paramViewGroup)
  {
    if (mViewDragHelper == null)
    {
      if (mSensitivitySet) {
        paramViewGroup = ViewDragHelper.create(paramViewGroup, mSensitivity, mDragCallback);
      } else {
        paramViewGroup = ViewDragHelper.create(paramViewGroup, mDragCallback);
      }
      mViewDragHelper = paramViewGroup;
    }
  }
  
  static float fraction(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return (paramFloat3 - paramFloat1) / (paramFloat2 - paramFloat1);
  }
  
  public boolean canSwipeDismissView(@NonNull View paramView)
  {
    return true;
  }
  
  public int getDragState()
  {
    if (mViewDragHelper != null) {
      return mViewDragHelper.getViewDragState();
    }
    return 0;
  }
  
  public boolean onInterceptTouchEvent(CoordinatorLayout paramCoordinatorLayout, V paramV, MotionEvent paramMotionEvent)
  {
    boolean bool = mInterceptingEvents;
    int i = paramMotionEvent.getActionMasked();
    if (i != 3) {
      switch (i)
      {
      default: 
        break;
      case 0: 
        mInterceptingEvents = paramCoordinatorLayout.isPointInChildBounds(paramV, (int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
        bool = mInterceptingEvents;
        break;
      }
    } else {
      mInterceptingEvents = false;
    }
    if (bool)
    {
      ensureViewDragHelper(paramCoordinatorLayout);
      return mViewDragHelper.shouldInterceptTouchEvent(paramMotionEvent);
    }
    return false;
  }
  
  public boolean onTouchEvent(CoordinatorLayout paramCoordinatorLayout, V paramV, MotionEvent paramMotionEvent)
  {
    if (mViewDragHelper != null)
    {
      mViewDragHelper.processTouchEvent(paramMotionEvent);
      return true;
    }
    return false;
  }
  
  public void setDragDismissDistance(float paramFloat)
  {
    mDragDismissThreshold = clamp(0.0F, paramFloat, 1.0F);
  }
  
  public void setEndAlphaSwipeDistance(float paramFloat)
  {
    mAlphaEndSwipeDistance = clamp(0.0F, paramFloat, 1.0F);
  }
  
  public void setListener(OnDismissListener paramOnDismissListener)
  {
    mListener = paramOnDismissListener;
  }
  
  public void setSensitivity(float paramFloat)
  {
    mSensitivity = paramFloat;
    mSensitivitySet = true;
  }
  
  public void setStartAlphaSwipeDistance(float paramFloat)
  {
    mAlphaStartSwipeDistance = clamp(0.0F, paramFloat, 1.0F);
  }
  
  public void setSwipeDirection(int paramInt)
  {
    mSwipeDirection = paramInt;
  }
  
  public static abstract interface OnDismissListener
  {
    public abstract void onDismiss(View paramView);
    
    public abstract void onDragStateChanged(int paramInt);
  }
  
  private class SettleRunnable
    implements Runnable
  {
    private final boolean mDismiss;
    private final View mView;
    
    SettleRunnable(View paramView, boolean paramBoolean)
    {
      mView = paramView;
      mDismiss = paramBoolean;
    }
    
    public void run()
    {
      if ((mViewDragHelper != null) && (mViewDragHelper.continueSettling(true)))
      {
        ViewCompat.postOnAnimation(mView, this);
        return;
      }
      if ((mDismiss) && (mListener != null)) {
        mListener.onDismiss(mView);
      }
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  private static @interface SwipeDirection {}
}

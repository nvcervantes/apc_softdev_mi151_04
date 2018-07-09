package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.design.R.dimen;
import android.support.design.R.styleable;
import android.support.v4.math.MathUtils;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

public class BottomSheetBehavior<V extends View>
  extends CoordinatorLayout.Behavior<V>
{
  private static final float HIDE_FRICTION = 0.1F;
  private static final float HIDE_THRESHOLD = 0.5F;
  public static final int PEEK_HEIGHT_AUTO = -1;
  public static final int STATE_COLLAPSED = 4;
  public static final int STATE_DRAGGING = 1;
  public static final int STATE_EXPANDED = 3;
  public static final int STATE_HIDDEN = 5;
  public static final int STATE_SETTLING = 2;
  int mActivePointerId;
  private BottomSheetCallback mCallback;
  private final ViewDragHelper.Callback mDragCallback = new ViewDragHelper.Callback()
  {
    public int clampViewPositionHorizontal(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      return paramAnonymousView.getLeft();
    }
    
    public int clampViewPositionVertical(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      int i = mMinOffset;
      if (mHideable) {
        paramAnonymousInt2 = mParentHeight;
      } else {
        paramAnonymousInt2 = mMaxOffset;
      }
      return MathUtils.clamp(paramAnonymousInt1, i, paramAnonymousInt2);
    }
    
    public int getViewVerticalDragRange(View paramAnonymousView)
    {
      if (mHideable) {
        return mParentHeight - mMinOffset;
      }
      return mMaxOffset - mMinOffset;
    }
    
    public void onViewDragStateChanged(int paramAnonymousInt)
    {
      if (paramAnonymousInt == 1) {
        setStateInternal(1);
      }
    }
    
    public void onViewPositionChanged(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
    {
      dispatchOnSlide(paramAnonymousInt2);
    }
    
    public void onViewReleased(View paramAnonymousView, float paramAnonymousFloat1, float paramAnonymousFloat2)
    {
      int j = 4;
      if (paramAnonymousFloat2 < 0.0F) {}
      for (int i = mMinOffset;; i = mMinOffset)
      {
        j = 3;
        break label135;
        if ((mHideable) && (shouldHide(paramAnonymousView, paramAnonymousFloat2)))
        {
          i = mParentHeight;
          j = 5;
          break label135;
        }
        if (paramAnonymousFloat2 != 0.0F) {
          break label126;
        }
        i = paramAnonymousView.getTop();
        if (Math.abs(i - mMinOffset) >= Math.abs(i - mMaxOffset)) {
          break;
        }
      }
      i = mMaxOffset;
      break label135;
      label126:
      i = mMaxOffset;
      label135:
      if (mViewDragHelper.settleCapturedViewAt(paramAnonymousView.getLeft(), i))
      {
        setStateInternal(2);
        ViewCompat.postOnAnimation(paramAnonymousView, new BottomSheetBehavior.SettleRunnable(BottomSheetBehavior.this, paramAnonymousView, j));
        return;
      }
      setStateInternal(j);
    }
    
    public boolean tryCaptureView(View paramAnonymousView, int paramAnonymousInt)
    {
      if (mState == 1) {
        return false;
      }
      if (mTouchingScrollingChild) {
        return false;
      }
      if ((mState == 3) && (mActivePointerId == paramAnonymousInt))
      {
        View localView = (View)mNestedScrollingChildRef.get();
        if ((localView != null) && (localView.canScrollVertically(-1))) {
          return false;
        }
      }
      return (mViewRef != null) && (mViewRef.get() == paramAnonymousView);
    }
  };
  boolean mHideable;
  private boolean mIgnoreEvents;
  private int mInitialY;
  private int mLastNestedScrollDy;
  int mMaxOffset;
  private float mMaximumVelocity;
  int mMinOffset;
  private boolean mNestedScrolled;
  WeakReference<View> mNestedScrollingChildRef;
  int mParentHeight;
  private int mPeekHeight;
  private boolean mPeekHeightAuto;
  private int mPeekHeightMin;
  private boolean mSkipCollapsed;
  int mState = 4;
  boolean mTouchingScrollingChild;
  private VelocityTracker mVelocityTracker;
  ViewDragHelper mViewDragHelper;
  WeakReference<V> mViewRef;
  
  public BottomSheetBehavior() {}
  
  public BottomSheetBehavior(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.BottomSheetBehavior_Layout);
    TypedValue localTypedValue = paramAttributeSet.peekValue(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
    if ((localTypedValue != null) && (data == -1)) {
      setPeekHeight(data);
    } else {
      setPeekHeight(paramAttributeSet.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
    }
    setHideable(paramAttributeSet.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
    setSkipCollapsed(paramAttributeSet.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
    paramAttributeSet.recycle();
    mMaximumVelocity = ViewConfiguration.get(paramContext).getScaledMaximumFlingVelocity();
  }
  
  public static <V extends View> BottomSheetBehavior<V> from(V paramV)
  {
    paramV = paramV.getLayoutParams();
    if (!(paramV instanceof CoordinatorLayout.LayoutParams)) {
      throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }
    paramV = ((CoordinatorLayout.LayoutParams)paramV).getBehavior();
    if (!(paramV instanceof BottomSheetBehavior)) {
      throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
    }
    return (BottomSheetBehavior)paramV;
  }
  
  private float getYVelocity()
  {
    mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
    return mVelocityTracker.getYVelocity(mActivePointerId);
  }
  
  private void reset()
  {
    mActivePointerId = -1;
    if (mVelocityTracker != null)
    {
      mVelocityTracker.recycle();
      mVelocityTracker = null;
    }
  }
  
  void dispatchOnSlide(int paramInt)
  {
    View localView = (View)mViewRef.get();
    if ((localView != null) && (mCallback != null))
    {
      if (paramInt > mMaxOffset)
      {
        mCallback.onSlide(localView, (mMaxOffset - paramInt) / (mParentHeight - mMaxOffset));
        return;
      }
      mCallback.onSlide(localView, (mMaxOffset - paramInt) / (mMaxOffset - mMinOffset));
    }
  }
  
  @VisibleForTesting
  View findScrollingChild(View paramView)
  {
    if (ViewCompat.isNestedScrollingEnabled(paramView)) {
      return paramView;
    }
    if ((paramView instanceof ViewGroup))
    {
      paramView = (ViewGroup)paramView;
      int i = 0;
      int j = paramView.getChildCount();
      while (i < j)
      {
        View localView = findScrollingChild(paramView.getChildAt(i));
        if (localView != null) {
          return localView;
        }
        i += 1;
      }
    }
    return null;
  }
  
  public final int getPeekHeight()
  {
    if (mPeekHeightAuto) {
      return -1;
    }
    return mPeekHeight;
  }
  
  @VisibleForTesting
  int getPeekHeightMin()
  {
    return mPeekHeightMin;
  }
  
  public boolean getSkipCollapsed()
  {
    return mSkipCollapsed;
  }
  
  public final int getState()
  {
    return mState;
  }
  
  public boolean isHideable()
  {
    return mHideable;
  }
  
  public boolean onInterceptTouchEvent(CoordinatorLayout paramCoordinatorLayout, V paramV, MotionEvent paramMotionEvent)
  {
    boolean bool1 = paramV.isShown();
    boolean bool2 = false;
    if (!bool1)
    {
      mIgnoreEvents = true;
      return false;
    }
    int i = paramMotionEvent.getActionMasked();
    if (i == 0) {
      reset();
    }
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
    mVelocityTracker.addMovement(paramMotionEvent);
    if (i != 3)
    {
      switch (i)
      {
      default: 
        break;
      case 0: 
        int j = (int)paramMotionEvent.getX();
        mInitialY = ((int)paramMotionEvent.getY());
        View localView;
        if (mNestedScrollingChildRef != null) {
          localView = (View)mNestedScrollingChildRef.get();
        } else {
          localView = null;
        }
        if ((localView != null) && (paramCoordinatorLayout.isPointInChildBounds(localView, j, mInitialY)))
        {
          mActivePointerId = paramMotionEvent.getPointerId(paramMotionEvent.getActionIndex());
          mTouchingScrollingChild = true;
        }
        if ((mActivePointerId == -1) && (!paramCoordinatorLayout.isPointInChildBounds(paramV, j, mInitialY))) {
          bool1 = true;
        } else {
          bool1 = false;
        }
        mIgnoreEvents = bool1;
        break;
      }
    }
    else
    {
      mTouchingScrollingChild = false;
      mActivePointerId = -1;
      if (mIgnoreEvents)
      {
        mIgnoreEvents = false;
        return false;
      }
    }
    if ((!mIgnoreEvents) && (mViewDragHelper.shouldInterceptTouchEvent(paramMotionEvent))) {
      return true;
    }
    paramV = (View)mNestedScrollingChildRef.get();
    bool1 = bool2;
    if (i == 2)
    {
      bool1 = bool2;
      if (paramV != null)
      {
        bool1 = bool2;
        if (!mIgnoreEvents)
        {
          bool1 = bool2;
          if (mState != 1)
          {
            bool1 = bool2;
            if (!paramCoordinatorLayout.isPointInChildBounds(paramV, (int)paramMotionEvent.getX(), (int)paramMotionEvent.getY()))
            {
              bool1 = bool2;
              if (Math.abs(mInitialY - paramMotionEvent.getY()) > mViewDragHelper.getTouchSlop()) {
                bool1 = true;
              }
            }
          }
        }
      }
    }
    return bool1;
  }
  
  public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, V paramV, int paramInt)
  {
    if ((ViewCompat.getFitsSystemWindows(paramCoordinatorLayout)) && (!ViewCompat.getFitsSystemWindows(paramV))) {
      ViewCompat.setFitsSystemWindows(paramV, true);
    }
    int i = paramV.getTop();
    paramCoordinatorLayout.onLayoutChild(paramV, paramInt);
    mParentHeight = paramCoordinatorLayout.getHeight();
    if (mPeekHeightAuto)
    {
      if (mPeekHeightMin == 0) {
        mPeekHeightMin = paramCoordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
      }
      paramInt = Math.max(mPeekHeightMin, mParentHeight - paramCoordinatorLayout.getWidth() * 9 / 16);
    }
    else
    {
      paramInt = mPeekHeight;
    }
    mMinOffset = Math.max(0, mParentHeight - paramV.getHeight());
    mMaxOffset = Math.max(mParentHeight - paramInt, mMinOffset);
    if (mState == 3) {
      ViewCompat.offsetTopAndBottom(paramV, mMinOffset);
    } else if ((mHideable) && (mState == 5)) {
      ViewCompat.offsetTopAndBottom(paramV, mParentHeight);
    } else if (mState == 4) {
      ViewCompat.offsetTopAndBottom(paramV, mMaxOffset);
    } else if ((mState == 1) || (mState == 2)) {
      ViewCompat.offsetTopAndBottom(paramV, i - paramV.getTop());
    }
    if (mViewDragHelper == null) {
      mViewDragHelper = ViewDragHelper.create(paramCoordinatorLayout, mDragCallback);
    }
    mViewRef = new WeakReference(paramV);
    mNestedScrollingChildRef = new WeakReference(findScrollingChild(paramV));
    return true;
  }
  
  public boolean onNestedPreFling(CoordinatorLayout paramCoordinatorLayout, V paramV, View paramView, float paramFloat1, float paramFloat2)
  {
    return (paramView == mNestedScrollingChildRef.get()) && ((mState != 3) || (super.onNestedPreFling(paramCoordinatorLayout, paramV, paramView, paramFloat1, paramFloat2)));
  }
  
  public void onNestedPreScroll(CoordinatorLayout paramCoordinatorLayout, V paramV, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    if (paramView != (View)mNestedScrollingChildRef.get()) {
      return;
    }
    paramInt1 = paramV.getTop();
    int i = paramInt1 - paramInt2;
    if (paramInt2 > 0)
    {
      if (i < mMinOffset)
      {
        paramArrayOfInt[1] = (paramInt1 - mMinOffset);
        ViewCompat.offsetTopAndBottom(paramV, -paramArrayOfInt[1]);
        setStateInternal(3);
      }
      else
      {
        paramArrayOfInt[1] = paramInt2;
        ViewCompat.offsetTopAndBottom(paramV, -paramInt2);
        setStateInternal(1);
      }
    }
    else if ((paramInt2 < 0) && (!paramView.canScrollVertically(-1))) {
      if ((i > mMaxOffset) && (!mHideable))
      {
        paramArrayOfInt[1] = (paramInt1 - mMaxOffset);
        ViewCompat.offsetTopAndBottom(paramV, -paramArrayOfInt[1]);
        setStateInternal(4);
      }
      else
      {
        paramArrayOfInt[1] = paramInt2;
        ViewCompat.offsetTopAndBottom(paramV, -paramInt2);
        setStateInternal(1);
      }
    }
    dispatchOnSlide(paramV.getTop());
    mLastNestedScrollDy = paramInt2;
    mNestedScrolled = true;
  }
  
  public void onRestoreInstanceState(CoordinatorLayout paramCoordinatorLayout, V paramV, Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramCoordinatorLayout, paramV, paramParcelable.getSuperState());
    if ((state != 1) && (state != 2))
    {
      mState = state;
      return;
    }
    mState = 4;
  }
  
  public Parcelable onSaveInstanceState(CoordinatorLayout paramCoordinatorLayout, V paramV)
  {
    return new SavedState(super.onSaveInstanceState(paramCoordinatorLayout, paramV), mState);
  }
  
  public boolean onStartNestedScroll(CoordinatorLayout paramCoordinatorLayout, V paramV, View paramView1, View paramView2, int paramInt)
  {
    boolean bool = false;
    mLastNestedScrollDy = 0;
    mNestedScrolled = false;
    if ((paramInt & 0x2) != 0) {
      bool = true;
    }
    return bool;
  }
  
  public void onStopNestedScroll(CoordinatorLayout paramCoordinatorLayout, V paramV, View paramView)
  {
    int i = paramV.getTop();
    int k = mMinOffset;
    int j = 3;
    if (i == k)
    {
      setStateInternal(3);
      return;
    }
    if ((mNestedScrollingChildRef != null) && (paramView == mNestedScrollingChildRef.get()))
    {
      if (!mNestedScrolled) {
        return;
      }
      if (mLastNestedScrollDy > 0)
      {
        i = mMinOffset;
      }
      else if ((mHideable) && (shouldHide(paramV, getYVelocity())))
      {
        i = mParentHeight;
        j = 5;
      }
      else
      {
        if (mLastNestedScrollDy == 0)
        {
          i = paramV.getTop();
          if (Math.abs(i - mMinOffset) < Math.abs(i - mMaxOffset))
          {
            i = mMinOffset;
            break label164;
          }
          i = mMaxOffset;
        }
        else
        {
          i = mMaxOffset;
        }
        j = 4;
      }
      label164:
      if (mViewDragHelper.smoothSlideViewTo(paramV, paramV.getLeft(), i))
      {
        setStateInternal(2);
        ViewCompat.postOnAnimation(paramV, new SettleRunnable(paramV, j));
      }
      else
      {
        setStateInternal(j);
      }
      mNestedScrolled = false;
      return;
    }
  }
  
  public boolean onTouchEvent(CoordinatorLayout paramCoordinatorLayout, V paramV, MotionEvent paramMotionEvent)
  {
    if (!paramV.isShown()) {
      return false;
    }
    int i = paramMotionEvent.getActionMasked();
    if ((mState == 1) && (i == 0)) {
      return true;
    }
    if (mViewDragHelper != null) {
      mViewDragHelper.processTouchEvent(paramMotionEvent);
    }
    if (i == 0) {
      reset();
    }
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
    mVelocityTracker.addMovement(paramMotionEvent);
    if ((i == 2) && (!mIgnoreEvents) && (Math.abs(mInitialY - paramMotionEvent.getY()) > mViewDragHelper.getTouchSlop())) {
      mViewDragHelper.captureChildView(paramV, paramMotionEvent.getPointerId(paramMotionEvent.getActionIndex()));
    }
    return mIgnoreEvents ^ true;
  }
  
  public void setBottomSheetCallback(BottomSheetCallback paramBottomSheetCallback)
  {
    mCallback = paramBottomSheetCallback;
  }
  
  public void setHideable(boolean paramBoolean)
  {
    mHideable = paramBoolean;
  }
  
  public final void setPeekHeight(int paramInt)
  {
    int i = 1;
    if (paramInt == -1)
    {
      if (!mPeekHeightAuto)
      {
        mPeekHeightAuto = true;
        paramInt = i;
        break label73;
      }
    }
    else {
      if ((mPeekHeightAuto) || (mPeekHeight != paramInt)) {
        break label47;
      }
    }
    paramInt = 0;
    break label73;
    label47:
    mPeekHeightAuto = false;
    mPeekHeight = Math.max(0, paramInt);
    mMaxOffset = (mParentHeight - paramInt);
    paramInt = i;
    label73:
    if ((paramInt != 0) && (mState == 4) && (mViewRef != null))
    {
      View localView = (View)mViewRef.get();
      if (localView != null) {
        localView.requestLayout();
      }
    }
  }
  
  public void setSkipCollapsed(boolean paramBoolean)
  {
    mSkipCollapsed = paramBoolean;
  }
  
  public final void setState(final int paramInt)
  {
    if (paramInt == mState) {
      return;
    }
    if (mViewRef == null)
    {
      if ((paramInt == 4) || (paramInt == 3) || ((mHideable) && (paramInt == 5))) {
        mState = paramInt;
      }
      return;
    }
    final View localView = (View)mViewRef.get();
    if (localView == null) {
      return;
    }
    ViewParent localViewParent = localView.getParent();
    if ((localViewParent != null) && (localViewParent.isLayoutRequested()) && (ViewCompat.isAttachedToWindow(localView)))
    {
      localView.post(new Runnable()
      {
        public void run()
        {
          startSettlingAnimation(localView, paramInt);
        }
      });
      return;
    }
    startSettlingAnimation(localView, paramInt);
  }
  
  void setStateInternal(int paramInt)
  {
    if (mState == paramInt) {
      return;
    }
    mState = paramInt;
    View localView = (View)mViewRef.get();
    if ((localView != null) && (mCallback != null)) {
      mCallback.onStateChanged(localView, paramInt);
    }
  }
  
  boolean shouldHide(View paramView, float paramFloat)
  {
    if (mSkipCollapsed) {
      return true;
    }
    if (paramView.getTop() < mMaxOffset) {
      return false;
    }
    return Math.abs(paramView.getTop() + paramFloat * 0.1F - mMaxOffset) / mPeekHeight > 0.5F;
  }
  
  void startSettlingAnimation(View paramView, int paramInt)
  {
    int i;
    if (paramInt == 4)
    {
      i = mMaxOffset;
    }
    else if (paramInt == 3)
    {
      i = mMinOffset;
    }
    else
    {
      if ((!mHideable) || (paramInt != 5)) {
        break label85;
      }
      i = mParentHeight;
    }
    if (mViewDragHelper.smoothSlideViewTo(paramView, paramView.getLeft(), i))
    {
      setStateInternal(2);
      ViewCompat.postOnAnimation(paramView, new SettleRunnable(paramView, paramInt));
      return;
    }
    setStateInternal(paramInt);
    return;
    label85:
    paramView = new StringBuilder();
    paramView.append("Illegal state argument: ");
    paramView.append(paramInt);
    throw new IllegalArgumentException(paramView.toString());
  }
  
  public static abstract class BottomSheetCallback
  {
    public BottomSheetCallback() {}
    
    public abstract void onSlide(@NonNull View paramView, float paramFloat);
    
    public abstract void onStateChanged(@NonNull View paramView, int paramInt);
  }
  
  protected static class SavedState
    extends AbsSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator()
    {
      public BottomSheetBehavior.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new BottomSheetBehavior.SavedState(paramAnonymousParcel, null);
      }
      
      public BottomSheetBehavior.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
      {
        return new BottomSheetBehavior.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
      }
      
      public BottomSheetBehavior.SavedState[] newArray(int paramAnonymousInt)
      {
        return new BottomSheetBehavior.SavedState[paramAnonymousInt];
      }
    };
    final int state;
    
    public SavedState(Parcel paramParcel)
    {
      this(paramParcel, null);
    }
    
    public SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      state = paramParcel.readInt();
    }
    
    public SavedState(Parcelable paramParcelable, int paramInt)
    {
      super();
      state = paramInt;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(state);
    }
  }
  
  private class SettleRunnable
    implements Runnable
  {
    private final int mTargetState;
    private final View mView;
    
    SettleRunnable(View paramView, int paramInt)
    {
      mView = paramView;
      mTargetState = paramInt;
    }
    
    public void run()
    {
      if ((mViewDragHelper != null) && (mViewDragHelper.continueSettling(true)))
      {
        ViewCompat.postOnAnimation(mView, this);
        return;
      }
      setStateInternal(mTargetState);
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface State {}
}

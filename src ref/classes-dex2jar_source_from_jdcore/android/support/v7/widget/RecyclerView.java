package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.os.TraceCompat;
import android.support.v4.util.Preconditions;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.support.v7.recyclerview.R.dimen;
import android.support.v7.recyclerview.R.styleable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerView
  extends ViewGroup
  implements ScrollingView, NestedScrollingChild2
{
  static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC;
  private static final boolean ALLOW_THREAD_GAP_WORK;
  private static final int[] CLIP_TO_PADDING_ATTR;
  static final boolean DEBUG = false;
  static final int DEFAULT_ORIENTATION = 1;
  static final boolean DISPATCH_TEMP_DETACH = false;
  private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION;
  static final boolean FORCE_INVALIDATE_DISPLAY_LIST;
  static final long FOREVER_NS = Long.MAX_VALUE;
  public static final int HORIZONTAL = 0;
  private static final boolean IGNORE_DETACHED_FOCUSED_CHILD;
  private static final int INVALID_POINTER = -1;
  public static final int INVALID_TYPE = -1;
  private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = { Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE };
  static final int MAX_SCROLL_DURATION = 2000;
  private static final int[] NESTED_SCROLLING_ATTRS = { 16843830 };
  public static final long NO_ID = -1L;
  public static final int NO_POSITION = -1;
  static final boolean POST_UPDATES_ON_ANIMATION;
  public static final int SCROLL_STATE_DRAGGING = 1;
  public static final int SCROLL_STATE_IDLE = 0;
  public static final int SCROLL_STATE_SETTLING = 2;
  static final String TAG = "RecyclerView";
  public static final int TOUCH_SLOP_DEFAULT = 0;
  public static final int TOUCH_SLOP_PAGING = 1;
  static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
  static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
  private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
  static final String TRACE_NESTED_PREFETCH_TAG = "RV Nested Prefetch";
  private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
  private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
  static final String TRACE_PREFETCH_TAG = "RV Prefetch";
  static final String TRACE_SCROLL_TAG = "RV Scroll";
  static final boolean VERBOSE_TRACING = false;
  public static final int VERTICAL = 1;
  static final Interpolator sQuinticInterpolator = new Interpolator()
  {
    public float getInterpolation(float paramAnonymousFloat)
    {
      paramAnonymousFloat -= 1.0F;
      return paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat + 1.0F;
    }
  };
  RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
  private final AccessibilityManager mAccessibilityManager;
  private OnItemTouchListener mActiveOnItemTouchListener;
  Adapter mAdapter;
  AdapterHelper mAdapterHelper;
  boolean mAdapterUpdateDuringMeasure;
  private EdgeEffect mBottomGlow;
  private ChildDrawingOrderCallback mChildDrawingOrderCallback;
  ChildHelper mChildHelper;
  boolean mClipToPadding;
  boolean mDataSetHasChangedAfterLayout = false;
  boolean mDispatchItemsChangedEvent = false;
  private int mDispatchScrollCounter = 0;
  private int mEatenAccessibilityChangeFlags;
  @NonNull
  private EdgeEffectFactory mEdgeEffectFactory = new EdgeEffectFactory();
  boolean mEnableFastScroller;
  @VisibleForTesting
  boolean mFirstLayoutComplete;
  GapWorker mGapWorker;
  boolean mHasFixedSize;
  private boolean mIgnoreMotionEventTillDown;
  private int mInitialTouchX;
  private int mInitialTouchY;
  private int mInterceptRequestLayoutDepth = 0;
  boolean mIsAttached;
  ItemAnimator mItemAnimator = new DefaultItemAnimator();
  private RecyclerView.ItemAnimator.ItemAnimatorListener mItemAnimatorListener;
  private Runnable mItemAnimatorRunner;
  final ArrayList<ItemDecoration> mItemDecorations = new ArrayList();
  boolean mItemsAddedOrRemoved;
  boolean mItemsChanged;
  private int mLastTouchX;
  private int mLastTouchY;
  @VisibleForTesting
  LayoutManager mLayout;
  boolean mLayoutFrozen;
  private int mLayoutOrScrollCounter = 0;
  boolean mLayoutWasDefered;
  private EdgeEffect mLeftGlow;
  private final int mMaxFlingVelocity;
  private final int mMinFlingVelocity;
  private final int[] mMinMaxLayoutPositions;
  private final int[] mNestedOffsets;
  private final RecyclerViewDataObserver mObserver = new RecyclerViewDataObserver();
  private List<OnChildAttachStateChangeListener> mOnChildAttachStateListeners;
  private OnFlingListener mOnFlingListener;
  private final ArrayList<OnItemTouchListener> mOnItemTouchListeners = new ArrayList();
  @VisibleForTesting
  final List<ViewHolder> mPendingAccessibilityImportanceChange;
  private SavedState mPendingSavedState;
  boolean mPostedAnimatorRunner;
  GapWorker.LayoutPrefetchRegistryImpl mPrefetchRegistry;
  private boolean mPreserveFocusAfterLayout;
  final Recycler mRecycler = new Recycler();
  RecyclerListener mRecyclerListener;
  private EdgeEffect mRightGlow;
  private float mScaledHorizontalScrollFactor = Float.MIN_VALUE;
  private float mScaledVerticalScrollFactor = Float.MIN_VALUE;
  private final int[] mScrollConsumed;
  private OnScrollListener mScrollListener;
  private List<OnScrollListener> mScrollListeners;
  private final int[] mScrollOffset;
  private int mScrollPointerId = -1;
  private int mScrollState = 0;
  private NestedScrollingChildHelper mScrollingChildHelper;
  final State mState;
  final Rect mTempRect = new Rect();
  private final Rect mTempRect2 = new Rect();
  final RectF mTempRectF = new RectF();
  private EdgeEffect mTopGlow;
  private int mTouchSlop;
  final Runnable mUpdateChildViewsRunnable = new Runnable()
  {
    public void run()
    {
      if (mFirstLayoutComplete)
      {
        if (isLayoutRequested()) {
          return;
        }
        if (!mIsAttached)
        {
          requestLayout();
          return;
        }
        if (mLayoutFrozen)
        {
          mLayoutWasDefered = true;
          return;
        }
        consumePendingUpdateOperations();
        return;
      }
    }
  };
  private VelocityTracker mVelocityTracker;
  final ViewFlinger mViewFlinger;
  private final ViewInfoStore.ProcessCallback mViewInfoProcessCallback;
  final ViewInfoStore mViewInfoStore = new ViewInfoStore();
  
  static
  {
    CLIP_TO_PADDING_ATTR = new int[] { 16842987 };
    boolean bool;
    if ((Build.VERSION.SDK_INT != 18) && (Build.VERSION.SDK_INT != 19) && (Build.VERSION.SDK_INT != 20)) {
      bool = false;
    } else {
      bool = true;
    }
    FORCE_INVALIDATE_DISPLAY_LIST = bool;
    if (Build.VERSION.SDK_INT >= 23) {
      bool = true;
    } else {
      bool = false;
    }
    ALLOW_SIZE_IN_UNSPECIFIED_SPEC = bool;
    if (Build.VERSION.SDK_INT >= 16) {
      bool = true;
    } else {
      bool = false;
    }
    POST_UPDATES_ON_ANIMATION = bool;
    if (Build.VERSION.SDK_INT >= 21) {
      bool = true;
    } else {
      bool = false;
    }
    ALLOW_THREAD_GAP_WORK = bool;
    if (Build.VERSION.SDK_INT <= 15) {
      bool = true;
    } else {
      bool = false;
    }
    FORCE_ABS_FOCUS_SEARCH_DIRECTION = bool;
    if (Build.VERSION.SDK_INT <= 15) {
      bool = true;
    } else {
      bool = false;
    }
    IGNORE_DETACHED_FOCUSED_CHILD = bool;
  }
  
  public RecyclerView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public RecyclerView(Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public RecyclerView(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    boolean bool2 = true;
    mPreserveFocusAfterLayout = true;
    mViewFlinger = new ViewFlinger();
    if (ALLOW_THREAD_GAP_WORK) {
      localObject = new GapWorker.LayoutPrefetchRegistryImpl();
    } else {
      localObject = null;
    }
    mPrefetchRegistry = ((GapWorker.LayoutPrefetchRegistryImpl)localObject);
    mState = new State();
    mItemsAddedOrRemoved = false;
    mItemsChanged = false;
    mItemAnimatorListener = new ItemAnimatorRestoreListener();
    mPostedAnimatorRunner = false;
    mMinMaxLayoutPositions = new int[2];
    mScrollOffset = new int[2];
    mScrollConsumed = new int[2];
    mNestedOffsets = new int[2];
    mPendingAccessibilityImportanceChange = new ArrayList();
    mItemAnimatorRunner = new Runnable()
    {
      public void run()
      {
        if (mItemAnimator != null) {
          mItemAnimator.runPendingAnimations();
        }
        mPostedAnimatorRunner = false;
      }
    };
    mViewInfoProcessCallback = new ViewInfoStore.ProcessCallback()
    {
      public void processAppeared(RecyclerView.ViewHolder paramAnonymousViewHolder, RecyclerView.ItemAnimator.ItemHolderInfo paramAnonymousItemHolderInfo1, RecyclerView.ItemAnimator.ItemHolderInfo paramAnonymousItemHolderInfo2)
      {
        animateAppearance(paramAnonymousViewHolder, paramAnonymousItemHolderInfo1, paramAnonymousItemHolderInfo2);
      }
      
      public void processDisappeared(RecyclerView.ViewHolder paramAnonymousViewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo paramAnonymousItemHolderInfo1, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo paramAnonymousItemHolderInfo2)
      {
        mRecycler.unscrapView(paramAnonymousViewHolder);
        animateDisappearance(paramAnonymousViewHolder, paramAnonymousItemHolderInfo1, paramAnonymousItemHolderInfo2);
      }
      
      public void processPersistent(RecyclerView.ViewHolder paramAnonymousViewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo paramAnonymousItemHolderInfo1, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo paramAnonymousItemHolderInfo2)
      {
        paramAnonymousViewHolder.setIsRecyclable(false);
        if (mDataSetHasChangedAfterLayout)
        {
          if (mItemAnimator.animateChange(paramAnonymousViewHolder, paramAnonymousViewHolder, paramAnonymousItemHolderInfo1, paramAnonymousItemHolderInfo2)) {
            postAnimationRunner();
          }
        }
        else if (mItemAnimator.animatePersistence(paramAnonymousViewHolder, paramAnonymousItemHolderInfo1, paramAnonymousItemHolderInfo2)) {
          postAnimationRunner();
        }
      }
      
      public void unused(RecyclerView.ViewHolder paramAnonymousViewHolder)
      {
        mLayout.removeAndRecycleView(itemView, mRecycler);
      }
    };
    if (paramAttributeSet != null)
    {
      localObject = paramContext.obtainStyledAttributes(paramAttributeSet, CLIP_TO_PADDING_ATTR, paramInt, 0);
      mClipToPadding = ((TypedArray)localObject).getBoolean(0, true);
      ((TypedArray)localObject).recycle();
    }
    else
    {
      mClipToPadding = true;
    }
    setScrollContainer(true);
    setFocusableInTouchMode(true);
    Object localObject = ViewConfiguration.get(paramContext);
    mTouchSlop = ((ViewConfiguration)localObject).getScaledTouchSlop();
    mScaledHorizontalScrollFactor = ViewConfigurationCompat.getScaledHorizontalScrollFactor((ViewConfiguration)localObject, paramContext);
    mScaledVerticalScrollFactor = ViewConfigurationCompat.getScaledVerticalScrollFactor((ViewConfiguration)localObject, paramContext);
    mMinFlingVelocity = ((ViewConfiguration)localObject).getScaledMinimumFlingVelocity();
    mMaxFlingVelocity = ((ViewConfiguration)localObject).getScaledMaximumFlingVelocity();
    boolean bool1;
    if (getOverScrollMode() == 2) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    setWillNotDraw(bool1);
    mItemAnimator.setListener(mItemAnimatorListener);
    initAdapterManager();
    initChildrenHelper();
    if (ViewCompat.getImportantForAccessibility(this) == 0) {
      ViewCompat.setImportantForAccessibility(this, 1);
    }
    mAccessibilityManager = ((AccessibilityManager)getContext().getSystemService("accessibility"));
    setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
    if (paramAttributeSet != null)
    {
      localObject = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RecyclerView, paramInt, 0);
      String str = ((TypedArray)localObject).getString(R.styleable.RecyclerView_layoutManager);
      if (((TypedArray)localObject).getInt(R.styleable.RecyclerView_android_descendantFocusability, -1) == -1) {
        setDescendantFocusability(262144);
      }
      mEnableFastScroller = ((TypedArray)localObject).getBoolean(R.styleable.RecyclerView_fastScrollEnabled, false);
      if (mEnableFastScroller) {
        initFastScroller((StateListDrawable)((TypedArray)localObject).getDrawable(R.styleable.RecyclerView_fastScrollVerticalThumbDrawable), ((TypedArray)localObject).getDrawable(R.styleable.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable)((TypedArray)localObject).getDrawable(R.styleable.RecyclerView_fastScrollHorizontalThumbDrawable), ((TypedArray)localObject).getDrawable(R.styleable.RecyclerView_fastScrollHorizontalTrackDrawable));
      }
      ((TypedArray)localObject).recycle();
      createLayoutManager(paramContext, str, paramAttributeSet, paramInt, 0);
      bool1 = bool2;
      if (Build.VERSION.SDK_INT >= 21)
      {
        paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, NESTED_SCROLLING_ATTRS, paramInt, 0);
        bool1 = paramContext.getBoolean(0, true);
        paramContext.recycle();
      }
    }
    else
    {
      setDescendantFocusability(262144);
      bool1 = bool2;
    }
    setNestedScrollingEnabled(bool1);
  }
  
  private void addAnimatingView(ViewHolder paramViewHolder)
  {
    View localView = itemView;
    int i;
    if (localView.getParent() == this) {
      i = 1;
    } else {
      i = 0;
    }
    mRecycler.unscrapView(getChildViewHolder(localView));
    if (paramViewHolder.isTmpDetached())
    {
      mChildHelper.attachViewToParent(localView, -1, localView.getLayoutParams(), true);
      return;
    }
    if (i == 0)
    {
      mChildHelper.addView(localView, true);
      return;
    }
    mChildHelper.hide(localView);
  }
  
  private void animateChange(@NonNull ViewHolder paramViewHolder1, @NonNull ViewHolder paramViewHolder2, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo1, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo2, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramViewHolder1.setIsRecyclable(false);
    if (paramBoolean1) {
      addAnimatingView(paramViewHolder1);
    }
    if (paramViewHolder1 != paramViewHolder2)
    {
      if (paramBoolean2) {
        addAnimatingView(paramViewHolder2);
      }
      mShadowedHolder = paramViewHolder2;
      addAnimatingView(paramViewHolder1);
      mRecycler.unscrapView(paramViewHolder1);
      paramViewHolder2.setIsRecyclable(false);
      mShadowingHolder = paramViewHolder1;
    }
    if (mItemAnimator.animateChange(paramViewHolder1, paramViewHolder2, paramItemHolderInfo1, paramItemHolderInfo2)) {
      postAnimationRunner();
    }
  }
  
  private void cancelTouch()
  {
    resetTouch();
    setScrollState(0);
  }
  
  static void clearNestedRecyclerViewIfNotNested(@NonNull ViewHolder paramViewHolder)
  {
    if (mNestedRecyclerView != null)
    {
      Object localObject = (View)mNestedRecyclerView.get();
      while (localObject != null)
      {
        if (localObject == itemView) {
          return;
        }
        localObject = ((View)localObject).getParent();
        if ((localObject instanceof View)) {
          localObject = (View)localObject;
        } else {
          localObject = null;
        }
      }
      mNestedRecyclerView = null;
    }
  }
  
  private void createLayoutManager(Context paramContext, String paramString, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    if (paramString != null)
    {
      paramString = paramString.trim();
      if (!paramString.isEmpty())
      {
        String str = getFullClassName(paramContext, paramString);
        try
        {
          if (isInEditMode()) {
            paramString = getClass().getClassLoader();
          } else {
            paramString = paramContext.getClassLoader();
          }
          Class localClass = paramString.loadClass(str).asSubclass(LayoutManager.class);
          Object localObject = null;
          try
          {
            paramString = localClass.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
            paramContext = new Object[] { paramContext, paramAttributeSet, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) };
          }
          catch (NoSuchMethodException paramContext) {}
          try
          {
            paramString = localClass.getConstructor(new Class[0]);
            paramContext = localObject;
            paramString.setAccessible(true);
            setLayoutManager((LayoutManager)paramString.newInstance(paramContext));
            return;
          }
          catch (NoSuchMethodException paramString)
          {
            paramString.initCause(paramContext);
            paramContext = new StringBuilder();
            paramContext.append(paramAttributeSet.getPositionDescription());
            paramContext.append(": Error creating LayoutManager ");
            paramContext.append(str);
            throw new IllegalStateException(paramContext.toString(), paramString);
          }
          return;
        }
        catch (ClassCastException paramContext)
        {
          paramString = new StringBuilder();
          paramString.append(paramAttributeSet.getPositionDescription());
          paramString.append(": Class is not a LayoutManager ");
          paramString.append(str);
          throw new IllegalStateException(paramString.toString(), paramContext);
        }
        catch (IllegalAccessException paramContext)
        {
          paramString = new StringBuilder();
          paramString.append(paramAttributeSet.getPositionDescription());
          paramString.append(": Cannot access non-public constructor ");
          paramString.append(str);
          throw new IllegalStateException(paramString.toString(), paramContext);
        }
        catch (InstantiationException paramContext)
        {
          paramString = new StringBuilder();
          paramString.append(paramAttributeSet.getPositionDescription());
          paramString.append(": Could not instantiate the LayoutManager: ");
          paramString.append(str);
          throw new IllegalStateException(paramString.toString(), paramContext);
        }
        catch (InvocationTargetException paramContext)
        {
          paramString = new StringBuilder();
          paramString.append(paramAttributeSet.getPositionDescription());
          paramString.append(": Could not instantiate the LayoutManager: ");
          paramString.append(str);
          throw new IllegalStateException(paramString.toString(), paramContext);
        }
        catch (ClassNotFoundException paramContext)
        {
          paramString = new StringBuilder();
          paramString.append(paramAttributeSet.getPositionDescription());
          paramString.append(": Unable to find LayoutManager ");
          paramString.append(str);
          throw new IllegalStateException(paramString.toString(), paramContext);
        }
      }
    }
  }
  
  private boolean didChildRangeChange(int paramInt1, int paramInt2)
  {
    findMinMaxChildLayoutPositions(mMinMaxLayoutPositions);
    int[] arrayOfInt = mMinMaxLayoutPositions;
    boolean bool = false;
    if ((arrayOfInt[0] != paramInt1) || (mMinMaxLayoutPositions[1] != paramInt2)) {
      bool = true;
    }
    return bool;
  }
  
  private void dispatchContentChangedIfNecessary()
  {
    int i = mEatenAccessibilityChangeFlags;
    mEatenAccessibilityChangeFlags = 0;
    if ((i != 0) && (isAccessibilityEnabled()))
    {
      AccessibilityEvent localAccessibilityEvent = AccessibilityEvent.obtain();
      localAccessibilityEvent.setEventType(2048);
      AccessibilityEventCompat.setContentChangeTypes(localAccessibilityEvent, i);
      sendAccessibilityEventUnchecked(localAccessibilityEvent);
    }
  }
  
  private void dispatchLayoutStep1()
  {
    Object localObject = mState;
    boolean bool = true;
    ((State)localObject).assertLayoutStep(1);
    fillRemainingScrollValues(mState);
    mState.mIsMeasuring = false;
    startInterceptRequestLayout();
    mViewInfoStore.clear();
    onEnterLayoutOrScroll();
    processAdapterUpdatesAndSetAnimationFlags();
    saveFocusInfo();
    localObject = mState;
    if ((!mState.mRunSimpleAnimations) || (!mItemsChanged)) {
      bool = false;
    }
    mTrackOldChangeHolders = bool;
    mItemsChanged = false;
    mItemsAddedOrRemoved = false;
    mState.mInPreLayout = mState.mRunPredictiveAnimations;
    mState.mItemCount = mAdapter.getItemCount();
    findMinMaxChildLayoutPositions(mMinMaxLayoutPositions);
    int j;
    int i;
    RecyclerView.ItemAnimator.ItemHolderInfo localItemHolderInfo;
    if (mState.mRunSimpleAnimations)
    {
      j = mChildHelper.getChildCount();
      i = 0;
      while (i < j)
      {
        localObject = getChildViewHolderInt(mChildHelper.getChildAt(i));
        if ((!((ViewHolder)localObject).shouldIgnore()) && ((!((ViewHolder)localObject).isInvalid()) || (mAdapter.hasStableIds())))
        {
          localItemHolderInfo = mItemAnimator.recordPreLayoutInformation(mState, (ViewHolder)localObject, ItemAnimator.buildAdapterChangeFlagsForAnimations((ViewHolder)localObject), ((ViewHolder)localObject).getUnmodifiedPayloads());
          mViewInfoStore.addToPreLayout((ViewHolder)localObject, localItemHolderInfo);
          if ((mState.mTrackOldChangeHolders) && (((ViewHolder)localObject).isUpdated()) && (!((ViewHolder)localObject).isRemoved()) && (!((ViewHolder)localObject).shouldIgnore()) && (!((ViewHolder)localObject).isInvalid()))
          {
            long l = getChangedHolderKey((ViewHolder)localObject);
            mViewInfoStore.addToOldChangeHolders(l, (ViewHolder)localObject);
          }
        }
        i += 1;
      }
    }
    if (mState.mRunPredictiveAnimations)
    {
      saveOldPositions();
      bool = mState.mStructureChanged;
      mState.mStructureChanged = false;
      mLayout.onLayoutChildren(mRecycler, mState);
      mState.mStructureChanged = bool;
      i = 0;
      while (i < mChildHelper.getChildCount())
      {
        localObject = getChildViewHolderInt(mChildHelper.getChildAt(i));
        if ((!((ViewHolder)localObject).shouldIgnore()) && (!mViewInfoStore.isInPreLayout((ViewHolder)localObject)))
        {
          int k = ItemAnimator.buildAdapterChangeFlagsForAnimations((ViewHolder)localObject);
          bool = ((ViewHolder)localObject).hasAnyOfTheFlags(8192);
          j = k;
          if (!bool) {
            j = k | 0x1000;
          }
          localItemHolderInfo = mItemAnimator.recordPreLayoutInformation(mState, (ViewHolder)localObject, j, ((ViewHolder)localObject).getUnmodifiedPayloads());
          if (bool) {
            recordAnimationInfoIfBouncedHiddenView((ViewHolder)localObject, localItemHolderInfo);
          } else {
            mViewInfoStore.addToAppearedInPreLayoutHolders((ViewHolder)localObject, localItemHolderInfo);
          }
        }
        i += 1;
      }
      clearOldPositions();
    }
    else
    {
      clearOldPositions();
    }
    onExitLayoutOrScroll();
    stopInterceptRequestLayout(false);
    mState.mLayoutStep = 2;
  }
  
  private void dispatchLayoutStep2()
  {
    startInterceptRequestLayout();
    onEnterLayoutOrScroll();
    mState.assertLayoutStep(6);
    mAdapterHelper.consumeUpdatesInOnePass();
    mState.mItemCount = mAdapter.getItemCount();
    mState.mDeletedInvisibleItemCountSincePreviousLayout = 0;
    mState.mInPreLayout = false;
    mLayout.onLayoutChildren(mRecycler, mState);
    mState.mStructureChanged = false;
    mPendingSavedState = null;
    State localState = mState;
    boolean bool;
    if ((mState.mRunSimpleAnimations) && (mItemAnimator != null)) {
      bool = true;
    } else {
      bool = false;
    }
    mRunSimpleAnimations = bool;
    mState.mLayoutStep = 4;
    onExitLayoutOrScroll();
    stopInterceptRequestLayout(false);
  }
  
  private void dispatchLayoutStep3()
  {
    mState.assertLayoutStep(4);
    startInterceptRequestLayout();
    onEnterLayoutOrScroll();
    mState.mLayoutStep = 1;
    if (mState.mRunSimpleAnimations)
    {
      int i = mChildHelper.getChildCount() - 1;
      while (i >= 0)
      {
        ViewHolder localViewHolder1 = getChildViewHolderInt(mChildHelper.getChildAt(i));
        if (!localViewHolder1.shouldIgnore())
        {
          long l = getChangedHolderKey(localViewHolder1);
          RecyclerView.ItemAnimator.ItemHolderInfo localItemHolderInfo2 = mItemAnimator.recordPostLayoutInformation(mState, localViewHolder1);
          ViewHolder localViewHolder2 = mViewInfoStore.getFromOldChangeHolders(l);
          if ((localViewHolder2 != null) && (!localViewHolder2.shouldIgnore()))
          {
            boolean bool1 = mViewInfoStore.isDisappearing(localViewHolder2);
            boolean bool2 = mViewInfoStore.isDisappearing(localViewHolder1);
            if ((bool1) && (localViewHolder2 == localViewHolder1))
            {
              mViewInfoStore.addToPostLayout(localViewHolder1, localItemHolderInfo2);
            }
            else
            {
              RecyclerView.ItemAnimator.ItemHolderInfo localItemHolderInfo1 = mViewInfoStore.popFromPreLayout(localViewHolder2);
              mViewInfoStore.addToPostLayout(localViewHolder1, localItemHolderInfo2);
              localItemHolderInfo2 = mViewInfoStore.popFromPostLayout(localViewHolder1);
              if (localItemHolderInfo1 == null) {
                handleMissingPreInfoForChangeError(l, localViewHolder1, localViewHolder2);
              } else {
                animateChange(localViewHolder2, localViewHolder1, localItemHolderInfo1, localItemHolderInfo2, bool1, bool2);
              }
            }
          }
          else
          {
            mViewInfoStore.addToPostLayout(localViewHolder1, localItemHolderInfo2);
          }
        }
        i -= 1;
      }
      mViewInfoStore.process(mViewInfoProcessCallback);
    }
    mLayout.removeAndRecycleScrapInt(mRecycler);
    mState.mPreviousLayoutItemCount = mState.mItemCount;
    mDataSetHasChangedAfterLayout = false;
    mDispatchItemsChangedEvent = false;
    mState.mRunSimpleAnimations = false;
    mState.mRunPredictiveAnimations = false;
    mLayout.mRequestedSimpleAnimations = false;
    if (mRecycler.mChangedScrap != null) {
      mRecycler.mChangedScrap.clear();
    }
    if (mLayout.mPrefetchMaxObservedInInitialPrefetch)
    {
      mLayout.mPrefetchMaxCountObserved = 0;
      mLayout.mPrefetchMaxObservedInInitialPrefetch = false;
      mRecycler.updateViewCacheSize();
    }
    mLayout.onLayoutCompleted(mState);
    onExitLayoutOrScroll();
    stopInterceptRequestLayout(false);
    mViewInfoStore.clear();
    if (didChildRangeChange(mMinMaxLayoutPositions[0], mMinMaxLayoutPositions[1])) {
      dispatchOnScrolled(0, 0);
    }
    recoverFocusFromState();
    resetFocusInfo();
  }
  
  private boolean dispatchOnItemTouch(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    if (mActiveOnItemTouchListener != null) {
      if (i == 0)
      {
        mActiveOnItemTouchListener = null;
      }
      else
      {
        mActiveOnItemTouchListener.onTouchEvent(this, paramMotionEvent);
        if ((i == 3) || (i == 1)) {
          mActiveOnItemTouchListener = null;
        }
        return true;
      }
    }
    if (i != 0)
    {
      int j = mOnItemTouchListeners.size();
      i = 0;
      while (i < j)
      {
        OnItemTouchListener localOnItemTouchListener = (OnItemTouchListener)mOnItemTouchListeners.get(i);
        if (localOnItemTouchListener.onInterceptTouchEvent(this, paramMotionEvent))
        {
          mActiveOnItemTouchListener = localOnItemTouchListener;
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  private boolean dispatchOnItemTouchIntercept(MotionEvent paramMotionEvent)
  {
    int j = paramMotionEvent.getAction();
    if ((j == 3) || (j == 0)) {
      mActiveOnItemTouchListener = null;
    }
    int k = mOnItemTouchListeners.size();
    int i = 0;
    while (i < k)
    {
      OnItemTouchListener localOnItemTouchListener = (OnItemTouchListener)mOnItemTouchListeners.get(i);
      if ((localOnItemTouchListener.onInterceptTouchEvent(this, paramMotionEvent)) && (j != 3))
      {
        mActiveOnItemTouchListener = localOnItemTouchListener;
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private void findMinMaxChildLayoutPositions(int[] paramArrayOfInt)
  {
    int i2 = mChildHelper.getChildCount();
    if (i2 == 0)
    {
      paramArrayOfInt[0] = -1;
      paramArrayOfInt[1] = -1;
      return;
    }
    int k = Integer.MIN_VALUE;
    int j = Integer.MAX_VALUE;
    int m = 0;
    while (m < i2)
    {
      ViewHolder localViewHolder = getChildViewHolderInt(mChildHelper.getChildAt(m));
      int i1;
      if (localViewHolder.shouldIgnore())
      {
        i1 = k;
      }
      else
      {
        int n = localViewHolder.getLayoutPosition();
        int i = j;
        if (n < j) {
          i = n;
        }
        j = i;
        i1 = k;
        if (n > k)
        {
          i1 = n;
          j = i;
        }
      }
      m += 1;
      k = i1;
    }
    paramArrayOfInt[0] = j;
    paramArrayOfInt[1] = k;
  }
  
  @Nullable
  static RecyclerView findNestedRecyclerView(@NonNull View paramView)
  {
    if (!(paramView instanceof ViewGroup)) {
      return null;
    }
    if ((paramView instanceof RecyclerView)) {
      return (RecyclerView)paramView;
    }
    paramView = (ViewGroup)paramView;
    int j = paramView.getChildCount();
    int i = 0;
    while (i < j)
    {
      RecyclerView localRecyclerView = findNestedRecyclerView(paramView.getChildAt(i));
      if (localRecyclerView != null) {
        return localRecyclerView;
      }
      i += 1;
    }
    return null;
  }
  
  @Nullable
  private View findNextViewToFocus()
  {
    if (mState.mFocusedItemPosition != -1) {
      i = mState.mFocusedItemPosition;
    } else {
      i = 0;
    }
    int k = mState.getItemCount();
    int j = i;
    ViewHolder localViewHolder;
    while (j < k)
    {
      localViewHolder = findViewHolderForAdapterPosition(j);
      if (localViewHolder == null) {
        break;
      }
      if (itemView.hasFocusable()) {
        return itemView;
      }
      j += 1;
    }
    int i = Math.min(k, i) - 1;
    while (i >= 0)
    {
      localViewHolder = findViewHolderForAdapterPosition(i);
      if (localViewHolder == null) {
        return null;
      }
      if (itemView.hasFocusable()) {
        return itemView;
      }
      i -= 1;
    }
    return null;
  }
  
  static ViewHolder getChildViewHolderInt(View paramView)
  {
    if (paramView == null) {
      return null;
    }
    return getLayoutParamsmViewHolder;
  }
  
  static void getDecoratedBoundsWithMarginsInt(View paramView, Rect paramRect)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    Rect localRect = mDecorInsets;
    paramRect.set(paramView.getLeft() - left - leftMargin, paramView.getTop() - top - topMargin, paramView.getRight() + right + rightMargin, paramView.getBottom() + bottom + bottomMargin);
  }
  
  private int getDeepestFocusedViewWithId(View paramView)
  {
    int i = paramView.getId();
    while ((!paramView.isFocused()) && ((paramView instanceof ViewGroup)) && (paramView.hasFocus()))
    {
      View localView = ((ViewGroup)paramView).getFocusedChild();
      paramView = localView;
      if (localView.getId() != -1)
      {
        i = localView.getId();
        paramView = localView;
      }
    }
    return i;
  }
  
  private String getFullClassName(Context paramContext, String paramString)
  {
    if (paramString.charAt(0) == '.')
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext.getPackageName());
      localStringBuilder.append(paramString);
      return localStringBuilder.toString();
    }
    if (paramString.contains(".")) {
      return paramString;
    }
    paramContext = new StringBuilder();
    paramContext.append(RecyclerView.class.getPackage().getName());
    paramContext.append('.');
    paramContext.append(paramString);
    return paramContext.toString();
  }
  
  private NestedScrollingChildHelper getScrollingChildHelper()
  {
    if (mScrollingChildHelper == null) {
      mScrollingChildHelper = new NestedScrollingChildHelper(this);
    }
    return mScrollingChildHelper;
  }
  
  private void handleMissingPreInfoForChangeError(long paramLong, ViewHolder paramViewHolder1, ViewHolder paramViewHolder2)
  {
    int j = mChildHelper.getChildCount();
    int i = 0;
    while (i < j)
    {
      localObject = getChildViewHolderInt(mChildHelper.getChildAt(i));
      if ((localObject != paramViewHolder1) && (getChangedHolderKey((ViewHolder)localObject) == paramLong))
      {
        if ((mAdapter != null) && (mAdapter.hasStableIds()))
        {
          paramViewHolder2 = new StringBuilder();
          paramViewHolder2.append("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:");
          paramViewHolder2.append(localObject);
          paramViewHolder2.append(" \n View Holder 2:");
          paramViewHolder2.append(paramViewHolder1);
          paramViewHolder2.append(exceptionLabel());
          throw new IllegalStateException(paramViewHolder2.toString());
        }
        paramViewHolder2 = new StringBuilder();
        paramViewHolder2.append("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:");
        paramViewHolder2.append(localObject);
        paramViewHolder2.append(" \n View Holder 2:");
        paramViewHolder2.append(paramViewHolder1);
        paramViewHolder2.append(exceptionLabel());
        throw new IllegalStateException(paramViewHolder2.toString());
      }
      i += 1;
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Problem while matching changed view holders with the newones. The pre-layout information for the change holder ");
    ((StringBuilder)localObject).append(paramViewHolder2);
    ((StringBuilder)localObject).append(" cannot be found but it is necessary for ");
    ((StringBuilder)localObject).append(paramViewHolder1);
    ((StringBuilder)localObject).append(exceptionLabel());
    Log.e("RecyclerView", ((StringBuilder)localObject).toString());
  }
  
  private boolean hasUpdatedView()
  {
    int j = mChildHelper.getChildCount();
    int i = 0;
    while (i < j)
    {
      ViewHolder localViewHolder = getChildViewHolderInt(mChildHelper.getChildAt(i));
      if ((localViewHolder != null) && (!localViewHolder.shouldIgnore()) && (localViewHolder.isUpdated())) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private void initChildrenHelper()
  {
    mChildHelper = new ChildHelper(new ChildHelper.Callback()
    {
      public void addView(View paramAnonymousView, int paramAnonymousInt)
      {
        RecyclerView.this.addView(paramAnonymousView, paramAnonymousInt);
        dispatchChildAttached(paramAnonymousView);
      }
      
      public void attachViewToParent(View paramAnonymousView, int paramAnonymousInt, ViewGroup.LayoutParams paramAnonymousLayoutParams)
      {
        RecyclerView.ViewHolder localViewHolder = RecyclerView.getChildViewHolderInt(paramAnonymousView);
        if (localViewHolder != null)
        {
          if ((!localViewHolder.isTmpDetached()) && (!localViewHolder.shouldIgnore()))
          {
            paramAnonymousView = new StringBuilder();
            paramAnonymousView.append("Called attach on a child which is not detached: ");
            paramAnonymousView.append(localViewHolder);
            paramAnonymousView.append(exceptionLabel());
            throw new IllegalArgumentException(paramAnonymousView.toString());
          }
          localViewHolder.clearTmpDetachFlag();
        }
        RecyclerView.this.attachViewToParent(paramAnonymousView, paramAnonymousInt, paramAnonymousLayoutParams);
      }
      
      public void detachViewFromParent(int paramAnonymousInt)
      {
        Object localObject = getChildAt(paramAnonymousInt);
        if (localObject != null)
        {
          localObject = RecyclerView.getChildViewHolderInt((View)localObject);
          if (localObject != null)
          {
            if ((((RecyclerView.ViewHolder)localObject).isTmpDetached()) && (!((RecyclerView.ViewHolder)localObject).shouldIgnore()))
            {
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("called detach on an already detached child ");
              localStringBuilder.append(localObject);
              localStringBuilder.append(exceptionLabel());
              throw new IllegalArgumentException(localStringBuilder.toString());
            }
            ((RecyclerView.ViewHolder)localObject).addFlags(256);
          }
        }
        RecyclerView.this.detachViewFromParent(paramAnonymousInt);
      }
      
      public View getChildAt(int paramAnonymousInt)
      {
        return RecyclerView.this.getChildAt(paramAnonymousInt);
      }
      
      public int getChildCount()
      {
        return RecyclerView.this.getChildCount();
      }
      
      public RecyclerView.ViewHolder getChildViewHolder(View paramAnonymousView)
      {
        return RecyclerView.getChildViewHolderInt(paramAnonymousView);
      }
      
      public int indexOfChild(View paramAnonymousView)
      {
        return RecyclerView.this.indexOfChild(paramAnonymousView);
      }
      
      public void onEnteredHiddenState(View paramAnonymousView)
      {
        paramAnonymousView = RecyclerView.getChildViewHolderInt(paramAnonymousView);
        if (paramAnonymousView != null) {
          RecyclerView.ViewHolder.access$200(paramAnonymousView, RecyclerView.this);
        }
      }
      
      public void onLeftHiddenState(View paramAnonymousView)
      {
        paramAnonymousView = RecyclerView.getChildViewHolderInt(paramAnonymousView);
        if (paramAnonymousView != null) {
          RecyclerView.ViewHolder.access$300(paramAnonymousView, RecyclerView.this);
        }
      }
      
      public void removeAllViews()
      {
        int j = getChildCount();
        int i = 0;
        while (i < j)
        {
          View localView = getChildAt(i);
          dispatchChildDetached(localView);
          localView.clearAnimation();
          i += 1;
        }
        RecyclerView.this.removeAllViews();
      }
      
      public void removeViewAt(int paramAnonymousInt)
      {
        View localView = RecyclerView.this.getChildAt(paramAnonymousInt);
        if (localView != null)
        {
          dispatchChildDetached(localView);
          localView.clearAnimation();
        }
        RecyclerView.this.removeViewAt(paramAnonymousInt);
      }
    });
  }
  
  private boolean isPreferredNextFocus(View paramView1, View paramView2, int paramInt)
  {
    boolean bool3 = false;
    boolean bool1 = false;
    boolean bool4 = false;
    boolean bool5 = false;
    boolean bool6 = false;
    boolean bool2 = false;
    if (paramView2 != null)
    {
      if (paramView2 == this) {
        return false;
      }
      if (findContainingItemView(paramView2) == null) {
        return false;
      }
      if (paramView1 == null) {
        return true;
      }
      if (findContainingItemView(paramView1) == null) {
        return true;
      }
      mTempRect.set(0, 0, paramView1.getWidth(), paramView1.getHeight());
      mTempRect2.set(0, 0, paramView2.getWidth(), paramView2.getHeight());
      offsetDescendantRectToMyCoords(paramView1, mTempRect);
      offsetDescendantRectToMyCoords(paramView2, mTempRect2);
      int i = mLayout.getLayoutDirection();
      int j = -1;
      int k;
      if (i == 1) {
        k = -1;
      } else {
        k = 1;
      }
      if (((mTempRect.left < mTempRect2.left) || (mTempRect.right <= mTempRect2.left)) && (mTempRect.right < mTempRect2.right)) {
        i = 1;
      } else if (((mTempRect.right > mTempRect2.right) || (mTempRect.left >= mTempRect2.right)) && (mTempRect.left > mTempRect2.left)) {
        i = -1;
      } else {
        i = 0;
      }
      if (((mTempRect.top < mTempRect2.top) || (mTempRect.bottom <= mTempRect2.top)) && (mTempRect.bottom < mTempRect2.bottom)) {
        j = 1;
      } else if (((mTempRect.bottom <= mTempRect2.bottom) && (mTempRect.top < mTempRect2.bottom)) || (mTempRect.top <= mTempRect2.top)) {
        j = 0;
      }
      if (paramInt != 17)
      {
        if (paramInt != 33)
        {
          if (paramInt != 66)
          {
            if (paramInt != 130)
            {
              switch (paramInt)
              {
              default: 
                paramView1 = new StringBuilder();
                paramView1.append("Invalid direction: ");
                paramView1.append(paramInt);
                paramView1.append(exceptionLabel());
                throw new IllegalArgumentException(paramView1.toString());
              case 2: 
                if (j <= 0)
                {
                  bool1 = bool2;
                  if (j == 0)
                  {
                    bool1 = bool2;
                    if (i * k < 0) {}
                  }
                }
                else
                {
                  bool1 = true;
                }
                return bool1;
              }
              if (j >= 0)
              {
                bool1 = bool3;
                if (j == 0)
                {
                  bool1 = bool3;
                  if (i * k > 0) {}
                }
              }
              else
              {
                bool1 = true;
              }
              return bool1;
            }
            if (j > 0) {
              bool1 = true;
            }
            return bool1;
          }
          bool1 = bool4;
          if (i > 0) {
            bool1 = true;
          }
          return bool1;
        }
        bool1 = bool5;
        if (j < 0) {
          bool1 = true;
        }
        return bool1;
      }
      bool1 = bool6;
      if (i < 0) {
        bool1 = true;
      }
      return bool1;
    }
    return false;
  }
  
  private void onPointerUp(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionIndex();
    if (paramMotionEvent.getPointerId(i) == mScrollPointerId)
    {
      if (i == 0) {
        i = 1;
      } else {
        i = 0;
      }
      mScrollPointerId = paramMotionEvent.getPointerId(i);
      int j = (int)(paramMotionEvent.getX(i) + 0.5F);
      mLastTouchX = j;
      mInitialTouchX = j;
      i = (int)(paramMotionEvent.getY(i) + 0.5F);
      mLastTouchY = i;
      mInitialTouchY = i;
    }
  }
  
  private boolean predictiveItemAnimationsEnabled()
  {
    return (mItemAnimator != null) && (mLayout.supportsPredictiveItemAnimations());
  }
  
  private void processAdapterUpdatesAndSetAnimationFlags()
  {
    if (mDataSetHasChangedAfterLayout)
    {
      mAdapterHelper.reset();
      if (mDispatchItemsChangedEvent) {
        mLayout.onItemsChanged(this);
      }
    }
    if (predictiveItemAnimationsEnabled()) {
      mAdapterHelper.preProcess();
    } else {
      mAdapterHelper.consumeUpdatesInOnePass();
    }
    boolean bool1 = mItemsAddedOrRemoved;
    boolean bool2 = true;
    int i;
    if ((!bool1) && (!mItemsChanged)) {
      i = 0;
    } else {
      i = 1;
    }
    State localState = mState;
    if ((mFirstLayoutComplete) && (mItemAnimator != null) && ((mDataSetHasChangedAfterLayout) || (i != 0) || (mLayout.mRequestedSimpleAnimations)) && ((!mDataSetHasChangedAfterLayout) || (mAdapter.hasStableIds()))) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    mRunSimpleAnimations = bool1;
    localState = mState;
    if ((mState.mRunSimpleAnimations) && (i != 0) && (!mDataSetHasChangedAfterLayout) && (predictiveItemAnimationsEnabled())) {
      bool1 = bool2;
    } else {
      bool1 = false;
    }
    mRunPredictiveAnimations = bool1;
  }
  
  private void pullGlows(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    int j = 1;
    if (paramFloat2 < 0.0F)
    {
      ensureLeftGlow();
      EdgeEffectCompat.onPull(mLeftGlow, -paramFloat2 / getWidth(), 1.0F - paramFloat3 / getHeight());
    }
    for (;;)
    {
      i = 1;
      break label80;
      if (paramFloat2 <= 0.0F) {
        break;
      }
      ensureRightGlow();
      EdgeEffectCompat.onPull(mRightGlow, paramFloat2 / getWidth(), paramFloat3 / getHeight());
    }
    int i = 0;
    label80:
    if (paramFloat4 < 0.0F)
    {
      ensureTopGlow();
      EdgeEffectCompat.onPull(mTopGlow, -paramFloat4 / getHeight(), paramFloat1 / getWidth());
      i = j;
    }
    else if (paramFloat4 > 0.0F)
    {
      ensureBottomGlow();
      EdgeEffectCompat.onPull(mBottomGlow, paramFloat4 / getHeight(), 1.0F - paramFloat1 / getWidth());
      i = j;
    }
    if ((i != 0) || (paramFloat2 != 0.0F) || (paramFloat4 != 0.0F)) {
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  private void recoverFocusFromState()
  {
    if ((mPreserveFocusAfterLayout) && (mAdapter != null) && (hasFocus()) && (getDescendantFocusability() != 393216))
    {
      if ((getDescendantFocusability() == 131072) && (isFocused())) {
        return;
      }
      Object localObject1;
      if (!isFocused())
      {
        localObject1 = getFocusedChild();
        if ((IGNORE_DETACHED_FOCUSED_CHILD) && ((((View)localObject1).getParent() == null) || (!((View)localObject1).hasFocus())))
        {
          if (mChildHelper.getChildCount() == 0) {
            requestFocus();
          }
        }
        else if (!mChildHelper.isHidden((View)localObject1)) {
          return;
        }
      }
      long l = mState.mFocusedItemId;
      Object localObject2 = null;
      if ((l != -1L) && (mAdapter.hasStableIds())) {
        localObject1 = findViewHolderForItemId(mState.mFocusedItemId);
      } else {
        localObject1 = null;
      }
      if ((localObject1 != null) && (!mChildHelper.isHidden(itemView)) && (itemView.hasFocusable()))
      {
        localObject1 = itemView;
      }
      else
      {
        localObject1 = localObject2;
        if (mChildHelper.getChildCount() > 0) {
          localObject1 = findNextViewToFocus();
        }
      }
      if (localObject1 != null)
      {
        localObject2 = localObject1;
        if (mState.mFocusedSubChildId != -1L)
        {
          View localView = ((View)localObject1).findViewById(mState.mFocusedSubChildId);
          localObject2 = localObject1;
          if (localView != null)
          {
            localObject2 = localObject1;
            if (localView.isFocusable()) {
              localObject2 = localView;
            }
          }
        }
        localObject2.requestFocus();
      }
      return;
    }
  }
  
  private void releaseGlows()
  {
    if (mLeftGlow != null)
    {
      mLeftGlow.onRelease();
      bool2 = mLeftGlow.isFinished();
    }
    else
    {
      bool2 = false;
    }
    boolean bool1 = bool2;
    if (mTopGlow != null)
    {
      mTopGlow.onRelease();
      bool1 = bool2 | mTopGlow.isFinished();
    }
    boolean bool2 = bool1;
    if (mRightGlow != null)
    {
      mRightGlow.onRelease();
      bool2 = bool1 | mRightGlow.isFinished();
    }
    bool1 = bool2;
    if (mBottomGlow != null)
    {
      mBottomGlow.onRelease();
      bool1 = bool2 | mBottomGlow.isFinished();
    }
    if (bool1) {
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  private void requestChildOnScreen(@NonNull View paramView1, @Nullable View paramView2)
  {
    if (paramView2 != null) {
      localObject = paramView2;
    } else {
      localObject = paramView1;
    }
    mTempRect.set(0, 0, ((View)localObject).getWidth(), ((View)localObject).getHeight());
    Object localObject = ((View)localObject).getLayoutParams();
    if ((localObject instanceof LayoutParams))
    {
      localObject = (LayoutParams)localObject;
      if (!mInsetsDirty)
      {
        localObject = mDecorInsets;
        localRect = mTempRect;
        left -= left;
        localRect = mTempRect;
        right += right;
        localRect = mTempRect;
        top -= top;
        localRect = mTempRect;
        bottom += bottom;
      }
    }
    if (paramView2 != null)
    {
      offsetDescendantRectToMyCoords(paramView2, mTempRect);
      offsetRectIntoDescendantCoords(paramView1, mTempRect);
    }
    localObject = mLayout;
    Rect localRect = mTempRect;
    boolean bool2 = mFirstLayoutComplete;
    boolean bool1;
    if (paramView2 == null) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    ((LayoutManager)localObject).requestChildRectangleOnScreen(this, paramView1, localRect, bool2 ^ true, bool1);
  }
  
  private void resetFocusInfo()
  {
    mState.mFocusedItemId = -1L;
    mState.mFocusedItemPosition = -1;
    mState.mFocusedSubChildId = -1;
  }
  
  private void resetTouch()
  {
    if (mVelocityTracker != null) {
      mVelocityTracker.clear();
    }
    stopNestedScroll(0);
    releaseGlows();
  }
  
  private void saveFocusInfo()
  {
    boolean bool = mPreserveFocusAfterLayout;
    State localState = null;
    Object localObject;
    if ((bool) && (hasFocus()) && (mAdapter != null)) {
      localObject = getFocusedChild();
    } else {
      localObject = null;
    }
    if (localObject == null) {
      localObject = localState;
    } else {
      localObject = findContainingViewHolder((View)localObject);
    }
    if (localObject == null)
    {
      resetFocusInfo();
      return;
    }
    localState = mState;
    long l;
    if (mAdapter.hasStableIds()) {
      l = ((ViewHolder)localObject).getItemId();
    } else {
      l = -1L;
    }
    mFocusedItemId = l;
    localState = mState;
    int i;
    if (mDataSetHasChangedAfterLayout) {
      i = -1;
    } else if (((ViewHolder)localObject).isRemoved()) {
      i = mOldPosition;
    } else {
      i = ((ViewHolder)localObject).getAdapterPosition();
    }
    mFocusedItemPosition = i;
    mState.mFocusedSubChildId = getDeepestFocusedViewWithId(itemView);
  }
  
  private void setAdapterInternal(Adapter paramAdapter, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (mAdapter != null)
    {
      mAdapter.unregisterAdapterDataObserver(mObserver);
      mAdapter.onDetachedFromRecyclerView(this);
    }
    if ((!paramBoolean1) || (paramBoolean2)) {
      removeAndRecycleViews();
    }
    mAdapterHelper.reset();
    Adapter localAdapter = mAdapter;
    mAdapter = paramAdapter;
    if (paramAdapter != null)
    {
      paramAdapter.registerAdapterDataObserver(mObserver);
      paramAdapter.onAttachedToRecyclerView(this);
    }
    if (mLayout != null) {
      mLayout.onAdapterChanged(localAdapter, mAdapter);
    }
    mRecycler.onAdapterChanged(localAdapter, mAdapter, paramBoolean1);
    mState.mStructureChanged = true;
  }
  
  private void stopScrollersInternal()
  {
    mViewFlinger.stop();
    if (mLayout != null) {
      mLayout.stopSmoothScroller();
    }
  }
  
  void absorbGlows(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
    {
      ensureLeftGlow();
      mLeftGlow.onAbsorb(-paramInt1);
    }
    else if (paramInt1 > 0)
    {
      ensureRightGlow();
      mRightGlow.onAbsorb(paramInt1);
    }
    if (paramInt2 < 0)
    {
      ensureTopGlow();
      mTopGlow.onAbsorb(-paramInt2);
    }
    else if (paramInt2 > 0)
    {
      ensureBottomGlow();
      mBottomGlow.onAbsorb(paramInt2);
    }
    if ((paramInt1 != 0) || (paramInt2 != 0)) {
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  public void addFocusables(ArrayList<View> paramArrayList, int paramInt1, int paramInt2)
  {
    if ((mLayout == null) || (!mLayout.onAddFocusables(this, paramArrayList, paramInt1, paramInt2))) {
      super.addFocusables(paramArrayList, paramInt1, paramInt2);
    }
  }
  
  public void addItemDecoration(ItemDecoration paramItemDecoration)
  {
    addItemDecoration(paramItemDecoration, -1);
  }
  
  public void addItemDecoration(ItemDecoration paramItemDecoration, int paramInt)
  {
    if (mLayout != null) {
      mLayout.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
    }
    if (mItemDecorations.isEmpty()) {
      setWillNotDraw(false);
    }
    if (paramInt < 0) {
      mItemDecorations.add(paramItemDecoration);
    } else {
      mItemDecorations.add(paramInt, paramItemDecoration);
    }
    markItemDecorInsetsDirty();
    requestLayout();
  }
  
  public void addOnChildAttachStateChangeListener(OnChildAttachStateChangeListener paramOnChildAttachStateChangeListener)
  {
    if (mOnChildAttachStateListeners == null) {
      mOnChildAttachStateListeners = new ArrayList();
    }
    mOnChildAttachStateListeners.add(paramOnChildAttachStateChangeListener);
  }
  
  public void addOnItemTouchListener(OnItemTouchListener paramOnItemTouchListener)
  {
    mOnItemTouchListeners.add(paramOnItemTouchListener);
  }
  
  public void addOnScrollListener(OnScrollListener paramOnScrollListener)
  {
    if (mScrollListeners == null) {
      mScrollListeners = new ArrayList();
    }
    mScrollListeners.add(paramOnScrollListener);
  }
  
  void animateAppearance(@NonNull ViewHolder paramViewHolder, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo1, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo2)
  {
    paramViewHolder.setIsRecyclable(false);
    if (mItemAnimator.animateAppearance(paramViewHolder, paramItemHolderInfo1, paramItemHolderInfo2)) {
      postAnimationRunner();
    }
  }
  
  void animateDisappearance(@NonNull ViewHolder paramViewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo1, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo2)
  {
    addAnimatingView(paramViewHolder);
    paramViewHolder.setIsRecyclable(false);
    if (mItemAnimator.animateDisappearance(paramViewHolder, paramItemHolderInfo1, paramItemHolderInfo2)) {
      postAnimationRunner();
    }
  }
  
  void assertInLayoutOrScroll(String paramString)
  {
    if (!isComputingLayout())
    {
      if (paramString == null)
      {
        paramString = new StringBuilder();
        paramString.append("Cannot call this method unless RecyclerView is computing a layout or scrolling");
        paramString.append(exceptionLabel());
        throw new IllegalStateException(paramString.toString());
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append(exceptionLabel());
      throw new IllegalStateException(localStringBuilder.toString());
    }
  }
  
  void assertNotInLayoutOrScroll(String paramString)
  {
    if (isComputingLayout())
    {
      if (paramString == null)
      {
        paramString = new StringBuilder();
        paramString.append("Cannot call this method while RecyclerView is computing a layout or scrolling");
        paramString.append(exceptionLabel());
        throw new IllegalStateException(paramString.toString());
      }
      throw new IllegalStateException(paramString);
    }
    if (mDispatchScrollCounter > 0)
    {
      paramString = new StringBuilder();
      paramString.append("");
      paramString.append(exceptionLabel());
      Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(paramString.toString()));
    }
  }
  
  boolean canReuseUpdatedViewHolder(ViewHolder paramViewHolder)
  {
    return (mItemAnimator == null) || (mItemAnimator.canReuseUpdatedViewHolder(paramViewHolder, paramViewHolder.getUnmodifiedPayloads()));
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return ((paramLayoutParams instanceof LayoutParams)) && (mLayout.checkLayoutParams((LayoutParams)paramLayoutParams));
  }
  
  void clearOldPositions()
  {
    int j = mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      ViewHolder localViewHolder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
      if (!localViewHolder.shouldIgnore()) {
        localViewHolder.clearOldPosition();
      }
      i += 1;
    }
    mRecycler.clearOldPositions();
  }
  
  public void clearOnChildAttachStateChangeListeners()
  {
    if (mOnChildAttachStateListeners != null) {
      mOnChildAttachStateListeners.clear();
    }
  }
  
  public void clearOnScrollListeners()
  {
    if (mScrollListeners != null) {
      mScrollListeners.clear();
    }
  }
  
  public int computeHorizontalScrollExtent()
  {
    LayoutManager localLayoutManager = mLayout;
    int i = 0;
    if (localLayoutManager == null) {
      return 0;
    }
    if (mLayout.canScrollHorizontally()) {
      i = mLayout.computeHorizontalScrollExtent(mState);
    }
    return i;
  }
  
  public int computeHorizontalScrollOffset()
  {
    LayoutManager localLayoutManager = mLayout;
    int i = 0;
    if (localLayoutManager == null) {
      return 0;
    }
    if (mLayout.canScrollHorizontally()) {
      i = mLayout.computeHorizontalScrollOffset(mState);
    }
    return i;
  }
  
  public int computeHorizontalScrollRange()
  {
    LayoutManager localLayoutManager = mLayout;
    int i = 0;
    if (localLayoutManager == null) {
      return 0;
    }
    if (mLayout.canScrollHorizontally()) {
      i = mLayout.computeHorizontalScrollRange(mState);
    }
    return i;
  }
  
  public int computeVerticalScrollExtent()
  {
    LayoutManager localLayoutManager = mLayout;
    int i = 0;
    if (localLayoutManager == null) {
      return 0;
    }
    if (mLayout.canScrollVertically()) {
      i = mLayout.computeVerticalScrollExtent(mState);
    }
    return i;
  }
  
  public int computeVerticalScrollOffset()
  {
    LayoutManager localLayoutManager = mLayout;
    int i = 0;
    if (localLayoutManager == null) {
      return 0;
    }
    if (mLayout.canScrollVertically()) {
      i = mLayout.computeVerticalScrollOffset(mState);
    }
    return i;
  }
  
  public int computeVerticalScrollRange()
  {
    LayoutManager localLayoutManager = mLayout;
    int i = 0;
    if (localLayoutManager == null) {
      return 0;
    }
    if (mLayout.canScrollVertically()) {
      i = mLayout.computeVerticalScrollRange(mState);
    }
    return i;
  }
  
  void considerReleasingGlowsOnScroll(int paramInt1, int paramInt2)
  {
    if ((mLeftGlow != null) && (!mLeftGlow.isFinished()) && (paramInt1 > 0))
    {
      mLeftGlow.onRelease();
      bool2 = mLeftGlow.isFinished();
    }
    else
    {
      bool2 = false;
    }
    boolean bool1 = bool2;
    if (mRightGlow != null)
    {
      bool1 = bool2;
      if (!mRightGlow.isFinished())
      {
        bool1 = bool2;
        if (paramInt1 < 0)
        {
          mRightGlow.onRelease();
          bool1 = bool2 | mRightGlow.isFinished();
        }
      }
    }
    boolean bool2 = bool1;
    if (mTopGlow != null)
    {
      bool2 = bool1;
      if (!mTopGlow.isFinished())
      {
        bool2 = bool1;
        if (paramInt2 > 0)
        {
          mTopGlow.onRelease();
          bool2 = bool1 | mTopGlow.isFinished();
        }
      }
    }
    bool1 = bool2;
    if (mBottomGlow != null)
    {
      bool1 = bool2;
      if (!mBottomGlow.isFinished())
      {
        bool1 = bool2;
        if (paramInt2 < 0)
        {
          mBottomGlow.onRelease();
          bool1 = bool2 | mBottomGlow.isFinished();
        }
      }
    }
    if (bool1) {
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  void consumePendingUpdateOperations()
  {
    if ((mFirstLayoutComplete) && (!mDataSetHasChangedAfterLayout))
    {
      if (!mAdapterHelper.hasPendingUpdates()) {
        return;
      }
      if ((mAdapterHelper.hasAnyUpdateTypes(4)) && (!mAdapterHelper.hasAnyUpdateTypes(11)))
      {
        TraceCompat.beginSection("RV PartialInvalidate");
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        mAdapterHelper.preProcess();
        if (!mLayoutWasDefered) {
          if (hasUpdatedView()) {
            dispatchLayout();
          } else {
            mAdapterHelper.consumePostponedUpdates();
          }
        }
        stopInterceptRequestLayout(true);
        onExitLayoutOrScroll();
        TraceCompat.endSection();
        return;
      }
      if (mAdapterHelper.hasPendingUpdates())
      {
        TraceCompat.beginSection("RV FullInvalidate");
        dispatchLayout();
        TraceCompat.endSection();
      }
      return;
    }
    TraceCompat.beginSection("RV FullInvalidate");
    dispatchLayout();
    TraceCompat.endSection();
  }
  
  void defaultOnMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(LayoutManager.chooseSize(paramInt1, getPaddingLeft() + getPaddingRight(), ViewCompat.getMinimumWidth(this)), LayoutManager.chooseSize(paramInt2, getPaddingTop() + getPaddingBottom(), ViewCompat.getMinimumHeight(this)));
  }
  
  void dispatchChildAttached(View paramView)
  {
    ViewHolder localViewHolder = getChildViewHolderInt(paramView);
    onChildAttachedToWindow(paramView);
    if ((mAdapter != null) && (localViewHolder != null)) {
      mAdapter.onViewAttachedToWindow(localViewHolder);
    }
    if (mOnChildAttachStateListeners != null)
    {
      int i = mOnChildAttachStateListeners.size() - 1;
      while (i >= 0)
      {
        ((OnChildAttachStateChangeListener)mOnChildAttachStateListeners.get(i)).onChildViewAttachedToWindow(paramView);
        i -= 1;
      }
    }
  }
  
  void dispatchChildDetached(View paramView)
  {
    ViewHolder localViewHolder = getChildViewHolderInt(paramView);
    onChildDetachedFromWindow(paramView);
    if ((mAdapter != null) && (localViewHolder != null)) {
      mAdapter.onViewDetachedFromWindow(localViewHolder);
    }
    if (mOnChildAttachStateListeners != null)
    {
      int i = mOnChildAttachStateListeners.size() - 1;
      while (i >= 0)
      {
        ((OnChildAttachStateChangeListener)mOnChildAttachStateListeners.get(i)).onChildViewDetachedFromWindow(paramView);
        i -= 1;
      }
    }
  }
  
  void dispatchLayout()
  {
    if (mAdapter == null)
    {
      Log.e("RecyclerView", "No adapter attached; skipping layout");
      return;
    }
    if (mLayout == null)
    {
      Log.e("RecyclerView", "No layout manager attached; skipping layout");
      return;
    }
    mState.mIsMeasuring = false;
    if (mState.mLayoutStep == 1)
    {
      dispatchLayoutStep1();
      mLayout.setExactMeasureSpecsFrom(this);
      dispatchLayoutStep2();
    }
    else if ((!mAdapterHelper.hasUpdates()) && (mLayout.getWidth() == getWidth()) && (mLayout.getHeight() == getHeight()))
    {
      mLayout.setExactMeasureSpecsFrom(this);
    }
    else
    {
      mLayout.setExactMeasureSpecsFrom(this);
      dispatchLayoutStep2();
    }
    dispatchLayoutStep3();
  }
  
  public boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    return getScrollingChildHelper().dispatchNestedFling(paramFloat1, paramFloat2, paramBoolean);
  }
  
  public boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2)
  {
    return getScrollingChildHelper().dispatchNestedPreFling(paramFloat1, paramFloat2);
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    return getScrollingChildHelper().dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2);
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt3)
  {
    return getScrollingChildHelper().dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2, paramInt3);
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
  {
    return getScrollingChildHelper().dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt);
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, int paramInt5)
  {
    return getScrollingChildHelper().dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt, paramInt5);
  }
  
  void dispatchOnScrollStateChanged(int paramInt)
  {
    if (mLayout != null) {
      mLayout.onScrollStateChanged(paramInt);
    }
    onScrollStateChanged(paramInt);
    if (mScrollListener != null) {
      mScrollListener.onScrollStateChanged(this, paramInt);
    }
    if (mScrollListeners != null)
    {
      int i = mScrollListeners.size() - 1;
      while (i >= 0)
      {
        ((OnScrollListener)mScrollListeners.get(i)).onScrollStateChanged(this, paramInt);
        i -= 1;
      }
    }
  }
  
  void dispatchOnScrolled(int paramInt1, int paramInt2)
  {
    mDispatchScrollCounter += 1;
    int i = getScrollX();
    int j = getScrollY();
    onScrollChanged(i, j, i, j);
    onScrolled(paramInt1, paramInt2);
    if (mScrollListener != null) {
      mScrollListener.onScrolled(this, paramInt1, paramInt2);
    }
    if (mScrollListeners != null)
    {
      i = mScrollListeners.size() - 1;
      while (i >= 0)
      {
        ((OnScrollListener)mScrollListeners.get(i)).onScrolled(this, paramInt1, paramInt2);
        i -= 1;
      }
    }
    mDispatchScrollCounter -= 1;
  }
  
  void dispatchPendingImportantForAccessibilityChanges()
  {
    int i = mPendingAccessibilityImportanceChange.size() - 1;
    while (i >= 0)
    {
      ViewHolder localViewHolder = (ViewHolder)mPendingAccessibilityImportanceChange.get(i);
      if ((itemView.getParent() == this) && (!localViewHolder.shouldIgnore()))
      {
        int j = mPendingAccessibilityState;
        if (j != -1)
        {
          ViewCompat.setImportantForAccessibility(itemView, j);
          mPendingAccessibilityState = -1;
        }
      }
      i -= 1;
    }
    mPendingAccessibilityImportanceChange.clear();
  }
  
  protected void dispatchRestoreInstanceState(SparseArray<Parcelable> paramSparseArray)
  {
    dispatchThawSelfOnly(paramSparseArray);
  }
  
  protected void dispatchSaveInstanceState(SparseArray<Parcelable> paramSparseArray)
  {
    dispatchFreezeSelfOnly(paramSparseArray);
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    int j = mItemDecorations.size();
    int k = 0;
    int i = 0;
    while (i < j)
    {
      ((ItemDecoration)mItemDecorations.get(i)).onDrawOver(paramCanvas, this, mState);
      i += 1;
    }
    int m;
    if ((mLeftGlow != null) && (!mLeftGlow.isFinished()))
    {
      m = paramCanvas.save();
      if (mClipToPadding) {
        i = getPaddingBottom();
      } else {
        i = 0;
      }
      paramCanvas.rotate(270.0F);
      paramCanvas.translate(-getHeight() + i, 0.0F);
      if ((mLeftGlow != null) && (mLeftGlow.draw(paramCanvas))) {
        j = 1;
      } else {
        j = 0;
      }
      paramCanvas.restoreToCount(m);
    }
    else
    {
      j = 0;
    }
    i = j;
    if (mTopGlow != null)
    {
      i = j;
      if (!mTopGlow.isFinished())
      {
        m = paramCanvas.save();
        if (mClipToPadding) {
          paramCanvas.translate(getPaddingLeft(), getPaddingTop());
        }
        if ((mTopGlow != null) && (mTopGlow.draw(paramCanvas))) {
          i = 1;
        } else {
          i = 0;
        }
        i = j | i;
        paramCanvas.restoreToCount(m);
      }
    }
    j = i;
    if (mRightGlow != null)
    {
      j = i;
      if (!mRightGlow.isFinished())
      {
        m = paramCanvas.save();
        int n = getWidth();
        if (mClipToPadding) {
          j = getPaddingTop();
        } else {
          j = 0;
        }
        paramCanvas.rotate(90.0F);
        paramCanvas.translate(-j, -n);
        if ((mRightGlow != null) && (mRightGlow.draw(paramCanvas))) {
          j = 1;
        } else {
          j = 0;
        }
        j = i | j;
        paramCanvas.restoreToCount(m);
      }
    }
    if ((mBottomGlow != null) && (!mBottomGlow.isFinished()))
    {
      m = paramCanvas.save();
      paramCanvas.rotate(180.0F);
      if (mClipToPadding) {
        paramCanvas.translate(-getWidth() + getPaddingRight(), -getHeight() + getPaddingBottom());
      } else {
        paramCanvas.translate(-getWidth(), -getHeight());
      }
      i = k;
      if (mBottomGlow != null)
      {
        i = k;
        if (mBottomGlow.draw(paramCanvas)) {
          i = 1;
        }
      }
      i |= j;
      paramCanvas.restoreToCount(m);
    }
    else
    {
      i = j;
    }
    j = i;
    if (i == 0)
    {
      j = i;
      if (mItemAnimator != null)
      {
        j = i;
        if (mItemDecorations.size() > 0)
        {
          j = i;
          if (mItemAnimator.isRunning()) {
            j = 1;
          }
        }
      }
    }
    if (j != 0) {
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  public boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    return super.drawChild(paramCanvas, paramView, paramLong);
  }
  
  void ensureBottomGlow()
  {
    if (mBottomGlow != null) {
      return;
    }
    mBottomGlow = mEdgeEffectFactory.createEdgeEffect(this, 3);
    if (mClipToPadding)
    {
      mBottomGlow.setSize(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
      return;
    }
    mBottomGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
  }
  
  void ensureLeftGlow()
  {
    if (mLeftGlow != null) {
      return;
    }
    mLeftGlow = mEdgeEffectFactory.createEdgeEffect(this, 0);
    if (mClipToPadding)
    {
      mLeftGlow.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
      return;
    }
    mLeftGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
  }
  
  void ensureRightGlow()
  {
    if (mRightGlow != null) {
      return;
    }
    mRightGlow = mEdgeEffectFactory.createEdgeEffect(this, 2);
    if (mClipToPadding)
    {
      mRightGlow.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
      return;
    }
    mRightGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
  }
  
  void ensureTopGlow()
  {
    if (mTopGlow != null) {
      return;
    }
    mTopGlow = mEdgeEffectFactory.createEdgeEffect(this, 1);
    if (mClipToPadding)
    {
      mTopGlow.setSize(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
      return;
    }
    mTopGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
  }
  
  String exceptionLabel()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(" ");
    localStringBuilder.append(super.toString());
    localStringBuilder.append(", adapter:");
    localStringBuilder.append(mAdapter);
    localStringBuilder.append(", layout:");
    localStringBuilder.append(mLayout);
    localStringBuilder.append(", context:");
    localStringBuilder.append(getContext());
    return localStringBuilder.toString();
  }
  
  final void fillRemainingScrollValues(State paramState)
  {
    if (getScrollState() == 2)
    {
      OverScroller localOverScroller = mViewFlinger.mScroller;
      mRemainingScrollHorizontal = (localOverScroller.getFinalX() - localOverScroller.getCurrX());
      mRemainingScrollVertical = (localOverScroller.getFinalY() - localOverScroller.getCurrY());
      return;
    }
    mRemainingScrollHorizontal = 0;
    mRemainingScrollVertical = 0;
  }
  
  public View findChildViewUnder(float paramFloat1, float paramFloat2)
  {
    int i = mChildHelper.getChildCount() - 1;
    while (i >= 0)
    {
      View localView = mChildHelper.getChildAt(i);
      float f1 = localView.getTranslationX();
      float f2 = localView.getTranslationY();
      if ((paramFloat1 >= localView.getLeft() + f1) && (paramFloat1 <= localView.getRight() + f1) && (paramFloat2 >= localView.getTop() + f2) && (paramFloat2 <= localView.getBottom() + f2)) {
        return localView;
      }
      i -= 1;
    }
    return null;
  }
  
  @Nullable
  public View findContainingItemView(View paramView)
  {
    ViewParent localViewParent = paramView.getParent();
    View localView = paramView;
    for (paramView = localViewParent; (paramView != null) && (paramView != this) && ((paramView instanceof View)); paramView = localView.getParent()) {
      localView = (View)paramView;
    }
    if (paramView == this) {
      return localView;
    }
    return null;
  }
  
  @Nullable
  public ViewHolder findContainingViewHolder(View paramView)
  {
    paramView = findContainingItemView(paramView);
    if (paramView == null) {
      return null;
    }
    return getChildViewHolder(paramView);
  }
  
  public ViewHolder findViewHolderForAdapterPosition(int paramInt)
  {
    boolean bool = mDataSetHasChangedAfterLayout;
    Object localObject1 = null;
    if (bool) {
      return null;
    }
    int j = mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      ViewHolder localViewHolder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
      Object localObject2 = localObject1;
      if (localViewHolder != null)
      {
        localObject2 = localObject1;
        if (!localViewHolder.isRemoved())
        {
          localObject2 = localObject1;
          if (getAdapterPositionFor(localViewHolder) == paramInt) {
            if (mChildHelper.isHidden(itemView)) {
              localObject2 = localViewHolder;
            } else {
              return localViewHolder;
            }
          }
        }
      }
      i += 1;
      localObject1 = localObject2;
    }
    return localObject1;
  }
  
  public ViewHolder findViewHolderForItemId(long paramLong)
  {
    Object localObject2 = mAdapter;
    Object localObject1 = null;
    if (localObject2 != null)
    {
      if (!mAdapter.hasStableIds()) {
        return null;
      }
      int j = mChildHelper.getUnfilteredChildCount();
      int i = 0;
      while (i < j)
      {
        ViewHolder localViewHolder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
        localObject2 = localObject1;
        if (localViewHolder != null)
        {
          localObject2 = localObject1;
          if (!localViewHolder.isRemoved())
          {
            localObject2 = localObject1;
            if (localViewHolder.getItemId() == paramLong) {
              if (mChildHelper.isHidden(itemView)) {
                localObject2 = localViewHolder;
              } else {
                return localViewHolder;
              }
            }
          }
        }
        i += 1;
        localObject1 = localObject2;
      }
      return localObject1;
    }
    return null;
  }
  
  public ViewHolder findViewHolderForLayoutPosition(int paramInt)
  {
    return findViewHolderForPosition(paramInt, false);
  }
  
  @Deprecated
  public ViewHolder findViewHolderForPosition(int paramInt)
  {
    return findViewHolderForPosition(paramInt, false);
  }
  
  ViewHolder findViewHolderForPosition(int paramInt, boolean paramBoolean)
  {
    int j = mChildHelper.getUnfilteredChildCount();
    Object localObject1 = null;
    int i = 0;
    while (i < j)
    {
      ViewHolder localViewHolder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
      Object localObject2 = localObject1;
      if (localViewHolder != null)
      {
        localObject2 = localObject1;
        if (!localViewHolder.isRemoved())
        {
          if (paramBoolean)
          {
            if (mPosition != paramInt)
            {
              localObject2 = localObject1;
              break label115;
            }
          }
          else if (localViewHolder.getLayoutPosition() != paramInt)
          {
            localObject2 = localObject1;
            break label115;
          }
          if (mChildHelper.isHidden(itemView)) {
            localObject2 = localViewHolder;
          } else {
            return localViewHolder;
          }
        }
      }
      label115:
      i += 1;
      localObject1 = localObject2;
    }
    return localObject1;
  }
  
  public boolean fling(int paramInt1, int paramInt2)
  {
    LayoutManager localLayoutManager = mLayout;
    int k = 0;
    if (localLayoutManager == null)
    {
      Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
      return false;
    }
    if (mLayoutFrozen) {
      return false;
    }
    boolean bool2 = mLayout.canScrollHorizontally();
    boolean bool3 = mLayout.canScrollVertically();
    int i;
    if (bool2)
    {
      i = paramInt1;
      if (Math.abs(paramInt1) >= mMinFlingVelocity) {}
    }
    else
    {
      i = 0;
    }
    int j;
    if (bool3)
    {
      j = paramInt2;
      if (Math.abs(paramInt2) >= mMinFlingVelocity) {}
    }
    else
    {
      j = 0;
    }
    if ((i == 0) && (j == 0)) {
      return false;
    }
    float f1 = i;
    float f2 = j;
    if (!dispatchNestedPreFling(f1, f2))
    {
      boolean bool1;
      if ((!bool2) && (!bool3)) {
        bool1 = false;
      } else {
        bool1 = true;
      }
      dispatchNestedFling(f1, f2, bool1);
      if ((mOnFlingListener != null) && (mOnFlingListener.onFling(i, j))) {
        return true;
      }
      if (bool1)
      {
        paramInt1 = k;
        if (bool2) {
          paramInt1 = 1;
        }
        paramInt2 = paramInt1;
        if (bool3) {
          paramInt2 = paramInt1 | 0x2;
        }
        startNestedScroll(paramInt2, 1);
        paramInt1 = Math.max(-mMaxFlingVelocity, Math.min(i, mMaxFlingVelocity));
        paramInt2 = Math.max(-mMaxFlingVelocity, Math.min(j, mMaxFlingVelocity));
        mViewFlinger.fling(paramInt1, paramInt2);
        return true;
      }
    }
    return false;
  }
  
  public View focusSearch(View paramView, int paramInt)
  {
    Object localObject = mLayout.onInterceptFocusSearch(paramView, paramInt);
    if (localObject != null) {
      return localObject;
    }
    int i;
    if ((mAdapter != null) && (mLayout != null) && (!isComputingLayout()) && (!mLayoutFrozen)) {
      i = 1;
    } else {
      i = 0;
    }
    localObject = FocusFinder.getInstance();
    if ((i != 0) && ((paramInt == 2) || (paramInt == 1)))
    {
      int j;
      if (mLayout.canScrollVertically())
      {
        if (paramInt == 2) {
          j = 130;
        } else {
          j = 33;
        }
        if (((FocusFinder)localObject).findNextFocus(this, paramView, j) == null) {
          k = 1;
        } else {
          k = 0;
        }
        i = k;
        if (FORCE_ABS_FOCUS_SEARCH_DIRECTION)
        {
          paramInt = j;
          i = k;
        }
      }
      else
      {
        i = 0;
      }
      int m = i;
      int k = paramInt;
      if (i == 0)
      {
        m = i;
        k = paramInt;
        if (mLayout.canScrollHorizontally())
        {
          if (mLayout.getLayoutDirection() == 1) {
            i = 1;
          } else {
            i = 0;
          }
          if (paramInt == 2) {
            j = 1;
          } else {
            j = 0;
          }
          if ((i ^ j) != 0) {
            i = 66;
          } else {
            i = 17;
          }
          if (((FocusFinder)localObject).findNextFocus(this, paramView, i) == null) {
            j = 1;
          } else {
            j = 0;
          }
          m = j;
          k = paramInt;
          if (FORCE_ABS_FOCUS_SEARCH_DIRECTION)
          {
            k = i;
            m = j;
          }
        }
      }
      if (m != 0)
      {
        consumePendingUpdateOperations();
        if (findContainingItemView(paramView) == null) {
          return null;
        }
        startInterceptRequestLayout();
        mLayout.onFocusSearchFailed(paramView, k, mRecycler, mState);
        stopInterceptRequestLayout(false);
      }
      localObject = ((FocusFinder)localObject).findNextFocus(this, paramView, k);
      paramInt = k;
    }
    else
    {
      localObject = ((FocusFinder)localObject).findNextFocus(this, paramView, paramInt);
      if ((localObject == null) && (i != 0))
      {
        consumePendingUpdateOperations();
        if (findContainingItemView(paramView) == null) {
          return null;
        }
        startInterceptRequestLayout();
        localObject = mLayout.onFocusSearchFailed(paramView, paramInt, mRecycler, mState);
        stopInterceptRequestLayout(false);
      }
    }
    if ((localObject != null) && (!((View)localObject).hasFocusable()))
    {
      if (getFocusedChild() == null) {
        return super.focusSearch(paramView, paramInt);
      }
      requestChildOnScreen((View)localObject, null);
      return paramView;
    }
    if (isPreferredNextFocus(paramView, (View)localObject, paramInt)) {
      return localObject;
    }
    return super.focusSearch(paramView, paramInt);
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    if (mLayout == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("RecyclerView has no LayoutManager");
      localStringBuilder.append(exceptionLabel());
      throw new IllegalStateException(localStringBuilder.toString());
    }
    return mLayout.generateDefaultLayoutParams();
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    if (mLayout == null)
    {
      paramAttributeSet = new StringBuilder();
      paramAttributeSet.append("RecyclerView has no LayoutManager");
      paramAttributeSet.append(exceptionLabel());
      throw new IllegalStateException(paramAttributeSet.toString());
    }
    return mLayout.generateLayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if (mLayout == null)
    {
      paramLayoutParams = new StringBuilder();
      paramLayoutParams.append("RecyclerView has no LayoutManager");
      paramLayoutParams.append(exceptionLabel());
      throw new IllegalStateException(paramLayoutParams.toString());
    }
    return mLayout.generateLayoutParams(paramLayoutParams);
  }
  
  public Adapter getAdapter()
  {
    return mAdapter;
  }
  
  int getAdapterPositionFor(ViewHolder paramViewHolder)
  {
    if ((!paramViewHolder.hasAnyOfTheFlags(524)) && (paramViewHolder.isBound())) {
      return mAdapterHelper.applyPendingUpdatesToPosition(mPosition);
    }
    return -1;
  }
  
  public int getBaseline()
  {
    if (mLayout != null) {
      return mLayout.getBaseline();
    }
    return super.getBaseline();
  }
  
  long getChangedHolderKey(ViewHolder paramViewHolder)
  {
    if (mAdapter.hasStableIds()) {
      return paramViewHolder.getItemId();
    }
    return mPosition;
  }
  
  public int getChildAdapterPosition(View paramView)
  {
    paramView = getChildViewHolderInt(paramView);
    if (paramView != null) {
      return paramView.getAdapterPosition();
    }
    return -1;
  }
  
  protected int getChildDrawingOrder(int paramInt1, int paramInt2)
  {
    if (mChildDrawingOrderCallback == null) {
      return super.getChildDrawingOrder(paramInt1, paramInt2);
    }
    return mChildDrawingOrderCallback.onGetChildDrawingOrder(paramInt1, paramInt2);
  }
  
  public long getChildItemId(View paramView)
  {
    Adapter localAdapter = mAdapter;
    long l = -1L;
    if (localAdapter != null)
    {
      if (!mAdapter.hasStableIds()) {
        return -1L;
      }
      paramView = getChildViewHolderInt(paramView);
      if (paramView != null) {
        l = paramView.getItemId();
      }
      return l;
    }
    return -1L;
  }
  
  public int getChildLayoutPosition(View paramView)
  {
    paramView = getChildViewHolderInt(paramView);
    if (paramView != null) {
      return paramView.getLayoutPosition();
    }
    return -1;
  }
  
  @Deprecated
  public int getChildPosition(View paramView)
  {
    return getChildAdapterPosition(paramView);
  }
  
  public ViewHolder getChildViewHolder(View paramView)
  {
    Object localObject = paramView.getParent();
    if ((localObject != null) && (localObject != this))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("View ");
      ((StringBuilder)localObject).append(paramView);
      ((StringBuilder)localObject).append(" is not a direct child of ");
      ((StringBuilder)localObject).append(this);
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    return getChildViewHolderInt(paramView);
  }
  
  public boolean getClipToPadding()
  {
    return mClipToPadding;
  }
  
  public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate()
  {
    return mAccessibilityDelegate;
  }
  
  public void getDecoratedBoundsWithMargins(View paramView, Rect paramRect)
  {
    getDecoratedBoundsWithMarginsInt(paramView, paramRect);
  }
  
  public EdgeEffectFactory getEdgeEffectFactory()
  {
    return mEdgeEffectFactory;
  }
  
  public ItemAnimator getItemAnimator()
  {
    return mItemAnimator;
  }
  
  Rect getItemDecorInsetsForChild(View paramView)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if (!mInsetsDirty) {
      return mDecorInsets;
    }
    if ((mState.isPreLayout()) && ((localLayoutParams.isItemChanged()) || (localLayoutParams.isViewInvalid()))) {
      return mDecorInsets;
    }
    Rect localRect = mDecorInsets;
    localRect.set(0, 0, 0, 0);
    int j = mItemDecorations.size();
    int i = 0;
    while (i < j)
    {
      mTempRect.set(0, 0, 0, 0);
      ((ItemDecoration)mItemDecorations.get(i)).getItemOffsets(mTempRect, paramView, this, mState);
      left += mTempRect.left;
      top += mTempRect.top;
      right += mTempRect.right;
      bottom += mTempRect.bottom;
      i += 1;
    }
    mInsetsDirty = false;
    return localRect;
  }
  
  public ItemDecoration getItemDecorationAt(int paramInt)
  {
    int i = getItemDecorationCount();
    if ((paramInt >= 0) && (paramInt < i)) {
      return (ItemDecoration)mItemDecorations.get(paramInt);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" is an invalid index for size ");
    localStringBuilder.append(i);
    throw new IndexOutOfBoundsException(localStringBuilder.toString());
  }
  
  public int getItemDecorationCount()
  {
    return mItemDecorations.size();
  }
  
  public LayoutManager getLayoutManager()
  {
    return mLayout;
  }
  
  public int getMaxFlingVelocity()
  {
    return mMaxFlingVelocity;
  }
  
  public int getMinFlingVelocity()
  {
    return mMinFlingVelocity;
  }
  
  long getNanoTime()
  {
    if (ALLOW_THREAD_GAP_WORK) {
      return System.nanoTime();
    }
    return 0L;
  }
  
  @Nullable
  public OnFlingListener getOnFlingListener()
  {
    return mOnFlingListener;
  }
  
  public boolean getPreserveFocusAfterLayout()
  {
    return mPreserveFocusAfterLayout;
  }
  
  public RecycledViewPool getRecycledViewPool()
  {
    return mRecycler.getRecycledViewPool();
  }
  
  public int getScrollState()
  {
    return mScrollState;
  }
  
  public boolean hasFixedSize()
  {
    return mHasFixedSize;
  }
  
  public boolean hasNestedScrollingParent()
  {
    return getScrollingChildHelper().hasNestedScrollingParent();
  }
  
  public boolean hasNestedScrollingParent(int paramInt)
  {
    return getScrollingChildHelper().hasNestedScrollingParent(paramInt);
  }
  
  public boolean hasPendingAdapterUpdates()
  {
    return (!mFirstLayoutComplete) || (mDataSetHasChangedAfterLayout) || (mAdapterHelper.hasPendingUpdates());
  }
  
  void initAdapterManager()
  {
    mAdapterHelper = new AdapterHelper(new AdapterHelper.Callback()
    {
      void dispatchUpdate(AdapterHelper.UpdateOp paramAnonymousUpdateOp)
      {
        int i = cmd;
        if (i != 4)
        {
          if (i != 8)
          {
            switch (i)
            {
            default: 
              return;
            case 2: 
              mLayout.onItemsRemoved(RecyclerView.this, positionStart, itemCount);
              return;
            }
            mLayout.onItemsAdded(RecyclerView.this, positionStart, itemCount);
            return;
          }
          mLayout.onItemsMoved(RecyclerView.this, positionStart, itemCount, 1);
          return;
        }
        mLayout.onItemsUpdated(RecyclerView.this, positionStart, itemCount, payload);
      }
      
      public RecyclerView.ViewHolder findViewHolder(int paramAnonymousInt)
      {
        RecyclerView.ViewHolder localViewHolder = findViewHolderForPosition(paramAnonymousInt, true);
        if (localViewHolder == null) {
          return null;
        }
        if (mChildHelper.isHidden(itemView)) {
          return null;
        }
        return localViewHolder;
      }
      
      public void markViewHoldersUpdated(int paramAnonymousInt1, int paramAnonymousInt2, Object paramAnonymousObject)
      {
        viewRangeUpdate(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousObject);
        mItemsChanged = true;
      }
      
      public void offsetPositionsForAdd(int paramAnonymousInt1, int paramAnonymousInt2)
      {
        offsetPositionRecordsForInsert(paramAnonymousInt1, paramAnonymousInt2);
        mItemsAddedOrRemoved = true;
      }
      
      public void offsetPositionsForMove(int paramAnonymousInt1, int paramAnonymousInt2)
      {
        offsetPositionRecordsForMove(paramAnonymousInt1, paramAnonymousInt2);
        mItemsAddedOrRemoved = true;
      }
      
      public void offsetPositionsForRemovingInvisible(int paramAnonymousInt1, int paramAnonymousInt2)
      {
        offsetPositionRecordsForRemove(paramAnonymousInt1, paramAnonymousInt2, true);
        mItemsAddedOrRemoved = true;
        RecyclerView.State localState = mState;
        mDeletedInvisibleItemCountSincePreviousLayout += paramAnonymousInt2;
      }
      
      public void offsetPositionsForRemovingLaidOutOrNewView(int paramAnonymousInt1, int paramAnonymousInt2)
      {
        offsetPositionRecordsForRemove(paramAnonymousInt1, paramAnonymousInt2, false);
        mItemsAddedOrRemoved = true;
      }
      
      public void onDispatchFirstPass(AdapterHelper.UpdateOp paramAnonymousUpdateOp)
      {
        dispatchUpdate(paramAnonymousUpdateOp);
      }
      
      public void onDispatchSecondPass(AdapterHelper.UpdateOp paramAnonymousUpdateOp)
      {
        dispatchUpdate(paramAnonymousUpdateOp);
      }
    });
  }
  
  @VisibleForTesting
  void initFastScroller(StateListDrawable paramStateListDrawable1, Drawable paramDrawable1, StateListDrawable paramStateListDrawable2, Drawable paramDrawable2)
  {
    if ((paramStateListDrawable1 != null) && (paramDrawable1 != null) && (paramStateListDrawable2 != null) && (paramDrawable2 != null))
    {
      Resources localResources = getContext().getResources();
      new FastScroller(this, paramStateListDrawable1, paramDrawable1, paramStateListDrawable2, paramDrawable2, localResources.getDimensionPixelSize(R.dimen.fastscroll_default_thickness), localResources.getDimensionPixelSize(R.dimen.fastscroll_minimum_range), localResources.getDimensionPixelOffset(R.dimen.fastscroll_margin));
      return;
    }
    paramStateListDrawable1 = new StringBuilder();
    paramStateListDrawable1.append("Trying to set fast scroller without both required drawables.");
    paramStateListDrawable1.append(exceptionLabel());
    throw new IllegalArgumentException(paramStateListDrawable1.toString());
  }
  
  void invalidateGlows()
  {
    mBottomGlow = null;
    mTopGlow = null;
    mRightGlow = null;
    mLeftGlow = null;
  }
  
  public void invalidateItemDecorations()
  {
    if (mItemDecorations.size() == 0) {
      return;
    }
    if (mLayout != null) {
      mLayout.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
    }
    markItemDecorInsetsDirty();
    requestLayout();
  }
  
  boolean isAccessibilityEnabled()
  {
    return (mAccessibilityManager != null) && (mAccessibilityManager.isEnabled());
  }
  
  public boolean isAnimating()
  {
    return (mItemAnimator != null) && (mItemAnimator.isRunning());
  }
  
  public boolean isAttachedToWindow()
  {
    return mIsAttached;
  }
  
  public boolean isComputingLayout()
  {
    return mLayoutOrScrollCounter > 0;
  }
  
  public boolean isLayoutFrozen()
  {
    return mLayoutFrozen;
  }
  
  public boolean isNestedScrollingEnabled()
  {
    return getScrollingChildHelper().isNestedScrollingEnabled();
  }
  
  void jumpToPositionForSmoothScroller(int paramInt)
  {
    if (mLayout == null) {
      return;
    }
    mLayout.scrollToPosition(paramInt);
    awakenScrollBars();
  }
  
  void markItemDecorInsetsDirty()
  {
    int j = mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      mChildHelper.getUnfilteredChildAt(i).getLayoutParams()).mInsetsDirty = true;
      i += 1;
    }
    mRecycler.markItemDecorInsetsDirty();
  }
  
  void markKnownViewsInvalid()
  {
    int j = mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      ViewHolder localViewHolder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
      if ((localViewHolder != null) && (!localViewHolder.shouldIgnore())) {
        localViewHolder.addFlags(6);
      }
      i += 1;
    }
    markItemDecorInsetsDirty();
    mRecycler.markKnownViewsInvalid();
  }
  
  public void offsetChildrenHorizontal(int paramInt)
  {
    int j = mChildHelper.getChildCount();
    int i = 0;
    while (i < j)
    {
      mChildHelper.getChildAt(i).offsetLeftAndRight(paramInt);
      i += 1;
    }
  }
  
  public void offsetChildrenVertical(int paramInt)
  {
    int j = mChildHelper.getChildCount();
    int i = 0;
    while (i < j)
    {
      mChildHelper.getChildAt(i).offsetTopAndBottom(paramInt);
      i += 1;
    }
  }
  
  void offsetPositionRecordsForInsert(int paramInt1, int paramInt2)
  {
    int j = mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      ViewHolder localViewHolder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
      if ((localViewHolder != null) && (!localViewHolder.shouldIgnore()) && (mPosition >= paramInt1))
      {
        localViewHolder.offsetPosition(paramInt2, false);
        mState.mStructureChanged = true;
      }
      i += 1;
    }
    mRecycler.offsetPositionRecordsForInsert(paramInt1, paramInt2);
    requestLayout();
  }
  
  void offsetPositionRecordsForMove(int paramInt1, int paramInt2)
  {
    int n = mChildHelper.getUnfilteredChildCount();
    int j;
    int k;
    int i;
    if (paramInt1 < paramInt2)
    {
      j = paramInt2;
      k = -1;
      i = paramInt1;
    }
    else
    {
      j = paramInt1;
      i = paramInt2;
      k = 1;
    }
    int m = 0;
    while (m < n)
    {
      ViewHolder localViewHolder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(m));
      if ((localViewHolder != null) && (mPosition >= i) && (mPosition <= j))
      {
        if (mPosition == paramInt1) {
          localViewHolder.offsetPosition(paramInt2 - paramInt1, false);
        } else {
          localViewHolder.offsetPosition(k, false);
        }
        mState.mStructureChanged = true;
      }
      m += 1;
    }
    mRecycler.offsetPositionRecordsForMove(paramInt1, paramInt2);
    requestLayout();
  }
  
  void offsetPositionRecordsForRemove(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int j = mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      ViewHolder localViewHolder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
      if ((localViewHolder != null) && (!localViewHolder.shouldIgnore())) {
        if (mPosition >= paramInt1 + paramInt2)
        {
          localViewHolder.offsetPosition(-paramInt2, paramBoolean);
          mState.mStructureChanged = true;
        }
        else if (mPosition >= paramInt1)
        {
          localViewHolder.flagRemovedAndOffsetPosition(paramInt1 - 1, -paramInt2, paramBoolean);
          mState.mStructureChanged = true;
        }
      }
      i += 1;
    }
    mRecycler.offsetPositionRecordsForRemove(paramInt1, paramInt2, paramBoolean);
    requestLayout();
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    mLayoutOrScrollCounter = 0;
    boolean bool = true;
    mIsAttached = true;
    if ((!mFirstLayoutComplete) || (isLayoutRequested())) {
      bool = false;
    }
    mFirstLayoutComplete = bool;
    if (mLayout != null) {
      mLayout.dispatchAttachedToWindow(this);
    }
    mPostedAnimatorRunner = false;
    if (ALLOW_THREAD_GAP_WORK)
    {
      mGapWorker = ((GapWorker)GapWorker.sGapWorker.get());
      if (mGapWorker == null)
      {
        mGapWorker = new GapWorker();
        Display localDisplay = ViewCompat.getDisplay(this);
        float f;
        if ((!isInEditMode()) && (localDisplay != null))
        {
          f = localDisplay.getRefreshRate();
          if (f >= 30.0F) {}
        }
        else
        {
          f = 60.0F;
        }
        mGapWorker.mFrameIntervalNs = ((1.0E9F / f));
        GapWorker.sGapWorker.set(mGapWorker);
      }
      mGapWorker.add(this);
    }
  }
  
  public void onChildAttachedToWindow(View paramView) {}
  
  public void onChildDetachedFromWindow(View paramView) {}
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (mItemAnimator != null) {
      mItemAnimator.endAnimations();
    }
    stopScroll();
    mIsAttached = false;
    if (mLayout != null) {
      mLayout.dispatchDetachedFromWindow(this, mRecycler);
    }
    mPendingAccessibilityImportanceChange.clear();
    removeCallbacks(mItemAnimatorRunner);
    mViewInfoStore.onDetach();
    if ((ALLOW_THREAD_GAP_WORK) && (mGapWorker != null))
    {
      mGapWorker.remove(this);
      mGapWorker = null;
    }
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int j = mItemDecorations.size();
    int i = 0;
    while (i < j)
    {
      ((ItemDecoration)mItemDecorations.get(i)).onDraw(paramCanvas, this, mState);
      i += 1;
    }
  }
  
  void onEnterLayoutOrScroll()
  {
    mLayoutOrScrollCounter += 1;
  }
  
  void onExitLayoutOrScroll()
  {
    onExitLayoutOrScroll(true);
  }
  
  void onExitLayoutOrScroll(boolean paramBoolean)
  {
    mLayoutOrScrollCounter -= 1;
    if (mLayoutOrScrollCounter < 1)
    {
      mLayoutOrScrollCounter = 0;
      if (paramBoolean)
      {
        dispatchContentChangedIfNecessary();
        dispatchPendingImportantForAccessibilityChanges();
      }
    }
  }
  
  public boolean onGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    if (mLayout == null) {
      return false;
    }
    if (mLayoutFrozen) {
      return false;
    }
    if (paramMotionEvent.getAction() == 8)
    {
      float f1;
      float f2;
      if ((paramMotionEvent.getSource() & 0x2) != 0)
      {
        if (mLayout.canScrollVertically()) {
          f1 = -paramMotionEvent.getAxisValue(9);
        } else {
          f1 = 0.0F;
        }
        f2 = f1;
        if (mLayout.canScrollHorizontally())
        {
          f2 = paramMotionEvent.getAxisValue(10);
          break label145;
        }
      }
      for (;;)
      {
        float f3 = 0.0F;
        f1 = f2;
        f2 = f3;
        break label145;
        if ((paramMotionEvent.getSource() & 0x400000) == 0) {
          break label141;
        }
        f2 = paramMotionEvent.getAxisValue(26);
        if (!mLayout.canScrollVertically()) {
          break;
        }
        f2 = -f2;
      }
      if (mLayout.canScrollHorizontally())
      {
        f1 = 0.0F;
      }
      else
      {
        label141:
        f1 = 0.0F;
        f2 = f1;
      }
      label145:
      if ((f1 != 0.0F) || (f2 != 0.0F)) {
        scrollByInternal((int)(f2 * mScaledHorizontalScrollFactor), (int)(f1 * mScaledVerticalScrollFactor), paramMotionEvent);
      }
    }
    return false;
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool2 = mLayoutFrozen;
    boolean bool1 = false;
    if (bool2) {
      return false;
    }
    if (dispatchOnItemTouchIntercept(paramMotionEvent))
    {
      cancelTouch();
      return true;
    }
    if (mLayout == null) {
      return false;
    }
    bool2 = mLayout.canScrollHorizontally();
    boolean bool3 = mLayout.canScrollVertically();
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
    mVelocityTracker.addMovement(paramMotionEvent);
    int j = paramMotionEvent.getActionMasked();
    int i = paramMotionEvent.getActionIndex();
    switch (j)
    {
    case 4: 
    default: 
      break;
    case 6: 
      onPointerUp(paramMotionEvent);
      break;
    case 5: 
      mScrollPointerId = paramMotionEvent.getPointerId(i);
      j = (int)(paramMotionEvent.getX(i) + 0.5F);
      mLastTouchX = j;
      mInitialTouchX = j;
      i = (int)(paramMotionEvent.getY(i) + 0.5F);
      mLastTouchY = i;
      mInitialTouchY = i;
      break;
    case 3: 
      cancelTouch();
      break;
    case 2: 
      j = paramMotionEvent.findPointerIndex(mScrollPointerId);
      if (j < 0)
      {
        paramMotionEvent = new StringBuilder();
        paramMotionEvent.append("Error processing scroll; pointer index for id ");
        paramMotionEvent.append(mScrollPointerId);
        paramMotionEvent.append(" not found. Did any MotionEvents get skipped?");
        Log.e("RecyclerView", paramMotionEvent.toString());
        return false;
      }
      i = (int)(paramMotionEvent.getX(j) + 0.5F);
      int k = (int)(paramMotionEvent.getY(j) + 0.5F);
      if (mScrollState != 1)
      {
        j = mInitialTouchX;
        int m = mInitialTouchY;
        if ((bool2) && (Math.abs(i - j) > mTouchSlop))
        {
          mLastTouchX = i;
          i = 1;
        }
        else
        {
          i = 0;
        }
        j = i;
        if (bool3)
        {
          j = i;
          if (Math.abs(k - m) > mTouchSlop)
          {
            mLastTouchY = k;
            j = 1;
          }
        }
        if (j != 0) {
          setScrollState(1);
        }
      }
      break;
    case 1: 
      mVelocityTracker.clear();
      stopNestedScroll(0);
      break;
    case 0: 
      if (mIgnoreMotionEventTillDown) {
        mIgnoreMotionEventTillDown = false;
      }
      mScrollPointerId = paramMotionEvent.getPointerId(0);
      i = (int)(paramMotionEvent.getX() + 0.5F);
      mLastTouchX = i;
      mInitialTouchX = i;
      i = (int)(paramMotionEvent.getY() + 0.5F);
      mLastTouchY = i;
      mInitialTouchY = i;
      if (mScrollState == 2)
      {
        getParent().requestDisallowInterceptTouchEvent(true);
        setScrollState(1);
      }
      paramMotionEvent = mNestedOffsets;
      mNestedOffsets[1] = 0;
      paramMotionEvent[0] = 0;
      if (bool2) {
        i = 1;
      } else {
        i = 0;
      }
      j = i;
      if (bool3) {
        j = i | 0x2;
      }
      startNestedScroll(j, 0);
    }
    if (mScrollState == 1) {
      bool1 = true;
    }
    return bool1;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    TraceCompat.beginSection("RV OnLayout");
    dispatchLayout();
    TraceCompat.endSection();
    mFirstLayoutComplete = true;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (mLayout == null)
    {
      defaultOnMeasure(paramInt1, paramInt2);
      return;
    }
    boolean bool = mLayout.isAutoMeasureEnabled();
    int j = 0;
    if (bool)
    {
      int k = View.MeasureSpec.getMode(paramInt1);
      int m = View.MeasureSpec.getMode(paramInt2);
      mLayout.onMeasure(mRecycler, mState, paramInt1, paramInt2);
      int i = j;
      if (k == 1073741824)
      {
        i = j;
        if (m == 1073741824) {
          i = 1;
        }
      }
      if (i == 0)
      {
        if (mAdapter == null) {
          return;
        }
        if (mState.mLayoutStep == 1) {
          dispatchLayoutStep1();
        }
        mLayout.setMeasureSpecs(paramInt1, paramInt2);
        mState.mIsMeasuring = true;
        dispatchLayoutStep2();
        mLayout.setMeasuredDimensionFromChildren(paramInt1, paramInt2);
        if (!mLayout.shouldMeasureTwice()) {
          return;
        }
        mLayout.setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
        mState.mIsMeasuring = true;
        dispatchLayoutStep2();
        mLayout.setMeasuredDimensionFromChildren(paramInt1, paramInt2);
      }
    }
    else
    {
      if (mHasFixedSize)
      {
        mLayout.onMeasure(mRecycler, mState, paramInt1, paramInt2);
        return;
      }
      if (mAdapterUpdateDuringMeasure)
      {
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        processAdapterUpdatesAndSetAnimationFlags();
        onExitLayoutOrScroll();
        if (mState.mRunPredictiveAnimations)
        {
          mState.mInPreLayout = true;
        }
        else
        {
          mAdapterHelper.consumeUpdatesInOnePass();
          mState.mInPreLayout = false;
        }
        mAdapterUpdateDuringMeasure = false;
        stopInterceptRequestLayout(false);
      }
      else if (mState.mRunPredictiveAnimations)
      {
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
        return;
      }
      if (mAdapter != null) {
        mState.mItemCount = mAdapter.getItemCount();
      } else {
        mState.mItemCount = 0;
      }
      startInterceptRequestLayout();
      mLayout.onMeasure(mRecycler, mState, paramInt1, paramInt2);
      stopInterceptRequestLayout(false);
      mState.mInPreLayout = false;
    }
  }
  
  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect)
  {
    if (isComputingLayout()) {
      return false;
    }
    return super.onRequestFocusInDescendants(paramInt, paramRect);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof SavedState))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    mPendingSavedState = ((SavedState)paramParcelable);
    super.onRestoreInstanceState(mPendingSavedState.getSuperState());
    if ((mLayout != null) && (mPendingSavedState.mLayoutState != null)) {
      mLayout.onRestoreInstanceState(mPendingSavedState.mLayoutState);
    }
  }
  
  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    if (mPendingSavedState != null)
    {
      localSavedState.copyFrom(mPendingSavedState);
      return localSavedState;
    }
    if (mLayout != null)
    {
      mLayoutState = mLayout.onSaveInstanceState();
      return localSavedState;
    }
    mLayoutState = null;
    return localSavedState;
  }
  
  public void onScrollStateChanged(int paramInt) {}
  
  public void onScrolled(int paramInt1, int paramInt2) {}
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if ((paramInt1 != paramInt3) || (paramInt2 != paramInt4)) {
      invalidateGlows();
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool1 = mLayoutFrozen;
    int i2 = 0;
    if (!bool1)
    {
      if (mIgnoreMotionEventTillDown) {
        return false;
      }
      if (dispatchOnItemTouch(paramMotionEvent))
      {
        cancelTouch();
        return true;
      }
      if (mLayout == null) {
        return false;
      }
      bool1 = mLayout.canScrollHorizontally();
      boolean bool2 = mLayout.canScrollVertically();
      if (mVelocityTracker == null) {
        mVelocityTracker = VelocityTracker.obtain();
      }
      MotionEvent localMotionEvent = MotionEvent.obtain(paramMotionEvent);
      int j = paramMotionEvent.getActionMasked();
      int i = paramMotionEvent.getActionIndex();
      if (j == 0)
      {
        int[] arrayOfInt = mNestedOffsets;
        mNestedOffsets[1] = 0;
        arrayOfInt[0] = 0;
      }
      localMotionEvent.offsetLocation(mNestedOffsets[0], mNestedOffsets[1]);
      switch (j)
      {
      case 4: 
      default: 
        i = i2;
        break;
      case 6: 
        onPointerUp(paramMotionEvent);
        i = i2;
        break;
      case 5: 
        mScrollPointerId = paramMotionEvent.getPointerId(i);
        j = (int)(paramMotionEvent.getX(i) + 0.5F);
        mLastTouchX = j;
        mInitialTouchX = j;
        i = (int)(paramMotionEvent.getY(i) + 0.5F);
        mLastTouchY = i;
        mInitialTouchY = i;
        i = i2;
        break;
      case 3: 
        cancelTouch();
        i = i2;
        break;
      case 2: 
        i = paramMotionEvent.findPointerIndex(mScrollPointerId);
        if (i < 0)
        {
          paramMotionEvent = new StringBuilder();
          paramMotionEvent.append("Error processing scroll; pointer index for id ");
          paramMotionEvent.append(mScrollPointerId);
          paramMotionEvent.append(" not found. Did any MotionEvents get skipped?");
          Log.e("RecyclerView", paramMotionEvent.toString());
          return false;
        }
        int i3 = (int)(paramMotionEvent.getX(i) + 0.5F);
        int i4 = (int)(paramMotionEvent.getY(i) + 0.5F);
        int m = mLastTouchX - i3;
        int k = mLastTouchY - i4;
        j = m;
        i = k;
        if (dispatchNestedPreScroll(m, k, mScrollConsumed, mScrollOffset, 0))
        {
          j = m - mScrollConsumed[0];
          i = k - mScrollConsumed[1];
          localMotionEvent.offsetLocation(mScrollOffset[0], mScrollOffset[1]);
          paramMotionEvent = mNestedOffsets;
          paramMotionEvent[0] += mScrollOffset[0];
          paramMotionEvent = mNestedOffsets;
          paramMotionEvent[1] += mScrollOffset[1];
        }
        k = j;
        m = i;
        if (mScrollState != 1)
        {
          if ((bool1) && (Math.abs(j) > mTouchSlop))
          {
            if (j > 0) {
              j -= mTouchSlop;
            } else {
              j += mTouchSlop;
            }
            k = 1;
          }
          else
          {
            k = 0;
          }
          int i1 = k;
          int n = i;
          if (bool2)
          {
            i1 = k;
            n = i;
            if (Math.abs(i) > mTouchSlop)
            {
              if (i > 0) {
                n = i - mTouchSlop;
              } else {
                n = i + mTouchSlop;
              }
              i1 = 1;
            }
          }
          k = j;
          m = n;
          if (i1 != 0)
          {
            setScrollState(1);
            m = n;
            k = j;
          }
        }
        i = i2;
        if (mScrollState == 1)
        {
          mLastTouchX = (i3 - mScrollOffset[0]);
          mLastTouchY = (i4 - mScrollOffset[1]);
          if (bool1) {
            i = k;
          } else {
            i = 0;
          }
          if (bool2) {
            j = m;
          } else {
            j = 0;
          }
          if (scrollByInternal(i, j, localMotionEvent)) {
            getParent().requestDisallowInterceptTouchEvent(true);
          }
          i = i2;
          if (mGapWorker != null) {
            if (k == 0)
            {
              i = i2;
              if (m == 0) {
                break;
              }
            }
            else
            {
              mGapWorker.postFromTraversal(this, k, m);
              i = i2;
            }
          }
        }
        break;
      case 1: 
        mVelocityTracker.addMovement(localMotionEvent);
        mVelocityTracker.computeCurrentVelocity(1000, mMaxFlingVelocity);
        float f1;
        if (bool1) {
          f1 = -mVelocityTracker.getXVelocity(mScrollPointerId);
        } else {
          f1 = 0.0F;
        }
        float f2;
        if (bool2) {
          f2 = -mVelocityTracker.getYVelocity(mScrollPointerId);
        } else {
          f2 = 0.0F;
        }
        if (((f1 == 0.0F) && (f2 == 0.0F)) || (!fling((int)f1, (int)f2))) {
          setScrollState(0);
        }
        resetTouch();
        i = 1;
        break;
      case 0: 
        mScrollPointerId = paramMotionEvent.getPointerId(0);
        i = (int)(paramMotionEvent.getX() + 0.5F);
        mLastTouchX = i;
        mInitialTouchX = i;
        i = (int)(paramMotionEvent.getY() + 0.5F);
        mLastTouchY = i;
        mInitialTouchY = i;
        if (bool1) {
          i = 1;
        } else {
          i = 0;
        }
        j = i;
        if (bool2) {
          j = i | 0x2;
        }
        startNestedScroll(j, 0);
        i = i2;
      }
      if (i == 0) {
        mVelocityTracker.addMovement(localMotionEvent);
      }
      localMotionEvent.recycle();
      return true;
    }
    return false;
  }
  
  void postAnimationRunner()
  {
    if ((!mPostedAnimatorRunner) && (mIsAttached))
    {
      ViewCompat.postOnAnimation(this, mItemAnimatorRunner);
      mPostedAnimatorRunner = true;
    }
  }
  
  void processDataSetCompletelyChanged(boolean paramBoolean)
  {
    mDispatchItemsChangedEvent = (paramBoolean | mDispatchItemsChangedEvent);
    mDataSetHasChangedAfterLayout = true;
    markKnownViewsInvalid();
  }
  
  void recordAnimationInfoIfBouncedHiddenView(ViewHolder paramViewHolder, RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo)
  {
    paramViewHolder.setFlags(0, 8192);
    if ((mState.mTrackOldChangeHolders) && (paramViewHolder.isUpdated()) && (!paramViewHolder.isRemoved()) && (!paramViewHolder.shouldIgnore()))
    {
      long l = getChangedHolderKey(paramViewHolder);
      mViewInfoStore.addToOldChangeHolders(l, paramViewHolder);
    }
    mViewInfoStore.addToPreLayout(paramViewHolder, paramItemHolderInfo);
  }
  
  void removeAndRecycleViews()
  {
    if (mItemAnimator != null) {
      mItemAnimator.endAnimations();
    }
    if (mLayout != null)
    {
      mLayout.removeAndRecycleAllViews(mRecycler);
      mLayout.removeAndRecycleScrapInt(mRecycler);
    }
    mRecycler.clear();
  }
  
  boolean removeAnimatingView(View paramView)
  {
    startInterceptRequestLayout();
    boolean bool = mChildHelper.removeViewIfHidden(paramView);
    if (bool)
    {
      paramView = getChildViewHolderInt(paramView);
      mRecycler.unscrapView(paramView);
      mRecycler.recycleViewHolderInternal(paramView);
    }
    stopInterceptRequestLayout(bool ^ true);
    return bool;
  }
  
  protected void removeDetachedView(View paramView, boolean paramBoolean)
  {
    ViewHolder localViewHolder = getChildViewHolderInt(paramView);
    if (localViewHolder != null) {
      if (localViewHolder.isTmpDetached())
      {
        localViewHolder.clearTmpDetachFlag();
      }
      else if (!localViewHolder.shouldIgnore())
      {
        paramView = new StringBuilder();
        paramView.append("Called removeDetachedView with a view which is not flagged as tmp detached.");
        paramView.append(localViewHolder);
        paramView.append(exceptionLabel());
        throw new IllegalArgumentException(paramView.toString());
      }
    }
    paramView.clearAnimation();
    dispatchChildDetached(paramView);
    super.removeDetachedView(paramView, paramBoolean);
  }
  
  public void removeItemDecoration(ItemDecoration paramItemDecoration)
  {
    if (mLayout != null) {
      mLayout.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
    }
    mItemDecorations.remove(paramItemDecoration);
    if (mItemDecorations.isEmpty())
    {
      boolean bool;
      if (getOverScrollMode() == 2) {
        bool = true;
      } else {
        bool = false;
      }
      setWillNotDraw(bool);
    }
    markItemDecorInsetsDirty();
    requestLayout();
  }
  
  public void removeItemDecorationAt(int paramInt)
  {
    int i = getItemDecorationCount();
    if ((paramInt >= 0) && (paramInt < i))
    {
      removeItemDecoration(getItemDecorationAt(paramInt));
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" is an invalid index for size ");
    localStringBuilder.append(i);
    throw new IndexOutOfBoundsException(localStringBuilder.toString());
  }
  
  public void removeOnChildAttachStateChangeListener(OnChildAttachStateChangeListener paramOnChildAttachStateChangeListener)
  {
    if (mOnChildAttachStateListeners == null) {
      return;
    }
    mOnChildAttachStateListeners.remove(paramOnChildAttachStateChangeListener);
  }
  
  public void removeOnItemTouchListener(OnItemTouchListener paramOnItemTouchListener)
  {
    mOnItemTouchListeners.remove(paramOnItemTouchListener);
    if (mActiveOnItemTouchListener == paramOnItemTouchListener) {
      mActiveOnItemTouchListener = null;
    }
  }
  
  public void removeOnScrollListener(OnScrollListener paramOnScrollListener)
  {
    if (mScrollListeners != null) {
      mScrollListeners.remove(paramOnScrollListener);
    }
  }
  
  void repositionShadowingViews()
  {
    int j = mChildHelper.getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = mChildHelper.getChildAt(i);
      Object localObject = getChildViewHolder(localView);
      if ((localObject != null) && (mShadowingHolder != null))
      {
        localObject = mShadowingHolder.itemView;
        int k = localView.getLeft();
        int m = localView.getTop();
        if ((k != ((View)localObject).getLeft()) || (m != ((View)localObject).getTop())) {
          ((View)localObject).layout(k, m, ((View)localObject).getWidth() + k, ((View)localObject).getHeight() + m);
        }
      }
      i += 1;
    }
  }
  
  public void requestChildFocus(View paramView1, View paramView2)
  {
    if ((!mLayout.onRequestChildFocus(this, mState, paramView1, paramView2)) && (paramView2 != null)) {
      requestChildOnScreen(paramView1, paramView2);
    }
    super.requestChildFocus(paramView1, paramView2);
  }
  
  public boolean requestChildRectangleOnScreen(View paramView, Rect paramRect, boolean paramBoolean)
  {
    return mLayout.requestChildRectangleOnScreen(this, paramView, paramRect, paramBoolean);
  }
  
  public void requestDisallowInterceptTouchEvent(boolean paramBoolean)
  {
    int j = mOnItemTouchListeners.size();
    int i = 0;
    while (i < j)
    {
      ((OnItemTouchListener)mOnItemTouchListeners.get(i)).onRequestDisallowInterceptTouchEvent(paramBoolean);
      i += 1;
    }
    super.requestDisallowInterceptTouchEvent(paramBoolean);
  }
  
  public void requestLayout()
  {
    if ((mInterceptRequestLayoutDepth == 0) && (!mLayoutFrozen))
    {
      super.requestLayout();
      return;
    }
    mLayoutWasDefered = true;
  }
  
  void saveOldPositions()
  {
    int j = mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      ViewHolder localViewHolder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
      if (!localViewHolder.shouldIgnore()) {
        localViewHolder.saveOldPosition();
      }
      i += 1;
    }
  }
  
  public void scrollBy(int paramInt1, int paramInt2)
  {
    if (mLayout == null)
    {
      Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
      return;
    }
    if (mLayoutFrozen) {
      return;
    }
    boolean bool1 = mLayout.canScrollHorizontally();
    boolean bool2 = mLayout.canScrollVertically();
    if ((bool1) || (bool2))
    {
      if (!bool1) {
        paramInt1 = 0;
      }
      if (!bool2) {
        paramInt2 = 0;
      }
      scrollByInternal(paramInt1, paramInt2, null);
    }
  }
  
  boolean scrollByInternal(int paramInt1, int paramInt2, MotionEvent paramMotionEvent)
  {
    consumePendingUpdateOperations();
    Adapter localAdapter = mAdapter;
    boolean bool = false;
    int j;
    int i;
    int m;
    int k;
    int n;
    if (localAdapter != null)
    {
      startInterceptRequestLayout();
      onEnterLayoutOrScroll();
      TraceCompat.beginSection("RV Scroll");
      fillRemainingScrollValues(mState);
      if (paramInt1 != 0)
      {
        j = mLayout.scrollHorizontallyBy(paramInt1, mRecycler, mState);
        i = paramInt1 - j;
      }
      else
      {
        j = 0;
        i = j;
      }
      if (paramInt2 != 0)
      {
        m = mLayout.scrollVerticallyBy(paramInt2, mRecycler, mState);
        k = paramInt2 - m;
      }
      else
      {
        m = 0;
        k = m;
      }
      TraceCompat.endSection();
      repositionShadowingViews();
      onExitLayoutOrScroll();
      stopInterceptRequestLayout(false);
      n = k;
    }
    else
    {
      k = 0;
      i = k;
      j = i;
      n = j;
      m = j;
      j = k;
    }
    if (!mItemDecorations.isEmpty()) {
      invalidate();
    }
    if (dispatchNestedScroll(j, m, i, n, mScrollOffset, 0))
    {
      mLastTouchX -= mScrollOffset[0];
      mLastTouchY -= mScrollOffset[1];
      if (paramMotionEvent != null) {
        paramMotionEvent.offsetLocation(mScrollOffset[0], mScrollOffset[1]);
      }
      paramMotionEvent = mNestedOffsets;
      paramMotionEvent[0] += mScrollOffset[0];
      paramMotionEvent = mNestedOffsets;
      paramMotionEvent[1] += mScrollOffset[1];
    }
    else if (getOverScrollMode() != 2)
    {
      if ((paramMotionEvent != null) && (!MotionEventCompat.isFromSource(paramMotionEvent, 8194))) {
        pullGlows(paramMotionEvent.getX(), i, paramMotionEvent.getY(), n);
      }
      considerReleasingGlowsOnScroll(paramInt1, paramInt2);
    }
    if ((j != 0) || (m != 0)) {
      dispatchOnScrolled(j, m);
    }
    if (!awakenScrollBars()) {
      invalidate();
    }
    if ((j != 0) || (m != 0)) {
      bool = true;
    }
    return bool;
  }
  
  public void scrollTo(int paramInt1, int paramInt2)
  {
    Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
  }
  
  public void scrollToPosition(int paramInt)
  {
    if (mLayoutFrozen) {
      return;
    }
    stopScroll();
    if (mLayout == null)
    {
      Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
      return;
    }
    mLayout.scrollToPosition(paramInt);
    awakenScrollBars();
  }
  
  public void sendAccessibilityEventUnchecked(AccessibilityEvent paramAccessibilityEvent)
  {
    if (shouldDeferAccessibilityEvent(paramAccessibilityEvent)) {
      return;
    }
    super.sendAccessibilityEventUnchecked(paramAccessibilityEvent);
  }
  
  public void setAccessibilityDelegateCompat(RecyclerViewAccessibilityDelegate paramRecyclerViewAccessibilityDelegate)
  {
    mAccessibilityDelegate = paramRecyclerViewAccessibilityDelegate;
    ViewCompat.setAccessibilityDelegate(this, mAccessibilityDelegate);
  }
  
  public void setAdapter(Adapter paramAdapter)
  {
    setLayoutFrozen(false);
    setAdapterInternal(paramAdapter, false, true);
    processDataSetCompletelyChanged(false);
    requestLayout();
  }
  
  public void setChildDrawingOrderCallback(ChildDrawingOrderCallback paramChildDrawingOrderCallback)
  {
    if (paramChildDrawingOrderCallback == mChildDrawingOrderCallback) {
      return;
    }
    mChildDrawingOrderCallback = paramChildDrawingOrderCallback;
    boolean bool;
    if (mChildDrawingOrderCallback != null) {
      bool = true;
    } else {
      bool = false;
    }
    setChildrenDrawingOrderEnabled(bool);
  }
  
  @VisibleForTesting
  boolean setChildImportantForAccessibilityInternal(ViewHolder paramViewHolder, int paramInt)
  {
    if (isComputingLayout())
    {
      mPendingAccessibilityState = paramInt;
      mPendingAccessibilityImportanceChange.add(paramViewHolder);
      return false;
    }
    ViewCompat.setImportantForAccessibility(itemView, paramInt);
    return true;
  }
  
  public void setClipToPadding(boolean paramBoolean)
  {
    if (paramBoolean != mClipToPadding) {
      invalidateGlows();
    }
    mClipToPadding = paramBoolean;
    super.setClipToPadding(paramBoolean);
    if (mFirstLayoutComplete) {
      requestLayout();
    }
  }
  
  public void setEdgeEffectFactory(@NonNull EdgeEffectFactory paramEdgeEffectFactory)
  {
    Preconditions.checkNotNull(paramEdgeEffectFactory);
    mEdgeEffectFactory = paramEdgeEffectFactory;
    invalidateGlows();
  }
  
  public void setHasFixedSize(boolean paramBoolean)
  {
    mHasFixedSize = paramBoolean;
  }
  
  public void setItemAnimator(ItemAnimator paramItemAnimator)
  {
    if (mItemAnimator != null)
    {
      mItemAnimator.endAnimations();
      mItemAnimator.setListener(null);
    }
    mItemAnimator = paramItemAnimator;
    if (mItemAnimator != null) {
      mItemAnimator.setListener(mItemAnimatorListener);
    }
  }
  
  public void setItemViewCacheSize(int paramInt)
  {
    mRecycler.setViewCacheSize(paramInt);
  }
  
  public void setLayoutFrozen(boolean paramBoolean)
  {
    if (paramBoolean != mLayoutFrozen)
    {
      assertNotInLayoutOrScroll("Do not setLayoutFrozen in layout or scroll");
      if (!paramBoolean)
      {
        mLayoutFrozen = false;
        if ((mLayoutWasDefered) && (mLayout != null) && (mAdapter != null)) {
          requestLayout();
        }
        mLayoutWasDefered = false;
        return;
      }
      long l = SystemClock.uptimeMillis();
      onTouchEvent(MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0));
      mLayoutFrozen = true;
      mIgnoreMotionEventTillDown = true;
      stopScroll();
    }
  }
  
  public void setLayoutManager(LayoutManager paramLayoutManager)
  {
    if (paramLayoutManager == mLayout) {
      return;
    }
    stopScroll();
    if (mLayout != null)
    {
      if (mItemAnimator != null) {
        mItemAnimator.endAnimations();
      }
      mLayout.removeAndRecycleAllViews(mRecycler);
      mLayout.removeAndRecycleScrapInt(mRecycler);
      mRecycler.clear();
      if (mIsAttached) {
        mLayout.dispatchDetachedFromWindow(this, mRecycler);
      }
      mLayout.setRecyclerView(null);
      mLayout = null;
    }
    else
    {
      mRecycler.clear();
    }
    mChildHelper.removeAllViewsUnfiltered();
    mLayout = paramLayoutManager;
    if (paramLayoutManager != null)
    {
      if (mRecyclerView != null)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("LayoutManager ");
        localStringBuilder.append(paramLayoutManager);
        localStringBuilder.append(" is already attached to a RecyclerView:");
        localStringBuilder.append(mRecyclerView.exceptionLabel());
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      mLayout.setRecyclerView(this);
      if (mIsAttached) {
        mLayout.dispatchAttachedToWindow(this);
      }
    }
    mRecycler.updateViewCacheSize();
    requestLayout();
  }
  
  public void setNestedScrollingEnabled(boolean paramBoolean)
  {
    getScrollingChildHelper().setNestedScrollingEnabled(paramBoolean);
  }
  
  public void setOnFlingListener(@Nullable OnFlingListener paramOnFlingListener)
  {
    mOnFlingListener = paramOnFlingListener;
  }
  
  @Deprecated
  public void setOnScrollListener(OnScrollListener paramOnScrollListener)
  {
    mScrollListener = paramOnScrollListener;
  }
  
  public void setPreserveFocusAfterLayout(boolean paramBoolean)
  {
    mPreserveFocusAfterLayout = paramBoolean;
  }
  
  public void setRecycledViewPool(RecycledViewPool paramRecycledViewPool)
  {
    mRecycler.setRecycledViewPool(paramRecycledViewPool);
  }
  
  public void setRecyclerListener(RecyclerListener paramRecyclerListener)
  {
    mRecyclerListener = paramRecyclerListener;
  }
  
  void setScrollState(int paramInt)
  {
    if (paramInt == mScrollState) {
      return;
    }
    mScrollState = paramInt;
    if (paramInt != 2) {
      stopScrollersInternal();
    }
    dispatchOnScrollStateChanged(paramInt);
  }
  
  public void setScrollingTouchSlop(int paramInt)
  {
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(getContext());
    switch (paramInt)
    {
    default: 
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("setScrollingTouchSlop(): bad argument constant ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append("; using default value");
      Log.w("RecyclerView", localStringBuilder.toString());
      break;
    case 1: 
      mTouchSlop = localViewConfiguration.getScaledPagingTouchSlop();
      return;
    }
    mTouchSlop = localViewConfiguration.getScaledTouchSlop();
  }
  
  public void setViewCacheExtension(ViewCacheExtension paramViewCacheExtension)
  {
    mRecycler.setViewCacheExtension(paramViewCacheExtension);
  }
  
  boolean shouldDeferAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if (isComputingLayout())
    {
      int i;
      if (paramAccessibilityEvent != null) {
        i = AccessibilityEventCompat.getContentChangeTypes(paramAccessibilityEvent);
      } else {
        i = 0;
      }
      int j = i;
      if (i == 0) {
        j = 0;
      }
      mEatenAccessibilityChangeFlags = (j | mEatenAccessibilityChangeFlags);
      return true;
    }
    return false;
  }
  
  public void smoothScrollBy(int paramInt1, int paramInt2)
  {
    smoothScrollBy(paramInt1, paramInt2, null);
  }
  
  public void smoothScrollBy(int paramInt1, int paramInt2, Interpolator paramInterpolator)
  {
    if (mLayout == null)
    {
      Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
      return;
    }
    if (mLayoutFrozen) {
      return;
    }
    if (!mLayout.canScrollHorizontally()) {
      paramInt1 = 0;
    }
    if (!mLayout.canScrollVertically()) {
      paramInt2 = 0;
    }
    if ((paramInt1 != 0) || (paramInt2 != 0)) {
      mViewFlinger.smoothScrollBy(paramInt1, paramInt2, paramInterpolator);
    }
  }
  
  public void smoothScrollToPosition(int paramInt)
  {
    if (mLayoutFrozen) {
      return;
    }
    if (mLayout == null)
    {
      Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
      return;
    }
    mLayout.smoothScrollToPosition(this, mState, paramInt);
  }
  
  void startInterceptRequestLayout()
  {
    mInterceptRequestLayoutDepth += 1;
    if ((mInterceptRequestLayoutDepth == 1) && (!mLayoutFrozen)) {
      mLayoutWasDefered = false;
    }
  }
  
  public boolean startNestedScroll(int paramInt)
  {
    return getScrollingChildHelper().startNestedScroll(paramInt);
  }
  
  public boolean startNestedScroll(int paramInt1, int paramInt2)
  {
    return getScrollingChildHelper().startNestedScroll(paramInt1, paramInt2);
  }
  
  void stopInterceptRequestLayout(boolean paramBoolean)
  {
    if (mInterceptRequestLayoutDepth < 1) {
      mInterceptRequestLayoutDepth = 1;
    }
    if ((!paramBoolean) && (!mLayoutFrozen)) {
      mLayoutWasDefered = false;
    }
    if (mInterceptRequestLayoutDepth == 1)
    {
      if ((paramBoolean) && (mLayoutWasDefered) && (!mLayoutFrozen) && (mLayout != null) && (mAdapter != null)) {
        dispatchLayout();
      }
      if (!mLayoutFrozen) {
        mLayoutWasDefered = false;
      }
    }
    mInterceptRequestLayoutDepth -= 1;
  }
  
  public void stopNestedScroll()
  {
    getScrollingChildHelper().stopNestedScroll();
  }
  
  public void stopNestedScroll(int paramInt)
  {
    getScrollingChildHelper().stopNestedScroll(paramInt);
  }
  
  public void stopScroll()
  {
    setScrollState(0);
    stopScrollersInternal();
  }
  
  public void swapAdapter(Adapter paramAdapter, boolean paramBoolean)
  {
    setLayoutFrozen(false);
    setAdapterInternal(paramAdapter, true, paramBoolean);
    processDataSetCompletelyChanged(true);
    requestLayout();
  }
  
  void viewRangeUpdate(int paramInt1, int paramInt2, Object paramObject)
  {
    int j = mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = mChildHelper.getUnfilteredChildAt(i);
      ViewHolder localViewHolder = getChildViewHolderInt(localView);
      if ((localViewHolder != null) && (!localViewHolder.shouldIgnore()) && (mPosition >= paramInt1) && (mPosition < paramInt1 + paramInt2))
      {
        localViewHolder.addFlags(2);
        localViewHolder.addChangePayload(paramObject);
        getLayoutParamsmInsetsDirty = true;
      }
      i += 1;
    }
    mRecycler.viewRangeUpdate(paramInt1, paramInt2);
  }
  
  public static abstract class Adapter<VH extends RecyclerView.ViewHolder>
  {
    private boolean mHasStableIds = false;
    private final RecyclerView.AdapterDataObservable mObservable = new RecyclerView.AdapterDataObservable();
    
    public Adapter() {}
    
    public final void bindViewHolder(@NonNull VH paramVH, int paramInt)
    {
      mPosition = paramInt;
      if (hasStableIds()) {
        mItemId = getItemId(paramInt);
      }
      paramVH.setFlags(1, 519);
      TraceCompat.beginSection("RV OnBindView");
      onBindViewHolder(paramVH, paramInt, paramVH.getUnmodifiedPayloads());
      paramVH.clearPayload();
      paramVH = itemView.getLayoutParams();
      if ((paramVH instanceof RecyclerView.LayoutParams)) {
        mInsetsDirty = true;
      }
      TraceCompat.endSection();
    }
    
    public final VH createViewHolder(@NonNull ViewGroup paramViewGroup, int paramInt)
    {
      try
      {
        TraceCompat.beginSection("RV CreateView");
        paramViewGroup = onCreateViewHolder(paramViewGroup, paramInt);
        if (itemView.getParent() != null) {
          throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
        }
        mItemViewType = paramInt;
        return paramViewGroup;
      }
      finally
      {
        TraceCompat.endSection();
      }
    }
    
    public abstract int getItemCount();
    
    public long getItemId(int paramInt)
    {
      return -1L;
    }
    
    public int getItemViewType(int paramInt)
    {
      return 0;
    }
    
    public final boolean hasObservers()
    {
      return mObservable.hasObservers();
    }
    
    public final boolean hasStableIds()
    {
      return mHasStableIds;
    }
    
    public final void notifyDataSetChanged()
    {
      mObservable.notifyChanged();
    }
    
    public final void notifyItemChanged(int paramInt)
    {
      mObservable.notifyItemRangeChanged(paramInt, 1);
    }
    
    public final void notifyItemChanged(int paramInt, @Nullable Object paramObject)
    {
      mObservable.notifyItemRangeChanged(paramInt, 1, paramObject);
    }
    
    public final void notifyItemInserted(int paramInt)
    {
      mObservable.notifyItemRangeInserted(paramInt, 1);
    }
    
    public final void notifyItemMoved(int paramInt1, int paramInt2)
    {
      mObservable.notifyItemMoved(paramInt1, paramInt2);
    }
    
    public final void notifyItemRangeChanged(int paramInt1, int paramInt2)
    {
      mObservable.notifyItemRangeChanged(paramInt1, paramInt2);
    }
    
    public final void notifyItemRangeChanged(int paramInt1, int paramInt2, @Nullable Object paramObject)
    {
      mObservable.notifyItemRangeChanged(paramInt1, paramInt2, paramObject);
    }
    
    public final void notifyItemRangeInserted(int paramInt1, int paramInt2)
    {
      mObservable.notifyItemRangeInserted(paramInt1, paramInt2);
    }
    
    public final void notifyItemRangeRemoved(int paramInt1, int paramInt2)
    {
      mObservable.notifyItemRangeRemoved(paramInt1, paramInt2);
    }
    
    public final void notifyItemRemoved(int paramInt)
    {
      mObservable.notifyItemRangeRemoved(paramInt, 1);
    }
    
    public void onAttachedToRecyclerView(@NonNull RecyclerView paramRecyclerView) {}
    
    public abstract void onBindViewHolder(@NonNull VH paramVH, int paramInt);
    
    public void onBindViewHolder(@NonNull VH paramVH, int paramInt, @NonNull List<Object> paramList)
    {
      onBindViewHolder(paramVH, paramInt);
    }
    
    @NonNull
    public abstract VH onCreateViewHolder(@NonNull ViewGroup paramViewGroup, int paramInt);
    
    public void onDetachedFromRecyclerView(@NonNull RecyclerView paramRecyclerView) {}
    
    public boolean onFailedToRecycleView(@NonNull VH paramVH)
    {
      return false;
    }
    
    public void onViewAttachedToWindow(@NonNull VH paramVH) {}
    
    public void onViewDetachedFromWindow(@NonNull VH paramVH) {}
    
    public void onViewRecycled(@NonNull VH paramVH) {}
    
    public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver paramAdapterDataObserver)
    {
      mObservable.registerObserver(paramAdapterDataObserver);
    }
    
    public void setHasStableIds(boolean paramBoolean)
    {
      if (hasObservers()) {
        throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
      }
      mHasStableIds = paramBoolean;
    }
    
    public void unregisterAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver paramAdapterDataObserver)
    {
      mObservable.unregisterObserver(paramAdapterDataObserver);
    }
  }
  
  static class AdapterDataObservable
    extends Observable<RecyclerView.AdapterDataObserver>
  {
    AdapterDataObservable() {}
    
    public boolean hasObservers()
    {
      return mObservers.isEmpty() ^ true;
    }
    
    public void notifyChanged()
    {
      int i = mObservers.size() - 1;
      while (i >= 0)
      {
        ((RecyclerView.AdapterDataObserver)mObservers.get(i)).onChanged();
        i -= 1;
      }
    }
    
    public void notifyItemMoved(int paramInt1, int paramInt2)
    {
      int i = mObservers.size() - 1;
      while (i >= 0)
      {
        ((RecyclerView.AdapterDataObserver)mObservers.get(i)).onItemRangeMoved(paramInt1, paramInt2, 1);
        i -= 1;
      }
    }
    
    public void notifyItemRangeChanged(int paramInt1, int paramInt2)
    {
      notifyItemRangeChanged(paramInt1, paramInt2, null);
    }
    
    public void notifyItemRangeChanged(int paramInt1, int paramInt2, @Nullable Object paramObject)
    {
      int i = mObservers.size() - 1;
      while (i >= 0)
      {
        ((RecyclerView.AdapterDataObserver)mObservers.get(i)).onItemRangeChanged(paramInt1, paramInt2, paramObject);
        i -= 1;
      }
    }
    
    public void notifyItemRangeInserted(int paramInt1, int paramInt2)
    {
      int i = mObservers.size() - 1;
      while (i >= 0)
      {
        ((RecyclerView.AdapterDataObserver)mObservers.get(i)).onItemRangeInserted(paramInt1, paramInt2);
        i -= 1;
      }
    }
    
    public void notifyItemRangeRemoved(int paramInt1, int paramInt2)
    {
      int i = mObservers.size() - 1;
      while (i >= 0)
      {
        ((RecyclerView.AdapterDataObserver)mObservers.get(i)).onItemRangeRemoved(paramInt1, paramInt2);
        i -= 1;
      }
    }
  }
  
  public static abstract class AdapterDataObserver
  {
    public AdapterDataObserver() {}
    
    public void onChanged() {}
    
    public void onItemRangeChanged(int paramInt1, int paramInt2) {}
    
    public void onItemRangeChanged(int paramInt1, int paramInt2, @Nullable Object paramObject)
    {
      onItemRangeChanged(paramInt1, paramInt2);
    }
    
    public void onItemRangeInserted(int paramInt1, int paramInt2) {}
    
    public void onItemRangeMoved(int paramInt1, int paramInt2, int paramInt3) {}
    
    public void onItemRangeRemoved(int paramInt1, int paramInt2) {}
  }
  
  public static abstract interface ChildDrawingOrderCallback
  {
    public abstract int onGetChildDrawingOrder(int paramInt1, int paramInt2);
  }
  
  public static class EdgeEffectFactory
  {
    public static final int DIRECTION_BOTTOM = 3;
    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_TOP = 1;
    
    public EdgeEffectFactory() {}
    
    @NonNull
    protected EdgeEffect createEdgeEffect(RecyclerView paramRecyclerView, int paramInt)
    {
      return new EdgeEffect(paramRecyclerView.getContext());
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface EdgeDirection {}
  }
  
  public static abstract class ItemAnimator
  {
    public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
    public static final int FLAG_CHANGED = 2;
    public static final int FLAG_INVALIDATED = 4;
    public static final int FLAG_MOVED = 2048;
    public static final int FLAG_REMOVED = 8;
    private long mAddDuration = 120L;
    private long mChangeDuration = 250L;
    private ArrayList<ItemAnimatorFinishedListener> mFinishedListeners = new ArrayList();
    private ItemAnimatorListener mListener = null;
    private long mMoveDuration = 250L;
    private long mRemoveDuration = 120L;
    
    public ItemAnimator() {}
    
    static int buildAdapterChangeFlagsForAnimations(RecyclerView.ViewHolder paramViewHolder)
    {
      int j = RecyclerView.ViewHolder.access$1600(paramViewHolder) & 0xE;
      if (paramViewHolder.isInvalid()) {
        return 4;
      }
      int i = j;
      if ((j & 0x4) == 0)
      {
        int k = paramViewHolder.getOldPosition();
        int m = paramViewHolder.getAdapterPosition();
        i = j;
        if (k != -1)
        {
          i = j;
          if (m != -1)
          {
            i = j;
            if (k != m) {
              i = j | 0x800;
            }
          }
        }
      }
      return i;
    }
    
    public abstract boolean animateAppearance(@NonNull RecyclerView.ViewHolder paramViewHolder, @Nullable ItemHolderInfo paramItemHolderInfo1, @NonNull ItemHolderInfo paramItemHolderInfo2);
    
    public abstract boolean animateChange(@NonNull RecyclerView.ViewHolder paramViewHolder1, @NonNull RecyclerView.ViewHolder paramViewHolder2, @NonNull ItemHolderInfo paramItemHolderInfo1, @NonNull ItemHolderInfo paramItemHolderInfo2);
    
    public abstract boolean animateDisappearance(@NonNull RecyclerView.ViewHolder paramViewHolder, @NonNull ItemHolderInfo paramItemHolderInfo1, @Nullable ItemHolderInfo paramItemHolderInfo2);
    
    public abstract boolean animatePersistence(@NonNull RecyclerView.ViewHolder paramViewHolder, @NonNull ItemHolderInfo paramItemHolderInfo1, @NonNull ItemHolderInfo paramItemHolderInfo2);
    
    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder paramViewHolder)
    {
      return true;
    }
    
    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder paramViewHolder, @NonNull List<Object> paramList)
    {
      return canReuseUpdatedViewHolder(paramViewHolder);
    }
    
    public final void dispatchAnimationFinished(RecyclerView.ViewHolder paramViewHolder)
    {
      onAnimationFinished(paramViewHolder);
      if (mListener != null) {
        mListener.onAnimationFinished(paramViewHolder);
      }
    }
    
    public final void dispatchAnimationStarted(RecyclerView.ViewHolder paramViewHolder)
    {
      onAnimationStarted(paramViewHolder);
    }
    
    public final void dispatchAnimationsFinished()
    {
      int j = mFinishedListeners.size();
      int i = 0;
      while (i < j)
      {
        ((ItemAnimatorFinishedListener)mFinishedListeners.get(i)).onAnimationsFinished();
        i += 1;
      }
      mFinishedListeners.clear();
    }
    
    public abstract void endAnimation(RecyclerView.ViewHolder paramViewHolder);
    
    public abstract void endAnimations();
    
    public long getAddDuration()
    {
      return mAddDuration;
    }
    
    public long getChangeDuration()
    {
      return mChangeDuration;
    }
    
    public long getMoveDuration()
    {
      return mMoveDuration;
    }
    
    public long getRemoveDuration()
    {
      return mRemoveDuration;
    }
    
    public abstract boolean isRunning();
    
    public final boolean isRunning(ItemAnimatorFinishedListener paramItemAnimatorFinishedListener)
    {
      boolean bool = isRunning();
      if (paramItemAnimatorFinishedListener != null)
      {
        if (!bool)
        {
          paramItemAnimatorFinishedListener.onAnimationsFinished();
          return bool;
        }
        mFinishedListeners.add(paramItemAnimatorFinishedListener);
      }
      return bool;
    }
    
    public ItemHolderInfo obtainHolderInfo()
    {
      return new ItemHolderInfo();
    }
    
    public void onAnimationFinished(RecyclerView.ViewHolder paramViewHolder) {}
    
    public void onAnimationStarted(RecyclerView.ViewHolder paramViewHolder) {}
    
    @NonNull
    public ItemHolderInfo recordPostLayoutInformation(@NonNull RecyclerView.State paramState, @NonNull RecyclerView.ViewHolder paramViewHolder)
    {
      return obtainHolderInfo().setFrom(paramViewHolder);
    }
    
    @NonNull
    public ItemHolderInfo recordPreLayoutInformation(@NonNull RecyclerView.State paramState, @NonNull RecyclerView.ViewHolder paramViewHolder, int paramInt, @NonNull List<Object> paramList)
    {
      return obtainHolderInfo().setFrom(paramViewHolder);
    }
    
    public abstract void runPendingAnimations();
    
    public void setAddDuration(long paramLong)
    {
      mAddDuration = paramLong;
    }
    
    public void setChangeDuration(long paramLong)
    {
      mChangeDuration = paramLong;
    }
    
    void setListener(ItemAnimatorListener paramItemAnimatorListener)
    {
      mListener = paramItemAnimatorListener;
    }
    
    public void setMoveDuration(long paramLong)
    {
      mMoveDuration = paramLong;
    }
    
    public void setRemoveDuration(long paramLong)
    {
      mRemoveDuration = paramLong;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface AdapterChanges {}
    
    public static abstract interface ItemAnimatorFinishedListener
    {
      public abstract void onAnimationsFinished();
    }
    
    static abstract interface ItemAnimatorListener
    {
      public abstract void onAnimationFinished(RecyclerView.ViewHolder paramViewHolder);
    }
    
    public static class ItemHolderInfo
    {
      public int bottom;
      public int changeFlags;
      public int left;
      public int right;
      public int top;
      
      public ItemHolderInfo() {}
      
      public ItemHolderInfo setFrom(RecyclerView.ViewHolder paramViewHolder)
      {
        return setFrom(paramViewHolder, 0);
      }
      
      public ItemHolderInfo setFrom(RecyclerView.ViewHolder paramViewHolder, int paramInt)
      {
        paramViewHolder = itemView;
        left = paramViewHolder.getLeft();
        top = paramViewHolder.getTop();
        right = paramViewHolder.getRight();
        bottom = paramViewHolder.getBottom();
        return this;
      }
    }
  }
  
  private class ItemAnimatorRestoreListener
    implements RecyclerView.ItemAnimator.ItemAnimatorListener
  {
    ItemAnimatorRestoreListener() {}
    
    public void onAnimationFinished(RecyclerView.ViewHolder paramViewHolder)
    {
      paramViewHolder.setIsRecyclable(true);
      if ((mShadowedHolder != null) && (mShadowingHolder == null)) {
        mShadowedHolder = null;
      }
      mShadowingHolder = null;
      if ((!RecyclerView.ViewHolder.access$1500(paramViewHolder)) && (!removeAnimatingView(itemView)) && (paramViewHolder.isTmpDetached())) {
        removeDetachedView(itemView, false);
      }
    }
  }
  
  public static abstract class ItemDecoration
  {
    public ItemDecoration() {}
    
    @Deprecated
    public void getItemOffsets(Rect paramRect, int paramInt, RecyclerView paramRecyclerView)
    {
      paramRect.set(0, 0, 0, 0);
    }
    
    public void getItemOffsets(Rect paramRect, View paramView, RecyclerView paramRecyclerView, RecyclerView.State paramState)
    {
      getItemOffsets(paramRect, ((RecyclerView.LayoutParams)paramView.getLayoutParams()).getViewLayoutPosition(), paramRecyclerView);
    }
    
    @Deprecated
    public void onDraw(Canvas paramCanvas, RecyclerView paramRecyclerView) {}
    
    public void onDraw(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.State paramState)
    {
      onDraw(paramCanvas, paramRecyclerView);
    }
    
    @Deprecated
    public void onDrawOver(Canvas paramCanvas, RecyclerView paramRecyclerView) {}
    
    public void onDrawOver(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.State paramState)
    {
      onDrawOver(paramCanvas, paramRecyclerView);
    }
  }
  
  public static abstract class LayoutManager
  {
    boolean mAutoMeasure = false;
    ChildHelper mChildHelper;
    private int mHeight;
    private int mHeightMode;
    ViewBoundsCheck mHorizontalBoundCheck = new ViewBoundsCheck(mHorizontalBoundCheckCallback);
    private final ViewBoundsCheck.Callback mHorizontalBoundCheckCallback = new ViewBoundsCheck.Callback()
    {
      public View getChildAt(int paramAnonymousInt)
      {
        return RecyclerView.LayoutManager.this.getChildAt(paramAnonymousInt);
      }
      
      public int getChildCount()
      {
        return RecyclerView.LayoutManager.this.getChildCount();
      }
      
      public int getChildEnd(View paramAnonymousView)
      {
        RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramAnonymousView.getLayoutParams();
        return getDecoratedRight(paramAnonymousView) + rightMargin;
      }
      
      public int getChildStart(View paramAnonymousView)
      {
        RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramAnonymousView.getLayoutParams();
        return getDecoratedLeft(paramAnonymousView) - leftMargin;
      }
      
      public View getParent()
      {
        return mRecyclerView;
      }
      
      public int getParentEnd()
      {
        return getWidth() - getPaddingRight();
      }
      
      public int getParentStart()
      {
        return getPaddingLeft();
      }
    };
    boolean mIsAttachedToWindow = false;
    private boolean mItemPrefetchEnabled = true;
    private boolean mMeasurementCacheEnabled = true;
    int mPrefetchMaxCountObserved;
    boolean mPrefetchMaxObservedInInitialPrefetch;
    RecyclerView mRecyclerView;
    boolean mRequestedSimpleAnimations = false;
    @Nullable
    RecyclerView.SmoothScroller mSmoothScroller;
    ViewBoundsCheck mVerticalBoundCheck = new ViewBoundsCheck(mVerticalBoundCheckCallback);
    private final ViewBoundsCheck.Callback mVerticalBoundCheckCallback = new ViewBoundsCheck.Callback()
    {
      public View getChildAt(int paramAnonymousInt)
      {
        return RecyclerView.LayoutManager.this.getChildAt(paramAnonymousInt);
      }
      
      public int getChildCount()
      {
        return RecyclerView.LayoutManager.this.getChildCount();
      }
      
      public int getChildEnd(View paramAnonymousView)
      {
        RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramAnonymousView.getLayoutParams();
        return getDecoratedBottom(paramAnonymousView) + bottomMargin;
      }
      
      public int getChildStart(View paramAnonymousView)
      {
        RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramAnonymousView.getLayoutParams();
        return getDecoratedTop(paramAnonymousView) - topMargin;
      }
      
      public View getParent()
      {
        return mRecyclerView;
      }
      
      public int getParentEnd()
      {
        return getHeight() - getPaddingBottom();
      }
      
      public int getParentStart()
      {
        return getPaddingTop();
      }
    };
    private int mWidth;
    private int mWidthMode;
    
    public LayoutManager() {}
    
    private void addViewInt(View paramView, int paramInt, boolean paramBoolean)
    {
      Object localObject = RecyclerView.getChildViewHolderInt(paramView);
      if ((!paramBoolean) && (!((RecyclerView.ViewHolder)localObject).isRemoved())) {
        mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout((RecyclerView.ViewHolder)localObject);
      } else {
        mRecyclerView.mViewInfoStore.addToDisappearedInLayout((RecyclerView.ViewHolder)localObject);
      }
      RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
      if ((!((RecyclerView.ViewHolder)localObject).wasReturnedFromScrap()) && (!((RecyclerView.ViewHolder)localObject).isScrap()))
      {
        if (paramView.getParent() == mRecyclerView)
        {
          int j = mChildHelper.indexOfChild(paramView);
          int i = paramInt;
          if (paramInt == -1) {
            i = mChildHelper.getChildCount();
          }
          if (j == -1)
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:");
            ((StringBuilder)localObject).append(mRecyclerView.indexOfChild(paramView));
            ((StringBuilder)localObject).append(mRecyclerView.exceptionLabel());
            throw new IllegalStateException(((StringBuilder)localObject).toString());
          }
          if (j != i) {
            mRecyclerView.mLayout.moveView(j, i);
          }
        }
        else
        {
          mChildHelper.addView(paramView, paramInt, false);
          mInsetsDirty = true;
          if ((mSmoothScroller != null) && (mSmoothScroller.isRunning())) {
            mSmoothScroller.onChildAttachedToWindow(paramView);
          }
        }
      }
      else
      {
        if (((RecyclerView.ViewHolder)localObject).isScrap()) {
          ((RecyclerView.ViewHolder)localObject).unScrap();
        } else {
          ((RecyclerView.ViewHolder)localObject).clearReturnedFromScrapFlag();
        }
        mChildHelper.attachViewToParent(paramView, paramInt, paramView.getLayoutParams(), false);
      }
      if (mPendingInvalidate)
      {
        itemView.invalidate();
        mPendingInvalidate = false;
      }
    }
    
    public static int chooseSize(int paramInt1, int paramInt2, int paramInt3)
    {
      int i = View.MeasureSpec.getMode(paramInt1);
      paramInt1 = View.MeasureSpec.getSize(paramInt1);
      if (i != Integer.MIN_VALUE)
      {
        if (i != 1073741824) {
          return Math.max(paramInt2, paramInt3);
        }
        return paramInt1;
      }
      return Math.min(paramInt1, Math.max(paramInt2, paramInt3));
    }
    
    private void detachViewInternal(int paramInt, View paramView)
    {
      mChildHelper.detachViewFromParent(paramInt);
    }
    
    public static int getChildMeasureSpec(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
    {
      int j = 0;
      int i = Math.max(0, paramInt1 - paramInt3);
      if (paramBoolean) {
        if (paramInt4 < 0) {}
      }
      while (paramInt4 >= 0)
      {
        paramInt3 = paramInt4;
        paramInt1 = 1073741824;
        break label125;
        if (paramInt4 != -1) {
          break;
        }
        if ((paramInt2 != Integer.MIN_VALUE) && ((paramInt2 == 0) || (paramInt2 != 1073741824)))
        {
          paramInt2 = 0;
          paramInt1 = paramInt2;
        }
        else
        {
          paramInt1 = i;
        }
        paramInt3 = paramInt1;
        paramInt1 = paramInt2;
        break label125;
      }
      if (paramInt4 == -1)
      {
        paramInt1 = paramInt2;
        paramInt3 = i;
      }
      else if (paramInt4 == -2)
      {
        if (paramInt2 != Integer.MIN_VALUE)
        {
          paramInt3 = i;
          paramInt1 = j;
          if (paramInt2 != 1073741824) {}
        }
        else
        {
          paramInt1 = Integer.MIN_VALUE;
          paramInt3 = i;
        }
      }
      else
      {
        paramInt3 = 0;
        paramInt1 = j;
      }
      label125:
      return View.MeasureSpec.makeMeasureSpec(paramInt3, paramInt1);
    }
    
    @Deprecated
    public static int getChildMeasureSpec(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    {
      int i = 0;
      paramInt1 = Math.max(0, paramInt1 - paramInt2);
      if (paramBoolean) {
        if (paramInt3 >= 0)
        {
          paramInt1 = paramInt3;
          label21:
          paramInt2 = 1073741824;
          break label59;
        }
      }
      do
      {
        paramInt1 = 0;
        paramInt2 = i;
        break label59;
        if (paramInt3 >= 0) {
          break;
        }
        if (paramInt3 == -1) {
          break label21;
        }
      } while (paramInt3 != -2);
      paramInt2 = Integer.MIN_VALUE;
      label59:
      return View.MeasureSpec.makeMeasureSpec(paramInt1, paramInt2);
    }
    
    private int[] getChildRectangleOnScreenScrollAmount(RecyclerView paramRecyclerView, View paramView, Rect paramRect, boolean paramBoolean)
    {
      int i = getPaddingLeft();
      int j = getPaddingTop();
      int k = getWidth();
      int i5 = getPaddingRight();
      int n = getHeight();
      int i1 = getPaddingBottom();
      int i6 = paramView.getLeft() + left - paramView.getScrollX();
      int i2 = paramView.getTop() + top - paramView.getScrollY();
      int i7 = paramRect.width();
      int i3 = paramRect.height();
      int i4 = i6 - i;
      i = Math.min(0, i4);
      int m = i2 - j;
      j = Math.min(0, m);
      i5 = i7 + i6 - (k - i5);
      k = Math.max(0, i5);
      n = Math.max(0, i3 + i2 - (n - i1));
      if (getLayoutDirection() == 1)
      {
        if (k != 0) {
          i = k;
        } else {
          i = Math.max(i, i5);
        }
      }
      else if (i == 0) {
        for (;;)
        {
          i = Math.min(i4, k);
        }
      }
      if (j == 0) {
        j = Math.min(m, n);
      }
      return new int[] { i, j };
    }
    
    public static Properties getProperties(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
    {
      Properties localProperties = new Properties();
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RecyclerView, paramInt1, paramInt2);
      orientation = paramContext.getInt(R.styleable.RecyclerView_android_orientation, 1);
      spanCount = paramContext.getInt(R.styleable.RecyclerView_spanCount, 1);
      reverseLayout = paramContext.getBoolean(R.styleable.RecyclerView_reverseLayout, false);
      stackFromEnd = paramContext.getBoolean(R.styleable.RecyclerView_stackFromEnd, false);
      paramContext.recycle();
      return localProperties;
    }
    
    private boolean isFocusedChildVisibleAfterScrolling(RecyclerView paramRecyclerView, int paramInt1, int paramInt2)
    {
      paramRecyclerView = paramRecyclerView.getFocusedChild();
      if (paramRecyclerView == null) {
        return false;
      }
      int i = getPaddingLeft();
      int j = getPaddingTop();
      int k = getWidth();
      int m = getPaddingRight();
      int n = getHeight();
      int i1 = getPaddingBottom();
      Rect localRect = mRecyclerView.mTempRect;
      getDecoratedBoundsWithMargins(paramRecyclerView, localRect);
      if ((left - paramInt1 < k - m) && (right - paramInt1 > i) && (top - paramInt2 < n - i1)) {
        return bottom - paramInt2 > j;
      }
      return false;
    }
    
    private static boolean isMeasurementUpToDate(int paramInt1, int paramInt2, int paramInt3)
    {
      int i = View.MeasureSpec.getMode(paramInt2);
      paramInt2 = View.MeasureSpec.getSize(paramInt2);
      boolean bool2 = false;
      boolean bool1 = false;
      if ((paramInt3 > 0) && (paramInt1 != paramInt3)) {
        return false;
      }
      if (i != Integer.MIN_VALUE)
      {
        if (i != 0)
        {
          if (i != 1073741824) {
            return false;
          }
          if (paramInt2 == paramInt1) {
            bool1 = true;
          }
          return bool1;
        }
        return true;
      }
      bool1 = bool2;
      if (paramInt2 >= paramInt1) {
        bool1 = true;
      }
      return bool1;
    }
    
    private void onSmoothScrollerStopped(RecyclerView.SmoothScroller paramSmoothScroller)
    {
      if (mSmoothScroller == paramSmoothScroller) {
        mSmoothScroller = null;
      }
    }
    
    private void scrapOrRecycleView(RecyclerView.Recycler paramRecycler, int paramInt, View paramView)
    {
      RecyclerView.ViewHolder localViewHolder = RecyclerView.getChildViewHolderInt(paramView);
      if (localViewHolder.shouldIgnore()) {
        return;
      }
      if ((localViewHolder.isInvalid()) && (!localViewHolder.isRemoved()) && (!mRecyclerView.mAdapter.hasStableIds()))
      {
        removeViewAt(paramInt);
        paramRecycler.recycleViewHolderInternal(localViewHolder);
        return;
      }
      detachViewAt(paramInt);
      paramRecycler.scrapView(paramView);
      mRecyclerView.mViewInfoStore.onViewDetached(localViewHolder);
    }
    
    public void addDisappearingView(View paramView)
    {
      addDisappearingView(paramView, -1);
    }
    
    public void addDisappearingView(View paramView, int paramInt)
    {
      addViewInt(paramView, paramInt, true);
    }
    
    public void addView(View paramView)
    {
      addView(paramView, -1);
    }
    
    public void addView(View paramView, int paramInt)
    {
      addViewInt(paramView, paramInt, false);
    }
    
    public void assertInLayoutOrScroll(String paramString)
    {
      if (mRecyclerView != null) {
        mRecyclerView.assertInLayoutOrScroll(paramString);
      }
    }
    
    public void assertNotInLayoutOrScroll(String paramString)
    {
      if (mRecyclerView != null) {
        mRecyclerView.assertNotInLayoutOrScroll(paramString);
      }
    }
    
    public void attachView(View paramView)
    {
      attachView(paramView, -1);
    }
    
    public void attachView(View paramView, int paramInt)
    {
      attachView(paramView, paramInt, (RecyclerView.LayoutParams)paramView.getLayoutParams());
    }
    
    public void attachView(View paramView, int paramInt, RecyclerView.LayoutParams paramLayoutParams)
    {
      RecyclerView.ViewHolder localViewHolder = RecyclerView.getChildViewHolderInt(paramView);
      if (localViewHolder.isRemoved()) {
        mRecyclerView.mViewInfoStore.addToDisappearedInLayout(localViewHolder);
      } else {
        mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(localViewHolder);
      }
      mChildHelper.attachViewToParent(paramView, paramInt, paramLayoutParams, localViewHolder.isRemoved());
    }
    
    public void calculateItemDecorationsForChild(View paramView, Rect paramRect)
    {
      if (mRecyclerView == null)
      {
        paramRect.set(0, 0, 0, 0);
        return;
      }
      paramRect.set(mRecyclerView.getItemDecorInsetsForChild(paramView));
    }
    
    public boolean canScrollHorizontally()
    {
      return false;
    }
    
    public boolean canScrollVertically()
    {
      return false;
    }
    
    public boolean checkLayoutParams(RecyclerView.LayoutParams paramLayoutParams)
    {
      return paramLayoutParams != null;
    }
    
    public void collectAdjacentPrefetchPositions(int paramInt1, int paramInt2, RecyclerView.State paramState, LayoutPrefetchRegistry paramLayoutPrefetchRegistry) {}
    
    public void collectInitialPrefetchPositions(int paramInt, LayoutPrefetchRegistry paramLayoutPrefetchRegistry) {}
    
    public int computeHorizontalScrollExtent(RecyclerView.State paramState)
    {
      return 0;
    }
    
    public int computeHorizontalScrollOffset(RecyclerView.State paramState)
    {
      return 0;
    }
    
    public int computeHorizontalScrollRange(RecyclerView.State paramState)
    {
      return 0;
    }
    
    public int computeVerticalScrollExtent(RecyclerView.State paramState)
    {
      return 0;
    }
    
    public int computeVerticalScrollOffset(RecyclerView.State paramState)
    {
      return 0;
    }
    
    public int computeVerticalScrollRange(RecyclerView.State paramState)
    {
      return 0;
    }
    
    public void detachAndScrapAttachedViews(RecyclerView.Recycler paramRecycler)
    {
      int i = getChildCount() - 1;
      while (i >= 0)
      {
        scrapOrRecycleView(paramRecycler, i, getChildAt(i));
        i -= 1;
      }
    }
    
    public void detachAndScrapView(View paramView, RecyclerView.Recycler paramRecycler)
    {
      scrapOrRecycleView(paramRecycler, mChildHelper.indexOfChild(paramView), paramView);
    }
    
    public void detachAndScrapViewAt(int paramInt, RecyclerView.Recycler paramRecycler)
    {
      scrapOrRecycleView(paramRecycler, paramInt, getChildAt(paramInt));
    }
    
    public void detachView(View paramView)
    {
      int i = mChildHelper.indexOfChild(paramView);
      if (i >= 0) {
        detachViewInternal(i, paramView);
      }
    }
    
    public void detachViewAt(int paramInt)
    {
      detachViewInternal(paramInt, getChildAt(paramInt));
    }
    
    void dispatchAttachedToWindow(RecyclerView paramRecyclerView)
    {
      mIsAttachedToWindow = true;
      onAttachedToWindow(paramRecyclerView);
    }
    
    void dispatchDetachedFromWindow(RecyclerView paramRecyclerView, RecyclerView.Recycler paramRecycler)
    {
      mIsAttachedToWindow = false;
      onDetachedFromWindow(paramRecyclerView, paramRecycler);
    }
    
    public void endAnimation(View paramView)
    {
      if (mRecyclerView.mItemAnimator != null) {
        mRecyclerView.mItemAnimator.endAnimation(RecyclerView.getChildViewHolderInt(paramView));
      }
    }
    
    @Nullable
    public View findContainingItemView(View paramView)
    {
      if (mRecyclerView == null) {
        return null;
      }
      paramView = mRecyclerView.findContainingItemView(paramView);
      if (paramView == null) {
        return null;
      }
      if (mChildHelper.isHidden(paramView)) {
        return null;
      }
      return paramView;
    }
    
    public View findViewByPosition(int paramInt)
    {
      int j = getChildCount();
      int i = 0;
      while (i < j)
      {
        View localView = getChildAt(i);
        RecyclerView.ViewHolder localViewHolder = RecyclerView.getChildViewHolderInt(localView);
        if ((localViewHolder != null) && (localViewHolder.getLayoutPosition() == paramInt) && (!localViewHolder.shouldIgnore()) && ((mRecyclerView.mState.isPreLayout()) || (!localViewHolder.isRemoved()))) {
          return localView;
        }
        i += 1;
      }
      return null;
    }
    
    public abstract RecyclerView.LayoutParams generateDefaultLayoutParams();
    
    public RecyclerView.LayoutParams generateLayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      return new RecyclerView.LayoutParams(paramContext, paramAttributeSet);
    }
    
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      if ((paramLayoutParams instanceof RecyclerView.LayoutParams)) {
        return new RecyclerView.LayoutParams((RecyclerView.LayoutParams)paramLayoutParams);
      }
      if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
        return new RecyclerView.LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
      }
      return new RecyclerView.LayoutParams(paramLayoutParams);
    }
    
    public int getBaseline()
    {
      return -1;
    }
    
    public int getBottomDecorationHeight(View paramView)
    {
      return getLayoutParamsmDecorInsets.bottom;
    }
    
    public View getChildAt(int paramInt)
    {
      if (mChildHelper != null) {
        return mChildHelper.getChildAt(paramInt);
      }
      return null;
    }
    
    public int getChildCount()
    {
      if (mChildHelper != null) {
        return mChildHelper.getChildCount();
      }
      return 0;
    }
    
    public boolean getClipToPadding()
    {
      return (mRecyclerView != null) && (mRecyclerView.mClipToPadding);
    }
    
    public int getColumnCountForAccessibility(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
    {
      paramRecycler = mRecyclerView;
      int i = 1;
      if (paramRecycler != null)
      {
        if (mRecyclerView.mAdapter == null) {
          return 1;
        }
        if (canScrollHorizontally()) {
          i = mRecyclerView.mAdapter.getItemCount();
        }
        return i;
      }
      return 1;
    }
    
    public int getDecoratedBottom(View paramView)
    {
      return paramView.getBottom() + getBottomDecorationHeight(paramView);
    }
    
    public void getDecoratedBoundsWithMargins(View paramView, Rect paramRect)
    {
      RecyclerView.getDecoratedBoundsWithMarginsInt(paramView, paramRect);
    }
    
    public int getDecoratedLeft(View paramView)
    {
      return paramView.getLeft() - getLeftDecorationWidth(paramView);
    }
    
    public int getDecoratedMeasuredHeight(View paramView)
    {
      Rect localRect = getLayoutParamsmDecorInsets;
      return paramView.getMeasuredHeight() + top + bottom;
    }
    
    public int getDecoratedMeasuredWidth(View paramView)
    {
      Rect localRect = getLayoutParamsmDecorInsets;
      return paramView.getMeasuredWidth() + left + right;
    }
    
    public int getDecoratedRight(View paramView)
    {
      return paramView.getRight() + getRightDecorationWidth(paramView);
    }
    
    public int getDecoratedTop(View paramView)
    {
      return paramView.getTop() - getTopDecorationHeight(paramView);
    }
    
    public View getFocusedChild()
    {
      if (mRecyclerView == null) {
        return null;
      }
      View localView = mRecyclerView.getFocusedChild();
      if (localView != null)
      {
        if (mChildHelper.isHidden(localView)) {
          return null;
        }
        return localView;
      }
      return null;
    }
    
    public int getHeight()
    {
      return mHeight;
    }
    
    public int getHeightMode()
    {
      return mHeightMode;
    }
    
    public int getItemCount()
    {
      RecyclerView.Adapter localAdapter;
      if (mRecyclerView != null) {
        localAdapter = mRecyclerView.getAdapter();
      } else {
        localAdapter = null;
      }
      if (localAdapter != null) {
        return localAdapter.getItemCount();
      }
      return 0;
    }
    
    public int getItemViewType(View paramView)
    {
      return RecyclerView.getChildViewHolderInt(paramView).getItemViewType();
    }
    
    public int getLayoutDirection()
    {
      return ViewCompat.getLayoutDirection(mRecyclerView);
    }
    
    public int getLeftDecorationWidth(View paramView)
    {
      return getLayoutParamsmDecorInsets.left;
    }
    
    public int getMinimumHeight()
    {
      return ViewCompat.getMinimumHeight(mRecyclerView);
    }
    
    public int getMinimumWidth()
    {
      return ViewCompat.getMinimumWidth(mRecyclerView);
    }
    
    public int getPaddingBottom()
    {
      if (mRecyclerView != null) {
        return mRecyclerView.getPaddingBottom();
      }
      return 0;
    }
    
    public int getPaddingEnd()
    {
      if (mRecyclerView != null) {
        return ViewCompat.getPaddingEnd(mRecyclerView);
      }
      return 0;
    }
    
    public int getPaddingLeft()
    {
      if (mRecyclerView != null) {
        return mRecyclerView.getPaddingLeft();
      }
      return 0;
    }
    
    public int getPaddingRight()
    {
      if (mRecyclerView != null) {
        return mRecyclerView.getPaddingRight();
      }
      return 0;
    }
    
    public int getPaddingStart()
    {
      if (mRecyclerView != null) {
        return ViewCompat.getPaddingStart(mRecyclerView);
      }
      return 0;
    }
    
    public int getPaddingTop()
    {
      if (mRecyclerView != null) {
        return mRecyclerView.getPaddingTop();
      }
      return 0;
    }
    
    public int getPosition(View paramView)
    {
      return ((RecyclerView.LayoutParams)paramView.getLayoutParams()).getViewLayoutPosition();
    }
    
    public int getRightDecorationWidth(View paramView)
    {
      return getLayoutParamsmDecorInsets.right;
    }
    
    public int getRowCountForAccessibility(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
    {
      paramRecycler = mRecyclerView;
      int i = 1;
      if (paramRecycler != null)
      {
        if (mRecyclerView.mAdapter == null) {
          return 1;
        }
        if (canScrollVertically()) {
          i = mRecyclerView.mAdapter.getItemCount();
        }
        return i;
      }
      return 1;
    }
    
    public int getSelectionModeForAccessibility(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
    {
      return 0;
    }
    
    public int getTopDecorationHeight(View paramView)
    {
      return getLayoutParamsmDecorInsets.top;
    }
    
    public void getTransformedBoundingBox(View paramView, boolean paramBoolean, Rect paramRect)
    {
      Object localObject;
      if (paramBoolean)
      {
        localObject = getLayoutParamsmDecorInsets;
        paramRect.set(-left, -top, paramView.getWidth() + right, paramView.getHeight() + bottom);
      }
      else
      {
        paramRect.set(0, 0, paramView.getWidth(), paramView.getHeight());
      }
      if (mRecyclerView != null)
      {
        localObject = paramView.getMatrix();
        if ((localObject != null) && (!((Matrix)localObject).isIdentity()))
        {
          RectF localRectF = mRecyclerView.mTempRectF;
          localRectF.set(paramRect);
          ((Matrix)localObject).mapRect(localRectF);
          paramRect.set((int)Math.floor(left), (int)Math.floor(top), (int)Math.ceil(right), (int)Math.ceil(bottom));
        }
      }
      paramRect.offset(paramView.getLeft(), paramView.getTop());
    }
    
    public int getWidth()
    {
      return mWidth;
    }
    
    public int getWidthMode()
    {
      return mWidthMode;
    }
    
    boolean hasFlexibleChildInBothOrientations()
    {
      int j = getChildCount();
      int i = 0;
      while (i < j)
      {
        ViewGroup.LayoutParams localLayoutParams = getChildAt(i).getLayoutParams();
        if ((width < 0) && (height < 0)) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    
    public boolean hasFocus()
    {
      return (mRecyclerView != null) && (mRecyclerView.hasFocus());
    }
    
    public void ignoreView(View paramView)
    {
      if ((paramView.getParent() == mRecyclerView) && (mRecyclerView.indexOfChild(paramView) != -1))
      {
        paramView = RecyclerView.getChildViewHolderInt(paramView);
        paramView.addFlags(128);
        mRecyclerView.mViewInfoStore.removeViewHolder(paramView);
        return;
      }
      paramView = new StringBuilder();
      paramView.append("View should be fully attached to be ignored");
      paramView.append(mRecyclerView.exceptionLabel());
      throw new IllegalArgumentException(paramView.toString());
    }
    
    public boolean isAttachedToWindow()
    {
      return mIsAttachedToWindow;
    }
    
    public boolean isAutoMeasureEnabled()
    {
      return mAutoMeasure;
    }
    
    public boolean isFocused()
    {
      return (mRecyclerView != null) && (mRecyclerView.isFocused());
    }
    
    public final boolean isItemPrefetchEnabled()
    {
      return mItemPrefetchEnabled;
    }
    
    public boolean isLayoutHierarchical(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
    {
      return false;
    }
    
    public boolean isMeasurementCacheEnabled()
    {
      return mMeasurementCacheEnabled;
    }
    
    public boolean isSmoothScrolling()
    {
      return (mSmoothScroller != null) && (mSmoothScroller.isRunning());
    }
    
    public boolean isViewPartiallyVisible(@NonNull View paramView, boolean paramBoolean1, boolean paramBoolean2)
    {
      if ((mHorizontalBoundCheck.isViewWithinBoundFlags(paramView, 24579)) && (mVerticalBoundCheck.isViewWithinBoundFlags(paramView, 24579))) {
        paramBoolean2 = true;
      } else {
        paramBoolean2 = false;
      }
      if (paramBoolean1) {
        return paramBoolean2;
      }
      return paramBoolean2 ^ true;
    }
    
    public void layoutDecorated(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      Rect localRect = getLayoutParamsmDecorInsets;
      paramView.layout(paramInt1 + left, paramInt2 + top, paramInt3 - right, paramInt4 - bottom);
    }
    
    public void layoutDecoratedWithMargins(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
      Rect localRect = mDecorInsets;
      paramView.layout(paramInt1 + left + leftMargin, paramInt2 + top + topMargin, paramInt3 - right - rightMargin, paramInt4 - bottom - bottomMargin);
    }
    
    public void measureChild(View paramView, int paramInt1, int paramInt2)
    {
      RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
      Rect localRect = mRecyclerView.getItemDecorInsetsForChild(paramView);
      int k = left;
      int m = right;
      int i = top;
      int j = bottom;
      paramInt1 = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight() + (paramInt1 + (k + m)), width, canScrollHorizontally());
      paramInt2 = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + (paramInt2 + (i + j)), height, canScrollVertically());
      if (shouldMeasureChild(paramView, paramInt1, paramInt2, localLayoutParams)) {
        paramView.measure(paramInt1, paramInt2);
      }
    }
    
    public void measureChildWithMargins(View paramView, int paramInt1, int paramInt2)
    {
      RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
      Rect localRect = mRecyclerView.getItemDecorInsetsForChild(paramView);
      int k = left;
      int m = right;
      int i = top;
      int j = bottom;
      paramInt1 = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight() + leftMargin + rightMargin + (paramInt1 + (k + m)), width, canScrollHorizontally());
      paramInt2 = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + topMargin + bottomMargin + (paramInt2 + (i + j)), height, canScrollVertically());
      if (shouldMeasureChild(paramView, paramInt1, paramInt2, localLayoutParams)) {
        paramView.measure(paramInt1, paramInt2);
      }
    }
    
    public void moveView(int paramInt1, int paramInt2)
    {
      Object localObject = getChildAt(paramInt1);
      if (localObject == null)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Cannot move a child from non-existing index:");
        ((StringBuilder)localObject).append(paramInt1);
        ((StringBuilder)localObject).append(mRecyclerView.toString());
        throw new IllegalArgumentException(((StringBuilder)localObject).toString());
      }
      detachViewAt(paramInt1);
      attachView((View)localObject, paramInt2);
    }
    
    public void offsetChildrenHorizontal(int paramInt)
    {
      if (mRecyclerView != null) {
        mRecyclerView.offsetChildrenHorizontal(paramInt);
      }
    }
    
    public void offsetChildrenVertical(int paramInt)
    {
      if (mRecyclerView != null) {
        mRecyclerView.offsetChildrenVertical(paramInt);
      }
    }
    
    public void onAdapterChanged(RecyclerView.Adapter paramAdapter1, RecyclerView.Adapter paramAdapter2) {}
    
    public boolean onAddFocusables(RecyclerView paramRecyclerView, ArrayList<View> paramArrayList, int paramInt1, int paramInt2)
    {
      return false;
    }
    
    @CallSuper
    public void onAttachedToWindow(RecyclerView paramRecyclerView) {}
    
    @Deprecated
    public void onDetachedFromWindow(RecyclerView paramRecyclerView) {}
    
    @CallSuper
    public void onDetachedFromWindow(RecyclerView paramRecyclerView, RecyclerView.Recycler paramRecycler)
    {
      onDetachedFromWindow(paramRecyclerView);
    }
    
    @Nullable
    public View onFocusSearchFailed(View paramView, int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
    {
      return null;
    }
    
    public void onInitializeAccessibilityEvent(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, AccessibilityEvent paramAccessibilityEvent)
    {
      if (mRecyclerView != null)
      {
        if (paramAccessibilityEvent == null) {
          return;
        }
        paramRecycler = mRecyclerView;
        boolean bool2 = true;
        boolean bool1 = bool2;
        if (!paramRecycler.canScrollVertically(1))
        {
          bool1 = bool2;
          if (!mRecyclerView.canScrollVertically(-1))
          {
            bool1 = bool2;
            if (!mRecyclerView.canScrollHorizontally(-1)) {
              if (mRecyclerView.canScrollHorizontally(1)) {
                bool1 = bool2;
              } else {
                bool1 = false;
              }
            }
          }
        }
        paramAccessibilityEvent.setScrollable(bool1);
        if (mRecyclerView.mAdapter != null) {
          paramAccessibilityEvent.setItemCount(mRecyclerView.mAdapter.getItemCount());
        }
        return;
      }
    }
    
    public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
    {
      onInitializeAccessibilityEvent(mRecyclerView.mRecycler, mRecyclerView.mState, paramAccessibilityEvent);
    }
    
    void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      onInitializeAccessibilityNodeInfo(mRecyclerView.mRecycler, mRecyclerView.mState, paramAccessibilityNodeInfoCompat);
    }
    
    public void onInitializeAccessibilityNodeInfo(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      if ((mRecyclerView.canScrollVertically(-1)) || (mRecyclerView.canScrollHorizontally(-1)))
      {
        paramAccessibilityNodeInfoCompat.addAction(8192);
        paramAccessibilityNodeInfoCompat.setScrollable(true);
      }
      if ((mRecyclerView.canScrollVertically(1)) || (mRecyclerView.canScrollHorizontally(1)))
      {
        paramAccessibilityNodeInfoCompat.addAction(4096);
        paramAccessibilityNodeInfoCompat.setScrollable(true);
      }
      paramAccessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(getRowCountForAccessibility(paramRecycler, paramState), getColumnCountForAccessibility(paramRecycler, paramState), isLayoutHierarchical(paramRecycler, paramState), getSelectionModeForAccessibility(paramRecycler, paramState)));
    }
    
    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      boolean bool = canScrollVertically();
      int j = 0;
      int i;
      if (bool) {
        i = getPosition(paramView);
      } else {
        i = 0;
      }
      if (canScrollHorizontally()) {
        j = getPosition(paramView);
      }
      paramAccessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(i, 1, j, 1, false, false));
    }
    
    void onInitializeAccessibilityNodeInfoForItem(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      RecyclerView.ViewHolder localViewHolder = RecyclerView.getChildViewHolderInt(paramView);
      if ((localViewHolder != null) && (!localViewHolder.isRemoved()) && (!mChildHelper.isHidden(itemView))) {
        onInitializeAccessibilityNodeInfoForItem(mRecyclerView.mRecycler, mRecyclerView.mState, paramView, paramAccessibilityNodeInfoCompat);
      }
    }
    
    public View onInterceptFocusSearch(View paramView, int paramInt)
    {
      return null;
    }
    
    public void onItemsAdded(RecyclerView paramRecyclerView, int paramInt1, int paramInt2) {}
    
    public void onItemsChanged(RecyclerView paramRecyclerView) {}
    
    public void onItemsMoved(RecyclerView paramRecyclerView, int paramInt1, int paramInt2, int paramInt3) {}
    
    public void onItemsRemoved(RecyclerView paramRecyclerView, int paramInt1, int paramInt2) {}
    
    public void onItemsUpdated(RecyclerView paramRecyclerView, int paramInt1, int paramInt2) {}
    
    public void onItemsUpdated(RecyclerView paramRecyclerView, int paramInt1, int paramInt2, Object paramObject)
    {
      onItemsUpdated(paramRecyclerView, paramInt1, paramInt2);
    }
    
    public void onLayoutChildren(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
    {
      Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
    }
    
    public void onLayoutCompleted(RecyclerView.State paramState) {}
    
    public void onMeasure(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt1, int paramInt2)
    {
      mRecyclerView.defaultOnMeasure(paramInt1, paramInt2);
    }
    
    public boolean onRequestChildFocus(RecyclerView paramRecyclerView, RecyclerView.State paramState, View paramView1, View paramView2)
    {
      return onRequestChildFocus(paramRecyclerView, paramView1, paramView2);
    }
    
    @Deprecated
    public boolean onRequestChildFocus(RecyclerView paramRecyclerView, View paramView1, View paramView2)
    {
      return (isSmoothScrolling()) || (paramRecyclerView.isComputingLayout());
    }
    
    public void onRestoreInstanceState(Parcelable paramParcelable) {}
    
    public Parcelable onSaveInstanceState()
    {
      return null;
    }
    
    public void onScrollStateChanged(int paramInt) {}
    
    boolean performAccessibilityAction(int paramInt, Bundle paramBundle)
    {
      return performAccessibilityAction(mRecyclerView.mRecycler, mRecyclerView.mState, paramInt, paramBundle);
    }
    
    public boolean performAccessibilityAction(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt, Bundle paramBundle)
    {
      if (mRecyclerView == null) {
        return false;
      }
      int j;
      if (paramInt != 4096)
      {
        if (paramInt != 8192)
        {
          paramInt = 0;
          i = paramInt;
          break label167;
        }
        if (mRecyclerView.canScrollVertically(-1)) {
          paramInt = -(getHeight() - getPaddingTop() - getPaddingBottom());
        } else {
          paramInt = 0;
        }
        j = paramInt;
        if (mRecyclerView.canScrollHorizontally(-1))
        {
          i = -(getWidth() - getPaddingLeft() - getPaddingRight());
          break label167;
        }
      }
      else
      {
        if (mRecyclerView.canScrollVertically(1)) {
          paramInt = getHeight() - getPaddingTop() - getPaddingBottom();
        } else {
          paramInt = 0;
        }
        j = paramInt;
        if (mRecyclerView.canScrollHorizontally(1))
        {
          i = getWidth() - getPaddingLeft() - getPaddingRight();
          break label167;
        }
      }
      int i = 0;
      paramInt = j;
      label167:
      if ((paramInt == 0) && (i == 0)) {
        return false;
      }
      mRecyclerView.scrollBy(i, paramInt);
      return true;
    }
    
    public boolean performAccessibilityActionForItem(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, View paramView, int paramInt, Bundle paramBundle)
    {
      return false;
    }
    
    boolean performAccessibilityActionForItem(View paramView, int paramInt, Bundle paramBundle)
    {
      return performAccessibilityActionForItem(mRecyclerView.mRecycler, mRecyclerView.mState, paramView, paramInt, paramBundle);
    }
    
    public void postOnAnimation(Runnable paramRunnable)
    {
      if (mRecyclerView != null) {
        ViewCompat.postOnAnimation(mRecyclerView, paramRunnable);
      }
    }
    
    public void removeAllViews()
    {
      int i = getChildCount() - 1;
      while (i >= 0)
      {
        mChildHelper.removeViewAt(i);
        i -= 1;
      }
    }
    
    public void removeAndRecycleAllViews(RecyclerView.Recycler paramRecycler)
    {
      int i = getChildCount() - 1;
      while (i >= 0)
      {
        if (!RecyclerView.getChildViewHolderInt(getChildAt(i)).shouldIgnore()) {
          removeAndRecycleViewAt(i, paramRecycler);
        }
        i -= 1;
      }
    }
    
    void removeAndRecycleScrapInt(RecyclerView.Recycler paramRecycler)
    {
      int j = paramRecycler.getScrapCount();
      int i = j - 1;
      while (i >= 0)
      {
        View localView = paramRecycler.getScrapViewAt(i);
        RecyclerView.ViewHolder localViewHolder = RecyclerView.getChildViewHolderInt(localView);
        if (!localViewHolder.shouldIgnore())
        {
          localViewHolder.setIsRecyclable(false);
          if (localViewHolder.isTmpDetached()) {
            mRecyclerView.removeDetachedView(localView, false);
          }
          if (mRecyclerView.mItemAnimator != null) {
            mRecyclerView.mItemAnimator.endAnimation(localViewHolder);
          }
          localViewHolder.setIsRecyclable(true);
          paramRecycler.quickRecycleScrapView(localView);
        }
        i -= 1;
      }
      paramRecycler.clearScrap();
      if (j > 0) {
        mRecyclerView.invalidate();
      }
    }
    
    public void removeAndRecycleView(View paramView, RecyclerView.Recycler paramRecycler)
    {
      removeView(paramView);
      paramRecycler.recycleView(paramView);
    }
    
    public void removeAndRecycleViewAt(int paramInt, RecyclerView.Recycler paramRecycler)
    {
      View localView = getChildAt(paramInt);
      removeViewAt(paramInt);
      paramRecycler.recycleView(localView);
    }
    
    public boolean removeCallbacks(Runnable paramRunnable)
    {
      if (mRecyclerView != null) {
        return mRecyclerView.removeCallbacks(paramRunnable);
      }
      return false;
    }
    
    public void removeDetachedView(View paramView)
    {
      mRecyclerView.removeDetachedView(paramView, false);
    }
    
    public void removeView(View paramView)
    {
      mChildHelper.removeView(paramView);
    }
    
    public void removeViewAt(int paramInt)
    {
      if (getChildAt(paramInt) != null) {
        mChildHelper.removeViewAt(paramInt);
      }
    }
    
    public boolean requestChildRectangleOnScreen(RecyclerView paramRecyclerView, View paramView, Rect paramRect, boolean paramBoolean)
    {
      return requestChildRectangleOnScreen(paramRecyclerView, paramView, paramRect, paramBoolean, false);
    }
    
    public boolean requestChildRectangleOnScreen(RecyclerView paramRecyclerView, View paramView, Rect paramRect, boolean paramBoolean1, boolean paramBoolean2)
    {
      paramView = getChildRectangleOnScreenScrollAmount(paramRecyclerView, paramView, paramRect, paramBoolean1);
      int i = paramView[0];
      int j = paramView[1];
      if (((paramBoolean2) && (!isFocusedChildVisibleAfterScrolling(paramRecyclerView, i, j))) || ((i == 0) && (j == 0))) {
        return false;
      }
      if (paramBoolean1)
      {
        paramRecyclerView.scrollBy(i, j);
        return true;
      }
      paramRecyclerView.smoothScrollBy(i, j);
      return true;
    }
    
    public void requestLayout()
    {
      if (mRecyclerView != null) {
        mRecyclerView.requestLayout();
      }
    }
    
    public void requestSimpleAnimationsInNextLayout()
    {
      mRequestedSimpleAnimations = true;
    }
    
    public int scrollHorizontallyBy(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
    {
      return 0;
    }
    
    public void scrollToPosition(int paramInt) {}
    
    public int scrollVerticallyBy(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
    {
      return 0;
    }
    
    @Deprecated
    public void setAutoMeasureEnabled(boolean paramBoolean)
    {
      mAutoMeasure = paramBoolean;
    }
    
    void setExactMeasureSpecsFrom(RecyclerView paramRecyclerView)
    {
      setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(paramRecyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(paramRecyclerView.getHeight(), 1073741824));
    }
    
    public final void setItemPrefetchEnabled(boolean paramBoolean)
    {
      if (paramBoolean != mItemPrefetchEnabled)
      {
        mItemPrefetchEnabled = paramBoolean;
        mPrefetchMaxCountObserved = 0;
        if (mRecyclerView != null) {
          mRecyclerView.mRecycler.updateViewCacheSize();
        }
      }
    }
    
    void setMeasureSpecs(int paramInt1, int paramInt2)
    {
      mWidth = View.MeasureSpec.getSize(paramInt1);
      mWidthMode = View.MeasureSpec.getMode(paramInt1);
      if ((mWidthMode == 0) && (!RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC)) {
        mWidth = 0;
      }
      mHeight = View.MeasureSpec.getSize(paramInt2);
      mHeightMode = View.MeasureSpec.getMode(paramInt2);
      if ((mHeightMode == 0) && (!RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC)) {
        mHeight = 0;
      }
    }
    
    public void setMeasuredDimension(int paramInt1, int paramInt2)
    {
      mRecyclerView.setMeasuredDimension(paramInt1, paramInt2);
    }
    
    public void setMeasuredDimension(Rect paramRect, int paramInt1, int paramInt2)
    {
      int i = paramRect.width();
      int j = getPaddingLeft();
      int k = getPaddingRight();
      int m = paramRect.height();
      int n = getPaddingTop();
      int i1 = getPaddingBottom();
      setMeasuredDimension(chooseSize(paramInt1, i + j + k, getMinimumWidth()), chooseSize(paramInt2, m + n + i1, getMinimumHeight()));
    }
    
    void setMeasuredDimensionFromChildren(int paramInt1, int paramInt2)
    {
      int i4 = getChildCount();
      if (i4 == 0)
      {
        mRecyclerView.defaultOnMeasure(paramInt1, paramInt2);
        return;
      }
      int j = 0;
      int i2 = Integer.MAX_VALUE;
      int n = Integer.MIN_VALUE;
      int i = n;
      int m = Integer.MAX_VALUE;
      while (j < i4)
      {
        View localView = getChildAt(j);
        Rect localRect = mRecyclerView.mTempRect;
        getDecoratedBoundsWithMargins(localView, localRect);
        int k = i2;
        if (left < i2) {
          k = left;
        }
        int i1 = n;
        if (right > n) {
          i1 = right;
        }
        n = m;
        if (top < m) {
          n = top;
        }
        int i3 = i;
        if (bottom > i) {
          i3 = bottom;
        }
        j += 1;
        m = n;
        i2 = k;
        n = i1;
        i = i3;
      }
      mRecyclerView.mTempRect.set(i2, m, n, i);
      setMeasuredDimension(mRecyclerView.mTempRect, paramInt1, paramInt2);
    }
    
    public void setMeasurementCacheEnabled(boolean paramBoolean)
    {
      mMeasurementCacheEnabled = paramBoolean;
    }
    
    void setRecyclerView(RecyclerView paramRecyclerView)
    {
      if (paramRecyclerView == null)
      {
        mRecyclerView = null;
        mChildHelper = null;
        mWidth = 0;
        mHeight = 0;
      }
      else
      {
        mRecyclerView = paramRecyclerView;
        mChildHelper = mChildHelper;
        mWidth = paramRecyclerView.getWidth();
        mHeight = paramRecyclerView.getHeight();
      }
      mWidthMode = 1073741824;
      mHeightMode = 1073741824;
    }
    
    boolean shouldMeasureChild(View paramView, int paramInt1, int paramInt2, RecyclerView.LayoutParams paramLayoutParams)
    {
      return (paramView.isLayoutRequested()) || (!mMeasurementCacheEnabled) || (!isMeasurementUpToDate(paramView.getWidth(), paramInt1, width)) || (!isMeasurementUpToDate(paramView.getHeight(), paramInt2, height));
    }
    
    boolean shouldMeasureTwice()
    {
      return false;
    }
    
    boolean shouldReMeasureChild(View paramView, int paramInt1, int paramInt2, RecyclerView.LayoutParams paramLayoutParams)
    {
      return (!mMeasurementCacheEnabled) || (!isMeasurementUpToDate(paramView.getMeasuredWidth(), paramInt1, width)) || (!isMeasurementUpToDate(paramView.getMeasuredHeight(), paramInt2, height));
    }
    
    public void smoothScrollToPosition(RecyclerView paramRecyclerView, RecyclerView.State paramState, int paramInt)
    {
      Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
    }
    
    public void startSmoothScroll(RecyclerView.SmoothScroller paramSmoothScroller)
    {
      if ((mSmoothScroller != null) && (paramSmoothScroller != mSmoothScroller) && (mSmoothScroller.isRunning())) {
        mSmoothScroller.stop();
      }
      mSmoothScroller = paramSmoothScroller;
      mSmoothScroller.start(mRecyclerView, this);
    }
    
    public void stopIgnoringView(View paramView)
    {
      paramView = RecyclerView.getChildViewHolderInt(paramView);
      paramView.stopIgnoring();
      paramView.resetInternal();
      paramView.addFlags(4);
    }
    
    void stopSmoothScroller()
    {
      if (mSmoothScroller != null) {
        mSmoothScroller.stop();
      }
    }
    
    public boolean supportsPredictiveItemAnimations()
    {
      return false;
    }
    
    public static abstract interface LayoutPrefetchRegistry
    {
      public abstract void addPosition(int paramInt1, int paramInt2);
    }
    
    public static class Properties
    {
      public int orientation;
      public boolean reverseLayout;
      public int spanCount;
      public boolean stackFromEnd;
      
      public Properties() {}
    }
  }
  
  public static class LayoutParams
    extends ViewGroup.MarginLayoutParams
  {
    final Rect mDecorInsets = new Rect();
    boolean mInsetsDirty = true;
    boolean mPendingInvalidate = false;
    RecyclerView.ViewHolder mViewHolder;
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
    
    public int getViewAdapterPosition()
    {
      return mViewHolder.getAdapterPosition();
    }
    
    public int getViewLayoutPosition()
    {
      return mViewHolder.getLayoutPosition();
    }
    
    @Deprecated
    public int getViewPosition()
    {
      return mViewHolder.getPosition();
    }
    
    public boolean isItemChanged()
    {
      return mViewHolder.isUpdated();
    }
    
    public boolean isItemRemoved()
    {
      return mViewHolder.isRemoved();
    }
    
    public boolean isViewInvalid()
    {
      return mViewHolder.isInvalid();
    }
    
    public boolean viewNeedsUpdate()
    {
      return mViewHolder.needsUpdate();
    }
  }
  
  public static abstract interface OnChildAttachStateChangeListener
  {
    public abstract void onChildViewAttachedToWindow(View paramView);
    
    public abstract void onChildViewDetachedFromWindow(View paramView);
  }
  
  public static abstract class OnFlingListener
  {
    public OnFlingListener() {}
    
    public abstract boolean onFling(int paramInt1, int paramInt2);
  }
  
  public static abstract interface OnItemTouchListener
  {
    public abstract boolean onInterceptTouchEvent(RecyclerView paramRecyclerView, MotionEvent paramMotionEvent);
    
    public abstract void onRequestDisallowInterceptTouchEvent(boolean paramBoolean);
    
    public abstract void onTouchEvent(RecyclerView paramRecyclerView, MotionEvent paramMotionEvent);
  }
  
  public static abstract class OnScrollListener
  {
    public OnScrollListener() {}
    
    public void onScrollStateChanged(RecyclerView paramRecyclerView, int paramInt) {}
    
    public void onScrolled(RecyclerView paramRecyclerView, int paramInt1, int paramInt2) {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface Orientation {}
  
  public static class RecycledViewPool
  {
    private static final int DEFAULT_MAX_SCRAP = 5;
    private int mAttachCount = 0;
    SparseArray<ScrapData> mScrap = new SparseArray();
    
    public RecycledViewPool() {}
    
    private ScrapData getScrapDataForType(int paramInt)
    {
      ScrapData localScrapData2 = (ScrapData)mScrap.get(paramInt);
      ScrapData localScrapData1 = localScrapData2;
      if (localScrapData2 == null)
      {
        localScrapData1 = new ScrapData();
        mScrap.put(paramInt, localScrapData1);
      }
      return localScrapData1;
    }
    
    void attach(RecyclerView.Adapter paramAdapter)
    {
      mAttachCount += 1;
    }
    
    public void clear()
    {
      int i = 0;
      while (i < mScrap.size())
      {
        mScrap.valueAt(i)).mScrapHeap.clear();
        i += 1;
      }
    }
    
    void detach()
    {
      mAttachCount -= 1;
    }
    
    void factorInBindTime(int paramInt, long paramLong)
    {
      ScrapData localScrapData = getScrapDataForType(paramInt);
      mBindRunningAverageNs = runningAverage(mBindRunningAverageNs, paramLong);
    }
    
    void factorInCreateTime(int paramInt, long paramLong)
    {
      ScrapData localScrapData = getScrapDataForType(paramInt);
      mCreateRunningAverageNs = runningAverage(mCreateRunningAverageNs, paramLong);
    }
    
    @Nullable
    public RecyclerView.ViewHolder getRecycledView(int paramInt)
    {
      Object localObject = (ScrapData)mScrap.get(paramInt);
      if ((localObject != null) && (!mScrapHeap.isEmpty()))
      {
        localObject = mScrapHeap;
        return (RecyclerView.ViewHolder)((ArrayList)localObject).remove(((ArrayList)localObject).size() - 1);
      }
      return null;
    }
    
    public int getRecycledViewCount(int paramInt)
    {
      return getScrapDataForTypemScrapHeap.size();
    }
    
    void onAdapterChanged(RecyclerView.Adapter paramAdapter1, RecyclerView.Adapter paramAdapter2, boolean paramBoolean)
    {
      if (paramAdapter1 != null) {
        detach();
      }
      if ((!paramBoolean) && (mAttachCount == 0)) {
        clear();
      }
      if (paramAdapter2 != null) {
        attach(paramAdapter2);
      }
    }
    
    public void putRecycledView(RecyclerView.ViewHolder paramViewHolder)
    {
      int i = paramViewHolder.getItemViewType();
      ArrayList localArrayList = getScrapDataForTypemScrapHeap;
      if (mScrap.get(i)).mMaxScrap <= localArrayList.size()) {
        return;
      }
      paramViewHolder.resetInternal();
      localArrayList.add(paramViewHolder);
    }
    
    long runningAverage(long paramLong1, long paramLong2)
    {
      if (paramLong1 == 0L) {
        return paramLong2;
      }
      return paramLong1 / 4L * 3L + paramLong2 / 4L;
    }
    
    public void setMaxRecycledViews(int paramInt1, int paramInt2)
    {
      Object localObject = getScrapDataForType(paramInt1);
      mMaxScrap = paramInt2;
      localObject = mScrapHeap;
      while (((ArrayList)localObject).size() > paramInt2) {
        ((ArrayList)localObject).remove(((ArrayList)localObject).size() - 1);
      }
    }
    
    int size()
    {
      int i = 0;
      int k;
      for (int j = 0; i < mScrap.size(); j = k)
      {
        ArrayList localArrayList = mScrap.valueAt(i)).mScrapHeap;
        k = j;
        if (localArrayList != null) {
          k = j + localArrayList.size();
        }
        i += 1;
      }
      return j;
    }
    
    boolean willBindInTime(int paramInt, long paramLong1, long paramLong2)
    {
      long l = getScrapDataForTypemBindRunningAverageNs;
      return (l == 0L) || (paramLong1 + l < paramLong2);
    }
    
    boolean willCreateInTime(int paramInt, long paramLong1, long paramLong2)
    {
      long l = getScrapDataForTypemCreateRunningAverageNs;
      return (l == 0L) || (paramLong1 + l < paramLong2);
    }
    
    static class ScrapData
    {
      long mBindRunningAverageNs = 0L;
      long mCreateRunningAverageNs = 0L;
      int mMaxScrap = 5;
      final ArrayList<RecyclerView.ViewHolder> mScrapHeap = new ArrayList();
      
      ScrapData() {}
    }
  }
  
  public final class Recycler
  {
    static final int DEFAULT_CACHE_SIZE = 2;
    final ArrayList<RecyclerView.ViewHolder> mAttachedScrap = new ArrayList();
    final ArrayList<RecyclerView.ViewHolder> mCachedViews = new ArrayList();
    ArrayList<RecyclerView.ViewHolder> mChangedScrap = null;
    RecyclerView.RecycledViewPool mRecyclerPool;
    private int mRequestedCacheMax = 2;
    private final List<RecyclerView.ViewHolder> mUnmodifiableAttachedScrap = Collections.unmodifiableList(mAttachedScrap);
    private RecyclerView.ViewCacheExtension mViewCacheExtension;
    int mViewCacheMax = 2;
    
    public Recycler() {}
    
    private void attachAccessibilityDelegateOnBind(RecyclerView.ViewHolder paramViewHolder)
    {
      if (isAccessibilityEnabled())
      {
        View localView = itemView;
        if (ViewCompat.getImportantForAccessibility(localView) == 0) {
          ViewCompat.setImportantForAccessibility(localView, 1);
        }
        if (!ViewCompat.hasAccessibilityDelegate(localView))
        {
          paramViewHolder.addFlags(16384);
          ViewCompat.setAccessibilityDelegate(localView, mAccessibilityDelegate.getItemDelegate());
        }
      }
    }
    
    private void invalidateDisplayListInt(RecyclerView.ViewHolder paramViewHolder)
    {
      if ((itemView instanceof ViewGroup)) {
        invalidateDisplayListInt((ViewGroup)itemView, false);
      }
    }
    
    private void invalidateDisplayListInt(ViewGroup paramViewGroup, boolean paramBoolean)
    {
      int i = paramViewGroup.getChildCount() - 1;
      while (i >= 0)
      {
        View localView = paramViewGroup.getChildAt(i);
        if ((localView instanceof ViewGroup)) {
          invalidateDisplayListInt((ViewGroup)localView, true);
        }
        i -= 1;
      }
      if (!paramBoolean) {
        return;
      }
      if (paramViewGroup.getVisibility() == 4)
      {
        paramViewGroup.setVisibility(0);
        paramViewGroup.setVisibility(4);
        return;
      }
      i = paramViewGroup.getVisibility();
      paramViewGroup.setVisibility(4);
      paramViewGroup.setVisibility(i);
    }
    
    private boolean tryBindViewHolderByDeadline(RecyclerView.ViewHolder paramViewHolder, int paramInt1, int paramInt2, long paramLong)
    {
      mOwnerRecyclerView = RecyclerView.this;
      int i = paramViewHolder.getItemViewType();
      long l = getNanoTime();
      if ((paramLong != Long.MAX_VALUE) && (!mRecyclerPool.willBindInTime(i, l, paramLong))) {
        return false;
      }
      mAdapter.bindViewHolder(paramViewHolder, paramInt1);
      paramLong = getNanoTime();
      mRecyclerPool.factorInBindTime(paramViewHolder.getItemViewType(), paramLong - l);
      attachAccessibilityDelegateOnBind(paramViewHolder);
      if (mState.isPreLayout()) {
        mPreLayoutPosition = paramInt2;
      }
      return true;
    }
    
    void addViewHolderToRecycledViewPool(RecyclerView.ViewHolder paramViewHolder, boolean paramBoolean)
    {
      RecyclerView.clearNestedRecyclerViewIfNotNested(paramViewHolder);
      if (paramViewHolder.hasAnyOfTheFlags(16384))
      {
        paramViewHolder.setFlags(0, 16384);
        ViewCompat.setAccessibilityDelegate(itemView, null);
      }
      if (paramBoolean) {
        dispatchViewRecycled(paramViewHolder);
      }
      mOwnerRecyclerView = null;
      getRecycledViewPool().putRecycledView(paramViewHolder);
    }
    
    public void bindViewToPosition(View paramView, int paramInt)
    {
      RecyclerView.ViewHolder localViewHolder = RecyclerView.getChildViewHolderInt(paramView);
      if (localViewHolder == null)
      {
        paramView = new StringBuilder();
        paramView.append("The view does not have a ViewHolder. You cannot pass arbitrary views to this method, they should be created by the Adapter");
        paramView.append(exceptionLabel());
        throw new IllegalArgumentException(paramView.toString());
      }
      int i = mAdapterHelper.findPositionOffset(paramInt);
      if ((i >= 0) && (i < mAdapter.getItemCount()))
      {
        tryBindViewHolderByDeadline(localViewHolder, i, paramInt, Long.MAX_VALUE);
        paramView = itemView.getLayoutParams();
        if (paramView == null)
        {
          paramView = (RecyclerView.LayoutParams)generateDefaultLayoutParams();
          itemView.setLayoutParams(paramView);
        }
        else if (!checkLayoutParams(paramView))
        {
          paramView = (RecyclerView.LayoutParams)generateLayoutParams(paramView);
          itemView.setLayoutParams(paramView);
        }
        else
        {
          paramView = (RecyclerView.LayoutParams)paramView;
        }
        boolean bool = true;
        mInsetsDirty = true;
        mViewHolder = localViewHolder;
        if (itemView.getParent() != null) {
          bool = false;
        }
        mPendingInvalidate = bool;
        return;
      }
      paramView = new StringBuilder();
      paramView.append("Inconsistency detected. Invalid item position ");
      paramView.append(paramInt);
      paramView.append("(offset:");
      paramView.append(i);
      paramView.append(").");
      paramView.append("state:");
      paramView.append(mState.getItemCount());
      paramView.append(exceptionLabel());
      throw new IndexOutOfBoundsException(paramView.toString());
    }
    
    public void clear()
    {
      mAttachedScrap.clear();
      recycleAndClearCachedViews();
    }
    
    void clearOldPositions()
    {
      int k = mCachedViews.size();
      int j = 0;
      int i = 0;
      while (i < k)
      {
        ((RecyclerView.ViewHolder)mCachedViews.get(i)).clearOldPosition();
        i += 1;
      }
      k = mAttachedScrap.size();
      i = 0;
      while (i < k)
      {
        ((RecyclerView.ViewHolder)mAttachedScrap.get(i)).clearOldPosition();
        i += 1;
      }
      if (mChangedScrap != null)
      {
        k = mChangedScrap.size();
        i = j;
        while (i < k)
        {
          ((RecyclerView.ViewHolder)mChangedScrap.get(i)).clearOldPosition();
          i += 1;
        }
      }
    }
    
    void clearScrap()
    {
      mAttachedScrap.clear();
      if (mChangedScrap != null) {
        mChangedScrap.clear();
      }
    }
    
    public int convertPreLayoutPositionToPostLayout(int paramInt)
    {
      if ((paramInt >= 0) && (paramInt < mState.getItemCount()))
      {
        if (!mState.isPreLayout()) {
          return paramInt;
        }
        return mAdapterHelper.findPositionOffset(paramInt);
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("invalid position ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(". State ");
      localStringBuilder.append("item count is ");
      localStringBuilder.append(mState.getItemCount());
      localStringBuilder.append(exceptionLabel());
      throw new IndexOutOfBoundsException(localStringBuilder.toString());
    }
    
    void dispatchViewRecycled(RecyclerView.ViewHolder paramViewHolder)
    {
      if (mRecyclerListener != null) {
        mRecyclerListener.onViewRecycled(paramViewHolder);
      }
      if (mAdapter != null) {
        mAdapter.onViewRecycled(paramViewHolder);
      }
      if (mState != null) {
        mViewInfoStore.removeViewHolder(paramViewHolder);
      }
    }
    
    RecyclerView.ViewHolder getChangedScrapViewForPosition(int paramInt)
    {
      if (mChangedScrap != null)
      {
        int k = mChangedScrap.size();
        if (k == 0) {
          return null;
        }
        int j = 0;
        int i = 0;
        RecyclerView.ViewHolder localViewHolder;
        while (i < k)
        {
          localViewHolder = (RecyclerView.ViewHolder)mChangedScrap.get(i);
          if ((!localViewHolder.wasReturnedFromScrap()) && (localViewHolder.getLayoutPosition() == paramInt))
          {
            localViewHolder.addFlags(32);
            return localViewHolder;
          }
          i += 1;
        }
        if (mAdapter.hasStableIds())
        {
          paramInt = mAdapterHelper.findPositionOffset(paramInt);
          if ((paramInt > 0) && (paramInt < mAdapter.getItemCount()))
          {
            long l = mAdapter.getItemId(paramInt);
            paramInt = j;
            while (paramInt < k)
            {
              localViewHolder = (RecyclerView.ViewHolder)mChangedScrap.get(paramInt);
              if ((!localViewHolder.wasReturnedFromScrap()) && (localViewHolder.getItemId() == l))
              {
                localViewHolder.addFlags(32);
                return localViewHolder;
              }
              paramInt += 1;
            }
          }
        }
        return null;
      }
      return null;
    }
    
    RecyclerView.RecycledViewPool getRecycledViewPool()
    {
      if (mRecyclerPool == null) {
        mRecyclerPool = new RecyclerView.RecycledViewPool();
      }
      return mRecyclerPool;
    }
    
    int getScrapCount()
    {
      return mAttachedScrap.size();
    }
    
    public List<RecyclerView.ViewHolder> getScrapList()
    {
      return mUnmodifiableAttachedScrap;
    }
    
    RecyclerView.ViewHolder getScrapOrCachedViewForId(long paramLong, int paramInt, boolean paramBoolean)
    {
      int i = mAttachedScrap.size() - 1;
      RecyclerView.ViewHolder localViewHolder;
      while (i >= 0)
      {
        localViewHolder = (RecyclerView.ViewHolder)mAttachedScrap.get(i);
        if ((localViewHolder.getItemId() == paramLong) && (!localViewHolder.wasReturnedFromScrap()))
        {
          if (paramInt == localViewHolder.getItemViewType())
          {
            localViewHolder.addFlags(32);
            if ((localViewHolder.isRemoved()) && (!mState.isPreLayout())) {
              localViewHolder.setFlags(2, 14);
            }
            return localViewHolder;
          }
          if (!paramBoolean)
          {
            mAttachedScrap.remove(i);
            removeDetachedView(itemView, false);
            quickRecycleScrapView(itemView);
          }
        }
        i -= 1;
      }
      i = mCachedViews.size() - 1;
      while (i >= 0)
      {
        localViewHolder = (RecyclerView.ViewHolder)mCachedViews.get(i);
        if (localViewHolder.getItemId() == paramLong)
        {
          if (paramInt == localViewHolder.getItemViewType())
          {
            if (!paramBoolean) {
              mCachedViews.remove(i);
            }
            return localViewHolder;
          }
          if (!paramBoolean)
          {
            recycleCachedViewAt(i);
            return null;
          }
        }
        i -= 1;
      }
      return null;
    }
    
    RecyclerView.ViewHolder getScrapOrHiddenOrCachedHolderForPosition(int paramInt, boolean paramBoolean)
    {
      int k = mAttachedScrap.size();
      int j = 0;
      int i = 0;
      RecyclerView.ViewHolder localViewHolder;
      while (i < k)
      {
        localViewHolder = (RecyclerView.ViewHolder)mAttachedScrap.get(i);
        if ((!localViewHolder.wasReturnedFromScrap()) && (localViewHolder.getLayoutPosition() == paramInt) && (!localViewHolder.isInvalid()) && ((mState.mInPreLayout) || (!localViewHolder.isRemoved())))
        {
          localViewHolder.addFlags(32);
          return localViewHolder;
        }
        i += 1;
      }
      if (!paramBoolean)
      {
        Object localObject = mChildHelper.findHiddenNonRemovedView(paramInt);
        if (localObject != null)
        {
          localViewHolder = RecyclerView.getChildViewHolderInt((View)localObject);
          mChildHelper.unhide((View)localObject);
          paramInt = mChildHelper.indexOfChild((View)localObject);
          if (paramInt == -1)
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("layout index should not be -1 after unhiding a view:");
            ((StringBuilder)localObject).append(localViewHolder);
            ((StringBuilder)localObject).append(exceptionLabel());
            throw new IllegalStateException(((StringBuilder)localObject).toString());
          }
          mChildHelper.detachViewFromParent(paramInt);
          scrapView((View)localObject);
          localViewHolder.addFlags(8224);
          return localViewHolder;
        }
      }
      k = mCachedViews.size();
      i = j;
      while (i < k)
      {
        localViewHolder = (RecyclerView.ViewHolder)mCachedViews.get(i);
        if ((!localViewHolder.isInvalid()) && (localViewHolder.getLayoutPosition() == paramInt))
        {
          if (!paramBoolean) {
            mCachedViews.remove(i);
          }
          return localViewHolder;
        }
        i += 1;
      }
      return null;
    }
    
    View getScrapViewAt(int paramInt)
    {
      return mAttachedScrap.get(paramInt)).itemView;
    }
    
    public View getViewForPosition(int paramInt)
    {
      return getViewForPosition(paramInt, false);
    }
    
    View getViewForPosition(int paramInt, boolean paramBoolean)
    {
      return tryGetViewHolderForPositionByDeadlineMAX_VALUEitemView;
    }
    
    void markItemDecorInsetsDirty()
    {
      int j = mCachedViews.size();
      int i = 0;
      while (i < j)
      {
        RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)mCachedViews.get(i)).itemView.getLayoutParams();
        if (localLayoutParams != null) {
          mInsetsDirty = true;
        }
        i += 1;
      }
    }
    
    void markKnownViewsInvalid()
    {
      int j = mCachedViews.size();
      int i = 0;
      while (i < j)
      {
        RecyclerView.ViewHolder localViewHolder = (RecyclerView.ViewHolder)mCachedViews.get(i);
        if (localViewHolder != null)
        {
          localViewHolder.addFlags(6);
          localViewHolder.addChangePayload(null);
        }
        i += 1;
      }
      if ((mAdapter == null) || (!mAdapter.hasStableIds())) {
        recycleAndClearCachedViews();
      }
    }
    
    void offsetPositionRecordsForInsert(int paramInt1, int paramInt2)
    {
      int j = mCachedViews.size();
      int i = 0;
      while (i < j)
      {
        RecyclerView.ViewHolder localViewHolder = (RecyclerView.ViewHolder)mCachedViews.get(i);
        if ((localViewHolder != null) && (mPosition >= paramInt1)) {
          localViewHolder.offsetPosition(paramInt2, true);
        }
        i += 1;
      }
    }
    
    void offsetPositionRecordsForMove(int paramInt1, int paramInt2)
    {
      int j;
      int k;
      int i;
      if (paramInt1 < paramInt2)
      {
        j = paramInt2;
        k = -1;
        i = paramInt1;
      }
      else
      {
        j = paramInt1;
        k = 1;
        i = paramInt2;
      }
      int n = mCachedViews.size();
      int m = 0;
      while (m < n)
      {
        RecyclerView.ViewHolder localViewHolder = (RecyclerView.ViewHolder)mCachedViews.get(m);
        if ((localViewHolder != null) && (mPosition >= i) && (mPosition <= j)) {
          if (mPosition == paramInt1) {
            localViewHolder.offsetPosition(paramInt2 - paramInt1, false);
          } else {
            localViewHolder.offsetPosition(k, false);
          }
        }
        m += 1;
      }
    }
    
    void offsetPositionRecordsForRemove(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      int i = mCachedViews.size() - 1;
      while (i >= 0)
      {
        RecyclerView.ViewHolder localViewHolder = (RecyclerView.ViewHolder)mCachedViews.get(i);
        if (localViewHolder != null) {
          if (mPosition >= paramInt1 + paramInt2)
          {
            localViewHolder.offsetPosition(-paramInt2, paramBoolean);
          }
          else if (mPosition >= paramInt1)
          {
            localViewHolder.addFlags(8);
            recycleCachedViewAt(i);
          }
        }
        i -= 1;
      }
    }
    
    void onAdapterChanged(RecyclerView.Adapter paramAdapter1, RecyclerView.Adapter paramAdapter2, boolean paramBoolean)
    {
      clear();
      getRecycledViewPool().onAdapterChanged(paramAdapter1, paramAdapter2, paramBoolean);
    }
    
    void quickRecycleScrapView(View paramView)
    {
      paramView = RecyclerView.getChildViewHolderInt(paramView);
      RecyclerView.ViewHolder.access$1002(paramView, null);
      RecyclerView.ViewHolder.access$1102(paramView, false);
      paramView.clearReturnedFromScrapFlag();
      recycleViewHolderInternal(paramView);
    }
    
    void recycleAndClearCachedViews()
    {
      int i = mCachedViews.size() - 1;
      while (i >= 0)
      {
        recycleCachedViewAt(i);
        i -= 1;
      }
      mCachedViews.clear();
      if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
        mPrefetchRegistry.clearPrefetchPositions();
      }
    }
    
    void recycleCachedViewAt(int paramInt)
    {
      addViewHolderToRecycledViewPool((RecyclerView.ViewHolder)mCachedViews.get(paramInt), true);
      mCachedViews.remove(paramInt);
    }
    
    public void recycleView(View paramView)
    {
      RecyclerView.ViewHolder localViewHolder = RecyclerView.getChildViewHolderInt(paramView);
      if (localViewHolder.isTmpDetached()) {
        removeDetachedView(paramView, false);
      }
      if (localViewHolder.isScrap()) {
        localViewHolder.unScrap();
      } else if (localViewHolder.wasReturnedFromScrap()) {
        localViewHolder.clearReturnedFromScrapFlag();
      }
      recycleViewHolderInternal(localViewHolder);
    }
    
    void recycleViewHolderInternal(RecyclerView.ViewHolder paramViewHolder)
    {
      boolean bool2 = paramViewHolder.isScrap();
      boolean bool1 = false;
      int k = 0;
      if ((!bool2) && (itemView.getParent() == null))
      {
        if (paramViewHolder.isTmpDetached())
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Tmp detached view should be removed from RecyclerView before it can be recycled: ");
          localStringBuilder.append(paramViewHolder);
          localStringBuilder.append(exceptionLabel());
          throw new IllegalArgumentException(localStringBuilder.toString());
        }
        if (paramViewHolder.shouldIgnore())
        {
          paramViewHolder = new StringBuilder();
          paramViewHolder.append("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.");
          paramViewHolder.append(exceptionLabel());
          throw new IllegalArgumentException(paramViewHolder.toString());
        }
        bool1 = RecyclerView.ViewHolder.access$900(paramViewHolder);
        int i;
        if ((mAdapter != null) && (bool1) && (mAdapter.onFailedToRecycleView(paramViewHolder))) {
          i = 1;
        } else {
          i = 0;
        }
        int j;
        if ((i == 0) && (!paramViewHolder.isRecyclable()))
        {
          i = 0;
          j = k;
          k = i;
        }
        else
        {
          if ((mViewCacheMax > 0) && (!paramViewHolder.hasAnyOfTheFlags(526)))
          {
            j = mCachedViews.size();
            i = j;
            if (j >= mViewCacheMax)
            {
              i = j;
              if (j > 0)
              {
                recycleCachedViewAt(0);
                i = j - 1;
              }
            }
            j = i;
            if (RecyclerView.ALLOW_THREAD_GAP_WORK)
            {
              j = i;
              if (i > 0)
              {
                j = i;
                if (!mPrefetchRegistry.lastPrefetchIncludedPosition(mPosition))
                {
                  i -= 1;
                  while (i >= 0)
                  {
                    j = mCachedViews.get(i)).mPosition;
                    if (!mPrefetchRegistry.lastPrefetchIncludedPosition(j)) {
                      break;
                    }
                    i -= 1;
                  }
                  j = i + 1;
                }
              }
            }
            mCachedViews.add(j, paramViewHolder);
            i = 1;
          }
          else
          {
            i = 0;
          }
          j = k;
          k = i;
          if (i == 0)
          {
            addViewHolderToRecycledViewPool(paramViewHolder, true);
            j = 1;
            k = i;
          }
        }
        mViewInfoStore.removeViewHolder(paramViewHolder);
        if ((k == 0) && (j == 0) && (bool1)) {
          mOwnerRecyclerView = null;
        }
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Scrapped or attached views may not be recycled. isScrap:");
      localStringBuilder.append(paramViewHolder.isScrap());
      localStringBuilder.append(" isAttached:");
      if (itemView.getParent() != null) {
        bool1 = true;
      }
      localStringBuilder.append(bool1);
      localStringBuilder.append(exceptionLabel());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    
    void recycleViewInternal(View paramView)
    {
      recycleViewHolderInternal(RecyclerView.getChildViewHolderInt(paramView));
    }
    
    void scrapView(View paramView)
    {
      paramView = RecyclerView.getChildViewHolderInt(paramView);
      if ((!paramView.hasAnyOfTheFlags(12)) && (paramView.isUpdated()) && (!canReuseUpdatedViewHolder(paramView)))
      {
        if (mChangedScrap == null) {
          mChangedScrap = new ArrayList();
        }
        paramView.setScrapContainer(this, true);
        mChangedScrap.add(paramView);
        return;
      }
      if ((paramView.isInvalid()) && (!paramView.isRemoved()) && (!mAdapter.hasStableIds()))
      {
        paramView = new StringBuilder();
        paramView.append("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
        paramView.append(exceptionLabel());
        throw new IllegalArgumentException(paramView.toString());
      }
      paramView.setScrapContainer(this, false);
      mAttachedScrap.add(paramView);
    }
    
    void setRecycledViewPool(RecyclerView.RecycledViewPool paramRecycledViewPool)
    {
      if (mRecyclerPool != null) {
        mRecyclerPool.detach();
      }
      mRecyclerPool = paramRecycledViewPool;
      if (paramRecycledViewPool != null) {
        mRecyclerPool.attach(getAdapter());
      }
    }
    
    void setViewCacheExtension(RecyclerView.ViewCacheExtension paramViewCacheExtension)
    {
      mViewCacheExtension = paramViewCacheExtension;
    }
    
    public void setViewCacheSize(int paramInt)
    {
      mRequestedCacheMax = paramInt;
      updateViewCacheSize();
    }
    
    @Nullable
    RecyclerView.ViewHolder tryGetViewHolderForPositionByDeadline(int paramInt, boolean paramBoolean, long paramLong)
    {
      if ((paramInt >= 0) && (paramInt < mState.getItemCount()))
      {
        boolean bool2 = mState.isPreLayout();
        boolean bool1 = true;
        if (bool2)
        {
          localObject2 = getChangedScrapViewForPosition(paramInt);
          localObject1 = localObject2;
          if (localObject2 != null)
          {
            j = 1;
            break label73;
          }
        }
        else
        {
          localObject1 = null;
        }
        int j = 0;
        Object localObject2 = localObject1;
        label73:
        localObject1 = localObject2;
        int i = j;
        if (localObject2 == null)
        {
          localObject2 = getScrapOrHiddenOrCachedHolderForPosition(paramInt, paramBoolean);
          localObject1 = localObject2;
          i = j;
          if (localObject2 != null) {
            if (!validateViewHolderForOffsetPosition((RecyclerView.ViewHolder)localObject2))
            {
              if (!paramBoolean)
              {
                ((RecyclerView.ViewHolder)localObject2).addFlags(4);
                if (((RecyclerView.ViewHolder)localObject2).isScrap())
                {
                  removeDetachedView(itemView, false);
                  ((RecyclerView.ViewHolder)localObject2).unScrap();
                }
                else if (((RecyclerView.ViewHolder)localObject2).wasReturnedFromScrap())
                {
                  ((RecyclerView.ViewHolder)localObject2).clearReturnedFromScrapFlag();
                }
                recycleViewHolderInternal((RecyclerView.ViewHolder)localObject2);
              }
              localObject1 = null;
              i = j;
            }
            else
            {
              i = 1;
              localObject1 = localObject2;
            }
          }
        }
        Object localObject3 = localObject1;
        int k = i;
        if (localObject1 == null)
        {
          k = mAdapterHelper.findPositionOffset(paramInt);
          if ((k >= 0) && (k < mAdapter.getItemCount()))
          {
            int m = mAdapter.getItemViewType(k);
            localObject2 = localObject1;
            j = i;
            if (mAdapter.hasStableIds())
            {
              localObject1 = getScrapOrCachedViewForId(mAdapter.getItemId(k), m, paramBoolean);
              localObject2 = localObject1;
              j = i;
              if (localObject1 != null)
              {
                mPosition = k;
                j = 1;
                localObject2 = localObject1;
              }
            }
            localObject1 = localObject2;
            if (localObject2 == null)
            {
              localObject1 = localObject2;
              if (mViewCacheExtension != null)
              {
                localObject3 = mViewCacheExtension.getViewForPositionAndType(this, paramInt, m);
                localObject1 = localObject2;
                if (localObject3 != null)
                {
                  localObject2 = getChildViewHolder((View)localObject3);
                  if (localObject2 == null)
                  {
                    localObject1 = new StringBuilder();
                    ((StringBuilder)localObject1).append("getViewForPositionAndType returned a view which does not have a ViewHolder");
                    ((StringBuilder)localObject1).append(exceptionLabel());
                    throw new IllegalArgumentException(((StringBuilder)localObject1).toString());
                  }
                  localObject1 = localObject2;
                  if (((RecyclerView.ViewHolder)localObject2).shouldIgnore())
                  {
                    localObject1 = new StringBuilder();
                    ((StringBuilder)localObject1).append("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view.");
                    ((StringBuilder)localObject1).append(exceptionLabel());
                    throw new IllegalArgumentException(((StringBuilder)localObject1).toString());
                  }
                }
              }
            }
            localObject2 = localObject1;
            if (localObject1 == null)
            {
              localObject1 = getRecycledViewPool().getRecycledView(m);
              localObject2 = localObject1;
              if (localObject1 != null)
              {
                ((RecyclerView.ViewHolder)localObject1).resetInternal();
                localObject2 = localObject1;
                if (RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST)
                {
                  invalidateDisplayListInt((RecyclerView.ViewHolder)localObject1);
                  localObject2 = localObject1;
                }
              }
            }
            localObject3 = localObject2;
            k = j;
            if (localObject2 == null)
            {
              long l1 = getNanoTime();
              if ((paramLong != Long.MAX_VALUE) && (!mRecyclerPool.willCreateInTime(m, l1, paramLong))) {
                return null;
              }
              localObject2 = mAdapter.createViewHolder(RecyclerView.this, m);
              if (RecyclerView.ALLOW_THREAD_GAP_WORK)
              {
                localObject1 = RecyclerView.findNestedRecyclerView(itemView);
                if (localObject1 != null) {
                  mNestedRecyclerView = new WeakReference(localObject1);
                }
              }
              long l2 = getNanoTime();
              mRecyclerPool.factorInCreateTime(m, l2 - l1);
              break label771;
            }
          }
          else
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("Inconsistency detected. Invalid item position ");
            ((StringBuilder)localObject1).append(paramInt);
            ((StringBuilder)localObject1).append("(offset:");
            ((StringBuilder)localObject1).append(k);
            ((StringBuilder)localObject1).append(").");
            ((StringBuilder)localObject1).append("state:");
            ((StringBuilder)localObject1).append(mState.getItemCount());
            ((StringBuilder)localObject1).append(exceptionLabel());
            throw new IndexOutOfBoundsException(((StringBuilder)localObject1).toString());
          }
        }
        localObject2 = localObject3;
        j = k;
        label771:
        if ((j != 0) && (!mState.isPreLayout()) && (((RecyclerView.ViewHolder)localObject2).hasAnyOfTheFlags(8192)))
        {
          ((RecyclerView.ViewHolder)localObject2).setFlags(0, 8192);
          if (mState.mRunSimpleAnimations)
          {
            i = RecyclerView.ItemAnimator.buildAdapterChangeFlagsForAnimations((RecyclerView.ViewHolder)localObject2);
            localObject1 = mItemAnimator.recordPreLayoutInformation(mState, (RecyclerView.ViewHolder)localObject2, i | 0x1000, ((RecyclerView.ViewHolder)localObject2).getUnmodifiedPayloads());
            recordAnimationInfoIfBouncedHiddenView((RecyclerView.ViewHolder)localObject2, (RecyclerView.ItemAnimator.ItemHolderInfo)localObject1);
          }
        }
        if ((mState.isPreLayout()) && (((RecyclerView.ViewHolder)localObject2).isBound())) {
          mPreLayoutPosition = paramInt;
        } else {
          if ((!((RecyclerView.ViewHolder)localObject2).isBound()) || (((RecyclerView.ViewHolder)localObject2).needsUpdate()) || (((RecyclerView.ViewHolder)localObject2).isInvalid())) {
            break label934;
          }
        }
        paramBoolean = false;
        break label954;
        label934:
        paramBoolean = tryBindViewHolderByDeadline((RecyclerView.ViewHolder)localObject2, mAdapterHelper.findPositionOffset(paramInt), paramInt, paramLong);
        label954:
        localObject1 = itemView.getLayoutParams();
        if (localObject1 == null)
        {
          localObject1 = (RecyclerView.LayoutParams)generateDefaultLayoutParams();
          itemView.setLayoutParams((ViewGroup.LayoutParams)localObject1);
        }
        else if (!checkLayoutParams((ViewGroup.LayoutParams)localObject1))
        {
          localObject1 = (RecyclerView.LayoutParams)generateLayoutParams((ViewGroup.LayoutParams)localObject1);
          itemView.setLayoutParams((ViewGroup.LayoutParams)localObject1);
        }
        else
        {
          localObject1 = (RecyclerView.LayoutParams)localObject1;
        }
        mViewHolder = ((RecyclerView.ViewHolder)localObject2);
        if ((j != 0) && (paramBoolean)) {
          paramBoolean = bool1;
        } else {
          paramBoolean = false;
        }
        mPendingInvalidate = paramBoolean;
        return localObject2;
      }
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Invalid item position ");
      ((StringBuilder)localObject1).append(paramInt);
      ((StringBuilder)localObject1).append("(");
      ((StringBuilder)localObject1).append(paramInt);
      ((StringBuilder)localObject1).append("). Item count:");
      ((StringBuilder)localObject1).append(mState.getItemCount());
      ((StringBuilder)localObject1).append(exceptionLabel());
      throw new IndexOutOfBoundsException(((StringBuilder)localObject1).toString());
    }
    
    void unscrapView(RecyclerView.ViewHolder paramViewHolder)
    {
      if (RecyclerView.ViewHolder.access$1100(paramViewHolder)) {
        mChangedScrap.remove(paramViewHolder);
      } else {
        mAttachedScrap.remove(paramViewHolder);
      }
      RecyclerView.ViewHolder.access$1002(paramViewHolder, null);
      RecyclerView.ViewHolder.access$1102(paramViewHolder, false);
      paramViewHolder.clearReturnedFromScrapFlag();
    }
    
    void updateViewCacheSize()
    {
      if (mLayout != null) {
        i = mLayout.mPrefetchMaxCountObserved;
      } else {
        i = 0;
      }
      mViewCacheMax = (mRequestedCacheMax + i);
      int i = mCachedViews.size() - 1;
      while ((i >= 0) && (mCachedViews.size() > mViewCacheMax))
      {
        recycleCachedViewAt(i);
        i -= 1;
      }
    }
    
    boolean validateViewHolderForOffsetPosition(RecyclerView.ViewHolder paramViewHolder)
    {
      if (paramViewHolder.isRemoved()) {
        return mState.isPreLayout();
      }
      if ((mPosition >= 0) && (mPosition < mAdapter.getItemCount()))
      {
        boolean bool2 = mState.isPreLayout();
        boolean bool1 = false;
        if ((!bool2) && (mAdapter.getItemViewType(mPosition) != paramViewHolder.getItemViewType())) {
          return false;
        }
        if (mAdapter.hasStableIds())
        {
          if (paramViewHolder.getItemId() == mAdapter.getItemId(mPosition)) {
            bool1 = true;
          }
          return bool1;
        }
        return true;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Inconsistency detected. Invalid view holder adapter position");
      localStringBuilder.append(paramViewHolder);
      localStringBuilder.append(exceptionLabel());
      throw new IndexOutOfBoundsException(localStringBuilder.toString());
    }
    
    void viewRangeUpdate(int paramInt1, int paramInt2)
    {
      int i = mCachedViews.size() - 1;
      while (i >= 0)
      {
        RecyclerView.ViewHolder localViewHolder = (RecyclerView.ViewHolder)mCachedViews.get(i);
        if (localViewHolder != null)
        {
          int j = mPosition;
          if ((j >= paramInt1) && (j < paramInt2 + paramInt1))
          {
            localViewHolder.addFlags(2);
            recycleCachedViewAt(i);
          }
        }
        i -= 1;
      }
    }
  }
  
  public static abstract interface RecyclerListener
  {
    public abstract void onViewRecycled(RecyclerView.ViewHolder paramViewHolder);
  }
  
  private class RecyclerViewDataObserver
    extends RecyclerView.AdapterDataObserver
  {
    RecyclerViewDataObserver() {}
    
    public void onChanged()
    {
      assertNotInLayoutOrScroll(null);
      mState.mStructureChanged = true;
      processDataSetCompletelyChanged(true);
      if (!mAdapterHelper.hasPendingUpdates()) {
        requestLayout();
      }
    }
    
    public void onItemRangeChanged(int paramInt1, int paramInt2, Object paramObject)
    {
      assertNotInLayoutOrScroll(null);
      if (mAdapterHelper.onItemRangeChanged(paramInt1, paramInt2, paramObject)) {
        triggerUpdateProcessor();
      }
    }
    
    public void onItemRangeInserted(int paramInt1, int paramInt2)
    {
      assertNotInLayoutOrScroll(null);
      if (mAdapterHelper.onItemRangeInserted(paramInt1, paramInt2)) {
        triggerUpdateProcessor();
      }
    }
    
    public void onItemRangeMoved(int paramInt1, int paramInt2, int paramInt3)
    {
      assertNotInLayoutOrScroll(null);
      if (mAdapterHelper.onItemRangeMoved(paramInt1, paramInt2, paramInt3)) {
        triggerUpdateProcessor();
      }
    }
    
    public void onItemRangeRemoved(int paramInt1, int paramInt2)
    {
      assertNotInLayoutOrScroll(null);
      if (mAdapterHelper.onItemRangeRemoved(paramInt1, paramInt2)) {
        triggerUpdateProcessor();
      }
    }
    
    void triggerUpdateProcessor()
    {
      if ((RecyclerView.POST_UPDATES_ON_ANIMATION) && (mHasFixedSize) && (mIsAttached))
      {
        ViewCompat.postOnAnimation(RecyclerView.this, mUpdateChildViewsRunnable);
        return;
      }
      mAdapterUpdateDuringMeasure = true;
      requestLayout();
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static class SavedState
    extends AbsSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator()
    {
      public RecyclerView.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new RecyclerView.SavedState(paramAnonymousParcel, null);
      }
      
      public RecyclerView.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
      {
        return new RecyclerView.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
      }
      
      public RecyclerView.SavedState[] newArray(int paramAnonymousInt)
      {
        return new RecyclerView.SavedState[paramAnonymousInt];
      }
    };
    Parcelable mLayoutState;
    
    SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      if (paramClassLoader == null) {
        paramClassLoader = RecyclerView.LayoutManager.class.getClassLoader();
      }
      mLayoutState = paramParcel.readParcelable(paramClassLoader);
    }
    
    SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    void copyFrom(SavedState paramSavedState)
    {
      mLayoutState = mLayoutState;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeParcelable(mLayoutState, 0);
    }
  }
  
  public static class SimpleOnItemTouchListener
    implements RecyclerView.OnItemTouchListener
  {
    public SimpleOnItemTouchListener() {}
    
    public boolean onInterceptTouchEvent(RecyclerView paramRecyclerView, MotionEvent paramMotionEvent)
    {
      return false;
    }
    
    public void onRequestDisallowInterceptTouchEvent(boolean paramBoolean) {}
    
    public void onTouchEvent(RecyclerView paramRecyclerView, MotionEvent paramMotionEvent) {}
  }
  
  public static abstract class SmoothScroller
  {
    private RecyclerView.LayoutManager mLayoutManager;
    private boolean mPendingInitialRun;
    private RecyclerView mRecyclerView;
    private final Action mRecyclingAction = new Action(0, 0);
    private boolean mRunning;
    private int mTargetPosition = -1;
    private View mTargetView;
    
    public SmoothScroller() {}
    
    private void onAnimation(int paramInt1, int paramInt2)
    {
      RecyclerView localRecyclerView = mRecyclerView;
      if ((!mRunning) || (mTargetPosition == -1) || (localRecyclerView == null)) {
        stop();
      }
      mPendingInitialRun = false;
      if (mTargetView != null) {
        if (getChildPosition(mTargetView) == mTargetPosition)
        {
          onTargetFound(mTargetView, mState, mRecyclingAction);
          mRecyclingAction.runIfNecessary(localRecyclerView);
          stop();
        }
        else
        {
          Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
          mTargetView = null;
        }
      }
      if (mRunning)
      {
        onSeekTargetStep(paramInt1, paramInt2, mState, mRecyclingAction);
        boolean bool = mRecyclingAction.hasJumpTarget();
        mRecyclingAction.runIfNecessary(localRecyclerView);
        if (bool)
        {
          if (mRunning)
          {
            mPendingInitialRun = true;
            mViewFlinger.postOnAnimation();
            return;
          }
          stop();
        }
      }
    }
    
    public View findViewByPosition(int paramInt)
    {
      return mRecyclerView.mLayout.findViewByPosition(paramInt);
    }
    
    public int getChildCount()
    {
      return mRecyclerView.mLayout.getChildCount();
    }
    
    public int getChildPosition(View paramView)
    {
      return mRecyclerView.getChildLayoutPosition(paramView);
    }
    
    @Nullable
    public RecyclerView.LayoutManager getLayoutManager()
    {
      return mLayoutManager;
    }
    
    public int getTargetPosition()
    {
      return mTargetPosition;
    }
    
    @Deprecated
    public void instantScrollToPosition(int paramInt)
    {
      mRecyclerView.scrollToPosition(paramInt);
    }
    
    public boolean isPendingInitialRun()
    {
      return mPendingInitialRun;
    }
    
    public boolean isRunning()
    {
      return mRunning;
    }
    
    protected void normalize(PointF paramPointF)
    {
      float f = (float)Math.sqrt(x * x + y * y);
      x /= f;
      y /= f;
    }
    
    protected void onChildAttachedToWindow(View paramView)
    {
      if (getChildPosition(paramView) == getTargetPosition()) {
        mTargetView = paramView;
      }
    }
    
    protected abstract void onSeekTargetStep(int paramInt1, int paramInt2, RecyclerView.State paramState, Action paramAction);
    
    protected abstract void onStart();
    
    protected abstract void onStop();
    
    protected abstract void onTargetFound(View paramView, RecyclerView.State paramState, Action paramAction);
    
    public void setTargetPosition(int paramInt)
    {
      mTargetPosition = paramInt;
    }
    
    void start(RecyclerView paramRecyclerView, RecyclerView.LayoutManager paramLayoutManager)
    {
      mRecyclerView = paramRecyclerView;
      mLayoutManager = paramLayoutManager;
      if (mTargetPosition == -1) {
        throw new IllegalArgumentException("Invalid target position");
      }
      RecyclerView.State.access$1302(mRecyclerView.mState, mTargetPosition);
      mRunning = true;
      mPendingInitialRun = true;
      mTargetView = findViewByPosition(getTargetPosition());
      onStart();
      mRecyclerView.mViewFlinger.postOnAnimation();
    }
    
    protected final void stop()
    {
      if (!mRunning) {
        return;
      }
      onStop();
      RecyclerView.State.access$1302(mRecyclerView.mState, -1);
      mTargetView = null;
      mTargetPosition = -1;
      mPendingInitialRun = false;
      mRunning = false;
      mLayoutManager.onSmoothScrollerStopped(this);
      mLayoutManager = null;
      mRecyclerView = null;
    }
    
    public static class Action
    {
      public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
      private boolean mChanged = false;
      private int mConsecutiveUpdates = 0;
      private int mDuration;
      private int mDx;
      private int mDy;
      private Interpolator mInterpolator;
      private int mJumpToPosition = -1;
      
      public Action(int paramInt1, int paramInt2)
      {
        this(paramInt1, paramInt2, Integer.MIN_VALUE, null);
      }
      
      public Action(int paramInt1, int paramInt2, int paramInt3)
      {
        this(paramInt1, paramInt2, paramInt3, null);
      }
      
      public Action(int paramInt1, int paramInt2, int paramInt3, Interpolator paramInterpolator)
      {
        mDx = paramInt1;
        mDy = paramInt2;
        mDuration = paramInt3;
        mInterpolator = paramInterpolator;
      }
      
      private void validate()
      {
        if ((mInterpolator != null) && (mDuration < 1)) {
          throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
        }
        if (mDuration < 1) {
          throw new IllegalStateException("Scroll duration must be a positive number");
        }
      }
      
      public int getDuration()
      {
        return mDuration;
      }
      
      public int getDx()
      {
        return mDx;
      }
      
      public int getDy()
      {
        return mDy;
      }
      
      public Interpolator getInterpolator()
      {
        return mInterpolator;
      }
      
      boolean hasJumpTarget()
      {
        return mJumpToPosition >= 0;
      }
      
      public void jumpTo(int paramInt)
      {
        mJumpToPosition = paramInt;
      }
      
      void runIfNecessary(RecyclerView paramRecyclerView)
      {
        if (mJumpToPosition >= 0)
        {
          int i = mJumpToPosition;
          mJumpToPosition = -1;
          paramRecyclerView.jumpToPositionForSmoothScroller(i);
          mChanged = false;
          return;
        }
        if (mChanged)
        {
          validate();
          if (mInterpolator == null)
          {
            if (mDuration == Integer.MIN_VALUE) {
              mViewFlinger.smoothScrollBy(mDx, mDy);
            } else {
              mViewFlinger.smoothScrollBy(mDx, mDy, mDuration);
            }
          }
          else {
            mViewFlinger.smoothScrollBy(mDx, mDy, mDuration, mInterpolator);
          }
          mConsecutiveUpdates += 1;
          if (mConsecutiveUpdates > 10) {
            Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
          }
          mChanged = false;
          return;
        }
        mConsecutiveUpdates = 0;
      }
      
      public void setDuration(int paramInt)
      {
        mChanged = true;
        mDuration = paramInt;
      }
      
      public void setDx(int paramInt)
      {
        mChanged = true;
        mDx = paramInt;
      }
      
      public void setDy(int paramInt)
      {
        mChanged = true;
        mDy = paramInt;
      }
      
      public void setInterpolator(Interpolator paramInterpolator)
      {
        mChanged = true;
        mInterpolator = paramInterpolator;
      }
      
      public void update(int paramInt1, int paramInt2, int paramInt3, Interpolator paramInterpolator)
      {
        mDx = paramInt1;
        mDy = paramInt2;
        mDuration = paramInt3;
        mInterpolator = paramInterpolator;
        mChanged = true;
      }
    }
    
    public static abstract interface ScrollVectorProvider
    {
      public abstract PointF computeScrollVectorForPosition(int paramInt);
    }
  }
  
  public static class State
  {
    static final int STEP_ANIMATIONS = 4;
    static final int STEP_LAYOUT = 2;
    static final int STEP_START = 1;
    private SparseArray<Object> mData;
    int mDeletedInvisibleItemCountSincePreviousLayout = 0;
    long mFocusedItemId;
    int mFocusedItemPosition;
    int mFocusedSubChildId;
    boolean mInPreLayout = false;
    boolean mIsMeasuring = false;
    int mItemCount = 0;
    int mLayoutStep = 1;
    int mPreviousLayoutItemCount = 0;
    int mRemainingScrollHorizontal;
    int mRemainingScrollVertical;
    boolean mRunPredictiveAnimations = false;
    boolean mRunSimpleAnimations = false;
    boolean mStructureChanged = false;
    private int mTargetPosition = -1;
    boolean mTrackOldChangeHolders = false;
    
    public State() {}
    
    void assertLayoutStep(int paramInt)
    {
      if ((mLayoutStep & paramInt) == 0)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Layout state should be one of ");
        localStringBuilder.append(Integer.toBinaryString(paramInt));
        localStringBuilder.append(" but it is ");
        localStringBuilder.append(Integer.toBinaryString(mLayoutStep));
        throw new IllegalStateException(localStringBuilder.toString());
      }
    }
    
    public boolean didStructureChange()
    {
      return mStructureChanged;
    }
    
    public <T> T get(int paramInt)
    {
      if (mData == null) {
        return null;
      }
      return mData.get(paramInt);
    }
    
    public int getItemCount()
    {
      if (mInPreLayout) {
        return mPreviousLayoutItemCount - mDeletedInvisibleItemCountSincePreviousLayout;
      }
      return mItemCount;
    }
    
    public int getRemainingScrollHorizontal()
    {
      return mRemainingScrollHorizontal;
    }
    
    public int getRemainingScrollVertical()
    {
      return mRemainingScrollVertical;
    }
    
    public int getTargetScrollPosition()
    {
      return mTargetPosition;
    }
    
    public boolean hasTargetScrollPosition()
    {
      return mTargetPosition != -1;
    }
    
    public boolean isMeasuring()
    {
      return mIsMeasuring;
    }
    
    public boolean isPreLayout()
    {
      return mInPreLayout;
    }
    
    void prepareForNestedPrefetch(RecyclerView.Adapter paramAdapter)
    {
      mLayoutStep = 1;
      mItemCount = paramAdapter.getItemCount();
      mInPreLayout = false;
      mTrackOldChangeHolders = false;
      mIsMeasuring = false;
    }
    
    public void put(int paramInt, Object paramObject)
    {
      if (mData == null) {
        mData = new SparseArray();
      }
      mData.put(paramInt, paramObject);
    }
    
    public void remove(int paramInt)
    {
      if (mData == null) {
        return;
      }
      mData.remove(paramInt);
    }
    
    State reset()
    {
      mTargetPosition = -1;
      if (mData != null) {
        mData.clear();
      }
      mItemCount = 0;
      mStructureChanged = false;
      mIsMeasuring = false;
      return this;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("State{mTargetPosition=");
      localStringBuilder.append(mTargetPosition);
      localStringBuilder.append(", mData=");
      localStringBuilder.append(mData);
      localStringBuilder.append(", mItemCount=");
      localStringBuilder.append(mItemCount);
      localStringBuilder.append(", mIsMeasuring=");
      localStringBuilder.append(mIsMeasuring);
      localStringBuilder.append(", mPreviousLayoutItemCount=");
      localStringBuilder.append(mPreviousLayoutItemCount);
      localStringBuilder.append(", mDeletedInvisibleItemCountSincePreviousLayout=");
      localStringBuilder.append(mDeletedInvisibleItemCountSincePreviousLayout);
      localStringBuilder.append(", mStructureChanged=");
      localStringBuilder.append(mStructureChanged);
      localStringBuilder.append(", mInPreLayout=");
      localStringBuilder.append(mInPreLayout);
      localStringBuilder.append(", mRunSimpleAnimations=");
      localStringBuilder.append(mRunSimpleAnimations);
      localStringBuilder.append(", mRunPredictiveAnimations=");
      localStringBuilder.append(mRunPredictiveAnimations);
      localStringBuilder.append('}');
      return localStringBuilder.toString();
    }
    
    public boolean willRunPredictiveAnimations()
    {
      return mRunPredictiveAnimations;
    }
    
    public boolean willRunSimpleAnimations()
    {
      return mRunSimpleAnimations;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    static @interface LayoutState {}
  }
  
  public static abstract class ViewCacheExtension
  {
    public ViewCacheExtension() {}
    
    public abstract View getViewForPositionAndType(RecyclerView.Recycler paramRecycler, int paramInt1, int paramInt2);
  }
  
  class ViewFlinger
    implements Runnable
  {
    private boolean mEatRunOnAnimationRequest = false;
    Interpolator mInterpolator = RecyclerView.sQuinticInterpolator;
    private int mLastFlingX;
    private int mLastFlingY;
    private boolean mReSchedulePostAnimationCallback = false;
    private OverScroller mScroller = new OverScroller(getContext(), RecyclerView.sQuinticInterpolator);
    
    ViewFlinger() {}
    
    private int computeScrollDuration(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      int j = Math.abs(paramInt1);
      int k = Math.abs(paramInt2);
      int i;
      if (j > k) {
        i = 1;
      } else {
        i = 0;
      }
      paramInt3 = (int)Math.sqrt(paramInt3 * paramInt3 + paramInt4 * paramInt4);
      paramInt2 = (int)Math.sqrt(paramInt1 * paramInt1 + paramInt2 * paramInt2);
      if (i != 0) {
        paramInt1 = getWidth();
      } else {
        paramInt1 = getHeight();
      }
      paramInt4 = paramInt1 / 2;
      float f2 = paramInt2;
      float f1 = paramInt1;
      float f3 = Math.min(1.0F, f2 * 1.0F / f1);
      f2 = paramInt4;
      f3 = distanceInfluenceForSnapDuration(f3);
      if (paramInt3 > 0)
      {
        paramInt1 = 4 * Math.round(1000.0F * Math.abs((f2 + f3 * f2) / paramInt3));
      }
      else
      {
        if (i != 0) {
          paramInt1 = j;
        } else {
          paramInt1 = k;
        }
        paramInt1 = (int)((paramInt1 / f1 + 1.0F) * 300.0F);
      }
      return Math.min(paramInt1, 2000);
    }
    
    private void disableRunOnAnimationRequests()
    {
      mReSchedulePostAnimationCallback = false;
      mEatRunOnAnimationRequest = true;
    }
    
    private float distanceInfluenceForSnapDuration(float paramFloat)
    {
      return (float)Math.sin((paramFloat - 0.5F) * 0.47123894F);
    }
    
    private void enableRunOnAnimationRequests()
    {
      mEatRunOnAnimationRequest = false;
      if (mReSchedulePostAnimationCallback) {
        postOnAnimation();
      }
    }
    
    public void fling(int paramInt1, int paramInt2)
    {
      setScrollState(2);
      mLastFlingY = 0;
      mLastFlingX = 0;
      mScroller.fling(0, 0, paramInt1, paramInt2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
      postOnAnimation();
    }
    
    void postOnAnimation()
    {
      if (mEatRunOnAnimationRequest)
      {
        mReSchedulePostAnimationCallback = true;
        return;
      }
      removeCallbacks(this);
      ViewCompat.postOnAnimation(RecyclerView.this, this);
    }
    
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   4: getfield 128	android/support/v7/widget/RecyclerView:mLayout	Landroid/support/v7/widget/RecyclerView$LayoutManager;
      //   7: ifnonnull +8 -> 15
      //   10: aload_0
      //   11: invokevirtual 131	android/support/v7/widget/RecyclerView$ViewFlinger:stop	()V
      //   14: return
      //   15: aload_0
      //   16: invokespecial 133	android/support/v7/widget/RecyclerView$ViewFlinger:disableRunOnAnimationRequests	()V
      //   19: aload_0
      //   20: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   23: invokevirtual 136	android/support/v7/widget/RecyclerView:consumePendingUpdateOperations	()V
      //   26: aload_0
      //   27: getfield 48	android/support/v7/widget/RecyclerView$ViewFlinger:mScroller	Landroid/widget/OverScroller;
      //   30: astore 13
      //   32: aload_0
      //   33: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   36: getfield 128	android/support/v7/widget/RecyclerView:mLayout	Landroid/support/v7/widget/RecyclerView$LayoutManager;
      //   39: getfield 142	android/support/v7/widget/RecyclerView$LayoutManager:mSmoothScroller	Landroid/support/v7/widget/RecyclerView$SmoothScroller;
      //   42: astore 14
      //   44: aload 13
      //   46: invokevirtual 146	android/widget/OverScroller:computeScrollOffset	()Z
      //   49: ifeq +867 -> 916
      //   52: aload_0
      //   53: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   56: invokestatic 150	android/support/v7/widget/RecyclerView:access$500	(Landroid/support/v7/widget/RecyclerView;)[I
      //   59: astore 15
      //   61: aload 13
      //   63: invokevirtual 153	android/widget/OverScroller:getCurrX	()I
      //   66: istore 11
      //   68: aload 13
      //   70: invokevirtual 156	android/widget/OverScroller:getCurrY	()I
      //   73: istore 12
      //   75: iload 11
      //   77: aload_0
      //   78: getfield 109	android/support/v7/widget/RecyclerView$ViewFlinger:mLastFlingX	I
      //   81: isub
      //   82: istore_2
      //   83: iload 12
      //   85: aload_0
      //   86: getfield 107	android/support/v7/widget/RecyclerView$ViewFlinger:mLastFlingY	I
      //   89: isub
      //   90: istore_1
      //   91: aload_0
      //   92: iload 11
      //   94: putfield 109	android/support/v7/widget/RecyclerView$ViewFlinger:mLastFlingX	I
      //   97: aload_0
      //   98: iload 12
      //   100: putfield 107	android/support/v7/widget/RecyclerView$ViewFlinger:mLastFlingY	I
      //   103: iload_2
      //   104: istore 6
      //   106: iload_1
      //   107: istore 5
      //   109: aload_0
      //   110: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   113: iload_2
      //   114: iload_1
      //   115: aload 15
      //   117: aconst_null
      //   118: iconst_1
      //   119: invokevirtual 160	android/support/v7/widget/RecyclerView:dispatchNestedPreScroll	(II[I[II)Z
      //   122: ifeq +19 -> 141
      //   125: iload_2
      //   126: aload 15
      //   128: iconst_0
      //   129: iaload
      //   130: isub
      //   131: istore 6
      //   133: iload_1
      //   134: aload 15
      //   136: iconst_1
      //   137: iaload
      //   138: isub
      //   139: istore 5
      //   141: aload_0
      //   142: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   145: getfield 164	android/support/v7/widget/RecyclerView:mAdapter	Landroid/support/v7/widget/RecyclerView$Adapter;
      //   148: ifnull +328 -> 476
      //   151: aload_0
      //   152: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   155: invokevirtual 167	android/support/v7/widget/RecyclerView:startInterceptRequestLayout	()V
      //   158: aload_0
      //   159: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   162: invokevirtual 170	android/support/v7/widget/RecyclerView:onEnterLayoutOrScroll	()V
      //   165: ldc -84
      //   167: invokestatic 178	android/support/v4/os/TraceCompat:beginSection	(Ljava/lang/String;)V
      //   170: aload_0
      //   171: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   174: aload_0
      //   175: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   178: getfield 182	android/support/v7/widget/RecyclerView:mState	Landroid/support/v7/widget/RecyclerView$State;
      //   181: invokevirtual 186	android/support/v7/widget/RecyclerView:fillRemainingScrollValues	(Landroid/support/v7/widget/RecyclerView$State;)V
      //   184: iload 6
      //   186: ifeq +38 -> 224
      //   189: aload_0
      //   190: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   193: getfield 128	android/support/v7/widget/RecyclerView:mLayout	Landroid/support/v7/widget/RecyclerView$LayoutManager;
      //   196: iload 6
      //   198: aload_0
      //   199: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   202: getfield 190	android/support/v7/widget/RecyclerView:mRecycler	Landroid/support/v7/widget/RecyclerView$Recycler;
      //   205: aload_0
      //   206: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   209: getfield 182	android/support/v7/widget/RecyclerView:mState	Landroid/support/v7/widget/RecyclerView$State;
      //   212: invokevirtual 194	android/support/v7/widget/RecyclerView$LayoutManager:scrollHorizontallyBy	(ILandroid/support/v7/widget/RecyclerView$Recycler;Landroid/support/v7/widget/RecyclerView$State;)I
      //   215: istore_1
      //   216: iload 6
      //   218: iload_1
      //   219: isub
      //   220: istore_2
      //   221: goto +7 -> 228
      //   224: iconst_0
      //   225: istore_1
      //   226: iload_1
      //   227: istore_2
      //   228: iload 5
      //   230: ifeq +39 -> 269
      //   233: aload_0
      //   234: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   237: getfield 128	android/support/v7/widget/RecyclerView:mLayout	Landroid/support/v7/widget/RecyclerView$LayoutManager;
      //   240: iload 5
      //   242: aload_0
      //   243: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   246: getfield 190	android/support/v7/widget/RecyclerView:mRecycler	Landroid/support/v7/widget/RecyclerView$Recycler;
      //   249: aload_0
      //   250: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   253: getfield 182	android/support/v7/widget/RecyclerView:mState	Landroid/support/v7/widget/RecyclerView$State;
      //   256: invokevirtual 197	android/support/v7/widget/RecyclerView$LayoutManager:scrollVerticallyBy	(ILandroid/support/v7/widget/RecyclerView$Recycler;Landroid/support/v7/widget/RecyclerView$State;)I
      //   259: istore_3
      //   260: iload 5
      //   262: iload_3
      //   263: isub
      //   264: istore 4
      //   266: goto +8 -> 274
      //   269: iconst_0
      //   270: istore_3
      //   271: iload_3
      //   272: istore 4
      //   274: invokestatic 200	android/support/v4/os/TraceCompat:endSection	()V
      //   277: aload_0
      //   278: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   281: invokevirtual 203	android/support/v7/widget/RecyclerView:repositionShadowingViews	()V
      //   284: aload_0
      //   285: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   288: invokevirtual 206	android/support/v7/widget/RecyclerView:onExitLayoutOrScroll	()V
      //   291: aload_0
      //   292: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   295: iconst_0
      //   296: invokevirtual 210	android/support/v7/widget/RecyclerView:stopInterceptRequestLayout	(Z)V
      //   299: iload_1
      //   300: istore 7
      //   302: iload_2
      //   303: istore 10
      //   305: iload_3
      //   306: istore 9
      //   308: iload 4
      //   310: istore 8
      //   312: aload 14
      //   314: ifnull +179 -> 493
      //   317: iload_1
      //   318: istore 7
      //   320: iload_2
      //   321: istore 10
      //   323: iload_3
      //   324: istore 9
      //   326: iload 4
      //   328: istore 8
      //   330: aload 14
      //   332: invokevirtual 215	android/support/v7/widget/RecyclerView$SmoothScroller:isPendingInitialRun	()Z
      //   335: ifne +158 -> 493
      //   338: iload_1
      //   339: istore 7
      //   341: iload_2
      //   342: istore 10
      //   344: iload_3
      //   345: istore 9
      //   347: iload 4
      //   349: istore 8
      //   351: aload 14
      //   353: invokevirtual 218	android/support/v7/widget/RecyclerView$SmoothScroller:isRunning	()Z
      //   356: ifeq +137 -> 493
      //   359: aload_0
      //   360: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   363: getfield 182	android/support/v7/widget/RecyclerView:mState	Landroid/support/v7/widget/RecyclerView$State;
      //   366: invokevirtual 223	android/support/v7/widget/RecyclerView$State:getItemCount	()I
      //   369: istore 7
      //   371: iload 7
      //   373: ifne +24 -> 397
      //   376: aload 14
      //   378: invokevirtual 224	android/support/v7/widget/RecyclerView$SmoothScroller:stop	()V
      //   381: iload_1
      //   382: istore 7
      //   384: iload_2
      //   385: istore 10
      //   387: iload_3
      //   388: istore 9
      //   390: iload 4
      //   392: istore 8
      //   394: goto +99 -> 493
      //   397: aload 14
      //   399: invokevirtual 227	android/support/v7/widget/RecyclerView$SmoothScroller:getTargetPosition	()I
      //   402: iload 7
      //   404: if_icmplt +42 -> 446
      //   407: aload 14
      //   409: iload 7
      //   411: iconst_1
      //   412: isub
      //   413: invokevirtual 230	android/support/v7/widget/RecyclerView$SmoothScroller:setTargetPosition	(I)V
      //   416: aload 14
      //   418: iload 6
      //   420: iload_2
      //   421: isub
      //   422: iload 5
      //   424: iload 4
      //   426: isub
      //   427: invokestatic 234	android/support/v7/widget/RecyclerView$SmoothScroller:access$600	(Landroid/support/v7/widget/RecyclerView$SmoothScroller;II)V
      //   430: iload_1
      //   431: istore 7
      //   433: iload_2
      //   434: istore 10
      //   436: iload_3
      //   437: istore 9
      //   439: iload 4
      //   441: istore 8
      //   443: goto +50 -> 493
      //   446: aload 14
      //   448: iload 6
      //   450: iload_2
      //   451: isub
      //   452: iload 5
      //   454: iload 4
      //   456: isub
      //   457: invokestatic 234	android/support/v7/widget/RecyclerView$SmoothScroller:access$600	(Landroid/support/v7/widget/RecyclerView$SmoothScroller;II)V
      //   460: iload_1
      //   461: istore 7
      //   463: iload_2
      //   464: istore 10
      //   466: iload_3
      //   467: istore 9
      //   469: iload 4
      //   471: istore 8
      //   473: goto +20 -> 493
      //   476: iconst_0
      //   477: istore 7
      //   479: iload 7
      //   481: istore_1
      //   482: iload_1
      //   483: istore_2
      //   484: iload_2
      //   485: istore 8
      //   487: iload_2
      //   488: istore 9
      //   490: iload_1
      //   491: istore 10
      //   493: aload_0
      //   494: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   497: getfield 238	android/support/v7/widget/RecyclerView:mItemDecorations	Ljava/util/ArrayList;
      //   500: invokevirtual 243	java/util/ArrayList:isEmpty	()Z
      //   503: ifne +10 -> 513
      //   506: aload_0
      //   507: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   510: invokevirtual 246	android/support/v7/widget/RecyclerView:invalidate	()V
      //   513: aload_0
      //   514: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   517: invokevirtual 249	android/support/v7/widget/RecyclerView:getOverScrollMode	()I
      //   520: iconst_2
      //   521: if_icmpeq +14 -> 535
      //   524: aload_0
      //   525: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   528: iload 6
      //   530: iload 5
      //   532: invokevirtual 252	android/support/v7/widget/RecyclerView:considerReleasingGlowsOnScroll	(II)V
      //   535: aload_0
      //   536: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   539: iload 7
      //   541: iload 9
      //   543: iload 10
      //   545: iload 8
      //   547: aconst_null
      //   548: iconst_1
      //   549: invokevirtual 256	android/support/v7/widget/RecyclerView:dispatchNestedScroll	(IIII[II)Z
      //   552: ifne +141 -> 693
      //   555: iload 10
      //   557: ifne +8 -> 565
      //   560: iload 8
      //   562: ifeq +131 -> 693
      //   565: aload 13
      //   567: invokevirtual 260	android/widget/OverScroller:getCurrVelocity	()F
      //   570: f2i
      //   571: istore_2
      //   572: iload 10
      //   574: iload 11
      //   576: if_icmpeq +24 -> 600
      //   579: iload 10
      //   581: ifge +9 -> 590
      //   584: iload_2
      //   585: ineg
      //   586: istore_1
      //   587: goto +15 -> 602
      //   590: iload 10
      //   592: ifle +8 -> 600
      //   595: iload_2
      //   596: istore_1
      //   597: goto +5 -> 602
      //   600: iconst_0
      //   601: istore_1
      //   602: iload 8
      //   604: iload 12
      //   606: if_icmpeq +22 -> 628
      //   609: iload 8
      //   611: ifge +9 -> 620
      //   614: iload_2
      //   615: ineg
      //   616: istore_2
      //   617: goto +13 -> 630
      //   620: iload 8
      //   622: ifle +6 -> 628
      //   625: goto +5 -> 630
      //   628: iconst_0
      //   629: istore_2
      //   630: aload_0
      //   631: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   634: invokevirtual 249	android/support/v7/widget/RecyclerView:getOverScrollMode	()I
      //   637: iconst_2
      //   638: if_icmpeq +12 -> 650
      //   641: aload_0
      //   642: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   645: iload_1
      //   646: iload_2
      //   647: invokevirtual 263	android/support/v7/widget/RecyclerView:absorbGlows	(II)V
      //   650: iload_1
      //   651: ifne +18 -> 669
      //   654: iload 10
      //   656: iload 11
      //   658: if_icmpeq +11 -> 669
      //   661: aload 13
      //   663: invokevirtual 266	android/widget/OverScroller:getFinalX	()I
      //   666: ifne +27 -> 693
      //   669: iload_2
      //   670: ifne +18 -> 688
      //   673: iload 8
      //   675: iload 12
      //   677: if_icmpeq +11 -> 688
      //   680: aload 13
      //   682: invokevirtual 269	android/widget/OverScroller:getFinalY	()I
      //   685: ifne +8 -> 693
      //   688: aload 13
      //   690: invokevirtual 272	android/widget/OverScroller:abortAnimation	()V
      //   693: iload 7
      //   695: ifne +8 -> 703
      //   698: iload 9
      //   700: ifeq +14 -> 714
      //   703: aload_0
      //   704: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   707: iload 7
      //   709: iload 9
      //   711: invokevirtual 275	android/support/v7/widget/RecyclerView:dispatchOnScrolled	(II)V
      //   714: aload_0
      //   715: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   718: invokestatic 279	android/support/v7/widget/RecyclerView:access$700	(Landroid/support/v7/widget/RecyclerView;)Z
      //   721: ifne +10 -> 731
      //   724: aload_0
      //   725: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   728: invokevirtual 246	android/support/v7/widget/RecyclerView:invalidate	()V
      //   731: iload 5
      //   733: ifeq +28 -> 761
      //   736: aload_0
      //   737: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   740: getfield 128	android/support/v7/widget/RecyclerView:mLayout	Landroid/support/v7/widget/RecyclerView$LayoutManager;
      //   743: invokevirtual 282	android/support/v7/widget/RecyclerView$LayoutManager:canScrollVertically	()Z
      //   746: ifeq +15 -> 761
      //   749: iload 9
      //   751: iload 5
      //   753: if_icmpne +8 -> 761
      //   756: iconst_1
      //   757: istore_1
      //   758: goto +5 -> 763
      //   761: iconst_0
      //   762: istore_1
      //   763: iload 6
      //   765: ifeq +28 -> 793
      //   768: aload_0
      //   769: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   772: getfield 128	android/support/v7/widget/RecyclerView:mLayout	Landroid/support/v7/widget/RecyclerView$LayoutManager;
      //   775: invokevirtual 285	android/support/v7/widget/RecyclerView$LayoutManager:canScrollHorizontally	()Z
      //   778: ifeq +15 -> 793
      //   781: iload 7
      //   783: iload 6
      //   785: if_icmpne +8 -> 793
      //   788: iconst_1
      //   789: istore_2
      //   790: goto +5 -> 795
      //   793: iconst_0
      //   794: istore_2
      //   795: iload 6
      //   797: ifne +8 -> 805
      //   800: iload 5
      //   802: ifeq +19 -> 821
      //   805: iload_2
      //   806: ifne +15 -> 821
      //   809: iload_1
      //   810: ifeq +6 -> 816
      //   813: goto +8 -> 821
      //   816: iconst_0
      //   817: istore_1
      //   818: goto +5 -> 823
      //   821: iconst_1
      //   822: istore_1
      //   823: aload 13
      //   825: invokevirtual 288	android/widget/OverScroller:isFinished	()Z
      //   828: ifne +56 -> 884
      //   831: iload_1
      //   832: ifne +17 -> 849
      //   835: aload_0
      //   836: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   839: iconst_1
      //   840: invokevirtual 292	android/support/v7/widget/RecyclerView:hasNestedScrollingParent	(I)Z
      //   843: ifne +6 -> 849
      //   846: goto +38 -> 884
      //   849: aload_0
      //   850: invokevirtual 99	android/support/v7/widget/RecyclerView$ViewFlinger:postOnAnimation	()V
      //   853: aload_0
      //   854: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   857: getfield 296	android/support/v7/widget/RecyclerView:mGapWorker	Landroid/support/v7/widget/GapWorker;
      //   860: ifnull +56 -> 916
      //   863: aload_0
      //   864: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   867: getfield 296	android/support/v7/widget/RecyclerView:mGapWorker	Landroid/support/v7/widget/GapWorker;
      //   870: aload_0
      //   871: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   874: iload 6
      //   876: iload 5
      //   878: invokevirtual 302	android/support/v7/widget/GapWorker:postFromTraversal	(Landroid/support/v7/widget/RecyclerView;II)V
      //   881: goto +35 -> 916
      //   884: aload_0
      //   885: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   888: iconst_0
      //   889: invokevirtual 105	android/support/v7/widget/RecyclerView:setScrollState	(I)V
      //   892: invokestatic 305	android/support/v7/widget/RecyclerView:access$800	()Z
      //   895: ifeq +13 -> 908
      //   898: aload_0
      //   899: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   902: getfield 309	android/support/v7/widget/RecyclerView:mPrefetchRegistry	Landroid/support/v7/widget/GapWorker$LayoutPrefetchRegistryImpl;
      //   905: invokevirtual 314	android/support/v7/widget/GapWorker$LayoutPrefetchRegistryImpl:clearPrefetchPositions	()V
      //   908: aload_0
      //   909: getfield 25	android/support/v7/widget/RecyclerView$ViewFlinger:this$0	Landroid/support/v7/widget/RecyclerView;
      //   912: iconst_1
      //   913: invokevirtual 317	android/support/v7/widget/RecyclerView:stopNestedScroll	(I)V
      //   916: aload 14
      //   918: ifnull +30 -> 948
      //   921: aload 14
      //   923: invokevirtual 215	android/support/v7/widget/RecyclerView$SmoothScroller:isPendingInitialRun	()Z
      //   926: ifeq +10 -> 936
      //   929: aload 14
      //   931: iconst_0
      //   932: iconst_0
      //   933: invokestatic 234	android/support/v7/widget/RecyclerView$SmoothScroller:access$600	(Landroid/support/v7/widget/RecyclerView$SmoothScroller;II)V
      //   936: aload_0
      //   937: getfield 37	android/support/v7/widget/RecyclerView$ViewFlinger:mReSchedulePostAnimationCallback	Z
      //   940: ifne +8 -> 948
      //   943: aload 14
      //   945: invokevirtual 224	android/support/v7/widget/RecyclerView$SmoothScroller:stop	()V
      //   948: aload_0
      //   949: invokespecial 319	android/support/v7/widget/RecyclerView$ViewFlinger:enableRunOnAnimationRequests	()V
      //   952: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	953	0	this	ViewFlinger
      //   90	742	1	i	int
      //   82	724	2	j	int
      //   259	208	3	k	int
      //   264	206	4	m	int
      //   107	770	5	n	int
      //   104	771	6	i1	int
      //   300	486	7	i2	int
      //   310	368	8	i3	int
      //   306	448	9	i4	int
      //   303	356	10	i5	int
      //   66	593	11	i6	int
      //   73	605	12	i7	int
      //   30	794	13	localOverScroller	OverScroller
      //   42	902	14	localSmoothScroller	RecyclerView.SmoothScroller
      //   59	76	15	arrayOfInt	int[]
    }
    
    public void smoothScrollBy(int paramInt1, int paramInt2)
    {
      smoothScrollBy(paramInt1, paramInt2, 0, 0);
    }
    
    public void smoothScrollBy(int paramInt1, int paramInt2, int paramInt3)
    {
      smoothScrollBy(paramInt1, paramInt2, paramInt3, RecyclerView.sQuinticInterpolator);
    }
    
    public void smoothScrollBy(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      smoothScrollBy(paramInt1, paramInt2, computeScrollDuration(paramInt1, paramInt2, paramInt3, paramInt4));
    }
    
    public void smoothScrollBy(int paramInt1, int paramInt2, int paramInt3, Interpolator paramInterpolator)
    {
      if (mInterpolator != paramInterpolator)
      {
        mInterpolator = paramInterpolator;
        mScroller = new OverScroller(getContext(), paramInterpolator);
      }
      setScrollState(2);
      mLastFlingY = 0;
      mLastFlingX = 0;
      mScroller.startScroll(0, 0, paramInt1, paramInt2, paramInt3);
      if (Build.VERSION.SDK_INT < 23) {
        mScroller.computeScrollOffset();
      }
      postOnAnimation();
    }
    
    public void smoothScrollBy(int paramInt1, int paramInt2, Interpolator paramInterpolator)
    {
      int i = computeScrollDuration(paramInt1, paramInt2, 0, 0);
      Interpolator localInterpolator = paramInterpolator;
      if (paramInterpolator == null) {
        localInterpolator = RecyclerView.sQuinticInterpolator;
      }
      smoothScrollBy(paramInt1, paramInt2, i, localInterpolator);
    }
    
    public void stop()
    {
      removeCallbacks(this);
      mScroller.abortAnimation();
    }
  }
  
  public static abstract class ViewHolder
  {
    static final int FLAG_ADAPTER_FULLUPDATE = 1024;
    static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
    static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
    static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
    static final int FLAG_BOUND = 1;
    static final int FLAG_IGNORE = 128;
    static final int FLAG_INVALID = 4;
    static final int FLAG_MOVED = 2048;
    static final int FLAG_NOT_RECYCLABLE = 16;
    static final int FLAG_REMOVED = 8;
    static final int FLAG_RETURNED_FROM_SCRAP = 32;
    static final int FLAG_SET_A11Y_ITEM_DELEGATE = 16384;
    static final int FLAG_TMP_DETACHED = 256;
    static final int FLAG_UPDATE = 2;
    private static final List<Object> FULLUPDATE_PAYLOADS = Collections.EMPTY_LIST;
    static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
    public final View itemView;
    private int mFlags;
    private boolean mInChangeScrap = false;
    private int mIsRecyclableCount = 0;
    long mItemId = -1L;
    int mItemViewType = -1;
    WeakReference<RecyclerView> mNestedRecyclerView;
    int mOldPosition = -1;
    RecyclerView mOwnerRecyclerView;
    List<Object> mPayloads = null;
    @VisibleForTesting
    int mPendingAccessibilityState = -1;
    int mPosition = -1;
    int mPreLayoutPosition = -1;
    private RecyclerView.Recycler mScrapContainer = null;
    ViewHolder mShadowedHolder = null;
    ViewHolder mShadowingHolder = null;
    List<Object> mUnmodifiedPayloads = null;
    private int mWasImportantForAccessibilityBeforeHidden = 0;
    
    public ViewHolder(View paramView)
    {
      if (paramView == null) {
        throw new IllegalArgumentException("itemView may not be null");
      }
      itemView = paramView;
    }
    
    private void createPayloadsIfNeeded()
    {
      if (mPayloads == null)
      {
        mPayloads = new ArrayList();
        mUnmodifiedPayloads = Collections.unmodifiableList(mPayloads);
      }
    }
    
    private boolean doesTransientStatePreventRecycling()
    {
      return ((mFlags & 0x10) == 0) && (ViewCompat.hasTransientState(itemView));
    }
    
    private void onEnteredHiddenState(RecyclerView paramRecyclerView)
    {
      if (mPendingAccessibilityState != -1) {
        mWasImportantForAccessibilityBeforeHidden = mPendingAccessibilityState;
      } else {
        mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(itemView);
      }
      paramRecyclerView.setChildImportantForAccessibilityInternal(this, 4);
    }
    
    private void onLeftHiddenState(RecyclerView paramRecyclerView)
    {
      paramRecyclerView.setChildImportantForAccessibilityInternal(this, mWasImportantForAccessibilityBeforeHidden);
      mWasImportantForAccessibilityBeforeHidden = 0;
    }
    
    private boolean shouldBeKeptAsChild()
    {
      return (mFlags & 0x10) != 0;
    }
    
    void addChangePayload(Object paramObject)
    {
      if (paramObject == null)
      {
        addFlags(1024);
        return;
      }
      if ((0x400 & mFlags) == 0)
      {
        createPayloadsIfNeeded();
        mPayloads.add(paramObject);
      }
    }
    
    void addFlags(int paramInt)
    {
      mFlags = (paramInt | mFlags);
    }
    
    void clearOldPosition()
    {
      mOldPosition = -1;
      mPreLayoutPosition = -1;
    }
    
    void clearPayload()
    {
      if (mPayloads != null) {
        mPayloads.clear();
      }
      mFlags &= 0xFBFF;
    }
    
    void clearReturnedFromScrapFlag()
    {
      mFlags &= 0xFFFFFFDF;
    }
    
    void clearTmpDetachFlag()
    {
      mFlags &= 0xFEFF;
    }
    
    void flagRemovedAndOffsetPosition(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      addFlags(8);
      offsetPosition(paramInt2, paramBoolean);
      mPosition = paramInt1;
    }
    
    public final int getAdapterPosition()
    {
      if (mOwnerRecyclerView == null) {
        return -1;
      }
      return mOwnerRecyclerView.getAdapterPositionFor(this);
    }
    
    public final long getItemId()
    {
      return mItemId;
    }
    
    public final int getItemViewType()
    {
      return mItemViewType;
    }
    
    public final int getLayoutPosition()
    {
      if (mPreLayoutPosition == -1) {
        return mPosition;
      }
      return mPreLayoutPosition;
    }
    
    public final int getOldPosition()
    {
      return mOldPosition;
    }
    
    @Deprecated
    public final int getPosition()
    {
      if (mPreLayoutPosition == -1) {
        return mPosition;
      }
      return mPreLayoutPosition;
    }
    
    List<Object> getUnmodifiedPayloads()
    {
      if ((mFlags & 0x400) == 0)
      {
        if ((mPayloads != null) && (mPayloads.size() != 0)) {
          return mUnmodifiedPayloads;
        }
        return FULLUPDATE_PAYLOADS;
      }
      return FULLUPDATE_PAYLOADS;
    }
    
    boolean hasAnyOfTheFlags(int paramInt)
    {
      return (paramInt & mFlags) != 0;
    }
    
    boolean isAdapterPositionUnknown()
    {
      return ((mFlags & 0x200) != 0) || (isInvalid());
    }
    
    boolean isBound()
    {
      return (mFlags & 0x1) != 0;
    }
    
    boolean isInvalid()
    {
      return (mFlags & 0x4) != 0;
    }
    
    public final boolean isRecyclable()
    {
      return ((mFlags & 0x10) == 0) && (!ViewCompat.hasTransientState(itemView));
    }
    
    boolean isRemoved()
    {
      return (mFlags & 0x8) != 0;
    }
    
    boolean isScrap()
    {
      return mScrapContainer != null;
    }
    
    boolean isTmpDetached()
    {
      return (mFlags & 0x100) != 0;
    }
    
    boolean isUpdated()
    {
      return (mFlags & 0x2) != 0;
    }
    
    boolean needsUpdate()
    {
      return (mFlags & 0x2) != 0;
    }
    
    void offsetPosition(int paramInt, boolean paramBoolean)
    {
      if (mOldPosition == -1) {
        mOldPosition = mPosition;
      }
      if (mPreLayoutPosition == -1) {
        mPreLayoutPosition = mPosition;
      }
      if (paramBoolean) {
        mPreLayoutPosition += paramInt;
      }
      mPosition += paramInt;
      if (itemView.getLayoutParams() != null) {
        itemView.getLayoutParams()).mInsetsDirty = true;
      }
    }
    
    void resetInternal()
    {
      mFlags = 0;
      mPosition = -1;
      mOldPosition = -1;
      mItemId = -1L;
      mPreLayoutPosition = -1;
      mIsRecyclableCount = 0;
      mShadowedHolder = null;
      mShadowingHolder = null;
      clearPayload();
      mWasImportantForAccessibilityBeforeHidden = 0;
      mPendingAccessibilityState = -1;
      RecyclerView.clearNestedRecyclerViewIfNotNested(this);
    }
    
    void saveOldPosition()
    {
      if (mOldPosition == -1) {
        mOldPosition = mPosition;
      }
    }
    
    void setFlags(int paramInt1, int paramInt2)
    {
      mFlags = (paramInt1 & paramInt2 | mFlags & (paramInt2 ^ 0xFFFFFFFF));
    }
    
    public final void setIsRecyclable(boolean paramBoolean)
    {
      int i;
      if (paramBoolean) {
        i = mIsRecyclableCount - 1;
      } else {
        i = mIsRecyclableCount + 1;
      }
      mIsRecyclableCount = i;
      if (mIsRecyclableCount < 0)
      {
        mIsRecyclableCount = 0;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for ");
        localStringBuilder.append(this);
        Log.e("View", localStringBuilder.toString());
        return;
      }
      if ((!paramBoolean) && (mIsRecyclableCount == 1))
      {
        mFlags |= 0x10;
        return;
      }
      if ((paramBoolean) && (mIsRecyclableCount == 0)) {
        mFlags &= 0xFFFFFFEF;
      }
    }
    
    void setScrapContainer(RecyclerView.Recycler paramRecycler, boolean paramBoolean)
    {
      mScrapContainer = paramRecycler;
      mInChangeScrap = paramBoolean;
    }
    
    boolean shouldIgnore()
    {
      return (mFlags & 0x80) != 0;
    }
    
    void stopIgnoring()
    {
      mFlags &= 0xFF7F;
    }
    
    public String toString()
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("ViewHolder{");
      ((StringBuilder)localObject).append(Integer.toHexString(hashCode()));
      ((StringBuilder)localObject).append(" position=");
      ((StringBuilder)localObject).append(mPosition);
      ((StringBuilder)localObject).append(" id=");
      ((StringBuilder)localObject).append(mItemId);
      ((StringBuilder)localObject).append(", oldPos=");
      ((StringBuilder)localObject).append(mOldPosition);
      ((StringBuilder)localObject).append(", pLpos:");
      ((StringBuilder)localObject).append(mPreLayoutPosition);
      StringBuilder localStringBuilder = new StringBuilder(((StringBuilder)localObject).toString());
      if (isScrap())
      {
        localStringBuilder.append(" scrap ");
        if (mInChangeScrap) {
          localObject = "[changeScrap]";
        } else {
          localObject = "[attachedScrap]";
        }
        localStringBuilder.append((String)localObject);
      }
      if (isInvalid()) {
        localStringBuilder.append(" invalid");
      }
      if (!isBound()) {
        localStringBuilder.append(" unbound");
      }
      if (needsUpdate()) {
        localStringBuilder.append(" update");
      }
      if (isRemoved()) {
        localStringBuilder.append(" removed");
      }
      if (shouldIgnore()) {
        localStringBuilder.append(" ignored");
      }
      if (isTmpDetached()) {
        localStringBuilder.append(" tmpDetached");
      }
      if (!isRecyclable())
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(" not recyclable(");
        ((StringBuilder)localObject).append(mIsRecyclableCount);
        ((StringBuilder)localObject).append(")");
        localStringBuilder.append(((StringBuilder)localObject).toString());
      }
      if (isAdapterPositionUnknown()) {
        localStringBuilder.append(" undefined adapter position");
      }
      if (itemView.getParent() == null) {
        localStringBuilder.append(" no parent");
      }
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
    
    void unScrap()
    {
      mScrapContainer.unscrapView(this);
    }
    
    boolean wasReturnedFromScrap()
    {
      return (mFlags & 0x20) != 0;
    }
  }
}

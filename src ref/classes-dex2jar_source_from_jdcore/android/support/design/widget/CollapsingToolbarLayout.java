package android.support.design.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.design.R.id;
import android.support.design.R.styleable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.math.MathUtils;
import android.support.v4.util.ObjectsCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.ViewGroupUtils;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CollapsingToolbarLayout
  extends FrameLayout
{
  private static final int DEFAULT_SCRIM_ANIMATION_DURATION = 600;
  final CollapsingTextHelper mCollapsingTextHelper;
  private boolean mCollapsingTitleEnabled;
  private Drawable mContentScrim;
  int mCurrentOffset;
  private boolean mDrawCollapsingTitle;
  private View mDummyView;
  private int mExpandedMarginBottom;
  private int mExpandedMarginEnd;
  private int mExpandedMarginStart;
  private int mExpandedMarginTop;
  WindowInsetsCompat mLastInsets;
  private AppBarLayout.OnOffsetChangedListener mOnOffsetChangedListener;
  private boolean mRefreshToolbar = true;
  private int mScrimAlpha;
  private long mScrimAnimationDuration;
  private ValueAnimator mScrimAnimator;
  private int mScrimVisibleHeightTrigger = -1;
  private boolean mScrimsAreShown;
  Drawable mStatusBarScrim;
  private final Rect mTmpRect = new Rect();
  private Toolbar mToolbar;
  private View mToolbarDirectChild;
  private int mToolbarId;
  
  public CollapsingToolbarLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CollapsingToolbarLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public CollapsingToolbarLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    ThemeUtils.checkAppCompatTheme(paramContext);
    mCollapsingTextHelper = new CollapsingTextHelper(this);
    mCollapsingTextHelper.setTextSizeInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CollapsingToolbarLayout, paramInt, android.support.design.R.style.Widget_Design_CollapsingToolbar);
    mCollapsingTextHelper.setExpandedTextGravity(paramContext.getInt(R.styleable.CollapsingToolbarLayout_expandedTitleGravity, 8388691));
    mCollapsingTextHelper.setCollapsedTextGravity(paramContext.getInt(R.styleable.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
    paramInt = paramContext.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
    mExpandedMarginBottom = paramInt;
    mExpandedMarginEnd = paramInt;
    mExpandedMarginTop = paramInt;
    mExpandedMarginStart = paramInt;
    if (paramContext.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart)) {
      mExpandedMarginStart = paramContext.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart, 0);
    }
    if (paramContext.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd)) {
      mExpandedMarginEnd = paramContext.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd, 0);
    }
    if (paramContext.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop)) {
      mExpandedMarginTop = paramContext.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop, 0);
    }
    if (paramContext.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom)) {
      mExpandedMarginBottom = paramContext.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom, 0);
    }
    mCollapsingTitleEnabled = paramContext.getBoolean(R.styleable.CollapsingToolbarLayout_titleEnabled, true);
    setTitle(paramContext.getText(R.styleable.CollapsingToolbarLayout_title));
    mCollapsingTextHelper.setExpandedTextAppearance(android.support.design.R.style.TextAppearance_Design_CollapsingToolbar_Expanded);
    mCollapsingTextHelper.setCollapsedTextAppearance(android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Widget_ActionBar_Title);
    if (paramContext.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance)) {
      mCollapsingTextHelper.setExpandedTextAppearance(paramContext.getResourceId(R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance, 0));
    }
    if (paramContext.hasValue(R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance)) {
      mCollapsingTextHelper.setCollapsedTextAppearance(paramContext.getResourceId(R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance, 0));
    }
    mScrimVisibleHeightTrigger = paramContext.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_scrimVisibleHeightTrigger, -1);
    mScrimAnimationDuration = paramContext.getInt(R.styleable.CollapsingToolbarLayout_scrimAnimationDuration, 600);
    setContentScrim(paramContext.getDrawable(R.styleable.CollapsingToolbarLayout_contentScrim));
    setStatusBarScrim(paramContext.getDrawable(R.styleable.CollapsingToolbarLayout_statusBarScrim));
    mToolbarId = paramContext.getResourceId(R.styleable.CollapsingToolbarLayout_toolbarId, -1);
    paramContext.recycle();
    setWillNotDraw(false);
    ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener()
    {
      public WindowInsetsCompat onApplyWindowInsets(View paramAnonymousView, WindowInsetsCompat paramAnonymousWindowInsetsCompat)
      {
        return onWindowInsetChanged(paramAnonymousWindowInsetsCompat);
      }
    });
  }
  
  private void animateScrim(int paramInt)
  {
    ensureToolbar();
    if (mScrimAnimator == null)
    {
      mScrimAnimator = new ValueAnimator();
      mScrimAnimator.setDuration(mScrimAnimationDuration);
      ValueAnimator localValueAnimator = mScrimAnimator;
      Interpolator localInterpolator;
      if (paramInt > mScrimAlpha) {
        localInterpolator = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
      } else {
        localInterpolator = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
      }
      localValueAnimator.setInterpolator(localInterpolator);
      mScrimAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
      {
        public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
        {
          setScrimAlpha(((Integer)paramAnonymousValueAnimator.getAnimatedValue()).intValue());
        }
      });
    }
    else if (mScrimAnimator.isRunning())
    {
      mScrimAnimator.cancel();
    }
    mScrimAnimator.setIntValues(new int[] { mScrimAlpha, paramInt });
    mScrimAnimator.start();
  }
  
  private void ensureToolbar()
  {
    if (!mRefreshToolbar) {
      return;
    }
    Object localObject2 = null;
    mToolbar = null;
    mToolbarDirectChild = null;
    if (mToolbarId != -1)
    {
      mToolbar = ((Toolbar)findViewById(mToolbarId));
      if (mToolbar != null) {
        mToolbarDirectChild = findDirectChild(mToolbar);
      }
    }
    if (mToolbar == null)
    {
      int j = getChildCount();
      int i = 0;
      Object localObject1;
      for (;;)
      {
        localObject1 = localObject2;
        if (i >= j) {
          break;
        }
        localObject1 = getChildAt(i);
        if ((localObject1 instanceof Toolbar))
        {
          localObject1 = (Toolbar)localObject1;
          break;
        }
        i += 1;
      }
      mToolbar = ((Toolbar)localObject1);
    }
    updateDummyView();
    mRefreshToolbar = false;
  }
  
  private View findDirectChild(View paramView)
  {
    ViewParent localViewParent = paramView.getParent();
    View localView = paramView;
    for (paramView = localViewParent; (paramView != this) && (paramView != null); paramView = paramView.getParent()) {
      if ((paramView instanceof View)) {
        localView = (View)paramView;
      }
    }
    return localView;
  }
  
  private static int getHeightWithMargins(@NonNull View paramView)
  {
    Object localObject = paramView.getLayoutParams();
    if ((localObject instanceof ViewGroup.MarginLayoutParams))
    {
      localObject = (ViewGroup.MarginLayoutParams)localObject;
      return paramView.getHeight() + topMargin + bottomMargin;
    }
    return paramView.getHeight();
  }
  
  static ViewOffsetHelper getViewOffsetHelper(View paramView)
  {
    ViewOffsetHelper localViewOffsetHelper2 = (ViewOffsetHelper)paramView.getTag(R.id.view_offset_helper);
    ViewOffsetHelper localViewOffsetHelper1 = localViewOffsetHelper2;
    if (localViewOffsetHelper2 == null)
    {
      localViewOffsetHelper1 = new ViewOffsetHelper(paramView);
      paramView.setTag(R.id.view_offset_helper, localViewOffsetHelper1);
    }
    return localViewOffsetHelper1;
  }
  
  private boolean isToolbarChild(View paramView)
  {
    View localView = mToolbarDirectChild;
    boolean bool = false;
    if ((localView != null) && (mToolbarDirectChild != this) ? paramView == mToolbarDirectChild : paramView == mToolbar) {
      bool = true;
    }
    return bool;
  }
  
  private void updateDummyView()
  {
    if ((!mCollapsingTitleEnabled) && (mDummyView != null))
    {
      ViewParent localViewParent = mDummyView.getParent();
      if ((localViewParent instanceof ViewGroup)) {
        ((ViewGroup)localViewParent).removeView(mDummyView);
      }
    }
    if ((mCollapsingTitleEnabled) && (mToolbar != null))
    {
      if (mDummyView == null) {
        mDummyView = new View(getContext());
      }
      if (mDummyView.getParent() == null) {
        mToolbar.addView(mDummyView, -1, -1);
      }
    }
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    ensureToolbar();
    if ((mToolbar == null) && (mContentScrim != null) && (mScrimAlpha > 0))
    {
      mContentScrim.mutate().setAlpha(mScrimAlpha);
      mContentScrim.draw(paramCanvas);
    }
    if ((mCollapsingTitleEnabled) && (mDrawCollapsingTitle)) {
      mCollapsingTextHelper.draw(paramCanvas);
    }
    if ((mStatusBarScrim != null) && (mScrimAlpha > 0))
    {
      int i;
      if (mLastInsets != null) {
        i = mLastInsets.getSystemWindowInsetTop();
      } else {
        i = 0;
      }
      if (i > 0)
      {
        mStatusBarScrim.setBounds(0, -mCurrentOffset, getWidth(), i - mCurrentOffset);
        mStatusBarScrim.mutate().setAlpha(mScrimAlpha);
        mStatusBarScrim.draw(paramCanvas);
      }
    }
  }
  
  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    Drawable localDrawable = mContentScrim;
    boolean bool = true;
    int i;
    if ((localDrawable != null) && (mScrimAlpha > 0) && (isToolbarChild(paramView)))
    {
      mContentScrim.mutate().setAlpha(mScrimAlpha);
      mContentScrim.draw(paramCanvas);
      i = 1;
    }
    else
    {
      i = 0;
    }
    if (!super.drawChild(paramCanvas, paramView, paramLong))
    {
      if (i != 0) {
        return true;
      }
      bool = false;
    }
    return bool;
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    int[] arrayOfInt = getDrawableState();
    Drawable localDrawable = mStatusBarScrim;
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (localDrawable != null)
    {
      bool1 = bool2;
      if (localDrawable.isStateful()) {
        bool1 = false | localDrawable.setState(arrayOfInt);
      }
    }
    localDrawable = mContentScrim;
    bool2 = bool1;
    if (localDrawable != null)
    {
      bool2 = bool1;
      if (localDrawable.isStateful()) {
        bool2 = bool1 | localDrawable.setState(arrayOfInt);
      }
    }
    bool1 = bool2;
    if (mCollapsingTextHelper != null) {
      bool1 = bool2 | mCollapsingTextHelper.setState(arrayOfInt);
    }
    if (bool1) {
      invalidate();
    }
  }
  
  protected LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams(-1, -1);
  }
  
  public FrameLayout.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected FrameLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return new LayoutParams(paramLayoutParams);
  }
  
  public int getCollapsedTitleGravity()
  {
    return mCollapsingTextHelper.getCollapsedTextGravity();
  }
  
  @NonNull
  public Typeface getCollapsedTitleTypeface()
  {
    return mCollapsingTextHelper.getCollapsedTypeface();
  }
  
  @Nullable
  public Drawable getContentScrim()
  {
    return mContentScrim;
  }
  
  public int getExpandedTitleGravity()
  {
    return mCollapsingTextHelper.getExpandedTextGravity();
  }
  
  public int getExpandedTitleMarginBottom()
  {
    return mExpandedMarginBottom;
  }
  
  public int getExpandedTitleMarginEnd()
  {
    return mExpandedMarginEnd;
  }
  
  public int getExpandedTitleMarginStart()
  {
    return mExpandedMarginStart;
  }
  
  public int getExpandedTitleMarginTop()
  {
    return mExpandedMarginTop;
  }
  
  @NonNull
  public Typeface getExpandedTitleTypeface()
  {
    return mCollapsingTextHelper.getExpandedTypeface();
  }
  
  final int getMaxOffsetForPinChild(View paramView)
  {
    ViewOffsetHelper localViewOffsetHelper = getViewOffsetHelper(paramView);
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    return getHeight() - localViewOffsetHelper.getLayoutTop() - paramView.getHeight() - bottomMargin;
  }
  
  int getScrimAlpha()
  {
    return mScrimAlpha;
  }
  
  public long getScrimAnimationDuration()
  {
    return mScrimAnimationDuration;
  }
  
  public int getScrimVisibleHeightTrigger()
  {
    if (mScrimVisibleHeightTrigger >= 0) {
      return mScrimVisibleHeightTrigger;
    }
    int i;
    if (mLastInsets != null) {
      i = mLastInsets.getSystemWindowInsetTop();
    } else {
      i = 0;
    }
    int j = ViewCompat.getMinimumHeight(this);
    if (j > 0) {
      return Math.min(j * 2 + i, getHeight());
    }
    return getHeight() / 3;
  }
  
  @Nullable
  public Drawable getStatusBarScrim()
  {
    return mStatusBarScrim;
  }
  
  @Nullable
  public CharSequence getTitle()
  {
    if (mCollapsingTitleEnabled) {
      return mCollapsingTextHelper.getText();
    }
    return null;
  }
  
  public boolean isTitleEnabled()
  {
    return mCollapsingTitleEnabled;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    ViewParent localViewParent = getParent();
    if ((localViewParent instanceof AppBarLayout))
    {
      ViewCompat.setFitsSystemWindows(this, ViewCompat.getFitsSystemWindows((View)localViewParent));
      if (mOnOffsetChangedListener == null) {
        mOnOffsetChangedListener = new OffsetUpdateListener();
      }
      ((AppBarLayout)localViewParent).addOnOffsetChangedListener(mOnOffsetChangedListener);
      ViewCompat.requestApplyInsets(this);
    }
  }
  
  protected void onDetachedFromWindow()
  {
    ViewParent localViewParent = getParent();
    if ((mOnOffsetChangedListener != null) && ((localViewParent instanceof AppBarLayout))) {
      ((AppBarLayout)localViewParent).removeOnOffsetChangedListener(mOnOffsetChangedListener);
    }
    super.onDetachedFromWindow();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    Object localObject = mLastInsets;
    int m = 0;
    int j;
    int k;
    int i;
    if (localObject != null)
    {
      j = mLastInsets.getSystemWindowInsetTop();
      k = getChildCount();
      i = 0;
      while (i < k)
      {
        localObject = getChildAt(i);
        if ((!ViewCompat.getFitsSystemWindows((View)localObject)) && (((View)localObject).getTop() < j)) {
          ViewCompat.offsetTopAndBottom((View)localObject, j);
        }
        i += 1;
      }
    }
    if ((mCollapsingTitleEnabled) && (mDummyView != null))
    {
      paramBoolean = ViewCompat.isAttachedToWindow(mDummyView);
      i = 1;
      if ((paramBoolean) && (mDummyView.getVisibility() == 0)) {
        paramBoolean = true;
      } else {
        paramBoolean = false;
      }
      mDrawCollapsingTitle = paramBoolean;
      if (mDrawCollapsingTitle)
      {
        if (ViewCompat.getLayoutDirection(this) != 1) {
          i = 0;
        }
        if (mToolbarDirectChild != null) {
          localObject = mToolbarDirectChild;
        } else {
          localObject = mToolbar;
        }
        int n = getMaxOffsetForPinChild((View)localObject);
        ViewGroupUtils.getDescendantRect(this, mDummyView, mTmpRect);
        localObject = mCollapsingTextHelper;
        int i1 = mTmpRect.left;
        if (i != 0) {
          j = mToolbar.getTitleMarginEnd();
        } else {
          j = mToolbar.getTitleMarginStart();
        }
        int i2 = mTmpRect.top;
        int i3 = mToolbar.getTitleMarginTop();
        int i4 = mTmpRect.right;
        if (i != 0) {
          k = mToolbar.getTitleMarginStart();
        } else {
          k = mToolbar.getTitleMarginEnd();
        }
        ((CollapsingTextHelper)localObject).setCollapsedBounds(i1 + j, i2 + n + i3, i4 + k, mTmpRect.bottom + n - mToolbar.getTitleMarginBottom());
        localObject = mCollapsingTextHelper;
        if (i != 0) {
          j = mExpandedMarginEnd;
        } else {
          j = mExpandedMarginStart;
        }
        k = mTmpRect.top;
        n = mExpandedMarginTop;
        if (i != 0) {
          i = mExpandedMarginStart;
        } else {
          i = mExpandedMarginEnd;
        }
        ((CollapsingTextHelper)localObject).setExpandedBounds(j, k + n, paramInt3 - paramInt1 - i, paramInt4 - paramInt2 - mExpandedMarginBottom);
        mCollapsingTextHelper.recalculate();
      }
    }
    paramInt2 = getChildCount();
    paramInt1 = m;
    while (paramInt1 < paramInt2)
    {
      getViewOffsetHelper(getChildAt(paramInt1)).onViewLayout();
      paramInt1 += 1;
    }
    if (mToolbar != null)
    {
      if ((mCollapsingTitleEnabled) && (TextUtils.isEmpty(mCollapsingTextHelper.getText()))) {
        mCollapsingTextHelper.setText(mToolbar.getTitle());
      }
      if ((mToolbarDirectChild != null) && (mToolbarDirectChild != this)) {
        setMinimumHeight(getHeightWithMargins(mToolbarDirectChild));
      } else {
        setMinimumHeight(getHeightWithMargins(mToolbar));
      }
    }
    updateScrimVisibility();
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    ensureToolbar();
    super.onMeasure(paramInt1, paramInt2);
    int i = View.MeasureSpec.getMode(paramInt2);
    if (mLastInsets != null) {
      paramInt2 = mLastInsets.getSystemWindowInsetTop();
    } else {
      paramInt2 = 0;
    }
    if ((i == 0) && (paramInt2 > 0)) {
      super.onMeasure(paramInt1, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + paramInt2, 1073741824));
    }
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (mContentScrim != null) {
      mContentScrim.setBounds(0, 0, paramInt1, paramInt2);
    }
  }
  
  WindowInsetsCompat onWindowInsetChanged(WindowInsetsCompat paramWindowInsetsCompat)
  {
    WindowInsetsCompat localWindowInsetsCompat;
    if (ViewCompat.getFitsSystemWindows(this)) {
      localWindowInsetsCompat = paramWindowInsetsCompat;
    } else {
      localWindowInsetsCompat = null;
    }
    if (!ObjectsCompat.equals(mLastInsets, localWindowInsetsCompat))
    {
      mLastInsets = localWindowInsetsCompat;
      requestLayout();
    }
    return paramWindowInsetsCompat.consumeSystemWindowInsets();
  }
  
  public void setCollapsedTitleGravity(int paramInt)
  {
    mCollapsingTextHelper.setCollapsedTextGravity(paramInt);
  }
  
  public void setCollapsedTitleTextAppearance(@StyleRes int paramInt)
  {
    mCollapsingTextHelper.setCollapsedTextAppearance(paramInt);
  }
  
  public void setCollapsedTitleTextColor(@ColorInt int paramInt)
  {
    setCollapsedTitleTextColor(ColorStateList.valueOf(paramInt));
  }
  
  public void setCollapsedTitleTextColor(@NonNull ColorStateList paramColorStateList)
  {
    mCollapsingTextHelper.setCollapsedTextColor(paramColorStateList);
  }
  
  public void setCollapsedTitleTypeface(@Nullable Typeface paramTypeface)
  {
    mCollapsingTextHelper.setCollapsedTypeface(paramTypeface);
  }
  
  public void setContentScrim(@Nullable Drawable paramDrawable)
  {
    if (mContentScrim != paramDrawable)
    {
      Drawable localDrawable2 = mContentScrim;
      Drawable localDrawable1 = null;
      if (localDrawable2 != null) {
        mContentScrim.setCallback(null);
      }
      if (paramDrawable != null) {
        localDrawable1 = paramDrawable.mutate();
      }
      mContentScrim = localDrawable1;
      if (mContentScrim != null)
      {
        mContentScrim.setBounds(0, 0, getWidth(), getHeight());
        mContentScrim.setCallback(this);
        mContentScrim.setAlpha(mScrimAlpha);
      }
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  public void setContentScrimColor(@ColorInt int paramInt)
  {
    setContentScrim(new ColorDrawable(paramInt));
  }
  
  public void setContentScrimResource(@DrawableRes int paramInt)
  {
    setContentScrim(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setExpandedTitleColor(@ColorInt int paramInt)
  {
    setExpandedTitleTextColor(ColorStateList.valueOf(paramInt));
  }
  
  public void setExpandedTitleGravity(int paramInt)
  {
    mCollapsingTextHelper.setExpandedTextGravity(paramInt);
  }
  
  public void setExpandedTitleMargin(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mExpandedMarginStart = paramInt1;
    mExpandedMarginTop = paramInt2;
    mExpandedMarginEnd = paramInt3;
    mExpandedMarginBottom = paramInt4;
    requestLayout();
  }
  
  public void setExpandedTitleMarginBottom(int paramInt)
  {
    mExpandedMarginBottom = paramInt;
    requestLayout();
  }
  
  public void setExpandedTitleMarginEnd(int paramInt)
  {
    mExpandedMarginEnd = paramInt;
    requestLayout();
  }
  
  public void setExpandedTitleMarginStart(int paramInt)
  {
    mExpandedMarginStart = paramInt;
    requestLayout();
  }
  
  public void setExpandedTitleMarginTop(int paramInt)
  {
    mExpandedMarginTop = paramInt;
    requestLayout();
  }
  
  public void setExpandedTitleTextAppearance(@StyleRes int paramInt)
  {
    mCollapsingTextHelper.setExpandedTextAppearance(paramInt);
  }
  
  public void setExpandedTitleTextColor(@NonNull ColorStateList paramColorStateList)
  {
    mCollapsingTextHelper.setExpandedTextColor(paramColorStateList);
  }
  
  public void setExpandedTitleTypeface(@Nullable Typeface paramTypeface)
  {
    mCollapsingTextHelper.setExpandedTypeface(paramTypeface);
  }
  
  void setScrimAlpha(int paramInt)
  {
    if (paramInt != mScrimAlpha)
    {
      if ((mContentScrim != null) && (mToolbar != null)) {
        ViewCompat.postInvalidateOnAnimation(mToolbar);
      }
      mScrimAlpha = paramInt;
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  public void setScrimAnimationDuration(@IntRange(from=0L) long paramLong)
  {
    mScrimAnimationDuration = paramLong;
  }
  
  public void setScrimVisibleHeightTrigger(@IntRange(from=0L) int paramInt)
  {
    if (mScrimVisibleHeightTrigger != paramInt)
    {
      mScrimVisibleHeightTrigger = paramInt;
      updateScrimVisibility();
    }
  }
  
  public void setScrimsShown(boolean paramBoolean)
  {
    boolean bool;
    if ((ViewCompat.isLaidOut(this)) && (!isInEditMode())) {
      bool = true;
    } else {
      bool = false;
    }
    setScrimsShown(paramBoolean, bool);
  }
  
  public void setScrimsShown(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (mScrimsAreShown != paramBoolean1)
    {
      int j = 0;
      int i = 0;
      if (paramBoolean2)
      {
        if (paramBoolean1) {
          i = 255;
        }
        animateScrim(i);
      }
      else
      {
        i = j;
        if (paramBoolean1) {
          i = 255;
        }
        setScrimAlpha(i);
      }
      mScrimsAreShown = paramBoolean1;
    }
  }
  
  public void setStatusBarScrim(@Nullable Drawable paramDrawable)
  {
    if (mStatusBarScrim != paramDrawable)
    {
      Drawable localDrawable2 = mStatusBarScrim;
      Drawable localDrawable1 = null;
      if (localDrawable2 != null) {
        mStatusBarScrim.setCallback(null);
      }
      if (paramDrawable != null) {
        localDrawable1 = paramDrawable.mutate();
      }
      mStatusBarScrim = localDrawable1;
      if (mStatusBarScrim != null)
      {
        if (mStatusBarScrim.isStateful()) {
          mStatusBarScrim.setState(getDrawableState());
        }
        DrawableCompat.setLayoutDirection(mStatusBarScrim, ViewCompat.getLayoutDirection(this));
        paramDrawable = mStatusBarScrim;
        boolean bool;
        if (getVisibility() == 0) {
          bool = true;
        } else {
          bool = false;
        }
        paramDrawable.setVisible(bool, false);
        mStatusBarScrim.setCallback(this);
        mStatusBarScrim.setAlpha(mScrimAlpha);
      }
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  public void setStatusBarScrimColor(@ColorInt int paramInt)
  {
    setStatusBarScrim(new ColorDrawable(paramInt));
  }
  
  public void setStatusBarScrimResource(@DrawableRes int paramInt)
  {
    setStatusBarScrim(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setTitle(@Nullable CharSequence paramCharSequence)
  {
    mCollapsingTextHelper.setText(paramCharSequence);
  }
  
  public void setTitleEnabled(boolean paramBoolean)
  {
    if (paramBoolean != mCollapsingTitleEnabled)
    {
      mCollapsingTitleEnabled = paramBoolean;
      updateDummyView();
      requestLayout();
    }
  }
  
  public void setVisibility(int paramInt)
  {
    super.setVisibility(paramInt);
    boolean bool;
    if (paramInt == 0) {
      bool = true;
    } else {
      bool = false;
    }
    if ((mStatusBarScrim != null) && (mStatusBarScrim.isVisible() != bool)) {
      mStatusBarScrim.setVisible(bool, false);
    }
    if ((mContentScrim != null) && (mContentScrim.isVisible() != bool)) {
      mContentScrim.setVisible(bool, false);
    }
  }
  
  final void updateScrimVisibility()
  {
    if ((mContentScrim != null) || (mStatusBarScrim != null))
    {
      boolean bool;
      if (getHeight() + mCurrentOffset < getScrimVisibleHeightTrigger()) {
        bool = true;
      } else {
        bool = false;
      }
      setScrimsShown(bool);
    }
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    return (super.verifyDrawable(paramDrawable)) || (paramDrawable == mContentScrim) || (paramDrawable == mStatusBarScrim);
  }
  
  public static class LayoutParams
    extends FrameLayout.LayoutParams
  {
    public static final int COLLAPSE_MODE_OFF = 0;
    public static final int COLLAPSE_MODE_PARALLAX = 2;
    public static final int COLLAPSE_MODE_PIN = 1;
    private static final float DEFAULT_PARALLAX_MULTIPLIER = 0.5F;
    int mCollapseMode = 0;
    float mParallaxMult = 0.5F;
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public LayoutParams(int paramInt1, int paramInt2, int paramInt3)
    {
      super(paramInt2, paramInt3);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CollapsingToolbarLayout_Layout);
      mCollapseMode = paramContext.getInt(R.styleable.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
      setParallaxMultiplier(paramContext.getFloat(R.styleable.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, 0.5F));
      paramContext.recycle();
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
    
    @RequiresApi(19)
    public LayoutParams(FrameLayout.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public int getCollapseMode()
    {
      return mCollapseMode;
    }
    
    public float getParallaxMultiplier()
    {
      return mParallaxMult;
    }
    
    public void setCollapseMode(int paramInt)
    {
      mCollapseMode = paramInt;
    }
    
    public void setParallaxMultiplier(float paramFloat)
    {
      mParallaxMult = paramFloat;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    static @interface CollapseMode {}
  }
  
  private class OffsetUpdateListener
    implements AppBarLayout.OnOffsetChangedListener
  {
    OffsetUpdateListener() {}
    
    public void onOffsetChanged(AppBarLayout paramAppBarLayout, int paramInt)
    {
      mCurrentOffset = paramInt;
      int i;
      if (mLastInsets != null) {
        i = mLastInsets.getSystemWindowInsetTop();
      } else {
        i = 0;
      }
      int k = getChildCount();
      int j = 0;
      while (j < k)
      {
        paramAppBarLayout = getChildAt(j);
        CollapsingToolbarLayout.LayoutParams localLayoutParams = (CollapsingToolbarLayout.LayoutParams)paramAppBarLayout.getLayoutParams();
        ViewOffsetHelper localViewOffsetHelper = CollapsingToolbarLayout.getViewOffsetHelper(paramAppBarLayout);
        switch (mCollapseMode)
        {
        default: 
          break;
        case 2: 
          localViewOffsetHelper.setTopAndBottomOffset(Math.round(-paramInt * mParallaxMult));
          break;
        case 1: 
          localViewOffsetHelper.setTopAndBottomOffset(MathUtils.clamp(-paramInt, 0, getMaxOffsetForPinChild(paramAppBarLayout)));
        }
        j += 1;
      }
      updateScrimVisibility();
      if ((mStatusBarScrim != null) && (i > 0)) {
        ViewCompat.postInvalidateOnAnimation(CollapsingToolbarLayout.this);
      }
      j = getHeight();
      k = ViewCompat.getMinimumHeight(CollapsingToolbarLayout.this);
      mCollapsingTextHelper.setExpansionFraction(Math.abs(paramInt) / (j - k - i));
    }
  }
}

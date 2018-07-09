package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.text.Layout;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class Toolbar
  extends ViewGroup
{
  private static final String TAG = "Toolbar";
  private MenuPresenter.Callback mActionMenuPresenterCallback;
  int mButtonGravity;
  ImageButton mCollapseButtonView;
  private CharSequence mCollapseDescription;
  private Drawable mCollapseIcon;
  private boolean mCollapsible;
  private int mContentInsetEndWithActions;
  private int mContentInsetStartWithNavigation;
  private RtlSpacingHelper mContentInsets;
  private boolean mEatingHover;
  private boolean mEatingTouch;
  View mExpandedActionView;
  private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
  private int mGravity = 8388627;
  private final ArrayList<View> mHiddenViews = new ArrayList();
  private ImageView mLogoView;
  private int mMaxButtonHeight;
  private MenuBuilder.Callback mMenuBuilderCallback;
  private ActionMenuView mMenuView;
  private final ActionMenuView.OnMenuItemClickListener mMenuViewItemClickListener = new ActionMenuView.OnMenuItemClickListener()
  {
    public boolean onMenuItemClick(MenuItem paramAnonymousMenuItem)
    {
      if (mOnMenuItemClickListener != null) {
        return mOnMenuItemClickListener.onMenuItemClick(paramAnonymousMenuItem);
      }
      return false;
    }
  };
  private ImageButton mNavButtonView;
  OnMenuItemClickListener mOnMenuItemClickListener;
  private ActionMenuPresenter mOuterActionMenuPresenter;
  private Context mPopupContext;
  private int mPopupTheme;
  private final Runnable mShowOverflowMenuRunnable = new Runnable()
  {
    public void run()
    {
      showOverflowMenu();
    }
  };
  private CharSequence mSubtitleText;
  private int mSubtitleTextAppearance;
  private int mSubtitleTextColor;
  private TextView mSubtitleTextView;
  private final int[] mTempMargins = new int[2];
  private final ArrayList<View> mTempViews = new ArrayList();
  private int mTitleMarginBottom;
  private int mTitleMarginEnd;
  private int mTitleMarginStart;
  private int mTitleMarginTop;
  private CharSequence mTitleText;
  private int mTitleTextAppearance;
  private int mTitleTextColor;
  private TextView mTitleTextView;
  private ToolbarWidgetWrapper mWrapper;
  
  public Toolbar(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public Toolbar(Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.toolbarStyle);
  }
  
  public Toolbar(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = TintTypedArray.obtainStyledAttributes(getContext(), paramAttributeSet, R.styleable.Toolbar, paramInt, 0);
    mTitleTextAppearance = paramContext.getResourceId(R.styleable.Toolbar_titleTextAppearance, 0);
    mSubtitleTextAppearance = paramContext.getResourceId(R.styleable.Toolbar_subtitleTextAppearance, 0);
    mGravity = paramContext.getInteger(R.styleable.Toolbar_android_gravity, mGravity);
    mButtonGravity = paramContext.getInteger(R.styleable.Toolbar_buttonGravity, 48);
    int i = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMargin, 0);
    paramInt = i;
    if (paramContext.hasValue(R.styleable.Toolbar_titleMargins)) {
      paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMargins, i);
    }
    mTitleMarginBottom = paramInt;
    mTitleMarginTop = paramInt;
    mTitleMarginEnd = paramInt;
    mTitleMarginStart = paramInt;
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginStart, -1);
    if (paramInt >= 0) {
      mTitleMarginStart = paramInt;
    }
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginEnd, -1);
    if (paramInt >= 0) {
      mTitleMarginEnd = paramInt;
    }
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginTop, -1);
    if (paramInt >= 0) {
      mTitleMarginTop = paramInt;
    }
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginBottom, -1);
    if (paramInt >= 0) {
      mTitleMarginBottom = paramInt;
    }
    mMaxButtonHeight = paramContext.getDimensionPixelSize(R.styleable.Toolbar_maxButtonHeight, -1);
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
    i = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
    int j = paramContext.getDimensionPixelSize(R.styleable.Toolbar_contentInsetLeft, 0);
    int k = paramContext.getDimensionPixelSize(R.styleable.Toolbar_contentInsetRight, 0);
    ensureContentInsets();
    mContentInsets.setAbsolute(j, k);
    if ((paramInt != Integer.MIN_VALUE) || (i != Integer.MIN_VALUE)) {
      mContentInsets.setRelative(paramInt, i);
    }
    mContentInsetStartWithNavigation = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
    mContentInsetEndWithActions = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
    mCollapseIcon = paramContext.getDrawable(R.styleable.Toolbar_collapseIcon);
    mCollapseDescription = paramContext.getText(R.styleable.Toolbar_collapseContentDescription);
    paramAttributeSet = paramContext.getText(R.styleable.Toolbar_title);
    if (!TextUtils.isEmpty(paramAttributeSet)) {
      setTitle(paramAttributeSet);
    }
    paramAttributeSet = paramContext.getText(R.styleable.Toolbar_subtitle);
    if (!TextUtils.isEmpty(paramAttributeSet)) {
      setSubtitle(paramAttributeSet);
    }
    mPopupContext = getContext();
    setPopupTheme(paramContext.getResourceId(R.styleable.Toolbar_popupTheme, 0));
    paramAttributeSet = paramContext.getDrawable(R.styleable.Toolbar_navigationIcon);
    if (paramAttributeSet != null) {
      setNavigationIcon(paramAttributeSet);
    }
    paramAttributeSet = paramContext.getText(R.styleable.Toolbar_navigationContentDescription);
    if (!TextUtils.isEmpty(paramAttributeSet)) {
      setNavigationContentDescription(paramAttributeSet);
    }
    paramAttributeSet = paramContext.getDrawable(R.styleable.Toolbar_logo);
    if (paramAttributeSet != null) {
      setLogo(paramAttributeSet);
    }
    paramAttributeSet = paramContext.getText(R.styleable.Toolbar_logoDescription);
    if (!TextUtils.isEmpty(paramAttributeSet)) {
      setLogoDescription(paramAttributeSet);
    }
    if (paramContext.hasValue(R.styleable.Toolbar_titleTextColor)) {
      setTitleTextColor(paramContext.getColor(R.styleable.Toolbar_titleTextColor, -1));
    }
    if (paramContext.hasValue(R.styleable.Toolbar_subtitleTextColor)) {
      setSubtitleTextColor(paramContext.getColor(R.styleable.Toolbar_subtitleTextColor, -1));
    }
    paramContext.recycle();
  }
  
  private void addCustomViewsWithGravity(List<View> paramList, int paramInt)
  {
    int i = ViewCompat.getLayoutDirection(this);
    int j = 0;
    if (i == 1) {
      i = 1;
    } else {
      i = 0;
    }
    int m = getChildCount();
    int k = GravityCompat.getAbsoluteGravity(paramInt, ViewCompat.getLayoutDirection(this));
    paramList.clear();
    paramInt = j;
    View localView;
    LayoutParams localLayoutParams;
    if (i != 0)
    {
      paramInt = m - 1;
      while (paramInt >= 0)
      {
        localView = getChildAt(paramInt);
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if ((mViewType == 0) && (shouldLayout(localView)) && (getChildHorizontalGravity(gravity) == k)) {
          paramList.add(localView);
        }
        paramInt -= 1;
      }
    }
    while (paramInt < m)
    {
      localView = getChildAt(paramInt);
      localLayoutParams = (LayoutParams)localView.getLayoutParams();
      if ((mViewType == 0) && (shouldLayout(localView)) && (getChildHorizontalGravity(gravity) == k)) {
        paramList.add(localView);
      }
      paramInt += 1;
    }
  }
  
  private void addSystemView(View paramView, boolean paramBoolean)
  {
    Object localObject = paramView.getLayoutParams();
    if (localObject == null) {
      localObject = generateDefaultLayoutParams();
    } else if (!checkLayoutParams((ViewGroup.LayoutParams)localObject)) {
      localObject = generateLayoutParams((ViewGroup.LayoutParams)localObject);
    } else {
      localObject = (LayoutParams)localObject;
    }
    mViewType = 1;
    if ((paramBoolean) && (mExpandedActionView != null))
    {
      paramView.setLayoutParams((ViewGroup.LayoutParams)localObject);
      mHiddenViews.add(paramView);
      return;
    }
    addView(paramView, (ViewGroup.LayoutParams)localObject);
  }
  
  private void ensureContentInsets()
  {
    if (mContentInsets == null) {
      mContentInsets = new RtlSpacingHelper();
    }
  }
  
  private void ensureLogoView()
  {
    if (mLogoView == null) {
      mLogoView = new AppCompatImageView(getContext());
    }
  }
  
  private void ensureMenu()
  {
    ensureMenuView();
    if (mMenuView.peekMenu() == null)
    {
      MenuBuilder localMenuBuilder = (MenuBuilder)mMenuView.getMenu();
      if (mExpandedMenuPresenter == null) {
        mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter();
      }
      mMenuView.setExpandedActionViewsExclusive(true);
      localMenuBuilder.addMenuPresenter(mExpandedMenuPresenter, mPopupContext);
    }
  }
  
  private void ensureMenuView()
  {
    if (mMenuView == null)
    {
      mMenuView = new ActionMenuView(getContext());
      mMenuView.setPopupTheme(mPopupTheme);
      mMenuView.setOnMenuItemClickListener(mMenuViewItemClickListener);
      mMenuView.setMenuCallbacks(mActionMenuPresenterCallback, mMenuBuilderCallback);
      LayoutParams localLayoutParams = generateDefaultLayoutParams();
      gravity = (0x800005 | mButtonGravity & 0x70);
      mMenuView.setLayoutParams(localLayoutParams);
      addSystemView(mMenuView, false);
    }
  }
  
  private void ensureNavButtonView()
  {
    if (mNavButtonView == null)
    {
      mNavButtonView = new AppCompatImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
      LayoutParams localLayoutParams = generateDefaultLayoutParams();
      gravity = (0x800003 | mButtonGravity & 0x70);
      mNavButtonView.setLayoutParams(localLayoutParams);
    }
  }
  
  private int getChildHorizontalGravity(int paramInt)
  {
    int i = ViewCompat.getLayoutDirection(this);
    int j = GravityCompat.getAbsoluteGravity(paramInt, i) & 0x7;
    if (j != 1)
    {
      paramInt = 3;
      if ((j != 3) && (j != 5))
      {
        if (i == 1) {
          paramInt = 5;
        }
        return paramInt;
      }
    }
    return j;
  }
  
  private int getChildTop(View paramView, int paramInt)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    int j = paramView.getMeasuredHeight();
    if (paramInt > 0) {
      paramInt = (j - paramInt) / 2;
    } else {
      paramInt = 0;
    }
    int i = getChildVerticalGravity(gravity);
    if (i != 48)
    {
      if (i != 80)
      {
        int k = getPaddingTop();
        paramInt = getPaddingBottom();
        int m = getHeight();
        i = (m - k - paramInt - j) / 2;
        if (i < topMargin)
        {
          paramInt = topMargin;
        }
        else
        {
          j = m - paramInt - j - i - k;
          paramInt = i;
          if (j < bottomMargin) {
            paramInt = Math.max(0, i - (bottomMargin - j));
          }
        }
        return k + paramInt;
      }
      return getHeight() - getPaddingBottom() - j - bottomMargin - paramInt;
    }
    return getPaddingTop() - paramInt;
  }
  
  private int getChildVerticalGravity(int paramInt)
  {
    paramInt &= 0x70;
    if ((paramInt != 16) && (paramInt != 48) && (paramInt != 80)) {
      return mGravity & 0x70;
    }
    return paramInt;
  }
  
  private int getHorizontalMargins(View paramView)
  {
    paramView = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    return MarginLayoutParamsCompat.getMarginStart(paramView) + MarginLayoutParamsCompat.getMarginEnd(paramView);
  }
  
  private MenuInflater getMenuInflater()
  {
    return new SupportMenuInflater(getContext());
  }
  
  private int getVerticalMargins(View paramView)
  {
    paramView = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    return topMargin + bottomMargin;
  }
  
  private int getViewListMeasuredWidth(List<View> paramList, int[] paramArrayOfInt)
  {
    int m = paramArrayOfInt[0];
    int k = paramArrayOfInt[1];
    int n = paramList.size();
    int i = 0;
    int j = i;
    while (i < n)
    {
      paramArrayOfInt = (View)paramList.get(i);
      LayoutParams localLayoutParams = (LayoutParams)paramArrayOfInt.getLayoutParams();
      m = leftMargin - m;
      k = rightMargin - k;
      int i1 = Math.max(0, m);
      int i2 = Math.max(0, k);
      m = Math.max(0, -m);
      k = Math.max(0, -k);
      j += i1 + paramArrayOfInt.getMeasuredWidth() + i2;
      i += 1;
    }
    return j;
  }
  
  private boolean isChildOrHidden(View paramView)
  {
    return (paramView.getParent() == this) || (mHiddenViews.contains(paramView));
  }
  
  private static boolean isCustomView(View paramView)
  {
    return getLayoutParamsmViewType == 0;
  }
  
  private int layoutChildLeft(View paramView, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    int i = leftMargin - paramArrayOfInt[0];
    paramInt1 += Math.max(0, i);
    paramArrayOfInt[0] = Math.max(0, -i);
    paramInt2 = getChildTop(paramView, paramInt2);
    i = paramView.getMeasuredWidth();
    paramView.layout(paramInt1, paramInt2, paramInt1 + i, paramView.getMeasuredHeight() + paramInt2);
    return paramInt1 + (i + rightMargin);
  }
  
  private int layoutChildRight(View paramView, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    int i = rightMargin - paramArrayOfInt[1];
    paramInt1 -= Math.max(0, i);
    paramArrayOfInt[1] = Math.max(0, -i);
    paramInt2 = getChildTop(paramView, paramInt2);
    i = paramView.getMeasuredWidth();
    paramView.layout(paramInt1 - i, paramInt2, paramInt1, paramView.getMeasuredHeight() + paramInt2);
    return paramInt1 - (i + leftMargin);
  }
  
  private int measureChildCollapseMargins(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    int i = leftMargin - paramArrayOfInt[0];
    int j = rightMargin - paramArrayOfInt[1];
    int k = Math.max(0, i) + Math.max(0, j);
    paramArrayOfInt[0] = Math.max(0, -i);
    paramArrayOfInt[1] = Math.max(0, -j);
    paramView.measure(getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight() + k + paramInt2, width), getChildMeasureSpec(paramInt3, getPaddingTop() + getPaddingBottom() + topMargin + bottomMargin + paramInt4, height));
    return paramView.getMeasuredWidth() + k;
  }
  
  private void measureChildConstrained(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    int i = getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight() + leftMargin + rightMargin + paramInt2, width);
    paramInt2 = getChildMeasureSpec(paramInt3, getPaddingTop() + getPaddingBottom() + topMargin + bottomMargin + paramInt4, height);
    paramInt3 = View.MeasureSpec.getMode(paramInt2);
    paramInt1 = paramInt2;
    if (paramInt3 != 1073741824)
    {
      paramInt1 = paramInt2;
      if (paramInt5 >= 0)
      {
        paramInt1 = paramInt5;
        if (paramInt3 != 0) {
          paramInt1 = Math.min(View.MeasureSpec.getSize(paramInt2), paramInt5);
        }
        paramInt1 = View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824);
      }
    }
    paramView.measure(i, paramInt1);
  }
  
  private void postShowOverflowMenu()
  {
    removeCallbacks(mShowOverflowMenuRunnable);
    post(mShowOverflowMenuRunnable);
  }
  
  private boolean shouldCollapse()
  {
    if (!mCollapsible) {
      return false;
    }
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = getChildAt(i);
      if ((shouldLayout(localView)) && (localView.getMeasuredWidth() > 0) && (localView.getMeasuredHeight() > 0)) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private boolean shouldLayout(View paramView)
  {
    return (paramView != null) && (paramView.getParent() == this) && (paramView.getVisibility() != 8);
  }
  
  void addChildrenForExpandedActionView()
  {
    int i = mHiddenViews.size() - 1;
    while (i >= 0)
    {
      addView((View)mHiddenViews.get(i));
      i -= 1;
    }
    mHiddenViews.clear();
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public boolean canShowOverflowMenu()
  {
    return (getVisibility() == 0) && (mMenuView != null) && (mMenuView.isOverflowReserved());
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return (super.checkLayoutParams(paramLayoutParams)) && ((paramLayoutParams instanceof LayoutParams));
  }
  
  public void collapseActionView()
  {
    MenuItemImpl localMenuItemImpl;
    if (mExpandedMenuPresenter == null) {
      localMenuItemImpl = null;
    } else {
      localMenuItemImpl = mExpandedMenuPresenter.mCurrentExpandedItem;
    }
    if (localMenuItemImpl != null) {
      localMenuItemImpl.collapseActionView();
    }
  }
  
  public void dismissPopupMenus()
  {
    if (mMenuView != null) {
      mMenuView.dismissPopupMenus();
    }
  }
  
  void ensureCollapseButtonView()
  {
    if (mCollapseButtonView == null)
    {
      mCollapseButtonView = new AppCompatImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
      mCollapseButtonView.setImageDrawable(mCollapseIcon);
      mCollapseButtonView.setContentDescription(mCollapseDescription);
      LayoutParams localLayoutParams = generateDefaultLayoutParams();
      gravity = (0x800003 | mButtonGravity & 0x70);
      mViewType = 2;
      mCollapseButtonView.setLayoutParams(localLayoutParams);
      mCollapseButtonView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          collapseActionView();
        }
      });
    }
  }
  
  protected LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams(-2, -2);
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof LayoutParams)) {
      return new LayoutParams((LayoutParams)paramLayoutParams);
    }
    if ((paramLayoutParams instanceof ActionBar.LayoutParams)) {
      return new LayoutParams((ActionBar.LayoutParams)paramLayoutParams);
    }
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
      return new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
    }
    return new LayoutParams(paramLayoutParams);
  }
  
  public int getContentInsetEnd()
  {
    if (mContentInsets != null) {
      return mContentInsets.getEnd();
    }
    return 0;
  }
  
  public int getContentInsetEndWithActions()
  {
    if (mContentInsetEndWithActions != Integer.MIN_VALUE) {
      return mContentInsetEndWithActions;
    }
    return getContentInsetEnd();
  }
  
  public int getContentInsetLeft()
  {
    if (mContentInsets != null) {
      return mContentInsets.getLeft();
    }
    return 0;
  }
  
  public int getContentInsetRight()
  {
    if (mContentInsets != null) {
      return mContentInsets.getRight();
    }
    return 0;
  }
  
  public int getContentInsetStart()
  {
    if (mContentInsets != null) {
      return mContentInsets.getStart();
    }
    return 0;
  }
  
  public int getContentInsetStartWithNavigation()
  {
    if (mContentInsetStartWithNavigation != Integer.MIN_VALUE) {
      return mContentInsetStartWithNavigation;
    }
    return getContentInsetStart();
  }
  
  public int getCurrentContentInsetEnd()
  {
    if (mMenuView != null)
    {
      MenuBuilder localMenuBuilder = mMenuView.peekMenu();
      if ((localMenuBuilder != null) && (localMenuBuilder.hasVisibleItems()))
      {
        i = 1;
        break label33;
      }
    }
    int i = 0;
    label33:
    if (i != 0) {
      return Math.max(getContentInsetEnd(), Math.max(mContentInsetEndWithActions, 0));
    }
    return getContentInsetEnd();
  }
  
  public int getCurrentContentInsetLeft()
  {
    if (ViewCompat.getLayoutDirection(this) == 1) {
      return getCurrentContentInsetEnd();
    }
    return getCurrentContentInsetStart();
  }
  
  public int getCurrentContentInsetRight()
  {
    if (ViewCompat.getLayoutDirection(this) == 1) {
      return getCurrentContentInsetStart();
    }
    return getCurrentContentInsetEnd();
  }
  
  public int getCurrentContentInsetStart()
  {
    if (getNavigationIcon() != null) {
      return Math.max(getContentInsetStart(), Math.max(mContentInsetStartWithNavigation, 0));
    }
    return getContentInsetStart();
  }
  
  public Drawable getLogo()
  {
    if (mLogoView != null) {
      return mLogoView.getDrawable();
    }
    return null;
  }
  
  public CharSequence getLogoDescription()
  {
    if (mLogoView != null) {
      return mLogoView.getContentDescription();
    }
    return null;
  }
  
  public Menu getMenu()
  {
    ensureMenu();
    return mMenuView.getMenu();
  }
  
  @Nullable
  public CharSequence getNavigationContentDescription()
  {
    if (mNavButtonView != null) {
      return mNavButtonView.getContentDescription();
    }
    return null;
  }
  
  @Nullable
  public Drawable getNavigationIcon()
  {
    if (mNavButtonView != null) {
      return mNavButtonView.getDrawable();
    }
    return null;
  }
  
  ActionMenuPresenter getOuterActionMenuPresenter()
  {
    return mOuterActionMenuPresenter;
  }
  
  @Nullable
  public Drawable getOverflowIcon()
  {
    ensureMenu();
    return mMenuView.getOverflowIcon();
  }
  
  Context getPopupContext()
  {
    return mPopupContext;
  }
  
  public int getPopupTheme()
  {
    return mPopupTheme;
  }
  
  public CharSequence getSubtitle()
  {
    return mSubtitleText;
  }
  
  public CharSequence getTitle()
  {
    return mTitleText;
  }
  
  public int getTitleMarginBottom()
  {
    return mTitleMarginBottom;
  }
  
  public int getTitleMarginEnd()
  {
    return mTitleMarginEnd;
  }
  
  public int getTitleMarginStart()
  {
    return mTitleMarginStart;
  }
  
  public int getTitleMarginTop()
  {
    return mTitleMarginTop;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public DecorToolbar getWrapper()
  {
    if (mWrapper == null) {
      mWrapper = new ToolbarWidgetWrapper(this, true);
    }
    return mWrapper;
  }
  
  public boolean hasExpandedActionView()
  {
    return (mExpandedMenuPresenter != null) && (mExpandedMenuPresenter.mCurrentExpandedItem != null);
  }
  
  public boolean hideOverflowMenu()
  {
    return (mMenuView != null) && (mMenuView.hideOverflowMenu());
  }
  
  public void inflateMenu(@MenuRes int paramInt)
  {
    getMenuInflater().inflate(paramInt, getMenu());
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public boolean isOverflowMenuShowPending()
  {
    return (mMenuView != null) && (mMenuView.isOverflowMenuShowPending());
  }
  
  public boolean isOverflowMenuShowing()
  {
    return (mMenuView != null) && (mMenuView.isOverflowMenuShowing());
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public boolean isTitleTruncated()
  {
    if (mTitleTextView == null) {
      return false;
    }
    Layout localLayout = mTitleTextView.getLayout();
    if (localLayout == null) {
      return false;
    }
    int j = localLayout.getLineCount();
    int i = 0;
    while (i < j)
    {
      if (localLayout.getEllipsisCount(i) > 0) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    removeCallbacks(mShowOverflowMenuRunnable);
  }
  
  public boolean onHoverEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionMasked();
    if (i == 9) {
      mEatingHover = false;
    }
    if (!mEatingHover)
    {
      boolean bool = super.onHoverEvent(paramMotionEvent);
      if ((i == 9) && (!bool)) {
        mEatingHover = true;
      }
    }
    if ((i == 10) || (i == 3)) {
      mEatingHover = false;
    }
    return true;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (ViewCompat.getLayoutDirection(this) == 1) {
      k = 1;
    } else {
      k = 0;
    }
    int n = getWidth();
    int i3 = getHeight();
    int i = getPaddingLeft();
    int i1 = getPaddingRight();
    int i2 = getPaddingTop();
    int i4 = getPaddingBottom();
    int m = n - i1;
    int[] arrayOfInt = mTempMargins;
    arrayOfInt[1] = 0;
    arrayOfInt[0] = 0;
    paramInt1 = ViewCompat.getMinimumHeight(this);
    if (paramInt1 >= 0) {
      paramInt4 = Math.min(paramInt1, paramInt4 - paramInt2);
    } else {
      paramInt4 = 0;
    }
    if (shouldLayout(mNavButtonView))
    {
      if (k != 0)
      {
        paramInt3 = layoutChildRight(mNavButtonView, m, arrayOfInt, paramInt4);
        j = i;
        break label167;
      }
      j = layoutChildLeft(mNavButtonView, i, arrayOfInt, paramInt4);
    }
    else
    {
      j = i;
    }
    paramInt3 = m;
    label167:
    paramInt1 = paramInt3;
    paramInt2 = j;
    if (shouldLayout(mCollapseButtonView)) {
      if (k != 0)
      {
        paramInt1 = layoutChildRight(mCollapseButtonView, paramInt3, arrayOfInt, paramInt4);
        paramInt2 = j;
      }
      else
      {
        paramInt2 = layoutChildLeft(mCollapseButtonView, j, arrayOfInt, paramInt4);
        paramInt1 = paramInt3;
      }
    }
    paramInt3 = paramInt1;
    int j = paramInt2;
    if (shouldLayout(mMenuView)) {
      if (k != 0)
      {
        j = layoutChildLeft(mMenuView, paramInt2, arrayOfInt, paramInt4);
        paramInt3 = paramInt1;
      }
      else
      {
        paramInt3 = layoutChildRight(mMenuView, paramInt1, arrayOfInt, paramInt4);
        j = paramInt2;
      }
    }
    paramInt2 = getCurrentContentInsetLeft();
    paramInt1 = getCurrentContentInsetRight();
    arrayOfInt[0] = Math.max(0, paramInt2 - j);
    arrayOfInt[1] = Math.max(0, paramInt1 - (m - paramInt3));
    paramInt2 = Math.max(j, paramInt2);
    paramInt3 = Math.min(paramInt3, m - paramInt1);
    paramInt1 = paramInt2;
    j = paramInt3;
    if (shouldLayout(mExpandedActionView)) {
      if (k != 0)
      {
        j = layoutChildRight(mExpandedActionView, paramInt3, arrayOfInt, paramInt4);
        paramInt1 = paramInt2;
      }
      else
      {
        paramInt1 = layoutChildLeft(mExpandedActionView, paramInt2, arrayOfInt, paramInt4);
        j = paramInt3;
      }
    }
    paramInt3 = paramInt1;
    paramInt2 = j;
    if (shouldLayout(mLogoView)) {
      if (k != 0)
      {
        paramInt2 = layoutChildRight(mLogoView, j, arrayOfInt, paramInt4);
        paramInt3 = paramInt1;
      }
      else
      {
        paramInt3 = layoutChildLeft(mLogoView, paramInt1, arrayOfInt, paramInt4);
        paramInt2 = j;
      }
    }
    paramBoolean = shouldLayout(mTitleTextView);
    boolean bool = shouldLayout(mSubtitleTextView);
    Object localObject1;
    if (paramBoolean)
    {
      localObject1 = (LayoutParams)mTitleTextView.getLayoutParams();
      paramInt1 = topMargin + mTitleTextView.getMeasuredHeight() + bottomMargin + 0;
    }
    else
    {
      paramInt1 = 0;
    }
    if (bool)
    {
      localObject1 = (LayoutParams)mSubtitleTextView.getLayoutParams();
      paramInt1 += topMargin + mSubtitleTextView.getMeasuredHeight() + bottomMargin;
    }
    if ((!paramBoolean) && (!bool)) {}
    for (paramInt1 = paramInt3;; paramInt1 = paramInt3)
    {
      paramInt3 = paramInt2;
      break label1321;
      if (paramBoolean) {
        localObject1 = mTitleTextView;
      } else {
        localObject1 = mSubtitleTextView;
      }
      if (bool) {
        localObject2 = mSubtitleTextView;
      } else {
        localObject2 = mTitleTextView;
      }
      localObject1 = (LayoutParams)((View)localObject1).getLayoutParams();
      Object localObject2 = (LayoutParams)((View)localObject2).getLayoutParams();
      if (((paramBoolean) && (mTitleTextView.getMeasuredWidth() > 0)) || ((bool) && (mSubtitleTextView.getMeasuredWidth() > 0))) {
        j = 1;
      } else {
        j = 0;
      }
      m = mGravity & 0x70;
      if (m != 48)
      {
        if (m != 80)
        {
          m = (i3 - i2 - i4 - paramInt1) / 2;
          if (m < topMargin + mTitleMarginTop)
          {
            paramInt1 = topMargin + mTitleMarginTop;
          }
          else
          {
            i3 = i3 - i4 - paramInt1 - m - i2;
            paramInt1 = m;
            if (i3 < bottomMargin + mTitleMarginBottom) {
              paramInt1 = Math.max(0, m - (bottomMargin + mTitleMarginBottom - i3));
            }
          }
          paramInt1 = i2 + paramInt1;
        }
        else
        {
          paramInt1 = i3 - i4 - bottomMargin - mTitleMarginBottom - paramInt1;
        }
      }
      else {
        paramInt1 = getPaddingTop() + topMargin + mTitleMarginTop;
      }
      if (k == 0) {
        break;
      }
      if (j != 0) {
        k = mTitleMarginStart;
      } else {
        k = 0;
      }
      k -= arrayOfInt[1];
      paramInt2 -= Math.max(0, k);
      arrayOfInt[1] = Math.max(0, -k);
      if (paramBoolean)
      {
        localObject1 = (LayoutParams)mTitleTextView.getLayoutParams();
        m = paramInt2 - mTitleTextView.getMeasuredWidth();
        k = mTitleTextView.getMeasuredHeight() + paramInt1;
        mTitleTextView.layout(m, paramInt1, paramInt2, k);
        paramInt1 = m - mTitleMarginEnd;
        m = k + bottomMargin;
      }
      else
      {
        k = paramInt2;
        m = paramInt1;
        paramInt1 = k;
      }
      if (bool)
      {
        localObject1 = (LayoutParams)mSubtitleTextView.getLayoutParams();
        k = m + topMargin;
        m = mSubtitleTextView.getMeasuredWidth();
        i2 = mSubtitleTextView.getMeasuredHeight();
        mSubtitleTextView.layout(paramInt2 - m, k, paramInt2, i2 + k);
        k = paramInt2 - mTitleMarginEnd;
        m = bottomMargin;
      }
      else
      {
        k = paramInt2;
      }
      if (j != 0) {
        paramInt2 = Math.min(paramInt1, k);
      }
    }
    if (j != 0) {
      k = mTitleMarginStart;
    } else {
      k = 0;
    }
    k -= arrayOfInt[0];
    paramInt3 += Math.max(0, k);
    arrayOfInt[0] = Math.max(0, -k);
    if (paramBoolean)
    {
      localObject1 = (LayoutParams)mTitleTextView.getLayoutParams();
      k = mTitleTextView.getMeasuredWidth() + paramInt3;
      m = mTitleTextView.getMeasuredHeight() + paramInt1;
      mTitleTextView.layout(paramInt3, paramInt1, k, m);
      k += mTitleMarginEnd;
      paramInt1 = m + bottomMargin;
    }
    else
    {
      k = paramInt3;
    }
    if (bool)
    {
      localObject1 = (LayoutParams)mSubtitleTextView.getLayoutParams();
      paramInt1 += topMargin;
      m = mSubtitleTextView.getMeasuredWidth() + paramInt3;
      i2 = mSubtitleTextView.getMeasuredHeight();
      mSubtitleTextView.layout(paramInt3, paramInt1, m, i2 + paramInt1);
      m += mTitleMarginEnd;
      paramInt1 = bottomMargin;
    }
    else
    {
      m = paramInt3;
    }
    paramInt1 = paramInt3;
    paramInt3 = paramInt2;
    if (j != 0)
    {
      paramInt1 = Math.max(k, m);
      paramInt3 = paramInt2;
    }
    label1321:
    j = i;
    i = 0;
    addCustomViewsWithGravity(mTempViews, 3);
    int k = mTempViews.size();
    paramInt2 = 0;
    while (paramInt2 < k)
    {
      paramInt1 = layoutChildLeft((View)mTempViews.get(paramInt2), paramInt1, arrayOfInt, paramInt4);
      paramInt2 += 1;
    }
    addCustomViewsWithGravity(mTempViews, 5);
    k = mTempViews.size();
    paramInt2 = 0;
    while (paramInt2 < k)
    {
      paramInt3 = layoutChildRight((View)mTempViews.get(paramInt2), paramInt3, arrayOfInt, paramInt4);
      paramInt2 += 1;
    }
    addCustomViewsWithGravity(mTempViews, 1);
    k = getViewListMeasuredWidth(mTempViews, arrayOfInt);
    paramInt2 = j + (n - j - i1) / 2 - k / 2;
    j = k + paramInt2;
    if (paramInt2 >= paramInt1) {
      if (j > paramInt3) {
        paramInt1 = paramInt2 - (j - paramInt3);
      } else {
        paramInt1 = paramInt2;
      }
    }
    paramInt3 = mTempViews.size();
    paramInt2 = i;
    while (paramInt2 < paramInt3)
    {
      paramInt1 = layoutChildLeft((View)mTempViews.get(paramInt2), paramInt1, arrayOfInt, paramInt4);
      paramInt2 += 1;
    }
    mTempViews.clear();
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int[] arrayOfInt = mTempMargins;
    if (ViewUtils.isLayoutRtl(this))
    {
      i1 = 1;
      n = 0;
    }
    else
    {
      n = 1;
      i1 = 0;
    }
    if (shouldLayout(mNavButtonView))
    {
      measureChildConstrained(mNavButtonView, paramInt1, 0, paramInt2, 0, mMaxButtonHeight);
      i = mNavButtonView.getMeasuredWidth() + getHorizontalMargins(mNavButtonView);
      m = Math.max(0, mNavButtonView.getMeasuredHeight() + getVerticalMargins(mNavButtonView));
      k = View.combineMeasuredStates(0, mNavButtonView.getMeasuredState());
    }
    else
    {
      i = 0;
      m = i;
      k = m;
    }
    int i2 = i;
    int j = m;
    int i = k;
    if (shouldLayout(mCollapseButtonView))
    {
      measureChildConstrained(mCollapseButtonView, paramInt1, 0, paramInt2, 0, mMaxButtonHeight);
      i2 = mCollapseButtonView.getMeasuredWidth() + getHorizontalMargins(mCollapseButtonView);
      j = Math.max(m, mCollapseButtonView.getMeasuredHeight() + getVerticalMargins(mCollapseButtonView));
      i = View.combineMeasuredStates(k, mCollapseButtonView.getMeasuredState());
    }
    int k = getCurrentContentInsetStart();
    int m = 0 + Math.max(k, i2);
    arrayOfInt[i1] = Math.max(0, k - i2);
    if (shouldLayout(mMenuView))
    {
      measureChildConstrained(mMenuView, paramInt1, m, paramInt2, 0, mMaxButtonHeight);
      k = mMenuView.getMeasuredWidth() + getHorizontalMargins(mMenuView);
      j = Math.max(j, mMenuView.getMeasuredHeight() + getVerticalMargins(mMenuView));
      i = View.combineMeasuredStates(i, mMenuView.getMeasuredState());
    }
    else
    {
      k = 0;
    }
    i2 = getCurrentContentInsetEnd();
    int i1 = m + Math.max(i2, k);
    arrayOfInt[n] = Math.max(0, i2 - k);
    int n = i1;
    m = j;
    k = i;
    if (shouldLayout(mExpandedActionView))
    {
      n = i1 + measureChildCollapseMargins(mExpandedActionView, paramInt1, i1, paramInt2, 0, arrayOfInt);
      m = Math.max(j, mExpandedActionView.getMeasuredHeight() + getVerticalMargins(mExpandedActionView));
      k = View.combineMeasuredStates(i, mExpandedActionView.getMeasuredState());
    }
    j = n;
    i1 = m;
    i = k;
    if (shouldLayout(mLogoView))
    {
      j = n + measureChildCollapseMargins(mLogoView, paramInt1, n, paramInt2, 0, arrayOfInt);
      i1 = Math.max(m, mLogoView.getMeasuredHeight() + getVerticalMargins(mLogoView));
      i = View.combineMeasuredStates(k, mLogoView.getMeasuredState());
    }
    int i3 = getChildCount();
    n = i1;
    k = 0;
    m = j;
    j = k;
    while (j < i3)
    {
      View localView = getChildAt(j);
      i2 = m;
      i1 = i;
      k = n;
      if (getLayoutParamsmViewType == 0) {
        if (!shouldLayout(localView))
        {
          i2 = m;
          i1 = i;
          k = n;
        }
        else
        {
          i2 = m + measureChildCollapseMargins(localView, paramInt1, m, paramInt2, 0, arrayOfInt);
          k = Math.max(n, localView.getMeasuredHeight() + getVerticalMargins(localView));
          i1 = View.combineMeasuredStates(i, localView.getMeasuredState());
        }
      }
      j += 1;
      m = i2;
      i = i1;
      n = k;
    }
    i1 = mTitleMarginTop + mTitleMarginBottom;
    i2 = mTitleMarginStart + mTitleMarginEnd;
    if (shouldLayout(mTitleTextView))
    {
      measureChildCollapseMargins(mTitleTextView, paramInt1, m + i2, paramInt2, i1, arrayOfInt);
      j = mTitleTextView.getMeasuredWidth();
      i3 = getHorizontalMargins(mTitleTextView);
      k = mTitleTextView.getMeasuredHeight();
      int i4 = getVerticalMargins(mTitleTextView);
      i = View.combineMeasuredStates(i, mTitleTextView.getMeasuredState());
      k += i4;
      j += i3;
    }
    else
    {
      k = 0;
      j = k;
    }
    if (shouldLayout(mSubtitleTextView))
    {
      j = Math.max(j, measureChildCollapseMargins(mSubtitleTextView, paramInt1, m + i2, paramInt2, k + i1, arrayOfInt));
      k += mSubtitleTextView.getMeasuredHeight() + getVerticalMargins(mSubtitleTextView);
      i = View.combineMeasuredStates(i, mSubtitleTextView.getMeasuredState());
    }
    k = Math.max(n, k);
    i2 = getPaddingLeft();
    i3 = getPaddingRight();
    n = getPaddingTop();
    i1 = getPaddingBottom();
    j = View.resolveSizeAndState(Math.max(m + j + (i2 + i3), getSuggestedMinimumWidth()), paramInt1, 0xFF000000 & i);
    paramInt1 = View.resolveSizeAndState(Math.max(k + (n + i1), getSuggestedMinimumHeight()), paramInt2, i << 16);
    if (shouldCollapse()) {
      paramInt1 = 0;
    }
    setMeasuredDimension(j, paramInt1);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof SavedState))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    SavedState localSavedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(localSavedState.getSuperState());
    if (mMenuView != null) {
      paramParcelable = mMenuView.peekMenu();
    } else {
      paramParcelable = null;
    }
    if ((expandedMenuItemId != 0) && (mExpandedMenuPresenter != null) && (paramParcelable != null))
    {
      paramParcelable = paramParcelable.findItem(expandedMenuItemId);
      if (paramParcelable != null) {
        paramParcelable.expandActionView();
      }
    }
    if (isOverflowOpen) {
      postShowOverflowMenu();
    }
  }
  
  public void onRtlPropertiesChanged(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      super.onRtlPropertiesChanged(paramInt);
    }
    ensureContentInsets();
    RtlSpacingHelper localRtlSpacingHelper = mContentInsets;
    boolean bool = true;
    if (paramInt != 1) {
      bool = false;
    }
    localRtlSpacingHelper.setDirection(bool);
  }
  
  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    if ((mExpandedMenuPresenter != null) && (mExpandedMenuPresenter.mCurrentExpandedItem != null)) {
      expandedMenuItemId = mExpandedMenuPresenter.mCurrentExpandedItem.getItemId();
    }
    isOverflowOpen = isOverflowMenuShowing();
    return localSavedState;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionMasked();
    if (i == 0) {
      mEatingTouch = false;
    }
    if (!mEatingTouch)
    {
      boolean bool = super.onTouchEvent(paramMotionEvent);
      if ((i == 0) && (!bool)) {
        mEatingTouch = true;
      }
    }
    if ((i == 1) || (i == 3)) {
      mEatingTouch = false;
    }
    return true;
  }
  
  void removeChildrenForExpandedActionView()
  {
    int i = getChildCount() - 1;
    while (i >= 0)
    {
      View localView = getChildAt(i);
      if ((getLayoutParamsmViewType != 2) && (localView != mMenuView))
      {
        removeViewAt(i);
        mHiddenViews.add(localView);
      }
      i -= 1;
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void setCollapsible(boolean paramBoolean)
  {
    mCollapsible = paramBoolean;
    requestLayout();
  }
  
  public void setContentInsetEndWithActions(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = Integer.MIN_VALUE;
    }
    if (i != mContentInsetEndWithActions)
    {
      mContentInsetEndWithActions = i;
      if (getNavigationIcon() != null) {
        requestLayout();
      }
    }
  }
  
  public void setContentInsetStartWithNavigation(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = Integer.MIN_VALUE;
    }
    if (i != mContentInsetStartWithNavigation)
    {
      mContentInsetStartWithNavigation = i;
      if (getNavigationIcon() != null) {
        requestLayout();
      }
    }
  }
  
  public void setContentInsetsAbsolute(int paramInt1, int paramInt2)
  {
    ensureContentInsets();
    mContentInsets.setAbsolute(paramInt1, paramInt2);
  }
  
  public void setContentInsetsRelative(int paramInt1, int paramInt2)
  {
    ensureContentInsets();
    mContentInsets.setRelative(paramInt1, paramInt2);
  }
  
  public void setLogo(@DrawableRes int paramInt)
  {
    setLogo(AppCompatResources.getDrawable(getContext(), paramInt));
  }
  
  public void setLogo(Drawable paramDrawable)
  {
    if (paramDrawable != null)
    {
      ensureLogoView();
      if (!isChildOrHidden(mLogoView)) {
        addSystemView(mLogoView, true);
      }
    }
    else if ((mLogoView != null) && (isChildOrHidden(mLogoView)))
    {
      removeView(mLogoView);
      mHiddenViews.remove(mLogoView);
    }
    if (mLogoView != null) {
      mLogoView.setImageDrawable(paramDrawable);
    }
  }
  
  public void setLogoDescription(@StringRes int paramInt)
  {
    setLogoDescription(getContext().getText(paramInt));
  }
  
  public void setLogoDescription(CharSequence paramCharSequence)
  {
    if (!TextUtils.isEmpty(paramCharSequence)) {
      ensureLogoView();
    }
    if (mLogoView != null) {
      mLogoView.setContentDescription(paramCharSequence);
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void setMenu(MenuBuilder paramMenuBuilder, ActionMenuPresenter paramActionMenuPresenter)
  {
    if ((paramMenuBuilder == null) && (mMenuView == null)) {
      return;
    }
    ensureMenuView();
    MenuBuilder localMenuBuilder = mMenuView.peekMenu();
    if (localMenuBuilder == paramMenuBuilder) {
      return;
    }
    if (localMenuBuilder != null)
    {
      localMenuBuilder.removeMenuPresenter(mOuterActionMenuPresenter);
      localMenuBuilder.removeMenuPresenter(mExpandedMenuPresenter);
    }
    if (mExpandedMenuPresenter == null) {
      mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter();
    }
    paramActionMenuPresenter.setExpandedActionViewsExclusive(true);
    if (paramMenuBuilder != null)
    {
      paramMenuBuilder.addMenuPresenter(paramActionMenuPresenter, mPopupContext);
      paramMenuBuilder.addMenuPresenter(mExpandedMenuPresenter, mPopupContext);
    }
    else
    {
      paramActionMenuPresenter.initForMenu(mPopupContext, null);
      mExpandedMenuPresenter.initForMenu(mPopupContext, null);
      paramActionMenuPresenter.updateMenuView(true);
      mExpandedMenuPresenter.updateMenuView(true);
    }
    mMenuView.setPopupTheme(mPopupTheme);
    mMenuView.setPresenter(paramActionMenuPresenter);
    mOuterActionMenuPresenter = paramActionMenuPresenter;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void setMenuCallbacks(MenuPresenter.Callback paramCallback, MenuBuilder.Callback paramCallback1)
  {
    mActionMenuPresenterCallback = paramCallback;
    mMenuBuilderCallback = paramCallback1;
    if (mMenuView != null) {
      mMenuView.setMenuCallbacks(paramCallback, paramCallback1);
    }
  }
  
  public void setNavigationContentDescription(@StringRes int paramInt)
  {
    CharSequence localCharSequence;
    if (paramInt != 0) {
      localCharSequence = getContext().getText(paramInt);
    } else {
      localCharSequence = null;
    }
    setNavigationContentDescription(localCharSequence);
  }
  
  public void setNavigationContentDescription(@Nullable CharSequence paramCharSequence)
  {
    if (!TextUtils.isEmpty(paramCharSequence)) {
      ensureNavButtonView();
    }
    if (mNavButtonView != null) {
      mNavButtonView.setContentDescription(paramCharSequence);
    }
  }
  
  public void setNavigationIcon(@DrawableRes int paramInt)
  {
    setNavigationIcon(AppCompatResources.getDrawable(getContext(), paramInt));
  }
  
  public void setNavigationIcon(@Nullable Drawable paramDrawable)
  {
    if (paramDrawable != null)
    {
      ensureNavButtonView();
      if (!isChildOrHidden(mNavButtonView)) {
        addSystemView(mNavButtonView, true);
      }
    }
    else if ((mNavButtonView != null) && (isChildOrHidden(mNavButtonView)))
    {
      removeView(mNavButtonView);
      mHiddenViews.remove(mNavButtonView);
    }
    if (mNavButtonView != null) {
      mNavButtonView.setImageDrawable(paramDrawable);
    }
  }
  
  public void setNavigationOnClickListener(View.OnClickListener paramOnClickListener)
  {
    ensureNavButtonView();
    mNavButtonView.setOnClickListener(paramOnClickListener);
  }
  
  public void setOnMenuItemClickListener(OnMenuItemClickListener paramOnMenuItemClickListener)
  {
    mOnMenuItemClickListener = paramOnMenuItemClickListener;
  }
  
  public void setOverflowIcon(@Nullable Drawable paramDrawable)
  {
    ensureMenu();
    mMenuView.setOverflowIcon(paramDrawable);
  }
  
  public void setPopupTheme(@StyleRes int paramInt)
  {
    if (mPopupTheme != paramInt)
    {
      mPopupTheme = paramInt;
      if (paramInt == 0)
      {
        mPopupContext = getContext();
        return;
      }
      mPopupContext = new ContextThemeWrapper(getContext(), paramInt);
    }
  }
  
  public void setSubtitle(@StringRes int paramInt)
  {
    setSubtitle(getContext().getText(paramInt));
  }
  
  public void setSubtitle(CharSequence paramCharSequence)
  {
    if (!TextUtils.isEmpty(paramCharSequence))
    {
      if (mSubtitleTextView == null)
      {
        Context localContext = getContext();
        mSubtitleTextView = new AppCompatTextView(localContext);
        mSubtitleTextView.setSingleLine();
        mSubtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
        if (mSubtitleTextAppearance != 0) {
          mSubtitleTextView.setTextAppearance(localContext, mSubtitleTextAppearance);
        }
        if (mSubtitleTextColor != 0) {
          mSubtitleTextView.setTextColor(mSubtitleTextColor);
        }
      }
      if (!isChildOrHidden(mSubtitleTextView)) {
        addSystemView(mSubtitleTextView, true);
      }
    }
    else if ((mSubtitleTextView != null) && (isChildOrHidden(mSubtitleTextView)))
    {
      removeView(mSubtitleTextView);
      mHiddenViews.remove(mSubtitleTextView);
    }
    if (mSubtitleTextView != null) {
      mSubtitleTextView.setText(paramCharSequence);
    }
    mSubtitleText = paramCharSequence;
  }
  
  public void setSubtitleTextAppearance(Context paramContext, @StyleRes int paramInt)
  {
    mSubtitleTextAppearance = paramInt;
    if (mSubtitleTextView != null) {
      mSubtitleTextView.setTextAppearance(paramContext, paramInt);
    }
  }
  
  public void setSubtitleTextColor(@ColorInt int paramInt)
  {
    mSubtitleTextColor = paramInt;
    if (mSubtitleTextView != null) {
      mSubtitleTextView.setTextColor(paramInt);
    }
  }
  
  public void setTitle(@StringRes int paramInt)
  {
    setTitle(getContext().getText(paramInt));
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    if (!TextUtils.isEmpty(paramCharSequence))
    {
      if (mTitleTextView == null)
      {
        Context localContext = getContext();
        mTitleTextView = new AppCompatTextView(localContext);
        mTitleTextView.setSingleLine();
        mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
        if (mTitleTextAppearance != 0) {
          mTitleTextView.setTextAppearance(localContext, mTitleTextAppearance);
        }
        if (mTitleTextColor != 0) {
          mTitleTextView.setTextColor(mTitleTextColor);
        }
      }
      if (!isChildOrHidden(mTitleTextView)) {
        addSystemView(mTitleTextView, true);
      }
    }
    else if ((mTitleTextView != null) && (isChildOrHidden(mTitleTextView)))
    {
      removeView(mTitleTextView);
      mHiddenViews.remove(mTitleTextView);
    }
    if (mTitleTextView != null) {
      mTitleTextView.setText(paramCharSequence);
    }
    mTitleText = paramCharSequence;
  }
  
  public void setTitleMargin(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mTitleMarginStart = paramInt1;
    mTitleMarginTop = paramInt2;
    mTitleMarginEnd = paramInt3;
    mTitleMarginBottom = paramInt4;
    requestLayout();
  }
  
  public void setTitleMarginBottom(int paramInt)
  {
    mTitleMarginBottom = paramInt;
    requestLayout();
  }
  
  public void setTitleMarginEnd(int paramInt)
  {
    mTitleMarginEnd = paramInt;
    requestLayout();
  }
  
  public void setTitleMarginStart(int paramInt)
  {
    mTitleMarginStart = paramInt;
    requestLayout();
  }
  
  public void setTitleMarginTop(int paramInt)
  {
    mTitleMarginTop = paramInt;
    requestLayout();
  }
  
  public void setTitleTextAppearance(Context paramContext, @StyleRes int paramInt)
  {
    mTitleTextAppearance = paramInt;
    if (mTitleTextView != null) {
      mTitleTextView.setTextAppearance(paramContext, paramInt);
    }
  }
  
  public void setTitleTextColor(@ColorInt int paramInt)
  {
    mTitleTextColor = paramInt;
    if (mTitleTextView != null) {
      mTitleTextView.setTextColor(paramInt);
    }
  }
  
  public boolean showOverflowMenu()
  {
    return (mMenuView != null) && (mMenuView.showOverflowMenu());
  }
  
  private class ExpandedActionViewMenuPresenter
    implements MenuPresenter
  {
    MenuItemImpl mCurrentExpandedItem;
    MenuBuilder mMenu;
    
    ExpandedActionViewMenuPresenter() {}
    
    public boolean collapseItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
    {
      if ((mExpandedActionView instanceof CollapsibleActionView)) {
        ((CollapsibleActionView)mExpandedActionView).onActionViewCollapsed();
      }
      removeView(mExpandedActionView);
      removeView(mCollapseButtonView);
      mExpandedActionView = null;
      addChildrenForExpandedActionView();
      mCurrentExpandedItem = null;
      requestLayout();
      paramMenuItemImpl.setActionViewExpanded(false);
      return true;
    }
    
    public boolean expandItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
    {
      ensureCollapseButtonView();
      paramMenuBuilder = mCollapseButtonView.getParent();
      if (paramMenuBuilder != Toolbar.this)
      {
        if ((paramMenuBuilder instanceof ViewGroup)) {
          ((ViewGroup)paramMenuBuilder).removeView(mCollapseButtonView);
        }
        addView(mCollapseButtonView);
      }
      mExpandedActionView = paramMenuItemImpl.getActionView();
      mCurrentExpandedItem = paramMenuItemImpl;
      paramMenuBuilder = mExpandedActionView.getParent();
      if (paramMenuBuilder != Toolbar.this)
      {
        if ((paramMenuBuilder instanceof ViewGroup)) {
          ((ViewGroup)paramMenuBuilder).removeView(mExpandedActionView);
        }
        paramMenuBuilder = generateDefaultLayoutParams();
        gravity = (0x800003 | mButtonGravity & 0x70);
        mViewType = 2;
        mExpandedActionView.setLayoutParams(paramMenuBuilder);
        addView(mExpandedActionView);
      }
      removeChildrenForExpandedActionView();
      requestLayout();
      paramMenuItemImpl.setActionViewExpanded(true);
      if ((mExpandedActionView instanceof CollapsibleActionView)) {
        ((CollapsibleActionView)mExpandedActionView).onActionViewExpanded();
      }
      return true;
    }
    
    public boolean flagActionItems()
    {
      return false;
    }
    
    public int getId()
    {
      return 0;
    }
    
    public MenuView getMenuView(ViewGroup paramViewGroup)
    {
      return null;
    }
    
    public void initForMenu(Context paramContext, MenuBuilder paramMenuBuilder)
    {
      if ((mMenu != null) && (mCurrentExpandedItem != null)) {
        mMenu.collapseItemActionView(mCurrentExpandedItem);
      }
      mMenu = paramMenuBuilder;
    }
    
    public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean) {}
    
    public void onRestoreInstanceState(Parcelable paramParcelable) {}
    
    public Parcelable onSaveInstanceState()
    {
      return null;
    }
    
    public boolean onSubMenuSelected(SubMenuBuilder paramSubMenuBuilder)
    {
      return false;
    }
    
    public void setCallback(MenuPresenter.Callback paramCallback) {}
    
    public void updateMenuView(boolean paramBoolean)
    {
      if (mCurrentExpandedItem != null)
      {
        MenuBuilder localMenuBuilder = mMenu;
        int k = 0;
        int j = k;
        if (localMenuBuilder != null)
        {
          int m = mMenu.size();
          int i = 0;
          for (;;)
          {
            j = k;
            if (i >= m) {
              break;
            }
            if (mMenu.getItem(i) == mCurrentExpandedItem)
            {
              j = 1;
              break;
            }
            i += 1;
          }
        }
        if (j == 0) {
          collapseItemActionView(mMenu, mCurrentExpandedItem);
        }
      }
    }
  }
  
  public static class LayoutParams
    extends ActionBar.LayoutParams
  {
    static final int CUSTOM = 0;
    static final int EXPANDED = 2;
    static final int SYSTEM = 1;
    int mViewType = 0;
    
    public LayoutParams(int paramInt)
    {
      this(-2, -1, paramInt);
    }
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
      gravity = 8388627;
    }
    
    public LayoutParams(int paramInt1, int paramInt2, int paramInt3)
    {
      super(paramInt2);
      gravity = paramInt3;
    }
    
    public LayoutParams(@NonNull Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public LayoutParams(ActionBar.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
      mViewType = mViewType;
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
      copyMarginsFromCompat(paramMarginLayoutParams);
    }
    
    void copyMarginsFromCompat(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      leftMargin = leftMargin;
      topMargin = topMargin;
      rightMargin = rightMargin;
      bottomMargin = bottomMargin;
    }
  }
  
  public static abstract interface OnMenuItemClickListener
  {
    public abstract boolean onMenuItemClick(MenuItem paramMenuItem);
  }
  
  public static class SavedState
    extends AbsSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator()
    {
      public Toolbar.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new Toolbar.SavedState(paramAnonymousParcel, null);
      }
      
      public Toolbar.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
      {
        return new Toolbar.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
      }
      
      public Toolbar.SavedState[] newArray(int paramAnonymousInt)
      {
        return new Toolbar.SavedState[paramAnonymousInt];
      }
    };
    int expandedMenuItemId;
    boolean isOverflowOpen;
    
    public SavedState(Parcel paramParcel)
    {
      this(paramParcel, null);
    }
    
    public SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      expandedMenuItemId = paramParcel.readInt();
      boolean bool;
      if (paramParcel.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      }
      isOverflowOpen = bool;
    }
    
    public SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }
  }
}

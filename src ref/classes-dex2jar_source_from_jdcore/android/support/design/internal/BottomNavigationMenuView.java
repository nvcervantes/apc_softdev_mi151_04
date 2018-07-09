package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.design.R.dimen;
import android.support.transition.AutoTransition;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SynchronizedPool;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class BottomNavigationMenuView
  extends ViewGroup
  implements MenuView
{
  private static final long ACTIVE_ANIMATION_DURATION_MS = 115L;
  private final int mActiveItemMaxWidth;
  private BottomNavigationItemView[] mButtons;
  private final int mInactiveItemMaxWidth;
  private final int mInactiveItemMinWidth;
  private int mItemBackgroundRes;
  private final int mItemHeight;
  private ColorStateList mItemIconTint;
  private final Pools.Pool<BottomNavigationItemView> mItemPool = new Pools.SynchronizedPool(5);
  private ColorStateList mItemTextColor;
  private MenuBuilder mMenu;
  private final View.OnClickListener mOnClickListener;
  private BottomNavigationPresenter mPresenter;
  private int mSelectedItemId = 0;
  private int mSelectedItemPosition = 0;
  private final TransitionSet mSet;
  private boolean mShiftingMode = true;
  private int[] mTempChildWidths;
  
  public BottomNavigationMenuView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public BottomNavigationMenuView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = getResources();
    mInactiveItemMaxWidth = paramContext.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_max_width);
    mInactiveItemMinWidth = paramContext.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_min_width);
    mActiveItemMaxWidth = paramContext.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_max_width);
    mItemHeight = paramContext.getDimensionPixelSize(R.dimen.design_bottom_navigation_height);
    mSet = new AutoTransition();
    mSet.setOrdering(0);
    mSet.setDuration(115L);
    mSet.setInterpolator(new FastOutSlowInInterpolator());
    mSet.addTransition(new TextScale());
    mOnClickListener = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = ((BottomNavigationItemView)paramAnonymousView).getItemData();
        if (!mMenu.performItemAction(paramAnonymousView, mPresenter, 0)) {
          paramAnonymousView.setChecked(true);
        }
      }
    };
    mTempChildWidths = new int[5];
  }
  
  private BottomNavigationItemView getNewItem()
  {
    BottomNavigationItemView localBottomNavigationItemView2 = (BottomNavigationItemView)mItemPool.acquire();
    BottomNavigationItemView localBottomNavigationItemView1 = localBottomNavigationItemView2;
    if (localBottomNavigationItemView2 == null) {
      localBottomNavigationItemView1 = new BottomNavigationItemView(getContext());
    }
    return localBottomNavigationItemView1;
  }
  
  public void buildMenuView()
  {
    removeAllViews();
    Object localObject1;
    if (mButtons != null)
    {
      localObject1 = mButtons;
      int j = localObject1.length;
      i = 0;
      while (i < j)
      {
        Object localObject2 = localObject1[i];
        mItemPool.release(localObject2);
        i += 1;
      }
    }
    if (mMenu.size() == 0)
    {
      mSelectedItemId = 0;
      mSelectedItemPosition = 0;
      mButtons = null;
      return;
    }
    mButtons = new BottomNavigationItemView[mMenu.size()];
    boolean bool;
    if (mMenu.size() > 3) {
      bool = true;
    } else {
      bool = false;
    }
    mShiftingMode = bool;
    int i = 0;
    while (i < mMenu.size())
    {
      mPresenter.setUpdateSuspended(true);
      mMenu.getItem(i).setCheckable(true);
      mPresenter.setUpdateSuspended(false);
      localObject1 = getNewItem();
      mButtons[i] = localObject1;
      ((BottomNavigationItemView)localObject1).setIconTintList(mItemIconTint);
      ((BottomNavigationItemView)localObject1).setTextColor(mItemTextColor);
      ((BottomNavigationItemView)localObject1).setItemBackground(mItemBackgroundRes);
      ((BottomNavigationItemView)localObject1).setShiftingMode(mShiftingMode);
      ((BottomNavigationItemView)localObject1).initialize((MenuItemImpl)mMenu.getItem(i), 0);
      ((BottomNavigationItemView)localObject1).setItemPosition(i);
      ((BottomNavigationItemView)localObject1).setOnClickListener(mOnClickListener);
      addView((View)localObject1);
      i += 1;
    }
    mSelectedItemPosition = Math.min(mMenu.size() - 1, mSelectedItemPosition);
    mMenu.getItem(mSelectedItemPosition).setChecked(true);
  }
  
  @Nullable
  public ColorStateList getIconTintList()
  {
    return mItemIconTint;
  }
  
  public int getItemBackgroundRes()
  {
    return mItemBackgroundRes;
  }
  
  public ColorStateList getItemTextColor()
  {
    return mItemTextColor;
  }
  
  public int getSelectedItemId()
  {
    return mSelectedItemId;
  }
  
  public int getWindowAnimations()
  {
    return 0;
  }
  
  public void initialize(MenuBuilder paramMenuBuilder)
  {
    mMenu = paramMenuBuilder;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getChildCount();
    int j = paramInt4 - paramInt2;
    paramInt2 = 0;
    paramInt4 = paramInt2;
    while (paramInt2 < i)
    {
      View localView = getChildAt(paramInt2);
      if (localView.getVisibility() != 8)
      {
        if (ViewCompat.getLayoutDirection(this) == 1)
        {
          int k = paramInt3 - paramInt1 - paramInt4;
          localView.layout(k - localView.getMeasuredWidth(), 0, k, j);
        }
        else
        {
          localView.layout(paramInt4, 0, localView.getMeasuredWidth() + paramInt4, j);
        }
        paramInt4 += localView.getMeasuredWidth();
      }
      paramInt2 += 1;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt2 = View.MeasureSpec.getSize(paramInt1);
    int j = getChildCount();
    int n = View.MeasureSpec.makeMeasureSpec(mItemHeight, 1073741824);
    Object localObject;
    int i;
    if (mShiftingMode)
    {
      paramInt1 = j - 1;
      k = Math.min(paramInt2 - mInactiveItemMinWidth * paramInt1, mActiveItemMaxWidth);
      paramInt2 -= k;
      int m = Math.min(paramInt2 / paramInt1, mInactiveItemMaxWidth);
      paramInt2 -= paramInt1 * m;
      paramInt1 = 0;
      while (paramInt1 < j)
      {
        localObject = mTempChildWidths;
        if (paramInt1 == mSelectedItemPosition) {
          i = k;
        } else {
          i = m;
        }
        localObject[paramInt1] = i;
        i = paramInt2;
        if (paramInt2 > 0)
        {
          localObject = mTempChildWidths;
          localObject[paramInt1] += 1;
          i = paramInt2 - 1;
        }
        paramInt1 += 1;
        paramInt2 = i;
      }
    }
    if (j == 0) {
      paramInt1 = 1;
    } else {
      paramInt1 = j;
    }
    int k = Math.min(paramInt2 / paramInt1, mActiveItemMaxWidth);
    paramInt2 -= k * j;
    paramInt1 = 0;
    while (paramInt1 < j)
    {
      mTempChildWidths[paramInt1] = k;
      i = paramInt2;
      if (paramInt2 > 0)
      {
        localObject = mTempChildWidths;
        localObject[paramInt1] += 1;
        i = paramInt2 - 1;
      }
      paramInt1 += 1;
      paramInt2 = i;
    }
    paramInt1 = 0;
    paramInt2 = paramInt1;
    while (paramInt1 < j)
    {
      localObject = getChildAt(paramInt1);
      if (((View)localObject).getVisibility() != 8)
      {
        ((View)localObject).measure(View.MeasureSpec.makeMeasureSpec(mTempChildWidths[paramInt1], 1073741824), n);
        getLayoutParamswidth = ((View)localObject).getMeasuredWidth();
        paramInt2 += ((View)localObject).getMeasuredWidth();
      }
      paramInt1 += 1;
    }
    setMeasuredDimension(View.resolveSizeAndState(paramInt2, View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824), 0), View.resolveSizeAndState(mItemHeight, n, 0));
  }
  
  public void setIconTintList(ColorStateList paramColorStateList)
  {
    mItemIconTint = paramColorStateList;
    if (mButtons == null) {
      return;
    }
    BottomNavigationItemView[] arrayOfBottomNavigationItemView = mButtons;
    int j = arrayOfBottomNavigationItemView.length;
    int i = 0;
    while (i < j)
    {
      arrayOfBottomNavigationItemView[i].setIconTintList(paramColorStateList);
      i += 1;
    }
  }
  
  public void setItemBackgroundRes(int paramInt)
  {
    mItemBackgroundRes = paramInt;
    if (mButtons == null) {
      return;
    }
    BottomNavigationItemView[] arrayOfBottomNavigationItemView = mButtons;
    int j = arrayOfBottomNavigationItemView.length;
    int i = 0;
    while (i < j)
    {
      arrayOfBottomNavigationItemView[i].setItemBackground(paramInt);
      i += 1;
    }
  }
  
  public void setItemTextColor(ColorStateList paramColorStateList)
  {
    mItemTextColor = paramColorStateList;
    if (mButtons == null) {
      return;
    }
    BottomNavigationItemView[] arrayOfBottomNavigationItemView = mButtons;
    int j = arrayOfBottomNavigationItemView.length;
    int i = 0;
    while (i < j)
    {
      arrayOfBottomNavigationItemView[i].setTextColor(paramColorStateList);
      i += 1;
    }
  }
  
  public void setPresenter(BottomNavigationPresenter paramBottomNavigationPresenter)
  {
    mPresenter = paramBottomNavigationPresenter;
  }
  
  void tryRestoreSelectedItemId(int paramInt)
  {
    int j = mMenu.size();
    int i = 0;
    while (i < j)
    {
      MenuItem localMenuItem = mMenu.getItem(i);
      if (paramInt == localMenuItem.getItemId())
      {
        mSelectedItemId = paramInt;
        mSelectedItemPosition = i;
        localMenuItem.setChecked(true);
        return;
      }
      i += 1;
    }
  }
  
  public void updateMenuView()
  {
    int j = mMenu.size();
    if (j != mButtons.length)
    {
      buildMenuView();
      return;
    }
    int k = mSelectedItemId;
    int i = 0;
    while (i < j)
    {
      MenuItem localMenuItem = mMenu.getItem(i);
      if (localMenuItem.isChecked())
      {
        mSelectedItemId = localMenuItem.getItemId();
        mSelectedItemPosition = i;
      }
      i += 1;
    }
    if (k != mSelectedItemId) {
      TransitionManager.beginDelayedTransition(this, mSet);
    }
    i = 0;
    while (i < j)
    {
      mPresenter.setUpdateSuspended(true);
      mButtons[i].initialize((MenuItemImpl)mMenu.getItem(i), 0);
      mPresenter.setUpdateSuspended(false);
      i += 1;
    }
  }
}

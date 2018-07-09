package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.R.color;
import android.support.design.R.dimen;
import android.support.design.R.style;
import android.support.design.R.styleable;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.internal.BottomNavigationPresenter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class BottomNavigationView
  extends FrameLayout
{
  private static final int[] CHECKED_STATE_SET = { 16842912 };
  private static final int[] DISABLED_STATE_SET = { -16842910 };
  private static final int MENU_PRESENTER_ID = 1;
  private final MenuBuilder mMenu;
  private MenuInflater mMenuInflater;
  private final BottomNavigationMenuView mMenuView;
  private final BottomNavigationPresenter mPresenter = new BottomNavigationPresenter();
  private OnNavigationItemReselectedListener mReselectedListener;
  private OnNavigationItemSelectedListener mSelectedListener;
  
  public BottomNavigationView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public BottomNavigationView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public BottomNavigationView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    ThemeUtils.checkAppCompatTheme(paramContext);
    mMenu = new BottomNavigationMenu(paramContext);
    mMenuView = new BottomNavigationMenuView(paramContext);
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
    gravity = 17;
    mMenuView.setLayoutParams(localLayoutParams);
    mPresenter.setBottomNavigationMenuView(mMenuView);
    mPresenter.setId(1);
    mMenuView.setPresenter(mPresenter);
    mMenu.addMenuPresenter(mPresenter);
    mPresenter.initForMenu(getContext(), mMenu);
    paramAttributeSet = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.BottomNavigationView, paramInt, R.style.Widget_Design_BottomNavigationView);
    if (paramAttributeSet.hasValue(R.styleable.BottomNavigationView_itemIconTint)) {
      mMenuView.setIconTintList(paramAttributeSet.getColorStateList(R.styleable.BottomNavigationView_itemIconTint));
    } else {
      mMenuView.setIconTintList(createDefaultColorStateList(16842808));
    }
    if (paramAttributeSet.hasValue(R.styleable.BottomNavigationView_itemTextColor)) {
      mMenuView.setItemTextColor(paramAttributeSet.getColorStateList(R.styleable.BottomNavigationView_itemTextColor));
    } else {
      mMenuView.setItemTextColor(createDefaultColorStateList(16842808));
    }
    if (paramAttributeSet.hasValue(R.styleable.BottomNavigationView_elevation)) {
      ViewCompat.setElevation(this, paramAttributeSet.getDimensionPixelSize(R.styleable.BottomNavigationView_elevation, 0));
    }
    paramInt = paramAttributeSet.getResourceId(R.styleable.BottomNavigationView_itemBackground, 0);
    mMenuView.setItemBackgroundRes(paramInt);
    if (paramAttributeSet.hasValue(R.styleable.BottomNavigationView_menu)) {
      inflateMenu(paramAttributeSet.getResourceId(R.styleable.BottomNavigationView_menu, 0));
    }
    paramAttributeSet.recycle();
    addView(mMenuView, localLayoutParams);
    if (Build.VERSION.SDK_INT < 21) {
      addCompatibilityTopDivider(paramContext);
    }
    mMenu.setCallback(new MenuBuilder.Callback()
    {
      public boolean onMenuItemSelected(MenuBuilder paramAnonymousMenuBuilder, MenuItem paramAnonymousMenuItem)
      {
        if ((mReselectedListener != null) && (paramAnonymousMenuItem.getItemId() == getSelectedItemId()))
        {
          mReselectedListener.onNavigationItemReselected(paramAnonymousMenuItem);
          return true;
        }
        return (mSelectedListener != null) && (!mSelectedListener.onNavigationItemSelected(paramAnonymousMenuItem));
      }
      
      public void onMenuModeChange(MenuBuilder paramAnonymousMenuBuilder) {}
    });
  }
  
  private void addCompatibilityTopDivider(Context paramContext)
  {
    View localView = new View(paramContext);
    localView.setBackgroundColor(ContextCompat.getColor(paramContext, R.color.design_bottom_navigation_shadow_color));
    localView.setLayoutParams(new FrameLayout.LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.design_bottom_navigation_shadow_height)));
    addView(localView);
  }
  
  private ColorStateList createDefaultColorStateList(int paramInt)
  {
    Object localObject = new TypedValue();
    if (!getContext().getTheme().resolveAttribute(paramInt, (TypedValue)localObject, true)) {
      return null;
    }
    ColorStateList localColorStateList = AppCompatResources.getColorStateList(getContext(), resourceId);
    if (!getContext().getTheme().resolveAttribute(R.attr.colorPrimary, (TypedValue)localObject, true)) {
      return null;
    }
    paramInt = data;
    int i = localColorStateList.getDefaultColor();
    localObject = DISABLED_STATE_SET;
    int[] arrayOfInt1 = CHECKED_STATE_SET;
    int[] arrayOfInt2 = EMPTY_STATE_SET;
    int j = localColorStateList.getColorForState(DISABLED_STATE_SET, i);
    return new ColorStateList(new int[][] { localObject, arrayOfInt1, arrayOfInt2 }, new int[] { j, paramInt, i });
  }
  
  private MenuInflater getMenuInflater()
  {
    if (mMenuInflater == null) {
      mMenuInflater = new SupportMenuInflater(getContext());
    }
    return mMenuInflater;
  }
  
  @DrawableRes
  public int getItemBackgroundResource()
  {
    return mMenuView.getItemBackgroundRes();
  }
  
  @Nullable
  public ColorStateList getItemIconTintList()
  {
    return mMenuView.getIconTintList();
  }
  
  @Nullable
  public ColorStateList getItemTextColor()
  {
    return mMenuView.getItemTextColor();
  }
  
  public int getMaxItemCount()
  {
    return 5;
  }
  
  @NonNull
  public Menu getMenu()
  {
    return mMenu;
  }
  
  @IdRes
  public int getSelectedItemId()
  {
    return mMenuView.getSelectedItemId();
  }
  
  public void inflateMenu(int paramInt)
  {
    mPresenter.setUpdateSuspended(true);
    getMenuInflater().inflate(paramInt, mMenu);
    mPresenter.setUpdateSuspended(false);
    mPresenter.updateMenuView(true);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof SavedState))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    mMenu.restorePresenterStates(menuPresenterState);
  }
  
  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    menuPresenterState = new Bundle();
    mMenu.savePresenterStates(menuPresenterState);
    return localSavedState;
  }
  
  public void setItemBackgroundResource(@DrawableRes int paramInt)
  {
    mMenuView.setItemBackgroundRes(paramInt);
  }
  
  public void setItemIconTintList(@Nullable ColorStateList paramColorStateList)
  {
    mMenuView.setIconTintList(paramColorStateList);
  }
  
  public void setItemTextColor(@Nullable ColorStateList paramColorStateList)
  {
    mMenuView.setItemTextColor(paramColorStateList);
  }
  
  public void setOnNavigationItemReselectedListener(@Nullable OnNavigationItemReselectedListener paramOnNavigationItemReselectedListener)
  {
    mReselectedListener = paramOnNavigationItemReselectedListener;
  }
  
  public void setOnNavigationItemSelectedListener(@Nullable OnNavigationItemSelectedListener paramOnNavigationItemSelectedListener)
  {
    mSelectedListener = paramOnNavigationItemSelectedListener;
  }
  
  public void setSelectedItemId(@IdRes int paramInt)
  {
    MenuItem localMenuItem = mMenu.findItem(paramInt);
    if ((localMenuItem != null) && (!mMenu.performItemAction(localMenuItem, mPresenter, 0))) {
      localMenuItem.setChecked(true);
    }
  }
  
  public static abstract interface OnNavigationItemReselectedListener
  {
    public abstract void onNavigationItemReselected(@NonNull MenuItem paramMenuItem);
  }
  
  public static abstract interface OnNavigationItemSelectedListener
  {
    public abstract boolean onNavigationItemSelected(@NonNull MenuItem paramMenuItem);
  }
  
  static class SavedState
    extends AbsSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator()
    {
      public BottomNavigationView.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new BottomNavigationView.SavedState(paramAnonymousParcel, null);
      }
      
      public BottomNavigationView.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
      {
        return new BottomNavigationView.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
      }
      
      public BottomNavigationView.SavedState[] newArray(int paramAnonymousInt)
      {
        return new BottomNavigationView.SavedState[paramAnonymousInt];
      }
    };
    Bundle menuPresenterState;
    
    public SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      readFromParcel(paramParcel, paramClassLoader);
    }
    
    public SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    private void readFromParcel(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      menuPresenterState = paramParcel.readBundle(paramClassLoader);
    }
    
    public void writeToParcel(@NonNull Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeBundle(menuPresenterState);
    }
  }
}

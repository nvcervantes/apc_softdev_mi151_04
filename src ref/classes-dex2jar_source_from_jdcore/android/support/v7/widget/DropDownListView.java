package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.appcompat.R.attr;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.Field;

class DropDownListView
  extends ListView
{
  public static final int INVALID_POSITION = -1;
  public static final int NO_POSITION = -1;
  private ViewPropertyAnimatorCompat mClickAnimation;
  private boolean mDrawsInPressedState;
  private boolean mHijackFocus;
  private Field mIsChildViewEnabled;
  private boolean mListSelectionHidden;
  private int mMotionPosition;
  private ResolveHoverRunnable mResolveHoverRunnable;
  private ListViewAutoScrollHelper mScrollHelper;
  private int mSelectionBottomPadding = 0;
  private int mSelectionLeftPadding = 0;
  private int mSelectionRightPadding = 0;
  private int mSelectionTopPadding = 0;
  private GateKeeperDrawable mSelector;
  private final Rect mSelectorRect = new Rect();
  
  DropDownListView(Context paramContext, boolean paramBoolean)
  {
    super(paramContext, null, R.attr.dropDownListViewStyle);
    mHijackFocus = paramBoolean;
    setCacheColorHint(0);
    try
    {
      mIsChildViewEnabled = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
      mIsChildViewEnabled.setAccessible(true);
      return;
    }
    catch (NoSuchFieldException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private void clearPressedItem()
  {
    mDrawsInPressedState = false;
    setPressed(false);
    drawableStateChanged();
    View localView = getChildAt(mMotionPosition - getFirstVisiblePosition());
    if (localView != null) {
      localView.setPressed(false);
    }
    if (mClickAnimation != null)
    {
      mClickAnimation.cancel();
      mClickAnimation = null;
    }
  }
  
  private void clickPressedItem(View paramView, int paramInt)
  {
    performItemClick(paramView, paramInt, getItemIdAtPosition(paramInt));
  }
  
  private void drawSelectorCompat(Canvas paramCanvas)
  {
    if (!mSelectorRect.isEmpty())
    {
      Drawable localDrawable = getSelector();
      if (localDrawable != null)
      {
        localDrawable.setBounds(mSelectorRect);
        localDrawable.draw(paramCanvas);
      }
    }
  }
  
  private void positionSelectorCompat(int paramInt, View paramView)
  {
    Rect localRect = mSelectorRect;
    localRect.set(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom());
    left -= mSelectionLeftPadding;
    top -= mSelectionTopPadding;
    right += mSelectionRightPadding;
    bottom += mSelectionBottomPadding;
    try
    {
      boolean bool = mIsChildViewEnabled.getBoolean(this);
      if (paramView.isEnabled() != bool)
      {
        mIsChildViewEnabled.set(this, Boolean.valueOf(bool ^ true));
        if (paramInt != -1)
        {
          refreshDrawableState();
          return;
        }
      }
    }
    catch (IllegalAccessException paramView)
    {
      paramView.printStackTrace();
    }
  }
  
  private void positionSelectorLikeFocusCompat(int paramInt, View paramView)
  {
    Drawable localDrawable = getSelector();
    boolean bool = true;
    int i;
    if ((localDrawable != null) && (paramInt != -1)) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      localDrawable.setVisible(false, false);
    }
    positionSelectorCompat(paramInt, paramView);
    if (i != 0)
    {
      paramView = mSelectorRect;
      float f1 = paramView.exactCenterX();
      float f2 = paramView.exactCenterY();
      if (getVisibility() != 0) {
        bool = false;
      }
      localDrawable.setVisible(bool, false);
      DrawableCompat.setHotspot(localDrawable, f1, f2);
    }
  }
  
  private void positionSelectorLikeTouchCompat(int paramInt, View paramView, float paramFloat1, float paramFloat2)
  {
    positionSelectorLikeFocusCompat(paramInt, paramView);
    paramView = getSelector();
    if ((paramView != null) && (paramInt != -1)) {
      DrawableCompat.setHotspot(paramView, paramFloat1, paramFloat2);
    }
  }
  
  private void setPressedItem(View paramView, int paramInt, float paramFloat1, float paramFloat2)
  {
    mDrawsInPressedState = true;
    if (Build.VERSION.SDK_INT >= 21) {
      drawableHotspotChanged(paramFloat1, paramFloat2);
    }
    if (!isPressed()) {
      setPressed(true);
    }
    layoutChildren();
    if (mMotionPosition != -1)
    {
      View localView = getChildAt(mMotionPosition - getFirstVisiblePosition());
      if ((localView != null) && (localView != paramView) && (localView.isPressed())) {
        localView.setPressed(false);
      }
    }
    mMotionPosition = paramInt;
    float f1 = paramView.getLeft();
    float f2 = paramView.getTop();
    if (Build.VERSION.SDK_INT >= 21) {
      paramView.drawableHotspotChanged(paramFloat1 - f1, paramFloat2 - f2);
    }
    if (!paramView.isPressed()) {
      paramView.setPressed(true);
    }
    positionSelectorLikeTouchCompat(paramInt, paramView, paramFloat1, paramFloat2);
    setSelectorEnabled(false);
    refreshDrawableState();
  }
  
  private void setSelectorEnabled(boolean paramBoolean)
  {
    if (mSelector != null) {
      mSelector.setEnabled(paramBoolean);
    }
  }
  
  private boolean touchModeDrawsInPressedStateCompat()
  {
    return mDrawsInPressedState;
  }
  
  private void updateSelectorStateCompat()
  {
    Drawable localDrawable = getSelector();
    if ((localDrawable != null) && (touchModeDrawsInPressedStateCompat()) && (isPressed())) {
      localDrawable.setState(getDrawableState());
    }
  }
  
  protected void dispatchDraw(Canvas paramCanvas)
  {
    drawSelectorCompat(paramCanvas);
    super.dispatchDraw(paramCanvas);
  }
  
  protected void drawableStateChanged()
  {
    if (mResolveHoverRunnable != null) {
      return;
    }
    super.drawableStateChanged();
    setSelectorEnabled(true);
    updateSelectorStateCompat();
  }
  
  public boolean hasFocus()
  {
    return (mHijackFocus) || (super.hasFocus());
  }
  
  public boolean hasWindowFocus()
  {
    return (mHijackFocus) || (super.hasWindowFocus());
  }
  
  public boolean isFocused()
  {
    return (mHijackFocus) || (super.isFocused());
  }
  
  public boolean isInTouchMode()
  {
    return ((mHijackFocus) && (mListSelectionHidden)) || (super.isInTouchMode());
  }
  
  public int lookForSelectablePosition(int paramInt, boolean paramBoolean)
  {
    ListAdapter localListAdapter = getAdapter();
    if (localListAdapter != null)
    {
      if (isInTouchMode()) {
        return -1;
      }
      int j = localListAdapter.getCount();
      if (!getAdapter().areAllItemsEnabled())
      {
        int i;
        if (paramBoolean)
        {
          paramInt = Math.max(0, paramInt);
          for (;;)
          {
            i = paramInt;
            if (paramInt >= j) {
              break;
            }
            i = paramInt;
            if (localListAdapter.isEnabled(paramInt)) {
              break;
            }
            paramInt += 1;
          }
        }
        paramInt = Math.min(paramInt, j - 1);
        for (;;)
        {
          i = paramInt;
          if (paramInt < 0) {
            break;
          }
          i = paramInt;
          if (localListAdapter.isEnabled(paramInt)) {
            break;
          }
          paramInt -= 1;
        }
        if (i >= 0)
        {
          if (i >= j) {
            return -1;
          }
          return i;
        }
        return -1;
      }
      if (paramInt >= 0)
      {
        if (paramInt >= j) {
          return -1;
        }
        return paramInt;
      }
      return -1;
    }
    return -1;
  }
  
  public int measureHeightOfChildrenCompat(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    paramInt2 = getListPaddingTop();
    paramInt3 = getListPaddingBottom();
    getListPaddingLeft();
    getListPaddingRight();
    int j = getDividerHeight();
    Object localObject = getDivider();
    ListAdapter localListAdapter = getAdapter();
    if (localListAdapter == null) {
      return paramInt2 + paramInt3;
    }
    if ((j <= 0) || (localObject == null)) {
      j = 0;
    }
    int i1 = localListAdapter.getCount();
    paramInt3 = paramInt2 + paramInt3;
    int k = 0;
    int i = k;
    paramInt2 = i;
    localObject = null;
    int m = i;
    while (k < i1)
    {
      int n = localListAdapter.getItemViewType(k);
      i = m;
      if (n != m)
      {
        localObject = null;
        i = n;
      }
      View localView = localListAdapter.getView(k, (View)localObject, this);
      ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
      localObject = localLayoutParams;
      if (localLayoutParams == null)
      {
        localObject = generateDefaultLayoutParams();
        localView.setLayoutParams((ViewGroup.LayoutParams)localObject);
      }
      if (height > 0) {
        m = View.MeasureSpec.makeMeasureSpec(height, 1073741824);
      } else {
        m = View.MeasureSpec.makeMeasureSpec(0, 0);
      }
      localView.measure(paramInt1, m);
      localView.forceLayout();
      m = paramInt3;
      if (k > 0) {
        m = paramInt3 + j;
      }
      paramInt3 = m + localView.getMeasuredHeight();
      if (paramInt3 >= paramInt4)
      {
        paramInt1 = paramInt4;
        if (paramInt5 >= 0)
        {
          paramInt1 = paramInt4;
          if (k > paramInt5)
          {
            paramInt1 = paramInt4;
            if (paramInt2 > 0)
            {
              paramInt1 = paramInt4;
              if (paramInt3 != paramInt4) {
                paramInt1 = paramInt2;
              }
            }
          }
        }
        return paramInt1;
      }
      n = paramInt2;
      if (paramInt5 >= 0)
      {
        n = paramInt2;
        if (k >= paramInt5) {
          n = paramInt3;
        }
      }
      k += 1;
      m = i;
      localObject = localView;
      paramInt2 = n;
    }
    return paramInt3;
  }
  
  protected void onDetachedFromWindow()
  {
    mResolveHoverRunnable = null;
    super.onDetachedFromWindow();
  }
  
  public boolean onForwardedEvent(MotionEvent paramMotionEvent, int paramInt)
  {
    int i = paramMotionEvent.getActionMasked();
    switch (i)
    {
    }
    boolean bool2;
    boolean bool1;
    for (;;)
    {
      bool2 = false;
      bool1 = true;
      break;
      label60:
      do
      {
        bool2 = false;
        bool1 = bool2;
        break;
        bool1 = true;
        break label60;
        bool1 = false;
        j = paramMotionEvent.findPointerIndex(paramInt);
      } while (j < 0);
      paramInt = (int)paramMotionEvent.getX(j);
      int j = (int)paramMotionEvent.getY(j);
      int k = pointToPosition(paramInt, j);
      if (k == -1)
      {
        bool2 = true;
        break;
      }
      View localView = getChildAt(k - getFirstVisiblePosition());
      setPressedItem(localView, k, paramInt, j);
      if (i == 1) {
        clickPressedItem(localView, k);
      }
    }
    if ((!bool1) || (bool2)) {
      clearPressedItem();
    }
    if (bool1)
    {
      if (mScrollHelper == null) {
        mScrollHelper = new ListViewAutoScrollHelper(this);
      }
      mScrollHelper.setEnabled(true);
      mScrollHelper.onTouch(this, paramMotionEvent);
      return bool1;
    }
    if (mScrollHelper != null) {
      mScrollHelper.setEnabled(false);
    }
    return bool1;
  }
  
  public boolean onHoverEvent(@NonNull MotionEvent paramMotionEvent)
  {
    if (Build.VERSION.SDK_INT < 26) {
      return super.onHoverEvent(paramMotionEvent);
    }
    int i = paramMotionEvent.getActionMasked();
    if ((i == 10) && (mResolveHoverRunnable == null))
    {
      mResolveHoverRunnable = new ResolveHoverRunnable(null);
      mResolveHoverRunnable.post();
    }
    boolean bool = super.onHoverEvent(paramMotionEvent);
    if ((i != 9) && (i != 7))
    {
      setSelection(-1);
      return bool;
    }
    i = pointToPosition((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
    if ((i != -1) && (i != getSelectedItemPosition()))
    {
      paramMotionEvent = getChildAt(i - getFirstVisiblePosition());
      if (paramMotionEvent.isEnabled()) {
        setSelectionFromTop(i, paramMotionEvent.getTop() - getTop());
      }
      updateSelectorStateCompat();
    }
    return bool;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 0) {
      mMotionPosition = pointToPosition((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
    }
    if (mResolveHoverRunnable != null) {
      mResolveHoverRunnable.cancel();
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  void setListSelectionHidden(boolean paramBoolean)
  {
    mListSelectionHidden = paramBoolean;
  }
  
  public void setSelector(Drawable paramDrawable)
  {
    if (paramDrawable != null) {
      localObject = new GateKeeperDrawable(paramDrawable);
    } else {
      localObject = null;
    }
    mSelector = ((GateKeeperDrawable)localObject);
    super.setSelector(mSelector);
    Object localObject = new Rect();
    if (paramDrawable != null) {
      paramDrawable.getPadding((Rect)localObject);
    }
    mSelectionLeftPadding = left;
    mSelectionTopPadding = top;
    mSelectionRightPadding = right;
    mSelectionBottomPadding = bottom;
  }
  
  private static class GateKeeperDrawable
    extends DrawableWrapper
  {
    private boolean mEnabled = true;
    
    GateKeeperDrawable(Drawable paramDrawable)
    {
      super();
    }
    
    public void draw(Canvas paramCanvas)
    {
      if (mEnabled) {
        super.draw(paramCanvas);
      }
    }
    
    void setEnabled(boolean paramBoolean)
    {
      mEnabled = paramBoolean;
    }
    
    public void setHotspot(float paramFloat1, float paramFloat2)
    {
      if (mEnabled) {
        super.setHotspot(paramFloat1, paramFloat2);
      }
    }
    
    public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      if (mEnabled) {
        super.setHotspotBounds(paramInt1, paramInt2, paramInt3, paramInt4);
      }
    }
    
    public boolean setState(int[] paramArrayOfInt)
    {
      if (mEnabled) {
        return super.setState(paramArrayOfInt);
      }
      return false;
    }
    
    public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
    {
      if (mEnabled) {
        return super.setVisible(paramBoolean1, paramBoolean2);
      }
      return false;
    }
  }
  
  private class ResolveHoverRunnable
    implements Runnable
  {
    private ResolveHoverRunnable() {}
    
    public void cancel()
    {
      DropDownListView.access$102(DropDownListView.this, null);
      removeCallbacks(this);
    }
    
    public void post()
    {
      post(this);
    }
    
    public void run()
    {
      DropDownListView.access$102(DropDownListView.this, null);
      drawableStateChanged();
    }
  }
}

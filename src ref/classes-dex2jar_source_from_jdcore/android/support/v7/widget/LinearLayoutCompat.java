package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat
  extends ViewGroup
{
  public static final int HORIZONTAL = 0;
  private static final int INDEX_BOTTOM = 2;
  private static final int INDEX_CENTER_VERTICAL = 0;
  private static final int INDEX_FILL = 3;
  private static final int INDEX_TOP = 1;
  public static final int SHOW_DIVIDER_BEGINNING = 1;
  public static final int SHOW_DIVIDER_END = 4;
  public static final int SHOW_DIVIDER_MIDDLE = 2;
  public static final int SHOW_DIVIDER_NONE = 0;
  public static final int VERTICAL = 1;
  private static final int VERTICAL_GRAVITY_COUNT = 4;
  private boolean mBaselineAligned = true;
  private int mBaselineAlignedChildIndex = -1;
  private int mBaselineChildTop = 0;
  private Drawable mDivider;
  private int mDividerHeight;
  private int mDividerPadding;
  private int mDividerWidth;
  private int mGravity = 8388659;
  private int[] mMaxAscent;
  private int[] mMaxDescent;
  private int mOrientation;
  private int mShowDividers;
  private int mTotalLength;
  private boolean mUseLargestChild;
  private float mWeightSum;
  
  public LinearLayoutCompat(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public LinearLayoutCompat(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public LinearLayoutCompat(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.LinearLayoutCompat, paramInt, 0);
    paramInt = paramContext.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
    if (paramInt >= 0) {
      setOrientation(paramInt);
    }
    paramInt = paramContext.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
    if (paramInt >= 0) {
      setGravity(paramInt);
    }
    boolean bool = paramContext.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
    if (!bool) {
      setBaselineAligned(bool);
    }
    mWeightSum = paramContext.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0F);
    mBaselineAlignedChildIndex = paramContext.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
    mUseLargestChild = paramContext.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
    setDividerDrawable(paramContext.getDrawable(R.styleable.LinearLayoutCompat_divider));
    mShowDividers = paramContext.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
    mDividerPadding = paramContext.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
    paramContext.recycle();
  }
  
  private void forceUniformHeight(int paramInt1, int paramInt2)
  {
    int j = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
    int i = 0;
    while (i < paramInt1)
    {
      View localView = getVirtualChildAt(i);
      if (localView.getVisibility() != 8)
      {
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (height == -1)
        {
          int k = width;
          width = localView.getMeasuredWidth();
          measureChildWithMargins(localView, paramInt2, 0, j, 0);
          width = k;
        }
      }
      i += 1;
    }
  }
  
  private void forceUniformWidth(int paramInt1, int paramInt2)
  {
    int j = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
    int i = 0;
    while (i < paramInt1)
    {
      View localView = getVirtualChildAt(i);
      if (localView.getVisibility() != 8)
      {
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (width == -1)
        {
          int k = height;
          height = localView.getMeasuredHeight();
          measureChildWithMargins(localView, j, 0, paramInt2, 0);
          height = k;
        }
      }
      i += 1;
    }
  }
  
  private void setChildFrame(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramView.layout(paramInt1, paramInt2, paramInt3 + paramInt1, paramInt4 + paramInt2);
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  void drawDividersHorizontal(Canvas paramCanvas)
  {
    int k = getVirtualChildCount();
    boolean bool = ViewUtils.isLayoutRtl(this);
    int i = 0;
    View localView;
    LayoutParams localLayoutParams;
    while (i < k)
    {
      localView = getVirtualChildAt(i);
      if ((localView != null) && (localView.getVisibility() != 8) && (hasDividerBeforeChildAt(i)))
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        int j;
        if (bool) {
          j = localView.getRight() + rightMargin;
        } else {
          j = localView.getLeft() - leftMargin - mDividerWidth;
        }
        drawVerticalDivider(paramCanvas, j);
      }
      i += 1;
    }
    if (hasDividerBeforeChildAt(k))
    {
      localView = getVirtualChildAt(k - 1);
      if (localView == null)
      {
        if (bool) {
          i = getPaddingLeft();
        } else {
          i = getWidth() - getPaddingRight() - mDividerWidth;
        }
      }
      else
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (bool) {
          i = localView.getLeft() - leftMargin - mDividerWidth;
        } else {
          i = localView.getRight() + rightMargin;
        }
      }
      drawVerticalDivider(paramCanvas, i);
    }
  }
  
  void drawDividersVertical(Canvas paramCanvas)
  {
    int j = getVirtualChildCount();
    int i = 0;
    View localView;
    LayoutParams localLayoutParams;
    while (i < j)
    {
      localView = getVirtualChildAt(i);
      if ((localView != null) && (localView.getVisibility() != 8) && (hasDividerBeforeChildAt(i)))
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        drawHorizontalDivider(paramCanvas, localView.getTop() - topMargin - mDividerHeight);
      }
      i += 1;
    }
    if (hasDividerBeforeChildAt(j))
    {
      localView = getVirtualChildAt(j - 1);
      if (localView == null)
      {
        i = getHeight() - getPaddingBottom() - mDividerHeight;
      }
      else
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        i = localView.getBottom() + bottomMargin;
      }
      drawHorizontalDivider(paramCanvas, i);
    }
  }
  
  void drawHorizontalDivider(Canvas paramCanvas, int paramInt)
  {
    mDivider.setBounds(getPaddingLeft() + mDividerPadding, paramInt, getWidth() - getPaddingRight() - mDividerPadding, mDividerHeight + paramInt);
    mDivider.draw(paramCanvas);
  }
  
  void drawVerticalDivider(Canvas paramCanvas, int paramInt)
  {
    mDivider.setBounds(paramInt, getPaddingTop() + mDividerPadding, mDividerWidth + paramInt, getHeight() - getPaddingBottom() - mDividerPadding);
    mDivider.draw(paramCanvas);
  }
  
  protected LayoutParams generateDefaultLayoutParams()
  {
    if (mOrientation == 0) {
      return new LayoutParams(-2, -2);
    }
    if (mOrientation == 1) {
      return new LayoutParams(-1, -2);
    }
    return null;
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return new LayoutParams(paramLayoutParams);
  }
  
  public int getBaseline()
  {
    if (mBaselineAlignedChildIndex < 0) {
      return super.getBaseline();
    }
    if (getChildCount() <= mBaselineAlignedChildIndex) {
      throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
    }
    View localView = getChildAt(mBaselineAlignedChildIndex);
    int k = localView.getBaseline();
    if (k == -1)
    {
      if (mBaselineAlignedChildIndex == 0) {
        return -1;
      }
      throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
    }
    int j = mBaselineChildTop;
    int i = j;
    if (mOrientation == 1)
    {
      int m = mGravity & 0x70;
      i = j;
      if (m != 48) {
        if (m != 16)
        {
          if (m != 80) {
            i = j;
          } else {
            i = getBottom() - getTop() - getPaddingBottom() - mTotalLength;
          }
        }
        else {
          i = j + (getBottom() - getTop() - getPaddingTop() - getPaddingBottom() - mTotalLength) / 2;
        }
      }
    }
    return i + getLayoutParamstopMargin + k;
  }
  
  public int getBaselineAlignedChildIndex()
  {
    return mBaselineAlignedChildIndex;
  }
  
  int getChildrenSkipCount(View paramView, int paramInt)
  {
    return 0;
  }
  
  public Drawable getDividerDrawable()
  {
    return mDivider;
  }
  
  public int getDividerPadding()
  {
    return mDividerPadding;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public int getDividerWidth()
  {
    return mDividerWidth;
  }
  
  public int getGravity()
  {
    return mGravity;
  }
  
  int getLocationOffset(View paramView)
  {
    return 0;
  }
  
  int getNextLocationOffset(View paramView)
  {
    return 0;
  }
  
  public int getOrientation()
  {
    return mOrientation;
  }
  
  public int getShowDividers()
  {
    return mShowDividers;
  }
  
  View getVirtualChildAt(int paramInt)
  {
    return getChildAt(paramInt);
  }
  
  int getVirtualChildCount()
  {
    return getChildCount();
  }
  
  public float getWeightSum()
  {
    return mWeightSum;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  protected boolean hasDividerBeforeChildAt(int paramInt)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    if (paramInt == 0)
    {
      if ((mShowDividers & 0x1) != 0) {
        bool1 = true;
      }
      return bool1;
    }
    if (paramInt == getChildCount())
    {
      bool1 = bool2;
      if ((mShowDividers & 0x4) != 0) {
        bool1 = true;
      }
      return bool1;
    }
    if ((mShowDividers & 0x2) != 0)
    {
      paramInt -= 1;
      while (paramInt >= 0)
      {
        if (getChildAt(paramInt).getVisibility() != 8) {
          return true;
        }
        paramInt -= 1;
      }
      return false;
    }
    return false;
  }
  
  public boolean isBaselineAligned()
  {
    return mBaselineAligned;
  }
  
  public boolean isMeasureWithLargestChildEnabled()
  {
    return mUseLargestChild;
  }
  
  void layoutHorizontal(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    boolean bool2 = ViewUtils.isLayoutRtl(this);
    int i1 = getPaddingTop();
    int i3 = paramInt4 - paramInt2;
    int i4 = getPaddingBottom();
    int i5 = getPaddingBottom();
    int m = getVirtualChildCount();
    paramInt4 = mGravity;
    paramInt2 = mGravity & 0x70;
    boolean bool1 = mBaselineAligned;
    int[] arrayOfInt1 = mMaxAscent;
    int[] arrayOfInt2 = mMaxDescent;
    paramInt4 = GravityCompat.getAbsoluteGravity(paramInt4 & 0x800007, ViewCompat.getLayoutDirection(this));
    int i9 = 1;
    if (paramInt4 != 1)
    {
      if (paramInt4 != 5) {
        paramInt1 = getPaddingLeft();
      } else {
        paramInt1 = getPaddingLeft() + paramInt3 - paramInt1 - mTotalLength;
      }
    }
    else
    {
      paramInt4 = getPaddingLeft();
      paramInt1 = (paramInt3 - paramInt1 - mTotalLength) / 2 + paramInt4;
    }
    int j;
    int k;
    if (bool2)
    {
      j = m - 1;
      k = -1;
    }
    else
    {
      j = 0;
      k = 1;
    }
    paramInt4 = 0;
    paramInt3 = i1;
    while (paramInt4 < m)
    {
      int i7 = j + k * paramInt4;
      View localView = getVirtualChildAt(i7);
      if (localView == null) {
        paramInt1 += measureNullChild(i7);
      }
      for (;;)
      {
        break;
        if (localView.getVisibility() != 8)
        {
          int i6 = localView.getMeasuredWidth();
          int i8 = localView.getMeasuredHeight();
          LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
          if ((bool1) && (height != -1)) {
            n = localView.getBaseline();
          } else {
            n = -1;
          }
          int i2 = gravity;
          int i = i2;
          if (i2 < 0) {
            i = paramInt2;
          }
          i &= 0x70;
          if (i != 16)
          {
            if (i != 48)
            {
              if (i != 80) {
                i = paramInt3;
              }
              for (;;)
              {
                break;
                i2 = i3 - i4 - i8 - bottomMargin;
                i = i2;
                if (n != -1)
                {
                  i = localView.getMeasuredHeight();
                  i = i2 - (arrayOfInt2[2] - (i - n));
                }
              }
            }
            i = topMargin + paramInt3;
            if (n != -1) {
              i += arrayOfInt1[1] - n;
            }
          }
          else
          {
            i = (i3 - i1 - i5 - i8) / 2 + paramInt3 + topMargin - bottomMargin;
          }
          i9 = 1;
          int n = paramInt1;
          if (hasDividerBeforeChildAt(i7)) {
            n = paramInt1 + mDividerWidth;
          }
          paramInt1 = leftMargin + n;
          setChildFrame(localView, paramInt1 + getLocationOffset(localView), i, i6, i8);
          i = rightMargin;
          n = getNextLocationOffset(localView);
          paramInt4 += getChildrenSkipCount(localView, i7);
          paramInt1 += i6 + i + n;
        }
        else
        {
          i9 = 1;
        }
      }
      paramInt4 += 1;
    }
  }
  
  void layoutVertical(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getPaddingLeft();
    int j = paramInt3 - paramInt1;
    int k = getPaddingRight();
    int m = getPaddingRight();
    int n = getVirtualChildCount();
    paramInt1 = mGravity & 0x70;
    int i1 = mGravity;
    if (paramInt1 != 16)
    {
      if (paramInt1 != 80) {
        paramInt1 = getPaddingTop();
      } else {
        paramInt1 = getPaddingTop() + paramInt4 - paramInt2 - mTotalLength;
      }
    }
    else
    {
      paramInt1 = getPaddingTop();
      paramInt1 = (paramInt4 - paramInt2 - mTotalLength) / 2 + paramInt1;
    }
    paramInt2 = 0;
    while (paramInt2 < n)
    {
      View localView = getVirtualChildAt(paramInt2);
      if (localView == null) {
        paramInt3 = paramInt1 + measureNullChild(paramInt2);
      }
      do
      {
        paramInt1 = paramInt3;
        break;
        paramInt3 = paramInt1;
      } while (localView.getVisibility() == 8);
      int i3 = localView.getMeasuredWidth();
      int i2 = localView.getMeasuredHeight();
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      paramInt4 = gravity;
      paramInt3 = paramInt4;
      if (paramInt4 < 0) {
        paramInt3 = i1 & 0x800007;
      }
      paramInt3 = GravityCompat.getAbsoluteGravity(paramInt3, ViewCompat.getLayoutDirection(this)) & 0x7;
      if (paramInt3 != 1) {
        if (paramInt3 != 5) {
          paramInt3 = leftMargin + i;
        }
      }
      for (;;)
      {
        break;
        paramInt3 = j - k - i3 - rightMargin;
        continue;
        paramInt3 = (j - i - m - i3) / 2 + i + leftMargin - rightMargin;
      }
      paramInt4 = paramInt1;
      if (hasDividerBeforeChildAt(paramInt2)) {
        paramInt4 = paramInt1 + mDividerHeight;
      }
      paramInt1 = paramInt4 + topMargin;
      setChildFrame(localView, paramInt3, paramInt1 + getLocationOffset(localView), i3, i2);
      paramInt3 = bottomMargin;
      paramInt4 = getNextLocationOffset(localView);
      paramInt2 += getChildrenSkipCount(localView, paramInt2);
      paramInt1 += i2 + paramInt3 + paramInt4;
      paramInt2 += 1;
    }
  }
  
  void measureChildBeforeLayout(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    measureChildWithMargins(paramView, paramInt2, paramInt3, paramInt4, paramInt5);
  }
  
  void measureHorizontal(int paramInt1, int paramInt2)
  {
    mTotalLength = 0;
    int i11 = getVirtualChildCount();
    int i13 = View.MeasureSpec.getMode(paramInt1);
    int i12 = View.MeasureSpec.getMode(paramInt2);
    if ((mMaxAscent == null) || (mMaxDescent == null))
    {
      mMaxAscent = new int[4];
      mMaxDescent = new int[4];
    }
    int[] arrayOfInt = mMaxAscent;
    Object localObject1 = mMaxDescent;
    arrayOfInt[3] = -1;
    arrayOfInt[2] = -1;
    arrayOfInt[1] = -1;
    arrayOfInt[0] = -1;
    localObject1[3] = -1;
    localObject1[2] = -1;
    localObject1[1] = -1;
    localObject1[0] = -1;
    boolean bool1 = mBaselineAligned;
    boolean bool2 = mUseLargestChild;
    int i5 = 1073741824;
    int i6;
    if (i13 == 1073741824) {
      i6 = 1;
    } else {
      i6 = 0;
    }
    int m = 0;
    int k = m;
    int i2 = k;
    int i4 = i2;
    int j = i4;
    int i1 = j;
    int i3 = i1;
    int n = i3;
    int i = 1;
    float f1 = 0.0F;
    View localView;
    int i7;
    Object localObject2;
    while (m < i11)
    {
      localView = getVirtualChildAt(m);
      if (localView == null) {
        mTotalLength += measureNullChild(m);
      }
      for (;;)
      {
        i7 = i5;
        i5 = m;
        m = i7;
        break label875;
        if (localView.getVisibility() != 8) {
          break;
        }
        m += getChildrenSkipCount(localView, m);
      }
      if (hasDividerBeforeChildAt(m)) {
        mTotalLength += mDividerWidth;
      }
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      f1 += weight;
      if ((i13 == i5) && (width == 0) && (weight > 0.0F))
      {
        if (i6 != 0)
        {
          mTotalLength += leftMargin + rightMargin;
        }
        else
        {
          i5 = mTotalLength;
          mTotalLength = Math.max(i5, leftMargin + i5 + rightMargin);
        }
        if (bool1)
        {
          i5 = View.MeasureSpec.makeMeasureSpec(0, 0);
          localView.measure(i5, i5);
          i5 = k;
        }
        else
        {
          i4 = 1;
          break label595;
        }
      }
      else
      {
        if ((width == 0) && (weight > 0.0F))
        {
          width = -2;
          i5 = 0;
        }
        else
        {
          i5 = Integer.MIN_VALUE;
        }
        if (f1 == 0.0F) {
          i7 = mTotalLength;
        } else {
          i7 = 0;
        }
        localObject2 = localView;
        measureChildBeforeLayout(localView, m, paramInt1, i7, paramInt2, 0);
        if (i5 != Integer.MIN_VALUE) {
          width = i5;
        }
        i7 = ((View)localObject2).getMeasuredWidth();
        if (i6 != 0)
        {
          mTotalLength += leftMargin + i7 + rightMargin + getNextLocationOffset((View)localObject2);
        }
        else
        {
          i5 = mTotalLength;
          mTotalLength = Math.max(i5, i5 + i7 + leftMargin + rightMargin + getNextLocationOffset((View)localObject2));
        }
        i5 = k;
        if (bool2) {
          i5 = Math.max(i7, k);
        }
      }
      k = i5;
      label595:
      int i9 = m;
      i8 = 1073741824;
      if ((i12 != 1073741824) && (height == -1))
      {
        m = 1;
        n = m;
      }
      else
      {
        m = 0;
      }
      i5 = topMargin + bottomMargin;
      i7 = localView.getMeasuredHeight() + i5;
      int i10 = View.combineMeasuredStates(i3, localView.getMeasuredState());
      if (bool1)
      {
        int i14 = localView.getBaseline();
        if (i14 != -1)
        {
          if (gravity < 0) {
            i3 = mGravity;
          } else {
            i3 = gravity;
          }
          i3 = ((i3 & 0x70) >> 4 & 0xFFFFFFFE) >> 1;
          arrayOfInt[i3] = Math.max(arrayOfInt[i3], i14);
          localObject1[i3] = Math.max(localObject1[i3], i7 - i14);
        }
      }
      i2 = Math.max(i2, i7);
      if ((i != 0) && (height == -1)) {
        i = 1;
      } else {
        i = 0;
      }
      if (weight > 0.0F) {
        if (m == 0) {
          for (;;)
          {
            i5 = i7;
          }
        }
      }
      for (m = Math.max(i1, i5);; m = i1)
      {
        break;
        if (m != 0) {
          i7 = i5;
        }
        j = Math.max(j, i7);
      }
      i5 = getChildrenSkipCount(localView, i9) + i9;
      i3 = i10;
      i1 = m;
      m = i8;
      label875:
      i7 = m;
      m = i5 + 1;
      i5 = i7;
    }
    m = i2;
    if ((mTotalLength > 0) && (hasDividerBeforeChildAt(i11))) {
      mTotalLength += mDividerWidth;
    }
    if ((arrayOfInt[1] == -1) && (arrayOfInt[0] == -1) && (arrayOfInt[2] == -1))
    {
      i2 = m;
      if (arrayOfInt[3] == -1) {}
    }
    else
    {
      i2 = Math.max(m, Math.max(arrayOfInt[3], Math.max(arrayOfInt[0], Math.max(arrayOfInt[1], arrayOfInt[2]))) + Math.max(localObject1[3], Math.max(localObject1[0], Math.max(localObject1[1], localObject1[2]))));
    }
    if ((bool2) && ((i13 == Integer.MIN_VALUE) || (i13 == 0)))
    {
      mTotalLength = 0;
      m = 0;
      while (m < i11)
      {
        localView = getVirtualChildAt(m);
        if (localView == null)
        {
          mTotalLength += measureNullChild(m);
        }
        else
        {
          if (localView.getVisibility() != 8) {
            break label1109;
          }
          m += getChildrenSkipCount(localView, m);
        }
        for (;;)
        {
          break;
          label1109:
          localObject2 = (LayoutParams)localView.getLayoutParams();
          if (i6 != 0)
          {
            mTotalLength += leftMargin + k + rightMargin + getNextLocationOffset(localView);
          }
          else
          {
            i5 = mTotalLength;
            mTotalLength = Math.max(i5, i5 + k + leftMargin + rightMargin + getNextLocationOffset(localView));
          }
        }
        m += 1;
      }
    }
    mTotalLength += getPaddingLeft() + getPaddingRight();
    int i8 = View.resolveSizeAndState(Math.max(mTotalLength, getSuggestedMinimumWidth()), paramInt1, 0);
    i5 = (0xFFFFFF & i8) - mTotalLength;
    if ((i4 == 0) && ((i5 == 0) || (f1 <= 0.0F)))
    {
      m = Math.max(j, i1);
      if ((bool2) && (i13 != 1073741824))
      {
        j = 0;
        while (j < i11)
        {
          localObject1 = getVirtualChildAt(j);
          if ((localObject1 != null) && (((View)localObject1).getVisibility() != 8) && (getLayoutParamsweight > 0.0F)) {
            ((View)localObject1).measure(View.MeasureSpec.makeMeasureSpec(k, 1073741824), View.MeasureSpec.makeMeasureSpec(((View)localObject1).getMeasuredHeight(), 1073741824));
          }
          j += 1;
        }
      }
      j = m;
      k = i;
    }
    else
    {
      if (mWeightSum > 0.0F) {
        f1 = mWeightSum;
      }
      arrayOfInt[3] = -1;
      arrayOfInt[2] = -1;
      arrayOfInt[1] = -1;
      arrayOfInt[0] = -1;
      localObject1[3] = -1;
      localObject1[2] = -1;
      localObject1[1] = -1;
      localObject1[0] = -1;
      mTotalLength = 0;
      i1 = -1;
      i2 = 0;
      k = i;
      m = j;
      i = i3;
      j = i5;
      i3 = i2;
      while (i3 < i11)
      {
        localView = getVirtualChildAt(i3);
        if ((localView != null) && (localView.getVisibility() != 8))
        {
          localObject2 = (LayoutParams)localView.getLayoutParams();
          float f2 = weight;
          if (f2 > 0.0F)
          {
            i4 = (int)(j * f2 / f1);
            i7 = getChildMeasureSpec(paramInt2, getPaddingTop() + getPaddingBottom() + topMargin + bottomMargin, height);
            if ((width == 0) && (i13 == 1073741824))
            {
              if (i4 > 0) {
                i2 = i4;
              } else {
                i2 = 0;
              }
              localView.measure(View.MeasureSpec.makeMeasureSpec(i2, 1073741824), i7);
            }
            else
            {
              i5 = localView.getMeasuredWidth() + i4;
              i2 = i5;
              if (i5 < 0) {
                i2 = 0;
              }
              localView.measure(View.MeasureSpec.makeMeasureSpec(i2, 1073741824), i7);
            }
            i = View.combineMeasuredStates(i, localView.getMeasuredState() & 0xFF000000);
            f1 -= f2;
            j -= i4;
          }
          if (i6 != 0) {}
          for (mTotalLength += localView.getMeasuredWidth() + leftMargin + rightMargin + getNextLocationOffset(localView);; mTotalLength = Math.max(i2, localView.getMeasuredWidth() + i2 + leftMargin + rightMargin + getNextLocationOffset(localView)))
          {
            break;
            i2 = mTotalLength;
          }
          if ((i12 != 1073741824) && (height == -1)) {
            i2 = 1;
          } else {
            i2 = 0;
          }
          i7 = topMargin + bottomMargin;
          i5 = localView.getMeasuredHeight() + i7;
          i4 = Math.max(i1, i5);
          if (i2 != 0) {
            i1 = i7;
          } else {
            i1 = i5;
          }
          i1 = Math.max(m, i1);
          if ((k != 0) && (height == -1)) {
            k = 1;
          } else {
            k = 0;
          }
          if (bool1)
          {
            i2 = localView.getBaseline();
            if (i2 != -1)
            {
              if (gravity < 0) {
                m = mGravity;
              } else {
                m = gravity;
              }
              m = ((m & 0x70) >> 4 & 0xFFFFFFFE) >> 1;
              arrayOfInt[m] = Math.max(arrayOfInt[m], i2);
              localObject1[m] = Math.max(localObject1[m], i5 - i2);
            }
          }
          m = i1;
          i1 = i4;
        }
        i3 += 1;
      }
      mTotalLength += getPaddingLeft() + getPaddingRight();
      if ((arrayOfInt[1] == -1) && (arrayOfInt[0] == -1) && (arrayOfInt[2] == -1))
      {
        j = i1;
        if (arrayOfInt[3] == -1) {}
      }
      else
      {
        j = Math.max(i1, Math.max(arrayOfInt[3], Math.max(arrayOfInt[0], Math.max(arrayOfInt[1], arrayOfInt[2]))) + Math.max(localObject1[3], Math.max(localObject1[0], Math.max(localObject1[1], localObject1[2]))));
      }
      i3 = i;
      i2 = j;
      j = m;
    }
    if ((k != 0) || (i12 == 1073741824)) {
      j = i2;
    }
    setMeasuredDimension(i8 | 0xFF000000 & i3, View.resolveSizeAndState(Math.max(j + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), paramInt2, i3 << 16));
    if (n != 0) {
      forceUniformHeight(i11, paramInt1);
    }
  }
  
  int measureNullChild(int paramInt)
  {
    return 0;
  }
  
  void measureVertical(int paramInt1, int paramInt2)
  {
    mTotalLength = 0;
    int i4 = getVirtualChildCount();
    int i11 = View.MeasureSpec.getMode(paramInt1);
    int j = View.MeasureSpec.getMode(paramInt2);
    int i12 = mBaselineAlignedChildIndex;
    boolean bool = mUseLargestChild;
    int i1 = 0;
    int i6 = i1;
    int n = i6;
    int i = n;
    int m = i;
    int i2 = m;
    int i5 = i2;
    int i3 = i5;
    float f1 = 0.0F;
    int k = 1;
    View localView;
    Object localObject;
    int i10;
    int i9;
    while (i2 < i4)
    {
      localView = getVirtualChildAt(i2);
      if (localView == null)
      {
        mTotalLength += measureNullChild(i2);
      }
      else if (localView.getVisibility() == 8)
      {
        i2 += getChildrenSkipCount(localView, i2);
      }
      else
      {
        if (hasDividerBeforeChildAt(i2)) {
          mTotalLength += mDividerHeight;
        }
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        f1 += weight;
        if ((j == 1073741824) && (height == 0) && (weight > 0.0F))
        {
          i5 = mTotalLength;
          mTotalLength = Math.max(i5, topMargin + i5 + bottomMargin);
          i5 = 1;
        }
        else
        {
          if ((height == 0) && (weight > 0.0F))
          {
            height = -2;
            i7 = 0;
          }
          else
          {
            i7 = Integer.MIN_VALUE;
          }
          if (f1 == 0.0F) {
            i8 = mTotalLength;
          } else {
            i8 = 0;
          }
          localObject = localView;
          measureChildBeforeLayout(localView, i2, paramInt1, 0, paramInt2, i8);
          if (i7 != Integer.MIN_VALUE) {
            height = i7;
          }
          i7 = ((View)localObject).getMeasuredHeight();
          i8 = mTotalLength;
          mTotalLength = Math.max(i8, i8 + i7 + topMargin + bottomMargin + getNextLocationOffset((View)localObject));
          if (bool) {
            n = Math.max(i7, n);
          }
        }
        i7 = i;
        i10 = i2;
        if ((i12 >= 0) && (i12 == i10 + 1)) {
          mBaselineChildTop = mTotalLength;
        }
        if ((i10 < i12) && (weight > 0.0F)) {
          throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
        }
        if ((i11 != 1073741824) && (width == -1))
        {
          i2 = 1;
          i3 = i2;
        }
        else
        {
          i2 = 0;
        }
        i8 = leftMargin + rightMargin;
        i9 = localView.getMeasuredWidth() + i8;
        i6 = Math.max(i6, i9);
        i1 = View.combineMeasuredStates(i1, localView.getMeasuredState());
        if ((k != 0) && (width == -1)) {
          i = 1;
        } else {
          i = 0;
        }
        if (weight > 0.0F)
        {
          if (i2 == 0) {
            for (;;)
            {
              i8 = i9;
            }
          }
          i2 = Math.max(i7, i8);
          k = m;
          m = i2;
        }
        else
        {
          if (i2 != 0) {
            i9 = i8;
          }
          k = Math.max(m, i9);
          m = i7;
        }
        i7 = getChildrenSkipCount(localView, i10);
        i2 = i;
        i = m;
        i7 += i10;
        m = k;
        k = i2;
        i2 = i7;
      }
      i2 += 1;
    }
    int i7 = i1;
    i1 = i6;
    if ((mTotalLength > 0) && (hasDividerBeforeChildAt(i4))) {
      mTotalLength += mDividerHeight;
    }
    i6 = i4;
    if (bool)
    {
      i2 = j;
      if (i2 != Integer.MIN_VALUE)
      {
        i4 = i1;
        if (i2 != 0)
        {
          i1 = i4;
          break label865;
        }
      }
      mTotalLength = 0;
      i2 = 0;
      for (;;)
      {
        i4 = i1;
        if (i2 >= i6) {
          break;
        }
        localView = getVirtualChildAt(i2);
        if (localView == null)
        {
          mTotalLength += measureNullChild(i2);
        }
        else
        {
          if (localView.getVisibility() != 8) {
            break label807;
          }
          i2 += getChildrenSkipCount(localView, i2);
        }
        break label856;
        label807:
        localObject = (LayoutParams)localView.getLayoutParams();
        i4 = mTotalLength;
        mTotalLength = Math.max(i4, i4 + n + topMargin + bottomMargin + getNextLocationOffset(localView));
        label856:
        i2 += 1;
      }
    }
    label865:
    i4 = j;
    mTotalLength += getPaddingTop() + getPaddingBottom();
    int i8 = View.resolveSizeAndState(Math.max(mTotalLength, getSuggestedMinimumHeight()), paramInt2, 0);
    j = (0xFFFFFF & i8) - mTotalLength;
    if ((i5 == 0) && ((j == 0) || (f1 <= 0.0F)))
    {
      j = Math.max(m, i);
      if ((bool) && (i4 != 1073741824))
      {
        i = 0;
        while (i < i6)
        {
          localView = getVirtualChildAt(i);
          if ((localView != null) && (localView.getVisibility() != 8) && (getLayoutParamsweight > 0.0F)) {
            localView.measure(View.MeasureSpec.makeMeasureSpec(localView.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(n, 1073741824));
          }
          i += 1;
        }
      }
      m = i1;
      i = j;
    }
    else
    {
      if (mWeightSum > 0.0F) {
        f1 = mWeightSum;
      }
      mTotalLength = 0;
      i2 = 0;
      n = j;
      j = m;
      m = i1;
      i = i7;
      i1 = i2;
      while (i1 < i6)
      {
        localView = getVirtualChildAt(i1);
        if (localView.getVisibility() != 8)
        {
          localObject = (LayoutParams)localView.getLayoutParams();
          float f2 = weight;
          if (f2 > 0.0F)
          {
            i5 = (int)(n * f2 / f1);
            i7 = getPaddingLeft();
            i9 = getPaddingRight();
            i2 = n - i5;
            n = leftMargin;
            i10 = rightMargin;
            i12 = width;
            f1 -= f2;
            i7 = getChildMeasureSpec(paramInt1, i7 + i9 + n + i10, i12);
            if ((height == 0) && (i4 == 1073741824))
            {
              if (i5 > 0) {
                n = i5;
              } else {
                n = 0;
              }
              localView.measure(i7, View.MeasureSpec.makeMeasureSpec(n, 1073741824));
            }
            else
            {
              i5 = localView.getMeasuredHeight() + i5;
              n = i5;
              if (i5 < 0) {
                n = 0;
              }
              localView.measure(i7, View.MeasureSpec.makeMeasureSpec(n, 1073741824));
            }
            i = View.combineMeasuredStates(i, localView.getMeasuredState() & 0xFF00);
            n = i2;
          }
          i5 = leftMargin + rightMargin;
          i7 = localView.getMeasuredWidth() + i5;
          i2 = Math.max(m, i7);
          if ((i11 != 1073741824) && (width == -1)) {
            m = 1;
          } else {
            m = 0;
          }
          if (m != 0) {
            m = i5;
          } else {
            m = i7;
          }
          i5 = Math.max(j, m);
          if ((k != 0) && (width == -1)) {
            j = 1;
          } else {
            j = 0;
          }
          k = mTotalLength;
          mTotalLength = Math.max(k, localView.getMeasuredHeight() + k + topMargin + bottomMargin + getNextLocationOffset(localView));
          m = i2;
          i2 = i5;
          k = j;
          j = i2;
        }
        i1 += 1;
      }
      mTotalLength += getPaddingTop() + getPaddingBottom();
      i7 = i;
      i = j;
    }
    if ((k != 0) || (i11 == 1073741824)) {
      i = m;
    }
    setMeasuredDimension(View.resolveSizeAndState(Math.max(i + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), paramInt1, i7), i8);
    if (i3 != 0) {
      forceUniformWidth(i6, paramInt2);
    }
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    if (mDivider == null) {
      return;
    }
    if (mOrientation == 1)
    {
      drawDividersVertical(paramCanvas);
      return;
    }
    drawDividersHorizontal(paramCanvas);
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (mOrientation == 1)
    {
      layoutVertical(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    layoutHorizontal(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (mOrientation == 1)
    {
      measureVertical(paramInt1, paramInt2);
      return;
    }
    measureHorizontal(paramInt1, paramInt2);
  }
  
  public void setBaselineAligned(boolean paramBoolean)
  {
    mBaselineAligned = paramBoolean;
  }
  
  public void setBaselineAlignedChildIndex(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < getChildCount()))
    {
      mBaselineAlignedChildIndex = paramInt;
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("base aligned child index out of range (0, ");
    localStringBuilder.append(getChildCount());
    localStringBuilder.append(")");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  public void setDividerDrawable(Drawable paramDrawable)
  {
    if (paramDrawable == mDivider) {
      return;
    }
    mDivider = paramDrawable;
    boolean bool = false;
    if (paramDrawable != null)
    {
      mDividerWidth = paramDrawable.getIntrinsicWidth();
      mDividerHeight = paramDrawable.getIntrinsicHeight();
    }
    else
    {
      mDividerWidth = 0;
      mDividerHeight = 0;
    }
    if (paramDrawable == null) {
      bool = true;
    }
    setWillNotDraw(bool);
    requestLayout();
  }
  
  public void setDividerPadding(int paramInt)
  {
    mDividerPadding = paramInt;
  }
  
  public void setGravity(int paramInt)
  {
    if (mGravity != paramInt)
    {
      int i = paramInt;
      if ((0x800007 & paramInt) == 0) {
        i = paramInt | 0x800003;
      }
      paramInt = i;
      if ((i & 0x70) == 0) {
        paramInt = i | 0x30;
      }
      mGravity = paramInt;
      requestLayout();
    }
  }
  
  public void setHorizontalGravity(int paramInt)
  {
    paramInt &= 0x800007;
    if ((0x800007 & mGravity) != paramInt)
    {
      mGravity = (paramInt | mGravity & 0xFF7FFFF8);
      requestLayout();
    }
  }
  
  public void setMeasureWithLargestChildEnabled(boolean paramBoolean)
  {
    mUseLargestChild = paramBoolean;
  }
  
  public void setOrientation(int paramInt)
  {
    if (mOrientation != paramInt)
    {
      mOrientation = paramInt;
      requestLayout();
    }
  }
  
  public void setShowDividers(int paramInt)
  {
    if (paramInt != mShowDividers) {
      requestLayout();
    }
    mShowDividers = paramInt;
  }
  
  public void setVerticalGravity(int paramInt)
  {
    paramInt &= 0x70;
    if ((mGravity & 0x70) != paramInt)
    {
      mGravity = (paramInt | mGravity & 0xFFFFFF8F);
      requestLayout();
    }
  }
  
  public void setWeightSum(float paramFloat)
  {
    mWeightSum = Math.max(0.0F, paramFloat);
  }
  
  public boolean shouldDelayChildPressedState()
  {
    return false;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface DividerMode {}
  
  public static class LayoutParams
    extends ViewGroup.MarginLayoutParams
  {
    public int gravity = -1;
    public float weight;
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
      weight = 0.0F;
    }
    
    public LayoutParams(int paramInt1, int paramInt2, float paramFloat)
    {
      super(paramInt2);
      weight = paramFloat;
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.LinearLayoutCompat_Layout);
      weight = paramContext.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0F);
      gravity = paramContext.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
      paramContext.recycle();
    }
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
      weight = weight;
      gravity = gravity;
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface OrientationMode {}
}

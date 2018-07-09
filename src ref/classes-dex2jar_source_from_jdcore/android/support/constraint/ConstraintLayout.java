package android.support.constraint;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintAnchor.Strength;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.InflateException;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.ArrayList;
import java.util.HashMap;

public class ConstraintLayout
  extends ViewGroup
{
  static final boolean ALLOWS_EMBEDDED = false;
  public static final int DESIGN_INFO_ID = 0;
  private static final boolean SIMPLE_LAYOUT = true;
  private static final String TAG = "ConstraintLayout";
  private static final boolean USE_CONSTRAINTS_HELPER = true;
  public static final String VERSION = "ConstraintLayout-1.1.0-beta1";
  SparseArray<View> mChildrenByIds = new SparseArray();
  private ArrayList<ConstraintHelper> mConstraintHelpers = new ArrayList(4);
  private ConstraintSet mConstraintSet = null;
  private int mConstraintSetId = -1;
  private HashMap<String, Integer> mDesignIds = new HashMap();
  private boolean mDirtyHierarchy = true;
  ConstraintWidgetContainer mLayoutWidget = new ConstraintWidgetContainer();
  private int mMaxHeight = Integer.MAX_VALUE;
  private int mMaxWidth = Integer.MAX_VALUE;
  private int mMinHeight = 0;
  private int mMinWidth = 0;
  private int mOptimizationLevel = 2;
  private String mTitle;
  private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList(100);
  
  public ConstraintLayout(Context paramContext)
  {
    super(paramContext);
    init(null);
  }
  
  public ConstraintLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet);
  }
  
  public ConstraintLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet);
  }
  
  private final ConstraintWidget getTargetWidget(int paramInt)
  {
    if (paramInt == 0) {
      return mLayoutWidget;
    }
    View localView = (View)mChildrenByIds.get(paramInt);
    if (localView == this) {
      return mLayoutWidget;
    }
    if (localView == null) {
      return null;
    }
    return getLayoutParamswidget;
  }
  
  private void init(AttributeSet paramAttributeSet)
  {
    mLayoutWidget.setCompanionWidget(this);
    mChildrenByIds.put(getId(), this);
    mConstraintSet = null;
    if (paramAttributeSet != null)
    {
      paramAttributeSet = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.ConstraintLayout_Layout);
      int j = paramAttributeSet.getIndexCount();
      int i = 0;
      while (i < j)
      {
        int k = paramAttributeSet.getIndex(i);
        if (k == R.styleable.ConstraintLayout_Layout_android_minWidth) {
          mMinWidth = paramAttributeSet.getDimensionPixelOffset(k, mMinWidth);
        } else if (k == R.styleable.ConstraintLayout_Layout_android_minHeight) {
          mMinHeight = paramAttributeSet.getDimensionPixelOffset(k, mMinHeight);
        } else if (k == R.styleable.ConstraintLayout_Layout_android_maxWidth) {
          mMaxWidth = paramAttributeSet.getDimensionPixelOffset(k, mMaxWidth);
        } else if (k == R.styleable.ConstraintLayout_Layout_android_maxHeight) {
          mMaxHeight = paramAttributeSet.getDimensionPixelOffset(k, mMaxHeight);
        } else if (k == R.styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
          mOptimizationLevel = paramAttributeSet.getInt(k, mOptimizationLevel);
        } else if (k == R.styleable.ConstraintLayout_Layout_title) {
          mTitle = paramAttributeSet.getString(k);
        } else if (k == R.styleable.ConstraintLayout_Layout_constraintSet) {
          k = paramAttributeSet.getResourceId(k, 0);
        }
        try
        {
          mConstraintSet = new ConstraintSet();
          mConstraintSet.load(getContext(), k);
        }
        catch (Resources.NotFoundException localNotFoundException)
        {
          for (;;) {}
        }
        mConstraintSet = null;
        mConstraintSetId = k;
        i += 1;
      }
      paramAttributeSet.recycle();
    }
    mLayoutWidget.setOptimizationLevel(mOptimizationLevel);
  }
  
  private void internalMeasureChildren(int paramInt1, int paramInt2)
  {
    int i4 = getPaddingTop() + getPaddingBottom();
    int i5 = getPaddingLeft() + getPaddingRight();
    int i3 = getChildCount();
    int k = 0;
    View localView;
    while (k < i3)
    {
      localView = getChildAt(k);
      if (localView.getVisibility() != 8)
      {
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        ConstraintWidget localConstraintWidget = widget;
        if ((!isGuideline) && (!isHelper))
        {
          int i1 = width;
          int i2 = height;
          int i;
          if ((!horizontalDimensionFixed) && (!verticalDimensionFixed) && ((horizontalDimensionFixed) || (matchConstraintDefaultWidth != 1)) && (width != -1) && ((verticalDimensionFixed) || ((matchConstraintDefaultHeight != 1) && (height != -1)))) {
            i = 0;
          } else {
            i = 1;
          }
          int m;
          int n;
          int j;
          if (i != 0)
          {
            if ((i1 != 0) && (i1 != -1))
            {
              m = getChildMeasureSpec(paramInt1, i5, i1);
              i = 0;
            }
            else
            {
              m = getChildMeasureSpec(paramInt1, i5, -2);
              i = 1;
            }
            if ((i2 != 0) && (i2 != -1))
            {
              n = getChildMeasureSpec(paramInt2, i4, i2);
              j = 0;
            }
            else
            {
              n = getChildMeasureSpec(paramInt2, i4, -2);
              j = 1;
            }
            localView.measure(m, n);
            boolean bool;
            if (i1 == -2) {
              bool = true;
            } else {
              bool = false;
            }
            localConstraintWidget.setWidthWrapContent(bool);
            if (i2 == -2) {
              bool = true;
            } else {
              bool = false;
            }
            localConstraintWidget.setHeightWrapContent(bool);
            m = localView.getMeasuredWidth();
            n = localView.getMeasuredHeight();
          }
          else
          {
            i = 0;
            j = 0;
            n = i2;
            m = i1;
          }
          localConstraintWidget.setWidth(m);
          localConstraintWidget.setHeight(n);
          if (i != 0) {
            localConstraintWidget.setWrapWidth(m);
          }
          if (j != 0) {
            localConstraintWidget.setWrapHeight(n);
          }
          if (needsBaseline)
          {
            i = localView.getBaseline();
            if (i != -1) {
              localConstraintWidget.setBaselineDistance(i);
            }
          }
        }
      }
      k += 1;
    }
    paramInt1 = 0;
    while (paramInt1 < i3)
    {
      localView = getChildAt(paramInt1);
      if ((localView instanceof Placeholder)) {
        ((Placeholder)localView).updatePostMeasure(this);
      }
      paramInt1 += 1;
    }
    paramInt2 = mConstraintHelpers.size();
    if (paramInt2 > 0)
    {
      paramInt1 = 0;
      while (paramInt1 < paramInt2)
      {
        ((ConstraintHelper)mConstraintHelpers.get(paramInt1)).updatePostMeasure(this);
        paramInt1 += 1;
      }
    }
  }
  
  private void setChildrenConstraints()
  {
    boolean bool3 = isInEditMode();
    int i5 = getChildCount();
    boolean bool1 = false;
    int i;
    if (bool3) {
      i = 0;
    }
    for (;;)
    {
      Object localObject1;
      if (i < i5) {
        localObject1 = getChildAt(i);
      }
      try
      {
        setDesignInformation(0, getResources().getResourceName(((View)localObject1).getId()), Integer.valueOf(((View)localObject1).getId()));
        i += 1;
        continue;
        if (mConstraintSetId != -1)
        {
          i = 0;
          while (i < i5)
          {
            localObject1 = getChildAt(i);
            if ((((View)localObject1).getId() == mConstraintSetId) && ((localObject1 instanceof Constraints))) {
              mConstraintSet = ((Constraints)localObject1).getConstraintSet();
            }
            i += 1;
          }
        }
        if (mConstraintSet != null) {
          mConstraintSet.applyToInternal(this);
        }
        mLayoutWidget.removeAllChildren();
        int j = mConstraintHelpers.size();
        if (j > 0)
        {
          i = 0;
          while (i < j)
          {
            ((ConstraintHelper)mConstraintHelpers.get(i)).updatePreLayout(this);
            i += 1;
          }
        }
        i = 0;
        while (i < i5)
        {
          localObject1 = getChildAt(i);
          if ((localObject1 instanceof Placeholder)) {
            ((Placeholder)localObject1).updatePreLayout(this);
          }
          i += 1;
        }
        int k = 0;
        while (k < i5)
        {
          Object localObject3 = getChildAt(k);
          Object localObject2 = getViewWidget((View)localObject3);
          boolean bool2;
          if (localObject2 == null)
          {
            bool2 = bool1;
          }
          else
          {
            localObject1 = (LayoutParams)((View)localObject3).getLayoutParams();
            ((LayoutParams)localObject1).validate();
            if (helped) {
              helped = bool1;
            } else {
              ((ConstraintWidget)localObject2).reset();
            }
            ((ConstraintWidget)localObject2).setVisibility(((View)localObject3).getVisibility());
            if (isInPlaceholder) {
              ((ConstraintWidget)localObject2).setVisibility(8);
            }
            ((ConstraintWidget)localObject2).setCompanionWidget(localObject3);
            mLayoutWidget.add((ConstraintWidget)localObject2);
            if ((!verticalDimensionFixed) || (!horizontalDimensionFixed)) {
              mVariableDimensionsWidgets.add(localObject2);
            }
            if (isGuideline)
            {
              localObject2 = (android.support.constraint.solver.widgets.Guideline)localObject2;
              if (guideBegin != -1) {
                ((android.support.constraint.solver.widgets.Guideline)localObject2).setGuideBegin(guideBegin);
              }
              if (guideEnd != -1) {
                ((android.support.constraint.solver.widgets.Guideline)localObject2).setGuideEnd(guideEnd);
              }
              bool2 = bool1;
              if (guidePercent != -1.0F)
              {
                ((android.support.constraint.solver.widgets.Guideline)localObject2).setGuidePercent(guidePercent);
                bool2 = bool1;
              }
            }
            else if ((resolvedLeftToLeft == -1) && (resolvedLeftToRight == -1) && (resolvedRightToLeft == -1) && (resolvedRightToRight == -1) && (topToTop == -1) && (topToBottom == -1) && (bottomToTop == -1) && (bottomToBottom == -1) && (baselineToBaseline == -1) && (editorAbsoluteX == -1) && (editorAbsoluteY == -1) && (width != -1))
            {
              bool2 = bool1;
              if (height != -1) {}
            }
            else
            {
              int i4 = resolvedLeftToLeft;
              j = resolvedLeftToRight;
              int m = resolvedRightToLeft;
              i = resolvedRightToRight;
              int i2 = resolveGoneLeftMargin;
              int n = resolveGoneRightMargin;
              float f = resolvedHorizontalBias;
              int i1;
              if (Build.VERSION.SDK_INT < 17)
              {
                m = leftToLeft;
                n = leftToRight;
                i1 = rightToLeft;
                i4 = rightToRight;
                i2 = goneLeftMargin;
                int i3 = goneRightMargin;
                f = horizontalBias;
                j = m;
                i = n;
                if (m == -1)
                {
                  j = m;
                  i = n;
                  if (n == -1) {
                    if (startToStart != -1)
                    {
                      j = startToStart;
                      i = n;
                    }
                    else
                    {
                      j = m;
                      i = n;
                      if (startToEnd != -1)
                      {
                        i = startToEnd;
                        j = m;
                      }
                    }
                  }
                }
                m = j;
                n = i;
                j = i1;
                i = i4;
                if (i1 == -1)
                {
                  j = i1;
                  i = i4;
                  if (i4 == -1) {
                    if (endToStart != -1)
                    {
                      j = endToStart;
                      i = i4;
                    }
                    else
                    {
                      j = i1;
                      i = i4;
                      if (endToEnd != -1)
                      {
                        i = endToEnd;
                        j = i1;
                      }
                    }
                  }
                }
                i1 = n;
                i4 = m;
                m = j;
                n = i3;
              }
              else
              {
                i1 = j;
              }
              if (i4 != -1)
              {
                localObject3 = getTargetWidget(i4);
                if (localObject3 != null) {
                  ((ConstraintWidget)localObject2).immediateConnect(ConstraintAnchor.Type.LEFT, (ConstraintWidget)localObject3, ConstraintAnchor.Type.LEFT, leftMargin, i2);
                }
                j = i;
              }
              else
              {
                j = i;
                if (i1 != -1)
                {
                  localObject3 = getTargetWidget(i1);
                  j = i;
                  if (localObject3 != null)
                  {
                    ((ConstraintWidget)localObject2).immediateConnect(ConstraintAnchor.Type.LEFT, (ConstraintWidget)localObject3, ConstraintAnchor.Type.RIGHT, leftMargin, i2);
                    j = i;
                  }
                }
              }
              if (m != -1)
              {
                localObject3 = getTargetWidget(m);
                if (localObject3 != null) {
                  ((ConstraintWidget)localObject2).immediateConnect(ConstraintAnchor.Type.RIGHT, (ConstraintWidget)localObject3, ConstraintAnchor.Type.LEFT, rightMargin, n);
                }
              }
              else if (j != -1)
              {
                localObject3 = getTargetWidget(j);
                if (localObject3 != null) {
                  ((ConstraintWidget)localObject2).immediateConnect(ConstraintAnchor.Type.RIGHT, (ConstraintWidget)localObject3, ConstraintAnchor.Type.RIGHT, rightMargin, n);
                }
              }
              if (topToTop != -1)
              {
                localObject3 = getTargetWidget(topToTop);
                if (localObject3 != null) {
                  ((ConstraintWidget)localObject2).immediateConnect(ConstraintAnchor.Type.TOP, (ConstraintWidget)localObject3, ConstraintAnchor.Type.TOP, topMargin, goneTopMargin);
                }
              }
              else if (topToBottom != -1)
              {
                localObject3 = getTargetWidget(topToBottom);
                if (localObject3 != null) {
                  ((ConstraintWidget)localObject2).immediateConnect(ConstraintAnchor.Type.TOP, (ConstraintWidget)localObject3, ConstraintAnchor.Type.BOTTOM, topMargin, goneTopMargin);
                }
              }
              if (bottomToTop != -1)
              {
                localObject3 = getTargetWidget(bottomToTop);
                if (localObject3 != null) {
                  ((ConstraintWidget)localObject2).immediateConnect(ConstraintAnchor.Type.BOTTOM, (ConstraintWidget)localObject3, ConstraintAnchor.Type.TOP, bottomMargin, goneBottomMargin);
                }
              }
              else if (bottomToBottom != -1)
              {
                localObject3 = getTargetWidget(bottomToBottom);
                if (localObject3 != null) {
                  ((ConstraintWidget)localObject2).immediateConnect(ConstraintAnchor.Type.BOTTOM, (ConstraintWidget)localObject3, ConstraintAnchor.Type.BOTTOM, bottomMargin, goneBottomMargin);
                }
              }
              if (baselineToBaseline != -1)
              {
                Object localObject4 = (View)mChildrenByIds.get(baselineToBaseline);
                localObject3 = getTargetWidget(baselineToBaseline);
                if ((localObject3 != null) && (localObject4 != null) && ((((View)localObject4).getLayoutParams() instanceof LayoutParams)))
                {
                  localObject4 = (LayoutParams)((View)localObject4).getLayoutParams();
                  needsBaseline = true;
                  needsBaseline = true;
                  ((ConstraintWidget)localObject2).getAnchor(ConstraintAnchor.Type.BASELINE).connect(((ConstraintWidget)localObject3).getAnchor(ConstraintAnchor.Type.BASELINE), 0, -1, ConstraintAnchor.Strength.STRONG, 0, true);
                  ((ConstraintWidget)localObject2).getAnchor(ConstraintAnchor.Type.TOP).reset();
                  ((ConstraintWidget)localObject2).getAnchor(ConstraintAnchor.Type.BOTTOM).reset();
                }
              }
              if ((f >= 0.0F) && (f != 0.5F)) {
                ((ConstraintWidget)localObject2).setHorizontalBiasPercent(f);
              }
              if ((verticalBias >= 0.0F) && (verticalBias != 0.5F)) {
                ((ConstraintWidget)localObject2).setVerticalBiasPercent(verticalBias);
              }
              if ((bool3) && ((editorAbsoluteX != -1) || (editorAbsoluteY != -1))) {
                ((ConstraintWidget)localObject2).setOrigin(editorAbsoluteX, editorAbsoluteY);
              }
              if (!horizontalDimensionFixed)
              {
                if (width == -1)
                {
                  ((ConstraintWidget)localObject2).setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                  getAnchorLEFTmMargin = leftMargin;
                  getAnchorRIGHTmMargin = rightMargin;
                }
                else
                {
                  ((ConstraintWidget)localObject2).setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                  ((ConstraintWidget)localObject2).setWidth(0);
                }
              }
              else
              {
                ((ConstraintWidget)localObject2).setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ((ConstraintWidget)localObject2).setWidth(width);
              }
              if (!verticalDimensionFixed)
              {
                if (height == -1)
                {
                  ((ConstraintWidget)localObject2).setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                  getAnchorTOPmMargin = topMargin;
                  getAnchorBOTTOMmMargin = bottomMargin;
                }
                else
                {
                  ((ConstraintWidget)localObject2).setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                  ((ConstraintWidget)localObject2).setHeight(0);
                }
              }
              else
              {
                ((ConstraintWidget)localObject2).setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ((ConstraintWidget)localObject2).setHeight(height);
              }
              bool2 = false;
              if (dimensionRatio != null) {
                ((ConstraintWidget)localObject2).setDimensionRatio(dimensionRatio);
              }
              ((ConstraintWidget)localObject2).setHorizontalWeight(horizontalWeight);
              ((ConstraintWidget)localObject2).setVerticalWeight(verticalWeight);
              ((ConstraintWidget)localObject2).setHorizontalChainStyle(horizontalChainStyle);
              ((ConstraintWidget)localObject2).setVerticalChainStyle(verticalChainStyle);
              ((ConstraintWidget)localObject2).setHorizontalMatchStyle(matchConstraintDefaultWidth, matchConstraintMinWidth, matchConstraintMaxWidth, matchConstraintPercentWidth);
              ((ConstraintWidget)localObject2).setVerticalMatchStyle(matchConstraintDefaultHeight, matchConstraintMinHeight, matchConstraintMaxHeight, matchConstraintPercentHeight);
            }
          }
          k += 1;
          bool1 = bool2;
        }
        return;
      }
      catch (Resources.NotFoundException localNotFoundException)
      {
        for (;;) {}
      }
    }
  }
  
  private void setSelfDimensionBehaviour(int paramInt1, int paramInt2)
  {
    int m = View.MeasureSpec.getMode(paramInt1);
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    int i = View.MeasureSpec.getMode(paramInt2);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    int j = getPaddingTop();
    int k = getPaddingBottom();
    int n = getPaddingLeft();
    int i1 = getPaddingRight();
    ConstraintWidget.DimensionBehaviour localDimensionBehaviour1 = ConstraintWidget.DimensionBehaviour.FIXED;
    ConstraintWidget.DimensionBehaviour localDimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
    getLayoutParams();
    if (m != Integer.MIN_VALUE)
    {
      if (m != 0) {
        if (m == 1073741824) {}
      }
      for (;;)
      {
        paramInt1 = 0;
        break;
        paramInt1 = Math.min(mMaxWidth, paramInt1) - (n + i1);
        break;
        localDimensionBehaviour1 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
      }
    }
    localDimensionBehaviour1 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
    if (i != Integer.MIN_VALUE)
    {
      if (i != 0) {
        if (i == 1073741824) {}
      }
      for (;;)
      {
        paramInt2 = 0;
        break;
        paramInt2 = Math.min(mMaxHeight, paramInt2) - (j + k);
        break;
        localDimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
      }
    }
    localDimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
    mLayoutWidget.setMinWidth(0);
    mLayoutWidget.setMinHeight(0);
    mLayoutWidget.setHorizontalDimensionBehaviour(localDimensionBehaviour1);
    mLayoutWidget.setWidth(paramInt1);
    mLayoutWidget.setVerticalDimensionBehaviour(localDimensionBehaviour2);
    mLayoutWidget.setHeight(paramInt2);
    mLayoutWidget.setMinWidth(mMinWidth - getPaddingLeft() - getPaddingRight());
    mLayoutWidget.setMinHeight(mMinHeight - getPaddingTop() - getPaddingBottom());
  }
  
  private void updateHierarchy()
  {
    int m = getChildCount();
    int k = 0;
    int i = 0;
    int j;
    for (;;)
    {
      j = k;
      if (i >= m) {
        break;
      }
      if (getChildAt(i).isLayoutRequested())
      {
        j = 1;
        break;
      }
      i += 1;
    }
    if (j != 0)
    {
      mVariableDimensionsWidgets.clear();
      setChildrenConstraints();
    }
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.addView(paramView, paramInt, paramLayoutParams);
    if (Build.VERSION.SDK_INT < 14) {
      onViewAdded(paramView);
    }
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  protected LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams(-2, -2);
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return new LayoutParams(paramLayoutParams);
  }
  
  public Object getDesignInformation(int paramInt, Object paramObject)
  {
    if ((paramInt == 0) && ((paramObject instanceof String)))
    {
      paramObject = (String)paramObject;
      if ((mDesignIds != null) && (mDesignIds.containsKey(paramObject))) {
        return mDesignIds.get(paramObject);
      }
    }
    return null;
  }
  
  public int getMaxHeight()
  {
    return mMaxHeight;
  }
  
  public int getMaxWidth()
  {
    return mMaxWidth;
  }
  
  public int getMinHeight()
  {
    return mMinHeight;
  }
  
  public int getMinWidth()
  {
    return mMinWidth;
  }
  
  public String getTitle()
  {
    return mTitle;
  }
  
  public View getViewById(int paramInt)
  {
    return (View)mChildrenByIds.get(paramInt);
  }
  
  public final ConstraintWidget getViewWidget(View paramView)
  {
    if (paramView == this) {
      return mLayoutWidget;
    }
    if (paramView == null) {
      return null;
    }
    return getLayoutParamswidget;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt2 = getChildCount();
    paramBoolean = isInEditMode();
    paramInt3 = mConstraintHelpers.size();
    if (paramInt3 > 0)
    {
      paramInt1 = 0;
      while (paramInt1 < paramInt3)
      {
        ((ConstraintHelper)mConstraintHelpers.get(paramInt1)).updatePostLayout(this);
        paramInt1 += 1;
      }
    }
    paramInt1 = 0;
    while (paramInt1 < paramInt2)
    {
      View localView = getChildAt(paramInt1);
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      ConstraintWidget localConstraintWidget = widget;
      if (((localView.getVisibility() != 8) || (isGuideline) || (isHelper) || (paramBoolean)) && (!isInPlaceholder))
      {
        paramInt3 = localConstraintWidget.getDrawX();
        paramInt4 = localConstraintWidget.getDrawY();
        int i = localConstraintWidget.getWidth() + paramInt3;
        int j = localConstraintWidget.getHeight() + paramInt4;
        localView.layout(paramInt3, paramInt4, i, j);
        if ((localView instanceof Placeholder))
        {
          localView = ((Placeholder)localView).getContent();
          if (localView != null)
          {
            localView.setVisibility(0);
            localView.layout(paramInt3, paramInt4, i, j);
          }
        }
      }
      paramInt1 += 1;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    Object localObject = this;
    int i = getPaddingLeft();
    int j = getPaddingTop();
    mLayoutWidget.setX(i);
    mLayoutWidget.setY(j);
    setSelfDimensionBehaviour(paramInt1, paramInt2);
    int i5 = mLayoutWidget.getWidth();
    int i6 = mLayoutWidget.getHeight();
    if (mDirtyHierarchy)
    {
      mDirtyHierarchy = false;
      updateHierarchy();
    }
    internalMeasureChildren(paramInt1, paramInt2);
    if (getChildCount() > 0) {
      solveLinearSystem();
    }
    int i3 = mVariableDimensionsWidgets.size();
    int i10 = j + getPaddingBottom();
    int i11 = i + getPaddingRight();
    if (i3 > 0)
    {
      int i1;
      if (mLayoutWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      int i2;
      if (mLayoutWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
        i2 = 1;
      } else {
        i2 = 0;
      }
      j = Math.max(mLayoutWidget.getWidth(), mMinWidth);
      int n = Math.max(mLayoutWidget.getHeight(), mMinHeight);
      k = 0;
      i = k;
      int m = i;
      int i4 = i;
      i = n;
      View localView;
      while (i4 < i3)
      {
        localObject = (ConstraintWidget)mVariableDimensionsWidgets.get(i4);
        localView = (View)((ConstraintWidget)localObject).getCompanionWidget();
        int i8;
        int i9;
        int i7;
        if (localView == null)
        {
          i8 = i;
          i9 = k;
          n = m;
          i7 = j;
        }
        else
        {
          LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
          i8 = i;
          i9 = k;
          n = m;
          i7 = j;
          if (!isHelper) {
            if (isGuideline)
            {
              i8 = i;
              i9 = k;
              n = m;
              i7 = j;
            }
            else if (localView.getVisibility() == 8)
            {
              i8 = i;
              i9 = k;
              n = m;
              i7 = j;
            }
            else
            {
              if (width == -2) {
                n = getChildMeasureSpec(paramInt1, i11, width);
              } else {
                n = View.MeasureSpec.makeMeasureSpec(((ConstraintWidget)localObject).getWidth(), 1073741824);
              }
              if (height == -2) {
                i7 = getChildMeasureSpec(paramInt2, i10, height);
              } else {
                i7 = View.MeasureSpec.makeMeasureSpec(((ConstraintWidget)localObject).getHeight(), 1073741824);
              }
              localView.measure(n, i7);
              i8 = localView.getMeasuredWidth();
              i7 = localView.getMeasuredHeight();
              n = m;
              m = j;
              if (i8 != ((ConstraintWidget)localObject).getWidth())
              {
                ((ConstraintWidget)localObject).setWidth(i8);
                m = j;
                if (i1 != 0)
                {
                  m = j;
                  if (((ConstraintWidget)localObject).getRight() > j) {
                    m = Math.max(j, ((ConstraintWidget)localObject).getRight() + ((ConstraintWidget)localObject).getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
                  }
                }
                n = 1;
              }
              j = i;
              if (i7 != ((ConstraintWidget)localObject).getHeight())
              {
                ((ConstraintWidget)localObject).setHeight(i7);
                j = i;
                if (i2 != 0)
                {
                  j = i;
                  if (((ConstraintWidget)localObject).getBottom() > i) {
                    j = Math.max(i, ((ConstraintWidget)localObject).getBottom() + ((ConstraintWidget)localObject).getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
                  }
                }
                n = 1;
              }
              i = n;
              if (needsBaseline)
              {
                i7 = localView.getBaseline();
                i = n;
                if (i7 != -1)
                {
                  i = n;
                  if (i7 != ((ConstraintWidget)localObject).getBaselineDistance())
                  {
                    ((ConstraintWidget)localObject).setBaselineDistance(i7);
                    i = 1;
                  }
                }
              }
              i8 = j;
              i9 = k;
              n = i;
              i7 = m;
              if (Build.VERSION.SDK_INT >= 11)
              {
                i9 = combineMeasuredStates(k, localView.getMeasuredState());
                i7 = m;
                n = i;
                i8 = j;
              }
            }
          }
        }
        i4 += 1;
        i = i8;
        k = i9;
        m = n;
        j = i7;
      }
      if (m != 0)
      {
        localObject = this;
        mLayoutWidget.setWidth(i5);
        mLayoutWidget.setHeight(i6);
        solveLinearSystem();
        if (mLayoutWidget.getWidth() < j)
        {
          mLayoutWidget.setWidth(j);
          j = 1;
        }
        else
        {
          j = 0;
        }
        if (mLayoutWidget.getHeight() < i)
        {
          mLayoutWidget.setHeight(i);
          j = 1;
        }
        if (j != 0) {
          solveLinearSystem();
        }
      }
      j = 0;
      for (;;)
      {
        i = k;
        if (j >= i3) {
          break;
        }
        localObject = (ConstraintWidget)mVariableDimensionsWidgets.get(j);
        localView = (View)((ConstraintWidget)localObject).getCompanionWidget();
        if (localView != null)
        {
          while ((localView.getWidth() == ((ConstraintWidget)localObject).getWidth()) && (localView.getHeight() == ((ConstraintWidget)localObject).getHeight())) {}
          localView.measure(View.MeasureSpec.makeMeasureSpec(((ConstraintWidget)localObject).getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(((ConstraintWidget)localObject).getHeight(), 1073741824));
        }
        j += 1;
      }
    }
    i = 0;
    localObject = this;
    j = mLayoutWidget.getWidth() + i11;
    int k = mLayoutWidget.getHeight() + i10;
    if (Build.VERSION.SDK_INT >= 11)
    {
      paramInt1 = resolveSizeAndState(j, paramInt1, i);
      paramInt2 = resolveSizeAndState(k, paramInt2, i << 16);
      paramInt1 = Math.min(mMaxWidth, paramInt1);
      i = Math.min(mMaxHeight, paramInt2);
      paramInt2 = paramInt1 & 0xFFFFFF;
      i &= 0xFFFFFF;
      paramInt1 = paramInt2;
      if (mLayoutWidget.isWidthMeasuredTooSmall()) {
        paramInt1 = paramInt2 | 0x1000000;
      }
      paramInt2 = i;
      if (mLayoutWidget.isHeightMeasuredTooSmall()) {
        paramInt2 = i | 0x1000000;
      }
      ((ConstraintLayout)localObject).setMeasuredDimension(paramInt1, paramInt2);
      return;
    }
    ((ConstraintLayout)localObject).setMeasuredDimension(j, k);
  }
  
  public void onViewAdded(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 14) {
      super.onViewAdded(paramView);
    }
    Object localObject = getViewWidget(paramView);
    if (((paramView instanceof Guideline)) && (!(localObject instanceof android.support.constraint.solver.widgets.Guideline)))
    {
      localObject = (LayoutParams)paramView.getLayoutParams();
      widget = new android.support.constraint.solver.widgets.Guideline();
      isGuideline = true;
      ((android.support.constraint.solver.widgets.Guideline)widget).setOrientation(orientation);
    }
    if ((paramView instanceof ConstraintHelper))
    {
      localObject = (ConstraintHelper)paramView;
      ((ConstraintHelper)localObject).validateParams();
      getLayoutParamsisHelper = true;
      if (!mConstraintHelpers.contains(localObject)) {
        mConstraintHelpers.add(localObject);
      }
    }
    mChildrenByIds.put(paramView.getId(), paramView);
    mDirtyHierarchy = true;
  }
  
  public void onViewRemoved(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 14) {
      super.onViewRemoved(paramView);
    }
    mChildrenByIds.remove(paramView.getId());
    mLayoutWidget.remove(getViewWidget(paramView));
    mConstraintHelpers.remove(paramView);
    mDirtyHierarchy = true;
  }
  
  public void removeView(View paramView)
  {
    super.removeView(paramView);
    if (Build.VERSION.SDK_INT < 14) {
      onViewRemoved(paramView);
    }
  }
  
  public void requestLayout()
  {
    super.requestLayout();
    mDirtyHierarchy = true;
  }
  
  public void setConstraintSet(ConstraintSet paramConstraintSet)
  {
    mConstraintSet = paramConstraintSet;
  }
  
  public void setDesignInformation(int paramInt, Object paramObject1, Object paramObject2)
  {
    if ((paramInt == 0) && ((paramObject1 instanceof String)) && ((paramObject2 instanceof Integer)))
    {
      if (mDesignIds == null) {
        mDesignIds = new HashMap();
      }
      String str = (String)paramObject1;
      paramInt = str.indexOf("/");
      paramObject1 = str;
      if (paramInt != -1) {
        paramObject1 = str.substring(paramInt + 1);
      }
      paramInt = ((Integer)paramObject2).intValue();
      mDesignIds.put(paramObject1, Integer.valueOf(paramInt));
    }
  }
  
  public void setId(int paramInt)
  {
    mChildrenByIds.remove(getId());
    super.setId(paramInt);
    mChildrenByIds.put(getId(), this);
  }
  
  public void setMaxHeight(int paramInt)
  {
    if (paramInt == mMaxHeight) {
      return;
    }
    mMaxHeight = paramInt;
    requestLayout();
  }
  
  public void setMaxWidth(int paramInt)
  {
    if (paramInt == mMaxWidth) {
      return;
    }
    mMaxWidth = paramInt;
    requestLayout();
  }
  
  public void setMinHeight(int paramInt)
  {
    if (paramInt == mMinHeight) {
      return;
    }
    mMinHeight = paramInt;
    requestLayout();
  }
  
  public void setMinWidth(int paramInt)
  {
    if (paramInt == mMinWidth) {
      return;
    }
    mMinWidth = paramInt;
    requestLayout();
  }
  
  public void setOptimizationLevel(int paramInt)
  {
    mLayoutWidget.setOptimizationLevel(paramInt);
  }
  
  public void setTitle(String paramString)
  {
    mTitle = paramString;
  }
  
  protected void solveLinearSystem()
  {
    mLayoutWidget.layout();
  }
  
  public static class LayoutParams
    extends ViewGroup.MarginLayoutParams
  {
    public static final int BASELINE = 5;
    public static final int BOTTOM = 4;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static final int END = 7;
    public static final int HORIZONTAL = 0;
    public static final int LEFT = 1;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int PARENT_ID = 0;
    public static final int RIGHT = 2;
    public static final int START = 6;
    public static final int TOP = 3;
    public static final int UNSET = -1;
    public static final int VERTICAL = 1;
    public int baselineToBaseline = -1;
    public int bottomToBottom = -1;
    public int bottomToTop = -1;
    public String dimensionRatio = null;
    int dimensionRatioSide = 1;
    float dimensionRatioValue = 0.0F;
    public int editorAbsoluteX = -1;
    public int editorAbsoluteY = -1;
    public int endToEnd = -1;
    public int endToStart = -1;
    public int goneBottomMargin = -1;
    public int goneEndMargin = -1;
    public int goneLeftMargin = -1;
    public int goneRightMargin = -1;
    public int goneStartMargin = -1;
    public int goneTopMargin = -1;
    public int guideBegin = -1;
    public int guideEnd = -1;
    public float guidePercent = -1.0F;
    public boolean helped = false;
    public float horizontalBias = 0.5F;
    public int horizontalChainStyle = 0;
    boolean horizontalDimensionFixed = true;
    public float horizontalWeight = 0.0F;
    boolean isGuideline = false;
    boolean isHelper = false;
    boolean isInPlaceholder = false;
    public int leftToLeft = -1;
    public int leftToRight = -1;
    public int matchConstraintDefaultHeight = 0;
    public int matchConstraintDefaultWidth = 0;
    public int matchConstraintMaxHeight = 0;
    public int matchConstraintMaxWidth = 0;
    public int matchConstraintMinHeight = 0;
    public int matchConstraintMinWidth = 0;
    public float matchConstraintPercentHeight = 1.0F;
    public float matchConstraintPercentWidth = 1.0F;
    boolean needsBaseline = false;
    public int orientation = -1;
    int resolveGoneLeftMargin = -1;
    int resolveGoneRightMargin = -1;
    float resolvedHorizontalBias = 0.5F;
    int resolvedLeftToLeft = -1;
    int resolvedLeftToRight = -1;
    int resolvedRightToLeft = -1;
    int resolvedRightToRight = -1;
    public int rightToLeft = -1;
    public int rightToRight = -1;
    public int startToEnd = -1;
    public int startToStart = -1;
    public int topToBottom = -1;
    public int topToTop = -1;
    public float verticalBias = 0.5F;
    public int verticalChainStyle = 0;
    boolean verticalDimensionFixed = true;
    public float verticalWeight = 0.0F;
    ConstraintWidget widget = new ConstraintWidget();
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: aload_2
      //   3: invokespecial 229	android/view/ViewGroup$MarginLayoutParams:<init>	(Landroid/content/Context;Landroid/util/AttributeSet;)V
      //   6: aload_0
      //   7: iconst_m1
      //   8: putfield 102	android/support/constraint/ConstraintLayout$LayoutParams:guideBegin	I
      //   11: aload_0
      //   12: iconst_m1
      //   13: putfield 104	android/support/constraint/ConstraintLayout$LayoutParams:guideEnd	I
      //   16: aload_0
      //   17: ldc 105
      //   19: putfield 107	android/support/constraint/ConstraintLayout$LayoutParams:guidePercent	F
      //   22: aload_0
      //   23: iconst_m1
      //   24: putfield 109	android/support/constraint/ConstraintLayout$LayoutParams:leftToLeft	I
      //   27: aload_0
      //   28: iconst_m1
      //   29: putfield 111	android/support/constraint/ConstraintLayout$LayoutParams:leftToRight	I
      //   32: aload_0
      //   33: iconst_m1
      //   34: putfield 113	android/support/constraint/ConstraintLayout$LayoutParams:rightToLeft	I
      //   37: aload_0
      //   38: iconst_m1
      //   39: putfield 115	android/support/constraint/ConstraintLayout$LayoutParams:rightToRight	I
      //   42: aload_0
      //   43: iconst_m1
      //   44: putfield 117	android/support/constraint/ConstraintLayout$LayoutParams:topToTop	I
      //   47: aload_0
      //   48: iconst_m1
      //   49: putfield 119	android/support/constraint/ConstraintLayout$LayoutParams:topToBottom	I
      //   52: aload_0
      //   53: iconst_m1
      //   54: putfield 121	android/support/constraint/ConstraintLayout$LayoutParams:bottomToTop	I
      //   57: aload_0
      //   58: iconst_m1
      //   59: putfield 123	android/support/constraint/ConstraintLayout$LayoutParams:bottomToBottom	I
      //   62: aload_0
      //   63: iconst_m1
      //   64: putfield 125	android/support/constraint/ConstraintLayout$LayoutParams:baselineToBaseline	I
      //   67: aload_0
      //   68: iconst_m1
      //   69: putfield 127	android/support/constraint/ConstraintLayout$LayoutParams:startToEnd	I
      //   72: aload_0
      //   73: iconst_m1
      //   74: putfield 129	android/support/constraint/ConstraintLayout$LayoutParams:startToStart	I
      //   77: aload_0
      //   78: iconst_m1
      //   79: putfield 131	android/support/constraint/ConstraintLayout$LayoutParams:endToStart	I
      //   82: aload_0
      //   83: iconst_m1
      //   84: putfield 133	android/support/constraint/ConstraintLayout$LayoutParams:endToEnd	I
      //   87: aload_0
      //   88: iconst_m1
      //   89: putfield 135	android/support/constraint/ConstraintLayout$LayoutParams:goneLeftMargin	I
      //   92: aload_0
      //   93: iconst_m1
      //   94: putfield 137	android/support/constraint/ConstraintLayout$LayoutParams:goneTopMargin	I
      //   97: aload_0
      //   98: iconst_m1
      //   99: putfield 139	android/support/constraint/ConstraintLayout$LayoutParams:goneRightMargin	I
      //   102: aload_0
      //   103: iconst_m1
      //   104: putfield 141	android/support/constraint/ConstraintLayout$LayoutParams:goneBottomMargin	I
      //   107: aload_0
      //   108: iconst_m1
      //   109: putfield 143	android/support/constraint/ConstraintLayout$LayoutParams:goneStartMargin	I
      //   112: aload_0
      //   113: iconst_m1
      //   114: putfield 145	android/support/constraint/ConstraintLayout$LayoutParams:goneEndMargin	I
      //   117: aload_0
      //   118: ldc -110
      //   120: putfield 148	android/support/constraint/ConstraintLayout$LayoutParams:horizontalBias	F
      //   123: aload_0
      //   124: ldc -110
      //   126: putfield 150	android/support/constraint/ConstraintLayout$LayoutParams:verticalBias	F
      //   129: aload_0
      //   130: aconst_null
      //   131: putfield 152	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatio	Ljava/lang/String;
      //   134: aload_0
      //   135: fconst_0
      //   136: putfield 154	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatioValue	F
      //   139: aload_0
      //   140: iconst_1
      //   141: putfield 156	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatioSide	I
      //   144: aload_0
      //   145: fconst_0
      //   146: putfield 158	android/support/constraint/ConstraintLayout$LayoutParams:horizontalWeight	F
      //   149: aload_0
      //   150: fconst_0
      //   151: putfield 160	android/support/constraint/ConstraintLayout$LayoutParams:verticalWeight	F
      //   154: aload_0
      //   155: iconst_0
      //   156: putfield 162	android/support/constraint/ConstraintLayout$LayoutParams:horizontalChainStyle	I
      //   159: aload_0
      //   160: iconst_0
      //   161: putfield 164	android/support/constraint/ConstraintLayout$LayoutParams:verticalChainStyle	I
      //   164: aload_0
      //   165: iconst_0
      //   166: putfield 166	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintDefaultWidth	I
      //   169: aload_0
      //   170: iconst_0
      //   171: putfield 168	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintDefaultHeight	I
      //   174: aload_0
      //   175: iconst_0
      //   176: putfield 170	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMinWidth	I
      //   179: aload_0
      //   180: iconst_0
      //   181: putfield 172	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMinHeight	I
      //   184: aload_0
      //   185: iconst_0
      //   186: putfield 174	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMaxWidth	I
      //   189: aload_0
      //   190: iconst_0
      //   191: putfield 176	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMaxHeight	I
      //   194: aload_0
      //   195: fconst_1
      //   196: putfield 178	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintPercentWidth	F
      //   199: aload_0
      //   200: fconst_1
      //   201: putfield 180	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintPercentHeight	F
      //   204: aload_0
      //   205: iconst_m1
      //   206: putfield 182	android/support/constraint/ConstraintLayout$LayoutParams:editorAbsoluteX	I
      //   209: aload_0
      //   210: iconst_m1
      //   211: putfield 184	android/support/constraint/ConstraintLayout$LayoutParams:editorAbsoluteY	I
      //   214: aload_0
      //   215: iconst_m1
      //   216: putfield 186	android/support/constraint/ConstraintLayout$LayoutParams:orientation	I
      //   219: aload_0
      //   220: iconst_1
      //   221: putfield 188	android/support/constraint/ConstraintLayout$LayoutParams:horizontalDimensionFixed	Z
      //   224: aload_0
      //   225: iconst_1
      //   226: putfield 190	android/support/constraint/ConstraintLayout$LayoutParams:verticalDimensionFixed	Z
      //   229: aload_0
      //   230: iconst_0
      //   231: putfield 192	android/support/constraint/ConstraintLayout$LayoutParams:needsBaseline	Z
      //   234: aload_0
      //   235: iconst_0
      //   236: putfield 194	android/support/constraint/ConstraintLayout$LayoutParams:isGuideline	Z
      //   239: aload_0
      //   240: iconst_0
      //   241: putfield 196	android/support/constraint/ConstraintLayout$LayoutParams:isHelper	Z
      //   244: aload_0
      //   245: iconst_0
      //   246: putfield 198	android/support/constraint/ConstraintLayout$LayoutParams:isInPlaceholder	Z
      //   249: aload_0
      //   250: iconst_m1
      //   251: putfield 200	android/support/constraint/ConstraintLayout$LayoutParams:resolvedLeftToLeft	I
      //   254: aload_0
      //   255: iconst_m1
      //   256: putfield 202	android/support/constraint/ConstraintLayout$LayoutParams:resolvedLeftToRight	I
      //   259: aload_0
      //   260: iconst_m1
      //   261: putfield 204	android/support/constraint/ConstraintLayout$LayoutParams:resolvedRightToLeft	I
      //   264: aload_0
      //   265: iconst_m1
      //   266: putfield 206	android/support/constraint/ConstraintLayout$LayoutParams:resolvedRightToRight	I
      //   269: aload_0
      //   270: iconst_m1
      //   271: putfield 208	android/support/constraint/ConstraintLayout$LayoutParams:resolveGoneLeftMargin	I
      //   274: aload_0
      //   275: iconst_m1
      //   276: putfield 210	android/support/constraint/ConstraintLayout$LayoutParams:resolveGoneRightMargin	I
      //   279: aload_0
      //   280: ldc -110
      //   282: putfield 212	android/support/constraint/ConstraintLayout$LayoutParams:resolvedHorizontalBias	F
      //   285: aload_0
      //   286: new 214	android/support/constraint/solver/widgets/ConstraintWidget
      //   289: dup
      //   290: invokespecial 217	android/support/constraint/solver/widgets/ConstraintWidget:<init>	()V
      //   293: putfield 219	android/support/constraint/ConstraintLayout$LayoutParams:widget	Landroid/support/constraint/solver/widgets/ConstraintWidget;
      //   296: aload_0
      //   297: iconst_0
      //   298: putfield 221	android/support/constraint/ConstraintLayout$LayoutParams:helped	Z
      //   301: aload_1
      //   302: aload_2
      //   303: getstatic 235	android/support/constraint/R$styleable:ConstraintLayout_Layout	[I
      //   306: invokevirtual 241	android/content/Context:obtainStyledAttributes	(Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;
      //   309: astore_1
      //   310: aload_1
      //   311: invokevirtual 247	android/content/res/TypedArray:getIndexCount	()I
      //   314: istore 7
      //   316: iconst_0
      //   317: istore 5
      //   319: iload 5
      //   321: iload 7
      //   323: if_icmpge +1650 -> 1973
      //   326: aload_1
      //   327: iload 5
      //   329: invokevirtual 251	android/content/res/TypedArray:getIndex	(I)I
      //   332: istore 6
      //   334: iload 6
      //   336: getstatic 254	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintLeft_toLeftOf	I
      //   339: if_icmpne +39 -> 378
      //   342: aload_0
      //   343: aload_1
      //   344: iload 6
      //   346: aload_0
      //   347: getfield 109	android/support/constraint/ConstraintLayout$LayoutParams:leftToLeft	I
      //   350: invokevirtual 258	android/content/res/TypedArray:getResourceId	(II)I
      //   353: putfield 109	android/support/constraint/ConstraintLayout$LayoutParams:leftToLeft	I
      //   356: aload_0
      //   357: getfield 109	android/support/constraint/ConstraintLayout$LayoutParams:leftToLeft	I
      //   360: iconst_m1
      //   361: if_icmpne +1603 -> 1964
      //   364: aload_0
      //   365: aload_1
      //   366: iload 6
      //   368: iconst_m1
      //   369: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   372: putfield 109	android/support/constraint/ConstraintLayout$LayoutParams:leftToLeft	I
      //   375: goto +1589 -> 1964
      //   378: iload 6
      //   380: getstatic 264	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintLeft_toRightOf	I
      //   383: if_icmpne +39 -> 422
      //   386: aload_0
      //   387: aload_1
      //   388: iload 6
      //   390: aload_0
      //   391: getfield 111	android/support/constraint/ConstraintLayout$LayoutParams:leftToRight	I
      //   394: invokevirtual 258	android/content/res/TypedArray:getResourceId	(II)I
      //   397: putfield 111	android/support/constraint/ConstraintLayout$LayoutParams:leftToRight	I
      //   400: aload_0
      //   401: getfield 111	android/support/constraint/ConstraintLayout$LayoutParams:leftToRight	I
      //   404: iconst_m1
      //   405: if_icmpne +1559 -> 1964
      //   408: aload_0
      //   409: aload_1
      //   410: iload 6
      //   412: iconst_m1
      //   413: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   416: putfield 111	android/support/constraint/ConstraintLayout$LayoutParams:leftToRight	I
      //   419: goto +1545 -> 1964
      //   422: iload 6
      //   424: getstatic 267	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintRight_toLeftOf	I
      //   427: if_icmpne +39 -> 466
      //   430: aload_0
      //   431: aload_1
      //   432: iload 6
      //   434: aload_0
      //   435: getfield 113	android/support/constraint/ConstraintLayout$LayoutParams:rightToLeft	I
      //   438: invokevirtual 258	android/content/res/TypedArray:getResourceId	(II)I
      //   441: putfield 113	android/support/constraint/ConstraintLayout$LayoutParams:rightToLeft	I
      //   444: aload_0
      //   445: getfield 113	android/support/constraint/ConstraintLayout$LayoutParams:rightToLeft	I
      //   448: iconst_m1
      //   449: if_icmpne +1515 -> 1964
      //   452: aload_0
      //   453: aload_1
      //   454: iload 6
      //   456: iconst_m1
      //   457: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   460: putfield 113	android/support/constraint/ConstraintLayout$LayoutParams:rightToLeft	I
      //   463: goto +1501 -> 1964
      //   466: iload 6
      //   468: getstatic 270	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintRight_toRightOf	I
      //   471: if_icmpne +39 -> 510
      //   474: aload_0
      //   475: aload_1
      //   476: iload 6
      //   478: aload_0
      //   479: getfield 115	android/support/constraint/ConstraintLayout$LayoutParams:rightToRight	I
      //   482: invokevirtual 258	android/content/res/TypedArray:getResourceId	(II)I
      //   485: putfield 115	android/support/constraint/ConstraintLayout$LayoutParams:rightToRight	I
      //   488: aload_0
      //   489: getfield 115	android/support/constraint/ConstraintLayout$LayoutParams:rightToRight	I
      //   492: iconst_m1
      //   493: if_icmpne +1471 -> 1964
      //   496: aload_0
      //   497: aload_1
      //   498: iload 6
      //   500: iconst_m1
      //   501: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   504: putfield 115	android/support/constraint/ConstraintLayout$LayoutParams:rightToRight	I
      //   507: goto +1457 -> 1964
      //   510: iload 6
      //   512: getstatic 273	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintTop_toTopOf	I
      //   515: if_icmpne +39 -> 554
      //   518: aload_0
      //   519: aload_1
      //   520: iload 6
      //   522: aload_0
      //   523: getfield 117	android/support/constraint/ConstraintLayout$LayoutParams:topToTop	I
      //   526: invokevirtual 258	android/content/res/TypedArray:getResourceId	(II)I
      //   529: putfield 117	android/support/constraint/ConstraintLayout$LayoutParams:topToTop	I
      //   532: aload_0
      //   533: getfield 117	android/support/constraint/ConstraintLayout$LayoutParams:topToTop	I
      //   536: iconst_m1
      //   537: if_icmpne +1427 -> 1964
      //   540: aload_0
      //   541: aload_1
      //   542: iload 6
      //   544: iconst_m1
      //   545: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   548: putfield 117	android/support/constraint/ConstraintLayout$LayoutParams:topToTop	I
      //   551: goto +1413 -> 1964
      //   554: iload 6
      //   556: getstatic 276	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintTop_toBottomOf	I
      //   559: if_icmpne +39 -> 598
      //   562: aload_0
      //   563: aload_1
      //   564: iload 6
      //   566: aload_0
      //   567: getfield 119	android/support/constraint/ConstraintLayout$LayoutParams:topToBottom	I
      //   570: invokevirtual 258	android/content/res/TypedArray:getResourceId	(II)I
      //   573: putfield 119	android/support/constraint/ConstraintLayout$LayoutParams:topToBottom	I
      //   576: aload_0
      //   577: getfield 119	android/support/constraint/ConstraintLayout$LayoutParams:topToBottom	I
      //   580: iconst_m1
      //   581: if_icmpne +1383 -> 1964
      //   584: aload_0
      //   585: aload_1
      //   586: iload 6
      //   588: iconst_m1
      //   589: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   592: putfield 119	android/support/constraint/ConstraintLayout$LayoutParams:topToBottom	I
      //   595: goto +1369 -> 1964
      //   598: iload 6
      //   600: getstatic 279	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintBottom_toTopOf	I
      //   603: if_icmpne +39 -> 642
      //   606: aload_0
      //   607: aload_1
      //   608: iload 6
      //   610: aload_0
      //   611: getfield 121	android/support/constraint/ConstraintLayout$LayoutParams:bottomToTop	I
      //   614: invokevirtual 258	android/content/res/TypedArray:getResourceId	(II)I
      //   617: putfield 121	android/support/constraint/ConstraintLayout$LayoutParams:bottomToTop	I
      //   620: aload_0
      //   621: getfield 121	android/support/constraint/ConstraintLayout$LayoutParams:bottomToTop	I
      //   624: iconst_m1
      //   625: if_icmpne +1339 -> 1964
      //   628: aload_0
      //   629: aload_1
      //   630: iload 6
      //   632: iconst_m1
      //   633: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   636: putfield 121	android/support/constraint/ConstraintLayout$LayoutParams:bottomToTop	I
      //   639: goto +1325 -> 1964
      //   642: iload 6
      //   644: getstatic 282	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintBottom_toBottomOf	I
      //   647: if_icmpne +39 -> 686
      //   650: aload_0
      //   651: aload_1
      //   652: iload 6
      //   654: aload_0
      //   655: getfield 123	android/support/constraint/ConstraintLayout$LayoutParams:bottomToBottom	I
      //   658: invokevirtual 258	android/content/res/TypedArray:getResourceId	(II)I
      //   661: putfield 123	android/support/constraint/ConstraintLayout$LayoutParams:bottomToBottom	I
      //   664: aload_0
      //   665: getfield 123	android/support/constraint/ConstraintLayout$LayoutParams:bottomToBottom	I
      //   668: iconst_m1
      //   669: if_icmpne +1295 -> 1964
      //   672: aload_0
      //   673: aload_1
      //   674: iload 6
      //   676: iconst_m1
      //   677: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   680: putfield 123	android/support/constraint/ConstraintLayout$LayoutParams:bottomToBottom	I
      //   683: goto +1281 -> 1964
      //   686: iload 6
      //   688: getstatic 285	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf	I
      //   691: if_icmpne +39 -> 730
      //   694: aload_0
      //   695: aload_1
      //   696: iload 6
      //   698: aload_0
      //   699: getfield 125	android/support/constraint/ConstraintLayout$LayoutParams:baselineToBaseline	I
      //   702: invokevirtual 258	android/content/res/TypedArray:getResourceId	(II)I
      //   705: putfield 125	android/support/constraint/ConstraintLayout$LayoutParams:baselineToBaseline	I
      //   708: aload_0
      //   709: getfield 125	android/support/constraint/ConstraintLayout$LayoutParams:baselineToBaseline	I
      //   712: iconst_m1
      //   713: if_icmpne +1251 -> 1964
      //   716: aload_0
      //   717: aload_1
      //   718: iload 6
      //   720: iconst_m1
      //   721: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   724: putfield 125	android/support/constraint/ConstraintLayout$LayoutParams:baselineToBaseline	I
      //   727: goto +1237 -> 1964
      //   730: iload 6
      //   732: getstatic 288	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_editor_absoluteX	I
      //   735: if_icmpne +20 -> 755
      //   738: aload_0
      //   739: aload_1
      //   740: iload 6
      //   742: aload_0
      //   743: getfield 182	android/support/constraint/ConstraintLayout$LayoutParams:editorAbsoluteX	I
      //   746: invokevirtual 291	android/content/res/TypedArray:getDimensionPixelOffset	(II)I
      //   749: putfield 182	android/support/constraint/ConstraintLayout$LayoutParams:editorAbsoluteX	I
      //   752: goto +1212 -> 1964
      //   755: iload 6
      //   757: getstatic 294	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_editor_absoluteY	I
      //   760: if_icmpne +20 -> 780
      //   763: aload_0
      //   764: aload_1
      //   765: iload 6
      //   767: aload_0
      //   768: getfield 184	android/support/constraint/ConstraintLayout$LayoutParams:editorAbsoluteY	I
      //   771: invokevirtual 291	android/content/res/TypedArray:getDimensionPixelOffset	(II)I
      //   774: putfield 184	android/support/constraint/ConstraintLayout$LayoutParams:editorAbsoluteY	I
      //   777: goto +1187 -> 1964
      //   780: iload 6
      //   782: getstatic 297	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintGuide_begin	I
      //   785: if_icmpne +20 -> 805
      //   788: aload_0
      //   789: aload_1
      //   790: iload 6
      //   792: aload_0
      //   793: getfield 102	android/support/constraint/ConstraintLayout$LayoutParams:guideBegin	I
      //   796: invokevirtual 291	android/content/res/TypedArray:getDimensionPixelOffset	(II)I
      //   799: putfield 102	android/support/constraint/ConstraintLayout$LayoutParams:guideBegin	I
      //   802: goto +1162 -> 1964
      //   805: iload 6
      //   807: getstatic 300	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintGuide_end	I
      //   810: if_icmpne +20 -> 830
      //   813: aload_0
      //   814: aload_1
      //   815: iload 6
      //   817: aload_0
      //   818: getfield 104	android/support/constraint/ConstraintLayout$LayoutParams:guideEnd	I
      //   821: invokevirtual 291	android/content/res/TypedArray:getDimensionPixelOffset	(II)I
      //   824: putfield 104	android/support/constraint/ConstraintLayout$LayoutParams:guideEnd	I
      //   827: goto +1137 -> 1964
      //   830: iload 6
      //   832: getstatic 303	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintGuide_percent	I
      //   835: if_icmpne +20 -> 855
      //   838: aload_0
      //   839: aload_1
      //   840: iload 6
      //   842: aload_0
      //   843: getfield 107	android/support/constraint/ConstraintLayout$LayoutParams:guidePercent	F
      //   846: invokevirtual 307	android/content/res/TypedArray:getFloat	(IF)F
      //   849: putfield 107	android/support/constraint/ConstraintLayout$LayoutParams:guidePercent	F
      //   852: goto +1112 -> 1964
      //   855: iload 6
      //   857: getstatic 310	android/support/constraint/R$styleable:ConstraintLayout_Layout_android_orientation	I
      //   860: if_icmpne +20 -> 880
      //   863: aload_0
      //   864: aload_1
      //   865: iload 6
      //   867: aload_0
      //   868: getfield 186	android/support/constraint/ConstraintLayout$LayoutParams:orientation	I
      //   871: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   874: putfield 186	android/support/constraint/ConstraintLayout$LayoutParams:orientation	I
      //   877: goto +1087 -> 1964
      //   880: iload 6
      //   882: getstatic 313	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintStart_toEndOf	I
      //   885: if_icmpne +39 -> 924
      //   888: aload_0
      //   889: aload_1
      //   890: iload 6
      //   892: aload_0
      //   893: getfield 127	android/support/constraint/ConstraintLayout$LayoutParams:startToEnd	I
      //   896: invokevirtual 258	android/content/res/TypedArray:getResourceId	(II)I
      //   899: putfield 127	android/support/constraint/ConstraintLayout$LayoutParams:startToEnd	I
      //   902: aload_0
      //   903: getfield 127	android/support/constraint/ConstraintLayout$LayoutParams:startToEnd	I
      //   906: iconst_m1
      //   907: if_icmpne +1057 -> 1964
      //   910: aload_0
      //   911: aload_1
      //   912: iload 6
      //   914: iconst_m1
      //   915: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   918: putfield 127	android/support/constraint/ConstraintLayout$LayoutParams:startToEnd	I
      //   921: goto +1043 -> 1964
      //   924: iload 6
      //   926: getstatic 316	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintStart_toStartOf	I
      //   929: if_icmpne +39 -> 968
      //   932: aload_0
      //   933: aload_1
      //   934: iload 6
      //   936: aload_0
      //   937: getfield 129	android/support/constraint/ConstraintLayout$LayoutParams:startToStart	I
      //   940: invokevirtual 258	android/content/res/TypedArray:getResourceId	(II)I
      //   943: putfield 129	android/support/constraint/ConstraintLayout$LayoutParams:startToStart	I
      //   946: aload_0
      //   947: getfield 129	android/support/constraint/ConstraintLayout$LayoutParams:startToStart	I
      //   950: iconst_m1
      //   951: if_icmpne +1013 -> 1964
      //   954: aload_0
      //   955: aload_1
      //   956: iload 6
      //   958: iconst_m1
      //   959: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   962: putfield 129	android/support/constraint/ConstraintLayout$LayoutParams:startToStart	I
      //   965: goto +999 -> 1964
      //   968: iload 6
      //   970: getstatic 319	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintEnd_toStartOf	I
      //   973: if_icmpne +39 -> 1012
      //   976: aload_0
      //   977: aload_1
      //   978: iload 6
      //   980: aload_0
      //   981: getfield 131	android/support/constraint/ConstraintLayout$LayoutParams:endToStart	I
      //   984: invokevirtual 258	android/content/res/TypedArray:getResourceId	(II)I
      //   987: putfield 131	android/support/constraint/ConstraintLayout$LayoutParams:endToStart	I
      //   990: aload_0
      //   991: getfield 131	android/support/constraint/ConstraintLayout$LayoutParams:endToStart	I
      //   994: iconst_m1
      //   995: if_icmpne +969 -> 1964
      //   998: aload_0
      //   999: aload_1
      //   1000: iload 6
      //   1002: iconst_m1
      //   1003: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   1006: putfield 131	android/support/constraint/ConstraintLayout$LayoutParams:endToStart	I
      //   1009: goto +955 -> 1964
      //   1012: iload 6
      //   1014: getstatic 322	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintEnd_toEndOf	I
      //   1017: if_icmpne +39 -> 1056
      //   1020: aload_0
      //   1021: aload_1
      //   1022: iload 6
      //   1024: aload_0
      //   1025: getfield 133	android/support/constraint/ConstraintLayout$LayoutParams:endToEnd	I
      //   1028: invokevirtual 258	android/content/res/TypedArray:getResourceId	(II)I
      //   1031: putfield 133	android/support/constraint/ConstraintLayout$LayoutParams:endToEnd	I
      //   1034: aload_0
      //   1035: getfield 133	android/support/constraint/ConstraintLayout$LayoutParams:endToEnd	I
      //   1038: iconst_m1
      //   1039: if_icmpne +925 -> 1964
      //   1042: aload_0
      //   1043: aload_1
      //   1044: iload 6
      //   1046: iconst_m1
      //   1047: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   1050: putfield 133	android/support/constraint/ConstraintLayout$LayoutParams:endToEnd	I
      //   1053: goto +911 -> 1964
      //   1056: iload 6
      //   1058: getstatic 325	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_goneMarginLeft	I
      //   1061: if_icmpne +20 -> 1081
      //   1064: aload_0
      //   1065: aload_1
      //   1066: iload 6
      //   1068: aload_0
      //   1069: getfield 135	android/support/constraint/ConstraintLayout$LayoutParams:goneLeftMargin	I
      //   1072: invokevirtual 328	android/content/res/TypedArray:getDimensionPixelSize	(II)I
      //   1075: putfield 135	android/support/constraint/ConstraintLayout$LayoutParams:goneLeftMargin	I
      //   1078: goto +886 -> 1964
      //   1081: iload 6
      //   1083: getstatic 331	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_goneMarginTop	I
      //   1086: if_icmpne +20 -> 1106
      //   1089: aload_0
      //   1090: aload_1
      //   1091: iload 6
      //   1093: aload_0
      //   1094: getfield 137	android/support/constraint/ConstraintLayout$LayoutParams:goneTopMargin	I
      //   1097: invokevirtual 328	android/content/res/TypedArray:getDimensionPixelSize	(II)I
      //   1100: putfield 137	android/support/constraint/ConstraintLayout$LayoutParams:goneTopMargin	I
      //   1103: goto +861 -> 1964
      //   1106: iload 6
      //   1108: getstatic 334	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_goneMarginRight	I
      //   1111: if_icmpne +20 -> 1131
      //   1114: aload_0
      //   1115: aload_1
      //   1116: iload 6
      //   1118: aload_0
      //   1119: getfield 139	android/support/constraint/ConstraintLayout$LayoutParams:goneRightMargin	I
      //   1122: invokevirtual 328	android/content/res/TypedArray:getDimensionPixelSize	(II)I
      //   1125: putfield 139	android/support/constraint/ConstraintLayout$LayoutParams:goneRightMargin	I
      //   1128: goto +836 -> 1964
      //   1131: iload 6
      //   1133: getstatic 337	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_goneMarginBottom	I
      //   1136: if_icmpne +20 -> 1156
      //   1139: aload_0
      //   1140: aload_1
      //   1141: iload 6
      //   1143: aload_0
      //   1144: getfield 141	android/support/constraint/ConstraintLayout$LayoutParams:goneBottomMargin	I
      //   1147: invokevirtual 328	android/content/res/TypedArray:getDimensionPixelSize	(II)I
      //   1150: putfield 141	android/support/constraint/ConstraintLayout$LayoutParams:goneBottomMargin	I
      //   1153: goto +811 -> 1964
      //   1156: iload 6
      //   1158: getstatic 340	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_goneMarginStart	I
      //   1161: if_icmpne +20 -> 1181
      //   1164: aload_0
      //   1165: aload_1
      //   1166: iload 6
      //   1168: aload_0
      //   1169: getfield 143	android/support/constraint/ConstraintLayout$LayoutParams:goneStartMargin	I
      //   1172: invokevirtual 328	android/content/res/TypedArray:getDimensionPixelSize	(II)I
      //   1175: putfield 143	android/support/constraint/ConstraintLayout$LayoutParams:goneStartMargin	I
      //   1178: goto +786 -> 1964
      //   1181: iload 6
      //   1183: getstatic 343	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_goneMarginEnd	I
      //   1186: if_icmpne +20 -> 1206
      //   1189: aload_0
      //   1190: aload_1
      //   1191: iload 6
      //   1193: aload_0
      //   1194: getfield 145	android/support/constraint/ConstraintLayout$LayoutParams:goneEndMargin	I
      //   1197: invokevirtual 328	android/content/res/TypedArray:getDimensionPixelSize	(II)I
      //   1200: putfield 145	android/support/constraint/ConstraintLayout$LayoutParams:goneEndMargin	I
      //   1203: goto +761 -> 1964
      //   1206: iload 6
      //   1208: getstatic 346	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintHorizontal_bias	I
      //   1211: if_icmpne +20 -> 1231
      //   1214: aload_0
      //   1215: aload_1
      //   1216: iload 6
      //   1218: aload_0
      //   1219: getfield 148	android/support/constraint/ConstraintLayout$LayoutParams:horizontalBias	F
      //   1222: invokevirtual 307	android/content/res/TypedArray:getFloat	(IF)F
      //   1225: putfield 148	android/support/constraint/ConstraintLayout$LayoutParams:horizontalBias	F
      //   1228: goto +736 -> 1964
      //   1231: iload 6
      //   1233: getstatic 349	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintVertical_bias	I
      //   1236: if_icmpne +20 -> 1256
      //   1239: aload_0
      //   1240: aload_1
      //   1241: iload 6
      //   1243: aload_0
      //   1244: getfield 150	android/support/constraint/ConstraintLayout$LayoutParams:verticalBias	F
      //   1247: invokevirtual 307	android/content/res/TypedArray:getFloat	(IF)F
      //   1250: putfield 150	android/support/constraint/ConstraintLayout$LayoutParams:verticalBias	F
      //   1253: goto +711 -> 1964
      //   1256: iload 6
      //   1258: getstatic 352	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintDimensionRatio	I
      //   1261: if_icmpne +276 -> 1537
      //   1264: aload_0
      //   1265: aload_1
      //   1266: iload 6
      //   1268: invokevirtual 356	android/content/res/TypedArray:getString	(I)Ljava/lang/String;
      //   1271: putfield 152	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatio	Ljava/lang/String;
      //   1274: aload_0
      //   1275: ldc_w 357
      //   1278: putfield 154	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatioValue	F
      //   1281: aload_0
      //   1282: iconst_m1
      //   1283: putfield 156	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatioSide	I
      //   1286: aload_0
      //   1287: getfield 152	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatio	Ljava/lang/String;
      //   1290: ifnull +674 -> 1964
      //   1293: aload_0
      //   1294: getfield 152	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatio	Ljava/lang/String;
      //   1297: invokevirtual 362	java/lang/String:length	()I
      //   1300: istore 8
      //   1302: aload_0
      //   1303: getfield 152	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatio	Ljava/lang/String;
      //   1306: bipush 44
      //   1308: invokevirtual 365	java/lang/String:indexOf	(I)I
      //   1311: istore 6
      //   1313: iload 6
      //   1315: ifle +65 -> 1380
      //   1318: iload 6
      //   1320: iload 8
      //   1322: iconst_1
      //   1323: isub
      //   1324: if_icmpge +56 -> 1380
      //   1327: aload_0
      //   1328: getfield 152	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatio	Ljava/lang/String;
      //   1331: iconst_0
      //   1332: iload 6
      //   1334: invokevirtual 369	java/lang/String:substring	(II)Ljava/lang/String;
      //   1337: astore_2
      //   1338: aload_2
      //   1339: ldc_w 371
      //   1342: invokevirtual 375	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1345: ifeq +11 -> 1356
      //   1348: aload_0
      //   1349: iconst_0
      //   1350: putfield 156	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatioSide	I
      //   1353: goto +18 -> 1371
      //   1356: aload_2
      //   1357: ldc_w 377
      //   1360: invokevirtual 375	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1363: ifeq +8 -> 1371
      //   1366: aload_0
      //   1367: iconst_1
      //   1368: putfield 156	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatioSide	I
      //   1371: iload 6
      //   1373: iconst_1
      //   1374: iadd
      //   1375: istore 6
      //   1377: goto +6 -> 1383
      //   1380: iconst_0
      //   1381: istore 6
      //   1383: aload_0
      //   1384: getfield 152	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatio	Ljava/lang/String;
      //   1387: bipush 58
      //   1389: invokevirtual 365	java/lang/String:indexOf	(I)I
      //   1392: istore 9
      //   1394: iload 9
      //   1396: iflt +113 -> 1509
      //   1399: iload 9
      //   1401: iload 8
      //   1403: iconst_1
      //   1404: isub
      //   1405: if_icmpge +104 -> 1509
      //   1408: aload_0
      //   1409: getfield 152	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatio	Ljava/lang/String;
      //   1412: iload 6
      //   1414: iload 9
      //   1416: invokevirtual 369	java/lang/String:substring	(II)Ljava/lang/String;
      //   1419: astore_2
      //   1420: aload_0
      //   1421: getfield 152	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatio	Ljava/lang/String;
      //   1424: iload 9
      //   1426: iconst_1
      //   1427: iadd
      //   1428: invokevirtual 379	java/lang/String:substring	(I)Ljava/lang/String;
      //   1431: astore 10
      //   1433: aload_2
      //   1434: invokevirtual 362	java/lang/String:length	()I
      //   1437: ifle +527 -> 1964
      //   1440: aload 10
      //   1442: invokevirtual 362	java/lang/String:length	()I
      //   1445: ifle +519 -> 1964
      //   1448: aload_2
      //   1449: invokestatic 385	java/lang/Float:parseFloat	(Ljava/lang/String;)F
      //   1452: fstore_3
      //   1453: aload 10
      //   1455: invokestatic 385	java/lang/Float:parseFloat	(Ljava/lang/String;)F
      //   1458: fstore 4
      //   1460: fload_3
      //   1461: fconst_0
      //   1462: fcmpl
      //   1463: ifle +501 -> 1964
      //   1466: fload 4
      //   1468: fconst_0
      //   1469: fcmpl
      //   1470: ifle +494 -> 1964
      //   1473: aload_0
      //   1474: getfield 156	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatioSide	I
      //   1477: iconst_1
      //   1478: if_icmpne +17 -> 1495
      //   1481: aload_0
      //   1482: fload 4
      //   1484: fload_3
      //   1485: fdiv
      //   1486: invokestatic 391	java/lang/Math:abs	(F)F
      //   1489: putfield 154	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatioValue	F
      //   1492: goto +472 -> 1964
      //   1495: aload_0
      //   1496: fload_3
      //   1497: fload 4
      //   1499: fdiv
      //   1500: invokestatic 391	java/lang/Math:abs	(F)F
      //   1503: putfield 154	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatioValue	F
      //   1506: goto +458 -> 1964
      //   1509: aload_0
      //   1510: getfield 152	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatio	Ljava/lang/String;
      //   1513: iload 6
      //   1515: invokevirtual 379	java/lang/String:substring	(I)Ljava/lang/String;
      //   1518: astore_2
      //   1519: aload_2
      //   1520: invokevirtual 362	java/lang/String:length	()I
      //   1523: ifle +441 -> 1964
      //   1526: aload_0
      //   1527: aload_2
      //   1528: invokestatic 385	java/lang/Float:parseFloat	(Ljava/lang/String;)F
      //   1531: putfield 154	android/support/constraint/ConstraintLayout$LayoutParams:dimensionRatioValue	F
      //   1534: goto +430 -> 1964
      //   1537: iload 6
      //   1539: getstatic 394	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintHorizontal_weight	I
      //   1542: if_icmpne +17 -> 1559
      //   1545: aload_0
      //   1546: aload_1
      //   1547: iload 6
      //   1549: fconst_0
      //   1550: invokevirtual 307	android/content/res/TypedArray:getFloat	(IF)F
      //   1553: putfield 158	android/support/constraint/ConstraintLayout$LayoutParams:horizontalWeight	F
      //   1556: goto +408 -> 1964
      //   1559: iload 6
      //   1561: getstatic 397	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintVertical_weight	I
      //   1564: if_icmpne +17 -> 1581
      //   1567: aload_0
      //   1568: aload_1
      //   1569: iload 6
      //   1571: fconst_0
      //   1572: invokevirtual 307	android/content/res/TypedArray:getFloat	(IF)F
      //   1575: putfield 160	android/support/constraint/ConstraintLayout$LayoutParams:verticalWeight	F
      //   1578: goto +386 -> 1964
      //   1581: iload 6
      //   1583: getstatic 400	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle	I
      //   1586: if_icmpne +17 -> 1603
      //   1589: aload_0
      //   1590: aload_1
      //   1591: iload 6
      //   1593: iconst_0
      //   1594: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   1597: putfield 162	android/support/constraint/ConstraintLayout$LayoutParams:horizontalChainStyle	I
      //   1600: goto +364 -> 1964
      //   1603: iload 6
      //   1605: getstatic 403	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintVertical_chainStyle	I
      //   1608: if_icmpne +17 -> 1625
      //   1611: aload_0
      //   1612: aload_1
      //   1613: iload 6
      //   1615: iconst_0
      //   1616: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   1619: putfield 164	android/support/constraint/ConstraintLayout$LayoutParams:verticalChainStyle	I
      //   1622: goto +342 -> 1964
      //   1625: iload 6
      //   1627: getstatic 406	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintWidth_default	I
      //   1630: if_icmpne +17 -> 1647
      //   1633: aload_0
      //   1634: aload_1
      //   1635: iload 6
      //   1637: iconst_0
      //   1638: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   1641: putfield 166	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintDefaultWidth	I
      //   1644: goto +320 -> 1964
      //   1647: iload 6
      //   1649: getstatic 409	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintHeight_default	I
      //   1652: if_icmpne +17 -> 1669
      //   1655: aload_0
      //   1656: aload_1
      //   1657: iload 6
      //   1659: iconst_0
      //   1660: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   1663: putfield 168	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintDefaultHeight	I
      //   1666: goto +298 -> 1964
      //   1669: iload 6
      //   1671: getstatic 412	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintWidth_min	I
      //   1674: if_icmpne +44 -> 1718
      //   1677: aload_0
      //   1678: aload_1
      //   1679: iload 6
      //   1681: aload_0
      //   1682: getfield 170	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMinWidth	I
      //   1685: invokevirtual 328	android/content/res/TypedArray:getDimensionPixelSize	(II)I
      //   1688: putfield 170	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMinWidth	I
      //   1691: goto +273 -> 1964
      //   1694: aload_1
      //   1695: iload 6
      //   1697: aload_0
      //   1698: getfield 170	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMinWidth	I
      //   1701: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   1704: bipush -2
      //   1706: if_icmpne +258 -> 1964
      //   1709: aload_0
      //   1710: bipush -2
      //   1712: putfield 170	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMinWidth	I
      //   1715: goto +249 -> 1964
      //   1718: iload 6
      //   1720: getstatic 415	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintWidth_max	I
      //   1723: if_icmpne +44 -> 1767
      //   1726: aload_0
      //   1727: aload_1
      //   1728: iload 6
      //   1730: aload_0
      //   1731: getfield 174	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMaxWidth	I
      //   1734: invokevirtual 328	android/content/res/TypedArray:getDimensionPixelSize	(II)I
      //   1737: putfield 174	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMaxWidth	I
      //   1740: goto +224 -> 1964
      //   1743: aload_1
      //   1744: iload 6
      //   1746: aload_0
      //   1747: getfield 174	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMaxWidth	I
      //   1750: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   1753: bipush -2
      //   1755: if_icmpne +209 -> 1964
      //   1758: aload_0
      //   1759: bipush -2
      //   1761: putfield 174	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMaxWidth	I
      //   1764: goto +200 -> 1964
      //   1767: iload 6
      //   1769: getstatic 418	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintWidth_percent	I
      //   1772: if_icmpne +20 -> 1792
      //   1775: aload_0
      //   1776: aload_1
      //   1777: iload 6
      //   1779: aload_0
      //   1780: getfield 178	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintPercentWidth	F
      //   1783: invokevirtual 307	android/content/res/TypedArray:getFloat	(IF)F
      //   1786: putfield 178	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintPercentWidth	F
      //   1789: goto +175 -> 1964
      //   1792: iload 6
      //   1794: getstatic 421	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintHeight_min	I
      //   1797: if_icmpne +44 -> 1841
      //   1800: aload_0
      //   1801: aload_1
      //   1802: iload 6
      //   1804: aload_0
      //   1805: getfield 172	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMinHeight	I
      //   1808: invokevirtual 328	android/content/res/TypedArray:getDimensionPixelSize	(II)I
      //   1811: putfield 172	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMinHeight	I
      //   1814: goto +150 -> 1964
      //   1817: aload_1
      //   1818: iload 6
      //   1820: aload_0
      //   1821: getfield 172	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMinHeight	I
      //   1824: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   1827: bipush -2
      //   1829: if_icmpne +135 -> 1964
      //   1832: aload_0
      //   1833: bipush -2
      //   1835: putfield 172	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMinHeight	I
      //   1838: goto +126 -> 1964
      //   1841: iload 6
      //   1843: getstatic 424	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintHeight_max	I
      //   1846: if_icmpne +44 -> 1890
      //   1849: aload_0
      //   1850: aload_1
      //   1851: iload 6
      //   1853: aload_0
      //   1854: getfield 176	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMaxHeight	I
      //   1857: invokevirtual 328	android/content/res/TypedArray:getDimensionPixelSize	(II)I
      //   1860: putfield 176	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMaxHeight	I
      //   1863: goto +101 -> 1964
      //   1866: aload_1
      //   1867: iload 6
      //   1869: aload_0
      //   1870: getfield 176	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMaxHeight	I
      //   1873: invokevirtual 261	android/content/res/TypedArray:getInt	(II)I
      //   1876: bipush -2
      //   1878: if_icmpne +86 -> 1964
      //   1881: aload_0
      //   1882: bipush -2
      //   1884: putfield 176	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintMaxHeight	I
      //   1887: goto +77 -> 1964
      //   1890: iload 6
      //   1892: getstatic 427	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintHeight_percent	I
      //   1895: if_icmpne +20 -> 1915
      //   1898: aload_0
      //   1899: aload_1
      //   1900: iload 6
      //   1902: aload_0
      //   1903: getfield 180	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintPercentHeight	F
      //   1906: invokevirtual 307	android/content/res/TypedArray:getFloat	(IF)F
      //   1909: putfield 180	android/support/constraint/ConstraintLayout$LayoutParams:matchConstraintPercentHeight	F
      //   1912: goto +52 -> 1964
      //   1915: iload 6
      //   1917: getstatic 430	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintLeft_creator	I
      //   1920: if_icmpne +6 -> 1926
      //   1923: goto +41 -> 1964
      //   1926: iload 6
      //   1928: getstatic 433	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintTop_creator	I
      //   1931: if_icmpne +6 -> 1937
      //   1934: goto +30 -> 1964
      //   1937: iload 6
      //   1939: getstatic 436	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintRight_creator	I
      //   1942: if_icmpne +6 -> 1948
      //   1945: goto +19 -> 1964
      //   1948: iload 6
      //   1950: getstatic 439	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintBottom_creator	I
      //   1953: if_icmpne +6 -> 1959
      //   1956: goto +8 -> 1964
      //   1959: getstatic 442	android/support/constraint/R$styleable:ConstraintLayout_Layout_layout_constraintBaseline_creator	I
      //   1962: istore 6
      //   1964: iload 5
      //   1966: iconst_1
      //   1967: iadd
      //   1968: istore 5
      //   1970: goto -1651 -> 319
      //   1973: aload_1
      //   1974: invokevirtual 445	android/content/res/TypedArray:recycle	()V
      //   1977: aload_0
      //   1978: invokevirtual 448	android/support/constraint/ConstraintLayout$LayoutParams:validate	()V
      //   1981: return
      //   1982: astore_2
      //   1983: goto -19 -> 1964
      //   1986: astore_2
      //   1987: goto -293 -> 1694
      //   1990: astore_2
      //   1991: goto -248 -> 1743
      //   1994: astore_2
      //   1995: goto -178 -> 1817
      //   1998: astore_2
      //   1999: goto -133 -> 1866
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	2002	0	this	LayoutParams
      //   0	2002	1	paramContext	Context
      //   0	2002	2	paramAttributeSet	AttributeSet
      //   1452	45	3	f1	float
      //   1458	40	4	f2	float
      //   317	1652	5	i	int
      //   332	1631	6	j	int
      //   314	10	7	k	int
      //   1300	105	8	m	int
      //   1392	36	9	n	int
      //   1431	23	10	str	String
      // Exception table:
      //   from	to	target	type
      //   1448	1460	1982	java/lang/NumberFormatException
      //   1473	1492	1982	java/lang/NumberFormatException
      //   1495	1506	1982	java/lang/NumberFormatException
      //   1526	1534	1982	java/lang/NumberFormatException
      //   1677	1691	1986	android/view/InflateException
      //   1726	1740	1990	android/view/InflateException
      //   1800	1814	1994	android/view/InflateException
      //   1849	1863	1998	android/view/InflateException
    }
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
      guideBegin = guideBegin;
      guideEnd = guideEnd;
      guidePercent = guidePercent;
      leftToLeft = leftToLeft;
      leftToRight = leftToRight;
      rightToLeft = rightToLeft;
      rightToRight = rightToRight;
      topToTop = topToTop;
      topToBottom = topToBottom;
      bottomToTop = bottomToTop;
      bottomToBottom = bottomToBottom;
      baselineToBaseline = baselineToBaseline;
      startToEnd = startToEnd;
      startToStart = startToStart;
      endToStart = endToStart;
      endToEnd = endToEnd;
      goneLeftMargin = goneLeftMargin;
      goneTopMargin = goneTopMargin;
      goneRightMargin = goneRightMargin;
      goneBottomMargin = goneBottomMargin;
      goneStartMargin = goneStartMargin;
      goneEndMargin = goneEndMargin;
      horizontalBias = horizontalBias;
      verticalBias = verticalBias;
      dimensionRatio = dimensionRatio;
      dimensionRatioValue = dimensionRatioValue;
      dimensionRatioSide = dimensionRatioSide;
      horizontalWeight = horizontalWeight;
      verticalWeight = verticalWeight;
      horizontalChainStyle = horizontalChainStyle;
      verticalChainStyle = verticalChainStyle;
      matchConstraintDefaultWidth = matchConstraintDefaultWidth;
      matchConstraintDefaultHeight = matchConstraintDefaultHeight;
      matchConstraintMinWidth = matchConstraintMinWidth;
      matchConstraintMaxWidth = matchConstraintMaxWidth;
      matchConstraintMinHeight = matchConstraintMinHeight;
      matchConstraintMaxHeight = matchConstraintMaxHeight;
      editorAbsoluteX = editorAbsoluteX;
      editorAbsoluteY = editorAbsoluteY;
      orientation = orientation;
      horizontalDimensionFixed = horizontalDimensionFixed;
      verticalDimensionFixed = verticalDimensionFixed;
      needsBaseline = needsBaseline;
      isGuideline = isGuideline;
      resolvedLeftToLeft = resolvedLeftToLeft;
      resolvedLeftToRight = resolvedLeftToRight;
      resolvedRightToLeft = resolvedRightToLeft;
      resolvedRightToRight = resolvedRightToRight;
      resolveGoneLeftMargin = resolveGoneLeftMargin;
      resolveGoneRightMargin = resolveGoneRightMargin;
      resolvedHorizontalBias = resolvedHorizontalBias;
      widget = widget;
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    @TargetApi(17)
    public void resolveLayoutDirection(int paramInt)
    {
      super.resolveLayoutDirection(paramInt);
      resolvedRightToLeft = -1;
      resolvedRightToRight = -1;
      resolvedLeftToLeft = -1;
      resolvedLeftToRight = -1;
      resolveGoneLeftMargin = -1;
      resolveGoneRightMargin = -1;
      resolveGoneLeftMargin = goneLeftMargin;
      resolveGoneRightMargin = goneRightMargin;
      resolvedHorizontalBias = horizontalBias;
      paramInt = getLayoutDirection();
      int i = 0;
      if (1 == paramInt) {
        paramInt = 1;
      } else {
        paramInt = 0;
      }
      if (paramInt != 0)
      {
        if (startToEnd != -1) {
          resolvedRightToLeft = startToEnd;
        }
        for (;;)
        {
          paramInt = 1;
          break;
          paramInt = i;
          if (startToStart == -1) {
            break;
          }
          resolvedRightToRight = startToStart;
        }
        if (endToStart != -1)
        {
          resolvedLeftToRight = endToStart;
          paramInt = 1;
        }
        if (endToEnd != -1)
        {
          resolvedLeftToLeft = endToEnd;
          paramInt = 1;
        }
        if (goneStartMargin != -1) {
          resolveGoneRightMargin = goneStartMargin;
        }
        if (goneEndMargin != -1) {
          resolveGoneLeftMargin = goneEndMargin;
        }
        if (paramInt != 0) {
          resolvedHorizontalBias = (1.0F - horizontalBias);
        }
      }
      else
      {
        if (startToEnd != -1) {
          resolvedLeftToRight = startToEnd;
        }
        if (startToStart != -1) {
          resolvedLeftToLeft = startToStart;
        }
        if (endToStart != -1) {
          resolvedRightToLeft = endToStart;
        }
        if (endToEnd != -1) {
          resolvedRightToRight = endToEnd;
        }
        if (goneStartMargin != -1) {
          resolveGoneLeftMargin = goneStartMargin;
        }
        if (goneEndMargin != -1) {
          resolveGoneRightMargin = goneEndMargin;
        }
      }
      if ((endToStart == -1) && (endToEnd == -1)) {
        if (rightToLeft != -1) {
          resolvedRightToLeft = rightToLeft;
        } else if (rightToRight != -1) {
          resolvedRightToRight = rightToRight;
        }
      }
      if ((startToStart == -1) && (startToEnd == -1))
      {
        if (leftToLeft != -1)
        {
          resolvedLeftToLeft = leftToLeft;
          return;
        }
        if (leftToRight != -1) {
          resolvedLeftToRight = leftToRight;
        }
      }
    }
    
    public void validate()
    {
      isGuideline = false;
      horizontalDimensionFixed = true;
      verticalDimensionFixed = true;
      if ((width == 0) || (width == -1)) {
        horizontalDimensionFixed = false;
      }
      if ((height == 0) || (height == -1)) {
        verticalDimensionFixed = false;
      }
      if ((guidePercent != -1.0F) || (guideBegin != -1) || (guideEnd != -1))
      {
        isGuideline = true;
        horizontalDimensionFixed = true;
        verticalDimensionFixed = true;
        if (!(widget instanceof android.support.constraint.solver.widgets.Guideline)) {
          widget = new android.support.constraint.solver.widgets.Guideline();
        }
        ((android.support.constraint.solver.widgets.Guideline)widget).setOrientation(orientation);
      }
    }
  }
}

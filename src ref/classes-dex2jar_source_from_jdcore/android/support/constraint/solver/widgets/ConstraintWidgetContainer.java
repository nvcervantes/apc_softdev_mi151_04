package android.support.constraint.solver.widgets;

import android.support.constraint.solver.ArrayRow;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import java.util.ArrayList;
import java.util.Arrays;

public class ConstraintWidgetContainer
  extends WidgetContainer
{
  static boolean ALLOW_ROOT_GROUP = true;
  private static final int CHAIN_FIRST = 0;
  private static final int CHAIN_FIRST_VISIBLE = 2;
  private static final int CHAIN_LAST = 1;
  private static final int CHAIN_LAST_VISIBLE = 3;
  private static final boolean DEBUG = false;
  private static final boolean DEBUG_LAYOUT = false;
  private static final boolean DEBUG_OPTIMIZE = false;
  private static final int FLAG_CHAIN_DANGLING = 1;
  private static final int FLAG_RECOMPUTE_BOUNDS = 2;
  private static final int FLAG_USE_OPTIMIZE = 0;
  private static final int MAX_ITERATIONS = 8;
  public static final int OPTIMIZATION_ALL = 2;
  public static final int OPTIMIZATION_BASIC = 4;
  public static final int OPTIMIZATION_CHAIN = 8;
  public static final int OPTIMIZATION_NONE = 1;
  private static final boolean USE_SNAPSHOT = true;
  private static final boolean USE_THREAD = false;
  private boolean[] flags = new boolean[3];
  protected LinearSystem mBackgroundSystem = null;
  private ConstraintWidget[] mChainEnds = new ConstraintWidget[4];
  private boolean mHeightMeasuredTooSmall = false;
  private ConstraintWidget[] mHorizontalChainsArray = new ConstraintWidget[4];
  private int mHorizontalChainsSize = 0;
  private ConstraintWidget[] mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
  private int mOptimizationLevel = 2;
  int mPaddingBottom;
  int mPaddingLeft;
  int mPaddingRight;
  int mPaddingTop;
  private Snapshot mSnapshot;
  protected LinearSystem mSystem = new LinearSystem();
  private ConstraintWidget[] mVerticalChainsArray = new ConstraintWidget[4];
  private int mVerticalChainsSize = 0;
  private boolean mWidthMeasuredTooSmall = false;
  int mWrapHeight;
  int mWrapWidth;
  
  public ConstraintWidgetContainer() {}
  
  public ConstraintWidgetContainer(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }
  
  public ConstraintWidgetContainer(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  private void addHorizontalChain(ConstraintWidget paramConstraintWidget)
  {
    int i = 0;
    while (i < mHorizontalChainsSize)
    {
      if (mHorizontalChainsArray[i] == paramConstraintWidget) {
        return;
      }
      i += 1;
    }
    if (mHorizontalChainsSize + 1 >= mHorizontalChainsArray.length) {
      mHorizontalChainsArray = ((ConstraintWidget[])Arrays.copyOf(mHorizontalChainsArray, mHorizontalChainsArray.length * 2));
    }
    mHorizontalChainsArray[mHorizontalChainsSize] = paramConstraintWidget;
    mHorizontalChainsSize += 1;
  }
  
  private void addVerticalChain(ConstraintWidget paramConstraintWidget)
  {
    int i = 0;
    while (i < mVerticalChainsSize)
    {
      if (mVerticalChainsArray[i] == paramConstraintWidget) {
        return;
      }
      i += 1;
    }
    if (mVerticalChainsSize + 1 >= mVerticalChainsArray.length) {
      mVerticalChainsArray = ((ConstraintWidget[])Arrays.copyOf(mVerticalChainsArray, mVerticalChainsArray.length * 2));
    }
    mVerticalChainsArray[mVerticalChainsSize] = paramConstraintWidget;
    mVerticalChainsSize += 1;
  }
  
  private void applyHorizontalChain(LinearSystem paramLinearSystem)
  {
    Object localObject1 = paramLinearSystem;
    int i = 0;
    int j = 0;
    for (;;)
    {
      Object localObject4 = this;
      if (j >= mHorizontalChainsSize) {
        break;
      }
      ConstraintWidget localConstraintWidget = mHorizontalChainsArray[j];
      int i1 = ((ConstraintWidgetContainer)localObject4).countMatchConstraintsChainedWidgets((LinearSystem)localObject1, mChainEnds, mHorizontalChainsArray[j], 0, flags);
      Object localObject2 = mChainEnds[2];
      if (localObject2 == null) {
        k = i;
      }
      float f;
      for (;;)
      {
        i = k;
        localObject3 = localObject1;
        break label2634;
        if (flags[1] != 0)
        {
          m = localConstraintWidget.getDrawX();
          for (;;)
          {
            k = i;
            if (localObject2 == null) {
              break;
            }
            ((LinearSystem)localObject1).addEquality(mLeft.mSolverVariable, m);
            localObject3 = mHorizontalNextWidget;
            m += mLeft.getMargin() + ((ConstraintWidget)localObject2).getWidth() + mRight.getMargin();
            localObject2 = localObject3;
          }
        }
        if (mHorizontalChainStyle == 0) {
          k = 1;
        } else {
          k = i;
        }
        if (mHorizontalChainStyle == 2) {
          m = 1;
        } else {
          m = i;
        }
        if (mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
          n = 1;
        } else {
          n = i;
        }
        if (((mOptimizationLevel == 2) || (mOptimizationLevel == 8)) && (flags[i] != 0) && (mHorizontalChainFixedPosition) && (m == 0) && (n == 0) && (mHorizontalChainStyle == 0))
        {
          Optimizer.applyDirectResolutionHorizontalChain((ConstraintWidgetContainer)localObject4, (LinearSystem)localObject1, i1, localConstraintWidget);
          k = i;
        }
        else
        {
          if ((i1 == 0) || (m != 0)) {
            break label1708;
          }
          f = 0.0F;
          localObject3 = null;
          while (localObject2 != null)
          {
            if (mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
            {
              m = mLeft.getMargin();
              k = m;
              if (localObject3 != null) {
                k = m + mRight.getMargin();
              }
              if (mLeft.mTarget.mOwner.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                m = 2;
              } else {
                m = 3;
              }
              ((LinearSystem)localObject1).addGreaterThan(mLeft.mSolverVariable, mLeft.mTarget.mSolverVariable, k, m);
              k = mRight.getMargin();
              m = k;
              if (mRight.mTarget.mOwner.mLeft.mTarget != null)
              {
                m = k;
                if (mRight.mTarget.mOwner.mLeft.mTarget.mOwner == localObject2) {
                  m = k + mRight.mTarget.mOwner.mLeft.getMargin();
                }
              }
              if (mRight.mTarget.mOwner.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                k = 2;
              } else {
                k = 3;
              }
              localObject3 = mRight.mTarget.mSolverVariable;
              if (localObject2 == mChainEnds[3])
              {
                localObject3 = mChainEnds[1].mRight.mTarget.mSolverVariable;
                k = 3;
              }
              ((LinearSystem)localObject1).addLowerThan(mRight.mSolverVariable, (SolverVariable)localObject3, -m, k);
            }
            else
            {
              f += mHorizontalWeight;
              if (mRight.mTarget != null)
              {
                m = mRight.getMargin();
                k = m;
                if (localObject2 != mChainEnds[3]) {
                  k = m + mRight.mTarget.mOwner.mLeft.getMargin();
                }
              }
              else
              {
                k = i;
              }
              ((LinearSystem)localObject1).addGreaterThan(mRight.mSolverVariable, mLeft.mSolverVariable, i, 1);
              ((LinearSystem)localObject1).addLowerThan(mRight.mSolverVariable, mRight.mTarget.mSolverVariable, -k, 1);
            }
            localObject5 = mHorizontalNextWidget;
            localObject3 = localObject2;
            localObject2 = localObject5;
          }
          if (i1 != 1) {
            break;
          }
          localObject3 = mMatchConstraintsChainedWidgets[i];
          m = mLeft.getMargin();
          k = m;
          if (mLeft.mTarget != null) {
            k = m + mLeft.mTarget.getMargin();
          }
          n = mRight.getMargin();
          m = n;
          if (mRight.mTarget != null) {
            m = n + mRight.mTarget.getMargin();
          }
          localObject2 = mRight.mTarget.mSolverVariable;
          if (localObject3 == mChainEnds[3]) {
            localObject2 = mChainEnds[1].mRight.mTarget.mSolverVariable;
          }
          if (mMatchConstraintDefaultWidth == 1)
          {
            ((LinearSystem)localObject1).addGreaterThan(mLeft.mSolverVariable, mLeft.mTarget.mSolverVariable, k, 1);
            ((LinearSystem)localObject1).addLowerThan(mRight.mSolverVariable, (SolverVariable)localObject2, -m, 1);
            ((LinearSystem)localObject1).addEquality(mRight.mSolverVariable, mLeft.mSolverVariable, localConstraintWidget.getWidth(), 2);
            k = i;
          }
          else
          {
            ((LinearSystem)localObject1).addEquality(mLeft.mSolverVariable, mLeft.mTarget.mSolverVariable, k, 1);
            ((LinearSystem)localObject1).addEquality(mRight.mSolverVariable, (SolverVariable)localObject2, -m, 1);
            k = i;
          }
        }
      }
      int n = i;
      int m = i1;
      Object localObject7;
      Object localObject8;
      Object localObject9;
      for (;;)
      {
        i1 = m - 1;
        k = i;
        if (n >= i1) {
          break;
        }
        localObject5 = mMatchConstraintsChainedWidgets[n];
        localObject2 = mMatchConstraintsChainedWidgets;
        n += 1;
        localObject6 = localObject2[n];
        localObject7 = mLeft.mSolverVariable;
        localObject8 = mRight.mSolverVariable;
        localObject9 = mLeft.mSolverVariable;
        localObject2 = mRight.mSolverVariable;
        if (localObject6 == mChainEnds[3]) {
          localObject2 = mChainEnds[1].mRight.mSolverVariable;
        }
        k = mLeft.getMargin();
        i = k;
        if (mLeft.mTarget != null)
        {
          i = k;
          if (mLeft.mTarget.mOwner.mRight.mTarget != null)
          {
            i = k;
            if (mLeft.mTarget.mOwner.mRight.mTarget.mOwner == localObject5) {
              i = k + mLeft.mTarget.mOwner.mRight.getMargin();
            }
          }
        }
        ((LinearSystem)localObject1).addGreaterThan((SolverVariable)localObject7, mLeft.mTarget.mSolverVariable, i, 2);
        k = mRight.getMargin();
        i = k;
        if (mRight.mTarget != null)
        {
          i = k;
          if (mHorizontalNextWidget != null)
          {
            if (mHorizontalNextWidget.mLeft.mTarget != null) {
              i = mHorizontalNextWidget.mLeft.getMargin();
            } else {
              i = 0;
            }
            i = k + i;
          }
        }
        ((LinearSystem)localObject1).addLowerThan((SolverVariable)localObject8, mRight.mTarget.mSolverVariable, -i, 2);
        if (n == i1)
        {
          k = mLeft.getMargin();
          i = k;
          if (mLeft.mTarget != null)
          {
            i = k;
            if (mLeft.mTarget.mOwner.mRight.mTarget != null)
            {
              i = k;
              if (mLeft.mTarget.mOwner.mRight.mTarget.mOwner == localObject6) {
                i = k + mLeft.mTarget.mOwner.mRight.getMargin();
              }
            }
          }
          ((LinearSystem)localObject1).addGreaterThan((SolverVariable)localObject9, mLeft.mTarget.mSolverVariable, i, 2);
          localObject3 = mRight;
          if (localObject6 == mChainEnds[3]) {
            localObject3 = mChainEnds[1].mRight;
          }
          k = ((ConstraintAnchor)localObject3).getMargin();
          i = k;
          if (mTarget != null)
          {
            i = k;
            if (mTarget.mOwner.mLeft.mTarget != null)
            {
              i = k;
              if (mTarget.mOwner.mLeft.mTarget.mOwner == localObject6) {
                i = k + mTarget.mOwner.mLeft.getMargin();
              }
            }
          }
          ((LinearSystem)localObject1).addLowerThan((SolverVariable)localObject2, mTarget.mSolverVariable, -i, 2);
        }
        if (mMatchConstraintMaxWidth > 0) {
          ((LinearSystem)localObject1).addLowerThan((SolverVariable)localObject8, (SolverVariable)localObject7, mMatchConstraintMaxWidth, 2);
        }
        localObject3 = paramLinearSystem.createRow();
        ((ArrayRow)localObject3).createRowEqualDimension(mHorizontalWeight, f, mHorizontalWeight, (SolverVariable)localObject7, mLeft.getMargin(), (SolverVariable)localObject8, mRight.getMargin(), (SolverVariable)localObject9, mLeft.getMargin(), (SolverVariable)localObject2, mRight.getMargin());
        ((LinearSystem)localObject1).addConstraint((ArrayRow)localObject3);
        i = 0;
      }
      label1708:
      localObject4 = localObject2;
      Object localObject5 = null;
      Object localObject6 = localObject5;
      i = 0;
      Object localObject3 = localObject1;
      localObject1 = localObject5;
      for (;;)
      {
        localObject7 = localObject4;
        if (localObject7 == null) {
          break;
        }
        localObject8 = mHorizontalNextWidget;
        if (localObject8 == null)
        {
          localObject4 = mChainEnds[1];
          i = 1;
        }
        else
        {
          localObject4 = localObject1;
        }
        if (m != 0)
        {
          localObject1 = mLeft;
          i1 = ((ConstraintAnchor)localObject1).getMargin();
          n = i1;
          if (localObject6 != null) {
            n = i1 + mRight.getMargin();
          }
          if (localObject2 != localObject7) {
            i1 = 3;
          } else {
            i1 = 1;
          }
          ((LinearSystem)localObject3).addGreaterThan(mSolverVariable, mTarget.mSolverVariable, n, i1);
          if (mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
          {
            localObject5 = mRight;
            if (mMatchConstraintDefaultWidth == 1)
            {
              n = Math.max(mMatchConstraintMinWidth, ((ConstraintWidget)localObject7).getWidth());
              ((LinearSystem)localObject3).addEquality(mSolverVariable, mSolverVariable, n, 3);
            }
            else
            {
              ((LinearSystem)localObject3).addGreaterThan(mSolverVariable, mTarget.mSolverVariable, mMargin, 3);
              ((LinearSystem)localObject3).addLowerThan(mSolverVariable, mSolverVariable, mMatchConstraintMinWidth, 3);
            }
          }
        }
        else if ((k == 0) && (i != 0) && (localObject6 != null))
        {
          if (mRight.mTarget == null)
          {
            ((LinearSystem)localObject3).addEquality(mRight.mSolverVariable, ((ConstraintWidget)localObject7).getDrawRight());
          }
          else
          {
            n = mRight.getMargin();
            ((LinearSystem)localObject3).addEquality(mRight.mSolverVariable, mRight.mTarget.mSolverVariable, -n, 5);
          }
        }
        else
        {
          if ((k != 0) || (i != 0) || (localObject6 != null)) {
            break label2140;
          }
          if (mLeft.mTarget == null)
          {
            ((LinearSystem)localObject3).addEquality(mLeft.mSolverVariable, ((ConstraintWidget)localObject7).getDrawX());
          }
          else
          {
            n = mLeft.getMargin();
            ((LinearSystem)localObject3).addEquality(mLeft.mSolverVariable, mLeft.mTarget.mSolverVariable, n, 5);
          }
        }
        localObject1 = localObject8;
        break label2397;
        label2140:
        localObject9 = mLeft;
        ConstraintAnchor localConstraintAnchor = mRight;
        n = ((ConstraintAnchor)localObject9).getMargin();
        i1 = localConstraintAnchor.getMargin();
        ((LinearSystem)localObject3).addGreaterThan(mSolverVariable, mTarget.mSolverVariable, n, 1);
        ((LinearSystem)localObject3).addLowerThan(mSolverVariable, mTarget.mSolverVariable, -i1, 1);
        if (mTarget != null) {
          localObject5 = mTarget.mSolverVariable;
        } else {
          localObject5 = null;
        }
        if (localObject6 == null) {
          if (mLeft.mTarget != null) {
            localObject5 = mLeft.mTarget.mSolverVariable;
          } else {
            localObject5 = null;
          }
        }
        localObject1 = localObject8;
        if (localObject8 == null) {
          if (mRight.mTarget != null) {
            localObject1 = mRight.mTarget.mOwner;
          } else {
            localObject1 = null;
          }
        }
        if (localObject1 != null)
        {
          localObject6 = mLeft.mSolverVariable;
          if (i != 0) {
            if (mRight.mTarget != null) {
              localObject6 = mRight.mTarget.mSolverVariable;
            } else {
              localObject6 = null;
            }
          }
          if ((localObject5 != null) && (localObject6 != null)) {
            ((LinearSystem)localObject3).addCentering(mSolverVariable, (SolverVariable)localObject5, n, 0.5F, (SolverVariable)localObject6, mSolverVariable, i1, 4);
          }
        }
        label2397:
        if (i != 0) {
          localObject1 = null;
        }
        localObject5 = localObject3;
        localObject3 = localObject4;
        localObject6 = localObject7;
        localObject4 = localObject1;
        localObject1 = localObject3;
        localObject3 = localObject5;
      }
      int k = j;
      localObject4 = localObject3;
      n = 0;
      localObject3 = localObject4;
      j = k;
      i = n;
      if (m != 0)
      {
        localObject5 = mLeft;
        localObject6 = mRight;
        m = ((ConstraintAnchor)localObject5).getMargin();
        i1 = ((ConstraintAnchor)localObject6).getMargin();
        if (mLeft.mTarget != null) {
          localObject2 = mLeft.mTarget.mSolverVariable;
        } else {
          localObject2 = null;
        }
        if (mRight.mTarget != null) {
          localObject1 = mRight.mTarget.mSolverVariable;
        } else {
          localObject1 = null;
        }
        localObject3 = localObject4;
        j = k;
        i = n;
        if (localObject2 != null)
        {
          localObject3 = localObject4;
          j = k;
          i = n;
          if (localObject1 != null)
          {
            ((LinearSystem)localObject4).addLowerThan(mSolverVariable, (SolverVariable)localObject1, -i1, 1);
            ((LinearSystem)localObject4).addCentering(mSolverVariable, (SolverVariable)localObject2, m, mHorizontalBiasPercent, (SolverVariable)localObject1, mSolverVariable, i1, 4);
            i = n;
            j = k;
            localObject3 = localObject4;
          }
        }
      }
      label2634:
      j += 1;
      localObject1 = localObject3;
    }
  }
  
  private void applyVerticalChain(LinearSystem paramLinearSystem)
  {
    Object localObject1 = paramLinearSystem;
    int i = 0;
    int j = 0;
    for (;;)
    {
      Object localObject4 = this;
      if (j >= mVerticalChainsSize) {
        break;
      }
      ConstraintWidget localConstraintWidget = mVerticalChainsArray[j];
      int i1 = ((ConstraintWidgetContainer)localObject4).countMatchConstraintsChainedWidgets((LinearSystem)localObject1, mChainEnds, mVerticalChainsArray[j], 1, flags);
      Object localObject2 = mChainEnds[2];
      if (localObject2 == null) {
        k = i;
      }
      float f;
      for (;;)
      {
        i = k;
        localObject3 = localObject1;
        break label2721;
        if (flags[1] != 0)
        {
          m = localConstraintWidget.getDrawY();
          for (;;)
          {
            k = i;
            if (localObject2 == null) {
              break;
            }
            ((LinearSystem)localObject1).addEquality(mTop.mSolverVariable, m);
            localObject3 = mVerticalNextWidget;
            m += mTop.getMargin() + ((ConstraintWidget)localObject2).getHeight() + mBottom.getMargin();
            localObject2 = localObject3;
          }
        }
        if (mVerticalChainStyle == 0) {
          k = 1;
        } else {
          k = i;
        }
        if (mVerticalChainStyle == 2) {
          m = 1;
        } else {
          m = i;
        }
        if (mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
          n = 1;
        } else {
          n = i;
        }
        if (((mOptimizationLevel == 2) || (mOptimizationLevel == 8)) && (flags[i] != 0) && (mVerticalChainFixedPosition) && (m == 0) && (n == 0) && (mVerticalChainStyle == 0))
        {
          Optimizer.applyDirectResolutionVerticalChain((ConstraintWidgetContainer)localObject4, (LinearSystem)localObject1, i1, localConstraintWidget);
          k = i;
        }
        else
        {
          if ((i1 == 0) || (m != 0)) {
            break label1708;
          }
          f = 0.0F;
          localObject3 = null;
          while (localObject2 != null)
          {
            if (mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
            {
              m = mTop.getMargin();
              k = m;
              if (localObject3 != null) {
                k = m + mBottom.getMargin();
              }
              if (mTop.mTarget.mOwner.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                m = 2;
              } else {
                m = 3;
              }
              ((LinearSystem)localObject1).addGreaterThan(mTop.mSolverVariable, mTop.mTarget.mSolverVariable, k, m);
              k = mBottom.getMargin();
              m = k;
              if (mBottom.mTarget.mOwner.mTop.mTarget != null)
              {
                m = k;
                if (mBottom.mTarget.mOwner.mTop.mTarget.mOwner == localObject2) {
                  m = k + mBottom.mTarget.mOwner.mTop.getMargin();
                }
              }
              if (mBottom.mTarget.mOwner.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                k = 2;
              } else {
                k = 3;
              }
              localObject3 = mBottom.mTarget.mSolverVariable;
              if (localObject2 == mChainEnds[3])
              {
                localObject3 = mChainEnds[1].mBottom.mTarget.mSolverVariable;
                k = 3;
              }
              ((LinearSystem)localObject1).addLowerThan(mBottom.mSolverVariable, (SolverVariable)localObject3, -m, k);
            }
            else
            {
              f += mVerticalWeight;
              if (mBottom.mTarget != null)
              {
                m = mBottom.getMargin();
                k = m;
                if (localObject2 != mChainEnds[3]) {
                  k = m + mBottom.mTarget.mOwner.mTop.getMargin();
                }
              }
              else
              {
                k = i;
              }
              ((LinearSystem)localObject1).addGreaterThan(mBottom.mSolverVariable, mTop.mSolverVariable, i, 1);
              ((LinearSystem)localObject1).addLowerThan(mBottom.mSolverVariable, mBottom.mTarget.mSolverVariable, -k, 1);
            }
            localObject5 = mVerticalNextWidget;
            localObject3 = localObject2;
            localObject2 = localObject5;
          }
          if (i1 != 1) {
            break;
          }
          localObject3 = mMatchConstraintsChainedWidgets[i];
          m = mTop.getMargin();
          k = m;
          if (mTop.mTarget != null) {
            k = m + mTop.mTarget.getMargin();
          }
          n = mBottom.getMargin();
          m = n;
          if (mBottom.mTarget != null) {
            m = n + mBottom.mTarget.getMargin();
          }
          localObject2 = mBottom.mTarget.mSolverVariable;
          if (localObject3 == mChainEnds[3]) {
            localObject2 = mChainEnds[1].mBottom.mTarget.mSolverVariable;
          }
          if (mMatchConstraintDefaultHeight == 1)
          {
            ((LinearSystem)localObject1).addGreaterThan(mTop.mSolverVariable, mTop.mTarget.mSolverVariable, k, 1);
            ((LinearSystem)localObject1).addLowerThan(mBottom.mSolverVariable, (SolverVariable)localObject2, -m, 1);
            ((LinearSystem)localObject1).addEquality(mBottom.mSolverVariable, mTop.mSolverVariable, localConstraintWidget.getHeight(), 2);
            k = i;
          }
          else
          {
            ((LinearSystem)localObject1).addEquality(mTop.mSolverVariable, mTop.mTarget.mSolverVariable, k, 1);
            ((LinearSystem)localObject1).addEquality(mBottom.mSolverVariable, (SolverVariable)localObject2, -m, 1);
            k = i;
          }
        }
      }
      int n = i;
      int m = i1;
      Object localObject6;
      Object localObject7;
      Object localObject9;
      for (;;)
      {
        i1 = m - 1;
        k = i;
        if (n >= i1) {
          break;
        }
        localObject5 = mMatchConstraintsChainedWidgets[n];
        localObject2 = mMatchConstraintsChainedWidgets;
        n += 1;
        localObject6 = localObject2[n];
        localObject7 = mTop.mSolverVariable;
        localObject8 = mBottom.mSolverVariable;
        localObject9 = mTop.mSolverVariable;
        localObject2 = mBottom.mSolverVariable;
        if (localObject6 == mChainEnds[3]) {
          localObject2 = mChainEnds[1].mBottom.mSolverVariable;
        }
        k = mTop.getMargin();
        i = k;
        if (mTop.mTarget != null)
        {
          i = k;
          if (mTop.mTarget.mOwner.mBottom.mTarget != null)
          {
            i = k;
            if (mTop.mTarget.mOwner.mBottom.mTarget.mOwner == localObject5) {
              i = k + mTop.mTarget.mOwner.mBottom.getMargin();
            }
          }
        }
        ((LinearSystem)localObject1).addGreaterThan((SolverVariable)localObject7, mTop.mTarget.mSolverVariable, i, 2);
        k = mBottom.getMargin();
        i = k;
        if (mBottom.mTarget != null)
        {
          i = k;
          if (mVerticalNextWidget != null)
          {
            if (mVerticalNextWidget.mTop.mTarget != null) {
              i = mVerticalNextWidget.mTop.getMargin();
            } else {
              i = 0;
            }
            i = k + i;
          }
        }
        ((LinearSystem)localObject1).addLowerThan((SolverVariable)localObject8, mBottom.mTarget.mSolverVariable, -i, 2);
        if (n == i1)
        {
          k = mTop.getMargin();
          i = k;
          if (mTop.mTarget != null)
          {
            i = k;
            if (mTop.mTarget.mOwner.mBottom.mTarget != null)
            {
              i = k;
              if (mTop.mTarget.mOwner.mBottom.mTarget.mOwner == localObject6) {
                i = k + mTop.mTarget.mOwner.mBottom.getMargin();
              }
            }
          }
          ((LinearSystem)localObject1).addGreaterThan((SolverVariable)localObject9, mTop.mTarget.mSolverVariable, i, 2);
          localObject3 = mBottom;
          if (localObject6 == mChainEnds[3]) {
            localObject3 = mChainEnds[1].mBottom;
          }
          k = ((ConstraintAnchor)localObject3).getMargin();
          i = k;
          if (mTarget != null)
          {
            i = k;
            if (mTarget.mOwner.mTop.mTarget != null)
            {
              i = k;
              if (mTarget.mOwner.mTop.mTarget.mOwner == localObject6) {
                i = k + mTarget.mOwner.mTop.getMargin();
              }
            }
          }
          ((LinearSystem)localObject1).addLowerThan((SolverVariable)localObject2, mTarget.mSolverVariable, -i, 2);
        }
        if (mMatchConstraintMaxHeight > 0) {
          ((LinearSystem)localObject1).addLowerThan((SolverVariable)localObject8, (SolverVariable)localObject7, mMatchConstraintMaxHeight, 2);
        }
        localObject3 = paramLinearSystem.createRow();
        ((ArrayRow)localObject3).createRowEqualDimension(mVerticalWeight, f, mVerticalWeight, (SolverVariable)localObject7, mTop.getMargin(), (SolverVariable)localObject8, mBottom.getMargin(), (SolverVariable)localObject9, mTop.getMargin(), (SolverVariable)localObject2, mBottom.getMargin());
        ((LinearSystem)localObject1).addConstraint((ArrayRow)localObject3);
        i = 0;
      }
      label1708:
      localObject4 = localObject2;
      Object localObject5 = null;
      Object localObject8 = localObject5;
      i = 0;
      Object localObject3 = localObject1;
      n = k;
      localObject1 = localObject5;
      for (;;)
      {
        localObject7 = localObject4;
        if (localObject7 == null) {
          break;
        }
        localObject6 = mVerticalNextWidget;
        if (localObject6 == null)
        {
          localObject4 = mChainEnds[1];
          i = 1;
        }
        else
        {
          localObject4 = localObject1;
        }
        if (m != 0)
        {
          localObject9 = mTop;
          i1 = ((ConstraintAnchor)localObject9).getMargin();
          k = i1;
          if (localObject8 != null) {
            k = i1 + mBottom.getMargin();
          }
          if (localObject2 != localObject7) {
            i1 = 3;
          } else {
            i1 = 1;
          }
          if (mTarget != null)
          {
            localObject1 = mSolverVariable;
            localObject5 = mTarget.mSolverVariable;
          }
          else if (mBaseline.mTarget != null)
          {
            localObject1 = mBaseline.mSolverVariable;
            localObject5 = mBaseline.mTarget.mSolverVariable;
            k -= ((ConstraintAnchor)localObject9).getMargin();
          }
          else
          {
            localObject1 = null;
            localObject5 = localObject1;
          }
          if ((localObject1 != null) && (localObject5 != null)) {
            ((LinearSystem)localObject3).addGreaterThan((SolverVariable)localObject1, (SolverVariable)localObject5, k, i1);
          }
          if (mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
          {
            localObject1 = mBottom;
            if (mMatchConstraintDefaultHeight == 1)
            {
              k = Math.max(mMatchConstraintMinHeight, ((ConstraintWidget)localObject7).getHeight());
              ((LinearSystem)localObject3).addEquality(mSolverVariable, mSolverVariable, k, 3);
            }
            else
            {
              ((LinearSystem)localObject3).addGreaterThan(mSolverVariable, mTarget.mSolverVariable, mMargin, 3);
              ((LinearSystem)localObject3).addLowerThan(mSolverVariable, mSolverVariable, mMatchConstraintMinHeight, 3);
            }
          }
        }
        else if ((n == 0) && (i != 0) && (localObject8 != null))
        {
          if (mBottom.mTarget == null)
          {
            ((LinearSystem)localObject3).addEquality(mBottom.mSolverVariable, ((ConstraintWidget)localObject7).getDrawBottom());
          }
          else
          {
            k = mBottom.getMargin();
            ((LinearSystem)localObject3).addEquality(mBottom.mSolverVariable, mBottom.mTarget.mSolverVariable, -k, 5);
          }
        }
        else
        {
          if ((n != 0) || (i != 0) || (localObject8 != null)) {
            break label2227;
          }
          if (mTop.mTarget == null)
          {
            ((LinearSystem)localObject3).addEquality(mTop.mSolverVariable, ((ConstraintWidget)localObject7).getDrawY());
          }
          else
          {
            k = mTop.getMargin();
            ((LinearSystem)localObject3).addEquality(mTop.mSolverVariable, mTop.mTarget.mSolverVariable, k, 5);
          }
        }
        localObject1 = localObject6;
        break label2484;
        label2227:
        localObject9 = mTop;
        ConstraintAnchor localConstraintAnchor = mBottom;
        k = ((ConstraintAnchor)localObject9).getMargin();
        i1 = localConstraintAnchor.getMargin();
        ((LinearSystem)localObject3).addGreaterThan(mSolverVariable, mTarget.mSolverVariable, k, 1);
        ((LinearSystem)localObject3).addLowerThan(mSolverVariable, mTarget.mSolverVariable, -i1, 1);
        if (mTarget != null) {
          localObject5 = mTarget.mSolverVariable;
        } else {
          localObject5 = null;
        }
        if (localObject8 == null) {
          if (mTop.mTarget != null) {
            localObject5 = mTop.mTarget.mSolverVariable;
          } else {
            localObject5 = null;
          }
        }
        localObject1 = localObject6;
        if (localObject6 == null) {
          if (mBottom.mTarget != null) {
            localObject1 = mBottom.mTarget.mOwner;
          } else {
            localObject1 = null;
          }
        }
        if (localObject1 != null)
        {
          localObject6 = mTop.mSolverVariable;
          if (i != 0) {
            if (mBottom.mTarget != null) {
              localObject6 = mBottom.mTarget.mSolverVariable;
            } else {
              localObject6 = null;
            }
          }
          if ((localObject5 != null) && (localObject6 != null)) {
            ((LinearSystem)localObject3).addCentering(mSolverVariable, (SolverVariable)localObject5, k, 0.5F, (SolverVariable)localObject6, mSolverVariable, i1, 4);
          }
        }
        label2484:
        if (i != 0) {
          localObject1 = null;
        }
        localObject5 = localObject3;
        localObject3 = localObject4;
        localObject4 = localObject1;
        localObject1 = localObject3;
        localObject8 = localObject7;
        localObject3 = localObject5;
      }
      int k = j;
      localObject4 = localObject3;
      n = 0;
      localObject3 = localObject4;
      j = k;
      i = n;
      if (m != 0)
      {
        localObject5 = mTop;
        localObject6 = mBottom;
        m = ((ConstraintAnchor)localObject5).getMargin();
        i1 = ((ConstraintAnchor)localObject6).getMargin();
        if (mTop.mTarget != null) {
          localObject2 = mTop.mTarget.mSolverVariable;
        } else {
          localObject2 = null;
        }
        if (mBottom.mTarget != null) {
          localObject1 = mBottom.mTarget.mSolverVariable;
        } else {
          localObject1 = null;
        }
        localObject3 = localObject4;
        j = k;
        i = n;
        if (localObject2 != null)
        {
          localObject3 = localObject4;
          j = k;
          i = n;
          if (localObject1 != null)
          {
            ((LinearSystem)localObject4).addLowerThan(mSolverVariable, (SolverVariable)localObject1, -i1, 1);
            ((LinearSystem)localObject4).addCentering(mSolverVariable, (SolverVariable)localObject2, m, mVerticalBiasPercent, (SolverVariable)localObject1, mSolverVariable, i1, 4);
            i = n;
            j = k;
            localObject3 = localObject4;
          }
        }
      }
      label2721:
      j += 1;
      localObject1 = localObject3;
    }
  }
  
  private int countMatchConstraintsChainedWidgets(LinearSystem paramLinearSystem, ConstraintWidget[] paramArrayOfConstraintWidget, ConstraintWidget paramConstraintWidget, int paramInt, boolean[] paramArrayOfBoolean)
  {
    paramArrayOfBoolean[0] = true;
    paramArrayOfBoolean[1] = false;
    paramArrayOfConstraintWidget[0] = null;
    paramArrayOfConstraintWidget[2] = null;
    paramArrayOfConstraintWidget[1] = null;
    paramArrayOfConstraintWidget[3] = null;
    boolean bool1;
    Object localObject1;
    Object localObject3;
    Object localObject4;
    int i;
    if (paramInt == 0)
    {
      if ((mLeft.mTarget != null) && (mLeft.mTarget.mOwner != this)) {
        bool1 = false;
      } else {
        bool1 = true;
      }
      mHorizontalNextWidget = null;
      if (paramConstraintWidget.getVisibility() != 8) {
        localObject2 = paramConstraintWidget;
      } else {
        localObject2 = null;
      }
      paramInt = 0;
      localConstraintWidget2 = null;
      localObject1 = localObject2;
      localConstraintWidget1 = paramConstraintWidget;
      for (;;)
      {
        localObject3 = localObject2;
        localObject4 = localObject1;
        i = paramInt;
        if (mRight.mTarget == null) {
          break;
        }
        mHorizontalNextWidget = null;
        if (localConstraintWidget1.getVisibility() != 8)
        {
          localObject3 = localObject1;
          if (localObject1 == null) {
            localObject3 = localConstraintWidget1;
          }
          if ((localObject2 != null) && (localObject2 != localConstraintWidget1)) {
            mHorizontalNextWidget = localConstraintWidget1;
          }
          localObject2 = localConstraintWidget1;
          localObject1 = localObject3;
        }
        else
        {
          paramLinearSystem.addEquality(mLeft.mSolverVariable, mLeft.mTarget.mSolverVariable, 0, 5);
          paramLinearSystem.addEquality(mRight.mSolverVariable, mLeft.mSolverVariable, 0, 5);
        }
        i = paramInt;
        if (localConstraintWidget1.getVisibility() != 8)
        {
          i = paramInt;
          if (mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
          {
            if (mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
              paramArrayOfBoolean[0] = false;
            }
            i = paramInt;
            if (mDimensionRatio <= 0.0F)
            {
              paramArrayOfBoolean[0] = false;
              i = paramInt + 1;
              if (i >= mMatchConstraintsChainedWidgets.length) {
                mMatchConstraintsChainedWidgets = ((ConstraintWidget[])Arrays.copyOf(mMatchConstraintsChainedWidgets, mMatchConstraintsChainedWidgets.length * 2));
              }
              mMatchConstraintsChainedWidgets[paramInt] = localConstraintWidget1;
            }
          }
        }
        if (mRight.mTarget.mOwner.mLeft.mTarget == null)
        {
          localObject3 = localObject2;
          localObject4 = localObject1;
          break;
        }
        if (mRight.mTarget.mOwner.mLeft.mTarget.mOwner != localConstraintWidget1)
        {
          localObject3 = localObject2;
          localObject4 = localObject1;
          break;
        }
        if (mRight.mTarget.mOwner == localConstraintWidget1)
        {
          localObject3 = localObject2;
          localObject4 = localObject1;
          break;
        }
        localConstraintWidget2 = mRight.mTarget.mOwner;
        localConstraintWidget1 = localConstraintWidget2;
        paramInt = i;
      }
      bool2 = bool1;
      if (mRight.mTarget != null)
      {
        bool2 = bool1;
        if (mRight.mTarget.mOwner != this) {
          bool2 = false;
        }
      }
      if ((mLeft.mTarget != null) && (mRight.mTarget != null)) {
        break label528;
      }
      paramArrayOfBoolean[1] = true;
      label528:
      mHorizontalChainFixedPosition = bool2;
      mHorizontalNextWidget = null;
      paramArrayOfConstraintWidget[0] = paramConstraintWidget;
      paramArrayOfConstraintWidget[2] = localObject4;
      paramArrayOfConstraintWidget[1] = localConstraintWidget2;
      paramArrayOfConstraintWidget[3] = localObject3;
      return i;
    }
    if ((mTop.mTarget != null) && (mTop.mTarget.mOwner != this)) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    mVerticalNextWidget = null;
    if (paramConstraintWidget.getVisibility() != 8) {
      localObject1 = paramConstraintWidget;
    } else {
      localObject1 = null;
    }
    paramInt = 0;
    ConstraintWidget localConstraintWidget2 = null;
    Object localObject2 = localObject1;
    ConstraintWidget localConstraintWidget1 = paramConstraintWidget;
    for (;;)
    {
      localObject3 = localObject1;
      localObject4 = localObject2;
      i = paramInt;
      if (mBottom.mTarget == null) {
        break;
      }
      mVerticalNextWidget = null;
      if (localConstraintWidget1.getVisibility() != 8)
      {
        localObject3 = localObject1;
        if (localObject1 == null) {
          localObject3 = localConstraintWidget1;
        }
        if ((localObject2 != null) && (localObject2 != localConstraintWidget1)) {
          mVerticalNextWidget = localConstraintWidget1;
        }
        localObject2 = localConstraintWidget1;
        localObject1 = localObject3;
      }
      else
      {
        paramLinearSystem.addEquality(mTop.mSolverVariable, mTop.mTarget.mSolverVariable, 0, 5);
        paramLinearSystem.addEquality(mBottom.mSolverVariable, mTop.mSolverVariable, 0, 5);
      }
      if ((localConstraintWidget1.getVisibility() != 8) && (mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT))
      {
        if (mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
          paramArrayOfBoolean[0] = false;
        }
        i = paramInt;
        if (mDimensionRatio <= 0.0F)
        {
          paramArrayOfBoolean[0] = false;
          i = paramInt + 1;
          if (i >= mMatchConstraintsChainedWidgets.length) {
            mMatchConstraintsChainedWidgets = ((ConstraintWidget[])Arrays.copyOf(mMatchConstraintsChainedWidgets, mMatchConstraintsChainedWidgets.length * 2));
          }
          mMatchConstraintsChainedWidgets[paramInt] = localConstraintWidget1;
        }
      }
      else
      {
        i = paramInt;
      }
      if ((mBottom.mTarget.mOwner.mTop.mTarget == null) || (mBottom.mTarget.mOwner.mTop.mTarget.mOwner != localConstraintWidget1) || (mBottom.mTarget.mOwner == localConstraintWidget1))
      {
        localObject3 = localObject1;
        localObject4 = localObject2;
        break;
      }
      localConstraintWidget2 = mBottom.mTarget.mOwner;
      localConstraintWidget1 = localConstraintWidget2;
      paramInt = i;
    }
    boolean bool2 = bool1;
    if (mBottom.mTarget != null)
    {
      bool2 = bool1;
      if (mBottom.mTarget.mOwner != this) {
        bool2 = false;
      }
    }
    if ((mTop.mTarget != null) && (mBottom.mTarget != null)) {
      break label1042;
    }
    paramArrayOfBoolean[1] = true;
    label1042:
    mVerticalChainFixedPosition = bool2;
    mVerticalNextWidget = null;
    paramArrayOfConstraintWidget[0] = paramConstraintWidget;
    paramArrayOfConstraintWidget[2] = localObject3;
    paramArrayOfConstraintWidget[1] = localConstraintWidget2;
    paramArrayOfConstraintWidget[3] = localObject4;
    return i;
  }
  
  public static ConstraintWidgetContainer createContainer(ConstraintWidgetContainer paramConstraintWidgetContainer, String paramString, ArrayList<ConstraintWidget> paramArrayList, int paramInt)
  {
    Rectangle localRectangle = getBounds(paramArrayList);
    if ((width != 0) && (height != 0))
    {
      if (paramInt > 0)
      {
        int j = Math.min(x, y);
        i = paramInt;
        if (paramInt > j) {
          i = j;
        }
        localRectangle.grow(i, i);
      }
      paramConstraintWidgetContainer.setOrigin(x, y);
      paramConstraintWidgetContainer.setDimension(width, height);
      paramConstraintWidgetContainer.setDebugName(paramString);
      paramInt = 0;
      paramString = ((ConstraintWidget)paramArrayList.get(0)).getParent();
      int i = paramArrayList.size();
      while (paramInt < i)
      {
        ConstraintWidget localConstraintWidget = (ConstraintWidget)paramArrayList.get(paramInt);
        if (localConstraintWidget.getParent() == paramString)
        {
          paramConstraintWidgetContainer.add(localConstraintWidget);
          localConstraintWidget.setX(localConstraintWidget.getX() - x);
          localConstraintWidget.setY(localConstraintWidget.getY() - y);
        }
        paramInt += 1;
      }
      return paramConstraintWidgetContainer;
    }
    return null;
  }
  
  private boolean optimize(LinearSystem paramLinearSystem)
  {
    int i4 = mChildren.size();
    int i = 0;
    ConstraintWidget localConstraintWidget;
    while (i < i4)
    {
      localConstraintWidget = (ConstraintWidget)mChildren.get(i);
      mHorizontalResolution = -1;
      mVerticalResolution = -1;
      if ((mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) || (mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT))
      {
        mHorizontalResolution = 1;
        mVerticalResolution = 1;
      }
      if ((localConstraintWidget instanceof Barrier))
      {
        mHorizontalResolution = 1;
        mVerticalResolution = 1;
      }
      i += 1;
    }
    int k = 0;
    i = k;
    int n = i;
    int m = i;
    while (k == 0)
    {
      int i1 = 0;
      j = i1;
      int i3;
      for (i = j; i1 < i4; i = i3)
      {
        localConstraintWidget = (ConstraintWidget)mChildren.get(i1);
        if (mHorizontalResolution == -1) {
          if (mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
            mHorizontalResolution = 1;
          } else {
            Optimizer.checkHorizontalSimpleDependency(this, paramLinearSystem, localConstraintWidget);
          }
        }
        if (mVerticalResolution == -1) {
          if (mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
            mVerticalResolution = 1;
          } else {
            Optimizer.checkVerticalSimpleDependency(this, paramLinearSystem, localConstraintWidget);
          }
        }
        int i2 = j;
        if (mVerticalResolution == -1) {
          i2 = j + 1;
        }
        i3 = i;
        if (mHorizontalResolution == -1) {
          i3 = i + 1;
        }
        i1 += 1;
        j = i2;
      }
      if ((j == 0) && (i == 0)) {}
      do
      {
        i1 = 1;
        break;
        i1 = k;
        if (m != j) {
          break;
        }
        i1 = k;
      } while (n == i);
      k = i1;
      m = j;
      n = i;
    }
    int j = 0;
    i = j;
    k = i;
    m = i;
    while (j < i4)
    {
      paramLinearSystem = (ConstraintWidget)mChildren.get(j);
      if (mHorizontalResolution != 1)
      {
        i = m;
        if (mHorizontalResolution != -1) {}
      }
      else
      {
        i = m + 1;
      }
      if (mVerticalResolution != 1)
      {
        n = k;
        if (mVerticalResolution != -1) {}
      }
      else
      {
        n = k + 1;
      }
      j += 1;
      m = i;
      k = n;
    }
    return (m == 0) && (k == 0);
  }
  
  private void resetChains()
  {
    mHorizontalChainsSize = 0;
    mVerticalChainsSize = 0;
  }
  
  static int setGroup(ConstraintAnchor paramConstraintAnchor, int paramInt)
  {
    int i = mGroup;
    if (mOwner.getParent() == null) {
      return paramInt;
    }
    if (i <= paramInt) {
      return i;
    }
    mGroup = paramInt;
    ConstraintAnchor localConstraintAnchor1 = paramConstraintAnchor.getOpposite();
    ConstraintAnchor localConstraintAnchor2 = mTarget;
    i = paramInt;
    if (localConstraintAnchor1 != null) {
      i = setGroup(localConstraintAnchor1, paramInt);
    }
    paramInt = i;
    if (localConstraintAnchor2 != null) {
      paramInt = setGroup(localConstraintAnchor2, i);
    }
    i = paramInt;
    if (localConstraintAnchor1 != null) {
      i = setGroup(localConstraintAnchor1, paramInt);
    }
    mGroup = i;
    return i;
  }
  
  void addChain(ConstraintWidget paramConstraintWidget, int paramInt)
  {
    if (paramInt == 0)
    {
      while ((mLeft.mTarget != null) && (mLeft.mTarget.mOwner.mRight.mTarget != null) && (mLeft.mTarget.mOwner.mRight.mTarget == mLeft) && (mLeft.mTarget.mOwner != paramConstraintWidget)) {
        paramConstraintWidget = mLeft.mTarget.mOwner;
      }
      addHorizontalChain(paramConstraintWidget);
      return;
    }
    if (paramInt == 1)
    {
      while ((mTop.mTarget != null) && (mTop.mTarget.mOwner.mBottom.mTarget != null) && (mTop.mTarget.mOwner.mBottom.mTarget == mTop) && (mTop.mTarget.mOwner != paramConstraintWidget)) {
        paramConstraintWidget = mTop.mTarget.mOwner;
      }
      addVerticalChain(paramConstraintWidget);
    }
  }
  
  public boolean addChildrenToSolver(LinearSystem paramLinearSystem, int paramInt)
  {
    addToSolver(paramLinearSystem, paramInt);
    int k = mChildren.size();
    int i = mOptimizationLevel;
    int j = 0;
    if ((i != 2) && (mOptimizationLevel != 4))
    {
      i = 1;
    }
    else
    {
      if (optimize(paramLinearSystem)) {
        return false;
      }
      i = 0;
    }
    while (j < k)
    {
      ConstraintWidget localConstraintWidget = (ConstraintWidget)mChildren.get(j);
      if ((localConstraintWidget instanceof ConstraintWidgetContainer))
      {
        ConstraintWidget.DimensionBehaviour localDimensionBehaviour1 = mHorizontalDimensionBehaviour;
        ConstraintWidget.DimensionBehaviour localDimensionBehaviour2 = mVerticalDimensionBehaviour;
        if (localDimensionBehaviour1 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
          localConstraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
        }
        if (localDimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
          localConstraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
        }
        localConstraintWidget.addToSolver(paramLinearSystem, paramInt);
        if (localDimensionBehaviour1 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
          localConstraintWidget.setHorizontalDimensionBehaviour(localDimensionBehaviour1);
        }
        if (localDimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
          localConstraintWidget.setVerticalDimensionBehaviour(localDimensionBehaviour2);
        }
      }
      else
      {
        if (i != 0) {
          Optimizer.checkMatchParent(this, paramLinearSystem, localConstraintWidget);
        }
        localConstraintWidget.addToSolver(paramLinearSystem, paramInt);
      }
      j += 1;
    }
    if (mHorizontalChainsSize > 0) {
      applyHorizontalChain(paramLinearSystem);
    }
    if (mVerticalChainsSize > 0) {
      applyVerticalChain(paramLinearSystem);
    }
    return true;
  }
  
  public void findHorizontalWrapRecursive(ConstraintWidget paramConstraintWidget, boolean[] paramArrayOfBoolean)
  {
    Object localObject1 = mHorizontalDimensionBehaviour;
    Object localObject2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
    int j = 0;
    if ((localObject1 == localObject2) && (mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && (mDimensionRatio > 0.0F))
    {
      paramArrayOfBoolean[0] = false;
      return;
    }
    if ((mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && (mMatchConstraintDefaultWidth == 2))
    {
      paramArrayOfBoolean[0] = false;
      return;
    }
    int k = paramConstraintWidget.getOptimizerWrapWidth();
    if ((mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && (mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && (mDimensionRatio > 0.0F))
    {
      paramArrayOfBoolean[0] = false;
      return;
    }
    boolean bool2 = true;
    mHorizontalWrapVisited = true;
    if ((paramConstraintWidget instanceof Guideline))
    {
      localObject1 = (Guideline)paramConstraintWidget;
      if (((Guideline)localObject1).getOrientation() == 1)
      {
        if (((Guideline)localObject1).getRelativeBegin() != -1)
        {
          j = ((Guideline)localObject1).getRelativeBegin();
          i = 0;
        }
        else if (((Guideline)localObject1).getRelativeEnd() != -1)
        {
          i = ((Guideline)localObject1).getRelativeEnd();
        }
        else
        {
          if (((Guideline)localObject1).getRelativePercent() != -1.0F)
          {
            paramArrayOfBoolean[0] = false;
            return;
          }
          i = 0;
        }
      }
      else {
        j = k;
      }
    }
    for (int i = k;; i = k)
    {
      k = i;
      i = j;
      j = k;
      break label969;
      if ((mRight.isConnected()) || (mLeft.isConnected())) {
        break;
      }
      j = k + paramConstraintWidget.getX();
    }
    if ((mRight.mTarget != null) && (mLeft.mTarget != null) && (mIsWidthWrapContent) && (mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT))
    {
      paramArrayOfBoolean[0] = false;
      return;
    }
    if ((mRight.mTarget != null) && (mLeft.mTarget != null) && ((mRight.mTarget == mLeft.mTarget) || ((mRight.mTarget.mOwner == mLeft.mTarget.mOwner) && (mRight.mTarget.mOwner != mParent))))
    {
      paramArrayOfBoolean[0] = false;
      return;
    }
    localObject1 = mRight.mTarget;
    localObject2 = null;
    ConstraintWidget localConstraintWidget;
    if (localObject1 != null)
    {
      localConstraintWidget = mRight.mTarget.mOwner;
      i = mRight.getMargin() + k;
      localObject1 = localConstraintWidget;
      m = i;
      if (!localConstraintWidget.isRoot())
      {
        localObject1 = localConstraintWidget;
        m = i;
        if (!mHorizontalWrapVisited)
        {
          findHorizontalWrapRecursive(localConstraintWidget, paramArrayOfBoolean);
          localObject1 = localConstraintWidget;
          m = i;
        }
      }
    }
    else
    {
      m = k;
      localObject1 = null;
    }
    i = k;
    if (mLeft.mTarget != null)
    {
      localConstraintWidget = mLeft.mTarget.mOwner;
      j = k + mLeft.getMargin();
      i = j;
      localObject2 = localConstraintWidget;
      if (!localConstraintWidget.isRoot())
      {
        i = j;
        localObject2 = localConstraintWidget;
        if (!mHorizontalWrapVisited)
        {
          findHorizontalWrapRecursive(localConstraintWidget, paramArrayOfBoolean);
          localObject2 = localConstraintWidget;
          i = j;
        }
      }
    }
    j = m;
    boolean bool1;
    if (mRight.mTarget != null)
    {
      j = m;
      if (!((ConstraintWidget)localObject1).isRoot())
      {
        if (mRight.mTarget.mType == ConstraintAnchor.Type.RIGHT)
        {
          k = m + (mDistToRight - ((ConstraintWidget)localObject1).getOptimizerWrapWidth());
        }
        else
        {
          k = m;
          if (mRight.mTarget.getType() == ConstraintAnchor.Type.LEFT) {
            k = m + mDistToRight;
          }
        }
        if ((!mRightHasCentered) && ((mLeft.mTarget == null) || (mRight.mTarget == null) || (mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT))) {
          bool1 = false;
        } else {
          bool1 = true;
        }
        mRightHasCentered = bool1;
        j = k;
        if (mRightHasCentered) {
          if (mLeft.mTarget != null)
          {
            j = k;
            if (mLeft.mTarget.mOwner == paramConstraintWidget) {}
          }
          else
          {
            j = k + (k - mDistToRight);
          }
        }
      }
    }
    int m = i;
    if (mLeft.mTarget != null)
    {
      m = i;
      if (!((ConstraintWidget)localObject2).isRoot())
      {
        if (mLeft.mTarget.getType() == ConstraintAnchor.Type.LEFT)
        {
          k = i + (mDistToLeft - ((ConstraintWidget)localObject2).getOptimizerWrapWidth());
        }
        else
        {
          k = i;
          if (mLeft.mTarget.getType() == ConstraintAnchor.Type.RIGHT) {
            k = i + mDistToLeft;
          }
        }
        bool1 = bool2;
        if (!mLeftHasCentered) {
          if ((mLeft.mTarget != null) && (mRight.mTarget != null) && (mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)) {
            bool1 = bool2;
          } else {
            bool1 = false;
          }
        }
        mLeftHasCentered = bool1;
        m = k;
        if (mLeftHasCentered) {
          if (mRight.mTarget != null)
          {
            m = k;
            if (mRight.mTarget.mOwner == paramConstraintWidget) {}
          }
          else
          {
            i = k + (k - mDistToLeft);
            break label969;
          }
        }
      }
    }
    i = m;
    label969:
    m = i;
    k = j;
    if (paramConstraintWidget.getVisibility() == 8)
    {
      m = i - mWidth;
      k = j - mWidth;
    }
    mDistToLeft = m;
    mDistToRight = k;
  }
  
  public void findVerticalWrapRecursive(ConstraintWidget paramConstraintWidget, boolean[] paramArrayOfBoolean)
  {
    Object localObject1 = mVerticalDimensionBehaviour;
    Object localObject2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
    int j = 0;
    if ((localObject1 == localObject2) && (mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && (mDimensionRatio > 0.0F))
    {
      paramArrayOfBoolean[0] = false;
      return;
    }
    if ((mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && (mMatchConstraintDefaultHeight == 2))
    {
      paramArrayOfBoolean[0] = false;
      return;
    }
    int i = paramConstraintWidget.getOptimizerWrapHeight();
    boolean bool2 = true;
    mVerticalWrapVisited = true;
    if ((paramConstraintWidget instanceof Guideline))
    {
      localObject1 = (Guideline)paramConstraintWidget;
      if (((Guideline)localObject1).getOrientation() == 0) {
        if (((Guideline)localObject1).getRelativeBegin() != -1)
        {
          j = ((Guideline)localObject1).getRelativeBegin();
          i = 0;
        }
        else if (((Guideline)localObject1).getRelativeEnd() != -1)
        {
          i = ((Guideline)localObject1).getRelativeEnd();
        }
        else
        {
          if (((Guideline)localObject1).getRelativePercent() != -1.0F)
          {
            paramArrayOfBoolean[0] = false;
            return;
          }
          i = 0;
        }
      }
    }
    for (j = i;; j = i + paramConstraintWidget.getY())
    {
      n = j;
      break label1117;
      if ((mBaseline.mTarget != null) || (mTop.mTarget != null) || (mBottom.mTarget != null)) {
        break;
      }
    }
    if ((mBottom.mTarget != null) && (mTop.mTarget != null) && (mIsHeightWrapContent) && (mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT))
    {
      paramArrayOfBoolean[0] = false;
      return;
    }
    if ((mBottom.mTarget != null) && (mTop.mTarget != null) && ((mBottom.mTarget == mTop.mTarget) || ((mBottom.mTarget.mOwner == mTop.mTarget.mOwner) && (mBottom.mTarget.mOwner != mParent))))
    {
      paramArrayOfBoolean[0] = false;
      return;
    }
    int m;
    if (mBaseline.isConnected())
    {
      localObject1 = mBaseline.mTarget.getOwner();
      if (!mVerticalWrapVisited) {
        findVerticalWrapRecursive((ConstraintWidget)localObject1, paramArrayOfBoolean);
      }
      k = Math.max(mDistToTop - mHeight + i, i);
      m = Math.max(mDistToBottom - mHeight + i, i);
      j = m;
      i = k;
      if (paramConstraintWidget.getVisibility() == 8)
      {
        i = k - mHeight;
        j = m - mHeight;
      }
      mDistToTop = i;
      mDistToBottom = j;
      return;
    }
    boolean bool1 = mTop.isConnected();
    localObject2 = null;
    ConstraintWidget localConstraintWidget;
    if (bool1)
    {
      localConstraintWidget = mTop.mTarget.getOwner();
      j = mTop.getMargin() + i;
      localObject1 = localConstraintWidget;
      m = j;
      if (!localConstraintWidget.isRoot())
      {
        localObject1 = localConstraintWidget;
        m = j;
        if (!mVerticalWrapVisited)
        {
          findVerticalWrapRecursive(localConstraintWidget, paramArrayOfBoolean);
          localObject1 = localConstraintWidget;
          m = j;
        }
      }
    }
    else
    {
      m = i;
      localObject1 = null;
    }
    j = i;
    if (mBottom.isConnected())
    {
      localConstraintWidget = mBottom.mTarget.getOwner();
      i += mBottom.getMargin();
      j = i;
      localObject2 = localConstraintWidget;
      if (!localConstraintWidget.isRoot())
      {
        j = i;
        localObject2 = localConstraintWidget;
        if (!mVerticalWrapVisited)
        {
          findVerticalWrapRecursive(localConstraintWidget, paramArrayOfBoolean);
          localObject2 = localConstraintWidget;
          j = i;
        }
      }
    }
    int k = m;
    if (mTop.mTarget != null)
    {
      k = m;
      if (!((ConstraintWidget)localObject1).isRoot())
      {
        if (mTop.mTarget.getType() == ConstraintAnchor.Type.TOP)
        {
          i = m + (mDistToTop - ((ConstraintWidget)localObject1).getOptimizerWrapHeight());
        }
        else
        {
          i = m;
          if (mTop.mTarget.getType() == ConstraintAnchor.Type.BOTTOM) {
            i = m + mDistToTop;
          }
        }
        if ((!mTopHasCentered) && ((mTop.mTarget == null) || (mTop.mTarget.mOwner == paramConstraintWidget) || (mBottom.mTarget == null) || (mBottom.mTarget.mOwner == paramConstraintWidget) || (mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT))) {
          bool1 = false;
        } else {
          bool1 = true;
        }
        mTopHasCentered = bool1;
        k = i;
        if (mTopHasCentered) {
          if (mBottom.mTarget != null)
          {
            k = i;
            if (mBottom.mTarget.mOwner == paramConstraintWidget) {}
          }
          else
          {
            k = i + (i - mDistToTop);
          }
        }
      }
    }
    i = j;
    int n = k;
    if (mBottom.mTarget != null)
    {
      i = j;
      n = k;
      if (!((ConstraintWidget)localObject2).isRoot())
      {
        if (mBottom.mTarget.getType() == ConstraintAnchor.Type.BOTTOM)
        {
          m = j + (mDistToBottom - ((ConstraintWidget)localObject2).getOptimizerWrapHeight());
        }
        else
        {
          m = j;
          if (mBottom.mTarget.getType() == ConstraintAnchor.Type.TOP) {
            m = j + mDistToBottom;
          }
        }
        bool1 = bool2;
        if (!mBottomHasCentered) {
          if ((mTop.mTarget != null) && (mTop.mTarget.mOwner != paramConstraintWidget) && (mBottom.mTarget != null) && (mBottom.mTarget.mOwner != paramConstraintWidget) && (mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)) {
            bool1 = bool2;
          } else {
            bool1 = false;
          }
        }
        mBottomHasCentered = bool1;
        i = m;
        n = k;
        if (mBottomHasCentered) {
          if (mTop.mTarget != null)
          {
            i = m;
            n = k;
            if (mTop.mTarget.mOwner == paramConstraintWidget) {}
          }
          else
          {
            i = m + (m - mDistToBottom);
            n = k;
          }
        }
      }
    }
    label1117:
    k = i;
    j = n;
    if (paramConstraintWidget.getVisibility() == 8)
    {
      j = n - mHeight;
      k = i - mHeight;
    }
    mDistToTop = j;
    mDistToBottom = k;
  }
  
  public void findWrapSize(ArrayList<ConstraintWidget> paramArrayList, boolean[] paramArrayOfBoolean)
  {
    int i6 = paramArrayList.size();
    paramArrayOfBoolean[0] = true;
    int i5 = 0;
    int i = i5;
    int j = i;
    int k = j;
    int m = k;
    int n = m;
    int i1 = n;
    int i2 = n;
    n = m;
    m = k;
    int i3 = j;
    int i4 = i;
    k = i5;
    for (;;)
    {
      boolean[] arrayOfBoolean = paramArrayOfBoolean;
      if (k < i6) {}
      for (;;)
      {
        try
        {
          ConstraintWidget localConstraintWidget = (ConstraintWidget)paramArrayList.get(k);
          if (localConstraintWidget.isRoot())
          {
            i = i2;
            j = i1;
          }
          else
          {
            if (!mHorizontalWrapVisited) {
              findHorizontalWrapRecursive(localConstraintWidget, arrayOfBoolean);
            }
            int i8 = arrayOfBoolean[0];
            if (i8 == 0)
            {
              i = 0;
              if (i < i6)
              {
                paramArrayOfBoolean = (ConstraintWidget)paramArrayList.get(i);
                mHorizontalWrapVisited = false;
                mVerticalWrapVisited = false;
                mLeftHasCentered = false;
                mRightHasCentered = false;
                mTopHasCentered = false;
                mBottomHasCentered = false;
                i += 1;
              }
              return;
            }
            if (!mVerticalWrapVisited) {
              findVerticalWrapRecursive(localConstraintWidget, arrayOfBoolean);
            }
            i8 = arrayOfBoolean[0];
            if (i8 == 0)
            {
              i = 0;
              if (i < i6)
              {
                paramArrayOfBoolean = (ConstraintWidget)paramArrayList.get(i);
                mHorizontalWrapVisited = false;
                mVerticalWrapVisited = false;
                mLeftHasCentered = false;
                mRightHasCentered = false;
                mTopHasCentered = false;
                mBottomHasCentered = false;
                i += 1;
              }
              return;
            }
            i = mDistToLeft;
            i5 = mDistToRight;
            i7 = localConstraintWidget.getWidth();
            j = mDistToTop + mDistToBottom - localConstraintWidget.getHeight();
            if (mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT)
            {
              i = localConstraintWidget.getWidth() + mLeft.mMargin + mRight.mMargin;
              if (mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                j = localConstraintWidget.getHeight() + mTop.mMargin + mBottom.mMargin;
              }
              if (localConstraintWidget.getVisibility() == 8)
              {
                i = 0;
                j = 0;
              }
              i4 = Math.max(i4, mDistToLeft);
              i3 = Math.max(i3, mDistToRight);
              i2 = Math.max(i2, mDistToBottom);
              n = Math.max(n, mDistToTop);
              m = Math.max(m, i);
              j = Math.max(i1, j);
              i = i2;
            }
          }
        }
        finally
        {
          int i7;
          continue;
        }
        i = Math.max(i4, i3);
        mWrapWidth = Math.max(mMinWidth, Math.max(i, m));
        i = Math.max(n, i2);
        mWrapHeight = Math.max(mMinHeight, Math.max(i, i1));
        i = 0;
        if (i < i6)
        {
          paramArrayOfBoolean = (ConstraintWidget)paramArrayList.get(i);
          mHorizontalWrapVisited = false;
          mVerticalWrapVisited = false;
          mLeftHasCentered = false;
          mRightHasCentered = false;
          mTopHasCentered = false;
          mBottomHasCentered = false;
          i += 1;
        }
        else
        {
          return;
          i = 0;
          if (i < i6)
          {
            paramArrayOfBoolean = (ConstraintWidget)paramArrayList.get(i);
            mHorizontalWrapVisited = false;
            mVerticalWrapVisited = false;
            mLeftHasCentered = false;
            mRightHasCentered = false;
            mTopHasCentered = false;
            mBottomHasCentered = false;
            i += 1;
          }
          else
          {
            throw arrayOfBoolean;
            i = i + i5 - i7;
          }
        }
      }
      k += 1;
      i2 = i;
      i1 = j;
    }
  }
  
  public ArrayList<Guideline> getHorizontalGuidelines()
  {
    ArrayList localArrayList = new ArrayList();
    int j = mChildren.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = (ConstraintWidget)mChildren.get(i);
      if ((localObject instanceof Guideline))
      {
        localObject = (Guideline)localObject;
        if (((Guideline)localObject).getOrientation() == 0) {
          localArrayList.add(localObject);
        }
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public LinearSystem getSystem()
  {
    return mSystem;
  }
  
  public String getType()
  {
    return "ConstraintLayout";
  }
  
  public ArrayList<Guideline> getVerticalGuidelines()
  {
    ArrayList localArrayList = new ArrayList();
    int j = mChildren.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = (ConstraintWidget)mChildren.get(i);
      if ((localObject instanceof Guideline))
      {
        localObject = (Guideline)localObject;
        if (((Guideline)localObject).getOrientation() == 1) {
          localArrayList.add(localObject);
        }
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public boolean handlesInternalConstraints()
  {
    return false;
  }
  
  public boolean isHeightMeasuredTooSmall()
  {
    return mHeightMeasuredTooSmall;
  }
  
  public boolean isWidthMeasuredTooSmall()
  {
    return mWidthMeasuredTooSmall;
  }
  
  public void layout()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge Z and I\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public int layoutFindGroups()
  {
    Object localObject2 = new ConstraintAnchor.Type[5];
    Object localObject1 = ConstraintAnchor.Type.LEFT;
    int n = 0;
    localObject2[0] = localObject1;
    localObject2[1] = ConstraintAnchor.Type.RIGHT;
    localObject2[2] = ConstraintAnchor.Type.TOP;
    localObject2[3] = ConstraintAnchor.Type.BASELINE;
    localObject2[4] = ConstraintAnchor.Type.BOTTOM;
    int i1 = mChildren.size();
    int k = 0;
    Object localObject3;
    int j;
    for (int i = 1; k < i1; i = j)
    {
      localObject1 = (ConstraintWidget)mChildren.get(k);
      localObject3 = mLeft;
      if (mTarget != null)
      {
        j = i;
        if (setGroup((ConstraintAnchor)localObject3, i) == i) {
          j = i + 1;
        }
      }
      else
      {
        mGroup = Integer.MAX_VALUE;
        j = i;
      }
      localObject3 = mTop;
      if (mTarget != null)
      {
        i = j;
        if (setGroup((ConstraintAnchor)localObject3, j) == j) {
          i = j + 1;
        }
      }
      else
      {
        mGroup = Integer.MAX_VALUE;
        i = j;
      }
      localObject3 = mRight;
      if (mTarget != null)
      {
        j = i;
        if (setGroup((ConstraintAnchor)localObject3, i) == i) {
          j = i + 1;
        }
      }
      else
      {
        mGroup = Integer.MAX_VALUE;
        j = i;
      }
      localObject3 = mBottom;
      if (mTarget != null)
      {
        i = j;
        if (setGroup((ConstraintAnchor)localObject3, j) == j) {
          i = j + 1;
        }
      }
      else
      {
        mGroup = Integer.MAX_VALUE;
        i = j;
      }
      localObject1 = mBaseline;
      if (mTarget != null)
      {
        j = i;
        if (setGroup((ConstraintAnchor)localObject1, i) == i) {
          j = i + 1;
        }
      }
      else
      {
        mGroup = Integer.MAX_VALUE;
        j = i;
      }
      k += 1;
    }
    i = 1;
    int m;
    while (i != 0)
    {
      k = 0;
      i = k;
      while (k < i1)
      {
        localObject3 = (ConstraintWidget)mChildren.get(k);
        m = 0;
        while (m < localObject2.length)
        {
          ConstraintAnchor localConstraintAnchor = localObject2[m];
          localObject1 = null;
          switch (2.$SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[localConstraintAnchor.ordinal()])
          {
          default: 
            break;
          case 5: 
            localObject1 = mBaseline;
            break;
          case 4: 
            localObject1 = mBottom;
            break;
          case 3: 
            localObject1 = mRight;
            break;
          case 2: 
            localObject1 = mTop;
            break;
          case 1: 
            localObject1 = mLeft;
          }
          localConstraintAnchor = mTarget;
          if (localConstraintAnchor != null)
          {
            j = i;
            if (mOwner.getParent() != null)
            {
              j = i;
              if (mGroup != mGroup)
              {
                if (mGroup > mGroup) {
                  i = mGroup;
                } else {
                  i = mGroup;
                }
                mGroup = i;
                mGroup = i;
                j = 1;
              }
            }
            localConstraintAnchor = localConstraintAnchor.getOpposite();
            i = j;
            if (localConstraintAnchor != null)
            {
              i = j;
              if (mGroup != mGroup)
              {
                if (mGroup > mGroup) {
                  i = mGroup;
                } else {
                  i = mGroup;
                }
                mGroup = i;
                mGroup = i;
                i = 1;
              }
            }
          }
          m += 1;
        }
        k += 1;
      }
    }
    localObject1 = new int[mChildren.size() * localObject2.length + 1];
    Arrays.fill((int[])localObject1, -1);
    i = 0;
    k = n;
    while (k < i1)
    {
      localObject2 = (ConstraintWidget)mChildren.get(k);
      localObject3 = mLeft;
      j = i;
      if (mGroup != Integer.MAX_VALUE)
      {
        m = mGroup;
        j = i;
        if (localObject1[m] == -1)
        {
          localObject1[m] = i;
          j = i + 1;
        }
        mGroup = localObject1[m];
      }
      localObject3 = mTop;
      i = j;
      if (mGroup != Integer.MAX_VALUE)
      {
        m = mGroup;
        i = j;
        if (localObject1[m] == -1)
        {
          localObject1[m] = j;
          i = j + 1;
        }
        mGroup = localObject1[m];
      }
      localObject3 = mRight;
      j = i;
      if (mGroup != Integer.MAX_VALUE)
      {
        m = mGroup;
        j = i;
        if (localObject1[m] == -1)
        {
          localObject1[m] = i;
          j = i + 1;
        }
        mGroup = localObject1[m];
      }
      localObject3 = mBottom;
      i = j;
      if (mGroup != Integer.MAX_VALUE)
      {
        m = mGroup;
        i = j;
        if (localObject1[m] == -1)
        {
          localObject1[m] = j;
          i = j + 1;
        }
        mGroup = localObject1[m];
      }
      localObject2 = mBaseline;
      j = i;
      if (mGroup != Integer.MAX_VALUE)
      {
        m = mGroup;
        j = i;
        if (localObject1[m] == -1)
        {
          localObject1[m] = i;
          j = i + 1;
        }
        mGroup = localObject1[m];
      }
      k += 1;
      i = j;
    }
    return i;
  }
  
  public int layoutFindGroupsSimple()
  {
    int j = mChildren.size();
    int i = 0;
    while (i < j)
    {
      ConstraintWidget localConstraintWidget = (ConstraintWidget)mChildren.get(i);
      mLeft.mGroup = 0;
      mRight.mGroup = 0;
      mTop.mGroup = 1;
      mBottom.mGroup = 1;
      mBaseline.mGroup = 1;
      i += 1;
    }
    return 2;
  }
  
  public void layoutWithGroup(int paramInt)
  {
    int k = mX;
    int m = mY;
    ConstraintWidget localConstraintWidget = mParent;
    int j = 0;
    if (localConstraintWidget != null)
    {
      if (mSnapshot == null) {
        mSnapshot = new Snapshot(this);
      }
      mSnapshot.updateFrom(this);
      mX = 0;
      mY = 0;
      resetAnchors();
      resetSolverVariables(mSystem.getCache());
    }
    else
    {
      mX = 0;
      mY = 0;
    }
    int n = mChildren.size();
    int i = 0;
    while (i < n)
    {
      localConstraintWidget = (ConstraintWidget)mChildren.get(i);
      if ((localConstraintWidget instanceof WidgetContainer)) {
        ((WidgetContainer)localConstraintWidget).layout();
      }
      i += 1;
    }
    mLeft.mGroup = 0;
    mRight.mGroup = 0;
    mTop.mGroup = 1;
    mBottom.mGroup = 1;
    mSystem.reset();
    i = j;
    while (i < paramInt)
    {
      try
      {
        addToSolver(mSystem, i);
        mSystem.minimize();
        updateFromSolver(mSystem, i);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      updateFromSolver(mSystem, -2);
      i += 1;
    }
    if (mParent != null)
    {
      paramInt = getWidth();
      i = getHeight();
      mSnapshot.applyTo(this);
      setWidth(paramInt);
      setHeight(i);
    }
    else
    {
      mX = k;
      mY = m;
    }
    if (this == getRootConstraintContainer()) {
      updateDrawPosition();
    }
  }
  
  public void reset()
  {
    mSystem.reset();
    mPaddingLeft = 0;
    mPaddingRight = 0;
    mPaddingTop = 0;
    mPaddingBottom = 0;
    super.reset();
  }
  
  public void setOptimizationLevel(int paramInt)
  {
    mOptimizationLevel = paramInt;
  }
  
  public void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mPaddingLeft = paramInt1;
    mPaddingTop = paramInt2;
    mPaddingRight = paramInt3;
    mPaddingBottom = paramInt4;
  }
  
  public void updateChildrenFromSolver(LinearSystem paramLinearSystem, int paramInt, boolean[] paramArrayOfBoolean)
  {
    int i = 0;
    paramArrayOfBoolean[2] = false;
    updateFromSolver(paramLinearSystem, paramInt);
    int j = mChildren.size();
    while (i < j)
    {
      ConstraintWidget localConstraintWidget = (ConstraintWidget)mChildren.get(i);
      localConstraintWidget.updateFromSolver(paramLinearSystem, paramInt);
      if ((mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && (localConstraintWidget.getWidth() < localConstraintWidget.getWrapWidth())) {
        paramArrayOfBoolean[2] = true;
      }
      if ((mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && (localConstraintWidget.getHeight() < localConstraintWidget.getWrapHeight())) {
        paramArrayOfBoolean[2] = true;
      }
      i += 1;
    }
  }
}

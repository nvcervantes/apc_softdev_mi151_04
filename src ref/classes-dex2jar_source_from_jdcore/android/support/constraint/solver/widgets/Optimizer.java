package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;

public class Optimizer
{
  public Optimizer() {}
  
  static void applyDirectResolutionHorizontalChain(ConstraintWidgetContainer paramConstraintWidgetContainer, LinearSystem paramLinearSystem, int paramInt, ConstraintWidget paramConstraintWidget)
  {
    Object localObject1 = paramConstraintWidget;
    float f2 = 0.0F;
    int m = 0;
    int k = m;
    Object localObject3 = null;
    int i;
    int j;
    Object localObject2;
    for (;;)
    {
      int n = 1;
      if (localObject1 == null) {
        break;
      }
      if (((ConstraintWidget)localObject1).getVisibility() != 8) {
        n = 0;
      }
      i = m;
      j = k;
      f1 = f2;
      if (n == 0)
      {
        m += 1;
        if (mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
        {
          n = ((ConstraintWidget)localObject1).getWidth();
          if (mLeft.mTarget != null) {
            i = mLeft.getMargin();
          } else {
            i = 0;
          }
          if (mRight.mTarget != null) {
            j = mRight.getMargin();
          } else {
            j = 0;
          }
          j = k + n + i + j;
          i = m;
          f1 = f2;
        }
        else
        {
          f1 = f2 + mHorizontalWeight;
          j = k;
          i = m;
        }
      }
      if (mRight.mTarget != null) {
        localObject3 = mRight.mTarget.mOwner;
      } else {
        localObject3 = null;
      }
      localObject2 = localObject3;
      if (localObject3 != null) {
        if (mLeft.mTarget != null)
        {
          localObject2 = localObject3;
          if (mLeft.mTarget != null)
          {
            localObject2 = localObject3;
            if (mLeft.mTarget.mOwner == localObject1) {}
          }
        }
        else
        {
          localObject2 = null;
        }
      }
      localObject3 = localObject1;
      localObject1 = localObject2;
      m = i;
      k = j;
      f2 = f1;
    }
    if (localObject3 != null)
    {
      if (mRight.mTarget != null) {
        i = mRight.mTarget.mOwner.getX();
      } else {
        i = 0;
      }
      j = i;
      if (mRight.mTarget != null)
      {
        j = i;
        if (mRight.mTarget.mOwner == paramConstraintWidgetContainer) {
          j = paramConstraintWidgetContainer.getRight();
        }
      }
    }
    else
    {
      j = 0;
    }
    float f5 = j - 0 - k;
    float f1 = f5 / (m + 1);
    float f3;
    if (paramInt == 0) {
      f3 = f1;
    }
    for (;;)
    {
      break;
      f3 = f5 / paramInt;
      f1 = 0.0F;
    }
    while (paramConstraintWidget != null)
    {
      if (mLeft.mTarget != null) {
        i = mLeft.getMargin();
      } else {
        i = 0;
      }
      if (mRight.mTarget != null) {
        j = mRight.getMargin();
      } else {
        j = 0;
      }
      float f4;
      if (paramConstraintWidget.getVisibility() != 8)
      {
        f4 = i;
        f1 += f4;
        paramLinearSystem.addEquality(mLeft.mSolverVariable, (int)(f1 + 0.5F));
        if (mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
        {
          if (f2 == 0.0F) {
            f1 += f3 - f4 - j;
          } else {
            f1 += mHorizontalWeight * f5 / f2 - f4 - j;
          }
        }
        else {
          f1 += paramConstraintWidget.getWidth();
        }
        paramLinearSystem.addEquality(mRight.mSolverVariable, (int)(0.5F + f1));
        f4 = f1;
        if (paramInt == 0) {
          f4 = f1 + f3;
        }
        f1 = f4 + j;
      }
      else
      {
        f4 = f3 / 2.0F;
        localObject1 = mLeft.mSolverVariable;
        i = (int)(f1 - f4 + 0.5F);
        paramLinearSystem.addEquality((SolverVariable)localObject1, i);
        paramLinearSystem.addEquality(mRight.mSolverVariable, i);
      }
      if (mRight.mTarget != null) {
        localObject1 = mRight.mTarget.mOwner;
      } else {
        localObject1 = null;
      }
      localObject2 = localObject1;
      if (localObject1 != null)
      {
        localObject2 = localObject1;
        if (mLeft.mTarget != null)
        {
          localObject2 = localObject1;
          if (mLeft.mTarget.mOwner != paramConstraintWidget) {
            localObject2 = null;
          }
        }
      }
      if (localObject2 == paramConstraintWidgetContainer) {
        paramConstraintWidget = null;
      } else {
        paramConstraintWidget = localObject2;
      }
    }
  }
  
  static void applyDirectResolutionVerticalChain(ConstraintWidgetContainer paramConstraintWidgetContainer, LinearSystem paramLinearSystem, int paramInt, ConstraintWidget paramConstraintWidget)
  {
    Object localObject1 = paramConstraintWidget;
    float f2 = 0.0F;
    int m = 0;
    int k = m;
    Object localObject3 = null;
    int i;
    int j;
    Object localObject2;
    for (;;)
    {
      int n = 1;
      if (localObject1 == null) {
        break;
      }
      if (((ConstraintWidget)localObject1).getVisibility() != 8) {
        n = 0;
      }
      i = m;
      j = k;
      f1 = f2;
      if (n == 0)
      {
        m += 1;
        if (mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
        {
          n = ((ConstraintWidget)localObject1).getHeight();
          if (mTop.mTarget != null) {
            i = mTop.getMargin();
          } else {
            i = 0;
          }
          if (mBottom.mTarget != null) {
            j = mBottom.getMargin();
          } else {
            j = 0;
          }
          j = k + n + i + j;
          i = m;
          f1 = f2;
        }
        else
        {
          f1 = f2 + mVerticalWeight;
          j = k;
          i = m;
        }
      }
      if (mBottom.mTarget != null) {
        localObject3 = mBottom.mTarget.mOwner;
      } else {
        localObject3 = null;
      }
      localObject2 = localObject3;
      if (localObject3 != null) {
        if (mTop.mTarget != null)
        {
          localObject2 = localObject3;
          if (mTop.mTarget != null)
          {
            localObject2 = localObject3;
            if (mTop.mTarget.mOwner == localObject1) {}
          }
        }
        else
        {
          localObject2 = null;
        }
      }
      localObject3 = localObject1;
      localObject1 = localObject2;
      m = i;
      k = j;
      f2 = f1;
    }
    if (localObject3 != null)
    {
      if (mBottom.mTarget != null) {
        i = mBottom.mTarget.mOwner.getX();
      } else {
        i = 0;
      }
      j = i;
      if (mBottom.mTarget != null)
      {
        j = i;
        if (mBottom.mTarget.mOwner == paramConstraintWidgetContainer) {
          j = paramConstraintWidgetContainer.getBottom();
        }
      }
    }
    else
    {
      j = 0;
    }
    float f5 = j - 0 - k;
    float f1 = f5 / (m + 1);
    float f3;
    if (paramInt == 0) {
      f3 = f1;
    }
    for (;;)
    {
      break;
      f3 = f5 / paramInt;
      f1 = 0.0F;
    }
    while (paramConstraintWidget != null)
    {
      if (mTop.mTarget != null) {
        i = mTop.getMargin();
      } else {
        i = 0;
      }
      if (mBottom.mTarget != null) {
        j = mBottom.getMargin();
      } else {
        j = 0;
      }
      float f4;
      if (paramConstraintWidget.getVisibility() != 8)
      {
        f4 = i;
        f1 += f4;
        paramLinearSystem.addEquality(mTop.mSolverVariable, (int)(f1 + 0.5F));
        if (mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
        {
          if (f2 == 0.0F) {
            f1 += f3 - f4 - j;
          } else {
            f1 += mVerticalWeight * f5 / f2 - f4 - j;
          }
        }
        else {
          f1 += paramConstraintWidget.getHeight();
        }
        paramLinearSystem.addEquality(mBottom.mSolverVariable, (int)(0.5F + f1));
        f4 = f1;
        if (paramInt == 0) {
          f4 = f1 + f3;
        }
        f1 = f4 + j;
      }
      else
      {
        f4 = f3 / 2.0F;
        localObject1 = mTop.mSolverVariable;
        i = (int)(f1 - f4 + 0.5F);
        paramLinearSystem.addEquality((SolverVariable)localObject1, i);
        paramLinearSystem.addEquality(mBottom.mSolverVariable, i);
      }
      if (mBottom.mTarget != null) {
        localObject1 = mBottom.mTarget.mOwner;
      } else {
        localObject1 = null;
      }
      localObject2 = localObject1;
      if (localObject1 != null)
      {
        localObject2 = localObject1;
        if (mTop.mTarget != null)
        {
          localObject2 = localObject1;
          if (mTop.mTarget.mOwner != paramConstraintWidget) {
            localObject2 = null;
          }
        }
      }
      if (localObject2 == paramConstraintWidgetContainer) {
        paramConstraintWidget = null;
      } else {
        paramConstraintWidget = localObject2;
      }
    }
  }
  
  static void checkHorizontalSimpleDependency(ConstraintWidgetContainer paramConstraintWidgetContainer, LinearSystem paramLinearSystem, ConstraintWidget paramConstraintWidget)
  {
    if (mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
    {
      mHorizontalResolution = 1;
      return;
    }
    int i;
    int j;
    if ((mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT))
    {
      mLeft.mSolverVariable = paramLinearSystem.createObjectVariable(mLeft);
      mRight.mSolverVariable = paramLinearSystem.createObjectVariable(mRight);
      i = mLeft.mMargin;
      j = paramConstraintWidgetContainer.getWidth() - mRight.mMargin;
      paramLinearSystem.addEquality(mLeft.mSolverVariable, i);
      paramLinearSystem.addEquality(mRight.mSolverVariable, j);
      paramConstraintWidget.setHorizontalDimension(i, j);
      mHorizontalResolution = 2;
      return;
    }
    if ((mLeft.mTarget != null) && (mRight.mTarget != null))
    {
      if ((mLeft.mTarget.mOwner == paramConstraintWidgetContainer) && (mRight.mTarget.mOwner == paramConstraintWidgetContainer))
      {
        i = mLeft.getMargin();
        j = mRight.getMargin();
        if (mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
        {
          j = paramConstraintWidgetContainer.getWidth() - j;
        }
        else
        {
          int k = paramConstraintWidget.getWidth();
          i += (int)((paramConstraintWidgetContainer.getWidth() - i - j - k) * mHorizontalBiasPercent + 0.5F);
          j = paramConstraintWidget.getWidth() + i;
        }
        mLeft.mSolverVariable = paramLinearSystem.createObjectVariable(mLeft);
        mRight.mSolverVariable = paramLinearSystem.createObjectVariable(mRight);
        paramLinearSystem.addEquality(mLeft.mSolverVariable, i);
        paramLinearSystem.addEquality(mRight.mSolverVariable, j);
        mHorizontalResolution = 2;
        paramConstraintWidget.setHorizontalDimension(i, j);
        return;
      }
      mHorizontalResolution = 1;
      return;
    }
    if ((mLeft.mTarget != null) && (mLeft.mTarget.mOwner == paramConstraintWidgetContainer))
    {
      i = mLeft.getMargin();
      j = paramConstraintWidget.getWidth() + i;
      mLeft.mSolverVariable = paramLinearSystem.createObjectVariable(mLeft);
      mRight.mSolverVariable = paramLinearSystem.createObjectVariable(mRight);
      paramLinearSystem.addEquality(mLeft.mSolverVariable, i);
      paramLinearSystem.addEquality(mRight.mSolverVariable, j);
      mHorizontalResolution = 2;
      paramConstraintWidget.setHorizontalDimension(i, j);
      return;
    }
    if ((mRight.mTarget != null) && (mRight.mTarget.mOwner == paramConstraintWidgetContainer))
    {
      mLeft.mSolverVariable = paramLinearSystem.createObjectVariable(mLeft);
      mRight.mSolverVariable = paramLinearSystem.createObjectVariable(mRight);
      i = paramConstraintWidgetContainer.getWidth() - mRight.getMargin();
      j = i - paramConstraintWidget.getWidth();
      paramLinearSystem.addEquality(mLeft.mSolverVariable, j);
      paramLinearSystem.addEquality(mRight.mSolverVariable, i);
      mHorizontalResolution = 2;
      paramConstraintWidget.setHorizontalDimension(j, i);
      return;
    }
    if ((mLeft.mTarget != null) && (mLeft.mTarget.mOwner.mHorizontalResolution == 2))
    {
      paramConstraintWidgetContainer = mLeft.mTarget.mSolverVariable;
      mLeft.mSolverVariable = paramLinearSystem.createObjectVariable(mLeft);
      mRight.mSolverVariable = paramLinearSystem.createObjectVariable(mRight);
      i = (int)(computedValue + mLeft.getMargin() + 0.5F);
      j = paramConstraintWidget.getWidth() + i;
      paramLinearSystem.addEquality(mLeft.mSolverVariable, i);
      paramLinearSystem.addEquality(mRight.mSolverVariable, j);
      mHorizontalResolution = 2;
      paramConstraintWidget.setHorizontalDimension(i, j);
      return;
    }
    if ((mRight.mTarget != null) && (mRight.mTarget.mOwner.mHorizontalResolution == 2))
    {
      paramConstraintWidgetContainer = mRight.mTarget.mSolverVariable;
      mLeft.mSolverVariable = paramLinearSystem.createObjectVariable(mLeft);
      mRight.mSolverVariable = paramLinearSystem.createObjectVariable(mRight);
      i = (int)(computedValue - mRight.getMargin() + 0.5F);
      j = i - paramConstraintWidget.getWidth();
      paramLinearSystem.addEquality(mLeft.mSolverVariable, j);
      paramLinearSystem.addEquality(mRight.mSolverVariable, i);
      mHorizontalResolution = 2;
      paramConstraintWidget.setHorizontalDimension(j, i);
      return;
    }
    if (mLeft.mTarget != null) {
      i = 1;
    } else {
      i = 0;
    }
    if (mRight.mTarget != null) {
      j = 1;
    } else {
      j = 0;
    }
    if ((i == 0) && (j == 0)) {
      if ((paramConstraintWidget instanceof Guideline))
      {
        Guideline localGuideline = (Guideline)paramConstraintWidget;
        if (localGuideline.getOrientation() == 1)
        {
          mLeft.mSolverVariable = paramLinearSystem.createObjectVariable(mLeft);
          mRight.mSolverVariable = paramLinearSystem.createObjectVariable(mRight);
          float f;
          if (localGuideline.getRelativeBegin() != -1)
          {
            f = localGuideline.getRelativeBegin();
          }
          else if (localGuideline.getRelativeEnd() != -1)
          {
            f = paramConstraintWidgetContainer.getWidth() - localGuideline.getRelativeEnd();
          }
          else
          {
            f = paramConstraintWidgetContainer.getWidth();
            f = localGuideline.getRelativePercent() * f;
          }
          i = (int)(f + 0.5F);
          paramLinearSystem.addEquality(mLeft.mSolverVariable, i);
          paramLinearSystem.addEquality(mRight.mSolverVariable, i);
          mHorizontalResolution = 2;
          mVerticalResolution = 2;
          paramConstraintWidget.setHorizontalDimension(i, i);
          paramConstraintWidget.setVerticalDimension(0, paramConstraintWidgetContainer.getHeight());
        }
      }
      else
      {
        mLeft.mSolverVariable = paramLinearSystem.createObjectVariable(mLeft);
        mRight.mSolverVariable = paramLinearSystem.createObjectVariable(mRight);
        i = paramConstraintWidget.getX();
        j = paramConstraintWidget.getWidth();
        paramLinearSystem.addEquality(mLeft.mSolverVariable, i);
        paramLinearSystem.addEquality(mRight.mSolverVariable, j + i);
        mHorizontalResolution = 2;
      }
    }
  }
  
  static void checkMatchParent(ConstraintWidgetContainer paramConstraintWidgetContainer, LinearSystem paramLinearSystem, ConstraintWidget paramConstraintWidget)
  {
    int i;
    int j;
    if ((mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT))
    {
      mLeft.mSolverVariable = paramLinearSystem.createObjectVariable(mLeft);
      mRight.mSolverVariable = paramLinearSystem.createObjectVariable(mRight);
      i = mLeft.mMargin;
      j = paramConstraintWidgetContainer.getWidth() - mRight.mMargin;
      paramLinearSystem.addEquality(mLeft.mSolverVariable, i);
      paramLinearSystem.addEquality(mRight.mSolverVariable, j);
      paramConstraintWidget.setHorizontalDimension(i, j);
      mHorizontalResolution = 2;
    }
    if ((mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT))
    {
      mTop.mSolverVariable = paramLinearSystem.createObjectVariable(mTop);
      mBottom.mSolverVariable = paramLinearSystem.createObjectVariable(mBottom);
      i = mTop.mMargin;
      j = paramConstraintWidgetContainer.getHeight() - mBottom.mMargin;
      paramLinearSystem.addEquality(mTop.mSolverVariable, i);
      paramLinearSystem.addEquality(mBottom.mSolverVariable, j);
      if ((mBaselineDistance > 0) || (paramConstraintWidget.getVisibility() == 8))
      {
        mBaseline.mSolverVariable = paramLinearSystem.createObjectVariable(mBaseline);
        paramLinearSystem.addEquality(mBaseline.mSolverVariable, mBaselineDistance + i);
      }
      paramConstraintWidget.setVerticalDimension(i, j);
      mVerticalResolution = 2;
    }
  }
  
  static void checkVerticalSimpleDependency(ConstraintWidgetContainer paramConstraintWidgetContainer, LinearSystem paramLinearSystem, ConstraintWidget paramConstraintWidget)
  {
    Object localObject = mVerticalDimensionBehaviour;
    ConstraintWidget.DimensionBehaviour localDimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
    int k = 1;
    if (localObject == localDimensionBehaviour)
    {
      mVerticalResolution = 1;
      return;
    }
    int i;
    int j;
    if ((mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT))
    {
      mTop.mSolverVariable = paramLinearSystem.createObjectVariable(mTop);
      mBottom.mSolverVariable = paramLinearSystem.createObjectVariable(mBottom);
      i = mTop.mMargin;
      j = paramConstraintWidgetContainer.getHeight() - mBottom.mMargin;
      paramLinearSystem.addEquality(mTop.mSolverVariable, i);
      paramLinearSystem.addEquality(mBottom.mSolverVariable, j);
      if ((mBaselineDistance > 0) || (paramConstraintWidget.getVisibility() == 8))
      {
        mBaseline.mSolverVariable = paramLinearSystem.createObjectVariable(mBaseline);
        paramLinearSystem.addEquality(mBaseline.mSolverVariable, mBaselineDistance + i);
      }
      paramConstraintWidget.setVerticalDimension(i, j);
      mVerticalResolution = 2;
      return;
    }
    if ((mTop.mTarget != null) && (mBottom.mTarget != null))
    {
      if ((mTop.mTarget.mOwner == paramConstraintWidgetContainer) && (mBottom.mTarget.mOwner == paramConstraintWidgetContainer))
      {
        i = mTop.getMargin();
        j = mBottom.getMargin();
        if (mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
        {
          j = paramConstraintWidget.getHeight() + i;
        }
        else
        {
          k = paramConstraintWidget.getHeight();
          int m = paramConstraintWidgetContainer.getHeight();
          i = (int)(i + (m - i - j - k) * mVerticalBiasPercent + 0.5F);
          j = paramConstraintWidget.getHeight() + i;
        }
        mTop.mSolverVariable = paramLinearSystem.createObjectVariable(mTop);
        mBottom.mSolverVariable = paramLinearSystem.createObjectVariable(mBottom);
        paramLinearSystem.addEquality(mTop.mSolverVariable, i);
        paramLinearSystem.addEquality(mBottom.mSolverVariable, j);
        if ((mBaselineDistance > 0) || (paramConstraintWidget.getVisibility() == 8))
        {
          mBaseline.mSolverVariable = paramLinearSystem.createObjectVariable(mBaseline);
          paramLinearSystem.addEquality(mBaseline.mSolverVariable, mBaselineDistance + i);
        }
        mVerticalResolution = 2;
        paramConstraintWidget.setVerticalDimension(i, j);
        return;
      }
      mVerticalResolution = 1;
      return;
    }
    if ((mTop.mTarget != null) && (mTop.mTarget.mOwner == paramConstraintWidgetContainer))
    {
      i = mTop.getMargin();
      j = paramConstraintWidget.getHeight() + i;
      mTop.mSolverVariable = paramLinearSystem.createObjectVariable(mTop);
      mBottom.mSolverVariable = paramLinearSystem.createObjectVariable(mBottom);
      paramLinearSystem.addEquality(mTop.mSolverVariable, i);
      paramLinearSystem.addEquality(mBottom.mSolverVariable, j);
      if ((mBaselineDistance > 0) || (paramConstraintWidget.getVisibility() == 8))
      {
        mBaseline.mSolverVariable = paramLinearSystem.createObjectVariable(mBaseline);
        paramLinearSystem.addEquality(mBaseline.mSolverVariable, mBaselineDistance + i);
      }
      mVerticalResolution = 2;
      paramConstraintWidget.setVerticalDimension(i, j);
      return;
    }
    if ((mBottom.mTarget != null) && (mBottom.mTarget.mOwner == paramConstraintWidgetContainer))
    {
      mTop.mSolverVariable = paramLinearSystem.createObjectVariable(mTop);
      mBottom.mSolverVariable = paramLinearSystem.createObjectVariable(mBottom);
      i = paramConstraintWidgetContainer.getHeight() - mBottom.getMargin();
      j = i - paramConstraintWidget.getHeight();
      paramLinearSystem.addEquality(mTop.mSolverVariable, j);
      paramLinearSystem.addEquality(mBottom.mSolverVariable, i);
      if ((mBaselineDistance > 0) || (paramConstraintWidget.getVisibility() == 8))
      {
        mBaseline.mSolverVariable = paramLinearSystem.createObjectVariable(mBaseline);
        paramLinearSystem.addEquality(mBaseline.mSolverVariable, mBaselineDistance + j);
      }
      mVerticalResolution = 2;
      paramConstraintWidget.setVerticalDimension(j, i);
      return;
    }
    if ((mTop.mTarget != null) && (mTop.mTarget.mOwner.mVerticalResolution == 2))
    {
      paramConstraintWidgetContainer = mTop.mTarget.mSolverVariable;
      mTop.mSolverVariable = paramLinearSystem.createObjectVariable(mTop);
      mBottom.mSolverVariable = paramLinearSystem.createObjectVariable(mBottom);
      i = (int)(computedValue + mTop.getMargin() + 0.5F);
      j = paramConstraintWidget.getHeight() + i;
      paramLinearSystem.addEquality(mTop.mSolverVariable, i);
      paramLinearSystem.addEquality(mBottom.mSolverVariable, j);
      if ((mBaselineDistance > 0) || (paramConstraintWidget.getVisibility() == 8))
      {
        mBaseline.mSolverVariable = paramLinearSystem.createObjectVariable(mBaseline);
        paramLinearSystem.addEquality(mBaseline.mSolverVariable, mBaselineDistance + i);
      }
      mVerticalResolution = 2;
      paramConstraintWidget.setVerticalDimension(i, j);
      return;
    }
    if ((mBottom.mTarget != null) && (mBottom.mTarget.mOwner.mVerticalResolution == 2))
    {
      paramConstraintWidgetContainer = mBottom.mTarget.mSolverVariable;
      mTop.mSolverVariable = paramLinearSystem.createObjectVariable(mTop);
      mBottom.mSolverVariable = paramLinearSystem.createObjectVariable(mBottom);
      i = (int)(computedValue - mBottom.getMargin() + 0.5F);
      j = i - paramConstraintWidget.getHeight();
      paramLinearSystem.addEquality(mTop.mSolverVariable, j);
      paramLinearSystem.addEquality(mBottom.mSolverVariable, i);
      if ((mBaselineDistance > 0) || (paramConstraintWidget.getVisibility() == 8))
      {
        mBaseline.mSolverVariable = paramLinearSystem.createObjectVariable(mBaseline);
        paramLinearSystem.addEquality(mBaseline.mSolverVariable, mBaselineDistance + j);
      }
      mVerticalResolution = 2;
      paramConstraintWidget.setVerticalDimension(j, i);
      return;
    }
    if ((mBaseline.mTarget != null) && (mBaseline.mTarget.mOwner.mVerticalResolution == 2))
    {
      paramConstraintWidgetContainer = mBaseline.mTarget.mSolverVariable;
      mTop.mSolverVariable = paramLinearSystem.createObjectVariable(mTop);
      mBottom.mSolverVariable = paramLinearSystem.createObjectVariable(mBottom);
      i = (int)(computedValue - mBaselineDistance + 0.5F);
      j = paramConstraintWidget.getHeight() + i;
      paramLinearSystem.addEquality(mTop.mSolverVariable, i);
      paramLinearSystem.addEquality(mBottom.mSolverVariable, j);
      mBaseline.mSolverVariable = paramLinearSystem.createObjectVariable(mBaseline);
      paramLinearSystem.addEquality(mBaseline.mSolverVariable, mBaselineDistance + i);
      mVerticalResolution = 2;
      paramConstraintWidget.setVerticalDimension(i, j);
      return;
    }
    if (mBaseline.mTarget != null) {
      i = 1;
    } else {
      i = 0;
    }
    if (mTop.mTarget != null) {
      j = 1;
    } else {
      j = 0;
    }
    if (mBottom.mTarget == null) {
      k = 0;
    }
    if ((i == 0) && (j == 0) && (k == 0)) {
      if ((paramConstraintWidget instanceof Guideline))
      {
        localObject = (Guideline)paramConstraintWidget;
        if (((Guideline)localObject).getOrientation() == 0)
        {
          mTop.mSolverVariable = paramLinearSystem.createObjectVariable(mTop);
          mBottom.mSolverVariable = paramLinearSystem.createObjectVariable(mBottom);
          float f;
          if (((Guideline)localObject).getRelativeBegin() != -1)
          {
            f = ((Guideline)localObject).getRelativeBegin();
          }
          else if (((Guideline)localObject).getRelativeEnd() != -1)
          {
            f = paramConstraintWidgetContainer.getHeight() - ((Guideline)localObject).getRelativeEnd();
          }
          else
          {
            f = paramConstraintWidgetContainer.getHeight();
            f = ((Guideline)localObject).getRelativePercent() * f;
          }
          i = (int)(f + 0.5F);
          paramLinearSystem.addEquality(mTop.mSolverVariable, i);
          paramLinearSystem.addEquality(mBottom.mSolverVariable, i);
          mVerticalResolution = 2;
          mHorizontalResolution = 2;
          paramConstraintWidget.setVerticalDimension(i, i);
          paramConstraintWidget.setHorizontalDimension(0, paramConstraintWidgetContainer.getWidth());
        }
      }
      else
      {
        mTop.mSolverVariable = paramLinearSystem.createObjectVariable(mTop);
        mBottom.mSolverVariable = paramLinearSystem.createObjectVariable(mBottom);
        i = paramConstraintWidget.getY();
        j = paramConstraintWidget.getHeight();
        paramLinearSystem.addEquality(mTop.mSolverVariable, i);
        paramLinearSystem.addEquality(mBottom.mSolverVariable, j + i);
        if ((mBaselineDistance > 0) || (paramConstraintWidget.getVisibility() == 8))
        {
          mBaseline.mSolverVariable = paramLinearSystem.createObjectVariable(mBaseline);
          paramLinearSystem.addEquality(mBaseline.mSolverVariable, i + mBaselineDistance);
        }
        mVerticalResolution = 2;
      }
    }
  }
}

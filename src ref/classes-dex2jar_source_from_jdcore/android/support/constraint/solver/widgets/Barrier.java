package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;

public class Barrier
  extends Helper
{
  public static final int BOTTOM = 3;
  public static final int LEFT = 0;
  public static final int RIGHT = 1;
  public static final int TOP = 2;
  private int mBarrierType = 0;
  
  public Barrier() {}
  
  public void addToSolver(LinearSystem paramLinearSystem, int paramInt)
  {
    mListAnchors[0] = mLeft;
    mListAnchors[2] = mTop;
    mListAnchors[1] = mRight;
    mListAnchors[3] = mBottom;
    paramInt = 0;
    while (paramInt < mListAnchors.length)
    {
      mListAnchors[paramInt].mSolverVariable = paramLinearSystem.createObjectVariable(mListAnchors[paramInt]);
      paramInt += 1;
    }
    if ((mBarrierType >= 0) && (mBarrierType < 4))
    {
      ConstraintAnchor localConstraintAnchor = mListAnchors[mBarrierType];
      paramInt = 0;
      while (paramInt < mWidgetsCount)
      {
        SolverVariable localSolverVariable = paramLinearSystem.createObjectVariable(mWidgets[paramInt].mListAnchors[mBarrierType]);
        mWidgets[paramInt].mListAnchors[mBarrierType].mSolverVariable = localSolverVariable;
        if ((mBarrierType != 0) && (mBarrierType != 2)) {
          paramLinearSystem.addGreaterThan(mSolverVariable, localSolverVariable, 0, 0);
        } else {
          paramLinearSystem.addLowerThan(mSolverVariable, localSolverVariable, 0, 0);
        }
        paramInt += 1;
      }
      if (mBarrierType == 0)
      {
        paramLinearSystem.addEquality(mRight.mSolverVariable, mLeft.mSolverVariable, 0, 5);
        return;
      }
      if (mBarrierType == 1)
      {
        paramLinearSystem.addEquality(mLeft.mSolverVariable, mRight.mSolverVariable, 0, 5);
        return;
      }
      if (mBarrierType == 2)
      {
        paramLinearSystem.addEquality(mBottom.mSolverVariable, mTop.mSolverVariable, 0, 5);
        return;
      }
      if (mBarrierType == 3) {
        paramLinearSystem.addEquality(mTop.mSolverVariable, mBottom.mSolverVariable, 0, 5);
      }
      return;
    }
  }
  
  public void setBarrierType(int paramInt)
  {
    mBarrierType = paramInt;
  }
}

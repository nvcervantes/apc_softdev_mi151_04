package android.support.design.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

final class StateListAnimator
{
  private final Animator.AnimatorListener mAnimationListener = new AnimatorListenerAdapter()
  {
    public void onAnimationEnd(Animator paramAnonymousAnimator)
    {
      if (mRunningAnimator == paramAnonymousAnimator) {
        mRunningAnimator = null;
      }
    }
  };
  private Tuple mLastMatch = null;
  ValueAnimator mRunningAnimator = null;
  private final ArrayList<Tuple> mTuples = new ArrayList();
  
  StateListAnimator() {}
  
  private void cancel()
  {
    if (mRunningAnimator != null)
    {
      mRunningAnimator.cancel();
      mRunningAnimator = null;
    }
  }
  
  private void start(Tuple paramTuple)
  {
    mRunningAnimator = mAnimator;
    mRunningAnimator.start();
  }
  
  public void addState(int[] paramArrayOfInt, ValueAnimator paramValueAnimator)
  {
    paramArrayOfInt = new Tuple(paramArrayOfInt, paramValueAnimator);
    paramValueAnimator.addListener(mAnimationListener);
    mTuples.add(paramArrayOfInt);
  }
  
  public void jumpToCurrentState()
  {
    if (mRunningAnimator != null)
    {
      mRunningAnimator.end();
      mRunningAnimator = null;
    }
  }
  
  void setState(int[] paramArrayOfInt)
  {
    int j = mTuples.size();
    int i = 0;
    while (i < j)
    {
      Tuple localTuple = (Tuple)mTuples.get(i);
      if (StateSet.stateSetMatches(mSpecs, paramArrayOfInt))
      {
        paramArrayOfInt = localTuple;
        break label55;
      }
      i += 1;
    }
    paramArrayOfInt = null;
    label55:
    if (paramArrayOfInt == mLastMatch) {
      return;
    }
    if (mLastMatch != null) {
      cancel();
    }
    mLastMatch = paramArrayOfInt;
    if (paramArrayOfInt != null) {
      start(paramArrayOfInt);
    }
  }
  
  static class Tuple
  {
    final ValueAnimator mAnimator;
    final int[] mSpecs;
    
    Tuple(int[] paramArrayOfInt, ValueAnimator paramValueAnimator)
    {
      mSpecs = paramArrayOfInt;
      mAnimator = paramValueAnimator;
    }
  }
}

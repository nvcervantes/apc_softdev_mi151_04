package com.github.mikephil.charting.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.os.Build.VERSION;

public class ChartAnimator
{
  private ValueAnimator.AnimatorUpdateListener mListener;
  protected float mPhaseX = 1.0F;
  protected float mPhaseY = 1.0F;
  
  public ChartAnimator() {}
  
  public ChartAnimator(ValueAnimator.AnimatorUpdateListener paramAnimatorUpdateListener)
  {
    mListener = paramAnimatorUpdateListener;
  }
  
  public void animateX(int paramInt)
  {
    if (Build.VERSION.SDK_INT < 11) {
      return;
    }
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this, "phaseX", new float[] { 0.0F, 1.0F });
    localObjectAnimator.setDuration(paramInt);
    localObjectAnimator.addUpdateListener(mListener);
    localObjectAnimator.start();
  }
  
  public void animateX(int paramInt, Easing.EasingOption paramEasingOption)
  {
    if (Build.VERSION.SDK_INT < 11) {
      return;
    }
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this, "phaseX", new float[] { 0.0F, 1.0F });
    localObjectAnimator.setInterpolator(Easing.getEasingFunctionFromOption(paramEasingOption));
    localObjectAnimator.setDuration(paramInt);
    localObjectAnimator.addUpdateListener(mListener);
    localObjectAnimator.start();
  }
  
  public void animateX(int paramInt, EasingFunction paramEasingFunction)
  {
    if (Build.VERSION.SDK_INT < 11) {
      return;
    }
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this, "phaseX", new float[] { 0.0F, 1.0F });
    localObjectAnimator.setInterpolator(paramEasingFunction);
    localObjectAnimator.setDuration(paramInt);
    localObjectAnimator.addUpdateListener(mListener);
    localObjectAnimator.start();
  }
  
  public void animateXY(int paramInt1, int paramInt2)
  {
    if (Build.VERSION.SDK_INT < 11) {
      return;
    }
    ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(this, "phaseY", new float[] { 0.0F, 1.0F });
    localObjectAnimator1.setDuration(paramInt2);
    ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(this, "phaseX", new float[] { 0.0F, 1.0F });
    localObjectAnimator2.setDuration(paramInt1);
    if (paramInt1 > paramInt2) {
      localObjectAnimator2.addUpdateListener(mListener);
    } else {
      localObjectAnimator1.addUpdateListener(mListener);
    }
    localObjectAnimator2.start();
    localObjectAnimator1.start();
  }
  
  public void animateXY(int paramInt1, int paramInt2, Easing.EasingOption paramEasingOption1, Easing.EasingOption paramEasingOption2)
  {
    if (Build.VERSION.SDK_INT < 11) {
      return;
    }
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this, "phaseY", new float[] { 0.0F, 1.0F });
    localObjectAnimator.setInterpolator(Easing.getEasingFunctionFromOption(paramEasingOption2));
    localObjectAnimator.setDuration(paramInt2);
    paramEasingOption2 = ObjectAnimator.ofFloat(this, "phaseX", new float[] { 0.0F, 1.0F });
    paramEasingOption2.setInterpolator(Easing.getEasingFunctionFromOption(paramEasingOption1));
    paramEasingOption2.setDuration(paramInt1);
    if (paramInt1 > paramInt2) {
      paramEasingOption2.addUpdateListener(mListener);
    } else {
      localObjectAnimator.addUpdateListener(mListener);
    }
    paramEasingOption2.start();
    localObjectAnimator.start();
  }
  
  public void animateXY(int paramInt1, int paramInt2, EasingFunction paramEasingFunction1, EasingFunction paramEasingFunction2)
  {
    if (Build.VERSION.SDK_INT < 11) {
      return;
    }
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this, "phaseY", new float[] { 0.0F, 1.0F });
    localObjectAnimator.setInterpolator(paramEasingFunction2);
    localObjectAnimator.setDuration(paramInt2);
    paramEasingFunction2 = ObjectAnimator.ofFloat(this, "phaseX", new float[] { 0.0F, 1.0F });
    paramEasingFunction2.setInterpolator(paramEasingFunction1);
    paramEasingFunction2.setDuration(paramInt1);
    if (paramInt1 > paramInt2) {
      paramEasingFunction2.addUpdateListener(mListener);
    } else {
      localObjectAnimator.addUpdateListener(mListener);
    }
    paramEasingFunction2.start();
    localObjectAnimator.start();
  }
  
  public void animateY(int paramInt)
  {
    if (Build.VERSION.SDK_INT < 11) {
      return;
    }
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this, "phaseY", new float[] { 0.0F, 1.0F });
    localObjectAnimator.setDuration(paramInt);
    localObjectAnimator.addUpdateListener(mListener);
    localObjectAnimator.start();
  }
  
  public void animateY(int paramInt, Easing.EasingOption paramEasingOption)
  {
    if (Build.VERSION.SDK_INT < 11) {
      return;
    }
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this, "phaseY", new float[] { 0.0F, 1.0F });
    localObjectAnimator.setInterpolator(Easing.getEasingFunctionFromOption(paramEasingOption));
    localObjectAnimator.setDuration(paramInt);
    localObjectAnimator.addUpdateListener(mListener);
    localObjectAnimator.start();
  }
  
  public void animateY(int paramInt, EasingFunction paramEasingFunction)
  {
    if (Build.VERSION.SDK_INT < 11) {
      return;
    }
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this, "phaseY", new float[] { 0.0F, 1.0F });
    localObjectAnimator.setInterpolator(paramEasingFunction);
    localObjectAnimator.setDuration(paramInt);
    localObjectAnimator.addUpdateListener(mListener);
    localObjectAnimator.start();
  }
  
  public float getPhaseX()
  {
    return mPhaseX;
  }
  
  public float getPhaseY()
  {
    return mPhaseY;
  }
  
  public void setPhaseX(float paramFloat)
  {
    mPhaseX = paramFloat;
  }
  
  public void setPhaseY(float paramFloat)
  {
    mPhaseY = paramFloat;
  }
}

package com.github.mikephil.charting.jobs;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.view.View;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

@SuppressLint({"NewApi"})
public abstract class AnimatedViewPortJob
  extends ViewPortJob
  implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener
{
  protected ObjectAnimator animator;
  protected float phase;
  protected float xOrigin;
  protected float yOrigin;
  
  public AnimatedViewPortJob(ViewPortHandler paramViewPortHandler, float paramFloat1, float paramFloat2, Transformer paramTransformer, View paramView, float paramFloat3, float paramFloat4, long paramLong)
  {
    super(paramViewPortHandler, paramFloat1, paramFloat2, paramTransformer, paramView);
    xOrigin = paramFloat3;
    yOrigin = paramFloat4;
    animator = ObjectAnimator.ofFloat(this, "phase", new float[] { 0.0F, 1.0F });
    animator.setDuration(paramLong);
    animator.addUpdateListener(this);
    animator.addListener(this);
  }
  
  public float getPhase()
  {
    return phase;
  }
  
  public float getXOrigin()
  {
    return xOrigin;
  }
  
  public float getYOrigin()
  {
    return yOrigin;
  }
  
  public void onAnimationCancel(Animator paramAnimator)
  {
    try
    {
      recycleSelf();
      return;
    }
    catch (IllegalArgumentException paramAnimator) {}
  }
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    try
    {
      recycleSelf();
      return;
    }
    catch (IllegalArgumentException paramAnimator) {}
  }
  
  public void onAnimationRepeat(Animator paramAnimator) {}
  
  public void onAnimationStart(Animator paramAnimator) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator) {}
  
  public abstract void recycleSelf();
  
  protected void resetAnimator()
  {
    animator.removeAllListeners();
    animator.removeAllUpdateListeners();
    animator.reverse();
    animator.addUpdateListener(this);
    animator.addListener(this);
  }
  
  @SuppressLint({"NewApi"})
  public void run()
  {
    animator.start();
  }
  
  public void setPhase(float paramFloat)
  {
    phase = paramFloat;
  }
}

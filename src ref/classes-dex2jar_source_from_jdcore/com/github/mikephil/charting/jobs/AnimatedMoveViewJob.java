package com.github.mikephil.charting.jobs;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.View;
import com.github.mikephil.charting.utils.ObjectPool;
import com.github.mikephil.charting.utils.ObjectPool.Poolable;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

@SuppressLint({"NewApi"})
public class AnimatedMoveViewJob
  extends AnimatedViewPortJob
{
  private static ObjectPool<AnimatedMoveViewJob> pool = ObjectPool.create(4, new AnimatedMoveViewJob(null, 0.0F, 0.0F, null, null, 0.0F, 0.0F, 0L));
  
  static
  {
    pool.setReplenishPercentage(0.5F);
  }
  
  public AnimatedMoveViewJob(ViewPortHandler paramViewPortHandler, float paramFloat1, float paramFloat2, Transformer paramTransformer, View paramView, float paramFloat3, float paramFloat4, long paramLong)
  {
    super(paramViewPortHandler, paramFloat1, paramFloat2, paramTransformer, paramView, paramFloat3, paramFloat4, paramLong);
  }
  
  public static AnimatedMoveViewJob getInstance(ViewPortHandler paramViewPortHandler, float paramFloat1, float paramFloat2, Transformer paramTransformer, View paramView, float paramFloat3, float paramFloat4, long paramLong)
  {
    AnimatedMoveViewJob localAnimatedMoveViewJob = (AnimatedMoveViewJob)pool.get();
    mViewPortHandler = paramViewPortHandler;
    xValue = paramFloat1;
    yValue = paramFloat2;
    mTrans = paramTransformer;
    view = paramView;
    xOrigin = paramFloat3;
    yOrigin = paramFloat4;
    animator.setDuration(paramLong);
    return localAnimatedMoveViewJob;
  }
  
  public static void recycleInstance(AnimatedMoveViewJob paramAnimatedMoveViewJob)
  {
    pool.recycle(paramAnimatedMoveViewJob);
  }
  
  protected ObjectPool.Poolable instantiate()
  {
    return new AnimatedMoveViewJob(null, 0.0F, 0.0F, null, null, 0.0F, 0.0F, 0L);
  }
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    pts[0] = (xOrigin + (xValue - xOrigin) * phase);
    pts[1] = (yOrigin + (yValue - yOrigin) * phase);
    mTrans.pointValuesToPixel(pts);
    mViewPortHandler.centerViewPort(pts, view);
  }
  
  public void recycleSelf()
  {
    recycleInstance(this);
  }
}

package com.github.mikephil.charting.jobs;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.view.View;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.ObjectPool;
import com.github.mikephil.charting.utils.ObjectPool.Poolable;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

@SuppressLint({"NewApi"})
public class AnimatedZoomJob
  extends AnimatedViewPortJob
  implements Animator.AnimatorListener
{
  private static ObjectPool<AnimatedZoomJob> pool = ObjectPool.create(8, new AnimatedZoomJob(null, null, null, null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0L));
  protected Matrix mOnAnimationUpdateMatrixBuffer = new Matrix();
  protected float xAxisRange;
  protected YAxis yAxis;
  protected float zoomCenterX;
  protected float zoomCenterY;
  protected float zoomOriginX;
  protected float zoomOriginY;
  
  @SuppressLint({"NewApi"})
  public AnimatedZoomJob(ViewPortHandler paramViewPortHandler, View paramView, Transformer paramTransformer, YAxis paramYAxis, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, long paramLong)
  {
    super(paramViewPortHandler, paramFloat2, paramFloat3, paramTransformer, paramView, paramFloat4, paramFloat5, paramLong);
    zoomCenterX = paramFloat6;
    zoomCenterY = paramFloat7;
    zoomOriginX = paramFloat8;
    zoomOriginY = paramFloat9;
    animator.addListener(this);
    yAxis = paramYAxis;
    xAxisRange = paramFloat1;
  }
  
  public static AnimatedZoomJob getInstance(ViewPortHandler paramViewPortHandler, View paramView, Transformer paramTransformer, YAxis paramYAxis, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, long paramLong)
  {
    paramYAxis = (AnimatedZoomJob)pool.get();
    mViewPortHandler = paramViewPortHandler;
    xValue = paramFloat2;
    yValue = paramFloat3;
    mTrans = paramTransformer;
    view = paramView;
    xOrigin = paramFloat4;
    yOrigin = paramFloat5;
    paramYAxis.resetAnimator();
    animator.setDuration(paramLong);
    return paramYAxis;
  }
  
  protected ObjectPool.Poolable instantiate()
  {
    return new AnimatedZoomJob(null, null, null, null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0L);
  }
  
  public void onAnimationCancel(Animator paramAnimator) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    ((BarLineChartBase)view).calculateOffsets();
    view.postInvalidate();
  }
  
  public void onAnimationRepeat(Animator paramAnimator) {}
  
  public void onAnimationStart(Animator paramAnimator) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    float f1 = xOrigin;
    float f2 = xValue;
    float f3 = xOrigin;
    float f4 = phase;
    float f5 = yOrigin;
    float f6 = yValue;
    float f7 = yOrigin;
    float f8 = phase;
    paramValueAnimator = mOnAnimationUpdateMatrixBuffer;
    mViewPortHandler.setZoom(f1 + (f2 - f3) * f4, f5 + (f6 - f7) * f8, paramValueAnimator);
    mViewPortHandler.refresh(paramValueAnimator, view, false);
    f1 = yAxis.mAxisRange / mViewPortHandler.getScaleY();
    f2 = xAxisRange / mViewPortHandler.getScaleX();
    pts[0] = (zoomOriginX + (zoomCenterX - f2 / 2.0F - zoomOriginX) * phase);
    pts[1] = (zoomOriginY + (zoomCenterY + f1 / 2.0F - zoomOriginY) * phase);
    mTrans.pointValuesToPixel(pts);
    mViewPortHandler.translate(pts, paramValueAnimator);
    mViewPortHandler.refresh(paramValueAnimator, view, true);
  }
  
  public void recycleSelf() {}
}

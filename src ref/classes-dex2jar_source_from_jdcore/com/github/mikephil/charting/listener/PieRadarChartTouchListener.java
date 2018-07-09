package com.github.mikephil.charting.listener;

import android.annotation.SuppressLint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.github.mikephil.charting.charts.PieRadarChartBase;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;

public class PieRadarChartTouchListener
  extends ChartTouchListener<PieRadarChartBase<?>>
{
  private ArrayList<AngularVelocitySample> _velocitySamples = new ArrayList();
  private float mDecelerationAngularVelocity = 0.0F;
  private long mDecelerationLastTime = 0L;
  private float mStartAngle = 0.0F;
  private MPPointF mTouchStartPoint = MPPointF.getInstance(0.0F, 0.0F);
  
  public PieRadarChartTouchListener(PieRadarChartBase<?> paramPieRadarChartBase)
  {
    super(paramPieRadarChartBase);
  }
  
  private float calculateVelocity()
  {
    if (_velocitySamples.isEmpty()) {
      return 0.0F;
    }
    Object localObject = _velocitySamples;
    int j = 0;
    AngularVelocitySample localAngularVelocitySample1 = (AngularVelocitySample)((ArrayList)localObject).get(0);
    AngularVelocitySample localAngularVelocitySample2 = (AngularVelocitySample)_velocitySamples.get(_velocitySamples.size() - 1);
    int i = _velocitySamples.size() - 1;
    localObject = localAngularVelocitySample1;
    while (i >= 0)
    {
      localObject = (AngularVelocitySample)_velocitySamples.get(i);
      if (angle != angle) {
        break;
      }
      i -= 1;
    }
    float f2 = (float)(time - time) / 1000.0F;
    float f1 = f2;
    if (f2 == 0.0F) {
      f1 = 0.1F;
    }
    i = j;
    if (angle >= angle) {
      i = 1;
    }
    j = i;
    if (Math.abs(angle - angle) > 270.0D) {
      j = i ^ 0x1;
    }
    if (angle - angle > 180.0D) {
      angle = ((float)(angle + 360.0D));
    } else if (angle - angle > 180.0D) {
      angle = ((float)(angle + 360.0D));
    }
    f2 = Math.abs((angle - angle) / f1);
    f1 = f2;
    if (j == 0) {
      f1 = -f2;
    }
    return f1;
  }
  
  private void resetVelocity()
  {
    _velocitySamples.clear();
  }
  
  private void sampleVelocity(float paramFloat1, float paramFloat2)
  {
    long l = AnimationUtils.currentAnimationTimeMillis();
    _velocitySamples.add(new AngularVelocitySample(l, ((PieRadarChartBase)mChart).getAngleForPoint(paramFloat1, paramFloat2)));
    int i = _velocitySamples.size();
    while ((i - 2 > 0) && (l - _velocitySamples.get(0)).time > 1000L))
    {
      _velocitySamples.remove(0);
      i -= 1;
    }
  }
  
  public void computeScroll()
  {
    if (mDecelerationAngularVelocity == 0.0F) {
      return;
    }
    long l = AnimationUtils.currentAnimationTimeMillis();
    mDecelerationAngularVelocity *= ((PieRadarChartBase)mChart).getDragDecelerationFrictionCoef();
    float f = (float)(l - mDecelerationLastTime) / 1000.0F;
    ((PieRadarChartBase)mChart).setRotationAngle(((PieRadarChartBase)mChart).getRotationAngle() + mDecelerationAngularVelocity * f);
    mDecelerationLastTime = l;
    if (Math.abs(mDecelerationAngularVelocity) >= 0.001D)
    {
      Utils.postInvalidateOnAnimation(mChart);
      return;
    }
    stopDeceleration();
  }
  
  public void onLongPress(MotionEvent paramMotionEvent)
  {
    mLastGesture = ChartTouchListener.ChartGesture.LONG_PRESS;
    OnChartGestureListener localOnChartGestureListener = ((PieRadarChartBase)mChart).getOnChartGestureListener();
    if (localOnChartGestureListener != null) {
      localOnChartGestureListener.onChartLongPressed(paramMotionEvent);
    }
  }
  
  public boolean onSingleTapConfirmed(MotionEvent paramMotionEvent)
  {
    return true;
  }
  
  public boolean onSingleTapUp(MotionEvent paramMotionEvent)
  {
    mLastGesture = ChartTouchListener.ChartGesture.SINGLE_TAP;
    OnChartGestureListener localOnChartGestureListener = ((PieRadarChartBase)mChart).getOnChartGestureListener();
    if (localOnChartGestureListener != null) {
      localOnChartGestureListener.onChartSingleTapped(paramMotionEvent);
    }
    if (!((PieRadarChartBase)mChart).isHighlightPerTapEnabled()) {
      return false;
    }
    performHighlight(((PieRadarChartBase)mChart).getHighlightByTouchPoint(paramMotionEvent.getX(), paramMotionEvent.getY()), paramMotionEvent);
    return true;
  }
  
  @SuppressLint({"ClickableViewAccessibility"})
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (mGestureDetector.onTouchEvent(paramMotionEvent)) {
      return true;
    }
    if (((PieRadarChartBase)mChart).isRotationEnabled())
    {
      float f1 = paramMotionEvent.getX();
      float f2 = paramMotionEvent.getY();
      switch (paramMotionEvent.getAction())
      {
      default: 
        return true;
      case 2: 
        if (((PieRadarChartBase)mChart).isDragDecelerationEnabled()) {
          sampleVelocity(f1, f2);
        }
        if ((mTouchMode == 0) && (distance(f1, mTouchStartPoint.x, f2, mTouchStartPoint.y) > Utils.convertDpToPixel(8.0F)))
        {
          mLastGesture = ChartTouchListener.ChartGesture.ROTATE;
          mTouchMode = 6;
          ((PieRadarChartBase)mChart).disableScroll();
        }
        else if (mTouchMode == 6)
        {
          updateGestureRotation(f1, f2);
          ((PieRadarChartBase)mChart).invalidate();
        }
        endAction(paramMotionEvent);
        return true;
      case 1: 
        if (((PieRadarChartBase)mChart).isDragDecelerationEnabled())
        {
          stopDeceleration();
          sampleVelocity(f1, f2);
          mDecelerationAngularVelocity = calculateVelocity();
          if (mDecelerationAngularVelocity != 0.0F)
          {
            mDecelerationLastTime = AnimationUtils.currentAnimationTimeMillis();
            Utils.postInvalidateOnAnimation(mChart);
          }
        }
        ((PieRadarChartBase)mChart).enableScroll();
        mTouchMode = 0;
        endAction(paramMotionEvent);
        return true;
      }
      startAction(paramMotionEvent);
      stopDeceleration();
      resetVelocity();
      if (((PieRadarChartBase)mChart).isDragDecelerationEnabled()) {
        sampleVelocity(f1, f2);
      }
      setGestureStartAngle(f1, f2);
      mTouchStartPoint.x = f1;
      mTouchStartPoint.y = f2;
    }
    return true;
  }
  
  public void setGestureStartAngle(float paramFloat1, float paramFloat2)
  {
    mStartAngle = (((PieRadarChartBase)mChart).getAngleForPoint(paramFloat1, paramFloat2) - ((PieRadarChartBase)mChart).getRawRotationAngle());
  }
  
  public void stopDeceleration()
  {
    mDecelerationAngularVelocity = 0.0F;
  }
  
  public void updateGestureRotation(float paramFloat1, float paramFloat2)
  {
    ((PieRadarChartBase)mChart).setRotationAngle(((PieRadarChartBase)mChart).getAngleForPoint(paramFloat1, paramFloat2) - mStartAngle);
  }
  
  private class AngularVelocitySample
  {
    public float angle;
    public long time;
    
    public AngularVelocitySample(long paramLong, float paramFloat)
    {
      time = paramLong;
      angle = paramFloat;
    }
  }
}

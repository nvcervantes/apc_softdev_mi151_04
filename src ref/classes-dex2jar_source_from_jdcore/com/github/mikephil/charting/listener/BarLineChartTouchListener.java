package com.github.mikephil.charting.listener;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class BarLineChartTouchListener
  extends ChartTouchListener<BarLineChartBase<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>>>
{
  private IDataSet mClosestDataSetToTouch;
  private MPPointF mDecelerationCurrentPoint = MPPointF.getInstance(0.0F, 0.0F);
  private long mDecelerationLastTime = 0L;
  private MPPointF mDecelerationVelocity = MPPointF.getInstance(0.0F, 0.0F);
  private float mDragTriggerDist;
  private Matrix mMatrix = new Matrix();
  private float mMinScalePointerDistance;
  private float mSavedDist = 1.0F;
  private Matrix mSavedMatrix = new Matrix();
  private float mSavedXDist = 1.0F;
  private float mSavedYDist = 1.0F;
  private MPPointF mTouchPointCenter = MPPointF.getInstance(0.0F, 0.0F);
  private MPPointF mTouchStartPoint = MPPointF.getInstance(0.0F, 0.0F);
  private VelocityTracker mVelocityTracker;
  
  public BarLineChartTouchListener(BarLineChartBase<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>> paramBarLineChartBase, Matrix paramMatrix, float paramFloat)
  {
    super(paramBarLineChartBase);
    mMatrix = paramMatrix;
    mDragTriggerDist = Utils.convertDpToPixel(paramFloat);
    mMinScalePointerDistance = Utils.convertDpToPixel(3.5F);
  }
  
  private static float getXDist(MotionEvent paramMotionEvent)
  {
    return Math.abs(paramMotionEvent.getX(0) - paramMotionEvent.getX(1));
  }
  
  private static float getYDist(MotionEvent paramMotionEvent)
  {
    return Math.abs(paramMotionEvent.getY(0) - paramMotionEvent.getY(1));
  }
  
  private boolean inverted()
  {
    return ((mClosestDataSetToTouch == null) && (((BarLineChartBase)mChart).isAnyAxisInverted())) || ((mClosestDataSetToTouch != null) && (((BarLineChartBase)mChart).isInverted(mClosestDataSetToTouch.getAxisDependency())));
  }
  
  private static void midPoint(MPPointF paramMPPointF, MotionEvent paramMotionEvent)
  {
    float f1 = paramMotionEvent.getX(0);
    float f2 = paramMotionEvent.getX(1);
    float f3 = paramMotionEvent.getY(0);
    float f4 = paramMotionEvent.getY(1);
    x = ((f1 + f2) / 2.0F);
    y = ((f3 + f4) / 2.0F);
  }
  
  private void performDrag(MotionEvent paramMotionEvent, float paramFloat1, float paramFloat2)
  {
    mLastGesture = ChartTouchListener.ChartGesture.DRAG;
    mMatrix.set(mSavedMatrix);
    OnChartGestureListener localOnChartGestureListener = ((BarLineChartBase)mChart).getOnChartGestureListener();
    float f1 = paramFloat1;
    float f2 = paramFloat2;
    if (inverted()) {
      if ((mChart instanceof HorizontalBarChart))
      {
        f1 = -paramFloat1;
        f2 = paramFloat2;
      }
      else
      {
        f2 = -paramFloat2;
        f1 = paramFloat1;
      }
    }
    mMatrix.postTranslate(f1, f2);
    if (localOnChartGestureListener != null) {
      localOnChartGestureListener.onChartTranslate(paramMotionEvent, f1, f2);
    }
  }
  
  private void performHighlightDrag(MotionEvent paramMotionEvent)
  {
    paramMotionEvent = ((BarLineChartBase)mChart).getHighlightByTouchPoint(paramMotionEvent.getX(), paramMotionEvent.getY());
    if ((paramMotionEvent != null) && (!paramMotionEvent.equalTo(mLastHighlighted)))
    {
      mLastHighlighted = paramMotionEvent;
      ((BarLineChartBase)mChart).highlightValue(paramMotionEvent, true);
    }
  }
  
  private void performZoom(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getPointerCount() >= 2)
    {
      OnChartGestureListener localOnChartGestureListener = ((BarLineChartBase)mChart).getOnChartGestureListener();
      float f1 = spacing(paramMotionEvent);
      if (f1 > mMinScalePointerDistance)
      {
        MPPointF localMPPointF = getTrans(mTouchPointCenter.x, mTouchPointCenter.y);
        ViewPortHandler localViewPortHandler = ((BarLineChartBase)mChart).getViewPortHandler();
        int m = mTouchMode;
        int j = 0;
        int k = 0;
        int i = 0;
        boolean bool1;
        if (m == 4)
        {
          mLastGesture = ChartTouchListener.ChartGesture.PINCH_ZOOM;
          f1 /= mSavedDist;
          if (f1 < 1.0F) {
            i = 1;
          }
          if (i != 0) {
            bool1 = localViewPortHandler.canZoomOutMoreX();
          } else {
            bool1 = localViewPortHandler.canZoomInMoreX();
          }
          boolean bool2;
          if (i != 0) {
            bool2 = localViewPortHandler.canZoomOutMoreY();
          } else {
            bool2 = localViewPortHandler.canZoomInMoreY();
          }
          float f2;
          if (((BarLineChartBase)mChart).isScaleXEnabled()) {
            f2 = f1;
          } else {
            f2 = 1.0F;
          }
          if (!((BarLineChartBase)mChart).isScaleYEnabled()) {
            f1 = 1.0F;
          }
          if ((bool2) || (bool1))
          {
            mMatrix.set(mSavedMatrix);
            mMatrix.postScale(f2, f1, x, y);
            if (localOnChartGestureListener != null) {
              localOnChartGestureListener.onChartScale(paramMotionEvent, f2, f1);
            }
          }
        }
        else if ((mTouchMode == 2) && (((BarLineChartBase)mChart).isScaleXEnabled()))
        {
          mLastGesture = ChartTouchListener.ChartGesture.X_ZOOM;
          f1 = getXDist(paramMotionEvent) / mSavedXDist;
          i = j;
          if (f1 < 1.0F) {
            i = 1;
          }
          if (i != 0) {
            bool1 = localViewPortHandler.canZoomOutMoreX();
          } else {
            bool1 = localViewPortHandler.canZoomInMoreX();
          }
          if (bool1)
          {
            mMatrix.set(mSavedMatrix);
            mMatrix.postScale(f1, 1.0F, x, y);
            if (localOnChartGestureListener != null) {
              localOnChartGestureListener.onChartScale(paramMotionEvent, f1, 1.0F);
            }
          }
        }
        else if ((mTouchMode == 3) && (((BarLineChartBase)mChart).isScaleYEnabled()))
        {
          mLastGesture = ChartTouchListener.ChartGesture.Y_ZOOM;
          f1 = getYDist(paramMotionEvent) / mSavedYDist;
          i = k;
          if (f1 < 1.0F) {
            i = 1;
          }
          if (i != 0) {
            bool1 = localViewPortHandler.canZoomOutMoreY();
          } else {
            bool1 = localViewPortHandler.canZoomInMoreY();
          }
          if (bool1)
          {
            mMatrix.set(mSavedMatrix);
            mMatrix.postScale(1.0F, f1, x, y);
            if (localOnChartGestureListener != null) {
              localOnChartGestureListener.onChartScale(paramMotionEvent, 1.0F, f1);
            }
          }
        }
        MPPointF.recycleInstance(localMPPointF);
      }
    }
  }
  
  private void saveTouchStart(MotionEvent paramMotionEvent)
  {
    mSavedMatrix.set(mMatrix);
    mTouchStartPoint.x = paramMotionEvent.getX();
    mTouchStartPoint.y = paramMotionEvent.getY();
    mClosestDataSetToTouch = ((BarLineChartBase)mChart).getDataSetByTouchPoint(paramMotionEvent.getX(), paramMotionEvent.getY());
  }
  
  private static float spacing(MotionEvent paramMotionEvent)
  {
    float f1 = paramMotionEvent.getX(0) - paramMotionEvent.getX(1);
    float f2 = paramMotionEvent.getY(0) - paramMotionEvent.getY(1);
    return (float)Math.sqrt(f1 * f1 + f2 * f2);
  }
  
  public void computeScroll()
  {
    float f1 = mDecelerationVelocity.x;
    float f2 = 0.0F;
    if ((f1 == 0.0F) && (mDecelerationVelocity.y == 0.0F)) {
      return;
    }
    long l = AnimationUtils.currentAnimationTimeMillis();
    Object localObject = mDecelerationVelocity;
    x *= ((BarLineChartBase)mChart).getDragDecelerationFrictionCoef();
    localObject = mDecelerationVelocity;
    y *= ((BarLineChartBase)mChart).getDragDecelerationFrictionCoef();
    f1 = (float)(l - mDecelerationLastTime) / 1000.0F;
    float f3 = mDecelerationVelocity.x;
    float f4 = mDecelerationVelocity.y;
    localObject = mDecelerationCurrentPoint;
    x += f3 * f1;
    localObject = mDecelerationCurrentPoint;
    y += f4 * f1;
    localObject = MotionEvent.obtain(l, l, 2, mDecelerationCurrentPoint.x, mDecelerationCurrentPoint.y, 0);
    if (((BarLineChartBase)mChart).isDragXEnabled()) {
      f1 = mDecelerationCurrentPoint.x - mTouchStartPoint.x;
    } else {
      f1 = 0.0F;
    }
    if (((BarLineChartBase)mChart).isDragYEnabled()) {
      f2 = mDecelerationCurrentPoint.y - mTouchStartPoint.y;
    }
    performDrag((MotionEvent)localObject, f1, f2);
    ((MotionEvent)localObject).recycle();
    mMatrix = ((BarLineChartBase)mChart).getViewPortHandler().refresh(mMatrix, mChart, false);
    mDecelerationLastTime = l;
    if ((Math.abs(mDecelerationVelocity.x) < 0.01D) && (Math.abs(mDecelerationVelocity.y) < 0.01D))
    {
      ((BarLineChartBase)mChart).calculateOffsets();
      ((BarLineChartBase)mChart).postInvalidate();
      stopDeceleration();
      return;
    }
    Utils.postInvalidateOnAnimation(mChart);
  }
  
  public Matrix getMatrix()
  {
    return mMatrix;
  }
  
  public MPPointF getTrans(float paramFloat1, float paramFloat2)
  {
    ViewPortHandler localViewPortHandler = ((BarLineChartBase)mChart).getViewPortHandler();
    float f = localViewPortHandler.offsetLeft();
    if (inverted()) {
      paramFloat2 = -(paramFloat2 - localViewPortHandler.offsetTop());
    } else {
      paramFloat2 = -(((BarLineChartBase)mChart).getMeasuredHeight() - paramFloat2 - localViewPortHandler.offsetBottom());
    }
    return MPPointF.getInstance(paramFloat1 - f, paramFloat2);
  }
  
  public boolean onDoubleTap(MotionEvent paramMotionEvent)
  {
    mLastGesture = ChartTouchListener.ChartGesture.DOUBLE_TAP;
    Object localObject1 = ((BarLineChartBase)mChart).getOnChartGestureListener();
    if (localObject1 != null) {
      ((OnChartGestureListener)localObject1).onChartDoubleTapped(paramMotionEvent);
    }
    if ((((BarLineChartBase)mChart).isDoubleTapToZoomEnabled()) && (((BarLineScatterCandleBubbleData)((BarLineChartBase)mChart).getData()).getEntryCount() > 0))
    {
      localObject1 = getTrans(paramMotionEvent.getX(), paramMotionEvent.getY());
      Object localObject2 = (BarLineChartBase)mChart;
      boolean bool = ((BarLineChartBase)mChart).isScaleXEnabled();
      float f2 = 1.0F;
      float f1;
      if (bool) {
        f1 = 1.4F;
      } else {
        f1 = 1.0F;
      }
      if (((BarLineChartBase)mChart).isScaleYEnabled()) {
        f2 = 1.4F;
      }
      ((BarLineChartBase)localObject2).zoom(f1, f2, x, y);
      if (((BarLineChartBase)mChart).isLogEnabled())
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Double-Tap, Zooming In, x: ");
        ((StringBuilder)localObject2).append(x);
        ((StringBuilder)localObject2).append(", y: ");
        ((StringBuilder)localObject2).append(y);
        Log.i("BarlineChartTouch", ((StringBuilder)localObject2).toString());
      }
      MPPointF.recycleInstance((MPPointF)localObject1);
    }
    return super.onDoubleTap(paramMotionEvent);
  }
  
  public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    mLastGesture = ChartTouchListener.ChartGesture.FLING;
    OnChartGestureListener localOnChartGestureListener = ((BarLineChartBase)mChart).getOnChartGestureListener();
    if (localOnChartGestureListener != null) {
      localOnChartGestureListener.onChartFling(paramMotionEvent1, paramMotionEvent2, paramFloat1, paramFloat2);
    }
    return super.onFling(paramMotionEvent1, paramMotionEvent2, paramFloat1, paramFloat2);
  }
  
  public void onLongPress(MotionEvent paramMotionEvent)
  {
    mLastGesture = ChartTouchListener.ChartGesture.LONG_PRESS;
    OnChartGestureListener localOnChartGestureListener = ((BarLineChartBase)mChart).getOnChartGestureListener();
    if (localOnChartGestureListener != null) {
      localOnChartGestureListener.onChartLongPressed(paramMotionEvent);
    }
  }
  
  public boolean onSingleTapUp(MotionEvent paramMotionEvent)
  {
    mLastGesture = ChartTouchListener.ChartGesture.SINGLE_TAP;
    OnChartGestureListener localOnChartGestureListener = ((BarLineChartBase)mChart).getOnChartGestureListener();
    if (localOnChartGestureListener != null) {
      localOnChartGestureListener.onChartSingleTapped(paramMotionEvent);
    }
    if (!((BarLineChartBase)mChart).isHighlightPerTapEnabled()) {
      return false;
    }
    performHighlight(((BarLineChartBase)mChart).getHighlightByTouchPoint(paramMotionEvent.getX(), paramMotionEvent.getY()), paramMotionEvent);
    return super.onSingleTapUp(paramMotionEvent);
  }
  
  @SuppressLint({"ClickableViewAccessibility"})
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
    mVelocityTracker.addMovement(paramMotionEvent);
    int j = paramMotionEvent.getActionMasked();
    int i = 3;
    if ((j == 3) && (mVelocityTracker != null))
    {
      mVelocityTracker.recycle();
      mVelocityTracker = null;
    }
    if (mTouchMode == 0) {
      mGestureDetector.onTouchEvent(paramMotionEvent);
    }
    if ((!((BarLineChartBase)mChart).isDragEnabled()) && (!((BarLineChartBase)mChart).isScaleXEnabled()) && (!((BarLineChartBase)mChart).isScaleYEnabled())) {
      return true;
    }
    int k = paramMotionEvent.getAction();
    j = 0;
    float f2;
    float f1;
    switch (k & 0xFF)
    {
    case 4: 
    default: 
      break;
    case 6: 
      Utils.velocityTrackerPointerUpCleanUpIfNecessary(paramMotionEvent, mVelocityTracker);
      mTouchMode = 5;
      break;
    case 5: 
      if (paramMotionEvent.getPointerCount() >= 2)
      {
        ((BarLineChartBase)mChart).disableScroll();
        saveTouchStart(paramMotionEvent);
        mSavedXDist = getXDist(paramMotionEvent);
        mSavedYDist = getYDist(paramMotionEvent);
        mSavedDist = spacing(paramMotionEvent);
        if (mSavedDist > 10.0F) {
          if (((BarLineChartBase)mChart).isPinchZoomEnabled())
          {
            mTouchMode = 4;
          }
          else if (((BarLineChartBase)mChart).isScaleXEnabled() != ((BarLineChartBase)mChart).isScaleYEnabled())
          {
            if (((BarLineChartBase)mChart).isScaleXEnabled()) {
              i = 2;
            }
            mTouchMode = i;
          }
          else
          {
            if (mSavedXDist > mSavedYDist) {
              i = 2;
            }
            mTouchMode = i;
          }
        }
        midPoint(mTouchPointCenter, paramMotionEvent);
      }
      break;
    case 3: 
      mTouchMode = 0;
      endAction(paramMotionEvent);
      break;
    case 2: 
      if (mTouchMode == 1)
      {
        ((BarLineChartBase)mChart).disableScroll();
        boolean bool = ((BarLineChartBase)mChart).isDragXEnabled();
        f2 = 0.0F;
        if (bool) {
          f1 = paramMotionEvent.getX() - mTouchStartPoint.x;
        } else {
          f1 = 0.0F;
        }
        if (((BarLineChartBase)mChart).isDragYEnabled()) {
          f2 = paramMotionEvent.getY() - mTouchStartPoint.y;
        }
        performDrag(paramMotionEvent, f1, f2);
      }
      else if ((mTouchMode != 2) && (mTouchMode != 3) && (mTouchMode != 4))
      {
        if ((mTouchMode == 0) && (Math.abs(distance(paramMotionEvent.getX(), mTouchStartPoint.x, paramMotionEvent.getY(), mTouchStartPoint.y)) > mDragTriggerDist) && (((BarLineChartBase)mChart).isDragEnabled()))
        {
          if (((BarLineChartBase)mChart).isFullyZoomedOut())
          {
            i = j;
            if (((BarLineChartBase)mChart).hasNoDragOffset()) {}
          }
          else
          {
            i = 1;
          }
          if (i != 0)
          {
            f1 = Math.abs(paramMotionEvent.getX() - mTouchStartPoint.x);
            f2 = Math.abs(paramMotionEvent.getY() - mTouchStartPoint.y);
            if (((((BarLineChartBase)mChart).isDragXEnabled()) || (f2 >= f1)) && ((((BarLineChartBase)mChart).isDragYEnabled()) || (f2 <= f1)))
            {
              mLastGesture = ChartTouchListener.ChartGesture.DRAG;
              mTouchMode = 1;
            }
          }
          else if (((BarLineChartBase)mChart).isHighlightPerDragEnabled())
          {
            mLastGesture = ChartTouchListener.ChartGesture.DRAG;
            if (((BarLineChartBase)mChart).isHighlightPerDragEnabled()) {
              performHighlightDrag(paramMotionEvent);
            }
          }
        }
      }
      else
      {
        ((BarLineChartBase)mChart).disableScroll();
        if ((((BarLineChartBase)mChart).isScaleXEnabled()) || (((BarLineChartBase)mChart).isScaleYEnabled())) {
          performZoom(paramMotionEvent);
        }
      }
      break;
    case 1: 
      paramView = mVelocityTracker;
      i = paramMotionEvent.getPointerId(0);
      paramView.computeCurrentVelocity(1000, Utils.getMaximumFlingVelocity());
      f1 = paramView.getYVelocity(i);
      f2 = paramView.getXVelocity(i);
      if (((Math.abs(f2) > Utils.getMinimumFlingVelocity()) || (Math.abs(f1) > Utils.getMinimumFlingVelocity())) && (mTouchMode == 1) && (((BarLineChartBase)mChart).isDragDecelerationEnabled()))
      {
        stopDeceleration();
        mDecelerationLastTime = AnimationUtils.currentAnimationTimeMillis();
        mDecelerationCurrentPoint.x = paramMotionEvent.getX();
        mDecelerationCurrentPoint.y = paramMotionEvent.getY();
        mDecelerationVelocity.x = f2;
        mDecelerationVelocity.y = f1;
        Utils.postInvalidateOnAnimation(mChart);
      }
      if ((mTouchMode == 2) || (mTouchMode == 3) || (mTouchMode == 4) || (mTouchMode == 5))
      {
        ((BarLineChartBase)mChart).calculateOffsets();
        ((BarLineChartBase)mChart).postInvalidate();
      }
      mTouchMode = 0;
      ((BarLineChartBase)mChart).enableScroll();
      if (mVelocityTracker != null)
      {
        mVelocityTracker.recycle();
        mVelocityTracker = null;
      }
      endAction(paramMotionEvent);
      break;
    case 0: 
      startAction(paramMotionEvent);
      stopDeceleration();
      saveTouchStart(paramMotionEvent);
    }
    mMatrix = ((BarLineChartBase)mChart).getViewPortHandler().refresh(mMatrix, mChart, true);
    return true;
  }
  
  public void setDragTriggerDist(float paramFloat)
  {
    mDragTriggerDist = Utils.convertDpToPixel(paramFloat);
  }
  
  public void stopDeceleration()
  {
    mDecelerationVelocity.x = 0.0F;
    mDecelerationVelocity.y = 0.0F;
  }
}

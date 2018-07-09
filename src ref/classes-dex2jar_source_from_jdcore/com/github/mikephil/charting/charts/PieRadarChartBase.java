package com.github.mikephil.charting.charts;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.animation.Easing.EasingOption;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment;
import com.github.mikephil.charting.components.Legend.LegendVerticalAlignment;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.PieRadarChartTouchListener;
import com.github.mikephil.charting.renderer.LegendRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class PieRadarChartBase<T extends ChartData<? extends IDataSet<? extends Entry>>>
  extends Chart<T>
{
  protected float mMinOffset = 0.0F;
  private float mRawRotationAngle = 270.0F;
  protected boolean mRotateEnabled = true;
  private float mRotationAngle = 270.0F;
  
  public PieRadarChartBase(Context paramContext)
  {
    super(paramContext);
  }
  
  public PieRadarChartBase(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public PieRadarChartBase(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void calcMinMax() {}
  
  public void calculateOffsets()
  {
    Object localObject = mLegend;
    float f5 = 0.0F;
    float f4 = 0.0F;
    if ((localObject != null) && (mLegend.isEnabled()) && (!mLegend.isDrawInsideEnabled()))
    {
      f1 = Math.min(mLegend.mNeededWidth, mViewPortHandler.getChartWidth() * mLegend.getMaxSizePercent());
      switch (2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation[mLegend.getOrientation().ordinal()])
      {
      default: 
        break;
      case 2: 
        if ((mLegend.getVerticalAlignment() == Legend.LegendVerticalAlignment.TOP) || (mLegend.getVerticalAlignment() == Legend.LegendVerticalAlignment.BOTTOM))
        {
          f1 = getRequiredLegendOffset();
          f2 = Math.min(mLegend.mNeededHeight + f1, mViewPortHandler.getChartHeight() * mLegend.getMaxSizePercent());
          f1 = f2;
          switch (2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment[mLegend.getVerticalAlignment().ordinal()])
          {
          default: 
            break;
          case 2: 
            f2 = f1;
            f1 = 0.0F;
            f3 = f1;
          }
        }
        break;
      case 1: 
        for (;;)
        {
          f3 = f2;
          f1 = 0.0F;
          f2 = f1;
          break label622;
          if ((mLegend.getHorizontalAlignment() != Legend.LegendHorizontalAlignment.LEFT) && (mLegend.getHorizontalAlignment() != Legend.LegendHorizontalAlignment.RIGHT))
          {
            f1 = 0.0F;
          }
          else if (mLegend.getVerticalAlignment() == Legend.LegendVerticalAlignment.CENTER)
          {
            f1 += Utils.convertDpToPixel(13.0F);
          }
          else
          {
            f2 = f1 + Utils.convertDpToPixel(8.0F);
            f3 = mLegend.mNeededHeight;
            f5 = mLegend.mTextHeightMax;
            localObject = getCenter();
            if (mLegend.getHorizontalAlignment() == Legend.LegendHorizontalAlignment.RIGHT) {
              f1 = getWidth() - f2 + 15.0F;
            } else {
              f1 = f2 - 15.0F;
            }
            f3 = f3 + f5 + 15.0F;
            f5 = distanceToCenter(f1, f3);
            MPPointF localMPPointF = getPosition((MPPointF)localObject, getRadius(), getAngleForPoint(f1, f3));
            f1 = distanceToCenter(x, y);
            f6 = Utils.convertDpToPixel(5.0F);
            if ((f3 >= y) && (getHeight() - f2 > getWidth())) {
              f1 = f2;
            } else if (f5 < f1) {
              f1 = f6 + (f1 - f5);
            } else {
              f1 = 0.0F;
            }
            MPPointF.recycleInstance((MPPointF)localObject);
            MPPointF.recycleInstance(localMPPointF);
          }
          switch (2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment[mLegend.getHorizontalAlignment().ordinal()])
          {
          default: 
            break;
          case 3: 
            switch (2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment[mLegend.getVerticalAlignment().ordinal()])
            {
            default: 
              break;
            case 2: 
              f1 = Math.min(mLegend.mNeededHeight, mViewPortHandler.getChartHeight() * mLegend.getMaxSizePercent());
              break;
            case 1: 
              f2 = Math.min(mLegend.mNeededHeight, mViewPortHandler.getChartHeight() * mLegend.getMaxSizePercent());
            }
            break;
          }
        }
        break label618;
        f2 = 0.0F;
        f3 = f2;
        f4 = f1;
        f1 = f3;
        break;
      }
      f1 = 0.0F;
      label618:
      f2 = 0.0F;
      f3 = 0.0F;
      label622:
      f4 += getRequiredBaseOffset();
      f1 += getRequiredBaseOffset();
      f3 += getRequiredBaseOffset();
      f5 = f2 + getRequiredBaseOffset();
      f2 = f1;
      f1 = f5;
    }
    else
    {
      f2 = 0.0F;
      f1 = f2;
      f3 = f1;
      f4 = f5;
    }
    float f6 = Utils.convertDpToPixel(mMinOffset);
    f5 = f6;
    if ((this instanceof RadarChart))
    {
      localObject = getXAxis();
      f5 = f6;
      if (((XAxis)localObject).isEnabled())
      {
        f5 = f6;
        if (((XAxis)localObject).isDrawLabelsEnabled()) {
          f5 = Math.max(f6, mLabelRotatedWidth);
        }
      }
    }
    float f8 = getExtraTopOffset();
    float f7 = getExtraRightOffset();
    f6 = getExtraBottomOffset();
    f4 = Math.max(f5, f4 + getExtraLeftOffset());
    float f3 = Math.max(f5, f3 + f8);
    float f2 = Math.max(f5, f2 + f7);
    float f1 = Math.max(f5, Math.max(getRequiredBaseOffset(), f1 + f6));
    mViewPortHandler.restrainViewPort(f4, f3, f2, f1);
    if (mLogEnabled)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("offsetLeft: ");
      ((StringBuilder)localObject).append(f4);
      ((StringBuilder)localObject).append(", offsetTop: ");
      ((StringBuilder)localObject).append(f3);
      ((StringBuilder)localObject).append(", offsetRight: ");
      ((StringBuilder)localObject).append(f2);
      ((StringBuilder)localObject).append(", offsetBottom: ");
      ((StringBuilder)localObject).append(f1);
      Log.i("MPAndroidChart", ((StringBuilder)localObject).toString());
    }
  }
  
  public void computeScroll()
  {
    if ((mChartTouchListener instanceof PieRadarChartTouchListener)) {
      ((PieRadarChartTouchListener)mChartTouchListener).computeScroll();
    }
  }
  
  public float distanceToCenter(float paramFloat1, float paramFloat2)
  {
    MPPointF localMPPointF = getCenterOffsets();
    if (paramFloat1 > x) {
      paramFloat1 -= x;
    } else {
      paramFloat1 = x - paramFloat1;
    }
    if (paramFloat2 > y) {
      paramFloat2 -= y;
    } else {
      paramFloat2 = y - paramFloat2;
    }
    paramFloat1 = (float)Math.sqrt(Math.pow(paramFloat1, 2.0D) + Math.pow(paramFloat2, 2.0D));
    MPPointF.recycleInstance(localMPPointF);
    return paramFloat1;
  }
  
  public float getAngleForPoint(float paramFloat1, float paramFloat2)
  {
    MPPointF localMPPointF = getCenterOffsets();
    double d1 = paramFloat1 - x;
    double d2 = paramFloat2 - y;
    float f = (float)Math.toDegrees(Math.acos(d2 / Math.sqrt(d1 * d1 + d2 * d2)));
    paramFloat2 = f;
    if (paramFloat1 > x) {
      paramFloat2 = 360.0F - f;
    }
    paramFloat2 += 90.0F;
    paramFloat1 = paramFloat2;
    if (paramFloat2 > 360.0F) {
      paramFloat1 = paramFloat2 - 360.0F;
    }
    MPPointF.recycleInstance(localMPPointF);
    return paramFloat1;
  }
  
  public float getDiameter()
  {
    RectF localRectF = mViewPortHandler.getContentRect();
    left += getExtraLeftOffset();
    top += getExtraTopOffset();
    right -= getExtraRightOffset();
    bottom -= getExtraBottomOffset();
    return Math.min(localRectF.width(), localRectF.height());
  }
  
  public abstract int getIndexForAngle(float paramFloat);
  
  public int getMaxVisibleCount()
  {
    return mData.getEntryCount();
  }
  
  public float getMinOffset()
  {
    return mMinOffset;
  }
  
  public MPPointF getPosition(MPPointF paramMPPointF, float paramFloat1, float paramFloat2)
  {
    MPPointF localMPPointF = MPPointF.getInstance(0.0F, 0.0F);
    getPosition(paramMPPointF, paramFloat1, paramFloat2, localMPPointF);
    return localMPPointF;
  }
  
  public void getPosition(MPPointF paramMPPointF1, float paramFloat1, float paramFloat2, MPPointF paramMPPointF2)
  {
    double d1 = x;
    double d2 = paramFloat1;
    double d3 = paramFloat2;
    x = ((float)(d1 + Math.cos(Math.toRadians(d3)) * d2));
    y = ((float)(y + d2 * Math.sin(Math.toRadians(d3))));
  }
  
  public abstract float getRadius();
  
  public float getRawRotationAngle()
  {
    return mRawRotationAngle;
  }
  
  protected abstract float getRequiredBaseOffset();
  
  protected abstract float getRequiredLegendOffset();
  
  public float getRotationAngle()
  {
    return mRotationAngle;
  }
  
  public float getYChartMax()
  {
    return 0.0F;
  }
  
  public float getYChartMin()
  {
    return 0.0F;
  }
  
  protected void init()
  {
    super.init();
    mChartTouchListener = new PieRadarChartTouchListener(this);
  }
  
  public boolean isRotationEnabled()
  {
    return mRotateEnabled;
  }
  
  public void notifyDataSetChanged()
  {
    if (mData == null) {
      return;
    }
    calcMinMax();
    if (mLegend != null) {
      mLegendRenderer.computeLegend(mData);
    }
    calculateOffsets();
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((mTouchEnabled) && (mChartTouchListener != null)) {
      return mChartTouchListener.onTouch(this, paramMotionEvent);
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public void setMinOffset(float paramFloat)
  {
    mMinOffset = paramFloat;
  }
  
  public void setRotationAngle(float paramFloat)
  {
    mRawRotationAngle = paramFloat;
    mRotationAngle = Utils.getNormalizedAngle(mRawRotationAngle);
  }
  
  public void setRotationEnabled(boolean paramBoolean)
  {
    mRotateEnabled = paramBoolean;
  }
  
  @SuppressLint({"NewApi"})
  public void spin(int paramInt, float paramFloat1, float paramFloat2, Easing.EasingOption paramEasingOption)
  {
    if (Build.VERSION.SDK_INT < 11) {
      return;
    }
    setRotationAngle(paramFloat1);
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this, "rotationAngle", new float[] { paramFloat1, paramFloat2 });
    localObjectAnimator.setDuration(paramInt);
    localObjectAnimator.setInterpolator(Easing.getEasingFunctionFromOption(paramEasingOption));
    localObjectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
    {
      public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
      {
        postInvalidate();
      }
    });
    localObjectAnimator.start();
  }
}

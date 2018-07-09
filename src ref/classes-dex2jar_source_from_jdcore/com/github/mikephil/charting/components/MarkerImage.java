package com.github.mikephil.charting.components;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.MPPointF;
import java.lang.ref.WeakReference;

public class MarkerImage
  implements IMarker
{
  private Context mContext;
  private Drawable mDrawable;
  private Rect mDrawableBoundsCache = new Rect();
  private MPPointF mOffset = new MPPointF();
  private MPPointF mOffset2 = new MPPointF();
  private FSize mSize = new FSize();
  private WeakReference<Chart> mWeakChart;
  
  public MarkerImage(Context paramContext, int paramInt)
  {
    mContext = paramContext;
    if (Build.VERSION.SDK_INT >= 21)
    {
      mDrawable = mContext.getResources().getDrawable(paramInt, null);
      return;
    }
    mDrawable = mContext.getResources().getDrawable(paramInt);
  }
  
  public void draw(Canvas paramCanvas, float paramFloat1, float paramFloat2)
  {
    if (mDrawable == null) {
      return;
    }
    MPPointF localMPPointF = getOffsetForDrawingAtPoint(paramFloat1, paramFloat2);
    float f2 = mSize.width;
    float f3 = mSize.height;
    float f1 = f2;
    if (f2 == 0.0F)
    {
      f1 = f2;
      if (mDrawable != null) {
        f1 = mDrawable.getIntrinsicWidth();
      }
    }
    f2 = f3;
    if (f3 == 0.0F)
    {
      f2 = f3;
      if (mDrawable != null) {
        f2 = mDrawable.getIntrinsicHeight();
      }
    }
    mDrawable.copyBounds(mDrawableBoundsCache);
    mDrawable.setBounds(mDrawableBoundsCache.left, mDrawableBoundsCache.top, mDrawableBoundsCache.left + (int)f1, mDrawableBoundsCache.top + (int)f2);
    int i = paramCanvas.save();
    paramCanvas.translate(paramFloat1 + x, paramFloat2 + y);
    mDrawable.draw(paramCanvas);
    paramCanvas.restoreToCount(i);
    mDrawable.setBounds(mDrawableBoundsCache);
  }
  
  public Chart getChartView()
  {
    if (mWeakChart == null) {
      return null;
    }
    return (Chart)mWeakChart.get();
  }
  
  public MPPointF getOffset()
  {
    return mOffset;
  }
  
  public MPPointF getOffsetForDrawingAtPoint(float paramFloat1, float paramFloat2)
  {
    Object localObject = getOffset();
    mOffset2.x = x;
    mOffset2.y = y;
    localObject = getChartView();
    float f2 = mSize.width;
    float f3 = mSize.height;
    float f1 = f2;
    if (f2 == 0.0F)
    {
      f1 = f2;
      if (mDrawable != null) {
        f1 = mDrawable.getIntrinsicWidth();
      }
    }
    f2 = f3;
    if (f3 == 0.0F)
    {
      f2 = f3;
      if (mDrawable != null) {
        f2 = mDrawable.getIntrinsicHeight();
      }
    }
    if (mOffset2.x + paramFloat1 < 0.0F) {
      mOffset2.x = (-paramFloat1);
    } else if ((localObject != null) && (paramFloat1 + f1 + mOffset2.x > ((Chart)localObject).getWidth())) {
      mOffset2.x = (((Chart)localObject).getWidth() - paramFloat1 - f1);
    }
    if (mOffset2.y + paramFloat2 < 0.0F) {
      mOffset2.y = (-paramFloat2);
    } else if ((localObject != null) && (paramFloat2 + f2 + mOffset2.y > ((Chart)localObject).getHeight())) {
      mOffset2.y = (((Chart)localObject).getHeight() - paramFloat2 - f2);
    }
    return mOffset2;
  }
  
  public FSize getSize()
  {
    return mSize;
  }
  
  public void refreshContent(Entry paramEntry, Highlight paramHighlight) {}
  
  public void setChartView(Chart paramChart)
  {
    mWeakChart = new WeakReference(paramChart);
  }
  
  public void setOffset(float paramFloat1, float paramFloat2)
  {
    mOffset.x = paramFloat1;
    mOffset.y = paramFloat2;
  }
  
  public void setOffset(MPPointF paramMPPointF)
  {
    mOffset = paramMPPointF;
    if (mOffset == null) {
      mOffset = new MPPointF();
    }
  }
  
  public void setSize(FSize paramFSize)
  {
    mSize = paramFSize;
    if (mSize == null) {
      mSize = new FSize();
    }
  }
}

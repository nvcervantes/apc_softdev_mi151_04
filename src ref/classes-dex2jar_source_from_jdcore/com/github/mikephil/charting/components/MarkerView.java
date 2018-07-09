package com.github.mikephil.charting.components;

import android.content.Context;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import java.lang.ref.WeakReference;

public class MarkerView
  extends RelativeLayout
  implements IMarker
{
  private MPPointF mOffset = new MPPointF();
  private MPPointF mOffset2 = new MPPointF();
  private WeakReference<Chart> mWeakChart;
  
  public MarkerView(Context paramContext, int paramInt)
  {
    super(paramContext);
    setupLayoutResource(paramInt);
  }
  
  private void setupLayoutResource(int paramInt)
  {
    View localView = LayoutInflater.from(getContext()).inflate(paramInt, this);
    localView.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
    localView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
    localView.layout(0, 0, localView.getMeasuredWidth(), localView.getMeasuredHeight());
  }
  
  public void draw(Canvas paramCanvas, float paramFloat1, float paramFloat2)
  {
    MPPointF localMPPointF = getOffsetForDrawingAtPoint(paramFloat1, paramFloat2);
    int i = paramCanvas.save();
    paramCanvas.translate(paramFloat1 + x, paramFloat2 + y);
    draw(paramCanvas);
    paramCanvas.restoreToCount(i);
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
    float f1 = getWidth();
    float f2 = getHeight();
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
  
  public void refreshContent(Entry paramEntry, Highlight paramHighlight)
  {
    measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
    layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
  }
  
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
}

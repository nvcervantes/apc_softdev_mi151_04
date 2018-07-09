package com.github.mikephil.charting.charts;

import android.content.Context;
import android.util.AttributeSet;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.renderer.LineChartRenderer;

public class LineChart
  extends BarLineChartBase<LineData>
  implements LineDataProvider
{
  public LineChart(Context paramContext)
  {
    super(paramContext);
  }
  
  public LineChart(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public LineChart(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public LineData getLineData()
  {
    return (LineData)mData;
  }
  
  protected void init()
  {
    super.init();
    mRenderer = new LineChartRenderer(this, mAnimator, mViewPortHandler);
  }
  
  protected void onDetachedFromWindow()
  {
    if ((mRenderer != null) && ((mRenderer instanceof LineChartRenderer))) {
      ((LineChartRenderer)mRenderer).releaseBitmap();
    }
    super.onDetachedFromWindow();
  }
}

package com.github.mikephil.charting.charts;

import android.content.Context;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.interfaces.dataprovider.CandleDataProvider;
import com.github.mikephil.charting.renderer.CandleStickChartRenderer;

public class CandleStickChart
  extends BarLineChartBase<CandleData>
  implements CandleDataProvider
{
  public CandleStickChart(Context paramContext)
  {
    super(paramContext);
  }
  
  public CandleStickChart(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public CandleStickChart(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public CandleData getCandleData()
  {
    return (CandleData)mData;
  }
  
  protected void init()
  {
    super.init();
    mRenderer = new CandleStickChartRenderer(this, mAnimator, mViewPortHandler);
    getXAxis().setSpaceMin(0.5F);
    getXAxis().setSpaceMax(0.5F);
  }
}

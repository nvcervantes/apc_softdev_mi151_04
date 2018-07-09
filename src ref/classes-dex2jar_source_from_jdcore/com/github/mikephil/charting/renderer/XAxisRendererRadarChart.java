package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class XAxisRendererRadarChart
  extends XAxisRenderer
{
  private RadarChart mChart;
  
  public XAxisRendererRadarChart(ViewPortHandler paramViewPortHandler, XAxis paramXAxis, RadarChart paramRadarChart)
  {
    super(paramViewPortHandler, paramXAxis, null);
    mChart = paramRadarChart;
  }
  
  public void renderAxisLabels(Canvas paramCanvas)
  {
    if (mXAxis.isEnabled())
    {
      if (!mXAxis.isDrawLabelsEnabled()) {
        return;
      }
      float f1 = mXAxis.getLabelRotationAngle();
      MPPointF localMPPointF1 = MPPointF.getInstance(0.5F, 0.25F);
      mAxisLabelPaint.setTypeface(mXAxis.getTypeface());
      mAxisLabelPaint.setTextSize(mXAxis.getTextSize());
      mAxisLabelPaint.setColor(mXAxis.getTextColor());
      float f2 = mChart.getSliceAngle();
      float f3 = mChart.getFactor();
      MPPointF localMPPointF2 = mChart.getCenterOffsets();
      MPPointF localMPPointF3 = MPPointF.getInstance(0.0F, 0.0F);
      int i = 0;
      while (i < ((IRadarDataSet)((RadarData)mChart.getData()).getMaxEntryCountSet()).getEntryCount())
      {
        Object localObject = mXAxis.getValueFormatter();
        float f4 = i;
        localObject = ((IAxisValueFormatter)localObject).getFormattedValue(f4, mXAxis);
        float f5 = mChart.getRotationAngle();
        Utils.getPosition(localMPPointF2, mChart.getYRange() * f3 + mXAxis.mLabelRotatedWidth / 2.0F, (f4 * f2 + f5) % 360.0F, localMPPointF3);
        drawLabel(paramCanvas, (String)localObject, x, y - mXAxis.mLabelRotatedHeight / 2.0F, localMPPointF1, f1);
        i += 1;
      }
      MPPointF.recycleInstance(localMPPointF2);
      MPPointF.recycleInstance(localMPPointF3);
      MPPointF.recycleInstance(localMPPointF1);
      return;
    }
  }
  
  public void renderLimitLines(Canvas paramCanvas) {}
}

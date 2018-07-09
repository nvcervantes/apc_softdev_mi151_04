package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class YAxisRendererRadarChart
  extends YAxisRenderer
{
  private RadarChart mChart;
  private Path mRenderLimitLinesPathBuffer = new Path();
  
  public YAxisRendererRadarChart(ViewPortHandler paramViewPortHandler, YAxis paramYAxis, RadarChart paramRadarChart)
  {
    super(paramViewPortHandler, paramYAxis, null);
    mChart = paramRadarChart;
  }
  
  protected void computeAxisValues(float paramFloat1, float paramFloat2)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public void renderAxisLabels(Canvas paramCanvas)
  {
    if (mYAxis.isEnabled())
    {
      if (!mYAxis.isDrawLabelsEnabled()) {
        return;
      }
      mAxisLabelPaint.setTypeface(mYAxis.getTypeface());
      mAxisLabelPaint.setTextSize(mYAxis.getTextSize());
      mAxisLabelPaint.setColor(mYAxis.getTextColor());
      MPPointF localMPPointF1 = mChart.getCenterOffsets();
      MPPointF localMPPointF2 = MPPointF.getInstance(0.0F, 0.0F);
      float f = mChart.getFactor();
      int j = mYAxis.isDrawBottomYLabelEntryEnabled() ^ true;
      int i;
      if (mYAxis.isDrawTopYLabelEntryEnabled()) {
        i = mYAxis.mEntryCount;
      } else {
        i = mYAxis.mEntryCount - 1;
      }
      while (j < i)
      {
        Utils.getPosition(localMPPointF1, (mYAxis.mEntries[j] - mYAxis.mAxisMinimum) * f, mChart.getRotationAngle(), localMPPointF2);
        paramCanvas.drawText(mYAxis.getFormattedLabel(j), x + 10.0F, y, mAxisLabelPaint);
        int k;
        j += 1;
      }
      MPPointF.recycleInstance(localMPPointF1);
      MPPointF.recycleInstance(localMPPointF2);
      return;
    }
  }
  
  public void renderLimitLines(Canvas paramCanvas)
  {
    List localList = mYAxis.getLimitLines();
    if (localList == null) {
      return;
    }
    float f1 = mChart.getSliceAngle();
    float f2 = mChart.getFactor();
    MPPointF localMPPointF1 = mChart.getCenterOffsets();
    MPPointF localMPPointF2 = MPPointF.getInstance(0.0F, 0.0F);
    int i = 0;
    while (i < localList.size())
    {
      Object localObject = (LimitLine)localList.get(i);
      if (((LimitLine)localObject).isEnabled())
      {
        mLimitLinePaint.setColor(((LimitLine)localObject).getLineColor());
        mLimitLinePaint.setPathEffect(((LimitLine)localObject).getDashPathEffect());
        mLimitLinePaint.setStrokeWidth(((LimitLine)localObject).getLineWidth());
        float f3 = ((LimitLine)localObject).getLimit();
        float f4 = mChart.getYChartMin();
        localObject = mRenderLimitLinesPathBuffer;
        ((Path)localObject).reset();
        int j = 0;
        while (j < ((IRadarDataSet)((RadarData)mChart.getData()).getMaxEntryCountSet()).getEntryCount())
        {
          Utils.getPosition(localMPPointF1, (f3 - f4) * f2, j * f1 + mChart.getRotationAngle(), localMPPointF2);
          if (j == 0) {
            ((Path)localObject).moveTo(x, y);
          } else {
            ((Path)localObject).lineTo(x, y);
          }
          j += 1;
        }
        ((Path)localObject).close();
        paramCanvas.drawPath((Path)localObject, mLimitLinePaint);
      }
      i += 1;
    }
    MPPointF.recycleInstance(localMPPointF1);
    MPPointF.recycleInstance(localMPPointF2);
  }
}

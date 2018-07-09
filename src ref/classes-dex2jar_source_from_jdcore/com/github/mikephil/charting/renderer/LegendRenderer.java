package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Typeface;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendDirection;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment;
import com.github.mikephil.charting.components.Legend.LegendOrientation;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LegendRenderer
  extends Renderer
{
  protected List<LegendEntry> computedEntries = new ArrayList(16);
  protected Paint.FontMetrics legendFontMetrics = new Paint.FontMetrics();
  protected Legend mLegend;
  protected Paint mLegendFormPaint;
  protected Paint mLegendLabelPaint;
  private Path mLineFormPath = new Path();
  
  public LegendRenderer(ViewPortHandler paramViewPortHandler, Legend paramLegend)
  {
    super(paramViewPortHandler);
    mLegend = paramLegend;
    mLegendLabelPaint = new Paint(1);
    mLegendLabelPaint.setTextSize(Utils.convertDpToPixel(9.0F));
    mLegendLabelPaint.setTextAlign(Paint.Align.LEFT);
    mLegendFormPaint = new Paint(1);
    mLegendFormPaint.setStyle(Paint.Style.FILL);
  }
  
  public void computeLegend(ChartData<?> paramChartData)
  {
    Object localObject = paramChartData;
    if (!mLegend.isLegendCustom())
    {
      computedEntries.clear();
      int i = 0;
      while (i < paramChartData.getDataSetCount())
      {
        IDataSet localIDataSet = ((ChartData)localObject).getDataSetByIndex(i);
        List localList = localIDataSet.getColors();
        int k = localIDataSet.getEntryCount();
        int j;
        if ((localIDataSet instanceof IBarDataSet))
        {
          IBarDataSet localIBarDataSet = (IBarDataSet)localIDataSet;
          if (localIBarDataSet.isStacked())
          {
            String[] arrayOfString = localIBarDataSet.getStackLabels();
            j = 0;
            while ((j < localList.size()) && (j < localIBarDataSet.getStackSize()))
            {
              computedEntries.add(new LegendEntry(arrayOfString[(j % arrayOfString.length)], localIDataSet.getForm(), localIDataSet.getFormSize(), localIDataSet.getFormLineWidth(), localIDataSet.getFormLineDashEffect(), ((Integer)localList.get(j)).intValue()));
              j += 1;
            }
            if (localIBarDataSet.getLabel() != null) {
              computedEntries.add(new LegendEntry(localIDataSet.getLabel(), Legend.LegendForm.NONE, NaN.0F, NaN.0F, null, 1122867));
            }
            break label675;
          }
        }
        if ((localIDataSet instanceof IPieDataSet))
        {
          localObject = (IPieDataSet)localIDataSet;
          j = 0;
          while ((j < localList.size()) && (j < k))
          {
            computedEntries.add(new LegendEntry(((PieEntry)((IPieDataSet)localObject).getEntryForIndex(j)).getLabel(), localIDataSet.getForm(), localIDataSet.getFormSize(), localIDataSet.getFormLineWidth(), localIDataSet.getFormLineDashEffect(), ((Integer)localList.get(j)).intValue()));
            j += 1;
          }
          if (((IPieDataSet)localObject).getLabel() != null) {
            computedEntries.add(new LegendEntry(localIDataSet.getLabel(), Legend.LegendForm.NONE, NaN.0F, NaN.0F, null, 1122867));
          }
        }
        else
        {
          if (!(localIDataSet instanceof ICandleDataSet)) {
            break label549;
          }
          localObject = (ICandleDataSet)localIDataSet;
          if (((ICandleDataSet)localObject).getDecreasingColor() == 1122867) {
            break label549;
          }
          j = ((ICandleDataSet)localObject).getDecreasingColor();
          k = ((ICandleDataSet)localObject).getIncreasingColor();
          computedEntries.add(new LegendEntry(null, localIDataSet.getForm(), localIDataSet.getFormSize(), localIDataSet.getFormLineWidth(), localIDataSet.getFormLineDashEffect(), j));
          computedEntries.add(new LegendEntry(localIDataSet.getLabel(), localIDataSet.getForm(), localIDataSet.getFormSize(), localIDataSet.getFormLineWidth(), localIDataSet.getFormLineDashEffect(), k));
        }
        for (;;)
        {
          localObject = paramChartData;
          break;
          label549:
          j = 0;
          while ((j < localList.size()) && (j < k))
          {
            if ((j < localList.size() - 1) && (j < k - 1)) {
              localObject = null;
            } else {
              localObject = paramChartData.getDataSetByIndex(i).getLabel();
            }
            computedEntries.add(new LegendEntry((String)localObject, localIDataSet.getForm(), localIDataSet.getFormSize(), localIDataSet.getFormLineWidth(), localIDataSet.getFormLineDashEffect(), ((Integer)localList.get(j)).intValue()));
            j += 1;
          }
        }
        label675:
        i += 1;
      }
      if (mLegend.getExtraEntries() != null) {
        Collections.addAll(computedEntries, mLegend.getExtraEntries());
      }
      mLegend.setEntries(computedEntries);
    }
    paramChartData = mLegend.getTypeface();
    if (paramChartData != null) {
      mLegendLabelPaint.setTypeface(paramChartData);
    }
    mLegendLabelPaint.setTextSize(mLegend.getTextSize());
    mLegendLabelPaint.setColor(mLegend.getTextColor());
    mLegend.calculateDimensions(mLegendLabelPaint, mViewPortHandler);
  }
  
  protected void drawForm(Canvas paramCanvas, float paramFloat1, float paramFloat2, LegendEntry paramLegendEntry, Legend paramLegend)
  {
    if ((formColor != 1122868) && (formColor != 1122867))
    {
      if (formColor == 0) {
        return;
      }
      int i = paramCanvas.save();
      Legend.LegendForm localLegendForm2 = form;
      Legend.LegendForm localLegendForm1 = localLegendForm2;
      if (localLegendForm2 == Legend.LegendForm.DEFAULT) {
        localLegendForm1 = paramLegend.getForm();
      }
      mLegendFormPaint.setColor(formColor);
      if (Float.isNaN(formSize)) {
        f1 = paramLegend.getFormSize();
      } else {
        f1 = formSize;
      }
      float f2 = Utils.convertDpToPixel(f1);
      float f1 = f2 / 2.0F;
      switch (1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm[localLegendForm1.ordinal()])
      {
      default: 
        break;
      case 6: 
        if (Float.isNaN(formLineWidth)) {
          f1 = paramLegend.getFormLineWidth();
        } else {
          f1 = formLineWidth;
        }
        f1 = Utils.convertDpToPixel(f1);
        if (formLineDashEffect == null) {
          paramLegendEntry = paramLegend.getFormLineDashEffect();
        } else {
          paramLegendEntry = formLineDashEffect;
        }
        mLegendFormPaint.setStyle(Paint.Style.STROKE);
        mLegendFormPaint.setStrokeWidth(f1);
        mLegendFormPaint.setPathEffect(paramLegendEntry);
        mLineFormPath.reset();
        mLineFormPath.moveTo(paramFloat1, paramFloat2);
        mLineFormPath.lineTo(paramFloat1 + f2, paramFloat2);
        paramCanvas.drawPath(mLineFormPath, mLegendFormPaint);
        break;
      case 5: 
        mLegendFormPaint.setStyle(Paint.Style.FILL);
        paramCanvas.drawRect(paramFloat1, paramFloat2 - f1, paramFloat1 + f2, paramFloat2 + f1, mLegendFormPaint);
        break;
      case 3: 
      case 4: 
        mLegendFormPaint.setStyle(Paint.Style.FILL);
        paramCanvas.drawCircle(paramFloat1 + f1, paramFloat2, f1, mLegendFormPaint);
      }
      paramCanvas.restoreToCount(i);
      return;
    }
  }
  
  protected void drawLabel(Canvas paramCanvas, float paramFloat1, float paramFloat2, String paramString)
  {
    paramCanvas.drawText(paramString, paramFloat1, paramFloat2, mLegendLabelPaint);
  }
  
  public Paint getFormPaint()
  {
    return mLegendFormPaint;
  }
  
  public Paint getLabelPaint()
  {
    return mLegendLabelPaint;
  }
  
  public void renderLegend(Canvas paramCanvas)
  {
    if (!mLegend.isEnabled()) {
      return;
    }
    Object localObject1 = mLegend.getTypeface();
    if (localObject1 != null) {
      mLegendLabelPaint.setTypeface((Typeface)localObject1);
    }
    mLegendLabelPaint.setTextSize(mLegend.getTextSize());
    mLegendLabelPaint.setColor(mLegend.getTextColor());
    float f13 = Utils.getLineHeight(mLegendLabelPaint, legendFontMetrics);
    float f11 = Utils.getLineSpacing(mLegendLabelPaint, legendFontMetrics) + Utils.convertDpToPixel(mLegend.getYEntrySpace());
    float f12 = f13 - Utils.calcTextHeight(mLegendLabelPaint, "ABC") / 2.0F;
    LegendEntry[] arrayOfLegendEntry = mLegend.getEntries();
    float f6 = Utils.convertDpToPixel(mLegend.getFormToTextSpace());
    float f5 = Utils.convertDpToPixel(mLegend.getXEntrySpace());
    localObject1 = mLegend.getOrientation();
    Legend.LegendHorizontalAlignment localLegendHorizontalAlignment = mLegend.getHorizontalAlignment();
    Object localObject3 = mLegend.getVerticalAlignment();
    Legend.LegendDirection localLegendDirection = mLegend.getDirection();
    float f8 = Utils.convertDpToPixel(mLegend.getFormSize());
    float f4 = Utils.convertDpToPixel(mLegend.getStackSpace());
    float f7 = mLegend.getYOffset();
    float f2 = mLegend.getXOffset();
    switch (1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment[localLegendHorizontalAlignment.ordinal()])
    {
    default: 
      f1 = 0.0F;
      break;
    case 3: 
      if (localObject1 == Legend.LegendOrientation.VERTICAL) {
        f1 = mViewPortHandler.getChartWidth() / 2.0F;
      } else {
        f1 = mViewPortHandler.contentLeft() + mViewPortHandler.contentWidth() / 2.0F;
      }
      if (localLegendDirection == Legend.LegendDirection.LEFT_TO_RIGHT) {
        f3 = f2;
      } else {
        f3 = -f2;
      }
      f1 = f3 + f1;
      if (localObject1 == Legend.LegendOrientation.VERTICAL)
      {
        double d2 = f1;
        double d1;
        if (localLegendDirection == Legend.LegendDirection.LEFT_TO_RIGHT) {
          d1 = -mLegend.mNeededWidth / 2.0D + f2;
        } else {
          d1 = mLegend.mNeededWidth / 2.0D - f2;
        }
        f1 = (float)(d2 + d1);
        break label530;
      }
      f2 = f1;
      break;
    case 2: 
      if (localObject1 == Legend.LegendOrientation.VERTICAL) {
        f1 = mViewPortHandler.getChartWidth() - f2;
      } else {
        f1 = mViewPortHandler.contentRight() - f2;
      }
      f2 = f1;
      if (localLegendDirection == Legend.LegendDirection.LEFT_TO_RIGHT) {
        f1 -= mLegend.mNeededWidth;
      } else {
        f1 = f2;
      }
      break;
    }
    if (localObject1 != Legend.LegendOrientation.VERTICAL) {
      f2 += mViewPortHandler.contentLeft();
    }
    float f1 = f2;
    if (localLegendDirection == Legend.LegendDirection.RIGHT_TO_LEFT) {
      f1 = f2 + mLegend.mNeededWidth;
    }
    label530:
    float f3 = f6;
    int i;
    int j;
    switch (1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation[localObject1.ordinal()])
    {
    default: 
      return;
    case 2: 
      switch (1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment[localObject3.ordinal()])
      {
      default: 
        f2 = 0.0F;
        break;
      case 3: 
        f2 = mViewPortHandler.getChartHeight() / 2.0F - mLegend.mNeededHeight / 2.0F + mLegend.getYOffset();
        break;
      case 2: 
        if (localLegendHorizontalAlignment == Legend.LegendHorizontalAlignment.CENTER) {
          f2 = mViewPortHandler.getChartHeight();
        } else {
          f2 = mViewPortHandler.contentBottom();
        }
        f2 -= mLegend.mNeededHeight + f7;
        break;
      case 1: 
        if (localLegendHorizontalAlignment == Legend.LegendHorizontalAlignment.CENTER) {
          f2 = 0.0F;
        } else {
          f2 = mViewPortHandler.contentTop();
        }
        f2 += f7;
      }
      f5 = f2;
      f7 = 0.0F;
      i = 0;
      j = 0;
      f2 = f4;
      f4 = f5;
      localObject1 = localLegendDirection;
    }
    while (i < arrayOfLegendEntry.length)
    {
      Object localObject2 = arrayOfLegendEntry[i];
      if (form != Legend.LegendForm.NONE) {
        k = 1;
      } else {
        k = 0;
      }
      if (Float.isNaN(formSize)) {
        f9 = f8;
      } else {
        f9 = Utils.convertDpToPixel(formSize);
      }
      if (k != 0)
      {
        if (localObject1 == Legend.LegendDirection.LEFT_TO_RIGHT) {}
        for (f5 = f1 + f7;; f5 = f1 - (f9 - f7))
        {
          f6 = f5;
          break;
        }
        drawForm(paramCanvas, f6, f4 + f12, (LegendEntry)localObject2, mLegend);
        f5 = f6;
        if (localObject1 == Legend.LegendDirection.LEFT_TO_RIGHT) {
          f5 = f6 + f9;
        }
      }
      else
      {
        f5 = f1;
      }
      f6 = f2;
      if (label != null)
      {
        if ((k != 0) && (j == 0))
        {
          if (localObject1 == Legend.LegendDirection.LEFT_TO_RIGHT) {
            f2 = f3;
          } else {
            f2 = -f3;
          }
          f2 = f5 + f2;
        }
        else if (j != 0)
        {
          f2 = f1;
        }
        else
        {
          f2 = f5;
        }
        f5 = f2;
        if (localObject1 == Legend.LegendDirection.RIGHT_TO_LEFT) {
          f5 = f2 - Utils.calcTextWidth(mLegendLabelPaint, label);
        }
        if (j == 0)
        {
          drawLabel(paramCanvas, f5, f4 + f13, label);
        }
        else
        {
          f4 += f13 + f11;
          drawLabel(paramCanvas, f5, f4 + f13, label);
        }
        f4 += f13 + f11;
        f2 = 0.0F;
      }
      else
      {
        f2 = f7 + (f9 + f6);
        j = 1;
      }
      i += 1;
      f7 = f2;
      f2 = f6;
      continue;
      f6 = f3;
      List localList = mLegend.getCalculatedLineSizes();
      localObject1 = mLegend.getCalculatedLabelSizes();
      localObject2 = mLegend.getCalculatedLabelBreakPoints();
      f2 = f7;
      switch (1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment[localObject3.ordinal()])
      {
      default: 
        f2 = 0.0F;
        break;
      case 3: 
        f2 = f7 + (mViewPortHandler.getChartHeight() - mLegend.mNeededHeight) / 2.0F;
        break;
      case 2: 
        f2 = mViewPortHandler.getChartHeight() - f7 - mLegend.mNeededHeight;
      }
      int k = arrayOfLegendEntry.length;
      float f10 = f2;
      f7 = f1;
      i = 0;
      j = 0;
      f3 = f5;
      float f9 = f12;
      f2 = f1;
      for (f1 = f10; i < k; f1 = f5)
      {
        localObject3 = arrayOfLegendEntry[i];
        int m;
        if (form != Legend.LegendForm.NONE) {
          m = 1;
        } else {
          m = 0;
        }
        if (Float.isNaN(formSize)) {
          f10 = f8;
        } else {
          f10 = Utils.convertDpToPixel(formSize);
        }
        if ((i < ((List)localObject2).size()) && (((Boolean)((List)localObject2).get(i)).booleanValue()))
        {
          f5 = f1 + (f13 + f11);
          f1 = f2;
        }
        else
        {
          f5 = f1;
          f1 = f7;
        }
        if ((f1 == f2) && (localLegendHorizontalAlignment == Legend.LegendHorizontalAlignment.CENTER) && (j < localList.size()))
        {
          if (localLegendDirection == Legend.LegendDirection.RIGHT_TO_LEFT) {}
          for (f7 = getwidth;; f7 = -getwidth) {
            break;
          }
          f1 += f7 / 2.0F;
          j += 1;
        }
        int n;
        if (label == null) {
          n = 1;
        } else {
          n = 0;
        }
        if (m != 0)
        {
          f7 = f1;
          if (localLegendDirection == Legend.LegendDirection.RIGHT_TO_LEFT) {
            f7 = f1 - f10;
          }
          drawForm(paramCanvas, f7, f5 + f9, (LegendEntry)localObject3, mLegend);
          f1 = f7;
          if (localLegendDirection == Legend.LegendDirection.LEFT_TO_RIGHT) {
            f1 = f7 + f10;
          }
        }
        if (n == 0)
        {
          f7 = f1;
          if (m != 0)
          {
            if (localLegendDirection == Legend.LegendDirection.RIGHT_TO_LEFT) {
              f7 = -f6;
            } else {
              f7 = f6;
            }
            f7 = f1 + f7;
          }
          f1 = f7;
          if (localLegendDirection == Legend.LegendDirection.RIGHT_TO_LEFT) {
            f1 = f7 - getwidth;
          }
          f7 = f1;
          drawLabel(paramCanvas, f7, f5 + f13, label);
          f1 = f7;
          if (localLegendDirection == Legend.LegendDirection.LEFT_TO_RIGHT) {
            f1 = f7 + getwidth;
          }
          if (localLegendDirection == Legend.LegendDirection.RIGHT_TO_LEFT) {
            f7 = -f3;
          } else {
            f7 = f3;
          }
          f7 = f1 + f7;
        }
        else
        {
          if (localLegendDirection == Legend.LegendDirection.RIGHT_TO_LEFT) {
            f7 = -f4;
          } else {
            f7 = f4;
          }
          f7 = f1 + f7;
        }
        i += 1;
      }
    }
  }
}

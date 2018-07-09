package com.github.mikephil.charting.utils;

import android.graphics.Matrix;

public class TransformerHorizontalBarChart
  extends Transformer
{
  public TransformerHorizontalBarChart(ViewPortHandler paramViewPortHandler)
  {
    super(paramViewPortHandler);
  }
  
  public void prepareMatrixOffset(boolean paramBoolean)
  {
    mMatrixOffset.reset();
    if (!paramBoolean)
    {
      mMatrixOffset.postTranslate(mViewPortHandler.offsetLeft(), mViewPortHandler.getChartHeight() - mViewPortHandler.offsetBottom());
      return;
    }
    mMatrixOffset.setTranslate(-(mViewPortHandler.getChartWidth() - mViewPortHandler.offsetRight()), mViewPortHandler.getChartHeight() - mViewPortHandler.offsetBottom());
    mMatrixOffset.postScale(-1.0F, 1.0F);
  }
}

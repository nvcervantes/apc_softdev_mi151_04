package com.github.mikephil.charting.utils;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import java.util.List;

public class Transformer
{
  private Matrix mMBuffer1 = new Matrix();
  private Matrix mMBuffer2 = new Matrix();
  protected Matrix mMatrixOffset = new Matrix();
  protected Matrix mMatrixValueToPx = new Matrix();
  protected Matrix mPixelToValueMatrixBuffer = new Matrix();
  protected ViewPortHandler mViewPortHandler;
  float[] ptsBuffer = new float[2];
  protected float[] valuePointsForGenerateTransformedValuesBubble = new float[1];
  protected float[] valuePointsForGenerateTransformedValuesCandle = new float[1];
  protected float[] valuePointsForGenerateTransformedValuesLine = new float[1];
  protected float[] valuePointsForGenerateTransformedValuesScatter = new float[1];
  
  public Transformer(ViewPortHandler paramViewPortHandler)
  {
    mViewPortHandler = paramViewPortHandler;
  }
  
  public float[] generateTransformedValuesBubble(IBubbleDataSet paramIBubbleDataSet, float paramFloat, int paramInt1, int paramInt2)
  {
    int i = (paramInt2 - paramInt1 + 1) * 2;
    if (valuePointsForGenerateTransformedValuesBubble.length != i) {
      valuePointsForGenerateTransformedValuesBubble = new float[i];
    }
    float[] arrayOfFloat = valuePointsForGenerateTransformedValuesBubble;
    paramInt2 = 0;
    while (paramInt2 < i)
    {
      Entry localEntry = paramIBubbleDataSet.getEntryForIndex(paramInt2 / 2 + paramInt1);
      if (localEntry != null)
      {
        arrayOfFloat[paramInt2] = localEntry.getX();
        arrayOfFloat[(paramInt2 + 1)] = (localEntry.getY() * paramFloat);
      }
      else
      {
        arrayOfFloat[paramInt2] = 0.0F;
        arrayOfFloat[(paramInt2 + 1)] = 0.0F;
      }
      paramInt2 += 2;
    }
    getValueToPixelMatrix().mapPoints(arrayOfFloat);
    return arrayOfFloat;
  }
  
  public float[] generateTransformedValuesCandle(ICandleDataSet paramICandleDataSet, float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    int i = (int)((paramInt2 - paramInt1) * paramFloat1 + 1.0F) * 2;
    if (valuePointsForGenerateTransformedValuesCandle.length != i) {
      valuePointsForGenerateTransformedValuesCandle = new float[i];
    }
    float[] arrayOfFloat = valuePointsForGenerateTransformedValuesCandle;
    paramInt2 = 0;
    while (paramInt2 < i)
    {
      CandleEntry localCandleEntry = (CandleEntry)paramICandleDataSet.getEntryForIndex(paramInt2 / 2 + paramInt1);
      if (localCandleEntry != null)
      {
        arrayOfFloat[paramInt2] = localCandleEntry.getX();
        arrayOfFloat[(paramInt2 + 1)] = (localCandleEntry.getHigh() * paramFloat2);
      }
      else
      {
        arrayOfFloat[paramInt2] = 0.0F;
        arrayOfFloat[(paramInt2 + 1)] = 0.0F;
      }
      paramInt2 += 2;
    }
    getValueToPixelMatrix().mapPoints(arrayOfFloat);
    return arrayOfFloat;
  }
  
  public float[] generateTransformedValuesLine(ILineDataSet paramILineDataSet, float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    int i = ((int)((paramInt2 - paramInt1) * paramFloat1) + 1) * 2;
    if (valuePointsForGenerateTransformedValuesLine.length != i) {
      valuePointsForGenerateTransformedValuesLine = new float[i];
    }
    float[] arrayOfFloat = valuePointsForGenerateTransformedValuesLine;
    paramInt2 = 0;
    while (paramInt2 < i)
    {
      Entry localEntry = paramILineDataSet.getEntryForIndex(paramInt2 / 2 + paramInt1);
      if (localEntry != null)
      {
        arrayOfFloat[paramInt2] = localEntry.getX();
        arrayOfFloat[(paramInt2 + 1)] = (localEntry.getY() * paramFloat2);
      }
      else
      {
        arrayOfFloat[paramInt2] = 0.0F;
        arrayOfFloat[(paramInt2 + 1)] = 0.0F;
      }
      paramInt2 += 2;
    }
    getValueToPixelMatrix().mapPoints(arrayOfFloat);
    return arrayOfFloat;
  }
  
  public float[] generateTransformedValuesScatter(IScatterDataSet paramIScatterDataSet, float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    int i = (int)((paramInt2 - paramInt1) * paramFloat1 + 1.0F) * 2;
    if (valuePointsForGenerateTransformedValuesScatter.length != i) {
      valuePointsForGenerateTransformedValuesScatter = new float[i];
    }
    float[] arrayOfFloat = valuePointsForGenerateTransformedValuesScatter;
    paramInt2 = 0;
    while (paramInt2 < i)
    {
      Entry localEntry = paramIScatterDataSet.getEntryForIndex(paramInt2 / 2 + paramInt1);
      if (localEntry != null)
      {
        arrayOfFloat[paramInt2] = localEntry.getX();
        arrayOfFloat[(paramInt2 + 1)] = (localEntry.getY() * paramFloat2);
      }
      else
      {
        arrayOfFloat[paramInt2] = 0.0F;
        arrayOfFloat[(paramInt2 + 1)] = 0.0F;
      }
      paramInt2 += 2;
    }
    getValueToPixelMatrix().mapPoints(arrayOfFloat);
    return arrayOfFloat;
  }
  
  public Matrix getOffsetMatrix()
  {
    return mMatrixOffset;
  }
  
  public MPPointD getPixelForValues(float paramFloat1, float paramFloat2)
  {
    ptsBuffer[0] = paramFloat1;
    ptsBuffer[1] = paramFloat2;
    pointValuesToPixel(ptsBuffer);
    return MPPointD.getInstance(ptsBuffer[0], ptsBuffer[1]);
  }
  
  public Matrix getPixelToValueMatrix()
  {
    getValueToPixelMatrix().invert(mMBuffer2);
    return mMBuffer2;
  }
  
  public Matrix getValueMatrix()
  {
    return mMatrixValueToPx;
  }
  
  public Matrix getValueToPixelMatrix()
  {
    mMBuffer1.set(mMatrixValueToPx);
    mMBuffer1.postConcat(mViewPortHandler.mMatrixTouch);
    mMBuffer1.postConcat(mMatrixOffset);
    return mMBuffer1;
  }
  
  public MPPointD getValuesByTouchPoint(float paramFloat1, float paramFloat2)
  {
    MPPointD localMPPointD = MPPointD.getInstance(0.0D, 0.0D);
    getValuesByTouchPoint(paramFloat1, paramFloat2, localMPPointD);
    return localMPPointD;
  }
  
  public void getValuesByTouchPoint(float paramFloat1, float paramFloat2, MPPointD paramMPPointD)
  {
    ptsBuffer[0] = paramFloat1;
    ptsBuffer[1] = paramFloat2;
    pixelsToValue(ptsBuffer);
    x = ptsBuffer[0];
    y = ptsBuffer[1];
  }
  
  public void pathValueToPixel(Path paramPath)
  {
    paramPath.transform(mMatrixValueToPx);
    paramPath.transform(mViewPortHandler.getMatrixTouch());
    paramPath.transform(mMatrixOffset);
  }
  
  public void pathValuesToPixel(List<Path> paramList)
  {
    int i = 0;
    while (i < paramList.size())
    {
      pathValueToPixel((Path)paramList.get(i));
      i += 1;
    }
  }
  
  public void pixelsToValue(float[] paramArrayOfFloat)
  {
    Matrix localMatrix = mPixelToValueMatrixBuffer;
    localMatrix.reset();
    mMatrixOffset.invert(localMatrix);
    localMatrix.mapPoints(paramArrayOfFloat);
    mViewPortHandler.getMatrixTouch().invert(localMatrix);
    localMatrix.mapPoints(paramArrayOfFloat);
    mMatrixValueToPx.invert(localMatrix);
    localMatrix.mapPoints(paramArrayOfFloat);
  }
  
  public void pointValuesToPixel(float[] paramArrayOfFloat)
  {
    mMatrixValueToPx.mapPoints(paramArrayOfFloat);
    mViewPortHandler.getMatrixTouch().mapPoints(paramArrayOfFloat);
    mMatrixOffset.mapPoints(paramArrayOfFloat);
  }
  
  public void prepareMatrixOffset(boolean paramBoolean)
  {
    mMatrixOffset.reset();
    if (!paramBoolean)
    {
      mMatrixOffset.postTranslate(mViewPortHandler.offsetLeft(), mViewPortHandler.getChartHeight() - mViewPortHandler.offsetBottom());
      return;
    }
    mMatrixOffset.setTranslate(mViewPortHandler.offsetLeft(), -mViewPortHandler.offsetTop());
    mMatrixOffset.postScale(1.0F, -1.0F);
  }
  
  public void prepareMatrixValuePx(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    float f2 = mViewPortHandler.contentWidth() / paramFloat2;
    float f1 = mViewPortHandler.contentHeight() / paramFloat3;
    paramFloat2 = f2;
    if (Float.isInfinite(f2)) {
      paramFloat2 = 0.0F;
    }
    paramFloat3 = f1;
    if (Float.isInfinite(f1)) {
      paramFloat3 = 0.0F;
    }
    mMatrixValueToPx.reset();
    mMatrixValueToPx.postTranslate(-paramFloat1, -paramFloat4);
    mMatrixValueToPx.postScale(paramFloat2, -paramFloat3);
  }
  
  public void rectToPixelPhase(RectF paramRectF, float paramFloat)
  {
    top *= paramFloat;
    bottom *= paramFloat;
    mMatrixValueToPx.mapRect(paramRectF);
    mViewPortHandler.getMatrixTouch().mapRect(paramRectF);
    mMatrixOffset.mapRect(paramRectF);
  }
  
  public void rectToPixelPhaseHorizontal(RectF paramRectF, float paramFloat)
  {
    left *= paramFloat;
    right *= paramFloat;
    mMatrixValueToPx.mapRect(paramRectF);
    mViewPortHandler.getMatrixTouch().mapRect(paramRectF);
    mMatrixOffset.mapRect(paramRectF);
  }
  
  public void rectValueToPixel(RectF paramRectF)
  {
    mMatrixValueToPx.mapRect(paramRectF);
    mViewPortHandler.getMatrixTouch().mapRect(paramRectF);
    mMatrixOffset.mapRect(paramRectF);
  }
  
  public void rectValueToPixelHorizontal(RectF paramRectF)
  {
    mMatrixValueToPx.mapRect(paramRectF);
    mViewPortHandler.getMatrixTouch().mapRect(paramRectF);
    mMatrixOffset.mapRect(paramRectF);
  }
  
  public void rectValueToPixelHorizontal(RectF paramRectF, float paramFloat)
  {
    left *= paramFloat;
    right *= paramFloat;
    mMatrixValueToPx.mapRect(paramRectF);
    mViewPortHandler.getMatrixTouch().mapRect(paramRectF);
    mMatrixOffset.mapRect(paramRectF);
  }
  
  public void rectValuesToPixel(List<RectF> paramList)
  {
    Matrix localMatrix = getValueToPixelMatrix();
    int i = 0;
    while (i < paramList.size())
    {
      localMatrix.mapRect((RectF)paramList.get(i));
      i += 1;
    }
  }
}

package com.github.mikephil.charting.utils;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.View;

public class ViewPortHandler
{
  protected Matrix mCenterViewPortMatrixBuffer = new Matrix();
  protected float mChartHeight = 0.0F;
  protected float mChartWidth = 0.0F;
  protected RectF mContentRect = new RectF();
  protected final Matrix mMatrixTouch = new Matrix();
  private float mMaxScaleX = Float.MAX_VALUE;
  private float mMaxScaleY = Float.MAX_VALUE;
  private float mMinScaleX = 1.0F;
  private float mMinScaleY = 1.0F;
  private float mScaleX = 1.0F;
  private float mScaleY = 1.0F;
  private float mTransOffsetX = 0.0F;
  private float mTransOffsetY = 0.0F;
  private float mTransX = 0.0F;
  private float mTransY = 0.0F;
  protected final float[] matrixBuffer = new float[9];
  protected float[] valsBufferForFitScreen = new float[9];
  
  public ViewPortHandler() {}
  
  public boolean canZoomInMoreX()
  {
    return mScaleX < mMaxScaleX;
  }
  
  public boolean canZoomInMoreY()
  {
    return mScaleY < mMaxScaleY;
  }
  
  public boolean canZoomOutMoreX()
  {
    return mScaleX > mMinScaleX;
  }
  
  public boolean canZoomOutMoreY()
  {
    return mScaleY > mMinScaleY;
  }
  
  public void centerViewPort(float[] paramArrayOfFloat, View paramView)
  {
    Matrix localMatrix = mCenterViewPortMatrixBuffer;
    localMatrix.reset();
    localMatrix.set(mMatrixTouch);
    float f1 = paramArrayOfFloat[0];
    float f2 = offsetLeft();
    float f3 = paramArrayOfFloat[1];
    float f4 = offsetTop();
    localMatrix.postTranslate(-(f1 - f2), -(f3 - f4));
    refresh(localMatrix, paramView, true);
  }
  
  public float contentBottom()
  {
    return mContentRect.bottom;
  }
  
  public float contentHeight()
  {
    return mContentRect.height();
  }
  
  public float contentLeft()
  {
    return mContentRect.left;
  }
  
  public float contentRight()
  {
    return mContentRect.right;
  }
  
  public float contentTop()
  {
    return mContentRect.top;
  }
  
  public float contentWidth()
  {
    return mContentRect.width();
  }
  
  public Matrix fitScreen()
  {
    Matrix localMatrix = new Matrix();
    fitScreen(localMatrix);
    return localMatrix;
  }
  
  public void fitScreen(Matrix paramMatrix)
  {
    mMinScaleX = 1.0F;
    mMinScaleY = 1.0F;
    paramMatrix.set(mMatrixTouch);
    float[] arrayOfFloat = valsBufferForFitScreen;
    int i = 0;
    while (i < 9)
    {
      arrayOfFloat[i] = 0.0F;
      i += 1;
    }
    paramMatrix.getValues(arrayOfFloat);
    arrayOfFloat[2] = 0.0F;
    arrayOfFloat[5] = 0.0F;
    arrayOfFloat[0] = 1.0F;
    arrayOfFloat[4] = 1.0F;
    paramMatrix.setValues(arrayOfFloat);
  }
  
  public float getChartHeight()
  {
    return mChartHeight;
  }
  
  public float getChartWidth()
  {
    return mChartWidth;
  }
  
  public MPPointF getContentCenter()
  {
    return MPPointF.getInstance(mContentRect.centerX(), mContentRect.centerY());
  }
  
  public RectF getContentRect()
  {
    return mContentRect;
  }
  
  public Matrix getMatrixTouch()
  {
    return mMatrixTouch;
  }
  
  public float getMaxScaleX()
  {
    return mMaxScaleX;
  }
  
  public float getMaxScaleY()
  {
    return mMaxScaleY;
  }
  
  public float getMinScaleX()
  {
    return mMinScaleX;
  }
  
  public float getMinScaleY()
  {
    return mMinScaleY;
  }
  
  public float getScaleX()
  {
    return mScaleX;
  }
  
  public float getScaleY()
  {
    return mScaleY;
  }
  
  public float getSmallestContentExtension()
  {
    return Math.min(mContentRect.width(), mContentRect.height());
  }
  
  public float getTransX()
  {
    return mTransX;
  }
  
  public float getTransY()
  {
    return mTransY;
  }
  
  public boolean hasChartDimens()
  {
    return (mChartHeight > 0.0F) && (mChartWidth > 0.0F);
  }
  
  public boolean hasNoDragOffset()
  {
    return (mTransOffsetX <= 0.0F) && (mTransOffsetY <= 0.0F);
  }
  
  public boolean isFullyZoomedOut()
  {
    return (isFullyZoomedOutX()) && (isFullyZoomedOutY());
  }
  
  public boolean isFullyZoomedOutX()
  {
    return (mScaleX <= mMinScaleX) && (mMinScaleX <= 1.0F);
  }
  
  public boolean isFullyZoomedOutY()
  {
    return (mScaleY <= mMinScaleY) && (mMinScaleY <= 1.0F);
  }
  
  public boolean isInBounds(float paramFloat1, float paramFloat2)
  {
    return (isInBoundsX(paramFloat1)) && (isInBoundsY(paramFloat2));
  }
  
  public boolean isInBoundsBottom(float paramFloat)
  {
    paramFloat = (int)(paramFloat * 100.0F) / 100.0F;
    return mContentRect.bottom >= paramFloat;
  }
  
  public boolean isInBoundsLeft(float paramFloat)
  {
    return mContentRect.left <= paramFloat + 1.0F;
  }
  
  public boolean isInBoundsRight(float paramFloat)
  {
    paramFloat = (int)(paramFloat * 100.0F) / 100.0F;
    return mContentRect.right >= paramFloat - 1.0F;
  }
  
  public boolean isInBoundsTop(float paramFloat)
  {
    return mContentRect.top <= paramFloat;
  }
  
  public boolean isInBoundsX(float paramFloat)
  {
    return (isInBoundsLeft(paramFloat)) && (isInBoundsRight(paramFloat));
  }
  
  public boolean isInBoundsY(float paramFloat)
  {
    return (isInBoundsTop(paramFloat)) && (isInBoundsBottom(paramFloat));
  }
  
  public void limitTransAndScale(Matrix paramMatrix, RectF paramRectF)
  {
    paramMatrix.getValues(matrixBuffer);
    float f3 = matrixBuffer[2];
    float f1 = matrixBuffer[0];
    float f4 = matrixBuffer[5];
    float f2 = matrixBuffer[4];
    mScaleX = Math.min(Math.max(mMinScaleX, f1), mMaxScaleX);
    mScaleY = Math.min(Math.max(mMinScaleY, f2), mMaxScaleY);
    f1 = 0.0F;
    if (paramRectF != null)
    {
      f1 = paramRectF.width();
      f2 = paramRectF.height();
    }
    else
    {
      f2 = 0.0F;
    }
    mTransX = Math.min(Math.max(f3, -f1 * (mScaleX - 1.0F) - mTransOffsetX), mTransOffsetX);
    mTransY = Math.max(Math.min(f4, f2 * (mScaleY - 1.0F) + mTransOffsetY), -mTransOffsetY);
    matrixBuffer[2] = mTransX;
    matrixBuffer[0] = mScaleX;
    matrixBuffer[5] = mTransY;
    matrixBuffer[4] = mScaleY;
    paramMatrix.setValues(matrixBuffer);
  }
  
  public float offsetBottom()
  {
    return mChartHeight - mContentRect.bottom;
  }
  
  public float offsetLeft()
  {
    return mContentRect.left;
  }
  
  public float offsetRight()
  {
    return mChartWidth - mContentRect.right;
  }
  
  public float offsetTop()
  {
    return mContentRect.top;
  }
  
  public Matrix refresh(Matrix paramMatrix, View paramView, boolean paramBoolean)
  {
    mMatrixTouch.set(paramMatrix);
    limitTransAndScale(mMatrixTouch, mContentRect);
    if (paramBoolean) {
      paramView.invalidate();
    }
    paramMatrix.set(mMatrixTouch);
    return paramMatrix;
  }
  
  public void resetZoom(Matrix paramMatrix)
  {
    paramMatrix.reset();
    paramMatrix.set(mMatrixTouch);
    paramMatrix.postScale(1.0F, 1.0F, 0.0F, 0.0F);
  }
  
  public void restrainViewPort(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    mContentRect.set(paramFloat1, paramFloat2, mChartWidth - paramFloat3, mChartHeight - paramFloat4);
  }
  
  public void setChartDimens(float paramFloat1, float paramFloat2)
  {
    float f1 = offsetLeft();
    float f2 = offsetTop();
    float f3 = offsetRight();
    float f4 = offsetBottom();
    mChartHeight = paramFloat2;
    mChartWidth = paramFloat1;
    restrainViewPort(f1, f2, f3, f4);
  }
  
  public void setDragOffsetX(float paramFloat)
  {
    mTransOffsetX = Utils.convertDpToPixel(paramFloat);
  }
  
  public void setDragOffsetY(float paramFloat)
  {
    mTransOffsetY = Utils.convertDpToPixel(paramFloat);
  }
  
  public void setMaximumScaleX(float paramFloat)
  {
    float f = paramFloat;
    if (paramFloat == 0.0F) {
      f = Float.MAX_VALUE;
    }
    mMaxScaleX = f;
    limitTransAndScale(mMatrixTouch, mContentRect);
  }
  
  public void setMaximumScaleY(float paramFloat)
  {
    float f = paramFloat;
    if (paramFloat == 0.0F) {
      f = Float.MAX_VALUE;
    }
    mMaxScaleY = f;
    limitTransAndScale(mMatrixTouch, mContentRect);
  }
  
  public void setMinMaxScaleX(float paramFloat1, float paramFloat2)
  {
    float f = paramFloat1;
    if (paramFloat1 < 1.0F) {
      f = 1.0F;
    }
    paramFloat1 = paramFloat2;
    if (paramFloat2 == 0.0F) {
      paramFloat1 = Float.MAX_VALUE;
    }
    mMinScaleX = f;
    mMaxScaleX = paramFloat1;
    limitTransAndScale(mMatrixTouch, mContentRect);
  }
  
  public void setMinMaxScaleY(float paramFloat1, float paramFloat2)
  {
    float f = paramFloat1;
    if (paramFloat1 < 1.0F) {
      f = 1.0F;
    }
    paramFloat1 = paramFloat2;
    if (paramFloat2 == 0.0F) {
      paramFloat1 = Float.MAX_VALUE;
    }
    mMinScaleY = f;
    mMaxScaleY = paramFloat1;
    limitTransAndScale(mMatrixTouch, mContentRect);
  }
  
  public void setMinimumScaleX(float paramFloat)
  {
    float f = paramFloat;
    if (paramFloat < 1.0F) {
      f = 1.0F;
    }
    mMinScaleX = f;
    limitTransAndScale(mMatrixTouch, mContentRect);
  }
  
  public void setMinimumScaleY(float paramFloat)
  {
    float f = paramFloat;
    if (paramFloat < 1.0F) {
      f = 1.0F;
    }
    mMinScaleY = f;
    limitTransAndScale(mMatrixTouch, mContentRect);
  }
  
  public Matrix setZoom(float paramFloat1, float paramFloat2)
  {
    Matrix localMatrix = new Matrix();
    setZoom(paramFloat1, paramFloat2, localMatrix);
    return localMatrix;
  }
  
  public Matrix setZoom(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    Matrix localMatrix = new Matrix();
    localMatrix.set(mMatrixTouch);
    localMatrix.setScale(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
    return localMatrix;
  }
  
  public void setZoom(float paramFloat1, float paramFloat2, Matrix paramMatrix)
  {
    paramMatrix.reset();
    paramMatrix.set(mMatrixTouch);
    paramMatrix.setScale(paramFloat1, paramFloat2);
  }
  
  public Matrix translate(float[] paramArrayOfFloat)
  {
    Matrix localMatrix = new Matrix();
    translate(paramArrayOfFloat, localMatrix);
    return localMatrix;
  }
  
  public void translate(float[] paramArrayOfFloat, Matrix paramMatrix)
  {
    paramMatrix.reset();
    paramMatrix.set(mMatrixTouch);
    float f1 = paramArrayOfFloat[0];
    float f2 = offsetLeft();
    float f3 = paramArrayOfFloat[1];
    float f4 = offsetTop();
    paramMatrix.postTranslate(-(f1 - f2), -(f3 - f4));
  }
  
  public Matrix zoom(float paramFloat1, float paramFloat2)
  {
    Matrix localMatrix = new Matrix();
    zoom(paramFloat1, paramFloat2, localMatrix);
    return localMatrix;
  }
  
  public Matrix zoom(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    Matrix localMatrix = new Matrix();
    zoom(paramFloat1, paramFloat2, paramFloat3, paramFloat4, localMatrix);
    return localMatrix;
  }
  
  public void zoom(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Matrix paramMatrix)
  {
    paramMatrix.reset();
    paramMatrix.set(mMatrixTouch);
    paramMatrix.postScale(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  public void zoom(float paramFloat1, float paramFloat2, Matrix paramMatrix)
  {
    paramMatrix.reset();
    paramMatrix.set(mMatrixTouch);
    paramMatrix.postScale(paramFloat1, paramFloat2);
  }
  
  public Matrix zoomIn(float paramFloat1, float paramFloat2)
  {
    Matrix localMatrix = new Matrix();
    zoomIn(paramFloat1, paramFloat2, localMatrix);
    return localMatrix;
  }
  
  public void zoomIn(float paramFloat1, float paramFloat2, Matrix paramMatrix)
  {
    paramMatrix.reset();
    paramMatrix.set(mMatrixTouch);
    paramMatrix.postScale(1.4F, 1.4F, paramFloat1, paramFloat2);
  }
  
  public Matrix zoomOut(float paramFloat1, float paramFloat2)
  {
    Matrix localMatrix = new Matrix();
    zoomOut(paramFloat1, paramFloat2, localMatrix);
    return localMatrix;
  }
  
  public void zoomOut(float paramFloat1, float paramFloat2, Matrix paramMatrix)
  {
    paramMatrix.reset();
    paramMatrix.set(mMatrixTouch);
    paramMatrix.postScale(0.7F, 0.7F, paramFloat1, paramFloat2);
  }
}

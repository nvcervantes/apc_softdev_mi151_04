package com.github.mikephil.charting.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import java.util.List;

public abstract class Utils
{
  public static final double DEG2RAD = 0.017453292519943295D;
  public static final double DOUBLE_EPSILON = Double.longBitsToDouble(1L);
  public static final float FDEG2RAD = 0.017453292F;
  public static final float FLOAT_EPSILON = Float.intBitsToFloat(1);
  private static final int[] POW_10;
  private static Rect mCalcTextHeightRect = new Rect();
  private static Rect mCalcTextSizeRect;
  private static IValueFormatter mDefaultValueFormatter;
  private static Rect mDrawTextRectBuffer = new Rect();
  private static Rect mDrawableBoundsCache;
  private static Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();
  private static Paint.FontMetrics mFontMetricsBuffer = new Paint.FontMetrics();
  private static int mMaximumFlingVelocity = 8000;
  private static DisplayMetrics mMetrics;
  private static int mMinimumFlingVelocity = 50;
  
  static
  {
    mCalcTextSizeRect = new Rect();
    POW_10 = new int[] { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000 };
    mDefaultValueFormatter = generateDefaultValueFormatter();
    mDrawableBoundsCache = new Rect();
  }
  
  public Utils() {}
  
  public static int calcTextHeight(Paint paramPaint, String paramString)
  {
    Rect localRect = mCalcTextHeightRect;
    localRect.set(0, 0, 0, 0);
    paramPaint.getTextBounds(paramString, 0, paramString.length(), localRect);
    return localRect.height();
  }
  
  public static FSize calcTextSize(Paint paramPaint, String paramString)
  {
    FSize localFSize = FSize.getInstance(0.0F, 0.0F);
    calcTextSize(paramPaint, paramString, localFSize);
    return localFSize;
  }
  
  public static void calcTextSize(Paint paramPaint, String paramString, FSize paramFSize)
  {
    Rect localRect = mCalcTextSizeRect;
    localRect.set(0, 0, 0, 0);
    paramPaint.getTextBounds(paramString, 0, paramString.length(), localRect);
    width = localRect.width();
    height = localRect.height();
  }
  
  public static int calcTextWidth(Paint paramPaint, String paramString)
  {
    return (int)paramPaint.measureText(paramString);
  }
  
  public static float convertDpToPixel(float paramFloat)
  {
    if (mMetrics == null)
    {
      Log.e("MPChartLib-Utils", "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before calling Utils.convertDpToPixel(...). Otherwise conversion does not take place.");
      return paramFloat;
    }
    return paramFloat * mMetricsdensity;
  }
  
  public static int[] convertIntegers(List<Integer> paramList)
  {
    int[] arrayOfInt = new int[paramList.size()];
    copyIntegers(paramList, arrayOfInt);
    return arrayOfInt;
  }
  
  public static float convertPixelsToDp(float paramFloat)
  {
    if (mMetrics == null)
    {
      Log.e("MPChartLib-Utils", "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before calling Utils.convertPixelsToDp(...). Otherwise conversion does not take place.");
      return paramFloat;
    }
    return paramFloat / mMetricsdensity;
  }
  
  public static String[] convertStrings(List<String> paramList)
  {
    String[] arrayOfString = new String[paramList.size()];
    int i = 0;
    while (i < arrayOfString.length)
    {
      arrayOfString[i] = ((String)paramList.get(i));
      i += 1;
    }
    return arrayOfString;
  }
  
  public static void copyIntegers(List<Integer> paramList, int[] paramArrayOfInt)
  {
    int i;
    if (paramArrayOfInt.length < paramList.size()) {
      i = paramArrayOfInt.length;
    } else {
      i = paramList.size();
    }
    int j = 0;
    while (j < i)
    {
      paramArrayOfInt[j] = ((Integer)paramList.get(j)).intValue();
      j += 1;
    }
  }
  
  public static void copyStrings(List<String> paramList, String[] paramArrayOfString)
  {
    int i;
    if (paramArrayOfString.length < paramList.size()) {
      i = paramArrayOfString.length;
    } else {
      i = paramList.size();
    }
    int j = 0;
    while (j < i)
    {
      paramArrayOfString[j] = ((String)paramList.get(j));
      j += 1;
    }
  }
  
  public static void drawImage(Canvas paramCanvas, Drawable paramDrawable, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    MPPointF localMPPointF = MPPointF.getInstance();
    x = (paramInt1 - paramInt3 / 2);
    y = (paramInt2 - paramInt4 / 2);
    paramDrawable.copyBounds(mDrawableBoundsCache);
    paramDrawable.setBounds(mDrawableBoundsCacheleft, mDrawableBoundsCachetop, mDrawableBoundsCacheleft + paramInt3, mDrawableBoundsCachetop + paramInt3);
    paramInt1 = paramCanvas.save();
    paramCanvas.translate(x, y);
    paramDrawable.draw(paramCanvas);
    paramCanvas.restoreToCount(paramInt1);
  }
  
  public static void drawMultilineText(Canvas paramCanvas, StaticLayout paramStaticLayout, float paramFloat1, float paramFloat2, TextPaint paramTextPaint, MPPointF paramMPPointF, float paramFloat3)
  {
    float f1 = paramTextPaint.getFontMetrics(mFontMetricsBuffer);
    float f5 = paramStaticLayout.getWidth();
    float f6 = paramStaticLayout.getLineCount() * f1;
    float f4 = 0.0F - mDrawTextRectBufferleft;
    float f3 = 0.0F + f6;
    Paint.Align localAlign = paramTextPaint.getTextAlign();
    paramTextPaint.setTextAlign(Paint.Align.LEFT);
    if (paramFloat3 != 0.0F)
    {
      float f2;
      if (x == 0.5F)
      {
        f2 = paramFloat1;
        f1 = paramFloat2;
        if (y == 0.5F) {}
      }
      else
      {
        FSize localFSize = getSizeOfRotatedRectangleByDegrees(f5, f6, paramFloat3);
        f2 = paramFloat1 - width * (x - 0.5F);
        f1 = paramFloat2 - height * (y - 0.5F);
        FSize.recycleInstance(localFSize);
      }
      paramCanvas.save();
      paramCanvas.translate(f2, f1);
      paramCanvas.rotate(paramFloat3);
      paramCanvas.translate(f4 - f5 * 0.5F, f3 - f6 * 0.5F);
      paramStaticLayout.draw(paramCanvas);
      paramCanvas.restore();
    }
    else
    {
      if (x == 0.0F)
      {
        f1 = f4;
        paramFloat3 = f3;
        if (y == 0.0F) {}
      }
      else
      {
        f1 = f4 - f5 * x;
        paramFloat3 = f3 - f6 * y;
      }
      paramCanvas.save();
      paramCanvas.translate(f1 + paramFloat1, paramFloat3 + paramFloat2);
      paramStaticLayout.draw(paramCanvas);
      paramCanvas.restore();
    }
    paramTextPaint.setTextAlign(localAlign);
  }
  
  public static void drawMultilineText(Canvas paramCanvas, String paramString, float paramFloat1, float paramFloat2, TextPaint paramTextPaint, FSize paramFSize, MPPointF paramMPPointF, float paramFloat3)
  {
    drawMultilineText(paramCanvas, new StaticLayout(paramString, 0, paramString.length(), paramTextPaint, (int)Math.max(Math.ceil(width), 1.0D), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false), paramFloat1, paramFloat2, paramTextPaint, paramMPPointF, paramFloat3);
  }
  
  public static void drawXAxisValue(Canvas paramCanvas, String paramString, float paramFloat1, float paramFloat2, Paint paramPaint, MPPointF paramMPPointF, float paramFloat3)
  {
    float f5 = paramPaint.getFontMetrics(mFontMetricsBuffer);
    paramPaint.getTextBounds(paramString, 0, paramString.length(), mDrawTextRectBuffer);
    float f4 = 0.0F - mDrawTextRectBufferleft;
    float f3 = -mFontMetricsBufferascent + 0.0F;
    Paint.Align localAlign = paramPaint.getTextAlign();
    paramPaint.setTextAlign(Paint.Align.LEFT);
    float f1;
    if (paramFloat3 != 0.0F)
    {
      float f6 = mDrawTextRectBuffer.width();
      float f2;
      if (x == 0.5F)
      {
        f2 = paramFloat1;
        f1 = paramFloat2;
        if (y == 0.5F) {}
      }
      else
      {
        FSize localFSize = getSizeOfRotatedRectangleByDegrees(mDrawTextRectBuffer.width(), f5, paramFloat3);
        f2 = paramFloat1 - width * (x - 0.5F);
        f1 = paramFloat2 - height * (y - 0.5F);
        FSize.recycleInstance(localFSize);
      }
      paramCanvas.save();
      paramCanvas.translate(f2, f1);
      paramCanvas.rotate(paramFloat3);
      paramCanvas.drawText(paramString, f4 - f6 * 0.5F, f3 - f5 * 0.5F, paramPaint);
      paramCanvas.restore();
    }
    else
    {
      if (x == 0.0F)
      {
        f1 = f4;
        paramFloat3 = f3;
        if (y == 0.0F) {}
      }
      else
      {
        f1 = f4 - mDrawTextRectBuffer.width() * x;
        paramFloat3 = f3 - f5 * y;
      }
      paramCanvas.drawText(paramString, f1 + paramFloat1, paramFloat3 + paramFloat2, paramPaint);
    }
    paramPaint.setTextAlign(localAlign);
  }
  
  public static String formatNumber(float paramFloat, int paramInt, boolean paramBoolean)
  {
    return formatNumber(paramFloat, paramInt, paramBoolean, '.');
  }
  
  public static String formatNumber(float paramFloat, int paramInt, boolean paramBoolean, char paramChar)
  {
    char[] arrayOfChar = new char[35];
    if (paramFloat == 0.0F) {
      return "0";
    }
    int i = 0;
    int m;
    if ((paramFloat < 1.0F) && (paramFloat > -1.0F)) {
      m = 1;
    } else {
      m = 0;
    }
    int k;
    if (paramFloat < 0.0F)
    {
      paramFloat = -paramFloat;
      k = 1;
    }
    else
    {
      k = 0;
    }
    int j;
    if (paramInt > POW_10.length) {
      j = POW_10.length - 1;
    } else {
      j = paramInt;
    }
    long l = Math.round(paramFloat * POW_10[j]);
    int i1 = arrayOfChar.length - 1;
    int n = 0;
    paramInt = i;
    i = i1;
    label350:
    for (;;)
    {
      if ((l == 0L) && (paramInt >= j + 1))
      {
        n = i;
        j = paramInt;
        if (m != 0)
        {
          arrayOfChar[i] = '0';
          j = paramInt + 1;
          n = i - 1;
        }
        paramInt = j;
        if (k != 0)
        {
          arrayOfChar[n] = '-';
          paramInt = j + 1;
        }
        paramInt = arrayOfChar.length - paramInt;
        return String.valueOf(arrayOfChar, paramInt, arrayOfChar.length - paramInt);
      }
      int i2 = (int)(l % 10L);
      l /= 10L;
      i1 = i - 1;
      arrayOfChar[i] = ((char)(i2 + 48));
      paramInt += 1;
      if (paramInt == j)
      {
        arrayOfChar[i1] = ',';
        paramInt += 1;
        i = i1 - 1;
        n = 1;
      }
      else
      {
        if ((paramBoolean) && (l != 0L) && (paramInt > j)) {
          if (n != 0)
          {
            if ((paramInt - j) % 4 == 0)
            {
              i = i1 - 1;
              arrayOfChar[i1] = paramChar;
              paramInt += 1;
              break label350;
            }
          }
          else if ((paramInt - j) % 4 == 3)
          {
            i = i1 - 1;
            arrayOfChar[i1] = paramChar;
            paramInt += 1;
            break label350;
          }
        }
        i = i1;
      }
    }
  }
  
  private static IValueFormatter generateDefaultValueFormatter()
  {
    return new DefaultValueFormatter(1);
  }
  
  public static int getDecimals(float paramFloat)
  {
    paramFloat = roundToNextSignificant(paramFloat);
    if (Float.isInfinite(paramFloat)) {
      return 0;
    }
    return (int)Math.ceil(-Math.log10(paramFloat)) + 2;
  }
  
  public static IValueFormatter getDefaultValueFormatter()
  {
    return mDefaultValueFormatter;
  }
  
  public static float getLineHeight(Paint paramPaint)
  {
    return getLineHeight(paramPaint, mFontMetrics);
  }
  
  public static float getLineHeight(Paint paramPaint, Paint.FontMetrics paramFontMetrics)
  {
    paramPaint.getFontMetrics(paramFontMetrics);
    return descent - ascent;
  }
  
  public static float getLineSpacing(Paint paramPaint)
  {
    return getLineSpacing(paramPaint, mFontMetrics);
  }
  
  public static float getLineSpacing(Paint paramPaint, Paint.FontMetrics paramFontMetrics)
  {
    paramPaint.getFontMetrics(paramFontMetrics);
    return ascent - top + bottom;
  }
  
  public static int getMaximumFlingVelocity()
  {
    return mMaximumFlingVelocity;
  }
  
  public static int getMinimumFlingVelocity()
  {
    return mMinimumFlingVelocity;
  }
  
  public static float getNormalizedAngle(float paramFloat)
  {
    while (paramFloat < 0.0F) {
      paramFloat += 360.0F;
    }
    return paramFloat % 360.0F;
  }
  
  public static MPPointF getPosition(MPPointF paramMPPointF, float paramFloat1, float paramFloat2)
  {
    MPPointF localMPPointF = MPPointF.getInstance(0.0F, 0.0F);
    getPosition(paramMPPointF, paramFloat1, paramFloat2, localMPPointF);
    return localMPPointF;
  }
  
  public static void getPosition(MPPointF paramMPPointF1, float paramFloat1, float paramFloat2, MPPointF paramMPPointF2)
  {
    double d1 = x;
    double d2 = paramFloat1;
    double d3 = paramFloat2;
    x = ((float)(d1 + Math.cos(Math.toRadians(d3)) * d2));
    y = ((float)(y + d2 * Math.sin(Math.toRadians(d3))));
  }
  
  public static int getSDKInt()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static FSize getSizeOfRotatedRectangleByDegrees(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return getSizeOfRotatedRectangleByRadians(paramFloat1, paramFloat2, paramFloat3 * 0.017453292F);
  }
  
  public static FSize getSizeOfRotatedRectangleByDegrees(FSize paramFSize, float paramFloat)
  {
    return getSizeOfRotatedRectangleByRadians(width, height, paramFloat * 0.017453292F);
  }
  
  public static FSize getSizeOfRotatedRectangleByRadians(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    double d = paramFloat3;
    return FSize.getInstance(Math.abs((float)Math.cos(d) * paramFloat1) + Math.abs((float)Math.sin(d) * paramFloat2), Math.abs(paramFloat1 * (float)Math.sin(d)) + Math.abs(paramFloat2 * (float)Math.cos(d)));
  }
  
  public static FSize getSizeOfRotatedRectangleByRadians(FSize paramFSize, float paramFloat)
  {
    return getSizeOfRotatedRectangleByRadians(width, height, paramFloat);
  }
  
  public static void init(Context paramContext)
  {
    if (paramContext == null)
    {
      mMinimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
      mMaximumFlingVelocity = ViewConfiguration.getMaximumFlingVelocity();
      Log.e("MPChartLib-Utils", "Utils.init(...) PROVIDED CONTEXT OBJECT IS NULL");
      return;
    }
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(paramContext);
    mMinimumFlingVelocity = localViewConfiguration.getScaledMinimumFlingVelocity();
    mMaximumFlingVelocity = localViewConfiguration.getScaledMaximumFlingVelocity();
    mMetrics = paramContext.getResources().getDisplayMetrics();
  }
  
  @Deprecated
  public static void init(Resources paramResources)
  {
    mMetrics = paramResources.getDisplayMetrics();
    mMinimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
    mMaximumFlingVelocity = ViewConfiguration.getMaximumFlingVelocity();
  }
  
  public static double nextUp(double paramDouble)
  {
    if (paramDouble == Double.POSITIVE_INFINITY) {
      return paramDouble;
    }
    paramDouble += 0.0D;
    long l2 = Double.doubleToRawLongBits(paramDouble);
    long l1;
    if (paramDouble >= 0.0D) {
      l1 = 1L;
    } else {
      l1 = -1L;
    }
    return Double.longBitsToDouble(l2 + l1);
  }
  
  @SuppressLint({"NewApi"})
  public static void postInvalidateOnAnimation(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramView.postInvalidateOnAnimation();
      return;
    }
    paramView.postInvalidateDelayed(10L);
  }
  
  public static float roundToNextSignificant(double paramDouble)
  {
    if ((!Double.isInfinite(paramDouble)) && (!Double.isNaN(paramDouble)) && (paramDouble != 0.0D))
    {
      double d;
      if (paramDouble < 0.0D) {
        d = -paramDouble;
      } else {
        d = paramDouble;
      }
      float f = (float)Math.pow(10.0D, 1 - (int)(float)Math.ceil((float)Math.log10(d)));
      return (float)Math.round(paramDouble * f) / f;
    }
    return 0.0F;
  }
  
  public static void velocityTrackerPointerUpCleanUpIfNecessary(MotionEvent paramMotionEvent, VelocityTracker paramVelocityTracker)
  {
    paramVelocityTracker.computeCurrentVelocity(1000, mMaximumFlingVelocity);
    int j = paramMotionEvent.getActionIndex();
    int i = paramMotionEvent.getPointerId(j);
    float f1 = paramVelocityTracker.getXVelocity(i);
    float f2 = paramVelocityTracker.getYVelocity(i);
    int k = paramMotionEvent.getPointerCount();
    i = 0;
    while (i < k)
    {
      if (i != j)
      {
        int m = paramMotionEvent.getPointerId(i);
        if (paramVelocityTracker.getXVelocity(m) * f1 + paramVelocityTracker.getYVelocity(m) * f2 < 0.0F)
        {
          paramVelocityTracker.clear();
          return;
        }
      }
      i += 1;
    }
  }
}

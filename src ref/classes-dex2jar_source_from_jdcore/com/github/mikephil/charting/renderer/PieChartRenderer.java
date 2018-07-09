package com.github.mikephil.charting.renderer;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet.ValuePosition;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

public class PieChartRenderer
  extends DataRenderer
{
  protected Canvas mBitmapCanvas;
  private RectF mCenterTextLastBounds = new RectF();
  private CharSequence mCenterTextLastValue;
  private StaticLayout mCenterTextLayout;
  private TextPaint mCenterTextPaint;
  protected PieChart mChart;
  protected WeakReference<Bitmap> mDrawBitmap;
  protected Path mDrawCenterTextPathBuffer = new Path();
  protected RectF mDrawHighlightedRectF = new RectF();
  private Paint mEntryLabelsPaint;
  private Path mHoleCirclePath = new Path();
  protected Paint mHolePaint;
  private RectF mInnerRectBuffer = new RectF();
  private Path mPathBuffer = new Path();
  private RectF[] mRectBuffer = { new RectF(), new RectF(), new RectF() };
  protected Paint mTransparentCirclePaint;
  protected Paint mValueLinePaint;
  
  public PieChartRenderer(PieChart paramPieChart, ChartAnimator paramChartAnimator, ViewPortHandler paramViewPortHandler)
  {
    super(paramChartAnimator, paramViewPortHandler);
    mChart = paramPieChart;
    mHolePaint = new Paint(1);
    mHolePaint.setColor(-1);
    mHolePaint.setStyle(Paint.Style.FILL);
    mTransparentCirclePaint = new Paint(1);
    mTransparentCirclePaint.setColor(-1);
    mTransparentCirclePaint.setStyle(Paint.Style.FILL);
    mTransparentCirclePaint.setAlpha(105);
    mCenterTextPaint = new TextPaint(1);
    mCenterTextPaint.setColor(-16777216);
    mCenterTextPaint.setTextSize(Utils.convertDpToPixel(12.0F));
    mValuePaint.setTextSize(Utils.convertDpToPixel(13.0F));
    mValuePaint.setColor(-1);
    mValuePaint.setTextAlign(Paint.Align.CENTER);
    mEntryLabelsPaint = new Paint(1);
    mEntryLabelsPaint.setColor(-1);
    mEntryLabelsPaint.setTextAlign(Paint.Align.CENTER);
    mEntryLabelsPaint.setTextSize(Utils.convertDpToPixel(13.0F));
    mValueLinePaint = new Paint(1);
    mValueLinePaint.setStyle(Paint.Style.STROKE);
  }
  
  protected float calculateMinimumRadiusForSpacedSlice(MPPointF paramMPPointF, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
  {
    float f3 = paramFloat6 / 2.0F;
    float f1 = x;
    double d = (paramFloat5 + paramFloat6) * 0.017453292F;
    paramFloat6 = f1 + (float)Math.cos(d) * paramFloat1;
    f1 = y + (float)Math.sin(d) * paramFloat1;
    float f2 = x;
    d = (paramFloat5 + f3) * 0.017453292F;
    paramFloat5 = (float)Math.cos(d);
    f3 = y;
    float f4 = (float)Math.sin(d);
    return (float)(paramFloat1 - (float)(Math.sqrt(Math.pow(paramFloat6 - paramFloat3, 2.0D) + Math.pow(f1 - paramFloat4, 2.0D)) / 2.0D * Math.tan(0.017453292519943295D * ((180.0D - paramFloat2) / 2.0D))) - Math.sqrt(Math.pow(f2 + paramFloat5 * paramFloat1 - (paramFloat6 + paramFloat3) / 2.0F, 2.0D) + Math.pow(f3 + f4 * paramFloat1 - (f1 + paramFloat4) / 2.0F, 2.0D)));
  }
  
  protected void drawCenterText(Canvas paramCanvas)
  {
    Object localObject = mChart.getCenterText();
    if ((mChart.isDrawCenterTextEnabled()) && (localObject != null))
    {
      MPPointF localMPPointF1 = mChart.getCenterCircleBox();
      MPPointF localMPPointF2 = mChart.getCenterTextOffset();
      float f2 = x + x;
      float f3 = y + y;
      if ((mChart.isDrawHoleEnabled()) && (!mChart.isDrawSlicesUnderHoleEnabled())) {
        f1 = mChart.getRadius() * (mChart.getHoleRadius() / 100.0F);
      } else {
        f1 = mChart.getRadius();
      }
      RectF localRectF1 = mRectBuffer[0];
      left = (f2 - f1);
      top = (f3 - f1);
      right = (f2 + f1);
      bottom = (f3 + f1);
      RectF localRectF2 = mRectBuffer[1];
      localRectF2.set(localRectF1);
      float f1 = mChart.getCenterTextRadiusPercent() / 100.0F;
      if (f1 > 0.0D) {
        localRectF2.inset((localRectF2.width() - localRectF2.width() * f1) / 2.0F, (localRectF2.height() - localRectF2.height() * f1) / 2.0F);
      }
      if ((localObject.equals(mCenterTextLastValue)) && (localRectF2.equals(mCenterTextLastBounds))) {
        break label321;
      }
      mCenterTextLastBounds.set(localRectF2);
      mCenterTextLastValue = ((CharSequence)localObject);
      f1 = mCenterTextLastBounds.width();
      mCenterTextLayout = new StaticLayout((CharSequence)localObject, 0, ((CharSequence)localObject).length(), mCenterTextPaint, (int)Math.max(Math.ceil(f1), 1.0D), Layout.Alignment.ALIGN_CENTER, 1.0F, 0.0F, false);
      label321:
      f1 = mCenterTextLayout.getHeight();
      paramCanvas.save();
      if (Build.VERSION.SDK_INT >= 18)
      {
        localObject = mDrawCenterTextPathBuffer;
        ((Path)localObject).reset();
        ((Path)localObject).addOval(localRectF1, Path.Direction.CW);
        paramCanvas.clipPath((Path)localObject);
      }
      paramCanvas.translate(left, top + (localRectF2.height() - f1) / 2.0F);
      mCenterTextLayout.draw(paramCanvas);
      paramCanvas.restore();
      MPPointF.recycleInstance(localMPPointF1);
      MPPointF.recycleInstance(localMPPointF2);
    }
  }
  
  public void drawData(Canvas paramCanvas)
  {
    int i = (int)mViewPortHandler.getChartWidth();
    int j = (int)mViewPortHandler.getChartHeight();
    if ((mDrawBitmap == null) || (((Bitmap)mDrawBitmap.get()).getWidth() != i) || (((Bitmap)mDrawBitmap.get()).getHeight() != j))
    {
      if ((i > 0) && (j > 0))
      {
        mDrawBitmap = new WeakReference(Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_4444));
        mBitmapCanvas = new Canvas((Bitmap)mDrawBitmap.get());
      }
    }
    else
    {
      ((Bitmap)mDrawBitmap.get()).eraseColor(0);
      Iterator localIterator = ((PieData)mChart.getData()).getDataSets().iterator();
      while (localIterator.hasNext())
      {
        IPieDataSet localIPieDataSet = (IPieDataSet)localIterator.next();
        if ((localIPieDataSet.isVisible()) && (localIPieDataSet.getEntryCount() > 0)) {
          drawDataSet(paramCanvas, localIPieDataSet);
        }
      }
      return;
    }
  }
  
  protected void drawDataSet(Canvas paramCanvas, IPieDataSet paramIPieDataSet)
  {
    Object localObject1 = this;
    Object localObject2 = paramIPieDataSet;
    float f6 = mChart.getRotationAngle();
    float f3 = mAnimator.getPhaseX();
    float f11 = mAnimator.getPhaseY();
    RectF localRectF = mChart.getCircleBox();
    int i1 = paramIPieDataSet.getEntryCount();
    float[] arrayOfFloat = mChart.getDrawAngles();
    paramCanvas = mChart.getCenterCircleBox();
    float f7 = mChart.getRadius();
    boolean bool = mChart.isDrawHoleEnabled();
    int n = 1;
    int k;
    if ((bool) && (!mChart.isDrawSlicesUnderHoleEnabled())) {
      k = 1;
    } else {
      k = 0;
    }
    float f2;
    if (k != 0) {
      f2 = mChart.getHoleRadius() / 100.0F * f7;
    } else {
      f2 = 0.0F;
    }
    int i = 0;
    for (int j = i; i < i1; j = m)
    {
      m = j;
      if (Math.abs(((PieEntry)((IPieDataSet)localObject2).getEntryForIndex(i)).getY()) > Utils.FLOAT_EPSILON) {
        m = j + 1;
      }
      i += 1;
    }
    float f5;
    if (j <= 1) {
      f5 = 0.0F;
    } else {
      f5 = ((PieChartRenderer)localObject1).getSliceSpace((IPieDataSet)localObject2);
    }
    int i2 = 0;
    float f8 = 0.0F;
    int m = i1;
    i = n;
    n = i2;
    for (;;)
    {
      localObject2 = paramIPieDataSet;
      if (n >= m) {
        break;
      }
      float f12 = arrayOfFloat[n];
      if ((Math.abs(((IPieDataSet)localObject2).getEntryForIndex(n).getY()) > Utils.FLOAT_EPSILON) && (!mChart.needsHighlight(n)))
      {
        if ((f5 > 0.0F) && (f12 <= 180.0F)) {
          i1 = i;
        } else {
          i1 = 0;
        }
        mRenderPaint.setColor(((IPieDataSet)localObject2).getColor(n));
        if (j == 1) {
          f1 = 0.0F;
        } else {
          f1 = f5 / (0.017453292F * f7);
        }
        float f9 = f6 + (f8 + f1 / 2.0F) * f11;
        float f4 = (f12 - f1) * f11;
        float f1 = f4;
        if (f4 < 0.0F) {
          f1 = 0.0F;
        }
        mPathBuffer.reset();
        f4 = x;
        double d = f9 * 0.017453292F;
        float f10 = f4 + (float)Math.cos(d) * f7;
        float f13 = y + (float)Math.sin(d) * f7;
        if ((f1 >= 360.0F) && (f1 % 360.0F <= Utils.FLOAT_EPSILON))
        {
          mPathBuffer.addCircle(x, y, f7, Path.Direction.CW);
        }
        else
        {
          mPathBuffer.moveTo(f10, f13);
          mPathBuffer.arcTo(localRectF, f9, f1);
        }
        mInnerRectBuffer.set(x - f2, y - f2, x + f2, y + f2);
        if (k != 0)
        {
          if ((f2 <= 0.0F) && (i1 == 0)) {
            break label857;
          }
          if (i1 != 0)
          {
            f9 = ((PieChartRenderer)localObject1).calculateMinimumRadiusForSpacedSlice(paramCanvas, f7, f12 * f11, f10, f13, f9, f1);
            f4 = f9;
            if (f9 < 0.0F) {
              f4 = -f9;
            }
            f4 = Math.max(f2, f4);
          }
          else
          {
            f4 = f2;
          }
          f9 = f1;
          if ((j != 1) && (f4 != 0.0F)) {
            f1 = f5 / (0.017453292F * f4);
          } else {
            f1 = 0.0F;
          }
          f13 = f1 / 2.0F;
          f10 = (f12 - f1) * f11;
          f1 = f10;
          if (f10 < 0.0F) {
            f1 = 0.0F;
          }
          f10 = (f8 + f13) * f11 + f6 + f1;
          if ((f9 >= 360.0F) && (f9 % 360.0F <= Utils.FLOAT_EPSILON))
          {
            mPathBuffer.addCircle(x, y, f4, Path.Direction.CCW);
          }
          else
          {
            localObject1 = this;
            localObject2 = mPathBuffer;
            f9 = x;
            d = f10 * 0.017453292F;
            ((Path)localObject2).lineTo(f9 + (float)Math.cos(d) * f4, y + f4 * (float)Math.sin(d));
            mPathBuffer.arcTo(mInnerRectBuffer, f10, -f1);
          }
          i = 1;
          localObject1 = this;
          localObject2 = paramCanvas;
          paramCanvas = (Canvas)localObject1;
          break label1027;
        }
        label857:
        i2 = 1;
        Canvas localCanvas = paramCanvas;
        paramCanvas = (Canvas)localObject1;
        localObject2 = localCanvas;
        i = i2;
        if (f1 % 360.0F > Utils.FLOAT_EPSILON) {
          if (i1 != 0)
          {
            f4 = f1 / 2.0F;
            f1 = ((PieChartRenderer)localObject1).calculateMinimumRadiusForSpacedSlice(localCanvas, f7, f12 * f11, f10, f13, f9, f1);
            f10 = x;
            d = (f9 + f4) * 0.017453292F;
            f4 = (float)Math.cos(d);
            f9 = y;
            f13 = (float)Math.sin(d);
            mPathBuffer.lineTo(f10 + f4 * f1, f9 + f1 * f13);
            paramCanvas = (Canvas)localObject1;
            localObject2 = localCanvas;
            i = i2;
          }
          else
          {
            mPathBuffer.lineTo(x, y);
            i = i2;
            localObject2 = localCanvas;
            paramCanvas = (Canvas)localObject1;
          }
        }
        label1027:
        mPathBuffer.close();
        mBitmapCanvas.drawPath(mPathBuffer, mRenderPaint);
        localObject1 = paramCanvas;
      }
      else
      {
        localObject2 = paramCanvas;
      }
      f8 += f12 * f3;
      n += 1;
      paramCanvas = (Canvas)localObject2;
    }
    MPPointF.recycleInstance(paramCanvas);
  }
  
  protected void drawEntryLabel(Canvas paramCanvas, String paramString, float paramFloat1, float paramFloat2)
  {
    paramCanvas.drawText(paramString, paramFloat1, paramFloat2, mEntryLabelsPaint);
  }
  
  public void drawExtras(Canvas paramCanvas)
  {
    drawHole(paramCanvas);
    paramCanvas.drawBitmap((Bitmap)mDrawBitmap.get(), 0.0F, 0.0F, null);
    drawCenterText(paramCanvas);
  }
  
  public void drawHighlighted(Canvas paramCanvas, Highlight[] paramArrayOfHighlight)
  {
    float f3 = mAnimator.getPhaseX();
    float f8 = mAnimator.getPhaseY();
    float f9 = mChart.getRotationAngle();
    float[] arrayOfFloat = mChart.getDrawAngles();
    paramCanvas = mChart.getAbsoluteAngles();
    MPPointF localMPPointF = mChart.getCenterCircleBox();
    float f10 = mChart.getRadius();
    int i;
    if ((mChart.isDrawHoleEnabled()) && (!mChart.isDrawSlicesUnderHoleEnabled())) {
      i = 1;
    } else {
      i = 0;
    }
    int i3 = 0;
    float f1;
    if (i != 0) {
      f1 = mChart.getHoleRadius() / 100.0F * f10;
    } else {
      f1 = 0.0F;
    }
    RectF localRectF = mDrawHighlightedRectF;
    localRectF.set(0.0F, 0.0F, 0.0F, 0.0F);
    int j = 0;
    for (;;)
    {
      Object localObject = paramArrayOfHighlight;
      if (j >= localObject.length) {
        break;
      }
      int i2 = (int)localObject[j].getX();
      if (i2 < arrayOfFloat.length)
      {
        do
        {
          localObject = ((PieData)mChart.getData()).getDataSetByIndex(localObject[j].getDataSetIndex());
        } while ((localObject == null) || (!((IPieDataSet)localObject).isHighlightEnabled()));
        int m = ((IPieDataSet)localObject).getEntryCount();
        int n = 0;
        int i1;
        for (int k = n; n < m; k = i1)
        {
          i1 = k;
          if (Math.abs(((PieEntry)((IPieDataSet)localObject).getEntryForIndex(n)).getY()) > Utils.FLOAT_EPSILON) {
            i1 = k + 1;
          }
          n += 1;
        }
        float f4;
        if (i2 == 0) {
          f4 = 0.0F;
        } else {
          f4 = paramCanvas[(i2 - 1)] * f3;
        }
        float f5;
        if (k <= 1) {
          f5 = 0.0F;
        } else {
          f5 = ((IPieDataSet)localObject).getSliceSpace();
        }
        float f11 = arrayOfFloat[i2];
        float f2 = ((IPieDataSet)localObject).getSelectionShift();
        float f13 = f10 + f2;
        localRectF.set(mChart.getCircleBox());
        f2 = -f2;
        localRectF.inset(f2, f2);
        if ((f5 > 0.0F) && (f11 <= 180.0F)) {
          m = 1;
        } else {
          m = 0;
        }
        mRenderPaint.setColor(((IPieDataSet)localObject).getColor(i2));
        if (k == 1) {
          f6 = 0.0F;
        } else {
          f6 = f5 / (0.017453292F * f10);
        }
        if (k == 1) {
          f2 = 0.0F;
        } else {
          f2 = f5 / (0.017453292F * f13);
        }
        float f12 = f9 + (f4 + f6 / 2.0F) * f8;
        float f6 = (f11 - f6) * f8;
        i3 = 0;
        if (f6 < 0.0F) {
          f6 = 0.0F;
        }
        float f14 = (f4 + f2 / 2.0F) * f8 + f9;
        float f7 = (f11 - f2) * f8;
        f2 = f7;
        if (f7 < 0.0F) {
          f2 = 0.0F;
        }
        mPathBuffer.reset();
        double d;
        if ((f6 >= 360.0F) && (f6 % 360.0F <= Utils.FLOAT_EPSILON))
        {
          mPathBuffer.addCircle(x, y, f13, Path.Direction.CW);
        }
        else
        {
          localObject = mPathBuffer;
          f7 = x;
          d = f14 * 0.017453292F;
          ((Path)localObject).moveTo(f7 + (float)Math.cos(d) * f13, y + f13 * (float)Math.sin(d));
          mPathBuffer.arcTo(localRectF, f14, f2);
        }
        if (m != 0)
        {
          f2 = x;
          d = f12 * 0.017453292F;
          f2 = calculateMinimumRadiusForSpacedSlice(localMPPointF, f10, f11 * f8, (float)Math.cos(d) * f10 + f2, y + (float)Math.sin(d) * f10, f12, f6);
        }
        else
        {
          f2 = 0.0F;
        }
        mInnerRectBuffer.set(x - f1, y - f1, x + f1, y + f1);
        if ((i != 0) && ((f1 > 0.0F) || (m != 0)))
        {
          if (m != 0)
          {
            f7 = f2;
            if (f2 < 0.0F) {
              f7 = -f2;
            }
            f2 = Math.max(f1, f7);
          }
          else
          {
            f2 = f1;
          }
          if ((k != 1) && (f2 != 0.0F)) {
            f5 /= 0.017453292F * f2;
          } else {
            f5 = 0.0F;
          }
          f12 = f5 / 2.0F;
          f7 = (f11 - f5) * f8;
          f5 = f7;
          if (f7 < 0.0F) {
            f5 = 0.0F;
          }
          f4 = f9 + (f4 + f12) * f8 + f5;
          if ((f6 >= 360.0F) && (f6 % 360.0F <= Utils.FLOAT_EPSILON))
          {
            mPathBuffer.addCircle(x, y, f2, Path.Direction.CCW);
          }
          else
          {
            localObject = mPathBuffer;
            f6 = x;
            d = f4 * 0.017453292F;
            ((Path)localObject).lineTo(f6 + (float)Math.cos(d) * f2, y + f2 * (float)Math.sin(d));
            mPathBuffer.arcTo(mInnerRectBuffer, f4, -f5);
          }
        }
        else if (f6 % 360.0F > Utils.FLOAT_EPSILON)
        {
          if (m != 0)
          {
            f5 = f6 / 2.0F;
            f4 = x;
            d = (f12 + f5) * 0.017453292F;
            f5 = (float)Math.cos(d);
            f6 = y;
            f7 = (float)Math.sin(d);
            mPathBuffer.lineTo(f4 + f5 * f2, f6 + f2 * f7);
          }
          else
          {
            mPathBuffer.lineTo(x, y);
          }
        }
        mPathBuffer.close();
        mBitmapCanvas.drawPath(mPathBuffer, mRenderPaint);
      }
      j += 1;
    }
    MPPointF.recycleInstance(localMPPointF);
  }
  
  protected void drawHole(Canvas paramCanvas)
  {
    if ((mChart.isDrawHoleEnabled()) && (mBitmapCanvas != null))
    {
      float f1 = mChart.getRadius();
      float f2 = mChart.getHoleRadius() / 100.0F * f1;
      paramCanvas = mChart.getCenterCircleBox();
      if (Color.alpha(mHolePaint.getColor()) > 0) {
        mBitmapCanvas.drawCircle(x, y, f2, mHolePaint);
      }
      if ((Color.alpha(mTransparentCirclePaint.getColor()) > 0) && (mChart.getTransparentCircleRadius() > mChart.getHoleRadius()))
      {
        int i = mTransparentCirclePaint.getAlpha();
        float f3 = mChart.getTransparentCircleRadius() / 100.0F;
        mTransparentCirclePaint.setAlpha((int)(i * mAnimator.getPhaseX() * mAnimator.getPhaseY()));
        mHoleCirclePath.reset();
        mHoleCirclePath.addCircle(x, y, f1 * f3, Path.Direction.CW);
        mHoleCirclePath.addCircle(x, y, f2, Path.Direction.CCW);
        mBitmapCanvas.drawPath(mHoleCirclePath, mTransparentCirclePaint);
        mTransparentCirclePaint.setAlpha(i);
      }
      MPPointF.recycleInstance(paramCanvas);
    }
  }
  
  protected void drawRoundedSlices(Canvas paramCanvas)
  {
    if (!mChart.isDrawRoundedSlicesEnabled()) {
      return;
    }
    IPieDataSet localIPieDataSet = ((PieData)mChart.getData()).getDataSet();
    if (!localIPieDataSet.isVisible()) {
      return;
    }
    float f1 = mAnimator.getPhaseX();
    float f2 = mAnimator.getPhaseY();
    MPPointF localMPPointF = mChart.getCenterCircleBox();
    float f4 = mChart.getRadius();
    float f5 = (f4 - mChart.getHoleRadius() * f4 / 100.0F) / 2.0F;
    paramCanvas = mChart.getDrawAngles();
    float f3 = mChart.getRotationAngle();
    int i = 0;
    while (i < localIPieDataSet.getEntryCount())
    {
      float f6 = paramCanvas[i];
      if (Math.abs(localIPieDataSet.getEntryForIndex(i).getY()) > Utils.FLOAT_EPSILON)
      {
        double d1 = f4 - f5;
        double d2 = (f3 + f6) * f2;
        float f7 = (float)(Math.cos(Math.toRadians(d2)) * d1 + x);
        float f8 = (float)(d1 * Math.sin(Math.toRadians(d2)) + y);
        mRenderPaint.setColor(localIPieDataSet.getColor(i));
        mBitmapCanvas.drawCircle(f7, f8, f5, mRenderPaint);
      }
      f3 += f6 * f1;
      i += 1;
    }
    MPPointF.recycleInstance(localMPPointF);
  }
  
  public void drawValues(Canvas paramCanvas)
  {
    Object localObject3 = mChart.getCenterCircleBox();
    float f3 = mChart.getRadius();
    float f2 = mChart.getRotationAngle();
    Object localObject4 = mChart.getDrawAngles();
    Object localObject5 = mChart.getAbsoluteAngles();
    float f9 = mAnimator.getPhaseX();
    float f10 = mAnimator.getPhaseY();
    float f11 = mChart.getHoleRadius() / 100.0F;
    float f1 = f3 / 10.0F * 3.6F;
    if (mChart.isDrawHoleEnabled()) {
      f1 = (f3 - f3 * f11) / 2.0F;
    }
    float f12 = f3 - f1;
    Object localObject1 = (PieData)mChart.getData();
    Object localObject2 = ((PieData)localObject1).getDataSets();
    float f13 = ((PieData)localObject1).getYValueSum();
    boolean bool1 = mChart.isDrawEntryLabelsEnabled();
    paramCanvas.save();
    float f14 = Utils.convertDpToPixel(5.0F);
    int j = 0;
    int i = j;
    f1 = f3;
    while (i < ((List)localObject2).size())
    {
      Object localObject7 = (IPieDataSet)((List)localObject2).get(i);
      boolean bool2 = ((IPieDataSet)localObject7).isDrawValuesEnabled();
      if ((!bool2) && (!bool1))
      {
        localObject6 = localObject2;
        localObject2 = localObject3;
        localObject3 = localObject5;
        localObject5 = localObject1;
        localObject1 = localObject6;
      }
      else
      {
        PieDataSet.ValuePosition localValuePosition1 = ((IPieDataSet)localObject7).getXValuePosition();
        PieDataSet.ValuePosition localValuePosition2 = ((IPieDataSet)localObject7).getYValuePosition();
        applyValueTextStyle((IDataSet)localObject7);
        float f15 = Utils.calcTextHeight(mValuePaint, "Q") + Utils.convertDpToPixel(4.0F);
        IValueFormatter localIValueFormatter = ((IPieDataSet)localObject7).getValueFormatter();
        int n = ((IPieDataSet)localObject7).getEntryCount();
        localObject6 = mValueLinePaint;
        ((Paint)localObject6).setColor(((IPieDataSet)localObject7).getValueLineColor());
        mValueLinePaint.setStrokeWidth(Utils.convertDpToPixel(((IPieDataSet)localObject7).getValueLineWidth()));
        float f16 = getSliceSpace((IPieDataSet)localObject7);
        MPPointF localMPPointF = MPPointF.getInstance(((IPieDataSet)localObject7).getIconsOffset());
        x = Utils.convertDpToPixel(x);
        y = Utils.convertDpToPixel(y);
        int m = 0;
        int k = j;
        localObject6 = localObject2;
        j = i;
        localObject2 = localObject7;
        i = m;
        while (i < n)
        {
          localObject7 = (PieEntry)((IPieDataSet)localObject2).getEntryForIndex(i);
          if (k == 0) {
            f3 = 0.0F;
          } else {
            f3 = localObject5[(k - 1)] * f9;
          }
          float f6 = f2 + (f3 + (localObject4[k] - f16 / (0.017453292F * f12) / 2.0F) / 2.0F) * f10;
          if (mChart.isUsePercentValuesEnabled()) {}
          for (float f4 = ((PieEntry)localObject7).getY() / f13 * 100.0F;; f4 = ((PieEntry)localObject7).getY()) {
            break;
          }
          double d = f6 * 0.017453292F;
          float f7 = (float)Math.cos(d);
          float f8 = (float)Math.sin(d);
          int i1;
          if ((bool1) && (localValuePosition1 == PieDataSet.ValuePosition.OUTSIDE_SLICE)) {
            i1 = 1;
          } else {
            i1 = 0;
          }
          int i2;
          if ((bool2) && (localValuePosition2 == PieDataSet.ValuePosition.OUTSIDE_SLICE)) {
            i2 = 1;
          } else {
            i2 = 0;
          }
          if ((bool1) && (localValuePosition1 == PieDataSet.ValuePosition.INSIDE_SLICE)) {
            m = 1;
          } else {
            m = 0;
          }
          int i3;
          if ((bool2) && (localValuePosition2 == PieDataSet.ValuePosition.INSIDE_SLICE)) {
            i3 = 1;
          } else {
            i3 = 0;
          }
          if ((i1 == 0) && (i2 == 0)) {
            break label1140;
          }
          float f19 = ((IPieDataSet)localObject2).getValueLinePart1Length();
          f3 = ((IPieDataSet)localObject2).getValueLinePart2Length();
          float f5 = ((IPieDataSet)localObject2).getValueLinePart1OffsetPercentage() / 100.0F;
          if (mChart.isDrawHoleEnabled())
          {
            f17 = f1 * f11;
            f5 = (f1 - f17) * f5 + f17;
          }
          else
          {
            f5 = f1 * f5;
          }
          if (((IPieDataSet)localObject2).isValueLineVariableLength()) {
            f3 = f3 * f12 * (float)Math.abs(Math.sin(d));
          } else {
            f3 *= f12;
          }
          float f17 = x;
          float f18 = y;
          float f20 = (1.0F + f19) * f12;
          f19 = x + f20 * f7;
          f20 = f20 * f8 + y;
          d = f6 % 360.0D;
          if ((d >= 90.0D) && (d <= 270.0D))
          {
            f3 = f19 - f3;
            mValuePaint.setTextAlign(Paint.Align.RIGHT);
            if (i1 != 0) {
              mEntryLabelsPaint.setTextAlign(Paint.Align.RIGHT);
            }
            f6 = f3;
            f3 -= f14;
          }
          else
          {
            f6 = f19 + f3;
            mValuePaint.setTextAlign(Paint.Align.LEFT);
            if (i1 != 0) {
              mEntryLabelsPaint.setTextAlign(Paint.Align.LEFT);
            }
            f3 = f6 + f14;
          }
          if (((IPieDataSet)localObject2).getValueLineColor() != 1122867)
          {
            paramCanvas.drawLine(f5 * f7 + f17, f5 * f8 + f18, f19, f20, mValueLinePaint);
            paramCanvas.drawLine(f19, f20, f6, f20, mValueLinePaint);
          }
          Object localObject8 = localObject2;
          int i4 = i;
          Object localObject9;
          if ((i1 != 0) && (i2 != 0))
          {
            i1 = localObject8.getValueTextColor(i4);
            localObject8 = localObject7;
            drawValue(paramCanvas, localIValueFormatter, f4, (Entry)localObject7, 0, f3, f20, i1);
            if ((i4 < ((PieData)localObject1).getEntryCount()) && (localObject8.getLabel() != null)) {
              drawEntryLabel(paramCanvas, localObject8.getLabel(), f3, f20 + f15);
            }
          }
          else
          {
            localObject9 = localObject7;
            if (i1 != 0)
            {
              if ((i4 < ((PieData)localObject1).getEntryCount()) && (((PieEntry)localObject9).getLabel() != null)) {
                drawEntryLabel(paramCanvas, ((PieEntry)localObject9).getLabel(), f3, f20 + f15 / 2.0F);
              }
            }
            else if (i2 != 0) {
              drawValue(paramCanvas, localIValueFormatter, f4, (Entry)localObject9, 0, f3, f20 + f15 / 2.0F, localObject8.getValueTextColor(i4));
            }
          }
          label1140:
          localObject8 = localObject3;
          localObject3 = localObject1;
          if ((m == 0) && (i3 == 0)) {
            break label1353;
          }
          f3 = f12 * f7 + x;
          f5 = f12 * f8 + y;
          mValuePaint.setTextAlign(Paint.Align.CENTER);
          if ((m != 0) && (i3 != 0))
          {
            drawValue(paramCanvas, localIValueFormatter, f4, (Entry)localObject7, 0, f3, f5, ((IPieDataSet)localObject2).getValueTextColor(i));
            if ((i < ((PieData)localObject3).getEntryCount()) && (((PieEntry)localObject7).getLabel() != null)) {
              drawEntryLabel(paramCanvas, ((PieEntry)localObject7).getLabel(), f3, f5 + f15);
            }
          }
          else if (m != 0)
          {
            if ((i < ((PieData)localObject3).getEntryCount()) && (((PieEntry)localObject7).getLabel() != null)) {
              drawEntryLabel(paramCanvas, ((PieEntry)localObject7).getLabel(), f3, f5 + f15 / 2.0F);
            }
          }
          else if (i3 != 0)
          {
            drawValue(paramCanvas, localIValueFormatter, f4, (Entry)localObject7, 0, f3, f5 + f15 / 2.0F, ((IPieDataSet)localObject2).getValueTextColor(i));
          }
          label1353:
          localObject1 = localObject8;
          if ((((PieEntry)localObject7).getIcon() != null) && (((IPieDataSet)localObject2).isDrawIconsEnabled()))
          {
            localObject9 = ((PieEntry)localObject7).getIcon();
            localObject7 = localMPPointF;
            f3 = y;
            localObject8 = localObject1;
            f4 = x;
            f5 = y;
            f6 = y;
            f17 = x;
            Utils.drawImage(paramCanvas, (Drawable)localObject9, (int)((f12 + f3) * f7 + f4), (int)((f12 + f5) * f8 + f6 + f17), ((Drawable)localObject9).getIntrinsicWidth(), ((Drawable)localObject9).getIntrinsicHeight());
          }
          k += 1;
          i += 1;
          localObject7 = localObject4;
          localObject4 = localObject1;
          localObject1 = localObject3;
          localObject3 = localObject4;
          localObject4 = localObject7;
        }
        localObject2 = localObject3;
        localObject3 = localObject5;
        localObject5 = localObject1;
        MPPointF.recycleInstance(localMPPointF);
        localObject1 = localObject6;
        i = j;
        j = k;
      }
      i += 1;
      Object localObject6 = localObject5;
      localObject5 = localObject3;
      localObject3 = localObject2;
      localObject2 = localObject1;
      localObject1 = localObject6;
    }
    MPPointF.recycleInstance((MPPointF)localObject3);
    paramCanvas.restore();
  }
  
  public TextPaint getPaintCenterText()
  {
    return mCenterTextPaint;
  }
  
  public Paint getPaintEntryLabels()
  {
    return mEntryLabelsPaint;
  }
  
  public Paint getPaintHole()
  {
    return mHolePaint;
  }
  
  public Paint getPaintTransparentCircle()
  {
    return mTransparentCirclePaint;
  }
  
  protected float getSliceSpace(IPieDataSet paramIPieDataSet)
  {
    if (!paramIPieDataSet.isAutomaticallyDisableSliceSpacingEnabled()) {
      return paramIPieDataSet.getSliceSpace();
    }
    if (paramIPieDataSet.getSliceSpace() / mViewPortHandler.getSmallestContentExtension() > paramIPieDataSet.getYMin() / ((PieData)mChart.getData()).getYValueSum() * 2.0F) {
      return 0.0F;
    }
    return paramIPieDataSet.getSliceSpace();
  }
  
  public void initBuffers() {}
  
  public void releaseBitmap()
  {
    if (mBitmapCanvas != null)
    {
      mBitmapCanvas.setBitmap(null);
      mBitmapCanvas = null;
    }
    if (mDrawBitmap != null)
    {
      ((Bitmap)mDrawBitmap.get()).recycle();
      mDrawBitmap.clear();
      mDrawBitmap = null;
    }
  }
}

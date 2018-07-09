package com.github.mikephil.charting.renderer;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet.Mode;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class LineChartRenderer
  extends LineRadarRenderer
{
  protected Path cubicFillPath = new Path();
  protected Path cubicPath = new Path();
  protected Canvas mBitmapCanvas;
  protected Bitmap.Config mBitmapConfig = Bitmap.Config.ARGB_8888;
  protected LineDataProvider mChart;
  protected Paint mCirclePaintInner;
  private float[] mCirclesBuffer = new float[2];
  protected WeakReference<Bitmap> mDrawBitmap;
  protected Path mGenerateFilledPathBuffer = new Path();
  private HashMap<IDataSet, DataSetImageCache> mImageCaches = new HashMap();
  private float[] mLineBuffer = new float[4];
  
  public LineChartRenderer(LineDataProvider paramLineDataProvider, ChartAnimator paramChartAnimator, ViewPortHandler paramViewPortHandler)
  {
    super(paramChartAnimator, paramViewPortHandler);
    mChart = paramLineDataProvider;
    mCirclePaintInner = new Paint(1);
    mCirclePaintInner.setStyle(Paint.Style.FILL);
    mCirclePaintInner.setColor(-1);
  }
  
  private void generateFilledPath(ILineDataSet paramILineDataSet, int paramInt1, int paramInt2, Path paramPath)
  {
    float f1 = paramILineDataSet.getFillFormatter().getFillLinePosition(paramILineDataSet, mChart);
    float f2 = mAnimator.getPhaseY();
    int i;
    if (paramILineDataSet.getMode() == LineDataSet.Mode.STEPPED) {
      i = 1;
    } else {
      i = 0;
    }
    paramPath.reset();
    Object localObject = paramILineDataSet.getEntryForIndex(paramInt1);
    paramPath.moveTo(((Entry)localObject).getX(), f1);
    paramPath.lineTo(((Entry)localObject).getX(), ((Entry)localObject).getY() * f2);
    paramInt1 += 1;
    Entry localEntry;
    for (localObject = null; paramInt1 <= paramInt2; localObject = localEntry)
    {
      localEntry = paramILineDataSet.getEntryForIndex(paramInt1);
      if ((i != 0) && (localObject != null)) {
        paramPath.lineTo(localEntry.getX(), ((Entry)localObject).getY() * f2);
      }
      paramPath.lineTo(localEntry.getX(), localEntry.getY() * f2);
      paramInt1 += 1;
    }
    if (localObject != null) {
      paramPath.lineTo(((Entry)localObject).getX(), f1);
    }
    paramPath.close();
  }
  
  protected void drawCircles(Canvas paramCanvas)
  {
    mRenderPaint.setStyle(Paint.Style.FILL);
    float f1 = mAnimator.getPhaseY();
    mCirclesBuffer[0] = 0.0F;
    mCirclesBuffer[1] = 0.0F;
    List localList = mChart.getLineData().getDataSets();
    int i = 0;
    while (i < localList.size())
    {
      ILineDataSet localILineDataSet = (ILineDataSet)localList.get(i);
      if ((localILineDataSet.isVisible()) && (localILineDataSet.isDrawCirclesEnabled()) && (localILineDataSet.getEntryCount() != 0))
      {
        mCirclePaintInner.setColor(localILineDataSet.getCircleHoleColor());
        Transformer localTransformer = mChart.getTransformer(localILineDataSet.getAxisDependency());
        mXBounds.set(mChart, localILineDataSet);
        float f2 = localILineDataSet.getCircleRadius();
        float f3 = localILineDataSet.getCircleHoleRadius();
        boolean bool1;
        if ((localILineDataSet.isDrawCircleHoleEnabled()) && (f3 < f2) && (f3 > 0.0F)) {
          bool1 = true;
        } else {
          bool1 = false;
        }
        boolean bool2;
        if ((bool1) && (localILineDataSet.getCircleHoleColor() == 1122867)) {
          bool2 = true;
        } else {
          bool2 = false;
        }
        DataSetImageCache localDataSetImageCache;
        if (mImageCaches.containsKey(localILineDataSet))
        {
          localDataSetImageCache = (DataSetImageCache)mImageCaches.get(localILineDataSet);
        }
        else
        {
          localDataSetImageCache = new DataSetImageCache(null);
          mImageCaches.put(localILineDataSet, localDataSetImageCache);
        }
        if (localDataSetImageCache.init(localILineDataSet)) {
          localDataSetImageCache.fill(localILineDataSet, bool1, bool2);
        }
        int k = mXBounds.range;
        int m = mXBounds.min;
        int j = mXBounds.min;
        while (j <= k + m)
        {
          Object localObject = localILineDataSet.getEntryForIndex(j);
          if (localObject == null) {
            break;
          }
          mCirclesBuffer[0] = ((Entry)localObject).getX();
          mCirclesBuffer[1] = (((Entry)localObject).getY() * f1);
          localTransformer.pointValuesToPixel(mCirclesBuffer);
          if (!mViewPortHandler.isInBoundsRight(mCirclesBuffer[0])) {
            break;
          }
          if ((mViewPortHandler.isInBoundsLeft(mCirclesBuffer[0])) && (mViewPortHandler.isInBoundsY(mCirclesBuffer[1])))
          {
            localObject = localDataSetImageCache.getBitmap(j);
            if (localObject != null) {
              paramCanvas.drawBitmap((Bitmap)localObject, mCirclesBuffer[0] - f2, mCirclesBuffer[1] - f2, null);
            }
          }
          j += 1;
        }
      }
      i += 1;
    }
  }
  
  protected void drawCubicBezier(ILineDataSet paramILineDataSet)
  {
    Math.max(0.0F, Math.min(1.0F, mAnimator.getPhaseX()));
    float f1 = mAnimator.getPhaseY();
    Transformer localTransformer = mChart.getTransformer(paramILineDataSet.getAxisDependency());
    mXBounds.set(mChart, paramILineDataSet);
    float f2 = paramILineDataSet.getCubicIntensity();
    cubicPath.reset();
    if (mXBounds.range >= 1)
    {
      int i = mXBounds.min + 1;
      int j = mXBounds.min;
      j = mXBounds.range;
      Object localObject3 = paramILineDataSet.getEntryForIndex(Math.max(i - 2, 0));
      Object localObject1 = paramILineDataSet.getEntryForIndex(Math.max(i - 1, 0));
      int k = -1;
      if (localObject1 == null) {
        return;
      }
      cubicPath.moveTo(((Entry)localObject1).getX(), ((Entry)localObject1).getY() * f1);
      i = mXBounds.min + 1;
      Object localObject2 = localObject1;
      while (i <= mXBounds.range + mXBounds.min)
      {
        if (k != i) {
          localObject2 = paramILineDataSet.getEntryForIndex(i);
        }
        j = i + 1;
        if (j < paramILineDataSet.getEntryCount()) {
          i = j;
        }
        Entry localEntry = paramILineDataSet.getEntryForIndex(i);
        float f3 = ((Entry)localObject2).getX();
        float f4 = ((Entry)localObject3).getX();
        float f5 = ((Entry)localObject2).getY();
        float f6 = ((Entry)localObject3).getY();
        float f7 = localEntry.getX();
        float f8 = ((Entry)localObject1).getX();
        float f9 = localEntry.getY();
        float f10 = ((Entry)localObject1).getY();
        cubicPath.cubicTo(((Entry)localObject1).getX() + (f3 - f4) * f2, (((Entry)localObject1).getY() + (f5 - f6) * f2) * f1, ((Entry)localObject2).getX() - (f7 - f8) * f2, (((Entry)localObject2).getY() - (f9 - f10) * f2) * f1, ((Entry)localObject2).getX(), ((Entry)localObject2).getY() * f1);
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localEntry;
        k = i;
        i = j;
      }
    }
    if (paramILineDataSet.isDrawFilledEnabled())
    {
      cubicFillPath.reset();
      cubicFillPath.addPath(cubicPath);
      drawCubicFill(mBitmapCanvas, paramILineDataSet, cubicFillPath, localTransformer, mXBounds);
    }
    mRenderPaint.setColor(paramILineDataSet.getColor());
    mRenderPaint.setStyle(Paint.Style.STROKE);
    localTransformer.pathValueToPixel(cubicPath);
    mBitmapCanvas.drawPath(cubicPath, mRenderPaint);
    mRenderPaint.setPathEffect(null);
  }
  
  protected void drawCubicFill(Canvas paramCanvas, ILineDataSet paramILineDataSet, Path paramPath, Transformer paramTransformer, BarLineScatterCandleBubbleRenderer.XBounds paramXBounds)
  {
    float f = paramILineDataSet.getFillFormatter().getFillLinePosition(paramILineDataSet, mChart);
    paramPath.lineTo(paramILineDataSet.getEntryForIndex(min + range).getX(), f);
    paramPath.lineTo(paramILineDataSet.getEntryForIndex(min).getX(), f);
    paramPath.close();
    paramTransformer.pathValueToPixel(paramPath);
    paramTransformer = paramILineDataSet.getFillDrawable();
    if (paramTransformer != null)
    {
      drawFilledPath(paramCanvas, paramPath, paramTransformer);
      return;
    }
    drawFilledPath(paramCanvas, paramPath, paramILineDataSet.getFillColor(), paramILineDataSet.getFillAlpha());
  }
  
  public void drawData(Canvas paramCanvas)
  {
    int i = (int)mViewPortHandler.getChartWidth();
    int j = (int)mViewPortHandler.getChartHeight();
    if ((mDrawBitmap == null) || (((Bitmap)mDrawBitmap.get()).getWidth() != i) || (((Bitmap)mDrawBitmap.get()).getHeight() != j))
    {
      if ((i > 0) && (j > 0))
      {
        mDrawBitmap = new WeakReference(Bitmap.createBitmap(i, j, mBitmapConfig));
        mBitmapCanvas = new Canvas((Bitmap)mDrawBitmap.get());
      }
    }
    else
    {
      ((Bitmap)mDrawBitmap.get()).eraseColor(0);
      Iterator localIterator = mChart.getLineData().getDataSets().iterator();
      while (localIterator.hasNext())
      {
        ILineDataSet localILineDataSet = (ILineDataSet)localIterator.next();
        if (localILineDataSet.isVisible()) {
          drawDataSet(paramCanvas, localILineDataSet);
        }
      }
      paramCanvas.drawBitmap((Bitmap)mDrawBitmap.get(), 0.0F, 0.0F, mRenderPaint);
      return;
    }
  }
  
  protected void drawDataSet(Canvas paramCanvas, ILineDataSet paramILineDataSet)
  {
    if (paramILineDataSet.getEntryCount() < 1) {
      return;
    }
    mRenderPaint.setStrokeWidth(paramILineDataSet.getLineWidth());
    mRenderPaint.setPathEffect(paramILineDataSet.getDashPathEffect());
    switch (1.$SwitchMap$com$github$mikephil$charting$data$LineDataSet$Mode[paramILineDataSet.getMode().ordinal()])
    {
    default: 
      drawLinear(paramCanvas, paramILineDataSet);
      break;
    case 4: 
      drawHorizontalBezier(paramILineDataSet);
      break;
    case 3: 
      drawCubicBezier(paramILineDataSet);
    }
    mRenderPaint.setPathEffect(null);
  }
  
  public void drawExtras(Canvas paramCanvas)
  {
    drawCircles(paramCanvas);
  }
  
  public void drawHighlighted(Canvas paramCanvas, Highlight[] paramArrayOfHighlight)
  {
    LineData localLineData = mChart.getLineData();
    int i = 0;
    int j = paramArrayOfHighlight.length;
    while (i < j)
    {
      Highlight localHighlight = paramArrayOfHighlight[i];
      ILineDataSet localILineDataSet = (ILineDataSet)localLineData.getDataSetByIndex(localHighlight.getDataSetIndex());
      if ((localILineDataSet != null) && (localILineDataSet.isHighlightEnabled()))
      {
        Object localObject = localILineDataSet.getEntryForXValue(localHighlight.getX(), localHighlight.getY());
        if (isInBoundsX((Entry)localObject, localILineDataSet))
        {
          localObject = mChart.getTransformer(localILineDataSet.getAxisDependency()).getPixelForValues(((Entry)localObject).getX(), ((Entry)localObject).getY() * mAnimator.getPhaseY());
          localHighlight.setDraw((float)x, (float)y);
          drawHighlightLines(paramCanvas, (float)x, (float)y, localILineDataSet);
        }
      }
      i += 1;
    }
  }
  
  protected void drawHorizontalBezier(ILineDataSet paramILineDataSet)
  {
    float f1 = mAnimator.getPhaseY();
    Transformer localTransformer = mChart.getTransformer(paramILineDataSet.getAxisDependency());
    mXBounds.set(mChart, paramILineDataSet);
    cubicPath.reset();
    if (mXBounds.range >= 1)
    {
      Object localObject = paramILineDataSet.getEntryForIndex(mXBounds.min);
      cubicPath.moveTo(((Entry)localObject).getX(), ((Entry)localObject).getY() * f1);
      int i = mXBounds.min + 1;
      while (i <= mXBounds.range + mXBounds.min)
      {
        Entry localEntry = paramILineDataSet.getEntryForIndex(i);
        float f2 = ((Entry)localObject).getX() + (localEntry.getX() - ((Entry)localObject).getX()) / 2.0F;
        cubicPath.cubicTo(f2, ((Entry)localObject).getY() * f1, f2, localEntry.getY() * f1, localEntry.getX(), localEntry.getY() * f1);
        i += 1;
        localObject = localEntry;
      }
    }
    if (paramILineDataSet.isDrawFilledEnabled())
    {
      cubicFillPath.reset();
      cubicFillPath.addPath(cubicPath);
      drawCubicFill(mBitmapCanvas, paramILineDataSet, cubicFillPath, localTransformer, mXBounds);
    }
    mRenderPaint.setColor(paramILineDataSet.getColor());
    mRenderPaint.setStyle(Paint.Style.STROKE);
    localTransformer.pathValueToPixel(cubicPath);
    mBitmapCanvas.drawPath(cubicPath, mRenderPaint);
    mRenderPaint.setPathEffect(null);
  }
  
  protected void drawLinear(Canvas paramCanvas, ILineDataSet paramILineDataSet)
  {
    int j = paramILineDataSet.getEntryCount();
    boolean bool = paramILineDataSet.isDrawSteppedEnabled();
    int i;
    if (bool) {
      i = 4;
    } else {
      i = 2;
    }
    Transformer localTransformer = mChart.getTransformer(paramILineDataSet.getAxisDependency());
    float f = mAnimator.getPhaseY();
    mRenderPaint.setStyle(Paint.Style.STROKE);
    Canvas localCanvas;
    if (paramILineDataSet.isDashedLineEnabled()) {
      localCanvas = mBitmapCanvas;
    } else {
      localCanvas = paramCanvas;
    }
    mXBounds.set(mChart, paramILineDataSet);
    if ((paramILineDataSet.isDrawFilledEnabled()) && (j > 0)) {
      drawLinearFill(paramCanvas, paramILineDataSet, localTransformer, mXBounds);
    }
    if (paramILineDataSet.getColors().size() > 1)
    {
      k = mLineBuffer.length;
      j = i * 2;
      if (k <= j) {
        mLineBuffer = new float[i * 4];
      }
      i = mXBounds.min;
      while (i <= mXBounds.range + mXBounds.min)
      {
        paramCanvas = paramILineDataSet.getEntryForIndex(i);
        if (paramCanvas != null)
        {
          mLineBuffer[0] = paramCanvas.getX();
          mLineBuffer[1] = (paramCanvas.getY() * f);
          if (i < mXBounds.max)
          {
            paramCanvas = paramILineDataSet.getEntryForIndex(i + 1);
            if (paramCanvas == null) {
              break;
            }
            if (bool)
            {
              mLineBuffer[2] = paramCanvas.getX();
              mLineBuffer[3] = mLineBuffer[1];
              mLineBuffer[4] = mLineBuffer[2];
              mLineBuffer[5] = mLineBuffer[3];
              mLineBuffer[6] = paramCanvas.getX();
              mLineBuffer[7] = (paramCanvas.getY() * f);
            }
            else
            {
              mLineBuffer[2] = paramCanvas.getX();
              mLineBuffer[3] = (paramCanvas.getY() * f);
            }
          }
          else
          {
            mLineBuffer[2] = mLineBuffer[0];
            mLineBuffer[3] = mLineBuffer[1];
          }
          localTransformer.pointValuesToPixel(mLineBuffer);
          if (!mViewPortHandler.isInBoundsRight(mLineBuffer[0])) {
            break;
          }
          if ((mViewPortHandler.isInBoundsLeft(mLineBuffer[2])) && ((mViewPortHandler.isInBoundsTop(mLineBuffer[1])) || (mViewPortHandler.isInBoundsBottom(mLineBuffer[3]))))
          {
            mRenderPaint.setColor(paramILineDataSet.getColor(i));
            localCanvas.drawLines(mLineBuffer, 0, j, mRenderPaint);
          }
        }
        i += 1;
      }
    }
    int k = mLineBuffer.length;
    j *= i;
    if (k < Math.max(j, i) * 2) {
      mLineBuffer = new float[Math.max(j, i) * 4];
    }
    if (paramILineDataSet.getEntryForIndex(mXBounds.min) != null)
    {
      j = mXBounds.min;
      int m;
      for (k = 0; j <= mXBounds.range + mXBounds.min; k = m)
      {
        if (j == 0) {
          m = 0;
        } else {
          m = j - 1;
        }
        Object localObject = paramILineDataSet.getEntryForIndex(m);
        paramCanvas = paramILineDataSet.getEntryForIndex(j);
        m = k;
        if (localObject != null) {
          if (paramCanvas == null)
          {
            m = k;
          }
          else
          {
            float[] arrayOfFloat = mLineBuffer;
            int n = k + 1;
            arrayOfFloat[k] = ((Entry)localObject).getX();
            arrayOfFloat = mLineBuffer;
            m = n + 1;
            arrayOfFloat[n] = (((Entry)localObject).getY() * f);
            k = m;
            if (bool)
            {
              arrayOfFloat = mLineBuffer;
              k = m + 1;
              arrayOfFloat[m] = paramCanvas.getX();
              arrayOfFloat = mLineBuffer;
              m = k + 1;
              arrayOfFloat[k] = (((Entry)localObject).getY() * f);
              arrayOfFloat = mLineBuffer;
              n = m + 1;
              arrayOfFloat[m] = paramCanvas.getX();
              arrayOfFloat = mLineBuffer;
              k = n + 1;
              arrayOfFloat[n] = (((Entry)localObject).getY() * f);
            }
            localObject = mLineBuffer;
            m = k + 1;
            localObject[k] = paramCanvas.getX();
            mLineBuffer[m] = (paramCanvas.getY() * f);
            m += 1;
          }
        }
        j += 1;
      }
      if (k > 0)
      {
        localTransformer.pointValuesToPixel(mLineBuffer);
        i = Math.max((mXBounds.range + 1) * i, i);
        mRenderPaint.setColor(paramILineDataSet.getColor());
        localCanvas.drawLines(mLineBuffer, 0, i * 2, mRenderPaint);
      }
    }
    mRenderPaint.setPathEffect(null);
  }
  
  protected void drawLinearFill(Canvas paramCanvas, ILineDataSet paramILineDataSet, Transformer paramTransformer, BarLineScatterCandleBubbleRenderer.XBounds paramXBounds)
  {
    Path localPath = mGenerateFilledPathBuffer;
    int n = min;
    int k = range + min;
    int i = 0;
    int i1;
    int j;
    do
    {
      i1 = i * 128 + n;
      int m = i1 + 128;
      j = m;
      if (m > k) {
        j = k;
      }
      if (i1 <= j)
      {
        generateFilledPath(paramILineDataSet, i1, j, localPath);
        paramTransformer.pathValueToPixel(localPath);
        paramXBounds = paramILineDataSet.getFillDrawable();
        if (paramXBounds != null) {
          drawFilledPath(paramCanvas, localPath, paramXBounds);
        } else {
          drawFilledPath(paramCanvas, localPath, paramILineDataSet.getFillColor(), paramILineDataSet.getFillAlpha());
        }
      }
      i += 1;
    } while (i1 <= j);
  }
  
  public void drawValues(Canvas paramCanvas)
  {
    if (isDrawingValuesAllowed(mChart))
    {
      List localList = mChart.getLineData().getDataSets();
      int i = 0;
      while (i < localList.size())
      {
        ILineDataSet localILineDataSet = (ILineDataSet)localList.get(i);
        if (shouldDrawValues(localILineDataSet))
        {
          applyValueTextStyle(localILineDataSet);
          Object localObject1 = mChart.getTransformer(localILineDataSet.getAxisDependency());
          int k = (int)(localILineDataSet.getCircleRadius() * 1.75F);
          int j = k;
          if (!localILineDataSet.isDrawCirclesEnabled()) {
            j = k / 2;
          }
          mXBounds.set(mChart, localILineDataSet);
          float[] arrayOfFloat = ((Transformer)localObject1).generateTransformedValuesLine(localILineDataSet, mAnimator.getPhaseX(), mAnimator.getPhaseY(), mXBounds.min, mXBounds.max);
          localObject1 = MPPointF.getInstance(localILineDataSet.getIconsOffset());
          x = Utils.convertDpToPixel(x);
          y = Utils.convertDpToPixel(y);
          k = 0;
          while (k < arrayOfFloat.length)
          {
            float f1 = arrayOfFloat[k];
            float f2 = arrayOfFloat[(k + 1)];
            if (!mViewPortHandler.isInBoundsRight(f1)) {
              break;
            }
            if ((mViewPortHandler.isInBoundsLeft(f1)) && (mViewPortHandler.isInBoundsY(f2)))
            {
              int m = k / 2;
              Object localObject3 = localILineDataSet.getEntryForIndex(mXBounds.min + m);
              if (localILineDataSet.isDrawValuesEnabled()) {
                drawValue(paramCanvas, localILineDataSet.getValueFormatter(), ((Entry)localObject3).getY(), (Entry)localObject3, i, f1, f2 - j, localILineDataSet.getValueTextColor(m));
              }
              Object localObject2 = localObject1;
              if ((((Entry)localObject3).getIcon() != null) && (localILineDataSet.isDrawIconsEnabled()))
              {
                localObject3 = ((Entry)localObject3).getIcon();
                Utils.drawImage(paramCanvas, (Drawable)localObject3, (int)(f1 + x), (int)(f2 + y), ((Drawable)localObject3).getIntrinsicWidth(), ((Drawable)localObject3).getIntrinsicHeight());
              }
            }
            k += 2;
          }
          MPPointF.recycleInstance((MPPointF)localObject1);
        }
        i += 1;
      }
    }
  }
  
  public Bitmap.Config getBitmapConfig()
  {
    return mBitmapConfig;
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
  
  public void setBitmapConfig(Bitmap.Config paramConfig)
  {
    mBitmapConfig = paramConfig;
    releaseBitmap();
  }
  
  private class DataSetImageCache
  {
    private Bitmap[] circleBitmaps;
    private Path mCirclePathBuffer = new Path();
    
    private DataSetImageCache() {}
    
    protected void fill(ILineDataSet paramILineDataSet, boolean paramBoolean1, boolean paramBoolean2)
    {
      int j = paramILineDataSet.getCircleColorCount();
      float f1 = paramILineDataSet.getCircleRadius();
      float f2 = paramILineDataSet.getCircleHoleRadius();
      int i = 0;
      while (i < j)
      {
        Object localObject = Bitmap.Config.ARGB_4444;
        int k = (int)(f1 * 2.1D);
        localObject = Bitmap.createBitmap(k, k, (Bitmap.Config)localObject);
        Canvas localCanvas = new Canvas((Bitmap)localObject);
        circleBitmaps[i] = localObject;
        mRenderPaint.setColor(paramILineDataSet.getCircleColor(i));
        if (paramBoolean2)
        {
          mCirclePathBuffer.reset();
          mCirclePathBuffer.addCircle(f1, f1, f1, Path.Direction.CW);
          mCirclePathBuffer.addCircle(f1, f1, f2, Path.Direction.CCW);
          localCanvas.drawPath(mCirclePathBuffer, mRenderPaint);
        }
        else
        {
          localCanvas.drawCircle(f1, f1, f1, mRenderPaint);
          if (paramBoolean1) {
            localCanvas.drawCircle(f1, f1, f2, mCirclePaintInner);
          }
        }
        i += 1;
      }
    }
    
    protected Bitmap getBitmap(int paramInt)
    {
      return circleBitmaps[(paramInt % circleBitmaps.length)];
    }
    
    protected boolean init(ILineDataSet paramILineDataSet)
    {
      int i = paramILineDataSet.getCircleColorCount();
      if (circleBitmaps == null)
      {
        circleBitmaps = new Bitmap[i];
        return true;
      }
      if (circleBitmaps.length != i)
      {
        circleBitmaps = new Bitmap[i];
        return true;
      }
      return false;
    }
  }
}

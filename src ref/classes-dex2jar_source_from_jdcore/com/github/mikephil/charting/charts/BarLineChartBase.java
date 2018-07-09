package com.github.mikephil.charting.charts;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.jobs.AnimatedMoveViewJob;
import com.github.mikephil.charting.jobs.AnimatedZoomJob;
import com.github.mikephil.charting.jobs.MoveViewJob;
import com.github.mikephil.charting.jobs.ZoomJob;
import com.github.mikephil.charting.listener.BarLineChartTouchListener;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnDrawListener;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.renderer.LegendRenderer;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

@SuppressLint({"RtlHardcoded"})
public abstract class BarLineChartBase<T extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>>
  extends Chart<T>
  implements BarLineScatterCandleBubbleDataProvider
{
  private long drawCycles = 0L;
  protected boolean mAutoScaleMinMaxEnabled = false;
  protected YAxis mAxisLeft;
  protected YAxisRenderer mAxisRendererLeft;
  protected YAxisRenderer mAxisRendererRight;
  protected YAxis mAxisRight;
  protected Paint mBorderPaint;
  protected boolean mClipValuesToContent = false;
  private boolean mCustomViewPortEnabled = false;
  protected boolean mDoubleTapToZoomEnabled = true;
  private boolean mDragXEnabled = true;
  private boolean mDragYEnabled = true;
  protected boolean mDrawBorders = false;
  protected boolean mDrawGridBackground = false;
  protected OnDrawListener mDrawListener;
  protected Matrix mFitScreenMatrixBuffer = new Matrix();
  protected float[] mGetPositionBuffer = new float[2];
  protected Paint mGridBackgroundPaint;
  protected boolean mHighlightPerDragEnabled = true;
  protected boolean mKeepPositionOnRotation = false;
  protected Transformer mLeftAxisTransformer;
  protected int mMaxVisibleCount = 100;
  protected float mMinOffset = 15.0F;
  private RectF mOffsetsBuffer = new RectF();
  protected float[] mOnSizeChangedBuffer = new float[2];
  protected boolean mPinchZoomEnabled = false;
  protected Transformer mRightAxisTransformer;
  private boolean mScaleXEnabled = true;
  private boolean mScaleYEnabled = true;
  protected XAxisRenderer mXAxisRenderer;
  protected Matrix mZoomMatrixBuffer = new Matrix();
  protected MPPointD posForGetHighestVisibleX = MPPointD.getInstance(0.0D, 0.0D);
  protected MPPointD posForGetLowestVisibleX = MPPointD.getInstance(0.0D, 0.0D);
  private long totalTime = 0L;
  
  public BarLineChartBase(Context paramContext)
  {
    super(paramContext);
  }
  
  public BarLineChartBase(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public BarLineChartBase(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void autoScale()
  {
    float f1 = getLowestVisibleX();
    float f2 = getHighestVisibleX();
    ((BarLineScatterCandleBubbleData)mData).calcMinMaxY(f1, f2);
    mXAxis.calculate(((BarLineScatterCandleBubbleData)mData).getXMin(), ((BarLineScatterCandleBubbleData)mData).getXMax());
    if (mAxisLeft.isEnabled()) {
      mAxisLeft.calculate(((BarLineScatterCandleBubbleData)mData).getYMin(YAxis.AxisDependency.LEFT), ((BarLineScatterCandleBubbleData)mData).getYMax(YAxis.AxisDependency.LEFT));
    }
    if (mAxisRight.isEnabled()) {
      mAxisRight.calculate(((BarLineScatterCandleBubbleData)mData).getYMin(YAxis.AxisDependency.RIGHT), ((BarLineScatterCandleBubbleData)mData).getYMax(YAxis.AxisDependency.RIGHT));
    }
    calculateOffsets();
  }
  
  protected void calcMinMax()
  {
    mXAxis.calculate(((BarLineScatterCandleBubbleData)mData).getXMin(), ((BarLineScatterCandleBubbleData)mData).getXMax());
    mAxisLeft.calculate(((BarLineScatterCandleBubbleData)mData).getYMin(YAxis.AxisDependency.LEFT), ((BarLineScatterCandleBubbleData)mData).getYMax(YAxis.AxisDependency.LEFT));
    mAxisRight.calculate(((BarLineScatterCandleBubbleData)mData).getYMin(YAxis.AxisDependency.RIGHT), ((BarLineScatterCandleBubbleData)mData).getYMax(YAxis.AxisDependency.RIGHT));
  }
  
  protected void calculateLegendOffsets(RectF paramRectF)
  {
    left = 0.0F;
    right = 0.0F;
    top = 0.0F;
    bottom = 0.0F;
    if ((mLegend != null) && (mLegend.isEnabled()) && (!mLegend.isDrawInsideEnabled())) {
      switch (2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation[mLegend.getOrientation().ordinal()])
      {
      default: 
        
      case 2: 
        switch (2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment[mLegend.getVerticalAlignment().ordinal()])
        {
        default: 
          
        case 2: 
          bottom += Math.min(mLegend.mNeededHeight, mViewPortHandler.getChartHeight() * mLegend.getMaxSizePercent()) + mLegend.getYOffset();
          if ((getXAxis().isEnabled()) && (getXAxis().isDrawLabelsEnabled()))
          {
            bottom += getXAxismLabelRotatedHeight;
            return;
          }
          break;
        case 1: 
          top += Math.min(mLegend.mNeededHeight, mViewPortHandler.getChartHeight() * mLegend.getMaxSizePercent()) + mLegend.getYOffset();
          if ((getXAxis().isEnabled()) && (getXAxis().isDrawLabelsEnabled()))
          {
            top += getXAxismLabelRotatedHeight;
            return;
          }
          break;
        }
        break;
      case 1: 
        switch (2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment[mLegend.getHorizontalAlignment().ordinal()])
        {
        default: 
          return;
        case 3: 
          switch (2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment[mLegend.getVerticalAlignment().ordinal()])
          {
          default: 
            return;
          case 2: 
            bottom += Math.min(mLegend.mNeededHeight, mViewPortHandler.getChartHeight() * mLegend.getMaxSizePercent()) + mLegend.getYOffset();
            return;
          }
          top += Math.min(mLegend.mNeededHeight, mViewPortHandler.getChartHeight() * mLegend.getMaxSizePercent()) + mLegend.getYOffset();
          return;
        case 2: 
          right += Math.min(mLegend.mNeededWidth, mViewPortHandler.getChartWidth() * mLegend.getMaxSizePercent()) + mLegend.getXOffset();
          return;
        }
        left += Math.min(mLegend.mNeededWidth, mViewPortHandler.getChartWidth() * mLegend.getMaxSizePercent()) + mLegend.getXOffset();
      }
    }
  }
  
  public void calculateOffsets()
  {
    if (!mCustomViewPortEnabled)
    {
      calculateLegendOffsets(mOffsetsBuffer);
      float f2 = mOffsetsBuffer.left + 0.0F;
      float f5 = mOffsetsBuffer.top + 0.0F;
      float f1 = mOffsetsBuffer.right + 0.0F;
      float f6 = 0.0F + mOffsetsBuffer.bottom;
      float f3 = f2;
      if (mAxisLeft.needsOffset()) {
        f3 = f2 + mAxisLeft.getRequiredWidthSpace(mAxisRendererLeft.getPaintAxisLabels());
      }
      float f4 = f1;
      if (mAxisRight.needsOffset()) {
        f4 = f1 + mAxisRight.getRequiredWidthSpace(mAxisRendererRight.getPaintAxisLabels());
      }
      f1 = f6;
      f2 = f5;
      if (mXAxis.isEnabled())
      {
        f1 = f6;
        f2 = f5;
        if (mXAxis.isDrawLabelsEnabled())
        {
          float f7 = mXAxis.mLabelRotatedHeight + mXAxis.getYOffset();
          if (mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM)
          {
            f1 = f6 + f7;
            f2 = f5;
          }
          else if (mXAxis.getPosition() == XAxis.XAxisPosition.TOP)
          {
            f2 = f5 + f7;
            f1 = f6;
          }
          else
          {
            f1 = f6;
            f2 = f5;
            if (mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED)
            {
              f1 = f6 + f7;
              f2 = f5 + f7;
            }
          }
        }
      }
      f2 += getExtraTopOffset();
      f4 += getExtraRightOffset();
      f1 += getExtraBottomOffset();
      f3 += getExtraLeftOffset();
      f5 = Utils.convertDpToPixel(mMinOffset);
      mViewPortHandler.restrainViewPort(Math.max(f5, f3), Math.max(f5, f2), Math.max(f5, f4), Math.max(f5, f1));
      if (mLogEnabled)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("offsetLeft: ");
        localStringBuilder.append(f3);
        localStringBuilder.append(", offsetTop: ");
        localStringBuilder.append(f2);
        localStringBuilder.append(", offsetRight: ");
        localStringBuilder.append(f4);
        localStringBuilder.append(", offsetBottom: ");
        localStringBuilder.append(f1);
        Log.i("MPAndroidChart", localStringBuilder.toString());
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Content: ");
        localStringBuilder.append(mViewPortHandler.getContentRect().toString());
        Log.i("MPAndroidChart", localStringBuilder.toString());
      }
    }
    prepareOffsetMatrix();
    prepareValuePxMatrix();
  }
  
  public void centerViewTo(float paramFloat1, float paramFloat2, YAxis.AxisDependency paramAxisDependency)
  {
    float f1 = getAxisRange(paramAxisDependency) / mViewPortHandler.getScaleY();
    float f2 = getXAxismAxisRange / mViewPortHandler.getScaleX();
    addViewportJob(MoveViewJob.getInstance(mViewPortHandler, paramFloat1 - f2 / 2.0F, paramFloat2 + f1 / 2.0F, getTransformer(paramAxisDependency), this));
  }
  
  @TargetApi(11)
  public void centerViewToAnimated(float paramFloat1, float paramFloat2, YAxis.AxisDependency paramAxisDependency, long paramLong)
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      MPPointD localMPPointD = getValuesByTouchPoint(mViewPortHandler.contentLeft(), mViewPortHandler.contentTop(), paramAxisDependency);
      float f1 = getAxisRange(paramAxisDependency) / mViewPortHandler.getScaleY();
      float f2 = getXAxismAxisRange / mViewPortHandler.getScaleX();
      addViewportJob(AnimatedMoveViewJob.getInstance(mViewPortHandler, paramFloat1 - f2 / 2.0F, paramFloat2 + f1 / 2.0F, getTransformer(paramAxisDependency), this, (float)x, (float)y, paramLong));
      MPPointD.recycleInstance(localMPPointD);
      return;
    }
    Log.e("MPAndroidChart", "Unable to execute centerViewToAnimated(...) on API level < 11");
  }
  
  public void centerViewToY(float paramFloat, YAxis.AxisDependency paramAxisDependency)
  {
    float f = getAxisRange(paramAxisDependency) / mViewPortHandler.getScaleY();
    addViewportJob(MoveViewJob.getInstance(mViewPortHandler, 0.0F, paramFloat + f / 2.0F, getTransformer(paramAxisDependency), this));
  }
  
  public void computeScroll()
  {
    if ((mChartTouchListener instanceof BarLineChartTouchListener)) {
      ((BarLineChartTouchListener)mChartTouchListener).computeScroll();
    }
  }
  
  protected void drawGridBackground(Canvas paramCanvas)
  {
    if (mDrawGridBackground) {
      paramCanvas.drawRect(mViewPortHandler.getContentRect(), mGridBackgroundPaint);
    }
    if (mDrawBorders) {
      paramCanvas.drawRect(mViewPortHandler.getContentRect(), mBorderPaint);
    }
  }
  
  public void fitScreen()
  {
    Matrix localMatrix = mFitScreenMatrixBuffer;
    mViewPortHandler.fitScreen(localMatrix);
    mViewPortHandler.refresh(localMatrix, this, false);
    calculateOffsets();
    postInvalidate();
  }
  
  public YAxis getAxis(YAxis.AxisDependency paramAxisDependency)
  {
    if (paramAxisDependency == YAxis.AxisDependency.LEFT) {
      return mAxisLeft;
    }
    return mAxisRight;
  }
  
  public YAxis getAxisLeft()
  {
    return mAxisLeft;
  }
  
  protected float getAxisRange(YAxis.AxisDependency paramAxisDependency)
  {
    if (paramAxisDependency == YAxis.AxisDependency.LEFT) {
      return mAxisLeft.mAxisRange;
    }
    return mAxisRight.mAxisRange;
  }
  
  public YAxis getAxisRight()
  {
    return mAxisRight;
  }
  
  public IBarLineScatterCandleBubbleDataSet getDataSetByTouchPoint(float paramFloat1, float paramFloat2)
  {
    Highlight localHighlight = getHighlightByTouchPoint(paramFloat1, paramFloat2);
    if (localHighlight != null) {
      return (IBarLineScatterCandleBubbleDataSet)((BarLineScatterCandleBubbleData)mData).getDataSetByIndex(localHighlight.getDataSetIndex());
    }
    return null;
  }
  
  public OnDrawListener getDrawListener()
  {
    return mDrawListener;
  }
  
  public Entry getEntryByTouchPoint(float paramFloat1, float paramFloat2)
  {
    Highlight localHighlight = getHighlightByTouchPoint(paramFloat1, paramFloat2);
    if (localHighlight != null) {
      return ((BarLineScatterCandleBubbleData)mData).getEntryForHighlight(localHighlight);
    }
    return null;
  }
  
  public float getHighestVisibleX()
  {
    getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(mViewPortHandler.contentRight(), mViewPortHandler.contentBottom(), posForGetHighestVisibleX);
    return (float)Math.min(mXAxis.mAxisMaximum, posForGetHighestVisibleX.x);
  }
  
  public float getLowestVisibleX()
  {
    getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(mViewPortHandler.contentLeft(), mViewPortHandler.contentBottom(), posForGetLowestVisibleX);
    return (float)Math.max(mXAxis.mAxisMinimum, posForGetLowestVisibleX.x);
  }
  
  public int getMaxVisibleCount()
  {
    return mMaxVisibleCount;
  }
  
  public float getMinOffset()
  {
    return mMinOffset;
  }
  
  public Paint getPaint(int paramInt)
  {
    Paint localPaint = super.getPaint(paramInt);
    if (localPaint != null) {
      return localPaint;
    }
    if (paramInt != 4) {
      return null;
    }
    return mGridBackgroundPaint;
  }
  
  public MPPointD getPixelForValues(float paramFloat1, float paramFloat2, YAxis.AxisDependency paramAxisDependency)
  {
    return getTransformer(paramAxisDependency).getPixelForValues(paramFloat1, paramFloat2);
  }
  
  public MPPointF getPosition(Entry paramEntry, YAxis.AxisDependency paramAxisDependency)
  {
    if (paramEntry == null) {
      return null;
    }
    mGetPositionBuffer[0] = paramEntry.getX();
    mGetPositionBuffer[1] = paramEntry.getY();
    getTransformer(paramAxisDependency).pointValuesToPixel(mGetPositionBuffer);
    return MPPointF.getInstance(mGetPositionBuffer[0], mGetPositionBuffer[1]);
  }
  
  public YAxisRenderer getRendererLeftYAxis()
  {
    return mAxisRendererLeft;
  }
  
  public YAxisRenderer getRendererRightYAxis()
  {
    return mAxisRendererRight;
  }
  
  public XAxisRenderer getRendererXAxis()
  {
    return mXAxisRenderer;
  }
  
  public float getScaleX()
  {
    if (mViewPortHandler == null) {
      return 1.0F;
    }
    return mViewPortHandler.getScaleX();
  }
  
  public float getScaleY()
  {
    if (mViewPortHandler == null) {
      return 1.0F;
    }
    return mViewPortHandler.getScaleY();
  }
  
  public Transformer getTransformer(YAxis.AxisDependency paramAxisDependency)
  {
    if (paramAxisDependency == YAxis.AxisDependency.LEFT) {
      return mLeftAxisTransformer;
    }
    return mRightAxisTransformer;
  }
  
  public MPPointD getValuesByTouchPoint(float paramFloat1, float paramFloat2, YAxis.AxisDependency paramAxisDependency)
  {
    MPPointD localMPPointD = MPPointD.getInstance(0.0D, 0.0D);
    getValuesByTouchPoint(paramFloat1, paramFloat2, paramAxisDependency, localMPPointD);
    return localMPPointD;
  }
  
  public void getValuesByTouchPoint(float paramFloat1, float paramFloat2, YAxis.AxisDependency paramAxisDependency, MPPointD paramMPPointD)
  {
    getTransformer(paramAxisDependency).getValuesByTouchPoint(paramFloat1, paramFloat2, paramMPPointD);
  }
  
  public float getVisibleXRange()
  {
    return Math.abs(getHighestVisibleX() - getLowestVisibleX());
  }
  
  public float getYChartMax()
  {
    return Math.max(mAxisLeft.mAxisMaximum, mAxisRight.mAxisMaximum);
  }
  
  public float getYChartMin()
  {
    return Math.min(mAxisLeft.mAxisMinimum, mAxisRight.mAxisMinimum);
  }
  
  public boolean hasNoDragOffset()
  {
    return mViewPortHandler.hasNoDragOffset();
  }
  
  protected void init()
  {
    super.init();
    mAxisLeft = new YAxis(YAxis.AxisDependency.LEFT);
    mAxisRight = new YAxis(YAxis.AxisDependency.RIGHT);
    mLeftAxisTransformer = new Transformer(mViewPortHandler);
    mRightAxisTransformer = new Transformer(mViewPortHandler);
    mAxisRendererLeft = new YAxisRenderer(mViewPortHandler, mAxisLeft, mLeftAxisTransformer);
    mAxisRendererRight = new YAxisRenderer(mViewPortHandler, mAxisRight, mRightAxisTransformer);
    mXAxisRenderer = new XAxisRenderer(mViewPortHandler, mXAxis, mLeftAxisTransformer);
    setHighlighter(new ChartHighlighter(this));
    mChartTouchListener = new BarLineChartTouchListener(this, mViewPortHandler.getMatrixTouch(), 3.0F);
    mGridBackgroundPaint = new Paint();
    mGridBackgroundPaint.setStyle(Paint.Style.FILL);
    mGridBackgroundPaint.setColor(Color.rgb(240, 240, 240));
    mBorderPaint = new Paint();
    mBorderPaint.setStyle(Paint.Style.STROKE);
    mBorderPaint.setColor(-16777216);
    mBorderPaint.setStrokeWidth(Utils.convertDpToPixel(1.0F));
  }
  
  public boolean isAnyAxisInverted()
  {
    if (mAxisLeft.isInverted()) {
      return true;
    }
    return mAxisRight.isInverted();
  }
  
  public boolean isAutoScaleMinMaxEnabled()
  {
    return mAutoScaleMinMaxEnabled;
  }
  
  public boolean isClipValuesToContentEnabled()
  {
    return mClipValuesToContent;
  }
  
  public boolean isDoubleTapToZoomEnabled()
  {
    return mDoubleTapToZoomEnabled;
  }
  
  public boolean isDragEnabled()
  {
    return (mDragXEnabled) || (mDragYEnabled);
  }
  
  public boolean isDragXEnabled()
  {
    return mDragXEnabled;
  }
  
  public boolean isDragYEnabled()
  {
    return mDragYEnabled;
  }
  
  public boolean isDrawBordersEnabled()
  {
    return mDrawBorders;
  }
  
  public boolean isFullyZoomedOut()
  {
    return mViewPortHandler.isFullyZoomedOut();
  }
  
  public boolean isHighlightPerDragEnabled()
  {
    return mHighlightPerDragEnabled;
  }
  
  public boolean isInverted(YAxis.AxisDependency paramAxisDependency)
  {
    return getAxis(paramAxisDependency).isInverted();
  }
  
  public boolean isKeepPositionOnRotation()
  {
    return mKeepPositionOnRotation;
  }
  
  public boolean isPinchZoomEnabled()
  {
    return mPinchZoomEnabled;
  }
  
  public boolean isScaleXEnabled()
  {
    return mScaleXEnabled;
  }
  
  public boolean isScaleYEnabled()
  {
    return mScaleYEnabled;
  }
  
  public void moveViewTo(float paramFloat1, float paramFloat2, YAxis.AxisDependency paramAxisDependency)
  {
    float f = getAxisRange(paramAxisDependency) / mViewPortHandler.getScaleY();
    addViewportJob(MoveViewJob.getInstance(mViewPortHandler, paramFloat1, paramFloat2 + f / 2.0F, getTransformer(paramAxisDependency), this));
  }
  
  @TargetApi(11)
  public void moveViewToAnimated(float paramFloat1, float paramFloat2, YAxis.AxisDependency paramAxisDependency, long paramLong)
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      MPPointD localMPPointD = getValuesByTouchPoint(mViewPortHandler.contentLeft(), mViewPortHandler.contentTop(), paramAxisDependency);
      float f = getAxisRange(paramAxisDependency) / mViewPortHandler.getScaleY();
      addViewportJob(AnimatedMoveViewJob.getInstance(mViewPortHandler, paramFloat1, paramFloat2 + f / 2.0F, getTransformer(paramAxisDependency), this, (float)x, (float)y, paramLong));
      MPPointD.recycleInstance(localMPPointD);
      return;
    }
    Log.e("MPAndroidChart", "Unable to execute moveViewToAnimated(...) on API level < 11");
  }
  
  public void moveViewToX(float paramFloat)
  {
    addViewportJob(MoveViewJob.getInstance(mViewPortHandler, paramFloat, 0.0F, getTransformer(YAxis.AxisDependency.LEFT), this));
  }
  
  public void notifyDataSetChanged()
  {
    if (mData == null)
    {
      if (mLogEnabled) {
        Log.i("MPAndroidChart", "Preparing... DATA NOT SET.");
      }
      return;
    }
    if (mLogEnabled) {
      Log.i("MPAndroidChart", "Preparing...");
    }
    if (mRenderer != null) {
      mRenderer.initBuffers();
    }
    calcMinMax();
    mAxisRendererLeft.computeAxis(mAxisLeft.mAxisMinimum, mAxisLeft.mAxisMaximum, mAxisLeft.isInverted());
    mAxisRendererRight.computeAxis(mAxisRight.mAxisMinimum, mAxisRight.mAxisMaximum, mAxisRight.isInverted());
    mXAxisRenderer.computeAxis(mXAxis.mAxisMinimum, mXAxis.mAxisMaximum, false);
    if (mLegend != null) {
      mLegendRenderer.computeLegend(mData);
    }
    calculateOffsets();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (mData == null) {
      return;
    }
    long l1 = System.currentTimeMillis();
    drawGridBackground(paramCanvas);
    if (mAutoScaleMinMaxEnabled) {
      autoScale();
    }
    if (mAxisLeft.isEnabled()) {
      mAxisRendererLeft.computeAxis(mAxisLeft.mAxisMinimum, mAxisLeft.mAxisMaximum, mAxisLeft.isInverted());
    }
    if (mAxisRight.isEnabled()) {
      mAxisRendererRight.computeAxis(mAxisRight.mAxisMinimum, mAxisRight.mAxisMaximum, mAxisRight.isInverted());
    }
    if (mXAxis.isEnabled()) {
      mXAxisRenderer.computeAxis(mXAxis.mAxisMinimum, mXAxis.mAxisMaximum, false);
    }
    mXAxisRenderer.renderAxisLine(paramCanvas);
    mAxisRendererLeft.renderAxisLine(paramCanvas);
    mAxisRendererRight.renderAxisLine(paramCanvas);
    mXAxisRenderer.renderGridLines(paramCanvas);
    mAxisRendererLeft.renderGridLines(paramCanvas);
    mAxisRendererRight.renderGridLines(paramCanvas);
    if ((mXAxis.isEnabled()) && (mXAxis.isDrawLimitLinesBehindDataEnabled())) {
      mXAxisRenderer.renderLimitLines(paramCanvas);
    }
    if ((mAxisLeft.isEnabled()) && (mAxisLeft.isDrawLimitLinesBehindDataEnabled())) {
      mAxisRendererLeft.renderLimitLines(paramCanvas);
    }
    if ((mAxisRight.isEnabled()) && (mAxisRight.isDrawLimitLinesBehindDataEnabled())) {
      mAxisRendererRight.renderLimitLines(paramCanvas);
    }
    int i = paramCanvas.save();
    paramCanvas.clipRect(mViewPortHandler.getContentRect());
    mRenderer.drawData(paramCanvas);
    if (valuesToHighlight()) {
      mRenderer.drawHighlighted(paramCanvas, mIndicesToHighlight);
    }
    paramCanvas.restoreToCount(i);
    mRenderer.drawExtras(paramCanvas);
    if ((mXAxis.isEnabled()) && (!mXAxis.isDrawLimitLinesBehindDataEnabled())) {
      mXAxisRenderer.renderLimitLines(paramCanvas);
    }
    if ((mAxisLeft.isEnabled()) && (!mAxisLeft.isDrawLimitLinesBehindDataEnabled())) {
      mAxisRendererLeft.renderLimitLines(paramCanvas);
    }
    if ((mAxisRight.isEnabled()) && (!mAxisRight.isDrawLimitLinesBehindDataEnabled())) {
      mAxisRendererRight.renderLimitLines(paramCanvas);
    }
    mXAxisRenderer.renderAxisLabels(paramCanvas);
    mAxisRendererLeft.renderAxisLabels(paramCanvas);
    mAxisRendererRight.renderAxisLabels(paramCanvas);
    if (isClipValuesToContentEnabled())
    {
      i = paramCanvas.save();
      paramCanvas.clipRect(mViewPortHandler.getContentRect());
      mRenderer.drawValues(paramCanvas);
      paramCanvas.restoreToCount(i);
    }
    else
    {
      mRenderer.drawValues(paramCanvas);
    }
    mLegendRenderer.renderLegend(paramCanvas);
    drawDescription(paramCanvas);
    drawMarkers(paramCanvas);
    if (mLogEnabled)
    {
      l1 = System.currentTimeMillis() - l1;
      totalTime += l1;
      drawCycles += 1L;
      long l2 = totalTime / drawCycles;
      paramCanvas = new StringBuilder();
      paramCanvas.append("Drawtime: ");
      paramCanvas.append(l1);
      paramCanvas.append(" ms, average: ");
      paramCanvas.append(l2);
      paramCanvas.append(" ms, cycles: ");
      paramCanvas.append(drawCycles);
      Log.i("MPAndroidChart", paramCanvas.toString());
    }
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    float[] arrayOfFloat = mOnSizeChangedBuffer;
    mOnSizeChangedBuffer[1] = 0.0F;
    arrayOfFloat[0] = 0.0F;
    if (mKeepPositionOnRotation)
    {
      mOnSizeChangedBuffer[0] = mViewPortHandler.contentLeft();
      mOnSizeChangedBuffer[1] = mViewPortHandler.contentTop();
      getTransformer(YAxis.AxisDependency.LEFT).pixelsToValue(mOnSizeChangedBuffer);
    }
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (mKeepPositionOnRotation)
    {
      getTransformer(YAxis.AxisDependency.LEFT).pointValuesToPixel(mOnSizeChangedBuffer);
      mViewPortHandler.centerViewPort(mOnSizeChangedBuffer, this);
      return;
    }
    mViewPortHandler.refresh(mViewPortHandler.getMatrixTouch(), this, true);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    super.onTouchEvent(paramMotionEvent);
    if (mChartTouchListener != null)
    {
      if (mData == null) {
        return false;
      }
      if (!mTouchEnabled) {
        return false;
      }
      return mChartTouchListener.onTouch(this, paramMotionEvent);
    }
    return false;
  }
  
  protected void prepareOffsetMatrix()
  {
    mRightAxisTransformer.prepareMatrixOffset(mAxisRight.isInverted());
    mLeftAxisTransformer.prepareMatrixOffset(mAxisLeft.isInverted());
  }
  
  protected void prepareValuePxMatrix()
  {
    if (mLogEnabled)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Preparing Value-Px Matrix, xmin: ");
      localStringBuilder.append(mXAxis.mAxisMinimum);
      localStringBuilder.append(", xmax: ");
      localStringBuilder.append(mXAxis.mAxisMaximum);
      localStringBuilder.append(", xdelta: ");
      localStringBuilder.append(mXAxis.mAxisRange);
      Log.i("MPAndroidChart", localStringBuilder.toString());
    }
    mRightAxisTransformer.prepareMatrixValuePx(mXAxis.mAxisMinimum, mXAxis.mAxisRange, mAxisRight.mAxisRange, mAxisRight.mAxisMinimum);
    mLeftAxisTransformer.prepareMatrixValuePx(mXAxis.mAxisMinimum, mXAxis.mAxisRange, mAxisLeft.mAxisRange, mAxisLeft.mAxisMinimum);
  }
  
  public void resetTracking()
  {
    totalTime = 0L;
    drawCycles = 0L;
  }
  
  public void resetViewPortOffsets()
  {
    mCustomViewPortEnabled = false;
    calculateOffsets();
  }
  
  public void resetZoom()
  {
    mViewPortHandler.resetZoom(mZoomMatrixBuffer);
    mViewPortHandler.refresh(mZoomMatrixBuffer, this, false);
    calculateOffsets();
    postInvalidate();
  }
  
  public void setAutoScaleMinMaxEnabled(boolean paramBoolean)
  {
    mAutoScaleMinMaxEnabled = paramBoolean;
  }
  
  public void setBorderColor(int paramInt)
  {
    mBorderPaint.setColor(paramInt);
  }
  
  public void setBorderWidth(float paramFloat)
  {
    mBorderPaint.setStrokeWidth(Utils.convertDpToPixel(paramFloat));
  }
  
  public void setClipValuesToContent(boolean paramBoolean)
  {
    mClipValuesToContent = paramBoolean;
  }
  
  public void setDoubleTapToZoomEnabled(boolean paramBoolean)
  {
    mDoubleTapToZoomEnabled = paramBoolean;
  }
  
  public void setDragEnabled(boolean paramBoolean)
  {
    mDragXEnabled = paramBoolean;
    mDragYEnabled = paramBoolean;
  }
  
  public void setDragOffsetX(float paramFloat)
  {
    mViewPortHandler.setDragOffsetX(paramFloat);
  }
  
  public void setDragOffsetY(float paramFloat)
  {
    mViewPortHandler.setDragOffsetY(paramFloat);
  }
  
  public void setDragXEnabled(boolean paramBoolean)
  {
    mDragXEnabled = paramBoolean;
  }
  
  public void setDragYEnabled(boolean paramBoolean)
  {
    mDragYEnabled = paramBoolean;
  }
  
  public void setDrawBorders(boolean paramBoolean)
  {
    mDrawBorders = paramBoolean;
  }
  
  public void setDrawGridBackground(boolean paramBoolean)
  {
    mDrawGridBackground = paramBoolean;
  }
  
  public void setGridBackgroundColor(int paramInt)
  {
    mGridBackgroundPaint.setColor(paramInt);
  }
  
  public void setHighlightPerDragEnabled(boolean paramBoolean)
  {
    mHighlightPerDragEnabled = paramBoolean;
  }
  
  public void setKeepPositionOnRotation(boolean paramBoolean)
  {
    mKeepPositionOnRotation = paramBoolean;
  }
  
  public void setMaxVisibleValueCount(int paramInt)
  {
    mMaxVisibleCount = paramInt;
  }
  
  public void setMinOffset(float paramFloat)
  {
    mMinOffset = paramFloat;
  }
  
  public void setOnDrawListener(OnDrawListener paramOnDrawListener)
  {
    mDrawListener = paramOnDrawListener;
  }
  
  public void setPaint(Paint paramPaint, int paramInt)
  {
    super.setPaint(paramPaint, paramInt);
    if (paramInt != 4) {
      return;
    }
    mGridBackgroundPaint = paramPaint;
  }
  
  public void setPinchZoom(boolean paramBoolean)
  {
    mPinchZoomEnabled = paramBoolean;
  }
  
  public void setRendererLeftYAxis(YAxisRenderer paramYAxisRenderer)
  {
    mAxisRendererLeft = paramYAxisRenderer;
  }
  
  public void setRendererRightYAxis(YAxisRenderer paramYAxisRenderer)
  {
    mAxisRendererRight = paramYAxisRenderer;
  }
  
  public void setScaleEnabled(boolean paramBoolean)
  {
    mScaleXEnabled = paramBoolean;
    mScaleYEnabled = paramBoolean;
  }
  
  public void setScaleMinima(float paramFloat1, float paramFloat2)
  {
    mViewPortHandler.setMinimumScaleX(paramFloat1);
    mViewPortHandler.setMinimumScaleY(paramFloat2);
  }
  
  public void setScaleXEnabled(boolean paramBoolean)
  {
    mScaleXEnabled = paramBoolean;
  }
  
  public void setScaleYEnabled(boolean paramBoolean)
  {
    mScaleYEnabled = paramBoolean;
  }
  
  public void setViewPortOffsets(final float paramFloat1, final float paramFloat2, final float paramFloat3, final float paramFloat4)
  {
    mCustomViewPortEnabled = true;
    post(new Runnable()
    {
      public void run()
      {
        mViewPortHandler.restrainViewPort(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
        prepareOffsetMatrix();
        prepareValuePxMatrix();
      }
    });
  }
  
  public void setVisibleXRange(float paramFloat1, float paramFloat2)
  {
    paramFloat1 = mXAxis.mAxisRange / paramFloat1;
    paramFloat2 = mXAxis.mAxisRange / paramFloat2;
    mViewPortHandler.setMinMaxScaleX(paramFloat1, paramFloat2);
  }
  
  public void setVisibleXRangeMaximum(float paramFloat)
  {
    paramFloat = mXAxis.mAxisRange / paramFloat;
    mViewPortHandler.setMinimumScaleX(paramFloat);
  }
  
  public void setVisibleXRangeMinimum(float paramFloat)
  {
    paramFloat = mXAxis.mAxisRange / paramFloat;
    mViewPortHandler.setMaximumScaleX(paramFloat);
  }
  
  public void setVisibleYRange(float paramFloat1, float paramFloat2, YAxis.AxisDependency paramAxisDependency)
  {
    paramFloat1 = getAxisRange(paramAxisDependency) / paramFloat1;
    paramFloat2 = getAxisRange(paramAxisDependency) / paramFloat2;
    mViewPortHandler.setMinMaxScaleY(paramFloat1, paramFloat2);
  }
  
  public void setVisibleYRangeMaximum(float paramFloat, YAxis.AxisDependency paramAxisDependency)
  {
    paramFloat = getAxisRange(paramAxisDependency) / paramFloat;
    mViewPortHandler.setMinimumScaleY(paramFloat);
  }
  
  public void setVisibleYRangeMinimum(float paramFloat, YAxis.AxisDependency paramAxisDependency)
  {
    paramFloat = getAxisRange(paramAxisDependency) / paramFloat;
    mViewPortHandler.setMaximumScaleY(paramFloat);
  }
  
  public void setXAxisRenderer(XAxisRenderer paramXAxisRenderer)
  {
    mXAxisRenderer = paramXAxisRenderer;
  }
  
  public void zoom(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    mViewPortHandler.zoom(paramFloat1, paramFloat2, paramFloat3, -paramFloat4, mZoomMatrixBuffer);
    mViewPortHandler.refresh(mZoomMatrixBuffer, this, false);
    calculateOffsets();
    postInvalidate();
  }
  
  public void zoom(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, YAxis.AxisDependency paramAxisDependency)
  {
    addViewportJob(ZoomJob.getInstance(mViewPortHandler, paramFloat1, paramFloat2, paramFloat3, paramFloat4, getTransformer(paramAxisDependency), paramAxisDependency, this));
  }
  
  @TargetApi(11)
  public void zoomAndCenterAnimated(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, YAxis.AxisDependency paramAxisDependency, long paramLong)
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      MPPointD localMPPointD = getValuesByTouchPoint(mViewPortHandler.contentLeft(), mViewPortHandler.contentTop(), paramAxisDependency);
      addViewportJob(AnimatedZoomJob.getInstance(mViewPortHandler, this, getTransformer(paramAxisDependency), getAxis(paramAxisDependency), mXAxis.mAxisRange, paramFloat1, paramFloat2, mViewPortHandler.getScaleX(), mViewPortHandler.getScaleY(), paramFloat3, paramFloat4, (float)x, (float)y, paramLong));
      MPPointD.recycleInstance(localMPPointD);
      return;
    }
    Log.e("MPAndroidChart", "Unable to execute zoomAndCenterAnimated(...) on API level < 11");
  }
  
  public void zoomIn()
  {
    MPPointF localMPPointF = mViewPortHandler.getContentCenter();
    mViewPortHandler.zoomIn(x, -y, mZoomMatrixBuffer);
    mViewPortHandler.refresh(mZoomMatrixBuffer, this, false);
    MPPointF.recycleInstance(localMPPointF);
    calculateOffsets();
    postInvalidate();
  }
  
  public void zoomOut()
  {
    MPPointF localMPPointF = mViewPortHandler.getContentCenter();
    mViewPortHandler.zoomOut(x, -y, mZoomMatrixBuffer);
    mViewPortHandler.refresh(mZoomMatrixBuffer, this, false);
    MPPointF.recycleInstance(localMPPointF);
    calculateOffsets();
    postInvalidate();
  }
  
  public void zoomToCenter(float paramFloat1, float paramFloat2)
  {
    MPPointF localMPPointF = getCenterOffsets();
    Matrix localMatrix = mZoomMatrixBuffer;
    mViewPortHandler.zoom(paramFloat1, paramFloat2, x, -y, localMatrix);
    mViewPortHandler.refresh(localMatrix, this, false);
  }
}

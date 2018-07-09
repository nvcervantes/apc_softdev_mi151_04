package com.github.mikephil.charting.charts;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.animation.Easing.EasingOption;
import com.github.mikephil.charting.animation.EasingFunction;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.IHighlighter;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.renderer.LegendRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"NewApi"})
public abstract class Chart<T extends ChartData<? extends IDataSet<? extends Entry>>>
  extends ViewGroup
  implements ChartInterface
{
  public static final String LOG_TAG = "MPAndroidChart";
  public static final int PAINT_CENTER_TEXT = 14;
  public static final int PAINT_DESCRIPTION = 11;
  public static final int PAINT_GRID_BACKGROUND = 4;
  public static final int PAINT_HOLE = 13;
  public static final int PAINT_INFO = 7;
  public static final int PAINT_LEGEND_LABEL = 18;
  protected ChartAnimator mAnimator;
  protected ChartTouchListener mChartTouchListener;
  protected T mData = null;
  protected DefaultValueFormatter mDefaultValueFormatter = new DefaultValueFormatter(0);
  protected Paint mDescPaint;
  protected Description mDescription;
  private boolean mDragDecelerationEnabled = true;
  private float mDragDecelerationFrictionCoef = 0.9F;
  protected boolean mDrawMarkers = true;
  private float mExtraBottomOffset = 0.0F;
  private float mExtraLeftOffset = 0.0F;
  private float mExtraRightOffset = 0.0F;
  private float mExtraTopOffset = 0.0F;
  private OnChartGestureListener mGestureListener;
  protected boolean mHighLightPerTapEnabled = true;
  protected IHighlighter mHighlighter;
  protected Highlight[] mIndicesToHighlight;
  protected Paint mInfoPaint;
  protected ArrayList<Runnable> mJobs = new ArrayList();
  protected Legend mLegend;
  protected LegendRenderer mLegendRenderer;
  protected boolean mLogEnabled = false;
  protected IMarker mMarker;
  protected float mMaxHighlightDistance = 0.0F;
  private String mNoDataText = "No chart data available.";
  private boolean mOffsetsCalculated = false;
  protected DataRenderer mRenderer;
  protected OnChartValueSelectedListener mSelectionListener;
  protected boolean mTouchEnabled = true;
  private boolean mUnbind = false;
  protected ViewPortHandler mViewPortHandler = new ViewPortHandler();
  protected XAxis mXAxis;
  
  public Chart(Context paramContext)
  {
    super(paramContext);
    init();
  }
  
  public Chart(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }
  
  public Chart(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }
  
  private void unbindDrawables(View paramView)
  {
    if (paramView.getBackground() != null) {
      paramView.getBackground().setCallback(null);
    }
    if ((paramView instanceof ViewGroup))
    {
      int i = 0;
      ViewGroup localViewGroup;
      for (;;)
      {
        localViewGroup = (ViewGroup)paramView;
        if (i >= localViewGroup.getChildCount()) {
          break;
        }
        unbindDrawables(localViewGroup.getChildAt(i));
        i += 1;
      }
      localViewGroup.removeAllViews();
    }
  }
  
  public void addViewportJob(Runnable paramRunnable)
  {
    if (mViewPortHandler.hasChartDimens())
    {
      post(paramRunnable);
      return;
    }
    mJobs.add(paramRunnable);
  }
  
  public void animateX(int paramInt)
  {
    mAnimator.animateX(paramInt);
  }
  
  public void animateX(int paramInt, Easing.EasingOption paramEasingOption)
  {
    mAnimator.animateX(paramInt, paramEasingOption);
  }
  
  public void animateX(int paramInt, EasingFunction paramEasingFunction)
  {
    mAnimator.animateX(paramInt, paramEasingFunction);
  }
  
  public void animateXY(int paramInt1, int paramInt2)
  {
    mAnimator.animateXY(paramInt1, paramInt2);
  }
  
  public void animateXY(int paramInt1, int paramInt2, Easing.EasingOption paramEasingOption1, Easing.EasingOption paramEasingOption2)
  {
    mAnimator.animateXY(paramInt1, paramInt2, paramEasingOption1, paramEasingOption2);
  }
  
  public void animateXY(int paramInt1, int paramInt2, EasingFunction paramEasingFunction1, EasingFunction paramEasingFunction2)
  {
    mAnimator.animateXY(paramInt1, paramInt2, paramEasingFunction1, paramEasingFunction2);
  }
  
  public void animateY(int paramInt)
  {
    mAnimator.animateY(paramInt);
  }
  
  public void animateY(int paramInt, Easing.EasingOption paramEasingOption)
  {
    mAnimator.animateY(paramInt, paramEasingOption);
  }
  
  public void animateY(int paramInt, EasingFunction paramEasingFunction)
  {
    mAnimator.animateY(paramInt, paramEasingFunction);
  }
  
  protected abstract void calcMinMax();
  
  protected abstract void calculateOffsets();
  
  public void clear()
  {
    mData = null;
    mOffsetsCalculated = false;
    mIndicesToHighlight = null;
    mChartTouchListener.setLastHighlighted(null);
    invalidate();
  }
  
  public void clearAllViewportJobs()
  {
    mJobs.clear();
  }
  
  public void clearValues()
  {
    mData.clearValues();
    invalidate();
  }
  
  public void disableScroll()
  {
    ViewParent localViewParent = getParent();
    if (localViewParent != null) {
      localViewParent.requestDisallowInterceptTouchEvent(true);
    }
  }
  
  protected void drawDescription(Canvas paramCanvas)
  {
    if ((mDescription != null) && (mDescription.isEnabled()))
    {
      MPPointF localMPPointF = mDescription.getPosition();
      mDescPaint.setTypeface(mDescription.getTypeface());
      mDescPaint.setTextSize(mDescription.getTextSize());
      mDescPaint.setColor(mDescription.getTextColor());
      mDescPaint.setTextAlign(mDescription.getTextAlign());
      float f1;
      float f2;
      if (localMPPointF == null)
      {
        f1 = getWidth() - mViewPortHandler.offsetRight() - mDescription.getXOffset();
        f2 = getHeight() - mViewPortHandler.offsetBottom() - mDescription.getYOffset();
      }
      else
      {
        f1 = x;
        f2 = y;
      }
      paramCanvas.drawText(mDescription.getText(), f1, f2, mDescPaint);
    }
  }
  
  protected void drawMarkers(Canvas paramCanvas)
  {
    if ((mMarker != null) && (isDrawMarkersEnabled()))
    {
      if (!valuesToHighlight()) {
        return;
      }
      int i = 0;
      while (i < mIndicesToHighlight.length)
      {
        Highlight localHighlight = mIndicesToHighlight[i];
        Object localObject = mData.getDataSetByIndex(localHighlight.getDataSetIndex());
        Entry localEntry = mData.getEntryForHighlight(mIndicesToHighlight[i]);
        int j = ((IDataSet)localObject).getEntryIndex(localEntry);
        if ((localEntry != null) && (j <= ((IDataSet)localObject).getEntryCount() * mAnimator.getPhaseX()))
        {
          localObject = getMarkerPosition(localHighlight);
          if (mViewPortHandler.isInBounds(localObject[0], localObject[1]))
          {
            mMarker.refreshContent(localEntry, localHighlight);
            mMarker.draw(paramCanvas, localObject[0], localObject[1]);
          }
        }
        i += 1;
      }
      return;
    }
  }
  
  public void enableScroll()
  {
    ViewParent localViewParent = getParent();
    if (localViewParent != null) {
      localViewParent.requestDisallowInterceptTouchEvent(false);
    }
  }
  
  public ChartAnimator getAnimator()
  {
    return mAnimator;
  }
  
  public MPPointF getCenter()
  {
    return MPPointF.getInstance(getWidth() / 2.0F, getHeight() / 2.0F);
  }
  
  public MPPointF getCenterOfView()
  {
    return getCenter();
  }
  
  public MPPointF getCenterOffsets()
  {
    return mViewPortHandler.getContentCenter();
  }
  
  public Bitmap getChartBitmap()
  {
    Bitmap localBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
    Canvas localCanvas = new Canvas(localBitmap);
    Drawable localDrawable = getBackground();
    if (localDrawable != null) {
      localDrawable.draw(localCanvas);
    } else {
      localCanvas.drawColor(-1);
    }
    draw(localCanvas);
    return localBitmap;
  }
  
  public RectF getContentRect()
  {
    return mViewPortHandler.getContentRect();
  }
  
  public T getData()
  {
    return mData;
  }
  
  public IValueFormatter getDefaultValueFormatter()
  {
    return mDefaultValueFormatter;
  }
  
  public Description getDescription()
  {
    return mDescription;
  }
  
  public float getDragDecelerationFrictionCoef()
  {
    return mDragDecelerationFrictionCoef;
  }
  
  public float getExtraBottomOffset()
  {
    return mExtraBottomOffset;
  }
  
  public float getExtraLeftOffset()
  {
    return mExtraLeftOffset;
  }
  
  public float getExtraRightOffset()
  {
    return mExtraRightOffset;
  }
  
  public float getExtraTopOffset()
  {
    return mExtraTopOffset;
  }
  
  public Highlight getHighlightByTouchPoint(float paramFloat1, float paramFloat2)
  {
    if (mData == null)
    {
      Log.e("MPAndroidChart", "Can't select by touch. No data set.");
      return null;
    }
    return getHighlighter().getHighlight(paramFloat1, paramFloat2);
  }
  
  public Highlight[] getHighlighted()
  {
    return mIndicesToHighlight;
  }
  
  public IHighlighter getHighlighter()
  {
    return mHighlighter;
  }
  
  public ArrayList<Runnable> getJobs()
  {
    return mJobs;
  }
  
  public Legend getLegend()
  {
    return mLegend;
  }
  
  public LegendRenderer getLegendRenderer()
  {
    return mLegendRenderer;
  }
  
  public IMarker getMarker()
  {
    return mMarker;
  }
  
  protected float[] getMarkerPosition(Highlight paramHighlight)
  {
    return new float[] { paramHighlight.getDrawX(), paramHighlight.getDrawY() };
  }
  
  @Deprecated
  public IMarker getMarkerView()
  {
    return getMarker();
  }
  
  public float getMaxHighlightDistance()
  {
    return mMaxHighlightDistance;
  }
  
  public OnChartGestureListener getOnChartGestureListener()
  {
    return mGestureListener;
  }
  
  public ChartTouchListener getOnTouchListener()
  {
    return mChartTouchListener;
  }
  
  public Paint getPaint(int paramInt)
  {
    if (paramInt != 7)
    {
      if (paramInt != 11) {
        return null;
      }
      return mDescPaint;
    }
    return mInfoPaint;
  }
  
  public DataRenderer getRenderer()
  {
    return mRenderer;
  }
  
  public ViewPortHandler getViewPortHandler()
  {
    return mViewPortHandler;
  }
  
  public XAxis getXAxis()
  {
    return mXAxis;
  }
  
  public float getXChartMax()
  {
    return mXAxis.mAxisMaximum;
  }
  
  public float getXChartMin()
  {
    return mXAxis.mAxisMinimum;
  }
  
  public float getXRange()
  {
    return mXAxis.mAxisRange;
  }
  
  public float getYMax()
  {
    return mData.getYMax();
  }
  
  public float getYMin()
  {
    return mData.getYMin();
  }
  
  public void highlightValue(float paramFloat1, float paramFloat2, int paramInt)
  {
    highlightValue(paramFloat1, paramFloat2, paramInt, true);
  }
  
  public void highlightValue(float paramFloat1, float paramFloat2, int paramInt, boolean paramBoolean)
  {
    if ((paramInt >= 0) && (paramInt < mData.getDataSetCount()))
    {
      highlightValue(new Highlight(paramFloat1, paramFloat2, paramInt), paramBoolean);
      return;
    }
    highlightValue(null, paramBoolean);
  }
  
  public void highlightValue(float paramFloat, int paramInt)
  {
    highlightValue(paramFloat, paramInt, true);
  }
  
  public void highlightValue(float paramFloat, int paramInt, boolean paramBoolean)
  {
    highlightValue(paramFloat, NaN.0F, paramInt, paramBoolean);
  }
  
  public void highlightValue(Highlight paramHighlight)
  {
    highlightValue(paramHighlight, false);
  }
  
  public void highlightValue(Highlight paramHighlight, boolean paramBoolean)
  {
    Object localObject;
    if (paramHighlight == null)
    {
      mIndicesToHighlight = null;
      localObject = null;
    }
    else
    {
      if (mLogEnabled)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Highlighted: ");
        ((StringBuilder)localObject).append(paramHighlight.toString());
        Log.i("MPAndroidChart", ((StringBuilder)localObject).toString());
      }
      localObject = mData.getEntryForHighlight(paramHighlight);
      if (localObject == null)
      {
        mIndicesToHighlight = null;
        paramHighlight = null;
      }
      else
      {
        mIndicesToHighlight = new Highlight[] { paramHighlight };
      }
    }
    setLastHighlighted(mIndicesToHighlight);
    if ((paramBoolean) && (mSelectionListener != null)) {
      if (!valuesToHighlight()) {
        mSelectionListener.onNothingSelected();
      } else {
        mSelectionListener.onValueSelected((Entry)localObject, paramHighlight);
      }
    }
    invalidate();
  }
  
  public void highlightValues(Highlight[] paramArrayOfHighlight)
  {
    mIndicesToHighlight = paramArrayOfHighlight;
    setLastHighlighted(paramArrayOfHighlight);
    invalidate();
  }
  
  protected void init()
  {
    setWillNotDraw(false);
    if (Build.VERSION.SDK_INT < 11) {
      mAnimator = new ChartAnimator();
    } else {
      mAnimator = new ChartAnimator(new ValueAnimator.AnimatorUpdateListener()
      {
        public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
        {
          postInvalidate();
        }
      });
    }
    Utils.init(getContext());
    mMaxHighlightDistance = Utils.convertDpToPixel(500.0F);
    mDescription = new Description();
    mLegend = new Legend();
    mLegendRenderer = new LegendRenderer(mViewPortHandler, mLegend);
    mXAxis = new XAxis();
    mDescPaint = new Paint(1);
    mInfoPaint = new Paint(1);
    mInfoPaint.setColor(Color.rgb(247, 189, 51));
    mInfoPaint.setTextAlign(Paint.Align.CENTER);
    mInfoPaint.setTextSize(Utils.convertDpToPixel(12.0F));
    if (mLogEnabled) {
      Log.i("", "Chart.init()");
    }
  }
  
  public boolean isDragDecelerationEnabled()
  {
    return mDragDecelerationEnabled;
  }
  
  @Deprecated
  public boolean isDrawMarkerViewsEnabled()
  {
    return isDrawMarkersEnabled();
  }
  
  public boolean isDrawMarkersEnabled()
  {
    return mDrawMarkers;
  }
  
  public boolean isEmpty()
  {
    if (mData == null) {
      return true;
    }
    return mData.getEntryCount() <= 0;
  }
  
  public boolean isHighlightPerTapEnabled()
  {
    return mHighLightPerTapEnabled;
  }
  
  public boolean isLogEnabled()
  {
    return mLogEnabled;
  }
  
  public abstract void notifyDataSetChanged();
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (mUnbind) {
      unbindDrawables(this);
    }
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    if (mData == null)
    {
      if ((TextUtils.isEmpty(mNoDataText) ^ true))
      {
        MPPointF localMPPointF = getCenter();
        paramCanvas.drawText(mNoDataText, x, y, mInfoPaint);
      }
      return;
    }
    if (!mOffsetsCalculated)
    {
      calculateOffsets();
      mOffsetsCalculated = true;
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = 0;
    while (i < getChildCount())
    {
      getChildAt(i).layout(paramInt1, paramInt2, paramInt3, paramInt4);
      i += 1;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i = (int)Utils.convertDpToPixel(50.0F);
    setMeasuredDimension(Math.max(getSuggestedMinimumWidth(), resolveSize(i, paramInt1)), Math.max(getSuggestedMinimumHeight(), resolveSize(i, paramInt2)));
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (mLogEnabled) {
      Log.i("MPAndroidChart", "OnSizeChanged()");
    }
    if ((paramInt1 > 0) && (paramInt2 > 0) && (paramInt1 < 10000) && (paramInt2 < 10000))
    {
      if (mLogEnabled)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Setting chart dimens, width: ");
        ((StringBuilder)localObject).append(paramInt1);
        ((StringBuilder)localObject).append(", height: ");
        ((StringBuilder)localObject).append(paramInt2);
        Log.i("MPAndroidChart", ((StringBuilder)localObject).toString());
      }
      mViewPortHandler.setChartDimens(paramInt1, paramInt2);
    }
    else if (mLogEnabled)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("*Avoiding* setting chart dimens! width: ");
      ((StringBuilder)localObject).append(paramInt1);
      ((StringBuilder)localObject).append(", height: ");
      ((StringBuilder)localObject).append(paramInt2);
      Log.w("MPAndroidChart", ((StringBuilder)localObject).toString());
    }
    notifyDataSetChanged();
    Object localObject = mJobs.iterator();
    while (((Iterator)localObject).hasNext()) {
      post((Runnable)((Iterator)localObject).next());
    }
    mJobs.clear();
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void removeViewportJob(Runnable paramRunnable)
  {
    mJobs.remove(paramRunnable);
  }
  
  public boolean saveToGallery(String paramString, int paramInt)
  {
    return saveToGallery(paramString, "", "MPAndroidChart-Library Save", Bitmap.CompressFormat.JPEG, paramInt);
  }
  
  public boolean saveToGallery(String paramString1, String paramString2, String paramString3, Bitmap.CompressFormat paramCompressFormat, int paramInt)
  {
    int i;
    if (paramInt >= 0)
    {
      i = paramInt;
      if (paramInt <= 100) {}
    }
    else
    {
      i = 50;
    }
    long l1 = System.currentTimeMillis();
    Object localObject1 = Environment.getExternalStorageDirectory();
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(((File)localObject1).getAbsolutePath());
    ((StringBuilder)localObject2).append("/DCIM/");
    ((StringBuilder)localObject2).append(paramString2);
    File localFile = new File(((StringBuilder)localObject2).toString());
    boolean bool2 = localFile.exists();
    boolean bool1 = false;
    if ((!bool2) && (!localFile.mkdirs())) {
      return false;
    }
    switch (2.$SwitchMap$android$graphics$Bitmap$CompressFormat[paramCompressFormat.ordinal()])
    {
    default: 
      localObject2 = "image/jpeg";
      paramString2 = paramString1;
      localObject1 = localObject2;
      if (!paramString1.endsWith(".jpg"))
      {
        paramString2 = paramString1;
        localObject1 = localObject2;
        if (!paramString1.endsWith(".jpeg"))
        {
          paramString2 = new StringBuilder();
          paramString2.append(paramString1);
          paramString2.append(".jpg");
          paramString2 = paramString2.toString();
          localObject1 = localObject2;
        }
      }
      break;
    case 2: 
      localObject2 = "image/webp";
      paramString2 = paramString1;
      localObject1 = localObject2;
      if (!paramString1.endsWith(".webp"))
      {
        paramString2 = new StringBuilder();
        paramString2.append(paramString1);
        paramString2.append(".webp");
        paramString2 = paramString2.toString();
        localObject1 = localObject2;
      }
      break;
    case 1: 
      localObject2 = "image/png";
      paramString2 = paramString1;
      localObject1 = localObject2;
      if (!paramString1.endsWith(".png"))
      {
        paramString2 = new StringBuilder();
        paramString2.append(paramString1);
        paramString2.append(".png");
        paramString2 = paramString2.toString();
        localObject1 = localObject2;
      }
      break;
    }
    paramString1 = new StringBuilder();
    paramString1.append(localFile.getAbsolutePath());
    paramString1.append("/");
    paramString1.append(paramString2);
    paramString1 = paramString1.toString();
    try
    {
      localObject2 = new FileOutputStream(paramString1);
      getChartBitmap().compress(paramCompressFormat, i, (OutputStream)localObject2);
      ((FileOutputStream)localObject2).flush();
      ((FileOutputStream)localObject2).close();
      long l2 = new File(paramString1).length();
      paramCompressFormat = new ContentValues(8);
      paramCompressFormat.put("title", paramString2);
      paramCompressFormat.put("_display_name", paramString2);
      paramCompressFormat.put("date_added", Long.valueOf(l1));
      paramCompressFormat.put("mime_type", (String)localObject1);
      paramCompressFormat.put("description", paramString3);
      paramCompressFormat.put("orientation", Integer.valueOf(0));
      paramCompressFormat.put("_data", paramString1);
      paramCompressFormat.put("_size", Long.valueOf(l2));
      if (getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, paramCompressFormat) != null) {
        bool1 = true;
      }
      return bool1;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
    }
    return false;
  }
  
  public boolean saveToPath(String paramString1, String paramString2)
  {
    Bitmap localBitmap = getChartBitmap();
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(Environment.getExternalStorageDirectory().getPath());
      localStringBuilder.append(paramString2);
      localStringBuilder.append("/");
      localStringBuilder.append(paramString1);
      localStringBuilder.append(".png");
      paramString1 = new FileOutputStream(localStringBuilder.toString());
      localBitmap.compress(Bitmap.CompressFormat.PNG, 40, paramString1);
      paramString1.close();
      return true;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return false;
  }
  
  public void setData(T paramT)
  {
    mData = paramT;
    mOffsetsCalculated = false;
    if (paramT == null) {
      return;
    }
    setupDefaultFormatter(paramT.getYMin(), paramT.getYMax());
    paramT = mData.getDataSets().iterator();
    while (paramT.hasNext())
    {
      IDataSet localIDataSet = (IDataSet)paramT.next();
      if ((localIDataSet.needsFormatter()) || (localIDataSet.getValueFormatter() == mDefaultValueFormatter)) {
        localIDataSet.setValueFormatter(mDefaultValueFormatter);
      }
    }
    notifyDataSetChanged();
    if (mLogEnabled) {
      Log.i("MPAndroidChart", "Data is set.");
    }
  }
  
  public void setDescription(Description paramDescription)
  {
    mDescription = paramDescription;
  }
  
  public void setDragDecelerationEnabled(boolean paramBoolean)
  {
    mDragDecelerationEnabled = paramBoolean;
  }
  
  public void setDragDecelerationFrictionCoef(float paramFloat)
  {
    float f = paramFloat;
    if (paramFloat < 0.0F) {
      f = 0.0F;
    }
    paramFloat = f;
    if (f >= 1.0F) {
      paramFloat = 0.999F;
    }
    mDragDecelerationFrictionCoef = paramFloat;
  }
  
  @Deprecated
  public void setDrawMarkerViews(boolean paramBoolean)
  {
    setDrawMarkers(paramBoolean);
  }
  
  public void setDrawMarkers(boolean paramBoolean)
  {
    mDrawMarkers = paramBoolean;
  }
  
  public void setExtraBottomOffset(float paramFloat)
  {
    mExtraBottomOffset = Utils.convertDpToPixel(paramFloat);
  }
  
  public void setExtraLeftOffset(float paramFloat)
  {
    mExtraLeftOffset = Utils.convertDpToPixel(paramFloat);
  }
  
  public void setExtraOffsets(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    setExtraLeftOffset(paramFloat1);
    setExtraTopOffset(paramFloat2);
    setExtraRightOffset(paramFloat3);
    setExtraBottomOffset(paramFloat4);
  }
  
  public void setExtraRightOffset(float paramFloat)
  {
    mExtraRightOffset = Utils.convertDpToPixel(paramFloat);
  }
  
  public void setExtraTopOffset(float paramFloat)
  {
    mExtraTopOffset = Utils.convertDpToPixel(paramFloat);
  }
  
  public void setHardwareAccelerationEnabled(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      if (paramBoolean)
      {
        setLayerType(2, null);
        return;
      }
      setLayerType(1, null);
      return;
    }
    Log.e("MPAndroidChart", "Cannot enable/disable hardware acceleration for devices below API level 11.");
  }
  
  public void setHighlightPerTapEnabled(boolean paramBoolean)
  {
    mHighLightPerTapEnabled = paramBoolean;
  }
  
  public void setHighlighter(ChartHighlighter paramChartHighlighter)
  {
    mHighlighter = paramChartHighlighter;
  }
  
  protected void setLastHighlighted(Highlight[] paramArrayOfHighlight)
  {
    if ((paramArrayOfHighlight != null) && (paramArrayOfHighlight.length > 0) && (paramArrayOfHighlight[0] != null))
    {
      mChartTouchListener.setLastHighlighted(paramArrayOfHighlight[0]);
      return;
    }
    mChartTouchListener.setLastHighlighted(null);
  }
  
  public void setLogEnabled(boolean paramBoolean)
  {
    mLogEnabled = paramBoolean;
  }
  
  public void setMarker(IMarker paramIMarker)
  {
    mMarker = paramIMarker;
  }
  
  @Deprecated
  public void setMarkerView(IMarker paramIMarker)
  {
    setMarker(paramIMarker);
  }
  
  public void setMaxHighlightDistance(float paramFloat)
  {
    mMaxHighlightDistance = Utils.convertDpToPixel(paramFloat);
  }
  
  public void setNoDataText(String paramString)
  {
    mNoDataText = paramString;
  }
  
  public void setNoDataTextColor(int paramInt)
  {
    mInfoPaint.setColor(paramInt);
  }
  
  public void setNoDataTextTypeface(Typeface paramTypeface)
  {
    mInfoPaint.setTypeface(paramTypeface);
  }
  
  public void setOnChartGestureListener(OnChartGestureListener paramOnChartGestureListener)
  {
    mGestureListener = paramOnChartGestureListener;
  }
  
  public void setOnChartValueSelectedListener(OnChartValueSelectedListener paramOnChartValueSelectedListener)
  {
    mSelectionListener = paramOnChartValueSelectedListener;
  }
  
  public void setOnTouchListener(ChartTouchListener paramChartTouchListener)
  {
    mChartTouchListener = paramChartTouchListener;
  }
  
  public void setPaint(Paint paramPaint, int paramInt)
  {
    if (paramInt != 7)
    {
      if (paramInt != 11) {
        return;
      }
      mDescPaint = paramPaint;
      return;
    }
    mInfoPaint = paramPaint;
  }
  
  public void setRenderer(DataRenderer paramDataRenderer)
  {
    if (paramDataRenderer != null) {
      mRenderer = paramDataRenderer;
    }
  }
  
  public void setTouchEnabled(boolean paramBoolean)
  {
    mTouchEnabled = paramBoolean;
  }
  
  public void setUnbindEnabled(boolean paramBoolean)
  {
    mUnbind = paramBoolean;
  }
  
  protected void setupDefaultFormatter(float paramFloat1, float paramFloat2)
  {
    if ((mData != null) && (mData.getEntryCount() >= 2)) {
      paramFloat1 = Math.abs(paramFloat2 - paramFloat1);
    } else {
      paramFloat1 = Math.max(Math.abs(paramFloat1), Math.abs(paramFloat2));
    }
    int i = Utils.getDecimals(paramFloat1);
    mDefaultValueFormatter.setup(i);
  }
  
  public boolean valuesToHighlight()
  {
    Highlight[] arrayOfHighlight = mIndicesToHighlight;
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (arrayOfHighlight != null)
    {
      bool1 = bool2;
      if (mIndicesToHighlight.length > 0)
      {
        if (mIndicesToHighlight[0] == null) {
          return false;
        }
        bool1 = true;
      }
    }
    return bool1;
  }
}

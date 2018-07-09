package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class AxisRenderer
  extends Renderer
{
  protected AxisBase mAxis;
  protected Paint mAxisLabelPaint;
  protected Paint mAxisLinePaint;
  protected Paint mGridPaint;
  protected Paint mLimitLinePaint;
  protected Transformer mTrans;
  
  public AxisRenderer(ViewPortHandler paramViewPortHandler, Transformer paramTransformer, AxisBase paramAxisBase)
  {
    super(paramViewPortHandler);
    mTrans = paramTransformer;
    mAxis = paramAxisBase;
    if (mViewPortHandler != null)
    {
      mAxisLabelPaint = new Paint(1);
      mGridPaint = new Paint();
      mGridPaint.setColor(-7829368);
      mGridPaint.setStrokeWidth(1.0F);
      mGridPaint.setStyle(Paint.Style.STROKE);
      mGridPaint.setAlpha(90);
      mAxisLinePaint = new Paint();
      mAxisLinePaint.setColor(-16777216);
      mAxisLinePaint.setStrokeWidth(1.0F);
      mAxisLinePaint.setStyle(Paint.Style.STROKE);
      mLimitLinePaint = new Paint(1);
      mLimitLinePaint.setStyle(Paint.Style.STROKE);
    }
  }
  
  public void computeAxis(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    float f2 = paramFloat1;
    float f1 = paramFloat2;
    if (mViewPortHandler != null)
    {
      f2 = paramFloat1;
      f1 = paramFloat2;
      if (mViewPortHandler.contentWidth() > 10.0F)
      {
        f2 = paramFloat1;
        f1 = paramFloat2;
        if (!mViewPortHandler.isFullyZoomedOutY())
        {
          MPPointD localMPPointD1 = mTrans.getValuesByTouchPoint(mViewPortHandler.contentLeft(), mViewPortHandler.contentTop());
          MPPointD localMPPointD2 = mTrans.getValuesByTouchPoint(mViewPortHandler.contentLeft(), mViewPortHandler.contentBottom());
          if (!paramBoolean)
          {
            paramFloat2 = (float)y;
            paramFloat1 = (float)y;
          }
          else
          {
            paramFloat2 = (float)y;
            paramFloat1 = (float)y;
          }
          MPPointD.recycleInstance(localMPPointD1);
          MPPointD.recycleInstance(localMPPointD2);
          f1 = paramFloat1;
          f2 = paramFloat2;
        }
      }
    }
    computeAxisValues(f2, f1);
  }
  
  protected void computeAxisValues(float paramFloat1, float paramFloat2)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public Paint getPaintAxisLabels()
  {
    return mAxisLabelPaint;
  }
  
  public Paint getPaintAxisLine()
  {
    return mAxisLinePaint;
  }
  
  public Paint getPaintGrid()
  {
    return mGridPaint;
  }
  
  public Transformer getTransformer()
  {
    return mTrans;
  }
  
  public abstract void renderAxisLabels(Canvas paramCanvas);
  
  public abstract void renderAxisLine(Canvas paramCanvas);
  
  public abstract void renderGridLines(Canvas paramCanvas);
  
  public abstract void renderLimitLines(Canvas paramCanvas);
}

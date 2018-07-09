package com.github.mikephil.charting.jobs;

import android.graphics.Matrix;
import android.view.View;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.utils.ObjectPool;
import com.github.mikephil.charting.utils.ObjectPool.Poolable;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class ZoomJob
  extends ViewPortJob
{
  private static ObjectPool<ZoomJob> pool = ObjectPool.create(1, new ZoomJob(null, 0.0F, 0.0F, 0.0F, 0.0F, null, null, null));
  protected YAxis.AxisDependency axisDependency;
  protected Matrix mRunMatrixBuffer = new Matrix();
  protected float scaleX;
  protected float scaleY;
  
  static
  {
    pool.setReplenishPercentage(0.5F);
  }
  
  public ZoomJob(ViewPortHandler paramViewPortHandler, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Transformer paramTransformer, YAxis.AxisDependency paramAxisDependency, View paramView)
  {
    super(paramViewPortHandler, paramFloat3, paramFloat4, paramTransformer, paramView);
    scaleX = paramFloat1;
    scaleY = paramFloat2;
    axisDependency = paramAxisDependency;
  }
  
  public static ZoomJob getInstance(ViewPortHandler paramViewPortHandler, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Transformer paramTransformer, YAxis.AxisDependency paramAxisDependency, View paramView)
  {
    ZoomJob localZoomJob = (ZoomJob)pool.get();
    xValue = paramFloat3;
    yValue = paramFloat4;
    scaleX = paramFloat1;
    scaleY = paramFloat2;
    mViewPortHandler = paramViewPortHandler;
    mTrans = paramTransformer;
    axisDependency = paramAxisDependency;
    view = paramView;
    return localZoomJob;
  }
  
  public static void recycleInstance(ZoomJob paramZoomJob)
  {
    pool.recycle(paramZoomJob);
  }
  
  protected ObjectPool.Poolable instantiate()
  {
    return new ZoomJob(null, 0.0F, 0.0F, 0.0F, 0.0F, null, null, null);
  }
  
  public void run()
  {
    Matrix localMatrix = mRunMatrixBuffer;
    mViewPortHandler.zoom(scaleX, scaleY, localMatrix);
    mViewPortHandler.refresh(localMatrix, view, false);
    float f1 = view).getAxis(axisDependency).mAxisRange / mViewPortHandler.getScaleY();
    float f2 = view).getXAxis().mAxisRange / mViewPortHandler.getScaleX();
    pts[0] = (xValue - f2 / 2.0F);
    pts[1] = (yValue + f1 / 2.0F);
    mTrans.pointValuesToPixel(pts);
    mViewPortHandler.translate(pts, localMatrix);
    mViewPortHandler.refresh(localMatrix, view, false);
    ((BarLineChartBase)view).calculateOffsets();
    view.postInvalidate();
    recycleInstance(this);
  }
}

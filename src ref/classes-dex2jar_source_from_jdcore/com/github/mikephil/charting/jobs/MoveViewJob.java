package com.github.mikephil.charting.jobs;

import android.view.View;
import com.github.mikephil.charting.utils.ObjectPool;
import com.github.mikephil.charting.utils.ObjectPool.Poolable;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class MoveViewJob
  extends ViewPortJob
{
  private static ObjectPool<MoveViewJob> pool = ObjectPool.create(2, new MoveViewJob(null, 0.0F, 0.0F, null, null));
  
  static
  {
    pool.setReplenishPercentage(0.5F);
  }
  
  public MoveViewJob(ViewPortHandler paramViewPortHandler, float paramFloat1, float paramFloat2, Transformer paramTransformer, View paramView)
  {
    super(paramViewPortHandler, paramFloat1, paramFloat2, paramTransformer, paramView);
  }
  
  public static MoveViewJob getInstance(ViewPortHandler paramViewPortHandler, float paramFloat1, float paramFloat2, Transformer paramTransformer, View paramView)
  {
    MoveViewJob localMoveViewJob = (MoveViewJob)pool.get();
    mViewPortHandler = paramViewPortHandler;
    xValue = paramFloat1;
    yValue = paramFloat2;
    mTrans = paramTransformer;
    view = paramView;
    return localMoveViewJob;
  }
  
  public static void recycleInstance(MoveViewJob paramMoveViewJob)
  {
    pool.recycle(paramMoveViewJob);
  }
  
  protected ObjectPool.Poolable instantiate()
  {
    return new MoveViewJob(mViewPortHandler, xValue, yValue, mTrans, view);
  }
  
  public void run()
  {
    pts[0] = xValue;
    pts[1] = yValue;
    mTrans.pointValuesToPixel(pts);
    mViewPortHandler.centerViewPort(pts, view);
    recycleInstance(this);
  }
}

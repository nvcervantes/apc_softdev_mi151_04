package com.github.mikephil.charting.jobs;

import android.view.View;
import com.github.mikephil.charting.utils.ObjectPool.Poolable;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class ViewPortJob
  extends ObjectPool.Poolable
  implements Runnable
{
  protected Transformer mTrans;
  protected ViewPortHandler mViewPortHandler;
  protected float[] pts = new float[2];
  protected View view;
  protected float xValue = 0.0F;
  protected float yValue = 0.0F;
  
  public ViewPortJob(ViewPortHandler paramViewPortHandler, float paramFloat1, float paramFloat2, Transformer paramTransformer, View paramView)
  {
    mViewPortHandler = paramViewPortHandler;
    xValue = paramFloat1;
    yValue = paramFloat2;
    mTrans = paramTransformer;
    view = paramView;
  }
  
  public float getXValue()
  {
    return xValue;
  }
  
  public float getYValue()
  {
    return yValue;
  }
}

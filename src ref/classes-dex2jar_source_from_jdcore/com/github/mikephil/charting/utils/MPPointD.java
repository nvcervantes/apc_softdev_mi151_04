package com.github.mikephil.charting.utils;

import java.util.List;

public class MPPointD
  extends ObjectPool.Poolable
{
  private static ObjectPool<MPPointD> pool = ObjectPool.create(64, new MPPointD(0.0D, 0.0D));
  public double x;
  public double y;
  
  static
  {
    pool.setReplenishPercentage(0.5F);
  }
  
  private MPPointD(double paramDouble1, double paramDouble2)
  {
    x = paramDouble1;
    y = paramDouble2;
  }
  
  public static MPPointD getInstance(double paramDouble1, double paramDouble2)
  {
    MPPointD localMPPointD = (MPPointD)pool.get();
    x = paramDouble1;
    y = paramDouble2;
    return localMPPointD;
  }
  
  public static void recycleInstance(MPPointD paramMPPointD)
  {
    pool.recycle(paramMPPointD);
  }
  
  public static void recycleInstances(List<MPPointD> paramList)
  {
    pool.recycle(paramList);
  }
  
  protected ObjectPool.Poolable instantiate()
  {
    return new MPPointD(0.0D, 0.0D);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("MPPointD, x: ");
    localStringBuilder.append(x);
    localStringBuilder.append(", y: ");
    localStringBuilder.append(y);
    return localStringBuilder.toString();
  }
}

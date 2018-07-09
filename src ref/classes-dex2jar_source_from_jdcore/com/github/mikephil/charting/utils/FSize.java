package com.github.mikephil.charting.utils;

import java.util.List;

public final class FSize
  extends ObjectPool.Poolable
{
  private static ObjectPool<FSize> pool = ObjectPool.create(256, new FSize(0.0F, 0.0F));
  public float height;
  public float width;
  
  static
  {
    pool.setReplenishPercentage(0.5F);
  }
  
  public FSize() {}
  
  public FSize(float paramFloat1, float paramFloat2)
  {
    width = paramFloat1;
    height = paramFloat2;
  }
  
  public static FSize getInstance(float paramFloat1, float paramFloat2)
  {
    FSize localFSize = (FSize)pool.get();
    width = paramFloat1;
    height = paramFloat2;
    return localFSize;
  }
  
  public static void recycleInstance(FSize paramFSize)
  {
    pool.recycle(paramFSize);
  }
  
  public static void recycleInstances(List<FSize> paramList)
  {
    pool.recycle(paramList);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    if (paramObject == null) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof FSize))
    {
      paramObject = (FSize)paramObject;
      boolean bool1 = bool2;
      if (width == width)
      {
        bool1 = bool2;
        if (height == height) {
          bool1 = true;
        }
      }
      return bool1;
    }
    return false;
  }
  
  public int hashCode()
  {
    return Float.floatToIntBits(width) ^ Float.floatToIntBits(height);
  }
  
  protected ObjectPool.Poolable instantiate()
  {
    return new FSize(0.0F, 0.0F);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(width);
    localStringBuilder.append("x");
    localStringBuilder.append(height);
    return localStringBuilder.toString();
  }
}

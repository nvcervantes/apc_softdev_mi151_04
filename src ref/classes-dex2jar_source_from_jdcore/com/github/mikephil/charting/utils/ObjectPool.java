package com.github.mikephil.charting.utils;

import java.util.List;

public class ObjectPool<T extends Poolable>
{
  private static int ids;
  private int desiredCapacity;
  private T modelObject;
  private Object[] objects;
  private int objectsPointer;
  private int poolId;
  private float replenishPercentage;
  
  private ObjectPool(int paramInt, T paramT)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("Object Pool must be instantiated with a capacity greater than 0!");
    }
    desiredCapacity = paramInt;
    objects = new Object[desiredCapacity];
    objectsPointer = 0;
    modelObject = paramT;
    replenishPercentage = 1.0F;
    refillPool();
  }
  
  public static ObjectPool create(int paramInt, Poolable paramPoolable)
  {
    try
    {
      paramPoolable = new ObjectPool(paramInt, paramPoolable);
      poolId = ids;
      ids += 1;
      return paramPoolable;
    }
    finally
    {
      paramPoolable = finally;
      throw paramPoolable;
    }
  }
  
  private void refillPool()
  {
    refillPool(replenishPercentage);
  }
  
  private void refillPool(float paramFloat)
  {
    int j = (int)(desiredCapacity * paramFloat);
    int i;
    if (j < 1)
    {
      i = 1;
    }
    else
    {
      i = j;
      if (j > desiredCapacity) {
        i = desiredCapacity;
      }
    }
    j = 0;
    while (j < i)
    {
      objects[j] = modelObject.instantiate();
      j += 1;
    }
    objectsPointer = (i - 1);
  }
  
  private void resizePool()
  {
    int j = desiredCapacity;
    desiredCapacity *= 2;
    Object[] arrayOfObject = new Object[desiredCapacity];
    int i = 0;
    while (i < j)
    {
      arrayOfObject[i] = objects[i];
      i += 1;
    }
    objects = arrayOfObject;
  }
  
  public T get()
  {
    try
    {
      if ((objectsPointer == -1) && (replenishPercentage > 0.0F)) {
        refillPool();
      }
      Poolable localPoolable = (Poolable)objects[objectsPointer];
      currentOwnerId = Poolable.NO_OWNER;
      objectsPointer -= 1;
      return localPoolable;
    }
    finally {}
  }
  
  public int getPoolCapacity()
  {
    return objects.length;
  }
  
  public int getPoolCount()
  {
    return objectsPointer + 1;
  }
  
  public int getPoolId()
  {
    return poolId;
  }
  
  public float getReplenishPercentage()
  {
    return replenishPercentage;
  }
  
  public void recycle(T paramT)
  {
    try
    {
      if (currentOwnerId != Poolable.NO_OWNER)
      {
        if (currentOwnerId == poolId) {
          throw new IllegalArgumentException("The object passed is already stored in this pool!");
        }
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("The object to recycle already belongs to poolId ");
        localStringBuilder.append(currentOwnerId);
        localStringBuilder.append(".  Object cannot belong to two different pool instances simultaneously!");
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      objectsPointer += 1;
      if (objectsPointer >= objects.length) {
        resizePool();
      }
      currentOwnerId = poolId;
      objects[objectsPointer] = paramT;
      return;
    }
    finally {}
  }
  
  public void recycle(List<T> paramList)
  {
    try
    {
      while (paramList.size() + objectsPointer + 1 > desiredCapacity) {
        resizePool();
      }
      int j = paramList.size();
      int i = 0;
      while (i < j)
      {
        Poolable localPoolable = (Poolable)paramList.get(i);
        if (currentOwnerId != Poolable.NO_OWNER)
        {
          if (currentOwnerId == poolId) {
            throw new IllegalArgumentException("The object passed is already stored in this pool!");
          }
          paramList = new StringBuilder();
          paramList.append("The object to recycle already belongs to poolId ");
          paramList.append(currentOwnerId);
          paramList.append(".  Object cannot belong to two different pool instances simultaneously!");
          throw new IllegalArgumentException(paramList.toString());
        }
        currentOwnerId = poolId;
        objects[(objectsPointer + 1 + i)] = localPoolable;
        i += 1;
      }
      objectsPointer += j;
      return;
    }
    finally {}
  }
  
  public void setReplenishPercentage(float paramFloat)
  {
    float f;
    if (paramFloat > 1.0F)
    {
      f = 1.0F;
    }
    else
    {
      f = paramFloat;
      if (paramFloat < 0.0F) {
        f = 0.0F;
      }
    }
    replenishPercentage = f;
  }
  
  public static abstract class Poolable
  {
    public static int NO_OWNER = -1;
    int currentOwnerId = NO_OWNER;
    
    public Poolable() {}
    
    protected abstract Poolable instantiate();
  }
}

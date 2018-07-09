package com.github.mikephil.charting.utils;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;

public class MPPointF
  extends ObjectPool.Poolable
{
  public static final Parcelable.Creator<MPPointF> CREATOR = new Parcelable.Creator()
  {
    public MPPointF createFromParcel(Parcel paramAnonymousParcel)
    {
      MPPointF localMPPointF = new MPPointF(0.0F, 0.0F);
      localMPPointF.my_readFromParcel(paramAnonymousParcel);
      return localMPPointF;
    }
    
    public MPPointF[] newArray(int paramAnonymousInt)
    {
      return new MPPointF[paramAnonymousInt];
    }
  };
  private static ObjectPool<MPPointF> pool = ObjectPool.create(32, new MPPointF(0.0F, 0.0F));
  public float x;
  public float y;
  
  static
  {
    pool.setReplenishPercentage(0.5F);
  }
  
  public MPPointF() {}
  
  public MPPointF(float paramFloat1, float paramFloat2)
  {
    x = paramFloat1;
    y = paramFloat2;
  }
  
  public static MPPointF getInstance()
  {
    return (MPPointF)pool.get();
  }
  
  public static MPPointF getInstance(float paramFloat1, float paramFloat2)
  {
    MPPointF localMPPointF = (MPPointF)pool.get();
    x = paramFloat1;
    y = paramFloat2;
    return localMPPointF;
  }
  
  public static MPPointF getInstance(MPPointF paramMPPointF)
  {
    MPPointF localMPPointF = (MPPointF)pool.get();
    x = x;
    y = y;
    return localMPPointF;
  }
  
  public static void recycleInstance(MPPointF paramMPPointF)
  {
    pool.recycle(paramMPPointF);
  }
  
  public static void recycleInstances(List<MPPointF> paramList)
  {
    pool.recycle(paramList);
  }
  
  public float getX()
  {
    return x;
  }
  
  public float getY()
  {
    return y;
  }
  
  protected ObjectPool.Poolable instantiate()
  {
    return new MPPointF(0.0F, 0.0F);
  }
  
  public void my_readFromParcel(Parcel paramParcel)
  {
    x = paramParcel.readFloat();
    y = paramParcel.readFloat();
  }
}

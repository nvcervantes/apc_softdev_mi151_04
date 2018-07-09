package com.google.common.geometry;

public final class R2Vector
{
  private final double x;
  private final double y;
  
  public strictfp R2Vector()
  {
    this(0.0D, 0.0D);
  }
  
  public strictfp R2Vector(double paramDouble1, double paramDouble2)
  {
    x = paramDouble1;
    y = paramDouble2;
  }
  
  public strictfp R2Vector(double[] paramArrayOfDouble)
  {
    if (paramArrayOfDouble.length != 2) {
      throw new IllegalStateException("Points must have exactly 2 coordinates");
    }
    x = paramArrayOfDouble[0];
    y = paramArrayOfDouble[1];
  }
  
  public static strictfp R2Vector add(R2Vector paramR2Vector1, R2Vector paramR2Vector2)
  {
    return new R2Vector(x + x, y + y);
  }
  
  public static strictfp double dotProd(R2Vector paramR2Vector1, R2Vector paramR2Vector2)
  {
    return x * x + y * y;
  }
  
  public static strictfp R2Vector mul(R2Vector paramR2Vector, double paramDouble)
  {
    return new R2Vector(x * paramDouble, paramDouble * y);
  }
  
  public strictfp double crossProd(R2Vector paramR2Vector)
  {
    return x * y - y * x;
  }
  
  public strictfp double dotProd(R2Vector paramR2Vector)
  {
    return dotProd(this, paramR2Vector);
  }
  
  public strictfp boolean equals(Object paramObject)
  {
    boolean bool1 = paramObject instanceof R2Vector;
    boolean bool2 = false;
    if (!bool1) {
      return false;
    }
    paramObject = (R2Vector)paramObject;
    bool1 = bool2;
    if (x == x)
    {
      bool1 = bool2;
      if (y == y) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public strictfp double get(int paramInt)
  {
    if (paramInt > 1) {
      throw new ArrayIndexOutOfBoundsException(paramInt);
    }
    if (paramInt == 0) {
      return x;
    }
    return y;
  }
  
  public strictfp int hashCode()
  {
    long l = 17L + (629L + Double.doubleToLongBits(Math.abs(x)));
    l += 37L * l + Double.doubleToLongBits(Math.abs(y));
    return (int)(l ^ l >>> 32);
  }
  
  public strictfp boolean lessThan(R2Vector paramR2Vector)
  {
    if (x < x) {
      return true;
    }
    if (x < x) {
      return false;
    }
    return y < y;
  }
  
  public strictfp double norm2()
  {
    return x * x + y * y;
  }
  
  public strictfp String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("(");
    localStringBuilder.append(x);
    localStringBuilder.append(", ");
    localStringBuilder.append(y);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public strictfp double x()
  {
    return x;
  }
  
  public strictfp double y()
  {
    return y;
  }
}

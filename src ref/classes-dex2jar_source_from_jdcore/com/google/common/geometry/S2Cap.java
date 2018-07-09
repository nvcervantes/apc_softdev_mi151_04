package com.google.common.geometry;

public final class S2Cap
  implements S2Region
{
  private static final double ROUND_UP = 1.0000000000000002D;
  private final S2Point axis;
  private final double height;
  
  private strictfp S2Cap()
  {
    axis = new S2Point();
    height = 0.0D;
  }
  
  private strictfp S2Cap(S2Point paramS2Point, double paramDouble)
  {
    axis = paramS2Point;
    height = paramDouble;
  }
  
  public static strictfp S2Cap empty()
  {
    return new S2Cap(new S2Point(1.0D, 0.0D, 0.0D), -1.0D);
  }
  
  public static strictfp S2Cap fromAxisAngle(S2Point paramS2Point, S1Angle paramS1Angle)
  {
    double d = Math.sin(0.5D * paramS1Angle.radians());
    return new S2Cap(paramS2Point, 2.0D * d * d);
  }
  
  public static strictfp S2Cap fromAxisArea(S2Point paramS2Point, double paramDouble)
  {
    return new S2Cap(paramS2Point, paramDouble / 6.283185307179586D);
  }
  
  public static strictfp S2Cap fromAxisHeight(S2Point paramS2Point, double paramDouble)
  {
    return new S2Cap(paramS2Point, paramDouble);
  }
  
  public static strictfp S2Cap full()
  {
    return new S2Cap(new S2Point(1.0D, 0.0D, 0.0D), 2.0D);
  }
  
  public strictfp S2Cap addCap(S2Cap paramS2Cap)
  {
    if (isEmpty()) {
      return new S2Cap(axis, height);
    }
    double d = axis.angle(axis) + paramS2Cap.angle().radians();
    if (d >= 3.141592653589793D) {
      return new S2Cap(axis, 2.0D);
    }
    d = Math.sin(0.5D * d);
    d = Math.max(height, 2.0000000000000004D * d * d);
    return new S2Cap(axis, d);
  }
  
  public strictfp S2Cap addPoint(S2Point paramS2Point)
  {
    if (isEmpty()) {
      return new S2Cap(paramS2Point, 0.0D);
    }
    double d = S2Point.sub(axis, paramS2Point).norm2();
    d = Math.max(height, 0.5000000000000001D * d);
    return new S2Cap(axis, d);
  }
  
  public strictfp S1Angle angle()
  {
    if (isEmpty()) {
      return S1Angle.radians(-1.0D);
    }
    return S1Angle.radians(2.0D * Math.asin(Math.sqrt(0.5D * height)));
  }
  
  strictfp boolean approxEquals(S2Cap paramS2Cap)
  {
    return approxEquals(paramS2Cap, 1.0E-14D);
  }
  
  strictfp boolean approxEquals(S2Cap paramS2Cap, double paramDouble)
  {
    return ((axis.aequal(axis, paramDouble)) && (Math.abs(height - height) <= paramDouble)) || ((isEmpty()) && (height <= paramDouble)) || ((paramS2Cap.isEmpty()) && (height <= paramDouble)) || ((isFull()) && (height >= 2.0D - paramDouble)) || ((paramS2Cap.isFull()) && (height >= 2.0D - paramDouble));
  }
  
  public strictfp double area()
  {
    return 6.283185307179586D * Math.max(0.0D, height);
  }
  
  public strictfp S2Point axis()
  {
    return axis;
  }
  
  public strictfp S2Cap complement()
  {
    double d;
    if (isFull()) {
      d = -1.0D;
    } else {
      d = 2.0D - Math.max(height, 0.0D);
    }
    return fromAxisHeight(S2Point.neg(axis), d);
  }
  
  public strictfp boolean contains(S2Cap paramS2Cap)
  {
    if (!isFull())
    {
      if (paramS2Cap.isEmpty()) {
        return true;
      }
      return angle().radians() >= axis.angle(axis) + paramS2Cap.angle().radians();
    }
    return true;
  }
  
  public strictfp boolean contains(S2Cell paramS2Cell)
  {
    S2Point[] arrayOfS2Point = new S2Point[4];
    int i = 0;
    while (i < 4)
    {
      arrayOfS2Point[i] = paramS2Cell.getVertex(i);
      if (!contains(arrayOfS2Point[i])) {
        return false;
      }
      i += 1;
    }
    return complement().intersects(paramS2Cell, arrayOfS2Point) ^ true;
  }
  
  public strictfp boolean contains(S2Point paramS2Point)
  {
    return S2Point.sub(axis, paramS2Point).norm2() <= 2.0D * height;
  }
  
  public strictfp boolean equals(Object paramObject)
  {
    boolean bool1 = paramObject instanceof S2Cap;
    boolean bool2 = false;
    if (!bool1) {
      return false;
    }
    paramObject = (S2Cap)paramObject;
    if (((!axis.equals(axis)) || (height != height)) && ((!isEmpty()) || (!paramObject.isEmpty())))
    {
      bool1 = bool2;
      if (isFull())
      {
        bool1 = bool2;
        if (!paramObject.isFull()) {}
      }
    }
    else
    {
      bool1 = true;
    }
    return bool1;
  }
  
  public strictfp S2Cap getCapBound()
  {
    return this;
  }
  
  public strictfp S2LatLngRect getRectBound()
  {
    if (isEmpty()) {
      return S2LatLngRect.empty();
    }
    S2LatLng localS2LatLng = new S2LatLng(axis);
    double d1 = angle().radians();
    double[] arrayOfDouble1 = new double[2];
    double[] arrayOfDouble2 = new double[2];
    double[] tmp44_42 = arrayOfDouble2;
    tmp44_42[0] = -3.141592653589793D;
    double[] tmp50_44 = tmp44_42;
    tmp50_44[1] = 3.141592653589793D;
    tmp50_44;
    tmp44_42[0] = (localS2LatLng.lat().radians() - d1);
    int i;
    if (tmp44_42[0] <= -1.5707963267948966D)
    {
      tmp44_42[0] = -1.5707963267948966D;
      i = 1;
    }
    else
    {
      i = 0;
    }
    tmp44_42[1] = (localS2LatLng.lat().radians() + d1);
    if (tmp44_42[1] >= 1.5707963267948966D)
    {
      tmp44_42[1] = 1.5707963267948966D;
      i = 1;
    }
    if (i == 0)
    {
      d1 = Math.sqrt(height * (2.0D - height));
      double d2 = Math.cos(localS2LatLng.lat().radians());
      if (d1 <= d2)
      {
        d1 = Math.asin(d1 / d2);
        tmp50_44[0] = Math.IEEEremainder(localS2LatLng.lng().radians() - d1, 6.283185307179586D);
        tmp50_44[1] = Math.IEEEremainder(localS2LatLng.lng().radians() + d1, 6.283185307179586D);
      }
    }
    return new S2LatLngRect(new R1Interval(tmp44_42[0], tmp44_42[1]), new S1Interval(tmp50_44[0], tmp50_44[1]));
  }
  
  public strictfp int hashCode()
  {
    if (isFull()) {
      return 17;
    }
    if (isEmpty()) {
      return 37;
    }
    int i = axis.hashCode();
    long l = Double.doubleToLongBits(height);
    return 37 * (629 + i) + (int)(l >>> 32 ^ l);
  }
  
  public strictfp double height()
  {
    return height;
  }
  
  public strictfp boolean interiorContains(S2Point paramS2Point)
  {
    return (isFull()) || (S2Point.sub(axis, paramS2Point).norm2() < 2.0D * height);
  }
  
  public strictfp boolean interiorIntersects(S2Cap paramS2Cap)
  {
    return complement().contains(paramS2Cap) ^ true;
  }
  
  public strictfp boolean intersects(S2Cell paramS2Cell, S2Point[] paramArrayOfS2Point)
  {
    if (height >= 1.0D) {
      return false;
    }
    if (isEmpty()) {
      return false;
    }
    if (paramS2Cell.contains(axis)) {
      return true;
    }
    double d1 = height;
    double d2 = height;
    int i = 0;
    while (i < 4)
    {
      S2Point localS2Point = paramS2Cell.getEdgeRaw(i);
      double d3 = axis.dotProd(localS2Point);
      if (d3 <= 0.0D)
      {
        if (d3 * d3 > localS2Point.norm2() * (d1 * (2.0D - d2))) {
          return false;
        }
        localS2Point = S2Point.crossProd(localS2Point, axis);
        if ((localS2Point.dotProd(paramArrayOfS2Point[i]) < 0.0D) && (localS2Point.dotProd(paramArrayOfS2Point[(i + 1 & 0x3)]) > 0.0D)) {
          return true;
        }
      }
      i += 1;
    }
    return false;
  }
  
  public strictfp boolean isEmpty()
  {
    return height < 0.0D;
  }
  
  public strictfp boolean isFull()
  {
    return height >= 2.0D;
  }
  
  public strictfp boolean isValid()
  {
    return (S2.isUnitLength(axis)) && (height <= 2.0D);
  }
  
  public strictfp boolean mayIntersect(S2Cell paramS2Cell)
  {
    S2Point[] arrayOfS2Point = new S2Point[4];
    int i = 0;
    while (i < 4)
    {
      arrayOfS2Point[i] = paramS2Cell.getVertex(i);
      if (contains(arrayOfS2Point[i])) {
        return true;
      }
      i += 1;
    }
    return intersects(paramS2Cell, arrayOfS2Point);
  }
  
  public strictfp String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[Point = ");
    localStringBuilder.append(axis.toString());
    localStringBuilder.append(" Height = ");
    localStringBuilder.append(height);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

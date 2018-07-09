package com.google.common.geometry;

public class S2Point
  implements Comparable<S2Point>
{
  final double x;
  final double y;
  final double z;
  
  public strictfp S2Point()
  {
    z = 0.0D;
    y = 0.0D;
    x = 0.0D;
  }
  
  public strictfp S2Point(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    x = paramDouble1;
    y = paramDouble2;
    z = paramDouble3;
  }
  
  public static strictfp S2Point add(S2Point paramS2Point1, S2Point paramS2Point2)
  {
    return new S2Point(x + x, y + y, z + z);
  }
  
  public static strictfp S2Point crossProd(S2Point paramS2Point1, S2Point paramS2Point2)
  {
    return new S2Point(y * z - z * y, z * x - x * z, x * y - y * x);
  }
  
  public static strictfp S2Point div(S2Point paramS2Point, double paramDouble)
  {
    return new S2Point(x / paramDouble, y / paramDouble, z / paramDouble);
  }
  
  public static strictfp S2Point fabs(S2Point paramS2Point)
  {
    return new S2Point(Math.abs(x), Math.abs(y), Math.abs(z));
  }
  
  public static strictfp S2Point minus(S2Point paramS2Point1, S2Point paramS2Point2)
  {
    return sub(paramS2Point1, paramS2Point2);
  }
  
  public static strictfp S2Point mul(S2Point paramS2Point, double paramDouble)
  {
    return new S2Point(paramDouble * x, y * paramDouble, z * paramDouble);
  }
  
  public static strictfp S2Point neg(S2Point paramS2Point)
  {
    return new S2Point(-x, -y, -z);
  }
  
  public static strictfp S2Point normalize(S2Point paramS2Point)
  {
    double d2 = paramS2Point.norm();
    double d1 = d2;
    if (d2 != 0.0D) {
      d1 = 1.0D / d2;
    }
    return mul(paramS2Point, d1);
  }
  
  public static strictfp S2Point sub(S2Point paramS2Point1, S2Point paramS2Point2)
  {
    return new S2Point(x - x, y - y, z - z);
  }
  
  strictfp boolean aequal(S2Point paramS2Point, double paramDouble)
  {
    return (Math.abs(x - x) < paramDouble) && (Math.abs(y - y) < paramDouble) && (Math.abs(z - z) < paramDouble);
  }
  
  public strictfp double angle(S2Point paramS2Point)
  {
    return Math.atan2(crossProd(this, paramS2Point).norm(), dotProd(paramS2Point));
  }
  
  public strictfp int compareTo(S2Point paramS2Point)
  {
    if (lessThan(paramS2Point)) {
      return -1;
    }
    if (equals(paramS2Point)) {
      return 0;
    }
    return 1;
  }
  
  public strictfp double dotProd(S2Point paramS2Point)
  {
    return x * x + y * y + z * z;
  }
  
  public strictfp boolean equals(Object paramObject)
  {
    boolean bool1 = paramObject instanceof S2Point;
    boolean bool2 = false;
    if (!bool1) {
      return false;
    }
    paramObject = (S2Point)paramObject;
    bool1 = bool2;
    if (x == x)
    {
      bool1 = bool2;
      if (y == y)
      {
        bool1 = bool2;
        if (z == z) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public strictfp double get(int paramInt)
  {
    if (paramInt == 0) {
      return x;
    }
    if (paramInt == 1) {
      return y;
    }
    return z;
  }
  
  public strictfp int hashCode()
  {
    long l = 17L + (629L + Double.doubleToLongBits(Math.abs(x)));
    l += 37L * l + Double.doubleToLongBits(Math.abs(y));
    l += 37L * l + Double.doubleToLongBits(Math.abs(z));
    return (int)(l ^ l >>> 32);
  }
  
  public strictfp int largestAbsComponent()
  {
    S2Point localS2Point = fabs(this);
    if (x > y)
    {
      if (x > z) {
        return 0;
      }
      return 2;
    }
    if (y > z) {
      return 1;
    }
    return 2;
  }
  
  public strictfp boolean lessThan(S2Point paramS2Point)
  {
    if (x < x) {
      return true;
    }
    if (x < x) {
      return false;
    }
    if (y < y) {
      return true;
    }
    if (y < y) {
      return false;
    }
    return z < z;
  }
  
  public strictfp double norm()
  {
    return Math.sqrt(norm2());
  }
  
  public strictfp double norm2()
  {
    return x * x + y * y + z * z;
  }
  
  public strictfp S2Point ortho()
  {
    int i = largestAbsComponent();
    S2Point localS2Point;
    if (i == 1) {
      localS2Point = new S2Point(1.0D, 0.0D, 0.0D);
    }
    for (;;)
    {
      break;
      if (i == 2) {
        localS2Point = new S2Point(0.0D, 1.0D, 0.0D);
      } else {
        localS2Point = new S2Point(0.0D, 0.0D, 1.0D);
      }
    }
    return normalize(crossProd(this, localS2Point));
  }
  
  public strictfp String toDegreesString()
  {
    S2LatLng localS2LatLng = new S2LatLng(this);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("(");
    localStringBuilder.append(Double.toString(localS2LatLng.latDegrees()));
    localStringBuilder.append(", ");
    localStringBuilder.append(Double.toString(localS2LatLng.lngDegrees()));
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public strictfp String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("(");
    localStringBuilder.append(x);
    localStringBuilder.append(", ");
    localStringBuilder.append(y);
    localStringBuilder.append(", ");
    localStringBuilder.append(z);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

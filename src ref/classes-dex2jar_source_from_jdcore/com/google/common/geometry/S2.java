package com.google.common.geometry;

public final class S2
{
  private static final long EXPONENT_MASK = 9218868437227405312L;
  private static final int EXPONENT_SHIFT = 52;
  private static final int[][] IJ_TO_POS = { { 0, 1, 3, 2 }, { 0, 3, 1, 2 }, { 2, 3, 1, 0 }, { 2, 1, 3, 0 } };
  public static final int INVERT_MASK = 2;
  public static final double M_1_PI = 0.3183098861837907D;
  public static final double M_E = 2.718281828459045D;
  public static final double M_PI = 3.141592653589793D;
  public static final double M_PI_2 = 1.5707963267948966D;
  public static final double M_PI_4 = 0.7853981633974483D;
  public static final double M_SQRT2 = Math.sqrt(2.0D);
  private static final int[][] POS_TO_IJ;
  private static final int[] POS_TO_ORIENTATION = { 1, 0, 0, 3 };
  public static final int SWAP_MASK = 1;
  
  static
  {
    POS_TO_IJ = new int[][] { { 0, 1, 3, 2 }, { 0, 2, 3, 1 }, { 3, 2, 0, 1 }, { 3, 1, 0, 2 } };
  }
  
  private strictfp S2() {}
  
  public static strictfp double angle(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3)
  {
    return S2Point.crossProd(paramS2Point1, paramS2Point2).angle(S2Point.crossProd(paramS2Point3, paramS2Point2));
  }
  
  public static strictfp boolean approxEquals(double paramDouble1, double paramDouble2)
  {
    return approxEquals(paramDouble1, paramDouble2, 1.0E-15D);
  }
  
  public static strictfp boolean approxEquals(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    return Math.abs(paramDouble1 - paramDouble2) <= paramDouble3;
  }
  
  public static strictfp boolean approxEquals(S2Point paramS2Point1, S2Point paramS2Point2)
  {
    return approxEquals(paramS2Point1, paramS2Point2, 1.0E-15D);
  }
  
  public static strictfp boolean approxEquals(S2Point paramS2Point1, S2Point paramS2Point2, double paramDouble)
  {
    return paramS2Point1.angle(paramS2Point2) <= paramDouble;
  }
  
  static strictfp double area(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3)
  {
    double d1 = paramS2Point2.angle(paramS2Point3);
    double d2 = paramS2Point3.angle(paramS2Point1);
    double d3 = paramS2Point1.angle(paramS2Point2);
    double d4 = (d1 + d2 + d3) * 0.5D;
    if (d4 >= 3.0E-4D)
    {
      double d6 = d4 * d4;
      double d5 = d4 - Math.max(d1, Math.max(d2, d3));
      if (d5 < 0.01D * d4 * d6 * d6)
      {
        d6 = girardArea(paramS2Point1, paramS2Point2, paramS2Point3);
        if (d5 < 0.1D * d6 * d4) {
          return d6;
        }
      }
    }
    return 4.0D * Math.atan(Math.sqrt(Math.max(0.0D, Math.tan(0.5D * d4) * Math.tan((d4 - d1) * 0.5D) * Math.tan((d4 - d2) * 0.5D) * Math.tan(0.5D * (d4 - d3)))));
  }
  
  static strictfp int exp(double paramDouble)
  {
    if (paramDouble == 0.0D) {
      return 0;
    }
    return (int)((0x7FF0000000000000 & Double.doubleToLongBits(paramDouble)) >> 52) - 1022;
  }
  
  private static strictfp int expensiveCCW(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3)
  {
    if ((!paramS2Point1.equals(paramS2Point2)) && (!paramS2Point2.equals(paramS2Point3)) && (!paramS2Point3.equals(paramS2Point1)))
    {
      double d1 = paramS2Point1.dotProd(paramS2Point2);
      double d3 = 1.0D;
      if (d1 > 0.0D) {
        d1 = -1.0D;
      } else {
        d1 = 1.0D;
      }
      double d2;
      if (paramS2Point2.dotProd(paramS2Point3) > 0.0D) {
        d2 = -1.0D;
      } else {
        d2 = 1.0D;
      }
      if (paramS2Point3.dotProd(paramS2Point1) > 0.0D) {
        d3 = -1.0D;
      }
      S2Point localS2Point1 = S2Point.add(paramS2Point1, S2Point.mul(paramS2Point2, d1));
      S2Point localS2Point2 = S2Point.add(paramS2Point2, S2Point.mul(paramS2Point3, d2));
      S2Point localS2Point3 = S2Point.add(paramS2Point3, S2Point.mul(paramS2Point1, d3));
      double d4 = localS2Point1.norm2();
      double d5 = localS2Point2.norm2();
      double d6 = localS2Point3.norm2();
      if ((d6 >= d5) && ((d6 != d5) || (!paramS2Point1.lessThan(paramS2Point2))))
      {
        if ((d4 >= d6) && ((d4 != d6) || (!paramS2Point2.lessThan(paramS2Point3)))) {
          d1 = S2Point.crossProd(localS2Point3, localS2Point2).dotProd(paramS2Point3) * d3;
        } else {
          d1 = S2Point.crossProd(localS2Point2, localS2Point1).dotProd(paramS2Point2) * d2;
        }
      }
      else if ((d4 >= d5) && ((d4 != d5) || (!paramS2Point1.lessThan(paramS2Point3)))) {
        d1 = S2Point.crossProd(localS2Point3, localS2Point2).dotProd(paramS2Point3) * d3;
      } else {
        d1 = S2Point.crossProd(localS2Point1, localS2Point3).dotProd(paramS2Point1) * d1;
      }
      if (d1 > 0.0D) {
        return 1;
      }
      if (d1 < 0.0D) {
        return -1;
      }
      int j = planarOrderedCCW(new R2Vector(y, z), new R2Vector(y, z), new R2Vector(y, z));
      int i = j;
      if (j == 0)
      {
        j = planarOrderedCCW(new R2Vector(z, x), new R2Vector(z, x), new R2Vector(z, x));
        i = j;
        if (j == 0) {
          i = planarOrderedCCW(new R2Vector(x, y), new R2Vector(x, y), new R2Vector(x, y));
        }
      }
      return i;
    }
    return 0;
  }
  
  public static strictfp double girardArea(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3)
  {
    S2Point localS2Point = S2Point.crossProd(paramS2Point1, paramS2Point2);
    paramS2Point2 = S2Point.crossProd(paramS2Point2, paramS2Point3);
    paramS2Point1 = S2Point.crossProd(paramS2Point1, paramS2Point3);
    return Math.max(0.0D, localS2Point.angle(paramS2Point1) - localS2Point.angle(paramS2Point2) + paramS2Point2.angle(paramS2Point1));
  }
  
  public static final strictfp int ijToPos(int paramInt1, int paramInt2)
  {
    if ((paramInt1 >= 0) && (paramInt1 < 4))
    {
      if ((paramInt2 >= 0) && (paramInt2 < 4)) {
        return IJ_TO_POS[paramInt1][paramInt2];
      }
      throw new IllegalArgumentException(String.format("Index of ij should be in range [0,3] but got %d", new Object[] { Integer.valueOf(paramInt2) }));
    }
    throw new IllegalArgumentException(String.format("Orientation should be in range [0,3] but got %d", new Object[] { Integer.valueOf(paramInt1) }));
  }
  
  public static strictfp boolean isUnitLength(S2Point paramS2Point)
  {
    return Math.abs(paramS2Point.norm2() - 1.0D) <= 1.0E-15D;
  }
  
  public static strictfp boolean orderedCCW(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3, S2Point paramS2Point4)
  {
    int i = robustCCW(paramS2Point2, paramS2Point4, paramS2Point1);
    boolean bool = false;
    if (i >= 0) {
      j = 1;
    } else {
      j = 0;
    }
    i = j;
    if (robustCCW(paramS2Point3, paramS2Point4, paramS2Point2) >= 0) {
      i = j + 1;
    }
    int j = i;
    if (robustCCW(paramS2Point1, paramS2Point4, paramS2Point3) > 0) {
      j = i + 1;
    }
    if (j >= 2) {
      bool = true;
    }
    return bool;
  }
  
  public static strictfp S2Point origin()
  {
    return new S2Point(0.0D, 1.0D, 0.0D);
  }
  
  public static strictfp S2Point ortho(S2Point paramS2Point)
  {
    return paramS2Point.ortho();
  }
  
  public static strictfp int planarCCW(R2Vector paramR2Vector1, R2Vector paramR2Vector2)
  {
    double d1;
    if (paramR2Vector1.dotProd(paramR2Vector2) > 0.0D) {
      d1 = -1.0D;
    } else {
      d1 = 1.0D;
    }
    R2Vector localR2Vector = R2Vector.add(paramR2Vector1, R2Vector.mul(paramR2Vector2, d1));
    double d2 = paramR2Vector1.norm2();
    double d3 = paramR2Vector2.norm2();
    if ((d2 >= d3) && ((d2 != d3) || (!paramR2Vector1.lessThan(paramR2Vector2)))) {
      d1 = localR2Vector.crossProd(paramR2Vector2);
    } else {
      d1 = paramR2Vector1.crossProd(localR2Vector) * d1;
    }
    if (d1 > 0.0D) {
      return 1;
    }
    if (d1 < 0.0D) {
      return -1;
    }
    return 0;
  }
  
  public static strictfp S2Point planarCentroid(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3)
  {
    return new S2Point((x + x + x) / 3.0D, (y + y + y) / 3.0D, (z + z + z) / 3.0D);
  }
  
  public static strictfp int planarOrderedCCW(R2Vector paramR2Vector1, R2Vector paramR2Vector2, R2Vector paramR2Vector3)
  {
    int i = planarCCW(paramR2Vector1, paramR2Vector2) + 0 + planarCCW(paramR2Vector2, paramR2Vector3) + planarCCW(paramR2Vector3, paramR2Vector1);
    if (i > 0) {
      return 1;
    }
    if (i < 0) {
      return -1;
    }
    return 0;
  }
  
  public static strictfp int posToIJ(int paramInt1, int paramInt2)
  {
    if ((paramInt1 >= 0) && (paramInt1 < 4))
    {
      if ((paramInt2 >= 0) && (paramInt2 < 4)) {
        return POS_TO_IJ[paramInt1][paramInt2];
      }
      throw new IllegalArgumentException(String.format("Position should be in range [0,3] but got %d", new Object[] { Integer.valueOf(paramInt2) }));
    }
    throw new IllegalArgumentException(String.format("Orientation should be in range [0,3] but got %d", new Object[] { Integer.valueOf(paramInt1) }));
  }
  
  public static strictfp int posToOrientation(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < 4)) {
      return POS_TO_ORIENTATION[paramInt];
    }
    throw new IllegalArgumentException(String.format("Position should be in range [0,3] but got %d", new Object[] { Integer.valueOf(paramInt) }));
  }
  
  public static strictfp int robustCCW(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3)
  {
    return robustCCW(paramS2Point1, paramS2Point2, paramS2Point3, S2Point.crossProd(paramS2Point1, paramS2Point2));
  }
  
  public static strictfp int robustCCW(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3, S2Point paramS2Point4)
  {
    double d = paramS2Point4.dotProd(paramS2Point3);
    if (d > 1.6E-15D) {
      return 1;
    }
    if (d < -1.6E-15D) {
      return -1;
    }
    return expensiveCCW(paramS2Point1, paramS2Point2, paramS2Point3);
  }
  
  public static strictfp S2Point robustCrossProd(S2Point paramS2Point1, S2Point paramS2Point2)
  {
    paramS2Point2 = S2Point.crossProd(S2Point.add(paramS2Point2, paramS2Point1), S2Point.sub(paramS2Point2, paramS2Point1));
    if (!paramS2Point2.equals(new S2Point(0.0D, 0.0D, 0.0D))) {
      return paramS2Point2;
    }
    return ortho(paramS2Point1);
  }
  
  public static strictfp double signedArea(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3)
  {
    return area(paramS2Point1, paramS2Point2, paramS2Point3) * robustCCW(paramS2Point1, paramS2Point2, paramS2Point3);
  }
  
  public static strictfp boolean simpleCCW(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3)
  {
    return S2Point.crossProd(paramS2Point3, paramS2Point1).dotProd(paramS2Point2) > 0.0D;
  }
  
  public static strictfp boolean simpleCrossing(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3, S2Point paramS2Point4)
  {
    S2Point localS2Point1 = S2Point.crossProd(paramS2Point1, paramS2Point2);
    S2Point localS2Point2 = S2Point.crossProd(paramS2Point3, paramS2Point4);
    double d1 = -localS2Point1.dotProd(paramS2Point3);
    double d2 = -localS2Point2.dotProd(paramS2Point2);
    double d3 = localS2Point1.dotProd(paramS2Point4);
    double d4 = localS2Point2.dotProd(paramS2Point1);
    return (d1 * d2 > 0.0D) && (d2 * d3 > 0.0D) && (d3 * d4 > 0.0D);
  }
  
  public static strictfp S2Point trueCentroid(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3)
  {
    double d1 = S2Point.crossProd(paramS2Point2, paramS2Point3).norm();
    double d2 = S2Point.crossProd(paramS2Point3, paramS2Point1).norm();
    double d4 = S2Point.crossProd(paramS2Point1, paramS2Point2).norm();
    double d3 = 1.0D;
    if (d1 == 0.0D) {
      d1 = 1.0D;
    } else {
      d1 = Math.asin(d1) / d1;
    }
    if (d2 == 0.0D) {
      d2 = 1.0D;
    } else {
      d2 = Math.asin(d2) / d2;
    }
    if (d4 != 0.0D) {
      for (;;)
      {
        d3 = Math.asin(d4) / d4;
      }
    }
    S2Point localS2Point1 = new S2Point(x, x, x);
    S2Point localS2Point2 = new S2Point(y, y, y);
    paramS2Point1 = new S2Point(z, z, z);
    paramS2Point2 = new S2Point(d1, d2, d3);
    return new S2Point(0.5D * S2Point.crossProd(localS2Point2, paramS2Point1).dotProd(paramS2Point2), 0.5D * S2Point.crossProd(paramS2Point1, localS2Point1).dotProd(paramS2Point2), 0.5D * S2Point.crossProd(localS2Point1, localS2Point2).dotProd(paramS2Point2));
  }
  
  public static strictfp double turnAngle(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3)
  {
    double d = S2Point.crossProd(paramS2Point2, paramS2Point1).angle(S2Point.crossProd(paramS2Point3, paramS2Point2));
    if (robustCCW(paramS2Point1, paramS2Point2, paramS2Point3) > 0) {
      return d;
    }
    return -d;
  }
  
  public static class Metric
  {
    private final double deriv;
    private final int dim;
    
    public strictfp Metric(int paramInt, double paramDouble)
    {
      deriv = paramDouble;
      dim = paramInt;
    }
    
    public strictfp double deriv()
    {
      return deriv;
    }
    
    public strictfp int getClosestLevel(double paramDouble)
    {
      return getMinLevel(S2.M_SQRT2 * paramDouble);
    }
    
    public strictfp int getMaxLevel(double paramDouble)
    {
      if (paramDouble <= 0.0D) {
        return 30;
      }
      return Math.max(0, Math.min(30, S2.exp((1 << dim) * deriv / paramDouble) - 1 >> dim - 1));
    }
    
    public strictfp int getMinLevel(double paramDouble)
    {
      if (paramDouble <= 0.0D) {
        return 30;
      }
      return Math.max(0, Math.min(30, -(S2.exp(paramDouble / ((1 << dim) * deriv)) - 1 >> dim - 1)));
    }
    
    public strictfp double getValue(int paramInt)
    {
      return StrictMath.scalb(deriv, dim * (1 - paramInt));
    }
  }
}

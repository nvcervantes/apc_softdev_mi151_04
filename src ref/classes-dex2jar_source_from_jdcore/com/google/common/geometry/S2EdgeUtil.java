package com.google.common.geometry;

public class S2EdgeUtil
{
  public static final S1Angle DEFAULT_INTERSECTION_TOLERANCE = S1Angle.radians(1.5E-15D);
  
  private strictfp S2EdgeUtil() {}
  
  public static strictfp boolean edgeOrVertexCrossing(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3, S2Point paramS2Point4)
  {
    int i = robustCrossing(paramS2Point1, paramS2Point2, paramS2Point3, paramS2Point4);
    if (i < 0) {
      return false;
    }
    if (i > 0) {
      return true;
    }
    return vertexCrossing(paramS2Point1, paramS2Point2, paramS2Point3, paramS2Point4);
  }
  
  public static strictfp S2Point getClosestPoint(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3)
  {
    if ((S2.isUnitLength(paramS2Point1)) && (S2.isUnitLength(paramS2Point2)) && (S2.isUnitLength(paramS2Point3)))
    {
      S2Point localS2Point1 = S2.robustCrossProd(paramS2Point2, paramS2Point3);
      S2Point localS2Point2 = S2Point.minus(paramS2Point1, S2Point.mul(localS2Point1, paramS2Point1.dotProd(localS2Point1) / localS2Point1.norm2()));
      if ((S2.simpleCCW(localS2Point1, paramS2Point2, localS2Point2)) && (S2.simpleCCW(localS2Point2, paramS2Point3, localS2Point1))) {
        return S2Point.normalize(localS2Point2);
      }
      if (S2Point.minus(paramS2Point1, paramS2Point2).norm2() <= S2Point.minus(paramS2Point1, paramS2Point3).norm2()) {
        return paramS2Point2;
      }
      return paramS2Point3;
    }
    throw new IllegalArgumentException();
  }
  
  public static strictfp S1Angle getDistance(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3)
  {
    return getDistance(paramS2Point1, paramS2Point2, paramS2Point3, S2.robustCrossProd(paramS2Point2, paramS2Point3));
  }
  
  public static strictfp S1Angle getDistance(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3, S2Point paramS2Point4)
  {
    if ((S2.isUnitLength(paramS2Point1)) && (S2.isUnitLength(paramS2Point2)) && (S2.isUnitLength(paramS2Point3)))
    {
      if ((S2.simpleCCW(paramS2Point4, paramS2Point2, paramS2Point1)) && (S2.simpleCCW(paramS2Point1, paramS2Point3, paramS2Point4))) {
        return S1Angle.radians(Math.asin(Math.min(1.0D, Math.abs(paramS2Point1.dotProd(paramS2Point4)) / paramS2Point4.norm())));
      }
      return S1Angle.radians(2.0D * Math.asin(Math.min(1.0D, 0.5D * Math.sqrt(Math.min(S2Point.minus(paramS2Point1, paramS2Point2).norm2(), S2Point.minus(paramS2Point1, paramS2Point3).norm2())))));
    }
    throw new IllegalArgumentException();
  }
  
  public static strictfp double getDistanceFraction(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3)
  {
    if (paramS2Point2.equals(paramS2Point3)) {
      throw new IllegalArgumentException();
    }
    double d = paramS2Point1.angle(paramS2Point2);
    return d / (paramS2Point1.angle(paramS2Point3) + d);
  }
  
  public static strictfp S2Point getIntersection(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3, S2Point paramS2Point4)
  {
    if (robustCrossing(paramS2Point1, paramS2Point2, paramS2Point3, paramS2Point4) <= 0) {
      throw new IllegalArgumentException("Input edges a0a1 and b0b1 muct have a true robustCrossing.");
    }
    S2Point localS2Point1 = S2Point.normalize(S2.robustCrossProd(paramS2Point1, paramS2Point2));
    S2Point localS2Point2 = S2Point.normalize(S2.robustCrossProd(paramS2Point3, paramS2Point4));
    Object localObject2 = S2Point.normalize(S2.robustCrossProd(localS2Point1, localS2Point2));
    Object localObject1 = localObject2;
    if (((S2Point)localObject2).dotProd(S2Point.add(S2Point.add(paramS2Point1, paramS2Point2), S2Point.add(paramS2Point3, paramS2Point4))) < 0.0D) {
      localObject1 = S2Point.neg((S2Point)localObject2);
    }
    if ((S2.orderedCCW(paramS2Point1, (S2Point)localObject1, paramS2Point2, localS2Point1)) && (S2.orderedCCW(paramS2Point3, (S2Point)localObject1, paramS2Point4, localS2Point2))) {
      return localObject1;
    }
    localObject2 = new CloserResult(10.0D, (S2Point)localObject1);
    if (S2.orderedCCW(paramS2Point3, paramS2Point1, paramS2Point4, localS2Point2)) {
      ((CloserResult)localObject2).replaceIfCloser((S2Point)localObject1, paramS2Point1);
    }
    if (S2.orderedCCW(paramS2Point3, paramS2Point2, paramS2Point4, localS2Point2)) {
      ((CloserResult)localObject2).replaceIfCloser((S2Point)localObject1, paramS2Point2);
    }
    if (S2.orderedCCW(paramS2Point1, paramS2Point3, paramS2Point2, localS2Point1)) {
      ((CloserResult)localObject2).replaceIfCloser((S2Point)localObject1, paramS2Point3);
    }
    if (S2.orderedCCW(paramS2Point1, paramS2Point4, paramS2Point2, localS2Point1)) {
      ((CloserResult)localObject2).replaceIfCloser((S2Point)localObject1, paramS2Point4);
    }
    return ((CloserResult)localObject2).getVmin();
  }
  
  public static strictfp int robustCrossing(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3, S2Point paramS2Point4)
  {
    S2Point localS2Point = S2Point.crossProd(paramS2Point1, paramS2Point2);
    int j = -S2.robustCCW(paramS2Point1, paramS2Point2, paramS2Point3, localS2Point);
    int k = S2.robustCCW(paramS2Point1, paramS2Point2, paramS2Point4, localS2Point);
    if ((k & j) == 0) {
      return 0;
    }
    int i = -1;
    if (k != j) {
      return -1;
    }
    localS2Point = S2Point.crossProd(paramS2Point3, paramS2Point4);
    if (-S2.robustCCW(paramS2Point3, paramS2Point4, paramS2Point2, localS2Point) != j) {
      return -1;
    }
    if (S2.robustCCW(paramS2Point3, paramS2Point4, paramS2Point1, localS2Point) == j) {
      i = 1;
    }
    return i;
  }
  
  public static strictfp boolean simpleCrossing(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3, S2Point paramS2Point4)
  {
    S2Point localS2Point = S2Point.crossProd(paramS2Point1, paramS2Point2);
    double d1 = -localS2Point.dotProd(paramS2Point3);
    double d2 = localS2Point.dotProd(paramS2Point4);
    boolean bool2 = false;
    if (d2 * d1 <= 0.0D) {
      return false;
    }
    paramS2Point3 = S2Point.crossProd(paramS2Point3, paramS2Point4);
    d2 = -paramS2Point3.dotProd(paramS2Point2);
    double d3 = paramS2Point3.dotProd(paramS2Point1);
    boolean bool1 = bool2;
    if (d2 * d1 > 0.0D)
    {
      bool1 = bool2;
      if (d1 * d3 > 0.0D) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static strictfp boolean vertexCrossing(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3, S2Point paramS2Point4)
  {
    if (!paramS2Point1.equals(paramS2Point2))
    {
      if (paramS2Point3.equals(paramS2Point4)) {
        return false;
      }
      if (paramS2Point1.equals(paramS2Point4)) {
        return S2.orderedCCW(S2.ortho(paramS2Point1), paramS2Point3, paramS2Point2, paramS2Point1);
      }
      if (paramS2Point2.equals(paramS2Point3)) {
        return S2.orderedCCW(S2.ortho(paramS2Point2), paramS2Point4, paramS2Point1, paramS2Point2);
      }
      if (paramS2Point1.equals(paramS2Point3)) {
        return S2.orderedCCW(S2.ortho(paramS2Point1), paramS2Point4, paramS2Point2, paramS2Point1);
      }
      if (paramS2Point2.equals(paramS2Point4)) {
        return S2.orderedCCW(S2.ortho(paramS2Point2), paramS2Point3, paramS2Point1, paramS2Point2);
      }
      return false;
    }
    return false;
  }
  
  static class CloserResult
  {
    private double dmin2;
    private S2Point vmin;
    
    public strictfp CloserResult(double paramDouble, S2Point paramS2Point)
    {
      dmin2 = paramDouble;
      vmin = paramS2Point;
    }
    
    public strictfp double getDmin2()
    {
      return dmin2;
    }
    
    public strictfp S2Point getVmin()
    {
      return vmin;
    }
    
    public strictfp void replaceIfCloser(S2Point paramS2Point1, S2Point paramS2Point2)
    {
      double d = S2Point.minus(paramS2Point1, paramS2Point2).norm2();
      if ((d < dmin2) || ((d == dmin2) && (paramS2Point2.lessThan(vmin))))
      {
        dmin2 = d;
        vmin = paramS2Point2;
      }
    }
  }
  
  public static class EdgeCrosser
  {
    private final S2Point a;
    private final S2Point aCrossB;
    private int acb;
    private final S2Point b;
    private S2Point c;
    
    public strictfp EdgeCrosser(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3)
    {
      a = paramS2Point1;
      b = paramS2Point2;
      aCrossB = S2Point.crossProd(paramS2Point1, paramS2Point2);
      restartAt(paramS2Point3);
    }
    
    private strictfp int robustCrossingInternal(S2Point paramS2Point)
    {
      S2Point localS2Point = S2Point.crossProd(c, paramS2Point);
      int j = -S2.robustCCW(c, paramS2Point, b, localS2Point);
      int k = acb;
      int i = -1;
      if (j != k) {
        return -1;
      }
      if (S2.robustCCW(c, paramS2Point, a, localS2Point) == acb) {
        i = 1;
      }
      return i;
    }
    
    public strictfp boolean edgeOrVertexCrossing(S2Point paramS2Point)
    {
      S2Point localS2Point = new S2Point(c.get(0), c.get(1), c.get(2));
      int i = robustCrossing(paramS2Point);
      if (i < 0) {
        return false;
      }
      if (i > 0) {
        return true;
      }
      return S2EdgeUtil.vertexCrossing(a, b, localS2Point, paramS2Point);
    }
    
    public strictfp void restartAt(S2Point paramS2Point)
    {
      c = paramS2Point;
      acb = (-S2.robustCCW(a, b, paramS2Point, aCrossB));
    }
    
    public strictfp int robustCrossing(S2Point paramS2Point)
    {
      int j = S2.robustCCW(a, b, paramS2Point, aCrossB);
      int i;
      if ((j == -acb) && (j != 0)) {
        i = -1;
      } else if ((acb & j) == 0) {
        i = 0;
      } else {
        i = robustCrossingInternal(paramS2Point);
      }
      c = paramS2Point;
      acb = (-j);
      return i;
    }
  }
  
  public static class LongitudePruner
  {
    private S1Interval interval;
    private double lng0;
    
    public strictfp LongitudePruner(S1Interval paramS1Interval, S2Point paramS2Point)
    {
      interval = paramS1Interval;
      lng0 = S2LatLng.longitude(paramS2Point).radians();
    }
    
    public strictfp boolean intersects(S2Point paramS2Point)
    {
      double d = S2LatLng.longitude(paramS2Point).radians();
      boolean bool = interval.intersects(S1Interval.fromPointPair(lng0, d));
      lng0 = d;
      return bool;
    }
  }
  
  public static class RectBounder
  {
    private S2Point a;
    private S2LatLng aLatLng;
    private S2LatLngRect bound = S2LatLngRect.empty();
    
    public strictfp RectBounder() {}
    
    public strictfp void addPoint(S2Point paramS2Point)
    {
      S2LatLng localS2LatLng = new S2LatLng(paramS2Point);
      if (bound.isEmpty())
      {
        bound = bound.addPoint(localS2LatLng);
      }
      else
      {
        bound = bound.union(S2LatLngRect.fromPointPair(aLatLng, localS2LatLng));
        Object localObject = S2.robustCrossProd(a, paramS2Point);
        S2Point localS2Point = S2Point.crossProd((S2Point)localObject, new S2Point(0.0D, 0.0D, 1.0D));
        double d1 = localS2Point.dotProd(a);
        if (localS2Point.dotProd(paramS2Point) * d1 < 0.0D)
        {
          double d2 = Math.acos(Math.abs(((S2Point)localObject).get(2) / ((S2Point)localObject).norm()));
          localObject = bound.lat();
          if (d1 < 0.0D) {
            localObject = new R1Interval(((R1Interval)localObject).lo(), Math.max(d2, bound.lat().hi()));
          } else {
            localObject = new R1Interval(Math.min(-d2, bound.lat().lo()), ((R1Interval)localObject).hi());
          }
          bound = new S2LatLngRect((R1Interval)localObject, bound.lng());
        }
      }
      a = paramS2Point;
      aLatLng = localS2LatLng;
    }
    
    public strictfp S2LatLngRect getBound()
    {
      return bound;
    }
  }
  
  public static class WedgeContains
    implements S2EdgeUtil.WedgeRelation
  {
    public strictfp WedgeContains() {}
    
    public strictfp int test(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3, S2Point paramS2Point4, S2Point paramS2Point5)
    {
      if ((S2.orderedCCW(paramS2Point3, paramS2Point5, paramS2Point4, paramS2Point2)) && (S2.orderedCCW(paramS2Point4, paramS2Point1, paramS2Point3, paramS2Point2))) {
        return 1;
      }
      return 0;
    }
  }
  
  public static class WedgeContainsOrCrosses
    implements S2EdgeUtil.WedgeRelation
  {
    public strictfp WedgeContainsOrCrosses() {}
    
    public strictfp int test(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3, S2Point paramS2Point4, S2Point paramS2Point5)
    {
      boolean bool = S2.orderedCCW(paramS2Point1, paramS2Point3, paramS2Point5, paramS2Point2);
      int i = -1;
      if (bool)
      {
        if (S2.orderedCCW(paramS2Point5, paramS2Point4, paramS2Point1, paramS2Point2)) {
          return 1;
        }
        if (paramS2Point3.equals(paramS2Point5)) {
          i = 0;
        }
        return i;
      }
      if (S2.orderedCCW(paramS2Point1, paramS2Point4, paramS2Point3, paramS2Point2)) {
        i = 0;
      }
      return i;
    }
  }
  
  public static class WedgeContainsOrIntersects
    implements S2EdgeUtil.WedgeRelation
  {
    public strictfp WedgeContainsOrIntersects() {}
    
    public strictfp int test(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3, S2Point paramS2Point4, S2Point paramS2Point5)
    {
      boolean bool = S2.orderedCCW(paramS2Point1, paramS2Point3, paramS2Point5, paramS2Point2);
      int i = -1;
      if (bool)
      {
        if (S2.orderedCCW(paramS2Point5, paramS2Point4, paramS2Point1, paramS2Point2)) {
          i = 1;
        }
        return i;
      }
      if (!S2.orderedCCW(paramS2Point3, paramS2Point4, paramS2Point5, paramS2Point2)) {
        return 0;
      }
      if (paramS2Point3.equals(paramS2Point4)) {
        return 0;
      }
      return -1;
    }
  }
  
  public static class WedgeIntersects
    implements S2EdgeUtil.WedgeRelation
  {
    public strictfp WedgeIntersects() {}
    
    public strictfp int test(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3, S2Point paramS2Point4, S2Point paramS2Point5)
    {
      if ((S2.orderedCCW(paramS2Point1, paramS2Point5, paramS2Point4, paramS2Point2)) && (S2.orderedCCW(paramS2Point4, paramS2Point3, paramS2Point1, paramS2Point2))) {
        return 0;
      }
      return -1;
    }
  }
  
  public static abstract interface WedgeRelation
  {
    public abstract int test(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3, S2Point paramS2Point4, S2Point paramS2Point5);
  }
  
  public static class XYZPruner
  {
    private boolean boundSet = false;
    private S2Point lastVertex;
    private double maxDeformation;
    private double xmax;
    private double xmin;
    private double ymax;
    private double ymin;
    private double zmax;
    private double zmin;
    
    public strictfp XYZPruner() {}
    
    public strictfp void addEdgeToBounds(S2Point paramS2Point1, S2Point paramS2Point2)
    {
      if (!boundSet)
      {
        boundSet = true;
        d = x;
        xmax = d;
        xmin = d;
        d = y;
        ymax = d;
        ymin = d;
        d = z;
        zmax = d;
        zmin = d;
      }
      xmin = Math.min(xmin, Math.min(x, x));
      ymin = Math.min(ymin, Math.min(y, y));
      zmin = Math.min(zmin, Math.min(z, z));
      xmax = Math.max(xmax, Math.max(x, x));
      ymax = Math.max(ymax, Math.max(y, y));
      zmax = Math.max(zmax, Math.max(z, z));
      double d = Math.abs(x - x) + Math.abs(y - y) + Math.abs(z - z);
      if (d < 0.025D)
      {
        maxDeformation = Math.max(maxDeformation, d * 0.0025D);
        return;
      }
      if (d < 1.0D)
      {
        maxDeformation = Math.max(maxDeformation, d * 0.11D);
        return;
      }
      maxDeformation = (d * 0.5D);
    }
    
    public strictfp boolean intersects(S2Point paramS2Point)
    {
      double d1 = x;
      double d2 = xmin;
      boolean bool2 = false;
      boolean bool1;
      if (d1 < d2)
      {
        bool1 = bool2;
        if (lastVertex.x < xmin) {}
      }
      else if ((x > xmax) && (lastVertex.x > xmax))
      {
        bool1 = bool2;
      }
      else if (y < ymin)
      {
        bool1 = bool2;
        if (lastVertex.y < ymin) {}
      }
      else if ((y > ymax) && (lastVertex.y > ymax))
      {
        bool1 = bool2;
      }
      else if (z < zmin)
      {
        bool1 = bool2;
        if (lastVertex.z < zmin) {}
      }
      else if ((z > zmax) && (lastVertex.z > zmax))
      {
        bool1 = bool2;
      }
      else
      {
        bool1 = true;
      }
      lastVertex = paramS2Point;
      return bool1;
    }
    
    public strictfp void setFirstIntersectPoint(S2Point paramS2Point)
    {
      xmin -= maxDeformation;
      ymin -= maxDeformation;
      zmin -= maxDeformation;
      xmax += maxDeformation;
      ymax += maxDeformation;
      zmax += maxDeformation;
      lastVertex = paramS2Point;
    }
  }
}

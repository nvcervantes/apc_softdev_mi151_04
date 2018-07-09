package com.google.common.geometry;

public final class R1Interval
{
  private final double hi;
  private final double lo;
  
  public strictfp R1Interval(double paramDouble1, double paramDouble2)
  {
    lo = paramDouble1;
    hi = paramDouble2;
  }
  
  public static strictfp R1Interval empty()
  {
    return new R1Interval(1.0D, 0.0D);
  }
  
  public static strictfp R1Interval fromPoint(double paramDouble)
  {
    return new R1Interval(paramDouble, paramDouble);
  }
  
  public static strictfp R1Interval fromPointPair(double paramDouble1, double paramDouble2)
  {
    if (paramDouble1 <= paramDouble2) {
      return new R1Interval(paramDouble1, paramDouble2);
    }
    return new R1Interval(paramDouble2, paramDouble1);
  }
  
  public strictfp R1Interval addPoint(double paramDouble)
  {
    if (isEmpty()) {
      return fromPoint(paramDouble);
    }
    if (paramDouble < lo()) {
      return new R1Interval(paramDouble, hi());
    }
    if (paramDouble > hi()) {
      return new R1Interval(lo(), paramDouble);
    }
    return new R1Interval(lo(), hi());
  }
  
  public strictfp boolean approxEquals(R1Interval paramR1Interval)
  {
    return approxEquals(paramR1Interval, 1.0E-15D);
  }
  
  public strictfp boolean approxEquals(R1Interval paramR1Interval, double paramDouble)
  {
    boolean bool4 = isEmpty();
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool1 = false;
    if (bool4)
    {
      if (paramR1Interval.getLength() <= paramDouble) {
        bool1 = true;
      }
      return bool1;
    }
    if (paramR1Interval.isEmpty())
    {
      bool1 = bool2;
      if (getLength() <= paramDouble) {
        bool1 = true;
      }
      return bool1;
    }
    bool1 = bool3;
    if (Math.abs(paramR1Interval.lo() - lo()) + Math.abs(paramR1Interval.hi() - hi()) <= paramDouble) {
      bool1 = true;
    }
    return bool1;
  }
  
  public strictfp boolean contains(double paramDouble)
  {
    return (paramDouble >= lo()) && (paramDouble <= hi());
  }
  
  public strictfp boolean contains(R1Interval paramR1Interval)
  {
    if (paramR1Interval.isEmpty()) {
      return true;
    }
    return (paramR1Interval.lo() >= lo()) && (paramR1Interval.hi() <= hi());
  }
  
  public strictfp boolean equals(Object paramObject)
  {
    boolean bool1 = paramObject instanceof R1Interval;
    boolean bool2 = false;
    if (bool1)
    {
      paramObject = (R1Interval)paramObject;
      if ((lo() != paramObject.lo()) || (hi() != paramObject.hi()))
      {
        bool1 = bool2;
        if (isEmpty())
        {
          bool1 = bool2;
          if (!paramObject.isEmpty()) {}
        }
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    return false;
  }
  
  public strictfp R1Interval expanded(double paramDouble)
  {
    if (isEmpty()) {
      return this;
    }
    return new R1Interval(lo() - paramDouble, hi() + paramDouble);
  }
  
  public strictfp double getCenter()
  {
    return 0.5D * (lo() + hi());
  }
  
  public strictfp double getLength()
  {
    return hi() - lo();
  }
  
  public strictfp int hashCode()
  {
    if (isEmpty()) {
      return 17;
    }
    long l = 37L * (629L + Double.doubleToLongBits(lo)) + Double.doubleToLongBits(hi);
    return (int)(l ^ l >>> 32);
  }
  
  public strictfp double hi()
  {
    return hi;
  }
  
  public strictfp boolean interiorContains(double paramDouble)
  {
    return (paramDouble > lo()) && (paramDouble < hi());
  }
  
  public strictfp boolean interiorContains(R1Interval paramR1Interval)
  {
    if (paramR1Interval.isEmpty()) {
      return true;
    }
    return (paramR1Interval.lo() > lo()) && (paramR1Interval.hi() < hi());
  }
  
  public strictfp boolean interiorIntersects(R1Interval paramR1Interval)
  {
    return (paramR1Interval.lo() < hi()) && (lo() < paramR1Interval.hi()) && (lo() < hi()) && (paramR1Interval.lo() <= paramR1Interval.hi());
  }
  
  public strictfp R1Interval intersection(R1Interval paramR1Interval)
  {
    return new R1Interval(Math.max(lo(), paramR1Interval.lo()), Math.min(hi(), paramR1Interval.hi()));
  }
  
  public strictfp boolean intersects(R1Interval paramR1Interval)
  {
    double d1 = lo();
    double d2 = paramR1Interval.lo();
    boolean bool3 = false;
    boolean bool2 = false;
    if (d1 <= d2)
    {
      bool1 = bool2;
      if (paramR1Interval.lo() <= hi())
      {
        bool1 = bool2;
        if (paramR1Interval.lo() <= paramR1Interval.hi()) {
          bool1 = true;
        }
      }
      return bool1;
    }
    boolean bool1 = bool3;
    if (lo() <= paramR1Interval.hi())
    {
      bool1 = bool3;
      if (lo() <= hi()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public strictfp boolean isEmpty()
  {
    return lo() > hi();
  }
  
  public strictfp double lo()
  {
    return lo;
  }
  
  public strictfp String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[");
    localStringBuilder.append(lo());
    localStringBuilder.append(", ");
    localStringBuilder.append(hi());
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public strictfp R1Interval union(R1Interval paramR1Interval)
  {
    if (isEmpty()) {
      return paramR1Interval;
    }
    if (paramR1Interval.isEmpty()) {
      return this;
    }
    return new R1Interval(Math.min(lo(), paramR1Interval.lo()), Math.max(hi(), paramR1Interval.hi()));
  }
}

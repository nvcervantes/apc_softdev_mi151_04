package com.google.common.geometry;

public final class S1Interval
  implements Cloneable
{
  private final double hi;
  private final double lo;
  
  public strictfp S1Interval(double paramDouble1, double paramDouble2)
  {
    this(paramDouble1, paramDouble2, false);
  }
  
  private strictfp S1Interval(double paramDouble1, double paramDouble2, boolean paramBoolean)
  {
    double d2;
    double d3;
    if (!paramBoolean)
    {
      double d1;
      if ((paramDouble1 == -3.141592653589793D) && (paramDouble2 != 3.141592653589793D)) {
        d1 = 3.141592653589793D;
      } else {
        d1 = paramDouble1;
      }
      d2 = d1;
      d3 = paramDouble2;
      if (paramDouble2 == -3.141592653589793D)
      {
        d2 = d1;
        d3 = paramDouble2;
        if (paramDouble1 != 3.141592653589793D)
        {
          d3 = 3.141592653589793D;
          d2 = d1;
        }
      }
    }
    else
    {
      d3 = paramDouble2;
      d2 = paramDouble1;
    }
    lo = d2;
    hi = d3;
  }
  
  public strictfp S1Interval(S1Interval paramS1Interval)
  {
    lo = lo;
    hi = hi;
  }
  
  public static strictfp S1Interval empty()
  {
    return new S1Interval(3.141592653589793D, -3.141592653589793D, true);
  }
  
  public static strictfp S1Interval fromPoint(double paramDouble)
  {
    double d = paramDouble;
    if (paramDouble == -3.141592653589793D) {
      d = 3.141592653589793D;
    }
    return new S1Interval(d, d, true);
  }
  
  public static strictfp S1Interval fromPointPair(double paramDouble1, double paramDouble2)
  {
    if (paramDouble1 == -3.141592653589793D) {
      paramDouble1 = 3.141592653589793D;
    }
    double d = paramDouble2;
    if (paramDouble2 == -3.141592653589793D) {
      d = 3.141592653589793D;
    }
    if (positiveDistance(paramDouble1, d) <= 3.141592653589793D) {
      return new S1Interval(paramDouble1, d, true);
    }
    return new S1Interval(d, paramDouble1, true);
  }
  
  public static strictfp S1Interval full()
  {
    return new S1Interval(-3.141592653589793D, 3.141592653589793D, true);
  }
  
  public static strictfp double positiveDistance(double paramDouble1, double paramDouble2)
  {
    double d = paramDouble2 - paramDouble1;
    if (d >= 0.0D) {
      return d;
    }
    return paramDouble2 + 3.141592653589793D - (paramDouble1 - 3.141592653589793D);
  }
  
  public strictfp S1Interval addPoint(double paramDouble)
  {
    double d = paramDouble;
    if (paramDouble == -3.141592653589793D) {
      d = 3.141592653589793D;
    }
    if (fastContains(d)) {
      return new S1Interval(this);
    }
    if (isEmpty()) {
      return fromPoint(d);
    }
    if (positiveDistance(d, lo()) < positiveDistance(hi(), d)) {
      return new S1Interval(d, hi());
    }
    return new S1Interval(lo(), d);
  }
  
  public strictfp boolean approxEquals(S1Interval paramS1Interval)
  {
    return approxEquals(paramS1Interval, 1.0E-9D);
  }
  
  public strictfp boolean approxEquals(S1Interval paramS1Interval, double paramDouble)
  {
    boolean bool4 = isEmpty();
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool1 = false;
    if (bool4)
    {
      if (paramS1Interval.getLength() <= paramDouble) {
        bool1 = true;
      }
      return bool1;
    }
    if (paramS1Interval.isEmpty())
    {
      bool1 = bool2;
      if (getLength() <= paramDouble) {
        bool1 = true;
      }
      return bool1;
    }
    bool1 = bool3;
    if (Math.abs(Math.IEEEremainder(paramS1Interval.lo() - lo(), 6.283185307179586D)) + Math.abs(Math.IEEEremainder(paramS1Interval.hi() - hi(), 6.283185307179586D)) <= paramDouble) {
      bool1 = true;
    }
    return bool1;
  }
  
  public strictfp S1Interval complement()
  {
    if (lo() == hi()) {
      return full();
    }
    return new S1Interval(hi(), lo(), true);
  }
  
  public strictfp boolean contains(double paramDouble)
  {
    double d = paramDouble;
    if (paramDouble == -3.141592653589793D) {
      d = 3.141592653589793D;
    }
    return fastContains(d);
  }
  
  public strictfp boolean contains(S1Interval paramS1Interval)
  {
    boolean bool5 = isInverted();
    boolean bool3 = false;
    boolean bool1 = false;
    boolean bool4 = false;
    boolean bool2 = false;
    if (bool5)
    {
      if (paramS1Interval.isInverted())
      {
        bool1 = bool2;
        if (paramS1Interval.lo() >= lo())
        {
          bool1 = bool2;
          if (paramS1Interval.hi() <= hi()) {
            bool1 = true;
          }
        }
        return bool1;
      }
      if (paramS1Interval.lo() < lo())
      {
        bool1 = bool3;
        if (paramS1Interval.hi() > hi()) {}
      }
      else
      {
        bool1 = bool3;
        if (!isEmpty()) {
          bool1 = true;
        }
      }
      return bool1;
    }
    if (paramS1Interval.isInverted())
    {
      if ((isFull()) || (paramS1Interval.isEmpty())) {
        bool1 = true;
      }
      return bool1;
    }
    bool1 = bool4;
    if (paramS1Interval.lo() >= lo())
    {
      bool1 = bool4;
      if (paramS1Interval.hi() <= hi()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public strictfp boolean equals(Object paramObject)
  {
    boolean bool1 = paramObject instanceof S1Interval;
    boolean bool2 = false;
    if (bool1)
    {
      paramObject = (S1Interval)paramObject;
      bool1 = bool2;
      if (lo() == paramObject.lo())
      {
        bool1 = bool2;
        if (hi() == paramObject.hi()) {
          bool1 = true;
        }
      }
      return bool1;
    }
    return false;
  }
  
  public strictfp S1Interval expanded(double paramDouble)
  {
    if (isEmpty()) {
      return this;
    }
    if (getLength() + 2.0D * paramDouble >= 6.283185307179585D) {
      return full();
    }
    double d1 = Math.IEEEremainder(lo() - paramDouble, 6.283185307179586D);
    double d2 = Math.IEEEremainder(hi() + paramDouble, 6.283185307179586D);
    paramDouble = d1;
    if (d1 == -3.141592653589793D) {
      paramDouble = 3.141592653589793D;
    }
    return new S1Interval(paramDouble, d2);
  }
  
  public strictfp boolean fastContains(double paramDouble)
  {
    boolean bool1 = isInverted();
    boolean bool3 = false;
    boolean bool2 = false;
    if (bool1)
    {
      if (paramDouble < lo())
      {
        bool1 = bool2;
        if (paramDouble > hi()) {}
      }
      else
      {
        bool1 = bool2;
        if (!isEmpty()) {
          bool1 = true;
        }
      }
      return bool1;
    }
    bool1 = bool3;
    if (paramDouble >= lo())
    {
      bool1 = bool3;
      if (paramDouble <= hi()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public strictfp double getCenter()
  {
    double d = 0.5D * (lo() + hi());
    if (!isInverted()) {
      return d;
    }
    if (d <= 0.0D) {
      return d + 3.141592653589793D;
    }
    return d - 3.141592653589793D;
  }
  
  public strictfp double getLength()
  {
    double d = hi() - lo();
    if (d >= 0.0D) {
      return d;
    }
    d += 6.283185307179586D;
    if (d > 0.0D) {
      return d;
    }
    return -1.0D;
  }
  
  public strictfp int hashCode()
  {
    long l = 37L * (629L + Double.doubleToLongBits(lo())) + Double.doubleToLongBits(hi());
    return (int)(l >>> 32 ^ l);
  }
  
  public strictfp double hi()
  {
    return hi;
  }
  
  public strictfp boolean interiorContains(double paramDouble)
  {
    double d = paramDouble;
    if (paramDouble == -3.141592653589793D) {
      d = 3.141592653589793D;
    }
    boolean bool3 = isInverted();
    boolean bool2 = true;
    boolean bool1 = true;
    if (bool3)
    {
      if (d <= lo())
      {
        if (d < hi()) {
          return true;
        }
        bool1 = false;
      }
      return bool1;
    }
    if (d > lo())
    {
      bool1 = bool2;
      if (d < hi()) {}
    }
    else
    {
      if (isFull()) {
        return true;
      }
      bool1 = false;
    }
    return bool1;
  }
  
  public strictfp boolean interiorContains(S1Interval paramS1Interval)
  {
    boolean bool5 = isInverted();
    boolean bool2 = true;
    boolean bool3 = true;
    boolean bool4 = true;
    boolean bool1 = true;
    if (bool5)
    {
      if (!paramS1Interval.isInverted())
      {
        if (paramS1Interval.lo() <= lo())
        {
          if (paramS1Interval.hi() < hi()) {
            return true;
          }
          bool1 = false;
        }
        return bool1;
      }
      if (paramS1Interval.lo() > lo())
      {
        bool1 = bool2;
        if (paramS1Interval.hi() < hi()) {}
      }
      else
      {
        if (paramS1Interval.isEmpty()) {
          return true;
        }
        bool1 = false;
      }
      return bool1;
    }
    if (paramS1Interval.isInverted())
    {
      bool1 = bool3;
      if (!isFull())
      {
        if (paramS1Interval.isEmpty()) {
          return true;
        }
        bool1 = false;
      }
      return bool1;
    }
    if (paramS1Interval.lo() > lo())
    {
      bool1 = bool4;
      if (paramS1Interval.hi() < hi()) {}
    }
    else
    {
      if (isFull()) {
        return true;
      }
      bool1 = false;
    }
    return bool1;
  }
  
  public strictfp boolean interiorIntersects(S1Interval paramS1Interval)
  {
    boolean bool4 = isEmpty();
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool1 = false;
    if ((!bool4) && (!paramS1Interval.isEmpty()))
    {
      if (lo() == hi()) {
        return false;
      }
      if (isInverted())
      {
        if ((paramS1Interval.isInverted()) || (paramS1Interval.lo() < hi()) || (paramS1Interval.hi() > lo())) {
          bool1 = true;
        }
        return bool1;
      }
      if (paramS1Interval.isInverted())
      {
        if (paramS1Interval.lo() >= hi())
        {
          bool1 = bool2;
          if (paramS1Interval.hi() <= lo()) {}
        }
        else
        {
          bool1 = true;
        }
        return bool1;
      }
      if ((paramS1Interval.lo() >= hi()) || (paramS1Interval.hi() <= lo()))
      {
        bool1 = bool3;
        if (!isFull()) {}
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    return false;
  }
  
  public strictfp S1Interval intersection(S1Interval paramS1Interval)
  {
    if (paramS1Interval.isEmpty()) {
      return empty();
    }
    if (fastContains(paramS1Interval.lo()))
    {
      if (fastContains(paramS1Interval.hi()))
      {
        if (paramS1Interval.getLength() < getLength()) {
          return paramS1Interval;
        }
        return this;
      }
      return new S1Interval(paramS1Interval.lo(), hi(), true);
    }
    if (fastContains(paramS1Interval.hi())) {
      return new S1Interval(lo(), paramS1Interval.hi(), true);
    }
    if (paramS1Interval.fastContains(lo())) {
      return this;
    }
    return empty();
  }
  
  public strictfp boolean intersects(S1Interval paramS1Interval)
  {
    boolean bool4 = isEmpty();
    boolean bool3 = false;
    boolean bool2 = false;
    boolean bool1 = false;
    if (!bool4)
    {
      if (paramS1Interval.isEmpty()) {
        return false;
      }
      if (isInverted())
      {
        if ((paramS1Interval.isInverted()) || (paramS1Interval.lo() <= hi()) || (paramS1Interval.hi() >= lo())) {
          bool1 = true;
        }
        return bool1;
      }
      if (paramS1Interval.isInverted())
      {
        if (paramS1Interval.lo() > hi())
        {
          bool1 = bool3;
          if (paramS1Interval.hi() < lo()) {}
        }
        else
        {
          bool1 = true;
        }
        return bool1;
      }
      bool1 = bool2;
      if (paramS1Interval.lo() <= hi())
      {
        bool1 = bool2;
        if (paramS1Interval.hi() >= lo()) {
          bool1 = true;
        }
      }
      return bool1;
    }
    return false;
  }
  
  public strictfp boolean isEmpty()
  {
    return lo() - hi() == 6.283185307179586D;
  }
  
  public strictfp boolean isFull()
  {
    return hi() - lo() == 6.283185307179586D;
  }
  
  public strictfp boolean isInverted()
  {
    return lo() > hi();
  }
  
  public strictfp boolean isValid()
  {
    return (Math.abs(lo()) <= 3.141592653589793D) && (Math.abs(hi()) <= 3.141592653589793D) && ((lo() != -3.141592653589793D) || (hi() == 3.141592653589793D)) && ((hi() != -3.141592653589793D) || (lo() == 3.141592653589793D));
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
  
  public strictfp S1Interval union(S1Interval paramS1Interval)
  {
    if (paramS1Interval.isEmpty()) {
      return this;
    }
    if (fastContains(paramS1Interval.lo()))
    {
      if (fastContains(paramS1Interval.hi()))
      {
        if (contains(paramS1Interval)) {
          return this;
        }
        return full();
      }
      return new S1Interval(lo(), paramS1Interval.hi(), true);
    }
    if (fastContains(paramS1Interval.hi())) {
      return new S1Interval(paramS1Interval.lo(), hi(), true);
    }
    if (!isEmpty())
    {
      if (paramS1Interval.fastContains(lo())) {
        return paramS1Interval;
      }
      if (positiveDistance(paramS1Interval.hi(), lo()) < positiveDistance(hi(), paramS1Interval.lo())) {
        return new S1Interval(paramS1Interval.lo(), hi(), true);
      }
      return new S1Interval(lo(), paramS1Interval.hi(), true);
    }
    return paramS1Interval;
  }
}

package com.google.common.geometry;

public class S2LatLngRect
  implements S2Region
{
  private final R1Interval lat;
  private final S1Interval lng;
  
  public strictfp S2LatLngRect(R1Interval paramR1Interval, S1Interval paramS1Interval)
  {
    lat = paramR1Interval;
    lng = paramS1Interval;
  }
  
  public strictfp S2LatLngRect(S2LatLng paramS2LatLng1, S2LatLng paramS2LatLng2)
  {
    lat = new R1Interval(paramS2LatLng1.lat().radians(), paramS2LatLng2.lat().radians());
    lng = new S1Interval(paramS2LatLng1.lng().radians(), paramS2LatLng2.lng().radians());
  }
  
  public static strictfp S2LatLngRect empty()
  {
    return new S2LatLngRect(R1Interval.empty(), S1Interval.empty());
  }
  
  public static strictfp S2LatLngRect fromCenterSize(S2LatLng paramS2LatLng1, S2LatLng paramS2LatLng2)
  {
    return fromPoint(paramS2LatLng1).expanded(paramS2LatLng2.mul(0.5D));
  }
  
  public static strictfp S2LatLngRect fromEdge(S2Point paramS2Point1, S2Point paramS2Point2)
  {
    S2LatLngRect localS2LatLngRect = fromPointPair(new S2LatLng(paramS2Point1), new S2LatLng(paramS2Point2));
    S2Point localS2Point1 = S2.robustCrossProd(paramS2Point1, paramS2Point2);
    S2Point localS2Point2 = S2Point.crossProd(localS2Point1, new S2Point(0.0D, 0.0D, 1.0D));
    double d1 = localS2Point2.dotProd(paramS2Point1);
    if (localS2Point2.dotProd(paramS2Point2) * d1 >= 0.0D) {
      return localS2LatLngRect;
    }
    double d2 = Math.acos(Math.abs(z / localS2Point1.norm()));
    if (d1 < 0.0D) {
      return new S2LatLngRect(new R1Interval(localS2LatLngRect.lat().lo(), d2), localS2LatLngRect.lng());
    }
    return new S2LatLngRect(new R1Interval(-d2, localS2LatLngRect.lat().hi()), localS2LatLngRect.lng());
  }
  
  public static strictfp S2LatLngRect fromPoint(S2LatLng paramS2LatLng)
  {
    return new S2LatLngRect(paramS2LatLng, paramS2LatLng);
  }
  
  public static strictfp S2LatLngRect fromPointPair(S2LatLng paramS2LatLng1, S2LatLng paramS2LatLng2)
  {
    return new S2LatLngRect(R1Interval.fromPointPair(paramS2LatLng1.lat().radians(), paramS2LatLng2.lat().radians()), S1Interval.fromPointPair(paramS2LatLng1.lng().radians(), paramS2LatLng2.lng().radians()));
  }
  
  public static strictfp S2LatLngRect full()
  {
    return new S2LatLngRect(fullLat(), fullLng());
  }
  
  public static strictfp R1Interval fullLat()
  {
    return new R1Interval(-1.5707963267948966D, 1.5707963267948966D);
  }
  
  public static strictfp S1Interval fullLng()
  {
    return S1Interval.full();
  }
  
  private static strictfp boolean intersectsLatEdge(S2Point paramS2Point1, S2Point paramS2Point2, double paramDouble, S1Interval paramS1Interval)
  {
    S2Point localS2Point2 = S2Point.normalize(S2.robustCrossProd(paramS2Point1, paramS2Point2));
    S2Point localS2Point1 = localS2Point2;
    if (z < 0.0D) {
      localS2Point1 = S2Point.neg(localS2Point2);
    }
    localS2Point2 = S2Point.normalize(S2.robustCrossProd(localS2Point1, new S2Point(0.0D, 0.0D, 1.0D)));
    localS2Point1 = S2Point.crossProd(localS2Point2, localS2Point1);
    paramDouble = Math.sin(paramDouble);
    if (Math.abs(paramDouble) >= z) {
      return false;
    }
    paramDouble /= z;
    double d1 = Math.sqrt(1.0D - paramDouble * paramDouble);
    double d2 = Math.atan2(d1, paramDouble);
    paramS2Point1 = S1Interval.fromPointPair(Math.atan2(paramS2Point1.dotProd(localS2Point2), paramS2Point1.dotProd(localS2Point1)), Math.atan2(paramS2Point2.dotProd(localS2Point2), paramS2Point2.dotProd(localS2Point1)));
    if (paramS2Point1.contains(d2))
    {
      paramS2Point2 = S2Point.add(S2Point.mul(localS2Point1, paramDouble), S2Point.mul(localS2Point2, d1));
      if (paramS1Interval.contains(Math.atan2(y, x))) {
        return true;
      }
    }
    if (paramS2Point1.contains(-d2))
    {
      paramS2Point1 = S2Point.sub(S2Point.mul(localS2Point1, paramDouble), S2Point.mul(localS2Point2, d1));
      if (paramS1Interval.contains(Math.atan2(y, x))) {
        return true;
      }
    }
    return false;
  }
  
  private static strictfp boolean intersectsLngEdge(S2Point paramS2Point1, S2Point paramS2Point2, R1Interval paramR1Interval, double paramDouble)
  {
    return S2.simpleCrossing(paramS2Point1, paramS2Point2, S2LatLng.fromRadians(paramR1Interval.lo(), paramDouble).toPoint(), S2LatLng.fromRadians(paramR1Interval.hi(), paramDouble).toPoint());
  }
  
  public strictfp S2LatLngRect addPoint(S2LatLng paramS2LatLng)
  {
    return new S2LatLngRect(lat.addPoint(paramS2LatLng.lat().radians()), lng.addPoint(paramS2LatLng.lng().radians()));
  }
  
  public strictfp S2LatLngRect addPoint(S2Point paramS2Point)
  {
    return addPoint(new S2LatLng(paramS2Point));
  }
  
  public strictfp boolean approxEquals(S2LatLngRect paramS2LatLngRect)
  {
    return approxEquals(paramS2LatLngRect, 1.0E-15D);
  }
  
  public strictfp boolean approxEquals(S2LatLngRect paramS2LatLngRect, double paramDouble)
  {
    return (lat.approxEquals(lat, paramDouble)) && (lng.approxEquals(lng, paramDouble));
  }
  
  public strictfp double area()
  {
    if (isEmpty()) {
      return 0.0D;
    }
    return lng().getLength() * Math.abs(Math.sin(latHi().radians()) - Math.sin(latLo().radians()));
  }
  
  public strictfp S2Region clone()
  {
    return new S2LatLngRect(lo(), hi());
  }
  
  public strictfp boolean contains(S2Cell paramS2Cell)
  {
    return contains(paramS2Cell.getRectBound());
  }
  
  public strictfp boolean contains(S2LatLng paramS2LatLng)
  {
    return (lat.contains(paramS2LatLng.lat().radians())) && (lng.contains(paramS2LatLng.lng().radians()));
  }
  
  public strictfp boolean contains(S2LatLngRect paramS2LatLngRect)
  {
    return (lat.contains(lat)) && (lng.contains(lng));
  }
  
  public strictfp boolean contains(S2Point paramS2Point)
  {
    return contains(new S2LatLng(paramS2Point));
  }
  
  public strictfp S2LatLngRect convolveWithCap(S1Angle paramS1Angle)
  {
    S2Cap localS2Cap = S2Cap.fromAxisAngle(new S2Point(1.0D, 0.0D, 0.0D), paramS1Angle);
    int i = 0;
    paramS1Angle = this;
    while (i < 4)
    {
      paramS1Angle = paramS1Angle.union(S2Cap.fromAxisHeight(getVertex(i).toPoint(), localS2Cap.height()).getRectBound());
      i += 1;
    }
    return paramS1Angle;
  }
  
  public strictfp boolean equals(Object paramObject)
  {
    boolean bool1 = paramObject instanceof S2LatLngRect;
    boolean bool2 = false;
    if (!bool1) {
      return false;
    }
    paramObject = (S2LatLngRect)paramObject;
    bool1 = bool2;
    if (lat().equals(paramObject.lat()))
    {
      bool1 = bool2;
      if (lng().equals(paramObject.lng())) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public strictfp S2LatLngRect expanded(S2LatLng paramS2LatLng)
  {
    if (isEmpty()) {
      return this;
    }
    return new S2LatLngRect(lat.expanded(paramS2LatLng.lat().radians()).intersection(fullLat()), lng.expanded(paramS2LatLng.lng().radians()));
  }
  
  public strictfp S2Cap getCapBound()
  {
    if (isEmpty()) {
      return S2Cap.empty();
    }
    double d2;
    if (lat.lo() + lat.hi() < 0.0D) {
      d2 = -1.0D;
    }
    for (double d1 = 1.5707963267948966D + lat.hi();; d1 = 1.5707963267948966D - lat.lo())
    {
      break;
      d2 = 1.0D;
    }
    S2Cap localS2Cap2 = S2Cap.fromAxisAngle(new S2Point(0.0D, 0.0D, d2), S1Angle.radians(d1));
    d1 = lng.hi() - lng.lo();
    if ((Math.IEEEremainder(d1, 6.283185307179586D) >= 0.0D) && (d1 < 6.283185307179586D))
    {
      S2Cap localS2Cap1 = S2Cap.fromAxisAngle(getCenter().toPoint(), S1Angle.radians(0.0D));
      int i = 0;
      while (i < 4)
      {
        localS2Cap1 = localS2Cap1.addPoint(getVertex(i).toPoint());
        i += 1;
      }
      if (localS2Cap1.height() < localS2Cap2.height()) {
        return localS2Cap1;
      }
    }
    return localS2Cap2;
  }
  
  public strictfp S2LatLng getCenter()
  {
    return S2LatLng.fromRadians(lat.getCenter(), lng.getCenter());
  }
  
  public strictfp S1Angle getDistance(S2LatLng paramS2LatLng)
  {
    if (isEmpty()) {
      throw new IllegalStateException();
    }
    if (!paramS2LatLng.isValid()) {
      throw new IllegalArgumentException();
    }
    if (lng().contains(paramS2LatLng.lng().radians())) {
      return S1Angle.radians(Math.max(0.0D, Math.max(paramS2LatLng.lat().radians() - lat().hi(), lat().lo() - paramS2LatLng.lat().radians())));
    }
    Object localObject = new S1Interval(lng().hi(), lng().complement().getCenter());
    double d = lng().lo();
    if (((S1Interval)localObject).contains(paramS2LatLng.lng().radians())) {
      d = lng().hi();
    }
    localObject = S2LatLng.fromRadians(lat().lo(), d).toPoint();
    S2Point localS2Point1 = S2LatLng.fromRadians(lat().hi(), d).toPoint();
    S2Point localS2Point2 = S2LatLng.fromRadians(0.0D, d - 1.5707963267948966D).normalized().toPoint();
    return S2EdgeUtil.getDistance(paramS2LatLng.toPoint(), (S2Point)localObject, localS2Point1, localS2Point2);
  }
  
  public strictfp S1Angle getDistance(S2LatLngRect paramS2LatLngRect)
  {
    if (isEmpty()) {
      throw new IllegalStateException();
    }
    if (paramS2LatLngRect.isEmpty()) {
      throw new IllegalArgumentException();
    }
    if (lng().intersects(paramS2LatLngRect.lng()))
    {
      if (lat().intersects(paramS2LatLngRect.lat())) {
        return S1Angle.radians(0.0D);
      }
      if (lat().lo() > paramS2LatLngRect.lat().hi())
      {
        paramS2LatLngRect = paramS2LatLngRect.latHi();
        localObject1 = latLo();
      }
      else
      {
        localObject2 = latHi();
        localObject1 = paramS2LatLngRect.latLo();
        paramS2LatLngRect = (S2LatLngRect)localObject2;
      }
      return S1Angle.radians(((S1Angle)localObject1).radians() - paramS2LatLngRect.radians());
    }
    Object localObject1 = S1Interval.fromPointPair(lng().lo(), paramS2LatLngRect.lng().hi());
    Object localObject2 = S1Interval.fromPointPair(lng().hi(), paramS2LatLngRect.lng().lo());
    if (((S1Interval)localObject1).getLength() < ((S1Interval)localObject2).getLength())
    {
      localObject2 = lngLo();
      localObject1 = paramS2LatLngRect.lngHi();
    }
    else
    {
      localObject2 = lngHi();
      localObject1 = paramS2LatLngRect.lngLo();
    }
    S2Point localS2Point1 = new S2LatLng(latLo(), (S1Angle)localObject2).toPoint();
    S2Point localS2Point2 = new S2LatLng(latHi(), (S1Angle)localObject2).toPoint();
    localObject2 = S2LatLng.fromRadians(0.0D, ((S1Angle)localObject2).radians() - 1.5707963267948966D).normalized().toPoint();
    S2Point localS2Point3 = new S2LatLng(paramS2LatLngRect.latLo(), (S1Angle)localObject1).toPoint();
    paramS2LatLngRect = new S2LatLng(paramS2LatLngRect.latHi(), (S1Angle)localObject1).toPoint();
    localObject1 = S2LatLng.fromRadians(0.0D, ((S1Angle)localObject1).radians() - 1.5707963267948966D).normalized().toPoint();
    return S1Angle.min(S2EdgeUtil.getDistance(localS2Point1, localS2Point3, paramS2LatLngRect, (S2Point)localObject1), S1Angle.min(S2EdgeUtil.getDistance(localS2Point2, localS2Point3, paramS2LatLngRect, (S2Point)localObject1), S1Angle.min(S2EdgeUtil.getDistance(localS2Point3, localS2Point1, localS2Point2, (S2Point)localObject2), S2EdgeUtil.getDistance(paramS2LatLngRect, localS2Point1, localS2Point2, (S2Point)localObject2))));
  }
  
  public strictfp S2LatLngRect getRectBound()
  {
    return this;
  }
  
  public strictfp S2LatLng getSize()
  {
    return S2LatLng.fromRadians(lat.getLength(), lng.getLength());
  }
  
  public strictfp S2LatLng getVertex(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("Invalid vertex index.");
    case 3: 
      return S2LatLng.fromRadians(lat.hi(), lng.lo());
    case 2: 
      return S2LatLng.fromRadians(lat.hi(), lng.hi());
    case 1: 
      return S2LatLng.fromRadians(lat.lo(), lng.hi());
    }
    return S2LatLng.fromRadians(lat.lo(), lng.lo());
  }
  
  public strictfp int hashCode()
  {
    return 37 * (629 + lat.hashCode()) + lng.hashCode();
  }
  
  public strictfp S2LatLng hi()
  {
    return new S2LatLng(latHi(), lngHi());
  }
  
  public strictfp boolean interiorContains(S2LatLng paramS2LatLng)
  {
    return (lat.interiorContains(paramS2LatLng.lat().radians())) && (lng.interiorContains(paramS2LatLng.lng().radians()));
  }
  
  public strictfp boolean interiorContains(S2LatLngRect paramS2LatLngRect)
  {
    return (lat.interiorContains(lat)) && (lng.interiorContains(lng));
  }
  
  public strictfp boolean interiorContains(S2Point paramS2Point)
  {
    return interiorContains(new S2LatLng(paramS2Point));
  }
  
  public strictfp boolean interiorIntersects(S2LatLngRect paramS2LatLngRect)
  {
    return (lat.interiorIntersects(lat)) && (lng.interiorIntersects(lng));
  }
  
  public strictfp S2LatLngRect intersection(S2LatLngRect paramS2LatLngRect)
  {
    R1Interval localR1Interval = lat.intersection(lat);
    paramS2LatLngRect = lng.intersection(lng);
    if ((!localR1Interval.isEmpty()) && (!paramS2LatLngRect.isEmpty())) {
      return new S2LatLngRect(localR1Interval, paramS2LatLngRect);
    }
    return empty();
  }
  
  public strictfp boolean intersects(S2Cell paramS2Cell)
  {
    if (isEmpty()) {
      return false;
    }
    if (contains(paramS2Cell.getCenter())) {
      return true;
    }
    if (paramS2Cell.contains(getCenter().toPoint())) {
      return true;
    }
    if (!intersects(paramS2Cell.getRectBound())) {
      return false;
    }
    S2Point[] arrayOfS2Point = new S2Point[4];
    S2LatLng[] arrayOfS2LatLng = new S2LatLng[4];
    int i = 0;
    while (i < 4)
    {
      arrayOfS2Point[i] = paramS2Cell.getVertex(i);
      arrayOfS2LatLng[i] = new S2LatLng(arrayOfS2Point[i]);
      if (contains(arrayOfS2LatLng[i])) {
        return true;
      }
      i += 1;
    }
    int j;
    for (i = 0; i < 4; i = j)
    {
      double d = arrayOfS2LatLng[i].lng().radians();
      j = i + 1;
      int k = j & 0x3;
      paramS2Cell = S1Interval.fromPointPair(d, arrayOfS2LatLng[k].lng().radians());
      if (lng.intersects(paramS2Cell))
      {
        S2Point localS2Point1 = arrayOfS2Point[i];
        S2Point localS2Point2 = arrayOfS2Point[k];
        if ((paramS2Cell.contains(lng.lo())) && (intersectsLngEdge(localS2Point1, localS2Point2, lat, lng.lo()))) {
          return true;
        }
        if ((paramS2Cell.contains(lng.hi())) && (intersectsLngEdge(localS2Point1, localS2Point2, lat, lng.hi()))) {
          return true;
        }
        if (intersectsLatEdge(localS2Point1, localS2Point2, lat.lo(), lng)) {
          return true;
        }
        if (intersectsLatEdge(localS2Point1, localS2Point2, lat.hi(), lng)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public strictfp boolean intersects(S2LatLngRect paramS2LatLngRect)
  {
    return (lat.intersects(lat)) && (lng.intersects(lng));
  }
  
  public strictfp boolean isEmpty()
  {
    return lat.isEmpty();
  }
  
  public strictfp boolean isFull()
  {
    return (lat.equals(fullLat())) && (lng.isFull());
  }
  
  public strictfp boolean isInverted()
  {
    return lng.isInverted();
  }
  
  public strictfp boolean isValid()
  {
    return (Math.abs(lat.lo()) <= 1.5707963267948966D) && (Math.abs(lat.hi()) <= 1.5707963267948966D) && (lng.isValid()) && (lat.isEmpty() == lng.isEmpty());
  }
  
  public strictfp R1Interval lat()
  {
    return lat;
  }
  
  public strictfp S1Angle latHi()
  {
    return S1Angle.radians(lat.hi());
  }
  
  public strictfp S1Angle latLo()
  {
    return S1Angle.radians(lat.lo());
  }
  
  public strictfp S1Interval lng()
  {
    return lng;
  }
  
  public strictfp S1Angle lngHi()
  {
    return S1Angle.radians(lng.hi());
  }
  
  public strictfp S1Angle lngLo()
  {
    return S1Angle.radians(lng.lo());
  }
  
  public strictfp S2LatLng lo()
  {
    return new S2LatLng(latLo(), lngLo());
  }
  
  public strictfp boolean mayIntersect(S2Cell paramS2Cell)
  {
    return intersects(paramS2Cell.getRectBound());
  }
  
  public strictfp String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[Lo=");
    localStringBuilder.append(lo());
    localStringBuilder.append(", Hi=");
    localStringBuilder.append(hi());
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public strictfp S2LatLngRect union(S2LatLngRect paramS2LatLngRect)
  {
    return new S2LatLngRect(lat.union(lat), lng.union(lng));
  }
}

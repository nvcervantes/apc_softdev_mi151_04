package com.google.maps.android;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class PolyUtil
{
  private static final double DEFAULT_TOLERANCE = 0.1D;
  
  private PolyUtil() {}
  
  public static boolean containsLocation(double paramDouble1, double paramDouble2, List<LatLng> paramList, boolean paramBoolean)
  {
    int i = paramList.size();
    if (i == 0) {
      return false;
    }
    double d3 = Math.toRadians(paramDouble1);
    double d4 = Math.toRadians(paramDouble2);
    LatLng localLatLng = (LatLng)paramList.get(i - 1);
    paramDouble1 = Math.toRadians(latitude);
    paramDouble2 = Math.toRadians(longitude);
    paramList = paramList.iterator();
    int j;
    for (i = 0; paramList.hasNext(); i = j)
    {
      localLatLng = (LatLng)paramList.next();
      double d5 = MathUtil.wrap(d4 - paramDouble2, -3.141592653589793D, 3.141592653589793D);
      if ((d3 == paramDouble1) && (d5 == 0.0D)) {
        return true;
      }
      double d2 = Math.toRadians(latitude);
      double d1 = Math.toRadians(longitude);
      j = i;
      if (intersects(paramDouble1, d2, MathUtil.wrap(d1 - paramDouble2, -3.141592653589793D, 3.141592653589793D), d3, d5, paramBoolean)) {
        j = i + 1;
      }
      paramDouble1 = d2;
      paramDouble2 = d1;
    }
    return (i & 0x1) != 0;
  }
  
  public static boolean containsLocation(LatLng paramLatLng, List<LatLng> paramList, boolean paramBoolean)
  {
    return containsLocation(latitude, longitude, paramList, paramBoolean);
  }
  
  public static List<LatLng> decode(String paramString)
  {
    int i2 = paramString.length();
    ArrayList localArrayList = new ArrayList();
    int m = 0;
    int i = m;
    int j = i;
    int k = i;
    i = m;
    if (i < i2)
    {
      m = 0;
      int n = 1;
      for (int i1 = i;; i1 = i)
      {
        i = i1 + 1;
        i1 = paramString.charAt(i1) - '?' - 1;
        n += (i1 << m);
        m += 5;
        if (i1 < 31)
        {
          if ((n & 0x1) != 0) {
            m = n >> 1 ^ 0xFFFFFFFF;
          } else {
            m = n >> 1;
          }
          i1 = m + k;
          k = 0;
          m = 1;
          for (n = i;; n = i)
          {
            i = n + 1;
            n = paramString.charAt(n) - '?' - 1;
            m += (n << k);
            k += 5;
            if (n < 31)
            {
              if ((m & 0x1) != 0) {
                k = m >> 1 ^ 0xFFFFFFFF;
              } else {
                k = m >> 1;
              }
              j += k;
              localArrayList.add(new LatLng(i1 * 1.0E-5D, j * 1.0E-5D));
              k = i1;
              break;
            }
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static double distanceToLine(LatLng paramLatLng1, LatLng paramLatLng2, LatLng paramLatLng3)
  {
    if (paramLatLng2.equals(paramLatLng3)) {
      return SphericalUtil.computeDistanceBetween(paramLatLng3, paramLatLng1);
    }
    double d1 = Math.toRadians(latitude);
    double d2 = Math.toRadians(longitude);
    double d3 = Math.toRadians(latitude);
    double d4 = Math.toRadians(longitude);
    double d6 = Math.toRadians(latitude);
    double d5 = Math.toRadians(longitude);
    d6 -= d3;
    d5 -= d4;
    d1 = ((d1 - d3) * d6 + (d2 - d4) * d5) / (d6 * d6 + d5 * d5);
    if (d1 <= 0.0D) {
      return SphericalUtil.computeDistanceBetween(paramLatLng1, paramLatLng2);
    }
    if (d1 >= 1.0D) {
      return SphericalUtil.computeDistanceBetween(paramLatLng1, paramLatLng3);
    }
    return SphericalUtil.computeDistanceBetween(new LatLng(latitude - latitude, longitude - longitude), new LatLng((latitude - latitude) * d1, d1 * (longitude - longitude)));
  }
  
  public static String encode(List<LatLng> paramList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    paramList = paramList.iterator();
    long l1 = 0L;
    long l3;
    for (long l2 = 0L; paramList.hasNext(); l2 = l3)
    {
      LatLng localLatLng = (LatLng)paramList.next();
      long l4 = Math.round(latitude * 100000.0D);
      l3 = Math.round(longitude * 100000.0D);
      encode(l4 - l1, localStringBuffer);
      encode(l3 - l2, localStringBuffer);
      l1 = l4;
    }
    return localStringBuffer.toString();
  }
  
  private static void encode(long paramLong, StringBuffer paramStringBuffer)
  {
    if (paramLong < 0L) {
      paramLong = paramLong << 1 ^ 0xFFFFFFFFFFFFFFFF;
    }
    for (paramLong <<= 1; paramLong >= 32L; paramLong >>= 5) {
      paramStringBuffer.append(Character.toChars((int)((0x20 | paramLong & 0x1F) + 63L)));
    }
    paramStringBuffer.append(Character.toChars((int)(paramLong + 63L)));
  }
  
  private static boolean intersects(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, boolean paramBoolean)
  {
    if (((paramDouble5 >= 0.0D) && (paramDouble5 >= paramDouble3)) || ((paramDouble5 < 0.0D) && (paramDouble5 < paramDouble3))) {
      return false;
    }
    if (paramDouble4 <= -1.5707963267948966D) {
      return false;
    }
    if ((paramDouble1 > -1.5707963267948966D) && (paramDouble2 > -1.5707963267948966D) && (paramDouble1 < 1.5707963267948966D))
    {
      if (paramDouble2 >= 1.5707963267948966D) {
        return false;
      }
      if (paramDouble3 <= -3.141592653589793D) {
        return false;
      }
      double d = ((paramDouble3 - paramDouble5) * paramDouble1 + paramDouble2 * paramDouble5) / paramDouble3;
      if ((paramDouble1 >= 0.0D) && (paramDouble2 >= 0.0D) && (paramDouble4 < d)) {
        return false;
      }
      if ((paramDouble1 <= 0.0D) && (paramDouble2 <= 0.0D) && (paramDouble4 >= d)) {
        return true;
      }
      if (paramDouble4 >= 1.5707963267948966D) {
        return true;
      }
      if (paramBoolean)
      {
        if (Math.tan(paramDouble4) < tanLatGC(paramDouble1, paramDouble2, paramDouble3, paramDouble5)) {}
      }
      else {
        while (MathUtil.mercator(paramDouble4) >= mercatorLatRhumb(paramDouble1, paramDouble2, paramDouble3, paramDouble5)) {
          return true;
        }
      }
      return false;
    }
    return false;
  }
  
  public static boolean isClosedPolygon(List<LatLng> paramList)
  {
    return ((LatLng)paramList.get(0)).equals((LatLng)paramList.get(paramList.size() - 1));
  }
  
  public static boolean isLocationOnEdge(LatLng paramLatLng, List<LatLng> paramList, boolean paramBoolean)
  {
    return isLocationOnEdge(paramLatLng, paramList, paramBoolean, 0.1D);
  }
  
  public static boolean isLocationOnEdge(LatLng paramLatLng, List<LatLng> paramList, boolean paramBoolean, double paramDouble)
  {
    return isLocationOnEdgeOrPath(paramLatLng, paramList, true, paramBoolean, paramDouble);
  }
  
  private static boolean isLocationOnEdgeOrPath(LatLng paramLatLng, List<LatLng> paramList, boolean paramBoolean1, boolean paramBoolean2, double paramDouble)
  {
    int i = paramList.size();
    if (i == 0) {
      return false;
    }
    double d12 = paramDouble / 6371009.0D;
    double d10 = MathUtil.hav(d12);
    double d7 = Math.toRadians(latitude);
    double d11 = Math.toRadians(longitude);
    if (paramBoolean1) {
      i -= 1;
    } else {
      i = 0;
    }
    paramLatLng = (LatLng)paramList.get(i);
    double d5 = Math.toRadians(latitude);
    double d1 = Math.toRadians(longitude);
    double d2;
    double d3;
    if (paramBoolean2)
    {
      paramLatLng = paramList.iterator();
      paramDouble = d7;
      d2 = d1;
      for (d1 = d5; paramLatLng.hasNext(); d1 = d3)
      {
        paramList = (LatLng)paramLatLng.next();
        d3 = Math.toRadians(latitude);
        d4 = Math.toRadians(longitude);
        if (isOnSegmentGC(d1, d2, d3, d4, paramDouble, d11, d10)) {
          return true;
        }
        d2 = d4;
      }
    }
    paramDouble = d12 + d7;
    double d4 = MathUtil.mercator(d5);
    double d13 = MathUtil.mercator(d7);
    double[] arrayOfDouble = new double[3];
    paramLatLng = paramList.iterator();
    while (paramLatLng.hasNext())
    {
      paramList = (LatLng)paramLatLng.next();
      double d6 = Math.toRadians(latitude);
      double d8 = MathUtil.mercator(d6);
      double d9 = Math.toRadians(longitude);
      d2 = paramDouble;
      d3 = d6;
      if (Math.max(d5, d6) >= d7 - d12)
      {
        d2 = paramDouble;
        d3 = d6;
        if (Math.min(d5, d6) <= paramDouble)
        {
          d5 = MathUtil.wrap(d9 - d1, -3.141592653589793D, 3.141592653589793D);
          d1 = MathUtil.wrap(d11 - d1, -3.141592653589793D, 3.141592653589793D);
          arrayOfDouble[0] = d1;
          arrayOfDouble[1] = (d1 + 6.283185307179586D);
          arrayOfDouble[2] = (d1 - 6.283185307179586D);
          int j = arrayOfDouble.length;
          i = 0;
          d1 = d6;
          for (;;)
          {
            d2 = paramDouble;
            d3 = d1;
            if (i >= j) {
              break;
            }
            d3 = arrayOfDouble[i];
            d6 = d8 - d4;
            double d14 = d5 * d5 + d6 * d6;
            d2 = 0.0D;
            if (d14 > 0.0D) {
              d2 = MathUtil.clamp((d3 * d5 + (d13 - d4) * d6) / d14, 0.0D, 1.0D);
            }
            if (MathUtil.havDistance(d7, MathUtil.inverseMercator(d4 + d2 * d6), d3 - d2 * d5) < d10) {
              return true;
            }
            i += 1;
          }
        }
      }
      d1 = d9;
      d4 = d8;
      paramDouble = d2;
      d5 = d3;
    }
    return false;
  }
  
  public static boolean isLocationOnPath(LatLng paramLatLng, List<LatLng> paramList, boolean paramBoolean)
  {
    return isLocationOnPath(paramLatLng, paramList, paramBoolean, 0.1D);
  }
  
  public static boolean isLocationOnPath(LatLng paramLatLng, List<LatLng> paramList, boolean paramBoolean, double paramDouble)
  {
    return isLocationOnEdgeOrPath(paramLatLng, paramList, false, paramBoolean, paramDouble);
  }
  
  private static boolean isOnSegmentGC(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7)
  {
    double d1 = MathUtil.havDistance(paramDouble1, paramDouble5, paramDouble2 - paramDouble6);
    if (d1 <= paramDouble7) {
      return true;
    }
    double d2 = MathUtil.havDistance(paramDouble3, paramDouble5, paramDouble4 - paramDouble6);
    if (d2 <= paramDouble7) {
      return true;
    }
    paramDouble5 = sinDeltaBearing(paramDouble1, paramDouble2, paramDouble3, paramDouble4, paramDouble5, paramDouble6);
    paramDouble5 = MathUtil.havFromSin(MathUtil.sinFromHav(d1) * paramDouble5);
    boolean bool = false;
    if (paramDouble5 > paramDouble7) {
      return false;
    }
    paramDouble1 = MathUtil.havDistance(paramDouble1, paramDouble3, paramDouble2 - paramDouble4);
    paramDouble2 = (1.0D - 2.0D * paramDouble1) * paramDouble5 + paramDouble1;
    if (d1 <= paramDouble2)
    {
      if (d2 > paramDouble2) {
        return false;
      }
      if (paramDouble1 < 0.74D) {
        return true;
      }
      paramDouble1 = 1.0D - 2.0D * paramDouble5;
      if (MathUtil.sinSumFromHav((d1 - paramDouble5) / paramDouble1, (d2 - paramDouble5) / paramDouble1) > 0.0D) {
        bool = true;
      }
      return bool;
    }
    return false;
  }
  
  private static double mercatorLatRhumb(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    return (MathUtil.mercator(paramDouble1) * (paramDouble3 - paramDouble4) + MathUtil.mercator(paramDouble2) * paramDouble4) / paramDouble3;
  }
  
  public static List<LatLng> simplify(List<LatLng> paramList, double paramDouble)
  {
    int k = paramList.size();
    if (k < 1) {
      throw new IllegalArgumentException("Polyline must have at least 1 point");
    }
    if (paramDouble <= 0.0D) {
      throw new IllegalArgumentException("Tolerance must be greater than zero");
    }
    boolean bool = isClosedPolygon(paramList);
    Object localObject1 = null;
    if (bool)
    {
      localObject1 = (LatLng)paramList.get(paramList.size() - 1);
      paramList.remove(paramList.size() - 1);
      paramList.add(new LatLng(latitude + 1.0E-11D, longitude + 1.0E-11D));
    }
    Object localObject2 = new Stack();
    double[] arrayOfDouble = new double[k];
    int j = 0;
    int i = 0;
    arrayOfDouble[0] = 1.0D;
    int m = k - 1;
    arrayOfDouble[m] = 1.0D;
    if (k > 2)
    {
      ((Stack)localObject2).push(new int[] { 0, m });
      k = 0;
      for (;;)
      {
        j = i;
        if (((Stack)localObject2).size() <= 0) {
          break;
        }
        int[] arrayOfInt = (int[])((Stack)localObject2).pop();
        j = arrayOfInt[i] + 1;
        double d2;
        for (double d1 = 0.0D; j < arrayOfInt[1]; d1 = d2)
        {
          double d3 = distanceToLine((LatLng)paramList.get(j), (LatLng)paramList.get(arrayOfInt[i]), (LatLng)paramList.get(arrayOfInt[1]));
          d2 = d1;
          if (d3 > d1)
          {
            d2 = d3;
            k = j;
          }
          j += 1;
          i = 0;
        }
        if (d1 > paramDouble)
        {
          arrayOfDouble[k] = d1;
          ((Stack)localObject2).push(new int[] { arrayOfInt[0], k });
          ((Stack)localObject2).push(new int[] { k, arrayOfInt[1] });
        }
        i = 0;
      }
    }
    if (bool)
    {
      paramList.remove(paramList.size() - 1);
      paramList.add(localObject1);
    }
    localObject1 = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      localObject2 = (LatLng)paramList.next();
      if (arrayOfDouble[j] != 0.0D) {
        ((ArrayList)localObject1).add(localObject2);
      }
      j += 1;
    }
    return localObject1;
  }
  
  private static double sinDeltaBearing(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6)
  {
    double d3 = Math.sin(paramDouble1);
    double d1 = Math.cos(paramDouble3);
    double d2 = Math.cos(paramDouble5);
    paramDouble6 -= paramDouble2;
    double d4 = paramDouble4 - paramDouble2;
    paramDouble2 = Math.sin(paramDouble6) * d2;
    paramDouble4 = Math.sin(d4) * d1;
    paramDouble5 = Math.sin(paramDouble5 - paramDouble1);
    d3 = 2.0D * d3;
    paramDouble5 += d2 * d3 * MathUtil.hav(paramDouble6);
    paramDouble1 = Math.sin(paramDouble3 - paramDouble1) + d3 * d1 * MathUtil.hav(d4);
    paramDouble3 = (paramDouble2 * paramDouble2 + paramDouble5 * paramDouble5) * (paramDouble4 * paramDouble4 + paramDouble1 * paramDouble1);
    if (paramDouble3 <= 0.0D) {
      return 1.0D;
    }
    return (paramDouble2 * paramDouble1 - paramDouble5 * paramDouble4) / Math.sqrt(paramDouble3);
  }
  
  private static double tanLatGC(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    return (Math.tan(paramDouble1) * Math.sin(paramDouble3 - paramDouble4) + Math.tan(paramDouble2) * Math.sin(paramDouble4)) / Math.sin(paramDouble3);
  }
}

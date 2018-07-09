package com.google.maps.android;

import com.google.android.gms.maps.model.LatLng;
import java.util.Iterator;
import java.util.List;

public class SphericalUtil
{
  private SphericalUtil() {}
  
  static double computeAngleBetween(LatLng paramLatLng1, LatLng paramLatLng2)
  {
    return distanceRadians(Math.toRadians(latitude), Math.toRadians(longitude), Math.toRadians(latitude), Math.toRadians(longitude));
  }
  
  public static double computeArea(List<LatLng> paramList)
  {
    return Math.abs(computeSignedArea(paramList));
  }
  
  public static double computeDistanceBetween(LatLng paramLatLng1, LatLng paramLatLng2)
  {
    return computeAngleBetween(paramLatLng1, paramLatLng2) * 6371009.0D;
  }
  
  public static double computeHeading(LatLng paramLatLng1, LatLng paramLatLng2)
  {
    double d1 = Math.toRadians(latitude);
    double d3 = Math.toRadians(longitude);
    double d2 = Math.toRadians(latitude);
    d3 = Math.toRadians(longitude) - d3;
    return MathUtil.wrap(Math.toDegrees(Math.atan2(Math.sin(d3) * Math.cos(d2), Math.cos(d1) * Math.sin(d2) - Math.sin(d1) * Math.cos(d2) * Math.cos(d3))), -180.0D, 180.0D);
  }
  
  public static double computeLength(List<LatLng> paramList)
  {
    int i = paramList.size();
    double d1 = 0.0D;
    if (i < 2) {
      return 0.0D;
    }
    LatLng localLatLng = (LatLng)paramList.get(0);
    double d2 = Math.toRadians(latitude);
    double d3 = Math.toRadians(longitude);
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      localLatLng = (LatLng)paramList.next();
      double d5 = Math.toRadians(latitude);
      double d4 = Math.toRadians(longitude);
      d1 += distanceRadians(d2, d3, d5, d4);
      d2 = d5;
      d3 = d4;
    }
    return d1 * 6371009.0D;
  }
  
  public static LatLng computeOffset(LatLng paramLatLng, double paramDouble1, double paramDouble2)
  {
    double d2 = paramDouble1 / 6371009.0D;
    paramDouble2 = Math.toRadians(paramDouble2);
    double d3 = Math.toRadians(latitude);
    paramDouble1 = Math.toRadians(longitude);
    double d1 = Math.cos(d2);
    double d4 = Math.sin(d2);
    d2 = Math.sin(d3);
    d4 *= Math.cos(d3);
    d3 = d1 * d2 + Math.cos(paramDouble2) * d4;
    paramDouble2 = Math.atan2(d4 * Math.sin(paramDouble2), d1 - d2 * d3);
    return new LatLng(Math.toDegrees(Math.asin(d3)), Math.toDegrees(paramDouble1 + paramDouble2));
  }
  
  public static LatLng computeOffsetOrigin(LatLng paramLatLng, double paramDouble1, double paramDouble2)
  {
    paramDouble2 = Math.toRadians(paramDouble2);
    paramDouble1 /= 6371009.0D;
    double d1 = Math.cos(paramDouble1);
    double d2 = Math.sin(paramDouble1) * Math.cos(paramDouble2);
    double d3 = Math.sin(paramDouble1);
    double d4 = Math.sin(paramDouble2);
    paramDouble1 = Math.sin(Math.toRadians(latitude));
    double d7 = d1 * d1;
    double d8 = d2 * d2;
    double d5 = d8 * d7 + d7 * d7 - d7 * paramDouble1 * paramDouble1;
    if (d5 < 0.0D) {
      return null;
    }
    double d6 = d2 * paramDouble1;
    paramDouble2 = Math.sqrt(d5);
    d7 += d8;
    paramDouble2 = (d6 + paramDouble2) / d7;
    d8 = (paramDouble1 - d2 * paramDouble2) / d1;
    paramDouble2 = Math.atan2(d8, paramDouble2);
    if (paramDouble2 >= -1.5707963267948966D)
    {
      paramDouble1 = paramDouble2;
      if (paramDouble2 <= 1.5707963267948966D) {}
    }
    else
    {
      paramDouble1 = Math.atan2(d8, (d6 - Math.sqrt(d5)) / d7);
    }
    if (paramDouble1 >= -1.5707963267948966D)
    {
      if (paramDouble1 > 1.5707963267948966D) {
        return null;
      }
      paramDouble2 = Math.toRadians(longitude);
      d1 = Math.atan2(d3 * d4, d1 * Math.cos(paramDouble1) - d2 * Math.sin(paramDouble1));
      return new LatLng(Math.toDegrees(paramDouble1), Math.toDegrees(paramDouble2 - d1));
    }
    return null;
  }
  
  public static double computeSignedArea(List<LatLng> paramList)
  {
    return computeSignedArea(paramList, 6371009.0D);
  }
  
  static double computeSignedArea(List<LatLng> paramList, double paramDouble)
  {
    int i = paramList.size();
    double d1 = 0.0D;
    if (i < 3) {
      return 0.0D;
    }
    LatLng localLatLng = (LatLng)paramList.get(i - 1);
    double d2 = Math.tan((1.5707963267948966D - Math.toRadians(latitude)) / 2.0D);
    double d3 = Math.toRadians(longitude);
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      localLatLng = (LatLng)paramList.next();
      double d5 = Math.tan((1.5707963267948966D - Math.toRadians(latitude)) / 2.0D);
      double d4 = Math.toRadians(longitude);
      d1 += polarTriangleArea(d5, d4, d2, d3);
      d2 = d5;
      d3 = d4;
    }
    return d1 * (paramDouble * paramDouble);
  }
  
  private static double distanceRadians(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    return MathUtil.arcHav(MathUtil.havDistance(paramDouble1, paramDouble3, paramDouble2 - paramDouble4));
  }
  
  public static LatLng interpolate(LatLng paramLatLng1, LatLng paramLatLng2, double paramDouble)
  {
    double d1 = Math.toRadians(latitude);
    double d4 = Math.toRadians(longitude);
    double d2 = Math.toRadians(latitude);
    double d5 = Math.toRadians(longitude);
    double d7 = Math.cos(d1);
    double d6 = Math.cos(d2);
    double d8 = computeAngleBetween(paramLatLng1, paramLatLng2);
    double d9 = Math.sin(d8);
    if (d9 < 1.0E-6D) {
      return paramLatLng1;
    }
    double d3 = Math.sin((1.0D - paramDouble) * d8) / d9;
    paramDouble = Math.sin(paramDouble * d8) / d9;
    d7 *= d3;
    d8 = Math.cos(d4);
    d9 = d6 * paramDouble;
    d6 = d8 * d7 + Math.cos(d5) * d9;
    d4 = d7 * Math.sin(d4) + d9 * Math.sin(d5);
    paramDouble = Math.atan2(d3 * Math.sin(d1) + paramDouble * Math.sin(d2), Math.sqrt(d6 * d6 + d4 * d4));
    d1 = Math.atan2(d4, d6);
    return new LatLng(Math.toDegrees(paramDouble), Math.toDegrees(d1));
  }
  
  private static double polarTriangleArea(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    paramDouble2 -= paramDouble4;
    paramDouble1 *= paramDouble3;
    return 2.0D * Math.atan2(Math.sin(paramDouble2) * paramDouble1, 1.0D + paramDouble1 * Math.cos(paramDouble2));
  }
}

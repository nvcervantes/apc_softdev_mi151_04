package com.google.maps.android;

class MathUtil
{
  static final double EARTH_RADIUS = 6371009.0D;
  
  MathUtil() {}
  
  static double arcHav(double paramDouble)
  {
    return 2.0D * Math.asin(Math.sqrt(paramDouble));
  }
  
  static double clamp(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    if (paramDouble1 < paramDouble2) {
      return paramDouble2;
    }
    paramDouble2 = paramDouble1;
    if (paramDouble1 > paramDouble3) {
      paramDouble2 = paramDouble3;
    }
    return paramDouble2;
  }
  
  static double hav(double paramDouble)
  {
    paramDouble = Math.sin(paramDouble * 0.5D);
    return paramDouble * paramDouble;
  }
  
  static double havDistance(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    return hav(paramDouble1 - paramDouble2) + hav(paramDouble3) * Math.cos(paramDouble1) * Math.cos(paramDouble2);
  }
  
  static double havFromSin(double paramDouble)
  {
    paramDouble *= paramDouble;
    return paramDouble / (1.0D + Math.sqrt(1.0D - paramDouble)) * 0.5D;
  }
  
  static double inverseMercator(double paramDouble)
  {
    return 2.0D * Math.atan(Math.exp(paramDouble)) - 1.5707963267948966D;
  }
  
  static double mercator(double paramDouble)
  {
    return Math.log(Math.tan(paramDouble * 0.5D + 0.7853981633974483D));
  }
  
  static double mod(double paramDouble1, double paramDouble2)
  {
    return (paramDouble1 % paramDouble2 + paramDouble2) % paramDouble2;
  }
  
  static double sinFromHav(double paramDouble)
  {
    return 2.0D * Math.sqrt(paramDouble * (1.0D - paramDouble));
  }
  
  static double sinSumFromHav(double paramDouble1, double paramDouble2)
  {
    double d1 = Math.sqrt((1.0D - paramDouble1) * paramDouble1);
    double d2 = Math.sqrt((1.0D - paramDouble2) * paramDouble2);
    return 2.0D * (d1 + d2 - (d1 * paramDouble2 + d2 * paramDouble1) * 2.0D);
  }
  
  static double wrap(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    if ((paramDouble1 >= paramDouble2) && (paramDouble1 < paramDouble3)) {
      return paramDouble1;
    }
    return mod(paramDouble1 - paramDouble2, paramDouble3 - paramDouble2) + paramDouble2;
  }
}

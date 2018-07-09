package com.google.maps.android.data.kml;

public class KmlBoolean
{
  public KmlBoolean() {}
  
  public static boolean parseBoolean(String paramString)
  {
    return ("1".equals(paramString)) || ("true".equals(paramString));
  }
}

package com.facebook.internal;

public class InternalSettings
{
  private static volatile String mCustomUserAgent;
  
  public InternalSettings() {}
  
  public static String getCustomUserAgent()
  {
    return mCustomUserAgent;
  }
  
  public static void setCustomUserAgent(String paramString)
  {
    mCustomUserAgent = paramString;
  }
}

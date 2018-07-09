package com.facebook.places.internal;

public class WifiScanResult
{
  public String bssid;
  public int frequency;
  public int rssi;
  public String ssid;
  public long timestampMs;
  
  public WifiScanResult() {}
  
  public WifiScanResult(String paramString1, String paramString2, int paramInt1, int paramInt2, long paramLong)
  {
    ssid = paramString1;
    bssid = paramString2;
    rssi = paramInt1;
    frequency = paramInt2;
    timestampMs = paramLong;
  }
}

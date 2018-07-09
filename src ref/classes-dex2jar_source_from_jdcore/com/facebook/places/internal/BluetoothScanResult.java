package com.facebook.places.internal;

public class BluetoothScanResult
{
  public String payload;
  public int rssi;
  public long timestampNanos;
  
  public BluetoothScanResult(String paramString, int paramInt, long paramLong)
  {
    payload = paramString;
    rssi = paramInt;
    timestampNanos = paramLong;
  }
}

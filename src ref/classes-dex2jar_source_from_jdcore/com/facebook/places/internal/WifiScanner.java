package com.facebook.places.internal;

import java.util.List;

public abstract interface WifiScanner
{
  public abstract WifiScanResult getConnectedWifi()
    throws ScannerException;
  
  public abstract List<WifiScanResult> getWifiScans()
    throws ScannerException;
  
  public abstract void initAndCheckEligibility()
    throws ScannerException;
  
  public abstract boolean isWifiScanningEnabled();
}

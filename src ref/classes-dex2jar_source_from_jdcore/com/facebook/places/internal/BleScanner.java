package com.facebook.places.internal;

import java.util.List;

public abstract interface BleScanner
{
  public abstract int getErrorCode();
  
  public abstract List<BluetoothScanResult> getScanResults();
  
  public abstract void initAndCheckEligibility()
    throws ScannerException;
  
  public abstract void startScanning()
    throws ScannerException;
  
  public abstract void stopScanning()
    throws ScannerException;
}

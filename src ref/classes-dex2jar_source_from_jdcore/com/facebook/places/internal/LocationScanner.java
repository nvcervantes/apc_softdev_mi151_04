package com.facebook.places.internal;

import android.location.Location;

public abstract interface LocationScanner
{
  public abstract Location getLocation()
    throws ScannerException;
  
  public abstract void initAndCheckEligibility()
    throws ScannerException;
}

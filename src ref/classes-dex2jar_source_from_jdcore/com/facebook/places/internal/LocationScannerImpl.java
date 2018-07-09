package com.facebook.places.internal;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.HandlerThread;
import com.facebook.internal.Validate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LocationScannerImpl
  implements LocationScanner, LocationListener
{
  private static final float MIN_DISTANCE_BETWEEN_UPDATES = 0.0F;
  private static final long MIN_TIME_BETWEEN_UPDATES = 100L;
  private Context context;
  private List<String> enabledProviders;
  private Location freshLocation;
  private LocationManager locationManager;
  private LocationPackageRequestParams params;
  private final Object scanLock = new Object();
  
  public LocationScannerImpl(Context paramContext, LocationPackageRequestParams paramLocationPackageRequestParams)
  {
    context = paramContext;
    params = paramLocationPackageRequestParams;
    locationManager = ((LocationManager)paramContext.getSystemService("location"));
  }
  
  private Location getFreshLocation()
    throws ScannerException
  {
    freshLocation = null;
    localHandlerThread = new HandlerThread("LocationScanner");
    try
    {
      localHandlerThread.start();
      ??? = enabledProviders.iterator();
      while (((Iterator)???).hasNext())
      {
        String str = (String)((Iterator)???).next();
        locationManager.requestLocationUpdates(str, 100L, 0.0F, this, localHandlerThread.getLooper());
      }
    }
    finally
    {
      label99:
      locationManager.removeUpdates(this);
      localHandlerThread.quit();
    }
    try
    {
      synchronized (scanLock)
      {
        scanLock.wait(params.getLocationRequestTimeoutMs());
      }
    }
    catch (Exception localException)
    {
      break label99;
    }
    locationManager.removeUpdates(this);
    localHandlerThread.quit();
    if (freshLocation == null) {
      throw new ScannerException(ScannerException.Type.TIMEOUT);
    }
    return freshLocation;
  }
  
  private Location getLastLocation(String paramString)
  {
    paramString = locationManager.getLastKnownLocation(paramString);
    if (paramString != null)
    {
      long l = paramString.getTime();
      if (System.currentTimeMillis() - l < params.getLastLocationMaxAgeMs()) {
        return paramString;
      }
    }
    return null;
  }
  
  public Location getLocation()
    throws ScannerException
  {
    Iterator localIterator = enabledProviders.iterator();
    while (localIterator.hasNext())
    {
      Location localLocation = getLastLocation((String)localIterator.next());
      if (localLocation != null) {
        return localLocation;
      }
    }
    return getFreshLocation();
  }
  
  public void initAndCheckEligibility()
    throws ScannerException
  {
    if (!Validate.hasLocationPermission(context)) {
      throw new ScannerException(ScannerException.Type.PERMISSION_DENIED);
    }
    enabledProviders = new ArrayList(params.getLocationProviders().length);
    String[] arrayOfString = params.getLocationProviders();
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = arrayOfString[i];
      if (locationManager.isProviderEnabled(str)) {
        enabledProviders.add(str);
      }
      i += 1;
    }
    if (enabledProviders.isEmpty()) {
      throw new ScannerException(ScannerException.Type.DISABLED);
    }
  }
  
  public void onLocationChanged(Location paramLocation)
  {
    if ((freshLocation == null) && (paramLocation.getAccuracy() < params.getLocationMaxAccuracyMeters())) {
      synchronized (scanLock)
      {
        freshLocation = paramLocation;
        scanLock.notify();
        return;
      }
    }
  }
  
  public void onProviderDisabled(String paramString) {}
  
  public void onProviderEnabled(String paramString) {}
  
  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
}

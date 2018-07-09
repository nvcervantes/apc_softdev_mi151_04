package com.facebook.places.internal;

public class LocationPackageRequestParams
{
  private static final boolean DEFAULT_BLUETOOTH_ENABLED = true;
  private static final long DEFAULT_BLUETOOTH_FLUSH_RESULTS_TIMEOUT_MS = 300L;
  private static final int DEFAULT_BLUETOOTH_MAX_SCAN_RESULTS = 25;
  private static final long DEFAULT_BLUETOOTH_SCAN_DURATION_MS = 500L;
  private static final long DEFAULT_LAST_LOCATION_MAX_AGE_MS = 60000L;
  private static final boolean DEFAULT_LOCATION_ENABLED = true;
  private static final float DEFAULT_LOCATION_MAX_ACCURACY_METERS = 100.0F;
  private static final String[] DEFAULT_LOCATION_PROVIDERS = { "network", "gps" };
  private static final long DEFAULT_LOCATION_REQUEST_TIMEOUT_MS = 30000L;
  private static final boolean DEFAULT_WIFI_ACTIVE_SCAN_ALLOWED = true;
  private static final boolean DEFAULT_WIFI_ACTIVE_SCAN_FORCED = false;
  private static final boolean DEFAULT_WIFI_ENABLED = true;
  private static final int DEFAULT_WIFI_MAX_SCAN_RESULTS = 25;
  private static final long DEFAULT_WIFI_SCAN_MAX_AGE_MS = 30000L;
  private static final long DEFAULT_WIFI_SCAN_TIMEOUT_MS = 6000L;
  private long bluetoothFlushResultsTimeoutMs;
  private int bluetoothMaxScanResults;
  private long bluetoothScanDurationMs;
  private boolean isBluetoothScanEnabled;
  private boolean isLocationScanEnabled;
  private boolean isWifiActiveScanAllowed;
  private boolean isWifiActiveScanForced;
  private boolean isWifiScanEnabled;
  private long lastLocationMaxAgeMs;
  private float locationMaxAccuracyMeters;
  private final String[] locationProviders;
  private long locationRequestTimeoutMs;
  private int wifiMaxScanResults;
  private long wifiScanMaxAgeMs;
  private long wifiScanTimeoutMs;
  
  private LocationPackageRequestParams(Builder paramBuilder)
  {
    isLocationScanEnabled = isLocationScanEnabled;
    locationProviders = locationProviders;
    locationMaxAccuracyMeters = locationMaxAccuracyMeters;
    locationRequestTimeoutMs = locationRequestTimeoutMs;
    lastLocationMaxAgeMs = lastLocationMaxAgeMs;
    isWifiScanEnabled = isWifiScanEnabled;
    wifiScanMaxAgeMs = wifiScanMaxAgeMs;
    wifiMaxScanResults = wifiMaxScanResults;
    wifiScanTimeoutMs = wifiScanTimeoutMs;
    isWifiActiveScanAllowed = isWifiActiveScanAllowed;
    isWifiActiveScanForced = isWifiActiveScanForced;
    isBluetoothScanEnabled = isBluetoothScanEnabled;
    bluetoothScanDurationMs = bluetoothScanDurationMs;
    bluetoothMaxScanResults = bluetoothMaxScanResults;
    bluetoothFlushResultsTimeoutMs = bluetoothFlushResultsTimeoutMs;
  }
  
  public long getBluetoothFlushResultsTimeoutMs()
  {
    return bluetoothFlushResultsTimeoutMs;
  }
  
  public int getBluetoothMaxScanResults()
  {
    return bluetoothMaxScanResults;
  }
  
  public long getBluetoothScanDurationMs()
  {
    return bluetoothScanDurationMs;
  }
  
  public long getLastLocationMaxAgeMs()
  {
    return lastLocationMaxAgeMs;
  }
  
  public float getLocationMaxAccuracyMeters()
  {
    return locationMaxAccuracyMeters;
  }
  
  public String[] getLocationProviders()
  {
    return locationProviders;
  }
  
  public long getLocationRequestTimeoutMs()
  {
    return locationRequestTimeoutMs;
  }
  
  public int getWifiMaxScanResults()
  {
    return wifiMaxScanResults;
  }
  
  public long getWifiScanMaxAgeMs()
  {
    return wifiScanMaxAgeMs;
  }
  
  public long getWifiScanTimeoutMs()
  {
    return wifiScanTimeoutMs;
  }
  
  public boolean isBluetoothScanEnabled()
  {
    return isBluetoothScanEnabled;
  }
  
  public boolean isLocationScanEnabled()
  {
    return isLocationScanEnabled;
  }
  
  public boolean isWifiActiveScanAllowed()
  {
    return isWifiActiveScanAllowed;
  }
  
  public boolean isWifiActiveScanForced()
  {
    return isWifiActiveScanForced;
  }
  
  public boolean isWifiScanEnabled()
  {
    return isWifiScanEnabled;
  }
  
  public static class Builder
  {
    private long bluetoothFlushResultsTimeoutMs = 300L;
    private int bluetoothMaxScanResults = 25;
    private long bluetoothScanDurationMs = 500L;
    private boolean isBluetoothScanEnabled = true;
    private boolean isLocationScanEnabled = true;
    private boolean isWifiActiveScanAllowed = true;
    private boolean isWifiActiveScanForced = false;
    private boolean isWifiScanEnabled = true;
    private long lastLocationMaxAgeMs = 60000L;
    private float locationMaxAccuracyMeters = 100.0F;
    private String[] locationProviders = LocationPackageRequestParams.DEFAULT_LOCATION_PROVIDERS;
    private long locationRequestTimeoutMs = 30000L;
    private int wifiMaxScanResults = 25;
    private long wifiScanMaxAgeMs = 30000L;
    private long wifiScanTimeoutMs = 6000L;
    
    public Builder() {}
    
    public LocationPackageRequestParams build()
    {
      return new LocationPackageRequestParams(this, null);
    }
    
    public Builder setBluetoothFlushResultsTimeoutMs(long paramLong)
    {
      bluetoothFlushResultsTimeoutMs = paramLong;
      return this;
    }
    
    public Builder setBluetoothMaxScanResults(int paramInt)
    {
      bluetoothMaxScanResults = paramInt;
      return this;
    }
    
    public Builder setBluetoothScanDurationMs(long paramLong)
    {
      bluetoothScanDurationMs = paramLong;
      return this;
    }
    
    public Builder setBluetoothScanEnabled(boolean paramBoolean)
    {
      isBluetoothScanEnabled = paramBoolean;
      return this;
    }
    
    public Builder setLastLocationMaxAgeMs(long paramLong)
    {
      lastLocationMaxAgeMs = paramLong;
      return this;
    }
    
    public Builder setLocationMaxAccuracyMeters(float paramFloat)
    {
      locationMaxAccuracyMeters = paramFloat;
      return this;
    }
    
    public Builder setLocationProviders(String[] paramArrayOfString)
    {
      locationProviders = paramArrayOfString;
      return this;
    }
    
    public Builder setLocationRequestTimeoutMs(long paramLong)
    {
      locationRequestTimeoutMs = paramLong;
      return this;
    }
    
    public Builder setLocationScanEnabled(boolean paramBoolean)
    {
      isLocationScanEnabled = paramBoolean;
      return this;
    }
    
    public Builder setWifiActiveScanAllowed(boolean paramBoolean)
    {
      isWifiActiveScanAllowed = paramBoolean;
      return this;
    }
    
    public Builder setWifiActiveScanForced(boolean paramBoolean)
    {
      isWifiActiveScanForced = paramBoolean;
      return this;
    }
    
    public Builder setWifiMaxScanResults(int paramInt)
    {
      wifiMaxScanResults = paramInt;
      return this;
    }
    
    public Builder setWifiScanEnabled(boolean paramBoolean)
    {
      isWifiScanEnabled = paramBoolean;
      return this;
    }
    
    public Builder setWifiScanMaxAgeMs(long paramLong)
    {
      wifiScanMaxAgeMs = paramLong;
      return this;
    }
    
    public Builder setWifiScanTimeoutMs(long paramLong)
    {
      wifiScanTimeoutMs = paramLong;
      return this;
    }
  }
}

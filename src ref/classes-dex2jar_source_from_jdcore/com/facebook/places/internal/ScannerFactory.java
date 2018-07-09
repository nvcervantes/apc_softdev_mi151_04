package com.facebook.places.internal;

import android.content.Context;
import android.os.Build.VERSION;

public class ScannerFactory
{
  public static final int OS_VERSION_JELLY_BEAN_MR1 = 17;
  public static final int OS_VERSION_JELLY_BEAN_MR2 = 18;
  public static final int OS_VERSION_LOLLIPOP = 21;
  
  public ScannerFactory() {}
  
  public static BleScanner newBleScanner(Context paramContext, LocationPackageRequestParams paramLocationPackageRequestParams)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return new BleScannerImpl(paramContext, paramLocationPackageRequestParams);
    }
    return new BleScannerLegacy();
  }
  
  public static LocationScanner newLocationScanner(Context paramContext, LocationPackageRequestParams paramLocationPackageRequestParams)
  {
    return new LocationScannerImpl(paramContext, paramLocationPackageRequestParams);
  }
  
  public static WifiScanner newWifiScanner(Context paramContext, LocationPackageRequestParams paramLocationPackageRequestParams)
  {
    return new WifiScannerImpl(paramContext, paramLocationPackageRequestParams);
  }
}

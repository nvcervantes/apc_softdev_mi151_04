package com.facebook.places.internal;

import android.util.Log;
import com.facebook.FacebookSdk;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

public class LocationPackageManager
{
  private static final String TAG = "LocationPackageManager";
  
  public LocationPackageManager() {}
  
  private static void logException(String paramString, Throwable paramThrowable)
  {
    if (FacebookSdk.isDebugEnabled()) {
      Log.e("LocationPackageManager", paramString, paramThrowable);
    }
  }
  
  private static FutureTask<LocationPackage> newBluetoothScanFuture(LocationPackageRequestParams paramLocationPackageRequestParams)
  {
    new FutureTask(new Callable()
    {
      /* Error */
      public LocationPackage call()
        throws Exception
      {
        // Byte code:
        //   0: new 28	com/facebook/places/internal/LocationPackage
        //   3: dup
        //   4: invokespecial 29	com/facebook/places/internal/LocationPackage:<init>	()V
        //   7: astore_2
        //   8: invokestatic 35	com/facebook/FacebookSdk:getApplicationContext	()Landroid/content/Context;
        //   11: aload_0
        //   12: getfield 18	com/facebook/places/internal/LocationPackageManager$3:val$requestParams	Lcom/facebook/places/internal/LocationPackageRequestParams;
        //   15: invokestatic 41	com/facebook/places/internal/ScannerFactory:newBleScanner	(Landroid/content/Context;Lcom/facebook/places/internal/LocationPackageRequestParams;)Lcom/facebook/places/internal/BleScanner;
        //   18: astore_3
        //   19: aload_3
        //   20: invokeinterface 46 1 0
        //   25: aload_3
        //   26: invokeinterface 49 1 0
        //   31: aload_0
        //   32: getfield 18	com/facebook/places/internal/LocationPackageManager$3:val$requestParams	Lcom/facebook/places/internal/LocationPackageRequestParams;
        //   35: invokevirtual 55	com/facebook/places/internal/LocationPackageRequestParams:getBluetoothScanDurationMs	()J
        //   38: invokestatic 61	java/lang/Thread:sleep	(J)V
        //   41: aload_3
        //   42: invokeinterface 64 1 0
        //   47: aload_3
        //   48: invokeinterface 68 1 0
        //   53: istore_1
        //   54: iload_1
        //   55: ifne +20 -> 75
        //   58: aload_2
        //   59: aload_3
        //   60: invokeinterface 72 1 0
        //   65: putfield 76	com/facebook/places/internal/LocationPackage:ambientBluetoothLe	Ljava/util/List;
        //   68: aload_2
        //   69: iconst_1
        //   70: putfield 80	com/facebook/places/internal/LocationPackage:isBluetoothScanningEnabled	Z
        //   73: aload_2
        //   74: areturn
        //   75: invokestatic 84	com/facebook/FacebookSdk:isDebugEnabled	()Z
        //   78: ifeq +25 -> 103
        //   81: ldc 86
        //   83: ldc 88
        //   85: iconst_1
        //   86: anewarray 5	java/lang/Object
        //   89: dup
        //   90: iconst_0
        //   91: iload_1
        //   92: invokestatic 94	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   95: aastore
        //   96: invokestatic 100	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   99: invokestatic 106	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   102: pop
        //   103: aload_2
        //   104: iconst_0
        //   105: putfield 80	com/facebook/places/internal/LocationPackage:isBluetoothScanningEnabled	Z
        //   108: aload_2
        //   109: areturn
        //   110: astore 4
        //   112: aload_3
        //   113: invokeinterface 64 1 0
        //   118: aload 4
        //   120: athrow
        //   121: astore_3
        //   122: ldc 108
        //   124: aload_3
        //   125: invokestatic 112	com/facebook/places/internal/LocationPackageManager:access$300	(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   128: aload_2
        //   129: iconst_0
        //   130: putfield 80	com/facebook/places/internal/LocationPackage:isBluetoothScanningEnabled	Z
        //   133: aload_2
        //   134: areturn
        //   135: astore 4
        //   137: goto -96 -> 41
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	140	0	this	3
        //   53	39	1	i	int
        //   7	127	2	localLocationPackage	LocationPackage
        //   18	95	3	localBleScanner	BleScanner
        //   121	4	3	localException1	Exception
        //   110	9	4	localObject	Object
        //   135	1	4	localException2	Exception
        // Exception table:
        //   from	to	target	type
        //   25	31	110	finally
        //   31	41	110	finally
        //   8	25	121	java/lang/Exception
        //   41	54	121	java/lang/Exception
        //   58	73	121	java/lang/Exception
        //   75	103	121	java/lang/Exception
        //   103	108	121	java/lang/Exception
        //   112	121	121	java/lang/Exception
        //   31	41	135	java/lang/Exception
      }
    });
  }
  
  private static FutureTask<LocationPackage> newLocationScanFuture(LocationScanner paramLocationScanner, LocationPackageRequestParams paramLocationPackageRequestParams)
  {
    new FutureTask(new Callable()
    {
      public LocationPackage call()
        throws Exception
      {
        localLocationPackage = new LocationPackage();
        try
        {
          location = val$locationScanner.getLocation();
          return localLocationPackage;
        }
        catch (ScannerException localScannerException)
        {
          locationError = type;
          LocationPackageManager.logException("Exception while getting location", localScannerException);
          return localLocationPackage;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
        locationError = ScannerException.Type.UNKNOWN_ERROR;
        return localLocationPackage;
      }
    });
  }
  
  private static FutureTask<LocationPackage> newWifiScanFuture(LocationPackageRequestParams paramLocationPackageRequestParams)
  {
    new FutureTask(new Callable()
    {
      public LocationPackage call()
        throws Exception
      {
        LocationPackage localLocationPackage = new LocationPackage();
        try
        {
          WifiScanner localWifiScanner = ScannerFactory.newWifiScanner(FacebookSdk.getApplicationContext(), val$requestParams);
          localWifiScanner.initAndCheckEligibility();
          connectedWifi = localWifiScanner.getConnectedWifi();
          isWifiScanningEnabled = localWifiScanner.isWifiScanningEnabled();
          if (isWifiScanningEnabled)
          {
            ambientWifi = localWifiScanner.getWifiScans();
            return localLocationPackage;
          }
        }
        catch (Exception localException)
        {
          LocationPackageManager.logException("Exception scanning for wifi access points", localException);
          isWifiScanningEnabled = false;
        }
        return localLocationPackage;
      }
    });
  }
  
  public static void requestLocationPackage(LocationPackageRequestParams paramLocationPackageRequestParams, final Listener paramListener)
  {
    FacebookSdk.getExecutor().execute(new Runnable()
    {
      public void run()
      {
        LocationPackage localLocationPackage = new LocationPackage();
        for (;;)
        {
          try
          {
            boolean bool = val$requestParams.isLocationScanEnabled();
            Object localObject5 = null;
            if (!bool) {
              break label298;
            }
            Object localObject1 = ScannerFactory.newLocationScanner(FacebookSdk.getApplicationContext(), val$requestParams);
            ((LocationScanner)localObject1).initAndCheckEligibility();
            localObject1 = LocationPackageManager.newLocationScanFuture((LocationScanner)localObject1, val$requestParams);
            FacebookSdk.getExecutor().execute((Runnable)localObject1);
            if (!val$requestParams.isWifiScanEnabled()) {
              break label303;
            }
            Object localObject3 = LocationPackageManager.newWifiScanFuture(val$requestParams);
            FacebookSdk.getExecutor().execute((Runnable)localObject3);
            if (val$requestParams.isBluetoothScanEnabled())
            {
              localObject5 = LocationPackageManager.newBluetoothScanFuture(val$requestParams);
              FacebookSdk.getExecutor().execute((Runnable)localObject5);
            }
            if (localObject5 != null) {
              try
              {
                localObject5 = (LocationPackage)((FutureTask)localObject5).get();
                ambientBluetoothLe = ambientBluetoothLe;
                isBluetoothScanningEnabled = isBluetoothScanningEnabled;
              }
              catch (Exception localException4)
              {
                LocationPackageManager.logException("Exception scanning for bluetooth beacons", localException4);
              }
            }
            if (localObject3 != null) {
              try
              {
                localObject3 = (LocationPackage)((FutureTask)localObject3).get();
                isWifiScanningEnabled = isWifiScanningEnabled;
                connectedWifi = connectedWifi;
                ambientWifi = ambientWifi;
              }
              catch (Exception localException3)
              {
                LocationPackageManager.logException("Exception scanning for wifi access points", localException3);
              }
            }
            if (localObject1 != null) {
              try
              {
                localObject1 = (LocationPackage)((FutureTask)localObject1).get();
                locationError = locationError;
                location = location;
              }
              catch (Exception localException1)
              {
                LocationPackageManager.logException("Exception getting location", localException1);
              }
            }
            paramListener.onLocationPackage(localLocationPackage);
          }
          catch (Exception localException2)
          {
            LocationPackageManager.logException("Exception requesting a location package", localException2);
          }
          catch (ScannerException localScannerException)
          {
            LocationPackageManager.logException("Exception scanning for locations", localScannerException);
            locationError = type;
          }
          return;
          label298:
          Object localObject2 = null;
          continue;
          label303:
          Object localObject4 = null;
        }
      }
    });
  }
  
  public static abstract interface Listener
  {
    public abstract void onLocationPackage(LocationPackage paramLocationPackage);
  }
}

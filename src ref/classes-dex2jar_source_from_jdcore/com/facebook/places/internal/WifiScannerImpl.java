package com.facebook.places.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.internal.Validate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WifiScannerImpl
  implements WifiScanner
{
  private static final String SSID_NOMAP = "_nomap";
  private static final String SSID_OPTOUT = "_optout";
  private ScanResultBroadcastReceiver broadcastReceiver;
  private Context context;
  private final LocationPackageRequestParams params;
  private final Object scanLock = new Object();
  private WifiManager wifiManager;
  
  WifiScannerImpl(Context paramContext, LocationPackageRequestParams paramLocationPackageRequestParams)
  {
    context = paramContext;
    params = paramLocationPackageRequestParams;
  }
  
  private static void filterResults(List<ScanResult> paramList, int paramInt)
  {
    if (paramList.size() > paramInt)
    {
      Collections.sort(paramList, new Comparator()
      {
        public int compare(ScanResult paramAnonymousScanResult1, ScanResult paramAnonymousScanResult2)
        {
          return level - level;
        }
      });
      paramList.subList(paramInt, paramList.size()).clear();
    }
  }
  
  private static List<ScanResult> filterWifiScanResultsByMaxAge(List<ScanResult> paramList, long paramLong)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramList != null)
    {
      if (Build.VERSION.SDK_INT < 17)
      {
        localArrayList.addAll(paramList);
        return localArrayList;
      }
      long l3 = SystemClock.elapsedRealtime();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        ScanResult localScanResult = (ScanResult)paramList.next();
        long l2 = l3 - timestamp / 1000L;
        long l1 = l2;
        if (l2 < 0L) {
          l1 = System.currentTimeMillis() - timestamp;
        }
        if (l1 < paramLong) {
          localArrayList.add(localScanResult);
        }
      }
    }
    return localArrayList;
  }
  
  private List<WifiScanResult> getActiveScanResults()
    throws ScannerException
  {
    localObject3 = null;
    ??? = localObject3;
    try
    {
      try
      {
        if (Validate.hasChangeWifiStatePermission(context))
        {
          registerBroadcastReceiver();
          boolean bool = wifiManager.startScan();
          ??? = localObject3;
          if (!bool) {}
        }
      }
      finally
      {
        label65:
        unregisterBroadcastReceiver();
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject2 = localObject3;
      }
    }
    try
    {
      synchronized (scanLock)
      {
        scanLock.wait(params.getWifiScanTimeoutMs());
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      break label65;
    }
    ??? = getCachedScanResults();
    unregisterBroadcastReceiver();
    return localList;
  }
  
  private List<WifiScanResult> getCachedScanResults()
    throws ScannerException
  {
    try
    {
      Object localObject = filterWifiScanResultsByMaxAge(wifiManager.getScanResults(), params.getWifiScanMaxAgeMs());
      filterResults((List)localObject, params.getWifiMaxScanResults());
      ArrayList localArrayList = new ArrayList(((List)localObject).size());
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ScanResult localScanResult = (ScanResult)((Iterator)localObject).next();
        if (!isWifiSsidBlacklisted(SSID))
        {
          WifiScanResult localWifiScanResult = new WifiScanResult();
          bssid = BSSID;
          ssid = SSID;
          rssi = level;
          frequency = frequency;
          if (Build.VERSION.SDK_INT >= 17) {
            timestampMs = TimeUnit.MICROSECONDS.toMillis(timestamp);
          } else {
            timestampMs = SystemClock.elapsedRealtime();
          }
          localArrayList.add(localWifiScanResult);
        }
      }
      return localArrayList;
    }
    catch (Exception localException)
    {
      throw new ScannerException(ScannerException.Type.UNKNOWN_ERROR, localException);
    }
  }
  
  private boolean isWifiScanningAlwaysOn()
  {
    if (Build.VERSION.SDK_INT >= 18) {
      return wifiManager.isScanAlwaysAvailable();
    }
    return false;
  }
  
  private static boolean isWifiSsidBlacklisted(String paramString)
  {
    return (paramString != null) && ((paramString.endsWith("_nomap")) || (paramString.contains("_optout")));
  }
  
  private void registerBroadcastReceiver()
  {
    if (broadcastReceiver != null) {
      unregisterBroadcastReceiver();
    }
    broadcastReceiver = new ScanResultBroadcastReceiver(null);
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.wifi.SCAN_RESULTS");
    context.registerReceiver(broadcastReceiver, localIntentFilter);
  }
  
  private void unregisterBroadcastReceiver()
  {
    if (broadcastReceiver != null) {}
    try
    {
      context.unregisterReceiver(broadcastReceiver);
      broadcastReceiver = null;
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public WifiScanResult getConnectedWifi()
    throws ScannerException
  {
    try
    {
      WifiInfo localWifiInfo = wifiManager.getConnectionInfo();
      if ((localWifiInfo != null) && (!TextUtils.isEmpty(localWifiInfo.getBSSID())) && (localWifiInfo.getSupplicantState() == SupplicantState.COMPLETED) && (!isWifiSsidBlacklisted(localWifiInfo.getSSID())))
      {
        WifiScanResult localWifiScanResult = new WifiScanResult();
        bssid = localWifiInfo.getBSSID();
        ssid = localWifiInfo.getSSID();
        rssi = localWifiInfo.getRssi();
        timestampMs = SystemClock.elapsedRealtime();
        if (Build.VERSION.SDK_INT >= 21) {
          frequency = localWifiInfo.getFrequency();
        }
        return localWifiScanResult;
      }
      return null;
    }
    catch (Exception localException)
    {
      throw new ScannerException(ScannerException.Type.UNKNOWN_ERROR, localException);
    }
  }
  
  public List<WifiScanResult> getWifiScans()
    throws ScannerException
  {
    List localList1 = null;
    for (;;)
    {
      List localList2;
      try
      {
        if (!params.isWifiActiveScanForced()) {
          localList1 = getCachedScanResults();
        }
        if (localList1 == null) {
          break label82;
        }
        if (!localList1.isEmpty()) {
          break label77;
        }
      }
      finally {}
      if (!params.isWifiActiveScanForced())
      {
        localList2 = localList1;
        if (params.isWifiActiveScanAllowed())
        {
          localList2 = localList1;
          if (i == 0) {}
        }
      }
      else
      {
        localList2 = getActiveScanResults();
      }
      return localList2;
      label77:
      int i = 0;
      continue;
      label82:
      i = 1;
    }
  }
  
  public void initAndCheckEligibility()
    throws ScannerException
  {
    if (!context.getPackageManager().hasSystemFeature("android.hardware.wifi")) {
      throw new ScannerException(ScannerException.Type.NOT_SUPPORTED);
    }
    if (!Validate.hasWiFiPermission(context)) {
      throw new ScannerException(ScannerException.Type.PERMISSION_DENIED);
    }
    if (wifiManager == null) {
      wifiManager = ((WifiManager)context.getSystemService("wifi"));
    }
    if ((!isWifiScanningAlwaysOn()) && (!wifiManager.isWifiEnabled())) {
      throw new ScannerException(ScannerException.Type.DISABLED);
    }
  }
  
  public boolean isWifiScanningEnabled()
  {
    try
    {
      initAndCheckEligibility();
      boolean bool = Validate.hasLocationPermission(context);
      if (bool) {
        return true;
      }
    }
    catch (ScannerException localScannerException)
    {
      for (;;) {}
    }
    return false;
  }
  
  private class ScanResultBroadcastReceiver
    extends BroadcastReceiver
  {
    private ScanResultBroadcastReceiver() {}
    
    public void onReceive(Context arg1, Intent paramIntent)
    {
      if ((paramIntent != null) && ("android.net.wifi.SCAN_RESULTS".equals(paramIntent.getAction()))) {
        synchronized (scanLock)
        {
          scanLock.notify();
          WifiScannerImpl.this.unregisterBroadcastReceiver();
          return;
        }
      }
    }
  }
}

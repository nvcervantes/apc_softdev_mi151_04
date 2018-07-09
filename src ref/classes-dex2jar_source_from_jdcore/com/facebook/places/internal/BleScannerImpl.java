package com.facebook.places.internal;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.internal.Validate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@TargetApi(21)
public class BleScannerImpl
  implements BleScanner
{
  private static final byte[] EDDYSTONE_PREFIX = fromHexString("16aafe");
  private static final byte[] GRAVITY_PREFIX = fromHexString("17ffab01");
  private static final byte[] IBEACON_PREFIX = fromHexString("ff4c000215");
  private static final String TAG = "BleScannerImpl";
  private BluetoothAdapter bluetoothAdapter;
  private BluetoothLeScanner bluetoothLeScanner;
  private Context context;
  private int errorCode;
  private boolean isScanInProgress;
  private LocationPackageRequestParams params;
  private ScanCallBackImpl scanCallBack;
  private final List<BluetoothScanResult> scanResults = new ArrayList();
  
  BleScannerImpl(Context paramContext, LocationPackageRequestParams paramLocationPackageRequestParams)
  {
    context = paramContext;
    params = paramLocationPackageRequestParams;
  }
  
  private static String formatPayload(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length != 0)) {
      return toHexString(paramArrayOfByte, getPayloadLength(paramArrayOfByte));
    }
    return null;
  }
  
  private static byte[] fromHexString(String paramString)
  {
    int j = paramString.length();
    byte[] arrayOfByte = new byte[j / 2];
    int i = 0;
    while (i < j)
    {
      arrayOfByte[(i / 2)] = ((byte)((Character.digit(paramString.charAt(i), 16) << 4) + Character.digit(paramString.charAt(i + 1), 16)));
      i += 2;
    }
    return arrayOfByte;
  }
  
  private static int getPayloadLength(byte[] paramArrayOfByte)
  {
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      int j = paramArrayOfByte[i];
      if (j == 0) {
        return i;
      }
      if (j < 0) {
        return paramArrayOfByte.length;
      }
      i += 1 + j;
    }
    return paramArrayOfByte.length;
  }
  
  private static boolean isAdvPacketBeacon(byte[] paramArrayOfByte, int paramInt)
  {
    int i = paramInt + 1;
    if (isArrayContained(paramArrayOfByte, i, IBEACON_PREFIX)) {
      return true;
    }
    if (isArrayContained(paramArrayOfByte, i, EDDYSTONE_PREFIX)) {
      return true;
    }
    if (isArrayContained(paramArrayOfByte, paramInt, GRAVITY_PREFIX)) {
      return true;
    }
    return isAltBeacon(paramArrayOfByte, paramInt);
  }
  
  private static boolean isAltBeacon(byte[] paramArrayOfByte, int paramInt)
  {
    int k = paramInt + 5;
    boolean bool2 = false;
    if (k < paramArrayOfByte.length)
    {
      int i = paramArrayOfByte[paramInt];
      int j = paramArrayOfByte[(paramInt + 1)];
      paramInt = paramArrayOfByte[(paramInt + 4)];
      k = paramArrayOfByte[k];
      boolean bool1 = bool2;
      if (i == 27)
      {
        bool1 = bool2;
        if (j == -1)
        {
          bool1 = bool2;
          if (paramInt == -66)
          {
            bool1 = bool2;
            if (k == -84) {
              bool1 = true;
            }
          }
        }
      }
      return bool1;
    }
    return false;
  }
  
  private static boolean isArrayContained(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2)
  {
    int j = paramArrayOfByte2.length;
    if (paramInt + j > paramArrayOfByte1.length) {
      return false;
    }
    int i = 0;
    while (i < j)
    {
      if (paramArrayOfByte1[(paramInt + i)] != paramArrayOfByte2[i]) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private static boolean isBeacon(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return false;
    }
    int k = paramArrayOfByte.length;
    int j;
    for (int i = 0; i < k; i = j)
    {
      j = paramArrayOfByte[i];
      if (j <= 0) {
        return false;
      }
      j = j + 1 + i;
      if (j > k) {
        return false;
      }
      if (isAdvPacketBeacon(paramArrayOfByte, i)) {
        return true;
      }
    }
    return false;
  }
  
  private static void logException(String paramString, Exception paramException)
  {
    if (FacebookSdk.isDebugEnabled()) {
      Log.e("BleScannerImpl", paramString, paramException);
    }
  }
  
  private static BluetoothScanResult newBluetoothScanResult(ScanResult paramScanResult)
  {
    ScanRecord localScanRecord = paramScanResult.getScanRecord();
    if (isBeacon(localScanRecord.getBytes())) {
      return new BluetoothScanResult(formatPayload(localScanRecord.getBytes()), paramScanResult.getRssi(), paramScanResult.getTimestampNanos());
    }
    return null;
  }
  
  private static String toHexString(byte[] paramArrayOfByte, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i;
    if (paramInt >= 0)
    {
      i = paramInt;
      if (paramInt <= paramArrayOfByte.length) {}
    }
    else
    {
      i = paramArrayOfByte.length;
    }
    paramInt = 0;
    while (paramInt < i)
    {
      localStringBuffer.append(String.format("%02x", new Object[] { Byte.valueOf(paramArrayOfByte[paramInt]) }));
      paramInt += 1;
    }
    return localStringBuffer.toString();
  }
  
  private void waitForMainLooper(long paramLong)
  {
    try
    {
      synchronized (new Object())
      {
        new Handler(Looper.getMainLooper()).post(new Runnable()
        {
          public void run()
          {
            try
            {
              synchronized (localObject1)
              {
                localObject1.notify();
                return;
              }
              return;
            }
            catch (Exception localException)
            {
              BleScannerImpl.logException("Exception waiting for main looper", localException);
            }
          }
        });
        ???.wait(paramLong);
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      logException("Exception waiting for main looper", localException);
    }
  }
  
  public int getErrorCode()
  {
    try
    {
      int i = errorCode;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  /* Error */
  public List<BluetoothScanResult> getScanResults()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 69	com/facebook/places/internal/BleScannerImpl:scanResults	Ljava/util/List;
    //   6: astore_3
    //   7: aload_3
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield 73	com/facebook/places/internal/BleScannerImpl:params	Lcom/facebook/places/internal/LocationPackageRequestParams;
    //   13: invokevirtual 226	com/facebook/places/internal/LocationPackageRequestParams:getBluetoothMaxScanResults	()I
    //   16: istore_1
    //   17: aload_0
    //   18: getfield 69	com/facebook/places/internal/BleScannerImpl:scanResults	Ljava/util/List;
    //   21: invokeinterface 231 1 0
    //   26: iload_1
    //   27: if_icmple +52 -> 79
    //   30: new 66	java/util/ArrayList
    //   33: dup
    //   34: iload_1
    //   35: invokespecial 234	java/util/ArrayList:<init>	(I)V
    //   38: astore_2
    //   39: new 10	com/facebook/places/internal/BleScannerImpl$2
    //   42: dup
    //   43: aload_0
    //   44: invokespecial 237	com/facebook/places/internal/BleScannerImpl$2:<init>	(Lcom/facebook/places/internal/BleScannerImpl;)V
    //   47: astore 4
    //   49: aload_0
    //   50: getfield 69	com/facebook/places/internal/BleScannerImpl:scanResults	Ljava/util/List;
    //   53: aload 4
    //   55: invokestatic 243	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   58: aload_2
    //   59: aload_0
    //   60: getfield 69	com/facebook/places/internal/BleScannerImpl:scanResults	Ljava/util/List;
    //   63: iconst_0
    //   64: iload_1
    //   65: invokeinterface 247 3 0
    //   70: invokeinterface 251 2 0
    //   75: pop
    //   76: goto +31 -> 107
    //   79: new 66	java/util/ArrayList
    //   82: dup
    //   83: aload_0
    //   84: getfield 69	com/facebook/places/internal/BleScannerImpl:scanResults	Ljava/util/List;
    //   87: invokeinterface 231 1 0
    //   92: invokespecial 234	java/util/ArrayList:<init>	(I)V
    //   95: astore_2
    //   96: aload_2
    //   97: aload_0
    //   98: getfield 69	com/facebook/places/internal/BleScannerImpl:scanResults	Ljava/util/List;
    //   101: invokeinterface 251 2 0
    //   106: pop
    //   107: aload_3
    //   108: monitorexit
    //   109: aload_0
    //   110: monitorexit
    //   111: aload_2
    //   112: areturn
    //   113: astore_2
    //   114: aload_3
    //   115: monitorexit
    //   116: aload_2
    //   117: athrow
    //   118: astore_2
    //   119: aload_0
    //   120: monitorexit
    //   121: aload_2
    //   122: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	123	0	this	BleScannerImpl
    //   16	49	1	i	int
    //   38	74	2	localArrayList	ArrayList
    //   113	4	2	localObject1	Object
    //   118	4	2	localObject2	Object
    //   47	7	4	local2	2
    // Exception table:
    //   from	to	target	type
    //   9	76	113	finally
    //   79	107	113	finally
    //   107	109	113	finally
    //   114	116	113	finally
    //   2	9	118	finally
    //   116	118	118	finally
  }
  
  public void initAndCheckEligibility()
    throws ScannerException
  {
    try
    {
      if (Build.VERSION.SDK_INT < 21) {
        throw new ScannerException(ScannerException.Type.NOT_SUPPORTED);
      }
      if (!Validate.hasBluetoothPermission(context)) {
        throw new ScannerException(ScannerException.Type.PERMISSION_DENIED);
      }
      if (!Validate.hasLocationPermission(context)) {
        throw new ScannerException(ScannerException.Type.PERMISSION_DENIED);
      }
      bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
      if ((bluetoothAdapter != null) && (bluetoothAdapter.isEnabled()))
      {
        bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
        if (bluetoothLeScanner == null) {
          throw new ScannerException(ScannerException.Type.UNKNOWN_ERROR);
        }
        return;
      }
      throw new ScannerException(ScannerException.Type.DISABLED);
    }
    finally {}
  }
  
  /* Error */
  public void startScanning()
    throws ScannerException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 309	com/facebook/places/internal/BleScannerImpl:isScanInProgress	Z
    //   6: ifeq +14 -> 20
    //   9: new 256	com/facebook/places/internal/ScannerException
    //   12: dup
    //   13: getstatic 312	com/facebook/places/internal/ScannerException$Type:SCAN_ALREADY_IN_PROGRESS	Lcom/facebook/places/internal/ScannerException$Type;
    //   16: invokespecial 270	com/facebook/places/internal/ScannerException:<init>	(Lcom/facebook/places/internal/ScannerException$Type;)V
    //   19: athrow
    //   20: aload_0
    //   21: new 12	com/facebook/places/internal/BleScannerImpl$ScanCallBackImpl
    //   24: dup
    //   25: aload_0
    //   26: aconst_null
    //   27: invokespecial 315	com/facebook/places/internal/BleScannerImpl$ScanCallBackImpl:<init>	(Lcom/facebook/places/internal/BleScannerImpl;Lcom/facebook/places/internal/BleScannerImpl$1;)V
    //   30: putfield 317	com/facebook/places/internal/BleScannerImpl:scanCallBack	Lcom/facebook/places/internal/BleScannerImpl$ScanCallBackImpl;
    //   33: aload_0
    //   34: iconst_1
    //   35: putfield 309	com/facebook/places/internal/BleScannerImpl:isScanInProgress	Z
    //   38: aload_0
    //   39: iconst_0
    //   40: putfield 82	com/facebook/places/internal/BleScannerImpl:errorCode	I
    //   43: aload_0
    //   44: getfield 69	com/facebook/places/internal/BleScannerImpl:scanResults	Ljava/util/List;
    //   47: astore_1
    //   48: aload_1
    //   49: monitorenter
    //   50: aload_0
    //   51: getfield 69	com/facebook/places/internal/BleScannerImpl:scanResults	Ljava/util/List;
    //   54: invokeinterface 320 1 0
    //   59: aload_1
    //   60: monitorexit
    //   61: aload_0
    //   62: getfield 299	com/facebook/places/internal/BleScannerImpl:bluetoothLeScanner	Landroid/bluetooth/le/BluetoothLeScanner;
    //   65: ifnonnull +14 -> 79
    //   68: new 256	com/facebook/places/internal/ScannerException
    //   71: dup
    //   72: getstatic 302	com/facebook/places/internal/ScannerException$Type:UNKNOWN_ERROR	Lcom/facebook/places/internal/ScannerException$Type;
    //   75: invokespecial 270	com/facebook/places/internal/ScannerException:<init>	(Lcom/facebook/places/internal/ScannerException$Type;)V
    //   78: athrow
    //   79: new 322	android/bluetooth/le/ScanSettings$Builder
    //   82: dup
    //   83: invokespecial 323	android/bluetooth/le/ScanSettings$Builder:<init>	()V
    //   86: astore_1
    //   87: aload_1
    //   88: iconst_2
    //   89: invokevirtual 327	android/bluetooth/le/ScanSettings$Builder:setScanMode	(I)Landroid/bluetooth/le/ScanSettings$Builder;
    //   92: pop
    //   93: aload_1
    //   94: lconst_0
    //   95: invokevirtual 331	android/bluetooth/le/ScanSettings$Builder:setReportDelay	(J)Landroid/bluetooth/le/ScanSettings$Builder;
    //   98: pop
    //   99: aload_0
    //   100: getfield 299	com/facebook/places/internal/BleScannerImpl:bluetoothLeScanner	Landroid/bluetooth/le/BluetoothLeScanner;
    //   103: aconst_null
    //   104: aload_1
    //   105: invokevirtual 335	android/bluetooth/le/ScanSettings$Builder:build	()Landroid/bluetooth/le/ScanSettings;
    //   108: aload_0
    //   109: getfield 317	com/facebook/places/internal/BleScannerImpl:scanCallBack	Lcom/facebook/places/internal/BleScannerImpl$ScanCallBackImpl;
    //   112: invokevirtual 341	android/bluetooth/le/BluetoothLeScanner:startScan	(Ljava/util/List;Landroid/bluetooth/le/ScanSettings;Landroid/bluetooth/le/ScanCallback;)V
    //   115: aload_0
    //   116: iconst_1
    //   117: putfield 309	com/facebook/places/internal/BleScannerImpl:isScanInProgress	Z
    //   120: aload_0
    //   121: monitorexit
    //   122: return
    //   123: new 256	com/facebook/places/internal/ScannerException
    //   126: dup
    //   127: getstatic 302	com/facebook/places/internal/ScannerException$Type:UNKNOWN_ERROR	Lcom/facebook/places/internal/ScannerException$Type;
    //   130: invokespecial 270	com/facebook/places/internal/ScannerException:<init>	(Lcom/facebook/places/internal/ScannerException$Type;)V
    //   133: athrow
    //   134: astore_2
    //   135: aload_1
    //   136: monitorexit
    //   137: aload_2
    //   138: athrow
    //   139: astore_1
    //   140: aload_0
    //   141: monitorexit
    //   142: aload_1
    //   143: athrow
    //   144: astore_1
    //   145: goto -22 -> 123
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	148	0	this	BleScannerImpl
    //   139	4	1	localObject2	Object
    //   144	1	1	localException	Exception
    //   134	4	2	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   50	61	134	finally
    //   135	137	134	finally
    //   2	20	139	finally
    //   20	50	139	finally
    //   61	79	139	finally
    //   79	120	139	finally
    //   123	134	139	finally
    //   137	139	139	finally
    //   79	120	144	java/lang/Exception
  }
  
  public void stopScanning()
  {
    try
    {
      bluetoothLeScanner.flushPendingScanResults(scanCallBack);
      bluetoothLeScanner.stopScan(scanCallBack);
      waitForMainLooper(params.getBluetoothFlushResultsTimeoutMs());
      isScanInProgress = false;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  private class ScanCallBackImpl
    extends ScanCallback
  {
    private ScanCallBackImpl() {}
    
    public void onBatchScanResults(List<ScanResult> paramList)
    {
      super.onBatchScanResults(paramList);
      try
      {
        synchronized (scanResults)
        {
          paramList = paramList.iterator();
          while (paramList.hasNext())
          {
            BluetoothScanResult localBluetoothScanResult = BleScannerImpl.newBluetoothScanResult((ScanResult)paramList.next());
            if (localBluetoothScanResult != null) {
              scanResults.add(localBluetoothScanResult);
            }
          }
          return;
        }
        return;
      }
      catch (Exception paramList)
      {
        BleScannerImpl.logException("Exception in ble scan callback", paramList);
      }
    }
    
    public void onScanFailed(int paramInt)
    {
      super.onScanFailed(paramInt);
      BleScannerImpl.access$202(BleScannerImpl.this, paramInt);
    }
    
    public void onScanResult(int paramInt, ScanResult paramScanResult)
    {
      super.onScanResult(paramInt, paramScanResult);
      try
      {
        synchronized (scanResults)
        {
          paramScanResult = BleScannerImpl.newBluetoothScanResult(paramScanResult);
          if (paramScanResult != null) {
            scanResults.add(paramScanResult);
          }
          return;
        }
        return;
      }
      catch (Exception paramScanResult)
      {
        BleScannerImpl.logException("Exception in ble scan callback", paramScanResult);
      }
    }
  }
}

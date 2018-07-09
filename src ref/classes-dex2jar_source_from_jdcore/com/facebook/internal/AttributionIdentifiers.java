package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.facebook.FacebookException;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class AttributionIdentifiers
{
  private static final String ANDROID_ID_COLUMN_NAME = "androidid";
  private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
  private static final String ATTRIBUTION_ID_CONTENT_PROVIDER = "com.facebook.katana.provider.AttributionIdProvider";
  private static final String ATTRIBUTION_ID_CONTENT_PROVIDER_WAKIZASHI = "com.facebook.wakizashi.provider.AttributionIdProvider";
  private static final int CONNECTION_RESULT_SUCCESS = 0;
  private static final long IDENTIFIER_REFRESH_INTERVAL_MILLIS = 3600000L;
  private static final String LIMIT_TRACKING_COLUMN_NAME = "limit_tracking";
  private static final String TAG = AttributionIdentifiers.class.getCanonicalName();
  private static AttributionIdentifiers recentlyFetchedIdentifiers;
  private String androidAdvertiserId;
  private String androidInstallerPackage;
  private String attributionId;
  private long fetchTime;
  private boolean limitTracking;
  
  public AttributionIdentifiers() {}
  
  private static AttributionIdentifiers cacheAndReturnIdentifiers(AttributionIdentifiers paramAttributionIdentifiers)
  {
    fetchTime = System.currentTimeMillis();
    recentlyFetchedIdentifiers = paramAttributionIdentifiers;
    return paramAttributionIdentifiers;
  }
  
  private static AttributionIdentifiers getAndroidId(Context paramContext)
  {
    AttributionIdentifiers localAttributionIdentifiers = getAndroidIdViaReflection(paramContext);
    Object localObject = localAttributionIdentifiers;
    if (localAttributionIdentifiers == null)
    {
      paramContext = getAndroidIdViaService(paramContext);
      localObject = paramContext;
      if (paramContext == null) {
        localObject = new AttributionIdentifiers();
      }
    }
    return localObject;
  }
  
  private static AttributionIdentifiers getAndroidIdViaReflection(Context paramContext)
  {
    try
    {
      if (Looper.myLooper() == Looper.getMainLooper()) {
        throw new FacebookException("getAndroidId cannot be called on the main thread.");
      }
      Object localObject = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", new Class[] { Context.class });
      if (localObject == null) {
        return null;
      }
      localObject = Utility.invokeMethodQuietly(null, (Method)localObject, new Object[] { paramContext });
      if ((localObject instanceof Integer))
      {
        if (((Integer)localObject).intValue() != 0) {
          return null;
        }
        localObject = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", new Class[] { Context.class });
        if (localObject == null) {
          return null;
        }
        paramContext = Utility.invokeMethodQuietly(null, (Method)localObject, new Object[] { paramContext });
        if (paramContext == null) {
          return null;
        }
        localObject = Utility.getMethodQuietly(paramContext.getClass(), "getId", new Class[0]);
        Method localMethod = Utility.getMethodQuietly(paramContext.getClass(), "isLimitAdTrackingEnabled", new Class[0]);
        if (localObject != null)
        {
          if (localMethod == null) {
            return null;
          }
          AttributionIdentifiers localAttributionIdentifiers = new AttributionIdentifiers();
          androidAdvertiserId = ((String)Utility.invokeMethodQuietly(paramContext, (Method)localObject, new Object[0]));
          limitTracking = ((Boolean)Utility.invokeMethodQuietly(paramContext, localMethod, new Object[0])).booleanValue();
          return localAttributionIdentifiers;
        }
        return null;
      }
      return null;
    }
    catch (Exception paramContext)
    {
      Utility.logd("android_id", paramContext);
    }
    return null;
  }
  
  /* Error */
  private static AttributionIdentifiers getAndroidIdViaService(Context paramContext)
  {
    // Byte code:
    //   0: new 11	com/facebook/internal/AttributionIdentifiers$GoogleAdServiceConnection
    //   3: dup
    //   4: aconst_null
    //   5: invokespecial 155	com/facebook/internal/AttributionIdentifiers$GoogleAdServiceConnection:<init>	(Lcom/facebook/internal/AttributionIdentifiers$1;)V
    //   8: astore_1
    //   9: new 157	android/content/Intent
    //   12: dup
    //   13: ldc -97
    //   15: invokespecial 160	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   18: astore_2
    //   19: aload_2
    //   20: ldc -94
    //   22: invokevirtual 166	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   25: pop
    //   26: aload_0
    //   27: aload_2
    //   28: aload_1
    //   29: iconst_1
    //   30: invokevirtual 170	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   33: ifeq +71 -> 104
    //   36: new 8	com/facebook/internal/AttributionIdentifiers$GoogleAdInfo
    //   39: dup
    //   40: aload_1
    //   41: invokevirtual 174	com/facebook/internal/AttributionIdentifiers$GoogleAdServiceConnection:getBinder	()Landroid/os/IBinder;
    //   44: invokespecial 177	com/facebook/internal/AttributionIdentifiers$GoogleAdInfo:<init>	(Landroid/os/IBinder;)V
    //   47: astore_2
    //   48: new 2	com/facebook/internal/AttributionIdentifiers
    //   51: dup
    //   52: invokespecial 79	com/facebook/internal/AttributionIdentifiers:<init>	()V
    //   55: astore_3
    //   56: aload_3
    //   57: aload_2
    //   58: invokevirtual 180	com/facebook/internal/AttributionIdentifiers$GoogleAdInfo:getAdvertiserId	()Ljava/lang/String;
    //   61: putfield 138	com/facebook/internal/AttributionIdentifiers:androidAdvertiserId	Ljava/lang/String;
    //   64: aload_3
    //   65: aload_2
    //   66: invokevirtual 183	com/facebook/internal/AttributionIdentifiers$GoogleAdInfo:isTrackingLimited	()Z
    //   69: putfield 146	com/facebook/internal/AttributionIdentifiers:limitTracking	Z
    //   72: aload_0
    //   73: aload_1
    //   74: invokevirtual 187	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   77: aload_3
    //   78: areturn
    //   79: astore_2
    //   80: goto +17 -> 97
    //   83: astore_2
    //   84: ldc -108
    //   86: aload_2
    //   87: invokestatic 152	com/facebook/internal/Utility:logd	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   90: aload_0
    //   91: aload_1
    //   92: invokevirtual 187	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   95: aconst_null
    //   96: areturn
    //   97: aload_0
    //   98: aload_1
    //   99: invokevirtual 187	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   102: aload_2
    //   103: athrow
    //   104: aconst_null
    //   105: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	106	0	paramContext	Context
    //   8	91	1	localGoogleAdServiceConnection	GoogleAdServiceConnection
    //   18	48	2	localObject1	Object
    //   79	1	2	localObject2	Object
    //   83	20	2	localException	Exception
    //   55	23	3	localAttributionIdentifiers	AttributionIdentifiers
    // Exception table:
    //   from	to	target	type
    //   36	72	79	finally
    //   84	90	79	finally
    //   36	72	83	java/lang/Exception
  }
  
  /* Error */
  public static AttributionIdentifiers getAttributionIdentifiers(Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 87	android/os/Looper:myLooper	()Landroid/os/Looper;
    //   3: invokestatic 90	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   6: if_acmpne +12 -> 18
    //   9: getstatic 54	com/facebook/internal/AttributionIdentifiers:TAG	Ljava/lang/String;
    //   12: ldc -66
    //   14: invokestatic 196	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   17: pop
    //   18: getstatic 70	com/facebook/internal/AttributionIdentifiers:recentlyFetchedIdentifiers	Lcom/facebook/internal/AttributionIdentifiers;
    //   21: ifnull +24 -> 45
    //   24: invokestatic 66	java/lang/System:currentTimeMillis	()J
    //   27: getstatic 70	com/facebook/internal/AttributionIdentifiers:recentlyFetchedIdentifiers	Lcom/facebook/internal/AttributionIdentifiers;
    //   30: getfield 68	com/facebook/internal/AttributionIdentifiers:fetchTime	J
    //   33: lsub
    //   34: ldc2_w 31
    //   37: lcmp
    //   38: ifge +7 -> 45
    //   41: getstatic 70	com/facebook/internal/AttributionIdentifiers:recentlyFetchedIdentifiers	Lcom/facebook/internal/AttributionIdentifiers;
    //   44: areturn
    //   45: aload_0
    //   46: invokestatic 198	com/facebook/internal/AttributionIdentifiers:getAndroidId	(Landroid/content/Context;)Lcom/facebook/internal/AttributionIdentifiers;
    //   49: astore 6
    //   51: aconst_null
    //   52: astore 5
    //   54: aload_0
    //   55: invokevirtual 202	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   58: ldc 22
    //   60: iconst_0
    //   61: invokevirtual 208	android/content/pm/PackageManager:resolveContentProvider	(Ljava/lang/String;I)Landroid/content/pm/ProviderInfo;
    //   64: ifnull +13 -> 77
    //   67: ldc -46
    //   69: invokestatic 216	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   72: astore 4
    //   74: goto +315 -> 389
    //   77: aload_0
    //   78: invokevirtual 202	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   81: ldc 25
    //   83: iconst_0
    //   84: invokevirtual 208	android/content/pm/PackageManager:resolveContentProvider	(Ljava/lang/String;I)Landroid/content/pm/ProviderInfo;
    //   87: ifnull +305 -> 392
    //   90: ldc -38
    //   92: invokestatic 216	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   95: astore 4
    //   97: goto +292 -> 389
    //   100: aload_0
    //   101: invokestatic 222	com/facebook/internal/AttributionIdentifiers:getInstallerPackageName	(Landroid/content/Context;)Ljava/lang/String;
    //   104: astore 7
    //   106: aload 7
    //   108: ifnull +10 -> 118
    //   111: aload 6
    //   113: aload 7
    //   115: putfield 224	com/facebook/internal/AttributionIdentifiers:androidInstallerPackage	Ljava/lang/String;
    //   118: aload 4
    //   120: ifnonnull +9 -> 129
    //   123: aload 6
    //   125: invokestatic 226	com/facebook/internal/AttributionIdentifiers:cacheAndReturnIdentifiers	(Lcom/facebook/internal/AttributionIdentifiers;)Lcom/facebook/internal/AttributionIdentifiers;
    //   128: areturn
    //   129: aload_0
    //   130: invokevirtual 230	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   133: aload 4
    //   135: iconst_3
    //   136: anewarray 136	java/lang/String
    //   139: dup
    //   140: iconst_0
    //   141: ldc 19
    //   143: aastore
    //   144: dup
    //   145: iconst_1
    //   146: ldc 16
    //   148: aastore
    //   149: dup
    //   150: iconst_2
    //   151: ldc 35
    //   153: aastore
    //   154: aconst_null
    //   155: aconst_null
    //   156: aconst_null
    //   157: invokevirtual 236	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   160: astore_0
    //   161: aload_0
    //   162: ifnull +123 -> 285
    //   165: aload_0
    //   166: invokeinterface 241 1 0
    //   171: ifne +6 -> 177
    //   174: goto +111 -> 285
    //   177: aload_0
    //   178: ldc 19
    //   180: invokeinterface 245 2 0
    //   185: istore_1
    //   186: aload_0
    //   187: ldc 16
    //   189: invokeinterface 245 2 0
    //   194: istore_2
    //   195: aload_0
    //   196: ldc 35
    //   198: invokeinterface 245 2 0
    //   203: istore_3
    //   204: aload 6
    //   206: aload_0
    //   207: iload_1
    //   208: invokeinterface 249 2 0
    //   213: putfield 251	com/facebook/internal/AttributionIdentifiers:attributionId	Ljava/lang/String;
    //   216: iload_2
    //   217: ifle +42 -> 259
    //   220: iload_3
    //   221: ifle +38 -> 259
    //   224: aload 6
    //   226: invokevirtual 254	com/facebook/internal/AttributionIdentifiers:getAndroidAdvertiserId	()Ljava/lang/String;
    //   229: ifnonnull +30 -> 259
    //   232: aload 6
    //   234: aload_0
    //   235: iload_2
    //   236: invokeinterface 249 2 0
    //   241: putfield 138	com/facebook/internal/AttributionIdentifiers:androidAdvertiserId	Ljava/lang/String;
    //   244: aload 6
    //   246: aload_0
    //   247: iload_3
    //   248: invokeinterface 249 2 0
    //   253: invokestatic 258	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   256: putfield 146	com/facebook/internal/AttributionIdentifiers:limitTracking	Z
    //   259: aload_0
    //   260: ifnull +9 -> 269
    //   263: aload_0
    //   264: invokeinterface 261 1 0
    //   269: aload 6
    //   271: invokestatic 226	com/facebook/internal/AttributionIdentifiers:cacheAndReturnIdentifiers	(Lcom/facebook/internal/AttributionIdentifiers;)Lcom/facebook/internal/AttributionIdentifiers;
    //   274: areturn
    //   275: astore 4
    //   277: goto +99 -> 376
    //   280: astore 4
    //   282: goto +35 -> 317
    //   285: aload 6
    //   287: invokestatic 226	com/facebook/internal/AttributionIdentifiers:cacheAndReturnIdentifiers	(Lcom/facebook/internal/AttributionIdentifiers;)Lcom/facebook/internal/AttributionIdentifiers;
    //   290: astore 4
    //   292: aload_0
    //   293: ifnull +9 -> 302
    //   296: aload_0
    //   297: invokeinterface 261 1 0
    //   302: aload 4
    //   304: areturn
    //   305: astore 4
    //   307: aload 5
    //   309: astore_0
    //   310: goto +66 -> 376
    //   313: astore 4
    //   315: aconst_null
    //   316: astore_0
    //   317: getstatic 54	com/facebook/internal/AttributionIdentifiers:TAG	Ljava/lang/String;
    //   320: astore 5
    //   322: new 263	java/lang/StringBuilder
    //   325: dup
    //   326: invokespecial 264	java/lang/StringBuilder:<init>	()V
    //   329: astore 6
    //   331: aload 6
    //   333: ldc_w 266
    //   336: invokevirtual 270	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   339: pop
    //   340: aload 6
    //   342: aload 4
    //   344: invokevirtual 273	java/lang/Exception:toString	()Ljava/lang/String;
    //   347: invokevirtual 270	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   350: pop
    //   351: aload 5
    //   353: aload 6
    //   355: invokevirtual 274	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   358: invokestatic 277	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   361: pop
    //   362: aload_0
    //   363: ifnull +9 -> 372
    //   366: aload_0
    //   367: invokeinterface 261 1 0
    //   372: aconst_null
    //   373: areturn
    //   374: astore 4
    //   376: aload_0
    //   377: ifnull +9 -> 386
    //   380: aload_0
    //   381: invokeinterface 261 1 0
    //   386: aload 4
    //   388: athrow
    //   389: goto -289 -> 100
    //   392: aconst_null
    //   393: astore 4
    //   395: goto -295 -> 100
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	398	0	paramContext	Context
    //   185	23	1	i	int
    //   194	42	2	j	int
    //   203	45	3	k	int
    //   72	62	4	localUri	android.net.Uri
    //   275	1	4	localObject1	Object
    //   280	1	4	localException1	Exception
    //   290	13	4	localAttributionIdentifiers	AttributionIdentifiers
    //   305	1	4	localObject2	Object
    //   313	30	4	localException2	Exception
    //   374	13	4	localObject3	Object
    //   393	1	4	localObject4	Object
    //   52	300	5	str1	String
    //   49	305	6	localObject5	Object
    //   104	10	7	str2	String
    // Exception table:
    //   from	to	target	type
    //   165	174	275	finally
    //   177	216	275	finally
    //   224	259	275	finally
    //   285	292	275	finally
    //   165	174	280	java/lang/Exception
    //   177	216	280	java/lang/Exception
    //   224	259	280	java/lang/Exception
    //   285	292	280	java/lang/Exception
    //   54	74	305	finally
    //   77	97	305	finally
    //   100	106	305	finally
    //   111	118	305	finally
    //   123	129	305	finally
    //   129	161	305	finally
    //   54	74	313	java/lang/Exception
    //   77	97	313	java/lang/Exception
    //   100	106	313	java/lang/Exception
    //   111	118	313	java/lang/Exception
    //   123	129	313	java/lang/Exception
    //   129	161	313	java/lang/Exception
    //   317	362	374	finally
  }
  
  @Nullable
  private static String getInstallerPackageName(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    if (localPackageManager != null) {
      return localPackageManager.getInstallerPackageName(paramContext.getPackageName());
    }
    return null;
  }
  
  public String getAndroidAdvertiserId()
  {
    return androidAdvertiserId;
  }
  
  public String getAndroidInstallerPackage()
  {
    return androidInstallerPackage;
  }
  
  public String getAttributionId()
  {
    return attributionId;
  }
  
  public boolean isTrackingLimited()
  {
    return limitTracking;
  }
  
  private static final class GoogleAdInfo
    implements IInterface
  {
    private static final int FIRST_TRANSACTION_CODE = 1;
    private static final int SECOND_TRANSACTION_CODE = 2;
    private IBinder binder;
    
    GoogleAdInfo(IBinder paramIBinder)
    {
      binder = paramIBinder;
    }
    
    public IBinder asBinder()
    {
      return binder;
    }
    
    public String getAdvertiserId()
      throws RemoteException
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        binder.transact(1, localParcel1, localParcel2, 0);
        localParcel2.readException();
        String str = localParcel2.readString();
        return str;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public boolean isTrackingLimited()
      throws RemoteException
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        boolean bool = true;
        localParcel1.writeInt(1);
        binder.transact(2, localParcel1, localParcel2, 0);
        localParcel2.readException();
        int i = localParcel2.readInt();
        if (i == 0) {
          bool = false;
        }
        return bool;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
  }
  
  private static final class GoogleAdServiceConnection
    implements ServiceConnection
  {
    private AtomicBoolean consumed = new AtomicBoolean(false);
    private final BlockingQueue<IBinder> queue = new LinkedBlockingDeque();
    
    private GoogleAdServiceConnection() {}
    
    public IBinder getBinder()
      throws InterruptedException
    {
      if (consumed.compareAndSet(true, true)) {
        throw new IllegalStateException("Binder already consumed");
      }
      return (IBinder)queue.take();
    }
    
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      if (paramIBinder != null) {}
      try
      {
        queue.put(paramIBinder);
        return;
      }
      catch (InterruptedException paramComponentName) {}
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName) {}
  }
}

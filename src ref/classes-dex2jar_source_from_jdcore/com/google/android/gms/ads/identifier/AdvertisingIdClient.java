package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzfp;
import com.google.android.gms.internal.zzfq;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@KeepForSdkWithMembers
public class AdvertisingIdClient
{
  @Nullable
  private com.google.android.gms.common.zza zza;
  @Nullable
  private zzfp zzb;
  private boolean zzc;
  private Object zzd = new Object();
  @Nullable
  private zza zze;
  private final Context zzf;
  private boolean zzg;
  private long zzh;
  
  @Hide
  public AdvertisingIdClient(Context paramContext)
  {
    this(paramContext, 30000L, false, false);
  }
  
  @Hide
  public AdvertisingIdClient(Context paramContext, long paramLong, boolean paramBoolean1, boolean paramBoolean2)
  {
    zzbq.zza(paramContext);
    Context localContext = paramContext;
    if (paramBoolean1)
    {
      localContext = paramContext.getApplicationContext();
      if (localContext == null) {
        localContext = paramContext;
      }
    }
    zzf = localContext;
    zzc = false;
    zzh = paramLong;
    zzg = paramBoolean2;
  }
  
  /* Error */
  public static Info getAdvertisingIdInfo(Context paramContext)
    throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException
  {
    // Byte code:
    //   0: new 74	com/google/android/gms/ads/identifier/zzb
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 76	com/google/android/gms/ads/identifier/zzb:<init>	(Landroid/content/Context;)V
    //   8: astore 6
    //   10: aload 6
    //   12: ldc 78
    //   14: iconst_0
    //   15: invokevirtual 81	com/google/android/gms/ads/identifier/zzb:zza	(Ljava/lang/String;Z)Z
    //   18: istore_2
    //   19: aload 6
    //   21: ldc 83
    //   23: fconst_0
    //   24: invokevirtual 86	com/google/android/gms/ads/identifier/zzb:zza	(Ljava/lang/String;F)F
    //   27: fstore_1
    //   28: aload 6
    //   30: ldc 88
    //   32: ldc 90
    //   34: invokevirtual 93	com/google/android/gms/ads/identifier/zzb:zza	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   37: astore 5
    //   39: new 2	com/google/android/gms/ads/identifier/AdvertisingIdClient
    //   42: dup
    //   43: aload_0
    //   44: ldc2_w 94
    //   47: iload_2
    //   48: aload 6
    //   50: ldc 97
    //   52: iconst_0
    //   53: invokevirtual 81	com/google/android/gms/ads/identifier/zzb:zza	(Ljava/lang/String;Z)Z
    //   56: invokespecial 34	com/google/android/gms/ads/identifier/AdvertisingIdClient:<init>	(Landroid/content/Context;JZZ)V
    //   59: astore_0
    //   60: invokestatic 103	android/os/SystemClock:elapsedRealtime	()J
    //   63: lstore_3
    //   64: aload_0
    //   65: iconst_0
    //   66: invokespecial 106	com/google/android/gms/ads/identifier/AdvertisingIdClient:zza	(Z)V
    //   69: aload_0
    //   70: invokevirtual 110	com/google/android/gms/ads/identifier/AdvertisingIdClient:getInfo	()Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
    //   73: astore 6
    //   75: aload_0
    //   76: aload 6
    //   78: iload_2
    //   79: fload_1
    //   80: invokestatic 103	android/os/SystemClock:elapsedRealtime	()J
    //   83: lload_3
    //   84: lsub
    //   85: aload 5
    //   87: aconst_null
    //   88: invokespecial 113	com/google/android/gms/ads/identifier/AdvertisingIdClient:zza	(Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;ZFJLjava/lang/String;Ljava/lang/Throwable;)Z
    //   91: pop
    //   92: aload_0
    //   93: invokevirtual 116	com/google/android/gms/ads/identifier/AdvertisingIdClient:finish	()V
    //   96: aload 6
    //   98: areturn
    //   99: astore 5
    //   101: goto +23 -> 124
    //   104: astore 6
    //   106: aload_0
    //   107: aconst_null
    //   108: iload_2
    //   109: fload_1
    //   110: ldc2_w 94
    //   113: aload 5
    //   115: aload 6
    //   117: invokespecial 113	com/google/android/gms/ads/identifier/AdvertisingIdClient:zza	(Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;ZFJLjava/lang/String;Ljava/lang/Throwable;)Z
    //   120: pop
    //   121: aload 6
    //   123: athrow
    //   124: aload_0
    //   125: invokevirtual 116	com/google/android/gms/ads/identifier/AdvertisingIdClient:finish	()V
    //   128: aload 5
    //   130: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	131	0	paramContext	Context
    //   27	83	1	f	float
    //   18	91	2	bool	boolean
    //   63	21	3	l	long
    //   37	49	5	str1	String
    //   99	30	5	str2	String
    //   8	89	6	localObject	Object
    //   104	18	6	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   60	92	99	finally
    //   106	124	99	finally
    //   60	92	104	java/lang/Throwable
  }
  
  @Hide
  public static boolean getIsAdIdFakeForDebugLogging(Context paramContext)
    throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException
  {
    zzb localZzb = new zzb(paramContext);
    paramContext = new AdvertisingIdClient(paramContext, -1L, localZzb.zza("gads:ad_id_app_context:enabled", false), localZzb.zza("com.google.android.gms.ads.identifier.service.PERSISTENT_START", false));
    try
    {
      paramContext.zza(false);
      boolean bool = paramContext.getIsAdIdFakeForDebugLogging();
      return bool;
    }
    finally
    {
      paramContext.finish();
    }
  }
  
  @Hide
  public static void setShouldSkipGmsCoreVersionCheck(boolean paramBoolean) {}
  
  private static com.google.android.gms.common.zza zza(Context paramContext, boolean paramBoolean)
    throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException
  {
    try
    {
      paramContext.getPackageManager().getPackageInfo("com.android.vending", 0);
      int i = zzf.zza().isGooglePlayServicesAvailable(paramContext);
      if ((i != 0) && (i != 2)) {
        throw new IOException("Google Play services not available");
      }
      if (paramBoolean) {
        localObject = "com.google.android.gms.ads.identifier.service.PERSISTENT_START";
      } else {
        localObject = "com.google.android.gms.ads.identifier.service.START";
      }
      com.google.android.gms.common.zza localZza = new com.google.android.gms.common.zza();
      Object localObject = new Intent((String)localObject);
      ((Intent)localObject).setPackage("com.google.android.gms");
      try
      {
        paramBoolean = com.google.android.gms.common.stats.zza.zza().zza(paramContext, (Intent)localObject, localZza, 1);
        if (paramBoolean) {
          return localZza;
        }
        throw new IOException("Connection failure");
      }
      catch (Throwable paramContext)
      {
        throw new IOException(paramContext);
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    throw new GooglePlayServicesNotAvailableException(9);
  }
  
  @Hide
  private static zzfp zza(Context paramContext, com.google.android.gms.common.zza paramZza)
    throws IOException
  {
    try
    {
      paramContext = zzfq.zza(paramZza.zza(10000L, TimeUnit.MILLISECONDS));
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      throw new IOException(paramContext);
      throw new IOException("Interrupted exception");
    }
    catch (InterruptedException paramContext)
    {
      for (;;) {}
    }
  }
  
  private final void zza()
  {
    synchronized (zzd)
    {
      if (zze != null) {
        zze.zza.countDown();
      }
    }
    try
    {
      zze.join();
      if (zzh > 0L) {
        zze = new zza(this, zzh);
      }
      return;
      localObject2 = finally;
      throw localObject2;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
  }
  
  @Hide
  private final void zza(boolean paramBoolean)
    throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException
  {
    zzbq.zzc("Calling this from your main thread can lead to deadlock");
    try
    {
      if (zzc) {
        finish();
      }
      zza = zza(zzf, zzg);
      zzb = zza(zzf, zza);
      zzc = true;
      if (paramBoolean) {
        zza();
      }
      return;
    }
    finally {}
  }
  
  private final boolean zza(Info paramInfo, boolean paramBoolean, float paramFloat, long paramLong, String paramString, Throwable paramThrowable)
  {
    if (Math.random() > paramFloat) {
      return false;
    }
    HashMap localHashMap = new HashMap();
    String str;
    if (paramBoolean) {
      str = "1";
    } else {
      str = "0";
    }
    localHashMap.put("app_context", str);
    if (paramInfo != null)
    {
      if (paramInfo.isLimitAdTrackingEnabled()) {
        str = "1";
      } else {
        str = "0";
      }
      localHashMap.put("limit_ad_tracking", str);
    }
    if ((paramInfo != null) && (paramInfo.getId() != null)) {
      localHashMap.put("ad_id_size", Integer.toString(paramInfo.getId().length()));
    }
    if (paramThrowable != null) {
      localHashMap.put("error", paramThrowable.getClass().getName());
    }
    if ((paramString != null) && (!paramString.isEmpty())) {
      localHashMap.put("experiment_id", paramString);
    }
    localHashMap.put("tag", "AdvertisingIdClient");
    localHashMap.put("time_spent", Long.toString(paramLong));
    new zza(this, localHashMap).start();
    return true;
  }
  
  @Hide
  protected void finalize()
    throws Throwable
  {
    finish();
    super.finalize();
  }
  
  @Hide
  public void finish()
  {
    zzbq.zzc("Calling this from your main thread can lead to deadlock");
    try
    {
      if (zzf != null)
      {
        com.google.android.gms.common.zza localZza = zza;
        if (localZza != null)
        {
          try
          {
            if (zzc)
            {
              com.google.android.gms.common.stats.zza.zza();
              zzf.unbindService(zza);
            }
          }
          catch (Throwable localThrowable)
          {
            Log.i("AdvertisingIdClient", "AdvertisingIdClient unbindService failed.", localThrowable);
          }
          zzc = false;
          zzb = null;
          zza = null;
          return;
        }
      }
      return;
    }
    finally {}
  }
  
  /* Error */
  @Hide
  public Info getInfo()
    throws IOException
  {
    // Byte code:
    //   0: ldc -33
    //   2: invokestatic 225	com/google/android/gms/common/internal/zzbq:zzc	(Ljava/lang/String;)V
    //   5: aload_0
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 56	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzc	Z
    //   11: ifne +84 -> 95
    //   14: aload_0
    //   15: getfield 41	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzd	Ljava/lang/Object;
    //   18: astore_1
    //   19: aload_1
    //   20: monitorenter
    //   21: aload_0
    //   22: getfield 207	com/google/android/gms/ads/identifier/AdvertisingIdClient:zze	Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
    //   25: ifnull +54 -> 79
    //   28: aload_0
    //   29: getfield 207	com/google/android/gms/ads/identifier/AdvertisingIdClient:zze	Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
    //   32: getfield 335	com/google/android/gms/ads/identifier/AdvertisingIdClient$zza:zzb	Z
    //   35: ifne +6 -> 41
    //   38: goto +41 -> 79
    //   41: aload_1
    //   42: monitorexit
    //   43: aload_0
    //   44: iconst_0
    //   45: invokespecial 106	com/google/android/gms/ads/identifier/AdvertisingIdClient:zza	(Z)V
    //   48: aload_0
    //   49: getfield 56	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzc	Z
    //   52: ifne +43 -> 95
    //   55: new 64	java/io/IOException
    //   58: dup
    //   59: ldc_w 337
    //   62: invokespecial 154	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   65: athrow
    //   66: astore_1
    //   67: new 64	java/io/IOException
    //   70: dup
    //   71: ldc_w 337
    //   74: aload_1
    //   75: invokespecial 340	java/io/IOException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   78: athrow
    //   79: new 64	java/io/IOException
    //   82: dup
    //   83: ldc_w 342
    //   86: invokespecial 154	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   89: athrow
    //   90: astore_2
    //   91: aload_1
    //   92: monitorexit
    //   93: aload_2
    //   94: athrow
    //   95: aload_0
    //   96: getfield 229	com/google/android/gms/ads/identifier/AdvertisingIdClient:zza	Lcom/google/android/gms/common/zza;
    //   99: invokestatic 46	com/google/android/gms/common/internal/zzbq:zza	(Ljava/lang/Object;)Ljava/lang/Object;
    //   102: pop
    //   103: aload_0
    //   104: getfield 233	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzb	Lcom/google/android/gms/internal/zzfp;
    //   107: invokestatic 46	com/google/android/gms/common/internal/zzbq:zza	(Ljava/lang/Object;)Ljava/lang/Object;
    //   110: pop
    //   111: new 6	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info
    //   114: dup
    //   115: aload_0
    //   116: getfield 233	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzb	Lcom/google/android/gms/internal/zzfp;
    //   119: invokeinterface 346 1 0
    //   124: aload_0
    //   125: getfield 233	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzb	Lcom/google/android/gms/internal/zzfp;
    //   128: iconst_1
    //   129: invokeinterface 349 2 0
    //   134: invokespecial 352	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:<init>	(Ljava/lang/String;Z)V
    //   137: astore_1
    //   138: aload_0
    //   139: monitorexit
    //   140: aload_0
    //   141: invokespecial 235	com/google/android/gms/ads/identifier/AdvertisingIdClient:zza	()V
    //   144: aload_1
    //   145: areturn
    //   146: astore_1
    //   147: ldc_w 299
    //   150: ldc_w 354
    //   153: aload_1
    //   154: invokestatic 329	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   157: pop
    //   158: new 64	java/io/IOException
    //   161: dup
    //   162: ldc_w 356
    //   165: invokespecial 154	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   168: athrow
    //   169: astore_1
    //   170: aload_0
    //   171: monitorexit
    //   172: aload_1
    //   173: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	174	0	this	AdvertisingIdClient
    //   66	26	1	localException	Exception
    //   137	8	1	localInfo	Info
    //   146	8	1	localRemoteException	android.os.RemoteException
    //   169	4	1	localObject2	Object
    //   90	4	2	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   43	48	66	java/lang/Exception
    //   21	38	90	finally
    //   41	43	90	finally
    //   79	90	90	finally
    //   91	93	90	finally
    //   111	138	146	android/os/RemoteException
    //   7	21	169	finally
    //   43	48	169	finally
    //   48	66	169	finally
    //   67	79	169	finally
    //   93	95	169	finally
    //   95	111	169	finally
    //   111	138	169	finally
    //   138	140	169	finally
    //   147	169	169	finally
    //   170	172	169	finally
  }
  
  /* Error */
  @Hide
  public boolean getIsAdIdFakeForDebugLogging()
    throws IOException
  {
    // Byte code:
    //   0: ldc -33
    //   2: invokestatic 225	com/google/android/gms/common/internal/zzbq:zzc	(Ljava/lang/String;)V
    //   5: aload_0
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 56	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzc	Z
    //   11: ifne +84 -> 95
    //   14: aload_0
    //   15: getfield 41	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzd	Ljava/lang/Object;
    //   18: astore_2
    //   19: aload_2
    //   20: monitorenter
    //   21: aload_0
    //   22: getfield 207	com/google/android/gms/ads/identifier/AdvertisingIdClient:zze	Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
    //   25: ifnull +54 -> 79
    //   28: aload_0
    //   29: getfield 207	com/google/android/gms/ads/identifier/AdvertisingIdClient:zze	Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
    //   32: getfield 335	com/google/android/gms/ads/identifier/AdvertisingIdClient$zza:zzb	Z
    //   35: ifne +6 -> 41
    //   38: goto +41 -> 79
    //   41: aload_2
    //   42: monitorexit
    //   43: aload_0
    //   44: iconst_0
    //   45: invokespecial 106	com/google/android/gms/ads/identifier/AdvertisingIdClient:zza	(Z)V
    //   48: aload_0
    //   49: getfield 56	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzc	Z
    //   52: ifne +43 -> 95
    //   55: new 64	java/io/IOException
    //   58: dup
    //   59: ldc_w 337
    //   62: invokespecial 154	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   65: athrow
    //   66: astore_2
    //   67: new 64	java/io/IOException
    //   70: dup
    //   71: ldc_w 337
    //   74: aload_2
    //   75: invokespecial 340	java/io/IOException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   78: athrow
    //   79: new 64	java/io/IOException
    //   82: dup
    //   83: ldc_w 342
    //   86: invokespecial 154	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   89: athrow
    //   90: astore_3
    //   91: aload_2
    //   92: monitorexit
    //   93: aload_3
    //   94: athrow
    //   95: aload_0
    //   96: getfield 229	com/google/android/gms/ads/identifier/AdvertisingIdClient:zza	Lcom/google/android/gms/common/zza;
    //   99: invokestatic 46	com/google/android/gms/common/internal/zzbq:zza	(Ljava/lang/Object;)Ljava/lang/Object;
    //   102: pop
    //   103: aload_0
    //   104: getfield 233	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzb	Lcom/google/android/gms/internal/zzfp;
    //   107: invokestatic 46	com/google/android/gms/common/internal/zzbq:zza	(Ljava/lang/Object;)Ljava/lang/Object;
    //   110: pop
    //   111: aload_0
    //   112: getfield 233	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzb	Lcom/google/android/gms/internal/zzfp;
    //   115: invokeinterface 358 1 0
    //   120: istore_1
    //   121: aload_0
    //   122: monitorexit
    //   123: aload_0
    //   124: invokespecial 235	com/google/android/gms/ads/identifier/AdvertisingIdClient:zza	()V
    //   127: iload_1
    //   128: ireturn
    //   129: astore_2
    //   130: ldc_w 299
    //   133: ldc_w 354
    //   136: aload_2
    //   137: invokestatic 329	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   140: pop
    //   141: new 64	java/io/IOException
    //   144: dup
    //   145: ldc_w 356
    //   148: invokespecial 154	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   151: athrow
    //   152: astore_2
    //   153: aload_0
    //   154: monitorexit
    //   155: aload_2
    //   156: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	157	0	this	AdvertisingIdClient
    //   120	8	1	bool	boolean
    //   66	26	2	localException	Exception
    //   129	8	2	localRemoteException	android.os.RemoteException
    //   152	4	2	localObject2	Object
    //   90	4	3	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   43	48	66	java/lang/Exception
    //   21	38	90	finally
    //   41	43	90	finally
    //   79	90	90	finally
    //   91	93	90	finally
    //   111	121	129	android/os/RemoteException
    //   7	21	152	finally
    //   43	48	152	finally
    //   48	66	152	finally
    //   67	79	152	finally
    //   93	95	152	finally
    //   95	111	152	finally
    //   111	121	152	finally
    //   121	123	152	finally
    //   130	152	152	finally
    //   153	155	152	finally
  }
  
  @Hide
  public void start()
    throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException
  {
    zza(true);
  }
  
  public static final class Info
  {
    private final String zza;
    private final boolean zzb;
    
    public Info(String paramString, boolean paramBoolean)
    {
      zza = paramString;
      zzb = paramBoolean;
    }
    
    public final String getId()
    {
      return zza;
    }
    
    public final boolean isLimitAdTrackingEnabled()
    {
      return zzb;
    }
    
    public final String toString()
    {
      String str = zza;
      boolean bool = zzb;
      StringBuilder localStringBuilder = new StringBuilder(7 + String.valueOf(str).length());
      localStringBuilder.append("{");
      localStringBuilder.append(str);
      localStringBuilder.append("}");
      localStringBuilder.append(bool);
      return localStringBuilder.toString();
    }
  }
  
  @Hide
  static final class zza
    extends Thread
  {
    CountDownLatch zza;
    boolean zzb;
    private WeakReference<AdvertisingIdClient> zzc;
    private long zzd;
    
    public zza(AdvertisingIdClient paramAdvertisingIdClient, long paramLong)
    {
      zzc = new WeakReference(paramAdvertisingIdClient);
      zzd = paramLong;
      zza = new CountDownLatch(1);
      zzb = false;
      start();
    }
    
    private final void zza()
    {
      AdvertisingIdClient localAdvertisingIdClient = (AdvertisingIdClient)zzc.get();
      if (localAdvertisingIdClient != null)
      {
        localAdvertisingIdClient.finish();
        zzb = true;
      }
    }
    
    public final void run()
    {
      try
      {
        if (!zza.await(zzd, TimeUnit.MILLISECONDS)) {
          zza();
        }
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
      zza();
    }
  }
}

package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.util.Log;
import com.google.android.gms.R.string;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.common.util.zzz;
import java.util.concurrent.atomic.AtomicBoolean;

@Hide
public class zzs
{
  @Deprecated
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  @Deprecated
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 12210000;
  public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
  static final AtomicBoolean zza = new AtomicBoolean();
  @Hide
  private static boolean zzb = false;
  @Hide
  private static boolean zzc = false;
  private static boolean zzd = false;
  private static boolean zze = false;
  private static final AtomicBoolean zzf = new AtomicBoolean();
  
  zzs() {}
  
  @Deprecated
  public static PendingIntent getErrorPendingIntent(int paramInt1, Context paramContext, int paramInt2)
  {
    return zzf.zza().getErrorResolutionPendingIntent(paramContext, paramInt1, paramInt2);
  }
  
  @Deprecated
  public static String getErrorString(int paramInt)
  {
    return ConnectionResult.zza(paramInt);
  }
  
  public static Context getRemoteContext(Context paramContext)
  {
    try
    {
      paramContext = paramContext.createPackageContext("com.google.android.gms", 3);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static Resources getRemoteResource(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getResourcesForApplication("com.google.android.gms");
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  @Deprecated
  public static int isGooglePlayServicesAvailable(Context paramContext)
  {
    return zza(paramContext, -1);
  }
  
  @Deprecated
  public static boolean isUserRecoverableError(int paramInt)
  {
    if (paramInt != 9) {
      switch (paramInt)
      {
      default: 
        return false;
      }
    }
    return true;
  }
  
  @Deprecated
  public static int zza(Context paramContext, int paramInt)
  {
    try
    {
      paramContext.getResources().getString(R.string.common_google_play_services_unknown_issue);
    }
    catch (Throwable localThrowable)
    {
      int i;
      boolean bool;
      for (;;) {}
    }
    Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
    if ((!"com.google.android.gms".equals(paramContext.getPackageName())) && (!zzf.get()))
    {
      i = zzbf.zzb(paramContext);
      if (i == 0) {
        throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
      }
      if (i != GOOGLE_PLAY_SERVICES_VERSION_CODE)
      {
        paramInt = GOOGLE_PLAY_SERVICES_VERSION_CODE;
        paramContext = new StringBuilder(320);
        paramContext.append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ");
        paramContext.append(paramInt);
        paramContext.append(" but found ");
        paramContext.append(i);
        paramContext.append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
        throw new IllegalStateException(paramContext.toString());
      }
    }
    if ((!zzj.zzb(paramContext)) && (!zzj.zzd(paramContext))) {
      bool = true;
    } else {
      bool = false;
    }
    return zza(paramContext, bool, GOOGLE_PLAY_SERVICES_VERSION_CODE, paramInt);
  }
  
  private static int zza(Context paramContext, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    boolean bool;
    if ((paramInt2 != -1) && (paramInt2 < 0)) {
      bool = false;
    } else {
      bool = true;
    }
    zzbq.zzb(bool);
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject = null;
    if (paramBoolean) {}
    try
    {
      localObject = localPackageManager.getPackageInfo("com.android.vending", 8256);
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    paramContext = "Google Play Store is missing.";
    for (;;)
    {
      Log.w("GooglePlayServicesUtil", paramContext);
      return 9;
      try
      {
        PackageInfo localPackageInfo = localPackageManager.getPackageInfo("com.google.android.gms", 64);
        zzt.zza(paramContext);
        if (!zzt.zza(localPackageInfo, true))
        {
          paramContext = "Google Play services signature invalid.";
        }
        else if ((paramBoolean) && ((!zzt.zza((PackageInfo)localObject, true)) || (!signatures[0].equals(signatures[0]))))
        {
          paramContext = "Google Play Store signature invalid.";
        }
        else
        {
          paramInt1 /= 1000;
          int i = versionCode / 1000;
          if ((i < paramInt1) && ((paramInt2 == -1) || (i < paramInt2 / 1000)))
          {
            paramInt1 = GOOGLE_PLAY_SERVICES_VERSION_CODE;
            paramInt2 = versionCode;
            paramContext = new StringBuilder(77);
            paramContext.append("Google Play services out of date.  Requires ");
            paramContext.append(paramInt1);
            paramContext.append(" but found ");
            paramContext.append(paramInt2);
            Log.w("GooglePlayServicesUtil", paramContext.toString());
            return 2;
          }
          localObject = applicationInfo;
          paramContext = (Context)localObject;
          if (localObject == null) {
            try
            {
              paramContext = localPackageManager.getApplicationInfo("com.google.android.gms", 0);
            }
            catch (PackageManager.NameNotFoundException paramContext)
            {
              Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", paramContext);
              return 1;
            }
          }
          if (!enabled) {
            return 3;
          }
          return 0;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        for (;;) {}
      }
    }
    Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
    return 1;
  }
  
  @Deprecated
  @Hide
  public static void zza(Context paramContext)
    throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
  {
    zzf.zza();
    int i = zzf.zza(paramContext, -1);
    if (i != 0)
    {
      zzf.zza();
      paramContext = zzf.zza(paramContext, i, "e");
      StringBuilder localStringBuilder = new StringBuilder(57);
      localStringBuilder.append("GooglePlayServices not available due to error ");
      localStringBuilder.append(i);
      Log.e("GooglePlayServicesUtil", localStringBuilder.toString());
      if (paramContext == null) {
        throw new GooglePlayServicesNotAvailableException(i);
      }
      throw new GooglePlayServicesRepairableException(i, "Google Play Services not available", paramContext);
    }
  }
  
  @Deprecated
  @TargetApi(19)
  @Hide
  public static boolean zza(Context paramContext, int paramInt, String paramString)
  {
    return zzz.zza(paramContext, paramInt, paramString);
  }
  
  /* Error */
  @TargetApi(21)
  static boolean zza(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc 9
    //   3: invokevirtual 121	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   6: istore_3
    //   7: invokestatic 267	com/google/android/gms/common/util/zzs:zzg	()Z
    //   10: ifeq +56 -> 66
    //   13: aload_0
    //   14: invokevirtual 72	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   17: invokevirtual 271	android/content/pm/PackageManager:getPackageInstaller	()Landroid/content/pm/PackageInstaller;
    //   20: invokevirtual 277	android/content/pm/PackageInstaller:getAllSessions	()Ljava/util/List;
    //   23: astore 4
    //   25: aload 4
    //   27: invokeinterface 283 1 0
    //   32: astore 4
    //   34: aload 4
    //   36: invokeinterface 288 1 0
    //   41: ifeq +25 -> 66
    //   44: aload_1
    //   45: aload 4
    //   47: invokeinterface 292 1 0
    //   52: checkcast 294	android/content/pm/PackageInstaller$SessionInfo
    //   55: invokevirtual 297	android/content/pm/PackageInstaller$SessionInfo:getAppPackageName	()Ljava/lang/String;
    //   58: invokevirtual 121	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   61: ifeq -27 -> 34
    //   64: iconst_1
    //   65: ireturn
    //   66: aload_0
    //   67: invokevirtual 72	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   70: astore 4
    //   72: aload 4
    //   74: aload_1
    //   75: sipush 8192
    //   78: invokevirtual 217	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   81: astore_1
    //   82: iload_3
    //   83: ifeq +8 -> 91
    //   86: aload_1
    //   87: getfield 228	android/content/pm/ApplicationInfo:enabled	Z
    //   90: ireturn
    //   91: aload_1
    //   92: getfield 228	android/content/pm/ApplicationInfo:enabled	Z
    //   95: ifeq +62 -> 157
    //   98: invokestatic 299	com/google/android/gms/common/util/zzs:zzd	()Z
    //   101: ifeq +48 -> 149
    //   104: aload_0
    //   105: ldc_w 301
    //   108: invokevirtual 305	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   111: checkcast 307	android/os/UserManager
    //   114: aload_0
    //   115: invokevirtual 115	android/content/Context:getPackageName	()Ljava/lang/String;
    //   118: invokevirtual 311	android/os/UserManager:getApplicationRestrictions	(Ljava/lang/String;)Landroid/os/Bundle;
    //   121: astore_0
    //   122: aload_0
    //   123: ifnull +26 -> 149
    //   126: ldc_w 313
    //   129: aload_0
    //   130: ldc_w 315
    //   133: invokevirtual 320	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   136: invokevirtual 121	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   139: istore_3
    //   140: iload_3
    //   141: ifeq +8 -> 149
    //   144: iconst_1
    //   145: istore_2
    //   146: goto +5 -> 151
    //   149: iconst_0
    //   150: istore_2
    //   151: iload_2
    //   152: ifne +5 -> 157
    //   155: iconst_1
    //   156: ireturn
    //   157: iconst_0
    //   158: ireturn
    //   159: astore_0
    //   160: iconst_0
    //   161: ireturn
    //   162: astore_0
    //   163: iconst_0
    //   164: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	165	0	paramContext	Context
    //   0	165	1	paramString	String
    //   145	7	2	i	int
    //   6	135	3	bool	boolean
    //   23	50	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   13	25	159	java/lang/Exception
    //   72	82	162	android/content/pm/PackageManager$NameNotFoundException
    //   86	91	162	android/content/pm/PackageManager$NameNotFoundException
    //   91	122	162	android/content/pm/PackageManager$NameNotFoundException
    //   126	140	162	android/content/pm/PackageManager$NameNotFoundException
  }
  
  /* Error */
  @Hide
  public static boolean zzb(Context paramContext)
  {
    // Byte code:
    //   0: getstatic 322	com/google/android/gms/common/zzs:zze	Z
    //   3: ifne +82 -> 85
    //   6: aload_0
    //   7: invokestatic 327	com/google/android/gms/internal/zzbih:zza	(Landroid/content/Context;)Lcom/google/android/gms/internal/zzbig;
    //   10: ldc 9
    //   12: bipush 64
    //   14: invokevirtual 331	com/google/android/gms/internal/zzbig:zzb	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   17: astore_1
    //   18: aload_0
    //   19: invokestatic 188	com/google/android/gms/common/zzt:zza	(Landroid/content/Context;)Lcom/google/android/gms/common/zzt;
    //   22: pop
    //   23: aload_1
    //   24: ifnull +26 -> 50
    //   27: aload_1
    //   28: iconst_0
    //   29: invokestatic 191	com/google/android/gms/common/zzt:zza	(Landroid/content/pm/PackageInfo;Z)Z
    //   32: ifne +18 -> 50
    //   35: aload_1
    //   36: iconst_1
    //   37: invokestatic 191	com/google/android/gms/common/zzt:zza	(Landroid/content/pm/PackageInfo;Z)Z
    //   40: ifeq +10 -> 50
    //   43: iconst_1
    //   44: putstatic 333	com/google/android/gms/common/zzs:zzd	Z
    //   47: goto +25 -> 72
    //   50: iconst_0
    //   51: putstatic 333	com/google/android/gms/common/zzs:zzd	Z
    //   54: goto +18 -> 72
    //   57: astore_0
    //   58: goto +21 -> 79
    //   61: astore_0
    //   62: ldc 103
    //   64: ldc_w 335
    //   67: aload_0
    //   68: invokestatic 337	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   71: pop
    //   72: iconst_1
    //   73: putstatic 322	com/google/android/gms/common/zzs:zze	Z
    //   76: goto +9 -> 85
    //   79: iconst_1
    //   80: putstatic 322	com/google/android/gms/common/zzs:zze	Z
    //   83: aload_0
    //   84: athrow
    //   85: getstatic 333	com/google/android/gms/common/zzs:zzd	Z
    //   88: ifne +19 -> 107
    //   91: ldc_w 301
    //   94: getstatic 342	android/os/Build:TYPE	Ljava/lang/String;
    //   97: invokevirtual 121	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   100: ifne +5 -> 105
    //   103: iconst_1
    //   104: ireturn
    //   105: iconst_0
    //   106: ireturn
    //   107: iconst_1
    //   108: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	109	0	paramContext	Context
    //   17	19	1	localPackageInfo	PackageInfo
    // Exception table:
    //   from	to	target	type
    //   6	23	57	finally
    //   27	47	57	finally
    //   50	54	57	finally
    //   62	72	57	finally
    //   6	23	61	android/content/pm/PackageManager$NameNotFoundException
    //   27	47	61	android/content/pm/PackageManager$NameNotFoundException
    //   50	54	61	android/content/pm/PackageManager$NameNotFoundException
  }
  
  @Deprecated
  @Hide
  public static boolean zzb(Context paramContext, int paramInt)
  {
    return zzz.zza(paramContext, paramInt);
  }
  
  @Deprecated
  @Hide
  public static void zzc(Context paramContext)
  {
    if (zza.getAndSet(true)) {
      return;
    }
    try
    {
      paramContext = (NotificationManager)paramContext.getSystemService("notification");
      if (paramContext != null) {
        paramContext.cancel(10436);
      }
      return;
    }
    catch (SecurityException paramContext) {}
  }
  
  @Deprecated
  @Hide
  public static boolean zzc(Context paramContext, int paramInt)
  {
    if (paramInt == 18) {
      return true;
    }
    if (paramInt == 1) {
      return zza(paramContext, "com.google.android.gms");
    }
    return false;
  }
  
  @Deprecated
  @Hide
  public static int zzd(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.google.android.gms", 0);
      return versionCode;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
    return 0;
  }
}

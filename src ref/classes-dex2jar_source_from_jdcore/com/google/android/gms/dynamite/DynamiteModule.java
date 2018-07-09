package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.DynamiteApi;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Hide
public final class DynamiteModule
{
  public static final zzd zza;
  public static final zzd zzb = new zzd();
  public static final zzd zzc = new zze();
  public static final zzd zzd = new zzf();
  public static final zzd zze = new zzg();
  private static Boolean zzf;
  private static zzk zzg;
  private static zzm zzh;
  private static String zzi;
  private static final ThreadLocal<zza> zzj = new ThreadLocal();
  private static final zzi zzk = new zza();
  private static zzd zzl;
  private final Context zzm;
  
  static
  {
    zza = new zzb();
    zzl = new zzc();
  }
  
  private DynamiteModule(Context paramContext)
  {
    zzm = ((Context)zzbq.zza(paramContext));
  }
  
  public static int zza(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getApplicationContext().getClassLoader();
      Object localObject = new StringBuilder(61 + String.valueOf(paramString).length());
      ((StringBuilder)localObject).append("com.google.android.gms.dynamite.descriptors.");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(".ModuleDescriptor");
      localObject = paramContext.loadClass(((StringBuilder)localObject).toString());
      paramContext = ((Class)localObject).getDeclaredField("MODULE_ID");
      localObject = ((Class)localObject).getDeclaredField("MODULE_VERSION");
      if (!paramContext.get(null).equals(paramString))
      {
        paramContext = String.valueOf(paramContext.get(null));
        localObject = new StringBuilder(51 + String.valueOf(paramContext).length() + String.valueOf(paramString).length());
        ((StringBuilder)localObject).append("Module descriptor id '");
        ((StringBuilder)localObject).append(paramContext);
        ((StringBuilder)localObject).append("' didn't match expected id '");
        ((StringBuilder)localObject).append(paramString);
        ((StringBuilder)localObject).append("'");
        Log.e("DynamiteModule", ((StringBuilder)localObject).toString());
        return 0;
      }
      int i = ((Field)localObject).getInt(null);
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext = String.valueOf(paramContext.getMessage());
      if (paramContext.length() != 0) {
        paramContext = "Failed to load module descriptor class: ".concat(paramContext);
      } else {
        paramContext = new String("Failed to load module descriptor class: ");
      }
      Log.e("DynamiteModule", paramContext);
      return 0;
      paramContext = new StringBuilder(45 + String.valueOf(paramString).length());
      paramContext.append("Local module descriptor class for ");
      paramContext.append(paramString);
      paramContext.append(" not found.");
      Log.w("DynamiteModule", paramContext.toString());
      return 0;
    }
    catch (ClassNotFoundException paramContext)
    {
      for (;;) {}
    }
  }
  
  /* Error */
  public static int zza(Context paramContext, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 203	com/google/android/gms/dynamite/DynamiteModule:zzf	Ljava/lang/Boolean;
    //   6: astore 5
    //   8: aload 5
    //   10: astore 4
    //   12: aload 5
    //   14: ifnonnull +271 -> 285
    //   17: aload_0
    //   18: invokevirtual 103	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   21: invokevirtual 107	android/content/Context:getClassLoader	()Ljava/lang/ClassLoader;
    //   24: ldc 6
    //   26: invokevirtual 206	java/lang/Class:getName	()Ljava/lang/String;
    //   29: invokevirtual 140	java/lang/ClassLoader:loadClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   32: astore 5
    //   34: aload 5
    //   36: ldc -48
    //   38: invokevirtual 148	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   41: astore 4
    //   43: aload 5
    //   45: monitorenter
    //   46: aload 4
    //   48: aconst_null
    //   49: invokevirtual 155	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   52: checkcast 136	java/lang/ClassLoader
    //   55: astore 6
    //   57: aload 6
    //   59: ifnull +32 -> 91
    //   62: aload 6
    //   64: invokestatic 211	java/lang/ClassLoader:getSystemClassLoader	()Ljava/lang/ClassLoader;
    //   67: if_acmpne +11 -> 78
    //   70: getstatic 216	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   73: astore 4
    //   75: goto +130 -> 205
    //   78: aload 6
    //   80: invokestatic 219	com/google/android/gms/dynamite/DynamiteModule:zza	(Ljava/lang/ClassLoader;)V
    //   83: getstatic 222	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   86: astore 4
    //   88: goto +117 -> 205
    //   91: ldc -32
    //   93: aload_0
    //   94: invokevirtual 103	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   97: invokevirtual 227	android/content/Context:getPackageName	()Ljava/lang/String;
    //   100: invokevirtual 228	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   103: ifeq +15 -> 118
    //   106: aload 4
    //   108: aconst_null
    //   109: invokestatic 211	java/lang/ClassLoader:getSystemClassLoader	()Ljava/lang/ClassLoader;
    //   112: invokevirtual 232	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   115: goto -45 -> 70
    //   118: aload_0
    //   119: aload_1
    //   120: iload_2
    //   121: invokestatic 234	com/google/android/gms/dynamite/DynamiteModule:zzc	(Landroid/content/Context;Ljava/lang/String;Z)I
    //   124: istore_3
    //   125: getstatic 236	com/google/android/gms/dynamite/DynamiteModule:zzi	Ljava/lang/String;
    //   128: ifnull +57 -> 185
    //   131: getstatic 236	com/google/android/gms/dynamite/DynamiteModule:zzi	Ljava/lang/String;
    //   134: invokevirtual 240	java/lang/String:isEmpty	()Z
    //   137: ifeq +6 -> 143
    //   140: goto +45 -> 185
    //   143: new 242	com/google/android/gms/dynamite/zzh
    //   146: dup
    //   147: getstatic 236	com/google/android/gms/dynamite/DynamiteModule:zzi	Ljava/lang/String;
    //   150: invokestatic 211	java/lang/ClassLoader:getSystemClassLoader	()Ljava/lang/ClassLoader;
    //   153: invokespecial 245	com/google/android/gms/dynamite/zzh:<init>	(Ljava/lang/String;Ljava/lang/ClassLoader;)V
    //   156: astore 6
    //   158: aload 6
    //   160: invokestatic 219	com/google/android/gms/dynamite/DynamiteModule:zza	(Ljava/lang/ClassLoader;)V
    //   163: aload 4
    //   165: aconst_null
    //   166: aload 6
    //   168: invokevirtual 232	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   171: getstatic 222	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   174: putstatic 203	com/google/android/gms/dynamite/DynamiteModule:zzf	Ljava/lang/Boolean;
    //   177: aload 5
    //   179: monitorexit
    //   180: ldc 2
    //   182: monitorexit
    //   183: iload_3
    //   184: ireturn
    //   185: aload 5
    //   187: monitorexit
    //   188: ldc 2
    //   190: monitorexit
    //   191: iload_3
    //   192: ireturn
    //   193: aload 4
    //   195: aconst_null
    //   196: invokestatic 211	java/lang/ClassLoader:getSystemClassLoader	()Ljava/lang/ClassLoader;
    //   199: invokevirtual 232	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   202: goto -132 -> 70
    //   205: aload 5
    //   207: monitorexit
    //   208: goto +72 -> 280
    //   211: astore 4
    //   213: aload 5
    //   215: monitorexit
    //   216: aload 4
    //   218: athrow
    //   219: astore 4
    //   221: aload 4
    //   223: invokestatic 115	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   226: astore 4
    //   228: new 109	java/lang/StringBuilder
    //   231: dup
    //   232: bipush 30
    //   234: aload 4
    //   236: invokestatic 115	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   239: invokevirtual 119	java/lang/String:length	()I
    //   242: iadd
    //   243: invokespecial 122	java/lang/StringBuilder:<init>	(I)V
    //   246: astore 5
    //   248: aload 5
    //   250: ldc -9
    //   252: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   255: pop
    //   256: aload 5
    //   258: aload 4
    //   260: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   263: pop
    //   264: ldc -89
    //   266: aload 5
    //   268: invokevirtual 134	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   271: invokestatic 196	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   274: pop
    //   275: getstatic 216	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   278: astore 4
    //   280: aload 4
    //   282: putstatic 203	com/google/android/gms/dynamite/DynamiteModule:zzf	Ljava/lang/Boolean;
    //   285: ldc 2
    //   287: monitorexit
    //   288: aload 4
    //   290: invokevirtual 250	java/lang/Boolean:booleanValue	()Z
    //   293: ifeq +57 -> 350
    //   296: aload_0
    //   297: aload_1
    //   298: iload_2
    //   299: invokestatic 234	com/google/android/gms/dynamite/DynamiteModule:zzc	(Landroid/content/Context;Ljava/lang/String;Z)I
    //   302: istore_3
    //   303: iload_3
    //   304: ireturn
    //   305: astore_0
    //   306: aload_0
    //   307: invokevirtual 251	com/google/android/gms/dynamite/DynamiteModule$zzc:getMessage	()Ljava/lang/String;
    //   310: invokestatic 115	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   313: astore_0
    //   314: aload_0
    //   315: invokevirtual 119	java/lang/String:length	()I
    //   318: ifeq +13 -> 331
    //   321: ldc -3
    //   323: aload_0
    //   324: invokevirtual 186	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   327: astore_0
    //   328: goto +13 -> 341
    //   331: new 111	java/lang/String
    //   334: dup
    //   335: ldc -3
    //   337: invokespecial 189	java/lang/String:<init>	(Ljava/lang/String;)V
    //   340: astore_0
    //   341: ldc -89
    //   343: aload_0
    //   344: invokestatic 196	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   347: pop
    //   348: iconst_0
    //   349: ireturn
    //   350: aload_0
    //   351: aload_1
    //   352: iload_2
    //   353: invokestatic 255	com/google/android/gms/dynamite/DynamiteModule:zzb	(Landroid/content/Context;Ljava/lang/String;Z)I
    //   356: ireturn
    //   357: astore_0
    //   358: ldc 2
    //   360: monitorexit
    //   361: aload_0
    //   362: athrow
    //   363: astore 4
    //   365: goto -282 -> 83
    //   368: astore 6
    //   370: goto -177 -> 193
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	373	0	paramContext	Context
    //   0	373	1	paramString	String
    //   0	373	2	paramBoolean	boolean
    //   124	180	3	i	int
    //   10	184	4	localObject1	Object
    //   211	6	4	localObject2	Object
    //   219	3	4	localClassNotFoundException	ClassNotFoundException
    //   226	63	4	localObject3	Object
    //   363	1	4	localZzc1	zzc
    //   6	261	5	localObject4	Object
    //   55	112	6	localObject5	Object
    //   368	1	6	localZzc2	zzc
    // Exception table:
    //   from	to	target	type
    //   46	57	211	finally
    //   62	70	211	finally
    //   70	75	211	finally
    //   78	83	211	finally
    //   83	88	211	finally
    //   91	115	211	finally
    //   118	140	211	finally
    //   143	177	211	finally
    //   177	180	211	finally
    //   185	188	211	finally
    //   193	202	211	finally
    //   205	208	211	finally
    //   213	216	211	finally
    //   17	46	219	java/lang/ClassNotFoundException
    //   17	46	219	java/lang/IllegalAccessException
    //   17	46	219	java/lang/NoSuchFieldException
    //   216	219	219	java/lang/ClassNotFoundException
    //   216	219	219	java/lang/IllegalAccessException
    //   216	219	219	java/lang/NoSuchFieldException
    //   296	303	305	com/google/android/gms/dynamite/DynamiteModule$zzc
    //   3	8	357	finally
    //   17	46	357	finally
    //   180	183	357	finally
    //   188	191	357	finally
    //   216	219	357	finally
    //   221	280	357	finally
    //   280	285	357	finally
    //   285	288	357	finally
    //   358	361	357	finally
    //   78	83	363	com/google/android/gms/dynamite/DynamiteModule$zzc
    //   118	140	368	com/google/android/gms/dynamite/DynamiteModule$zzc
    //   143	177	368	com/google/android/gms/dynamite/DynamiteModule$zzc
  }
  
  private static Context zza(Context paramContext, String paramString, int paramInt, Cursor paramCursor, zzm paramZzm)
  {
    try
    {
      paramContext = (Context)com.google.android.gms.dynamic.zzn.zza(paramZzm.zza(com.google.android.gms.dynamic.zzn.zza(paramContext), paramString, paramInt, com.google.android.gms.dynamic.zzn.zza(paramCursor)));
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext = String.valueOf(paramContext.toString());
      if (paramContext.length() != 0) {
        paramContext = "Failed to load DynamiteLoader: ".concat(paramContext);
      } else {
        paramContext = new String("Failed to load DynamiteLoader: ");
      }
      Log.e("DynamiteModule", paramContext);
    }
    return null;
  }
  
  public static DynamiteModule zza(Context paramContext, zzd paramZzd, String paramString)
    throws DynamiteModule.zzc
  {
    zza localZza1 = (zza)zzj.get();
    zza localZza2 = new zza(null);
    zzj.set(localZza2);
    try
    {
      zzj localZzj = paramZzd.zza(paramContext, paramString, zzk);
      int i = zza;
      int j = zzb;
      Object localObject = new StringBuilder(68 + String.valueOf(paramString).length() + String.valueOf(paramString).length());
      ((StringBuilder)localObject).append("Considering local module ");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(i);
      ((StringBuilder)localObject).append(" and remote module ");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(j);
      Log.i("DynamiteModule", ((StringBuilder)localObject).toString());
      if ((zzc != 0) && ((zzc != -1) || (zza != 0)) && ((zzc != 1) || (zzb != 0)))
      {
        if (zzc == -1)
        {
          paramZzd = zzc(paramContext, paramString);
          paramContext = paramZzd;
          if (zza != null) {
            paramContext = paramZzd;
          }
        }
        for (;;)
        {
          zza.close();
          label240:
          zzj.set(localZza1);
          return paramContext;
          i = zzc;
          if (i == 1) {
            try
            {
              localObject = zza(paramContext, paramString, zzb);
              return localObject;
            }
            catch (zzc localZzc)
            {
              localObject = String.valueOf(localZzc.getMessage());
              if (((String)localObject).length() != 0) {
                localObject = "Failed to load remote module: ".concat((String)localObject);
              } else {
                localObject = new String("Failed to load remote module: ");
              }
              Log.w("DynamiteModule", (String)localObject);
              if ((zza != 0) && (zzazzbzza, 0)).zzc == -1))
              {
                paramZzd = zzc(paramContext, paramString);
                paramContext = paramZzd;
                if (zza == null) {
                  break label240;
                }
                paramContext = paramZzd;
              }
              else
              {
                throw new zzc("Remote load failed. No local fallback found.", localZzc, null);
              }
            }
          }
        }
        i = zzc;
        paramContext = new StringBuilder(47);
        paramContext.append("VersionPolicy returned invalid code:");
        paramContext.append(i);
        throw new zzc(paramContext.toString(), null);
      }
      i = zza;
      j = zzb;
      paramContext = new StringBuilder(91);
      paramContext.append("No acceptable module found. Local version is ");
      paramContext.append(i);
      paramContext.append(" and remote version is ");
      paramContext.append(j);
      paramContext.append(".");
      throw new zzc(paramContext.toString(), null);
    }
    finally
    {
      if (zza != null) {
        zza.close();
      }
      zzj.set(localZza1);
    }
  }
  
  private static DynamiteModule zza(Context paramContext, String paramString, int paramInt)
    throws DynamiteModule.zzc
  {
    try
    {
      Boolean localBoolean = zzf;
      if (localBoolean == null) {
        throw new zzc("Failed to determine which loading route to use.", null);
      }
      if (localBoolean.booleanValue()) {
        return zzc(paramContext, paramString, paramInt);
      }
      return zzb(paramContext, paramString, paramInt);
    }
    finally {}
  }
  
  /* Error */
  private static zzk zza(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 351	com/google/android/gms/dynamite/DynamiteModule:zzg	Lcom/google/android/gms/dynamite/zzk;
    //   6: ifnull +12 -> 18
    //   9: getstatic 351	com/google/android/gms/dynamite/DynamiteModule:zzg	Lcom/google/android/gms/dynamite/zzk;
    //   12: astore_0
    //   13: ldc 2
    //   15: monitorexit
    //   16: aload_0
    //   17: areturn
    //   18: invokestatic 356	com/google/android/gms/common/zzf:zza	()Lcom/google/android/gms/common/zzf;
    //   21: aload_0
    //   22: invokevirtual 360	com/google/android/gms/common/zzf:isGooglePlayServicesAvailable	(Landroid/content/Context;)I
    //   25: ifeq +8 -> 33
    //   28: ldc 2
    //   30: monitorexit
    //   31: aconst_null
    //   32: areturn
    //   33: aload_0
    //   34: ldc -32
    //   36: iconst_3
    //   37: invokevirtual 364	android/content/Context:createPackageContext	(Ljava/lang/String;I)Landroid/content/Context;
    //   40: invokevirtual 107	android/content/Context:getClassLoader	()Ljava/lang/ClassLoader;
    //   43: ldc_w 366
    //   46: invokevirtual 140	java/lang/ClassLoader:loadClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   49: invokevirtual 369	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   52: checkcast 371	android/os/IBinder
    //   55: astore_0
    //   56: aload_0
    //   57: ifnonnull +8 -> 65
    //   60: aconst_null
    //   61: astore_0
    //   62: goto +37 -> 99
    //   65: aload_0
    //   66: ldc_w 373
    //   69: invokeinterface 377 2 0
    //   74: astore_1
    //   75: aload_1
    //   76: instanceof 379
    //   79: ifeq +11 -> 90
    //   82: aload_1
    //   83: checkcast 379	com/google/android/gms/dynamite/zzk
    //   86: astore_0
    //   87: goto +12 -> 99
    //   90: new 381	com/google/android/gms/dynamite/zzl
    //   93: dup
    //   94: aload_0
    //   95: invokespecial 384	com/google/android/gms/dynamite/zzl:<init>	(Landroid/os/IBinder;)V
    //   98: astore_0
    //   99: aload_0
    //   100: ifnull +57 -> 157
    //   103: aload_0
    //   104: putstatic 351	com/google/android/gms/dynamite/DynamiteModule:zzg	Lcom/google/android/gms/dynamite/zzk;
    //   107: ldc 2
    //   109: monitorexit
    //   110: aload_0
    //   111: areturn
    //   112: astore_0
    //   113: aload_0
    //   114: invokevirtual 180	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   117: invokestatic 115	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   120: astore_0
    //   121: aload_0
    //   122: invokevirtual 119	java/lang/String:length	()I
    //   125: ifeq +14 -> 139
    //   128: ldc_w 386
    //   131: aload_0
    //   132: invokevirtual 186	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   135: astore_0
    //   136: goto +14 -> 150
    //   139: new 111	java/lang/String
    //   142: dup
    //   143: ldc_w 386
    //   146: invokespecial 189	java/lang/String:<init>	(Ljava/lang/String;)V
    //   149: astore_0
    //   150: ldc -89
    //   152: aload_0
    //   153: invokestatic 173	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   156: pop
    //   157: ldc 2
    //   159: monitorexit
    //   160: aconst_null
    //   161: areturn
    //   162: astore_0
    //   163: ldc 2
    //   165: monitorexit
    //   166: aload_0
    //   167: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	168	0	paramContext	Context
    //   74	9	1	localIInterface	IInterface
    // Exception table:
    //   from	to	target	type
    //   33	56	112	java/lang/Exception
    //   65	87	112	java/lang/Exception
    //   90	99	112	java/lang/Exception
    //   103	107	112	java/lang/Exception
    //   3	16	162	finally
    //   18	31	162	finally
    //   33	56	162	finally
    //   65	87	162	finally
    //   90	99	162	finally
    //   103	107	162	finally
    //   107	110	162	finally
    //   113	136	162	finally
    //   139	150	162	finally
    //   150	157	162	finally
    //   157	160	162	finally
    //   163	166	162	finally
  }
  
  private static void zza(ClassLoader paramClassLoader)
    throws DynamiteModule.zzc
  {
    try
    {
      paramClassLoader = (IBinder)paramClassLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
      if (paramClassLoader == null)
      {
        paramClassLoader = null;
      }
      else
      {
        IInterface localIInterface = paramClassLoader.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
        if ((localIInterface instanceof zzm)) {
          paramClassLoader = (zzm)localIInterface;
        } else {
          paramClassLoader = new zzn(paramClassLoader);
        }
      }
      zzh = paramClassLoader;
      return;
    }
    catch (ClassNotFoundException|IllegalAccessException|InstantiationException|InvocationTargetException|NoSuchMethodException paramClassLoader)
    {
      throw new zzc("Failed to instantiate dynamite loader", paramClassLoader, null);
    }
  }
  
  public static int zzb(Context paramContext, String paramString)
  {
    return zza(paramContext, paramString, false);
  }
  
  private static int zzb(Context paramContext, String paramString, boolean paramBoolean)
  {
    zzk localZzk = zza(paramContext);
    if (localZzk == null) {
      return 0;
    }
    try
    {
      int i = localZzk.zza(com.google.android.gms.dynamic.zzn.zza(paramContext), paramString, paramBoolean);
      return i;
    }
    catch (RemoteException paramContext)
    {
      paramContext = String.valueOf(paramContext.getMessage());
      if (paramContext.length() != 0) {
        paramContext = "Failed to retrieve remote module version: ".concat(paramContext);
      } else {
        paramContext = new String("Failed to retrieve remote module version: ");
      }
      Log.w("DynamiteModule", paramContext);
    }
    return 0;
  }
  
  private static DynamiteModule zzb(Context paramContext, String paramString, int paramInt)
    throws DynamiteModule.zzc
  {
    Object localObject = new StringBuilder(51 + String.valueOf(paramString).length());
    ((StringBuilder)localObject).append("Selected remote version of ");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append(", version >= ");
    ((StringBuilder)localObject).append(paramInt);
    Log.i("DynamiteModule", ((StringBuilder)localObject).toString());
    localObject = zza(paramContext);
    if (localObject == null) {
      throw new zzc("Failed to create IDynamiteLoader.", null);
    }
    try
    {
      paramContext = ((zzk)localObject).zza(com.google.android.gms.dynamic.zzn.zza(paramContext), paramString, paramInt);
      if (com.google.android.gms.dynamic.zzn.zza(paramContext) == null) {
        throw new zzc("Failed to load remote module.", null);
      }
      return new DynamiteModule((Context)com.google.android.gms.dynamic.zzn.zza(paramContext));
    }
    catch (RemoteException paramContext)
    {
      throw new zzc("Failed to load remote module.", paramContext, null);
    }
  }
  
  /* Error */
  private static int zzc(Context paramContext, String paramString, boolean paramBoolean)
    throws DynamiteModule.zzc
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: invokevirtual 439	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   7: astore 5
    //   9: iload_2
    //   10: ifeq +264 -> 274
    //   13: ldc_w 441
    //   16: astore_0
    //   17: goto +3 -> 20
    //   20: new 109	java/lang/StringBuilder
    //   23: dup
    //   24: bipush 42
    //   26: aload_0
    //   27: invokestatic 115	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   30: invokevirtual 119	java/lang/String:length	()I
    //   33: iadd
    //   34: aload_1
    //   35: invokestatic 115	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   38: invokevirtual 119	java/lang/String:length	()I
    //   41: iadd
    //   42: invokespecial 122	java/lang/StringBuilder:<init>	(I)V
    //   45: astore 6
    //   47: aload 6
    //   49: ldc_w 443
    //   52: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: pop
    //   56: aload 6
    //   58: aload_0
    //   59: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: pop
    //   63: aload 6
    //   65: ldc_w 445
    //   68: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: pop
    //   72: aload 6
    //   74: aload_1
    //   75: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: pop
    //   79: aload 5
    //   81: aload 6
    //   83: invokevirtual 134	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   86: invokestatic 451	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   89: aconst_null
    //   90: aconst_null
    //   91: aconst_null
    //   92: aconst_null
    //   93: invokevirtual 457	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   96: astore_0
    //   97: aload_0
    //   98: ifnull +110 -> 208
    //   101: aload_0
    //   102: invokeinterface 460 1 0
    //   107: ifne +6 -> 113
    //   110: goto +98 -> 208
    //   113: aload_0
    //   114: iconst_0
    //   115: invokeinterface 463 2 0
    //   120: istore_3
    //   121: aload_0
    //   122: astore_1
    //   123: iload_3
    //   124: ifle +64 -> 188
    //   127: ldc 2
    //   129: monitorenter
    //   130: aload_0
    //   131: iconst_2
    //   132: invokeinterface 467 2 0
    //   137: putstatic 236	com/google/android/gms/dynamite/DynamiteModule:zzi	Ljava/lang/String;
    //   140: ldc 2
    //   142: monitorexit
    //   143: getstatic 47	com/google/android/gms/dynamite/DynamiteModule:zzj	Ljava/lang/ThreadLocal;
    //   146: invokevirtual 276	java/lang/ThreadLocal:get	()Ljava/lang/Object;
    //   149: checkcast 9	com/google/android/gms/dynamite/DynamiteModule$zza
    //   152: astore 4
    //   154: aload_0
    //   155: astore_1
    //   156: aload 4
    //   158: ifnull +30 -> 188
    //   161: aload_0
    //   162: astore_1
    //   163: aload 4
    //   165: getfield 312	com/google/android/gms/dynamite/DynamiteModule$zza:zza	Landroid/database/Cursor;
    //   168: ifnonnull +20 -> 188
    //   171: aload 4
    //   173: aload_0
    //   174: putfield 312	com/google/android/gms/dynamite/DynamiteModule$zza:zza	Landroid/database/Cursor;
    //   177: aconst_null
    //   178: astore_1
    //   179: goto +9 -> 188
    //   182: astore_1
    //   183: ldc 2
    //   185: monitorexit
    //   186: aload_1
    //   187: athrow
    //   188: aload_1
    //   189: ifnull +9 -> 198
    //   192: aload_1
    //   193: invokeinterface 317 1 0
    //   198: iload_3
    //   199: ireturn
    //   200: astore_1
    //   201: goto +61 -> 262
    //   204: astore_1
    //   205: goto +34 -> 239
    //   208: ldc -89
    //   210: ldc_w 469
    //   213: invokestatic 196	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   216: pop
    //   217: new 15	com/google/android/gms/dynamite/DynamiteModule$zzc
    //   220: dup
    //   221: ldc_w 471
    //   224: aconst_null
    //   225: invokespecial 335	com/google/android/gms/dynamite/DynamiteModule$zzc:<init>	(Ljava/lang/String;Lcom/google/android/gms/dynamite/zza;)V
    //   228: athrow
    //   229: astore_1
    //   230: aload 4
    //   232: astore_0
    //   233: goto +29 -> 262
    //   236: astore_1
    //   237: aconst_null
    //   238: astore_0
    //   239: aload_1
    //   240: instanceof 15
    //   243: ifeq +5 -> 248
    //   246: aload_1
    //   247: athrow
    //   248: new 15	com/google/android/gms/dynamite/DynamiteModule$zzc
    //   251: dup
    //   252: ldc_w 473
    //   255: aload_1
    //   256: aconst_null
    //   257: invokespecial 330	com/google/android/gms/dynamite/DynamiteModule$zzc:<init>	(Ljava/lang/String;Ljava/lang/Throwable;Lcom/google/android/gms/dynamite/zza;)V
    //   260: athrow
    //   261: astore_1
    //   262: aload_0
    //   263: ifnull +9 -> 272
    //   266: aload_0
    //   267: invokeinterface 317 1 0
    //   272: aload_1
    //   273: athrow
    //   274: ldc_w 475
    //   277: astore_0
    //   278: goto -258 -> 20
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	281	0	paramContext	Context
    //   0	281	1	paramString	String
    //   0	281	2	paramBoolean	boolean
    //   120	79	3	i	int
    //   1	230	4	localZza	zza
    //   7	73	5	localContentResolver	android.content.ContentResolver
    //   45	37	6	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   130	143	182	finally
    //   183	186	182	finally
    //   101	110	200	finally
    //   113	121	200	finally
    //   127	130	200	finally
    //   143	154	200	finally
    //   163	177	200	finally
    //   186	188	200	finally
    //   208	229	200	finally
    //   101	110	204	java/lang/Exception
    //   113	121	204	java/lang/Exception
    //   127	130	204	java/lang/Exception
    //   143	154	204	java/lang/Exception
    //   163	177	204	java/lang/Exception
    //   186	188	204	java/lang/Exception
    //   208	229	204	java/lang/Exception
    //   3	9	229	finally
    //   20	97	229	finally
    //   3	9	236	java/lang/Exception
    //   20	97	236	java/lang/Exception
    //   239	248	261	finally
    //   248	261	261	finally
  }
  
  private static DynamiteModule zzc(Context paramContext, String paramString)
  {
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {
      paramString = "Selected local version of ".concat(paramString);
    } else {
      paramString = new String("Selected local version of ");
    }
    Log.i("DynamiteModule", paramString);
    return new DynamiteModule(paramContext.getApplicationContext());
  }
  
  private static DynamiteModule zzc(Context paramContext, String paramString, int paramInt)
    throws DynamiteModule.zzc
  {
    Object localObject = new StringBuilder(51 + String.valueOf(paramString).length());
    ((StringBuilder)localObject).append("Selected remote version of ");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append(", version >= ");
    ((StringBuilder)localObject).append(paramInt);
    Log.i("DynamiteModule", ((StringBuilder)localObject).toString());
    try
    {
      localObject = zzh;
      if (localObject == null) {
        throw new zzc("DynamiteLoaderV2 was not cached.", null);
      }
      zza localZza = (zza)zzj.get();
      if ((localZza != null) && (zza != null))
      {
        paramContext = zza(paramContext.getApplicationContext(), paramString, paramInt, zza, (zzm)localObject);
        if (paramContext == null) {
          throw new zzc("Failed to get module context", null);
        }
        return new DynamiteModule(paramContext);
      }
      throw new zzc("No result cursor", null);
    }
    finally {}
  }
  
  public final Context zza()
  {
    return zzm;
  }
  
  public final IBinder zza(String paramString)
    throws DynamiteModule.zzc
  {
    try
    {
      IBinder localIBinder = (IBinder)zzm.getClassLoader().loadClass(paramString).newInstance();
      return localIBinder;
    }
    catch (ClassNotFoundException|InstantiationException|IllegalAccessException localClassNotFoundException)
    {
      paramString = String.valueOf(paramString);
      if (paramString.length() != 0) {
        paramString = "Failed to instantiate module class: ".concat(paramString);
      } else {
        paramString = new String("Failed to instantiate module class: ");
      }
      throw new zzc(paramString, localClassNotFoundException, null);
    }
  }
  
  @DynamiteApi
  public static class DynamiteLoaderClassLoader
  {
    public static ClassLoader sClassLoader;
    
    public DynamiteLoaderClassLoader() {}
  }
  
  static final class zza
  {
    public Cursor zza;
    
    private zza() {}
  }
  
  static final class zzb
    implements zzi
  {
    private final int zza;
    private final int zzb;
    
    public zzb(int paramInt1, int paramInt2)
    {
      zza = paramInt1;
      zzb = 0;
    }
    
    public final int zza(Context paramContext, String paramString)
    {
      return zza;
    }
    
    public final int zza(Context paramContext, String paramString, boolean paramBoolean)
    {
      return 0;
    }
  }
  
  public static final class zzc
    extends Exception
  {
    private zzc(String paramString)
    {
      super();
    }
    
    private zzc(String paramString, Throwable paramThrowable)
    {
      super(paramThrowable);
    }
  }
  
  public static abstract interface zzd
  {
    public abstract zzj zza(Context paramContext, String paramString, zzi paramZzi)
      throws DynamiteModule.zzc;
  }
}

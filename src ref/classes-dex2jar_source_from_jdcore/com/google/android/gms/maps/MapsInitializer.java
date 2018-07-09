package com.google.android.gms.maps;

public final class MapsInitializer
{
  private static boolean zza = false;
  
  private MapsInitializer() {}
  
  /* Error */
  public static int initialize(android.content.Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: ldc 21
    //   6: invokestatic 26	com/google/android/gms/common/internal/zzbq:zza	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   9: pop
    //   10: getstatic 28	com/google/android/gms/maps/MapsInitializer:zza	Z
    //   13: istore_2
    //   14: iload_2
    //   15: ifeq +8 -> 23
    //   18: ldc 2
    //   20: monitorexit
    //   21: iconst_0
    //   22: ireturn
    //   23: aload_0
    //   24: invokestatic 33	com/google/android/gms/maps/internal/zzbz:zza	(Landroid/content/Context;)Lcom/google/android/gms/maps/internal/zze;
    //   27: astore_0
    //   28: aload_0
    //   29: invokeinterface 38 1 0
    //   34: invokestatic 43	com/google/android/gms/maps/CameraUpdateFactory:zza	(Lcom/google/android/gms/maps/internal/ICameraUpdateFactoryDelegate;)V
    //   37: aload_0
    //   38: invokeinterface 47 1 0
    //   43: invokestatic 52	com/google/android/gms/maps/model/BitmapDescriptorFactory:zza	(Lcom/google/android/gms/maps/model/internal/zza;)V
    //   46: iconst_1
    //   47: putstatic 28	com/google/android/gms/maps/MapsInitializer:zza	Z
    //   50: ldc 2
    //   52: monitorexit
    //   53: iconst_0
    //   54: ireturn
    //   55: astore_0
    //   56: new 54	com/google/android/gms/maps/model/RuntimeRemoteException
    //   59: dup
    //   60: aload_0
    //   61: invokespecial 57	com/google/android/gms/maps/model/RuntimeRemoteException:<init>	(Landroid/os/RemoteException;)V
    //   64: athrow
    //   65: astore_0
    //   66: aload_0
    //   67: getfield 61	com/google/android/gms/common/GooglePlayServicesNotAvailableException:errorCode	I
    //   70: istore_1
    //   71: ldc 2
    //   73: monitorexit
    //   74: iload_1
    //   75: ireturn
    //   76: astore_0
    //   77: ldc 2
    //   79: monitorexit
    //   80: aload_0
    //   81: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	82	0	paramContext	android.content.Context
    //   70	5	1	i	int
    //   13	2	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   28	46	55	android/os/RemoteException
    //   23	28	65	com/google/android/gms/common/GooglePlayServicesNotAvailableException
    //   3	14	76	finally
    //   23	28	76	finally
    //   28	46	76	finally
    //   46	50	76	finally
    //   56	65	76	finally
    //   66	71	76	finally
  }
}

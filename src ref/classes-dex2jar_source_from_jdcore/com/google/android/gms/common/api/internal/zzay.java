package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;

abstract class zzay
  implements Runnable
{
  private zzay(zzao paramZzao) {}
  
  /* Error */
  @WorkerThread
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 12	com/google/android/gms/common/api/internal/zzay:zza	Lcom/google/android/gms/common/api/internal/zzao;
    //   4: invokestatic 29	com/google/android/gms/common/api/internal/zzao:zzb	(Lcom/google/android/gms/common/api/internal/zzao;)Ljava/util/concurrent/locks/Lock;
    //   7: invokeinterface 34 1 0
    //   12: invokestatic 40	java/lang/Thread:interrupted	()Z
    //   15: istore_1
    //   16: iload_1
    //   17: ifeq +16 -> 33
    //   20: aload_0
    //   21: getfield 12	com/google/android/gms/common/api/internal/zzay:zza	Lcom/google/android/gms/common/api/internal/zzao;
    //   24: invokestatic 29	com/google/android/gms/common/api/internal/zzao:zzb	(Lcom/google/android/gms/common/api/internal/zzao;)Ljava/util/concurrent/locks/Lock;
    //   27: invokeinterface 43 1 0
    //   32: return
    //   33: aload_0
    //   34: invokevirtual 45	com/google/android/gms/common/api/internal/zzay:zza	()V
    //   37: goto -17 -> 20
    //   40: astore_2
    //   41: goto +18 -> 59
    //   44: astore_2
    //   45: aload_0
    //   46: getfield 12	com/google/android/gms/common/api/internal/zzay:zza	Lcom/google/android/gms/common/api/internal/zzao;
    //   49: invokestatic 49	com/google/android/gms/common/api/internal/zzao:zzc	(Lcom/google/android/gms/common/api/internal/zzao;)Lcom/google/android/gms/common/api/internal/zzbi;
    //   52: aload_2
    //   53: invokevirtual 54	com/google/android/gms/common/api/internal/zzbi:zza	(Ljava/lang/RuntimeException;)V
    //   56: goto -36 -> 20
    //   59: aload_0
    //   60: getfield 12	com/google/android/gms/common/api/internal/zzay:zza	Lcom/google/android/gms/common/api/internal/zzao;
    //   63: invokestatic 29	com/google/android/gms/common/api/internal/zzao:zzb	(Lcom/google/android/gms/common/api/internal/zzao;)Ljava/util/concurrent/locks/Lock;
    //   66: invokeinterface 43 1 0
    //   71: aload_2
    //   72: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	73	0	this	zzay
    //   15	2	1	bool	boolean
    //   40	1	2	localObject	Object
    //   44	28	2	localRuntimeException	RuntimeException
    // Exception table:
    //   from	to	target	type
    //   12	16	40	finally
    //   33	37	40	finally
    //   45	56	40	finally
    //   12	16	44	java/lang/RuntimeException
    //   33	37	44	java/lang/RuntimeException
  }
  
  @WorkerThread
  protected abstract void zza();
}

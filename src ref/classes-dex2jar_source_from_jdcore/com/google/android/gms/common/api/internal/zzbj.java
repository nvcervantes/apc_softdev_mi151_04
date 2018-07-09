package com.google.android.gms.common.api.internal;

abstract class zzbj
{
  private final zzbh zza;
  
  protected zzbj(zzbh paramZzbh)
  {
    zza = paramZzbh;
  }
  
  protected abstract void zza();
  
  /* Error */
  public final void zza(zzbi paramZzbi)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 20	com/google/android/gms/common/api/internal/zzbi:zza	(Lcom/google/android/gms/common/api/internal/zzbi;)Ljava/util/concurrent/locks/Lock;
    //   4: invokeinterface 25 1 0
    //   9: aload_1
    //   10: invokestatic 29	com/google/android/gms/common/api/internal/zzbi:zzb	(Lcom/google/android/gms/common/api/internal/zzbi;)Lcom/google/android/gms/common/api/internal/zzbh;
    //   13: astore_2
    //   14: aload_0
    //   15: getfield 13	com/google/android/gms/common/api/internal/zzbj:zza	Lcom/google/android/gms/common/api/internal/zzbh;
    //   18: astore_3
    //   19: aload_2
    //   20: aload_3
    //   21: if_acmpeq +13 -> 34
    //   24: aload_1
    //   25: invokestatic 20	com/google/android/gms/common/api/internal/zzbi:zza	(Lcom/google/android/gms/common/api/internal/zzbi;)Ljava/util/concurrent/locks/Lock;
    //   28: invokeinterface 32 1 0
    //   33: return
    //   34: aload_0
    //   35: invokevirtual 34	com/google/android/gms/common/api/internal/zzbj:zza	()V
    //   38: goto -14 -> 24
    //   41: astore_2
    //   42: aload_1
    //   43: invokestatic 20	com/google/android/gms/common/api/internal/zzbi:zza	(Lcom/google/android/gms/common/api/internal/zzbi;)Ljava/util/concurrent/locks/Lock;
    //   46: invokeinterface 32 1 0
    //   51: aload_2
    //   52: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	53	0	this	zzbj
    //   0	53	1	paramZzbi	zzbi
    //   13	7	2	localZzbh1	zzbh
    //   41	11	2	localObject	Object
    //   18	3	3	localZzbh2	zzbh
    // Exception table:
    //   from	to	target	type
    //   9	19	41	finally
    //   34	38	41	finally
  }
}

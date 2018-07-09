package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.locks.Lock;

final class zzy
  implements zzcd
{
  private zzy(zzv paramZzv) {}
  
  /* Error */
  public final void zza(int paramInt, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 12	com/google/android/gms/common/api/internal/zzy:zza	Lcom/google/android/gms/common/api/internal/zzv;
    //   4: invokestatic 25	com/google/android/gms/common/api/internal/zzv:zza	(Lcom/google/android/gms/common/api/internal/zzv;)Ljava/util/concurrent/locks/Lock;
    //   7: invokeinterface 30 1 0
    //   12: aload_0
    //   13: getfield 12	com/google/android/gms/common/api/internal/zzy:zza	Lcom/google/android/gms/common/api/internal/zzv;
    //   16: invokestatic 34	com/google/android/gms/common/api/internal/zzv:zzc	(Lcom/google/android/gms/common/api/internal/zzv;)Z
    //   19: ifeq +34 -> 53
    //   22: aload_0
    //   23: getfield 12	com/google/android/gms/common/api/internal/zzy:zza	Lcom/google/android/gms/common/api/internal/zzv;
    //   26: iconst_0
    //   27: invokestatic 37	com/google/android/gms/common/api/internal/zzv:zza	(Lcom/google/android/gms/common/api/internal/zzv;Z)Z
    //   30: pop
    //   31: aload_0
    //   32: getfield 12	com/google/android/gms/common/api/internal/zzy:zza	Lcom/google/android/gms/common/api/internal/zzv;
    //   35: iload_1
    //   36: iload_2
    //   37: invokestatic 40	com/google/android/gms/common/api/internal/zzv:zza	(Lcom/google/android/gms/common/api/internal/zzv;IZ)V
    //   40: aload_0
    //   41: getfield 12	com/google/android/gms/common/api/internal/zzy:zza	Lcom/google/android/gms/common/api/internal/zzv;
    //   44: invokestatic 25	com/google/android/gms/common/api/internal/zzv:zza	(Lcom/google/android/gms/common/api/internal/zzv;)Ljava/util/concurrent/locks/Lock;
    //   47: invokeinterface 43 1 0
    //   52: return
    //   53: aload_0
    //   54: getfield 12	com/google/android/gms/common/api/internal/zzy:zza	Lcom/google/android/gms/common/api/internal/zzv;
    //   57: iconst_1
    //   58: invokestatic 37	com/google/android/gms/common/api/internal/zzv:zza	(Lcom/google/android/gms/common/api/internal/zzv;Z)Z
    //   61: pop
    //   62: aload_0
    //   63: getfield 12	com/google/android/gms/common/api/internal/zzy:zza	Lcom/google/android/gms/common/api/internal/zzv;
    //   66: invokestatic 47	com/google/android/gms/common/api/internal/zzv:zzf	(Lcom/google/android/gms/common/api/internal/zzv;)Lcom/google/android/gms/common/api/internal/zzbi;
    //   69: iload_1
    //   70: invokevirtual 53	com/google/android/gms/common/api/internal/zzbi:onConnectionSuspended	(I)V
    //   73: goto -33 -> 40
    //   76: astore_3
    //   77: aload_0
    //   78: getfield 12	com/google/android/gms/common/api/internal/zzy:zza	Lcom/google/android/gms/common/api/internal/zzv;
    //   81: invokestatic 25	com/google/android/gms/common/api/internal/zzv:zza	(Lcom/google/android/gms/common/api/internal/zzv;)Ljava/util/concurrent/locks/Lock;
    //   84: invokeinterface 43 1 0
    //   89: aload_3
    //   90: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	this	zzy
    //   0	91	1	paramInt	int
    //   0	91	2	paramBoolean	boolean
    //   76	14	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   12	40	76	finally
    //   53	73	76	finally
  }
  
  public final void zza(@Nullable Bundle paramBundle)
  {
    zzv.zza(zza).lock();
    try
    {
      zzv.zzb(zza, ConnectionResult.zza);
      zzv.zzb(zza);
      return;
    }
    finally
    {
      zzv.zza(zza).unlock();
    }
  }
  
  public final void zza(@NonNull ConnectionResult paramConnectionResult)
  {
    zzv.zza(zza).lock();
    try
    {
      zzv.zzb(zza, paramConnectionResult);
      zzv.zzb(zza);
      return;
    }
    finally
    {
      zzv.zza(zza).unlock();
    }
  }
}

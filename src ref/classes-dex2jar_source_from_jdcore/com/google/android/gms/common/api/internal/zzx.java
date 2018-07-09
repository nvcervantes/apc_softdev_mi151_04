package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.locks.Lock;

final class zzx
  implements zzcd
{
  private zzx(zzv paramZzv) {}
  
  /* Error */
  public final void zza(int paramInt, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 12	com/google/android/gms/common/api/internal/zzx:zza	Lcom/google/android/gms/common/api/internal/zzv;
    //   4: invokestatic 25	com/google/android/gms/common/api/internal/zzv:zza	(Lcom/google/android/gms/common/api/internal/zzv;)Ljava/util/concurrent/locks/Lock;
    //   7: invokeinterface 30 1 0
    //   12: aload_0
    //   13: getfield 12	com/google/android/gms/common/api/internal/zzx:zza	Lcom/google/android/gms/common/api/internal/zzv;
    //   16: invokestatic 34	com/google/android/gms/common/api/internal/zzv:zzc	(Lcom/google/android/gms/common/api/internal/zzv;)Z
    //   19: ifne +62 -> 81
    //   22: aload_0
    //   23: getfield 12	com/google/android/gms/common/api/internal/zzx:zza	Lcom/google/android/gms/common/api/internal/zzv;
    //   26: invokestatic 38	com/google/android/gms/common/api/internal/zzv:zzd	(Lcom/google/android/gms/common/api/internal/zzv;)Lcom/google/android/gms/common/ConnectionResult;
    //   29: ifnull +52 -> 81
    //   32: aload_0
    //   33: getfield 12	com/google/android/gms/common/api/internal/zzx:zza	Lcom/google/android/gms/common/api/internal/zzv;
    //   36: invokestatic 38	com/google/android/gms/common/api/internal/zzv:zzd	(Lcom/google/android/gms/common/api/internal/zzv;)Lcom/google/android/gms/common/ConnectionResult;
    //   39: invokevirtual 44	com/google/android/gms/common/ConnectionResult:isSuccess	()Z
    //   42: ifne +6 -> 48
    //   45: goto +36 -> 81
    //   48: aload_0
    //   49: getfield 12	com/google/android/gms/common/api/internal/zzx:zza	Lcom/google/android/gms/common/api/internal/zzv;
    //   52: iconst_1
    //   53: invokestatic 47	com/google/android/gms/common/api/internal/zzv:zza	(Lcom/google/android/gms/common/api/internal/zzv;Z)Z
    //   56: pop
    //   57: aload_0
    //   58: getfield 12	com/google/android/gms/common/api/internal/zzx:zza	Lcom/google/android/gms/common/api/internal/zzv;
    //   61: invokestatic 51	com/google/android/gms/common/api/internal/zzv:zze	(Lcom/google/android/gms/common/api/internal/zzv;)Lcom/google/android/gms/common/api/internal/zzbi;
    //   64: iload_1
    //   65: invokevirtual 57	com/google/android/gms/common/api/internal/zzbi:onConnectionSuspended	(I)V
    //   68: aload_0
    //   69: getfield 12	com/google/android/gms/common/api/internal/zzx:zza	Lcom/google/android/gms/common/api/internal/zzv;
    //   72: invokestatic 25	com/google/android/gms/common/api/internal/zzv:zza	(Lcom/google/android/gms/common/api/internal/zzv;)Ljava/util/concurrent/locks/Lock;
    //   75: invokeinterface 60 1 0
    //   80: return
    //   81: aload_0
    //   82: getfield 12	com/google/android/gms/common/api/internal/zzx:zza	Lcom/google/android/gms/common/api/internal/zzv;
    //   85: iconst_0
    //   86: invokestatic 47	com/google/android/gms/common/api/internal/zzv:zza	(Lcom/google/android/gms/common/api/internal/zzv;Z)Z
    //   89: pop
    //   90: aload_0
    //   91: getfield 12	com/google/android/gms/common/api/internal/zzx:zza	Lcom/google/android/gms/common/api/internal/zzv;
    //   94: iload_1
    //   95: iload_2
    //   96: invokestatic 63	com/google/android/gms/common/api/internal/zzv:zza	(Lcom/google/android/gms/common/api/internal/zzv;IZ)V
    //   99: goto -31 -> 68
    //   102: astore_3
    //   103: aload_0
    //   104: getfield 12	com/google/android/gms/common/api/internal/zzx:zza	Lcom/google/android/gms/common/api/internal/zzv;
    //   107: invokestatic 25	com/google/android/gms/common/api/internal/zzv:zza	(Lcom/google/android/gms/common/api/internal/zzv;)Ljava/util/concurrent/locks/Lock;
    //   110: invokeinterface 60 1 0
    //   115: aload_3
    //   116: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	117	0	this	zzx
    //   0	117	1	paramInt	int
    //   0	117	2	paramBoolean	boolean
    //   102	14	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   12	45	102	finally
    //   48	68	102	finally
    //   81	99	102	finally
  }
  
  public final void zza(@Nullable Bundle paramBundle)
  {
    zzv.zza(zza).lock();
    try
    {
      zzv.zza(zza, paramBundle);
      zzv.zza(zza, ConnectionResult.zza);
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
      zzv.zza(zza, paramConnectionResult);
      zzv.zzb(zza);
      return;
    }
    finally
    {
      zzv.zza(zza).unlock();
    }
  }
}

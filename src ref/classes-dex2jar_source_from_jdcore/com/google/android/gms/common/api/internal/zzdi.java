package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Result;

final class zzdi
  implements Runnable
{
  zzdi(zzdh paramZzdh, Result paramResult) {}
  
  /* Error */
  @android.support.annotation.WorkerThread
  public final void run()
  {
    // Byte code:
    //   0: getstatic 30	com/google/android/gms/common/api/internal/BasePendingResult:zzc	Ljava/lang/ThreadLocal;
    //   3: iconst_1
    //   4: invokestatic 36	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   7: invokevirtual 42	java/lang/ThreadLocal:set	(Ljava/lang/Object;)V
    //   10: aload_0
    //   11: getfield 14	com/google/android/gms/common/api/internal/zzdi:zzb	Lcom/google/android/gms/common/api/internal/zzdh;
    //   14: invokestatic 47	com/google/android/gms/common/api/internal/zzdh:zza	(Lcom/google/android/gms/common/api/internal/zzdh;)Lcom/google/android/gms/common/api/ResultTransform;
    //   17: aload_0
    //   18: getfield 16	com/google/android/gms/common/api/internal/zzdi:zza	Lcom/google/android/gms/common/api/Result;
    //   21: invokevirtual 53	com/google/android/gms/common/api/ResultTransform:onSuccess	(Lcom/google/android/gms/common/api/Result;)Lcom/google/android/gms/common/api/PendingResult;
    //   24: astore_1
    //   25: aload_0
    //   26: getfield 14	com/google/android/gms/common/api/internal/zzdi:zzb	Lcom/google/android/gms/common/api/internal/zzdh;
    //   29: invokestatic 56	com/google/android/gms/common/api/internal/zzdh:zzb	(Lcom/google/android/gms/common/api/internal/zzdh;)Lcom/google/android/gms/common/api/internal/zzdj;
    //   32: aload_0
    //   33: getfield 14	com/google/android/gms/common/api/internal/zzdi:zzb	Lcom/google/android/gms/common/api/internal/zzdh;
    //   36: invokestatic 56	com/google/android/gms/common/api/internal/zzdh:zzb	(Lcom/google/android/gms/common/api/internal/zzdh;)Lcom/google/android/gms/common/api/internal/zzdj;
    //   39: iconst_0
    //   40: aload_1
    //   41: invokevirtual 62	com/google/android/gms/common/api/internal/zzdj:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
    //   44: invokevirtual 66	com/google/android/gms/common/api/internal/zzdj:sendMessage	(Landroid/os/Message;)Z
    //   47: pop
    //   48: getstatic 30	com/google/android/gms/common/api/internal/BasePendingResult:zzc	Ljava/lang/ThreadLocal;
    //   51: iconst_0
    //   52: invokestatic 36	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   55: invokevirtual 42	java/lang/ThreadLocal:set	(Ljava/lang/Object;)V
    //   58: aload_0
    //   59: getfield 14	com/google/android/gms/common/api/internal/zzdi:zzb	Lcom/google/android/gms/common/api/internal/zzdh;
    //   62: aload_0
    //   63: getfield 16	com/google/android/gms/common/api/internal/zzdi:zza	Lcom/google/android/gms/common/api/Result;
    //   66: invokestatic 68	com/google/android/gms/common/api/internal/zzdh:zza	(Lcom/google/android/gms/common/api/internal/zzdh;Lcom/google/android/gms/common/api/Result;)V
    //   69: aload_0
    //   70: getfield 14	com/google/android/gms/common/api/internal/zzdi:zzb	Lcom/google/android/gms/common/api/internal/zzdh;
    //   73: invokestatic 71	com/google/android/gms/common/api/internal/zzdh:zzc	(Lcom/google/android/gms/common/api/internal/zzdh;)Ljava/lang/ref/WeakReference;
    //   76: invokevirtual 77	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   79: checkcast 79	com/google/android/gms/common/api/GoogleApiClient
    //   82: astore_1
    //   83: aload_1
    //   84: ifnull +11 -> 95
    //   87: aload_1
    //   88: aload_0
    //   89: getfield 14	com/google/android/gms/common/api/internal/zzdi:zzb	Lcom/google/android/gms/common/api/internal/zzdh;
    //   92: invokevirtual 82	com/google/android/gms/common/api/GoogleApiClient:zzb	(Lcom/google/android/gms/common/api/internal/zzdh;)V
    //   95: return
    //   96: astore_1
    //   97: goto +75 -> 172
    //   100: astore_1
    //   101: aload_0
    //   102: getfield 14	com/google/android/gms/common/api/internal/zzdi:zzb	Lcom/google/android/gms/common/api/internal/zzdh;
    //   105: invokestatic 56	com/google/android/gms/common/api/internal/zzdh:zzb	(Lcom/google/android/gms/common/api/internal/zzdh;)Lcom/google/android/gms/common/api/internal/zzdj;
    //   108: aload_0
    //   109: getfield 14	com/google/android/gms/common/api/internal/zzdi:zzb	Lcom/google/android/gms/common/api/internal/zzdh;
    //   112: invokestatic 56	com/google/android/gms/common/api/internal/zzdh:zzb	(Lcom/google/android/gms/common/api/internal/zzdh;)Lcom/google/android/gms/common/api/internal/zzdj;
    //   115: iconst_1
    //   116: aload_1
    //   117: invokevirtual 62	com/google/android/gms/common/api/internal/zzdj:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
    //   120: invokevirtual 66	com/google/android/gms/common/api/internal/zzdj:sendMessage	(Landroid/os/Message;)Z
    //   123: pop
    //   124: getstatic 30	com/google/android/gms/common/api/internal/BasePendingResult:zzc	Ljava/lang/ThreadLocal;
    //   127: iconst_0
    //   128: invokestatic 36	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   131: invokevirtual 42	java/lang/ThreadLocal:set	(Ljava/lang/Object;)V
    //   134: aload_0
    //   135: getfield 14	com/google/android/gms/common/api/internal/zzdi:zzb	Lcom/google/android/gms/common/api/internal/zzdh;
    //   138: aload_0
    //   139: getfield 16	com/google/android/gms/common/api/internal/zzdi:zza	Lcom/google/android/gms/common/api/Result;
    //   142: invokestatic 68	com/google/android/gms/common/api/internal/zzdh:zza	(Lcom/google/android/gms/common/api/internal/zzdh;Lcom/google/android/gms/common/api/Result;)V
    //   145: aload_0
    //   146: getfield 14	com/google/android/gms/common/api/internal/zzdi:zzb	Lcom/google/android/gms/common/api/internal/zzdh;
    //   149: invokestatic 71	com/google/android/gms/common/api/internal/zzdh:zzc	(Lcom/google/android/gms/common/api/internal/zzdh;)Ljava/lang/ref/WeakReference;
    //   152: invokevirtual 77	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   155: checkcast 79	com/google/android/gms/common/api/GoogleApiClient
    //   158: astore_1
    //   159: aload_1
    //   160: ifnull +11 -> 171
    //   163: aload_1
    //   164: aload_0
    //   165: getfield 14	com/google/android/gms/common/api/internal/zzdi:zzb	Lcom/google/android/gms/common/api/internal/zzdh;
    //   168: invokevirtual 82	com/google/android/gms/common/api/GoogleApiClient:zzb	(Lcom/google/android/gms/common/api/internal/zzdh;)V
    //   171: return
    //   172: getstatic 30	com/google/android/gms/common/api/internal/BasePendingResult:zzc	Ljava/lang/ThreadLocal;
    //   175: iconst_0
    //   176: invokestatic 36	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   179: invokevirtual 42	java/lang/ThreadLocal:set	(Ljava/lang/Object;)V
    //   182: aload_0
    //   183: getfield 14	com/google/android/gms/common/api/internal/zzdi:zzb	Lcom/google/android/gms/common/api/internal/zzdh;
    //   186: aload_0
    //   187: getfield 16	com/google/android/gms/common/api/internal/zzdi:zza	Lcom/google/android/gms/common/api/Result;
    //   190: invokestatic 68	com/google/android/gms/common/api/internal/zzdh:zza	(Lcom/google/android/gms/common/api/internal/zzdh;Lcom/google/android/gms/common/api/Result;)V
    //   193: aload_0
    //   194: getfield 14	com/google/android/gms/common/api/internal/zzdi:zzb	Lcom/google/android/gms/common/api/internal/zzdh;
    //   197: invokestatic 71	com/google/android/gms/common/api/internal/zzdh:zzc	(Lcom/google/android/gms/common/api/internal/zzdh;)Ljava/lang/ref/WeakReference;
    //   200: invokevirtual 77	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   203: checkcast 79	com/google/android/gms/common/api/GoogleApiClient
    //   206: astore_2
    //   207: aload_2
    //   208: ifnull +11 -> 219
    //   211: aload_2
    //   212: aload_0
    //   213: getfield 14	com/google/android/gms/common/api/internal/zzdi:zzb	Lcom/google/android/gms/common/api/internal/zzdh;
    //   216: invokevirtual 82	com/google/android/gms/common/api/GoogleApiClient:zzb	(Lcom/google/android/gms/common/api/internal/zzdh;)V
    //   219: aload_1
    //   220: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	221	0	this	zzdi
    //   24	64	1	localObject1	Object
    //   96	1	1	localObject2	Object
    //   100	17	1	localRuntimeException	RuntimeException
    //   158	62	1	localGoogleApiClient1	com.google.android.gms.common.api.GoogleApiClient
    //   206	6	2	localGoogleApiClient2	com.google.android.gms.common.api.GoogleApiClient
    // Exception table:
    //   from	to	target	type
    //   0	48	96	finally
    //   101	124	96	finally
    //   0	48	100	java/lang/RuntimeException
  }
}

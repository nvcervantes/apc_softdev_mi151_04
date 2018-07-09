package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzj;
import java.lang.ref.WeakReference;

final class zzaq
  implements zzj
{
  private final WeakReference<zzao> zza;
  private final Api<?> zzb;
  private final boolean zzc;
  
  public zzaq(zzao paramZzao, Api<?> paramApi, boolean paramBoolean)
  {
    zza = new WeakReference(paramZzao);
    zzb = paramApi;
    zzc = paramBoolean;
  }
  
  /* Error */
  public final void zza(@android.support.annotation.NonNull com.google.android.gms.common.ConnectionResult paramConnectionResult)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 26	com/google/android/gms/common/api/internal/zzaq:zza	Ljava/lang/ref/WeakReference;
    //   4: invokevirtual 40	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   7: checkcast 42	com/google/android/gms/common/api/internal/zzao
    //   10: astore_3
    //   11: aload_3
    //   12: ifnonnull +4 -> 16
    //   15: return
    //   16: invokestatic 48	android/os/Looper:myLooper	()Landroid/os/Looper;
    //   19: aload_3
    //   20: invokestatic 51	com/google/android/gms/common/api/internal/zzao:zzc	(Lcom/google/android/gms/common/api/internal/zzao;)Lcom/google/android/gms/common/api/internal/zzbi;
    //   23: getfield 57	com/google/android/gms/common/api/internal/zzbi:zzd	Lcom/google/android/gms/common/api/internal/zzba;
    //   26: invokevirtual 61	com/google/android/gms/common/api/GoogleApiClient:zzc	()Landroid/os/Looper;
    //   29: if_acmpne +8 -> 37
    //   32: iconst_1
    //   33: istore_2
    //   34: goto +5 -> 39
    //   37: iconst_0
    //   38: istore_2
    //   39: iload_2
    //   40: ldc 63
    //   42: invokestatic 68	com/google/android/gms/common/internal/zzbq:zza	(ZLjava/lang/Object;)V
    //   45: aload_3
    //   46: invokestatic 71	com/google/android/gms/common/api/internal/zzao:zzb	(Lcom/google/android/gms/common/api/internal/zzao;)Ljava/util/concurrent/locks/Lock;
    //   49: invokeinterface 76 1 0
    //   54: aload_3
    //   55: iconst_0
    //   56: invokestatic 79	com/google/android/gms/common/api/internal/zzao:zza	(Lcom/google/android/gms/common/api/internal/zzao;I)Z
    //   59: istore_2
    //   60: iload_2
    //   61: ifne +13 -> 74
    //   64: aload_3
    //   65: invokestatic 71	com/google/android/gms/common/api/internal/zzao:zzb	(Lcom/google/android/gms/common/api/internal/zzao;)Ljava/util/concurrent/locks/Lock;
    //   68: invokeinterface 82 1 0
    //   73: return
    //   74: aload_1
    //   75: invokevirtual 88	com/google/android/gms/common/ConnectionResult:isSuccess	()Z
    //   78: ifne +16 -> 94
    //   81: aload_3
    //   82: aload_1
    //   83: aload_0
    //   84: getfield 28	com/google/android/gms/common/api/internal/zzaq:zzb	Lcom/google/android/gms/common/api/Api;
    //   87: aload_0
    //   88: getfield 30	com/google/android/gms/common/api/internal/zzaq:zzc	Z
    //   91: invokestatic 91	com/google/android/gms/common/api/internal/zzao:zza	(Lcom/google/android/gms/common/api/internal/zzao;Lcom/google/android/gms/common/ConnectionResult;Lcom/google/android/gms/common/api/Api;Z)V
    //   94: aload_3
    //   95: invokestatic 95	com/google/android/gms/common/api/internal/zzao:zzj	(Lcom/google/android/gms/common/api/internal/zzao;)Z
    //   98: ifeq -34 -> 64
    //   101: aload_3
    //   102: invokestatic 99	com/google/android/gms/common/api/internal/zzao:zzi	(Lcom/google/android/gms/common/api/internal/zzao;)V
    //   105: goto -41 -> 64
    //   108: astore_1
    //   109: aload_3
    //   110: invokestatic 71	com/google/android/gms/common/api/internal/zzao:zzb	(Lcom/google/android/gms/common/api/internal/zzao;)Ljava/util/concurrent/locks/Lock;
    //   113: invokeinterface 82 1 0
    //   118: aload_1
    //   119: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	120	0	this	zzaq
    //   0	120	1	paramConnectionResult	com.google.android.gms.common.ConnectionResult
    //   33	28	2	bool	boolean
    //   10	100	3	localZzao	zzao
    // Exception table:
    //   from	to	target	type
    //   54	60	108	finally
    //   74	94	108	finally
    //   94	105	108	finally
  }
}

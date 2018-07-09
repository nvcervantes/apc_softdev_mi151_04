package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import java.util.Map;

final class zzad
  implements OnCompleteListener<Map<zzh<?>, String>>
{
  private zzcu zza;
  
  zzad(zzaa paramZzaa, zzcu paramZzcu)
  {
    zza = paramZzcu;
  }
  
  /* Error */
  public final void onComplete(@android.support.annotation.NonNull com.google.android.gms.tasks.Task<Map<zzh<?>, String>> paramTask)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   4: invokestatic 29	com/google/android/gms/common/api/internal/zzaa:zza	(Lcom/google/android/gms/common/api/internal/zzaa;)Ljava/util/concurrent/locks/Lock;
    //   7: invokeinterface 34 1 0
    //   12: aload_0
    //   13: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   16: invokestatic 37	com/google/android/gms/common/api/internal/zzaa:zzb	(Lcom/google/android/gms/common/api/internal/zzaa;)Z
    //   19: ifne +27 -> 46
    //   22: aload_0
    //   23: getfield 20	com/google/android/gms/common/api/internal/zzad:zza	Lcom/google/android/gms/common/api/internal/zzcu;
    //   26: astore_1
    //   27: aload_1
    //   28: invokeinterface 41 1 0
    //   33: aload_0
    //   34: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   37: invokestatic 29	com/google/android/gms/common/api/internal/zzaa:zza	(Lcom/google/android/gms/common/api/internal/zzaa;)Ljava/util/concurrent/locks/Lock;
    //   40: invokeinterface 44 1 0
    //   45: return
    //   46: aload_1
    //   47: invokevirtual 50	com/google/android/gms/tasks/Task:isSuccessful	()Z
    //   50: ifeq +90 -> 140
    //   53: aload_0
    //   54: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   57: new 52	android/support/v4/util/ArrayMap
    //   60: dup
    //   61: aload_0
    //   62: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   65: invokestatic 56	com/google/android/gms/common/api/internal/zzaa:zzm	(Lcom/google/android/gms/common/api/internal/zzaa;)Ljava/util/Map;
    //   68: invokeinterface 62 1 0
    //   73: invokespecial 65	android/support/v4/util/ArrayMap:<init>	(I)V
    //   76: invokestatic 68	com/google/android/gms/common/api/internal/zzaa:zzb	(Lcom/google/android/gms/common/api/internal/zzaa;Ljava/util/Map;)Ljava/util/Map;
    //   79: pop
    //   80: aload_0
    //   81: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   84: invokestatic 56	com/google/android/gms/common/api/internal/zzaa:zzm	(Lcom/google/android/gms/common/api/internal/zzaa;)Ljava/util/Map;
    //   87: invokeinterface 72 1 0
    //   92: invokeinterface 78 1 0
    //   97: astore_1
    //   98: aload_1
    //   99: invokeinterface 83 1 0
    //   104: ifeq +235 -> 339
    //   107: aload_1
    //   108: invokeinterface 87 1 0
    //   113: checkcast 89	com/google/android/gms/common/api/internal/zzz
    //   116: astore_2
    //   117: aload_0
    //   118: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   121: invokestatic 92	com/google/android/gms/common/api/internal/zzaa:zzg	(Lcom/google/android/gms/common/api/internal/zzaa;)Ljava/util/Map;
    //   124: aload_2
    //   125: invokevirtual 98	com/google/android/gms/common/api/GoogleApi:zzc	()Lcom/google/android/gms/common/api/internal/zzh;
    //   128: getstatic 103	com/google/android/gms/common/ConnectionResult:zza	Lcom/google/android/gms/common/ConnectionResult;
    //   131: invokeinterface 107 3 0
    //   136: pop
    //   137: goto -39 -> 98
    //   140: aload_1
    //   141: invokevirtual 111	com/google/android/gms/tasks/Task:getException	()Ljava/lang/Exception;
    //   144: instanceof 113
    //   147: ifeq +169 -> 316
    //   150: aload_1
    //   151: invokevirtual 111	com/google/android/gms/tasks/Task:getException	()Ljava/lang/Exception;
    //   154: checkcast 113	com/google/android/gms/common/api/AvailabilityException
    //   157: astore_3
    //   158: aload_0
    //   159: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   162: invokestatic 116	com/google/android/gms/common/api/internal/zzaa:zze	(Lcom/google/android/gms/common/api/internal/zzaa;)Z
    //   165: ifeq +136 -> 301
    //   168: aload_0
    //   169: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   172: new 52	android/support/v4/util/ArrayMap
    //   175: dup
    //   176: aload_0
    //   177: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   180: invokestatic 56	com/google/android/gms/common/api/internal/zzaa:zzm	(Lcom/google/android/gms/common/api/internal/zzaa;)Ljava/util/Map;
    //   183: invokeinterface 62 1 0
    //   188: invokespecial 65	android/support/v4/util/ArrayMap:<init>	(I)V
    //   191: invokestatic 68	com/google/android/gms/common/api/internal/zzaa:zzb	(Lcom/google/android/gms/common/api/internal/zzaa;Ljava/util/Map;)Ljava/util/Map;
    //   194: pop
    //   195: aload_0
    //   196: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   199: invokestatic 56	com/google/android/gms/common/api/internal/zzaa:zzm	(Lcom/google/android/gms/common/api/internal/zzaa;)Ljava/util/Map;
    //   202: invokeinterface 72 1 0
    //   207: invokeinterface 78 1 0
    //   212: astore 4
    //   214: aload 4
    //   216: invokeinterface 83 1 0
    //   221: ifeq +118 -> 339
    //   224: aload 4
    //   226: invokeinterface 87 1 0
    //   231: checkcast 89	com/google/android/gms/common/api/internal/zzz
    //   234: astore_1
    //   235: aload_1
    //   236: invokevirtual 98	com/google/android/gms/common/api/GoogleApi:zzc	()Lcom/google/android/gms/common/api/internal/zzh;
    //   239: astore 5
    //   241: aload_3
    //   242: aload_1
    //   243: invokevirtual 120	com/google/android/gms/common/api/AvailabilityException:getConnectionResult	(Lcom/google/android/gms/common/api/GoogleApi;)Lcom/google/android/gms/common/ConnectionResult;
    //   246: astore_2
    //   247: aload_0
    //   248: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   251: aload_1
    //   252: aload_2
    //   253: invokestatic 123	com/google/android/gms/common/api/internal/zzaa:zza	(Lcom/google/android/gms/common/api/internal/zzaa;Lcom/google/android/gms/common/api/internal/zzz;Lcom/google/android/gms/common/ConnectionResult;)Z
    //   256: ifeq +34 -> 290
    //   259: aload_0
    //   260: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   263: invokestatic 92	com/google/android/gms/common/api/internal/zzaa:zzg	(Lcom/google/android/gms/common/api/internal/zzaa;)Ljava/util/Map;
    //   266: astore_1
    //   267: new 100	com/google/android/gms/common/ConnectionResult
    //   270: dup
    //   271: bipush 16
    //   273: invokespecial 124	com/google/android/gms/common/ConnectionResult:<init>	(I)V
    //   276: astore_2
    //   277: aload_1
    //   278: aload 5
    //   280: aload_2
    //   281: invokeinterface 107 3 0
    //   286: pop
    //   287: goto -73 -> 214
    //   290: aload_0
    //   291: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   294: invokestatic 92	com/google/android/gms/common/api/internal/zzaa:zzg	(Lcom/google/android/gms/common/api/internal/zzaa;)Ljava/util/Map;
    //   297: astore_1
    //   298: goto -21 -> 277
    //   301: aload_0
    //   302: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   305: aload_3
    //   306: invokevirtual 127	com/google/android/gms/common/api/AvailabilityException:zza	()Landroid/support/v4/util/ArrayMap;
    //   309: invokestatic 68	com/google/android/gms/common/api/internal/zzaa:zzb	(Lcom/google/android/gms/common/api/internal/zzaa;Ljava/util/Map;)Ljava/util/Map;
    //   312: pop
    //   313: goto +26 -> 339
    //   316: ldc -127
    //   318: ldc -125
    //   320: aload_1
    //   321: invokevirtual 111	com/google/android/gms/tasks/Task:getException	()Ljava/lang/Exception;
    //   324: invokestatic 137	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   327: pop
    //   328: aload_0
    //   329: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   332: invokestatic 143	java/util/Collections:emptyMap	()Ljava/util/Map;
    //   335: invokestatic 68	com/google/android/gms/common/api/internal/zzaa:zzb	(Lcom/google/android/gms/common/api/internal/zzaa;Ljava/util/Map;)Ljava/util/Map;
    //   338: pop
    //   339: aload_0
    //   340: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   343: invokevirtual 146	com/google/android/gms/common/api/internal/zzaa:zzd	()Z
    //   346: ifeq +58 -> 404
    //   349: aload_0
    //   350: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   353: invokestatic 148	com/google/android/gms/common/api/internal/zzaa:zzd	(Lcom/google/android/gms/common/api/internal/zzaa;)Ljava/util/Map;
    //   356: aload_0
    //   357: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   360: invokestatic 92	com/google/android/gms/common/api/internal/zzaa:zzg	(Lcom/google/android/gms/common/api/internal/zzaa;)Ljava/util/Map;
    //   363: invokeinterface 152 2 0
    //   368: aload_0
    //   369: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   372: invokestatic 156	com/google/android/gms/common/api/internal/zzaa:zzf	(Lcom/google/android/gms/common/api/internal/zzaa;)Lcom/google/android/gms/common/ConnectionResult;
    //   375: ifnonnull +29 -> 404
    //   378: aload_0
    //   379: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   382: invokestatic 160	com/google/android/gms/common/api/internal/zzaa:zzi	(Lcom/google/android/gms/common/api/internal/zzaa;)V
    //   385: aload_0
    //   386: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   389: invokestatic 163	com/google/android/gms/common/api/internal/zzaa:zzj	(Lcom/google/android/gms/common/api/internal/zzaa;)V
    //   392: aload_0
    //   393: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   396: invokestatic 167	com/google/android/gms/common/api/internal/zzaa:zzl	(Lcom/google/android/gms/common/api/internal/zzaa;)Ljava/util/concurrent/locks/Condition;
    //   399: invokeinterface 172 1 0
    //   404: aload_0
    //   405: getfield 20	com/google/android/gms/common/api/internal/zzad:zza	Lcom/google/android/gms/common/api/internal/zzcu;
    //   408: astore_1
    //   409: goto -382 -> 27
    //   412: astore_1
    //   413: aload_0
    //   414: getfield 15	com/google/android/gms/common/api/internal/zzad:zzb	Lcom/google/android/gms/common/api/internal/zzaa;
    //   417: invokestatic 29	com/google/android/gms/common/api/internal/zzaa:zza	(Lcom/google/android/gms/common/api/internal/zzaa;)Ljava/util/concurrent/locks/Lock;
    //   420: invokeinterface 44 1 0
    //   425: aload_1
    //   426: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	427	0	this	zzad
    //   0	427	1	paramTask	com.google.android.gms.tasks.Task<Map<zzh<?>, String>>
    //   116	165	2	localObject	Object
    //   157	149	3	localAvailabilityException	com.google.android.gms.common.api.AvailabilityException
    //   212	13	4	localIterator	java.util.Iterator
    //   239	40	5	localZzh	zzh
    // Exception table:
    //   from	to	target	type
    //   12	27	412	finally
    //   27	33	412	finally
    //   46	98	412	finally
    //   98	137	412	finally
    //   140	214	412	finally
    //   214	277	412	finally
    //   277	287	412	finally
    //   290	298	412	finally
    //   301	313	412	finally
    //   316	339	412	finally
    //   339	404	412	finally
    //   404	409	412	finally
  }
  
  final void zza()
  {
    zza.zza();
  }
}

package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zzd;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzbic;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.internal.zzcyk;
import com.google.android.gms.tasks.Task;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zzaa
  implements zzcc
{
  private final Map<Api.zzc<?>, zzz<?>> zza = new HashMap();
  private final Map<Api.zzc<?>, zzz<?>> zzb = new HashMap();
  private final Map<Api<?>, Boolean> zzc;
  private final zzbm zzd;
  private final zzba zze;
  private final Lock zzf;
  private final Looper zzg;
  private final zzf zzh;
  private final Condition zzi;
  private final zzr zzj;
  private final boolean zzk;
  private final boolean zzl;
  private final Queue<zzm<?, ?>> zzm = new LinkedList();
  private boolean zzn;
  private Map<zzh<?>, ConnectionResult> zzo;
  private Map<zzh<?>, ConnectionResult> zzp;
  private zzad zzq;
  private ConnectionResult zzr;
  
  public zzaa(Context paramContext, Lock paramLock, Looper paramLooper, zzf paramZzf, Map<Api.zzc<?>, Api.zze> paramMap, zzr paramZzr, Map<Api<?>, Boolean> paramMap1, Api.zza<? extends zzcyj, zzcyk> paramZza, ArrayList<zzt> paramArrayList, zzba paramZzba, boolean paramBoolean)
  {
    zzf = paramLock;
    zzg = paramLooper;
    zzi = paramLock.newCondition();
    zzh = paramZzf;
    zze = paramZzba;
    zzc = paramMap1;
    zzj = paramZzr;
    zzk = paramBoolean;
    paramLock = new HashMap();
    paramZzf = paramMap1.keySet().iterator();
    while (paramZzf.hasNext())
    {
      paramMap1 = (Api)paramZzf.next();
      paramLock.put(paramMap1.zzc(), paramMap1);
    }
    paramZzf = new HashMap();
    paramMap1 = (ArrayList)paramArrayList;
    int j = paramMap1.size();
    int i = 0;
    while (i < j)
    {
      paramArrayList = paramMap1.get(i);
      i += 1;
      paramArrayList = (zzt)paramArrayList;
      paramZzf.put(zza, paramArrayList);
    }
    paramMap = paramMap.entrySet().iterator();
    int k = 0;
    j = 1;
    int m;
    for (i = 0; paramMap.hasNext(); i = m)
    {
      paramMap1 = (Map.Entry)paramMap.next();
      paramZzba = (Api)paramLock.get(paramMap1.getKey());
      paramArrayList = (Api.zze)paramMap1.getValue();
      if (paramArrayList.zzu())
      {
        if (!((Boolean)zzc.get(paramZzba)).booleanValue()) {
          k = 1;
        } else {
          k = i;
        }
        i = 1;
      }
      else
      {
        j = k;
        k = i;
        m = 0;
        i = j;
        j = m;
      }
      paramZzba = new zzz(paramContext, paramZzba, paramLooper, paramArrayList, (zzt)paramZzf.get(paramZzba), paramZzr, paramZza);
      zza.put((Api.zzc)paramMap1.getKey(), paramZzba);
      if (paramArrayList.l_()) {
        zzb.put((Api.zzc)paramMap1.getKey(), paramZzba);
      }
      m = k;
      k = i;
    }
    if ((k != 0) && (j == 0) && (i == 0)) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    }
    zzl = paramBoolean;
    zzd = zzbm.zza();
  }
  
  @Nullable
  private final ConnectionResult zza(@NonNull Api.zzc<?> paramZzc)
  {
    zzf.lock();
    try
    {
      paramZzc = (zzz)zza.get(paramZzc);
      if ((zzo != null) && (paramZzc != null))
      {
        paramZzc = (ConnectionResult)zzo.get(paramZzc.zzc());
        return paramZzc;
      }
      return null;
    }
    finally
    {
      zzf.unlock();
    }
  }
  
  private final boolean zza(zzz<?> paramZzz, ConnectionResult paramConnectionResult)
  {
    return (!paramConnectionResult.isSuccess()) && (!paramConnectionResult.hasResolution()) && (((Boolean)zzc.get(paramZzz.zza())).booleanValue()) && (paramZzz.zzh().zzu()) && (zzh.isUserResolvableError(paramConnectionResult.getErrorCode()));
  }
  
  private final <T extends zzm<? extends Result, ? extends Api.zzb>> boolean zzc(@NonNull T paramT)
  {
    Api.zzc localZzc = paramT.zzc();
    ConnectionResult localConnectionResult = zza(localZzc);
    if ((localConnectionResult != null) && (localConnectionResult.getErrorCode() == 4))
    {
      paramT.zzc(new Status(4, null, zzd.zza(((zzz)zza.get(localZzc)).zzc(), System.identityHashCode(zze))));
      return true;
    }
    return false;
  }
  
  private final boolean zzh()
  {
    zzf.lock();
    try
    {
      Iterator localIterator;
      if ((zzn) && (zzk)) {
        localIterator = zzb.keySet().iterator();
      }
      while (localIterator.hasNext())
      {
        ConnectionResult localConnectionResult = zza((Api.zzc)localIterator.next());
        if (localConnectionResult != null)
        {
          boolean bool = localConnectionResult.isSuccess();
          if (bool) {
            break;
          }
        }
        else
        {
          return false;
        }
      }
      return true;
    }
    finally
    {
      zzf.unlock();
    }
  }
  
  private final void zzi()
  {
    if (zzj == null)
    {
      zze.zzc = Collections.emptySet();
      return;
    }
    HashSet localHashSet = new HashSet(zzj.zze());
    Map localMap = zzj.zzg();
    Iterator localIterator = localMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Api localApi = (Api)localIterator.next();
      ConnectionResult localConnectionResult = zza(localApi);
      if ((localConnectionResult != null) && (localConnectionResult.isSuccess())) {
        localHashSet.addAll(getzza);
      }
    }
    zze.zzc = localHashSet;
  }
  
  private final void zzj()
  {
    while (!zzm.isEmpty()) {
      zzb((zzm)zzm.remove());
    }
    zze.zza(null);
  }
  
  @Nullable
  private final ConnectionResult zzk()
  {
    Iterator localIterator = zza.values().iterator();
    Object localObject1 = null;
    int j = 0;
    int i = j;
    Object localObject2 = null;
    while (localIterator.hasNext())
    {
      Object localObject3 = (zzz)localIterator.next();
      Api localApi = ((GoogleApi)localObject3).zza();
      localObject3 = ((GoogleApi)localObject3).zzc();
      localObject3 = (ConnectionResult)zzo.get(localObject3);
      if ((!((ConnectionResult)localObject3).isSuccess()) && ((!((Boolean)zzc.get(localApi)).booleanValue()) || (((ConnectionResult)localObject3).hasResolution()) || (zzh.isUserResolvableError(((ConnectionResult)localObject3).getErrorCode()))))
      {
        int k;
        if ((((ConnectionResult)localObject3).getErrorCode() == 4) && (zzk))
        {
          k = localApi.zza().zza();
          if ((localObject2 == null) || (i > k))
          {
            localObject2 = localObject3;
            i = k;
          }
        }
        else
        {
          k = localApi.zza().zza();
          if ((localObject1 == null) || (j > k))
          {
            localObject1 = localObject3;
            j = k;
          }
        }
      }
    }
    if ((localObject1 != null) && (localObject2 != null) && (j > i)) {
      return localObject2;
    }
    return localObject1;
  }
  
  public final ConnectionResult zza(long paramLong, TimeUnit paramTimeUnit)
  {
    zza();
    for (paramLong = paramTimeUnit.toNanos(paramLong);; paramLong = zzi.awaitNanos(paramLong))
    {
      if ((!zze()) || (paramLong <= 0L)) {}
      try
      {
        zzc();
        return new ConnectionResult(14, null);
      }
      catch (InterruptedException paramTimeUnit)
      {
        for (;;) {}
      }
    }
    Thread.currentThread().interrupt();
    return new ConnectionResult(15, null);
    if (zzd()) {
      return ConnectionResult.zza;
    }
    if (zzr != null) {
      return zzr;
    }
    return new ConnectionResult(13, null);
  }
  
  @Nullable
  public final ConnectionResult zza(@NonNull Api<?> paramApi)
  {
    return zza(paramApi.zzc());
  }
  
  public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zza(@NonNull T paramT)
  {
    if ((zzk) && (zzc(paramT))) {
      return paramT;
    }
    if (!zzd())
    {
      zzm.add(paramT);
      return paramT;
    }
    zze.zze.zza(paramT);
    return ((zzz)zza.get(paramT.zzc())).zza(paramT);
  }
  
  /* Error */
  public final void zza()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 59	com/google/android/gms/common/api/internal/zzaa:zzf	Ljava/util/concurrent/locks/Lock;
    //   4: invokeinterface 177 1 0
    //   9: aload_0
    //   10: getfield 204	com/google/android/gms/common/api/internal/zzaa:zzn	Z
    //   13: istore_1
    //   14: iload_1
    //   15: ifeq +13 -> 28
    //   18: aload_0
    //   19: getfield 59	com/google/android/gms/common/api/internal/zzaa:zzf	Ljava/util/concurrent/locks/Lock;
    //   22: invokeinterface 189 1 0
    //   27: return
    //   28: aload_0
    //   29: iconst_1
    //   30: putfield 204	com/google/android/gms/common/api/internal/zzaa:zzn	Z
    //   33: aload_0
    //   34: aconst_null
    //   35: putfield 179	com/google/android/gms/common/api/internal/zzaa:zzo	Ljava/util/Map;
    //   38: aload_0
    //   39: aconst_null
    //   40: putfield 228	com/google/android/gms/common/api/internal/zzaa:zzp	Ljava/util/Map;
    //   43: aload_0
    //   44: aconst_null
    //   45: putfield 382	com/google/android/gms/common/api/internal/zzaa:zzq	Lcom/google/android/gms/common/api/internal/zzad;
    //   48: aload_0
    //   49: aconst_null
    //   50: putfield 195	com/google/android/gms/common/api/internal/zzaa:zzr	Lcom/google/android/gms/common/ConnectionResult;
    //   53: aload_0
    //   54: getfield 168	com/google/android/gms/common/api/internal/zzaa:zzd	Lcom/google/android/gms/common/api/internal/zzbm;
    //   57: invokevirtual 384	com/google/android/gms/common/api/internal/zzbm:zzd	()V
    //   60: aload_0
    //   61: getfield 168	com/google/android/gms/common/api/internal/zzaa:zzd	Lcom/google/android/gms/common/api/internal/zzbm;
    //   64: aload_0
    //   65: getfield 50	com/google/android/gms/common/api/internal/zzaa:zza	Ljava/util/Map;
    //   68: invokeinterface 314 1 0
    //   73: invokevirtual 387	com/google/android/gms/common/api/internal/zzbm:zza	(Ljava/lang/Iterable;)Lcom/google/android/gms/tasks/Task;
    //   76: new 389	com/google/android/gms/internal/zzbic
    //   79: dup
    //   80: aload_0
    //   81: getfield 61	com/google/android/gms/common/api/internal/zzaa:zzg	Landroid/os/Looper;
    //   84: invokespecial 392	com/google/android/gms/internal/zzbic:<init>	(Landroid/os/Looper;)V
    //   87: new 394	com/google/android/gms/common/api/internal/zzac
    //   90: dup
    //   91: aload_0
    //   92: aconst_null
    //   93: invokespecial 397	com/google/android/gms/common/api/internal/zzac:<init>	(Lcom/google/android/gms/common/api/internal/zzaa;Lcom/google/android/gms/common/api/internal/zzab;)V
    //   96: invokevirtual 403	com/google/android/gms/tasks/Task:addOnCompleteListener	(Ljava/util/concurrent/Executor;Lcom/google/android/gms/tasks/OnCompleteListener;)Lcom/google/android/gms/tasks/Task;
    //   99: pop
    //   100: goto -82 -> 18
    //   103: astore_2
    //   104: aload_0
    //   105: getfield 59	com/google/android/gms/common/api/internal/zzaa:zzf	Ljava/util/concurrent/locks/Lock;
    //   108: invokeinterface 189 1 0
    //   113: aload_2
    //   114: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	115	0	this	zzaa
    //   13	2	1	bool	boolean
    //   103	11	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   9	14	103	finally
    //   28	100	103	finally
  }
  
  public final void zza(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {}
  
  public final boolean zza(zzcu paramZzcu)
  {
    zzf.lock();
    try
    {
      if ((zzn) && (!zzh()))
      {
        zzd.zzd();
        zzq = new zzad(this, paramZzcu);
        zzd.zza(zzb.values()).addOnCompleteListener(new zzbic(zzg), zzq);
        return true;
      }
      return false;
    }
    finally
    {
      zzf.unlock();
    }
  }
  
  public final ConnectionResult zzb()
  {
    zza();
    for (;;)
    {
      if (!zze()) {
        break label40;
      }
      try
      {
        zzi.await();
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
    }
    Thread.currentThread().interrupt();
    return new ConnectionResult(15, null);
    label40:
    if (zzd()) {
      return ConnectionResult.zza;
    }
    if (zzr != null) {
      return zzr;
    }
    return new ConnectionResult(13, null);
  }
  
  public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zzb(@NonNull T paramT)
  {
    Api.zzc localZzc = paramT.zzc();
    if ((zzk) && (zzc(paramT))) {
      return paramT;
    }
    zze.zze.zza(paramT);
    return ((zzz)zza.get(localZzc)).zzb(paramT);
  }
  
  public final void zzc()
  {
    zzf.lock();
    try
    {
      zzn = false;
      zzo = null;
      zzp = null;
      if (zzq != null)
      {
        zzq.zza();
        zzq = null;
      }
      zzr = null;
      while (!zzm.isEmpty())
      {
        zzm localZzm = (zzm)zzm.remove();
        localZzm.zza(null);
        localZzm.cancel();
      }
      zzi.signalAll();
      return;
    }
    finally
    {
      zzf.unlock();
    }
  }
  
  public final boolean zzd()
  {
    zzf.lock();
    try
    {
      if (zzo != null)
      {
        ConnectionResult localConnectionResult = zzr;
        if (localConnectionResult == null)
        {
          bool = true;
          break label32;
        }
      }
      boolean bool = false;
      label32:
      return bool;
    }
    finally
    {
      zzf.unlock();
    }
  }
  
  public final boolean zze()
  {
    zzf.lock();
    try
    {
      if (zzo == null)
      {
        bool = zzn;
        if (bool)
        {
          bool = true;
          break label32;
        }
      }
      boolean bool = false;
      label32:
      return bool;
    }
    finally
    {
      zzf.unlock();
    }
  }
  
  public final void zzf() {}
  
  public final void zzg()
  {
    zzf.lock();
    try
    {
      zzd.zze();
      if (zzq != null)
      {
        zzq.zza();
        zzq = null;
      }
      if (zzp == null) {
        zzp = new ArrayMap(zzb.size());
      }
      ConnectionResult localConnectionResult = new ConnectionResult(4);
      Iterator localIterator = zzb.values().iterator();
      while (localIterator.hasNext())
      {
        zzz localZzz = (zzz)localIterator.next();
        zzp.put(localZzz.zzc(), localConnectionResult);
      }
      if (zzo != null) {
        zzo.putAll(zzp);
      }
      return;
    }
    finally
    {
      zzf.unlock();
    }
  }
}

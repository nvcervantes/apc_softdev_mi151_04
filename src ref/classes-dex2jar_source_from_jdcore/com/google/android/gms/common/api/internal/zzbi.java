package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.internal.zzcyk;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zzbi
  implements zzcc, zzu
{
  final Map<Api.zzc<?>, Api.zze> zza;
  final Map<Api.zzc<?>, ConnectionResult> zzb = new HashMap();
  int zzc;
  final zzba zzd;
  final zzcd zze;
  private final Lock zzf;
  private final Condition zzg;
  private final Context zzh;
  private final zzf zzi;
  private final zzbk zzj;
  private zzr zzk;
  private Map<Api<?>, Boolean> zzl;
  private Api.zza<? extends zzcyj, zzcyk> zzm;
  private volatile zzbh zzn;
  private ConnectionResult zzo = null;
  
  public zzbi(Context paramContext, zzba paramZzba, Lock paramLock, Looper paramLooper, zzf paramZzf, Map<Api.zzc<?>, Api.zze> paramMap, zzr paramZzr, Map<Api<?>, Boolean> paramMap1, Api.zza<? extends zzcyj, zzcyk> paramZza, ArrayList<zzt> paramArrayList, zzcd paramZzcd)
  {
    zzh = paramContext;
    zzf = paramLock;
    zzi = paramZzf;
    zza = paramMap;
    zzk = paramZzr;
    zzl = paramMap1;
    zzm = paramZza;
    zzd = paramZzba;
    zze = paramZzcd;
    paramContext = (ArrayList)paramArrayList;
    int j = paramContext.size();
    int i = 0;
    while (i < j)
    {
      paramZzba = paramContext.get(i);
      i += 1;
      ((zzt)paramZzba).zza(this);
    }
    zzj = new zzbk(this, paramLooper);
    zzg = paramLock.newCondition();
    zzn = new zzaz(this);
  }
  
  public final void onConnected(@Nullable Bundle paramBundle)
  {
    zzf.lock();
    try
    {
      zzn.zza(paramBundle);
      return;
    }
    finally
    {
      zzf.unlock();
    }
  }
  
  public final void onConnectionSuspended(int paramInt)
  {
    zzf.lock();
    try
    {
      zzn.zza(paramInt);
      return;
    }
    finally
    {
      zzf.unlock();
    }
  }
  
  public final ConnectionResult zza(long paramLong, TimeUnit paramTimeUnit)
  {
    zza();
    for (paramLong = paramTimeUnit.toNanos(paramLong);; paramLong = zzg.awaitNanos(paramLong))
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
    if (zzo != null) {
      return zzo;
    }
    return new ConnectionResult(13, null);
  }
  
  @Nullable
  public final ConnectionResult zza(@NonNull Api<?> paramApi)
  {
    paramApi = paramApi.zzc();
    if (zza.containsKey(paramApi))
    {
      if (((Api.zze)zza.get(paramApi)).zzs()) {
        return ConnectionResult.zza;
      }
      if (zzb.containsKey(paramApi)) {
        return (ConnectionResult)zzb.get(paramApi);
      }
    }
    return null;
  }
  
  public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zza(@NonNull T paramT)
  {
    paramT.zzg();
    return zzn.zza(paramT);
  }
  
  public final void zza()
  {
    zzn.zzc();
  }
  
  final void zza(ConnectionResult paramConnectionResult)
  {
    zzf.lock();
    try
    {
      zzo = paramConnectionResult;
      zzn = new zzaz(this);
      zzn.zza();
      zzg.signalAll();
      return;
    }
    finally
    {
      zzf.unlock();
    }
  }
  
  public final void zza(@NonNull ConnectionResult paramConnectionResult, @NonNull Api<?> paramApi, boolean paramBoolean)
  {
    zzf.lock();
    try
    {
      zzn.zza(paramConnectionResult, paramApi, paramBoolean);
      return;
    }
    finally
    {
      zzf.unlock();
    }
  }
  
  final void zza(zzbj paramZzbj)
  {
    paramZzbj = zzj.obtainMessage(1, paramZzbj);
    zzj.sendMessage(paramZzbj);
  }
  
  final void zza(RuntimeException paramRuntimeException)
  {
    paramRuntimeException = zzj.obtainMessage(2, paramRuntimeException);
    zzj.sendMessage(paramRuntimeException);
  }
  
  public final void zza(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    String str = String.valueOf(paramString).concat("  ");
    paramPrintWriter.append(paramString).append("mState=").println(zzn);
    Iterator localIterator = zzl.keySet().iterator();
    while (localIterator.hasNext())
    {
      Api localApi = (Api)localIterator.next();
      paramPrintWriter.append(paramString).append(localApi.zzd()).println(":");
      ((Api.zze)zza.get(localApi.zzc())).zza(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
  }
  
  public final boolean zza(zzcu paramZzcu)
  {
    return false;
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
        zzg.await();
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
    if (zzo != null) {
      return zzo;
    }
    return new ConnectionResult(13, null);
  }
  
  public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zzb(@NonNull T paramT)
  {
    paramT.zzg();
    return zzn.zzb(paramT);
  }
  
  public final void zzc()
  {
    if (zzn.zzb()) {
      zzb.clear();
    }
  }
  
  public final boolean zzd()
  {
    return zzn instanceof zzal;
  }
  
  public final boolean zze()
  {
    return zzn instanceof zzao;
  }
  
  public final void zzf()
  {
    if (zzd()) {
      ((zzal)zzn).zzd();
    }
  }
  
  public final void zzg() {}
  
  final void zzh()
  {
    zzf.lock();
    try
    {
      zzn = new zzao(this, zzk, zzl, zzi, zzm, zzf, zzh);
      zzn.zza();
      zzg.signalAll();
      return;
    }
    finally
    {
      zzf.unlock();
    }
  }
  
  final void zzi()
  {
    zzf.lock();
    try
    {
      zzd.zzf();
      zzn = new zzal(this);
      zzn.zza();
      zzg.signalAll();
      return;
    }
    finally
    {
      zzf.unlock();
    }
  }
}

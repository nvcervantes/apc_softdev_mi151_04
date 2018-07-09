package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.internal.zzcyk;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

final class zzv
  implements zzcc
{
  private final Context zza;
  private final zzba zzb;
  private final Looper zzc;
  private final zzbi zzd;
  private final zzbi zze;
  private final Map<Api.zzc<?>, zzbi> zzf;
  private final Set<zzcu> zzg = Collections.newSetFromMap(new WeakHashMap());
  private final Api.zze zzh;
  private Bundle zzi;
  private ConnectionResult zzj = null;
  private ConnectionResult zzk = null;
  private boolean zzl = false;
  private final Lock zzm;
  private int zzn = 0;
  
  private zzv(Context paramContext, zzba paramZzba, Lock paramLock, Looper paramLooper, zzf paramZzf, Map<Api.zzc<?>, Api.zze> paramMap1, Map<Api.zzc<?>, Api.zze> paramMap2, zzr paramZzr, Api.zza<? extends zzcyj, zzcyk> paramZza, Api.zze paramZze, ArrayList<zzt> paramArrayList1, ArrayList<zzt> paramArrayList2, Map<Api<?>, Boolean> paramMap3, Map<Api<?>, Boolean> paramMap4)
  {
    zza = paramContext;
    zzb = paramZzba;
    zzm = paramLock;
    zzc = paramLooper;
    zzh = paramZze;
    zzd = new zzbi(paramContext, zzb, paramLock, paramLooper, paramZzf, paramMap2, null, paramMap4, null, paramArrayList2, new zzx(this, null));
    zze = new zzbi(paramContext, zzb, paramLock, paramLooper, paramZzf, paramMap1, paramZzr, paramMap3, paramZza, paramArrayList1, new zzy(this, null));
    paramContext = new ArrayMap();
    paramZzba = paramMap2.keySet().iterator();
    while (paramZzba.hasNext()) {
      paramContext.put((Api.zzc)paramZzba.next(), zzd);
    }
    paramZzba = paramMap1.keySet().iterator();
    while (paramZzba.hasNext()) {
      paramContext.put((Api.zzc)paramZzba.next(), zze);
    }
    zzf = Collections.unmodifiableMap(paramContext);
  }
  
  public static zzv zza(Context paramContext, zzba paramZzba, Lock paramLock, Looper paramLooper, zzf paramZzf, Map<Api.zzc<?>, Api.zze> paramMap, zzr paramZzr, Map<Api<?>, Boolean> paramMap1, Api.zza<? extends zzcyj, zzcyk> paramZza, ArrayList<zzt> paramArrayList)
  {
    ArrayMap localArrayMap1 = new ArrayMap();
    ArrayMap localArrayMap2 = new ArrayMap();
    Object localObject2 = paramMap.entrySet().iterator();
    paramMap = null;
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (Map.Entry)((Iterator)localObject2).next();
      localObject1 = (Api.zze)((Map.Entry)localObject3).getValue();
      if (((Api.zze)localObject1).zze()) {
        paramMap = (Map<Api.zzc<?>, Api.zze>)localObject1;
      }
      if (((Api.zze)localObject1).l_()) {
        localArrayMap1.put((Api.zzc)((Map.Entry)localObject3).getKey(), localObject1);
      } else {
        localArrayMap2.put((Api.zzc)((Map.Entry)localObject3).getKey(), localObject1);
      }
    }
    zzbq.zza(localArrayMap1.isEmpty() ^ true, "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
    Object localObject1 = new ArrayMap();
    localObject2 = new ArrayMap();
    Object localObject3 = paramMap1.keySet().iterator();
    Object localObject4;
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (Api)((Iterator)localObject3).next();
      Api.zzc localZzc = ((Api)localObject4).zzc();
      if (localArrayMap1.containsKey(localZzc)) {
        ((Map)localObject1).put(localObject4, (Boolean)paramMap1.get(localObject4));
      } else if (localArrayMap2.containsKey(localZzc)) {
        ((Map)localObject2).put(localObject4, (Boolean)paramMap1.get(localObject4));
      } else {
        throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
      }
    }
    paramMap1 = new ArrayList();
    localObject3 = new ArrayList();
    paramArrayList = (ArrayList)paramArrayList;
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      localObject4 = paramArrayList.get(i);
      i += 1;
      localObject4 = (zzt)localObject4;
      if (((Map)localObject1).containsKey(zza)) {
        paramMap1.add(localObject4);
      } else if (((Map)localObject2).containsKey(zza)) {
        ((ArrayList)localObject3).add(localObject4);
      } else {
        throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
      }
    }
    return new zzv(paramContext, paramZzba, paramLock, paramLooper, paramZzf, localArrayMap1, localArrayMap2, paramZzr, paramZza, paramMap, paramMap1, (ArrayList)localObject3, (Map)localObject1, (Map)localObject2);
  }
  
  private final void zza(int paramInt, boolean paramBoolean)
  {
    zzb.zza(paramInt, paramBoolean);
    zzk = null;
    zzj = null;
  }
  
  private final void zza(Bundle paramBundle)
  {
    if (zzi == null)
    {
      zzi = paramBundle;
      return;
    }
    if (paramBundle != null) {
      zzi.putAll(paramBundle);
    }
  }
  
  private final void zza(ConnectionResult paramConnectionResult)
  {
    switch (zzn)
    {
    default: 
      Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
      break;
    case 2: 
      zzb.zza(paramConnectionResult);
    case 1: 
      zzi();
    }
    zzn = 0;
  }
  
  private static boolean zzb(ConnectionResult paramConnectionResult)
  {
    return (paramConnectionResult != null) && (paramConnectionResult.isSuccess());
  }
  
  private final boolean zzc(zzm<? extends Result, ? extends Api.zzb> paramZzm)
  {
    paramZzm = paramZzm.zzc();
    zzbq.zzb(zzf.containsKey(paramZzm), "GoogleApiClient is not configured to use the API required for this call.");
    return ((zzbi)zzf.get(paramZzm)).equals(zze);
  }
  
  private final void zzh()
  {
    if (zzb(zzj))
    {
      if ((!zzb(zzk)) && (!zzj()))
      {
        if (zzk != null)
        {
          if (zzn == 1)
          {
            zzi();
            return;
          }
          zza(zzk);
          zzd.zzc();
        }
      }
      else
      {
        switch (zzn)
        {
        default: 
          Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
          break;
        case 2: 
          zzb.zza(zzi);
        case 1: 
          zzi();
        }
        zzn = 0;
      }
    }
    else
    {
      if ((zzj != null) && (zzb(zzk)))
      {
        zze.zzc();
        zza(zzj);
        return;
      }
      if ((zzj != null) && (zzk != null))
      {
        ConnectionResult localConnectionResult = zzj;
        if (zze.zzc < zzd.zzc) {
          localConnectionResult = zzk;
        }
        zza(localConnectionResult);
      }
    }
  }
  
  private final void zzi()
  {
    Iterator localIterator = zzg.iterator();
    while (localIterator.hasNext()) {
      ((zzcu)localIterator.next()).zza();
    }
    zzg.clear();
  }
  
  private final boolean zzj()
  {
    return (zzk != null) && (zzk.getErrorCode() == 4);
  }
  
  @Nullable
  private final PendingIntent zzk()
  {
    if (zzh == null) {
      return null;
    }
    return PendingIntent.getActivity(zza, System.identityHashCode(zzb), zzh.zzf(), 134217728);
  }
  
  public final ConnectionResult zza(long paramLong, @NonNull TimeUnit paramTimeUnit)
  {
    throw new UnsupportedOperationException();
  }
  
  @Nullable
  public final ConnectionResult zza(@NonNull Api<?> paramApi)
  {
    if (((zzbi)zzf.get(paramApi.zzc())).equals(zze))
    {
      if (zzj()) {
        return new ConnectionResult(4, zzk());
      }
      return zze.zza(paramApi);
    }
    return zzd.zza(paramApi);
  }
  
  public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zza(@NonNull T paramT)
  {
    if (zzc(paramT))
    {
      if (zzj())
      {
        paramT.zzc(new Status(4, null, zzk()));
        return paramT;
      }
      return zze.zza(paramT);
    }
    return zzd.zza(paramT);
  }
  
  public final void zza()
  {
    zzn = 2;
    zzl = false;
    zzk = null;
    zzj = null;
    zzd.zza();
    zze.zza();
  }
  
  public final void zza(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    paramPrintWriter.append(paramString).append("authClient").println(":");
    zze.zza(String.valueOf(paramString).concat("  "), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramPrintWriter.append(paramString).append("anonClient").println(":");
    zzd.zza(String.valueOf(paramString).concat("  "), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
  }
  
  public final boolean zza(zzcu paramZzcu)
  {
    zzm.lock();
    try
    {
      if (((zze()) || (zzd())) && (!zze.zzd()))
      {
        zzg.add(paramZzcu);
        if (zzn == 0) {
          zzn = 1;
        }
        zzk = null;
        zze.zza();
        return true;
      }
      return false;
    }
    finally
    {
      zzm.unlock();
    }
  }
  
  public final ConnectionResult zzb()
  {
    throw new UnsupportedOperationException();
  }
  
  public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zzb(@NonNull T paramT)
  {
    if (zzc(paramT))
    {
      if (zzj())
      {
        paramT.zzc(new Status(4, null, zzk()));
        return paramT;
      }
      return zze.zzb(paramT);
    }
    return zzd.zzb(paramT);
  }
  
  public final void zzc()
  {
    zzk = null;
    zzj = null;
    zzn = 0;
    zzd.zzc();
    zze.zzc();
    zzi();
  }
  
  public final boolean zzd()
  {
    zzm.lock();
    try
    {
      boolean bool1 = zzd.zzd();
      boolean bool2 = true;
      if (bool1)
      {
        bool1 = bool2;
        if (zze.zzd()) {
          break label61;
        }
        bool1 = bool2;
        if (zzj()) {
          break label61;
        }
        int i = zzn;
        if (i == 1)
        {
          bool1 = bool2;
          break label61;
        }
      }
      bool1 = false;
      label61:
      return bool1;
    }
    finally
    {
      zzm.unlock();
    }
  }
  
  public final boolean zze()
  {
    zzm.lock();
    try
    {
      int i = zzn;
      boolean bool;
      if (i == 2) {
        bool = true;
      } else {
        bool = false;
      }
      return bool;
    }
    finally
    {
      zzm.unlock();
    }
  }
  
  public final void zzf()
  {
    zzd.zzf();
    zze.zzf();
  }
  
  public final void zzg()
  {
    zzm.lock();
    try
    {
      boolean bool = zze();
      zze.zzc();
      zzk = new ConnectionResult(4);
      if (bool) {
        new Handler(zzc).post(new zzw(this));
      } else {
        zzi();
      }
      return;
    }
    finally
    {
      zzm.unlock();
    }
  }
}

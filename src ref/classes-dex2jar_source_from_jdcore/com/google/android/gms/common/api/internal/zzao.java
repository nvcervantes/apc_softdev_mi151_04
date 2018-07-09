package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zzd;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzan;
import com.google.android.gms.common.internal.zzbt;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.internal.zzcyk;
import com.google.android.gms.internal.zzcyw;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public final class zzao
  implements zzbh
{
  private final zzbi zza;
  private final Lock zzb;
  private final Context zzc;
  private final zzf zzd;
  private ConnectionResult zze;
  private int zzf;
  private int zzg = 0;
  private int zzh;
  private final Bundle zzi = new Bundle();
  private final Set<Api.zzc> zzj = new HashSet();
  private zzcyj zzk;
  private boolean zzl;
  private boolean zzm;
  private boolean zzn;
  private zzan zzo;
  private boolean zzp;
  private boolean zzq;
  private final zzr zzr;
  private final Map<Api<?>, Boolean> zzs;
  private final Api.zza<? extends zzcyj, zzcyk> zzt;
  private ArrayList<Future<?>> zzu = new ArrayList();
  
  public zzao(zzbi paramZzbi, zzr paramZzr, Map<Api<?>, Boolean> paramMap, zzf paramZzf, Api.zza<? extends zzcyj, zzcyk> paramZza, Lock paramLock, Context paramContext)
  {
    zza = paramZzbi;
    zzr = paramZzr;
    zzs = paramMap;
    zzd = paramZzf;
    zzt = paramZza;
    zzb = paramLock;
    zzc = paramContext;
  }
  
  private final void zza(zzcyw paramZzcyw)
  {
    if (!zzb(0)) {
      return;
    }
    Object localObject = paramZzcyw.zza();
    if (((ConnectionResult)localObject).isSuccess())
    {
      localObject = paramZzcyw.zzb();
      paramZzcyw = ((zzbt)localObject).zzb();
      if (!paramZzcyw.isSuccess())
      {
        localObject = String.valueOf(paramZzcyw);
        StringBuilder localStringBuilder = new StringBuilder(48 + String.valueOf(localObject).length());
        localStringBuilder.append("Sign-in succeeded with resolve account failure: ");
        localStringBuilder.append((String)localObject);
        Log.wtf("GoogleApiClientConnecting", localStringBuilder.toString(), new Exception());
        zzb(paramZzcyw);
        return;
      }
      zzn = true;
      zzo = ((zzbt)localObject).zza();
      zzp = ((zzbt)localObject).zzc();
      zzq = ((zzbt)localObject).zzd();
      zze();
      return;
    }
    if (zza((ConnectionResult)localObject))
    {
      zzg();
      zze();
      return;
    }
    zzb((ConnectionResult)localObject);
  }
  
  private final void zza(boolean paramBoolean)
  {
    if (zzk != null)
    {
      if ((zzk.zzs()) && (paramBoolean)) {
        zzk.zzh();
      }
      zzk.zzg();
      zzo = null;
    }
  }
  
  private final boolean zza(ConnectionResult paramConnectionResult)
  {
    return (zzl) && (!paramConnectionResult.hasResolution());
  }
  
  private final void zzb(ConnectionResult paramConnectionResult)
  {
    zzh();
    zza(paramConnectionResult.hasResolution() ^ true);
    zza.zza(paramConnectionResult);
    zza.zze.zza(paramConnectionResult);
  }
  
  private final void zzb(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean)
  {
    int m = paramApi.zza().zza();
    int k = 0;
    int j;
    if (paramBoolean)
    {
      if (paramConnectionResult.hasResolution()) {}
      while (zzd.zza(paramConnectionResult.getErrorCode()) != null)
      {
        i = 1;
        break;
      }
      int i = 0;
      j = k;
      if (i == 0) {}
    }
    else if (zze != null)
    {
      j = k;
      if (m >= zzf) {}
    }
    else
    {
      j = 1;
    }
    if (j != 0)
    {
      zze = paramConnectionResult;
      zzf = m;
    }
    zza.zzb.put(paramApi.zzc(), paramConnectionResult);
  }
  
  private final boolean zzb(int paramInt)
  {
    if (zzg != paramInt)
    {
      Log.w("GoogleApiClientConnecting", zza.zzd.zzh());
      Object localObject1 = String.valueOf(this);
      Object localObject2 = new StringBuilder(23 + String.valueOf(localObject1).length());
      ((StringBuilder)localObject2).append("Unexpected callback in ");
      ((StringBuilder)localObject2).append((String)localObject1);
      Log.w("GoogleApiClientConnecting", ((StringBuilder)localObject2).toString());
      int i = zzh;
      localObject1 = new StringBuilder(33);
      ((StringBuilder)localObject1).append("mRemainingConnections=");
      ((StringBuilder)localObject1).append(i);
      Log.w("GoogleApiClientConnecting", ((StringBuilder)localObject1).toString());
      localObject1 = zzc(zzg);
      localObject2 = zzc(paramInt);
      StringBuilder localStringBuilder = new StringBuilder(70 + String.valueOf(localObject1).length() + String.valueOf(localObject2).length());
      localStringBuilder.append("GoogleApiClient connecting is in step ");
      localStringBuilder.append((String)localObject1);
      localStringBuilder.append(" but received callback for step ");
      localStringBuilder.append((String)localObject2);
      Log.wtf("GoogleApiClientConnecting", localStringBuilder.toString(), new Exception());
      zzb(new ConnectionResult(8, null));
      return false;
    }
    return true;
  }
  
  private static String zzc(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "UNKNOWN";
    case 1: 
      return "STEP_GETTING_REMOTE_SERVICE";
    }
    return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
  }
  
  private final boolean zzd()
  {
    zzh -= 1;
    if (zzh > 0) {
      return false;
    }
    if (zzh < 0)
    {
      Log.w("GoogleApiClientConnecting", zza.zzd.zzh());
      Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
    }
    for (ConnectionResult localConnectionResult = new ConnectionResult(8, null);; localConnectionResult = zze)
    {
      zzb(localConnectionResult);
      return false;
      if (zze == null) {
        break;
      }
      zza.zzc = zzf;
    }
    return true;
  }
  
  private final void zze()
  {
    if (zzh != 0) {
      return;
    }
    if ((!zzm) || (zzn))
    {
      ArrayList localArrayList = new ArrayList();
      zzg = 1;
      zzh = zza.zza.size();
      Iterator localIterator = zza.zza.keySet().iterator();
      while (localIterator.hasNext())
      {
        Api.zzc localZzc = (Api.zzc)localIterator.next();
        if (zza.zzb.containsKey(localZzc))
        {
          if (zzd()) {
            zzf();
          }
        }
        else {
          localArrayList.add((Api.zze)zza.zza.get(localZzc));
        }
      }
      if (!localArrayList.isEmpty()) {
        zzu.add(zzbl.zza().submit(new zzau(this, localArrayList)));
      }
    }
  }
  
  private final void zzf()
  {
    zza.zzi();
    zzbl.zza().execute(new zzap(this));
    if (zzk != null)
    {
      if (zzp) {
        zzk.zza(zzo, zzq);
      }
      zza(false);
    }
    Object localObject = zza.zzb.keySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      Api.zzc localZzc = (Api.zzc)((Iterator)localObject).next();
      ((Api.zze)zza.zza.get(localZzc)).zzg();
    }
    if (zzi.isEmpty()) {
      localObject = null;
    } else {
      localObject = zzi;
    }
    zza.zze.zza((Bundle)localObject);
  }
  
  private final void zzg()
  {
    zzm = false;
    zza.zzd.zzc = Collections.emptySet();
    Iterator localIterator = zzj.iterator();
    while (localIterator.hasNext())
    {
      Api.zzc localZzc = (Api.zzc)localIterator.next();
      if (!zza.zzb.containsKey(localZzc)) {
        zza.zzb.put(localZzc, new ConnectionResult(17, null));
      }
    }
  }
  
  private final void zzh()
  {
    ArrayList localArrayList = (ArrayList)zzu;
    int j = localArrayList.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = localArrayList.get(i);
      i += 1;
      ((Future)localObject).cancel(true);
    }
    zzu.clear();
  }
  
  private final Set<Scope> zzi()
  {
    if (zzr == null) {
      return Collections.emptySet();
    }
    HashSet localHashSet = new HashSet(zzr.zze());
    Map localMap = zzr.zzg();
    Iterator localIterator = localMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Api localApi = (Api)localIterator.next();
      if (!zza.zzb.containsKey(localApi.zzc())) {
        localHashSet.addAll(getzza);
      }
    }
    return localHashSet;
  }
  
  public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zza(T paramT)
  {
    zza.zzd.zza.add(paramT);
    return paramT;
  }
  
  public final void zza()
  {
    zza.zzb.clear();
    zzm = false;
    zze = null;
    zzg = 0;
    zzl = true;
    zzn = false;
    zzp = false;
    HashMap localHashMap = new HashMap();
    Object localObject = zzs.keySet().iterator();
    int i = 0;
    while (((Iterator)localObject).hasNext())
    {
      Api localApi = (Api)((Iterator)localObject).next();
      Api.zze localZze = (Api.zze)zza.zza.get(localApi.zzc());
      int j;
      if (localApi.zza().zza() == 1) {
        j = 1;
      } else {
        j = 0;
      }
      i |= j;
      boolean bool = ((Boolean)zzs.get(localApi)).booleanValue();
      if (localZze.l_())
      {
        zzm = true;
        if (bool) {
          zzj.add(localApi.zzc());
        } else {
          zzl = false;
        }
      }
      localHashMap.put(localZze, new zzaq(this, localApi, bool));
    }
    if (i != 0) {
      zzm = false;
    }
    if (zzm)
    {
      zzr.zza(Integer.valueOf(System.identityHashCode(zza.zzd)));
      localObject = new zzax(this, null);
      zzk = ((zzcyj)zzt.zza(zzc, zza.zzd.zzc(), zzr, zzr.zzk(), (GoogleApiClient.ConnectionCallbacks)localObject, (GoogleApiClient.OnConnectionFailedListener)localObject));
    }
    zzh = zza.zza.size();
    zzu.add(zzbl.zza().submit(new zzar(this, localHashMap)));
  }
  
  public final void zza(int paramInt)
  {
    zzb(new ConnectionResult(8, null));
  }
  
  public final void zza(Bundle paramBundle)
  {
    if (!zzb(1)) {
      return;
    }
    if (paramBundle != null) {
      zzi.putAll(paramBundle);
    }
    if (zzd()) {
      zzf();
    }
  }
  
  public final void zza(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean)
  {
    if (!zzb(1)) {
      return;
    }
    zzb(paramConnectionResult, paramApi, paramBoolean);
    if (zzd()) {
      zzf();
    }
  }
  
  public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zzb(T paramT)
  {
    throw new IllegalStateException("GoogleApiClient is not connected yet.");
  }
  
  public final boolean zzb()
  {
    zzh();
    zza(true);
    zza.zza(null);
    return true;
  }
  
  public final void zzc() {}
}

package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

public final class zzaz
  implements zzbh
{
  private final zzbi zza;
  
  public zzaz(zzbi paramZzbi)
  {
    zza = paramZzbi;
  }
  
  public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zza(T paramT)
  {
    zza.zzd.zza.add(paramT);
    return paramT;
  }
  
  public final void zza()
  {
    Iterator localIterator = zza.zza.values().iterator();
    while (localIterator.hasNext()) {
      ((Api.zze)localIterator.next()).zzg();
    }
    zza.zzd.zzc = Collections.emptySet();
  }
  
  public final void zza(int paramInt) {}
  
  public final void zza(Bundle paramBundle) {}
  
  public final void zza(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean) {}
  
  public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zzb(T paramT)
  {
    throw new IllegalStateException("GoogleApiClient is not connected yet.");
  }
  
  public final boolean zzb()
  {
    return true;
  }
  
  public final void zzc()
  {
    zza.zzh();
  }
}

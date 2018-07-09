package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;

public abstract interface zzbh
{
  public abstract <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zza(T paramT);
  
  public abstract void zza();
  
  public abstract void zza(int paramInt);
  
  public abstract void zza(Bundle paramBundle);
  
  public abstract void zza(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean);
  
  public abstract <A extends Api.zzb, T extends zzm<? extends Result, A>> T zzb(T paramT);
  
  public abstract boolean zzb();
  
  public abstract void zzc();
}

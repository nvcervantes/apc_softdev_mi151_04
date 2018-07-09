package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzan;
import java.util.Set;

@WorkerThread
public abstract interface zzcy
{
  public abstract void zza(zzan paramZzan, Set<Scope> paramSet);
  
  public abstract void zzb(ConnectionResult paramConnectionResult);
}

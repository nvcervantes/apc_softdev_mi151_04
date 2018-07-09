package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzdo<A extends Api.zzb, L>
{
  private final zzck<L> zza;
  
  protected zzdo(zzck<L> paramZzck)
  {
    zza = paramZzck;
  }
  
  @Hide
  public final zzck<L> zza()
  {
    return zza;
  }
  
  @Hide
  protected abstract void zza(A paramA, TaskCompletionSource<Boolean> paramTaskCompletionSource)
    throws RemoteException;
}

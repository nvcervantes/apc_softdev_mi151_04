package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzcq<A extends Api.zzb, L>
{
  private final zzci<L> zza;
  
  protected zzcq(zzci<L> paramZzci)
  {
    zza = paramZzci;
  }
  
  @Hide
  public final zzck<L> zza()
  {
    return zza.zzc();
  }
  
  @Hide
  protected abstract void zza(A paramA, TaskCompletionSource<Void> paramTaskCompletionSource)
    throws RemoteException;
  
  public final void zzb()
  {
    zza.zzb();
  }
}

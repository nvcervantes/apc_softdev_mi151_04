package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzde<A extends Api.zzb, TResult>
{
  public zzde() {}
  
  @Hide
  protected abstract void zza(A paramA, TaskCompletionSource<TResult> paramTaskCompletionSource)
    throws RemoteException;
}

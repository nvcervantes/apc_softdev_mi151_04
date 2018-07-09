package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zze<TResult>
  extends zza
{
  private final zzde<Api.zzb, TResult> zza;
  private final TaskCompletionSource<TResult> zzb;
  private final zzda zzc;
  
  public zze(int paramInt, zzde<Api.zzb, TResult> paramZzde, TaskCompletionSource<TResult> paramTaskCompletionSource, zzda paramZzda)
  {
    super(paramInt);
    zzb = paramTaskCompletionSource;
    zza = paramZzde;
    zzc = paramZzda;
  }
  
  public final void zza(@NonNull Status paramStatus)
  {
    zzb.trySetException(zzc.zza(paramStatus));
  }
  
  public final void zza(@NonNull zzae paramZzae, boolean paramBoolean)
  {
    paramZzae.zza(zzb, paramBoolean);
  }
  
  public final void zza(zzbo<?> paramZzbo)
    throws DeadObjectException
  {
    try
    {
      zza.zza(paramZzbo.zzb(), zzb);
      return;
    }
    catch (RuntimeException paramZzbo)
    {
      zzb.trySetException(paramZzbo);
      return;
    }
    catch (RemoteException paramZzbo)
    {
      zza(zza.zza(paramZzbo));
      return;
    }
    catch (DeadObjectException paramZzbo)
    {
      throw paramZzbo;
    }
  }
}

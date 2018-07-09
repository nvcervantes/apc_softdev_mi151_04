package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzbz;

public abstract class zzm<R extends Result, A extends Api.zzb>
  extends BasePendingResult<R>
  implements zzn<R>
{
  private final Api.zzc<A> zza;
  private final Api<?> zzb;
  
  @Deprecated
  protected zzm(@NonNull Api.zzc<A> paramZzc, @NonNull GoogleApiClient paramGoogleApiClient)
  {
    super((GoogleApiClient)zzbq.zza(paramGoogleApiClient, "GoogleApiClient must not be null"));
    zza = ((Api.zzc)zzbq.zza(paramZzc));
    zzb = null;
  }
  
  protected zzm(@NonNull Api<?> paramApi, @NonNull GoogleApiClient paramGoogleApiClient)
  {
    super((GoogleApiClient)zzbq.zza(paramGoogleApiClient, "GoogleApiClient must not be null"));
    zzbq.zza(paramApi, "Api must not be null");
    zza = paramApi.zzc();
    zzb = paramApi;
  }
  
  private final void zza(@NonNull RemoteException paramRemoteException)
  {
    zzc(new Status(8, paramRemoteException.getLocalizedMessage(), null));
  }
  
  @Hide
  protected abstract void zza(@NonNull A paramA)
    throws RemoteException;
  
  @Hide
  public final void zzb(@NonNull A paramA)
    throws DeadObjectException
  {
    Object localObject = paramA;
    if ((paramA instanceof zzbz)) {
      localObject = zzbz.zzi();
    }
    try
    {
      zza((Api.zzb)localObject);
      return;
    }
    catch (RemoteException paramA)
    {
      zza(paramA);
      return;
    }
    catch (DeadObjectException paramA)
    {
      zza(paramA);
      throw paramA;
    }
  }
  
  @Hide
  public final Api.zzc<A> zzc()
  {
    return zza;
  }
  
  @Hide
  public final void zzc(@NonNull Status paramStatus)
  {
    zzbq.zzb(paramStatus.isSuccess() ^ true, "Failed result must not be success");
    zza(zza(paramStatus));
  }
  
  @Hide
  public final Api<?> zzd()
  {
    return zzb;
  }
}

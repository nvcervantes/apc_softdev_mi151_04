package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;

public final class zzd
  extends zzb<Void>
{
  private zzcq<Api.zzb, ?> zzb;
  private zzdo<Api.zzb, ?> zzc;
  
  public zzd(zzcr paramZzcr, TaskCompletionSource<Void> paramTaskCompletionSource)
  {
    super(3, paramTaskCompletionSource);
    zzb = zza;
    zzc = zzb;
  }
  
  public final void zzb(zzbo<?> paramZzbo)
    throws RemoteException
  {
    zzb.zza(paramZzbo.zzb(), zza);
    if (zzb.zza() != null) {
      paramZzbo.zzc().put(zzb.zza(), new zzcr(zzb, zzc));
    }
  }
}

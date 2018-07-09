package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.BasePendingResult;

final class zza
  implements PendingResult.zza
{
  zza(Batch paramBatch) {}
  
  public final void zza(Status paramStatus)
  {
    synchronized (Batch.zza(zza))
    {
      if (zza.isCanceled()) {
        return;
      }
      if (paramStatus.isCanceled()) {
        Batch.zza(zza, true);
      } else if (!paramStatus.isSuccess()) {
        Batch.zzb(zza, true);
      }
      Batch.zzb(zza);
      if (Batch.zzc(zza) == 0) {
        if (Batch.zzd(zza))
        {
          Batch.zze(zza);
        }
        else
        {
          if (Batch.zzf(zza)) {
            paramStatus = new Status(13);
          } else {
            paramStatus = Status.zza;
          }
          zza.zza(new BatchResult(paramStatus, Batch.zzg(zza)));
        }
      }
      return;
    }
  }
}

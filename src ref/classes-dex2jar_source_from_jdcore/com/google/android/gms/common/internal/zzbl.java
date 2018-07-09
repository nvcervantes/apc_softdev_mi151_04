package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

final class zzbl
  implements PendingResult.zza
{
  zzbl(PendingResult paramPendingResult, TaskCompletionSource paramTaskCompletionSource, zzbo paramZzbo, zzbp paramZzbp) {}
  
  public final void zza(Status paramStatus)
  {
    if (paramStatus.isSuccess())
    {
      paramStatus = zza.await(0L, TimeUnit.MILLISECONDS);
      zzb.setResult(zzc.zza(paramStatus));
      return;
    }
    zzb.setException(zzd.zza(paramStatus));
  }
}

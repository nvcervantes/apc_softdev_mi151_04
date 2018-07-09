package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzbq;
import java.util.concurrent.TimeUnit;

public final class BatchResult
  implements Result
{
  private final Status zza;
  private final PendingResult<?>[] zzb;
  
  BatchResult(Status paramStatus, PendingResult<?>[] paramArrayOfPendingResult)
  {
    zza = paramStatus;
    zzb = paramArrayOfPendingResult;
  }
  
  public final Status getStatus()
  {
    return zza;
  }
  
  public final <R extends Result> R take(BatchResultToken<R> paramBatchResultToken)
  {
    boolean bool;
    if (mId < zzb.length) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zzb(bool, "The result token does not belong to this batch");
    return zzb[mId].await(0L, TimeUnit.MILLISECONDS);
  }
}

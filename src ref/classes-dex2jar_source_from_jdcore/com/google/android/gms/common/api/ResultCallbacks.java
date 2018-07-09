package com.google.android.gms.common.api;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Hide;

public abstract class ResultCallbacks<R extends Result>
  implements ResultCallback<R>
{
  public ResultCallbacks() {}
  
  public abstract void onFailure(@NonNull Status paramStatus);
  
  @KeepForSdk
  @Hide
  public final void onResult(@NonNull R paramR)
  {
    Status localStatus = paramR.getStatus();
    if (localStatus.isSuccess())
    {
      onSuccess(paramR);
      return;
    }
    onFailure(localStatus);
    if ((paramR instanceof Releasable)) {
      try
      {
        ((Releasable)paramR).release();
        return;
      }
      catch (RuntimeException localRuntimeException)
      {
        paramR = String.valueOf(paramR);
        StringBuilder localStringBuilder = new StringBuilder(18 + String.valueOf(paramR).length());
        localStringBuilder.append("Unable to release ");
        localStringBuilder.append(paramR);
        Log.w("ResultCallbacks", localStringBuilder.toString(), localRuntimeException);
      }
    }
  }
  
  public abstract void onSuccess(@NonNull R paramR);
}

package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

public final class zzdb
  extends BasePendingResult<Status>
{
  @Deprecated
  public zzdb(Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public zzdb(GoogleApiClient paramGoogleApiClient)
  {
    super(paramGoogleApiClient);
  }
}

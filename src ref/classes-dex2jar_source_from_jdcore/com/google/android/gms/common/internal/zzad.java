package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

final class zzad
  implements zzg
{
  zzad(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {}
  
  public final void zza(@NonNull ConnectionResult paramConnectionResult)
  {
    zza.onConnectionFailed(paramConnectionResult);
  }
}

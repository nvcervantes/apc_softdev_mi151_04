package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;

final class zzac
  implements zzf
{
  zzac(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {}
  
  public final void zza(int paramInt)
  {
    zza.onConnectionSuspended(paramInt);
  }
  
  public final void zza(@Nullable Bundle paramBundle)
  {
    zza.onConnected(paramBundle);
  }
}

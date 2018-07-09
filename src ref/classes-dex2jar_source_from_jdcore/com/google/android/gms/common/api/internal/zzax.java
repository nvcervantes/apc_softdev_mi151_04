package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.zzcyj;
import java.util.concurrent.locks.Lock;

final class zzax
  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
  private zzax(zzao paramZzao) {}
  
  public final void onConnected(Bundle paramBundle)
  {
    zzao.zze(zza).zza(new zzav(zza));
  }
  
  public final void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult)
  {
    zzao.zzb(zza).lock();
    try
    {
      if (zzao.zzb(zza, paramConnectionResult))
      {
        zzao.zzh(zza);
        zzao.zzi(zza);
      }
      else
      {
        zzao.zza(zza, paramConnectionResult);
      }
      return;
    }
    finally
    {
      zzao.zzb(zza).unlock();
    }
  }
  
  public final void onConnectionSuspended(int paramInt) {}
}

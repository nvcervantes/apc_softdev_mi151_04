package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzbq;

public final class zzt
  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
  public final Api<?> zza;
  private final boolean zzb;
  private zzu zzc;
  
  public zzt(Api<?> paramApi, boolean paramBoolean)
  {
    zza = paramApi;
    zzb = paramBoolean;
  }
  
  private final void zza()
  {
    zzbq.zza(zzc, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
  }
  
  public final void onConnected(@Nullable Bundle paramBundle)
  {
    zza();
    zzc.onConnected(paramBundle);
  }
  
  public final void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult)
  {
    zza();
    zzc.zza(paramConnectionResult, zza, zzb);
  }
  
  public final void onConnectionSuspended(int paramInt)
  {
    zza();
    zzc.onConnectionSuspended(paramInt);
  }
  
  public final void zza(zzu paramZzu)
  {
    zzc = paramZzu;
  }
}

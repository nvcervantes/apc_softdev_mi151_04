package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import java.util.concurrent.atomic.AtomicReference;

final class zzbc
  implements GoogleApiClient.ConnectionCallbacks
{
  zzbc(zzba paramZzba, AtomicReference paramAtomicReference, zzdb paramZzdb) {}
  
  public final void onConnected(Bundle paramBundle)
  {
    zzba.zza(zzc, (GoogleApiClient)zza.get(), zzb, true);
  }
  
  public final void onConnectionSuspended(int paramInt) {}
}

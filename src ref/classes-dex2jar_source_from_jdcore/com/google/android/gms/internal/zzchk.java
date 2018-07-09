package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.location.LocationSettingsResult;

final class zzchk
  extends zzcgz
{
  private zzn<LocationSettingsResult> zza;
  
  public zzchk(zzn<LocationSettingsResult> paramZzn)
  {
    boolean bool;
    if (paramZzn != null) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zzb(bool, "listener can't be null.");
    zza = paramZzn;
  }
  
  public final void zza(LocationSettingsResult paramLocationSettingsResult)
    throws RemoteException
  {
    zza.zza(paramLocationSettingsResult);
    zza = null;
  }
}

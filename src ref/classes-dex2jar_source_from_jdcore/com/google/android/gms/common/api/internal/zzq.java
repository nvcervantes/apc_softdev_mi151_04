package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.zzf;

final class zzq
  implements Runnable
{
  private final zzp zzb;
  
  zzq(zzo paramZzo, zzp paramZzp)
  {
    zzb = paramZzp;
  }
  
  @MainThread
  public final void run()
  {
    if (!zza.zza) {
      return;
    }
    Object localObject = zzb.zzb();
    if (((ConnectionResult)localObject).hasResolution())
    {
      zza.zzd.startActivityForResult(GoogleApiActivity.zza(zza.zzg(), ((ConnectionResult)localObject).getResolution(), zzb.zza(), false), 1);
      return;
    }
    if (zza.zzc.isUserResolvableError(((ConnectionResult)localObject).getErrorCode()))
    {
      zza.zzc.zza(zza.zzg(), zza.zzd, ((ConnectionResult)localObject).getErrorCode(), 2, zza);
      return;
    }
    if (((ConnectionResult)localObject).getErrorCode() == 18)
    {
      localObject = GoogleApiAvailability.zza(zza.zzg(), zza);
      GoogleApiAvailability.zza(zza.zzg().getApplicationContext(), new zzr(this, (Dialog)localObject));
      return;
    }
    zza.zza((ConnectionResult)localObject, zzb.zza());
  }
}

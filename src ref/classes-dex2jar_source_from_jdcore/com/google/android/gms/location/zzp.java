package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.internal.zzcgl;
import com.google.android.gms.internal.zzcgs;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzp
  extends zzcgs
{
  zzp(FusedLocationProviderClient paramFusedLocationProviderClient, TaskCompletionSource paramTaskCompletionSource) {}
  
  public final void zza(zzcgl paramZzcgl)
    throws RemoteException
  {
    paramZzcgl = paramZzcgl.getStatus();
    if (paramZzcgl == null)
    {
      zza.trySetException(new ApiException(new Status(8, "Got null status from location service")));
      return;
    }
    if (paramZzcgl.getStatusCode() == 0)
    {
      zza.setResult(Boolean.valueOf(true));
      return;
    }
    zza.trySetException(zzb.zza(paramZzcgl));
  }
}

package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.common.zzf;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

public class zzcn
  extends zzo
{
  private TaskCompletionSource<Void> zze = new TaskCompletionSource();
  
  private zzcn(zzcf paramZzcf)
  {
    super(paramZzcf);
    zzd.zza("GmsAvailabilityHelper", this);
  }
  
  public static zzcn zzb(Activity paramActivity)
  {
    paramActivity = zza(paramActivity);
    zzcn localZzcn = (zzcn)paramActivity.zza("GmsAvailabilityHelper", zzcn.class);
    if (localZzcn != null)
    {
      if (zze.getTask().isComplete()) {
        zze = new TaskCompletionSource();
      }
      return localZzcn;
    }
    return new zzcn(paramActivity);
  }
  
  protected final void zza(ConnectionResult paramConnectionResult, int paramInt)
  {
    zze.setException(zzb.zza(new Status(paramConnectionResult.getErrorCode(), paramConnectionResult.getErrorMessage(), paramConnectionResult.getResolution())));
  }
  
  protected final void zzc()
  {
    int i = zzc.isGooglePlayServicesAvailable(zzd.zza());
    if (i == 0)
    {
      zze.setResult(null);
      return;
    }
    if (!zze.getTask().isComplete()) {
      zzb(new ConnectionResult(i, null), 0);
    }
  }
  
  public final Task<Void> zzf()
  {
    return zze.getTask();
  }
  
  public final void zzh()
  {
    super.zzh();
    zze.trySetException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
  }
}

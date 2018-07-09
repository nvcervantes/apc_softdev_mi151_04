package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;

public final class zzf
  extends zzb<Boolean>
{
  private zzck<?> zzb;
  
  public zzf(zzck<?> paramZzck, TaskCompletionSource<Boolean> paramTaskCompletionSource)
  {
    super(4, paramTaskCompletionSource);
    zzb = paramZzck;
  }
  
  public final void zzb(zzbo<?> paramZzbo)
    throws RemoteException
  {
    zzcr localZzcr = (zzcr)paramZzbo.zzc().remove(zzb);
    if (localZzcr != null)
    {
      zzb.zza(paramZzbo.zzb(), zza);
      zza.zzb();
      return;
    }
    zza.trySetResult(Boolean.valueOf(false));
  }
}

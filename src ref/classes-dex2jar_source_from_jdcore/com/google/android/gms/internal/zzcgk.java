package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;

final class zzcgk
  extends zzcgs
{
  private final zzn<Status> zza;
  
  public zzcgk(zzn<Status> paramZzn)
  {
    zza = paramZzn;
  }
  
  public final void zza(zzcgl paramZzcgl)
  {
    zza.zza(paramZzcgl.getStatus());
  }
}

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbq;

final class zzp
{
  private final int zza;
  private final ConnectionResult zzb;
  
  zzp(ConnectionResult paramConnectionResult, int paramInt)
  {
    zzbq.zza(paramConnectionResult);
    zzb = paramConnectionResult;
    zza = paramInt;
  }
  
  final int zza()
  {
    return zza;
  }
  
  final ConnectionResult zzb()
  {
    return zzb;
  }
}

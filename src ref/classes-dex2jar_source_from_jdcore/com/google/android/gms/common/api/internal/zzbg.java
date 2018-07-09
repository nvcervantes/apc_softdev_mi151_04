package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

final class zzbg
  extends zzby
{
  private WeakReference<zzba> zza;
  
  zzbg(zzba paramZzba)
  {
    zza = new WeakReference(paramZzba);
  }
  
  public final void zza()
  {
    zzba localZzba = (zzba)zza.get();
    if (localZzba == null) {
      return;
    }
    zzba.zza(localZzba);
  }
}

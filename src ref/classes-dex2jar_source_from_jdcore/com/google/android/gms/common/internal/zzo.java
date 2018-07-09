package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

@Hide
public final class zzo
  extends zze
{
  @BinderThread
  public zzo(zzd paramZzd, @Nullable int paramInt, Bundle paramBundle)
  {
    super(paramZzd, paramInt, null);
  }
  
  protected final void zza(ConnectionResult paramConnectionResult)
  {
    zza.zzb.zza(paramConnectionResult);
    zza.zza(paramConnectionResult);
  }
  
  protected final boolean zza()
  {
    zza.zzb.zza(ConnectionResult.zza);
    return true;
  }
}

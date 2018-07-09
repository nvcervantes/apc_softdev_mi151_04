package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

@Hide
public final class zzk
  extends zzax
{
  private zzd zza;
  private final int zzb;
  
  public zzk(@NonNull zzd paramZzd, int paramInt)
  {
    zza = paramZzd;
    zzb = paramInt;
  }
  
  @BinderThread
  public final void zza(int paramInt, @Nullable Bundle paramBundle)
  {
    Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
  }
  
  @BinderThread
  public final void zza(int paramInt, @NonNull IBinder paramIBinder, @Nullable Bundle paramBundle)
  {
    zzbq.zza(zza, "onPostInitComplete can be called only once per call to getRemoteService");
    zza.zza(paramInt, paramIBinder, paramBundle, zzb);
    zza = null;
  }
}

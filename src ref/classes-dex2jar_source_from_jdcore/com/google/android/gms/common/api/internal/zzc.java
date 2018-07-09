package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public final class zzc<A extends zzm<? extends Result, Api.zzb>>
  extends zza
{
  private A zza;
  
  public zzc(int paramInt, A paramA)
  {
    super(paramInt);
    zza = paramA;
  }
  
  public final void zza(@NonNull Status paramStatus)
  {
    zza.zzc(paramStatus);
  }
  
  public final void zza(@NonNull zzae paramZzae, boolean paramBoolean)
  {
    paramZzae.zza(zza, paramBoolean);
  }
  
  public final void zza(zzbo<?> paramZzbo)
    throws DeadObjectException
  {
    try
    {
      zza.zzb(paramZzbo.zzb());
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      paramZzbo = localRuntimeException.getClass().getSimpleName();
      String str = localRuntimeException.getLocalizedMessage();
      StringBuilder localStringBuilder = new StringBuilder(2 + String.valueOf(paramZzbo).length() + String.valueOf(str).length());
      localStringBuilder.append(paramZzbo);
      localStringBuilder.append(": ");
      localStringBuilder.append(str);
      paramZzbo = new Status(10, localStringBuilder.toString());
      zza.zzc(paramZzbo);
    }
  }
}

package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.zzc;

@Hide
public final class zzccl
{
  private boolean zza = false;
  private zzccm zzb = null;
  
  public zzccl() {}
  
  public final <T> T zza(zzcce<T> paramZzcce)
  {
    try
    {
      if (!zza)
      {
        paramZzcce = paramZzcce.zzb();
        return paramZzcce;
      }
      return paramZzcce.zza(zzb);
    }
    finally {}
  }
  
  public final void zza(Context paramContext)
  {
    try
    {
      if (zza) {
        return;
      }
      try
      {
        zzb = zzccn.asInterface(DynamiteModule.zza(paramContext, DynamiteModule.zze, "com.google.android.gms.flags").zza("com.google.android.gms.flags.impl.FlagProviderImpl"));
        zzb.init(zzn.zza(paramContext));
        zza = true;
      }
      catch (DynamiteModule.zzc|RemoteException paramContext)
      {
        Log.w("FlagValueProvider", "Failed to initialize flags module.", paramContext);
      }
      return;
    }
    finally {}
  }
}

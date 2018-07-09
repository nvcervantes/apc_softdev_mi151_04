package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;

public abstract class zzag
{
  private static final Object zza = new Object();
  private static zzag zzb;
  
  public zzag() {}
  
  public static zzag zza(Context paramContext)
  {
    synchronized (zza)
    {
      if (zzb == null) {
        zzb = new zzai(paramContext.getApplicationContext());
      }
      return zzb;
    }
  }
  
  public final void zza(String paramString1, String paramString2, int paramInt, ServiceConnection paramServiceConnection, String paramString3)
  {
    zzb(new zzah(paramString1, paramString2, paramInt), paramServiceConnection, paramString3);
  }
  
  public final boolean zza(ComponentName paramComponentName, ServiceConnection paramServiceConnection, String paramString)
  {
    return zza(new zzah(paramComponentName, 129), paramServiceConnection, paramString);
  }
  
  protected abstract boolean zza(zzah paramZzah, ServiceConnection paramServiceConnection, String paramString);
  
  public final void zzb(ComponentName paramComponentName, ServiceConnection paramServiceConnection, String paramString)
  {
    zzb(new zzah(paramComponentName, 129), paramServiceConnection, paramString);
  }
  
  protected abstract void zzb(zzah paramZzah, ServiceConnection paramServiceConnection, String paramString);
}

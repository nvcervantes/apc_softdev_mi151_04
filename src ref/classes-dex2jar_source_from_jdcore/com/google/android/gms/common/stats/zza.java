package com.google.android.gms.common.stats;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import com.google.android.gms.common.util.zzd;
import java.util.Collections;
import java.util.List;

public final class zza
{
  private static final Object zza = new Object();
  private static volatile zza zzb;
  private static boolean zzc = false;
  private final List<String> zzd = Collections.EMPTY_LIST;
  private final List<String> zze = Collections.EMPTY_LIST;
  private final List<String> zzf = Collections.EMPTY_LIST;
  private final List<String> zzg = Collections.EMPTY_LIST;
  
  private zza() {}
  
  public static zza zza()
  {
    if (zzb == null) {
      synchronized (zza)
      {
        if (zzb == null) {
          zzb = new zza();
        }
      }
    }
    return zzb;
  }
  
  public final boolean zza(Context paramContext, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt)
  {
    return zza(paramContext, paramContext.getClass().getName(), paramIntent, paramServiceConnection, paramInt);
  }
  
  public final boolean zza(Context paramContext, String paramString, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt)
  {
    paramString = paramIntent.getComponent();
    boolean bool;
    if (paramString == null) {
      bool = false;
    } else {
      bool = zzd.zzb(paramContext, paramString.getPackageName());
    }
    if (bool)
    {
      Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
      return false;
    }
    return paramContext.bindService(paramIntent, paramServiceConnection, paramInt);
  }
}

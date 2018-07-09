package com.google.android.gms.common.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public final class zzbx
  extends BroadcastReceiver
{
  private Context zza;
  private final zzby zzb;
  
  public zzbx(zzby paramZzby)
  {
    zzb = paramZzby;
  }
  
  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    paramContext = paramIntent.getData();
    if (paramContext != null) {
      paramContext = paramContext.getSchemeSpecificPart();
    } else {
      paramContext = null;
    }
    if ("com.google.android.gms".equals(paramContext))
    {
      zzb.zza();
      zza();
    }
  }
  
  public final void zza()
  {
    try
    {
      if (zza != null) {
        zza.unregisterReceiver(this);
      }
      zza = null;
      return;
    }
    finally {}
  }
  
  public final void zza(Context paramContext)
  {
    zza = paramContext;
  }
}

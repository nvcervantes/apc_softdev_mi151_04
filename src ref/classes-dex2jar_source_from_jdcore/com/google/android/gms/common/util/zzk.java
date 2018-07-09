package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;

public final class zzk
{
  private static IntentFilter zza = new IntentFilter("android.intent.action.BATTERY_CHANGED");
  private static long zzb = 0L;
  private static float zzc = NaN.0F;
  
  @TargetApi(20)
  public static int zza(Context paramContext)
  {
    if (paramContext != null)
    {
      if (paramContext.getApplicationContext() == null) {
        return -1;
      }
      Intent localIntent = paramContext.getApplicationContext().registerReceiver(null, zza);
      int j = 0;
      int i;
      if (localIntent == null) {
        i = 0;
      } else {
        i = localIntent.getIntExtra("plugged", 0);
      }
      if ((i & 0x7) != 0) {
        i = 1;
      } else {
        i = 0;
      }
      paramContext = (PowerManager)paramContext.getSystemService("power");
      if (paramContext == null) {
        return -1;
      }
      boolean bool;
      if (zzs.zzf()) {
        bool = paramContext.isInteractive();
      } else {
        bool = paramContext.isScreenOn();
      }
      if (bool) {
        j = 1;
      }
      return j << 1 | i;
    }
    return -1;
  }
  
  public static float zzb(Context paramContext)
  {
    try
    {
      if ((SystemClock.elapsedRealtime() - zzb < 60000L) && (!Float.isNaN(zzc)))
      {
        f = zzc;
        return f;
      }
      paramContext = paramContext.getApplicationContext().registerReceiver(null, zza);
      if (paramContext != null)
      {
        int i = paramContext.getIntExtra("level", -1);
        int j = paramContext.getIntExtra("scale", -1);
        zzc = i / j;
      }
      zzb = SystemClock.elapsedRealtime();
      float f = zzc;
      return f;
    }
    finally {}
  }
}

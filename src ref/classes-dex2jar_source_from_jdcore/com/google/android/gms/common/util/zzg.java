package com.google.android.gms.common.util;

import android.content.Context;
import android.os.DropBoxManager;
import android.util.Log;
import com.google.android.gms.common.internal.zzbq;

public final class zzg
{
  private static final String[] zza = { "android.", "com.android.", "dalvik.", "java.", "javax." };
  private static DropBoxManager zzb;
  private static boolean zzc = false;
  private static int zzd = -1;
  private static int zze;
  
  public static boolean zza(Context paramContext, Throwable paramThrowable)
  {
    return zza(paramContext, paramThrowable, 536870912);
  }
  
  private static boolean zza(Context paramContext, Throwable paramThrowable, int paramInt)
  {
    try
    {
      zzbq.zza(paramContext);
      zzbq.zza(paramThrowable);
      return false;
    }
    catch (Exception paramContext)
    {
      Log.e("CrashUtils", "Error adding exception to DropBox!", paramContext);
    }
    return false;
  }
}

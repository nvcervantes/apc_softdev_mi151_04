package com.google.android.gms.common.stats;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.zzk;
import java.util.List;

public final class zze
{
  private static zze zza = new zze();
  private static Boolean zzb;
  private static boolean zzc = false;
  
  public zze() {}
  
  public static zze zza()
  {
    return zza;
  }
  
  public static void zza(Context paramContext, String paramString1, int paramInt1, String paramString2, String paramString3, String paramString4, int paramInt2, List<String> paramList)
  {
    zza(paramContext, paramString1, 8, paramString2, paramString3, paramString4, paramInt2, paramList, 0L);
  }
  
  public static void zza(Context paramContext, String paramString1, int paramInt1, String paramString2, String paramString3, String paramString4, int paramInt2, List<String> paramList, long paramLong)
  {
    List<String> localList2 = paramList;
    if (zzb == null) {
      zzb = Boolean.valueOf(false);
    }
    if (!zzb.booleanValue()) {
      return;
    }
    if (TextUtils.isEmpty(paramString1))
    {
      paramContext = String.valueOf(paramString1);
      if (paramContext.length() != 0) {
        paramContext = "missing wakeLock key. ".concat(paramContext);
      } else {
        paramContext = new String("missing wakeLock key. ");
      }
      Log.e("WakeLockTracker", paramContext);
      return;
    }
    long l1 = System.currentTimeMillis();
    if ((7 == paramInt1) || (8 == paramInt1) || (10 == paramInt1) || (11 == paramInt1))
    {
      List<String> localList1 = localList2;
      if (localList2 != null)
      {
        localList1 = localList2;
        if (paramList.size() == 1)
        {
          localList1 = localList2;
          if ("com.google.android.gms".equals(localList2.get(0))) {
            localList1 = null;
          }
        }
      }
      long l2 = SystemClock.elapsedRealtime();
      int i = zzk.zza(paramContext);
      paramList = paramContext.getPackageName();
      if ("com.google.android.gms".equals(paramList)) {
        paramList = null;
      }
      paramString1 = new WakeLockEvent(l1, paramInt1, paramString2, paramInt2, localList1, paramString1, l2, i, paramString3, paramList, zzk.zzb(paramContext), paramLong, paramString4);
      try
      {
        paramContext.startService(new Intent().setComponent(zzb.zza).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", paramString1));
        return;
      }
      catch (Exception paramContext)
      {
        Log.wtf("WakeLockTracker", paramContext);
      }
    }
  }
}

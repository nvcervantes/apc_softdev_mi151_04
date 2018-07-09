package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.stats.zzc;
import com.google.android.gms.common.stats.zze;
import com.google.android.gms.common.util.zzaa;
import com.google.android.gms.common.util.zzw;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Hide
public final class zzcyz
{
  private static String zza = "WakeLock";
  private static String zzb = "*gcore*:";
  private static boolean zzc = false;
  private static ScheduledExecutorService zzo;
  private final PowerManager.WakeLock zzd;
  private WorkSource zze;
  private final int zzf;
  private final String zzg;
  private final String zzh;
  private final String zzi;
  private final Context zzj;
  private boolean zzk = true;
  private final Map<String, Integer[]> zzl = new HashMap();
  private int zzm;
  private AtomicInteger zzn = new AtomicInteger(0);
  
  public zzcyz(Context paramContext, int paramInt, String paramString)
  {
    this(paramContext, 1, paramString, null, str);
  }
  
  @SuppressLint({"UnwrappedWakeLock"})
  @Hide
  private zzcyz(Context paramContext, int paramInt, String paramString1, String paramString2, String paramString3)
  {
    this(paramContext, 1, paramString1, null, paramString3, null);
  }
  
  @SuppressLint({"UnwrappedWakeLock"})
  @Hide
  private zzcyz(Context paramContext, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    zzbq.zza(paramString1, "Wake lock name can NOT be empty");
    zzf = paramInt;
    zzh = null;
    zzi = null;
    zzj = paramContext.getApplicationContext();
    if (!"com.google.android.gms".equals(paramContext.getPackageName()))
    {
      paramString2 = String.valueOf(zzb);
      paramString4 = String.valueOf(paramString1);
      if (paramString4.length() != 0) {
        paramString2 = paramString2.concat(paramString4);
      } else {
        paramString2 = new String(paramString2);
      }
      zzg = paramString2;
    }
    else
    {
      zzg = paramString1;
    }
    zzd = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(paramInt, paramString1);
    if (zzaa.zza(zzj))
    {
      paramString1 = paramString3;
      if (zzw.zza(paramString3)) {
        paramString1 = paramContext.getPackageName();
      }
      zze = zzaa.zza(paramContext, paramString1);
      paramContext = zze;
      if ((paramContext != null) && (zzaa.zza(zzj)))
      {
        if (zze != null) {
          zze.add(paramContext);
        } else {
          zze = paramContext;
        }
        paramContext = zze;
        try
        {
          zzd.setWorkSource(paramContext);
        }
        catch (IllegalArgumentException paramContext)
        {
          Log.wtf(zza, paramContext.toString());
        }
      }
    }
    if (zzo == null) {
      zzo = zzbhg.zza().zza();
    }
  }
  
  private final String zza(String paramString)
  {
    if (zzk)
    {
      if (!TextUtils.isEmpty(paramString)) {
        return paramString;
      }
      return zzh;
    }
    return zzh;
  }
  
  private final void zza(int paramInt)
  {
    if (zzd.isHeld()) {
      try
      {
        zzd.release();
        return;
      }
      catch (RuntimeException localRuntimeException)
      {
        if (localRuntimeException.getClass().equals(RuntimeException.class))
        {
          Log.e(zza, String.valueOf(zzg).concat("was already released!"), new IllegalStateException());
          return;
        }
        throw localRuntimeException;
      }
    }
  }
  
  public final void zza()
  {
    if (zzn.decrementAndGet() < 0) {
      Log.e(zza, "release without a matched acquire!");
    }
    String str = zza(null);
    for (;;)
    {
      try
      {
        if (zzk)
        {
          Integer[] arrayOfInteger = (Integer[])zzl.get(str);
          if (arrayOfInteger != null)
          {
            if (arrayOfInteger[0].intValue() == 1)
            {
              zzl.remove(str);
              i = 1;
              break label176;
            }
            arrayOfInteger[0] = Integer.valueOf(arrayOfInteger[0].intValue() - 1);
          }
        }
        else
        {
          if ((!zzk) && (zzm == 1))
          {
            zze.zza();
            zze.zza(zzj, zzc.zza(zzd, str), 8, zzg, str, null, zzf, zzaa.zza(zze));
            zzm -= 1;
          }
          zza(0);
          return;
        }
      }
      finally {}
      int i = 0;
      label176:
      if (i != 0) {}
    }
  }
  
  public final void zza(long paramLong)
  {
    zzn.incrementAndGet();
    String str = zza(null);
    for (;;)
    {
      int i;
      try
      {
        boolean bool = zzl.isEmpty();
        i = 0;
        if (((!bool) || (zzm > 0)) && (!zzd.isHeld()))
        {
          zzl.clear();
          zzm = 0;
        }
        if (zzk)
        {
          Integer[] arrayOfInteger = (Integer[])zzl.get(str);
          if (arrayOfInteger == null)
          {
            zzl.put(str, new Integer[] { Integer.valueOf(1) });
            i = 1;
          }
          else
          {
            arrayOfInteger[0] = Integer.valueOf(arrayOfInteger[0].intValue() + 1);
          }
        }
        else
        {
          if ((!zzk) && (zzm == 0))
          {
            zze.zza();
            zze.zza(zzj, zzc.zza(zzd, str), 7, zzg, str, null, zzf, zzaa.zza(zze), 1000L);
            zzm += 1;
          }
          zzd.acquire();
          zzo.schedule(new zzcza(this), 1000L, TimeUnit.MILLISECONDS);
          return;
        }
      }
      finally {}
      if (i != 0) {}
    }
  }
  
  public final void zza(boolean paramBoolean)
  {
    zzd.setReferenceCounted(false);
    zzk = false;
  }
  
  public final boolean zzb()
  {
    return zzd.isHeld();
  }
}

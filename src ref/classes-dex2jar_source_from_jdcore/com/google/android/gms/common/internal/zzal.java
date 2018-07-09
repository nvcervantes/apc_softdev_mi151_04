package com.google.android.gms.common.internal;

import android.util.Log;

public final class zzal
{
  private static int zza = 15;
  private static final String zzb;
  private final String zzc;
  private final String zzd;
  
  public zzal(String paramString)
  {
    this(paramString, null);
  }
  
  public zzal(String paramString1, String paramString2)
  {
    zzbq.zza(paramString1, "log tag cannot be null");
    boolean bool;
    if (paramString1.length() <= 23) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zzb(bool, "tag \"%s\" is longer than the %d character maximum", new Object[] { paramString1, Integer.valueOf(23) });
    zzc = paramString1;
    if ((paramString2 != null) && (paramString2.length() > 0))
    {
      zzd = paramString2;
      return;
    }
    zzd = null;
  }
  
  private final String zza(String paramString)
  {
    if (zzd == null) {
      return paramString;
    }
    return zzd.concat(paramString);
  }
  
  private final String zza(String paramString, Object... paramVarArgs)
  {
    paramString = String.format(paramString, paramVarArgs);
    if (zzd == null) {
      return paramString;
    }
    return zzd.concat(paramString);
  }
  
  private final boolean zza(int paramInt)
  {
    return Log.isLoggable(zzc, paramInt);
  }
  
  public final void zza(String paramString1, String paramString2)
  {
    if (zza(3)) {
      Log.d(paramString1, zza(paramString2));
    }
  }
  
  public final void zza(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (zza(4)) {
      Log.i(paramString1, zza(paramString2), paramThrowable);
    }
  }
  
  public final void zza(String paramString1, String paramString2, Object... paramVarArgs)
  {
    if (zza(3)) {
      Log.d(paramString1, zza(paramString2, paramVarArgs));
    }
  }
  
  public final void zzb(String paramString1, String paramString2)
  {
    if (zza(5)) {
      Log.w(paramString1, zza(paramString2));
    }
  }
  
  public final void zzb(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (zza(5)) {
      Log.w(paramString1, zza(paramString2), paramThrowable);
    }
  }
  
  public final void zzb(String paramString1, String paramString2, Object... paramVarArgs)
  {
    if (zza(5)) {
      Log.w(zzc, zza(paramString2, paramVarArgs));
    }
  }
  
  public final void zzc(String paramString1, String paramString2)
  {
    if (zza(6)) {
      Log.e(paramString1, zza(paramString2));
    }
  }
  
  public final void zzc(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (zza(6)) {
      Log.e(paramString1, zza(paramString2), paramThrowable);
    }
  }
  
  public final void zzc(String paramString1, String paramString2, Object... paramVarArgs)
  {
    if (zza(6)) {
      Log.e(paramString1, zza(paramString2, paramVarArgs));
    }
  }
}

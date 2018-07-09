package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.zzal;
import java.util.Locale;

public final class zzbhf
{
  private final String zza;
  private final String zzb;
  private final zzal zzc;
  private final int zzd;
  
  private zzbhf(String paramString1, String paramString2)
  {
    zzb = paramString2;
    zza = paramString1;
    zzc = new zzal(paramString1);
    zzd = zza();
  }
  
  public zzbhf(String paramString, String... paramVarArgs)
  {
    this(paramString, zza(paramVarArgs));
  }
  
  private final int zza()
  {
    int i = 2;
    while ((7 >= i) && (!Log.isLoggable(zza, i))) {
      i += 1;
    }
    return i;
  }
  
  private static String zza(String... paramVarArgs)
  {
    if (paramVarArgs.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[');
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      String str = paramVarArgs[i];
      if (localStringBuilder.length() > 1) {
        localStringBuilder.append(",");
      }
      localStringBuilder.append(str);
      i += 1;
    }
    localStringBuilder.append(']');
    localStringBuilder.append(' ');
    return localStringBuilder.toString();
  }
  
  private final boolean zza(int paramInt)
  {
    return zzd <= paramInt;
  }
  
  private final String zzf(String paramString, @Nullable Object... paramVarArgs)
  {
    String str = paramString;
    if (paramVarArgs != null)
    {
      str = paramString;
      if (paramVarArgs.length > 0) {
        str = String.format(Locale.US, paramString, paramVarArgs);
      }
    }
    return zzb.concat(str);
  }
  
  public final void zza(String paramString, Throwable paramThrowable, @Nullable Object... paramVarArgs)
  {
    Log.wtf(zza, zzf(paramString, paramVarArgs), paramThrowable);
  }
  
  public final void zza(String paramString, @Nullable Object... paramVarArgs)
  {
    if (zza(2)) {
      Log.v(zza, zzf(paramString, paramVarArgs));
    }
  }
  
  public final void zza(Throwable paramThrowable)
  {
    Log.wtf(zza, paramThrowable);
  }
  
  public final void zzb(String paramString, @Nullable Object... paramVarArgs)
  {
    if (zza(3)) {
      Log.d(zza, zzf(paramString, paramVarArgs));
    }
  }
  
  public final void zzc(String paramString, @Nullable Object... paramVarArgs)
  {
    Log.i(zza, zzf(paramString, paramVarArgs));
  }
  
  public final void zzd(String paramString, @Nullable Object... paramVarArgs)
  {
    Log.w(zza, zzf(paramString, paramVarArgs));
  }
  
  public final void zze(String paramString, @Nullable Object... paramVarArgs)
  {
    Log.e(zza, zzf(paramString, paramVarArgs));
  }
}

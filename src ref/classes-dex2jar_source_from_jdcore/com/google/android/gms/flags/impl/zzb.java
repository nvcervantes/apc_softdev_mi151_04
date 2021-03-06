package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gms.internal.zzccq;

public final class zzb
  extends zza<Boolean>
{
  public static Boolean zza(SharedPreferences paramSharedPreferences, String paramString, Boolean paramBoolean)
  {
    try
    {
      paramSharedPreferences = (Boolean)zzccq.zza(new zzc(paramSharedPreferences, paramString, paramBoolean));
      return paramSharedPreferences;
    }
    catch (Exception paramSharedPreferences)
    {
      paramSharedPreferences = String.valueOf(paramSharedPreferences.getMessage());
      if (paramSharedPreferences.length() != 0) {
        paramSharedPreferences = "Flag value not available, returning default: ".concat(paramSharedPreferences);
      } else {
        paramSharedPreferences = new String("Flag value not available, returning default: ");
      }
      Log.w("FlagDataUtils", paramSharedPreferences);
    }
    return paramBoolean;
  }
}

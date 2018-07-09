package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.zzs;

@Hide
public final class zzb
{
  private SharedPreferences zza;
  
  @Hide
  public zzb(Context paramContext)
  {
    try
    {
      paramContext = zzs.getRemoteContext(paramContext);
      if (paramContext == null) {
        paramContext = null;
      } else {
        paramContext = paramContext.getSharedPreferences("google_ads_flags", 0);
      }
      zza = paramContext;
      return;
    }
    catch (Throwable paramContext)
    {
      Log.w("GmscoreFlag", "Error while getting SharedPreferences ", paramContext);
      zza = null;
    }
  }
  
  final float zza(String paramString, float paramFloat)
  {
    try
    {
      if (zza == null) {
        return 0.0F;
      }
      paramFloat = zza.getFloat(paramString, 0.0F);
      return paramFloat;
    }
    catch (Throwable paramString)
    {
      Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", paramString);
    }
    return 0.0F;
  }
  
  final String zza(String paramString1, String paramString2)
  {
    try
    {
      if (zza == null) {
        return paramString2;
      }
      paramString1 = zza.getString(paramString1, paramString2);
      return paramString1;
    }
    catch (Throwable paramString1)
    {
      Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", paramString1);
    }
    return paramString2;
  }
  
  public final boolean zza(String paramString, boolean paramBoolean)
  {
    try
    {
      if (zza == null) {
        return false;
      }
      paramBoolean = zza.getBoolean(paramString, false);
      return paramBoolean;
    }
    catch (Throwable paramString)
    {
      Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", paramString);
    }
    return false;
  }
}

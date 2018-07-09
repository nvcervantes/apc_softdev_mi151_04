package com.google.android.gms.phenotype;

import android.content.SharedPreferences;
import android.util.Log;

final class zzs
  extends PhenotypeFlag<String>
{
  zzs(PhenotypeFlag.Factory paramFactory, String paramString1, String paramString2)
  {
    super(paramFactory, paramString1, paramString2, null);
  }
  
  private final String zzb(SharedPreferences paramSharedPreferences)
  {
    try
    {
      paramSharedPreferences = paramSharedPreferences.getString(zza, null);
      return paramSharedPreferences;
    }
    catch (ClassCastException localClassCastException)
    {
      paramSharedPreferences = String.valueOf(zza);
      if (paramSharedPreferences.length() != 0) {
        paramSharedPreferences = "Invalid string value in SharedPreferences for ".concat(paramSharedPreferences);
      } else {
        paramSharedPreferences = new String("Invalid string value in SharedPreferences for ");
      }
      Log.e("PhenotypeFlag", paramSharedPreferences, localClassCastException);
    }
    return null;
  }
}

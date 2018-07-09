package com.google.android.gms.internal;

final class zzfgo
{
  private static final Class<?> zza = zza("libcore.io.Memory");
  private static final boolean zzb;
  
  static
  {
    boolean bool;
    if (zza("org.robolectric.Robolectric") != null) {
      bool = true;
    } else {
      bool = false;
    }
    zzb = bool;
  }
  
  private static <T> Class<T> zza(String paramString)
  {
    try
    {
      paramString = Class.forName(paramString);
      return paramString;
    }
    catch (Throwable paramString)
    {
      for (;;) {}
    }
    return null;
  }
  
  static boolean zza()
  {
    return (zza != null) && (!zzb);
  }
  
  static Class<?> zzb()
  {
    return zza;
  }
}

package com.google.android.gms.internal;

import java.lang.reflect.Constructor;

final class zzfjk
{
  private static final zzfji zza = ;
  private static final zzfji zzb = new zzfjj();
  
  static zzfji zza()
  {
    return zza;
  }
  
  static zzfji zzb()
  {
    return zzb;
  }
  
  private static zzfji zzc()
  {
    try
    {
      zzfji localZzfji = (zzfji)Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
      return localZzfji;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
}

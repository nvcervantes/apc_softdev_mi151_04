package com.google.android.gms.internal;

import java.lang.reflect.Constructor;

final class zzfhp
{
  private static final zzfhn<?> zza = new zzfho();
  private static final zzfhn<?> zzb = zzc();
  
  static zzfhn<?> zza()
  {
    return zza;
  }
  
  static zzfhn<?> zzb()
  {
    if (zzb == null) {
      throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
    return zzb;
  }
  
  private static zzfhn<?> zzc()
  {
    try
    {
      zzfhn localZzfhn = (zzfhn)Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
      return localZzfhn;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
}

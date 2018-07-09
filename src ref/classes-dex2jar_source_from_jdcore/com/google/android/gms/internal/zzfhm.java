package com.google.android.gms.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzfhm
{
  static final zzfhm zza = new zzfhm(true);
  private static volatile boolean zzb = false;
  private static final Class<?> zzc = ;
  private final Map<Object, Object> zzd;
  
  zzfhm()
  {
    zzd = new HashMap();
  }
  
  private zzfhm(boolean paramBoolean)
  {
    zzd = Collections.emptyMap();
  }
  
  public static zzfhm zza()
  {
    return zzfhl.zza();
  }
  
  private static Class<?> zzb()
  {
    try
    {
      Class localClass = Class.forName("com.google.protobuf.Extension");
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;) {}
    }
    return null;
  }
}

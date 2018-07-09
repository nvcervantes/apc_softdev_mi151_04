package com.google.android.gms.internal;

import java.lang.reflect.Method;

final class zzfhl
{
  private static Class<?> zza = ;
  
  public static zzfhm zza()
  {
    if (zza != null) {}
    try
    {
      zzfhm localZzfhm = zza("getEmptyRegistry");
      return localZzfhm;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return zzfhm.zza;
  }
  
  private static final zzfhm zza(String paramString)
    throws Exception
  {
    return (zzfhm)zza.getDeclaredMethod(paramString, new Class[0]).invoke(null, new Object[0]);
  }
  
  private static Class<?> zzb()
  {
    try
    {
      Class localClass = Class.forName("com.google.protobuf.ExtensionRegistry");
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;) {}
    }
    return null;
  }
}

package com.google.android.gms.internal;

import java.lang.reflect.Constructor;

final class zzfjx
{
  private static final Class<?> zza = ;
  private static final zzfkn<?, ?> zzb = zza(false);
  private static final zzfkn<?, ?> zzc = zza(true);
  private static final zzfkn<?, ?> zzd = new zzfkp();
  
  public static zzfkn<?, ?> zza()
  {
    return zzb;
  }
  
  private static zzfkn<?, ?> zza(boolean paramBoolean)
  {
    try
    {
      Object localObject = zze();
      if (localObject == null) {
        return null;
      }
      localObject = (zzfkn)((Class)localObject).getConstructor(new Class[] { Boolean.TYPE }).newInstance(new Object[] { Boolean.valueOf(paramBoolean) });
      return localObject;
    }
    catch (Throwable localThrowable) {}
    return null;
  }
  
  public static void zza(Class<?> paramClass)
  {
    if ((!zzfhu.class.isAssignableFrom(paramClass)) && (zza != null) && (!zza.isAssignableFrom(paramClass))) {
      throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
    }
  }
  
  public static zzfkn<?, ?> zzb()
  {
    return zzc;
  }
  
  public static zzfkn<?, ?> zzc()
  {
    return zzd;
  }
  
  private static Class<?> zzd()
  {
    try
    {
      Class localClass = Class.forName("com.google.protobuf.GeneratedMessage");
      return localClass;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return null;
  }
  
  private static Class<?> zze()
  {
    try
    {
      Class localClass = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
      return localClass;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return null;
  }
}

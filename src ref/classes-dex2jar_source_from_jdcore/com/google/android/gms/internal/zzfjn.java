package com.google.android.gms.internal;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzfjn
{
  private static final zzfjn zza = new zzfjn();
  private final zzfjw zzb;
  private final ConcurrentMap<Class<?>, zzfjv<?>> zzc = new ConcurrentHashMap();
  
  private zzfjn()
  {
    Object localObject1 = null;
    int i = 0;
    while (i <= 0)
    {
      localObject2 = zza(new String[] { "com.google.protobuf.AndroidProto3SchemaFactory" }[0]);
      localObject1 = localObject2;
      if (localObject2 != null) {
        break;
      }
      i += 1;
      localObject1 = localObject2;
    }
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = new zzfiq();
    }
    zzb = ((zzfjw)localObject2);
  }
  
  public static zzfjn zza()
  {
    return zza;
  }
  
  private static zzfjw zza(String paramString)
  {
    try
    {
      paramString = (zzfjw)Class.forName(paramString).getConstructor(new Class[0]).newInstance(new Object[0]);
      return paramString;
    }
    catch (Throwable paramString)
    {
      for (;;) {}
    }
    return null;
  }
  
  public final <T> zzfjv<T> zza(Class<T> paramClass)
  {
    zzfhz.zza(paramClass, "messageType");
    zzfjv localZzfjv = (zzfjv)zzc.get(paramClass);
    Object localObject = localZzfjv;
    if (localZzfjv == null)
    {
      localObject = zzb.zza(paramClass);
      zzfhz.zza(paramClass, "messageType");
      zzfhz.zza(localObject, "schema");
      paramClass = (zzfjv)zzc.putIfAbsent(paramClass, localObject);
      if (paramClass != null) {
        localObject = paramClass;
      }
    }
    return localObject;
  }
}

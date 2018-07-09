package com.google.android.gms.internal;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

final class zzfht
  implements zzfjb
{
  private static final zzfht zza = new zzfht();
  private final Map<Class<?>, Method> zzb = new HashMap();
  
  private zzfht() {}
  
  public static zzfht zza()
  {
    return zza;
  }
  
  public final boolean zza(Class<?> paramClass)
  {
    return zzfhu.class.isAssignableFrom(paramClass);
  }
  
  public final zzfja zzb(Class<?> paramClass)
  {
    if (!zzfhu.class.isAssignableFrom(paramClass))
    {
      paramClass = String.valueOf(paramClass.getName());
      if (paramClass.length() != 0) {
        paramClass = "Unsupported message type: ".concat(paramClass);
      } else {
        paramClass = new String("Unsupported message type: ");
      }
      throw new IllegalArgumentException(paramClass);
    }
    try
    {
      Object localObject = (Method)zzb.get(paramClass);
      if (localObject == null)
      {
        localObject = paramClass.getDeclaredMethod("buildMessageInfo", new Class[0]);
        ((Method)localObject).setAccessible(true);
        zzb.put(paramClass, localObject);
      }
      localObject = (zzfja)((Method)localObject).invoke(null, new Object[0]);
      return localObject;
    }
    catch (Exception localException)
    {
      paramClass = String.valueOf(paramClass.getName());
      if (paramClass.length() != 0) {
        paramClass = "Unable to get message info for ".concat(paramClass);
      } else {
        paramClass = new String("Unable to get message info for ");
      }
      throw new RuntimeException(paramClass, localException);
    }
  }
}

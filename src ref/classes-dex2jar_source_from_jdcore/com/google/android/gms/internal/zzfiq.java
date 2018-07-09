package com.google.android.gms.internal;

import java.lang.reflect.Method;

final class zzfiq
  implements zzfjw
{
  private static final zzfjb zzb = new zzfir();
  private final zzfjb zza;
  
  public zzfiq()
  {
    this(new zzfis(new zzfjb[] { zzfht.zza(), zza() }));
  }
  
  private zzfiq(zzfjb paramZzfjb)
  {
    zza = ((zzfjb)zzfhz.zza(paramZzfjb, "messageInfoFactory"));
  }
  
  private static zzfjb zza()
  {
    try
    {
      zzfjb localZzfjb = (zzfjb)Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
      return localZzfjb;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return zzb;
  }
  
  private static boolean zza(zzfja paramZzfja)
  {
    return paramZzfja.zza() == zzfhu.zzg.zzk;
  }
  
  public final <T> zzfjv<T> zza(Class<T> paramClass)
  {
    zzfjx.zza(paramClass);
    zzfja localZzfja = zza.zzb(paramClass);
    if (localZzfja.zzb())
    {
      if (zzfhu.class.isAssignableFrom(paramClass)) {
        return zzfjh.zza(paramClass, zzfjx.zzc(), zzfhp.zza(), localZzfja.zzc());
      }
      return zzfjh.zza(paramClass, zzfjx.zza(), zzfhp.zzb(), localZzfja.zzc());
    }
    if (zzfhu.class.isAssignableFrom(paramClass))
    {
      if (zza(localZzfja)) {
        return zzfjg.zza(paramClass, localZzfja, zzfjk.zzb(), zzfim.zzb(), zzfjx.zzc(), zzfhp.zza(), zzfiz.zzb());
      }
      return zzfjg.zza(paramClass, localZzfja, zzfjk.zzb(), zzfim.zzb(), zzfjx.zzc(), null, zzfiz.zzb());
    }
    if (zza(localZzfja)) {
      return zzfjg.zza(paramClass, localZzfja, zzfjk.zza(), zzfim.zza(), zzfjx.zza(), zzfhp.zzb(), zzfiz.zza());
    }
    return zzfjg.zza(paramClass, localZzfja, zzfjk.zza(), zzfim.zza(), zzfjx.zzb(), null, zzfiz.zza());
  }
}

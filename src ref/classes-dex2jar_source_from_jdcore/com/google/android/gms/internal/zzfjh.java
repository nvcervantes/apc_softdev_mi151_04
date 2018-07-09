package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzfjh<T>
  implements zzfjv<T>
{
  private final zzfjc zza;
  private final zzfkn<?, ?> zzb;
  private final boolean zzc;
  private final zzfhn<?> zzd;
  
  private zzfjh(Class<T> paramClass, zzfkn<?, ?> paramZzfkn, zzfhn<?> paramZzfhn, zzfjc paramZzfjc)
  {
    zzb = paramZzfkn;
    zzc = paramZzfhn.zza(paramClass);
    zzd = paramZzfhn;
    zza = paramZzfjc;
  }
  
  static <T> zzfjh<T> zza(Class<T> paramClass, zzfkn<?, ?> paramZzfkn, zzfhn<?> paramZzfhn, zzfjc paramZzfjc)
  {
    return new zzfjh(paramClass, paramZzfkn, paramZzfhn, paramZzfjc);
  }
  
  public final int zza(T paramT)
  {
    zzfkn localZzfkn = zzb;
    int j = 0 + localZzfkn.zzb(localZzfkn.zza(paramT));
    int i = j;
    if (zzc) {
      i = j + zzd.zza(paramT).zzc();
    }
    return i;
  }
  
  public final void zza(T paramT, zzfli paramZzfli)
  {
    Iterator localIterator = zzd.zza(paramT).zzb();
    if (localIterator.hasNext())
    {
      localObject = (Map.Entry)localIterator.next();
      zzfhs localZzfhs = (zzfhs)((Map.Entry)localObject).getKey();
      if ((localZzfhs.zzc() == zzfld.zzi) && (!localZzfhs.zzd()) && (!localZzfhs.zze()))
      {
        int i;
        if ((localObject instanceof zzfii)) {
          i = localZzfhs.zza();
        }
        for (localObject = ((zzfii)localObject).zza().zzc();; localObject = ((Map.Entry)localObject).getValue())
        {
          paramZzfli.zza(i, localObject);
          break;
          i = localZzfhs.zza();
        }
      }
      throw new IllegalStateException("Found invalid MessageSet item.");
    }
    Object localObject = zzb;
    ((zzfkn)localObject).zza(((zzfkn)localObject).zza(paramT), paramZzfli);
  }
}

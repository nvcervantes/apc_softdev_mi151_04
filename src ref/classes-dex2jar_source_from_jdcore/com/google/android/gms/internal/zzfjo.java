package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

final class zzfjo<E>
  extends zzfgn<E>
{
  private static final zzfjo<Object> zza;
  private final List<E> zzb;
  
  static
  {
    zzfjo localZzfjo = new zzfjo();
    zza = localZzfjo;
    localZzfjo.zzb();
  }
  
  zzfjo()
  {
    this(new ArrayList(10));
  }
  
  private zzfjo(List<E> paramList)
  {
    zzb = paramList;
  }
  
  public static <E> zzfjo<E> zzd()
  {
    return zza;
  }
  
  public final void add(int paramInt, E paramE)
  {
    zzc();
    zzb.add(paramInt, paramE);
    modCount += 1;
  }
  
  public final E get(int paramInt)
  {
    return zzb.get(paramInt);
  }
  
  public final E remove(int paramInt)
  {
    zzc();
    Object localObject = zzb.remove(paramInt);
    modCount += 1;
    return localObject;
  }
  
  public final E set(int paramInt, E paramE)
  {
    zzc();
    paramE = zzb.set(paramInt, paramE);
    modCount += 1;
    return paramE;
  }
  
  public final int size()
  {
    return zzb.size();
  }
}

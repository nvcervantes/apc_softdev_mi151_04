package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class zzfiw<K, V>
  extends LinkedHashMap<K, V>
{
  private static final zzfiw zzb;
  private boolean zza = true;
  
  static
  {
    zzfiw localZzfiw = new zzfiw();
    zzb = localZzfiw;
    zza = false;
  }
  
  private zzfiw() {}
  
  private zzfiw(Map<K, V> paramMap)
  {
    super(paramMap);
  }
  
  private static int zza(Object paramObject)
  {
    if ((paramObject instanceof byte[])) {
      return zzfhz.zza((byte[])paramObject);
    }
    if ((paramObject instanceof zzfia)) {
      throw new UnsupportedOperationException();
    }
    return paramObject.hashCode();
  }
  
  public static <K, V> zzfiw<K, V> zza()
  {
    return zzb;
  }
  
  private final void zze()
  {
    if (!zza) {
      throw new UnsupportedOperationException();
    }
  }
  
  public final void clear()
  {
    zze();
    super.clear();
  }
  
  public final Set<Map.Entry<K, V>> entrySet()
  {
    if (isEmpty()) {
      return Collections.emptySet();
    }
    return super.entrySet();
  }
  
  public final boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Map))
    {
      paramObject = (Map)paramObject;
      if (this != paramObject)
      {
        if (size() != paramObject.size()) {}
        for (;;)
        {
          i = 0;
          break label165;
          Iterator localIterator = entrySet().iterator();
          boolean bool;
          do
          {
            if (!localIterator.hasNext()) {
              break label163;
            }
            Object localObject2 = (Map.Entry)localIterator.next();
            if (!paramObject.containsKey(((Map.Entry)localObject2).getKey())) {
              break;
            }
            Object localObject1 = ((Map.Entry)localObject2).getValue();
            localObject2 = paramObject.get(((Map.Entry)localObject2).getKey());
            if (((localObject1 instanceof byte[])) && ((localObject2 instanceof byte[]))) {
              bool = Arrays.equals((byte[])localObject1, (byte[])localObject2);
            } else {
              bool = localObject1.equals(localObject2);
            }
          } while (bool);
        }
      }
      label163:
      int i = 1;
      label165:
      if (i != 0) {
        return true;
      }
    }
    return false;
  }
  
  public final int hashCode()
  {
    Iterator localIterator = entrySet().iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      int j = zza(localEntry.getKey());
      i += (zza(localEntry.getValue()) ^ j);
    }
    return i;
  }
  
  public final V put(K paramK, V paramV)
  {
    zze();
    zzfhz.zza(paramK);
    zzfhz.zza(paramV);
    return super.put(paramK, paramV);
  }
  
  public final void putAll(Map<? extends K, ? extends V> paramMap)
  {
    zze();
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      zzfhz.zza(localObject);
      zzfhz.zza(paramMap.get(localObject));
    }
    super.putAll(paramMap);
  }
  
  public final V remove(Object paramObject)
  {
    zze();
    return super.remove(paramObject);
  }
  
  public final void zza(zzfiw<K, V> paramZzfiw)
  {
    zze();
    if (!paramZzfiw.isEmpty()) {
      putAll(paramZzfiw);
    }
  }
  
  public final zzfiw<K, V> zzb()
  {
    if (isEmpty()) {
      return new zzfiw();
    }
    return new zzfiw(this);
  }
  
  public final void zzc()
  {
    zza = false;
  }
  
  public final boolean zzd()
  {
    return zza;
  }
}

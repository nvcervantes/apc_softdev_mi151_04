package com.google.android.gms.internal;

import java.util.Map.Entry;

final class zzfii<K>
  implements Map.Entry<K, Object>
{
  private Map.Entry<K, zzfig> zza;
  
  private zzfii(Map.Entry<K, zzfig> paramEntry)
  {
    zza = paramEntry;
  }
  
  public final K getKey()
  {
    return zza.getKey();
  }
  
  public final Object getValue()
  {
    if ((zzfig)zza.getValue() == null) {
      return null;
    }
    return zzfig.zza();
  }
  
  public final Object setValue(Object paramObject)
  {
    if (!(paramObject instanceof zzfjc)) {
      throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
    return ((zzfig)zza.getValue()).zza((zzfjc)paramObject);
  }
  
  public final zzfig zza()
  {
    return (zzfig)zza.getValue();
  }
}

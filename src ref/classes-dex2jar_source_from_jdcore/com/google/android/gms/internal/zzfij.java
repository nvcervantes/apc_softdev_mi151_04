package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzfij<K>
  implements Iterator<Map.Entry<K, Object>>
{
  private Iterator<Map.Entry<K, Object>> zza;
  
  public zzfij(Iterator<Map.Entry<K, Object>> paramIterator)
  {
    zza = paramIterator;
  }
  
  public final boolean hasNext()
  {
    return zza.hasNext();
  }
  
  public final void remove()
  {
    zza.remove();
  }
}

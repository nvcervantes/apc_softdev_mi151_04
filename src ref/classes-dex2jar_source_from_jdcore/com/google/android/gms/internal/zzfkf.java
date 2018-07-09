package com.google.android.gms.internal;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;

class zzfkf
  extends AbstractSet<Map.Entry<K, V>>
{
  private zzfkf(zzfjy paramZzfjy) {}
  
  public void clear()
  {
    zza.clear();
  }
  
  public boolean contains(Object paramObject)
  {
    Object localObject = (Map.Entry)paramObject;
    paramObject = zza.get(((Map.Entry)localObject).getKey());
    localObject = ((Map.Entry)localObject).getValue();
    return (paramObject == localObject) || ((paramObject != null) && (paramObject.equals(localObject)));
  }
  
  public Iterator<Map.Entry<K, V>> iterator()
  {
    return new zzfke(zza, null);
  }
  
  public boolean remove(Object paramObject)
  {
    paramObject = (Map.Entry)paramObject;
    if (contains(paramObject))
    {
      zza.remove(paramObject.getKey());
      return true;
    }
    return false;
  }
  
  public int size()
  {
    return zza.size();
  }
}

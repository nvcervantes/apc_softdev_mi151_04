package com.google.maps.android.data.geojson;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class BiMultiMap<K>
  extends HashMap<K, Object>
{
  private final Map<Object, K> mValuesToKeys = new HashMap();
  
  public BiMultiMap() {}
  
  public void clear()
  {
    super.clear();
    mValuesToKeys.clear();
  }
  
  public BiMultiMap<K> clone()
  {
    BiMultiMap localBiMultiMap = new BiMultiMap();
    localBiMultiMap.putAll((Map)super.clone());
    return localBiMultiMap;
  }
  
  public K getKey(Object paramObject)
  {
    return mValuesToKeys.get(paramObject);
  }
  
  public Object put(K paramK, Object paramObject)
  {
    mValuesToKeys.put(paramObject, paramK);
    return super.put(paramK, paramObject);
  }
  
  public Object put(K paramK, Collection paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      mValuesToKeys.put(localObject, paramK);
    }
    return super.put(paramK, paramCollection);
  }
  
  public void putAll(Map<? extends K, ?> paramMap)
  {
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      put(localEntry.getKey(), localEntry.getValue());
    }
  }
  
  public Object remove(Object paramObject)
  {
    paramObject = super.remove(paramObject);
    if ((paramObject instanceof Collection))
    {
      Iterator localIterator = ((Collection)paramObject).iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        mValuesToKeys.remove(localObject);
      }
    }
    mValuesToKeys.remove(paramObject);
    return paramObject;
  }
}

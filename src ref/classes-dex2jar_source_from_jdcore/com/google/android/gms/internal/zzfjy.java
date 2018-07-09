package com.google.android.gms.internal;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzfjy<K extends Comparable<K>, V>
  extends AbstractMap<K, V>
{
  private final int zza;
  private List<zzfkd> zzb;
  private Map<K, V> zzc;
  private boolean zzd;
  private volatile zzfkf zze;
  private Map<K, V> zzf;
  
  private zzfjy(int paramInt)
  {
    zza = paramInt;
    zzb = Collections.emptyList();
    zzc = Collections.emptyMap();
    zzf = Collections.emptyMap();
  }
  
  private final int zza(K paramK)
  {
    int j = zzb.size() - 1;
    if (j >= 0)
    {
      i = paramK.compareTo((Comparable)((zzfkd)zzb.get(j)).getKey());
      if (i > 0) {
        return -(j + 2);
      }
      if (i == 0) {
        return j;
      }
    }
    int i = 0;
    while (i <= j)
    {
      int k = (i + j) / 2;
      int m = paramK.compareTo((Comparable)((zzfkd)zzb.get(k)).getKey());
      if (m < 0) {
        j = k - 1;
      } else if (m > 0) {
        i = k + 1;
      } else {
        return k;
      }
    }
    return -(i + 1);
  }
  
  static <FieldDescriptorType extends zzfhs<FieldDescriptorType>> zzfjy<FieldDescriptorType, Object> zza(int paramInt)
  {
    return new zzfjz(paramInt);
  }
  
  private final V zzc(int paramInt)
  {
    zze();
    Object localObject = ((zzfkd)zzb.remove(paramInt)).getValue();
    if (!zzc.isEmpty())
    {
      Iterator localIterator = zzf().entrySet().iterator();
      zzb.add(new zzfkd(this, (Map.Entry)localIterator.next()));
      localIterator.remove();
    }
    return localObject;
  }
  
  private final void zze()
  {
    if (zzd) {
      throw new UnsupportedOperationException();
    }
  }
  
  private final SortedMap<K, V> zzf()
  {
    zze();
    if ((zzc.isEmpty()) && (!(zzc instanceof TreeMap)))
    {
      zzc = new TreeMap();
      zzf = ((TreeMap)zzc).descendingMap();
    }
    return (SortedMap)zzc;
  }
  
  public void clear()
  {
    zze();
    if (!zzb.isEmpty()) {
      zzb.clear();
    }
    if (!zzc.isEmpty()) {
      zzc.clear();
    }
  }
  
  public boolean containsKey(Object paramObject)
  {
    paramObject = (Comparable)paramObject;
    return (zza(paramObject) >= 0) || (zzc.containsKey(paramObject));
  }
  
  public Set<Map.Entry<K, V>> entrySet()
  {
    if (zze == null) {
      zze = new zzfkf(this, null);
    }
    return zze;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzfjy)) {
      return super.equals(paramObject);
    }
    paramObject = (zzfjy)paramObject;
    int j = size();
    if (j != paramObject.size()) {
      return false;
    }
    int k = zzc();
    if (k != paramObject.zzc()) {
      return entrySet().equals(paramObject.entrySet());
    }
    int i = 0;
    while (i < k)
    {
      if (!zzb(i).equals(paramObject.zzb(i))) {
        return false;
      }
      i += 1;
    }
    if (k != j) {
      return zzc.equals(zzc);
    }
    return true;
  }
  
  public V get(Object paramObject)
  {
    paramObject = (Comparable)paramObject;
    int i = zza(paramObject);
    if (i >= 0) {
      return ((zzfkd)zzb.get(i)).getValue();
    }
    return zzc.get(paramObject);
  }
  
  public int hashCode()
  {
    int k = zzc();
    int j = 0;
    int i = 0;
    while (j < k)
    {
      i += ((zzfkd)zzb.get(j)).hashCode();
      j += 1;
    }
    j = i;
    if (zzc.size() > 0) {
      j = i + zzc.hashCode();
    }
    return j;
  }
  
  public V remove(Object paramObject)
  {
    zze();
    paramObject = (Comparable)paramObject;
    int i = zza(paramObject);
    if (i >= 0) {
      return zzc(i);
    }
    if (zzc.isEmpty()) {
      return null;
    }
    return zzc.remove(paramObject);
  }
  
  public int size()
  {
    return zzb.size() + zzc.size();
  }
  
  public final V zza(K paramK, V paramV)
  {
    zze();
    int i = zza(paramK);
    if (i >= 0) {
      return ((zzfkd)zzb.get(i)).setValue(paramV);
    }
    zze();
    if ((zzb.isEmpty()) && (!(zzb instanceof ArrayList))) {
      zzb = new ArrayList(zza);
    }
    i = -(i + 1);
    if (i >= zza) {
      return zzf().put(paramK, paramV);
    }
    if (zzb.size() == zza)
    {
      zzfkd localZzfkd = (zzfkd)zzb.remove(zza - 1);
      zzf().put((Comparable)localZzfkd.getKey(), localZzfkd.getValue());
    }
    zzb.add(i, new zzfkd(this, paramK, paramV));
    return null;
  }
  
  public void zza()
  {
    if (!zzd)
    {
      Map localMap;
      if (zzc.isEmpty()) {
        localMap = Collections.emptyMap();
      } else {
        localMap = Collections.unmodifiableMap(zzc);
      }
      zzc = localMap;
      if (zzf.isEmpty()) {
        localMap = Collections.emptyMap();
      } else {
        localMap = Collections.unmodifiableMap(zzf);
      }
      zzf = localMap;
      zzd = true;
    }
  }
  
  public final Map.Entry<K, V> zzb(int paramInt)
  {
    return (Map.Entry)zzb.get(paramInt);
  }
  
  public final boolean zzb()
  {
    return zzd;
  }
  
  public final int zzc()
  {
    return zzb.size();
  }
  
  public final Iterable<Map.Entry<K, V>> zzd()
  {
    if (zzc.isEmpty()) {
      return zzfka.zza();
    }
    return zzc.entrySet();
  }
}
